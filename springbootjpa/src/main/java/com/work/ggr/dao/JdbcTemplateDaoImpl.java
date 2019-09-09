package com.work.ggr.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author : gr
 * @date : 2019/8/30 15:01
 */
@Repository
public class JdbcTemplateDaoImpl {

    @Autowired
    @Qualifier("JdbcTemplate1")
    private JdbcTemplate template1;

    @Autowired
    @Qualifier("JdbcTemplate2")
    private JdbcTemplate template2;

    public void queryMysql1() {
        List list = template1.queryForList("select * from Person");
        System.out.println(list);
    }

    public void queryMysql2() {
        List list = template2.queryForList("select * from Person");
        System.out.println(list);
    }

    public void insertMysql1(){
        String uuid= UUID.randomUUID().toString().replace("-","");
        String currentTime=new Date().toString();
        String insertSql=" insert into person (id,username,created,remark)values(?,?,?,?)";
        int count= template1.update(insertSql, new Object[]{uuid,"ggr",currentTime,"template_insert"});
        System.out.println(count);
    }

    public void updateMysql1(){
        String sql="update person set username=?,address=? where id=?";
        template1.update(sql,new Object[]{"template_update","5","43a155e10ba847f693a3a840ce5373a4"});
    }

    public void insertMysql2(){
        String uuid= UUID.randomUUID().toString().replace("-","");
        String currentTime=new Date().toString();
        String insertSql=" insert into person (id,username,created,remark)values(?,?,?,?)";
        int count= template2.update(insertSql, new Object[]{uuid,"ggr",currentTime,"template_insert"});
        System.out.println(count);
    }

    public void updateMysql2(){

    }
}
