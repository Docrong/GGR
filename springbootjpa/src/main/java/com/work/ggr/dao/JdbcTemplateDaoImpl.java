package com.work.ggr.dao;

import com.work.ggr.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author : gr
 * @date : 2019/8/30 15:01
 */
@Repository
public class JdbcTemplateDaoImpl {


    private JdbcTemplate template1;


    private JdbcTemplate template2;


    @Autowired
    public void setTemplate1(@Qualifier("JdbcTemplate1") JdbcTemplate template1) {
        this.template1 = template1;
    }

    @Autowired
    public void setTemplate2(@Qualifier("JdbcTemplate2") JdbcTemplate template2) {
        this.template2 = template2;
    }

    //查询
    public void queryMysql1() {
        List list = template1.queryForList("select * from Person");
        System.out.println(list);

        //映射到实体类
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<Person>(Person.class);
        //单行
        Person p = template1.queryForObject("select * from person where id=?", rowMapper, 1);
        System.out.println(p);
        //多行
        List<Person> mapperlist = template1.query("select * from person where username=?", rowMapper, "ggr");
        System.out.println(mapperlist);
    }

    //新增
    public void insertMysql1() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String currentTime = new Date().toString();
        String insertSql = " insert into person (id,username,created,remark)values(?,?,?,?)";
        int count = template1.update(insertSql, new Object[]{uuid, "ggr", currentTime, "template_insert"});
        System.out.println(count);
    }

    //升级
    public void updateMysql1() {
        String sql = "update person set username=?,address=? where id=?";
        template1.update(sql, new Object[]{"template_update", "5", "43a155e10ba847f693a3a840ce5373a4"});
    }

    //删除
    public void deleteMysql1() {
        String sql = "delete from person where username=?";
        template1.update(sql, "ggr");
    }

    //批量插入
    public void batchInsertMysql1() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String currentTime = new Date().toString();
        String insertSql = " insert into person (id,username,created,remark)values(?,?,?,?)";
        List args = new ArrayList();
        args.add(new Object[]{"1", "1", "1", "4"});
        template1.batchUpdate(insertSql, args);
    }

    public void queryMysql2() {
        List list = template2.queryForList("select * from Person");
        System.out.println(list);
    }

    public void insertMysql2() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String currentTime = new Date().toString();
        String insertSql = " insert into person (id,username,created,remark)values(?,?,?,?)";
        int count = template2.update(insertSql, new Object[]{uuid, "ggr", currentTime, "template_insert"});
        System.out.println(count);
    }

    public void updateMysql2() {

    }
}
