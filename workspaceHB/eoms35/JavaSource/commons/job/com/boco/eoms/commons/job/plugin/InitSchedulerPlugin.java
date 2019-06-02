package com.boco.eoms.commons.job.plugin;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.job.exception.ScheduleRunException;
import com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager;
import com.boco.eoms.log4j.Logger;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Aug 1, 2008 5:04:05 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class InitSchedulerPlugin implements PlugIn {
	/**
	 * log4j
	 */
	private final static Logger logger = Logger
			.getLogger(InitSchedulerPlugin.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.PlugIn#destroy()
	 */
	public void destroy() {
		logger.info("==================轮循停止成功==================");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
	 *      org.apache.struts.config.ModuleConfig)
	 */
	public void init(ActionServlet arg0, ModuleConfig arg1)
			throws ServletException {
		
		logger.info("==================轮循启动成功==================");
		ITawCommonsJobmonitorManager mgr = (ITawCommonsJobmonitorManager) ApplicationContextHolder
				.getInstance().getBean("ItawCommonsJobmonitorManager");
		try {
			// 任务调度器初始化
			mgr.instance();
			// 系统启动时装载任务
			mgr.run();
		} catch (ScheduleRunException e) {
			logger.error(e);
		}

	}

}
