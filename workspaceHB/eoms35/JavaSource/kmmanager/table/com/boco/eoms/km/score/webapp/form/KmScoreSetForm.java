package com.boco.eoms.km.score.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:积分设定表
 * </p>
 * <p>
 * Description:积分设定表
 * </p>
 * <p>
 * Fri Aug 07 14:32:13 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() me
 * @moudle.getVersion() 1.0
 * 
 */
public class KmScoreSetForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 锟斤拷锟�
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
	 * 积分权重信息
	 *
	 */
	private java.lang.String scoreWeightId;
   
	public void setScoreWeightId(java.lang.String scoreWeightId){
		this.scoreWeightId= scoreWeightId;       
	}
   
	public java.lang.String getScoreWeightId(){
		return this.scoreWeightId;
	}

	/**
	 *
	 * 功能名称
	 *
	 */
	private java.lang.String powerName;
   
	public void setPowerName(java.lang.String powerName){
		this.powerName= powerName;       
	}
   
	public java.lang.String getPowerName(){
		return this.powerName;
	}

	/**
	 *
	 * 动作名称
	 *
	 */
	private java.lang.String actionName;
   
	public void setActionName(java.lang.String actionName){
		this.actionName= actionName;       
	}
   
	public java.lang.String getActionName(){
		return this.actionName;
	}

	/**
	 *
	 * 人员等级
	 *
	 */
	private java.lang.Integer userLevel;
   
	public void setUserLevel(java.lang.Integer userLevel){
		this.userLevel= userLevel;       
	}
   
	public java.lang.Integer getUserLevel(){
		return this.userLevel;
	}

	/**
	 *
	 * 积分
	 *
	 */
	private java.lang.Integer score;
   
	public void setScore(java.lang.Integer score){
		this.score= score;       
	}
   
	public java.lang.Integer getScore(){
		return this.score;
	}

	/**
	 *
	 * 是否计入总分
	 *
	 */
	private java.lang.Integer isCount;
   
	public void setIsCount(java.lang.Integer isCount){
		this.isCount= isCount;       
	}
   
	public java.lang.Integer getIsCount(){
		return this.isCount;
	}

	/**
	 *
	 * 是否删除
	 *
	 */
	private java.lang.Integer isDeleted;
   
	public void setIsDeleted(java.lang.Integer isDeleted){
		this.isDeleted= isDeleted;       
	}
   
	public java.lang.Integer getIsDeleted(){
		return this.isDeleted;
	}

	/**
	 *
	 * 备注
	 *
	 */
	private java.lang.String remark;
   
	public void setRemark(java.lang.String remark){
		this.remark= remark;       
	}
   
	public java.lang.String getRemark(){
		return this.remark;
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


}