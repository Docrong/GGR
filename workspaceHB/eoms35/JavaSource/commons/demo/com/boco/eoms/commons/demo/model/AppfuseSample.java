package com.boco.eoms.commons.demo.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_prefix_table"
 */
public class AppfuseSample extends BaseObject {
	private String id;

	private String remark;

	/**
	 * @hibernate.id column="id" generator-class="uuid" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true" unique="true"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
