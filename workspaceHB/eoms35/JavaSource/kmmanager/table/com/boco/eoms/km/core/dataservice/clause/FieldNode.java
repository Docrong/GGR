package com.boco.eoms.km.core.dataservice.clause;

import java.sql.Types;
import java.util.List;

import com.boco.eoms.km.core.dataservice.database.RelationalDB;
import com.boco.eoms.km.core.dataservice.map.FieldMap;
import com.boco.eoms.km.core.dataservice.util.DataUtil;
import com.boco.eoms.km.core.util.Function;

public class FieldNode {

    private RelationalDB dataBase;
    private FieldMap fieldMap;
    private int function; // 函数
    private String value; // 值
    private String aliasName; // 别名
    private String pattern; // 格式化

    public FieldNode(RelationalDB aDataBase, FieldMap aFieldMap, int aFunction,
                     String aValue) {
        this(aDataBase, aFieldMap, aFunction, aValue, null);
    }

    public FieldNode(RelationalDB aDataBase, FieldMap aFieldMap, int aFunction,
                     String aValue, String aAliasName) {
        dataBase = aDataBase;
        fieldMap = aFieldMap;
        function = aFunction;
        value = aValue;
        aliasName = aAliasName;
        pattern = null;
    }

    public String getAliasName() {
        if (aliasName == null)
            aliasName = "";
        return aliasName;
    }

    public void setFunction(int aFuncCode) {
        function = aFuncCode;
    }

    public int getFunType() {
        return function;
    }

    private String getPattern() {
        return this.pattern;
    }

