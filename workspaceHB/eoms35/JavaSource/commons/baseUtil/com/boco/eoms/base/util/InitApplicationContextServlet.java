package com.boco.eoms.base.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.boco.eoms.version.DoVersionHandler;

/**
 * <p>
 * Title:初使化ApplicationConteHolder Servlet
 * </p>
 * <p>
 * Description:web continer启动时，将spring context
 * 写入ApplicationConteHolder。目的是spring配置文件仅加载一次。
 * </p>
 * <p>
 * Date:2007-10-17 14:01:00
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class InitApplicationContextServlet extends HttpServlet {

	/**
	 * 自动生成序列号
	 */
	private static final long serialVersionUID = -911553579769372190L;

	/**
	 * 初始化ApplicationContextHolder里的context，避免加载两次
	 */
	public void init() throws ServletException {
		super.init();
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServletContext());
		// 初始化applicationContextHolder的spring context
		ApplicationContextHolder.getInstance().setCtx(ctx);

		// 记入版本日志
		((DoVersionHandler) ctx.getBean("doVersionHandler")).doHandle();
		// 初始化内存中工单数组 ,add by qinmin
		// SheetKeyInit.init();
		// try{
		// ITawCommonsJobmonitorManager tawCommonsJobmonitorManager =
		// (ITawCommonsJobmonitorManager)ApplicationContextHolder.getInstance().getBean("ItawCommonsJobmonitorManager");
		// tawCommonsJobmonitorManager.instance();
		// tawCommonsJobmonitorManager.run();
		// }catch(Exception e){
		// BocoLog.error(e,"初始化轮循程序失败！");
		// e.printStackTrace();
		// }
	}
}
