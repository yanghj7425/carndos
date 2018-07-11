package com.yhj.config.mybaties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.yhj.web.dao"},includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Repository.class)})
@PropertySource(value = {"classpath:dataSource.properties"})
public class DBConfig {


    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("mysql.database.driverName"));
        dataSource.setUrl(environment.getRequiredProperty("mysql.database.url"));
        dataSource.setUsername(environment.getRequiredProperty("mysql.database.user"));
        dataSource.setPassword(environment.getRequiredProperty("mysql.database.password"));
        return dataSource;
    }



    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){


    }
}
