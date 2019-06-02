package com.boco.eoms.km.statics.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:部门知识贡献情况
 * </p>
 * <p>
 * Description:部门知识贡献情况
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 * 
 */
public class DeptContributeStatisticForm extends BaseForm implements java.io.Serializable {

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
	 * 部门名称
	 *
	 */
	private java.lang.String deptName;
   
	public void setDeptName(java.lang.String deptName){
		this.deptName= deptName;       
	}
   
	public java.lang.String getDeptName(){
		return this.deptName;
	}

	/**
	 *
	 * 新增知识数
	 *
	 */
	private java.lang.Long addCount;
   
	public void setAddCount(java.lang.Long addCount){
		this.addCount= addCount;       
	}
   
	public java.lang.Long getAddCount(){
		return this.addCount;
	}

	/**
	 *
	 * 提交知识有效率
	 *
	 */
	private java.lang.String auditOkPercentage;
   
	public void setAuditOkPercentage(java.lang.String auditOkPercentage){
		this.auditOkPercentage= auditOkPercentage;       
	}
   
	public java.lang.String getAuditOkPercentage(){
		return this.auditOkPercentage;
	}

	/**
	 *
	 * 知识利用率
	 *
	 */
	private java.lang.String utilizationRate;
   
	public void setUtilizationRate(java.lang.String utilizationRate){
		this.utilizationRate= utilizationRate;       
	}
   
	public java.lang.String getUtilizationRate(){
		return this.utilizationRate;
	}

	/**
	 *
	 * 知识引用数
	 *
	 */
	private java.lang.Long useCount;
   
	public void setUseCount(java.lang.Long useCount){
		this.useCount= useCount;       
	}
   
	public java.lang.Long getUseCount(){
		return this.useCount;
	}

	/**
	 *
	 * 上传文件数
	 *
	 */
	private java.lang.Long upCount;
   
	public void setUpCount(java.lang.Long upCount){
		this.upCount= upCount;       
	}
   
	public java.lang.Long getUpCount(){
		return this.upCount;
	}

}