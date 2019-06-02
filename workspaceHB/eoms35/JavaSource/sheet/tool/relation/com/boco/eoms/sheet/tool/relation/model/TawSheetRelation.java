package com.boco.eoms.sheet.tool.relation.model;

import com.boco.eoms.base.model.BaseObject;
import java.util.Date;

public class TawSheetRelation extends BaseObject {

	private String id;

	private String parentId;

	private String parentFlowName;

	private String parentTitle;

	private String parentSheetId;
	
	private String parentProcessId;

	private String currentId;

	private String currentFlowName;

	private String currentTitle;

	private String currentSheetId;
	
	private String currentProcessId;

	private String userId;

	private String subRoleId;

	private Date invokeTime;
	
	private int invokeState;
	
	private String taskName;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

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

	public String getCurrentSheetId() {
		return currentSheetId;
	}

	public void setCurrentSheetId(String currentSheetId) {
		this.currentSheetId = currentSheetId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getInvokeTime() {
		return invokeTime;
	}

	public void setInvokeTime(Date invokeTime) {
		this.invokeTime = invokeTime;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getCurrentTitle() {
		return currentTitle;
	}

	public void setCurrentTitle(String currentTitle) {
		this.currentTitle = currentTitle;
	}

	public String getParentFlowName() {
		return parentFlowName;
	}

	public void setParentFlowName(String parentFlowName) {
		this.parentFlowName = parentFlowName;
	}

	public String getCurrentProcessId() {
		return currentProcessId;
	}

	public void setCurrentProcessId(String currentProcessId) {
		this.currentProcessId = currentProcessId;
	}

	public String getParentProcessId() {
		return parentProcessId;
	}

	public void setParentProcessId(String parentProcessId) {
		this.parentProcessId = parentProcessId;
	}

	public int getInvokeState() {
		return invokeState;
	}

	public void setInvokeState(int invokeState) {
		this.invokeState = invokeState;
	}
 
	
}
