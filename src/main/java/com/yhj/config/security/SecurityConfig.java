package com.yhj.config.security;

import com.yhj.config.mybatis.MyBatisConfig;
import com.yhj.modules.authonzation.security.CustomFilterSecurityInterceptor;
import com.yhj.modules.authonzation.filter.PreAuthFilter;
import com.yhj.modules.authonzation.security.CustomAuthenticationProvider;
import com.yhj.modules.authonzation.security.CustomLoginFailureHandler;
import com.yhj.modules.authonzation.security.CustomLoginSuccessHandler;
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
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import javax.sql.DataSource;
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
    private DataSource dataSource;

    @Autowired
    private CustomFilterSecurityInterceptor filterSecurityInterceptor;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }


    @Bean
    public RememberMeServices rememberMeServices() {
        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
        // 此处需要设置数据源，否则无法从数据库查询验证信息
        rememberMeTokenRepository.setDataSource(dataSource);

        // 此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
        // rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices("INTERNAL_SECRET_KEY", customUserDetailService, rememberMeTokenRepository);
        rememberMeServices.setTokenValiditySeconds(300);

        // 该参数不是必须的，默认值为 "remember-me", 但如果设置必须和页面复选框的 name 一致
        rememberMeServices.setParameter("remember-me");
        return rememberMeServices;
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
        http
//                .authorizeRequests()
//                    .antMatchers("/home**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//                .and()
                .formLogin()
                .loginPage("/sys/login")
                .loginProcessingUrl("/sys/login")//form表单POST请求url提交地址，默认为/login
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customLoginSuccessHandler)
                .failureHandler(customLoginFailureHandler)
                .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices())
                .key("INTERNAL_SECRET_KEY")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedPage("/denied");

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
