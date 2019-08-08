package com.boco.eoms.km.core.dataservice.map;

public class ColumnMap {

    private String catalogName; // 获取指定列的表目录名称

    private Object colClass; // 如果调用方法 ResultSet.getObject 从列中检索值，则返回构造其实例的 Java 类的完全限定名称。

    private String columnClassName; // 如果调用方法 ResultSet.getObject 从列中检索值，则返回构造其实例的 Java 类的完全限定名称。

    private int columnDisplaySize; // 指示指定列的最大标准宽度，以字符为单位。

    private String columnLabel; // 获取用于打印输出和显示的指定列的建议标题。

    private String columnName; // 获取指定列的名称。

    private int columnType; // 检索指定列的 SQL 类型。

    private String columnTypeName; // 检索指定列的数据库特定的类型名称。

    private int precision; // 获取指定列的小数位数。

    private int scale; // 获取指定列的小数点右边的位数。

    private String schemaName; // 获取指定列的表模式。

    private String tableName; // 获取指定列的名称。

    private boolean isAutoIncrement; // 指示是否自动为指定列进行编号，这样这些列仍然是只读的。

    private boolean isCaseSensitive; // 指示列的大小写是否有关系。

    private boolean isCurrency; // 指示指定的列是否是一个哈希代码值。

    private boolean isDefinitelyWritable; // 指示在指定的列上进行写操作是否明确可以获得成功。

    private int isNullable; // 指示指定列中的值是否可以为 null。

    private boolean isReadOnly; // 指示指定的列是否明确不可写入。

    private boolean isSearchable; // 指示是否可以在 where 子句中使用指定的列。

    private boolean isSigned; // 指示指定列中的值是否带正负号。

    private boolean isWritable; // 指示在指定的列上进行写操作是否可以获得成功。

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Object getColClass() {
        return colClass;
    }

    public void setColClass(Object colClass) {
        this.colClass = colClass;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public int getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName.toUpperCase();
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    public void setCaseSensitive(boolean isCaseSensitive) {
        this.isCaseSensitive = isCaseSensitive;
    }

    public boolean isCurrency() {
        return isCurrency;
    }

    public void setCurrency(boolean isCurrency) {
        this.isCurrency = isCurrency;
    }

    public boolean isDefinitelyWritable() {
        return isDefinitelyWritable;
    }

    public void setDefinitelyWritable(boolean isDefinitelyWritable) {
        this.isDefinitelyWritable = isDefinitelyWritable;
    }

    public int isNullable() {
        return isNullable;
    }

    public void setIsNullable(int isNullable) {
        this.isNullable = isNullable;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    public boolean isSearchable() {
        return isSearchable;
    }

    public void setSearchable(boolean isSearchable) {
        this.isSearchable = isSearchable;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean isSigned) {
        this.isSigned = isSigned;
    }

    public boolean isWritable() {
        return isWritable;
    }

    public void setWritable(boolean isWritable) {
        this.isWritable = isWritable;
    }

}