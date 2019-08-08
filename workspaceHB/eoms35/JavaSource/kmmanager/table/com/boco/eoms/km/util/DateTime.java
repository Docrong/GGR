package com.boco.eoms.km.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间函数类
 *
 * @author ZHANGXIAOBO
 * @version 1.0
 */
public class DateTime {

    private static String localPattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将给定的日期/时间字符串转换为 Date。
     *
     * @param strDate 日期-时间字符串，必须满足“yyyy-MM-dd HH:mm:ss”格式。
     * @return Date。
     * @throws ParseException
     * @author ZHANGXIAOBO
     */
    public static Date formatLocalToDate(String strDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(localPattern);
        Date date = dateFormat.parse(strDate);
        return date;
    }

    /**
     * 将给定的 Date 格式化为日期/时间字符串。
     *
     * @param date 要被格式化的日期-时间值。
     * @return 格式化的日期-时间字符串。
     * @author ZHANGXIAOBO
     */
    public static String formatDateToLocal(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(localPattern);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    /**
     * 将给定的 Date 格式化为日期/时间字符串。
     *
     * @param date    要被格式化的日期-时间值。
     * @param pattern 日期格式符号。
     * @return 格式化的日期-时间字符串。
     * @author ZHANGXIAOBO
     */
    public static String formatDateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    /**
     * 根据日历的规则，为给定的日期字段添加或减去指定的时间量。
     * 例如，要从当前日历时间减去 5 天，可以通过调用以下方法做到这一点： add(date, Calendar.DAY_OF_MONTH, -5)。
     *
     * @param date   基准日期
     * @param field  日历字段。
     * @param amount 为字段添加的日期或时间量。
     * @return 计算后的新日期
     */
    public static Date getDateAddAmount(Date date, int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);
        return cal.getTime();
    }

    /**
     * 计算某天(基准日期)所处年度的开始日期
     *
     * @param date 基准日期
     * @return
     */
    public static Date getYearFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, Calendar.JANUARY); // 一月
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * 计算某天(基准日期)所处季度的开始日期
     *
     * @param date 基准日期
     * @return
     */
    public static Date getQuarterFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        if (month < Calendar.APRIL) {
            cal.set(Calendar.MONTH, Calendar.JANUARY); // 一月
        } else if (month < Calendar.JULY) {
            cal.set(Calendar.MONTH, Calendar.APRIL); // 四月
        } else if (month < Calendar.OCTOBER) {
            cal.set(Calendar.MONTH, Calendar.JULY); // 七月
        } else {
            cal.set(Calendar.MONTH, Calendar.OCTOBER); // 十月
        }
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * 计算某天(基准日期)所处月份的开始日期
     *
     * @param date 基准日期
     * @return
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * 计算某天(基准日期)所处周的开始日期
     *
     * @param date 基准日期
     * @return
     */
    public static Date getWeekFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weekNum = cal.get(Calendar.WEEK_OF_YEAR);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * 计算某年某周的开始日期
     *
     * @param yearNum 某年
     * @param weekNum 某周
     * @return 某年某周的开始日期
     * @author ZHANGXIAOBO
     */
    public static Date getYearWeekFirstDay(int yearNum, int weekNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    public static void main(String args[]) {
        System.out.println("计算当前年度的第一天");
        System.out.println(formatDateToLocal(DateTime.getYearFirstDay(new Date())));

        System.out.println("计算当前季度的第一天");
        System.out.println(formatDateToLocal(DateTime.getQuarterFirstDay(new Date())));

        System.out.println("计算当前月份的第一天");
        System.out.println(formatDateToLocal(DateTime.getMonthFirstDay(new Date())));

        System.out.println("计算当前周的第一天");
        System.out.println(formatDateToLocal(DateTime.getWeekFirstDay(new Date())));

        System.out.println("2009年第39周的周一正确日期时间为：2009-09-21 00:00:00");
        System.out.println(formatDateToLocal(DateTime.getYearWeekFirstDay(2009, 39)));

    }
}
