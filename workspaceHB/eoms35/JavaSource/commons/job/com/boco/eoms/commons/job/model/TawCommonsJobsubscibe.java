package com.boco.eoms.commons.job.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="User.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_commons_jobsubscibe"
 */
public class TawCommonsJobsubscibe extends BaseObject {
	/**
     * ID
     */
	private String id;
	/**
     * 订阅号
     */
	private String subId;
	/**
     * 订阅人
     */
	private String subscriberId;
	/**
     * 订阅部门
     */
	private Integer subscriberDeptId;
	/**
     * 订阅时间
     */
	private String subscribeTime;
	/**
     * 任务类型ID
     */
	private Integer jobSortId;
	/**
     * 任务执行周期
     */
	private String jobCycle;
	/**
     * 任务执行类型：simple或者cron
     */
	private String jobType;
	/**
     * 删除标志
     */
	private Integer deleted;

	/**
	 * @hibernate.property length="50" not-null="true"
	 */
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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
	 * @hibernate.property length="50" not-null="true"
	 */
	public Integer getJobSortId() {
		return jobSortId;
	}

	public void setJobSortId(Integer jobSortId) {
		this.jobSortId = jobSortId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true"
	 */
	public String getJobCycle() {
		return jobCycle;
	}

	public void setJobCycle(String jobCycle) {
		this.jobCycle = jobCycle;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true"
	 */
	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * @hibernate.property length="50" not-null="true"
	 */
	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false"
	 */
	public Integer getSubscriberDeptId() {
		return subscriberDeptId;
	}

	public void setSubscriberDeptId(Integer subscriberDeptId) {
		this.subscriberDeptId = subscriberDeptId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false"
	 */
	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	/**
	 * @hibernate.property length="50" not-null="false"
	 */
	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
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
