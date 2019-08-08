package com.boco.gr.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boco.gr.dao.RiskControlDao;
import com.boco.gr.model.TawRiskDocument;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class TestDemo extends AbstractJUnit4SpringContextTests {

    @Test
    public void test1() {
        System.out.println("12313123");
    }

    @Test
    public void test2() {
        ClassPathXmlApplicationContext resource = new ClassPathXmlApplicationContext("applicationContext-bean.xml");
        ;
        RiskControlDao dao = (RiskControlDao) resource.getBean("riskControlDao");//��ȡbean

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        Date date = new Date();
        System.out.println("���Ӳ���");
        System.out.println("·��" + resource);
        TawRiskDocument t = new TawRiskDocument();
//       t.setId("312222141412");

        t.setDeleted("0");
        t.setDocDesc("��������");
//		dao.saveTawRiskDocument(t);
        Map maptj = new HashMap();
        dao.getTawRiskDocumentList(maptj);


    }
}
