//---------------------------------------------------------
// Application: E_OMS
// Author     : Jerry
// File       : Pager.java
//
// Copyright 2003 boco
// Generated at Thu Mar 27 10:15:57 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.common.util;

import java.util.*;

public class Pager {
    private static int MAX_PAGE_INDEX = 200;
    private static String HEADER = "Result page";
    static ResourceBundle prop = null;

    static {
        prop = ResourceBundle.getBundle("resources.application_zh_CN");
        try {
            HEADER = prop.getString("pager.header.title");
        } catch (Exception e) {
        }
        try {
            MAX_PAGE_INDEX = Integer.parseInt(prop.getString("pager.max.page.index"));
        } catch (Exception e) {
        }
    }

    public static String generateOld1(int offset, int length, int size, String url) {
        if (length > size) {
            String pref;
            if (url.indexOf("?") > -1) {
                pref = "&";
            } else {
                pref = "?";
            }
            String header = "<font face='Helvetica' size='-1'>" + HEADER + ": ";

            if (offset > 0) {
                header += "&nbsp;<a href=\"" + url + pref + "pager.offset=" + (offset - size) + "\">" +
                        prop.getString("label.prePage") + "</a>\n";
            }
            int start;
            int radius = MAX_PAGE_INDEX / 2 * size;
            if (offset < radius) {
                start = 0;
            } else if (offset < length - radius) {
                start = offset - radius;
            } else {
                start = (length / size - MAX_PAGE_INDEX) * size;
            }
            for (int i = start; i < length && i < start + MAX_PAGE_INDEX * size; i += size) {
                if (i == offset) {
                    header += "<b>" + (i / size + 1) + "</b>\n";
                } else {
                    header += "&nbsp;<a href=\"" + url + pref + "pager.offset=" + i + "\">" + (i / size + 1) + "</a>\n";
                }
            }
            if (offset < length - size) {
                header += "&nbsp;<a href=\"" + url + pref + "pager.offset=" + ((int) offset + (int) size) + "\">" +
                        prop.getString("label.nextPage") + "</a>\n";
            }
            header += "</font>";
            return header;
        } else {
            return "";
        }
    }

    public static String generate(int offset, int length, int size, String url, String param) {
        String ranName = getRandom();
        String pref1 = "&";
        String pref;
        if (url.indexOf("?") > -1) {
            pref = "&";
        } else {
            pref = "?";
        }

        int pageNum = 0;
        int pageNo = offset / size + 1;

        if (length % size == 0) {
            pageNum = length / size;
        } else {
            pageNum = length / size + 1;
        }

        StringBuffer header = new StringBuffer();
        header.append(HEADER + ": ");

        header.append(prop.getString("taw.total") + "&nbsp;");
        header.append(String.valueOf(length) + "&nbsp;");
        header.append(prop.getString("taw.piece") + "&nbsp;");
        header.append(String.valueOf(pageNum) + "&nbsp;");
        header.append(prop.getString("taw.pageDescription") + "&nbsp;");
        header.append("<select name=\"page" + ranName + "\" onchange=\"goPage" + ranName + "();\">");

        for (int i = 1; i <= pageNum; i++) {
            header.append("<option value=\'" + (i - 1) * size + "\'");
            if (pageNo == i)
                header.append(" selected");
            header.append(">" + i + "</option>");
        }

        header.append("</select>" + prop.getString("taw.page") + "&nbsp;&nbsp;");

        if (offset > 0) {
            header.append("&nbsp;<a href=\"" + url + pref + param + pref1 + "pager.size=" + length + "&pager.offset=" + (offset - size) + "\">" +
                    prop.getString("label.prePage") + "</a>\n");
        }
        int start;
        int radius = MAX_PAGE_INDEX / 2 * size;
        if (offset < radius) {
            start = 0;
        } else if (offset < length - radius) {
            start = offset - radius;
        } else {
            start = (length / size - MAX_PAGE_INDEX) * size;
        }

        if (offset < length - size) {
            header.append("&nbsp;<a href=\"" + url + pref + param + pref1 + "pager.size=" + length + "&pager.offset=" + ((int) offset + (int) size) + "\">" +
                    prop.getString("label.nextPage") + "</a>\n");
        }

        header.append("\n");

        header.append("<script language=\"javascript\">\n");
        header.append("<!--\n");
        header.append("function goPage" + ranName + "()\n{window.location=\"" + url + pref + param + pref1 + "pager.size=" + length + "&pager.offset=\"" + " + document.all.page" + ranName + ".value;}\n");
        header.append("-->\n");
        header.append("</script>");

        return header.toString();
    }

