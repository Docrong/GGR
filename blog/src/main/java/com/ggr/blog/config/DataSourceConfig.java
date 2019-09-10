package com.ggr.blog.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 配置数据源oracle,mysql,这里配置所有数据源
 * @author gr
 * @date 2019-08-27 09:32:45
 */

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Autowired
    private MysqlConfig mysqlConfig;

    @Bean(name = "mysqlDataSource")
    public DataSource getDataSource() {
        System.out.println("==== init DS1 ====");
        DruidDataSource dataSource=  new DruidDataSource();
        dataSource.setUrl(mysqlConfig.getUrl());
//      获取自定义配置文件datasource.properties中的数据,git忽略提交,这样不用每次更改配置文件
//      dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setName("mysqlDataSource");
        return dataSource;
    }
    @Bean(name = "JdbcTemplate1")
    public JdbcTemplate JdbcTemplate1(
            @Qualifier("mysqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    @Bean(name = "mysqlDataSource2")
    public DataSource getDataSource2(){
        System.out.println("==== init DS2 ====");
        DruidDataSource dataSource=  new DruidDataSource();
        dataSource.setUrl(mysqlConfig.getUrl2());
        dataSource.setUsername(env.getProperty("spring.datasource2.username"));
        dataSource.setPassword(env.getProperty("spring.datasource2.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setName("mysqlDataSource2");
        return dataSource;
    }

    @Bean(name = "JdbcTemplate2")
    public JdbcTemplate JdbcTemplate2(
            @Qualifier("mysqlDataSource2") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
