package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmWorkorderRecordForm extends BaseForm implements Serializable {
	private String id;
	private String workOrderId;
	private String title;
	private String receiver;
	private String sender;
	private String replyTime;
	private String receiveTime;
	private String orderType;
	private String orderState;
	private String roomId;
	private String userId;
	private String workSerial;
	private String createTime;
	private String startTime;
	private String endTime;
	private String subProcessInstId;
	private String processInstId;
	private String activityInstId;
	private String workItemId;
	private String activityDefId;
	private String activityInstName;
	private String sheetType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	
	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getSubProcessInstId() {
		return subProcessInstId;
	}

	public void setSubProcessInstId(String subProcessInstId) {
		this.subProcessInstId = subProcessInstId;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getActivityInstId() {
		return activityInstId;
	}

	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
	}

	public String getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(String workItemId) {
		this.workItemId = workItemId;
	}

	public String getActivityDefId() {
		return activityDefId;
	}

	public void setActivityDefId(String activityDefId) {
		this.activityDefId = activityDefId;
	}

	public String getActivityInstName() {
		return activityInstName;
	}

	public void setActivityInstName(String activityInstName) {
		this.activityInstName = activityInstName;
	}
	
	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
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
