package com.boco.eoms.km.table.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @author 吕卫华
 * @version 1.0
 */
public class KmTableColumn extends BaseObject {

    /**
     * 主键
     */
    private java.lang.String id;

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getId() {
        return this.id;
    }

    /**
     * 模型基本信息（外键）
     */
    private java.lang.String tableId;

    public void setTableId(java.lang.String tableId) {
        this.tableId = tableId;
    }

    public java.lang.String getTableId() {
        return this.tableId;
    }

    /**
     * 创建人
     */
    private java.lang.String createUser;

    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }

    public java.lang.String getCreateUser() {
        return this.createUser;
    }

    /**
     * 创建部门
     */
    private java.lang.String createDept;

    public void setCreateDept(java.lang.String createDept) {
        this.createDept = createDept;
    }

    public java.lang.String getCreateDept() {
        return this.createDept;
    }

    /**
     * 创建时间
     */
    private java.util.Date createTime;

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 字段英文名
     */
    private java.lang.String colName;

    public void setColName(java.lang.String colName) {
        this.colName = colName.toUpperCase();
    }

    public java.lang.String getColName() {
        return this.colName.toUpperCase();
    }

    /**
     * 字段中文名
     */
    private java.lang.String colChname;

    public void setColChname(java.lang.String colChname) {
        this.colChname = colChname;
    }

    public java.lang.String getColChname() {
        return this.colChname;
    }

    /**
     * 字段的类型（字典）
     */
    private java.lang.Integer colType;

    public void setColType(java.lang.Integer colType) {
        this.colType = colType;
    }

    public java.lang.Integer getColType() {
        return this.colType;
    }

    /**
     * 绑定的类型
     */
    private java.lang.Integer colDictType;

    public void setColDictType(java.lang.Integer colDictType) {
        this.colDictType = colDictType;
    }

    public java.lang.Integer getColDictType() {
        return this.colDictType;
    }

    /**
     * 绑定类型的编号
     */
    private java.lang.Integer colDictId;

    public void setColDictId(java.lang.Integer colDictId) {
        this.colDictId = colDictId;
    }

    public java.lang.Integer getColDictId() {
        return this.colDictId;
    }

    /**
     * 默认值
     */
    private java.lang.String colDefault;

    public void setColDefault(java.lang.String colDefault) {
        this.colDefault = colDefault;
    }

    public java.lang.String getColDefault() {
        return this.colDefault;
    }

    /**
     * 字段的大小
     */
    private java.lang.Integer colSize;

    public void setColSize(java.lang.Integer colSize) {
        this.colSize = colSize;
    }

    public java.lang.Integer getColSize() {
        return this.colSize;
    }

    /**
     * 是否可以为空
     */
    private java.lang.Integer isNullable;

    public void setIsNullable(java.lang.Integer isNullable) {
        this.isNullable = isNullable;
    }

    public java.lang.Integer getIsNullable() {
        return this.isNullable;
    }

    /**
     * 是否开放
     */
    private java.lang.Integer isOpen;

    public void setIsOpen(java.lang.Integer isOpen) {
        this.isOpen = isOpen;
    }

    public java.lang.Integer getIsOpen() {
        return this.isOpen;
    }

    /**
     * 是否可显
     */
    private java.lang.Integer isVisibl;

    public void setIsVisibl(java.lang.Integer isVisibl) {
        this.isVisibl = isVisibl;
    }

    public java.lang.Integer getIsVisibl() {
        return this.isVisibl;
    }

    /**
     * 是否唯一
     */
    private java.lang.Integer isUnique;

    public void setIsUnique(java.lang.Integer isUnique) {
        this.isUnique = isUnique;
    }

    public java.lang.Integer getIsUnique() {
        return this.isUnique;
    }

    /**
     * 是否索引
     */
    private java.lang.Integer isIndex;

    public void setIsIndex(java.lang.Integer isIndex) {
        this.isIndex = isIndex;
    }

    public java.lang.Integer getIsIndex() {
        return this.isIndex;
    }

    /**
     * 是否删除
     */
    private java.lang.Integer isDeleted;

    public void setIsDeleted(java.lang.Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public java.lang.Integer getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * 关联子节点的COL_NAME
     */
    private String subNode;

    public void setSubNode(String subNode) {
        this.subNode = subNode;
    }

    public String getSubNode() {
        return this.subNode;
    }

    /**
     * 关联父节点的COL_NAME
     */
    private String parentNode;

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * 排序值
     */
    private java.lang.Integer orderBy;

    public void setOrderBy(java.lang.Integer orderBy) {
        this.orderBy = orderBy;
    }

    public java.lang.Integer getOrderBy() {
        return this.orderBy;
    }

    /**
     * 是否换行
     */
    private java.lang.String mark;

    public java.lang.String getMark() {
        return mark;
    }

    public void setMark(java.lang.String mark) {
        this.mark = mark;
    }

    public boolean equals(Object o) {
        if (o instanceof KmTableColumn) {
            KmTableColumn kmTableColumn = (KmTableColumn) o;
            if (this.id != null || this.id.equals(kmTableColumn.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}