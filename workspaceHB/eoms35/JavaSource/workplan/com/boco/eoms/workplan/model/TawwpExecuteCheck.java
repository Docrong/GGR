package com.boco.eoms.workplan.model;

import java.io.Serializable;

import com.boco.eoms.common.oo.DataObject;

public class TawwpExecuteCheck implements Serializable, DataObject {
	 
	
	private String id;
	private String executeId;
	private String checkContent;
	private String checker;
	private String checkTime;
	
	public String getCheckContent() {
		return checkContent;
	}
	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getExecuteId() {
		return executeId;
	}
	public void setExecuteId(String executeId) {
		this.executeId = executeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	 

}
