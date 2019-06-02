package com.boco.eoms.duty.model;

import java.io.Serializable;

public class TawRmThing implements Serializable {

	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public TawRmThing(){}
	
	private String id;
	
	private String roomId;
	
	private String thingName;
	
	private int isForUse;
	
	private int estate;
	
	private String inputUserId;
	
	private String inputTime;
	
	private String thingComment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getThingName() {
		return thingName;
	}

	public void setThingName(String thingName) {
		this.thingName = thingName;
	}

	public int getIsForUse() {
		return isForUse;
	}

	public void setIsForUse(int isForUse) {
		this.isForUse = isForUse;
	}

	public int getEstate() {
		return estate;
	}

	public void setEstate(int estate) {
		this.estate = estate;
	}

	public String getInputUserId() {
		return inputUserId;
	}

	public void setInputUserId(String inputUserId) {
		this.inputUserId = inputUserId;
	}

	public String getInputTime() {
		return inputTime;
	}

	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

	public String getThingComment() {
		return thingComment;
	}

	public void setThingComment(String thingComment) {
		this.thingComment = thingComment;
	}
}
