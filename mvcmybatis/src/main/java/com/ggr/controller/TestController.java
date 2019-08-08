package com.ggr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ggr.entity.Person;
import com.ggr.service.PersonService;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/mybatis")
    public @ResponseBody
    String mybatis() {
        Person person = new Person();
        person.setUsername("test2");
        person.setAddress("123");
        List list = personService.findAll();
        try {
//        	personService.insert(person);//利用注解直接添加
//        	personService.insertAnother(person);//利用.xml文件进行映射
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("list-->" + list);
        System.out.println("mybatis添加数据到数据库中");
        return "success";
    }
}