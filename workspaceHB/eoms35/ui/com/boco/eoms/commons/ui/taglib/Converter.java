package com.boco.eoms.commons.ui.taglib;
/**
 *
 * <p>Title: </p>
 *
 * <p>Description: convert charset code </p>
 *
 *
 * @author mios
 * @version 1.0
 */
public class Converter {
 
    public static String Ascii2utf(String s) {
        String str = new String();
        try {
            str = new String(s.getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return str;
    }
 
}
