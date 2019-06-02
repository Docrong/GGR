package com.boco.eoms.workbench.infopub.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

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
 * @author 曲静波
 * @version 3.5.1
 * 
 */

public class ThreadHistory extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5751736560951652677L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 贴子id
	 */
	private String threadId;

	/**
	 * 访问人用户id
	 */
	private String userId;

	/**
	 * 阅读时间
	 */
	private Date readTime;

	/**
	 * 访问时所在的ip地址
	 */
	private String ip;

	/**
	 * 评阅信息
	 */
	private String comments;
	
	/**
	 * 记录类型
	 */
	private String historyType;
	
	/**
	 * 删除标记
	 */
	private String isDel;
	
	/**
	 * 回复结果
	 */
	private String replyresult;
	private String replyresultName;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the ip
	 * 
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the readTime
	 */
	public Date getReadTime() {
		return readTime;
	}

	/**
	 * @param readTime
	 *            the readTime to set
	 */
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

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
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the historyType
	 */
	public String getHistoryType() {
		return historyType;
	}

	/**
	 * @param historyType
	 *            the historyType to set
	 */
	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof ThreadHistory) {
			ThreadHistory threadHistory = (ThreadHistory) o;
			if (this.id != null || this.id.equals(threadHistory.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}



	/**
	 * @return the isDel
	 */
	public String getIsDel() {
		return isDel;
	}

	/**
	 * @param isDel
	 *            the isDel to set
	 */
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getReplyresult() {
		return replyresult;
	}

	public void setReplyresult(String replyresult) {
		this.replyresult = replyresult;
	}

	public String getReplyresultName() {
		if(this.replyresult.equals("1")){
			
			replyresultName="同意";
		}
		else{
			replyresultName="不同意";
		}
		return replyresultName;
	}

	public void setReplyresultName(String replyresultName) {
		this.replyresultName = replyresultName;
	}
}
