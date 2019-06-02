package com.boco.eoms.commons.workdayset.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;
/**
* <p>
 * <a href="TawWorkdaySet.java.html"><i>View Source</i></a>
 * 
 * @author 
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_workdayset"
 */

public class TawWorkdaySet extends BaseObject {
	
	private String id;
	
	private String userId;
	
	private String areaId;
	
	private String workDate;
	
	private String createTime;
	
	private String startTime;
	
	private String endTime;
	
	private String status;
	
	private String deleted;

	private String cover;
	
	/**
	 * @hibernate.property length="32" not-null="true" unique="false"
	 */	
	public String getAreaId() {
		return areaId;
	}


	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	/**
	 * @hibernate.property length="32" not-null="true" unique="false"
	 */	
	public String getCreateTime() {
		return createTime;
	}

	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @hibernate.property length="5" not-null="false" 
	 */
	public String getDeleted() {
		return deleted;
	}


	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * @hibernate.property length="10" not-null="false" 
	 */
	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	 * @hibernate.property length="10" not-null="false" 
	 */
	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @hibernate.property length="5" not-null="false" 
	 */
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @hibernate.property length="30" not-null="false" 
	 */
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @hibernate.property length="30" not-null="false" 
	 */
	public String getWorkDate() {
		return workDate;
	}


	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	/**
	 * @hibernate.property length="5" not-null="false" 
	 */
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
