package com.boco.eoms.km.kmAuditer.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识管理审核人配置表
 * </p>
 * <p>
 * Description:知识管理审核人配置表
 * </p>
 * <p>
 * Wed Apr 29 15:46:36 CST 2009
 * </p>
 * 
 * @author 戴志刚
 * @version 1.0
 * 
 */
public class KmAuditer extends BaseObject {

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
	 * 新增时间
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
	 * 审核类型
	 *
	 */
	private java.lang.String auditType;
  
	public void setAuditType(java.lang.String auditType){
		this.auditType= auditType;       
	}
  
	public java.lang.String getAuditType(){
		return this.auditType;
	}

	/**
	 *
	 * 文件分类
	 *
	 */
	private java.lang.String nodeId;
  
	public void setNodeId(java.lang.String nodeId){
		this.nodeId= nodeId;       
	}
  
	public java.lang.String getNodeId(){
		return this.nodeId;
	}
	
	/**
	 *
	 * 知识库分类
	 *
	 */
	private java.lang.String tableId;
  
	public void setTableId(java.lang.String tableId){
		this.tableId= tableId;       
	}
  
	public java.lang.String getTableId(){
		return this.tableId;
	}
	
	/**
	 *
	 * 知识分类
	 *
	 */
	private java.lang.String themeId;
   
	public void setThemeId(java.lang.String themeId){
		this.themeId= themeId;       
	}
   
	public java.lang.String getThemeId(){
		return this.themeId;
	}

	/**
	 *
	 * 审核角色
	 *
	 */
	private java.lang.String roleId;
   
	public void setRoleId(java.lang.String roleId){
		this.roleId= roleId;       
	}
   
	public java.lang.String getRoleId(){
		return this.roleId;
	}

	/**
	 *
	 * 审核主管
	 *
	 */
	private java.lang.String masterId;
   
	public void setMasterId(java.lang.String masterId){
		this.masterId= masterId;       
	}
   
	public java.lang.String getMasterId(){
		return this.masterId;
	}

	/**
	 *
	 * 是否需要主管审核
	 *
	 */
	private java.lang.String masterAudit;
   
	public void setMasterAudit(java.lang.String masterAudit){
		this.masterAudit= masterAudit;       
	}
   
	public java.lang.String getMasterAudit(){
		return this.masterAudit;
	}

	/**
	 *
	 * 是否需要会审
	 *
	 */
	private java.lang.String isSign;
   
	public void setIsSign(java.lang.String isSign){
		this.isSign= isSign;       
	}
   
	public java.lang.String getIsSign(){
		return this.isSign;
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


	public boolean equals(Object o) {
		if( o instanceof KmAuditer ) {
			KmAuditer kmAuditer=(KmAuditer)o;
			if (this.id != null || this.id.equals(kmAuditer.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}