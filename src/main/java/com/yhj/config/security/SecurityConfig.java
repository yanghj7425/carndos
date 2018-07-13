package com.yhj.config.security;

import com.yhj.config.mybatis.MyBatisConfig;
import com.yhj.security.CustomFilterSecurityInterceptor;
import com.yhj.security.CustomSecurityMetadataSource;
import com.yhj.security.CustomSuccessHandler;
import com.yhj.security.CustomAccessDecisionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
@Import(MyBatisConfig.class)
@ComponentScan(basePackages = {"com.yhj.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").successHandler(customSuccessHandler)
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and().exceptionHandling().accessDeniedPage("/error")
                .and()
                .csrf();

    }
}
