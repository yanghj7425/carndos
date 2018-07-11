package com.yhj.config.core;

import com.yhj.config.security.SecurityConfig;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration
@Import({SecurityConfig.class})
@ComponentScan(basePackages = {"com.yhj.web.*"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class RootConfig {



}