    public FieldMap getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(FieldMap fieldMap) {
        this.fieldMap = fieldMap;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Object getSelectPreparedString(List valueList,
                                          List dataTypes) {
        try {
            String columnName = this.fieldMap.getColumnName(); // 字段名称
            String attrName = getAliasName(); // 字段别名
            return getSelectPreparedString(valueList, dataTypes, columnName,
                    attrName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSelectPreparedString(List aValueList,
                                          List aDataTypes, String columnName, String aliasName)
            throws Exception {
        StringBuffer sql = new StringBuffer();

        switch (this.function) {
            case Function.ADD: // 1
                if (this.value == null) {
                    throw new Exception(
                            "FieldNode.java getSelectPreparedString()");
                }
                sql.append(columnName);
                sql.append('+');
                sql.append('?');
                addParamList(aValueList, aDataTypes);
                break;
            case Function.SUB: // 2
                if (this.value == null) {
                    throw new Exception(
                            "FieldNode.java getSelectPreparedString()");
                }
                sql.append(columnName);
                sql.append('-');
                sql.append('?');
                addParamList(aValueList, aDataTypes);
                break;
            case Function.INC: // 3
                sql.append(columnName);
                sql.append('+');
                sql.append('?');
                aValueList.add(new Integer(1));
                aDataTypes.add(new Integer(4));
                break;
            case Function.DEC: // 4
                sql.append(columnName);
                sql.append('-');
                sql.append('?');
                aValueList.add(new Integer(1));
                aDataTypes.add(new Integer(4));
                break;
            case Function.ZERO: // 5
                sql.append('0');
                sql.append(' ');
                break;
            case Function.AVG: // 6
                sql.append(this.dataBase.getClauseStringAVG());
                sql.append('(');
                sql.append(columnName);
                sql.append(')');
                break;
            case Function.SUM: // 7
                sql.append(this.dataBase.getClauseStringSUM());
                sql.append('(');
                sql.append(columnName);
                sql.append(')');
                break;
            case Function.COUNT: // 8
                sql.append(this.dataBase.getClauseStringCOUNT());
                sql.append('(');
                sql.append(columnName);
                sql.append(')');
                break;
            case Function.NOT_FUNCTION: // 9
                sql.append(columnName);
                break;
            case Function.SET: // 10
                int columnType = this.fieldMap.getColumnType();
                if (columnType == Types.VARCHAR || columnType == Types.CHAR) {
                    sql.append('\'');
                    sql.append(this.value);
                    sql.append('\'');
                } else {
                    sql.append(this.fieldMap.convertStringToObject(
                            this.dataBase, this.value, getPattern()));
                }
                break;
            case Function.MAX: // 11
                sql.append(this.dataBase.getClauseStringMax());
                sql.append('(');
                sql.append(columnName);
                sql.append(')');
                break;
            case Function.MIN: // 12
                sql.append(this.dataBase.getClauseStringMin());
                sql.append('(');
                sql.append(columnName);
                sql.append(')');
                break;
            default:
                sql.append(columnName);
        }

        sql.append(" ");
        sql.append(aliasName); // 定义字段的别名
        return sql.toString();
    }

    public Object getInsertPreparedString(List outValue,
                                          List outType) throws Exception {
        String sql = varcharSQLString(outValue, outType);
        if (sql == null) {
            addParamList(outValue, outType);
            return "?";
        }
        return sql;
    }

    private void addParamList(List aValueList, List aDataTypes)
            throws Exception {
        // 添加值
        aValueList.add(this.fieldMap.convertStringToObject(this.dataBase, this.value, getPattern()));
        // 添加值的类型
        int columnType = this.fieldMap.getColumnType();
        if (columnType == Types.VARCHAR && this.value != null
                && (DataUtil.nativeLength(this.value) >= 1280)) {
            aDataTypes.add(new Integer(-1));
            return;
        }
        aDataTypes.add(new Integer(columnType));
    }

    public String varcharSQLString(List outValue, List outType) {
        return null;// 尚未实现，该方法是对ORACLE数据库的特殊处理
    }

    public Object getUpdatePreparedString(List aValueList,
                                          List aDataTypes) throws Exception {
        String columnName = this.fieldMap.getColumnName();
        StringBuffer sql = new StringBuffer();
        sql.append(columnName);
        sql.append('=');

        switch (this.function) {
            case Function.SET: // 10
                String tmp = varcharSQLString(aValueList, aDataTypes);
                if (tmp == null) {
                    sql.append(addParamList("", aValueList, aDataTypes, ""));
                    break;
                }
                return tmp;
            case Function.ADD: // 1
                if (this.value == null) {
                    throw new Exception(
                            "FieldNode.java getUpdatePreparedString()");
                }
                sql
                        .append(addParamList(columnName, aValueList,
                                aDataTypes, "+"));
                break;
            case Function.SUB: // 2
                if (this.value == null) {
                    throw new Exception(
                            "FieldNode.java getUpdatePreparedString()");
                }
                sql.append(columnName);
                sql.append('-');
                sql.append('?');
                sql.append(' ');
                addParamList(aValueList, aDataTypes);
                break;
            case Function.INC: // 3
                sql.append(columnName);
                sql.append('+');
                sql.append('?');
                aValueList.add(new Integer(1));
                aDataTypes.add(new Integer(4));
                break;
            case Function.DEC: // 4
                sql.append(columnName);
                sql.append('-');
                sql.append('?');
                aValueList.add(new Integer(1));
                aDataTypes.add(new Integer(4));
                break;
            case Function.ZERO: // 5
                sql.append('0');
                sql.append(' ');
                break;
            case Function.AVG: // 6
                throw new Exception("FieldNode.java getUpdatePreparedString()");
            case Function.SUM: // 7
                throw new Exception("FieldNode.java getUpdatePreparedString()");
            case Function.COUNT: // 8
                throw new Exception("FieldNode.java getUpdatePreparedString()");
            case Function.NOT_FUNCTION: // 9
                addParamList(aValueList, aDataTypes);
                sql.append("?");
                break;
            case Function.MAX: // 11
                throw new Exception("FieldNode.java getUpdatePreparedString()");
            case Function.MIN: // 12
                throw new Exception("FieldNode.java getUpdatePreparedString()");
            default:
                addParamList(aValueList, aDataTypes);
                sql.append("?");
        }
        return sql.toString();
    }

    private String addParamList(String aColName, List aValueList,
                                List aDataTypes, String opeString) throws Exception {
        StringBuffer buffer = new StringBuffer(aColName);
        buffer.append(opeString);
        int columnType = fieldMap.getColumnType();
        if (columnType == Types.VARCHAR && this.value != null
                && DataUtil.nativeLength(this.value) >= 1280) {
            aDataTypes.add(new Integer(-1));
        } else {
            aDataTypes.add(new Integer(columnType));
        }
        buffer.append('?');
        aValueList.add(this.fieldMap.convertStringToObject(this.dataBase, this.value, getPattern()));
        return buffer.toString();
    }
}
