package com.boco.eoms.km.score.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:积分设定树
 * </p>
 * <p>
 * Description:积分设定树
 * </p>
 * <p>
 * Mon Aug 17 14:42:36 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
public class KmScoreTree extends BaseObject {
	
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
	 * 节点名称
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
	 * 创建人
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
	 *
	 * 所占权重
	 *
	 */
	private java.lang.Integer weight;
   
	public void setWeight(java.lang.Integer weight){
		this.weight= weight;       
	}
   
	public java.lang.Integer getWeight(){
		return this.weight;
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
		if( o instanceof KmScoreTree ) {
			KmScoreTree kmScoreTree=(KmScoreTree)o;
			if (this.id != null || this.id.equals(kmScoreTree.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}