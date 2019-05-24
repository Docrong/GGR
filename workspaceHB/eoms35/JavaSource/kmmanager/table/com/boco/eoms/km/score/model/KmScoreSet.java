package com.boco.eoms.km.score.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:积分设定表
 * </p>
 * <p>
 * Description:积分设定表
 * </p>
 * <p>
 * Fri Aug 07 14:32:13 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
public class KmScoreSet extends BaseObject {

	/**
	 * 锟斤拷锟�
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
	 * 积分权重信息
	 *
	 */
	private java.lang.String scoreWeightId;
   
	public void setScoreWeightId(java.lang.String scoreWeightId){
		this.scoreWeightId= scoreWeightId;       
	}
   
	public java.lang.String getScoreWeightId(){
		return this.scoreWeightId;
	}

	/**
	 *
	 * 功能名称
	 *
	 */
	private java.lang.String powerName;
   
	public void setPowerName(java.lang.String powerName){
		this.powerName= powerName;       
	}
   
	public java.lang.String getPowerName(){
		return this.powerName;
	}

	/**
	 *
	 * 动作名称
	 *
	 */
	private java.lang.String actionName;
   
	public void setActionName(java.lang.String actionName){
		this.actionName= actionName;       
	}
   
	public java.lang.String getActionName(){
		return this.actionName;
	}
	
	/**
	 *
	 * 动作权重
	 *
	 */
	private java.lang.Integer actionWeight;
  
	public void setActionWeight(java.lang.Integer actionWeight){
		this.actionWeight= actionWeight;       
	}
  
	public java.lang.Integer getActionWeight(){
		return this.actionWeight;
	}

	/**
	 *
	 * 功能权重
	 *
	 */
	private java.lang.Integer powerWeight;
  
	public void setPowerWeight(java.lang.Integer powerWeight){
		this.powerWeight= powerWeight;       
	}
  
	public java.lang.Integer getPowerWeight(){
		return this.powerWeight;
	}

	/**
	 *
	 * 人员等级
	 *
	 */
	private java.lang.Integer userLevel;
   
	public void setUserLevel(java.lang.Integer userLevel){
		this.userLevel= userLevel;       
	}
   
	public java.lang.Integer getUserLevel(){
		return this.userLevel;
	}

	/**
	 *
	 * 积分
	 *
	 */
	private java.lang.Integer score;
   
	public void setScore(java.lang.Integer score){
		this.score= score;       
	}
   
	public java.lang.Integer getScore(){
		return this.score;
	}

	/**
	 *
	 * 是否计入总分
	 *
	 */
	private java.lang.Integer isCount;
   
	public void setIsCount(java.lang.Integer isCount){
		this.isCount= isCount;       
	}
   
	public java.lang.Integer getIsCount(){
		return this.isCount;
	}

	/**
	 *
	 * 是否删除
	 *
	 */
	private java.lang.Integer isDeleted;
   
	public void setIsDeleted(java.lang.Integer isDeleted){
		this.isDeleted= isDeleted;       
	}
   
	public java.lang.Integer getIsDeleted(){
		return this.isDeleted;
	}

	/**
	 *
	 * 备注
	 *
	 */
	private java.lang.String remark;
   
	public void setRemark(java.lang.String remark){
		this.remark= remark;       
	}
   
	public java.lang.String getRemark(){
		return this.remark;
	}


	public boolean equals(Object o) {
		if( o instanceof KmScoreSet ) {
			KmScoreSet kmScoreSet=(KmScoreSet)o;
			if (this.id != null || this.id.equals(kmScoreSet.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}