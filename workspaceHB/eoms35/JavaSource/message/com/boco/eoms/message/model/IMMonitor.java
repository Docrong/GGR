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

public class IMMonitor extends BaseObject {
	private String id;
	/**
	 * 
	 * 订阅服务ID
	 * 
	 */
	private String applyId;

	/**
	 * 业务ID
	 */
	private String buizid;
	/*
	 * 服务ID
	 */
	private String serviceId;

	/**
	 * 接受者id
	 */
	private String receiverId;
	/*
	 * 
	 * 是否立即发送
	 */
	private String isSendImediat;
	/**
	 * 是否重新采集数据
	 */
	private String regetData;
	/**
	 * 发送内容
	 */

	private String content;
	/**
	 * 发送文件路径
	 */
	private String filePath;
	/**
	 * 发送时间点
	 */
	private String dispatchTime;
	/**
	 * 删除标志
	 */
	private String deleted;
	/***************************************************************************
	 * toOrgIds 发送人员或者部门的串 格式：1,admin#1,sunshengtai#2,254 （其中1代表人员 2代表部门
	 * 254代表部门id）
	 */
	private String toOrgIds;

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
	public String getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(String dispatchTime) {
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getToOrgIds() {
		return toOrgIds;
	}

	public void setToOrgIds(String toOrgIds) {
		this.toOrgIds = toOrgIds;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
}
