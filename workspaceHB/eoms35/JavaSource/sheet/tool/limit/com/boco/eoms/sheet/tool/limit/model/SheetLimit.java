package com.boco.eoms.sheet.tool.limit.model;


import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;


public class SheetLimit extends BaseObject implements Serializable {

	private Integer id;
	private String moudleId;
	private String deptId;
	private String specialty1;
	private String specialty2;
	private String specialty3;
	private String specialty4;
	private String acceptLimit;
	private String replyLimit;
	private String roleName;
	private String stepId;
	private String t1Limit;
	private String t2Limit;
	private String t3Limit;
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAcceptLimit() {
		return acceptLimit;
	}

	public void setAcceptLimit(String acceptLimit) {
		this.acceptLimit = acceptLimit;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReplyLimit() {
		return replyLimit;
	}

	public void setReplyLimit(String replyLimit) {
		this.replyLimit = replyLimit;
	}

	public String getSpecialty1() {
		return specialty1;
	}

	public void setSpecialty1(String specialty1) {
		this.specialty1 = specialty1;
	}

	public String getSpecialty2() {
		return specialty2;
	}

	public void setSpecialty2(String specialty2) {
		this.specialty2 = specialty2;
	}

	public String getSpecialty3() {
		return specialty3;
	}

	public void setSpecialty3(String specialty3) {
		this.specialty3 = specialty3;
	}

	public String getSpecialty4() {
		return specialty4;
	}

	public void setSpecialty4(String specialty4) {
		this.specialty4 = specialty4;
	}

	public String getMoudleId() {
		return moudleId;
	}

	public void setMoudleId(String moudleId) {
		this.moudleId = moudleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getT1Limit() {
		return t1Limit;
	}

	public void setT1Limit(String limit) {
		t1Limit = limit;
	}

	public String getT2Limit() {
		return t2Limit;
	}

	public void setT2Limit(String limit) {
		t2Limit = limit;
	}

	public String getT3Limit() {
		return t3Limit;
	}

	public void setT3Limit(String limit) {
		t3Limit = limit;
	}
	
	

}
