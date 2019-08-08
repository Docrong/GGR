package com.boco.eoms.sheet.branchindexreduction.job;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.branchindexreduction.model.BranchIndexReductionMain;
import com.boco.eoms.sheet.branchindexreduction.model.BranchIndexReductionTask;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionFlowManager;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionLinkManager;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionMainManager;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionTaskManager;

/**
 * 自25日起的2个工作日  包含上个月的25号起的2个工作日
 *
 * @author wmm
 */
public class DateIfEqualShow {

    private static String isWeekend = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekend");/* 周中是放假的时间 */
    private static String isWeekday = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekday");/*周末上上班时间的*/

    public DateIfEqualShow() {
    }


    /**
     * 获取前n个工作日日期
     *
     * @return
     */
    public static long addDateByHour(int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        boolean holidayFlag = false;
        for (int i = 0; i < days; i++) {  // ?????
            //把原时间加1小时
            cal.add(Calendar.DAY_OF_YEAR, -1); /*当前时间减一天*/
            holidayFlag = checkHoliday(cal);
            if (holidayFlag) {/* 如果日期不合法*/
                i--;
            }
        }
        return cal.getTimeInMillis();

    }

    /**
     * 获取前n个工作日日期
     *
     * @return
     */
    public static long addDate01ByHour(Date date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        Date date01 = null;
        ;
        try {
            date01 = sdf1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date01);
        System.out.println("===========date01============" + date01);
        boolean holidayFlag = false;
        for (int i = 0; i < days; i++) {  // ?????
            //把原时间加1小时
            cal.add(Calendar.DAY_OF_YEAR, 1); /*当前时间加一天*/
            holidayFlag = checkHoliday(cal);
            if (holidayFlag) {/* 如果日期不合法*/
                i--;
            }
        }
        return cal.getTimeInMillis();

    }

    /**
     * 校验是否符合
     *
     * @param src
     * @return
     * @throws ParseException
     */
    public static boolean checkHoliday(Calendar cal) {
        //.getTime();
        String nowtime = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
        boolean result = false;
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (isWeekend.contains(nowtime)) { /* 周中是放假的时间 */
            System.out.println("当前日期：" + nowtime + ",不上班!");
            return true;
        }
        if ((week_index == 0 || week_index == 6) && !isWeekday.contains(nowtime)) { /*是周六或周末 并不在周末上班日期中 不合法*/
            result = true;
        }
        return result;

    }
}
