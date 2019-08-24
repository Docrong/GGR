package com.ggr.blog.dao;

import com.ggr.blog.model.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;


public interface CommentsDao  {

    public  Comments getCommentsById(String id) throws SQLException;


}
