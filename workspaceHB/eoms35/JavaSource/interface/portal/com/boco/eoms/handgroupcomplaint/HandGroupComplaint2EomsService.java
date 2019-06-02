package com.boco.eoms.handgroupcomplaint;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintLinkManager;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintMainManager;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintTaskManager;
import com.boco.eoms.sheet.groupcomplaint.task.impl.GroupComplaintTask;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.util.InterfaceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandGroupComplaint2EomsService
{	
	/**
	 * 工单详情
	 * @param opDetail
	 * @return
	 */
  public String getSheetInfoService(String opDetail)
  {
    String result = "";
    String packinfo = "";
    try {
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
      IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
      ITawSystemAreaManager areaservice = (ITawSystemAreaManager)ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
      GroupComplaintMain main = new GroupComplaintMain();

      String nodeName = "dataService";
      System.out.println("HandGroupComplaint2EomsService.getSheetInfoService内对应的nodeName======" + nodeName);
      HashMap sheetMap = new HashMap();
      HandGroupComplaintUtil hoUtil = new HandGroupComplaintUtil();

      if ((opDetail != null) && (!"".equals(opDetail)))
      {
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        if ((sheet_id != null) && (!"".equals(sheet_id))) {
          main = (GroupComplaintMain)mainservice.getMainBySheetId(sheet_id);
          if (main != null) {
            String toDeptId = main.getToDeptId();
            if ((!"".equals(toDeptId)) && (toDeptId != null)) {
              TawSystemArea area = areaservice.getAreaByAreaId(toDeptId);
              String areaName = area.getAreaname();
              main.setToDeptId(areaName);
            }
            Map mainMap = SheetBeanUtils.bean2Map(main);
            String status = StaticMethod.nullObject2String(main.getStatus());
            if ("1".equals(status)) {
              mainMap.put("taskStatus", "归档");
            }else if("0".equals(status)){
            	mainMap.put("taskStatus", "运行中");
            }else if("-12".equals(status)){
            	mainMap.put("taskStatus", "强制作废");
            }else if("-14".equals(status)){
            	mainMap.put("taskStatus", "作废");
            }  
              

            opDetail = hoUtil.getXmlFromMap(mainMap, StaticMethod.getFilePathForUrl("classpath:config/handgroupcomplaint.xml"), nodeName);
            result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
            System.out.println("getSheetInfoService="+result);
            return result;
          }

          result = "Status=-1;sheetDetail=;Errlist=工单详情接口没有找到工单流水号为 " + sheet_id + " 的工单未找到或不存在,请查证！";
          return result;
        }

        result = "Status=-1;sheetDetail=;Errlist=工单详情接口没有找到对应工单流水号Sheet_id的参数,请查证！";
        return result;
      }

      result = "Status=-1;sheetDetail=;Errlist=工单详情接口没有传入参数,请查证！";

      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=工单详情接口出错！详细信息为" + e.getMessage();
    }
    System.out.println("getSheetInfoService="+result);
    return result;
  }
  
  
  /**
   * 工单确认受理接口
   * @param opDetail
   * @return
   */
  public String acceptSheet(String opDetail)
  {
    String result = "";
    try {
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      HashMap sheetMap = new HashMap();
      HashMap columnMap = new HashMap();
      HashMap sheetMap1 = new HashMap();
      Map valueMap = new HashMap();
      IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
      IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
      IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
      String operateUserId = "";
      InterfaceUtilProperties properties = new InterfaceUtilProperties();

      String nodeName = "acceptSheet";
      System.out.println("HandGroupComplaint2EomsService.acceptSheet内对应的nodeName======" + nodeName);

      if ((opDetail != null) && (!"".equals(opDetail))) {
        String filePath = StaticMethod.getFilePathForUrl("classpath:config/handgroupcomplaint.xml");
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

        valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
        if ((sheet_id != null) && (!"".equals(sheet_id))) {
        	GroupComplaintMain main = (GroupComplaintMain)mainservice.getMainBySheetId(sheet_id);
          int status = StaticMethod.nullObject2int(main.getStatus());

          if (status != 0) {
            result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
            return result;
          }
          String sheetKey = StaticMethod.nullObject2String(main.getId());
          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='SecondExcuteHumTask' ";
          List taskList = taskservice.getTasksByCondition(condition);

          if ((taskList != null) && (taskList.size() > 0)) {
        	GroupComplaintTask task = (GroupComplaintTask)taskList.get(0);
            String operateType = StaticMethod.nullObject2String(task.getOperateType());
            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            TawSystemUser user = userMgr.getUserByuserid(operate_userid);
            if ((operate_userid != null) && (!"".equals(operate_userid))) {
              if ("user".equals(operateType)) {
                operateUserId = operate_userid;
              } else if ("subrole".equals(operateType)) {
                ITawSystemUserRefRoleManager usermgr = (ITawSystemUserRefRoleManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
                List userList = usermgr.getRoleidByuserid(operate_userid);
                if (userList.contains(task.getTaskOwner())) {
                  operateUserId = operate_userid;
                } else {
                  result = "Status=-1;Errlist=工单受理状态更新请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                  return result;
                }
              } else if ("dept".equals(operateType)) {
                if (task.getTaskOwner().equals(user.getDeptid())) {
                  operateUserId = operate_userid;
                } else {
                  result = "Status=-1;Errlist=工单受理状态更新请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                  return result;
                }
              }
            } else {
              result = "Status=-1;Errlist=工单受理状态更新请求接口传入的处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
              return result;
            }

            if (user != null) {
              valueMap.put("operateDeptId", user.getDeptid());
              valueMap.put("operaterContact", user.getMobile());
            }

            valueMap.put("operateUserId", operateUserId);
            valueMap.put("operateRoleId", task.getOperateRoleId());
            valueMap.put("mainId", main.getId());
            valueMap.put("aiid", task.getId());
            valueMap.put("toOrgRoleId", task.getOperateRoleId());
            try
            {
              sheetMap1.put("main", main);
              sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
              sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
              columnMap.put("selfSheet", sheetMap1);
              result = "Status=0;Errlist=";
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
            

          } else {
            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于待受理状态,无法进行受理状态更新操作！";
            return result;
          }
        }
        else {
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
    System.out.println("acceptSheet="+result);
    return result;
  }
  
  /**
   * 工单处理完成提交接口
   * @param opDetail
   * @return
   */
  public String dealSheet(String opDetail)
  {
    String result = "";
    try {
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
      IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
      IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
      HashMap sheetMap = new HashMap();
      HashMap columnMap = new HashMap();
      HashMap sheetMap1 = new HashMap();
      String operateUserId = "";
      InterfaceUtilProperties properties = new InterfaceUtilProperties();
      String nodeName = "dealSheet";
      String alarmStatus = "";
      System.out.println("HandGroupComplaint2EomsService.dealSheet内对应的nodeName======" + nodeName);
      String filePath = StaticMethod.getFilePathForUrl("classpath:config/handgroupcomplaint.xml");
      if ((opDetail != null) && (!"".equals(opDetail))) {
    	  
    	  
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);

        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
        String operate_time = StaticMethod.nullObject2String(sheetMap.get("Operate_time"));

        if ((sheet_id != null) && (!"".equals(sheet_id))) {
        	GroupComplaintMain main = (GroupComplaintMain)mainservice.getMainBySheetId(sheet_id);
          int status = StaticMethod.nullObject2int(main.getStatus());
          
          String linkAffectedAreas = StaticMethod.nullObject2String(valueMap.get("affectedAreas"));
          System.out.println("sheet_id==linkAffectedAreas="+linkAffectedAreas);
//          String mainAffectedAreas = StaticMethod.nullObject2String(main.getAffectedAreas());
          main.setAffectedAreas(linkAffectedAreas);
          if (status != 0) {
            result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
            return result;
          }
          String sheetKey = StaticMethod.nullObject2String(main.getId());
          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='SecondExcuteHumTask' ";
          List taskList = taskservice.getTasksByCondition(condition);

          if ((taskList != null) && (taskList.size() > 0)) {
        	GroupComplaintTask task = (GroupComplaintTask)taskList.get(0);
            String operateType = StaticMethod.nullObject2String(task.getOperateType());
            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            TawSystemUser user = userMgr.getUserByuserid(operate_userid);

            if ((operate_userid != null) && (!"".equals(operate_userid))) {
              if (task.getTaskOwner().equals(operate_userid)) {
                operateUserId = operate_userid;
              }
              else if ("user".equals(operateType)) {
                operateUserId = operate_userid;
              } else if ("subrole".equals(operateType)) {
                ITawSystemUserRefRoleManager usermgr = (ITawSystemUserRefRoleManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
                List userList = usermgr.getRoleidByuserid(operate_userid);
                if (userList.contains(task.getTaskOwner())) {
                  operateUserId = operate_userid;
                } else {
                  result = "Status=-1;Errlist=工单处理完成提交请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                  return result;
                }
              } else if ("dept".equals(operateType)) {
                if (task.getTaskOwner().equals(user.getDeptid())) {
                  operateUserId = operate_userid;
                } else {
                  result = "Status=-1;Errlist=工单处理完成提交请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                  return result;
                }
              }
            }
            else
            {
              result = "Status=-1;Errlist=工单处理完成提交请求接口传入的处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
              return result;
            }
            ITawSystemUserManager usemgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            if (user != null) {
              valueMap.put("operateDeptId", user.getDeptid());
              if("".equals(StaticMethod.nullObject2String(valueMap.get("operaterContact")))){
            	  valueMap.put("operaterContact", user.getMobile());
              }
            }

            valueMap.put("operateUserId", operateUserId);
            valueMap.put("operateRoleId", task.getOperateRoleId());
            valueMap.put("mainId", main.getId());
            valueMap.put("operateTime", operate_time);
            valueMap.put("aiid", task.getId());
            
            String subroleid = XmlManage.getFile("/config/handgroupcomplaint.xml").getProperty("subrole.zjsubroleid");
            
            valueMap.put("dealPerformer", subroleid);
            valueMap.put("dealPerformerType", "subrole");
            valueMap.put("dealPerformerLeader", subroleid);
            
            System.out.println("mainAffectedAreas==="+main.getAffectedAreas());
            Map sheetMainMap = SheetBeanUtils.bean2Map(main);
            valueMap.putAll(sheetMainMap);
            sheetMap1.put("main", main);
            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
            columnMap.put("selfSheet", sheetMap1);
            

            sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);
            result = "Status=0;Errlist=";
          } else {
            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于处理中状态,无法进行工单处理完成提交操作！";
            return result;
          }
        }
        else {
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
    System.out.println("dealSheet="+result);
    return result;
  }
  
  /**
   * 待办工单列表
   * @param opDetail
   * @return
   */
  public String getTodoListService(String opDetail)
  {
    String result = "";
    HashMap opDetailMap = new HashMap();
    InterfaceUtil interfaceUtil = new InterfaceUtil();
    ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
    HandGroupComplaintUtil hoUtil = new HandGroupComplaintUtil();
    try {
      String nodeName = "sheetList";
      System.out.println("HandGroupComplaint2EomsService.getSheetListService内对应的nodeName======" + nodeName);
      System.out.println("--------ph:opDateail-----------" + opDetail);
      if ((opDetail != null) && (!"".equals(opDetail))) {
        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
        String user_id = StaticMethod.nullObject2String(opDetailMap.get("User_id"));
        System.out.println("--------ph:user_id-----------" + user_id);
        TawSystemUser user = userMgr.getUserByuserid(user_id);
        String dept_id = StaticMethod.nullObject2String(user.getDeptid());
        System.out.println("--------ph:deptid-----------" + dept_id);
        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records")) - 1;
        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
        if (("".equals(user_id)) || (user_id == null) || (dept_id == null) || ("".equals(dept_id))) {
          result = "Status=-1;sheetDetail=;Errlist=工单列表查询接口出错！用户名在EOMS中不存在。";
          return result;
        }

        String hql = "SELECT TASK.SHEETID,TASK.TITLE,MAIN.MAINCOMPLETELIMITT2,CASE TASKSTATUS WHEN '2' THEN '未接单' WHEN '8' THEN  '接单' END AS TASKSTATUS,  CASE TASKNAME WHEN 'cc' THEN '抄送' WHEN 'SecondExcuteHumTask' THEN '二级处理' WHEN 'advice' THEN '阶段通知' WHEN 'reply' THEN '阶段回复' ELSE '其他' END AS TASKNAME,  MAIN.DEALTIME1  FROM GROUPCOMPLAINT_TASK TASK, GROUPCOMPLAINT_MAIN MAIN WHERE ((TASK.TASKOWNER = '"+user_id+"' OR TASK.TASKOWNER = '"+user.getDeptid()+"') OR TASK.TASKOWNER IN (SELECT USERREFROLE.SUBROLEID FROM TAW_SYSTEM_USERREFROLE USERREFROLE WHERE USERREFROLE.USERID = '"+user_id+"')) AND (TASK.TASKSTATUS = 2 OR TASK.TASKSTATUS = 8 OR TASK.TASKSTATUS = 1) AND TASK.TASKDISPLAYNAME <> '草稿' AND MAIN.ID = TASK.SHEETKEY AND MAIN.DELETED <> '1'  AND (TASK.IFWAITFORSUBTASK = 'false' OR (TASK.IFWAITFORSUBTASK = 'true' AND TASK.ID IN (SELECT TASK1.PARENTTASKID  FROM GROUPCOMPLAINT_TASK TASK1 WHERE TASK1.SUBTASKFLAG = 'true' AND (TASK1.SUBTASKDEALFALG = 'false' OR  TASK1.SUBTASKDEALFALG IS NULL)  AND TASK1.TASKSTATUS = '5'))) AND TASKNAME = 'SecondExcuteHumTask' ORDER BY TASK.CREATETIME DESC";
        System.out.println("hql==liu=="+hql);
        String countSql = "select count(*) num from ("+hql+")";
        System.out.println("countSql=liu="+countSql);
        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ > " + start_records;
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List taskList = services.getSheetAccessoriesList(sql);
        List countList = services.getSheetAccessoriesList(countSql);
        Map countMap = (Map)countList.get(0);
        String sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handgroupcomplaint.xml"), nodeName, sheet_totals);
        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
        System.out.println("getTodoListService="+result);
        return result;
      }
      result = "Status=-1;sheetDetail=;Errlist=工单列表查询接口没有传入opDetail参数,请查证！";
      System.out.println("getTodoListService="+result);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=工单列表查询接口出错！详细信息为" + e.getMessage();
    }
    System.out.println("getTodoListService="+result);
    return result;
  }

  
  /**
   * 工单驳回接口
   * @param opDetail
   * @return
   */
  public String rejectSheet(String opDetail)
  {
    String result = "";
    try {
      IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
      IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
      IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
      HashMap sheetMap = new HashMap();
      HashMap columnMap = new HashMap();
      HashMap sheetMap1 = new HashMap();
      InterfaceUtilProperties properties = new InterfaceUtilProperties();
      String nodeName = "rejectSheet";
      System.out.println("HandGroupComplaint2EomsService.dealSheet内对应的nodeName======" + nodeName);
      String filePath = StaticMethod.getFilePathForUrl("classpath:config/handgroupcomplaint.xml");
      if ((opDetail != null) && (!"".equals(opDetail))) {
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
        String operate_time = StaticMethod.nullObject2String(sheetMap.get("Reject_time"));
        if ((sheet_id != null) && (!"".equals(sheet_id))) {
        	GroupComplaintMain main = (GroupComplaintMain)mainservice.getMainBySheetId(sheet_id);
          int status = StaticMethod.nullObject2int(main.getStatus());

          if (status != 0) {
            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行驳回操作！";
            return result;
          }
          String sheetKey = StaticMethod.nullObject2String(main.getId());
          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='SecondExcuteHumTask'";

          List taskList = taskservice.getTasksByCondition(condition);
          if ((taskList != null) && (taskList.size() > 0)) {
        	GroupComplaintTask task = (GroupComplaintTask)taskList.get(0);
            String operateType = StaticMethod.nullObject2String(task.getOperateType());
            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            TawSystemUser user = userMgr.getUserByuserid(operateUserId);
            if (user != null) {
              valueMap.put("operateDeptId", user.getDeptid());
              valueMap.put("operaterContact", user.getMobile());
            }
            valueMap.put("operateUserId", operateUserId);
            valueMap.put("operateRoleId", task.getOperateRoleId());
            valueMap.put("mainId", main.getId());
            valueMap.put("aiid", task.getId());
            valueMap.put("piid", task.getProcessId());
            valueMap.put("operateTime", operate_time);
            valueMap.put("acceptTimeLimit", task.getAcceptTimeLimit());
            valueMap.put("completeTimeLimit", task.getCompleteTimeLimit());
            valueMap.put("operateType", "4");
            valueMap.put("hasNextTaskFlag", "true");

            if ("SecondExcuteHumTask".equals(task.getTaskName())) {
              valueMap.put("phaseId", "FirstExcuteHumTask");
              IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
              String sql = "select * from GroupComplaint_link link where link.mainid='" + 
                sheetKey + "' and link.operatetype='1' and link.activetemplateid='FirstExcuteHumTask' " + 
                "order by link.operatetime desc ";
              System.out.println("驳回操作查询T1操作人sql====" + sql);
              String subroleid = XmlManage.getFile("/config/handgroupcomplaint.xml").getProperty("subrole.t1subroleid");
              List linkList = services.getSheetAccessoriesList(sql);
              if ((linkList != null) && (linkList.size() > 0)) {
                Map linkMap = (Map)linkList.get(0);
                valueMap.put("dealPerformer", subroleid);
                valueMap.put("dealPerformerLeader", subroleid);
                valueMap.put("dealPerformerType", "subrole");
                valueMap.put("toOrgRoleId", StaticMethod.nullObject2String(linkMap.get("operateRoleId")));
                valueMap.put("nodeAcceptLimit", StaticMethod.nullObject2String(linkMap.get("nodeAcceptLimit")));
                valueMap.put("nodeCompleteLimit", StaticMethod.nullObject2String(linkMap.get("nodeCompleteLimit")));
              }
            }
            sheetMap1.put("main", main);
            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
            columnMap.put("selfSheet", sheetMap1);
            valueMap.put("correlationKey", StaticMethod.nullObject2String(main.getCorrelationKey()));
            sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);
            result = "Status=0;Errlist=";
			
          }
          else {
            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于处理中状态,无法进行工单驳回操作！";
            return result;
          }
        }
        else {
          result = "Status=-1;Errlist=工单驳回接口传入参数不正确,请查证！";
          return result;
        }
      } else {
        result = "Status=-1;Errlist=工单驳回接口没有传入opDetail参数,请查证！";
        return result;
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;Errlist=工单驳回接口出错！详细信息为" + e.getMessage();
    }
    System.out.println("rejectSheet="+result);
    return result;
  }
  
  /**
   * 工单流转环节接口
   * @param opDetail
   * @return
   */
  public String getSheetHistoryService(String opDetail)
  {
    String result = "";
    HashMap opDetailMap = new HashMap();
    InterfaceUtil interfaceUtil = new InterfaceUtil();
    IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
    HandGroupComplaintUtil hoUtil = new HandGroupComplaintUtil();
    try {
      String nodeName = "historyList";
      System.out.println("HandGroupComplaint2EomsService.getSheetListService内对应的nodeName======" + nodeName);
      if ((opDetail != null) && (!"".equals(opDetail))) {
        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
        String sheetId = StaticMethod.nullObject2String(opDetailMap.get("Sheet_id"));
        GroupComplaintMain main = (GroupComplaintMain)mainservice.getMainBySheetId(sheetId);
        String mainId = StaticMethod.nullObject2String(main.getId());
        String sql = "select operateuserid,operatercontact,accepttime,operatetime,activetemplateid,operatetype,	case when (case when operatetype=61 then acceptflag else completeflag end)=2 then 'Y' else 'N' end as limitFlag	from  groupcomplaint_link    where mainid='"+mainId+"'  order by operatetime";
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List taskList = services.getSheetAccessoriesList(sql);
        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handgroupcomplaint.xml"), nodeName, "");
        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
        System.out.println("getSheetHistoryService="+result);
        return result;
      }
      result = "Status=-1;sheetDetail=;Errlist=工单流转环节服务接口没有传入opDetail参数,请查证！";
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=工单流转环节服务接口出错！详细信息为" + e.getMessage();
    }
    System.out.println("getSheetHistoryService="+result);
    return result;
  }

 
  
  /**
   * 工单综合查询
   * @param opDetail
   * @return
   */
  public String getInquirySheet(String opDetail)
  {
    String result = "";
    HashMap opDetailMap = new HashMap();
    InterfaceUtil interfaceUtil = new InterfaceUtil();
    HandGroupComplaintUtil hoUtil = new HandGroupComplaintUtil();
    try
    {
      String nodeName = "inquirySheet";
      System.out.println("HandGroupComplaint2EomsService.getInquirySheet内对应的nodeName======" + nodeName);
      if ((opDetail != null) && (!"".equals(opDetail))) {
        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
        
        String sTime = StaticMethod.nullObject2String(opDetailMap.get("time_start"));//派单开始时间
        String eTime = StaticMethod.nullObject2String(opDetailMap.get("time_end"));//判断结束时间
        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records"));
        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
        if (end_records == 0) {
        	end_records = 15;
        }
        
        String Order_Id = StaticMethod.nullObject2String(opDetailMap.get("Order_Id"));//工单编号
        String Order_Title = StaticMethod.nullObject2String(opDetailMap.get("Order_Title"));//工单主题
        
        String hql = "SELECT title,sheetId,dealTime1 FROM groupcomplaint_main WHERE deleted = '0' ";
        if(!"".equals(sTime)){
        	hql = hql + " and sendtime >=to_date('" + sTime + "','yyyy-MM-dd HH24:mi:ss')";
        }
        if(!"".equals(eTime)){
        	hql = hql + " and sendtime <=to_date('" + eTime + "','yyyy-MM-dd HH24:mi:ss')";
        }
        
        if(!"".equals(Order_Id)){
        	hql = hql + " and sheetId like '%"+Order_Id+"%'";
        }
        
        if(!"".equals(Order_Title)){
        	hql = hql + " and title like '%"+Order_Title+"%'";
        }
        

        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String sheet_totals = "";
        
        
        
        
        String countSql = "select count(*) num  from ("+hql+")" ;
        System.out.println(countSql);
        List countList = services.getSheetAccessoriesList(countSql);
        Map countMap = (Map)countList.get(0);
        sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));

        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ >= " + start_records;
        List taskList = services.getSheetAccessoriesList(sql);
        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handgroupcomplaint.xml"), nodeName, sheet_totals);
        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
        System.out.println("getInquirySheet="+result);
        return result;
      }
      result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口没有传入opDetail参数,请查证！";
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口出错！详细信息为" + e.getMessage();
    }
    System.out.println("getInquirySheet="+result);
    return result;
  }
  /**
   * 已办工单详情
   * @param opDetail
   * @return
   */
  public String getFinishSheetListService(String opDetail)
  {
    String result = "";
    HashMap opDetailMap = new HashMap();
    InterfaceUtil interfaceUtil = new InterfaceUtil();
    HandGroupComplaintUtil hoUtil = new HandGroupComplaintUtil();
    try {
      	
      String nodeName = "finishSheetList";
      System.out.println("HandGroupComplaint2EomsService.getFinishSheetListService内对应的nodeName======" + nodeName);
      if ((opDetail != null) && (!"".equals(opDetail))) {
        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
        String user_id = StaticMethod.nullObject2String(opDetailMap.get("User_id"));
        String time_start = StaticMethod.nullObject2String(opDetailMap.get("Start_time"));
        String time_end = StaticMethod.nullObject2String(opDetailMap.get("End_time"));
        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records"));
        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
        
        String hql = "  select main.Sheetid,main.Title,main.dealTime1,main.Status  FROM GROUPCOMPLAINT_MAIN  MAIN,  GROUPCOMPLAINT_TASK  TASK WHERE TASK.SHEETKEY = MAIN.ID AND ((TASK.TASKOWNER = '"+user_id+"') OR  TASK.TASKOWNER IN (SELECT USERREFROLE.SUBROLEID  FROM TAW_SYSTEM_USERREFROLE  USERREFROLE  WHERE USERREFROLE.USERID = '"+user_id+"')) AND (TASK.TASKSTATUS = '5' OR TASK.IFWAITFORSUBTASK = 'true') AND MAIN.DELETED <> '1' AND MAIN.STATUS = 0  AND task.TASKNAME = 'SecondExcuteHumTask' ";
        if (end_records == 0) {
          end_records = 15;
        }
        
        if (!"".equals(time_start))
          {
        	 hql = hql+" and main.sendtime >=to_date('" + time_start + "','yyyy-MM-dd HH24:mi:ss')";
          }
          if (!"".equals(time_end))
          {
        	  hql = hql + " and main.sendtime <=to_date('" + time_end + "','yyyy-MM-dd HH24:mi:ss')";
          }
        
          hql = hql + " ORDER BY MAIN.SENDTIME DESC";
        
        String sheet_totals = "";
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");

        String countSql = "select count(*) num  from ("+hql+")";
        System.out.println("------已办列表数量SQL---------ph-----------------" + countSql);
        List countList = services.getSheetAccessoriesList(countSql);
        Map countMap = (Map)countList.get(0);
        sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
        System.out.println("------已办列表数量---------ph-----------------" + sheet_totals);

        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ >= " + start_records;
        List taskList = services.getSheetAccessoriesList(sql);

        for (int i = 0; i < taskList.size(); i++) {
          Map map = (Map)taskList.get(i);
          if ((map.get("status") != null) && ("0".equals(map.get("status").toString())))
            map.put("status", "运行中");
          else if ((map.get("status") != null) && ("1".equals(map.get("status").toString())))
            map.put("status", "已归档");
          else if ((map.get("status") != null) && (!"".equals(map.get("status")))) {
            map.put("status", "其他");
          }
        }
        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handgroupcomplaint.xml"), nodeName, sheet_totals);
        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
        System.out.println("getFinishSheetListService="+result);
        return result;
      }
      result = "Status=-1;sheetDetail=;Errlist=已办工单列表查询接口没有传入opDetail参数,请查证！";
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=已办工单列表查询接口出错！详细信息为" + e.getMessage();
    }
    System.out.println("getFinishSheetListService="+result);
    return result;
  }

}