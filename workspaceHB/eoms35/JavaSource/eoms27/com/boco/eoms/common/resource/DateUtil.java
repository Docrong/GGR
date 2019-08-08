package com.boco.eoms.common.resource;

import org.apache.log4j.Category;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.text.DateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-6
 * Time: 18:12:25
 * To change this template use File | Settings | File Templates.
 */

/**
 *  The contents of this file are subject to the Mozilla Public
 *  License Version 1.1 (the "License"); you may not use this file
 *  except in compliance with the License. You may obtain a copy of
 *  the License at http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an "AS
 *  IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 *  implied. See the License for the specific language governing
 *  rights and limitations under the License.
 *
 *  The Original Code is pow2toolkit library.
 *
 *  The Initial Owner of the Original Code is
 *  Power Of Two S.R.L. (www.pow2.com)
 *
 *  Portions created by Power Of Two S.R.L. are
 *  Copyright (C) Power Of Two S.R.L.
 *  All Rights Reserved.
 *
 * Contributor(s):
 */

import org.apache.log4j.Category;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Locale;
import java.text.DateFormat;

/**
 * Date utility class.
 *
 * @author Luca Fossato
 */
public class DateUtil {
    /**
     * Log4j category.
     */
    private static Category cat = Category.getInstance(DateUtil.class);


    /**
     * Get the current date string representation.
     *
     * @param dateFormat the input dateFormat.
     *                   See the <code>java.text.SimpleDateFormat</code> API for date format
     *                   string examples
     */
    public static String getCurrentDateString(String dateFormat) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(cal.getTime());
    }


    /**
     * Get the string representation of the input Date object
     *
     * @param date       the input Date object
     * @param dateFormat a date format string like "dd/MM/yyyy"
     * @return the string representation of the input Date object
     */
    public static String getDateString(Date date, String dateFormat) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(date);
        } else {
            return null;
        }
    }


    /**
     * Get a java Date object from an input date string representation.
     * <br>
     * See the <code>java.text.SimpleDateFormat</code> API for date format string
     * examples.
     *
     * @param sDate      the date string representation
     * @param dateFormat a date format string like "dd/MM/yyyy"
     * @return the Date object corresponding to the input date string,
     * or null if the conversion fails
     */
    public static Date getDate(String sDate, String dateFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        ParsePosition pos = new ParsePosition(0);

        return fmt.parse(sDate, pos);
    }


    /**
     * Add the input number of days to the startDate string representation.
     *
     * @param startDate  the start date string representation
     * @param dateFormat the start date format
     * @param days       the number of days to add to the startDate
     * @return the Date object representing the resulting date
     */
    public static Date addDays(String startDate, String dateFormat, int days) {
        return addDays(getDate(startDate, dateFormat), days);
    }


    /**
     * Add the input number of days to the start Date object.
     *
     * @param startDate the start Date object
     * @param days      the number of days to add to the startDate object
     * @return the Date object representing the resulting date
     */
    public static Date addDays(Date startDate, int days) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(Calendar.DATE, days);

        return gCal.getTime();
    }

    public static Date addWeeks(Date startDate, int weeks) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(Calendar.WEEK_OF_YEAR, weeks);

        return gCal.getTime();
    }

    public static Date addMonths(Date startDate, int months) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(Calendar.MONTH, months);

        return gCal.getTime();
    }

    /**
     * Add the input number of days to the start Date object.
     *
     * @param startDate the start Date object
     * @param hours     the number of days to add to the startDate object
     * @return the Date object representing the resulting date
     */
    public static Date addHours(Date startDate, int hours) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(Calendar.HOUR, hours);

        return gCal.getTime();
    }

    /**
     * Add the input number of days to the start Date object.
     *
     * @param startDate  the start Date object
     * @param hours      the number of hours to add to the startDate object
     * @param dateFormat the start date format
     * @return the Date object representing the resulting date
     */
    public static Date addHours(String startDate, String dateFormat, int hours) {
        return addHours(getDate(startDate, dateFormat), hours);
    }

    /**
     * Add the input number of days to the start Date object.
     *
     * @param startDate   the start Date object
     * @param hours       the number of hours to add to the startDate object
     * @param dateFormat1 the start date format
     * @param dateFormat2 the end date format
     * @return the Date object representing the resulting date
     */
    public static String addHours(String startDate, String dateFormat1, int hours, String dateFormat2) {
//         return  getDateString(addDays(getDate(startDate, dateFormat1), hours),dateFormat2);

        return getDateString(addHours(getDate(startDate, dateFormat1), hours), dateFormat2);
    }

    /**
     * Check if the <code>d</code> input date is between <code>d1</code> and
     * <code>d2</code>.
     *
     * @param d  the date to check
     * @param d1 the lower boundary date
     * @param d2 the upper boundary date
     * @return true if d1 <= d <= d2, false otherwise
     */
    public static boolean isDateBetween(Date d, Date d1, Date d2) {
        return ((d1.before(d) || d1.equals(d)) &&
                (d.before(d2) || d.equals(d2)));
    }

    public static String getCurrentTime() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    public static String getCurrentDate() {
        return DateFormat.getDateInstance().format(new Date());
    }

    public static String formatDate(Date theDate) {
        Locale locale = Locale.CHINA;
        String dateString = "";
        try {
            Calendar cal = Calendar.getInstance(locale);
            cal.setFirstDayOfWeek(Calendar.TUESDAY);
            cal.setTime((Date) theDate);

            //DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.MEDIUM,locale);
            java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
            dateString = dateFormatter.format(cal.getTime());
            //System.out.println(dateString);
            //System.out.println(cal.get(Calendar.YEAR));
            //System.out.println(cal.get(cal.DAY_OF_WEEK));
        } catch (Exception e) {
            System.out.println("test result:" + e.getMessage());
        } finally {
            return dateString;
        }
    }

    public static int getDateDiff(Date date1, Date date2, int sign) {

        long base = 1;
        switch (sign) {
            case Calendar.DATE:
                base *= 1000 * 60 * 60 * 24;
                break;
            case Calendar.HOUR:
                base *= 1000 * 60 * 60;
                break;
            case Calendar.MINUTE:
                base *= 1000 * 60;
                break;
            case Calendar.SECOND:
                base *= 1000;
                break;
            default:
                break;
        }
        return (int) ((date1.getTime() - date2.getTime()) / base);
    }


    public static void main(String arg[]) {
        Date date = new Date();
        //System.out.println(getCurrentDateString("yyyy-MM-dd HH:mm:ss:ss"));

    }

}