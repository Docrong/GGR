package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

public class TawRmReliefRecord extends BaseObject {
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
	 * @hibernate.class table="taw_rm_reliefrecord"
	 */
	private String id;
	private String handoverId;
	private String successorId;
	private String roomId;
	private String time;
	private String lbProblem;
	private String nuiteFlag;
	private String userId;
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
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getHandoverId() {
		return handoverId;
	}

	public void setHandoverId(String handoverId) {
		this.handoverId = handoverId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getSuccessorId() {
		return successorId;
	}

	public void setSuccessorId(String successorId) {
		this.successorId = successorId;
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
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="100" not-null="true" unique="true"
	 */
	public String getLbProblem() {
		return lbProblem;
	}

	public void setLbProblem(String lbProblem) {
		this.lbProblem = lbProblem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getNuiteFlag() {
		return nuiteFlag;
	}

	public void setNuiteFlag(String nuiteFlag) {
		this.nuiteFlag = nuiteFlag;
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
	public String getWorkSerial() {
		return workSerial;
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

	public void setWorkSerial(String workSerial) {
		this.workSerial = workSerial;
	}
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
