package com.boco.eoms.common.resource;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-6
 * Time: 18:13:05
 * To change this template use File | Settings | File Templates.
 */

public class StrTrans {

    public StrTrans() {
    }

    public static String replaceStr(String srcStr, String oldStr, String newStr) {
        String leftStr, rightStr;
        int leftDot, rightDot;
        if (Util.isNull(srcStr)) {
            srcStr = "";
        } else {
            leftDot = srcStr.indexOf(oldStr);
            while (leftDot >= 0) {
                leftStr = srcStr.substring(0, leftDot);
                rightStr = srcStr.substring(leftDot + oldStr.length());
                srcStr = leftStr + newStr + rightStr;
                leftDot = srcStr.indexOf(oldStr, leftDot + 1);
            }
        }
        return srcStr;
    }

    public static String transXml(String srcStr) {
        return srcStr.replaceAll("&#13;&#10;", "\r\n")
                .replaceAll("&#10;&#13;", "\r\n")
                .replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&#34;")
                .replaceAll("\'", "&#39;");

    }

    public static String transXml1(String srcStr) {
        return srcStr.replaceAll("&#13;&#10;", "<br>")
                .replaceAll("&#10;&#13;", "<br>")
                .replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\r\n", "&lt;br&gt;")
                .replaceAll("\n\r", "&lt;br&gt;")
                .replaceAll("\n", "&lt;br&gt;")
                .replaceAll("\"", "&#92;&#34;")
                .replaceAll("\\\\", "&#92;&#92;");
    }

    public static String repalceFromHtml(String ValStr) {
        String tmpString = "";
        if (ValStr != null) {
            try {
                tmpString = replaceStr(ValStr, "&#43;", "+");
                tmpString = tmpString.replaceAll("&#34;", "\"")
                        .replaceAll("&#39;", "\'")
                        .replaceAll("&#13;", "\r")
                        .replaceAll("&#10;", "\n")
                        .replaceAll("<BR>", "\r\n")
                        .replaceAll("&nbsp;", " ")
                        .replaceAll("&gt;", ">")
                        .replaceAll("&lt;", "<")
                        .replaceAll("&amp;", "&");
            } catch (Exception e) {
                System.out.print(Util.UNI2GBK(e.getMessage()));
            }
        }
        return tmpString;

    }

    public static String repalceToHtml(String ValStr) {
        String tmpString = "";
        if (ValStr != null) {
            try {
                tmpString = ValStr.replaceAll("&", "&amp;")
                        .replaceAll("<", "&lt;")
                        .replaceAll(">", "&gt;")
                        .replaceAll(" ", "&nbsp;")
                        .replaceAll("\r\n", "<BR>")
                        .replaceAll("\n", "&#10;")
                        .replaceAll("\r", "&#13;")
                        .replaceAll("\'", "&#39;")
                        .replaceAll("\"", "&#34;");
                tmpString = replaceStr(tmpString, "+", "&#43;");
            } catch (Exception e) {
                System.out.print(Util.UNI2GBK(e.getMessage()));
            }
        }
        return tmpString;
    }

    public static String repalceFromHtc(String ValStr) {
        String tmpString = "";
        if (ValStr != null) {
            try {
                tmpString = replaceStr(ValStr, "&#43;", "+");
                tmpString = tmpString.replaceAll("&#34;", "\"")
                        .replaceAll("&#39;", "\'")
                        .replaceAll("&#13;", "\r")
                        .replaceAll("&#10;", "\n")
                        .replaceAll("&gt;", ">")
                        .replaceAll("&lt;", "<")
                        .replaceAll("&amp;", "&");
            } catch (Exception e) {
                System.out.print(Util.UNI2GBK(e.getMessage()));
            }
        }
        return tmpString;

    }

    public static String repalceToHtc(String ValStr) {
        String tmpString = "";
        if (ValStr != null) {
            try {
                tmpString = ValStr.replaceAll("&", "&amp;")
                        .replaceAll("<", "&lt;")
                        .replaceAll(">", "&gt;")
                        .replaceAll("\n", "&#10;")
                        .replaceAll("\r", "&#13;")
                        .replaceAll("\'", "&#39;")
                        .replaceAll("\"", "&#34;");
                tmpString = replaceStr(tmpString, "+", "&#43;");
            } catch (Exception e) {
                System.out.print(Util.UNI2GBK(e.getMessage()));
            }
        }
        return tmpString;
    }

    public static String repalceFromDB(String DBStr) {
        String tmpString = "";
        if (DBStr != null) {
            try {
                tmpString = DBStr.replaceAll("''", "'")
                        .replaceAll("&#13;", "\r")
                        .replaceAll("&#10;", "\n")
                        .replaceAll("&#9;", "\t")
                        .replaceAll("&#127;", "");
            } catch (Exception e) {
                System.out.print(Util.UNI2GBK(e.getMessage()));
            }
        }
        return tmpString;

    }

    public static String repalceToDB(String DBStr) {
        String tmpString = "";
        if (DBStr != null) {
            try {
                tmpString = DBStr.replaceAll("'", "''")
                        .replaceAll("\r", "&#13;")
                        .replaceAll("\n", "&#10;")
                        .replaceAll("\t", "&#9;")
                        .replaceAll("", "&#127;");
            } catch (Exception e) {
                System.out.print(Util.UNI2GBK(e.getMessage()));
            }
        }
        return tmpString;
    }
}
