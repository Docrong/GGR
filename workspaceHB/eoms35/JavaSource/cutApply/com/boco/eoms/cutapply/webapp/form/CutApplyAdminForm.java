package com.boco.eoms.cutapply.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:干线割接管理权限人员
 * </p>
 * <p>
 * Description:干线割接管理权限人员
 * </p>
 * <p>
 * Thu Apr 02 16:59:37 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() wangsixuan
 * @moudle.getVersion() 3.5
 * 
 */
public class CutApplyAdminForm extends BaseForm implements java.io.Serializable {

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
	 * 权限人员
	 *
	 */
	private java.lang.String userId;
   
	public void setUserId(java.lang.String userId){
		this.userId= userId;       
	}
   
	public java.lang.String getUserId(){
		return this.userId;
	}
	
	/**
	 *
	 * 权限人员所在部门
	 *
	 */
	private java.lang.String deptId;
  
	public void setDeptId(java.lang.String deptId){
		this.deptId= deptId;       
	}
  
	public java.lang.String getDeptId(){
		return this.deptId;
	}

}