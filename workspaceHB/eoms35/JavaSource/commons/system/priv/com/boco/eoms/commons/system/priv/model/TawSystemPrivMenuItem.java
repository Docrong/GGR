/**
 * 
 */
package com.boco.eoms.commons.system.priv.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * <p>
 * <a href="TawSystemPrivMenuItem.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_priv_menuitem"
 */
public class TawSystemPrivMenuItem extends BaseObject implements Serializable {

	private String id;

	private String menuid;

	private String code;

	private String parentcode;

	private String isApp;

	private String isLeaf;

	private String isHide;

	private String remark;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */

	public boolean equals(Object o) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#hashCode()
	 */

	public int hashCode() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#toString()
	 */

	public String toString() {
		return null;
	}

	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="100" not-null="true"
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the itemId to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="100" not-null="true"
	 */
	public String getParentcode() {
		return parentcode;
	}

	/**
	 * @param parentcode
	 *            the parentItemId to set
	 */
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	/**
	 * @hibernate.property length="10" not-null="true"
	 */
	public String getIsApp() {
		return isApp;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setIsApp(String isApp) {
		this.isApp = isApp;
	}

	/**
	 * @hibernate.property length="10" not-null="true"
	 */
	public String getIsLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf
	 *            the isLeaf to set
	 */
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @hibernate.property length="10" not-null="true"
	 */
	public String getIsHide() {
		return isHide;
	}

	/**
	 * @param isHide
	 *            the isHide to set
	 */
	public void setIsHide(String isHide) {
		this.isHide = isHide;
	}

	/**
	 * @hibernate.property length="200"
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
