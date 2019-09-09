package com.work.ggr.dao;

import com.work.ggr.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.Map;

/**
 * @author : gr
 * @date : 2019/8/29 10:56
 */

@Repository("jpaDao")
@Transactional(value = "transactionManagerMain")
public class JpaDaoImpl {
    @Autowired
    @Qualifier("entityManagerFactoryMain")
    private EntityManagerFactory entityManagerFactory;



    public Map testMysql1(Map map) {
        System.out.println("testMysql1():" + entityManagerFactory);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Query query=entityManager.createQuery(" from Person where username='test2'");
        System.out.println("entity:****" + entityManager);
//        List list=query.getResultList();
//        System.out.println(list);
        Person p = new Person();
//        p.setId("123");
        p.setAddress("1");
        p.setCreated(new Date().toString());
        p.setPhone("12345678901");
        p.setRemark("hello");
        p.setUsername(new Date().toString());
        Person p2=entityManager.find(Person.class,"11");
        System.out.println(p2);
//        entityManager.remove(p2);
//        entityManager.flush();

        return null;
    }

//    @Autowired
//    @Qualifier("entityManagerFactorySecond")
//    private EntityManagerFactory entityManagerFactory2;
//    @Transactional(value = "transactionManagerSecond")
//    public Map testMysql2() {
//        EntityManager entityManager=entityManagerFactory2.createEntityManager();
//        Query query=entityManager.createQuery(" from Person");
//        List list=query.getResultList();
//        System.out.println(list);
//        return null;
//    }
}
