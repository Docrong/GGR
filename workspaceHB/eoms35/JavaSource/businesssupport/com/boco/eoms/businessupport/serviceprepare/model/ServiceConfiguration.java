package com.boco.eoms.businessupport.serviceprepare.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 服务配置表
 * @author yangliangliang
 *
 */

public class ServiceConfiguration implements Serializable{
    /**
     * 主键
     */
	private String id;
    /**
     * 环节名id
     */
	private String taskId;	
    /**
     * 专业服务id
     */
	private String specialtyId;		
    /**
     * 流程类型ID
     */
	private String processId;		
    /**
     * 产品规格表id
     */
	private String businessId;		
    /**
     * 是否必做任务
     */
	private String isNeed;	
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建时间
     */
	private Date createTime;	
	/**
	 * 删除标示
	 */
	private String deleted;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsNeed() {
		return isNeed;
	}
	public void setIsNeed(String isNeed) {
		this.isNeed = isNeed;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSpecialtyId() {
		return specialtyId;
	}
	public void setSpecialtyId(String specialtyId) {
		this.specialtyId = specialtyId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}		
}
