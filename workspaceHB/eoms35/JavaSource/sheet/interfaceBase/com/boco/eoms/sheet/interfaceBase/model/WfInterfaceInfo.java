package com.boco.eoms.sheet.interfaceBase.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * 接口轮训表
 */
public class WfInterfaceInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2888101240531764428L;

	private String id;
	
	private String mainBeanId;	
	
	private String linkBeanId;

	private String sheetType; // 工单类别:如业务开通

	private String serviceType; // 业务类别：如短信业务

	private String interfaceType; // 接口类型：如告警接口
	
	private String methodType;	//接口方法类型：如新增

	private String sheetKey;	//工单id

	private String linkId;		//link表id

	private Date createTime;	//建立时间

	private Date sendTime;		//发送成功时间

	private String isSended;	//是否已发送 0未发送，1已发送

	private String remark;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getIsSended() {
		return isSended;
	}

	public void setIsSended(String isSended) {
		this.isSended = isSended;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSheetKey() {
		return sheetKey;
	}

	public void setSheetKey(String sheetKey) {
		this.sheetKey = sheetKey;
	}

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public String getLinkBeanId() {
		return linkBeanId;
	}

	public void setLinkBeanId(String linkBeanId) {
		this.linkBeanId = linkBeanId;
	}

	public String getMainBeanId() {
		return mainBeanId;
	}

	public void setMainBeanId(String mainBeanId) {
		this.mainBeanId = mainBeanId;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

}
