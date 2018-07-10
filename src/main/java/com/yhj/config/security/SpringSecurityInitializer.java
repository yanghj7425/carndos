package com.yhj.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 创建一个扩展 AbstractSecurityWebApplicationInitializer 的一个类, 它将会自动地加载 springSecurityFilterChain
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
