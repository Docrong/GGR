package com.boco.eoms.km.statics.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @author ljt
 * @version 0.1
 * 
 */
public class KnowledgeChangesStatistic extends BaseObject {

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
	private java.lang.Integer addCount;
   
	public void setAddCount(java.lang.Integer addCount){
		this.addCount= addCount;       
	}
   
	public java.lang.Integer getAddCount(){
		return this.addCount;
	}

	/**
	 *
	 * 修改知识总数
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
	 * 删除知识总数
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
	 * 失效知识总数
	 *
	 */
	private java.lang.Integer overCount;
   
	public void setOverCount(java.lang.Integer overCount){
		this.overCount= overCount;       
	}
   
	public java.lang.Integer getOverCount(){
		return this.overCount;
	}

	public boolean equals(Object o) {
		if( o instanceof KnowledgeChangesStatistic ) {
			KnowledgeChangesStatistic knowledgeChangesStatistic=(KnowledgeChangesStatistic)o;
			if (this.id != null || this.id.equals(knowledgeChangesStatistic.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}