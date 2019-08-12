package com.ggr.blog.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean(name = "mysqlDataSource")
    public DataSource getDataSource() {
        DruidDataSource dataSource=  new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setName("mysqlDataSource");
        return dataSource;
    }

    @Bean(name = "oracleDataSource")
    public DataSource getDataSource2(){
        DruidDataSource dataSource=  new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource2.url"));
        dataSource.setUsername(env.getProperty("spring.datasource2.username"));
        dataSource.setPassword(env.getProperty("spring.datasource2.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource2.driver-class-name"));
        dataSource.setName("oracleDataSource");
        return dataSource;
    }
}
