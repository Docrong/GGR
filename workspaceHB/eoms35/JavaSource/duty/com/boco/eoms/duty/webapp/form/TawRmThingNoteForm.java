package com.boco.eoms.duty.webapp.form;


import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmThingNoteForm extends BaseForm implements java.io.Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;              
	
	private String thingId;     
	
	private String beginTime;    
	
	private String endTime;    
	
	private  String toUserId;    
	
	private  String noteComment; 
	
	private int  estate;       
	
	private String inputUserId;
	
	private String inputUserName;
	
	private String inputTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThingId() {
		return thingId;
	}
	public void setThingId(String thingId) {
		this.thingId = thingId;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getNoteComment() {
		return noteComment;
	}
	public void setNoteComment(String noteComment) {
		this.noteComment = noteComment;
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
	public String getInputUserName() {
		return inputUserName;
	}
	public void setInputUserName(String inputUserName) {
		this.inputUserName = inputUserName;
	}
	public String getInputTime() {
		return inputTime;
	}
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}
}
