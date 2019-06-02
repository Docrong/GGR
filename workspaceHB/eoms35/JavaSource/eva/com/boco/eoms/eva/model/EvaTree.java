package com.boco.eoms.eva.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:考核树，用来维持树形结构
 * </p>
 * <p>
 * Description:考核树，用来维持树形结构
 * </p>
 * <p>
 * Date:2008-11-20 上午11:02:55
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */

public class EvaTree extends BaseObject {

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 节点Id（按规则生成）
	 */
	private String nodeId;

	/**
	 * 节点名称
	 */
	private String nodeName;

	/**
	 * 节点类型（指标类型、指标、模板等）
	 */
	private String nodeType;

	/**
	 * 节点备注
	 */
	private String nodeRemark;

	/**
	 * 父节点Id
	 */
	private String parentNodeId;

	/**
	 * 是否叶节点
	 */
	private String leaf;

	/**
	 * 树节点对应的对象Id（模板、指标等）
	 */
	private String objectId;
	
	/**
	 * 指标节点对应的权值，模板节点默认为100
	 */
	private Float weight;

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeRemark() {
		return nodeRemark;
	}

	public void setNodeRemark(String nodeRemark) {
		this.nodeRemark = nodeRemark;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public boolean equals(Object o) {
		return false;
	}

	public String toString() {
		return null;
	}

	public int hashCode() {
		return 0;
	}

}
