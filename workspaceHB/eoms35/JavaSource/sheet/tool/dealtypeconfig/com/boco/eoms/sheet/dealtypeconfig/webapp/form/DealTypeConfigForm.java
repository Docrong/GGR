package com.boco.eoms.sheet.dealtypeconfig.webapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

public class DealTypeConfigForm  extends BaseForm implements Serializable{
	protected String id;
	protected String flowName;
	protected String taskName;
	protected String taskDisplayName;
	protected String dealPerformerType;
	protected String userId;
	public String getDealPerformerType() {
		return dealPerformerType;
	}
	public void setDealPerformerType(String dealPerformerType) {
		this.dealPerformerType = dealPerformerType;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTaskDisplayName() {
		return taskDisplayName;
	}
	public void setTaskDisplayName(String taskDisplayName) {
		this.taskDisplayName = taskDisplayName;
	}
}
