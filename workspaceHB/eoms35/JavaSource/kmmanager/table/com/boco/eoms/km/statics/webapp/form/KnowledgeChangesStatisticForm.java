package com.boco.eoms.km.statics.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识库知识变更情况统计表
 * </p>
 * <p>
 * Description:知识库知识变更情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 * 
 */
public class KnowledgeChangesStatisticForm extends BaseForm implements java.io.Serializable {

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
	 * 知识库名称
	 *
	 */
	private java.lang.String baseName;
   
	public void setBaseName(java.lang.String baseName){
		this.baseName= baseName;       
	}
   
	public java.lang.String getBaseName(){
		return this.baseName;
	}

	/**
	 *
	 * 库维护人
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
	 * 新增知识总数
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
	 * 修改知识总数
	 *
	 */
	private java.lang.Long modifyCount;
   
	public void setModifyCount(java.lang.Long modifyCount){
		this.modifyCount= modifyCount;       
	}
   
	public java.lang.Long getModifyCount(){
		return this.modifyCount;
	}

	/**
	 *
	 * 删除知识总数
	 *
	 */
	private java.lang.Long deleteCount;
   
	public void setDeleteCount(java.lang.Long deleteCount){
		this.deleteCount= deleteCount;       
	}
   
	public java.lang.Long getDeleteCount(){
		return this.deleteCount;
	}

	/**
	 *
	 * 失效知识总数
	 *
	 */
	private java.lang.Long overCount;
   
	public void setOverCount(java.lang.Long overCount){
		this.overCount= overCount;       
	}
   
	public java.lang.Long getOverCount(){
		return this.overCount;
	}

}