package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmDutyEventSubForm extends BaseForm implements Serializable{
	private String id;

	private String eventid;

	private String happentime;

	private String worksheetid;

	private String recordman;

	private String recordtime;

	private String content;

	private String remark;
	
	private String roomid;
	
	private String deptid;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getHappentime() {
		return happentime;
	}

	public void setHappentime(String happentime) {
		this.happentime = happentime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecordman() {
		return recordman;
	}

	public void setRecordman(String recordman) {
		this.recordman = recordman;
	}

	public String getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWorksheetid() {
		return worksheetid;
	}

	public void setWorksheetid(String worksheetid) {
		this.worksheetid = worksheetid;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

}
