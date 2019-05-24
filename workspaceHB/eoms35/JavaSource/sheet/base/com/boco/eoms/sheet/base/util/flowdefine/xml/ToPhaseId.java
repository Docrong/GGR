/*
 * Created on 2007-11-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util.flowdefine.xml;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ToPhaseId extends PhaseId {


	private String role;

	private String condition;
	
	/**
	 * 步聚操作是否显示
	 * true : 显示
	 * false : 显示
	 */
	private String stepdisplay;
	
	/**
	 * 动态流程图是否显示
	 * true : 显示
	 * false : 显示
	 */
	private String workflowdisplay;
	
	
	/**
	 * 操作类型 
	 */
	private String operatetype;
	
	/**
	 * 在没有确认受理时的驳回操作
	 * true : 在页面提供要驳回到环节的phaseId隐藏域
	 * false : 在页面不提供要驳回到环节的phaseId隐藏域
	 */
	private String isrejecttask;
	
	/**
	 * 环节中（包括新建页面）,派单树的roleid
	 * 空值（空字符串）：没有
	 * 有值roleId
	 */
	private String roleid;
	
	
	/***
	 * 主要应用每个环节中的输入页面时operateType值的判断
	 * 公共操作类型(目前11：分派回复和55：会审回复)
	 * 空值（没有内容）：该步骤没有公共操作类型
	 * 11： 分派回复
	 * 55： 会审回复
	 * 11,55 :分派和会审回复
	 */
	private String commonoperatetype;
	
	
	
	
	

	public String getCommonoperatetype() {
		return commonoperatetype;
	}

	public void setCommonoperatetype(String commonoperatetype) {
		this.commonoperatetype = commonoperatetype;
	}
	
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getIsrejecttask() {
		return isrejecttask;
	}

	public void setIsrejecttask(String isrejecttask) {
		this.isrejecttask = isrejecttask;
	}

	public String getOperatetype() {
		return operatetype;
	}

	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype;
	}

	public String getStepdisplay() {
		return stepdisplay;
	}

	public void setStepdisplay(String stepdisplay) {
		this.stepdisplay = stepdisplay;
	}

	public String getWorkflowdisplay() {
		return workflowdisplay;
	}

	public void setWorkflowdisplay(String workflowdisplay) {
		this.workflowdisplay = workflowdisplay;
	}

	/**
	 * @return Returns the condition.
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            The condition to set.
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}


	/**
	 * @return Returns the role.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            The role to set.
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
