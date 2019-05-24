package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:资料
 * </p>
 * <p>
 * Description:资料
 * </p>
 * <p>
 * Tue Apr 07 10:05:44 CST 2009
 * </p>
 * 
 * @author lixiaoming
 * @version 3.5
 * 
 */
public class PapersPart extends BaseObject {

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
	 * 标题
	 *
	 */
	private java.lang.String title;
   
	public void setTitle(java.lang.String title){
		this.title= title;       
	}
   
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *
	 * 添加时间
	 *
	 */
	private java.lang.String insertTime;
   
	public void setInsertTime(java.lang.String insertTime){
		this.insertTime= insertTime;       
	}
   
	public java.lang.String getInsertTime(){
		return this.insertTime;
	}

	/**
	 *
	 * 添加人
	 *
	 */
	private java.lang.String insertUserId;
   
	public void setInsertUserId(java.lang.String insertUserId){
		this.insertUserId= insertUserId;       
	}
   
	public java.lang.String getInsertUserId(){
		return this.insertUserId;
	}

	/**
	 *
	 * 部门ID
	 *
	 */
	private java.lang.String deptId;
   
	public void setDeptId(java.lang.String deptId){
		this.deptId= deptId;       
	}
   
	public java.lang.String getDeptId(){
		return this.deptId;
	}
	/**
	 *
	 * 子表ID
	 *
	 */
	private java.lang.String papersId;
  
	public java.lang.String getPapersId() {
		return papersId;
	}

	public void setPapersId(java.lang.String papersId) {
		this.papersId = papersId;
	}

	/**
	 *
	 * 删除标志
	 *
	 */
	private java.lang.String deleted;
   
	public void setDeleted(java.lang.String deleted){
		this.deleted= deleted;       
	}
   
	public java.lang.String getDeleted(){
		return this.deleted;
	}


	private String accessoriesId;
	
	public String getAccessoriesId() {
		return accessoriesId;
	}
	
	public void setAccessoriesId(String accessoriesId) {
		this.accessoriesId = accessoriesId;
	}

	public boolean equals(Object o) {
		if( o instanceof Papers ) {
			Papers papers=(Papers)o;
			if (this.id != null || this.id.equals(papers.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}