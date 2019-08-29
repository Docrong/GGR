package com.work.ggr.webapp.action;

import com.work.ggr.service.impl.PersonServiceImpl;
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
public class JpaAction {

    @Autowired
    private PersonServiceImpl personManager;

    @RequestMapping
    @ResponseBody
    public String test(){
        Map maptj=new HashMap();
        System.out.println("service:"+personManager);
        personManager.testJpa(maptj);
//        personManager.testDataSource(maptj);
        return "test";
    }

}
