package com.boco.eoms.km.core.util;

import org.w3c.dom.Element;
import com.boco.eoms.km.core.XMLTranslator;

/**
 * 基本操作定义
 *
 * @author zhangxb
 * @version 1.0
 */
public class Operate {

    /**
     * 定义基本操作编号
     */
    public static final int NOT_FIND = -1;
    public static final int INVALIDATE = 0;
    public static final int NOP = 0;
    public static final int NULL = 11;
    public static final int NOT_NULL = 1;
    public static final int EQUAL = 2;
    public static final int NOT_EQUAL = 3;
    public static final int LESSER = 4;
    public static final int LESSER_EQUAL = 5;
    public static final int GREATER = 6;
    public static final int GREATER_EQUAL = 7;
    public static final int LIKE = 8;
    public static final int BETWEEN = 9;
    public static final int MATCH = 10;
    public static final int JOIN = 20;

    /**
     * 定义基本操作名称
     */
    public static final String STR_NULL = "NULL";
    public static final String STR_NOT_NULL = "NOTNULL";
    public static final String STR_EQUAL = "=";
    public static final String STR_NOT_EQUAL = "!=";
    public static final String STR_AND = "&&";
    public static final String STR_OR = "||";
    public static final String STR_LEFT_BRACKET = "(";
    public static final String STR_RIGHT_BRACKET = ")";
    public static String STR_LESSER = "<";
    public static String STR_LESSER_EQUAL = "<=";
    public static String STR_GREATER = ">";
    public static String STR_GREATER_EQUAL = ">=";
    public static final String STR_LIKE = "LIKE";
    public static final String STR_BETWEEN = "BETWEEN";
    public static final String STR_MATCH = "MATCH";
    public static final String STR_INVALIDATE = "0";

    private static XMLTranslator xmlTranslator = new XMLTranslator();

    /**
     * 将操作名称转换成操作编号
     *
     * @param aStr 操作名称
     * @return 操作编号
     */
    public static int convertStringToInt(String aStr) {
        if (aStr == null)
            return EQUAL;// 2
        if (aStr.toUpperCase().equals("NULL"))
            return NULL; // 11
        if (aStr.toUpperCase().equals("NOTNULL"))
            return NOT_NULL;// 1
        if (aStr.toUpperCase().equals(STR_EQUAL))
            return EQUAL;// 2
        if (aStr.toUpperCase().equals(STR_NOT_EQUAL))
            return NOT_EQUAL;// 3
        if (aStr.toUpperCase().equals(STR_LESSER))
            return LESSER;// 4
        if (aStr.toUpperCase().equals(STR_LESSER_EQUAL))
            return LESSER_EQUAL;// 5
        if (aStr.toUpperCase().equals(STR_GREATER))
            return GREATER;// 6
        if (aStr.toUpperCase().equals(STR_GREATER_EQUAL))
            return GREATER_EQUAL;// 7
        if (aStr.toUpperCase().equals("LIKE"))
            return LIKE;// 8
        if (aStr.toUpperCase().equals("BETWEEN"))
            return BETWEEN;// 9
        if (aStr.toUpperCase().equals("MATCH"))
            return MATCH;// 10
        if (aStr.toUpperCase().equals("0"))
            return NOP;// 0
        return NOT_FIND;
    }

    /**
     * 将操作编号转换成操作名称
     *
     * @param aType 操作编号
     * @return 操作名称
     */
    public static String convertIntToString(int aType) {
        switch (aType) {
            case NULL: // 11
                return STR_NULL;
            case NOT_NULL: // 1
                return STR_NOT_NULL;
            case EQUAL: // 2
                return STR_EQUAL;
            case NOT_EQUAL: // 3
                return STR_NOT_EQUAL;
            case LESSER: // 4
                return STR_LESSER;
            case LESSER_EQUAL: // 5
                return STR_LESSER_EQUAL;
            case GREATER: // 6
                return STR_GREATER;
            case GREATER_EQUAL: // 7
                return STR_GREATER_EQUAL;
            case LIKE: // 8
                return STR_LIKE;
            case BETWEEN: // 9
                return STR_BETWEEN;
            case MATCH: // 10
                return STR_MATCH;
            case JOIN: // 20
                return null;
            case INVALIDATE: // 0
                return STR_INVALIDATE;
        }
        return null;
    }

    public static void setCriteria(Element entity, String fieldName,
                                   String ope, String opeValue) {
        if (entity == null) {
            return;
        }
        Element field = xmlTranslator.getLowElementByName(entity, fieldName);
        if (field == null) {
            xmlTranslator.setFieldValue(entity, fieldName, null);
            field = xmlTranslator.getLowElementByName(entity, fieldName);
        }
        if ((ope != null) && (!(ope.trim().equals("")))) {
            field.setAttribute("criteria", ope);
        }
        if ((opeValue != null) && (!(opeValue.trim().equals(""))))
            field.setAttribute("criteriaValue", opeValue);
    }

    public static void setEqualCriteria(Element entity, String fieldName,
                                        String opeValue) {
        setCriteria(entity, fieldName, "=", opeValue);
    }

    public static void setNotCriteria(Element entity, String fieldName) {
        setCriteria(entity, fieldName, "0", null);
    }
}