package com.ggr.blog.dao;

import com.ggr.blog.model.Comments;

import java.util.Map;


public interface CommentsDao  {

    public  Comments getCommentsById(String id);
    public Map getCommentsList(Map maptj);
    public void CommentsSave(Comments t);


}
