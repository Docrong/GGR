package com.work.ggr.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置主数据库
 *
 * @author : gr
 * @date : 2019/8/28 17:12
 */
@Configuration
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven /
@EnableJpaRepositories(basePackages = {"com.work.ggr.repository"},
        entityManagerFactoryRef = "entityManagerFactoryMain",
        transactionManagerRef = "transactionManagerMain")
public class RepositoryConfigMain {

    private Log log= LogFactory.getLog(this.getClass());

    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource dataSource;

    @Bean(name = "entityManagerFactoryMain")
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.work.ggr.model");
        factory.setDataSource(dataSource);


        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        jpaProperties.put("hibernate.jdbc.batch_size",50);
        jpaProperties.put("hibernate.show_sql",true);

        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet();
        System.out.println("entityManagerFactory:"+factory.getObject());
        return factory.getObject();
    }

    @Bean(name = "transactionManagerMain")
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        System.out.println("txManager:"+txManager);
        System.out.println(">>>>>>>>>>transactionManagerMain:"+txManager.getClass().getName());
        return txManager;
    }
    @Bean
    public Object testBean(@Qualifier("transactionManagerMain") PlatformTransactionManager platformTransactionManager) {
        log.info(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
}
