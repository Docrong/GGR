package com.boco.eoms.km.file.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:文件管理树
 * </p>
 * <p>
 * Description:文件管理树
 * </p>
 * <p>
 * Wed Mar 25 17:09:37 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */

public class KmFileTree extends BaseObject {
	
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
	 * 目录名
	 *
	 */
	private java.lang.String nodeName;
   
	public void setNodeName(java.lang.String nodeName){
		this.nodeName= nodeName;       
	}
   
	public java.lang.String getNodeName(){
		return this.nodeName;
	}

	/**
	 *
	 * 用户ID
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
	 * 创建时间
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
	
	/**
	 * 是否继承父目录权限
	 */
	private String hasParentOperate;
	
	public String getHasParentOperate() {
		return hasParentOperate;
	}

	public void setHasParentOperate(String hasParentOperate) {
		this.hasParentOperate = hasParentOperate;
	}
	
	public boolean equals(Object o) {
		if( o instanceof KmFileTree ) {
			KmFileTree kmFileTree=(KmFileTree)o;
			if (this.id != null || this.id.equals(kmFileTree.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}