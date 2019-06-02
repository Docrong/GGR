package com.boco.eoms.km.knowledge.webapp.form;

import java.util.HashMap;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:32:12 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() eoms
 * @moudle.getVersion() 1.0
 * 
 */
public class KmContentsForm extends BaseForm implements java.io.Serializable {

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
	 * 知识分类表(外键)
	 *
	 */
	private java.lang.String themeId;

	public void setThemeId(java.lang.String themeId) {
		this.themeId = themeId;
	}

	public java.lang.String getThemeId() {
		return this.themeId;
	}

	/**
	 *
	 * 表主键(外键)
	 *
	 */
	private java.lang.String tableId;

	public void setTableId(java.lang.String tableId) {
		this.tableId = tableId;
	}

	public java.lang.String getTableId() {
		return this.tableId;
	}

	/**
	 *
	 * 创建人
	 *
	 */
	private java.lang.String createUser;

	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}

	public java.lang.String getCreateUser() {
		return this.createUser;
	}

	/**
	 *
	 * 创建人部门
	 *
	 */
	private java.lang.String createDept;

	public void setCreateDept(java.lang.String createDept) {
		this.createDept = createDept;
	}

	public java.lang.String getCreateDept() {
		return this.createDept;
	}

	/**
	 *
	 * 创建时间
	 *
	 */
	private java.lang.String createTime;

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getCreateTime() {
		return this.createTime;
	}

	/**
	 *
	 * 修改人
	 *
	 */
	private java.lang.String modifyUser;

	public void setModifyUser(java.lang.String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public java.lang.String getModifyUser() {
		return this.modifyUser;
	}

	/**
	 *
	 * 修改人部门
	 *
	 */
	private java.lang.String modifyDept;

	public void setModifyDept(java.lang.String modifyDept) {
		this.modifyDept = modifyDept;
	}

	public java.lang.String getModifyDept() {
		return this.modifyDept;
	}

	/**
	 *
	 * 修改日期
	 *
	 */
	private java.lang.String modifyTime;

	public void setModifyTime(java.lang.String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public java.lang.String getModifyTime() {
		return this.modifyTime;
	}

	/**
	 *
	 * 知识标题
	 *
	 */
	private java.lang.String contentTitle;

	public void setContentTitle(java.lang.String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public java.lang.String getContentTitle() {
		return this.contentTitle;
	}

	/**
	 *
	 * 知识关键字
	 *
	 */
	private java.lang.String contentKeys;

	public void setContentKeys(java.lang.String contentKeys) {
		this.contentKeys = contentKeys;
	}

	public java.lang.String getContentKeys() {
		return this.contentKeys;
	}

	/**
	 *
	 * 知识状态
	 *
	 */
	private java.lang.String contentStatus;

	public void setContentStatus(java.lang.String contentStatus) {
		this.contentStatus = contentStatus;
	}

	public java.lang.String getContentStatus() {
		return this.contentStatus;
	}

	/**
	 *
	 * 审核标识
	 *
	 */
	private java.lang.String auditFlag;

	public void setAuditFlag(java.lang.String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public java.lang.String getAuditFlag() {
		return this.auditFlag;
	}

	/**
	 *
	 * 知识等级
	 *
	 */
	private java.lang.String rolestrFlag;

	public void setRolestrFlag(java.lang.String rolestrFlag) {
		this.rolestrFlag = rolestrFlag;
	}

	public java.lang.String getRolestrFlag() {
		return this.rolestrFlag;
	}

	/**
	 *
	 * 知识难易度
	 *
	 */
	private java.lang.String levelFlag;

	public void setLevelFlag(java.lang.String levelFlag) {
		this.levelFlag = levelFlag;
	}

	public java.lang.String getLevelFlag() {
		return this.levelFlag;
	}

	/**
	 *
	 * 是否推荐
	 *
	 */
	private java.lang.String isBest;

	public void setIsBest(java.lang.String isBest) {
		this.isBest = isBest;
	}

	public java.lang.String getIsBest() {
		return this.isBest;
	}

	/**
	 *
	 * 是否公开
	 *
	 */
	private java.lang.String isPublic;

	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	public java.lang.String getIsPublic() {
		return this.isPublic;
	}

	/**
	 *
	 * 知识评价：1星的次数
	 *
	 */
	private java.lang.String gradeOne;

	public void setGradeOne(java.lang.String gradeOne) {
		this.gradeOne = gradeOne;
	}

	public java.lang.String getGradeOne() {
		return this.gradeOne;
	}

	/**
	 *
	 * 知识评价：2星的次数
	 *
	 */
	private java.lang.String gradeTwo;

	public void setGradeTwo(java.lang.String gradeTwo) {
		this.gradeTwo = gradeTwo;
	}

	public java.lang.String getGradeTwo() {
		return this.gradeTwo;
	}

	/**
	 *
	 * 知识评价：3星的次数
	 *
	 */
	private java.lang.String gradeThree;

	public void setGradeThree(java.lang.String gradeThree) {
		this.gradeThree = gradeThree;
	}

	public java.lang.String getGradeThree() {
		return this.gradeThree;
	}

	/**
	 *
	 * 知识评价：4星的次数
	 *
	 */
	private java.lang.String gradeFour;

	public void setGradeFour(java.lang.String gradeFour) {
		this.gradeFour = gradeFour;
	}

	public java.lang.String getGradeFour() {
		return this.gradeFour;
	}

	/**
	 *
	 * 知识评价：5星的次数
	 *
	 */
	private java.lang.String gradeFive;

	public void setGradeFive(java.lang.String gradeFive) {
		this.gradeFive = gradeFive;
	}

	public java.lang.String getGradeFive() {
		return this.gradeFive;
	}

	/**
	 *
	 * 知识被阅读的次数
	 *
	 */
	private java.lang.String readCount;

	public void setReadCount(java.lang.String readCount) {
		this.readCount = readCount;
	}

	public java.lang.String getReadCount() {
		return this.readCount;
	}

	/**
	 *
	 * 知识被引用的次数
	 *
	 */
	private java.lang.String useCount;

	public void setUseCount(java.lang.String useCount) {
		this.useCount = useCount;
	}

	public java.lang.String getUseCount() {
		return this.useCount;
	}

	/**
	 *
	 * 知识被修改的次数
	 *
	 */
	private java.lang.String modifyCount;

	public void setModifyCount(java.lang.String modifyCount) {
		this.modifyCount = modifyCount;
	}

	public java.lang.String getModifyCount() {
		return this.modifyCount;
	}

	/**
	 *
	 * 知识内容
	 *
	 */
	private HashMap props = new HashMap();

	public String getProps(String key) {
		return (String)props.get(key);
	}

	public void setProps(String key, String value) {
		props.put(key, value);
	}

}