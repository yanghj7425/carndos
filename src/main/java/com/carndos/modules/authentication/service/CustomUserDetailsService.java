package com.carndos.modules.authentication.service;

import com.carndos.modules.user.dao.SysUserMapper;
import com.carndos.modules.user.entity.SysUser;
import com.carndos.modules.user.entity.SysUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customUserDetailService")
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());




    @Resource
    private SysUserMapper sysUserMapper;


    /**
     * @param sysUserName userName for authentication
     * @return
     * @description load user`s info and  return a UserDetail Object that construct by  username, password and user active status
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String sysUserName) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.querySysUserByName(sysUserName);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("SysUser: {}", sysUser);
        }

        return new org.springframework.security.core.userdetails.User(sysUser.getUserName(), sysUser.getUserPasswd(),
                sysUser.getUserStatus().equals(SysUser.ACTIVE), true, true, true, getGrantedAuthorities(sysUser));
    }

    /**
     * @param sysUser
     * @return
     * @description push user`s roles information into a List, than return it
     */
    private List<GrantedAuthority> getGrantedAuthorities(SysUser sysUser) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<SysUserRole> roles = sysUser.getRoles();
        for (SysUserRole role : roles) {
            if (logger.isDebugEnabled()) {
                logger.debug("sysUserRole:\t {} \t {}", role.getUserName(), role.getRoleName());
            }
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }


}
