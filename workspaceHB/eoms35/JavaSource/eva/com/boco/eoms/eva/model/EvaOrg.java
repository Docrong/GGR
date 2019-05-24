package com.boco.eoms.eva.model;

import java.util.Date;
import com.boco.eoms.base.model.BaseObject;

public class EvaOrg extends BaseObject {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 模板id
	 */
	private String templateId;

	/**
	 * 组织id
	 */
	private String orgId;

	/**
	 * 组织类型
	 */
	private String orgType;

	/**
	 * 动作类型
	 */
	private String actionType;

	/**
	 * 是否为当前执行的任务
	 */
	private String status;
	
	/**
	 * 组织名称，仅作为显示用，数据库中不存在此字段
	 */
	private String orgName;
	
	/**
	 * 模板名称，仅作为显示用，数据库中不存在此字段，且只有下发的任务才能取出此值
	 */
	private String templateName;

	/**
	 * 操作时间
	 */
	private Date operateTime;
	
	/**
	 * 考核起始时间
	 */
	private String evaStartTime;

	/**
	 * 考核终止时间
	 */
	private String evaEndTime;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public boolean equals(Object o) {
		return false;
	}

	public String toString() {
		return null;
	}

	public int hashCode() {
		return 0;
	}

	public EvaOrg(String orgId, String orgType) {
		super();
		this.orgId = orgId;
		this.orgType = orgType;
	}

	public EvaOrg() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEvaEndTime() {
		return evaEndTime;
	}

	public void setEvaEndTime(String evaEndTime) {
		this.evaEndTime = evaEndTime;
	}

	public String getEvaStartTime() {
		return evaStartTime;
	}

	public void setEvaStartTime(String evaStartTime) {
		this.evaStartTime = evaStartTime;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
}
