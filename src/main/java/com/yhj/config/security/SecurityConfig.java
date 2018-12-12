package com.yhj.config.security;

import com.yhj.config.mybatis.MyBatisConfig;
import com.yhj.modules.authonzation.filter.PreAuthFilter;
import com.yhj.modules.authonzation.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@Import(MyBatisConfig.class)
@ComponentScan(basePackages = {"com.yhj.config.security"})
@Configuration
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
    private CustomFilterSecurityInterceptor filterSecurityInterceptor;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    /**
     * @param http 资源拦截
     * @throws Exception
     */
    //@formatter:offs
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //加入自定义过滤器
        http.addFilterBefore(preAuthFilter(), AbstractPreAuthenticatedProcessingFilter.class);
        http.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);
        http.formLogin()
                .loginPage("/sys/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customLoginSuccessHandler)
                .failureHandler(customLoginFailureHandler);
        http.logout()
                .logoutUrl("/sys/logout")
                .logoutSuccessUrl("/sys/logout")
                .logoutSuccessHandler(new CustomLogoutHandler());
        http.exceptionHandling()
                .accessDeniedPage("/denied");
        http.csrf().disable();

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
        return filter;
    }

}
