package com.ggr.blog.webapp.action;

import com.ggr.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentsAction {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        HashMap maptj = new HashMap();
        maptj.put("author",author);
        maptj.put("title",title);
        Map map = articleService.findArticles(maptj);
        return map.toString();
    }
}
