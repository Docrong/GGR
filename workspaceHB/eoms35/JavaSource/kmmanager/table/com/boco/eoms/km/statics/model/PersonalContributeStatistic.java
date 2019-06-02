package com.boco.eoms.km.statics.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识编写人本期（周、月、季、年）知识贡献情况统计表
 * </p>
 * <p>
 * Description:知识编写人本期（周、月、季、年）知识贡献情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:14 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class PersonalContributeStatistic extends BaseObject {

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
	 * 编写知识数
	 *
	 */
	private java.lang.Integer addCount;
   
	public void setAddCount(java.lang.Integer addCount){
		this.addCount= addCount;       
	}
   
	public java.lang.Integer getAddCount(){
		return this.addCount;
	}

	/**
	 *
	 * 修改知识数
	 *
	 */
	private java.lang.Integer modifyCount;
   
	public void setModifyCount(java.lang.Integer modifyCount){
		this.modifyCount= modifyCount;       
	}
   
	public java.lang.Integer getModifyCount(){
		return this.modifyCount;
	}

	/**
	 *
	 * 失效知识数
	 *
	 */
	private java.lang.Integer overCount;
   
	public void setOverCount(java.lang.Integer overCount){
		this.overCount= overCount;       
	}
   
	public java.lang.Integer getOverCount(){
		return this.overCount;
	}

	/**
	 *
	 * 删除知识数
	 *
	 */
	private java.lang.Integer deleteCount;
   
	public void setDeleteCount(java.lang.Integer deleteCount){
		this.deleteCount= deleteCount;       
	}
   
	public java.lang.Integer getDeleteCount(){
		return this.deleteCount;
	}

	/**
	 *
	 * 上传知识数
	 *
	 */
	private java.lang.Integer upCount;
   
	public void setUpCount(java.lang.Integer upCount){
		this.upCount= upCount;       
	}
   
	public java.lang.Integer getUpCount(){
		return this.upCount;
	}

	public boolean equals(Object o) {
		if( o instanceof PersonalContributeStatistic ) {
			PersonalContributeStatistic personalContributeStatistic=(PersonalContributeStatistic)o;
			if (this.id != null || this.id.equals(personalContributeStatistic.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}