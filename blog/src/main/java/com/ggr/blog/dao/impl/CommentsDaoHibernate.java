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
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository("iCommentsDao")
public class CommentsDaoHibernate  extends HibernateDaoSupport implements CommentsDao {


    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }



    @Override
    public Comments getCommentsById(String id) {
        log.info(this.getClass().getName());
        System.out.println("sessionFactory321");

        HibernateCallback callback = new HibernateCallback(){

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("select sysdate from dual");
                List list=query.list();
                System.out.println(list);
                return null;
            }
        };


        return null;
    }
}
