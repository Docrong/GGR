package com.boco.eoms.duty.controller;

import org.apache.struts.validator.ValidatorForm;

public class TawDutyCustForm extends ValidatorForm {
	private int id = 0;

	private String userId = "";

	private int flagId = 0;

	private int flag = 0;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getFlagId() {
		return flagId;
	}

	public void setFlagId(int flagId) {
		this.flagId = flagId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
