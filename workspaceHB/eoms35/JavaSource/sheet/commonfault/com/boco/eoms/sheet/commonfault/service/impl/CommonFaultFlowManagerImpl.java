// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultFlowManagerImpl.java

package com.boco.eoms.sheet.commonfault.service.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.rule.service.RuleService;
import com.boco.eoms.commons.rule.service.RuleServiceFactory;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.service.impl.BusinessFlowServiceImpl;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultFlowManager;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonFaultFlowManagerImpl extends BusinessFlowServiceImpl
        implements ICommonFaultFlowManager {

    public CommonFaultFlowManagerImpl() {
    }

    public String completeHumanTask(String activityId, HashMap parameters, HashMap sessionMap)
            throws Exception {
        String sReturn = "";
        boolean autoHoldFlag = false;
        System.out.println("begin commonfalut task!");
        HashMap mainMap = (HashMap) parameters.get("main");
        HashMap linkMap = (HashMap) parameters.get("link");
        HashMap operateMap = (HashMap) parameters.get("operate");
        String stepId = StaticMethod.nullObject2String(linkMap.get("activeTemplateId"));
        String operateType = StaticMethod.nullObject2String(linkMap.get("operateType"));
        String phaseid = StaticMethod.nullObject2String(operateMap.get("phaseid"));
        String mainId = StaticMethod.nullObject2String(mainMap.get("id"));
        String holdStatisfied = "1030301";
        String endResult = "�ǳ�����";
        Map returnMap = new HashMap();
        if ((stepId.equals("FirstExcuteHumTask") || stepId.equals("SecondExcuteHumTask") || stepId.equals("ThirdExcuteHumTask")) && operateType.equals("46")) {
            try {
                Map input = new HashMap();
                Map params = new HashMap();
                params.putAll(mainMap);
                params.putAll(linkMap);
                params.putAll(operateMap);
                input.put("params", params);
                System.out.println(com.boco.eoms.sheet.base.util.flowdefine.xml.StaticMethod.getFilePathForUrl("classpath:config/autoHoldCommonfault-rule.xml"));
                RuleService ruleService = RuleServiceFactory.create(com.boco.eoms.sheet.base.util.flowdefine.xml.StaticMethod.getFilePathForUrl("classpath:config/autoHoldCommonfault-rule.xml"), "faultAutoHoldRule", input);
                Map output = ruleService.invoke(input);
                returnMap = (Map) output.get("returnMap");
                autoHoldFlag = Boolean.parseBoolean(StaticMethod.nullObject2String(returnMap.get("autoHoldFlag")));
                String temp1 = StaticMethod.nullObject2String(returnMap.get("autoHoldFlag"));
                String temp2 = StaticMethod.nullObject2String(returnMap.get("autoHoldFlag"));
                if (temp1.equals(""))
                    returnMap.put("holdStatisfied", holdStatisfied);
                if (temp2.equals(""))
                    returnMap.put("endResult", endResult);
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
            ILinkService linkService = (ILinkService) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
            IMainService mainService = (IMainService) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
            ITaskService taskService = (ITaskService) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
            if (autoHoldFlag) {
                operateMap.put("phaseId", "");
                operateMap.put("hasNextTaskFlag", "true");
                SheetBeanUtils.populateMap2Map(mainMap, returnMap);
                mainMap.put("status", Constants.SHEET_HOLD);
                mainMap.put("status", Constants.SHEET_HOLD);
                parameters.put("main", mainMap);
                parameters.put("link", linkMap);
                parameters.put("operate", operateMap);
                sReturn = super.completeHumanTask(activityId, parameters, sessionMap);
                BaseMain mainObject = mainService.getSingleMainPO(mainId);
                String tkid = "_AI:" + UUIDHexGenerator.getInstance().getID();
                ITask task = (ITask) taskService.getTaskModelObject().getClass().newInstance();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                HashMap mapObject = new HashMap();
                mapObject.put("id", tkid);
                mapObject.put("createTime", new Date());
                mapObject.put("taskStatus", "5");
                mapObject.put("processId", mainObject.getPiid());
                mapObject.put("sheetKey", mainId);
                mapObject.put("sheetId", mainObject.getSheetId());
                mapObject.put("title", mainObject.getTitle());
                mapObject.put("acceptTimeLimit", (Date) linkMap.get("nodeAcceptLimit"));
                mapObject.put("completeTimeLimit", (Date) linkMap.get("nodeCompleteLimit"));
                mapObject.put("preLinkId", StaticMethod.nullObject2String(linkMap.get("id")));
                mapObject.put("IfWaitForSubTask", "false");
                mapObject.put("taskDisplayName", "�鵵");
                mapObject.put("taskName", "HoldHumTask");
                mapObject.put("operateRoleId", mainObject.getSendRoleId());
                mapObject.put("taskOwner", mainObject.getSendUserId());
                mapObject.put("operateType", "subrole");
                mapObject.put("flowName", "CommonFaultMainFlowProcess");
                mapObject.put("sendTime", mainObject.getSendTime());
                SheetBeanUtils.populate(task, mapObject);
                task.setCreateDay(calendar.get(5));
                task.setCreateMonth(calendar.get(2) + 1);
                task.setCreateYear(calendar.get(1));
                taskService.addTask(task);
                BaseLink holdLink = (BaseLink) linkService.getLinkObject().getClass().newInstance();
                HashMap map = new HashMap();
                map.put("id", UUIDHexGenerator.getInstance().getID());
                map.put("mainId", mainId);
                map.put("operateTime", new Date());
                map.put("operateType", Integer.valueOf(18));
                map.put("operateUserId", StaticMethod.nullObject2String(mainMap.get("sendUserId")));
                map.put("operateDeptId", StaticMethod.nullObject2String(mainMap.get("sendDeptId")));
                map.put("operateRoleId", StaticMethod.nullObject2String(mainMap.get("sendRoleId")));
                map.put("activeTemplateId", "HoldHumTask");
                map.put("linkFaultResponseLevel", "101030402");
                map.put("linkIfGreatFault", "1030102");
                map.put("aiid", tkid);
                map.put("piid", mainObject.getPiid());
                map.put("correlationKey", mainObject.getCorrelationKey());
                map.put("preLinkId", StaticMethod.nullObject2String(linkMap.get("preLinkId")));
                SheetBeanUtils.populateMap2Map(map, returnMap);
                SheetBeanUtils.populate(holdLink, map);
                linkService.addLink(holdLink);
            } else {
                sReturn = super.completeHumanTask(activityId, parameters, sessionMap);
            }
        } else {
            sReturn = super.completeHumanTask(activityId, parameters, sessionMap);
        }
        return sReturn;
    }
}
