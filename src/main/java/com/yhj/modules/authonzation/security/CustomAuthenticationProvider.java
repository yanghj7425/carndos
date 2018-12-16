package com.yhj.modules.authonzation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component(value = "customAuthenticationProvider")
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {


    @Autowired
    public void setUserDetailService(UserDetailsService customUserDetailService) {
        super.setUserDetailsService(customUserDetailService);
    }
}
