package com.ggr.blog.service.impl;

import com.ggr.blog.dao.CommentsDao;
import com.ggr.blog.dao.impl.CommentsDaoHibernate;
import com.ggr.blog.model.Comments;
import com.ggr.blog.service.CommentsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service("iCommentsManager")
public class CommentsManagerImpl implements CommentsManager {
    @Autowired
    private CommentsDaoHibernate iCommentsDao;

    @Override
    public Comments getCommentsById(String id)  {
        return iCommentsDao.getCommentsById(id);
    }

    @Override
    public Map getCommentsList(Map maptj) {
        return iCommentsDao.getCommentsList(maptj);
    }

    @Override
    public void CommentsSave(Comments t) {
        iCommentsDao.CommentsSave(t);
    }
}
