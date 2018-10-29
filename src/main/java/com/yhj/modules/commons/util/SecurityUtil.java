package com.yhj.modules.commons.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static Object getPrincipal (){
        return  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
