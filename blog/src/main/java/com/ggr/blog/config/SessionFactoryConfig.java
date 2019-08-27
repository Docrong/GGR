package com.ggr.blog.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

/**
 * @author gr
 * @date 2019-08-27 09:35:21
 * 配置sessionFactory
 */
@Configuration
public class SessionFactoryConfig {
    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier(value = "mysqlDataSource")
    DataSource dataSource;

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean createLocalSessionFactoryBean() {
        LocalSessionFactoryBean sqlSessionFactoryBean = new LocalSessionFactoryBean();
        sqlSessionFactoryBean.setPackagesToScan("com.ggr.blog.model");
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;

    }

    @Autowired
    @Qualifier(value = "oracleDataSource")
    DataSource dataSource2;

    @Bean(name = "sessionFactory2")
    public LocalSessionFactoryBean createLocalSessionFactoryBean2() {
        LocalSessionFactoryBean sqlSessionFactoryBean = new LocalSessionFactoryBean();
        sqlSessionFactoryBean.setPackagesToScan("com.ggr.blog.model");
        sqlSessionFactoryBean.setDataSource(dataSource2);
        return sqlSessionFactoryBean;

    }

}
