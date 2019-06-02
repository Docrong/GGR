package com.boco.eoms.commonfault.importexcel.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:使用表格导入撤销工单
 * </p>
 * <p>
 * Description:使用表格导入撤销工单
 * </p>
 * <p>
 * Tue Oct 26 10:55:09 CST 2010
 * </p>
 * 
 * @author liulei
 * @version 3.5
 * 
 */
public class CommonfaultImportExcel extends BaseObject {

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
	 * 文件名
	 *
	 */
	private java.lang.String fileName;
   
	public void setFileName(java.lang.String fileName){
		this.fileName= fileName;       
	}
   
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *
	 * 上传日期
	 *
	 */
	private java.lang.String createTime;
   
	public void setCreateTime(java.lang.String createTime){
		this.createTime= createTime;       
	}
   
	public java.lang.String getCreateTime(){
		return this.createTime;
	}

	/**
	 *
	 * 上传人员
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
	 * 附件存储名称
	 *
	 */
	private java.lang.String accessories;
   
	public void setAccessories(java.lang.String accessories){
		this.accessories= accessories;       
	}
   
	public java.lang.String getAccessories(){
		return this.accessories;
	}


	private String accessoriesId;
	
	public String getAccessoriesId() {
		return accessoriesId;
	}
	
	public void setAccessoriesId(String accessoriesId) {
		this.accessoriesId = accessoriesId;
	}

	public boolean equals(Object o) {
		if( o instanceof CommonfaultImportExcel ) {
			CommonfaultImportExcel commonfaultImportExcel=(CommonfaultImportExcel)o;
			if (this.id != null || this.id.equals(commonfaultImportExcel.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}