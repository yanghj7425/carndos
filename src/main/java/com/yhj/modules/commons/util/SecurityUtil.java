package com.yhj.modules.commons.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SecurityUtil {
    private SecurityUtil() {
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static List<String> getRoles() {
        Collection<? extends GrantedAuthority> auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> list = new ArrayList<>();
        Iterator<? extends GrantedAuthority> itr = auth.iterator();
        while (itr.hasNext()) {
            GrantedAuthority each = itr.next();
            list.add(each.getAuthority());
        }
        return list;
    }
}
