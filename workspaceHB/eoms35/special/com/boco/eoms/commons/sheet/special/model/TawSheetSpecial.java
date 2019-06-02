package com.boco.eoms.commons.sheet.special.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSheetSpecial.java.do"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_common_sheet_special"
 */
public class TawSheetSpecial extends BaseObject implements Serializable {

	public boolean equals(Object o) {
	
		return false;
	}


	private Integer id;
	private String speid;
	private String specialtype;
	private String specialname;
	private String parspeid;
	private String ordercode;
	private String refuserid;
	private String leaf;
	private String style;
	private String remark;

	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="备注" 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @hibernate.property length="2"
	 * @eoms.show
	 * @eoms.cn name="特殊排序标志" 
	 * @return
	 */
	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="父专业类型ID" 
	 * @return
	 */
	public String getParspeid() {
		return parspeid;
	}

	public void setParspeid(String parspeid) {
		this.parspeid = parspeid;
	}

	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="用户ID" 
	 * @return
	 */
	public String getRefuserid() {
		return refuserid;
	}

	public void setRefuserid(String refuserid) {
		this.refuserid = refuserid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="专业名称" 
	 * @return
	 */
	public String getSpecialname() {
		return specialname;
	}

	public void setSpecialname(String specialname) {
		this.specialname = specialname;
	}

	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="专业类型" 
	 * @return
	 */
	public String getSpecialtype() {
		return specialtype;
	}

	public void setSpecialtype(String specialtype) {
		this.specialtype = specialtype;
	}

	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="专业ID" 
	 * @return
	 */
	public String getSpeid() {
		return speid;
	}

	public void setSpeid(String speid) {
		this.speid = speid;
	}

	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="机型" 
	 * @return
	 */
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int hashCode() {
		
		return 0;
	}

	public String toString() {
		
		return null;
	}

	
	
}
