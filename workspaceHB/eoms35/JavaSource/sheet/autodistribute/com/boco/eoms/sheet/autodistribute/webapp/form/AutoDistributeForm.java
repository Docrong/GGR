package com.boco.eoms.sheet.autodistribute.webapp.form;

import java.io.Serializable;
import java.util.Date;

import com.boco.eoms.base.webapp.form.BaseForm;

public class AutoDistributeForm extends BaseForm implements Serializable{

	protected String id;
	protected String flowName;
	protected String autoType;
	
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
	public String getAutoType() {
		return autoType;
	}
	public void setAutoType(String autoType) {
		this.autoType = autoType;
	}

}
