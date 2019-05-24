package com.boco.eoms.km.exam.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:考试发布
 * </p>
 * <p>
 * Description:考试发布
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmExamPublic extends BaseObject {

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
	 * 试卷id
	 *
	 */
	private java.lang.String testId;
   
	public void setTestId(java.lang.String testId){
		this.testId= testId;       
	}
   
	public java.lang.String getTestId(){
		return this.testId;
	}

	/**
	 *
	 * 发布人
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
	 * 发布部门
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
	 * 发布时间
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
	 * 发布说明
	 *
	 */
	private java.lang.String description;
   
	public void setDescription(java.lang.String description){
		this.description= description;       
	}
   
	public java.lang.String getDescription(){
		return this.description;
	}

	public boolean equals(Object o) {
		if( o instanceof KmExamPublic ) {
			KmExamPublic kmExamPublic=(KmExamPublic)o;
			if (this.id != null || this.id.equals(kmExamPublic.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}