    public static String generateSubmit(int offset, int length, int size, String url,
                                        String param, String formName) {
        String ranName = getRandom();
        String pref1 = "&";
        String pref;
        if (url.indexOf("?") > -1) {
            pref = "&";
        } else {
            pref = "?";
        }

        int pageNum = 0;
        int pageNo = offset / size + 1;

        if (length % size == 0) {
            pageNum = length / size;
        } else {
            pageNum = length / size + 1;
        }

        StringBuffer header = new StringBuffer();
        header.append(HEADER + ": ");

        header.append(prop.getString("taw.total") + "&nbsp;");
        header.append(String.valueOf(length) + "&nbsp;");
        header.append(prop.getString("taw.piece") + "&nbsp;");
        header.append(String.valueOf(pageNum) + "&nbsp;");
        header.append(prop.getString("taw.pageDescription") + "&nbsp;");
        header.append("<select name=\"page" + ranName + "\" onchange=\"goPage" + ranName + "();\">");

        for (int i = 1; i <= pageNum; i++) {
            header.append("<option value=\'" + (i - 1) * size + "\'");
            if (pageNo == i)
                header.append(" selected");
            header.append(">" + i + "</option>");
        }

        header.append("</select>" + prop.getString("taw.page") + "&nbsp;&nbsp;");

/*
    if (offset > 0) {
      header.append("&nbsp;<a href=\""+url+pref+param+pref1+"pager.offset="+(offset-size)+"\">"+
                 prop.getString("label.prePage")+"</a>\n");
    }
*/

        int start;
        int radius = MAX_PAGE_INDEX / 2 * size;
        if (offset < radius) {
            start = 0;
        } else if (offset < length - radius) {
            start = offset - radius;
        } else {
            start = (length / size - MAX_PAGE_INDEX) * size;
        }

/*
    if(offset < length - size) {
      header.append("&nbsp;<a href=\""+url+pref+param+pref1+"pager.offset="+((int)offset+(int)size)+"\">"+
                prop.getString("label.nextPage")+"</a>\n");
    }
*/
        header.append("\n");

        header.append("<script language=\"javascript\">\n");
        header.append("<!--\n");

    /*
    document.form1.action="TawRole/saveOperSel.do";
	document.form1.submit();
    */
        header.append("function goPage" + ranName + "()\n");
        header.append("{");

        header.append("document." + formName + ".action=\"" + url + pref + param + pref1 + "pager.offset=\"" + " + document.all.page" + ranName + ".value;\n");
        header.append("document." + formName + ".submit();\n");
        //eader.append("window.location=\""+url + pref +param+pref1+"pager.offset=\""+" + document.all.page.value;");
        header.append("}\n");
        header.append("-->\n");
        header.append("</script>");

        return header.toString();
    }

    public static String generate(int offset, int length, int size, String url) {
        String ranName = getRandom();
        String pref1 = "&";
        String pref;

        String param = "page.size" + length;

        if (url.indexOf("?") > -1) {
            pref = "&";
        } else {
            pref = "?";
        }

        int pageNum = 0;
        int pageNo = offset / size + 1;

        if (length % size == 0) {
            pageNum = length / size;
        } else {
            pageNum = length / size + 1;
        }

        StringBuffer header = new StringBuffer();
        header.append(HEADER + ": ");

        header.append(prop.getString("taw.total") + "&nbsp;");
        header.append(String.valueOf(length) + "&nbsp;");
        header.append(prop.getString("taw.piece") + "&nbsp;");
        header.append(String.valueOf(pageNum) + "&nbsp;");
        header.append(prop.getString("taw.pageDescription") + "&nbsp;");
        header.append("<select name=\"page" + ranName + "\" onchange=\"goPage" + ranName + "();\">");

        for (int i = 1; i <= pageNum; i++) {
            header.append("<option value=\'" + (i - 1) * size + "\'");
            if (pageNo == i)
                header.append(" selected");
            header.append(">" + i + "</option>");
        }

        header.append("</select>" + prop.getString("taw.page") + "&nbsp;&nbsp;");

        if (offset > 0) {
            header.append("&nbsp;<a href=\"" + url + pref + param + pref1 + "pager.size=" + length + "&pager.offset=" + (offset - size) + "\">" +
                    prop.getString("label.prePage") + "</a>\n");
        }
        int start;
        int radius = MAX_PAGE_INDEX / 2 * size;
        if (offset < radius) {
            start = 0;
        } else if (offset < length - radius) {
            start = offset - radius;
        } else {
            start = (length / size - MAX_PAGE_INDEX) * size;
        }

        if (offset < length - size) {
            header.append("&nbsp;<a href=\"" + url + pref + param + pref1 + "pager.size=" + length + "&pager.offset=" + ((int) offset + (int) size) + "\">" +
                    prop.getString("label.nextPage") + "</a>\n");
        }

        header.append("\n");

        header.append("<script language=\"javascript\">\n");
        header.append("<!--\n");
        header.append("function goPage" + ranName + "()\n{window.location=\"" + url + pref + param + pref1 + "pager.size=" + length + "&pager.offset=\"" + " + document.all.page" + ranName + ".value;}\n");
        header.append("-->\n");
        header.append("</script>");

        return header.toString();

    }

