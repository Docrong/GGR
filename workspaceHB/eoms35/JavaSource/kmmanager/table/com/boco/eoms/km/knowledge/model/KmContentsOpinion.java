package com.boco.eoms.km.knowledge.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 11:36:49 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
public class KmContentsOpinion extends BaseObject {

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
	 * 模型定义(外键)
	 */
	private String tableId;
	
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 *
	 * 知识分类表(外键)
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
	 * 评价的知识ID
	 *
	 */
	private java.lang.String contentId;
   
	public void setContentId(java.lang.String contentId){
		this.contentId= contentId;       
	}
   
	public java.lang.String getContentId(){
		return this.contentId;
	}

	/**
	 *
	 * 评价人
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
	 * 评价人部门
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
	 * 评价时间
	 *
	 */
	private java.util.Date createTime;
   
	public void setCreateTime(java.util.Date createTime){
		this.createTime= createTime;       
	}
   
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *
	 * 评价内容
	 *
	 */
	private java.lang.String opinionContent;
   
	public void setOpinionContent(java.lang.String opinionContent){
		this.opinionContent= opinionContent;       
	}
   
	public java.lang.String getOpinionContent(){
		return this.opinionContent;
	}

	/**
	 *
	 * 评分
	 *
	 */
	private java.lang.String opinionGrade;
   
	public void setOpinionGrade(java.lang.String opinionGrade){
		this.opinionGrade= opinionGrade;       
	}
   
	public java.lang.String getOpinionGrade(){
		return this.opinionGrade;
	}

	/**
	 *
	 * 是否提出修改
	 *
	 */
	private java.lang.String isEdit;
   
	public void setIsEdit(java.lang.String isEdit){
		this.isEdit= isEdit;       
	}
   
	public java.lang.String getIsEdit(){
		return this.isEdit;
	}

	/**
	 *
	 * 修改是否被采纳
	 *
	 */
	private java.lang.String isRefedit;
   
	public void setIsRefedit(java.lang.String isRefedit){
		this.isRefedit= isRefedit;       
	}
   
	public java.lang.String getIsRefedit(){
		return this.isRefedit;
	}

	/**
	 *
	 * 是否删除
	 *
	 */
	private java.lang.String isDeleted;
   
	public void setIsDeleted(java.lang.String isDeleted){
		this.isDeleted= isDeleted;       
	}
   
	public java.lang.String getIsDeleted(){
		return this.isDeleted;
	}

	public boolean equals(Object o) {
		if( o instanceof KmContentsOpinion ) {
			KmContentsOpinion kmContentsOpinion=(KmContentsOpinion)o;
			if (this.id != null || this.id.equals(kmContentsOpinion.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}