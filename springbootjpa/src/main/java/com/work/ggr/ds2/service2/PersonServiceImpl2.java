package com.work.ggr.ds2.service2;

import com.work.ggr.model.Person;
import com.work.ggr.ds2.repository2.PersonRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author : gr
 * @date : 2019/9/8 13:48
 */
@Transactional(value = "transactionManagerSecond")
@Service("personManager2")
public class PersonServiceImpl2 {


    @Autowired
    @Qualifier(value = "personRepository2")
    private PersonRepository2 personRepository;
    @Transactional(value = "transactionManagerSecond")
    public void testSecondDS(Map maptj){
        Person p2=new Person();

        p2.setPhone("数据库2");
        p2.setUsername("ggr");
        p2.setCreated(new Date().toString());
        personRepository.save(p2);
    }
}
