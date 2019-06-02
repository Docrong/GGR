package com.boco.eoms.km.statics.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

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
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 * 
 */
public class PersonalUseStatisticForm extends BaseForm implements java.io.Serializable {

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
	private java.lang.Long useCount;
   
	public void setUseCount(java.lang.Long useCount){
		this.useCount= useCount;       
	}
   
	public java.lang.Long getUseCount(){
		return this.useCount;
	}

	/**
	 *
	 * 提出知识修改意见数
	 *
	 */
	private java.lang.Long adviceCount;
   
	public void setAdviceCount(java.lang.Long adviceCount){
		this.adviceCount= adviceCount;       
	}
   
	public java.lang.Long getAdviceCount(){
		return this.adviceCount;
	}

	/**
	 *
	 * 评价知识数
	 *
	 */
	private java.lang.Long opinionCount;
   
	public void setOpinionCount(java.lang.Long opinionCount){
		this.opinionCount= opinionCount;       
	}
   
	public java.lang.Long getOpinionCount(){
		return this.opinionCount;
	}

	/**
	 * 
	 * 下载文件数
	 * 
	 */
	private java.lang.Long downCount;
	
	public java.lang.Long getDownCount() {
		return downCount;
	}

	public void setDownCount(java.lang.Long downCount) {
		this.downCount = downCount;
	}
	
}