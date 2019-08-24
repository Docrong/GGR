package com.ggr.blog.service;

import com.ggr.blog.model.Comments;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


public interface CommentsManager {

    public Comments getCommentsById(String id) throws SQLException;
}
