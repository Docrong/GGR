package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmVisitRecordForm extends BaseForm implements Serializable {
	private String id;
	private String visitorName;
	private String company;
	private String visitTime;
	private String leftTime;
	private String reason;
	private String receiver;
	private String userId;
	private String roomId;
	private String workSerial;
	private String createTime;
	private String startTime;
	private String endTime;
	private String tmpVisitorName;
	private String tmpCompany;
	private String tmpVisitTime;
	private String tmpLeftTime;
	private String tmpReceiver;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(String leftTime) {
		this.leftTime = leftTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getTmpVisitorName() {
		return tmpVisitorName;
	}

	public void setTmpVisitorName(String tmpVisitorName) {
		this.tmpVisitorName = tmpVisitorName;
	}

	public String getTmpCompany() {
		return tmpCompany;
	}

	public void setTmpCompany(String tmpCompany) {
		this.tmpCompany = tmpCompany;
	}

	public String getTmpVisitTime() {
		return tmpVisitTime;
	}

	public void setTmpVisitTime(String tmpVisitTime) {
		this.tmpVisitTime = tmpVisitTime;
	}

	public String getTmpLeftTime() {
		return tmpLeftTime;
	}

	public void setTmpLeftTime(String tmpLeftTime) {
		this.tmpLeftTime = tmpLeftTime;
	}

	public String getTmpReceiver() {
		return tmpReceiver;
	}

	public void setTmpReceiver(String tmpReceiver) {
		this.tmpReceiver = tmpReceiver;
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
