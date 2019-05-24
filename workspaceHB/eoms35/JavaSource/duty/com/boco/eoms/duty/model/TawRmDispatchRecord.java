package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

public class TawRmDispatchRecord extends BaseObject {
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
	 * @hibernate.class table="taw_rm_dispatchrecord"
	 */
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
	private String userId;
	private String roomId;
	private String workSerial;
	private String createTime;
	
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
	 * @hibernate.property length="50" not-null="true" unique="true"
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true" unique="true"
	 */
	public String getFileSource() {
		return fileSource;
	}

	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true" unique="true"
	 */
	public String getFileProperty() {
		return fileProperty;
	}

	public void setFileProperty(String fileProperty) {
		this.fileProperty = fileProperty;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getDispatchDeptId() {
		return dispatchDeptId;
	}

	public void setDispatchDeptId(String dispatchDeptId) {
		this.dispatchDeptId = dispatchDeptId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true" unique="true"
	 */
	public String getDispatchDept() {
		return dispatchDept;
	}

	public void setDispatchDept(String dispatchDept) {
		this.dispatchDept = dispatchDept;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getDispatcherId() {
		return dispatcherId;
	}

	public void setDispatcherId(String dispatcherId) {
		this.dispatcherId = dispatcherId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="100" not-null="false" unique="true"
	 */
	public String getExcuteRequest() {
		return excuteRequest;
	}

	public void setExcuteRequest(String excuteRequest) {
		this.excuteRequest = excuteRequest;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="100" not-null="false" unique="true"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
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
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
