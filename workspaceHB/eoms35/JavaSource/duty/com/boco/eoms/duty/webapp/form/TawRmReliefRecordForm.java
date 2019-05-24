package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmReliefRecordForm extends BaseForm implements Serializable {
	private String id;
	private String handoverId;
	private String successorId;
	private String roomId;
	private String time;
	private String lbProblem;
	private String nuiteFlag;
	private String handoverName;
	private String successorName;
	private String roomName;
	private String nuiteFlagName;
	private String userId;
	private String workSerial;
	private String createTime;
	private String startTime;
	private String endTime;
	private String tmpHandoverId;
	private String tmpSuccessorId;
	private String tmpRoomId;
	private String tmpTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getHandoverId() {
		return handoverId;
	}

	public void setHandoverId(String handoverId) {
		this.handoverId = handoverId;
	}

	public String getSuccessorId() {
		return successorId;
	}

	public void setSuccessorId(String successorId) {
		this.successorId = successorId;
	}
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getLbProblem() {
		return lbProblem;
	}

	public void setLbProblem(String lbProblem) {
		this.lbProblem = lbProblem;
	}
	
	public String getNuiteFlag() {
		return nuiteFlag;
	}

	public void setNuiteFlag(String nuiteFlag) {
		this.nuiteFlag = nuiteFlag;
	}
	
	public String getHandoverName() {
		return handoverName;
	}

	public void setHandoverName(String handoverName) {
		this.handoverName = handoverName;
	}

	public String getSuccessorName() {
		return successorName;
	}

	public void setSuccessorName(String successorName) {
		this.successorName = successorName;
	}
	
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public String getNuiteFlagName() {
		return nuiteFlagName;
	}

	public void setNuiteFlagName(String nuiteFlagName) {
		this.nuiteFlagName = nuiteFlagName;
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
	
	public String getTmpHandoverId() {
		return tmpHandoverId;
	}

	public void setTmpHandoverId(String tmpHandoverId) {
		this.tmpHandoverId = tmpHandoverId;
	}

	public String getTmpSuccessorId() {
		return tmpSuccessorId;
	}

	public void setTmpSuccessorId(String tmpSuccessorId) {
		this.tmpSuccessorId = tmpSuccessorId;
	}
	
	public String getTmpRoomId() {
		return tmpRoomId;
	}

	public void setTmpRoomId(String tmpRoomId) {
		this.tmpRoomId = tmpRoomId;
	}
	
	public String getTmpTime() {
		return tmpTime;
	}

	public void setTmpTime(String tmpTime) {
		this.tmpTime = tmpTime;
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
