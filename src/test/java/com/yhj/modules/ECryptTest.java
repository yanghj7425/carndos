package com.yhj.modules;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ECryptTest {

    @Test
    public void password() {

        //  $2a$10$pJKdP0V6ak6tOx6cL2rgJOdQgcI3.8mCVKDW/RNU04HXhvClpHYfq
        String password = "admin";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));

    }


}
