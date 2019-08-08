package com.boco.eoms.km.core.dataservice.database;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.core.dataservice.sql.BlobImpl;
import com.boco.eoms.km.core.dataservice.sql.ClobImpl;
import com.boco.eoms.km.core.dataservice.util.BaseUtil;

public abstract class RelationalDB {

    public static final String ORACLE_PRODUCT_NAME = "ORACLE";
    public static final String INFORMIX_PRODUCT_NAME = "INFORMIX";

    public static int LENGTH_DATE = 19;
    public static int LENGTH_TIME = 8;
    public static int LENGTH_TIMESTAMP = 19;
    public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String TIME_FORMAT = "HH:mm:ss";
    public static String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final int MAX_VARCHAR_PREPARE_LENGTH = 1280;

    protected static final char ZERO_FLAG = 48;
    protected static final char POIINT_FLAG = 46;

    static String DISTINCT = "DISTINCT";
    static String AND = "AND";
    static String ASC = "ASC";
    static String BETWEEN = "BETWEEN";
    static String DELETE = "DELETE";
    static String DESC = "DESC";
    static String EqualTo = "=";
    static String NOTEqual = "<>";
    static String FOR_UPDATE = "FOR UPDATE";
    static String FROM = "FROM";
    static String FROM_INCLUDE_SPACE = " FROM ";
    static String GROUP_BY = "GROUP BY";
    static String HAVING = "HAVING";
    static String IN = "IN";
    static String INSERT_INTO = "INSERT INTO";
    static String IS = "IS";
    static String LIKE = "LIKE";
    static String ORDER_BY = "ORDER BY";
    static String SELECT = "SELECT";
    static String SET = "SET";
    static String UPDATE = "UPDATE";
    static String WHERE = "WHERE";
    static String NOT = "NOT";
    static String OR = "OR";
    static String VALUES = "VALUES";
    static String AVG = "AVG";
    static String SUM = "SUM";
    static String COUNT = "COUNT";
    static String NULL = "NULL";
    static String MulMatch = "%";
    static String SingleMatch = "_";
    static String MAX = "MAX";
    static String MIN = "MIN";

    public String getClauseStringDistinct() {
        return DISTINCT;
    }

    public String getClauseStringAnd() {
        return AND;
    }

    public String getClauseStringAscend() {
        return ASC;
    }

    public String getClauseStringBetween() {
        return BETWEEN;
    }

    public String getClauseStringDelete() {
        return DELETE;
    }

    public String getClauseStringDescend() {
        return DESC;
    }

    public String getClauseStringEqualTo() {
        return EqualTo;
    }

    public String getClauseStringNOTEqual() {
        return NOTEqual;
    }

    public String getClauseStringForUpdate() {
        return FOR_UPDATE;
    }

    public String getClauseStringFrom() {
        return FROM;
    }

    public String getClauseStringFromIncludeSpace() {
        return FROM_INCLUDE_SPACE;
    }

    public String getClauseStringGroupBy() {
        return GROUP_BY;
    }

    public String getClauseStringHaving() {
        return HAVING;
    }

    public String getClauseStringIn() {
        return IN;
    }

    public String getClauseStringInsert() {
        return INSERT_INTO;
    }

    public String getClauseStringIs() {
        return IS;
    }

    public String getClauseStringLike() {
        return LIKE;
    }

    public String getClauseStringOrderBy() {
        return ORDER_BY;
    }

    public String getClauseStringSelect() {
        return SELECT;
    }

    public String getClauseStringSet() {
        return SET;
    }

    public String getClauseStringUpdate() {
        return UPDATE;
    }

    public String getClauseStringWhere() {
        return WHERE;
    }

    public String getClauseStringNot() {
        return NOT;
    }

    public String getClauseStringOr() {
        return OR;
    }

    public String getClauseStringValues() {
        return VALUES;
    }

    public String getClauseStringAVG() {
        return AVG;
    }

    public String getClauseStringSUM() {
        return SUM;
    }

    public String getClauseStringCOUNT() {
        return COUNT;
    }

    public String getClauseStringNULL() {
        return NULL;
    }

    public String getClauseStringMulMatch() {
        return MulMatch;
    }

    public String getClauseStringSingleMatch() {
        return SingleMatch;
    }

    public String getClauseStringMax() {
        return MAX;
    }

    public String getClauseStringMin() {
        return MIN;
    }

