package com.yhj.config.security;

import com.yhj.config.mybatis.MyBatisConfig;
import com.yhj.security.login.CustomAuthenticationProvider;
import com.yhj.security.login.CustomLoginFailureHandler;
import com.yhj.security.login.CustomLoginSuccessHandler;
import com.yhj.security.res.CustomFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
@Import(MyBatisConfig.class)
@ComponentScan(basePackages = {"com.yhj.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomLoginFailureHandler customLoginFailureHandler;

    @Autowired
    private CustomFilterSecurityInterceptor filterSecurityInterceptor;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * @param http 资源拦截
     *
     * @throws Exception
     */
    //@formatter:offs
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //加入自定义过滤器
        http.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);
        http
//                .authorizeRequests()
//                    .antMatchers("/home**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")//form表单POST请求url提交地址，默认为/login
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(customLoginSuccessHandler)
                 //   .failureHandler(customLoginFailureHandler)
                .and()
                .logout()
                    .logoutSuccessUrl("/login?logout")
                    .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/denied");

    }
}
