package com.boco.eoms.commons.system.role.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSystemUser.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_roletype"
 */
public class TawSystemRoleType extends BaseObject{
	private Long roletype_id;
	private String roletype_name;
	private String notes;
	private Integer deleted;
	private String system_name;
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="255" not-null="false" unique="true"
	 */
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * @hibernate.id column="roletype_id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public Long getRoletype_id() {
		return roletype_id;
	}
	public void setRoletype_id(Long roletype_id) {
		this.roletype_id = roletype_id;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getRoletype_name() {
		return roletype_name;
	}
	public void setRoletype_name(String roletype_name) {
		this.roletype_name = roletype_name;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getSystem_name() {
		return system_name;
	}
	public void setSystem_name(String system_name) {
		this.system_name = system_name;
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
