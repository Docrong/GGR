package com.ggr.blog.dao.impl;

import com.ggr.blog.dao.CommentsDao;
import com.ggr.blog.model.Comments;
<<<<<<< HEAD
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
=======
>>>>>>> 691bc0140004ae060b4cfa72d7cf0248f81678e5
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("iCommentsDao")
public class CommentsDaoHibernate  implements CommentsDao {


<<<<<<< HEAD
    protected Log log = LogFactory.getLog(getClass());
=======
>>>>>>> 691bc0140004ae060b4cfa72d7cf0248f81678e5


    @Override
    public Comments getCommentsById(String id) {
<<<<<<< HEAD
        log.info(this.getClass().getName());
=======
        System.out.println("sessionFactory");
>>>>>>> 691bc0140004ae060b4cfa72d7cf0248f81678e5
        return null;
    }
}
