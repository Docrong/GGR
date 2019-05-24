/**
 * 
 */
package com.boco.eoms.sheet.limit.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * @author Administrator
 *
 */
public class StepLimit  extends BaseObject implements Serializable {
	private String id;
	private String stepId;
	private String levelId;
	private String taskName;
	private String taskCnName;
	private String acceptLimit;
	private String completeLimit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskCnName() {
		return taskCnName;
	}
	public void setTaskCnName(String taskCnName) {
		this.taskCnName = taskCnName;
	}
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getAcceptLimit() {
		return acceptLimit;
	}
	public void setAcceptLimit(String acceptLimit) {
		this.acceptLimit = acceptLimit;
	}
	public String getCompleteLimit() {
		return completeLimit;
	}
	public void setCompleteLimit(String completeLimit) {
		this.completeLimit = completeLimit;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

}
