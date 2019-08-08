package com.boco.eoms.km.statics.util;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StatisticMethod {

    public static String objectToString(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof java.lang.String) {
            return (String) obj;
        }
        return obj.toString();
    }

    public static Integer objectToInteger(Object obj) {
        if (obj == null) {
            new Integer(0);
        } else if (obj instanceof java.lang.Integer) {
            return (Integer) obj;
        } else if (obj instanceof java.math.BigDecimal) {
            BigDecimal big = (BigDecimal) obj;
            return new Integer(big.intValue());
        }

        return new Integer(0);
    }

    public static Date countDate(Date inDate, int subDate) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(inDate);
        rightNow.add(Calendar.DATE, subDate);//你要加减的日期
        return rightNow.getTime();
    }

    /**
     * @param strDate
     * @return
     */
    public static Date stringToDate(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * @param strDate
     * @return
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String nowTime = dateFormat.format(date);
        return nowTime;
    }
}
