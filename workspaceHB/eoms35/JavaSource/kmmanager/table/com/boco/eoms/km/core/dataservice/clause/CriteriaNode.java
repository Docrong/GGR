package com.boco.eoms.km.core.dataservice.clause;

import java.util.List;

import com.boco.eoms.km.core.dataservice.database.RelationalDB;
import com.boco.eoms.km.core.dataservice.map.FieldMap;
import com.boco.eoms.km.core.util.Operate;

/**
 * @author zhangxb
 */
public class CriteriaNode {

    private RelationalDB dataBase;
    private FieldMap fieldMap; // 字段
    private int opeType; // 操作
    private String value; // 值
    private String pattern = null; // 精度

    public CriteriaNode(RelationalDB dataBase, FieldMap aFieldMap,
                        int anOpeType, String aValue) {
        this.dataBase = dataBase;
        this.fieldMap = aFieldMap;
        this.opeType = anOpeType;
        this.value = aValue;
        if (this.opeType == Operate.NOT_FIND) {
            this.opeType = Operate.EQUAL;
        }
    }

    public void setPattern(String pattarn) {
        this.pattern = pattarn;
    }

    public String getPattern() {
        return this.pattern;
    }

    /**
     * 处理当前查询条件
     *
     * @param aValueList
     * @param aDataTypes
     * @return
     */
    public String getPreparedString(List aValueList, List aDataTypes) {
        String sql = null;
        try {
            String columnName = this.fieldMap.getFieldName();
            StringBuffer sqlBuffer = new StringBuffer(columnName);
            sql = getPreparedString(sqlBuffer, aValueList, aDataTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }

    /**
     * 动态生成查询条件
     *
     * @param sql
     * @param aValueList
     * @param aDataTypes
     * @return String
     * @throws Exception
     */
    private String getPreparedString(StringBuffer sql, List aValueList, List aDataTypes) throws Exception {

        switch (this.opeType) {

            case Operate.INVALIDATE: // 0
                sql.append(this.dataBase.getClauseStringEqualTo()); // =
                sql.append("?");

                aValueList.add(this.fieldMap.convertStringToObject(this.dataBase, this.value, pattern));
                aDataTypes.add(new Integer(fieldMap.getColumnMap().getColumnType()));
                return sql.toString();

            case Operate.NULL: // 11
                sql.append(' ');
                sql.append(this.dataBase.getClauseStringIs());
                sql.append(' ');
                sql.append(this.dataBase.getClauseStringNULL());
                return sql.toString();

            case Operate.NOT_NULL: // 1
                sql.append(' ');
                sql.append(this.dataBase.getClauseStringIs());
                sql.append(' ');
                sql.append(this.dataBase.getClauseStringNot());
                sql.append(' ');
                sql.append(this.dataBase.getClauseStringNULL());
                return sql.toString();

            case Operate.EQUAL: // 2
                sql.append(this.dataBase.getClauseStringEqualTo());
                sql.append("?");

                aValueList.add(this.fieldMap.convertStringToObject(
                        this.dataBase, this.value, getPattern()));
                aDataTypes.add(new Integer(this.fieldMap.getColumnMap()
                        .getColumnType()));
                return sql.toString();

            case Operate.NOT_EQUAL:// 3
                sql.append(this.dataBase.getClauseStringNOTEqual());
                sql.append("?");
                aValueList.add(this.fieldMap.convertStringToObject(
                        this.dataBase, this.value, getPattern()));
                aDataTypes.add(new Integer(this.fieldMap.getColumnMap()
                        .getColumnType()));
                return sql.toString();

            case Operate.LESSER:// 4
                sql.append('<');
                sql.append("?");
                aValueList.add(this.fieldMap.convertStringToObject(
                        this.dataBase, this.value, getPattern()));
                aDataTypes.add(new Integer(this.fieldMap.getColumnMap()
                        .getColumnType()));
                return sql.toString();

            case Operate.LESSER_EQUAL:// 5
                sql.append("<=");
                sql.append("?");
                aValueList.add(this.fieldMap.convertStringToObject(
                        this.dataBase, this.value, getPattern()));
                aDataTypes.add(new Integer(this.fieldMap.getColumnMap()
                        .getColumnType()));
                return sql.toString();

            case Operate.GREATER:// 6:
                sql.append('>');
                sql.append("?");
                aValueList.add(this.fieldMap.convertStringToObject(
                        this.dataBase, this.value, getPattern()));
                aDataTypes.add(new Integer(this.fieldMap.getColumnMap()
                        .getColumnType()));
                return sql.toString();

            case Operate.GREATER_EQUAL:// 7:
                sql.append(">=");
                sql.append("?");
                aValueList.add(this.fieldMap.convertStringToObject(
                        this.dataBase, this.value, getPattern()));
                aDataTypes.add(new Integer(this.fieldMap.getColumnMap()
                        .getColumnType()));
                return sql.toString();

            case Operate.LIKE:// 8:
                sql.append(' ');
                sql.append(this.dataBase.getClauseStringLike());
                sql.append(" '");
                sql.append(convertLikeString(this.value));
                sql.append('\'');
                return sql.toString();

            case Operate.BETWEEN:// 9
                return convertBetweenString(this.dataBase, this.value, aValueList, aDataTypes);
        }

        return null;
    }

    private String convertLikeString(String aValue) throws Exception {
        int columnType = this.fieldMap.getColumnMap().getColumnType();

        if ((columnType == 1) || (columnType == 12)) {
            char[] chars = new char[aValue.length()];
            aValue.getChars(0, aValue.length(), chars, 0);
            StringBuffer clause = new StringBuffer();
            for (int i = 0; i < chars.length; ++i)
                if (chars[i] == '*') {
                    clause.append(this.dataBase.getClauseStringMulMatch());
                } else if (chars[i] == '?') {
                    clause.append(this.dataBase.getClauseStringSingleMatch());
                } else {
                    clause.append(chars[i]);
                }
            return clause.toString();
        }

        String exception = "<" + this.fieldMap.getFieldName() + ">" + " : "
                + columnType;
        throw new Exception(exception);
    }

    private String convertBetweenString(RelationalDB aDataBase, String aValue, List aValueList, List aDataTypes) throws Exception {
        int separator = 0;
        if (getPattern() == null)
            separator = aValue.indexOf("|");
        else
            separator = aValue.indexOf("|", getPattern().length() - 1);

        if (separator == -1) {
            String exception = "<" + this.fieldMap.getFieldName() + ">"
                    + aValue + "</" + this.fieldMap.getFieldName() + ">";
            throw new Exception(exception);
        }

        String front = aValue.substring(0, separator);
        String offside = aValue.substring(separator + 1);

        StringBuffer clause = new StringBuffer(this.fieldMap.getEntityMap().getTableName());
        clause.append('.').append(this.fieldMap.getColumnMap().getColumnName());
        clause.append(' ');
        clause.append(aDataBase.getClauseStringBetween());
        clause.append(" ? ");
        clause.append(aDataBase.getClauseStringAnd());
        clause.append(" ? ");

        aValueList.add(this.fieldMap.convertStringToObject(this.dataBase, front, getPattern()));
        aValueList.add(this.fieldMap.convertStringToObject(this.dataBase, offside, getPattern()));

        int type = this.fieldMap.getColumnMap().getColumnType();
        aDataTypes.add(new Integer(type));
        aDataTypes.add(new Integer(type));

        return clause.toString();
    }
}
