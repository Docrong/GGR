package com.boco.eoms.sheet.tool.relation.webapp.form;

import java.util.Date;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawSheetRelationForm extends BaseForm implements
		java.io.Serializable {

	private String parentId;

	private String parentFlowName;
	
	private String parentFlowCnName;

	private String parentTitle;

	private String parentSheetId;
	
	private String parentProcessId;

	private String currentId;

	private String currentFlowName;
	
	private String currentFlowCnName;

	private String currentTitle;

	private String currentSheetId;
	
	private String currentProcessId;

	private String userId;

	private String subRoleId;

	private String invokeTime;
	
	private int invokeState;
	
	private String taskName;
	
	private String taskCnName;
	

	public String getCurrentFlowName() {
		return currentFlowName;
	}

	public void setCurrentFlowName(String currentFlowName) {
		this.currentFlowName = currentFlowName;
	}

	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}

	public String getCurrentProcessId() {
		return currentProcessId;
	}

	public void setCurrentProcessId(String currentProcessId) {
		this.currentProcessId = currentProcessId;
	}

	public String getCurrentSheetId() {
		return currentSheetId;
	}

	public void setCurrentSheetId(String currentSheetId) {
		this.currentSheetId = currentSheetId;
	}

	public String getCurrentTitle() {
		return currentTitle;
	}

	public void setCurrentTitle(String currentTitle) {
		this.currentTitle = currentTitle;
	}

	public int getInvokeState() {
		return invokeState;
	}

	public void setInvokeState(int invokeState) {
		this.invokeState = invokeState;
	}

	public String getInvokeTime() {
		return invokeTime;
	}

	public void setInvokeTime(String invokeTime) {
		this.invokeTime = invokeTime;
	}

	public String getParentFlowName() {
		return parentFlowName;
	}

	public void setParentFlowName(String parentFlowName) {
		this.parentFlowName = parentFlowName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentProcessId() {
		return parentProcessId;
	}

	public void setParentProcessId(String parentProcessId) {
		this.parentProcessId = parentProcessId;
	}

	public String getParentSheetId() {
		return parentSheetId;
	}

	public void setParentSheetId(String parentSheetId) {
		this.parentSheetId = parentSheetId;
	}

	public String getParentTitle() {
		return parentTitle;
	}

	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}

	public String getSubRoleId() {
		return subRoleId;
	}

	public void setSubRoleId(String subRoleId) {
		this.subRoleId = subRoleId;
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

	public String getCurrentFlowCnName() {
		return currentFlowCnName;
	}

	public void setCurrentFlowCnName(String currentFlowCnName) {
		this.currentFlowCnName = currentFlowCnName;
	}

	public String getParentFlowCnName() {
		return parentFlowCnName;
	}

	public void setParentFlowCnName(String parentFlowCnName) {
		this.parentFlowCnName = parentFlowCnName;
	}

	public String getTaskCnName() {
		return taskCnName;
	}

	public void setTaskCnName(String taskCnName) {
		this.taskCnName = taskCnName;
	}

	
}
