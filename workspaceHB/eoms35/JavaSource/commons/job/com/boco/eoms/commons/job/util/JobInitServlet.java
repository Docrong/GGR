package com.boco.eoms.commons.job.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * <p>
 * Title:初使化轮训模块 Servlet
 * </p>
 * <p>
 * Description:web continer启动时，将spring context
 * 写入JobInitServlet。目的是轮训管理器仅加载一次。
 * </p>
 * <p>
 * Date:2007-10-17 14:01:00
 * </p>
 * 
 * @author wangbeiying
 */
public class JobInitServlet extends HttpServlet {
	/**
	 * 初始化JobInitServlet里的轮训管理器，避免加载两次
	 */
	public void init() throws ServletException {
		super.init();

		try {
			ITawCommonsJobmonitorManager tawCommonsJobmonitorManager = (ITawCommonsJobmonitorManager) ApplicationContextHolder
					.getInstance().getBean("ItawCommonsJobmonitorManager");
			tawCommonsJobmonitorManager.instance();
			tawCommonsJobmonitorManager.run();
		} catch (Exception e) {
			BocoLog.error(e, "初始化轮循程序失败！");
			e.printStackTrace();
		}
	}
}
