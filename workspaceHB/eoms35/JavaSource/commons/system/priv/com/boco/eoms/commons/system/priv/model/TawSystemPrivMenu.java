/**
 * 
 */
package com.boco.eoms.commons.system.priv.model;

// java standard library
import java.io.Serializable;

// eoms library
import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * <p>
 * <a href="TawSystemPrivMenu.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_priv_menu"
 */
public class TawSystemPrivMenu extends BaseObject implements Serializable {

	private String privid;

	private String Name;

	private String ownerId;

	private String remark;
	
	private String nature; //平台性质 add by gongyufeng 新增wap和eoms对菜单的区别

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
	 * @struts.validator type="required"
	 * @hibernate.property length="100" not-null="true" unique="true"
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="200" not-null="true" unique="false"
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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

	/**
	 * @return the privid
	 */
	public String getPrivid() {
		return privid;
	}

	/**
	 * @param privid
	 *            the privid to set
	 */
	public void setPrivid(String privid) {
		this.privid = privid;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

}
