package com.yhj.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:carndos.properties"})
public class MyBatisConfig implements EnvironmentAware {

    private Environment environment;


    /**
     * @return dataSource
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
     * 事务管理
     * @param dataSource
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }

    /**
     * @param dataSource
     * @return sessionFactoryBean
     * @description 配置 MyBatis sessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DruidDataSource dataSource) {
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

        try {
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yhj.modules");
        Properties properties = new Properties();
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        mapperScannerConfigurer.setMarkerInterface(BaseMapper.class);
        return mapperScannerConfigurer;
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    private Resource[] mapperLocations() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            return resolver.getResources("classpath:mapper/**/*.xml");
        } catch (IOException e) {
            throw new IllegalArgumentException("找不到指定的 mapper 文件");
        }
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
