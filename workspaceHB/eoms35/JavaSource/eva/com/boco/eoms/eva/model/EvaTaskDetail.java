package com.boco.eoms.eva.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * 
 * <p>
 * Title: 考核任务详细信息
 * </p>
 * <p>
 * Description:记录任务包含所有指标、权重以及层级关系
 * </p>
 * <p>
 * Date:2009-1-6 下午04:40:10
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class EvaTaskDetail extends BaseObject {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 任务id
	 */
	private String taskId;

	/**
	 * 指标id
	 */
	private String kpiId;

	/**
	 * 指标权重
	 */
	private Float weight;

	/**
	 * 指标在表格中占据行数
	 */
	private String rowspan;

	/**
	 * 指标在表格中占据列数
	 */
	private String colspan;

	/**
	 * 指标所属行列表列表编号（相当于行号）
	 */
	private String listNo;

	/**
	 * 指标对应节点id
	 */
	private String nodeId;

	/**
	 * 指标对应父节点id
	 */
	private String parentNodeId;

	/**
	 * 指标对应节点叶子标志
	 */
	private String leaf;

	public String getColspan() {
		return colspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public String getListNo() {
		return listNo;
	}

	public void setListNo(String listNo) {
		this.listNo = listNo;
	}

	public String getRowspan() {
		return rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
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

	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
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
