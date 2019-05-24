package com.boco.eoms.workbench.infopub.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:信息发布公告
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 3:31:20 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="myappfuse_test"
 * 
 */
public class dutyWork extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -621109313487845702L;

	/**
	 * 主键
	 */
	private String id;
	
	private String dutydate;
	
	private String starttimeDefined;
	
	private String endtimeDefined;
	
	private String username;
	
	private String mobile;

	

	
	

	
	
	public boolean equals(Object o) {
		if (o instanceof Forums) {
			Thread thread = (Thread) o;
			if (this.id != null || this.id.equals(thread.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	

	public String getDutydate() {
		return dutydate;
	}

	public void setDutydate(String dutydate) {
		this.dutydate = dutydate;
	}

	public String getEndtimeDefined() {
		return endtimeDefined;
	}

	public void setEndtimeDefined(String endtimeDefined) {
		this.endtimeDefined = endtimeDefined;
	}

	public String getStarttimeDefined() {
		return starttimeDefined;
	}

	public void setStarttimeDefined(String starttimeDefined) {
		this.starttimeDefined = starttimeDefined;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
