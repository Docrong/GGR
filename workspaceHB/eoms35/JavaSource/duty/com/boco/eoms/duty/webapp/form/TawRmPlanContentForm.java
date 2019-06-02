package com.boco.eoms.duty.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmPlanContentForm extends BaseForm implements java.io.Serializable{
	private String id;
	private String executecontentId;
	private String deptId;
	private String userId;
	private String month;
	private String content;
	private String monthplanName;
	private String roomId;
	private String workSerial;
	private String yearflag;
	private String monthflag;
	private String executeFlag;
	private String createTime;
	private String startTime;
	private String endTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getExecutecontentId() {
		return executecontentId;
	}

	public void setExecutecontentId(String executecontentId) {
		this.executecontentId = executecontentId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMonthplanName() {
		return monthplanName;
	}

	public void setMonthplanName(String monthplanName) {
		this.monthplanName = monthplanName;
	}
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getWorkSerial() {
		return workSerial;
	}

	public void setWorkSerial(String workSerial) {
		this.workSerial = workSerial;
	}

	public String getYearflag() {
		return yearflag;
	}

	public void setYearflag(String yearflag) {
		this.yearflag = yearflag;
	}

	public String getMonthflag() {
		return monthflag;
	}

	public void setMonthflag(String monthflag) {
		this.monthflag = monthflag;
	}
	public String getExecuteFlag() {
		return executeFlag;
	}

	public void setExecuteFlag(String executeFlag) {
		this.executeFlag = executeFlag;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/* To add non XDoclet-generated methods, create a file named
	    xdoclet-TawWorkbenchMemoForm.java 
	    containing the additional code and place it in your metadata/web directory.
	 */
	/**
	* @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	*                                                javax.servlet.http.HttpServletRequest)
	*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	 // reset any boolean data types to false
	
	}
}
