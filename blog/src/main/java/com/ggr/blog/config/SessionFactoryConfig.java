package com.ggr.blog.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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



}
