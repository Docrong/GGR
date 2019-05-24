/*
 * Created on 2007-11-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.model;

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
 * @hibernate.class table="taw_system_workflow"
 */
public class TawSystemWorkflow extends BaseObject{
	private Long id;
	//流程名称
	private String name; 
	//流程代码，具体代码格式详见规范文档
	private String flowId;
	//子流程代码，具体代码格式详见规范文档
	private String sheetid;
	//子流程对应的mainServiceBeanId
	private String mainServiceBeanId;
	//备注
	private String remark;
	//建单者角色id
	private String roleId;

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getFlowId() {
		return flowId;
	}
	/**
	 * @param flowId The flowId to set.
	 */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="255" not-null="false" unique="true"
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="255" not-null="false" unique="true"
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getSheetid() {
		return sheetid;
	}
	/**
	 * @param sheetid The sheetid to set.
	 */
	public void setSheetid(String sheetid) {
		this.sheetid = sheetid;
	}
	
	
	/**
	 * @return Returns the mainServiceBeanId.
	 */
	public String getMainServiceBeanId() {
		return mainServiceBeanId;
	}
	/**
	 * @param mainServiceBeanId The mainServiceBeanId to set.
	 */
	public void setMainServiceBeanId(String mainServiceBeanId) {
		this.mainServiceBeanId = mainServiceBeanId;
	}
	/* (non-Javadoc)
	 * @see com.boco.eoms.base.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.boco.eoms.base.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
