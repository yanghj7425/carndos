package com.yhj.config.security;

import com.yhj.config.mybatis.MyBatisConfig;
import com.yhj.modules.authentication.filter.PreAuthFilter;
import com.yhj.modules.authentication.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@Import(MyBatisConfig.class)
@Configuration
@CrossOrigin
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomLoginFailureHandler customLoginFailureHandler;

    @Autowired
    private UserDetailsService customUserDetailService;

    @Autowired
    private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomLogoutHandler customLogoutHandler;


    /**
     * @param http 资源拦截
     * @throws Exception
     */
    //@formatter:offs
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //加入自定义过滤器
        http.addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        http.addFilterBefore(preAuthFilter(), AbstractPreAuthenticatedProcessingFilter.class);

        http.formLogin()
                .loginPage("/sys/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customLoginSuccessHandler)
                .failureHandler(customLoginFailureHandler);
        http.logout().logoutUrl("/sys/logout")
                .logoutSuccessHandler(customLogoutHandler);
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        http.csrf().disable();

        http.addFilterBefore(corsFilter(), CorsFilter.class);

    }

    @Bean
    public UserDetailsByNameServiceWrapper userDetailsServiceWrapper(UserDetailsService customUserDetailService) {
        UserDetailsByNameServiceWrapper wrapper = new UserDetailsByNameServiceWrapper();
        wrapper.setUserDetailsService(customUserDetailService);
        return wrapper;
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper(customUserDetailService));
        provider.setThrowExceptionWhenTokenRejected(true);
        return provider;
    }

    @Bean
    public AuthenticationManager preAuthenticationManager() {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(preAuthProvider());
        return new ProviderManager(providers);
    }


    @Bean
    public PreAuthFilter preAuthFilter() {
        PreAuthFilter filter = new PreAuthFilter();
        filter.setAuthenticationManager(preAuthenticationManager());
        //认证异常处理器
        filter.setAuthenticationFailureHandler(customLoginFailureHandler);
        //认证成功处理器
        filter.setAuthenticationSuccessHandler(null);
        //处理验证每一个请求的 token
        filter.setCheckForPrincipalChanges(true);
        return filter;
    }


    /**
     * @return
     * @desc 处理跨域问题
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
