package com.boco.eoms.workbench.infopub.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:审核、阅读主题权限所属组织（人员、角色、部门）
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 4:11:46 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadPermimissionOrg extends BaseObject {

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 组织id
	 */
	private String orgId;

	/**
	 * 组织类型，如角色，部门，用户
	 */
	private String orgType;

	/**
	 * 组织名称
	 */
	private String name;

	/**
	 * 与信息关联的id
	 */

	private String threadId;

	/**
	 * 删除标记
	 */
	private String isDel;
	
	//2009-04-07 是否包括子部门  1包括，0或null不包括
	private String isIncludeSubDept;

	public String getIsIncludeSubDept() {
		return isIncludeSubDept;
	}

	public void setIsIncludeSubDept(String isIncludeSubDept) {
		this.isIncludeSubDept = isIncludeSubDept;
	}

	/**
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
	 * @return the orgId *
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType
	 *            the orgType to set
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * @return the threadId
	 */
	public String getThreadId() {
		return threadId;
	}

	/**
	 * @param threadId
	 *            the threadId to set
	 */
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	
	
	/**
	 * 构造方法
	 * 
	 * @param orgId
	 *            组织id,部门，用户，角色
	 * @param orgType
	 *            组类型 部门，用户，角色
	 * @param name
	 *            组织名称
	 * 
	 */
	public ThreadPermimissionOrg(String orgId, String orgType, String name) {
		super();
		this.orgId = orgId;
		this.orgType = orgType;
		this.name = name;
	}

	public ThreadPermimissionOrg() {
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isDel
	 */
	public String getIsDel() {
		return isDel;
	}

	/**
	 * @param isDel
	 *            the isDel to set
	 */
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof ThreadPermimissionOrg) {
			ThreadPermimissionOrg threadPermimissionOrg = (ThreadPermimissionOrg) o;
			if (this.id != null
					|| this.id.equals(threadPermimissionOrg.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
