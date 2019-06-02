/*
 * Created on 2008-1-11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseCollect implements Serializable{
	private String id;
	
	/**
     * 工单id
     */
    private String sheetId;

    /**
     * 工单标题
     */
    private String title;
	/**
     * 接收时限
     */
    private Date sheetAcceptLimit;

    /**
     * 完成时限
     */
    private Date sheetCompleteLimit;
    /**
     * 工单状态，未结束,已归档,已结束未归档
     */
    private Integer status;
    /**
     * 归档满意度
     */
    private Integer holdStatisfied;
    
    
    /**
     * link id
     */
    private String linkId;

    /**
     * main id
     */
    private String mainId;
    /**
     * 接收时限
     */
    private Date nodeAcceptLimit;

    /**
     * 完成时限
     */
    private Date nodeCompleteLimit;

    /**
     * 操作名称
     */
    private Integer operateType;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作组织类型,用户、角色、部门
     */
    private Integer operateOrgType;

    /**
     * 操作者用户ID
     */
    private String operateUserId;

    /**
     * 操作者部门ID
     */
    private String operateDeptId;

    /**
     * 操作者角色id
     */
    private String operateRoleId;

    /**
     * 目的对象的类型 部门 角色 人
     */
    private Integer toOrgType;

    /**
     * 目的用户id
     */
    private String toOrgUserId;

    /**
     * 目的部门id
     */
    private String toOrgDeptId;

    /**
     * 目的角色id
     */
    private String toOrgRoleId;

    /**
     * 
     * 接单类型,不超时,超时
     */
    private Integer acceptFlag;

    /**
     * 接单时间
     */
    private Date acceptTime;

    /**
     * 完成超时类型 超时 正常
     */
    private Integer completeFlag;
    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 上一条link id
     */
    private String preLinkId;
    
    private String activeTemplateId;
    
    
    /**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	
	/**
     * 是否被驳回
     */
    private String isReject;
    /**
     * 接单历时
     */
    private String acceptSecond;
    /**
     * 完成历时
     */
    private String completeSecond;
    
    
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the acceptFlag.
	 */
	public Integer getAcceptFlag() {
		return acceptFlag;
	}
	/**
	 * @param acceptFlag The acceptFlag to set.
	 */
	public void setAcceptFlag(Integer acceptFlag) {
		this.acceptFlag = acceptFlag;
	}
	/**
	 * @return Returns the acceptTime.
	 */
	public Date getAcceptTime() {
		return acceptTime;
	}
	/**
	 * @param acceptTime The acceptTime to set.
	 */
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}
	/**
	 * @return Returns the completeFlag.
	 */
	public Integer getCompleteFlag() {
		return completeFlag;
	}
	/**
	 * @param completeFlag The completeFlag to set.
	 */
	public void setCompleteFlag(Integer completeFlag) {
		this.completeFlag = completeFlag;
	}
	/**
	 * @return Returns the completeTime.
	 */
	public Date getCompleteTime() {
		return completeTime;
	}
	/**
	 * @param completeTime The completeTime to set.
	 */
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	/**
	 * @return Returns the holdStatisfied.
	 */
	public Integer getHoldStatisfied() {
		return holdStatisfied;
	}
	/**
	 * @param holdStatisfied The holdStatisfied to set.
	 */
	public void setHoldStatisfied(Integer holdStatisfied) {
		this.holdStatisfied = holdStatisfied;
	}
	/**
	 * @return Returns the id.
	 */
	public String getLinkId() {
		return linkId;
	}
	/**
	 * @param id The id to set.
	 */
	public void setLinkId(String id) {
		this.linkId = id;
	}
	/**
	 * @return Returns the mainId.
	 */
	public String getMainId() {
		return mainId;
	}
	/**
	 * @param mainId The mainId to set.
	 */
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	/**
	 * @return Returns the nodeAcceptLimit.
	 */
	public Date getNodeAcceptLimit() {
		return nodeAcceptLimit;
	}
	/**
	 * @param nodeAcceptLimit The nodeAcceptLimit to set.
	 */
	public void setNodeAcceptLimit(Date nodeAcceptLimit) {
		this.nodeAcceptLimit = nodeAcceptLimit;
	}
	/**
	 * @return Returns the nodeCompleteLimit.
	 */
	public Date getNodeCompleteLimit() {
		return nodeCompleteLimit;
	}
	/**
	 * @param nodeCompleteLimit The nodeCompleteLimit to set.
	 */
	public void setNodeCompleteLimit(Date nodeCompleteLimit) {
		this.nodeCompleteLimit = nodeCompleteLimit;
	}
	/**
	 * @return Returns the operateDeptId.
	 */
	public String getOperateDeptId() {
		return operateDeptId;
	}
	/**
	 * @param operateDeptId The operateDeptId to set.
	 */
	public void setOperateDeptId(String operateDeptId) {
		this.operateDeptId = operateDeptId;
	}
	/**
	 * @return Returns the operateOrgType.
	 */
	public Integer getOperateOrgType() {
		return operateOrgType;
	}
	/**
	 * @param operateOrgType The operateOrgType to set.
	 */
	public void setOperateOrgType(Integer operateOrgType) {
		this.operateOrgType = operateOrgType;
	}
	/**
	 * @return Returns the operateRoleId.
	 */
	public String getOperateRoleId() {
		return operateRoleId;
	}
	/**
	 * @param operateRoleId The operateRoleId to set.
	 */
	public void setOperateRoleId(String operateRoleId) {
		this.operateRoleId = operateRoleId;
	}
	/**
	 * @return Returns the operateTime.
	 */
	public Date getOperateTime() {
		return operateTime;
	}
	/**
	 * @param operateTime The operateTime to set.
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	/**
	 * @return Returns the operateType.
	 */
	public Integer getOperateType() {
		return operateType;
	}
	/**
	 * @param operateType The operateType to set.
	 */
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	/**
	 * @return Returns the operateUserId.
	 */
	public String getOperateUserId() {
		return operateUserId;
	}
	/**
	 * @param operateUserId The operateUserId to set.
	 */
	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}
	/**
	 * @return Returns the preLinkId.
	 */
	public String getPreLinkId() {
		return preLinkId;
	}
	/**
	 * @param preLinkId The preLinkId to set.
	 */
	public void setPreLinkId(String preLinkId) {
		this.preLinkId = preLinkId;
	}
	/**
	 * @return Returns the sheetAcceptLimit.
	 */
	public Date getSheetAcceptLimit() {
		return sheetAcceptLimit;
	}
	/**
	 * @param sheetAcceptLimit The sheetAcceptLimit to set.
	 */
	public void setSheetAcceptLimit(Date sheetAcceptLimit) {
		this.sheetAcceptLimit = sheetAcceptLimit;
	}
	/**
	 * @return Returns the sheetCompleteLimit.
	 */
	public Date getSheetCompleteLimit() {
		return sheetCompleteLimit;
	}
	/**
	 * @param sheetCompleteLimit The sheetCompleteLimit to set.
	 */
	public void setSheetCompleteLimit(Date sheetCompleteLimit) {
		this.sheetCompleteLimit = sheetCompleteLimit;
	}
	/**
	 * @return Returns the status.
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return Returns the toOrgDeptId.
	 */
	public String getToOrgDeptId() {
		return toOrgDeptId;
	}
	/**
	 * @param toOrgDeptId The toOrgDeptId to set.
	 */
	public void setToOrgDeptId(String toOrgDeptId) {
		this.toOrgDeptId = toOrgDeptId;
	}
	/**
	 * @return Returns the toOrgRoleId.
	 */
	public String getToOrgRoleId() {
		return toOrgRoleId;
	}
	/**
	 * @param toOrgRoleId The toOrgRoleId to set.
	 */
	public void setToOrgRoleId(String toOrgRoleId) {
		this.toOrgRoleId = toOrgRoleId;
	}
	/**
	 * @return Returns the toOrgType.
	 */
	public Integer getToOrgType() {
		return toOrgType;
	}
	/**
	 * @param toOrgType The toOrgType to set.
	 */
	public void setToOrgType(Integer toOrgType) {
		this.toOrgType = toOrgType;
	}
	/**
	 * @return Returns the toOrgUserId.
	 */
	public String getToOrgUserId() {
		return toOrgUserId;
	}
	/**
	 * @param toOrgUserId The toOrgUserId to set.
	 */
	public void setToOrgUserId(String toOrgUserId) {
		this.toOrgUserId = toOrgUserId;
	}
	/**
	 * @return Returns the acceptSecond.
	 */
	public String getAcceptSecond() {
		return acceptSecond;
	}
	/**
	 * @param acceptSecond The acceptSecond to set.
	 */
	public void setAcceptSecond(String acceptSecond) {
		this.acceptSecond = acceptSecond;
	}
	/**
	 * @return Returns the completeSecond.
	 */
	public String getCompleteSecond() {
		return completeSecond;
	}
	/**
	 * @param completeSecond The completeSecond to set.
	 */
	public void setCompleteSecond(String completeSecond) {
		this.completeSecond = completeSecond;
	}
	/**
	 * @return Returns the isReject.
	 */
	public String getIsReject() {
		return isReject;
	}
	/**
	 * @param isReject The isReject to set.
	 */
	public void setIsReject(String isReject) {
		this.isReject = isReject;
	}
	/**
	 * @return Returns the sheetId.
	 */
	public String getSheetId() {
		return sheetId;
	}
	/**
	 * @param sheetId The sheetId to set.
	 */
	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return Returns the activeTemplateId.
	 */
	public String getActiveTemplateId() {
		return activeTemplateId;
	}
	/**
	 * @param activeTemplateId The activeTemplateId to set.
	 */
	public void setActiveTemplateId(String activeTemplateId) {
		this.activeTemplateId = activeTemplateId;
	}
}
