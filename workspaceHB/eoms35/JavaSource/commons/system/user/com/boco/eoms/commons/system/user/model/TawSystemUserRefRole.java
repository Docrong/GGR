package com.boco.eoms.commons.system.user.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSystemUserRefRole.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_userrefrole"
 */
public class TawSystemUserRefRole extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	/**
	 * 角色导入版本控制
	 */
	private String version;

	private String id;

	private String userid;

	private String roleid;

	private String subRoleid;

	private Integer currentsheetcount;

	private String status;

	private String remark;

	private String groupType;

	private String roleType;

	/**
	 * @return Returns the roleType.
	 */
	public String getRoleType() {
		return roleType;
	}

	/**
	 * @param roleType
	 *            The roleType to set.
	 */
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	/**
	 * @return Returns the groupType.
	 */
	public String getGroupType() {
		return groupType;
	}

	/**
	 * @param groupType
	 *            The groupType to set.
	 */
	public void setGroupType(String groupType) {
		this.groupType = groupType;
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
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="角色ID"
	 * @return
	 */
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="用户ID"
	 * @return
	 */
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="操作者用户ID"
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.currentsheetcount
	 */
	public Integer getCurrentsheetcount() {
		return currentsheetcount;
	}

	public void setCurrentsheetcount(Integer currentsheetcount) {
		this.currentsheetcount = currentsheetcount;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="状态,6闲,7忙"
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubRoleid() {
		return subRoleid;
	}

	public void setSubRoleid(String subRoleid) {
		this.subRoleid = subRoleid;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

}
