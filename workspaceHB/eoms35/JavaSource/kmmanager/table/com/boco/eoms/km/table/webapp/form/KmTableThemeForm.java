package com.boco.eoms.km.table.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:模型分类表
 * </p>
 * <p>
 * Description:模型分类
 * </p>
 * <p>
 * Thu Mar 26 10:16:39 CST 2009
 * </p>
 *
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 */
public class KmTableThemeForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;


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
    private String createTime;

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 分类名称
     */
    private java.lang.String themeName;

    public void setThemeName(java.lang.String themeName) {
        this.themeName = themeName;
    }

    public java.lang.String getThemeName() {
        return this.themeName;
    }


    /**
     * 是否开放
     */
    private String isOpen;

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getIsOpen() {
        return this.isOpen;
    }

    /**
     * 是否删除
     */
    private String isDeleted;

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * 排序值
     */
    private String orderBy;

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    /**
     * 是否已被使用
     */
    private String isUsed;

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getIsUsed() {
        return this.isUsed;
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
     * 知识模型主键
     */
    private java.lang.String tableId;

    public java.lang.String getTableId() {
        return tableId;
    }

    public void setTableId(java.lang.String tableId) {
        this.tableId = tableId;
    }


    /**
     * 是否继承上级目录
     */
    private java.lang.String hasParentOperate;

    public java.lang.String getHasParentOperate() {
        return hasParentOperate;
    }

    public void setHasParentOperate(java.lang.String hasParentOperate) {
        this.hasParentOperate = hasParentOperate;
    }
}