    public static String generateOld2(int offset, int length, int size, String url, String param) {
        String ranName = getRandom();
        String pref1 = "&";
        if (length > size) {
            String pref;
            if (url.indexOf("?") > -1) {
                pref = "&";
            } else {
                pref = "?";
            }
            String header = "<font face='Helvetica' size='-1'>" + HEADER + ": ";
            if (offset > 0) {
                header += "&nbsp;<a href=\"" + url + pref + "pager.offset=" + (offset - size) + pref1 + param + "\">" +
                        prop.getString("label.prePage") + "</a>\n";
            }
            int start;
            int radius = MAX_PAGE_INDEX / 2 * size;
            if (offset < radius) {
                start = 0;
            } else if (offset < length - radius) {
                start = offset - radius;
            } else {
                start = (length / size - MAX_PAGE_INDEX) * size;
            }
            for (int i = start; i < length && i < start + MAX_PAGE_INDEX * size; i += size) {
                if (i == offset) {
                    header += "<b>" + (i / size + 1) + "</b>\n";
                } else {
                    header += "&nbsp;<a href=\"" + url + pref + "pager.offset=" + i + pref1 + param + "\">" + (i / size + 1) + "</a>\n";
                }
            }
            if (offset < length - size) {
                header += "&nbsp;<a href=\"" + url + pref + "pager.offset=" + ((int) offset + (int) size) + pref1 + param + "\">" +
                        prop.getString("label.nextPage") + "</a>\n";
            }
            header += "</font>";
            return header;
        } else {
            return "";
        }
    }

    public static String generateAll(int offset, int length, int size, String url) {
        String ranName = getRandom();
        String pref1 = "&";
        String pref;

        String param = "page.size" + length;

        if (url.indexOf("?") > -1) {
            pref = "&";
        } else {
            pref = "?";
        }

        int pageNum = 0;
        int pageNo = offset / size + 1;

        if (length % size == 0) {
            pageNum = length / size;
        } else {
            pageNum = length / size + 1;
        }

        StringBuffer header = new StringBuffer();
        header.append(HEADER + ": ");

        header.append(prop.getString("taw.total") + "&nbsp;");
        header.append(String.valueOf(length) + "&nbsp;");
        header.append(prop.getString("taw.piece") + "&nbsp;");
        header.append(String.valueOf(pageNum) + "&nbsp;");
        header.append(prop.getString("taw.pageDescription") + "&nbsp;");
        header.append("<select name=\"pageAll" + ranName + "\" onchange=\"goPageAll" + ranName + "();\">");

        for (int i = 1; i <= pageNum; i++) {
            header.append("<option value=\'" + (i - 1) * size + "\'");
            if (pageNo == i)
                header.append(" selected");
            header.append(">" + i + "</option>");
        }

        header.append("</select>" + prop.getString("taw.page") + "&nbsp;&nbsp;");

        if (offset > 0) {
            header.append("&nbsp;<a href=\"" + url + pref + param + pref1 + "pager.size=" + length + "&pager.offset=" + (offset - size) + "\">" +
                    prop.getString("label.prePage") + "</a>\n");
        }
        int start;
        int radius = MAX_PAGE_INDEX / 2 * size;
        if (offset < radius) {
            start = 0;
        } else if (offset < length - radius) {
            start = offset - radius;
        } else {
            start = (length / size - MAX_PAGE_INDEX) * size;
        }

        if (offset < length - size) {
            header.append("&nbsp;<a href=\"" + url + pref + param + pref1 + "pager.size=" + length + "&pager.offset=" + ((int) offset + (int) size) + "\">" +
                    prop.getString("label.nextPage") + "</a>\n");
        }

        header.append("\n");

        header.append("<script language=\"javascript\">\n");
        header.append("<!--\n");
        header.append("function goPageAll" + ranName + "()\n{window.location=\"" + url + pref + param + pref1 + "pager.size=" + length + "&pager.offset=\"" + " + document.all.pageAll" + ranName + ".value;}\n");
        header.append("-->\n");
        header.append("</script>");

        return header.toString();

    }

    /**
     * 避免首页定制goPage方面名称重复.随机字符串通过两个int的随机数组合而成.
     * getRandom
     *
     * @return String
     * @throws Exception
     */
    public static String getRandom() {
        String ranStr = "";
        try {
            Random random = new Random();
            ranStr = "" + Math.abs(random.nextInt()) + Math.abs(random.nextInt());
        } catch (Exception e) {
            System.out.println(e);
            ranStr = "";
        }
        return ranStr;
    }
}
