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
 * @hibernate.class table="taw_system_sub_role"
 */
public class TawSystemSubRole extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3714042146016951106L;

	private String id;

	private String subRoleName;

	private long roleId =0;

	private long parentId;

	private Integer limitCount;

	private Integer leaf;

	private String area;

	private String deptId;

	private String type1;

	private String type2;

	private String type3;

	private String type4;

	private String logo;

	private String deleted = "0";
	
	/**
	 * 角色导入版本控制
	 */
	private String version;
	
	private String county;
	

	/**
	 * @return Returns the deleted.
	 */
	public String getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            The deleted to set.
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return Returns the logo.
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo
	 *            The logo to set.
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getSubRoleName() {
		return subRoleName;
	}

	public void setSubRoleName(String subRoleName) {
		this.subRoleName = subRoleName;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
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

	/**
	 * @return Returns the type4.
	 */
	public String getType4() {
		return type4;
	}

	/**
	 * @param type4
	 *            The type4 to set.
	 */
	public void setType4(String type4) {
		this.type4 = type4;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
}
