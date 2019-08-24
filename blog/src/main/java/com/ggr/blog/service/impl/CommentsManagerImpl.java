package com.ggr.blog.service.impl;

import com.ggr.blog.dao.CommentsDao;
import com.ggr.blog.dao.impl.CommentsDaoHibernate;
import com.ggr.blog.model.Comments;
import com.ggr.blog.service.CommentsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("iCommentsManager")
public class CommentsManagerImpl implements CommentsManager {
    @Autowired
    private CommentsDaoHibernate iCommentsDao;
    public Comments getCommentsById(String id) throws SQLException {
        System.out.println(iCommentsDao);
        return iCommentsDao.getCommentsById(id);
    }
}
