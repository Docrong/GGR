package com.boco.eoms.commons.system.reported.model;

import com.boco.eoms.base.model.BaseObject;

public class TawSystemReportedUser extends BaseObject{

	private static final long serialVersionUID = 7767832611703970873L;
	private String id;  //主键ID
	private String reportedId;  //配置信息的ID
	private String reportedOrgId;  //上报对象的ID
	private String reportedOrgType;  //上报对象的类型


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getReportedId() {
		return reportedId;
	}
	public void setReportedId(String reportedId) {
		this.reportedId = reportedId;
	}

	public String getReportedOrgId() {
		return reportedOrgId;
	}
	public void setReportedOrgId(String reportedOrgId) {
		this.reportedOrgId = reportedOrgId;
	}	

	public String getReportedOrgType() {
		return reportedOrgType;
	}
	public void setReportedOrgType(String reportedOrgType) {
		this.reportedOrgType = reportedOrgType;
	}

	/**
	 * 构造方法
	 * 
	 * @param orgId
	 *            组织id,部门，用户，角色
	 * @param orgType
	 *            组类型 部门，用户，角色
	 * @param name
	 *            组织名称
	 * 
	 */
	public TawSystemReportedUser(String reportedOrgId, String reportedOrgType) {
		super();
		this.reportedOrgId = reportedOrgId;
		this.reportedOrgType = reportedOrgType;
	}

	public TawSystemReportedUser() {
		super();
	}

	public boolean equals(Object o) {
		if (o instanceof TawSystemReportedUser) {
			TawSystemReportedUser tawSystemReportedUser = (TawSystemReportedUser) o;
			if (this.id != null || this.id.equals(tawSystemReportedUser.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
