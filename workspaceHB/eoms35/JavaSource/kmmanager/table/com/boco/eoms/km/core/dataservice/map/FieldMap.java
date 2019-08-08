package com.boco.eoms.km.core.dataservice.map;

import com.boco.eoms.km.core.dataservice.database.RelationalDB;

public class FieldMap {

    private String id; // 主键
    private String tableId; // 模型基本信息（外键）
    private String createUser; // 创建人
    private String createDept; // 创建部门
    private java.util.Date createTime; // 创建时间
    private String fieldName;// 字段英文名
    private String fieldChname; // 字段中文名
    private int fieldType;// 字段的类型（字典）
    private int fieldDictType;// 绑定的类型
    private int fieldDictId;// 绑定类型的编号
    private String fieldDefault;// 默认值
    private int fieldSize; // 字段的大小
    private int isNullable; // 是否可以为空
    private int isOpen; // 是否开放
    private int isVisibl;// 是否可显
    private int isUnique; // 是否唯一
    private int isIndex; // 是否索引
    private int isDeleted; // 是否删除
    private String orderBy; // 排序值

    private ColumnMap columnMap; // 数据库字段定义
    private EntityMap entityMap; // 模型实体

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
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

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName.toUpperCase();
    }

    public String getFieldChname() {
        return fieldChname;
    }

    public void setFieldChname(String fieldChname) {
        this.fieldChname = fieldChname;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldDictType() {
        return fieldDictType;
    }

    public void setFieldDictType(int fieldDictType) {
        this.fieldDictType = fieldDictType;
    }

    public int getFieldDictId() {
        return fieldDictId;
    }

    public void setFieldDictId(int fieldDictId) {
        this.fieldDictId = fieldDictId;
    }

    public String getFieldDefault() {
        return fieldDefault;
    }

    public void setFieldDefault(String fieldDefault) {
        this.fieldDefault = fieldDefault;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public int getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(int isNullable) {
        this.isNullable = isNullable;
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

    public int getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(int isUnique) {
        this.isUnique = isUnique;
    }

    public int getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(int isIndex) {
        this.isIndex = isIndex;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public ColumnMap getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(ColumnMap columnMap) {
        this.columnMap = columnMap;
    }

    public EntityMap getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(EntityMap entityMap) {
        this.entityMap = entityMap;

    }

    public int getColumnType() {
        return this.columnMap.getColumnType();
    }

    public String getColumnName() {
        return this.columnMap.getColumnName();
    }

    /**
     * @param dataBase 相关数据库
     * @param value    要转换的值
     * @param pattern  格式
     * @return Object
     * @throws Exception
     */
    public Object convertStringToObject(RelationalDB dataBase, String value, String pattern) throws Exception {
        return convertStringToObject(dataBase, value, this.columnMap.getColumnType(), this.getFieldSize(), pattern);
    }

    public Object convertStringToObject(RelationalDB dataBase, String value, int javaSqlType, int aColLength, String pattern) throws Exception {
        return dataBase.convStringToObject(value, javaSqlType, pattern);
    }
}