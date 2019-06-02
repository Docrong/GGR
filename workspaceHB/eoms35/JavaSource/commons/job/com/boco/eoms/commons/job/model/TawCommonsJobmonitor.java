package com.boco.eoms.commons.job.model;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.job.service.ITawCommonsJobsortManager;
import com.boco.eoms.commons.job.util.JobStaticVariable;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="User.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_commons_jobmonitor"
 */
public class TawCommonsJobmonitor extends BaseObject {
	/**
	 * ID
	 */
	private String id;

	/**
	 * 任务订阅号
	 */
	private String jobSubId;

	/**
	 * 任务类型ID
	 */
	private Integer jobSortId;

	/**
	 * 任务开始执行时间
	 */
	private String executeStartTime;

	/**
	 * 任务执行结束时间
	 */
	private String executeEndTime;

	/**
	 * 允许执行的最大时间
	 */
	private Integer maxExecuteTime;

	/**
	 * 任务状态
	 */
	private Integer status;

	/**
	 * 任务类型名称（页面显示用）
	 */
	private String jobSortName;

	/**
	 * 状态名称（页面显示用）
	 */
	private String statusName;

	public String getJobSortName() {
		return ((ITawCommonsJobsortManager) ApplicationContextHolder
				.getInstance().getBean("ItawCommonsJobsortManager"))
				.sortId2name(this.jobSortId);
	}

	public void setJobSortName(String jobSortName) {
		this.jobSortName = jobSortName;
	}

	public String getStatusName() {
		if (JobStaticVariable.STATUS_RUNNING == status.intValue()) {
			statusName = "正在执行";
		} else if (JobStaticVariable.STATUS_END_NORMAL == status.intValue()) {
			statusName = "执行结束";
		}
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
	 * @hibernate.property length="50"
	 */
	public Integer getJobSortId() {
		return jobSortId;
	}

	public void setJobSortId(Integer jobSortId) {
		this.jobSortId = jobSortId;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public String getJobSubId() {
		return jobSubId;
	}

	public void setJobSubId(String jobSubId) {
		this.jobSubId = jobSubId;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public Integer getMaxExecuteTime() {
		return maxExecuteTime;
	}

	public void setMaxExecuteTime(Integer maxExecuteTime) {
		this.maxExecuteTime = maxExecuteTime;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public String getExecuteEndTime() {
		return executeEndTime;
	}

	public void setExecuteEndTime(String executeEndTime) {
		this.executeEndTime = executeEndTime;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public String getExecuteStartTime() {
		return executeStartTime;
	}

	public void setExecuteStartTime(String executeStartTime) {
		this.executeStartTime = executeStartTime;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
