package com.boco.eoms.message.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 * 
 * @struts.form name="smsServiceForm"
 */
public class SmsServiceForm extends BaseForm implements java.io.Serializable {

	protected String deleted;

	protected String id;

	protected String parentId;

	protected boolean isSendImediat;

	protected boolean isSendNight;

	protected String msgType;

	protected String name;

	protected String remark;

	protected Integer count;

	protected String cycleDay;

	protected String cycleHour;

	protected String cycleMonth;

	protected String endTime;

	protected String interval;

	protected String leaf;

	protected boolean regetData;

	protected String regetAddr;
	
	protected String regetProtocol;

	protected String sendDay;

	protected String sendHour;

	protected String sendMin;

	protected String sendStatus;

	protected String startTime;

	protected String userId;

	protected String cycleStatus;

	protected boolean isCycleSend;

	protected String status;
	
	protected boolean selStatus;
	
	//2009-03-31 add  非值班状态是否发送
	private String isSendUnDuty;
	
	public String getIsSendUnDuty() {
		return isSendUnDuty;
	}

	public void setIsSendUnDuty(String isSendUnDuty) {
		this.isSendUnDuty = isSendUnDuty;
	}

	/** Default empty constructor. */
	public SmsServiceForm() {
	}

	public String getDeleted() {
		return this.deleted;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getId() {
		return this.id;
	}

	/**
	 */

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsSendImediat() {
		return this.isSendImediat;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setIsSendImediat(boolean isSendImediat) {
		this.isSendImediat = isSendImediat;
	}

	public boolean getIsSendNight() {
		return this.isSendNight;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setIsSendNight(boolean isSendNight) {
		this.isSendNight = isSendNight;
	}

	public String getMsgType() {
		return this.msgType;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCount() {
		return this.count;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCycleDay() {
		return this.cycleDay;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setCycleDay(String cycleDay) {
		this.cycleDay = cycleDay;
	}

	public String getCycleHour() {
		return this.cycleHour;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setCycleHour(String cycleHour) {
		this.cycleHour = cycleHour;
	}

	public String getCycleMonth() {
		return this.cycleMonth;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setCycleMonth(String cycleMonth) {
		this.cycleMonth = cycleMonth;
	}

	public String getEndTime() {
		return this.endTime;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getInterval() {
		return this.interval;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getLeaf() {
		return this.leaf;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public boolean getRegetData() {
		return this.regetData;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setRegetData(boolean regetData) {
		this.regetData = regetData;
	}

	public String getSendDay() {
		return this.sendDay;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setSendDay(String sendDay) {
		this.sendDay = sendDay;
	}

	public String getSendHour() {
		return this.sendHour;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setSendHour(String sendHour) {
		this.sendHour = sendHour;
	}

	public String getSendMin() {
		return this.sendMin;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setSendMin(String sendMin) {
		this.sendMin = sendMin;
	}

	public String getSendStatus() {
		return this.sendStatus;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getUserId() {
		return this.userId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCycleStatus() {
		return this.cycleStatus;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setCycleStatus(String cycleStatus) {
		this.cycleStatus = cycleStatus;
	}

	public boolean getIsCycleSend() {
		return this.isCycleSend;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setIsCycleSend(boolean isCycleSend) {
		this.isCycleSend = isCycleSend;
	}

	public String getParentId() {
		return this.parentId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRegetAddr() {
		return regetAddr;
	}
	/**
	 * @struts.validator type="required"
	 */
	public void setRegetAddr(String regetAddr) {
		this.regetAddr = regetAddr;
	}
	
	
	

	public String getRegetProtocol() {
		return regetProtocol;
	}
	/**
	 * @struts.validator type="required"
	 */
	public void setRegetProtocol(String regetProtocol) {
		this.regetProtocol = regetProtocol;
	}
	
	public boolean getSelStatus() {
		return selStatus;
	}
	/**
	 * @struts.validator type="required"
	 */
	public void setSelStatus(boolean selStatus) {
		this.selStatus = selStatus;
	}

	/*
	 * To add non XDoclet-generated methods, create a file named
	 * xdoclet-SmsServiceForm.java containing the additional code and place it
	 * in your metadata/web directory.
	 */
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

}
