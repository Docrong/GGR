package com.boco.eoms.commons.system.reported.model;

import com.boco.eoms.base.model.BaseObject;

import java.sql.Timestamp;

public class TawSystemReported extends BaseObject{

	private static final long serialVersionUID = 7767832611703970873L;

	private String id;  //主键ID
	private String userId; //设置人ID
	private String functionId; //功能ID
	private String modelId; //功能模块ID
	private Timestamp createTime; //创建时间
	private String reportedOrgName; //显示上报组织的名字
	private String remark; //备注

	
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

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	public String getReportedOrgName() {
		return reportedOrgName;
	}
	public void setReportedOrgName(String reportedOrgName) {
		this.reportedOrgName = reportedOrgName;
	}	

	public boolean equals(Object o) {
		if (o instanceof TawSystemReported) {
			TawSystemReported tawSystemReported = (TawSystemReported) o;
			if (this.id != null || this.id.equals(tawSystemReported.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
