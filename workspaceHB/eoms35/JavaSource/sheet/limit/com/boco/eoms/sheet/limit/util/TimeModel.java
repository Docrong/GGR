/*
 * Created on 2007-11-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.limit.util;

import java.util.List;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TimeModel {
	private String modelName;
	private String flowName;
	private String taskFlowName;
	private List timeFilter;
	private ExceptTask exceptTask;
	private TimeWorktime timeWorktime;
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public List getTimeFilter() {
		return timeFilter;
	}
	public void setTimeFilter(List timeFilter) {
		this.timeFilter = timeFilter;
	}
	/**
	 * @return Returns the modelName.
	 */
	public String getModelName() {
		return modelName;
	}
	/**
	 * @param modelName The modelName to set.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getTaskFlowName() {
		return taskFlowName;
	}
	public void setTaskFlowName(String taskFlowName) {
		this.taskFlowName = taskFlowName;
	}
	public ExceptTask getExceptTask() {
		return exceptTask;
	}
	public void setExceptTask(ExceptTask exceptTask) {
		this.exceptTask = exceptTask;
	}
	public TimeWorktime getTimeWorktime() {
		return timeWorktime;
	}
	public void setTimeWorktime(TimeWorktime timeWorktime) {
		this.timeWorktime = timeWorktime;
	}
}
