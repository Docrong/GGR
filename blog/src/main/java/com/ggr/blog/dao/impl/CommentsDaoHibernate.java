package com.ggr.blog.dao.impl;

import com.ggr.blog.dao.CommentsDao;
import com.ggr.blog.model.Comments;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("iCommentsDao")
public class CommentsDaoHibernate  implements CommentsDao {




    @Override
    public Comments getCommentsById(String id) {
        System.out.println("sessionFactory");
        return null;
    }
}
