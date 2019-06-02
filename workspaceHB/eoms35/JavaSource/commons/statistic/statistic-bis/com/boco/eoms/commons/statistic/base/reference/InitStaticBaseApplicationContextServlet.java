package com.boco.eoms.commons.statistic.base.reference;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class InitStaticBaseApplicationContextServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5110097400326824874L;
	
	public void init() throws ServletException {
		super.init();
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServletContext());
		// 初始化applicationContextHolder的spring context
		ApplicationContextHolder.getInstance().setCtx(ctx);

	}

}
