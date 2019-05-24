package com.boco.eoms.sheet.tool.limit.webapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

public class SheetLimitForm extends BaseForm implements Serializable{

	protected Integer id;
	protected String deptId;
	protected String moudleId;
	protected String specialty1;
	protected String specialty2;
	protected String specialty3;
	protected String specialty4;
	protected String acceptLimit;
	protected String replyLimit;
	protected String roleName;
	protected String stepId;
	protected String t1Limit;
	protected String t2Limit;
	protected String t3Limit;
	
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
