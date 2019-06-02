package com.boco.eoms.km.knowledge.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:44:45 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
public class KmContentsHistory extends BaseObject {

	/**
	 *
	 * 主键
	 *
	 */
	private java.lang.String id;
 
	public void setId(java.lang.String id){
		this.id= id;       
	}
 
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 * 模型定义(外键)
	 */
	private String tableId;
	
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 *
	 * 知识分类表(外键)
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
	 * 创建人部门
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
	 *
	 * 修改人
	 *
	 */
	private java.lang.String modifyUser;
   
	public void setModifyUser(java.lang.String modifyUser){
		this.modifyUser= modifyUser;       
	}
   
	public java.lang.String getModifyUser(){
		return this.modifyUser;
	}

	/**
	 *
	 * 修改人部门
	 *
	 */
	private java.lang.String modifyDept;
   
	public void setModifyDept(java.lang.String modifyDept){
		this.modifyDept= modifyDept;       
	}
   
	public java.lang.String getModifyDept(){
		return this.modifyDept;
	}

	/**
	 *
	 * 修改日期
	 *
	 */
	private java.util.Date modifyTime;
   
	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime= modifyTime;       
	}
   
	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}

	/**
	 *
	 * 知识标题
	 *
	 */
	private java.lang.String contentTitle;
   
	public void setContentTitle(java.lang.String contentTitle){
		this.contentTitle= contentTitle;       
	}
   
	public java.lang.String getContentTitle(){
		return this.contentTitle;
	}

	/**
	 *
	 * 知识关键字
	 *
	 */
	private java.lang.String contentKeys;
   
	public void setContentKeys(java.lang.String contentKeys){
		this.contentKeys= contentKeys;       
	}
   
	public java.lang.String getContentKeys(){
		return this.contentKeys;
	}

	/**
	 *
	 * 知识状态
	 *
	 */
	private java.lang.String contentStatus;
   
	public void setContentStatus(java.lang.String contentStatus){
		this.contentStatus= contentStatus;       
	}
   
	public java.lang.String getContentStatus(){
		return this.contentStatus;
	}

	/**
	 *
	 * 审核标识
	 *
	 */
	private java.lang.String auditFlag;
   
	public void setAuditFlag(java.lang.String auditFlag){
		this.auditFlag= auditFlag;       
	}
   
	public java.lang.String getAuditFlag(){
		return this.auditFlag;
	}

	/**
	 *
	 * 知识等级
	 *
	 */
	private java.lang.String rolestrFlag;
   
	public void setRolestrFlag(java.lang.String rolestrFlag){
		this.rolestrFlag= rolestrFlag;       
	}
   
	public java.lang.String getRolestrFlag(){
		return this.rolestrFlag;
	}

	/**
	 *
	 * 知识难易度
	 *
	 */
	private java.lang.String levelFlag;
   
	public void setLevelFlag(java.lang.String levelFlag){
		this.levelFlag= levelFlag;       
	}
   
	public java.lang.String getLevelFlag(){
		return this.levelFlag;
	}

	/**
	 *
	 * 是否推荐
	 *
	 */
	private java.lang.String isBest;
   
	public void setIsBest(java.lang.String isBest){
		this.isBest= isBest;       
	}
   
	public java.lang.String getIsBest(){
		return this.isBest;
	}

	/**
	 *
	 * 是否公开
	 *
	 */
	private java.lang.String isPublic;
   
	public void setIsPublic(java.lang.String isPublic){
		this.isPublic= isPublic;       
	}
   
	public java.lang.String getIsPublic(){
		return this.isPublic;
	}

	/**
	 *
	 * 知识评价：1星的次数
	 *
	 */
	private java.lang.Long gradeOne;
   
	public void setGradeOne(java.lang.Long gradeOne){
		this.gradeOne= gradeOne;       
	}
   
	public java.lang.Long getGradeOne(){
		return this.gradeOne;
	}

	/**
	 *
	 * 知识评价：2星的次数
	 *
	 */
	private java.lang.Long gradeTwo;
   
	public void setGradeTwo(java.lang.Long gradeTwo){
		this.gradeTwo= gradeTwo;       
	}
   
	public java.lang.Long getGradeTwo(){
		return this.gradeTwo;
	}

	/**
	 *
	 * 知识评价：3星的次数
	 *
	 */
	private java.lang.Long gradeThree;
   
	public void setGradeThree(java.lang.Long gradeThree){
		this.gradeThree= gradeThree;       
	}
   
	public java.lang.Long getGradeThree(){
		return this.gradeThree;
	}

	/**
	 *
	 * 知识评价：4星的次数
	 *
	 */
	private java.lang.Long gradeFour;
   
	public void setGradeFour(java.lang.Long gradeFour){
		this.gradeFour= gradeFour;       
	}
   
	public java.lang.Long getGradeFour(){
		return this.gradeFour;
	}

	/**
	 *
	 * 知识评价：5星的次数
	 *
	 */
	private java.lang.Long gradeFive;
   
	public void setGradeFive(java.lang.Long gradeFive){
		this.gradeFive= gradeFive;       
	}
   
	public java.lang.Long getGradeFive(){
		return this.gradeFive;
	}

	/**
	 *
	 * 知识被阅读的次数
	 *
	 */
	private java.lang.Long readCount;
   
	public void setReadCount(java.lang.Long readCount){
		this.readCount= readCount;       
	}
   
	public java.lang.Long getReadCount(){
		return this.readCount;
	}

	/**
	 *
	 * 知识被引用的次数
	 *
	 */
	private java.lang.Long useCount;
   
	public void setUseCount(java.lang.Long useCount){
		this.useCount= useCount;       
	}
   
	public java.lang.Long getUseCount(){
		return this.useCount;
	}

	/**
	 *
	 * 知识内容
	 *
	 */
	private java.lang.String contentXml;
   
	public void setContentXml(java.lang.String contentXml){
		this.contentXml= contentXml;       
	}
   
	public java.lang.String getContentXml(){
		return this.contentXml;
	}

	/**
	 *
	 * 知识被修改的次数
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
	 * 知识版本号
	 *
	 */
	private java.lang.Long version;
   
	public void setVersion(java.lang.Long version){
		this.version= version;       
	}
   
	public java.lang.Long getVersion(){
		return this.version;
	}

	public boolean equals(Object o) {
		if( o instanceof KmContentsHistory ) {
			KmContentsHistory kmContentsHistory=(KmContentsHistory)o;
			if (this.id != null || this.id.equals(kmContentsHistory.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}