package com.boco.eoms.workbench.infopub.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:贴子审核历史
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 28, 2008 8:24:10 PM
 * </p>
 * 
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadAuditHistory extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3609449032002023240L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 信息id
	 */
	private String threadId;

	/**
	 * 信息审核状态
	 */
	private String status;

	/**
	 * 上一步审核信息
	 */
	private String parentId;

	/**
	 * 审核意见
	 */
	private String opinion;

	/**
	 * 组织id，如人员，部门，角色
	 */
	private String orgId;

	/**
	 * 组织类型，人员，部门，角色，StaticVarible
	 */
	private String orgType;

	/**
	 * 审核时间
	 */
	private Date auditTime;

	/**
	 * 当前步骤
	 */
	private String isCurrent;

	/**
	 * 提交审核时间
	 */
	private Date submitTime;

	/**
	 * 提交审核时说明
	 */
	private String note;

	/**
	 * 删除标记
	 */
	private String isDel;

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType
	 *            the orgType to set
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the isCurrent
	 */
	public String getIsCurrent() {
		return isCurrent;
	}

	/**
	 * @param isCurrent
	 *            the isCurrent to set
	 */
	public void setIsCurrent(String isCurrent) {
		this.isCurrent = isCurrent;
	}

	/**
	 * @return the opinion
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * @param opinion
	 *            the opinion to set
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the threadId
	 */
	public String getThreadId() {
		return threadId;
	}

	/**
	 * @param threadId
	 *            the threadId to set
	 */
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	/**
	 * @return the auditTime
	 */
	public Date getAuditTime() {
		return auditTime;
	}

	/**
	 * @param auditTime
	 *            the auditTime to set
	 * 
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof ThreadAuditHistory) {
			ThreadAuditHistory threadAuditHistory = (ThreadAuditHistory) o;
			if (this.id != null || this.id.equals(threadAuditHistory.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	

	/**
	 * @return the submitTime
	 */
	public Date getSubmitTime() {
		return submitTime;
	}

	/**
	 * @param submitTime
	 *            the submitTime to set
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the isDel
	 */
	public String getIsDel() {
		return isDel;
	}

	/**
	 * @param isDel
	 *            the isDel to set
	 */
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 构造方法
	 * 
	 * @param orgId
	 *            组织id,部门，用户，角色
	 * @param orgType
	 *            组类型 部门，用户，角色
	 * 
	 */
	public ThreadAuditHistory JSON2ThreadAuditHistory(String orgId, String orgType,String threadId,ThreadAuditHistory threadAuditHistory) {
		threadAuditHistory.setThreadId(threadId);
		threadAuditHistory.setOrgId(orgId);
		threadAuditHistory.setOrgType(orgType);
		return threadAuditHistory;
	}

}
