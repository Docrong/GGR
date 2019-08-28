package com.ggr.blog.webapp.action;

import com.ggr.blog.model.Comments;
import com.ggr.blog.service.CommentsManager;
import com.ggr.blog.service.impl.CommentsManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentsAction {

    @Autowired
    private CommentsManagerImpl commentsManager;

    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        commentsManager.getCommentsById("");
        System.out.println(commentsManager);
        return "test";
    }

    @RequestMapping("/getCommontsById")
    @ResponseBody
    public String getCommontsById(HttpServletRequest request, HttpServletResponse response){
        commentsManager.getCommentsById("2");
        return "getCommontsById";
    }

    @RequestMapping("/getCommontsList")
    @ResponseBody
    public String getCommontsList(HttpServletRequest request, HttpServletResponse response){
        Map map=commentsManager.getCommentsList(new HashMap()) ;
        List list= (List) map.get("result");
        return list+"";
    }

    @RequestMapping("/commentsSave")
    @ResponseBody
    public String commentsSave(HttpServletRequest request, HttpServletResponse response){
        Comments t =new Comments();
        t.setAgree("1");
        t.setAgainst("1");
        t.setDate("12");
        commentsManager.CommentsSave(t);
        System.out.println("save method");
        return "commentsSave";
    }
}
