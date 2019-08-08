package com.boco.eoms.km.core.util;

import org.w3c.dom.Element;
import com.boco.eoms.km.core.XMLTranslator;

/**
 * 基本函数定义
 *
 * @author zhangxb
 * @version 1.0
 */
public class Function {

    /**
     * 定义基本函数编号
     */
    public static final int INVALIDATE = 0;
    public static final int ADD = 1;
    public static final int SUB = 2;
    public static final int INC = 3;
    public static final int DEC = 4;
    public static final int ZERO = 5;
    public static final int AVG = 6;
    public static final int SUM = 7;
    public static final int COUNT = 8;
    public static final int NOT_FUNCTION = 9;
    public static final int SET = 10;
    public static final int MAX = 11;
    public static final int MIN = 12;

    /**
     * 定义基本函数名称
     */
    public static final String STR_SET_EQUAL = "=";
    public static final String STR_SET = "SET";
    public static final String STR_ADD = "ADD";
    public static final String STR_SUB = "SUB";
    public static final String STR_INC = "INC";
    public static final String STR_DEC = "DEC";
    public static final String STR_ZERO = "ZERO";
    public static final String STR_AVG = "AVG";
    public static final String STR_SUM = "SUM";
    public static final String STR_COUNT = "COUNT";
    public static final String STR_MAX = "MAX";
    public static final String STR_MIN = "MIN";
    public static final String STR_NOT_FUNCTION = "NOP";
    public static final String STR_INVALIDATE = "0";

    private static XMLTranslator xmlTranslator = new XMLTranslator();

    /**
     * 将函数名称转换成函数编号
     *
     * @param aStr 函数名称
     * @return 函数编号
     */
    public static int convertStringToInt(String aStr) {
        if (aStr == null)
            return NOT_FUNCTION; // 9
        if (aStr.equalsIgnoreCase("="))
            return SET; // 10
        if (aStr.equalsIgnoreCase("ADD"))
            return ADD; // 1
        if (aStr.equalsIgnoreCase("SUB"))
            return SUB; // 2
        if (aStr.equalsIgnoreCase("INC"))
            return INC; // 3
        if (aStr.equalsIgnoreCase("DEC"))
            return DEC; // 4
        if (aStr.equalsIgnoreCase("ZERO"))
            return ZERO; // 5
        if (aStr.equalsIgnoreCase("AVG"))
            return AVG; // 6
        if (aStr.equalsIgnoreCase("SUM"))
            return SUM; // 7
        if (aStr.equalsIgnoreCase("COUNT"))
            return COUNT; // 8
        if (aStr.equalsIgnoreCase("NOP"))
            return NOT_FUNCTION; // 9
        if (aStr.equalsIgnoreCase("SET"))
            return SET; // 10
        if (aStr.equalsIgnoreCase("MAX"))
            return MAX; // 11
        if (aStr.equalsIgnoreCase("MIN"))
            return MIN; // 12
        if (aStr.equalsIgnoreCase("0"))
            return INVALIDATE;
        return -1;
    }

    /**
     * 将函数编号转换成函数名称
     *
     * @param aType 函数编号
     * @return 函数名称
     */
    public static String convertIntToString(int aType) {
        if (aType == SET) // 10
            return "SET";
        if (aType == ADD) // 1
            return "ADD";
        if (aType == SUB) // 2
            return "SUB";
        if (aType == INC) // 3
            return "INC";
        if (aType == DEC) // 4
            return "DEC";
        if (aType == ZERO) // 5
            return "ZERO";
        if (aType == AVG) // 6
            return "AVG";
        if (aType == SUM) // 7
            return "SUM";
        if (aType == COUNT) // 8
            return "COUNT";
        if (aType == NOT_FUNCTION) // 9
            return "NOP";
        if (aType == INVALIDATE) // 0
            return "0";
        return null;
    }

    public static void setFieldOpe(Element entity, String fieldName,
                                   String function, String functionValue) {
        if (entity == null)
            return;
        Element field = xmlTranslator.getLowElementByName(entity, fieldName);
        if (field == null) {
            xmlTranslator.setFieldValue(entity, fieldName, null);
            field = xmlTranslator.getLowElementByName(entity, fieldName);
        }
        if (function != null && !function.trim().equals("")) {
            field.setAttribute("field", function);
        }
        if (functionValue != null && !functionValue.trim().equals("")) {
            xmlTranslator.setElementValue(field, functionValue);
        }
    }

    public static void setDefaultUpdateField(Element entity, String fieldName,
                                             String opeValue) {
        setFieldOpe(entity, fieldName, "SET", opeValue);
    }

    public static void setNotFieldOpe(Element entity, String fieldName) {
        setFieldOpe(entity, fieldName, "0", null);
    }
}
