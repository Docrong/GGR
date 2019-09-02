package com.work.ggr.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : gr
 * @date : 2019/8/30 15:01
 */
@Repository
public class JdbcDaoImpl {
    @Autowired
    @Qualifier("JdbcTemplate1")
    private JdbcTemplate template1;
    @Autowired
    @Qualifier("JdbcTemplate2")
    private JdbcTemplate template2;

    public void test1() {
        List list=template1.queryForList("select * from Person");
        System.out.println(list);
    }

    public void test2() {
        List list=template2.queryForList("select * from Person");
        System.out.println(list);
    }
}
