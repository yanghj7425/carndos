package com.yhj.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:dataSource.properties"})
public class MyBatisConfig {


    @Autowired
    private Environment environment;

    /**
     * @return
     *
     * @description 配置数据源
     */
    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("mysql.database.driverName"));
        dataSource.setUrl(environment.getRequiredProperty("mysql.database.url"));
        dataSource.setUsername(environment.getRequiredProperty("mysql.database.user"));
        dataSource.setPassword(environment.getRequiredProperty("mysql.database.password"));
        return dataSource;
    }

    /**
     * @param dataSource
     *
     * @return
     *
     * @description 配置 MyBatis sessionFactory
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DruidDataSource dataSource) {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        //添加数据源
        sessionFactoryBean.setDataSource(dataSource);
        //mapper 文件
        sessionFactoryBean.setMapperLocations(mapperLocations());

        //分页插件
        Interceptor interceptor = new PageHelper(); // 设置 分页拦截器
        Properties properties = new Properties();// 分页拦截器配置内容
        properties.setProperty("dialect", "mysql");
        interceptor.setProperties(properties);

        //添加分页插件
        sessionFactoryBean.setPlugins(new Interceptor[]{interceptor});
        return sessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }


    private Resource[] mapperLocations() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            return resolver.getResources("classpath:mysql/mapper/*.xml");
        } catch (IOException e) {
            throw new IllegalArgumentException("找不到指定的 mapper 文件");
        }
    }

}
