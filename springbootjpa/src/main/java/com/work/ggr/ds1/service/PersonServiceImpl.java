package com.work.ggr.ds1.service;


import com.work.ggr.dao.*;
import com.work.ggr.model.Person;
import com.work.ggr.ds1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author : gr
 * @date : 2019/8/29 8:54
 */
@Service("personManager")
@Transactional(value = "transactionManagerMain")
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


    @Autowired
    @Qualifier(value="personRepository")
    private PersonRepository personRepository;
    public Map testJpaRepository(Map maptj){
        System.out.println(personRepository);
//        Person p=personRepository.findById("2");
//        System.out.println(p);
//        List list=personRepository.findByUsernameAndAddress("test2","123");
//        System.out.println("(>^ω^<)喵");
//        System.out.println("personList===="+list);
//        System.out.println("(^・ω・^ )( ^・ω・^)(^・ω・^ )( ^・ω・^)");
//        p.setPhone(new Date().toString());
//        personRepository.save(p);
        Person p2=new Person();

        p2.setPhone("数据库1");
        p2.setUsername("ggr");
        p2.setCreated(new Date().toString());
        personRepository.save(p2);
        return null;
    }
}
