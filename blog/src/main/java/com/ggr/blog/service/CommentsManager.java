package com.ggr.blog.service;

import com.ggr.blog.model.Comments;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;


public interface CommentsManager {

    public Comments getCommentsById(String id);

    public Map getCommentsList(Map maptj);
    public void CommentsSave(Comments t);
}
