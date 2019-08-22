package com.ggr.blog.dao.impl;

import com.ggr.blog.dao.CommentsDao;
import com.ggr.blog.model.Comments;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("iCommentsDao")
public class CommentsDaoHibernate  extends HibernateDaoSupport implements CommentsDao {


    protected Log log = LogFactory.getLog(getClass());

    private SessionFactory sessionFactory;


    @Override
    public Comments getCommentsById(String id) {
        log.info(this.getClass().getName());
        System.out.println("sessionFactory321");




        return null;
    }
}
