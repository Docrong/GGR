package com.boco.eoms.extra.supplierkpi.model;

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
 * @hibernate.class table="taw_supplierkpi_checkModule"
 */


public class TawsuCheckModule extends BaseObject{
	private String id;
	private String checkUser;//审批人
	private String crUser;//送审人
	private String crTime;//送审时间
	private String checkTime;//审批时间
	private String checkContent;//审批意见
	private String checkUsers;//待审批人
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCheckContent() {
		return checkContent;
	}
	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCheckUsers() {
		return checkUsers;
	}
	public void setCheckUsers(String checkUsers) {
		this.checkUsers = checkUsers;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCrTime() {
		return crTime;
	}
	public void setCrTime(String crTime) {
		this.crTime = crTime;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCrUser() {
		return crUser;
	}
	public void setCrUser(String crUser) {
		this.crUser = crUser;
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
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
