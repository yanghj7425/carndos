package com.yhj.config.core;

import com.yhj.config.security.SecurityConfig;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import({SecurityConfig.class})
@ComponentScan(basePackages = {"com.yhj.web.*"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
public class RootConfig {


    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }

}
