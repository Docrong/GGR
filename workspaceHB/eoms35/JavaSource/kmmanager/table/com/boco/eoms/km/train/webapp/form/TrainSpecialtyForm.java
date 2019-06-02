package com.boco.eoms.km.train.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:专业分类
 * </p>
 * <p>
 * Description:专业分类
 * </p>
 * <p>
 * Thu Jul 09 10:50:06 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public class TrainSpecialtyForm extends BaseForm implements java.io.Serializable {

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
	 * 专业名字
	 *
	 */
	private java.lang.String specialtyName;
   
	public void setSpecialtyName(java.lang.String specialtyName){
		this.specialtyName= specialtyName;       
	}
   
	public java.lang.String getSpecialtyName(){
		return this.specialtyName;
	}

	/**
	 *
	 * 是否删除
	 *
	 */
	private java.lang.String isDelete;
   
	public void setIsDelete(java.lang.String isDelete){
		this.isDelete= isDelete;       
	}
   
	public java.lang.String getIsDelete(){
		return this.isDelete;
	}

	/**
	 * 节点Id（按规则生成）
	 */
	private String nodeId;
	
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	/**
	 * 父节点Id
	 */
	private String parentNodeId;
	
	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
	
	/**
	 * 是否叶节点
	 */
	private String leaf;
	
	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
}