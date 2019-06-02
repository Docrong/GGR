package com.boco.eoms.workplan.model;

import java.io.Serializable;

import com.boco.eoms.common.oo.DataObject;

public class TawwpExecuteFlag implements Serializable, DataObject {

	private String id; // 标识

	private String yearPlanId;

	private String monthPlanId;

	private String monthFlag;

	private String executedate;

	private String executer;

	private String state;

	private String remark;
	
	private String executeType; 

	public String getExecuteType() {
		return executeType;
	}

	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}

	public String getExecutedate() {
		return executedate;
	}

	public void setExecutedate(String executedate) {
		this.executedate = executedate;
	}

	public String getExecuter() {
		return executer;
	}

	public void setExecuter(String executer) {
		this.executer = executer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	 

	public String getMonthFlag() {
		return monthFlag;
	}

	public void setMonthFlag(String monthFlag) {
		this.monthFlag = monthFlag;
	}

	public String getMonthPlanId() {
		return monthPlanId;
	}

	public void setMonthPlanId(String monthPlanId) {
		this.monthPlanId = monthPlanId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getYearPlanId() {
		return yearPlanId;
	}

	public void setYearPlanId(String yearPlanId) {
		this.yearPlanId = yearPlanId;
	}

}
