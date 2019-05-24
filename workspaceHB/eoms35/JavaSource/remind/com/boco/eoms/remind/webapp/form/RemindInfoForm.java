package com.boco.eoms.remind.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class RemindInfoForm extends BaseForm implements java.io.Serializable {
	/**
	 * 主键，预留字段，需要保存数据库时使用
	 */
	protected String id;

	/**
	 * 提醒标题
	 */
	protected String remindTitle;

	/**
	 * 提醒内容
	 */
	protected String remindContent;

	/**
	 * 所属用户Id
	 */
	protected String userId;

	/**
	 * 周期
	 */
	protected String cycle;

	/**
	 * 通过提醒内容去完成任务的url
	 */
	protected String url;

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

	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}
}
