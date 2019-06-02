package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmLogUniteForm extends BaseForm implements Serializable {
	
	private String id;
	private String workerNames;
	private String planContent;
	private String workOrder;
	private String workbenchMemo;
	private String dispatchRecord;
	private String visitRecord;
	private String loanRecord;
	private String reliefRecord;
	private String beginTime;
	private String endTime;
	private String userId;
	private String roomId;
	private String workSerial;
	private String assessorId;
	private String auditingReason;
	private String auditingTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkerNames() {
		return workerNames;
	}

	public void setWorkerNames(String workerNames) {
		this.workerNames = workerNames;
	}

	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}

	public String getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

	public String getWorkbenchMemo() {
		return workbenchMemo;
	}

	public void setWorkbenchMemo(String workbenchMemo) {
		this.workbenchMemo = workbenchMemo;
	}

	public String getDispatchRecord() {
		return dispatchRecord;
	}

	public void setDispatchRecord(String dispatchRecord) {
		this.dispatchRecord = dispatchRecord;
	}

	public String getVisitRecord() {
		return visitRecord;
	}

	public void setVisitRecord(String visitRecord) {
		this.visitRecord = visitRecord;
	}

	public String getReliefRecord() {
		return reliefRecord;
	}

	public void setReliefRecord(String reliefRecord) {
		this.reliefRecord = reliefRecord;
	}

	public String getLoanRecord() {
		return loanRecord;
	}

	public void setLoanRecord(String loanRecord) {
		this.loanRecord = loanRecord;
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

	public String getAssessorId() {
		return assessorId;
	}

	public void setAssessorId(String assessorId) {
		this.assessorId = assessorId;
	}

	public String getAuditingReason() {
		return auditingReason;
	}

	public void setAuditingReason(String auditingReason) {
		this.auditingReason = auditingReason;
	}

	public String getAuditingTime() {
		return auditingTime;
	}

	public void setAuditingTime(String auditingTime) {
		this.auditingTime = auditingTime;
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
