package com.boco.eoms.message.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

public class SmsSpSubmit extends BaseObject {
	
	private String id;
	
	private String msgContent;
	
	private String desttermId;
	
/*	private String atTime;*/
	
	private Date sendTime;
	
	private int msgFormat;
	
	private int msgLevel;
	
	
	

	/*public String getAtTime() {
		return atTime;
	}




	public void setAtTime(String atTime) {
		this.atTime = atTime;
	}


*/

	public String getDesttermId() {
		return desttermId;
	}




	public void setDesttermId(String desttermId) {
		this.desttermId = desttermId;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getMsgContent() {
		return msgContent;
	}




	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}




	public int getMsgFormat() {
		return msgFormat;
	}




	public void setMsgFormat(int msgFormat) {
		this.msgFormat = msgFormat;
	}




	public int getMsgLevel() {
		return msgLevel;
	}




	public void setMsgLevel(int msgLevel) {
		this.msgLevel = msgLevel;
	}


/*

	public int getNeedTime() {
		return needTime;
	}




	public void setNeedTime(int needTime) {
		this.needTime = needTime;
	}
*/



	public Date getSendTime() {
		return sendTime;
	}




	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

/*


	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}

*/


	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
