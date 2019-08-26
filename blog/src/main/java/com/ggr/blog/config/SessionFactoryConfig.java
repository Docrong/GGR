package com.ggr.blog.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SessionFactoryConfig {
    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier(value = "mysqlDataSource")
    DataSource dataSource;

    @Bean(name="sessionFactory")
    public LocalSessionFactoryBean createLocalSessionFactoryBean() {
        LocalSessionFactoryBean sqlSessionFactoryBean = new LocalSessionFactoryBean();
        sqlSessionFactoryBean.setPackagesToScan("com.ggr.blog.model");
        sqlSessionFactoryBean.setDataSource(dataSource);



        return sqlSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory((SessionFactory) createLocalSessionFactoryBean());
        return txManager;
    }

}
