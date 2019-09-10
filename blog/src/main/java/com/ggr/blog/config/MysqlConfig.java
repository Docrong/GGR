package com.ggr.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author : gr
 * @date : 2019/9/9 9:42
 * 配置类,自定义数据库
 */
@Configuration
//@ConfigurationProperties(prefix = "mysql", ignoreUnknownFields = false)
@PropertySource("classpath:datasource.properties")

public class MysqlConfig {

    /*
    在这里@Value("${mysql.url}")和
    @ConfigurationProperties(prefix = "mysql", ignoreUnknownFields = false)
    二选一即可,这里为了充分自定义选择前一种模式
    */
    @Value("${mysql.url}")
    private String url;

    @Value("${mysql.url2}")
    private String url2;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    @Override
    public String toString() {
        return "MysqlConfig{" +
                "url='" + url + '\'' +
                ", url2='" + url2 + '\'' +
                '}';
    }
}
