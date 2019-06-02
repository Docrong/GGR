package com.boco.eoms.filemanager.topic;

/**
 * 共通函数
 * User: lxl
 * Date: 2003-9-4
 * Time: 16:29:30
 * To change this template use Options | File Templates.
 */

import java.util.Vector;


public class LBCommonFunc {
    /**
     * 得到数组中有几个不一样的值，数组中的值是排序的
     */
    public static String[] getRowSpan(String[] strArray) {
        String[] strReturn = null;
        try {
            Vector vNum = new Vector();
            if (strArray != null && strArray.length > 0) {
                int nLength = 0;
                String strTemp = strArray[0];
                for (int i = 0; i < strArray.length; i++) {
                    if (strTemp.equals(strArray[i])) {
                        nLength = nLength + 1;
                    } else {
                        strTemp = strArray[i];
                        vNum.addElement(Integer.toString(nLength));
                        nLength = 1;
                    }
                }
                vNum.addElement(Integer.toString(nLength));
            }
            strReturn = vector2StringArray(vNum);
        } catch (Exception e) {
            //System.out.println("getRowSpan"+e);
        }
        return strReturn;
    }

    /**
     * 格式数组生成Sql文所需要的Stirng 'a','b','c'
     *
     * @param strArray
     * @return
     */
    public static String strGetSqlParam(String[] strArray) {
        String strReturn = "";
        try {
            if (strArray != null && strArray.length > 0) {
                strReturn = "'";
                for (int i = 0; i < strArray.length; i++) {
                    strReturn = strReturn + strArray[i] + "','";
                }
                strReturn = strReturn.substring(0, strReturn.length() - 2);
            }
        } catch (Exception e) {
            //System.out.println("strGetSqlParam"+e);
        }
        return strReturn;
    }

    /**
     * vector to Stirng[]
     *
     * @param Vector
     * @return
     */
    public static String[] vector2StringArray(Vector vParam) {
        String[] strReturn = null;
        try {
            if (vParam != null && vParam.size() > 0) {
                int nMaxLength = vParam.size();
                strReturn = new String[nMaxLength];
                for (int i = 0; i < nMaxLength; i++) {
                    strReturn[i] = (String) vParam.get(i);
                }
            }
        } catch (Exception e) {
            //System.out.println("vector2StringArray"+e);
        }
        return strReturn;
    }

    /**
     * 向数组中加入一个值
     *
     * @param
     * @return
     */
    public static String[] add2Array(String[] strArray, String str) {
        String[] strReturn = null;
        try {
            if (strArray != null && strArray.length > 0) {
                int nMaxLength = strArray.length;
                strReturn = new String[nMaxLength + 1];
                int i = 0;
                for (i = 0; i < nMaxLength; i++) {
                    strReturn[i] = strArray[i];
                }
                strReturn[i] = str;
            } else {
                strReturn = new String[1];
                strReturn[0] = str;
            }

        } catch (Exception e) {
            //System.out.println("add2Array"+e);
        }
        return strReturn;
    }

    /**
     * 向数组中删除一个值
     *
     * @param
     * @return
     */
    public static String[] del2Array(String[] strArray1, String[] strArray2) {
        String[] strReturn = null;
        Vector vTemp = new Vector();
        try {
            if (strArray1 != null && strArray1.length > 0) {
                int nMaxLength = strArray1.length;
                for (int i = 0; i < nMaxLength; i++) {
                    vTemp.addElement(strArray1[i]);
                }
                if (strArray2 != null && strArray2.length > 0) {
                    for (int i = 0; i < strArray2.length; i++) {
                        int nIndex = vTemp.indexOf(strArray2[i]);
                        if (nIndex != -1) {
                            vTemp.remove(nIndex);
                        }
                    }
                }
                strReturn = vector2StringArray(vTemp);
            }
        } catch (Exception e) {
            //System.out.println("del2Array"+e);
        }
        return strReturn;
    }

