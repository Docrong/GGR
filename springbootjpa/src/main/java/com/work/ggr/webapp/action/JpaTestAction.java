package com.work.ggr.webapp.action;

import com.work.ggr.service.PersonServiceImpl;
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

    @RequestMapping("/test")
    @ResponseBody
    public String testJpa() {
        Map maptj = new HashMap();
        personManager.testJpa(maptj);
        return "testJpa";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String testDataSource() {
        Map maptj = new HashMap();
        personManager.testDataSource(maptj);
        return "testDataSource";
    }

    @RequestMapping("/test3")
    @ResponseBody
    public String testJdbc() {
        Map maptj = new HashMap();
        personManager.testJdbc(maptj);
        return "testJdbc";
    }

    @RequestMapping("/test4")
    @ResponseBody
    public String testJpaRepository(){
        Map maptj=new HashMap();
        personManager.testJpaRepository(maptj);
        return "testJpaRepository";
    }

}
