package com.boco.eoms.remind.model;

import com.boco.eoms.base.model.BaseObject;

public class RemindInfo extends BaseObject {
	/**
	 * 主键，预留字段，需要保存数据库时使用
	 */
	private String id;

	/**
	 * 提醒标题
	 */
	private String remindTitle;

	/**
	 * 提醒内容
	 */
	private String remindContent;

	/**
	 * 所属用户Id
	 */
	private String userId;

	/**
	 * 周期
	 */
	private String cycle;

	/**
	 * 通过提醒内容去完成任务的url
	 */
	private String url;

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemindContent() {
		return remindContent;
	}

	public void setRemindContent(String remindContent) {
		this.remindContent = remindContent;
	}

	public String getRemindTitle() {
		return remindTitle;
	}

	public void setRemindTitle(String remindTitle) {
		this.remindTitle = remindTitle;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object o) {
		return false;
	}

	public int hashCode() {
		return super.hashCode();
	}

	public String toString() {
		return super.toString();
	}
}
