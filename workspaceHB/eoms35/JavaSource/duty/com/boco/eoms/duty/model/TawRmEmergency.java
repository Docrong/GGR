package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

public class TawRmEmergency extends BaseObject {

	private String id;

	private String deptid;

	private String cruser;

	private String crtime;

	private String specialty;// 专业电话

	private String immobility; // 固定电话

	private String other; // 其他电话

	private String deptName;

	private String userName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCrtime() {
		return crtime;
	}

	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}

	public String getCruser() {
		return cruser;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImmobility() {
		return immobility;
	}

	public void setImmobility(String immobility) {
		this.immobility = immobility;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * create table TAW_RM_EMERGENCY ( ID VARCHAR2(32) not null, CRUSER
	 * VARCHAR2(32), CRTIME VARCHAR2(20), DEPTID VARCHAR2(32), SPECIALTY
	 * VARCHAR2(128), IMMOBILITY VARCHAR2(128), OTHER VARCHAR2(128) )
	 */
}
