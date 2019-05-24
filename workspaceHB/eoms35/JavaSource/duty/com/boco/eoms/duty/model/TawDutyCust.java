package com.boco.eoms.duty.model;

public class TawDutyCust {
	private int id;

	private String userId;

	private int flagId;

	private int flag;
	
	private String flagName;

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

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}

}
