package com.boco.eoms.commons.system.area.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSystemArea.java.do"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_area"
 */
public class TawSystemArea extends BaseObject implements Serializable {


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
	
	private Integer id;
	private String areaname;
	private String leaf;
	private String areaid;
	private String parentAreaid;
	private Integer ordercode;
	private String remark;
	private String areacode;
	private String oldAreaName;
	private String capital;
	private String zonenum;
	
	public String getOldAreaName() {
		return oldAreaName;
	}


	public void setOldAreaName(String oldAreaName) {
		this.oldAreaName = oldAreaName;
	}


	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="地域ID" 
	 * @return
	 */
	public String getAreaid() {
		return areaid;
	}


	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="地域名称" 
	 * @return
	 */
	public String getAreaname() {
		return areaname;
	}


	public void setAreaname(String areaname) {
		this.areaname = areaname;
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
	 * @hibernate.property length="2"
	 * @eoms.show
	 * @eoms.cn name="叶节点标志" 
	 * @return
	 */
	public String getLeaf() {
		return leaf;
	}


	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="部门树的叶节点标志" 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="地域上级ID" 
	 * @return
	 */
	public String getParentAreaid() {
		return parentAreaid;
	}

	public void setParentAreaid(String parentAreaid) {
		this.parentAreaid = parentAreaid;
	}


	/**
	 * @hibernate.property
	 * @eoms.show
	 * @eoms.cn name="级别标志" 
	 * @return
	 */
	public Integer getOrdercode() {
		return ordercode;
	}


	public void setOrdercode(Integer ordercode) {
		this.ordercode = ordercode;
	}


	public String getAreacode() {
		return areacode;
	}


	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	//是否是省会或直辖市的标志位
	public String getCapital() {
		return capital;
	}


	public void setCapital(String capital) {
		this.capital = capital;
	}


	public String getZonenum() {
		return zonenum;
	}


	public void setZonenum(String zonenum) {
		this.zonenum = zonenum;
	}
}

