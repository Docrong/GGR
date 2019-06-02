/*
 *
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 合并工单存储子单被更改的信息
 * 以便撤销的时候恢复使用
 * @author weichao
 *
 */
public class CommonFaultViSheetInfo implements Serializable {
	private String id;
	/**
	 * 主单main表Id
	 */
	private String mainId;
	/**
	 * 子单main表Id
	 */
	private String visId;
	private Integer deleted;
	/**
	 * task步骤Id
	 */
	private String taskId;
	
	/**
	 * main受理时限
	 */
	private Date mainSheetAcceptLimit;
	/**
	 * main完成时限
	 */
	private Date mainSheetCompleteLimit;
	/**
	 * task环节受理时限
	 */
	private Date taskSheetAcceptLimit;
	/**
	 * task环节完成时限
	 */
	private Date taskSheetCompleteLimit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public Date getMainSheetAcceptLimit() {
		return mainSheetAcceptLimit;
	}
	public void setMainSheetAcceptLimit(Date mainSheetAcceptLimit) {
		this.mainSheetAcceptLimit = mainSheetAcceptLimit;
	}
	public Date getMainSheetCompleteLimit() {
		return mainSheetCompleteLimit;
	}
	public void setMainSheetCompleteLimit(Date mainSheetCompleteLimit) {
		this.mainSheetCompleteLimit = mainSheetCompleteLimit;
	}
	public Date getTaskSheetAcceptLimit() {
		return taskSheetAcceptLimit;
	}
	public void setTaskSheetAcceptLimit(Date taskSheetAcceptLimit) {
		this.taskSheetAcceptLimit = taskSheetAcceptLimit;
	}
	public Date getTaskSheetCompleteLimit() {
		return taskSheetCompleteLimit;
	}
	public void setTaskSheetCompleteLimit(Date taskSheetCompleteLimit) {
		this.taskSheetCompleteLimit = taskSheetCompleteLimit;
	}
	public String getVisId() {
		return visId;
	}
	public void setVisId(String visId) {
		this.visId = visId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	
}
