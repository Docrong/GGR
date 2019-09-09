package com.work.ggr.webapp.action;

import com.work.ggr.config.MysqlConfig;
import com.work.ggr.ds1.service.PersonServiceImpl;
import com.work.ggr.ds2.service2.PersonServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : gr
 * @date : 2019/8/28 15:11
 */
@RestController
public class JpaTestAction {

    @Autowired
    private PersonServiceImpl personManager;
    @Autowired
    private PersonServiceImpl2 personManager2;

    @Autowired
    private MysqlConfig mysqlConfig;

    @RequestMapping("/test")
    @ResponseBody
    public String testJpa() {
        Map maptj = new HashMap();
        personManager.testJpa(maptj);
        System.out.println(mysqlConfig);
        return "testJpa";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String testDataSource() {
        Map maptj = new HashMap();
        personManager.testJdbc(maptj);
        return "testDataSource";
    }

    @RequestMapping("/test3")
    @ResponseBody
    public String testJdbc() {
        Map maptj = new HashMap();
        personManager.testJdbcTemplate(maptj);
        return "testJdbc";
    }

    @RequestMapping("/test4")
    @ResponseBody
    public String testJpaRepository() {
        Map maptj = new HashMap();
        personManager.testJpaRepository(maptj);
        return "testJpaRepository";
    }

    @RequestMapping("/test5")
    @ResponseBody
    public String testJPA2() {
        personManager2.testSecondDS(new HashMap());
        return "testJPARepository";
    }

}
