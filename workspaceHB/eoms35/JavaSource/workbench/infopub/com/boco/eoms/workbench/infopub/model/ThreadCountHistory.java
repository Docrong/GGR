package com.boco.eoms.workbench.infopub.model;


/**
 * <p>
 * Title:贴子查看记录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 3:56:54 PM
 * </p>
 * 
 * @author 赵东亮
 * @version 3.5.1
 * 
 */




public class ThreadCountHistory {


	/**
	 * 贴子id
	 */
	private String threadId;

	/**
	 * 访问人用户id
	 */
	private String userId;

	
	/**
	 * 同一用户的访问次数
	 */
	private String count;
	
	
	/**
	 * @return the threadId
	 */
	public String getThreadId() {
		return threadId;
	}

	/**
	 * @param threadId
	 *            the threadId to set
	 */
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

}
