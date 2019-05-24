package com.boco.eoms.km.table.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:模型字段定义表
 * </p>
 * <p>
 * Description:模型字段表
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 吕卫华
 * @moudle.getVersion() 1.0
 * 
 */
public class KmTableColumnForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 主键
	 * 
	 */
	private java.lang.String id;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 
	 * 模型基本信息（外键）
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
	 * 创建部门
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
	private String createTime;

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 
	 * 字段英文名
	 * 
	 */
	private java.lang.String colName;

	public void setColName(java.lang.String colName) {
		this.colName = colName;
	}

	public java.lang.String getColName() {
		return this.colName;
	}

	/**
	 * 
	 * 字段中文名
	 * 
	 */
	private java.lang.String colChname;

	public void setColChname(java.lang.String colChname) {
		this.colChname = colChname;
	}

	public java.lang.String getColChname() {
		return this.colChname;
	}

	/**
	 * 
	 * 字段的类型（字典）
	 * 
	 */
	private String colType;

	public void setColType(String colType) {
		this.colType = colType;
	}

	public String getColType() {
		return this.colType;
	}

	/**
	 * 
	 * 绑定的类型
	 * 
	 */
	private String colDictType;

	public void setColDictType(String colDictType) {
		this.colDictType = colDictType;
	}

	public String getColDictType() {
		return this.colDictType;
	}

	/**
	 * 
	 * 绑定类型的编号
	 * 
	 */
	private String colDictId;

	public void setColDictId(String colDictId) {
		this.colDictId = colDictId;
	}

	public String getColDictId() {
		return this.colDictId;
	}

	/**
	 * 
	 * 默认值
	 * 
	 */
	private java.lang.String colDefault;

	public void setColDefault(java.lang.String colDefault) {
		this.colDefault = colDefault;
	}

	public java.lang.String getColDefault() {
		return this.colDefault;
	}

	/**
	 * 
	 * 字段的大小
	 * 
	 */
	private String colSize;

	public void setColSize(String colSize) {
		this.colSize = colSize;
	}

	public String getColSize() {
		return this.colSize;
	}

	/**
	 * 
	 * 是否可以为空
	 * 
	 */
	private String isNullable;

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getIsNullable() {
		return this.isNullable;
	}

	/**
	 * 
	 * 是否开放
	 * 
	 */
	private String isOpen;

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getIsOpen() {
		return this.isOpen;
	}

	/**
	 * 
	 * 是否可显
	 * 
	 */
	private String isVisibl;

	public void setIsVisibl(String isVisibl) {
		this.isVisibl = isVisibl;
	}

	public String getIsVisibl() {
		return this.isVisibl;
	}

	/**
	 * 
	 * 是否唯一
	 * 
	 */
	private String isUnique;

	public void setIsUnique(String isUnique) {
		this.isUnique = isUnique;
	}

	public String getIsUnique() {
		return this.isUnique;
	}

	/**
	 * 
	 * 是否索引
	 * 
	 */
	private String isIndex;

	public void setIsIndex(String isIndex) {
		this.isIndex = isIndex;
	}

	public String getIsIndex() {
		return this.isIndex;
	}

	/**
	 * 
	 * 是否删除
	 * 
	 */
	private String isDeleted;

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * 
	 * 排序值
	 * 
	 */
	private java.lang.String orderBy;

	public void setOrderBy(java.lang.String orderBy) {
		this.orderBy = orderBy;
	}

	public java.lang.String getOrderBy() {
		return this.orderBy;
	}

	/**
	 * 
	 * 是否换行
	 * 
	 */
	private java.lang.String mark;

	public java.lang.String getMark() {
		return mark;
	}

	public void setMark(java.lang.String mark) {
		this.mark = mark;
	}

	/**
	 * 
	 * 关联子节点的COL_NAME
	 * 
	 */
	private String subNode;

	public void setSubNode(String subNode) {
		this.subNode = subNode;
	}

	public String getSubNode() {
		return this.subNode;
	}

	/**
	 * 
	 * 关联父节点的COL_NAME
	 * 
	 */
	private String parentNode;

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}
}