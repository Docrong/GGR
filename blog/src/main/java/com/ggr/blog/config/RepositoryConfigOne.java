package com.ggr.blog.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
 * @date: 2019/9/10 23:21
 * @author: gr
 */
@Configuration
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableJpaRepositories(basePackages = {"com.ggr.blog.repository"},
        entityManagerFactoryRef = "entityManagerFactoryOne",
        transactionManagerRef = "transactionManagerOne")
public class RepositoryConfigOne {
    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    @Qualifier("mysqlDataSource")

    private DataSource dataSource;

    @Bean(name = "entityManagerFactoryOne")
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.ggr.blog.model");
        factory.setDataSource(dataSource);


        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        jpaProperties.put("hibernate.jdbc.batch_size", 50);
        jpaProperties.put("hibernate.show_sql", true);

        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet();
        System.out.println("entityManagerFactory:" + factory.getObject());
        return factory.getObject();
    }

    @Bean(name = "transactionManagerOne")
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        System.out.println("txManager:" + txManager);
        System.out.println(">>>>>>>>>>transactionManagerOne:" + txManager.getClass().getName());
        return txManager;
    }

    @Bean
    public Object testBean(@Qualifier("transactionManagerOne") PlatformTransactionManager platformTransactionManager) {
        log.info(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
}
