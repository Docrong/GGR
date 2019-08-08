//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : Pager.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.util;

import java.util.*;

public class Pager {
    private static int MAX_PAGE_INDEX = 15;
    private static String HEADER = "Result page";

    static {
        ResourceBundle prop = ResourceBundle.getBundle("resources.application");
        try {
            HEADER = prop.getString("pager.header.title");
        } catch (Exception e) {
        }
        try {
            MAX_PAGE_INDEX = Integer.parseInt(prop.getString("pager.max.page.index"));
        } catch (Exception e) {
        }
    }

    public static String generate(int offset, int length, int size, String url) {
        if (length > size) {
            String pref;
            if (url.indexOf("?") > -1) {
                pref = "&";
            } else {
                pref = "?";
            }
            String header = "<font face='Helvetica' size='-1'>" + HEADER + ": ";
            if (offset > 0) {
                header += "&nbsp;<a href=\"" + url + pref + "pager.offset=" + (offset - size) + "\">[<< Prev]</a>\n";
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
                header += "&nbsp;<a href=\"" + url + pref + "pager.offset=" + ((int) offset + (int) size) + "\">[Next >>]</a>\n";
            }
            header += "</font>";
            return header;
        } else {
            return "";
        }
    }

}
