package com.yhj.modules.commons.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static Authentication getAuthentication (){
        return  SecurityContextHolder.getContext().getAuthentication();
    }
}
