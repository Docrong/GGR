package com.ggr.blog.dao.impl;

import com.ggr.blog.dao.CommentsDao;
import com.ggr.blog.model.Comments;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository("iCommentsDao")
public class CommentsDaoHibernate extends HibernateDaoSupport implements CommentsDao {

//    @Autowired
//    @Qualifier(value = "oracleDataSource")
//    private DataSource ds;

    // 不能直接使用 setSessionFactory 是因为在HibernateDaoSupport中被定义为final
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
        return super.createHibernateTemplate(sessionFactory);
    }


    protected Log log = LogFactory.getLog(getClass());

    @Override
    public Comments getCommentsById(final String id) {
        log.info(this.getClass().getName());
        System.out.println("sessionFactory321");
        try {
            Comments t=new Comments();
            t.setId("123");
            t.setDate("2019-8-26");
            getHibernateTemplate().save(t);
            System.out.println(t);
        } catch (Exception e) {
            e.printStackTrace();
        }

System.out.println("hibernate end");
        return null;
    }
}
