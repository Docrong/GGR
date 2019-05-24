package com.boco.eoms.km.log.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @author ljt
 * @version 0.1
 * 
 */
public class KmOperateLog extends BaseObject {

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
	private java.lang.Integer score;
  
	public void setScore(java.lang.Integer score){
		this.score= score;       
	}
  
	public java.lang.Integer getScore(){
		if(this.score == null){
			this.score = new java.lang.Integer(0);
		}		
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
	 * 
	 * 操作Id
	 * 
	 */
	private java.lang.Integer operateId;

	public java.lang.Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(java.lang.Integer operateId) {
		this.operateId = operateId;
	}

	public boolean equals(Object o) {
		if( o instanceof KmOperateLog ) {
			KmOperateLog kmOperateLog=(KmOperateLog)o;
			if (this.id != null || this.id.equals(kmOperateLog.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}