package com.boco.eoms.km.log.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识操作日统计表
 * </p>
 * <p>
 * Description:知识操作日统计表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class KmOperateDateLog extends BaseObject {

	/**
	 * 主键
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * 操作日期
	 * 
	 */
	private java.util.Date operateDate;

	public void setOperateDate(java.util.Date operateDate) {
		this.operateDate = operateDate;
	}

	public java.util.Date getOperateDate() {
		return this.operateDate;
	}

	/**
	 * 
	 * 操作人
	 * 
	 */
	private java.lang.String operateUser;

	public void setOperateUser(java.lang.String operateUser) {
		this.operateUser = operateUser;
	}

	public java.lang.String getOperateUser() {
		return this.operateUser;
	}

	/**
	 * 
	 * 操作部门
	 * 
	 */
	private java.lang.String operateDept;

	public void setOperateDept(java.lang.String operateDept) {
		this.operateDept = operateDept;
	}

	public java.lang.String getOperateDept() {
		return this.operateDept;
	}

	/**
	 * 
	 * 新增操作次数
	 * 
	 */
	private java.lang.Integer addCount;

	public void setAddCount(java.lang.Integer addCount) {
		this.addCount = addCount;
	}

	public java.lang.Integer getAddCount() {
		if (this.addCount == null) {
			this.addCount = new java.lang.Integer(0);
		}
		return this.addCount;
	}

	/**
	 * 
	 * 新增操作积分
	 * 
	 */
	private java.lang.Integer addScore;

	public void setAddScore(java.lang.Integer addScore) {
		this.addScore = addScore;
	}

	public java.lang.Integer getAddScore() {
		if (this.addScore == null) {
			this.addScore = new java.lang.Integer(0);
		}
		return this.addScore;
	}

	/**
	 * 
	 * 修改操作次数
	 * 
	 */
	private java.lang.Integer modifyCount;

	public void setModifyCount(java.lang.Integer modifyCount) {
		this.modifyCount = modifyCount;
	}

	public java.lang.Integer getModifyCount() {
		if (this.modifyCount == null) {
			this.modifyCount = new java.lang.Integer(0);
		}
		return this.modifyCount;
	}

	/**
	 * 
	 * 修改操作积分
	 * 
	 */
	private java.lang.Integer modifyScore;

	public void setModifyScore(java.lang.Integer modifyScore) {
		this.modifyScore = modifyScore;
	}

	public java.lang.Integer getModifyScore() {
		if (this.modifyScore == null) {
			this.modifyScore = new java.lang.Integer(0);
		}
		return this.modifyScore;
	}

	/**
	 * 
	 * 失效操作次数
	 * 
	 */
	private java.lang.Integer overCount;

	public void setOverCount(java.lang.Integer overCount) {
		this.overCount = overCount;
	}

	public java.lang.Integer getOverCount() {
		if (this.overCount == null) {
			this.overCount = new java.lang.Integer(0);
		}
		return this.overCount;
	}

	/**
	 * 
	 * 失效操作积分
	 * 
	 */
	private java.lang.Integer overScore;

	public void setOverScore(java.lang.Integer overScore) {
		this.overScore = overScore;
	}

	public java.lang.Integer getOverScore() {
		if (this.overScore == null) {
			this.overScore = new java.lang.Integer(0);
		}
		return this.overScore;
	}

	/**
	 * 
	 * 删除操作次数
	 * 
	 */
	private java.lang.Integer deleteCount;

	public void setDeleteCount(java.lang.Integer deleteCount) {
		this.deleteCount = deleteCount;
	}

	public java.lang.Integer getDeleteCount() {
		if (this.deleteCount == null) {
			this.deleteCount = new java.lang.Integer(0);
		}
		return this.deleteCount;
	}

	/**
	 * 
	 * 删除操作积分
	 * 
	 */
	private java.lang.Integer deleteScore;

	public void setDeleteScore(java.lang.Integer deleteScore) {
		this.deleteScore = deleteScore;
	}

	public java.lang.Integer getDeleteScore() {
		if (this.deleteScore == null) {
			this.deleteScore = new java.lang.Integer(0);
		}
		return this.deleteScore;
	}

	/**
	 * 
	 * 上传文件次数
	 * 
	 */
	private java.lang.Integer upCount;

	public void setUpCount(java.lang.Integer upCount) {
		this.upCount = upCount;
	}

	public java.lang.Integer getUpCount() {
		if (this.upCount == null) {
			this.upCount = new java.lang.Integer(0);
		}
		return this.upCount;
	}

	/**
	 * 
	 * 上传文件积分
	 * 
	 */
	private java.lang.Integer upScore;

	public void setUpScore(java.lang.Integer upScore) {
		this.upScore = upScore;
	}

	public java.lang.Integer getUpScore() {
		if (this.upScore == null) {
			this.upScore = new java.lang.Integer(0);
		}
		return this.upScore;
	}

	/**
	 * 
	 * 下载文件次数
	 * 
	 */
	private java.lang.Integer downCount;

	public void setDownCount(java.lang.Integer downCount) {
		this.downCount = downCount;
	}

	public java.lang.Integer getDownCount() {
		if (this.downCount == null) {
			this.downCount = new java.lang.Integer(0);
		}
		return this.downCount;
	}

	/**
	 * 
	 * 下载文件积分
	 * 
	 */
	private java.lang.Integer downScore;

	public void setDownScore(java.lang.Integer downScore) {
		this.downScore = downScore;
	}

	public java.lang.Integer getDownScore() {
		if (this.downScore == null) {
			this.downScore = new java.lang.Integer(0);
		}
		return this.downScore;
	}

	/**
	 * 
	 * 引用知识次数
	 * 
	 */
	private java.lang.Integer useCount;

	public void setUseCount(java.lang.Integer useCount) {
		this.useCount = useCount;
	}

	public java.lang.Integer getUseCount() {
		if (this.useCount == null) {
			this.useCount = new java.lang.Integer(0);
		}
		return this.useCount;
	}

	/**
	 * 
	 * 引用知识积分
	 * 
	 */
	private java.lang.Integer useScore;

	public void setUseScore(java.lang.Integer useScore) {
		this.useScore = useScore;
	}

	public java.lang.Integer getUseScore() {
		if (this.useScore == null) {
			this.useScore = new java.lang.Integer(0);
		}
		return this.useScore;
	}

	/**
	 * 
	 * 评价知识次数
	 * 
	 */
	private java.lang.Integer opinionCount;

	public void setOpinionCount(java.lang.Integer opinionCount) {
		this.opinionCount = opinionCount;
	}

	public java.lang.Integer getOpinionCount() {
		if (this.opinionCount == null) {
			this.opinionCount = new java.lang.Integer(0);
		}
		return this.opinionCount;
	}

	/**
	 * 
	 * 评价知识积分
	 * 
	 */
	private java.lang.Integer opinionScore;

	public void setOpinionScore(java.lang.Integer opinionScore) {
		this.opinionScore = opinionScore;
	}

	public java.lang.Integer getOpinionScore() {
		if (this.opinionScore == null) {
			this.opinionScore = new java.lang.Integer(0);
		}
		return this.opinionScore;
	}

	/**
	 * 
	 * 提出修改次数
	 * 
	 */
	private java.lang.Integer adviceCount;

	public void setAdviceCount(java.lang.Integer adviceCount) {
		this.adviceCount = adviceCount;
	}

	public java.lang.Integer getAdviceCount() {
		if (this.adviceCount == null) {
			this.adviceCount = new java.lang.Integer(0);
		}
		return this.adviceCount;
	}

	/**
	 * 
	 * 提出修改积分
	 * 
	 */
	private java.lang.Integer adviceScore;

	public void setAdviceScore(java.lang.Integer adviceScore) {
		this.adviceScore = adviceScore;
	}

	public java.lang.Integer getAdviceScore() {
		if (this.adviceScore == null) {
			this.adviceScore = new java.lang.Integer(0);
		}
		return this.adviceScore;
	}

	/**
	 * 
	 * 审核通过次数
	 * 
	 */
	private java.lang.Integer auditOkCount;

	public void setAuditOkCount(java.lang.Integer auditOkCount) {
		this.auditOkCount = auditOkCount;
	}

	public java.lang.Integer getAuditOkCount() {
		if (this.auditOkCount == null) {
			this.auditOkCount = new java.lang.Integer(0);
		}
		return this.auditOkCount;
	}

	/**
	 * 
	 * 审核通过积分
	 * 
	 */
	private java.lang.Integer auditOkScore;

	public void setAuditOkScore(java.lang.Integer auditOkScore) {
		this.auditOkScore = auditOkScore;
	}

	public java.lang.Integer getAuditOkScore() {
		if (this.auditOkScore == null) {
			this.auditOkScore = new java.lang.Integer(0);
		}
		return this.auditOkScore;
	}

	/**
	 * 
	 * 审核驳回次数
	 * 
	 */
	private java.lang.Integer auditBackCount;

	public void setAuditBackCount(java.lang.Integer auditBackCount) {
		this.auditBackCount = auditBackCount;
	}

	public java.lang.Integer getAuditBackCount() {
		if (this.auditBackCount == null) {
			this.auditBackCount = new java.lang.Integer(0);
		}
		return this.auditBackCount;
	}

	/**
	 * 
	 * 审核驳回积分
	 * 
	 */
	private java.lang.Integer auditBackScore;

	public void setAuditBackScore(java.lang.Integer auditBackScore) {
		this.auditBackScore = auditBackScore;
	}

	public java.lang.Integer getAuditBackScore() {
		if (this.auditBackScore == null) {
			this.auditBackScore = new java.lang.Integer(0);
		}
		return this.auditBackScore;
	}

	/**
	 * 
	 * 操作类型
	 * 
	 */
	private java.lang.Integer operateId;

	public void setOperateId(java.lang.Integer operateId) {
		this.operateId = operateId;
	}

	public java.lang.Integer getOperateId() {
		if (this.operateId == null) {
			this.operateId = new java.lang.Integer(0);
		}
		return this.operateId;
	}

	/**
	 * 
	 * 阅读次数
	 * 
	 */
	private java.lang.Integer readCount;

	public java.lang.Integer getReadCount() {
		if (this.readCount == null) {
			this.readCount = new java.lang.Integer(0);
		}		
		return readCount;
	}

	public void setReadCount(java.lang.Integer readCount) {
		this.readCount = readCount;
	}

	/**
	 * 
	 * 阅读次数积分
	 * 
	 */
	private java.lang.Integer readScore;

	public java.lang.Integer getReadScore() {
		if (this.readScore == null) {
			this.readScore = new java.lang.Integer(0);
		}		
		return readScore;
	}

	public void setReadScore(java.lang.Integer readScore) {
		this.readScore = readScore;
	}

	public boolean equals(Object o) {
		if (o instanceof KmOperateDateLog) {
			KmOperateDateLog kmOperateDateLog = (KmOperateDateLog) o;
			if (this.id != null || this.id.equals(kmOperateDateLog.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}