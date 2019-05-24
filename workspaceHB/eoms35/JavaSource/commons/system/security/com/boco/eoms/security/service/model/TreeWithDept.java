package com.boco.eoms.security.service.model;

import com.boco.eoms.common.util.StaticVariable;

/*
 * add by yuwenchao 2008-11-05
 * for Contact AD_DeptTree and Dept
 */

public class TreeWithDept implements java.io.Serializable {

	String deptId = "-10";
	private String deptName = "";
	private String notes = "";
	private String parentDeptId ;
	private int deleted = 0;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

}