    /**
     * 向数组中删除一个值,并且删除一组数组中同样的索引的值
     *
     * @param
     * @return
     */
    public static String[][] del2Array(String[] keyArray,
                                       String[] strArray1,
                                       String[] strArray2,
                                       String[] strArray3,
                                       String[] strArray4,
                                       String[] strArray5,
                                       String[] delArray) {
        String[][] strReturn = new String[6][];
        Vector vKey = new Vector();
        Vector vTemp1 = new Vector();
        Vector vTemp2 = new Vector();
        Vector vTemp3 = new Vector();
        Vector vTemp4 = new Vector();
        Vector vTemp5 = new Vector();
        //System.out.println("begin del2Array ");
        try {
            if (keyArray != null && keyArray.length > 0) {

                //System.out.println("begin del2Array if ");
                int nMaxLength = keyArray.length;
                //System.out.println("begin del2Array nMaxLength "+nMaxLength);
                for (int i = 0; i < nMaxLength; i++) {
                    //System.out.println("i="+i);
                    vKey.addElement(keyArray[i]);
                    vTemp1.addElement(strArray1[i]);
                    vTemp2.addElement(strArray2[i]);
                    vTemp3.addElement(strArray3[i]);
                    vTemp4.addElement(strArray4[i]);
                    vTemp5.addElement(strArray5[i]);
                }
                if (delArray != null && delArray.length > 0) {
                    for (int i = 0; i < delArray.length; i++) {
                        int nIndex = vKey.indexOf(delArray[i]);
                        //System.out.println("nIndex="+nIndex);
                        if (nIndex != -1) {
                            vKey.remove(nIndex);
                            vTemp1.remove(nIndex);
                            vTemp2.remove(nIndex);
                            vTemp3.remove(nIndex);
                            vTemp4.remove(nIndex);
                            vTemp5.remove(nIndex);
                        }
                    }
                }
                //System.out.println(vKey);
                //System.out.println(vTemp1);
                //System.out.println(vTemp2);
                //System.out.println(vTemp3);
                //System.out.println(vTemp4);
                strReturn[0] = vector2StringArray(vKey);
                strReturn[1] = vector2StringArray(vTemp1);
                strReturn[2] = vector2StringArray(vTemp2);
                strReturn[3] = vector2StringArray(vTemp3);
                strReturn[4] = vector2StringArray(vTemp4);
                strReturn[5] = vector2StringArray(vTemp5);
            }
        } catch (Exception e) {
            //System.out.println("del2Array"+e);
        }
        return strReturn;
    }

    /**
     * 下拉框
     *
     * @param strArray
     * @return
     */
    public static String getHtmlSelect(String[] strArray, String[] strArrayShow, String strName, String strValue) {
        StringBuffer strRetrunBuffer = new StringBuffer();
        try {
            if (strArray != null && strArray.length > 0 && strArrayShow != null && strArrayShow.length > 0 && strArrayShow.length == strArray.length) {
                if (strValue == null) {
                    strValue = "";
                }
                strRetrunBuffer.append("<select name= \"")
                        .append(strName)
                        .append("\"\n >");
                for (int i = 0; i < strArray.length; i++) {
                    String strChecked = "";
                    if (strArray[i].equals(strValue)) {
                        strChecked = "selected";
                    }
                    strRetrunBuffer.append(" <option value=\"")
                            .append(strArray[i])
                            .append("\" ")
                            .append(strChecked)
                            .append(" >\n")
                            .append(strArrayShow[i])
                            .append("</option>\n");

                }
                strRetrunBuffer.append(" </select>\n ");
            } else {
                //System.out.println("strHtmlSelect param error");
            }
        } catch (Exception e) {
            //System.out.println("strHtmlSelect"+e);
        }
        return strRetrunBuffer.toString();
    }

    /**
     * 下拉框 带有事件
     *
     * @param strArray
     * @return
     */
    public static String getHtmlSelect(String[] strArray, String[] strArrayShow, String strName, String strValue, String strOnChange) {
        StringBuffer strRetrunBuffer = new StringBuffer();
        try {
            if (strArray != null && strArray.length > 0 && strArrayShow != null && strArrayShow.length > 0 && strArrayShow.length == strArray.length) {
                strRetrunBuffer.append("<select name= \"")
                        .append(strName)
                        .append("\" >\n");
                for (int i = 0; i < strArray.length; i++) {
                    String strChecked = "";
                    if (strArray[i].equals(strValue)) {
                        strChecked = "selected";
                    }
                    strRetrunBuffer.append(" <option value=\"")
                            .append(strArray[i])
                            .append("\" ")
                            .append(strChecked)
                            .append(" > ")
                            .append(strArrayShow[i])
                            .append("</option>\n");

                }
                strRetrunBuffer.append(" </select>\n ");
            } else {
                //System.out.println("strHtmlSelect param error");
            }
        } catch (Exception e) {
            //System.out.println("strHtmlSelect"+e);
        }
        return strRetrunBuffer.toString();
    }

    /**
     * 转换字符  把null 和 "" conver to &nbsp;
     *
     * @param strArray
     * @return
     */
    public static String conver2nbsp(String str) {
        String strReturn = "";
        if (str != null && !str.equals("")) {
            strReturn = str;
        } else {
            strReturn = "&nbsp;";
        }
        return strReturn;
    }

    /**
     * 得到路径的最后一个文件夹
     *
     * @param str
     * @return String
     */
    public static String getPathFolder(String str, String strDiv) {
        String strReturn = str;
        if (str != null && !str.equals("")) {
            int nIndex = str.lastIndexOf(strDiv);
            if (nIndex > 0) {
                strReturn = str.substring(nIndex + 1);
            }
        } else {
            strReturn = "";
        }

        return strReturn;
    }

    /**
     * 得到路径的前面的路径
     *
     * @param str
     * @return String
     */
    public static String getForPathFolder(String str, String strDiv) {
        String strReturn = str;
        if (str != null && !str.equals("")) {
            int nIndex = str.lastIndexOf(strDiv);
            if (nIndex > 0) {
                strReturn = str.substring(0, nIndex + 1);
            }
        } else {
            strReturn = "";
        }

        return strReturn;
    }


}