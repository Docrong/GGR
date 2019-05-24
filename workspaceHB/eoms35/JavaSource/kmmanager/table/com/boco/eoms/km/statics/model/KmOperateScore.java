package com.boco.eoms.km.statics.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:操作积分定义表
 * </p>
 * <p>
 * Description:操作积分定义表
 * </p>
 * <p>
 * Thu Mar 26 15:57:40 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class KmOperateScore extends BaseObject {

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
	 * 操作名称
	 *
	 */
	private java.lang.String operateName;
   
	public void setOperateName(java.lang.String operateName){
		this.operateName= operateName;       
	}
   
	public java.lang.String getOperateName(){
		return this.operateName;
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
	 * 操作Id
	 *
	 */
	private java.lang.Integer operateId;
   
	public void setOperateId(java.lang.Integer operateId){
		this.operateId= operateId;       
	}
   
	public java.lang.Integer getOperateId(){
		return this.operateId;
	}

	public boolean equals(Object o) {
		if( o instanceof KmOperateScore ) {
			KmOperateScore kmOperateScore=(KmOperateScore)o;
			if (this.id != null || this.id.equals(kmOperateScore.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}