package com.boco.eoms.common.resource;

import java.util.Properties;
import java.util.Collection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-7
 * Time: 16:33:39
 * To change this template use File | Settings | File Templates.
 */
public class TransRs2Properties {

    public static Properties transRs2Properties(ResultSet rs) {
        String theName = "", theValue = "";
        Properties p = new Properties();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                theName = rsmd.getColumnName(i + 1);
                if (rsmd.getColumnType(i + 1) == 93) {  //It is a date
                    Timestamp date = rs.getTimestamp(i + 1);
                    if (date == null) {
                        theValue = "";
                    } else {
                        java.util.Date theDate = new java.util.Date(date.getTime());
                        theValue = DateUtil.getDateString(theDate, "yyyy-MM-dd HH:mm");
                    }
                } else {
                    theValue = rs.getString(i + 1);
                }
                if (Util.isNull(theValue)) theValue = "";
                p.put(theName, theValue.trim());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        } finally {
            return p;
        }
    }

    public static void toString(Properties p) {

        Collection c = p.values();
        Object[] o = c.toArray();
        /*for(int i =0;i<o.length;i++){
            System.out.println(i+"::"+o[i].toString());
        }*/
    }
}