    /**
     * 将页面输入的数据转换成对应的Java类型
     *
     * @param aValue    要转换的字符数据
     * @param aDataType 字段的javaSqlType值
     * @param pattern   格式化
     * @return Object
     * @throws Exception
     */
    public Object convStringToObject(String aValue, int aDataType,
                                     String pattern) throws Exception {
        if (aDataType == Types.CHAR || aDataType == Types.VARCHAR
                || aDataType == Types.LONGVARCHAR) {
            if (aValue == null) {
                return null;
            }
        } else if (aValue == null || aValue.trim().equals("")) {
            return null;
        }

        switch (aDataType) {
            case Types.BIT: // -7
                return null;
            case Types.TINYINT: // -6
                return null;

            case Types.SMALLINT: // 5
                return BaseUtil.convertStringToNumber(aValue, aDataType, pattern);
            case Types.INTEGER: // 4
                return BaseUtil.convertStringToNumber(aValue, aDataType, pattern);
            case Types.BIGINT: // -5
                return BaseUtil.convertStringToNumber(aValue, aDataType, pattern);
            case Types.FLOAT: // 6
                return BaseUtil.convertStringToNumber(aValue, aDataType, pattern);
            case Types.REAL: // 7
                return BaseUtil.convertStringToNumber(aValue, aDataType, pattern);
            case Types.DOUBLE: // 8
                return BaseUtil.convertStringToNumber(aValue, aDataType, pattern);
            case Types.NUMERIC: // 2
                return BaseUtil.convertStringToNumber(aValue, aDataType, pattern);
            case Types.DECIMAL: // 3
                return BaseUtil.convertStringToNumber(aValue, aDataType, pattern);

            case Types.CHAR: // 1
                return convertVarchar(aValue, aDataType, pattern);
            case Types.VARCHAR: // 12
                return convertVarchar(aValue, aDataType, pattern);
            case Types.LONGVARCHAR: // -1
                return convertVarchar(aValue, aDataType, pattern);

            case Types.DATE: // 91
                return convertStringToDate(aValue, aDataType, pattern);
            case Types.TIME: // 92
                return convertStringToDate(aValue, aDataType, pattern);
            case Types.TIMESTAMP: // 93
                return convertStringToDate(aValue, aDataType, pattern);

            case Types.BINARY: // -2
                return null;
            case Types.VARBINARY: // -3
                return null;
            case Types.LONGVARBINARY: // -4
                return null;
            case Types.NULL: // 0
                return null;
            case Types.OTHER: // 1111
                return null;
            case Types.JAVA_OBJECT: // 2000
                return null;
            case Types.DISTINCT: // 2001
                return null;
            case Types.STRUCT: // 2002
                return null;
            case Types.ARRAY: // 2003
                return null;
            case Types.BLOB: // 2004
                return new BlobImpl(aValue.getBytes());
            case Types.CLOB: // 2005
                return new ClobImpl(aValue);
            case Types.REF: // 2006
                // return ;
            case Types.DATALINK: // 70
                return null;
            case Types.BOOLEAN: // 16
                if (aValue.equalsIgnoreCase("true") || aValue.equalsIgnoreCase("t")
                        || aValue.equalsIgnoreCase("1")) {
                    return new Boolean("true");
                }
                return new Boolean(aValue);
        }

        return null;
    }

    protected java.util.Date convertStringToDate(String aValueString,
                                                 int aDataType, String pattern) throws Exception {
        java.text.DateFormat df = null;
        java.util.Date date = null;
        try {
            switch (aDataType) {
                case Types.DATE:
                    if ((pattern == null) || (pattern.equals("")))
                        pattern = DATE_FORMAT;

                    df = new SimpleDateFormat(pattern);
                    date = df.parse(padDateString(aValueString, pattern));
                    return new java.sql.Date(date.getTime());
                case Types.TIME:
                    if ((pattern == null) || (pattern.equals("")))
                        pattern = TIME_FORMAT;

                    df = new SimpleDateFormat(pattern);
                    date = df.parse(padDateString(aValueString, pattern));
                    return new Time(date.getTime());
                case Types.TIMESTAMP:
                    if ((pattern == null) || (pattern.equals("")))
                        pattern = TIMESTAMP_FORMAT;

                    df = new SimpleDateFormat(pattern);
                    date = df.parse(padDateString(aValueString, pattern));
                    return new Timestamp(date.getTime());
            }
            df = new SimpleDateFormat(DATE_FORMAT);
            date = df.parse(padDateString(aValueString, DATE_FORMAT));
            return new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            throw new Exception(e.getMessage() + ":" + "aValueString = ["
                    + aValueString + "]; pattern = [" + pattern + "]");
        }
    }

    private String padDateString(String aValue, String pattern) {
        int valueLength = LENGTH_DATE;
        if (DATE_FORMAT.equals(pattern))
            valueLength = LENGTH_DATE;
        else if (TIME_FORMAT.equals(pattern))
            valueLength = LENGTH_TIME;
        else if (TIMESTAMP_FORMAT.equals(pattern))
            valueLength = LENGTH_TIMESTAMP;
        else
            return aValue;
        if (aValue.length() >= valueLength)
            return aValue.substring(0, valueLength);

        StringBuffer sb = new StringBuffer(aValue);
        for (int i = aValue.length(); i < valueLength; ++i)
            sb.append('0');
        return sb.toString();
    }

    private Object convertVarchar(String aValueString, int aDataType,
                                  String pattern) {
        Object obj = null;
        switch (aDataType) {
            case Types.CHAR: // 1
                obj = convertChar(aValueString);
                if (obj instanceof String)
                    return BaseUtil.formatString((String) obj, pattern);
                return obj;
            case Types.VARCHAR: // 12
                obj = convertVarchar(aValueString);
                if (obj instanceof String)
                    return BaseUtil.formatString((String) obj, pattern);
                return obj;
            case Types.LONGVARCHAR: // -1
                return convertLongVarchar(aValueString);
        }
        return obj;
    }

    protected Object convertChar(String aValue) {
        if (aValue.trim().equals(""))
            return null;
        return aValue;
    }

    protected abstract Object convertVarchar(String paramString);

    protected abstract Object convertLongVarchar(String paramString);

    public static RelationalDB getInstance() {
        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            return new InformixDataBase();
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            return new OracleDataBase();
        }
        return new OracleDataBase();
    }
}