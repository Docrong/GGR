package com.boco.eoms.km.statics.util;

/**
 * <p>
 * Title:部门知识贡献情况
 * </p>
 * <p>
 * Description:部门知识贡献情况
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class DeptContributeStatisticConstants {

    /**
     * list key
     */
    public final static String DEPTCONTRIBUTESTATISTIC_LIST = "deptContributeStatisticList";

    public static void main(String[] args) {
        String inStr = "操作积分定义表";
        char temChr;
        int ascInt;
        int i;
        String result = new String("");
        if (inStr == null) {
            inStr = "";
        }
        for (i = 0; i < inStr.length(); i++) {
            temChr = inStr.charAt(i);
            ascInt = temChr + 0;
            result = result + "\\u" + Integer.toHexString(ascInt);

        }
        System.out.println(result);
    }
}