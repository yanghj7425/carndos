package com.yhj.config.core;

import com.yhj.config.security.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Configuration
@Import({SecurityConfig.class})
@ComponentScan(basePackages = {"com.yhj.web.*"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
public class RootConfig {


}
