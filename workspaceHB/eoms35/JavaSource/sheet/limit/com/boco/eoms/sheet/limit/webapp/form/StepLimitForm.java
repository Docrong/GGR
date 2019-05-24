/**
 * 
 */
package com.boco.eoms.sheet.limit.webapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * @author Administrator
 *
 */
public class StepLimitForm  extends BaseForm implements Serializable{

	protected String id;
	protected String stepId;
	protected String levelId;
	protected String taskId;
	protected String taskName;
	protected String acceptLimit;
	protected String completeLimit;
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
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	

}
