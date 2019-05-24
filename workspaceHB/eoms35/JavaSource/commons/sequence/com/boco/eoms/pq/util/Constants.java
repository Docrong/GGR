package com.boco.eoms.pq.util;

public class Constants {

	/**
	 * 队列中任务等待执行状态
	 */
	public final static String Q_STATUS_WAITING = "waiting";

	/**
	 * 队列中任务执行完成状态
	 */
	public final static String Q_STATUS_DONE = "done";

	/**
	 * HoldQ中任务执行仍然失败的错误状态
	 */
	public final static String Q_STATUS_ERROR = "error";

	/**
	 * 已删除标志
	 */
	public final static String DELETED = "1";

	/**
	 * 未删除标志
	 */
	public final static String UNDELETED = "0";
}
