package com.boco.eoms.commonfaultsheet;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.commonfault.interfaces.GroupFaultServiceLocator;
import com.boco.eoms.sheet.commonfault.interfaces.GroupFault_PortType;
import com.boco.eoms.sheet.commonfault.interfaces.Service_PortType;
import com.boco.eoms.sheet.commonfault.interfaces.Service_ServiceLocator;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.commonfault.webapp.action.CommonFaultSheetMethod;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;
import com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.netownershipwireless.service.INetOwnershipwirelessManager;
import com.boco.eoms.util.InterfaceUtil;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CommonfaultSheet {


    public String acceptSheet(String opDetail, List taskList, String serCaller) {
        String result = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            HashMap sheetMap = new HashMap();
            HashMap columnMap = new HashMap();
            HashMap sheetMap1 = new HashMap();
            Map valueMap = new HashMap();
            ICommonFaultMainManager mainservice = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
            ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
            ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            InterfaceUtilProperties properties = new InterfaceUtilProperties();

            String operateUserId = XmlManage.getFile("/config/commonfaultsheet.xml").getProperty("operate.operateUserId");
            String operateDeptId = XmlManage.getFile("/config/commonfaultsheet.xml").getProperty("operate.operateDeptId");
            String operateRoleId = XmlManage.getFile("/config/commonfaultsheet.xml").getProperty("operate.operateRoleId");
            String operaterContact = XmlManage.getFile("/config/commonfaultsheet.xml").getProperty("operate.operaterContact");
//      String operate_time = StaticMethod.getCurrentDateTime();

            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(13, -5);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String operate_time = sdf.format(calendar2.getTime());

            String nodeName = "acceptSheet";
            System.out.println("commonfaultsheet.acceptSheet内对应的nodeName======" + nodeName);

            if ((opDetail != null) && (!"".equals(opDetail))) {
                String filePath = StaticMethod.getFilePathForUrl("classpath:config/commonfaultsheet.xml");
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

                valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
                String sheet_id = StaticMethod.nullObject2String(sheetMap.get("sheetid"));
                if ((sheet_id != null) && (!"".equals(sheet_id))) {
                    CommonFaultMain main = (CommonFaultMain) mainservice.getMainByAlarmId(sheet_id);
                    int status = StaticMethod.nullObject2int(main.getStatus());

                    if (status != 0) {
                        result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
                        return result;
                    }


                    if ((taskList != null) && (taskList.size() > 0)) {
                        CommonFaultTask task = (CommonFaultTask) taskList.get(0);
                        valueMap.put("operateDeptId", operateDeptId);
                        valueMap.put("operaterContact", operaterContact);

                        valueMap.put("operateUserId", operateUserId);
                        valueMap.put("operateRoleId", operateRoleId);
                        valueMap.put("operateTime", operate_time);
                        valueMap.put("mainId", main.getId());
                        valueMap.put("aiid", task.getId());
                        valueMap.put("toOrgRoleId", task.getOperateRoleId());
                        valueMap.put("linkFaultDealInfo", serCaller);
                        try {
                            sheetMap1.put("main", main);
                            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
                            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
                            columnMap.put("selfSheet", sheetMap1);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口报错,详细内容为" + e.getMessage();
                            return result;
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口报错,详细内容为" + e.getMessage();
                            return result;
                        }
                        try {
                            sm.claimTask(task.getId(), valueMap, columnMap, operateUserId);
                        } catch (Exception e) {
                            e.printStackTrace();
                            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口报错,详细内容为" + e.getMessage();
                            return result;
                        }

                        result = "Status=0;Errlist=";
                    } else {
                        result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于待受理状态,无法进行受理状态更新操作！";
                        return result;
                    }
                } else {
                    result = "Status=-1;Errlist=工单受理状态更新请求接口传入参数不正确,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;Errlist=工单受理状态更新请求接口没有传入opDetail参数,请查证！";
                return result;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口出错！详细信息为" + e.getMessage();
        }
        return result;
    }

    public String replySheet(String serCaller, String opDetail) {
        String result = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            ICommonFaultMainManager mainservice = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
            ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
            ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            HashMap sheetMap = new HashMap();
            HashMap columnMap = new HashMap();
            HashMap sheetMap1 = new HashMap();
            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            String nodeName = "dealSheet";
            System.out.println("commonfaultsheet.dealSheet内对应的nodeName======" + nodeName);
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/commonfaultsheet.xml");
            if ((opDetail != null) && (!"".equals(opDetail))) {
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);

                String sheet_id = StaticMethod.nullObject2String(sheetMap.get("sheetid"));//传过来的是  告警流水号

                String operateUserId = XmlManage.getFile("/config/commonfaultsheet.xml").getProperty("operate.operateUserId");
                String operateDeptId = XmlManage.getFile("/config/commonfaultsheet.xml").getProperty("operate.operateDeptId");
                String operateRoleId = XmlManage.getFile("/config/commonfaultsheet.xml").getProperty("operate.operateRoleId");
                String operaterContact = XmlManage.getFile("/config/commonfaultsheet.xml").getProperty("operate.operaterContact");
                String operate_time = StaticMethod.getCurrentDateTime();
                if ((sheet_id != null) && (!"".equals(sheet_id))) {
                    CommonFaultMain main = (CommonFaultMain) mainservice.getMainByAlarmId(sheet_id);
                    int status = StaticMethod.nullObject2int(main.getStatus());


                    if (status != 0) {
                        result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
                        return result;
                    }
                    String sheetKey = StaticMethod.nullObject2String(main.getId());
                    String condition = " sheetKey = '" + sheetKey + "' and taskstatus in ('2','8') and taskName ='SecondExcuteHumTask' ";//未接单
                    List taskList = taskservice.getTasksByCondition(condition);

                    if ((taskList != null) && (taskList.size() > 0)) {
                        CommonFaultTask task = (CommonFaultTask) taskList.get(0);
                        String taskstatus = task.getTaskStatus();
                        if ("2".equals(taskstatus)) {
                            //未接单
                            System.out.println("in not acce by lyg=" + main.getSheetId());
                            acceptSheet(opDetail, taskList, serCaller);
                        }
                        valueMap.put("operateDeptId", operateDeptId);
                        valueMap.put("operaterContact", operaterContact);

                        valueMap.put("operateUserId", operateUserId);
                        valueMap.put("operateRoleId", operateRoleId);
                        valueMap.put("mainId", main.getId());
                        valueMap.put("operateTime", operate_time);
                        valueMap.put("aiid", task.getId());

                        valueMap.put("linkFaultDealInfo", serCaller);
                        Date mainAlarmSolveDate = main.getMainAlarmSolveDate();
                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String linkFaultAvoidTime = "";
                        if (mainAlarmSolveDate != null) {
                            linkFaultAvoidTime = dateformat.format(mainAlarmSolveDate);
                            System.out.println("linkFaultAvoidTime=lyg=" + linkFaultAvoidTime);
                        }

                        valueMap.put("linkFaultAvoidTime", linkFaultAvoidTime);

                        Map sheetMainMap = SheetBeanUtils.bean2Map(main);
                        valueMap.putAll(sheetMainMap);
                        sheetMap1.put("main", main);
                        sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
                        sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
                        columnMap.put("selfSheet", sheetMap1);
                        String mainAlarmId = StaticMethod.nullObject2String(main.getMainAlarmId());
                        String mainFaultResponseLevel = StaticMethod.nullObject2String(main.getMainFaultResponseLevel());
                        String linkDealStep = StaticMethod.nullObject2String(valueMap.get("linkdealdesc"));
                        String sendContact = StaticMethod.nullObject2String(main.getSendContact());

                        Date operateTime = SheetUtils.stringToDate(operate_time);
                        boolean inrule = false;
                        String obj = "";
                        System.out.println("lizhi:mainAlarmSolveDate=" + mainAlarmSolveDate + "sendContact=" + sendContact + "operateTime=" + operateTime + "mainFaultResponseLevel=" + mainFaultResponseLevel + "mainAlarmId=" + mainAlarmId + "linkDealStep=" + linkDealStep);
//            if ((mainAlarmSolveDate != null) && (!"".equals(sendContact)) && (operateTime.after(mainAlarmSolveDate)) && (!"101030401".equals(mainFaultResponseLevel)))
//            {
//              ICommonFaultAutoManager autoservice = (ICommonFaultAutoManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
//              List steplist = autoservice.getStepsbycondition(mainAlarmId, linkDealStep);
//              if ((steplist.size() > 0) && (!"其它".equals(linkDealStep)) && (!"其他".equals(linkDealStep)))
//              {
//                inrule = true;
//                Object[] object = (Object[])steplist.get(0);
//                obj = String.valueOf(object[2]);
//                System.out.println("满足自动归档——————");
//              }
//              else {
//                inrule = false;
//              }
                        if (inrule) {
                            System.out.println("---自动归档---mainAlarmId=" + mainAlarmId + "--mainFaultRespondseLevel=" + mainFaultResponseLevel + "--linkDealStep=" + linkDealStep);

                            valueMap.put("hasNextTaskFlag", "true");
                            valueMap.put("phaseId", "");
                            Calendar calendar = Calendar.getInstance();
                            CommonFaultLink linkbean = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
                            linkbean.setId(UUIDHexGenerator.getInstance().getID());
                            linkbean.setMainId(StaticMethod.nullObject2String(main.getId()));
                            linkbean.setOperateTime(calendar.getTime());
                            linkbean.setOperateType(new Integer(18));
                            linkbean.setOperateDay(calendar.get(5));
                            linkbean.setOperateMonth(calendar.get(2) + 1);
                            linkbean.setOperateYear(calendar.get(1));
                            linkbean.setOperateUserId(StaticMethod.nullObject2String(main.getSendUserId()));
                            linkbean.setOperateDeptId(StaticMethod.nullObject2String(main.getSendDeptId()));
                            linkbean.setOperateRoleId(StaticMethod.nullObject2String(main.getSendRoleId()));
                            linkbean.setOperaterContact(StaticMethod.nullObject2String(main.getSendContact()));
                            linkbean.setToOrgRoleId("");
                            linkbean.setToOrgType(new Integer(0));
                            linkbean.setAcceptFlag(new Integer(2));
                            linkbean.setCompleteFlag(new Integer(2));
                            linkbean.setActiveTemplateId("HoldHumTask");
                            linkservice.addLink(linkbean);
                            if (main != null) {
                                main.setEndResult(obj);
                                main.setStatus(new Integer(1));
                                main.setHoldStatisfied(Integer.valueOf(1030301));
                                mainservice.addMain(main);
                            }
                            CommonFaultTask taskhold = new CommonFaultTask();
                            try {
                                taskhold.setId(UUIDHexGenerator.getInstance().getID());
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                            taskhold.setTaskName("HoldHumTask");
                            taskhold.setTaskDisplayName("待归档");
                            taskhold.setFlowName("CommonFaultMainFlowProcess");
                            taskhold.setSendTime(new Date());
                            taskhold.setSheetKey(StaticMethod.nullObject2String(main.getId()));
                            taskhold.setTaskStatus("5");
                            taskhold.setSheetId(StaticMethod.nullObject2String(main.getSheetId()));
                            taskhold.setTitle(StaticMethod.nullObject2String(main.getTitle()));
                            taskhold.setOperateType("subrole");
                            taskhold.setCreateTime(new Date());
                            taskhold.setCreateYear(calendar.get(1));
                            taskhold.setCreateMonth(calendar.get(2) + 1);
                            taskhold.setCreateDay(calendar.get(5));
                            taskhold.setOperateRoleId(StaticMethod.nullObject2String(main.getSendRoleId()));
                            taskhold.setTaskOwner(StaticMethod.nullObject2String(main.getSendUserId()));
                            taskhold.setOperateType("subrole");
                            taskhold.setIfWaitForSubTask("false");
                            taskhold.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
                            taskhold.setPreLinkId(linkbean.getId());
                            taskservice.addTask(taskhold);
                        }
//            }
                        sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);
                        result = "Status=0;Errlist=";
                    } else {
                        result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未在T2环节,无法进行工单处理完成提交操作！";
                        return result;
                    }
                } else {
                    result = "Status=-1;Errlist=工单受理状态更新请求接口传入参数不正确,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;Errlist=工单受理状态更新请求接口没有传入opDetail参数,请查证！";
                return result;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口出错！详细信息为" + e.getMessage();
        }
        return result;
    }


//  public String getCCSheetListService(String opDetail)
//  {
//    String result = "";
//    HashMap opDetailMap = new HashMap();
//    InterfaceUtil interfaceUtil = new InterfaceUtil();
//    ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
//    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
//    try {
//      String nodeName = "sheetList";
//      System.out.println("HandheldOperation2EomsService.getSheetListService内对应的nodeName======" + nodeName);
//      if ((opDetail != null) && (!"".equals(opDetail))) {
//        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
//        String user_id = StaticMethod.nullObject2String(opDetailMap.get("User_id"));
//        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records")) - 1;
//        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
//        TawSystemUser user = userMgr.getUserByuserid(user_id);
//        System.out.println("--------ph:user_id-----------" + user_id);
//        String dept_id = StaticMethod.nullObject2String(user.getDeptid());
//        System.out.println("--------ph:deptid-----------" + dept_id);
//        if (("".equals(user_id)) || (user_id == null) || (dept_id == null) || ("".equals(dept_id))) {
//          result = "Status=-1;sheetDetail=;Errlist=工单列表查询接口出错！用户名在EOMS中不存在。";
//          return result;
//        }
//
//        String hql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.ccundoListSql");
//        hql = ExcelConverterUtil.replaceAll(hql, "@userId@", user_id);
//        hql = ExcelConverterUtil.replaceAll(hql, "@deptId@", user.getDeptid());
//        String countSql = "select count(distinct main.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
//        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ > " + start_records;
//        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//        List taskList = services.getSheetAccessoriesList(sql);
//        List countList = services.getSheetAccessoriesList(countSql);
//        Map countMap = (Map)countList.get(0);
//        String sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
//        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, sheet_totals);
//        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
//        return result;
//      }
//      result = "Status=-1;sheetDetail=;Errlist=工单抄送列表查询接口没有传入opDetail参数,请查证！";
//      return result;
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//      result = "Status=-1;sheetDetail=;Errlist=工单抄送列表查询接口出错！详细信息为" + e.getMessage();
//    }return result;
//  }
//
//  public String rejectSheet(String opDetail)
//  {
//    String result = "";
//    try {
//      ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//      ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
//      ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
//      InterfaceUtil interfaceUtil = new InterfaceUtil();
//      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
//      HashMap sheetMap = new HashMap();
//      HashMap columnMap = new HashMap();
//      HashMap sheetMap1 = new HashMap();
//      InterfaceUtilProperties properties = new InterfaceUtilProperties();
//      String nodeName = "rejectSheet";
//      System.out.println("HandheldOperation2EomsService.dealSheet内对应的nodeName======" + nodeName);
//      String filePath = StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml");
//      if ((opDetail != null) && (!"".equals(opDetail))) {
//        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
//        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
//        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
//        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
//        String operate_time = StaticMethod.nullObject2String(sheetMap.get("Reject_time"));
//        if ((sheet_id != null) && (!"".equals(sheet_id))) {
//          CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
//          int status = StaticMethod.nullObject2int(main.getStatus());
//
//          if (status != 0) {
//            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行驳回操作！";
//            return result;
//          }
//          String sheetKey = StaticMethod.nullObject2String(main.getId());
//          String condition = " sheetKey = '" + sheetKey + "' and taskstatus = '2'  and taskName ='SecondExcuteHumTask'";
//
//          List taskList = taskservice.getTasksByCondition(condition);
//          if ((taskList != null) && (taskList.size() > 0)) {
//            CommonFaultTask task = (CommonFaultTask)taskList.get(0);
//            String operateType = StaticMethod.nullObject2String(task.getOperateType());
//            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
//            TawSystemUser user = userMgr.getUserByuserid(operateUserId);
//            if (user != null) {
//              valueMap.put("operateDeptId", user.getDeptid());
//              valueMap.put("operaterContact", user.getMobile());
//            }
//            valueMap.put("operateUserId", operateUserId);
//            valueMap.put("operateRoleId", task.getOperateRoleId());
//            valueMap.put("mainId", main.getId());
//            valueMap.put("aiid", task.getId());
//            valueMap.put("piid", task.getProcessId());
//            valueMap.put("operateTime", operate_time);
//            valueMap.put("acceptTimeLimit", task.getAcceptTimeLimit());
//            valueMap.put("completeTimeLimit", task.getCompleteTimeLimit());
//            valueMap.put("operateType", "4");
//            valueMap.put("hasNextTaskFlag", "true");
//            
//            Map linkMap = new HashMap();
//            if ("SecondExcuteHumTask".equals(task.getTaskName())) {
//              valueMap.put("phaseId", "FirstExcuteTask");
//              IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//              String sql = "select * from commonfault_link link where link.mainid='" + 
//                sheetKey + "' and link.operatetype='1' and link.activetemplateid='FirstExcuteHumTask' " + 
//                "order by link.operatetime desc ";
//              System.out.println("驳回操作查询T1操作人sql====" + sql);
//              List linkList = services.getSheetAccessoriesList(sql);
//             
//              if ((linkList != null) && (linkList.size() > 0)) {
//                linkMap = (Map)linkList.get(0);
//                valueMap.put("dealPerformer", "8aa0813b1c6f2386011c6f39c8350027");
//                valueMap.put("dealPerformerLeader", "8aa0813b1c6f2386011c6f39c8350027");
//                valueMap.put("dealPerformerType", "subrole");
//                valueMap.put("toOrgRoleId", StaticMethod.nullObject2String(linkMap.get("operateRoleId")));
//                valueMap.put("nodeAcceptLimit", StaticMethod.nullObject2String(linkMap.get("nodeAcceptLimit")));
//                valueMap.put("nodeCompleteLimit", StaticMethod.nullObject2String(linkMap.get("nodeCompleteLimit")));
//              }
//            }
//           
//            
////          记录驳回次数 by lyg
//            String mainT2RejectionNum = StaticMethod.nullObject2String(main.getMainT2RejectionNum());
//        //    mainT2RejectionNum = String.valueOf(Integer.parseInt(mainT2RejectionNum)+1);
//            System.out.println("mainT2RejectionNum==liu==liu=="+mainT2RejectionNum);
//            if("".equals(mainT2RejectionNum)){
//				mainT2RejectionNum = "1";
//				System.out.println("mainT2RejectionNum==if1==liu=="+mainT2RejectionNum);
//			}else if("1".equals(mainT2RejectionNum) || "2".equals(mainT2RejectionNum) || "3".equals(mainT2RejectionNum) || "4".equals(mainT2RejectionNum)){
//				mainT2RejectionNum = String.valueOf(Integer.parseInt(mainT2RejectionNum) + 1);
//				System.out.println("mainT2RejectionNum==if2==liu=="+mainT2RejectionNum);
//			}else if("5".equals(mainT2RejectionNum)){
////				表示以前驳回了5次，这是最后一次驳回（第6次），此次驳回后，流程自动流转到T2
//				mainT2RejectionNum = "6";
//				//故障地市不为空，自动流转T2getMainFaultGenerantCity
//				System.out.println("mainT2RejectionNum==if3==liu=="+mainT2RejectionNum);
//				System.out.println("mainT2RejectionNum==if3==main.getToDeptId()=="+main.getToDeptId());
//				if(!"".equals(StaticMethod.nullObject2String(main.getToDeptId()))){
////					根据故障城市查询对应的subroleid
//					INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager)ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
//					String subroleid = netOwnershipwirelessManager.getSubroleId(StaticMethod.nullObject2String(main.getToDeptId()));
////					流程自动流转到T2
////					1.添加link表驳回信息（T2驳回到T1）
//					String deptId = "";
//					if (user != null)
//					{
//						deptId = user.getDeptid();
//					}
//					
//					GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
//					
////					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////					Date thisdate = dateFormat.parse(operate_time);
////					
////					calendar.setTime(thisdate);
//					CommonFaultLink Relink = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
//					String linkId = UUIDHexGenerator.getInstance().getID();
//					Relink.setId(linkId);
//					Relink.setMainId(StaticMethod.nullObject2String(main.getId()));
//					Relink.setOperateType(new Integer(4));
//					Relink.setOperateTime(calendar.getTime());
//					Relink.setOperateDay(calendar.get(5));
//					Relink.setOperateMonth(calendar.get(2) + 1);
//					Relink.setOperateYear(calendar.get(1));
//					Relink.setAcceptFlag(new Integer(0));
//					Relink.setPreLinkId(StaticMethod.nullObject2String(linkMap.get("id")));
//					Relink.setActiveTemplateId("SecondExcuteHumTask");
//					Relink.setToOrgType(new Integer(0));
//					Relink.setCompleteFlag(new Integer(0));
//					Relink.setOperateUserId(StaticMethod.nullObject2String(operateUserId));
////					Relink.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
//					Relink.setOperateDeptId(deptId);
//					String correlationKey = UUIDHexGenerator.getInstance().getID();
//					Relink.setCorrelationKey(correlationKey);
//					Relink.setTemplateFlag(0);
//					//T1link.setOperaterContact(StaticMethod.nullObject2String(mainrule.get("sendContact")));
//					Relink.setPiid(StaticMethod.nullObject2String(main.getPiid()));
//					Relink.setToOrgRoleId("8aa0813b1c6f2386011c6f39c8350027");
//					linkservice.addLink(Relink);
//					//2.T1确认受理
////					calendar.add(13, 30);
//					CommonFaultLink T1link61 = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
//					T1link61.setId(UUIDHexGenerator.getInstance().getID());
//					T1link61.setOperateType(new Integer("61"));
//					T1link61.setActiveTemplateId("FirstExcuteHumTask");
//					T1link61.setOperateTime(calendar.getTime());
//					T1link61.setOperateDay(calendar.get(5));
//					T1link61.setOperateMonth(calendar.get(2) + 1);
//					T1link61.setOperateYear(calendar.get(1));
//					T1link61.setMainId(StaticMethod.nullObject2String(main.getId()));
//					T1link61.setToOrgRoleId(subroleid);//综合班组
//					T1link61.setPreLinkId(linkId);
//					T1link61.setNodeAccessories("");
//					T1link61.setToOrgType(new Integer(0));
//					T1link61.setCompleteFlag(new Integer(0));
//					T1link61.setOperateUserId("fangmin");
//					T1link61.setOperateRoleId("8aa0813b1c6f2386011c6f39c8350027");
//					T1link61.setOperateDeptId("12201");
//					T1link61.setTemplateFlag(0);
//					linkservice.addLink(T1link61);
//					
//					//3.T1增加task记录
////					ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
//					CommonFaultTask T1Task = (CommonFaultTask)taskservice.getTaskModelObject().getClass().newInstance();
//					try
//					{
//						T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
//					}
//					catch (Exception e3)
//					{
//						e3.printStackTrace();
//					}
//					T1Task.setTaskName("FirstExcuteHumTask");
//					T1Task.setTaskDisplayName("T1处理");
//					T1Task.setFlowName("CommonFaultMainFlowProcess");
//					T1Task.setSendTime((Date)main.getSendTime());
//					T1Task.setSheetKey(StaticMethod.nullObject2String(main.getId()));
//					T1Task.setTaskStatus("5");
//					T1Task.setSheetId(StaticMethod.nullObject2String(main.getSheetId()));
//					T1Task.setTitle(StaticMethod.nullObject2String(main.getTitle()));
//					T1Task.setOperateType("subrole");
//					T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
//					T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
//					T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
//					T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
//					T1Task.setOperateRoleId(StaticMethod.nullObject2String("8aa0813b1c6f2386011c6f39c8350027"));
//					T1Task.setTaskOwner(StaticMethod.nullObject2String("fangmin"));
//					T1Task.setIfWaitForSubTask("false");
//					T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
//					T1Task.setPreLinkId(StaticMethod.nullObject2String(linkId));
//					taskservice.addTask(T1Task);
//					
//					
//					
////					4.派单到T2
////					calendar.add(13, 30);
//					String operateTime = StaticMethod.Cal2String(calendar);
//					valueMap.put("operateDeptId", "12201");
//					valueMap.put("operaterContact", "");
//
//					valueMap.put("operateUserId", "fangmin");
//					valueMap.put("operateRoleId", "8aa0813b1c6f2386011c6f39c8350027");
//					valueMap.put("mainId", main.getId());
//					valueMap.put("aiid", T1Task.getId());
//					valueMap.put("piid", T1Task.getProcessId());
//					valueMap.put("operateTime", operateTime);
////					valueMap.put("acceptTimeLimit", task.getAcceptTimeLimit());
////					valueMap.put("completeTimeLimit", task.getCompleteTimeLimit());
//					valueMap.put("operateType", "1");
//					valueMap.put("hasNextTaskFlag", "true");
//					
//					valueMap.put("activeTemplateId","FirstExcuteHumTask");
//					            
//					valueMap.put("phaseId", "SecondExcuteTask");
//					            
//					valueMap.put("dealPerformer", subroleid);//综合班组
//					valueMap.put("dealPerformerLeader", subroleid);//综合班组
//					valueMap.put("dealPerformerType", "subrole");
//					valueMap.put("toOrgRoleId", subroleid);//综合班组
//					
//					operateUserId = "fangmin";
//					
//				}
//				
//			}
//            
//            main.setMainT2RejectionNum(mainT2RejectionNum);
//            sheetMap1.put("main", main);
//            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
//            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
//            columnMap.put("selfSheet", sheetMap1);
//            valueMap.put("correlationKey", StaticMethod.nullObject2String(main.getCorrelationKey()));
//            
//            
//            sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);
//            
//            
//            
//            
//            
//			Date date = new Date();
//			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String currentDate = sf.format(date);
//			String typeID = "02";
//			String type = "驳回";
//			String json = "{'data':" + "{'type':'"+typeID+"','"
//			+ "name':['工单号','处理类型', '处理时间'],'" + "values':" + "[ "
//			+ "['"+StaticMethod.nullObject2String(main.getSheetId())+"','"+type+"','"+currentDate+"']" + "]"
//			+ "}" + "}";
//			System.out.println("cxfivrcj:json=" + json);
//			boolean cxfivrcjresult = false;
//			try {
//				Service_ServiceLocator service = new Service_ServiceLocator();
//				Service_PortType binding = (Service_PortType)service.getJSONServicePort();
//				cxfivrcjresult = binding.insert(json);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("cxfivrcj:result=" + cxfivrcjresult);
//			
//            result = "Status=0;Errlist=";
//          }
//          else {
//            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于处理中状态,无法进行工单驳回操作！";
//            return result;
//          }
//        }
//        else {
//          result = "Status=-1;Errlist=工单驳回接口传入参数不正确,请查证！";
//          return result;
//        }
//      } else {
//        result = "Status=-1;Errlist=工单驳回接口没有传入opDetail参数,请查证！";
//        return result;
//      }
//      return result;
//    } catch (Exception e) {
//      e.printStackTrace();
//      result = "Status=-1;Errlist=工单驳回接口出错！详细信息为" + e.getMessage();
//    }return result;
//  }
//
//  public String getSheetHistoryService(String opDetail)
//  {
//    String result = "";
//    HashMap opDetailMap = new HashMap();
//    InterfaceUtil interfaceUtil = new InterfaceUtil();
//    ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
//    try {
//      String nodeName = "historyList";
//      System.out.println("HandheldOperation2EomsService.getSheetListService内对应的nodeName======" + nodeName);
//      if ((opDetail != null) && (!"".equals(opDetail))) {
//        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
//        String sheetId = StaticMethod.nullObject2String(opDetailMap.get("Sheet_id"));
//        CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheetId);
//        String mainId = StaticMethod.nullObject2String(main.getId());
//        String sql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.historySql");
//        sql = ExcelConverterUtil.replaceAll(sql, "@mainid@", mainId);
//        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//        List taskList = services.getSheetAccessoriesList(sql);
//        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, "");
//        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
//        return result;
//      }
//      result = "Status=-1;sheetDetail=;Errlist=工单流转环节服务接口没有传入opDetail参数,请查证！";
//      return result;
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//      result = "Status=-1;sheetDetail=;Errlist=工单流转环节服务接口出错！详细信息为" + e.getMessage();
//    }return result;
//  }
//
//  public String csSheet(String opDetail)
//  {
//    String result = "";
//    ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//    ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
//    ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
//    InterfaceUtilProperties properties = new InterfaceUtilProperties();
//    System.out.println("-----工单抄送接口服务调用开始----------");
//    try {
//      InterfaceUtil interfaceUtil = new InterfaceUtil();
//      HashMap sheetMap = new HashMap();
//      HashMap columnMap = new HashMap();
//      HashMap sheetMap1 = new HashMap();
//      String nodeName = "csSheet";
//      System.out.println("HandheldOperation2EomsService.dealSheet内对应的nodeName======" + nodeName);
//      String filePath = StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml");
//      if ((opDetail != null) && (!"".equals(opDetail))) {
//        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
//        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
//        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
//        String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
//        CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
//        String mainId = StaticMethod.nullObject2String(main.getId());
//        String condition = " sheetKey = '" + mainId + "' and taskstatus ='8'";
//
//        List taskList = taskservice.getTasksByCondition(condition);
//        if ((taskList != null) && (taskList.size() > 0)) {
//          CommonFaultTask task = (CommonFaultTask)taskList.get(0);
//          String operateRoleId = StaticMethod.nullObject2String(task.getOperateRoleId());
//          valueMap.put("operateroleid", operateRoleId);
//        }
//        ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
//        TawSystemUser user = userMgr.getUserByuserid(operate_userid);
//        if (user != null) {
//          valueMap.put("operateuserid", user.getUserid());
//          valueMap.put("operateDeptId", user.getDeptid());
//          valueMap.put("operaterContact", user.getMobile());
//        }
//        valueMap.put("operateType", "-10");
//        valueMap.put("processTemplateName", "CommonFaultMainFlowProcess");
//        valueMap.put("activetemplateid", "SecondExcuteHumTask");
//        int status = StaticMethod.nullObject2int(main.getStatus());
//        if (status != 0) {
//          result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行抄送操作！";
//          return result;
//        }
//        String roleId = StaticMethod.nullObject2String(sheetMap.get("Role_id"));
//        sheetMap1.put("main", main);
//        sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
//        sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
//        columnMap.put("selfSheet", sheetMap1);
//        HandheldOperationUtil mgr = new HandheldOperationUtil();
//        if (roleId != "") {
//          valueMap.put("copyPerformer", roleId);
//          valueMap.put("copyPerformerLeader", roleId);
//          valueMap.put("copyPerformerType", "subrole");
//          mgr.newSaveNonFlowData(mainId, valueMap, columnMap, mainservice, taskservice, linkservice);
//          result = "Status=0;Errlist=";
//        } else {
//          result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单传入的抄送班组为空,无法进行工单抄送操作！";
//          return result;
//        }
//      }
//      else {
//        result = "Status=-1;Errlist=工单抄送接口没有传入opDetail参数,请查证！";
//        return result;
//      }
//      System.out.println("-----工单抄送接口服务调用结束----------");
//      return result;
//    } catch (Exception e) {
//      e.printStackTrace();
//      result = "Status=-1;Errlist=工单抄送接口出错！详细信息为" + e.getMessage();
//    }return result;
//  }
//
//  public String getInquirySheet(String opDetail)
//  {
//    String result = "";
//    HashMap opDetailMap = new HashMap();
//    InterfaceUtil interfaceUtil = new InterfaceUtil();
//    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
//    try
//    {
//      String nodeName = "inquirySheet";
//      System.out.println("HandheldOperation2EomsService.getInquirySheet内对应的nodeName======" + nodeName);
//      if ((opDetail != null) && (!"".equals(opDetail))) {
//        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
//
//        String sTime = StaticMethod.nullObject2String(opDetailMap.get("time_start"));
//        String eTime = StaticMethod.nullObject2String(opDetailMap.get("time_end"));
//        if ("".equals(sTime))
//        {
//          Calendar canlendar = Calendar.getInstance();
//          canlendar.add(5, -3);
//          sTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(canlendar.getTime());
//        }
//        if ("".equals(eTime))
//        {
//          Date date = new Date();
//          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//          eTime = df.format(date);
//        }
//        String netsort_one = StaticMethod.nullObject2String(opDetailMap.get("Netsort_one"));
//        String group_name = StaticMethod.nullObject2String(opDetailMap.get("GROUP_NAME"));
//        String user_t2id = StaticMethod.nullObject2String(opDetailMap.get("USER_T2id"));
//        String sheet_status = StaticMethod.nullObject2String(opDetailMap.get("Sheet_status"));
//        String fault_responseLevel = StaticMethod.nullObject2String(opDetailMap.get("Fault_ResponseLevel"));
//        String formNo = StaticMethod.nullObject2String(opDetailMap.get("FormNo"));
//        String formTitle = StaticMethod.nullObject2String(opDetailMap.get("FormTitle"));
//        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records"));
//        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
//        if (end_records == 0) {
//          end_records = 15;
//        }
//        String hql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.inquirySheetSql" + sheet_status);
//        if ((hql == null) || ("".equals(hql))) {
//          result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口没有传入工单状态=" + sheet_status + "有误,请查证！";
//          return result;
//        }
//        if ((!"".equals(netsort_one)) && (!"-1".equals(netsort_one)))
//          hql = ExcelConverterUtil.replaceAll(hql, "@mainnetsortone@", "and commonFaultMain.mainnetsortone='" + netsort_one + "'");
//        else {
//          hql = ExcelConverterUtil.replaceAll(hql, "@mainnetsortone@", "");
//        }
//        if ((!"".equals(fault_responseLevel)) && (!"-1".equals(fault_responseLevel)))
//          hql = ExcelConverterUtil.replaceAll(hql, "@mainfaultresponselevel@", "and commonFaultMain.mainfaultresponselevel='" + fault_responseLevel + "'");
//        else {
//          hql = ExcelConverterUtil.replaceAll(hql, "@mainfaultresponselevel@", "");
//        }
//        if ((!"ALL".equals(group_name)) && (!"".equals(group_name)))
//          hql = ExcelConverterUtil.replaceAll(hql, "@operateRoleId@", "and commonFaultLink.operateRoleId in ('" + group_name + "')");
//        else {
//          hql = ExcelConverterUtil.replaceAll(hql, "@operateRoleId@", "");
//        }
//        if ((!"ALL".equals(user_t2id)) && (!"".equals(user_t2id)))
//          hql = ExcelConverterUtil.replaceAll(hql, "@operateUserId@", "and commonFaultLink.operateUserId in ('" + user_t2id + "')");
//        else {
//          hql = ExcelConverterUtil.replaceAll(hql, "@operateUserId@", "");
//        }
//        if ((!"".equals(formNo)) && (!"-1".equals(formNo)))
//          hql = ExcelConverterUtil.replaceAll(hql, "@formNo@", "and commonFaultMain.sheetId like'%" + formNo + "%'");
//        else {
//          hql = ExcelConverterUtil.replaceAll(hql, "@formNo@", "");
//        }
//        if ((!"".equals(formTitle)) && (!"-1".equals(formTitle)))
//          hql = ExcelConverterUtil.replaceAll(hql, "@formTitle@", "and commonFaultMain.title like'%" + formTitle + "%'");
//        else {
//          hql = ExcelConverterUtil.replaceAll(hql, "@formTitle@", "");
//        }
//
//        hql = ExcelConverterUtil.replaceAll(hql, "@timeStart@", "and commonFaultMain.sendtime >=to_date('" + sTime + "','yyyy-MM-dd HH24:mi:ss')");
//        hql = ExcelConverterUtil.replaceAll(hql, "@timeEnd@", "and commonFaultMain.sendtime <=to_date('" + eTime + "','yyyy-MM-dd HH24:mi:ss')");
//        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//        String sheet_totals = "";
//
//        String countSql = "select count(distinct commonFaultMain.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
//        System.out.println(countSql);
//        List countList = services.getSheetAccessoriesList(countSql);
//        Map countMap = (Map)countList.get(0);
//        sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
//
//        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ >= " + start_records;
//        List taskList = services.getSheetAccessoriesList(sql);
//        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, sheet_totals);
//        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
//        return result;
//      }
//      result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口没有传入opDetail参数,请查证！";
//      return result;
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//      result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口出错！详细信息为" + e.getMessage();
//    }return result;
//  }
//
//  public String getFinishSheetListService(String opDetail)
//  {
//    String result = "";
//    HashMap opDetailMap = new HashMap();
//    InterfaceUtil interfaceUtil = new InterfaceUtil();
//    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
//    try {
//      String nodeName = "finishSheetList";
//      System.out.println("HandheldOperation2EomsService.getFinishSheetListService内对应的nodeName======" + nodeName);
//      if ((opDetail != null) && (!"".equals(opDetail))) {
//        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
//        String user_id = StaticMethod.nullObject2String(opDetailMap.get("User_id"));
//        String time_start = StaticMethod.nullObject2String(opDetailMap.get("Start_time"));
//        String time_end = StaticMethod.nullObject2String(opDetailMap.get("End_time"));
//        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records"));
//        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
//        String hql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.finishSheetList");
//        if ("".equals(time_start))
//        {
//          Calendar canlendar = Calendar.getInstance();
//          canlendar.add(5, -3);
//          time_start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(canlendar.getTime());
//        }
//        if ("".equals(time_end))
//        {
//          Date date = new Date();
//          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//          time_end = df.format(date);
//        }
//        if (end_records == 0) {
//          end_records = 15;
//        }
//        hql = ExcelConverterUtil.replaceAll(hql, "@Start_time@", "and a.sendtime >=to_date('" + time_start + "','yyyy-MM-dd HH24:mi:ss')");
//        hql = ExcelConverterUtil.replaceAll(hql, "@End_time@", "and a.sendtime <=to_date('" + time_end + "','yyyy-MM-dd HH24:mi:ss')");
//        hql = ExcelConverterUtil.replaceAll(hql, "@User_id@", user_id);
//        String sheet_totals = "";
//        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//
//        String countSql = "select count(distinct a.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
//        System.out.println("------已办列表数量SQL---------ph-----------------" + countSql);
//        List countList = services.getSheetAccessoriesList(countSql);
//        Map countMap = (Map)countList.get(0);
//        sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
//        System.out.println("------已办列表数量---------ph-----------------" + sheet_totals);
//
//        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ >= " + start_records;
//        List taskList = services.getSheetAccessoriesList(sql);
//
//        for (int i = 0; i < taskList.size(); i++) {
//          Map map = (Map)taskList.get(i);
//          if ((map.get("status") != null) && ("0".equals(map.get("status").toString())))
//            map.put("status", "运行中");
//          else if ((map.get("status") != null) && ("1".equals(map.get("status").toString())))
//            map.put("status", "已归档");
//          else if ((map.get("status") != null) && (!"".equals(map.get("status")))) {
//            map.put("status", "其他");
//          }
//        }
//        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, sheet_totals);
//        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
//        return result;
//      }
//      result = "Status=-1;sheetDetail=;Errlist=已办工单列表查询接口没有传入opDetail参数,请查证！";
//      return result;
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//      result = "Status=-1;sheetDetail=;Errlist=已办工单列表查询接口出错！详细信息为" + e.getMessage();
//    }return result;
//  }
//
//  public String transferSheet(String opDetail)
//  {
//    String result = "";
//    try {
//      ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//      ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
//      ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
//      InterfaceUtil interfaceUtil = new InterfaceUtil();
//      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
//      HashMap sheetMap = new HashMap();
//      HashMap columnMap = new HashMap();
//      HashMap sheetMap1 = new HashMap();
//      InterfaceUtilProperties properties = new InterfaceUtilProperties();
//      String nodeName = "transferSheet";
//      System.out.println("HandheldOperation2EomsService.transferSheet内对应的nodeName======" + nodeName);
//      String filePath = StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml");
//      if ((opDetail != null) && (!"".equals(opDetail))) {
//        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
//        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
//        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
//        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
//        String assignUserid = StaticMethod.nullObject2String(sheetMap.get("Assign_userid"));
//        String roleId = StaticMethod.nullObject2String(sheetMap.get("Role_id"));
//        if ((sheet_id != null) && (!"".equals(sheet_id))) {
//          CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
//          String mainId = StaticMethod.nullObject2String(main.getId());
//
//          String condition = " sheetKey = '" + mainId + "' and taskstatus ='2' and taskname='SecondExcuteHumTask'";
//          List taskList = taskservice.getTasksByCondition(condition);
//          if ((taskList != null) && (taskList.size() > 0))
//          {
//            CommonFaultLink linkbean = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
//            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
//            TawSystemUser user = userMgr.getUserByuserid(operateUserId);
//            Calendar calendar = Calendar.getInstance();
//            linkbean.setId(UUIDHexGenerator.getInstance().getID());
//            linkbean.setMainId(mainId);
//            linkbean.setOperateTime(calendar.getTime());
//            linkbean.setOperateType(new Integer(8));
//            linkbean.setOperateDay(calendar.get(5));
//            linkbean.setOperateMonth(calendar.get(2) + 1);
//            linkbean.setOperateYear(calendar.get(1));
//            linkbean.setOperateUserId(operateUserId);
//            linkbean.setOperateDeptId(user.getDeptid());
//            linkbean.setOperateRoleId(roleId);
//            linkbean.setOperaterContact(user.getMobile());
//            linkbean.setToOrgRoleId(assignUserid);
//            linkbean.setToOrgUserId(assignUserid);
//            linkbean.setToOrgType(new Integer(2));
//            linkbean.setAcceptFlag(new Integer(1));
//            linkbean.setCompleteFlag(new Integer(1));
//            linkbean.setActiveTemplateId("SecondExcuteHumTask");
//            linkservice.addLink(linkbean);
//
//            CommonFaultTask task = (CommonFaultTask)taskList.get(0);
//            String taskId = StaticMethod.nullObject2String(task.getId());
//            String updateSql = "update commonfault_task set taskowner='" + assignUserid + "' where id='" + taskId + "'";
//            IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//            services.updateTasks(updateSql);
//
//            valueMap.put("operateUserId", assignUserid);
//            valueMap.put("operateRoleId", task.getOperateRoleId());
//            valueMap.put("operateDeptId", user.getDeptid());
//            valueMap.put("operaterContact", user.getMobile());
//            valueMap.put("mainId", mainId);
//            valueMap.put("aiid", taskId);
//            valueMap.put("toOrgRoleId", task.getOperateRoleId());
//            sheetMap1.put("main", main);
//            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
//            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
//            columnMap.put("selfSheet", sheetMap1);
//            sm.claimTask(taskId, valueMap, columnMap, assignUserid);
//            result = "Status=0;Errlist=";
//          } else {
//            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于T2未接单处理环节,无法进行工单指定操作！";
//            return result;
//          }
//        } else {
//          result = "Status=-1;Errlist=工单指定接口传入参数不正确,没有传入工单编号,请查证！";
//          return result;
//        }
//      } else {
//        result = "Status=-1;sheetDetail=;Errlist=工单指定接口没有传入opDetail参数,请查证！";
//        return result;
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return result;
//  }
//
//  public String readSheet(String opDetail)
//  {
//    String result = "";
//    ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//    ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
//    ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
//    System.out.println("-----工单已阅接口服务调用开始----------");
//    try {
//      InterfaceUtil interfaceUtil = new InterfaceUtil();
//      HashMap sheetMap = new HashMap();
//      String nodeName = "readSheet";
//      System.out.println("HandheldOperation2EomsService.readSheet内对应的nodeName======" + nodeName);
//      if ((opDetail != null) && (!"".equals(opDetail))) {
//        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
//        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
//        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_id"));
//        CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
//        String mainId = StaticMethod.nullObject2String(main.getId());
//        String condition = " sheetKey = '" + mainId + "' and taskstatus ='2' and taskname='cc'";
//
//        List taskList = taskservice.getTasksByCondition(condition);
//        if ((taskList != null) && (taskList.size() > 0)) {
//          CommonFaultTask task = (CommonFaultTask)taskList.get(0);
//
//          String taskId = StaticMethod.nullObject2String(task.getId());
//          String updateSql = "update commonfault_task set taskstatus='5' where id='" + taskId + "'";
//          IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//          services.updateTasks(updateSql);
//
//          CommonFaultLink linkbean = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
//          ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
//          TawSystemUser user = userMgr.getUserByuserid(operateUserId);
//          Calendar calendar = Calendar.getInstance();
//          linkbean.setId(UUIDHexGenerator.getInstance().getID());
//          linkbean.setMainId(mainId);
//          linkbean.setOperateTime(calendar.getTime());
//          linkbean.setOperateType(new Integer(-15));
//          linkbean.setOperateDay(calendar.get(5));
//          linkbean.setOperateMonth(calendar.get(2) + 1);
//          linkbean.setOperateYear(calendar.get(1));
//          linkbean.setOperateUserId(operateUserId);
//          linkbean.setOperateDeptId(user.getDeptid());
//          linkbean.setOperateRoleId(StaticMethod.nullObject2String(task.getOperateRoleId()));
//          linkbean.setOperaterContact(user.getMobile());
//          linkbean.setActiveTemplateId("cc");
//          linkservice.addLink(linkbean);
//          result = "Status=0;Errlist=";
//        } else {
//          result = "Status=-1;Errlist=工单流水号为" + sheet_id + "没有对应的抄送记录,请查证！";
//          return result;
//        }
//      } else {
//        result = "Status=-1;Errlist=工单已阅接口没有传入opDetail参数,请查证！";
//        return result;
//      }
//      System.out.println("-----工单已阅接口服务调用结束----------");
//      return result;
//    } catch (Exception e) {
//      e.printStackTrace();
//      result = "Status=-1;Errlist=工单已阅接口出错！详细信息为" + e.getMessage();
//    }return result;
//  }
//  
//  
//	private void autoHoldViSheet(String taskName, String obj, Map main, Map link)
//	{
//		String sheetIdd = "";
//		try
//		{
//			BocoLog.info(this, "****副单开始自动归档*******");
//			ICommonFaultMainManager mainService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//			ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
//			ICommonFaultLinkManager linkService = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
//			IBusinessFlowService flowService = (IBusinessFlowService)ApplicationContextHolder.getInstance().getBean("iCommonFaultFlowManager");
//			String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
//			List list = mainService.getMainsByCondition(" mainIfCombine='" + StaticMethod.nullObject2String(main.get("id")) + "' and status=0  and deleted <> '1'");
//			BocoLog.info(this, "副单listsize==" + list.size() + "====主单sheetId=" + sheetId);
//			Calendar calendar = Calendar.getInstance();
//			for (int i = 0; list != null && i < list.size(); i++)
//			{
//				CommonFaultMain mainbean = (CommonFaultMain)list.get(i);
//				sheetIdd = mainbean.getSheetId();
//				BocoLog.info(this, "副单自动归档开始==" + mainbean.getSheetId());
//
//
//
//
//
//				String condition = "";
//				if ("SecondExcuteHumTask".equals(taskName))
//					condition = " sheetKey = '" + mainbean.getId() + "'" + " and taskName='SecondExcuteHumTask' and taskStatus='9' and (subTaskFlag is null or subTaskFlag = 'false' )";
//				else
//					condition = " sheetKey = '" + mainbean.getId() + "'" + " and taskName='HoldHumTask' and taskStatus='2' and (subTaskFlag is null or subTaskFlag = 'false' )";
//				List baseTasks = taskService.getTasksByCondition(condition);
//				CommonFaultTask baseTask = null;
//				if (baseTasks != null && baseTasks.size() > 0)
//				{
//					baseTask = (CommonFaultTask)baseTasks.get(0);
//				} else
//				{
//					BocoLog.info(this, "工单没有符合条件的质检记录==" + sheetId);
//					continue;
//				}
//				CommonFaultLink linkbean = (CommonFaultLink)linkService.getLinkObject().getClass().newInstance();
//				SheetBeanUtils.populate(linkbean, link);
//				linkbean.setId(UUIDHexGenerator.getInstance().getID());
//				linkbean.setMainId(StaticMethod.nullObject2String(mainbean.getId()));
//				linkbean.setMainId(StaticMethod.nullObject2String(mainbean.getId()));
//				linkbean.setToOrgRoleId(mainbean.getSendUserId());
//				linkbean.setPreLinkId(baseTask.getPreLinkId());
//				linkbean.setPiid(mainbean.getPiid());
//				linkbean.setAiid(baseTask.getId());
//				linkbean.setCorrelationKey(mainbean.getCorrelationKey());
//				linkService.addLink(linkbean);
//				baseTask.setTaskStatus("5");
//				baseTask.setCurrentLinkId(linkbean.getId());
//				taskService.addTask(baseTask);
//				mainbean.setEndResult(obj);
//				mainbean.setStatus(new Integer(1));
//				mainbean.setHoldStatisfied(Integer.valueOf(0xfb89d));
//				mainService.saveOrUpdateMain(mainbean);
//				if (taskName.equals("SecondExcuteHumTask"))
//				{
//					CommonFaultLink holdlinkbean = (CommonFaultLink)linkService.getLinkObject().getClass().newInstance();
//					holdlinkbean.setId(UUIDHexGenerator.getInstance().getID());
//					holdlinkbean.setMainId(StaticMethod.nullObject2String(mainbean.getId()));
//					holdlinkbean.setOperateTime(calendar.getTime());
//					holdlinkbean.setOperateType(new Integer(18));
//					holdlinkbean.setOperateDay(calendar.get(5));
//					holdlinkbean.setOperateMonth(calendar.get(2) + 1);
//					holdlinkbean.setOperateYear(calendar.get(1));
//					holdlinkbean.setOperateUserId(StaticMethod.nullObject2String(mainbean.getSendUserId()));
//					holdlinkbean.setOperateDeptId(StaticMethod.nullObject2String(mainbean.getSendDeptId()));
//					holdlinkbean.setOperateRoleId(StaticMethod.nullObject2String(mainbean.getSendRoleId()));
//					holdlinkbean.setOperaterContact(StaticMethod.nullObject2String(mainbean.getSendContact()));
//					holdlinkbean.setToOrgRoleId("");
//					holdlinkbean.setToOrgType(new Integer(0));
//					holdlinkbean.setAcceptFlag(new Integer(2));
//					holdlinkbean.setCompleteFlag(new Integer(2));
//					holdlinkbean.setActiveTemplateId("HoldHumTask");
//					linkService.addLink(holdlinkbean);
//				}
//				ITask task = (ITask)taskService.getTaskModelObject().getClass().newInstance();
//				String holdtkid = "_AI:" + UUIDHexGenerator.getInstance().getID();
//				task.setId(holdtkid);
//				task.setTaskName("HoldHumTask");
//				task.setTaskDisplayName("待归档");
//				task.setFlowName("CommonFaultMainFlowProcess");
//				task.setSendTime(new Date());
//				task.setSheetKey(StaticMethod.nullObject2String(mainbean.getId()));
//				task.setTaskStatus("5");
//				task.setSheetId(StaticMethod.nullObject2String(mainbean.getSheetId()));
//				task.setTitle(StaticMethod.nullObject2String(mainbean.getTitle()));
//				task.setOperateType("subrole");
//				task.setCreateTime(new Date());
//				task.setCreateYear(calendar.get(1));
//				task.setCreateMonth(calendar.get(2) + 1);
//				task.setCreateDay(calendar.get(5));
//				task.setOperateRoleId(StaticMethod.nullObject2String(main.get("sendRoleId")));
//				task.setTaskOwner(StaticMethod.nullObject2String(main.get("sendUserId")));
//				task.setOperateType("subrole");
//				task.setIfWaitForSubTask("false");
//				task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
//				task.setPreLinkId(linkbean.getId());
//				taskService.addTask(task);
//				HashMap map = new HashMap();
//				Map mainMap = SheetBeanUtils.bean2Map(mainbean);
//				Map linkMap = SheetBeanUtils.bean2Map(linkbean);
//				map.put("main", mainMap);
//				map.put("link", linkMap);
//				HashMap sessionMap = new HashMap();
//				sessionMap.put("userId", mainbean.getSendUserId());
//				sessionMap.put("password", "111");
//				flowService.triggerEvent(mainbean.getPiid(), "CommonFaultMainFlowProcess", "Over", map, sessionMap);
//				BocoLog.info(this, "sheetid is " + mainbean.getSheetId() + " 自动归档成功");
//			}
//
//		}
//		catch (Exception e)
//		{
//			BocoLog.info(this, "副单自动归档失败(auto hold error)===" + sheetIdd);
//			e.printStackTrace();
//		}
//	}
//
//	private void transferViSheet2HoldTask(String taskName, Map main, Map link)
//	{
//		String sheetIdd = "";
//		try
//		{
//			BocoLog.info(this, "****副单开始流转至待归档环节*******");
//			String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
//			ICommonFaultMainManager mainService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//			ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
//			ICommonFaultLinkManager linkService = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
//			IBusinessFlowService flowService = (IBusinessFlowService)ApplicationContextHolder.getInstance().getBean("iCommonFaultFlowManager");
//			List list = mainService.getMainsByCondition(" mainIfCombine='" + StaticMethod.nullObject2String(main.get("id")) + "' and status=0 and deleted <> '1'");
//			BocoLog.info(this, "副单listsize==" + list.size() + "====主单sheetId=" + sheetId);
//
//
//			for (int i = 0; list != null && i < list.size(); i++)
//			{
//				CommonFaultMain mainbean = (CommonFaultMain)list.get(i);
//				sheetIdd = mainbean.getSheetId();
//				BocoLog.info(this, "副单开始流转至待归档环节==" + mainbean.getSheetId());
//				Map maMap = SheetBeanUtils.bean2Map(mainbean);
//				String condition = " sheetKey = '" + mainbean.getId() + "'" + " and taskName='" + taskName + "' and taskStatus='9' and (subTaskFlag is null or subTaskFlag = 'false' )";
//				List baseTasks = taskService.getTasksByCondition(condition);
//				CommonFaultTask baseTask = null;
//				if (baseTasks != null && baseTasks.size() > 0)
//				{
//					baseTask = (CommonFaultTask)baseTasks.get(0);
//				} else
//				{
//					BocoLog.info(this, "工单没有符合条件的" + taskName + "记录==" + sheetId);
//					continue;
//				}
//				CommonFaultLink linkbean = (CommonFaultLink)linkService.getLinkObject().getClass().newInstance();
//				link.remove("id");
//				SheetBeanUtils.populate(linkbean, link);
//				linkbean.setMainId(StaticMethod.nullObject2String(mainbean.getId()));
//				linkbean.setToOrgRoleId(mainbean.getSendUserId());
//				linkbean.setPreLinkId(baseTask.getPreLinkId());
//				linkbean.setPiid(mainbean.getPiid());
//				linkbean.setAiid(baseTask.getId());
//				linkbean.setCorrelationKey(mainbean.getCorrelationKey());
//				Map liMap = SheetBeanUtils.bean2Map(linkbean);
//				HashMap columnMap = new HashMap();
//				columnMap.put("selfSheet", setNewInterfacePara());
//				HashMap parameters = new HashMap();
//				parameters.putAll(maMap);
//				parameters.putAll(liMap);
//				setBaseMap(parameters);
//				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
//				HashMap WpsMap = sm.prepareMap(parameters, columnMap);
//				Map opMap = (HashMap)WpsMap.get("operate");
//				Map mmMap = (HashMap)WpsMap.get("main");
//				Map llMap = (HashMap)WpsMap.get("link");
//				BocoLog.info(this, "---" + StaticMethod.nullObject2String(llMap.get("id")));
//				mmMap.put("mainIfReplay", StaticMethod.nullObject2String(llMap.get("id")));
//				opMap.put("phaseId", "HoldTask");
//				opMap.put("dealPerformer", mainbean.getSendUserId());
//				opMap.put("dealPerformerLeader", mainbean.getSendUserId());
//				opMap.put("dealPerformerType", "user");
//
//				WpsMap.put("operate", opMap);
//				WpsMap.put("main", mmMap);
//				HashMap sessionMap = new HashMap();
//				sessionMap.put("userId", mainbean.getSendUserId());
//				sessionMap.put("password", "111");
//				flowService.completeHumanTask(baseTask.getId(), WpsMap, sessionMap);
//				BocoLog.info(this, "sheetid is " + mainbean.getSheetId() + " 进入待归档成功");
//			}
//
//		}
//		catch (Exception e)
//		{
//			BocoLog.info(this, "副单进入待归档环节失败(auto come in holdtask error)===" + sheetIdd + ",并检查err日志");
//			e.printStackTrace();
//		}
//	}
//	
//	public Map setBaseMap(Map map)
//	{
//		try
//		{
//			ICommonFaultMainManager mainMgr = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//			ICommonFaultLinkManager linkMgr = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
//			String mainBeanId = "iCommonFaultMainManager";
//			map.put("beanId", new String[] {
//				mainBeanId
//			});
//			BocoLog.info(this, "mainClassName=" + mainMgr.getMainObject().getClass().getName());
//			BocoLog.info(this, "linkClassName=" + linkMgr.getLinkObject().getClass().getName());
//			map.put("mainClassName", new String[] {
//				mainMgr.getMainObject().getClass().getName()
//			});
//			map.put("linkClassName", new String[] {
//				linkMgr.getLinkObject().getClass().getName()
//			});
//		}
//		catch (Exception err)
//		{
//			err.printStackTrace();
//		}
//		return map;
//	}
//	
//	public Map setNewInterfacePara()
//	throws Exception
//{
//	ICommonFaultMainManager mainMgr = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//	ICommonFaultLinkManager linkMgr = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
//	HashMap sheetMap = new HashMap();
//	sheetMap.put("main", mainMgr.getMainObject().getClass().newInstance());
//	sheetMap.put("link", linkMgr.getLinkObject().getClass().newInstance());
//	sheetMap.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
//	return sheetMap;
//}

}