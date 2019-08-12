package com.ggr.blog.webapp.action;

import com.ggr.blog.service.CommentsManager;
import com.ggr.blog.service.impl.CommentsManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentsAction {

    @Autowired
    private CommentsManagerImpl commentsManager;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        commentsManager.getCommentsById("");
        System.out.println(commentsManager);
        return "212312";
    }
}
