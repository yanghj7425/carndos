package com.yhj.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            System.out.println("can`t redirect!");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        String retUrl;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }
        if (isDba(roles)) {
            retUrl = "/dba";
        } else if (isAdmin(roles)) {
            retUrl = "/admin";
        } else if (isUser(roles)) {
            retUrl = "/home";
        } else {
            retUrl = "/error";
        }
        return retUrl;
    }

    private boolean isUser(List<String> roles) {

        return hasRightRoles(roles, "ROLE_USER");
    }

    private boolean isAdmin(List<String> roles) {
        return hasRightRoles(roles, "ROLE_ADMIN");
    }

    private boolean isDba(List<String> roles) {
        return hasRightRoles(roles, "ROLE_DBA");
    }

    private boolean hasRightRoles(List<String> roles, String roleToken) {
        return roles.contains(roleToken);
    }

    @Override
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
