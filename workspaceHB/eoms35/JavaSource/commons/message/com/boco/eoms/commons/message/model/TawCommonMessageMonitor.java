package com.boco.eoms.commons.message.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawCommonMessageMonitor.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_common_messagemonitor"
 */
public class TawCommonMessageMonitor extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	private String monitorId;

	private String hieId;

	private String taskId;

	private Integer hieTimeLimit;

	private Integer receiverType;

	private Integer hieWay;

	private Integer hieInterval;

	private Integer hieAmount;

	private Integer hieCount;

	private Integer regionId;

	private String dispatchTime;

	private Integer hieArrive;

	private String hieContent;

	private Integer hieClose;

	private String dispatcher;

	private String startTime;

	private String endTime;

	private Integer nightAllow;

	private Integer cycle;

	private String receiver;

	private String urgent;

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="紧急程度"
	 * @return
	 */
	public String getUrgent() {
		return urgent;
	}

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="循环周期"
	 * @return
	 */
	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="崔办人"
	 * @return
	 */
	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="记录插入时间"
	 * @return
	 */
	public String getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(String dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="失效时间"
	 * @return
	 */
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="崔办次数"
	 * @return
	 */
	public Integer getHieAmount() {
		return hieAmount;
	}

	public void setHieAmount(Integer hieAmount) {
		this.hieAmount = hieAmount;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="消息是否送达"
	 * @return
	 */
	public Integer getHieArrive() {
		return hieArrive;
	}

	public void setHieArrive(Integer hieArrive) {
		this.hieArrive = hieArrive;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="关闭崔办状态"
	 * @return
	 */
	public Integer getHieClose() {
		return hieClose;
	}

	public void setHieClose(Integer hieClose) {
		this.hieClose = hieClose;
	}

	/**
	 * @hibernate.property length="500"
	 * @eoms.show
	 * @eoms.cn name="消息内容"
	 * @return
	 */
	public String getHieContent() {
		return hieContent;
	}

	public void setHieContent(String hieContent) {
		this.hieContent = hieContent;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="崔办计数器"
	 * @return
	 */
	public Integer getHieCount() {
		return hieCount;
	}

	public void setHieCount(Integer hieCount) {
		this.hieCount = hieCount;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="订阅的服务编号"
	 * @return
	 */
	public String getHieId() {
		return hieId;
	}

	public void setHieId(String hieId) {
		this.hieId = hieId;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="崔办间隔时间"
	 * @return
	 */
	public Integer getHieInterval() {
		return hieInterval;
	}

	public void setHieInterval(Integer hieInterval) {
		this.hieInterval = hieInterval;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="崔办时限"
	 * @return
	 */
	public Integer getHieTimeLimit() {
		return hieTimeLimit;
	}

	public void setHieTimeLimit(Integer hieTimeLimit) {
		this.hieTimeLimit = hieTimeLimit;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="崔办方式"
	 * @return
	 */
	public Integer getHieWay() {
		return hieWay;
	}

	public void setHieWay(Integer hieWay) {
		this.hieWay = hieWay;
	}

	/**
	 * @hibernate.id column="monitorId" generator-class="uuid.hex"
	 *               unsaved-value="null"
	 */
	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="是否晚上发送"
	 * @return
	 */
	public Integer getNightAllow() {
		return nightAllow;
	}

	public void setNightAllow(Integer nightAllow) {
		this.nightAllow = nightAllow;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="信息接收者"
	 * @return
	 */
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="接收者类型"
	 * @return
	 */
	public Integer getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(Integer receiverType) {
		this.receiverType = receiverType;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="所属地区"
	 * @return
	 */
	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="生效时间"
	 * @return
	 */
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="任务流水号"
	 * @return
	 */
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
