package com.work.ggr.service.impl;


import com.work.ggr.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : gr
 * @date : 2019/8/29 8:54
 */
@Service("personManager")
public class PersonServiceImpl {

    @Autowired
    private JpaDaoImpl dao;

    @Autowired
    private DataSourceDaoImpl dsDao;

    @Autowired
    private JdbcDaoImpl jdbcDao;

    public Map testJpa(Map maptj) {
        System.out.println("dao:" + dao);
        dao.testMysql1(maptj);
        return null;
    }

    public Map testDataSource(Map maptj) {
        System.out.println("test DataSource");
        dsDao.testMysql1();
        dsDao.testMysql2();
        return null;
    }

    public Map testJdbc(Map maptj){
        System.out.println("test JDBC");
        jdbcDao.test1();;
        jdbcDao.test2();
        return null;
    }
}
