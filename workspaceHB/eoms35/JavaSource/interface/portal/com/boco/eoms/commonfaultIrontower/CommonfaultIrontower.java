package com.boco.eoms.commonfaultIrontower;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommonfaultIrontower
{
 


  


  public String rejectSheet(Map valueMap)
  {
    String result = "";
    try {
      ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
      ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
      ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
      HashMap columnMap = new HashMap();
      HashMap sheetMap1 = new HashMap();
      if(valueMap != null) {
    	  
        String sheet_id = StaticMethod.nullObject2String(valueMap.get("sheetId"));
        if ((sheet_id != null) && (!"".equals(sheet_id))) {
          CommonFaultMain main = (CommonFaultMain)mainservice.getMainBySheetId(sheet_id);
          int status = StaticMethod.nullObject2int(main.getStatus());

          if (status != 0) {
            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行驳回操作！";
            return result;
          }
          String sheetKey = StaticMethod.nullObject2String(main.getId());
          String condition = " sheetKey = '" + sheetKey + "' and taskstatus in ('2','8')  and taskName ='SecondExcuteHumTask'";

          List taskList = taskservice.getTasksByCondition(condition);
          if ((taskList != null) && (taskList.size() > 0)) {
            CommonFaultTask task = (CommonFaultTask)taskList.get(0);
            valueMap.put("operateDeptId", StaticMethod.nullObject2String(valueMap.get("operateDeptId")));
            valueMap.put("operaterContact", StaticMethod.nullObject2String(valueMap.get("operaterContact")));
            valueMap.put("operateUserId", StaticMethod.nullObject2String(valueMap.get("operateUserId")));
            valueMap.put("operateRoleId", StaticMethod.nullObject2String(valueMap.get("operateRoleId")));
            valueMap.put("mainId", main.getId());
            valueMap.put("aiid", task.getId());
            valueMap.put("piid", task.getProcessId());
            valueMap.put("operateTime", new Date());
            valueMap.put("acceptTimeLimit", task.getAcceptTimeLimit());
            valueMap.put("completeTimeLimit", task.getCompleteTimeLimit());
            valueMap.put("operateType", "4");
            valueMap.put("hasNextTaskFlag", "true");
            
            Map linkMap = new HashMap();
            if ("SecondExcuteHumTask".equals(task.getTaskName())) {
              valueMap.put("phaseId", "FirstExcuteTask");
              IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
              String sql = "select * from commonfault_link link where link.mainid='" + 
                sheetKey + "' and link.operatetype='1' and link.activetemplateid='FirstExcuteHumTask' " + 
                "order by link.operatetime desc ";
              System.out.println("驳回操作查询T1操作人sql====" + sql);
              List linkList = services.getSheetAccessoriesList(sql);
             
              if ((linkList != null) && (linkList.size() > 0)) {
                linkMap = (Map)linkList.get(0);
                valueMap.put("dealPerformer", "8aa0813b1c6f2386011c6f39c8350027");
                valueMap.put("dealPerformerLeader", "8aa0813b1c6f2386011c6f39c8350027");
                valueMap.put("dealPerformerType", "subrole");
                valueMap.put("toOrgRoleId", StaticMethod.nullObject2String(linkMap.get("operateRoleId")));
                valueMap.put("nodeAcceptLimit", StaticMethod.nullObject2String(linkMap.get("nodeAcceptLimit")));
                valueMap.put("nodeCompleteLimit", StaticMethod.nullObject2String(linkMap.get("nodeCompleteLimit")));
              }
            }
           
            
//          记录驳回次数 by lyg
            String mainT2RejectionNum = StaticMethod.nullObject2String(main.getMainT2RejectionNum());
            System.out.println("mainT2RejectionNum==liu=iront=liu=="+mainT2RejectionNum);
            if("".equals(mainT2RejectionNum)){
				mainT2RejectionNum = "1";
				System.out.println("mainT2RejectionNum==if1=iront=liu=="+mainT2RejectionNum);
            }
            //驳回后将 标记状态值为 空
//            main.setMainTownerFlag("");
//            main.setMainIFTowner("3");//驳回后的铁塔工单，该字段用于在归档时是否调用铁塔接口，1表示铁塔建单未成功，2表示铁塔简单成功，3表示铁塔T2驳回T1
            valueMap.put("mainTownerFlag", "");
            valueMap.put("mainIFTowner", "3");
            main.setMainT2RejectionNum(mainT2RejectionNum);
            sheetMap1.put("main", main);
            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
            columnMap.put("selfSheet", sheetMap1);
            valueMap.put("correlationKey", StaticMethod.nullObject2String(main.getCorrelationKey()));
            
            sm.dealSheet(sheetKey, valueMap, columnMap, StaticMethod.nullObject2String(valueMap.get("operateUserId")), taskservice);
            result = "Status=0;Errlist=";
          
          }else {
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
  

}