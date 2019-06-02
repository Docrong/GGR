package com.boco.eoms.km.ask.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:问答类型
 * </p>
 * <p>
 * Description:问答类型
 * </p>
 * <p>
 * Tue Aug 04 15:17:03 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmAskSpeciality extends BaseObject {
	
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
	 * 类型名字
	 *
	 */
	private java.lang.String name;
   
	public void setName(java.lang.String name){
		this.name= name;       
	}
   
	public java.lang.String getName(){
		return this.name;
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
	 *
	 * 创建人
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
	 * 创建时间
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

	public boolean equals(Object o) {
		if( o instanceof KmAskSpeciality ) {
			KmAskSpeciality kmAskSpeciality=(KmAskSpeciality)o;
			if (this.id != null || this.id.equals(kmAskSpeciality.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}