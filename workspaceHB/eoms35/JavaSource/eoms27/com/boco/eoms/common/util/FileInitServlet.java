package com.boco.eoms.common.util;

import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.pud.study.file.appinfo.AppInformations;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class FileInitServlet extends HttpServlet {
	private Timer timer;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			timer = new Timer(true);

			// 对解析属性文件boco.xml进行初始化工作
			String file = config.getInitParameter("config");
			String filePath = this.getServletContext().getRealPath(file);
			PropertyFile prop = PropertyFile.getInstance(filePath);
			BocoLog.info(this, 99, "PropertyFile解析正确,文件路径是：" + filePath);

			// 查看连接池是否连接成功
			ConnectionPool pool = ConnectionPool.getInstance();
			BocoLog.info(this, 0, "eoms数据库连接成功!");
			BocoLog.info(this, 1, "网管数据库连接成功!");

			// 对附件工具进行初始化工作。
			BocoLog.info(this, 2, "开始初始化附件管理工具");
			AppInformations.init(filePath);
			BocoLog.info(this, 3, "完成初始化附件管理工具");

			/*
			 * //EXCEL文件导出工具初始化 BocoLog.info(this, 4, "开始初始化EXCEL管理工具");
			 * PoiExcel.init(filePath); BocoLog.info(this, 5, "完成初始化EXCEL管理工具");
			 */
			// 对轮循程序进行初始化并启动工作
			/*
			 * BocoLog.info(this, 6, "开始初始化轮循程序"); SchedulerManager sm =
			 * SchedulerManager.getInstance(); sm.run(); BocoLog.info(this, 7,
			 * "完成初始化轮循程序");
			 */

			// 作业计划由InitWorkplanPlugin加载
			// 作业计划模版初始化
			// BocoLog.info(this, 8, "开始作业计划环境初始化");
			// TawwpUtilDAO.initInfor();

			// TawwpFlowManage.getInstance(filePath);
			// BocoLog.info(this, 9, "完成作业计划环境初始化");
			/** ****************配置作业计划绝对路径 Start******************* */
			/*
			 * 绝对路径配置说明 By zhyg 确认已经设置web-inf/web.xml中的 <servlet>
			 * <servlet-name>fileInit</servlet-name>
			 * <servlet-class>com.boco.eoms.common.util.FileInitServlet</servlet-class>
			 * <init-param> <param-name>config</param-name>
			 * <param-value>/WEB-INF/configfiles</param-value> </init-param>
			 * ---------------------------- <init-param> <param-name>webinf</param-name>//web-inf目录
			 * <param-value>/WEB-INF</param-value>//web-inf目录的根目录相对地址
			 * </init-param> <init-param> <param-name>root</param-name>//根目录
			 * <param-value>/</param-value>//如果目录为根目录值必须为“/” </init-param>
			 * ----------------------------- <load-on-startup>1</load-on-startup>
			 * </servlet>
			 */

			// 设置web-inf路径
			String rootDir = config.getInitParameter("webinf");
			String rootDirPath = this.getServletContext().getRealPath(rootDir)
					+ "/";
			TawwpStaticVariable.rootDir = rootDirPath;

			// 设置根路径
			String webinfDir = config.getInitParameter("root");
			String webinfDirPath = this.getServletContext().getRealPath(
					webinfDir)
					+ "/";
			TawwpStaticVariable.wwwDir = webinfDirPath;

			// 设置服务器IP
			String serverIp = StaticMethod.getNodeName("serverip");
			TawwpStaticVariable.serverIp = serverIp;
			// BocoLog.info(this, 98, "设置[workplan]webinf：" +
			// TawwpStaticVariable.rootDir);
			// BocoLog.info(this, 97, "设置[workplan]root：" +
			// TawwpStaticVariable.wwwDir);
			/** ****************配置作业计划绝对路径 End***************** */

			long period = StaticMethod.nullObject2int(prop
					.getProperty("monitor_period"));
			BocoLog.debug(this, 29, String.valueOf(period));
			if (period > 0) {
				// timer.schedule(new MonitorScheduler(), 30000, period);
				// sms reply deal schedule
				// timer.schedule(new SMSDeliverScheduler(), 60000, 180000);

			}

			// 更改了连接池程序后，就不需要这个辅助程序了
			// timer.schedule(new ConnCheckerScheduler(),60000,3600000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("connect error!");
			throw new ServletException("Unable to open datasource.");
		}

	}

	public void destory() throws ServletException {
		timer.cancel();
	}

}
