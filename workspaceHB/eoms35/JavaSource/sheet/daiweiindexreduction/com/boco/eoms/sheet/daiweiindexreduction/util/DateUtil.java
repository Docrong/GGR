package com.boco.eoms.sheet.daiweiindexreduction.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.boco.eoms.commons.util.xml.XmlManage;

public class DateUtil {
    private static String isWeekend = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekend");
    private static String isWeekday = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekday");
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 校验是否符合
     *
     * @param src
     * @return
     * @throws ParseException
     */
    private static boolean checkHoliday(Calendar cal) {
        //.getTime();
        String nowtime = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
        boolean result = false;
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("isWeekend=" + isWeekend);
        System.out.println("nowtime=" + nowtime);
        if (isWeekend.contains(nowtime)) { /* 周中是放假的时间 */
            System.out.println("当前日期：" + nowtime + ",不上班!");
            return true;
        }
        if ((week_index == 0 || week_index == 6) && !isWeekday.contains(nowtime)) { /*是周六或周末 并不在周末上班日期中 不合法*/
            result = true;
        }
        return result;

    }

    /**
     * *判断当前日期如果是节假日往后推到最近的工作日0点0分
     *
     * @param date
     * @return
     */
    public static Date getWorkDate(Date date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            cal.setTime(sdf1.parse(sdf.format(date)));// 获得当前时间
            System.out.println("11111111111111111111111：" + sdf.format(date));
            if (checkHoliday(cal)) {
                cal.add(Calendar.DAY_OF_YEAR, 1);
                return getWorkDate(cal.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
