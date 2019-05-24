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
 * @hibernate.class table="taw_system_rolerefworkflow"
 */
public class TawSystemRoleRefWorkflow extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9113117854147421223L;

	private String id;

	private String startSheetName;

	private String sheetName;

	private String roleId;

	private String type;

	/**
	 * @return Returns the sheetName.
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * @param sheetName
	 *            The sheetName to set.
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * @return Returns the startSheetName.
	 */
	public String getStartSheetName() {
		return startSheetName;
	}

	/**
	 * @param startSheetName
	 *            The startSheetName to set.
	 */
	public void setStartSheetName(String startSheetName) {
		this.startSheetName = startSheetName;
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
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
