package com.boco.eoms.workbench.contact.model;

import java.io.Serializable;
import com.boco.eoms.base.model.BaseObject;

/**
 * 
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawWorkbenchDeptContact.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_workbench_deptcontact"
 */

public class TawWorkbenchDeptContact extends BaseObject implements Serializable  {
	/**
	 * @author 孙圣泰
	 * @deprecated
	 * @date 2008-06-25 21:54
	 * 
	 */
	private String id; // 主键

	private String deleted; // 删除标志（0表示实际存在的，1表示被删除的）

	private String userId; // 联系人信息所属用户ID

	private String contactName;// 联系人姓名

	private String deptId; // 联系人所属部门ID

	private String deptName; // 联系人所属部门名称（外部人员使用分组名称）

	private String position; // 联系人职务

	private String tele; // 联系人电话号码

	private String address; // 联系人地址

	private String email; // 联系人电子邮件地址

	private String groupId; // 联系人分组ID

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="联系人地址"
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="联系人姓名"
	 * @return
	 */
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @hibernate.property length="8"
	 * @eoms.show
	 * @eoms.cn name="删除标志"
	 * @return
	 */
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * @hibernate.property length="20"
	 * @eoms.show
	 * @eoms.cn name="联系人所属部门"
	 * @return
	 */
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="联系人所属部门名称"
	 * @return
	 */
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="联系人电子邮件地址"
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @hibernate.property length="8"
	 * @eoms.show
	 * @eoms.cn name="联系人分组ID"
	 * @return
	 */
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	 * @eoms.show
	 * @eoms.cn name="联系人职务"
	 * @return
	 */
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="联系人电话号码"
	 * @return
	 */
	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="联系人信息所属用户ID"
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
