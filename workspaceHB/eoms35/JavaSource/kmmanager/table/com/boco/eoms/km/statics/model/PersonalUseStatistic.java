package com.boco.eoms.km.statics.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识使用人本期（周、月、季、年）使用知识情况统计表
 * </p>
 * <p>
 * Description:知识使用人本期（周、月、季、年）使用知识情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class PersonalUseStatistic extends BaseObject {

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
	 * 引用知识数
	 *
	 */
	private java.lang.Integer useCount;
   
	public void setUseCount(java.lang.Integer useCount){
		this.useCount= useCount;       
	}
   
	public java.lang.Integer getUseCount(){
		return this.useCount;
	}

	/**
	 *
	 * 提出知识修改意见数
	 *
	 */
	private java.lang.Integer adviceCount;
   
	public void setAdviceCount(java.lang.Integer adviceCount){
		this.adviceCount= adviceCount;       
	}
   
	public java.lang.Integer getAdviceCount(){
		return this.adviceCount;
	}

	/**
	 *
	 * 评价知识数
	 *
	 */
	private java.lang.Integer opinionCount;
   
	public void setOpinionCount(java.lang.Integer opinionCount){
		this.opinionCount= opinionCount;       
	}
   
	public java.lang.Integer getOpinionCount(){
		return this.opinionCount;
	}

	/**
	 * 
	 * 下载文件数
	 * 
	 */
	private java.lang.Integer downCount;
	
	public java.lang.Integer getDownCount() {
		return downCount;
	}

	public void setDownCount(java.lang.Integer downCount) {
		this.downCount = downCount;
	}
	
	/**
	 * 
	 * 阅读次数
	 * 
	 */
	private java.lang.Integer readCount;

	public java.lang.Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(java.lang.Integer readCount) {
		this.readCount = readCount;
	}

	public boolean equals(Object o) {
		if( o instanceof PersonalUseStatistic ) {
			PersonalUseStatistic personalUseStatistic=(PersonalUseStatistic)o;
			if (this.id != null || this.id.equals(personalUseStatistic.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}