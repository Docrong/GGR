package com.boco.eoms.km.table.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:模块信息表
 * </p>
 * <p>
 * Description:模块信息表
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 吕卫华
 * @moudle.getVersion() 1.0
 * 
 */
public class KmTableGeneralForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 主键
	 *
	 */
	private java.lang.String id;
   
	public void setId(java.lang.String id){
		this.id= id;       
	}
   
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *
	 * 表英文名
	 *
	 */
	private java.lang.String tableName;
   
	public void setTableName(java.lang.String tableName){
		this.tableName= tableName;       
	}
   
	public java.lang.String getTableName(){
		return this.tableName;
	}

	/**
	 *
	 * 表中文名（模型名称）
	 *
	 */
	private java.lang.String tableChname;
   
	public void setTableChname(java.lang.String tableChname){
		this.tableChname= tableChname;       
	}
   
	public java.lang.String getTableChname(){
		return this.tableChname;
	}

	/**
	 *
	 * 创建人
	 *
	 */
	private java.lang.String createUser;
   
	public void setCreateUser(java.lang.String createUser){
		this.createUser= createUser;       
	}
   
	public java.lang.String getCreateUser(){
		return this.createUser;
	}

	/**
	 *
	 * 创建部门
	 *
	 */
	private java.lang.String createDept;
   
	public void setCreateDept(java.lang.String createDept){
		this.createDept= createDept;       
	}
   
	public java.lang.String getCreateDept(){
		return this.createDept;
	}

	/**
	 *
	 * 创建时间
	 *
	 */
	private String createTime;
   
	public void setCreateTime(String createTime){
		this.createTime= createTime;       
	}
   
	public String getCreateTime(){
		return this.createTime;
	}

	/**
	 *
	 * 备注描述
	 *
	 */
	private java.lang.String remark;
   
	public void setRemark(java.lang.String remark){
		this.remark= remark;       
	}
   
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *
	 * 排序值
	 *
	 */
	private String orderBy;
   
	public void setOrderBy(String orderBy){
		this.orderBy= orderBy;       
	}
   
	public String getOrderBy(){
		return this.orderBy;
	}

	/**
	 *
	 * 是否开放（字典）
	 *
	 */
	private String isOpen;
   
	public void setIsOpen(String isOpen){
		this.isOpen= isOpen;       
	}
   
	public String getIsOpen(){
		return this.isOpen;
	}

	/**
	 *
	 * 是否可显（字典）
	 *
	 */
	private String isVisibl;
   
	public void setIsVisibl(String isVisibl){
		this.isVisibl= isVisibl;       
	}
   
	public String getIsVisibl(){
		return this.isVisibl;
	}

	/**
	 *
	 * 是否需要审核（字典）
	 *
	 */
	private String isAudit;
   
	public void setIsAudit(String isAudit){
		this.isAudit= isAudit;       
	}
   
	public String getIsAudit(){
		return this.isAudit;
	}

	/**
	 *
	 * 是否删除（字典）
	 *
	 */
	private String isDeleted;
   
	public void setIsDeleted(String isDeleted){
		this.isDeleted= isDeleted;       
	}
   
	public String getIsDeleted(){
		return this.isDeleted;
	}

	/**
	 *
	 * 知识分类（字典）
	 *
	 */
	private java.lang.String themeId;
   
	public void setThemeId(java.lang.String themeId){
		this.themeId= themeId;       
	}
   
	public java.lang.String getThemeId(){
		return this.themeId;
	}
	
	/**
	 *
	 * 是否已创建表（字典）
	 *
	 */
	private String isCreated;
 
	public void setIsCreated(String isCreated){
		this.isCreated= isCreated;       
	}
 
	public String getIsCreated(){
		return this.isCreated;
	}

}