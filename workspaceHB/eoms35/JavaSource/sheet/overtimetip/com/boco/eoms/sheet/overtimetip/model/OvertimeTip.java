package com.boco.eoms.sheet.overtimetip.model;


import java.io.Serializable;
import java.util.Date;

import com.boco.eoms.base.model.BaseObject;


public class OvertimeTip extends BaseObject implements Serializable {

	private String id;
	private String flowName;
	private String specialty1;
	private String specialty2;
	private String specialty3;
	private String specialty4;
	private String specialty5;
	private String specialty6;
	private String specialty7;
	private String specialty8;
	private String specialty9;
	private String specialty10;
	private String userId;
	private String overtimeLimit;
	private Date setTime;
	
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
	public String getOvertimeLimit() {
		return overtimeLimit;
	}
	public void setOvertimeLimit(String overtimeLimit) {
		this.overtimeLimit = overtimeLimit;
	}
	public Date getSetTime() {
		return setTime;
	}
	public void setSetTime(Date setTime) {
		this.setTime = setTime;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getSpecialty5() {
		return specialty5;
	}
	public void setSpecialty5(String specialty5) {
		this.specialty5 = specialty5;
	}
	public String getSpecialty6() {
		return specialty6;
	}
	public void setSpecialty6(String specialty6) {
		this.specialty6 = specialty6;
	}
	public String getSpecialty10() {
		return specialty10;
	}
	public void setSpecialty10(String specialty10) {
		this.specialty10 = specialty10;
	}
	public String getSpecialty7() {
		return specialty7;
	}
	public void setSpecialty7(String specialty7) {
		this.specialty7 = specialty7;
	}
	public String getSpecialty8() {
		return specialty8;
	}
	public void setSpecialty8(String specialty8) {
		this.specialty8 = specialty8;
	}
	public String getSpecialty9() {
		return specialty9;
	}
	public void setSpecialty9(String specialty9) {
		this.specialty9 = specialty9;
	}
}
