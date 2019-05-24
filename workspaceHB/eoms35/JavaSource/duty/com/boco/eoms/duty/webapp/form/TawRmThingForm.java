package com.boco.eoms.duty.webapp.form;

import org.apache.struts.validator.ValidatorForm;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.common.util.StaticMethod;


/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * 2009-4-28
 * </p>
 * 
 * @Author panyunfu
 * @Version 3.5
 *
 */
public class TawRmThingForm  extends BaseForm implements java.io.Serializable{
	 /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String  id;        
	
	private String  thingName;    
	
	private int  isForUse;    
	
	private String  roomId;      
	
	private int  estate;        
	
	private String  inputUserId;
	
	private String  inputTime;     
	
	private String  thingComment;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	public String getRoomId() {
		return roomId;
	}
	
	public void setRoomId(String roomId) {
		this.roomId = roomId;
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
		return StaticMethod.nullObject2String(thingComment).trim();
	}
	
	public void setThingComment(String thingComment) {
		this.thingComment = thingComment;
	}
}
