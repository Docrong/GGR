package com.boco.eoms.commons.system.role.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:角色导入
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Sep 10, 2008 9:50:08 AM
 * </p>
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_role_import"
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawSystemRoleImport extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4754438625415457038L;

	// id VARCHAR(32) NOT NULL PRIMARY KEY,
	// role_id varchar(50),
	// sub_role_id varchar(50),
	// version varchar(50),
	// version_at DATETIME YEAR TO SECOND,
	// accessories_id varchar(50),
	// memo varchar(255)

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof TawSystemRoleImport) {
			TawSystemRoleImport tawSystemRoleImport = (TawSystemRoleImport) o;
			if (this.id != null || this.id.equals(tawSystemRoleImport.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 角色id
	 */
	private String roleId;

	/**
	 * 子角色id
	 */
	private String subRoleId;

	/**
	 * 版本
	 */
	private String version;

	/**
	 * 本版本上传日期
	 */
	private Date versionAt;

	/**
	 * 附件id
	 */
	private String accessoriesId;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * @return the accessoriesId
	 */
	public String getAccessoriesId() {
		return accessoriesId;
	}

	/**
	 * @param accessoriesId
	 *            the accessoriesId to set
	 */
	public void setAccessoriesId(String accessoriesId) {
		this.accessoriesId = accessoriesId;
	}

	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 * @return the id
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

	/**
	 * *
	 * 
	 * @hibernate.property length="255"
	 * 
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @hibernate.property length="50" not-null="true"
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 
	 * 
	 * @hibernate.property length="50" not-null="true"
	 * @return the subRoleId
	 */
	public String getSubRoleId() {
		return subRoleId;
	}

	/**
	 * @param subRoleId
	 *            the subRoleId to set
	 */
	public void setSubRoleId(String subRoleId) {
		this.subRoleId = subRoleId;
	}

	/**
	 * @hibernate.property length="50" not-null="true" unique="true"
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the versionAt
	 */
	public Date getVersionAt() {
		return versionAt;
	}

	/**
	 * @param versionAt
	 *            the versionAt to set
	 */
	public void setVersionAt(Date versionAt) {
		this.versionAt = versionAt;
	}

}
