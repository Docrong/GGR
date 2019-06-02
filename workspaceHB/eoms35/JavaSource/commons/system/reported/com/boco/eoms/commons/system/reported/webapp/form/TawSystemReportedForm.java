package com.boco.eoms.commons.system.reported.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawSystemReportedForm extends BaseForm implements
		java.io.Serializable {

	private static final long serialVersionUID = -425946148555481621L;

	protected String id;

	protected String reportedOrg;
	
	protected String functionId;
	
	protected String modelId;
	
	protected String remark;

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReportedOrg() {
		return reportedOrg;
	}

	public void setReportedOrg(String reportedOrg) {
		this.reportedOrg = reportedOrg;
	}

}