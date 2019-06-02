package com.boco.eoms.duty.model;

public class TawRmDutyEventSub {
	/**
	 * This class is used to generate the Struts Validator Form as well as the
	 * This class is used to generate Spring Validation rules as well as the
	 * Hibernate mapping file.
	 * 
	 * <p>
	 * <a href="User.java.html"><i>View Source</i></a>
	 * 
	 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated
	 *         by Dan Kibler (dan@getrolling.com) Extended to implement Acegi
	 *         UserDetails interface by David Carter david@carter.net 龚玉峰
	 *         2008-11-17
	 * 
	 * @struts.form include-all="true" extends="BaseForm"
	 * @hibernate.class table="taw_rm_dutyevent"
	 * 
	 */

	private String id;

	private String eventid;

	private String happentime;

	private String worksheetid;

	private String recordman;

	private String recordtime;

	private String content;

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
