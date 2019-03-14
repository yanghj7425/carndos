package com.carndos.config.activity;

import com.carndos.config.mybatis.MyBatisConfig;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import({MyBatisConfig.class})
public class ActivityConfig {

    private DataSource dataSource;


    private PlatformTransactionManager transactionManager;


    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration engineConfiguration = new SpringProcessEngineConfiguration();

        // set activity datasource
        engineConfiguration.setDataSource(dataSource);

        //set transaction manager
        engineConfiguration.setTransactionManager(transactionManager);

        //
        engineConfiguration.setDatabaseSchemaUpdate("true");

        engineConfiguration.setJobExecutorActivate(true);

        // store all history information
        engineConfiguration.setHistory("full");

        engineConfiguration.setProcessDefinitionCacheLimit(10);


        return engineConfiguration;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
