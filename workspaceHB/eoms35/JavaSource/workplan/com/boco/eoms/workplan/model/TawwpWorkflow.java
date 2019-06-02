package com.boco.eoms.workplan.model;

import java.io.Serializable;

import com.boco.eoms.common.oo.DataObject;

public class TawwpWorkflow implements Serializable, DataObject {
	/**
	 * 
	 */   
	private String id;
	private String executeId;
	private String flowId;
	private String flowType ;
	public TawwpWorkflow() {
	  }
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
	public String getExecuteId() {
		return executeId;
	}
	public void setExecuteId(String executeId) {
		this.executeId = executeId;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getFlowType() {
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

}
