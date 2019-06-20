package com.carndos.config.core;

import com.carndos.config.security.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({SecurityConfig.class})
@ComponentScan(basePackages = {"com.carndos.modules.*"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
public class RootConfig {


}
