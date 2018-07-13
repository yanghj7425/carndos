package com.yhj.security;

import com.yhj.web.dao.sys.SysUserMapper;
import com.yhj.web.dao.sys.SysUserRoleMapper;
import com.yhj.web.entity.sys.SysUser;
import com.yhj.web.entity.sys.SysUserRole;
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

@Service(value = "userDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    private final Integer Active = 1;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String sysUserName)
            throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.querySysUserByName(sysUserName);
        System.out.println("User : " + sysUser);
        if (sysUser == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(sysUser.getUserName(), sysUser.getUserPasswd(),
                sysUser.getUserStatus().equals(Active), true, true, true, getGrantedAuthorities(sysUser));
    }

    private List<GrantedAuthority> getGrantedAuthorities(SysUser sysUser) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<SysUserRole> roles = sysUserRoleMapper.querySysUserRoleByName(sysUser);
        for (SysUserRole sysUserRole : roles) {
            System.out.println("sysUserRole : " + sysUserRole.getUserName() + "\t" + sysUserRole.getRoleName());
            authorities.add(new SimpleGrantedAuthority(sysUserRole.getRoleName()));
        }
        System.out.print("authorities :" + authorities);
        return authorities;
    }


}
