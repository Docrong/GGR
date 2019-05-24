package com.boco.eoms.commons.system.cptroom.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSystemCptroom.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_cptroom"
 */
public class TawSystemCptroom extends BaseObject {
	private Integer id;
	
	private String roomname;
	
	private String manager;
	
	private String tempmanager;
	
	private String endtime;
	
	private String deptid;
	
	private String phone;
	
	private String mobile;
	
	private String fax;
	
	private String address;
	
	private Integer deleted;
	
	private String notes;
	
	private String parentid;
	
	private String leaf;
	
	
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
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="地址"
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @eoms.cn name="删除标志"
	 * @return
	 */
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * @hibernate.property length="20"
	 * @eoms.show
	 * @eoms.cn name="所属部门编号"
	 * @return
	 */
	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="临时负责终止时间"
	 * @return
	 */
	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="联系传真"
	 * @return
	 */
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
	 * @hibernate.id column="id" generator-class="increment" unsaved-value="null"
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @hibernate.property length="30"
	 * @eoms.show
	 * @eoms.cn name="机房负责人"
	 * @return
	 */
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="联系手机"
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @eoms.cn name="注释"
	 * @return
	 */
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="联系电话"
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="各机房名称"
	 * @return
	 */
	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	/**
	 * @hibernate.property length="30"
	 * @eoms.show
	 * @eoms.cn name="各机房名称"
	 * @return
	 */
	public String getTempmanager() {
		return tempmanager;
	}

	public void setTempmanager(String tempmanager) {
		this.tempmanager = tempmanager;
	}
	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="叶子节点"
	 * @return
	 */
	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="父机房ID"
	 * @return
	 */
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}
