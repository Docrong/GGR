package com.boco.eoms.message.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;
/**
* <p>
 * <a href="EmailMonitor.java.html"><i>View Source</i></a>
 * 
 * @author 
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="email_monitor"
 */

public class EmailMonitor extends BaseObject {
	private String id;
	private String applyId;
	private String buizid;
	private String serviceId;
	private String receiverId;
	private String isSendImediat;
	private String regetData;
	private String addresser;
	private String addressee;
	private Date dispatchTime;
	private String subject;
	private String content;
	private String accessoriesUrl;
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
	 * @hibernate.property length="2000" not-null="true" unique="true"
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
	 * @hibernate.property length="2000" not-null="true" unique="true"
	 * @eoms.show
	 * @eoms.cn name="收件人"
	 * @return
	 */
	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="2000" not-null="true" unique="true"
	 * @eoms.show
	 * @eoms.cn name="发件人"
	 * @return
	 */
	public String getAddresser() {
		return addresser;
	}

	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="500" not-null="true" unique="true"
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
	 * @hibernate.property length="1000"
	 * @eoms.show
	 * @eoms.cn name="附件URL地址" not-null="false" unique="false"
	 * @return
	 */
	public String getAccessoriesUrl() {
		return accessoriesUrl;
	}

	public void setAccessoriesUrl(String accessoriesUrl) {
		this.accessoriesUrl = accessoriesUrl;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="200"
	 * @eoms.show
	 * @eoms.cn name="邮件主题" not-null="true" unique="false"
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
