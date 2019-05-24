package com.boco.eoms.commons.loging;

import org.apache.commons.logging.LogFactory;

public class BocoLog {

	public BocoLog() {

	}

	private static BocoLog instance = new BocoLog();

	public static BocoLog getInstance() {
		return instance;
	}
   
	private static String[] LEVEL_DESC = { "跟踪信息", "调试信息", "提示信息", "警告信息",
			"错误信息", "致命错误", };

	/**
	 * 格式化信息串
	 * 
	 * @param name :
	 *            类名或者传入的其他名称(例如index.jsp)
	 * @param logLevel :
	 *            信息级别 0：跟踪信息...5:致命错误
	 * @param infoNum :
	 *            信息顺序
	 * @param msg :
	 *            信息内容
	 * @return ：格式化的信息内容
	 */
	private static String format(String name, int logLevel, String msg) {
		String line = "";

		line = LEVEL_DESC[logLevel] + " [" + name + "-" + "] " + msg;
		return line;
	}

	/**
	 * 根据类，得到类名的格式化信息
	 * 
	 * @param object :
	 *            类
	 * @param logLevel :
	 *            信息级别 0：跟踪信息...5:致命错误
	 * @param infoNum :
	 *            信息顺序
	 * @param msg :
	 *            信息内容
	 * @return ：格式化的信息内容
	 */
	private static String format(Object object, int logLevel, String msg) {
		String classname = "不知名类";
		if (object != null) {
			try {
				classname = object.getClass().getName();
			} catch (Exception e1) {
			}
		}
		return format(classname, logLevel, msg);
	}

	public static void trace(Object object, String msg) {
		LogFactory.getLog(object.getClass()).trace(format(object, 0, msg));
	}

	public static void trace(Object object, String msg, Throwable throwable) {
		LogFactory.getLog(object.getClass()).trace(format(object, 0, msg),
				throwable);
	}

	public static void debug(Object object, String msg) {
		LogFactory.getLog(object.getClass()).debug(format(object, 1, msg));
	}

	public static void debug(Object object, String msg, Throwable throwable) {
		LogFactory.getLog(object.getClass()).debug(format(object, 1, msg),
				throwable);
	}

	public static void info(Object object, String msg) {
		LogFactory.getLog(object.getClass()).info(format(object, 2, msg));
	}

	public static void info(Object object, String msg, Throwable throwable) {
		LogFactory.getLog(object.getClass()).info(format(object, 2, msg),
				throwable);
	}

	public static void warn(Object object, String msg) {
		LogFactory.getLog(object.getClass()).warn(format(object, 3, msg));
	}

	public static void warn(Object object, String msg, Throwable throwable) {
		LogFactory.getLog(object.getClass()).warn(format(object, 3, msg),
				throwable);
	}

	public static void error(Object object, String msg) {
		LogFactory.getLog(object.getClass()).error(format(object, 4, msg));
	}

	public static void error(Object object, String msg, Throwable throwable) {
		LogFactory.getLog(object.getClass()).error(format(object, 4, msg),
				throwable);
	}

	public static void fatal(Object object, String msg) {
		LogFactory.getLog(object.getClass()).fatal(format(object, 5, msg));
	}

	public static void fatal(Object object, String msg, Throwable throwable) {
		LogFactory.getLog(object.getClass()).fatal(format(object, 5, msg),
				throwable);
	}

}
