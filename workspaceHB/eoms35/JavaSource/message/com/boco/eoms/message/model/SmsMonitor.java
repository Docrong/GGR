package com.boco.eoms.message.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;
/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="sms_monitor"
 */
public class SmsMonitor extends BaseObject {
	private String id;
	
	private String applyId;
	
	private String buizid;
	
	private String serviceId;
	
	private String receiverId;
	
	private String isSendImediat;
	
	private String regetData;
	
	private String mobile;
	
	private Date dispatchTime;
	
	private String content;
	
	private String isCycleSend;
	
	private String deleted;
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="true" unique="true"
	 * @eoms.show
	 * @eoms.cn name="订阅服务ID"
	 * @return
	 */
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="500" not-null="true" unique="true"
	 * @eoms.show
	 * @eoms.cn name="发送内容"
	 * @return
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="20" not-null="true" unique="true"
	 * @eoms.show
	 * @eoms.cn name="发送时间点"
	 * @return
	 */
	public Date getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
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
	 * @hibernate.property length="100" not-null="true" unique="true"
	 * @eoms.show
	 * @eoms.cn name="发送手机号码"
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 * @eoms.show
	 * @eoms.cn name="接收者ID"
	 * @return
	 */
	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="false"
	 * @eoms.show
	 * @eoms.cn name="业务ID"
	 * @return
	 */
	public String getBuizid() {
		return buizid;
	}

	public void setBuizid(String buizid) {
		this.buizid = buizid;
	}	
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="false"
	 * @eoms.show
	 * @eoms.cn name="服务ID"
	 * @return
	 */
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="false"
	 * @eoms.show
	 * @eoms.cn name="是否立即发送"
	 * @return
	 */
	public String getIsSendImediat() {
		return isSendImediat;
	}

	public void setIsSendImediat(String isSendImediat) {
		this.isSendImediat = isSendImediat;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="5" not-null="false" unique="false"
	 * @eoms.show
	 * @eoms.cn name="删除标志"
	 * @return
	 */
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="1"
	 * @eoms.show
	 * @eoms.cn name="是否重新采集数据" not-null="false" unique="true"
	 * @return
	 */
	public String getRegetData() {
		return regetData;
	}

	public void setRegetData(String regetData) {
		this.regetData = regetData;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="5"
	 * @eoms.show
	 * @eoms.cn name="是否循环发送" not-null="false" unique="true"
	 * @return
	 */
	public String getIsCycleSend() {
		return isCycleSend;
	}

	public void setIsCycleSend(String isCycleSend) {
		this.isCycleSend = isCycleSend;
	}

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

}
