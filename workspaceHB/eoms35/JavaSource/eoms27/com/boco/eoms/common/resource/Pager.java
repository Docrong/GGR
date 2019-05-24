package com.boco.eoms.common.resource;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-3-30
 * Time: 20:03:12
 * To change this template use File | Settings | File Templates.
 */
public class Pager {
    private static int MAX_PAGE_INDEX = 200;
    private static String HEADER = "结果";

    public static String generate(int offset, int length, int size, String url) {

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

        header.append("共" + "&nbsp;");
        header.append(String.valueOf(length) + "&nbsp;");
        header.append("条" + "&nbsp;");
        header.append(String.valueOf(pageNum) + "&nbsp;");
        header.append("页记录 当前在第" + "&nbsp;");
        header.append("<select name=\"page\" onchange=\"goPage();\">");

        for (int i = 1; i <= pageNum; i++) {
            header.append("<option value=\'" + (i - 1) * size + "\'");
            if (pageNo == i)
                header.append(" selected");
            header.append(">" + i + "</option>");
        }

        header.append("</select>" + "页" + "&nbsp;&nbsp;");

        if (offset > 0) {
            header.append("&nbsp;<a href=\"" + url + pref + "pager.offset=" + (offset - size) + "\">" +
                    "上一页" + "</a>\n");
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
            header.append("&nbsp;<a href=\"" + url + pref + "pager.offset=" + ((int) offset + (int) size) + "\">" +
                    "下一页" + "</a>\n");
        }

        header.append("\n");

        header.append("<script language=\"javascript\">\n");
        header.append("<!--\n");
        header.append("function goPage()\n{window.location=\"" + url + pref + "pager.offset=\"" + " + document.all.page.value;}\n");
        header.append("-->\n");
        header.append("</script>");

        return header.toString();
    }
}