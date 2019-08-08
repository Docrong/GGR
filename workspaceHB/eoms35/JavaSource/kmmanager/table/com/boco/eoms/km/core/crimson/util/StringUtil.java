package com.boco.eoms.km.core.crimson.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringUtil {
    public static final String[] split(String srcStr, String delim) {
        StringTokenizer st = new StringTokenizer(srcStr, delim);
        ArrayList al = new ArrayList();
        while (st.hasMoreTokens())
            al.add(st.nextToken());

        String[] sa = new String[al.size()];
        al.toArray(sa);
        return sa;
    }

    public static final String regionMatch(String strLeft, String strRight) {
        if ((strLeft == null) || (strRight == null))
            return null;
        char[] chLefts = strLeft.toCharArray();
        char[] chRights = strRight.toCharArray();
        int len = (chLefts.length < chRights.length) ? chLefts.length
                : chRights.length;
        int i = 0;
        for (; i < len; ++i)
            if (chLefts[i] != chRights[i])
                break;

        if (i == 0)
            return null;
        return strLeft.substring(0, i);
    }

    public static final String replace(String str, String s1, String s2) {
        String ret = "";
        int i;
        while ((i = str.indexOf(s1)) >= 0) {
            ret = ret + str.substring(0, i);
            ret = ret + s2;
            str = str.substring(i + s1.length());
        }
        ret = ret + str;
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(regionMatch("root/data/info", "root/data/"));
    }
}