package com.yhj.modules.authentication.filter;

import com.yhj.modules.authentication.except.CustomAccessDeniedException;
import com.yhj.modules.authentication.except.CustomInvalidTokenException;
import com.yhj.modules.authentication.utils.JWTUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class PreAuthFilter extends GenericFilterBean
        implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher = null;
    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;
    private AuthenticationManager authenticationManager = null;
    private boolean checkForPrincipalChanges;
    private boolean invalidateSessionOnPrincipalChange = true;
    private AuthenticationSuccessHandler authenticationSuccessHandler = null;
    private AuthenticationFailureHandler authenticationFailureHandler = null;


    private boolean isUnsuccessfulAuthentication = true;


    public PreAuthFilter() {
        authenticationDetailsSource = new WebAuthenticationDetailsSource();
    }

    private Object getPreAuthenticatedPrincipal(HttpServletRequest request) throws CustomInvalidTokenException {
        String token = request.getHeader("X-Token");
        if (token == null) {
            return null;
        }
        String userName = JWTUtils.decoder(token, String.class);
        if (userName == null) {
            throw new CustomInvalidTokenException("非法的 Token");
        }
        return userName;
    }

    private Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "";
    }


    /**
     * Check whether all required properties have been set.
     */
    @Override
    public void afterPropertiesSet() {
        try {
            super.afterPropertiesSet();
        } catch (Exception e) {
            // convert to RuntimeException for passivity on afterPropertiesSet signature
            throw new RuntimeException(e);
        }
        Assert.notNull(authenticationManager, "An AuthenticationManager must be set");
    }

    /**
     * Try to authenticate a pre-authenticated user with Spring Security if the user has
     * not yet been authenticated.
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        isUnsuccessfulAuthentication = true;
        if (logger.isDebugEnabled()) {
            logger.debug("Checking secure context token: "
                    + SecurityContextHolder.getContext().getAuthentication());
        }

        if (requiresAuthentication((HttpServletRequest) request)) {
            doAuthenticate((HttpServletRequest) request, (HttpServletResponse) response);
        }
        if (isUnsuccessfulAuthentication) {
            try {
                chain.doFilter(request, response);
            } catch (Exception e) {
                authenticationFailureHandler.onAuthenticationFailure((HttpServletRequest) request, (HttpServletResponse) response, new CustomAccessDeniedException(e.getMessage(), e));
            }
        }

    }

    /**
     * Determines if the current principal has changed. The default implementation tries
     *
     * <ul>
     * <li>If the {@link #getPreAuthenticatedPrincipal(HttpServletRequest)} is a String, the {@link Authentication#getName()} is compared against the pre authenticated principal</li>
     * <li>Otherwise, the {@link #getPreAuthenticatedPrincipal(HttpServletRequest)} is compared against the {@link Authentication#getPrincipal()}
     * </ul>
     *
     * <p>
     * Subclasses can override this method to determine when a principal has changed.
     * </p>
     *
     * @param request
     * @param currentAuthentication
     * @return true if the principal has changed, else false
     */
    protected boolean principalChanged(HttpServletRequest request, Authentication currentAuthentication) {

        Object principal;
        try {
            principal = getPreAuthenticatedPrincipal(request);
        } catch (CustomInvalidTokenException e) {
            return true;
        }

        if ((principal instanceof String) && currentAuthentication.getName().equals(principal)) {
            return false;
        }

        if (principal != null && principal.equals(currentAuthentication.getPrincipal())) {
            return false;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Pre-authenticated principal has changed to " + principal + " and will be reauthenticated");
        }
        return true;
    }

    /**
     * Do the actual authentication for a pre-authenticated user.
     */
    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Authentication authResult;
        try {
            Object principal = getPreAuthenticatedPrincipal(request);
            Object credentials = getPreAuthenticatedCredentials(request);

            if (principal == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("No pre-authenticated principal found in request");
                }
                return;
            }

            if (logger.isDebugEnabled()) {
                logger.debug("preAuthenticatedPrincipal = " + principal
                        + ", trying to authenticate");
            }

            PreAuthenticatedAuthenticationToken authRequest = new PreAuthenticatedAuthenticationToken(
                    principal, credentials);
            authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
            authResult = authenticationManager.authenticate(authRequest);
            successfulAuthentication(request, response, authResult);
        } catch (Exception e) {
            unsuccessfulAuthentication(request, response, (AuthenticationException) e);
        }
    }

    private boolean requiresAuthentication(HttpServletRequest request) {
        Authentication currentUser = SecurityContextHolder.getContext()
                .getAuthentication();

        if (currentUser == null) {
            return true;
        }

        if (!checkForPrincipalChanges) {
            return false;
        }

        if (!principalChanged(request, currentUser)) {
            return false;
        }

        logger.debug("Pre-authenticated principal has changed and will be reauthenticated");

        if (invalidateSessionOnPrincipalChange) {
            SecurityContextHolder.clearContext();

            HttpSession session = request.getSession(false);

            if (session != null) {
                logger.debug("Invalidating existing session");
                session.invalidate();
                request.getSession();
            }
        }
        return true;
    }

    /**
     * Puts the <code>Authentication</code> instance returned by the authentication
     * manager into the secure context.
     */
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success: " + authResult);
        }
        SecurityContextHolder.getContext().setAuthentication(authResult);
        // Fire event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(
                    authResult, this.getClass()));
        }

        if (authenticationSuccessHandler != null) {
            authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
        }
        isUnsuccessfulAuthentication = true;
    }

    /**
     * Ensures the authentication object in the secure context is set to null when
     * authentication fails.
     * <p>
     * Caches the failure exception as a request attribute
     */
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("Cleared security context due to exception", failed);
        }
        request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, failed);

        if (authenticationFailureHandler != null) {
            authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
        }
        isUnsuccessfulAuthentication = false;
    }

    /**
     * @param anApplicationEventPublisher The ApplicationEventPublisher to use
     */
    public void setApplicationEventPublisher(
            ApplicationEventPublisher anApplicationEventPublisher) {
        this.eventPublisher = anApplicationEventPublisher;
    }

    /**
     * @param authenticationDetailsSource The AuthenticationDetailsSource to use
     */
    public void setAuthenticationDetailsSource(
            AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        Assert.notNull(authenticationDetailsSource,
                "AuthenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    protected AuthenticationDetailsSource<HttpServletRequest, ?> getAuthenticationDetailsSource() {
        return authenticationDetailsSource;
    }

    /**
     * @param authenticationManager The AuthenticationManager to use
     */
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * If set, the pre-authenticated principal will be checked on each request and
     * compared against the name of the current <tt>Authentication</tt> object. A check to
     * determine if {@link Authentication#getPrincipal()} is equal to the principal will
     * also be performed. If a change is detected, the user will be reauthenticated.
     *
     * @param checkForPrincipalChanges
     */
    public void setCheckForPrincipalChanges(boolean checkForPrincipalChanges) {
        this.checkForPrincipalChanges = checkForPrincipalChanges;
    }

    /**
     * If <tt>checkForPrincipalChanges</tt> is set, and a change of principal is detected,
     * determines whether any existing session should be invalidated before proceeding to
     * authenticate the new principal.
     *
     * @param invalidateSessionOnPrincipalChange <tt>false</tt> to retain the existing
     *                                           session. Defaults to <tt>true</tt>.
     */
    public void setInvalidateSessionOnPrincipalChange(
            boolean invalidateSessionOnPrincipalChange) {
        this.invalidateSessionOnPrincipalChange = invalidateSessionOnPrincipalChange;
    }

    /**
     * Sets the strategy used to handle a successful authentication.
     */
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    /**
     * Sets the strategy used to handle a failed authentication.
     */
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }


}
