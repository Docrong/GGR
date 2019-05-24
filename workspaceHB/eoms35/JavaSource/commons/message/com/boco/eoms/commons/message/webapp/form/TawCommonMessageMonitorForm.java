package com.boco.eoms.commons.message.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 * 
 * @struts.form name="tawCommonMessageMonitorForm"
 */
public class TawCommonMessageMonitorForm extends BaseForm implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String urgent;

	protected String cycle;

	protected String dispatcher;

	protected String dispatchTime;

	protected String endTime;

	protected String hieAmount;

	protected String hieArrive;

	protected String hieClose;

	protected String hieContent;

	protected String hieCount;

	protected String hieId;

	protected String hieInterval;

	protected String hieTimeLimit;

	protected String hieWay;

	protected String monitorId;

	protected String nightAllow;

	protected String receiver;

	protected String receiverType;

	protected String regionId;

	protected String startTime;

	protected String taskId;

	/** Default empty constructor. */
	public TawCommonMessageMonitorForm() {
	}

	public String getUrgent() {
		return this.urgent;
	}

	/**
	 */

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}

	public String getCycle() {
		return this.cycle;
	}

	/**
	 */

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getDispatcher() {
		return this.dispatcher;
	}

	/**
	 */

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	public String getDispatchTime() {
		return this.dispatchTime;
	}

	/**
	 */

	public void setDispatchTime(String dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	/**
	 */

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getHieAmount() {
		return this.hieAmount;
	}

	/**
	 */

	public void setHieAmount(String hieAmount) {
		this.hieAmount = hieAmount;
	}

	public String getHieArrive() {
		return this.hieArrive;
	}

	/**
	 */

	public void setHieArrive(String hieArrive) {
		this.hieArrive = hieArrive;
	}

	public String getHieClose() {
		return this.hieClose;
	}

	/**
	 */

	public void setHieClose(String hieClose) {
		this.hieClose = hieClose;
	}

	public String getHieContent() {
		return this.hieContent;
	}

	/**
	 */

	public void setHieContent(String hieContent) {
		this.hieContent = hieContent;
	}

	public String getHieCount() {
		return this.hieCount;
	}

	/**
	 */

	public void setHieCount(String hieCount) {
		this.hieCount = hieCount;
	}

	public String getHieId() {
		return this.hieId;
	}

	/**
	 */

	public void setHieId(String hieId) {
		this.hieId = hieId;
	}

	public String getHieInterval() {
		return this.hieInterval;
	}

	/**
	 */

	public void setHieInterval(String hieInterval) {
		this.hieInterval = hieInterval;
	}

	public String getHieTimeLimit() {
		return this.hieTimeLimit;
	}

	/**
	 */

	public void setHieTimeLimit(String hieTimeLimit) {
		this.hieTimeLimit = hieTimeLimit;
	}

	public String getHieWay() {
		return this.hieWay;
	}

	/**
	 */

	public void setHieWay(String hieWay) {
		this.hieWay = hieWay;
	}

	public String getMonitorId() {
		return this.monitorId;
	}

	/**
	 */

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	public String getNightAllow() {
		return this.nightAllow;
	}

	/**
	 */

	public void setNightAllow(String nightAllow) {
		this.nightAllow = nightAllow;
	}

	public String getReceiver() {
		return this.receiver;
	}

	/**
	 */

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverType() {
		return this.receiverType;
	}

	/**
	 */

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getRegionId() {
		return this.regionId;
	}

	/**
	 */

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getStartTime() {
		return this.startTime;
	}

	/**
	 */

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTaskId() {
		return this.taskId;
	}

	/**
	 */

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/*
	 * To add non XDoclet-generated methods, create a file named
	 * xdoclet-TawCommonMessageMonitorForm.java containing the additional code
	 * and place it in your metadata/web directory.
	 */
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

}
