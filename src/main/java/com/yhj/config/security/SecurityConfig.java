package com.yhj.config.security;

import com.yhj.config.mybatis.MyBatisConfig;
import com.yhj.security.login.CustomAuthenticationProvider;
import com.yhj.security.login.CustomLoginFailureHandler;
import com.yhj.security.login.CustomLoginSuccessHandler;
import com.yhj.security.res.CustomFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
@Import(MyBatisConfig.class)
@ComponentScan(basePackages = {"com.yhj.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Autowired
    private CustomLoginFailureHandler customLoginFailureHandler;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;


    @Autowired
    private CustomFilterSecurityInterceptor filterSecurityInterceptor;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);
        http
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")//form表单POST请求url提交地址，默认为/login
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(customLoginSuccessHandler)
                .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout")
                    .deleteCookies("JSESSIONID")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/error");

    }
}
