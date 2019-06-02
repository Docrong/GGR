package com.boco.eoms.km.statics.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识审核人本期（周、月、季、年）XX专业知识审核情况统计表
 * </p>
 * <p>
 * Description:知识审核人本期（周、月、季、年）XX专业知识审核情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 * 
 */
public class PersonalAuditStatisticForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * 用户姓名
	 *
	 */
	private java.lang.String userName;
   
	public void setUserName(java.lang.String userName){
		this.userName= userName;       
	}
   
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *
	 * 用户部门
	 *
	 */
	private java.lang.String userDept;
   
	public void setUserDept(java.lang.String userDept){
		this.userDept= userDept;       
	}
   
	public java.lang.String getUserDept(){
		return this.userDept;
	}

	/**
	 *
	 * 审核知识数
	 *
	 */
	private java.lang.Long auditCount;
   
	public void setAuditCount(java.lang.Long auditCount){
		this.auditCount= auditCount;       
	}
   
	public java.lang.Long getAuditCount(){
		return this.auditCount;
	}

	/**
	 *
	 * 知识审核通过次数
	 *
	 */
	private java.lang.Long auditOkCount;
   
	public void setAuditOkCount(java.lang.Long auditOkCount){
		this.auditOkCount= auditOkCount;       
	}
   
	public java.lang.Long getAuditOkCount(){
		return this.auditOkCount;
	}

	/**
	 *
	 * 知识审核驳回次数
	 *
	 */
	private java.lang.Long auditBackCount;
   
	public void setAuditBackCount(java.lang.Long auditBackCount){
		this.auditBackCount= auditBackCount;       
	}
   
	public java.lang.Long getAuditBackCount(){
		return this.auditBackCount;
	}

}