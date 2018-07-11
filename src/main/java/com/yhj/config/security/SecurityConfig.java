package com.yhj.config.security;

import com.alibaba.druid.pool.DruidDataSource;
import com.yhj.config.mybatis.MyBatisConfig;
import com.yhj.web.controller.security.CustomSecurityMetadataSource;
import com.yhj.web.controller.security.CustomSuccessHandler;
import com.yhj.web.dao.res.CustomAccessDecisionManager;
import com.yhj.web.dao.res.JdbcRequestMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    @Qualifier("customUserDetailService")
    UserDetailsService userDetailsService;


    private CustomSecurityMetadataSource customSecurityMetadataSource;


    private CustomAccessDecisionManager customAccessDecisionManager;


    @Autowired
    private DruidDataSource dataSource;


    public FilterSecurityInterceptor customFilterSecurityInterceptor() {
        FilterSecurityInterceptor fsi = new FilterSecurityInterceptor();
        fsi.setAccessDecisionManager(customAccessDecisionManager);
        fsi.setSecurityMetadataSource(customSecurityMetadataSource);

        return fsi;
    }


    public CustomSecurityMetadataSource customSecurityMetadataSource() {
        CustomSecurityMetadataSource source = new CustomSecurityMetadataSource();
        source.setBuilder(builder());
        return source;
    }


    public JdbcRequestMapBuilder builder() {
        JdbcRequestMapBuilder builder = new JdbcRequestMapBuilder();
        builder.setDataSource(dataSource);
        builder.setResourceQuerySQL(
                "SELECT " +
                        "    res.res_url, role.role_name " +
                        "FROM " +
                        "    res_role rr " +
                        "        LEFT JOIN " +
                        "    resource res ON rr.res_id = res.id " +
                        "        LEFT JOIN " +
                        "    role ON rr.role_id = role.id;"
        );
        return builder;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //   auth.userDetailsService(userDetailsService);
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("dba").roles("DBA");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
                .and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").successHandler(customSuccessHandler)
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and().exceptionHandling().accessDeniedPage("/error")
                .and()
                .csrf();
    }
}
