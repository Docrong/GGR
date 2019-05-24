package com.boco.eoms.businessupport.serviceprepare.model;

import java.io.Serializable;
/**
 * 环节库
 * @author yangliangliang
 *
 */
public class TaskLinks implements Serializable{
    /**
     * 主键
     */
	private String id;
    /**
     * 环节名
     */
	private String phaseId;	
    /**
     * 流程标识
     */
	private String sheetCode;		
    /**
     * 中文名称
     */
	private String chName;	
    /**
     *前置任务
     */
	private String p_taskName;
	/**
	 * 删除标示
	 */
	private String deleted;
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}	
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getP_taskName() {
		return p_taskName;
	}
	public void setP_taskName(String name) {
		p_taskName = name;
	}
	public String getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public String getSheetCode() {
		return sheetCode;
	}
	public void setSheetCode(String sheetCode) {
		this.sheetCode = sheetCode;
	}		
	
	
}
