package com.boco.eoms.message.model;

import com.boco.eoms.base.model.BaseObject;
/**
* <p>
 * <a href="SmsMobilesTemplate.java.html"><i>View Source</i></a>
 * 
 * @author 
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="sms_mobiles_template"
 */
public class SmsMobilesTemplate extends BaseObject {
	private String id;
	
	private String name;
	
	private String mobiles;
	
	private String users;
	
	private String remark;
	
	private String deleted;
	
	private String leaf="1";
	
	
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="100" not-null="false" unique="false"
	 * @eoms.show
	 * @eoms.cn name="号码组名称"
	 * @return
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="3000" not-null="false" unique="false"
	 * @eoms.show
	 * @eoms.cn name="号码组备注"
	 * @return
	 */
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="3000" not-null="false" unique="false"
	 * @eoms.show
	 * @eoms.cn name="号码组用户"
	 * @return
	 */
	
	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
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
	 * @hibernate.property length="3000" not-null="true" unique="false"
	 * @eoms.show
	 * @eoms.cn name="号码组号码"
	 * @return
	 */
	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="5" not-null="false" unique="false"
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
	

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
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
