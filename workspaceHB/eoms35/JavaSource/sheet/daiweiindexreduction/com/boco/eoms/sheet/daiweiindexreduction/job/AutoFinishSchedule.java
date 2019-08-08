package com.boco.eoms.sheet.daiweiindexreduction.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.branchindexreduction.util.DateUtil;
import com.boco.eoms.sheet.daiweiindexreduction.model.DaiWeiIndexReductionMain;
import com.boco.eoms.sheet.daiweiindexreduction.model.DaiWeiIndexReductionTask;
import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiWeiIndexReductionFlowManager;
import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiWeiIndexReductionLinkManager;
import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiWeiIndexReductionMainManager;
import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiWeiIndexReductionTaskManager;


/**
 * 归档环节建单人超6个工作日内未完成归档操作则系统自动归档(待归档环节超过1个工作日自动归档)
 *
 * @author wmm
 */
public class AutoFinishSchedule implements Job {

    private static String isWeekend = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekend");/* 周中是放假的时间 */
    private static String isWeekday = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekday");/*周末上上班时间的*/

    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        String nowtime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        BocoLog.info(AutoFinishSchedule.class, "AutoFinishSchedule Job is running start;" + nowtime);

        IDaiWeiIndexReductionTaskManager taskMgr = (IDaiWeiIndexReductionTaskManager) ApplicationContextHolder.getInstance().getBean("iDaiWeiIndexReductionTaskManager");
        IDaiWeiIndexReductionMainManager mainMgr = (IDaiWeiIndexReductionMainManager) ApplicationContextHolder.getInstance().getBean("iDaiWeiIndexReductionMainManager");
        IDaiWeiIndexReductionLinkManager linkMgr = (IDaiWeiIndexReductionLinkManager) ApplicationContextHolder.getInstance().getBean("iDaiWeiIndexReductionLinkManager");
        IDaiWeiIndexReductionFlowManager flowMgr = (IDaiWeiIndexReductionFlowManager) ApplicationContextHolder.getInstance().getBean("iDaiWeiIndexReductionFlowManager");

        Date date = addDateByHour();/*当前时间往前推1个工作日*/
        String time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
        String sheetId = "";
        System.out.println("当前时间往前推1个工作日" + time);
        List taskList = null;
        try {
            taskList = taskMgr.getTasksByCondition(" taskStatus!='5' and taskName='HoldTask' and createTime<to_date('" + time + "','YYYY-MM-DD HH24:MI:SS') ");
        } catch (Exception e) {
            BocoLog.info(AutoFinishSchedule.class, "the sql is error");
            e.printStackTrace();
        }
        if (taskList != null && taskList.size() > 0) {
            DaiWeiIndexReductionTask taskObj = null;
            DaiWeiIndexReductionMain mainObj = null;
            String taskId = "";
            HashMap sessionMap = new HashMap();
            sessionMap.put("password", "123");

            for (int i = 0; i < taskList.size(); i++) {
                try {
                    taskObj = (DaiWeiIndexReductionTask) taskList.get(i);
                    taskId = StaticMethod.nullObject2String(taskObj.getId());
                    mainObj = (DaiWeiIndexReductionMain) mainMgr.getSingleMainPO(taskObj.getSheetKey());
                    sheetId = StaticMethod.nullObject2String(mainObj.getSheetId());

//					mainObj.setEndResult("满意");//请写入归档意见
//					mainObj.setHoldStatisfied(Integer.valueOf(1030301));//请写入归档满意度  
//					mainObj.setStatus(Constants.SHEET_HOLD);
//					mainMgr.saveOrUpdateMain(mainObj);

                    BaseLink holdLink = (BaseLink) linkMgr.getLinkObject().getClass().newInstance();
                    HashMap linkMap = new HashMap();
                    linkMap.put("id", UUIDHexGenerator.getInstance().getID());
                    linkMap.put("mainId", StaticMethod.nullObject2String(taskObj.getSheetKey()));
                    linkMap.put("operateTime", new Date());
                    linkMap.put("operateType", Integer.valueOf(18));
                    linkMap.put("operateUserId", StaticMethod.nullObject2String(mainObj.getSendUserId()));
                    linkMap.put("operateDeptId", StaticMethod.nullObject2String(mainObj.getSendDeptId()));
                    linkMap.put("operateRoleId", StaticMethod.nullObject2String(mainObj.getSendRoleId()));
                    linkMap.put("operaterContact", StaticMethod.nullObject2String(mainObj.getSendContact()));
                    linkMap.put("activeTemplateId", "HoldTask");
                    linkMap.put("aiid", taskId); //task表待归档环节的task记录的id
                    linkMap.put("piid", StaticMethod.nullObject2String(mainObj.getPiid()));
                    linkMap.put("correlationKey", UUIDHexGenerator.getInstance().getID());
                    linkMap.put("preLinkId", StaticMethod.nullObject2String(taskObj.getPreLinkId()));
                    SheetBeanUtils.populate(holdLink, linkMap);
                    linkMgr.addLink(holdLink);

                    Map mainMap = SheetBeanUtils.bean2Map(mainObj);
                    HashMap sheetMap = new HashMap();
                    sheetMap.put("main", mainMap);
                    sheetMap.put("link", linkMap);
                    taskObj.setTaskStatus("5");
                    taskMgr.addTask(taskObj);
                    sessionMap.put("userId", mainObj.getSendUserId());

                    //终止流程实例
                    flowMgr.triggerEvent(mainObj.getPiid(), "DaiWeiIndexReduction", "forceHold", sheetMap, sessionMap);
                } catch (Exception e) {
                    System.out.println("自动归档失败。。。。from AutoFinishSchedule");
                    e.printStackTrace();

                    BocoLog.error(AutoFinishSchedule.class, "sheetId ：" + sheetId + "自动归档失败 error！" + e.getMessage());
                    continue;
                }
            }
        } else {
            BocoLog.info(AutoFinishSchedule.class, "当前时间" + nowtime + "没有符合条件的数据");
        }


    }

    /**
     * 获取前1个工作日日期
     *
     * @return
     */
    public static Date addDateByHour() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.getWorkDate(new Date()));
        boolean holidayFlag = false;
        for (int i = 0; i < 1; i++) {
            //把原时间加1小时
            cal.add(Calendar.DAY_OF_YEAR, -1); /*当前时间减一天*/
            holidayFlag = checkHoliday(cal);
            if (holidayFlag) {/* 如果日期不合法*/
                i--;
            }
        }
        return cal.getTime();

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
