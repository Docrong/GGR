package com.boco.eoms.commons.system.role.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSystemUser.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_role"
 */
public class TawSystemRole extends BaseObject {

	private long roleId;

	private String roleName;

	private long roleTypeId;

	private String deptId;

	private Integer titleId;

	private Integer levelId;

	private String notes;

	private String singleId;

	private Integer deleted;

	private Integer workflowFlag;

	private long parentId;

	private String structureFlag;

	private Integer limitCount;

	private Integer leaf;
	
	private Long postId;

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public Long getPostId() {
		return postId;
	}
	/**
	 * @param postId The postId to set.
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
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
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
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
	 * @hibernate.property length="100" not-null="false" unique="true"
	 */
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @hibernate.id column="role_id" generator-class="uuid.hex"
	 *               unsaved-value="null"
	 */
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public long getRoleTypeId() {
		return roleTypeId;
	}

	public void setRoleTypeId(long roleTypeId) {
		this.roleTypeId = roleTypeId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getSingleId() {
		return singleId;
	}

	public void setSingleId(String singleId) {
		this.singleId = singleId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public String getStructureFlag() {
		return structureFlag;
	}

	public void setStructureFlag(String structureFlag) {
		this.structureFlag = structureFlag;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="true"
	 */
	public Integer getWorkflowFlag() {
		return workflowFlag;
	}

	public void setWorkflowFlag(Integer workflowFlag) {
		this.workflowFlag = workflowFlag;
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
