package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmDispatchRecordForm extends BaseForm implements Serializable {
	private String id;
	private String fileName;
	private String fileSource;
	private String fileProperty;
	private String time;
	private String dispatchDeptId;
	private String dispatchDept;
	private String dispatcherId;
	private String dispatcher;
	private String receiver;
	private String excuteRequest;
	private String remark;
    private String deptId;
	private String userId;
	private String roomId;
	private String workSerial;
	private String createTime;
	private String startTime;
	private String endTime;
	private String tmpFileName;
	private String tmpFileSource;
	private String tmpFileProperty;
	private String tmpTime;
	private String tmpDispatchDeptId;
	private String tmpDispatchDept;
	private String tmpDispatcherId;
	private String tmpDispatcher;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSource() {
		return fileSource;
	}

	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}
	
	public String getFileProperty() {
		return fileProperty;
	}

	public void setFileProperty(String fileProperty) {
		this.fileProperty = fileProperty;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getDispatchDeptId() {
		return dispatchDeptId;
	}

	public void setDispatchDeptId(String dispatchDeptId) {
		this.dispatchDeptId = dispatchDeptId;
	}
	
	public String getDispatchDept() {
		return dispatchDept;
	}

	public void setDispatchDept(String dispatchDept) {
		this.dispatchDept = dispatchDept;
	}
	
	public String getDispatcherId() {
		return dispatcherId;
	}

	public void setDispatcherId(String dispatcherId) {
		this.dispatcherId = dispatcherId;
	}
	
	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getExcuteRequest() {
		return excuteRequest;
	}

	public void setExcuteRequest(String excuteRequest) {
		this.excuteRequest = excuteRequest;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    public String getDeptId()
    {
        return this.deptId;
    }
   /**
    */

    public void setDeptId( String deptId )
    {
        this.deptId = deptId;
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
	
	public String getTmpFileName() {
		return tmpFileName;
	}

	public void setTmpFileName(String tmpFileName) {
		this.tmpFileName = tmpFileName;
	}
	
	public String getTmpFileSource() {
		return tmpFileSource;
	}

	public void setTmpFileSource(String tmpFileSource) {
		this.tmpFileSource = tmpFileSource;
	}
	
	public String getTmpFileProperty() {
		return tmpFileProperty;
	}

	public void setTmpFileProperty(String tmpFileProperty) {
		this.tmpFileProperty = tmpFileProperty;
	}
	
	public String getTmpTime() {
		return tmpTime;
	}

	public void setTmpTime(String tmpTime) {
		this.tmpTime = tmpTime;
	}
	
	public String getTmpDispatchDeptId() {
		return tmpDispatchDeptId;
	}

	public void setTmpDispatchDeptId(String tmpDispatchDeptId) {
		this.tmpDispatchDeptId = tmpDispatchDeptId;
	}
	
	public String getTmpDispatchDept() {
		return tmpDispatchDept;
	}

	public void setTmpDispatchDept(String tmpDispatchDept) {
		this.tmpDispatchDept = tmpDispatchDept;
	}
	
	public String getTmpDispatcherId() {
		return tmpDispatcherId;
	}

	public void setTmpDispatcherId(String tmpDispatcherId) {
		this.tmpDispatcherId = tmpDispatcherId;
	}
	
	public String getTmpDispatcher() {
		return tmpDispatcher;
	}

	public void setTmpDispatcher(String tmpDispatcher) {
		this.tmpDispatcher = tmpDispatcher;
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
