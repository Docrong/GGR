package com.boco.eoms.km.log.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识操作历史记录表
 * </p>
 * <p>
 * Description:知识操作历史记录表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 * 
 */
public class KmOperateLogForm extends BaseForm implements java.io.Serializable {

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
	 * 知识分类表（外键）
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
	 * 表主键（外键）
	 *
	 */
	private java.lang.String tableId;
   
	public void setTableId(java.lang.String tableId){
		this.tableId= tableId;       
	}
   
	public java.lang.String getTableId(){
		return this.tableId;
	}

	/**
	 *
	 * 知识ID（外键）
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
	 * 操作人
	 *
	 */
	private java.lang.String operateUser;
   
	public void setOperateUser(java.lang.String operateUser){
		this.operateUser= operateUser;       
	}
   
	public java.lang.String getOperateUser(){
		return this.operateUser;
	}

	/**
	 *
	 * 操作部门
	 *
	 */
	private java.lang.String operateDept;
   
	public void setOperateDept(java.lang.String operateDept){
		this.operateDept= operateDept;       
	}
   
	public java.lang.String getOperateDept(){
		return this.operateDept;
	}

	/**
	 *
	 * 操作时间
	 *
	 */
	private java.util.Date operateTime;
   
	public void setOperateTime(java.util.Date operateTime){
		this.operateTime= operateTime;       
	}
   
	public java.util.Date getOperateTime(){
		return this.operateTime;
	}

	/**
	 *
	 * 操作积分
	 *
	 */
	private java.lang.Long score;
  
	public void setScore(java.lang.Long score){
		this.score= score;       
	}
  
	public java.lang.Long getScore(){
		return this.score;
	}

	/**
	 *
	 * 目标人
	 *
	 */
	private java.lang.String toUser;
   
	public void setToUser(java.lang.String toUser){
		this.toUser= toUser;       
	}
   
	public java.lang.String getToUser(){
		return this.toUser;
	}

	/**
	 * 操作Id
	 * 
	 */
	private java.lang.String operateId;

	public java.lang.String getOperateId() {
		return operateId;
	}

	public void setOperateId(java.lang.String operateId) {
		this.operateId = operateId;
	}
	
	
}