package com.boco.eoms.sheet.dealtypeconfig.model;


import java.io.Serializable;
import java.util.Date;

import com.boco.eoms.base.model.BaseObject;


public class DealTypeConfig extends BaseObject implements Serializable {

	private String id;
	private String flowName;
	private String taskName;
	private String taskDisplayName;
	private String dealPerformerType;
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getTaskDisplayName() {
		return taskDisplayName;
	}
	public void setTaskDisplayName(String taskDisplayName) {
		this.taskDisplayName = taskDisplayName;
	}

	

}
