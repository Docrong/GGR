package com.work.ggr.webapp.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : gr
 * @date : 2019/8/28 15:11
 */
@RestController
public class JpaAction {

    @RequestMapping
    @ResponseBody
    public String test(){
        return "test";
    }

}
