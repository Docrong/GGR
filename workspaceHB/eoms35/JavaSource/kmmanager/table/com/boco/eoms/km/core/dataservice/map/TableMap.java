package com.boco.eoms.km.core.dataservice.map;

import java.util.Date;

public class TableMap {

    private String id;// 主键
    private String themeId; // 知识分类（字典）
    private String tableName;// 表英文名
    private String tableChname;// 表中文名（模型名称）
    private String createUser;// 创建人
    private String createDept;// 创建部门
    private Date createTime;// 创建时间
    private String remark;// 备注描述
    private String orderBy;// 排序值
    private int isOpen;// 是否开放（字典）
    private int isVisibl;// 是否可显（字典）
    private int isAudit;// 是否需要审核（字典）
    private int isDeleted;// 是否删除（字典）
    private int isCreated; // 是否已创建表（字典）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableChname() {
        return tableChname;
    }

    public void setTableChname(String tableChname) {
        this.tableChname = tableChname;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public int getIsVisibl() {
        return isVisibl;
    }

    public void setIsVisibl(int isVisibl) {
        this.isVisibl = isVisibl;
    }

    public int getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(int isAudit) {
        this.isAudit = isAudit;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsCreated() {
        return isCreated;
    }

    public void setIsCreated(int isCreated) {
        this.isCreated = isCreated;
    }

}