package com.yhj.security.login;

import com.yhj.web.dao.sys.SysUserMapper;
import com.yhj.web.entity.sys.SysRole;
import com.yhj.web.entity.sys.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private SysUserMapper sysUserMapper;


    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String sysUserName) {
        SysUser sysUser = sysUserMapper.querySysUserByName(sysUserName);
        if (sysUser == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("SysUser: {}", sysUser);
        }

        return new org.springframework.security.core.userdetails.User(sysUser.getUserName(), sysUser.getUserPasswd(),
                sysUser.getUserStatus().equals(SysUser.ACTIVE), true, true, true, getGrantedAuthorities(sysUser));
    }

    private List<GrantedAuthority> getGrantedAuthorities(SysUser sysUser) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<SysRole> roles = sysUser.getRoles();
        for (SysRole role : roles) {
            if (logger.isDebugEnabled()) {
                logger.debug("sysUserRole:\t {} \t {}", sysUser.getUserName(), role.getRoleName());
            }
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }


}
