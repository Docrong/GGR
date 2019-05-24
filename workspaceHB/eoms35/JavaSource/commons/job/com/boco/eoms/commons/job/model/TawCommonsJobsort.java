package com.boco.eoms.commons.job.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @hibernate.class table="taw_commons_jobsort"
 */

public class TawCommonsJobsort extends BaseObject {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 任务类型名称
	 */
	private String jobSortName;

	/**
	 * 任务执行类
	 */
	private String jobClassName;

	/**
	 * 任务允许执行最大时间
	 */
	private Integer maxExecuteTime;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 删除标志
	 */
	private Integer deleted = new Integer(0);

	/**
	 * @hibernate.property length="5" not-null="true"
	 */
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	/**
	 * @hibernate.id column="id" generator-class="native" unsaved-value="null"
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true"
	 */
	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true"
	 */
	public String getJobSortName() {
		return jobSortName;
	}

	public void setJobSortName(String jobSortName) {
		this.jobSortName = jobSortName;
	}

	/**
	 * @hibernate.property length="50" not-null="false"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.property length="50" not-null="false"
	 */
	public Integer getMaxExecuteTime() {
		return maxExecuteTime;
	}

	public void setMaxExecuteTime(Integer maxExecuteTime) {
		this.maxExecuteTime = maxExecuteTime;
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