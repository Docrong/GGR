package com.boco.eoms.handheldoperation;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;
import com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.util.InterfaceUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandheldOperation2EomsService1
{
  public String getSheetInfoService(String opDetail)
  {
    String result = "";
    String packinfo = "";
    try {
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
      ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
      ITawSystemAreaManager areaservice = (ITawSystemAreaManager)ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
      CommonFaultMain main = new CommonFaultMain();

      String nodeName = "dataService";
      System.out.println("HandheldOperation2EomsService.getSheetInfoService内对应的nodeName======" + nodeName);
      HashMap sheetMap = new HashMap();
      HandheldOperationUtil hoUtil = new HandheldOperationUtil();

      if ((opDetail != null) && (!"".equals(opDetail)))
      {
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        if ((sheet_id != null) && (!"".equals(sheet_id))) {
          main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
          if (main != null) {
            String toDeptId = main.getToDeptId();
            if (!"".equals(toDeptId)&& toDeptId != null) {
              TawSystemArea area = areaservice.getAreaByAreaId(toDeptId);
              String areaName = area.getAreaname();
              main.setToDeptId(areaName);
            }
            Map mainMap = SheetBeanUtils.bean2Map(main);

            if ("1".equals(StaticMethod.nullObject2String(main.getStatus()))) {
              mainMap.put("taskStatus", "已归档");
            } else {
              String condition = " sheetId = '" + sheet_id + "' and taskstatus in ('2','8','5')";
              List taskList = taskservice.getTasksByCondition(condition);
              if ((taskList != null) && (taskList.size() > 0)) {
                CommonFaultTask task = (CommonFaultTask)taskList.get(0);
                String taskStatus = StaticMethod.nullObject2String(task.getTaskStatus());
                if ("2".equals(taskStatus))
                  mainMap.put("taskStatus", "待受理");
                else if ("8".equals(taskStatus))
                  mainMap.put("taskStatus", "处理中");
              }
              else {
                result = "Status=-1;sheetDetail=;Errlist=工单数据服务接口没有找到工单流水号为 " + sheet_id + " 的工单未找到或不存在,请查证！";
                return result;
              }
            }

            if ("1030101".equals(main.getMainIsPack())) {
            	System.out.println("~~~~~~~有告警追單信息");
    			ICommonFaultPackMainManager packservice = (ICommonFaultPackMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultPackMainManager");
    			String mainId = main.getId();
    			List packList = packservice.getListByMainId(mainId);
    			for (int i = 0; i < packList.size(); i++)
    			{
    				CommonFaultPackMain packmain = (CommonFaultPackMain)packList.get(i);
    				int j =i+1;
    				packinfo = packinfo +"\n"+j+":title:" + packmain.getTitle() + "\ngenerantTime:" + packmain.getMainFaultGenerantTime() + "\nsolveTime:" + packmain.getMainAlarmSolveDate() + "\n";
    			}

    			System.out.println("---告警追單信息：" + packinfo);
            }
            mainMap.put("packinfo", packinfo);
            
            
            String hql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.finishList");
            hql = ExcelConverterUtil.replaceAll(hql, "@id@", sheet_id );
            IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            List finishList = services.getSheetAccessoriesList(hql);
            if(finishList.size()>0){
            	Map finishMap = (Map)finishList.get(0);
                String linkFaultReasonSort = StaticMethod.nullObject2String(finishMap.get("linkFaultReasonSort"));            
                String linkFaultReasonSulinksection = StaticMethod.nullObject2String(finishMap.get("linkfaultreasonsubsection"));
                String linkFaultReasonSulinksectionTwo = StaticMethod.nullObject2String(finishMap.get("linkfaultreasonsubsectiontwo"));
                String faultdealdesc = StaticMethod.nullObject2String(finishMap.get("faultdealdesc"));
                String faultdealtime = StaticMethod.nullObject2String(finishMap.get("faultdealtime"));          
                String linkDealStep = StaticMethod.nullObject2String(finishMap.get("linkDealStep"));
                
                mainMap.put("linkFaultReasonSort", linkFaultReasonSort);
                mainMap.put("linkfaultreasonsubsection", linkFaultReasonSulinksection);
                mainMap.put("linkfaultreasonsubsectiontwo", linkFaultReasonSulinksectionTwo);
                mainMap.put("faultdealdesc", faultdealdesc);
                mainMap.put("faultdealtime", faultdealtime);
                mainMap.put("linkDealStep", linkDealStep);
            }
            
            opDetail = hoUtil.getXmlFromMap(mainMap, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName);
            result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
            return result;
          }

          result = "Status=-1;sheetDetail=;Errlist=工单数据服务接口没有找到工单流水号为 " + sheet_id + " 的工单未找到或不存在,请查证！";
          return result;
        }

        result = "Status=-1;sheetDetail=;Errlist=工单数据服务接口没有找到对应工单流水号Sheet_id的参数,请查证！";
        return result;
      }

      result = "Status=-1;sheetDetail=;Errlist=工单数据服务接口没有传入参数,请查证！";

      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=工单数据服务接口出错！详细信息为" + e.getMessage();
    }return result;
  }

  public String acceptSheet(String opDetail)
  {
    String result = "";
    try {
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      HashMap sheetMap = new HashMap();
      HashMap columnMap = new HashMap();
      HashMap sheetMap1 = new HashMap();
      Map valueMap = new HashMap();
      ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
      ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
      ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
      String operateUserId = "";
      InterfaceUtilProperties properties = new InterfaceUtilProperties();

      String nodeName = "acceptSheet";
      System.out.println("HandheldOperation2EomsService.acceptSheet内对应的nodeName======" + nodeName);

      if ((opDetail != null) && (!"".equals(opDetail))) {
        String filePath = StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml");
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

        valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
        if ((sheet_id != null) && (!"".equals(sheet_id))) {
          CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
          int status = StaticMethod.nullObject2int(main.getStatus());

          if (status != 0) {
            result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
            return result;
          }
          String sheetKey = StaticMethod.nullObject2String(main.getId());
          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='SecondExcuteHumTask' ";
          List taskList = taskservice.getTasksByCondition(condition);

          if ((taskList != null) && (taskList.size() > 0)) {
            CommonFaultTask task = (CommonFaultTask)taskList.get(0);
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
    }return result;
  }

  public String dealSheet(String opDetail,String attachRef)
  {
    String result = "";
    try {
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
      ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
      ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
      HashMap sheetMap = new HashMap();
      HashMap columnMap = new HashMap();
      HashMap sheetMap1 = new HashMap();
      String operateUserId = "";
      InterfaceUtilProperties properties = new InterfaceUtilProperties();
      String nodeName = "dealSheet";
      String alarmStatus ="";
      System.out.println("HandheldOperation2EomsService.dealSheet内对应的nodeName======" + nodeName);
      String filePath = StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml");
      if ((opDetail != null) && (!"".equals(opDetail))) {
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);

        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
        String operate_time = StaticMethod.nullObject2String(sheetMap.get("Operate_time"));
  //      String attachRef =StaticMethod.nullObject2String(sheetMap.get("attachRef"));
   /*   ---add by ph --*/
        if ((attachRef != null) && (!"".equals(attachRef))) {
        	System.out.println("--attachRef-"+attachRef);
        	 InterfaceUtil interfaceUtilattach = new InterfaceUtil();
        	 List attach = interfaceUtilattach.getAttachRefFromXml(attachRef);	
        	 WPSEngineServiceMethod wm = new WPSEngineServiceMethod();
        	 String attachId =  wm.getAttach(attach, "commonfault");     	
    		System.out.println("attachId=" + attachId);
    			if (attachId != null && attachId.length() > 0){
    				valueMap.put("sheetAccessories", attachId);
    			}
        }
        
        
  /*----------end by ph -*/      
        if ((sheet_id != null) && (!"".equals(sheet_id))) {
          CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
          int status = StaticMethod.nullObject2int(main.getStatus());

          if (status != 0) {
            result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
            return result;
          }
          String sheetKey = StaticMethod.nullObject2String(main.getId());
          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='SecondExcuteHumTask' ";
          List taskList = taskservice.getTasksByCondition(condition);

          if ((taskList != null) && (taskList.size() > 0)) {
            CommonFaultTask task = (CommonFaultTask)taskList.get(0);
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
              valueMap.put("operaterContact", user.getMobile());
            }

            valueMap.put("operateUserId", operateUserId);
            valueMap.put("operateRoleId", task.getOperateRoleId());
            valueMap.put("mainId", main.getId());
            valueMap.put("operateTime", operate_time);
            valueMap.put("aiid", task.getId());
            Date mainFaultGenerantTime = main.getMainFaultGenerantTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(mainFaultGenerantTime);
            cal.add(12, 10);
            valueMap.put("faultdealTime", cal.getTime());
            TawSystemUser tawsystemuser = usemgr.getUserByuserid(operateUserId);
            valueMap.put("faultTreatment", StaticMethod.nullObject2String(tawsystemuser.getUsername()));
            valueMap.put("linkDealdesc", StaticMethod.nullObject2String(valueMap.get("linkdealdesc")));
            valueMap.put("selectStep", StaticMethod.nullObject2String(valueMap.get("linkdealdesc")));
            Date mainAlarmSolveDate = main.getMainAlarmSolveDate();
            Date mainCheckTime = main.getMainCheckTime();

            if (mainAlarmSolveDate != null) {
              valueMap.put("linkFaultAvoidTime", mainAlarmSolveDate);
              valueMap.put("linkOperRenewTime", mainAlarmSolveDate);
            }
            else if (mainCheckTime != null) {
              valueMap.put("linkFaultAvoidTime", mainCheckTime);
              valueMap.put("linkOperRenewTime", mainCheckTime);
            }

            Map sheetMainMap = SheetBeanUtils.bean2Map(main);
            valueMap.putAll(sheetMainMap);
            sheetMap1.put("main", main);
            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
            columnMap.put("selfSheet", sheetMap1);
            alarmStatus ="处理完成";
            String mainAlarmId = StaticMethod.nullObject2String(main.getMainAlarmId());
            String mainFaultResponseLevel = StaticMethod.nullObject2String(main.getMainFaultResponseLevel());
            String linkDealStep = StaticMethod.nullObject2String(valueMap.get("linkdealdesc"));
            String sendContact = StaticMethod.nullObject2String(main.getSendContact());
            Date operateTime = SheetUtils.stringToDate(operate_time);
            boolean inrule = false;
            String obj = "";
            System.out.println("lizhi:mainAlarmSolveDate=" + mainAlarmSolveDate + "sendContact=" + sendContact + "operateTime=" + operateTime + "mainFaultResponseLevel=" + mainFaultResponseLevel + "mainAlarmId=" + mainAlarmId + "linkDealStep=" + linkDealStep);
            if ((mainAlarmSolveDate != null) && (!"".equals(sendContact)) && (operateTime.after(mainAlarmSolveDate)) && (!"101030401".equals(mainFaultResponseLevel)))
            {
              ICommonFaultAutoManager autoservice = (ICommonFaultAutoManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
              List steplist = autoservice.getStepsbycondition(mainAlarmId, linkDealStep);
              if ((steplist.size() > 0) && (!"其它".equals(linkDealStep)) && (!"其他".equals(linkDealStep)))
              {
                inrule = true;
                Object[] object = (Object[])steplist.get(0);
                obj = String.valueOf(object[2]);
                System.out.println("满足自动归档——————");
                valueMap.put("hasNextTaskFlag", "true");
                valueMap.put("phaseId", "");
              }
              else {
                inrule = false;
              }
            }
            sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);          
            
            if (inrule)
              {
                System.out.println("---自动归档---mainAlarmId=" + mainAlarmId + "--mainFaultRespondseLevel=" + mainFaultResponseLevel + "--linkDealStep=" + linkDealStep);

           
                Calendar calendar = Calendar.getInstance();
                CommonFaultLink linkbean = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
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
                if (main != null)
                {
                  main.setEndResult(obj);
                  main.setStatus(new Integer(1));
                  main.setHoldStatisfied(Integer.valueOf(1030301));
                  mainservice.addMain(main);
                }
                CommonFaultTask taskhold = new CommonFaultTask();
                try
                {
                  taskhold.setId(UUIDHexGenerator.getInstance().getID());
                }
                catch (Exception e3)
                {
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
                alarmStatus ="已归档";
              }
            

            if (alarmStatus.length() >0){
            CommonFaultBO.updateAlarm(sheetKey, alarmStatus);
            }
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
    }return result;
  }

  public String getSheetListService(String opDetail)
  {
    String result = "";
    HashMap opDetailMap = new HashMap();
    InterfaceUtil interfaceUtil = new InterfaceUtil();
    ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
    try {
      String nodeName = "sheetList";
      System.out.println("HandheldOperation2EomsService.getSheetListService内对应的nodeName======" + nodeName);
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

        String hql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.undoListSql");
        hql = ExcelConverterUtil.replaceAll(hql, "@userId@", user_id);
        hql = ExcelConverterUtil.replaceAll(hql, "@deptId@", user.getDeptid());
        String countSql = "select count(distinct main.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ > " + start_records;
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List taskList = services.getSheetAccessoriesList(sql);
        List countList = services.getSheetAccessoriesList(countSql);
        Map countMap = (Map)countList.get(0);
        String sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, sheet_totals);
        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
        return result;
      }
      result = "Status=-1;sheetDetail=;Errlist=工单列表查询接口没有传入opDetail参数,请查证！";
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=工单列表查询接口出错！详细信息为" + e.getMessage();
    }return result;
  }

  public String getCCSheetListService(String opDetail)
  {
    String result = "";
    HashMap opDetailMap = new HashMap();
    InterfaceUtil interfaceUtil = new InterfaceUtil();
    ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
    try {
      String nodeName = "sheetList";
      System.out.println("HandheldOperation2EomsService.getSheetListService内对应的nodeName======" + nodeName);
      if ((opDetail != null) && (!"".equals(opDetail))) {
        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
        String user_id = StaticMethod.nullObject2String(opDetailMap.get("User_id"));
        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records")) - 1;
        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
        TawSystemUser user = userMgr.getUserByuserid(user_id);
        System.out.println("--------ph:user_id-----------" + user_id);
        String dept_id = StaticMethod.nullObject2String(user.getDeptid());
        System.out.println("--------ph:deptid-----------" + dept_id);
        if (("".equals(user_id)) || (user_id == null) || (dept_id == null) || ("".equals(dept_id))) {
          result = "Status=-1;sheetDetail=;Errlist=工单列表查询接口出错！用户名在EOMS中不存在。";
          return result;
        }

        String hql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.ccundoListSql");
        hql = ExcelConverterUtil.replaceAll(hql, "@userId@", user_id);
        hql = ExcelConverterUtil.replaceAll(hql, "@deptId@", user.getDeptid());
        String countSql = "select count(distinct main.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ > " + start_records;
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List taskList = services.getSheetAccessoriesList(sql);
        List countList = services.getSheetAccessoriesList(countSql);
        Map countMap = (Map)countList.get(0);
        String sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, sheet_totals);
        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
        return result;
      }
      result = "Status=-1;sheetDetail=;Errlist=工单抄送列表查询接口没有传入opDetail参数,请查证！";
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=工单抄送列表查询接口出错！详细信息为" + e.getMessage();
    }return result;
  }

  public String rejectSheet(String opDetail)
  {
    String result = "";
    try {
      ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
      ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
      ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
      HashMap sheetMap = new HashMap();
      HashMap columnMap = new HashMap();
      HashMap sheetMap1 = new HashMap();
      InterfaceUtilProperties properties = new InterfaceUtilProperties();
      String nodeName = "rejectSheet";
      System.out.println("HandheldOperation2EomsService.dealSheet内对应的nodeName======" + nodeName);
      String filePath = StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml");
      if ((opDetail != null) && (!"".equals(opDetail))) {
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
        String operate_time = StaticMethod.nullObject2String(sheetMap.get("Reject_time"));
        if ((sheet_id != null) && (!"".equals(sheet_id))) {
          CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
          int status = StaticMethod.nullObject2int(main.getStatus());

          if (status != 0) {
            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行驳回操作！";
            return result;
          }
          String sheetKey = StaticMethod.nullObject2String(main.getId());
          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='SecondExcuteHumTask'";

          List taskList = taskservice.getTasksByCondition(condition);
          if ((taskList != null) && (taskList.size() > 0)) {
            CommonFaultTask task = (CommonFaultTask)taskList.get(0);
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
              valueMap.put("phaseId", "FirstExcuteTask");
              IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
              String sql = "select * from commonfault_link link where link.mainid='" + 
                sheetKey + "' and link.operatetype='1' and link.activetemplateid='FirstExcuteHumTask' " + 
                "order by link.operatetime desc ";
              System.out.println("驳回操作查询T1操作人sql====" + sql);
              List linkList = services.getSheetAccessoriesList(sql);
              if ((linkList != null) && (linkList.size() > 0)) {
                Map linkMap = (Map)linkList.get(0);
                valueMap.put("dealPerformer", "8aa0813b1c6f2386011c6f39c8350027");
                valueMap.put("dealPerformerLeader", "8aa0813b1c6f2386011c6f39c8350027");
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
    }return result;
  }

  public String getSheetHistoryService(String opDetail)
  {
    String result = "";
    HashMap opDetailMap = new HashMap();
    InterfaceUtil interfaceUtil = new InterfaceUtil();
    ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
    try {
      String nodeName = "historyList";
      System.out.println("HandheldOperation2EomsService.getSheetListService内对应的nodeName======" + nodeName);
      if ((opDetail != null) && (!"".equals(opDetail))) {
        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
        String sheetId = StaticMethod.nullObject2String(opDetailMap.get("Sheet_id"));
        CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheetId);
        String mainId = StaticMethod.nullObject2String(main.getId());
        String sql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.historySql");
        sql = ExcelConverterUtil.replaceAll(sql, "@mainid@", mainId);
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List taskList = services.getSheetAccessoriesList(sql);
        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, "");
        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
        return result;
      }
      result = "Status=-1;sheetDetail=;Errlist=工单流转环节服务接口没有传入opDetail参数,请查证！";
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=工单流转环节服务接口出错！详细信息为" + e.getMessage();
    }return result;
  }

  public String csSheet(String opDetail)
  {
    String result = "";
    ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
    ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
    ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
    InterfaceUtilProperties properties = new InterfaceUtilProperties();
    System.out.println("-----工单抄送接口服务调用开始----------");
    try {
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      HashMap sheetMap = new HashMap();
      HashMap columnMap = new HashMap();
      HashMap sheetMap1 = new HashMap();
      String nodeName = "csSheet";
      System.out.println("HandheldOperation2EomsService.dealSheet内对应的nodeName======" + nodeName);
      String filePath = StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml");
      if ((opDetail != null) && (!"".equals(opDetail))) {
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
        CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
        String mainId = StaticMethod.nullObject2String(main.getId());
        String condition = " sheetKey = '" + mainId + "' and taskstatus ='8'";

        List taskList = taskservice.getTasksByCondition(condition);
        if ((taskList != null) && (taskList.size() > 0)) {
          CommonFaultTask task = (CommonFaultTask)taskList.get(0);
          String operateRoleId = StaticMethod.nullObject2String(task.getOperateRoleId());
          valueMap.put("operateroleid", operateRoleId);
        }
        ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        TawSystemUser user = userMgr.getUserByuserid(operate_userid);
        if (user != null) {
          valueMap.put("operateuserid", user.getUserid());
          valueMap.put("operateDeptId", user.getDeptid());
          valueMap.put("operaterContact", user.getMobile());
        }
        valueMap.put("operateType", "-10");
        valueMap.put("processTemplateName", "CommonFaultMainFlowProcess");
        valueMap.put("activetemplateid", "SecondExcuteHumTask");
        int status = StaticMethod.nullObject2int(main.getStatus());
        if (status != 0) {
          result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行抄送操作！";
          return result;
        }
        String roleId = StaticMethod.nullObject2String(sheetMap.get("Role_id"));
        sheetMap1.put("main", main);
        sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
        sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
        columnMap.put("selfSheet", sheetMap1);
        HandheldOperationUtil mgr = new HandheldOperationUtil();
        if (roleId != "") {
          valueMap.put("copyPerformer", roleId);
          valueMap.put("copyPerformerLeader", roleId);
          valueMap.put("copyPerformerType", "subrole");
          mgr.newSaveNonFlowData(mainId, valueMap, columnMap, mainservice, taskservice, linkservice);
          result = "Status=0;Errlist=";
        } else {
          result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单传入的抄送班组为空,无法进行工单抄送操作！";
          return result;
        }
      }
      else {
        result = "Status=-1;Errlist=工单抄送接口没有传入opDetail参数,请查证！";
        return result;
      }
      System.out.println("-----工单抄送接口服务调用结束----------");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;Errlist=工单抄送接口出错！详细信息为" + e.getMessage();
    }return result;
  }

  public String getInquirySheet(String opDetail)
  {
    String result = "";
    HashMap opDetailMap = new HashMap();
    InterfaceUtil interfaceUtil = new InterfaceUtil();
    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
    try
    {
      String nodeName = "inquirySheet";
      System.out.println("HandheldOperation2EomsService.getInquirySheet内对应的nodeName======" + nodeName);
      if ((opDetail != null) && (!"".equals(opDetail))) {
        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");

        String sTime = StaticMethod.nullObject2String(opDetailMap.get("time_start"));
        String eTime = StaticMethod.nullObject2String(opDetailMap.get("time_end"));
        if ("".equals(sTime))
        {
          Calendar canlendar = Calendar.getInstance();
          canlendar.add(5, -3);
          sTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(canlendar.getTime());
        }
        if ("".equals(eTime))
        {
          Date date = new Date();
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          eTime = df.format(date);
        }
        String netsort_one = StaticMethod.nullObject2String(opDetailMap.get("Netsort_one"));
        String group_name = StaticMethod.nullObject2String(opDetailMap.get("GROUP_NAME"));
        String user_t2id = StaticMethod.nullObject2String(opDetailMap.get("USER_T2id"));
        String sheet_status = StaticMethod.nullObject2String(opDetailMap.get("Sheet_status"));
        String fault_responseLevel = StaticMethod.nullObject2String(opDetailMap.get("Fault_ResponseLevel"));
        String formNo = StaticMethod.nullObject2String(opDetailMap.get("FormNo"));
        String formTitle = StaticMethod.nullObject2String(opDetailMap.get("FormTitle"));
        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records"));
        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
        if (end_records == 0) {
          end_records = 15;
        }
        String hql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.inquirySheetSql" + sheet_status);
        if ((hql == null) || ("".equals(hql))) {
          result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口没有传入工单状态=" + sheet_status + "有误,请查证！";
          return result;
        }
        if ((!"".equals(netsort_one)) && (!"-1".equals(netsort_one)))
          hql = ExcelConverterUtil.replaceAll(hql, "@mainnetsortone@", "and commonFaultMain.mainnetsortone='" + netsort_one + "'");
        else {
          hql = ExcelConverterUtil.replaceAll(hql, "@mainnetsortone@", "");
        }
        if ((!"".equals(fault_responseLevel)) && (!"-1".equals(fault_responseLevel)))
          hql = ExcelConverterUtil.replaceAll(hql, "@mainfaultresponselevel@", "and commonFaultMain.mainfaultresponselevel='" + fault_responseLevel + "'");
        else {
          hql = ExcelConverterUtil.replaceAll(hql, "@mainfaultresponselevel@", "");
        }
        if ((!"ALL".equals(group_name)) && (!"".equals(group_name)))
          hql = ExcelConverterUtil.replaceAll(hql, "@operateRoleId@", "and commonFaultLink.operateRoleId in ('" + group_name + "')");
        else {
          hql = ExcelConverterUtil.replaceAll(hql, "@operateRoleId@", "");
        }
        if ((!"ALL".equals(user_t2id)) && (!"".equals(user_t2id)))
          hql = ExcelConverterUtil.replaceAll(hql, "@operateUserId@", "and commonFaultLink.operateUserId in ('" + user_t2id + "')");
        else {
          hql = ExcelConverterUtil.replaceAll(hql, "@operateUserId@", "");
        }
        if ((!"".equals(formNo)) && (!"-1".equals(formNo)))
          hql = ExcelConverterUtil.replaceAll(hql, "@formNo@", "and commonFaultMain.sheetId like'%" + formNo + "%'");
        else {
          hql = ExcelConverterUtil.replaceAll(hql, "@formNo@", "");
        }
        if ((!"".equals(formTitle)) && (!"-1".equals(formTitle)))
          hql = ExcelConverterUtil.replaceAll(hql, "@formTitle@", "and commonFaultMain.title like'%" + formTitle + "%'");
        else {
          hql = ExcelConverterUtil.replaceAll(hql, "@formTitle@", "");
        }

        hql = ExcelConverterUtil.replaceAll(hql, "@timeStart@", "and commonFaultMain.sendtime >=to_date('" + sTime + "','yyyy-MM-dd HH24:mi:ss')");
        hql = ExcelConverterUtil.replaceAll(hql, "@timeEnd@", "and commonFaultMain.sendtime <=to_date('" + eTime + "','yyyy-MM-dd HH24:mi:ss')");
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String sheet_totals = "";

        String countSql = "select count(distinct commonFaultMain.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
        System.out.println(countSql);
        List countList = services.getSheetAccessoriesList(countSql);
        Map countMap = (Map)countList.get(0);
        sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));

        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ >= " + start_records;
        List taskList = services.getSheetAccessoriesList(sql);
        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, sheet_totals);
        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
        return result;
      }
      result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口没有传入opDetail参数,请查证！";
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口出错！详细信息为" + e.getMessage();
    }return result;
  }

  public String getFinishSheetListService(String opDetail)
  {
    String result = "";
    HashMap opDetailMap = new HashMap();
    InterfaceUtil interfaceUtil = new InterfaceUtil();
    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
    try {
      String nodeName = "finishSheetList";
      System.out.println("HandheldOperation2EomsService.getFinishSheetListService内对应的nodeName======" + nodeName);
      if ((opDetail != null) && (!"".equals(opDetail))) {
        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
        String user_id = StaticMethod.nullObject2String(opDetailMap.get("User_id"));
        String time_start = StaticMethod.nullObject2String(opDetailMap.get("Start_time"));
        String time_end = StaticMethod.nullObject2String(opDetailMap.get("End_time"));
        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records"));
        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
        String hql = XmlManage.getFile("/config/handheldoperation.xml").getProperty("sql.finishSheetList");
        if ("".equals(time_start))
        {
          Calendar canlendar = Calendar.getInstance();
          canlendar.add(5, -3);
          time_start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(canlendar.getTime());
        }
        if ("".equals(time_end))
        {
          Date date = new Date();
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          time_end = df.format(date);
        }
        if (end_records == 0) {
          end_records = 15;
        }
        hql = ExcelConverterUtil.replaceAll(hql, "@Start_time@", "and a.sendtime >=to_date('" + time_start + "','yyyy-MM-dd HH24:mi:ss')");
        hql = ExcelConverterUtil.replaceAll(hql, "@End_time@", "and a.sendtime <=to_date('" + time_end + "','yyyy-MM-dd HH24:mi:ss')");
        hql = ExcelConverterUtil.replaceAll(hql, "@User_id@", user_id);
        String sheet_totals = "";
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");

        String countSql = "select count(distinct a.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
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
        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, sheet_totals);
        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
        return result;
      }
      result = "Status=-1;sheetDetail=;Errlist=已办工单列表查询接口没有传入opDetail参数,请查证！";
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;sheetDetail=;Errlist=已办工单列表查询接口出错！详细信息为" + e.getMessage();
    }return result;
  }

  public String transferSheet(String opDetail)
  {
    String result = "";
    try {
      ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
      ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
      ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
      HashMap sheetMap = new HashMap();
      HashMap columnMap = new HashMap();
      HashMap sheetMap1 = new HashMap();
      InterfaceUtilProperties properties = new InterfaceUtilProperties();
      String nodeName = "transferSheet";
      System.out.println("HandheldOperation2EomsService.transferSheet内对应的nodeName======" + nodeName);
      String filePath = StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml");
      if ((opDetail != null) && (!"".equals(opDetail))) {
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
        String assignUserid = StaticMethod.nullObject2String(sheetMap.get("Assign_userid"));
        String roleId = StaticMethod.nullObject2String(sheetMap.get("Role_id"));
        if ((sheet_id != null) && (!"".equals(sheet_id))) {
          CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
          String mainId = StaticMethod.nullObject2String(main.getId());

          String condition = " sheetKey = '" + mainId + "' and taskstatus ='2' and taskname='SecondExcuteHumTask'";
          List taskList = taskservice.getTasksByCondition(condition);
          if ((taskList != null) && (taskList.size() > 0))
          {
            CommonFaultLink linkbean = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            TawSystemUser user = userMgr.getUserByuserid(operateUserId);
            Calendar calendar = Calendar.getInstance();
            linkbean.setId(UUIDHexGenerator.getInstance().getID());
            linkbean.setMainId(mainId);
            linkbean.setOperateTime(calendar.getTime());
            linkbean.setOperateType(new Integer(8));
            linkbean.setOperateDay(calendar.get(5));
            linkbean.setOperateMonth(calendar.get(2) + 1);
            linkbean.setOperateYear(calendar.get(1));
            linkbean.setOperateUserId(operateUserId);
            linkbean.setOperateDeptId(user.getDeptid());
            linkbean.setOperateRoleId(roleId);
            linkbean.setOperaterContact(user.getMobile());
            linkbean.setToOrgRoleId(assignUserid);
            linkbean.setToOrgUserId(assignUserid);
            linkbean.setToOrgType(new Integer(2));
            linkbean.setAcceptFlag(new Integer(1));
            linkbean.setCompleteFlag(new Integer(1));
            linkbean.setActiveTemplateId("SecondExcuteHumTask");
            linkservice.addLink(linkbean);

            CommonFaultTask task = (CommonFaultTask)taskList.get(0);
            String taskId = StaticMethod.nullObject2String(task.getId());
            String updateSql = "update commonfault_task set taskowner='" + assignUserid + "' where id='" + taskId + "'";
            IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            services.updateTasks(updateSql);

            valueMap.put("operateUserId", assignUserid);
            valueMap.put("operateRoleId", task.getOperateRoleId());
            valueMap.put("operateDeptId", user.getDeptid());
            valueMap.put("operaterContact", user.getMobile());
            valueMap.put("mainId", mainId);
            valueMap.put("aiid", taskId);
            valueMap.put("toOrgRoleId", task.getOperateRoleId());
            sheetMap1.put("main", main);
            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
            columnMap.put("selfSheet", sheetMap1);
            sm.claimTask(taskId, valueMap, columnMap, assignUserid);
            result = "Status=0;Errlist=";
          } else {
            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于T2未接单处理环节,无法进行工单指定操作！";
            return result;
          }
        } else {
          result = "Status=-1;Errlist=工单指定接口传入参数不正确,没有传入工单编号,请查证！";
          return result;
        }
      } else {
        result = "Status=-1;sheetDetail=;Errlist=工单指定接口没有传入opDetail参数,请查证！";
        return result;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String readSheet(String opDetail)
  {
    String result = "";
    ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
    ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
    ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
    System.out.println("-----工单已阅接口服务调用开始----------");
    try {
      InterfaceUtil interfaceUtil = new InterfaceUtil();
      HashMap sheetMap = new HashMap();
      String nodeName = "readSheet";
      System.out.println("HandheldOperation2EomsService.readSheet内对应的nodeName======" + nodeName);
      if ((opDetail != null) && (!"".equals(opDetail))) {
        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_id"));
        CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
        String mainId = StaticMethod.nullObject2String(main.getId());
        String condition = " sheetKey = '" + mainId + "' and taskstatus ='2' and taskname='cc'";

        List taskList = taskservice.getTasksByCondition(condition);
        if ((taskList != null) && (taskList.size() > 0)) {
          CommonFaultTask task = (CommonFaultTask)taskList.get(0);

          String taskId = StaticMethod.nullObject2String(task.getId());
          String updateSql = "update commonfault_task set taskstatus='5' where id='" + taskId + "'";
          IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
          services.updateTasks(updateSql);

          CommonFaultLink linkbean = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
          ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
          TawSystemUser user = userMgr.getUserByuserid(operateUserId);
          Calendar calendar = Calendar.getInstance();
          linkbean.setId(UUIDHexGenerator.getInstance().getID());
          linkbean.setMainId(mainId);
          linkbean.setOperateTime(calendar.getTime());
          linkbean.setOperateType(new Integer(-15));
          linkbean.setOperateDay(calendar.get(5));
          linkbean.setOperateMonth(calendar.get(2) + 1);
          linkbean.setOperateYear(calendar.get(1));
          linkbean.setOperateUserId(operateUserId);
          linkbean.setOperateDeptId(user.getDeptid());
          linkbean.setOperateRoleId(StaticMethod.nullObject2String(task.getOperateRoleId()));
          linkbean.setOperaterContact(user.getMobile());
          linkbean.setActiveTemplateId("cc");
          linkservice.addLink(linkbean);
          result = "Status=0;Errlist=";
        } else {
          result = "Status=-1;Errlist=工单流水号为" + sheet_id + "没有对应的抄送记录,请查证！";
          return result;
        }
      } else {
        result = "Status=-1;Errlist=工单已阅接口没有传入opDetail参数,请查证！";
        return result;
      }
      System.out.println("-----工单已阅接口服务调用结束----------");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result = "Status=-1;Errlist=工单已阅接口出错！详细信息为" + e.getMessage();
    }return result;
  }
}