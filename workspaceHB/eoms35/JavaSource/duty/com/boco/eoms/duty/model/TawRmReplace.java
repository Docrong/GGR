package com.boco.eoms.duty.model;
 /*
  * 龚玉峰 2008-09-09
  */
public class TawRmReplace {
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
	 *         UserDetails interface by David Carter david@carter.net
	 * 
	 * @struts.form include-all="true" extends="BaseForm"
	 * @hibernate.class table="taw_rm_replace"
	 */
	private String id; // 主键
	
	private String roomId; // 机房id

	private String inputdate; // 插入时间

	private String workserial; // 替班的班次

	private String dutydate; // 替班的时间

	private String hander; // 替班的申请人

	private String receiver; // 替班的交接人

	private String flag; // 替班标志
	
	private String flagName;
	
	private String roomName;
	
	private String handerName;
	
	private String receiverName;

	private String reason; // 替班原因

	

	private String remark; // 备忘

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getDutydate() {
		return dutydate;
	}

	public void setDutydate(String dutydate) {
		this.dutydate = dutydate;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getHander() {
		return hander;
	}

	public void setHander(String hander) {
		this.hander = hander;
	}
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
	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="300" not-null="true" unique="true"
	 */
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
	 * @hibernate.property length="300" not-null="true" unique="true"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getWorkserial() {
		return workserial;
	}

	public void setWorkserial(String workserial) {
		this.workserial = workserial;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}

	public String getHanderName() {
		return handerName;
	}

	public void setHanderName(String handerName) {
		this.handerName = handerName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
}
