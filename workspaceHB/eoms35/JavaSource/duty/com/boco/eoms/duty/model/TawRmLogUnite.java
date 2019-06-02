package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

public class TawRmLogUnite extends BaseObject {
	/**
	 * This class is used to generate the Struts Validator Form as well as the This
	 * class is used to generate Spring Validation rules as well as the Hibernate
	 * mapping file.
	 * 
	 * <p>
	 * <a href="User.java.html"><i>View Source</i></a>
	 * 
	 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
	 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
	 *         UserDetails interface by David Carter david@carter.net
	 * 
	 * @struts.form include-all="true" extends="BaseForm"
	 * @hibernate.class table="taw_rm_logunite"
	 */
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
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="255" not-null="true" unique="true"
	 */
	public String getWorkerNames() {
		return workerNames;
	}

	public void setWorkerNames(String workerNames) {
		this.workerNames = workerNames;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="5000" not-null="true" unique="true"
	 */
	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10000" not-null="true" unique="true"
	 */
	public String getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="2000" not-null="true" unique="true"
	 */
	public String getWorkbenchMemo() {
		return workbenchMemo;
	}

	public void setWorkbenchMemo(String workbenchMemo) {
		this.workbenchMemo = workbenchMemo;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="2000" not-null="true" unique="true"
	 */
	public String getDispatchRecord() {
		return dispatchRecord;
	}

	public void setDispatchRecord(String dispatchRecord) {
		this.dispatchRecord = dispatchRecord;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="2000" not-null="true" unique="true"
	 */
	public String getVisitRecord() {
		return visitRecord;
	}

	public void setVisitRecord(String visitRecord) {
		this.visitRecord = visitRecord;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="2000" not-null="true" unique="true"
	 */
	public String getReliefRecord() {
		return reliefRecord;
	}

	public void setReliefRecord(String reliefRecord) {
		this.reliefRecord = reliefRecord;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="2000" not-null="true" unique="true"
	 */
	public String getLoanRecord() {
		return loanRecord;
	}

	public void setLoanRecord(String loanRecord) {
		this.loanRecord = loanRecord;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getWorkSerial() {
		return workSerial;
	}

	public void setWorkSerial(String workSerial) {
		this.workSerial = workSerial;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getAssessorId() {
		return assessorId;
	}

	public void setAssessorId(String assessorId) {
		this.assessorId = assessorId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getAuditingReason() {
		return auditingReason;
	}

	public void setAuditingReason(String auditingReason) {
		this.auditingReason = auditingReason;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getAuditingTime() {
		return auditingTime;
	}

	public void setAuditingTime(String auditingTime) {
		this.auditingTime = auditingTime;
	}
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
