package com.boco.eoms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexProjectController {

	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		return "首页";
	}
	
	@RequestMapping("/index2")
	public String index2(HttpServletRequest request,HttpServletResponse response) {
		return "首页2";
	}
}
