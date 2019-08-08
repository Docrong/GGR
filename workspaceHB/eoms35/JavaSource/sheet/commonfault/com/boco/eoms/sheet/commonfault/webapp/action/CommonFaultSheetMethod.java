// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultSheetMethod.java

package com.boco.eoms.sheet.commonfault.webapp.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.util.ParamEncoder;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commonfaultIrontower.ForwardServerToTower;
import com.boco.eoms.commonfaultIrontower.ForwardServerToTowerServiceLocator;
import com.boco.eoms.commonfaultfccos.GroupFaultServiceLocator;
import com.boco.eoms.commonfaultfccos.GroupFault_PortType;
import com.boco.eoms.commons.accessories.exception.AccessoriesException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.commonfault.interfaces.Service_PortType;
import com.boco.eoms.sheet.commonfault.interfaces.Service_ServiceLocator;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.commonfault.service.bo.SendMessageClient;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.netownership.model.NetOwnership;
import com.boco.eoms.sheet.netownership.service.INetOwnershipManager;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipwireless;
import com.boco.eoms.sheet.netownershipwireless.service.INetOwnershipwirelessManager;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import com.boco.eoms.sheet.ptnpretreatmentrule.model.PtnPretreatmentRule;
import com.boco.eoms.sheet.ptnpretreatmentrule.service.IPtnPretreatmentRuleManger;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;
import com.boco.eoms.sheetflowline.mgr.IAutoDealSOPMgr;
import com.boco.eoms.sheetflowline.mgr.IPreAllocatedMgr;
import com.boco.eoms.sheetflowline.model.AutoDealSOP;
import com.boco.eoms.sheetflowline.model.AutoDealSopSheet;
import com.boco.eoms.sheetflowline.model.PreAllocated;
import com.ibm.misc.BASE64Encoder;

public class CommonFaultSheetMethod extends BaseSheet {

    public CommonFaultSheetMethod() {
    }

    public void performAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HashMap columnMap = getInterfaceObjMap(mapping, form, request, response);
        String mainNetSortOne = StaticMethod.nullObject2String(request.getParameter("mainNetSortOne"));
        String mainNetSortTwo = StaticMethod.nullObject2String(request.getParameter("mainNetSortTwo"));
        String mainNetSortThree = StaticMethod.nullObject2String(request.getParameter("mainNetSortThree"));
        String mainFaultResponseLevel = StaticMethod.nullObject2String(request.getParameter("mainFaultResponseLevel"));
        String mainEquipmentFactory = StaticMethod.nullObject2String(request.getParameter("mainEquipmentFactory"));
        String alarmId = StaticMethod.nullObject2String(request.getParameter("mainAlarmId"));
        IPreAllocatedMgr mgr = (IPreAllocatedMgr) ApplicationContextHolder.getInstance().getBean("preAllocatedMgr");
        List list = mgr.search(mainNetSortOne, mainNetSortTwo, mainNetSortThree, mainEquipmentFactory, mainFaultResponseLevel, StaticMethod.getCurrentDateTime());
        String dutyUser = "";
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List list3 = new ArrayList();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                PreAllocated object = (PreAllocated) list.get(i);
                if (object.getCount() > 0)
                    if (object.getCount() == object.getAlreadyCount())
                        list1.add(object);
                    else if (object.getAlreadyCount() < object.getCount() && object.getAlreadyCount() > 0)
                        list2.add(object);
                    else if (object.getAlreadyCount() == 0)
                        list3.add(object);
            }

            PreAllocated pre = null;
            if (list2.size() > 0) {
                pre = (PreAllocated) list2.get(0);
                dutyUser = pre.getDutyUserId();
                pre.setAlreadyCount(pre.getAlreadyCount() + 1);
                mgr.updateObject(pre);
            } else if (list3.size() > 0) {
                pre = (PreAllocated) list3.get(0);
                dutyUser = pre.getDutyUserId();
                pre.setAlreadyCount(1);
                mgr.updateObject(pre);
            } else {
                pre = (PreAllocated) list1.get(0);
                dutyUser = pre.getDutyUserId();
                for (int j = 0; j < list1.size(); j++) {
                    PreAllocated object2 = (PreAllocated) list1.get(j);
                    if (object2.getId().equals(pre.getId()))
                        object2.setAlreadyCount(1);
                    else
                        object2.setAlreadyCount(0);
                    mgr.updateObject(object2);
                }

            }
        } else {
            List leaderlist = mgr.search("", "", "", "", "", StaticMethod.getCurrentDateTime());
            if (leaderlist != null && leaderlist.size() > 0) {
                PreAllocated object1 = (PreAllocated) leaderlist.get(0);
                dutyUser = object1.getDutyUserId();
            }
        }
        Map map = request.getParameterMap();
        System.out.println("=====request Map parentPhaseName:" + StaticMethod.nullObject2String(request.getParameter("parentPhaseName"), ""));
        Map serializableMap = SheetUtils.serializableParemeterMap(map);
        Iterator it = serializableMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        HashMap tempWpsMap;
        for (; it.hasNext(); WpsMap.putAll(tempWpsMap)) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
        }

        Map main1 = (Map) WpsMap.get("main");
        main1.put("perAllocatedUser", dutyUser);
        if (!"".equals(alarmId) && alarmId != null) {
            INetOwnershipManager netOwnershipManager = (INetOwnershipManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipManager");
            ArrayList noInterPreSolveList = new ArrayList();
            String tableName = "commonfault_predeal_alarmid";
            noInterPreSolveList = (ArrayList) netOwnershipManager.getListByAlarmId(tableName, alarmId);
            if (noInterPreSolveList.size() == 0)
                main1.put("intelligentPreSolve", "1030102");
            else
                main1.put("intelligentPreSolve", "1030101");
        } else {
            main1.put("intelligentPreSolve", "1030102");
        }
        WpsMap.put("main", main1);
        String processTemplateName = StaticMethod.nullObject2String(request.getParameter("processTemplateName"));
        String operateName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        setFlowEngineMap(WpsMap);
        dealFlowEngineMap(mapping, form, request, response);
        Map tmpmain = (Map) WpsMap.get("main");
        Map tmpoperate = (Map) WpsMap.get("operate");
        Map tmplink = (Map) WpsMap.get("link");
        String flowflag = StaticMethod.nullObject2String(request.getAttribute("flag"));
        if ("true".equals(flowflag)) {
            String tmpparentPhaseName = (String) tmpmain.get("parentPhaseName");
            System.out.println("==========basesheet:parentPhaseName:" + tmpparentPhaseName);
            HashMap sessionMap = new HashMap();
            TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
            sessionMap.put("userId", sessionform.getUserid());
            sessionMap.put("password", sessionform.getPassword());
            try {
                // 非流程动作处理
                String copyPerformer = StaticMethod.nullObject2String(tmpoperate.get("copyPerformer"));
                Integer operateType = (Integer) tmplink.get("operateType");
                if (!copyPerformer.equals("")
                        || operateType.intValue() == Constants.ACTION_MAKECOPYFOR
                        || operateType.intValue() == Constants.ACTION_PHASE_BACKTOUP
                        || operateType.intValue() == Constants.ACTION_DRIVERFORWARD) {
                    newSaveNonFlowData("", getFlowEngineMap());
                    String sheetId = StaticMethod.nullObject2String(tmpmain.get("sheetId"));
                    String title = StaticMethod.nullObject2String(tmpmain.get("title"));
                    workSM_NON_T(sheetId, copyPerformer, title);
                    tmpoperate.put("copyPerformer", "");
                    tmpoperate.put("copyPerformerLeader", "");
                    tmpoperate.put("copyPerformerType", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            businessFlowService.initProcess(processTemplateName, operateName, getFlowEngineMap(), sessionMap);
        }
    }

    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        if (taskName.equals("FirstExcuteHumTask")) {
            String mainPretreatment = StaticMethod.nullObject2String(request.getParameter("mainPretreatment"));
            if (mainPretreatment.equals("1030101"))
                taskName = StaticMethod.nullObject2String(request.getParameter("trueActiveTemplateId"));
        }
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        String subTaskFlag = StaticMethod.nullObject2String(request.getParameter("subTaskFlag"));
        System.out.println("operateName is -----------------------" + operatName);
        if (operatName.equals("forceHold")) {
            HashMap map = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
            if (sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            BaseMain main = getMainService().getSingleMainPO(sheetKey);
            map.put("main", main);
            try {
                map.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            map.put("operate", getPageColumnName());
            hashMap.put("selfSheet", map);
        } else if (taskName.equals("")) {
            HashMap sheetMap = new HashMap();
            try {
                sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else if (taskName.equals("DraftHumTask") || taskName.equals("BackHumTask")) {
            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey == null || sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            BaseMain main = getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else if (taskName.equals("HoldHumTask")) {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getAttribute("mainId"));
            HashMap sheetMap = new HashMap();
            BaseMain main = getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else if ((taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") || taskName.equals("ThirdExcuteHumTask")) && subTaskFlag.equals("")) {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getAttribute("mainId"));
            BaseMain main = getMainService().getSingleMainPO(sheetKey);
            HashMap sheetMap = new HashMap();
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
            HashMap map2 = new HashMap();
            IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("UrgentFault");
            try {
                map2.put("urgentMain", baseSheet.getMainService().getMainObject().getClass().newInstance());
                map2.put("urgentLink", baseSheet.getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            map2.put("urgentOperate", baseSheet.getPageColumnName());
            hashMap.put("urgent", map2);
        } else if (taskName.equals("advice") || taskName.equals("reply") || taskName.equals("cc")) {
            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey == null || sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            System.out.println("非流程动作=====sheetKey is" + sheetKey);
            BaseMain main = getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey == null || sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            BaseMain main = getMainService().getSingleMainPO(sheetKey);
            HashMap sheetMap = new HashMap();
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        }
        return hashMap;
    }

    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        if (taskName.equals("FirstExcuteHumTask")) {
            String mainPretreatment = StaticMethod.nullObject2String(request.getParameter("mainPretreatment"));
            if (mainPretreatment.equals("1030101"))
                taskName = StaticMethod.nullObject2String(request.getParameter("trueActiveTemplateId"));
        }
        HashMap sheetMap = getFlowEngineMap();
        Map main = (HashMap) sheetMap.get("main");
        Map operate = (HashMap) sheetMap.get("operate");
        String phaseId = (String) operate.get("phaseId");
        if (taskName.equals("") || taskName.equals("DraftHumTask")) {
            int acceptLimit = StaticMethod.nullObject2int(request.getParameter("acceptLimit"), -1);
            int replyLimit = StaticMethod.nullObject2int(request.getParameter("replyLimit"), -1);
            int t1Limit = StaticMethod.nullObject2int(request.getParameter("t1Limit"), -1);
            int t2Limit = StaticMethod.nullObject2int(request.getParameter("t2Limit"), -1);
            int t3Limit = StaticMethod.nullObject2int(request.getParameter("t3Limit"), -1);
            String localTime = StaticMethod.getCurrentDateTime();
            if (acceptLimit > -1)
                main.put("sheetAcceptLimit", SheetUtils.stringToDate(StaticMethod.getDateForMinute(localTime, acceptLimit)));
            if (replyLimit > -1)
                main.put("sheetCompleteLimit", SheetUtils.stringToDate(StaticMethod.getDateForMinute(localTime, replyLimit)));
            if (t1Limit > -1)
                main.put("mainCompleteLimitT1", SheetUtils.stringToDate(StaticMethod.getDateForMinute(localTime, t1Limit)));
            if (t2Limit > -1)
                main.put("mainCompleteLimitT2", SheetUtils.stringToDate(StaticMethod.getDateForMinute(localTime, t2Limit + t1Limit)));
            if (t3Limit > -1) {
                String status = StaticMethod.nullObject2String(main.get("status"));
                if (status.equals("3")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar c = Calendar.getInstance();
                    c.setTime(SheetUtils.stringToDate(StaticMethod.getDateForMinute(localTime, t3Limit + t2Limit + t1Limit)));
                    c.add(13, -1);
                    String date = dateFormat.format(c.getTime());
                    main.put("mainCompleteLimitT3", SheetUtils.stringToDate(date));
                } else {
                    main.put("mainCompleteLimitT3", SheetUtils.stringToDate(StaticMethod.getDateForMinute(localTime, t3Limit + t2Limit + t1Limit)));
                }
            }
            main.put("mainManualPreSolve", "1030101");
            sheetMap.put("main", main);
        }
        if (phaseId.equals("FirstExcuteTask")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("nodeCompleteLimit", main.get("mainCompleteLimitT1"));
            link.put("manualPreSolve", "1030101");
            sheetMap.put("link", link);
        }
        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);
        }
        if (taskName.equals("")) {
            String mainFaultResponseLevel = StaticMethod.nullObject2String(main.get("mainFaultResponseLevel"));
            if ("101030401".equals(mainFaultResponseLevel)) {
                String copyPerformer = StaticMethod.nullObject2String(operate.get("copyPerformer"));
                if (copyPerformer.equals("")) {
                    String toDeptId = StaticMethod.nullObject2String(main.get("toDeptId"));
                    WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                    String copyRoleId = XmlManage.getFile("/config/commonfault-util.xml").getProperty("copyRoleId");
                    TawSystemSubRole subRole = sm.getMatchRoles("CommonFaultMainFlowProcess", toDeptId, copyRoleId, main);
                    String subRoleId = "";
                    if (subRole != null && subRole.getId() != null && subRole.getId().length() != 0) {
                        subRoleId = subRole.getId();
                        operate.put("copyPerformer", subRoleId);
                        operate.put("copyPerformerLeader", subRoleId);
                        operate.put("copyPerformerType", "subrole");
                    }
                }
            }
        }
        String mainPretreatment = StaticMethod.nullObject2String(request.getParameter("mainPretreatment"));
        BocoLog.info(this, "====makeT1==== taskName:" + taskName);
        BocoLog.info(this, "====makeT1==== mainPretreatment:" + mainPretreatment);
        if ((taskName.equals("") || taskName.equals("BackHumTask") || taskName.equals("DraftHumTask")) && mainPretreatment.equals("1030101")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("activeTemplateId", taskName);
            sheetMap.put("link", link);
            sheetMap = makeT1(sheetMap);
        }
        if ("1".equals(main.get("isCustomerFlag"))) {
            String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("SendImmediately"));
            if (!sendImmediately.equalsIgnoreCase("true")) {
                operate.put("interfaceType", "InterSwitchAlarm");
                operate.put("methodType", "syncSheetState");
            }
        }
        String dealperformers[] = StaticMethod.nullObject2String(operate.get("dealPerformer")).split(",");
        if (dealperformers.length > 1) {
            String corrKey = "";
            String tmp = "";
            for (int i = 0; i < dealperformers.length; i++) {
                tmp = UUIDHexGenerator.getInstance().getID();
                if (dealperformers.length == 1)
                    corrKey = tmp;
                else if (corrKey.equals(""))
                    corrKey = tmp;
                else
                    corrKey = corrKey + "," + tmp;
            }

            System.out.println("corrKey" + corrKey);
            operate.put("extendKey1", corrKey);
        }
        Map linkrule = (HashMap) sheetMap.get("link");
        Map linkcs = (Map) sheetMap.get("link");
        Map mainrule = (HashMap) sheetMap.get("main");
        String operateType = StaticMethod.nullObject2String(linkrule.get("operateType"));
        String dealPerformer = StaticMethod.nullObject2String(operate.get("dealPerformer"));
        Date operateTime = (Date) linkrule.get("operateTime");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(operateTime);
        boolean flag = true;
        IAutoDealSOPMgr autoDealSopMgr = (IAutoDealSOPMgr) ApplicationContextHolder.getInstance().getBean("autoDealSopMgr");
        if (taskName.equals("") && phaseId.equals("FirstExcuteTask") && operateType.equals("0")) {
            Map map = new HashMap();
            map.put("autoDealTask", "T1");
            map.put("alarmId", StaticMethod.nullObject2String(mainrule.get("mainAlarmId")));
            map.put("autoRule", "0");
            List list = autoDealSopMgr.searchSOP(map);
            if (list != null && list.size() > 0) {
                AutoDealSOP autoDealSop = (AutoDealSOP) list.get(0);
                if ("1".equals(autoDealSop.getAutoDealMode())) {
                    operate.put("phaseId", "SecondExcuteTask");
                    CommonFaultBO.createT1Task(mainrule, dealPerformer, autoDealSop.getOperateUserId(), StaticMethod.nullObject2String(linkrule.get("id")));
                    operate.put("dealPerformer", autoDealSop.getTranferObject());
                    operate.put("dealPerformerLeader", autoDealSop.getTranferObject());
                    operate.put("dealPerformerType", "subrole");
                    calendar1.add(13, 20);
                    CommonFaultLink t1Link = CommonFaultBO.createT1Link(mainrule, autoDealSop, "1", calendar1.getTime(), StaticMethod.nullObject2String(linkrule.get("id")));
                    Map map1 = new HashMap();
                    map1.put("autoDealTask", "T2");
                    map1.put("alarmId", StaticMethod.nullObject2String(mainrule.get("mainAlarmId")));
                    map1.put("autoRule", "0");
                    List list1 = autoDealSopMgr.searchSOP(map1);
                    if (list1 != null && list1.size() > 0) {
                        AutoDealSOP autoDealSopT2 = (AutoDealSOP) list1.get(0);
                        operate.put("phaseId", "");
                        operate.put("hasNextTaskFlag", "true");
                        operate.put("dealPerformer", "");
                        operate.put("dealPerformerLeader", "");
                        operate.put("dealPerformerType", "user");
//						CommonFaultMain mainObject = CommonFaultBO.createMain(mainrule);
                        CommonFaultBO.createLink0(mainrule);
                        calendar1.add(13, 20);
                        CommonFaultBO.createT2Task(mainrule, autoDealSop.getTranferObject(), autoDealSopT2.getOperateUserId(), t1Link.getId());
                        CommonFaultLink t2Link = CommonFaultBO.createT2Link(mainrule, autoDealSopT2, calendar1.getTime(), StaticMethod.nullObject2String(t1Link.getId()));
                        calendar1.add(13, 20);
                        CommonFaultBO.createHoldMessage(mainrule, calendar1.getTime(), StaticMethod.nullObject2String(t2Link.getId()));
                        AutoDealSopSheet SOPsheet1 = new AutoDealSopSheet(autoDealSopT2.getId(), StaticMethod.nullObject2String(main.get("sheetId")), new Date());
                        autoDealSopMgr.saveSopSheet(SOPsheet1);
                        flag = false;
                    }
                } else {
                    operate.put("hasNextTaskFlag", "true");
                    operate.put("phaseId", "");
//					CommonFaultMain mainObject = CommonFaultBO.createMain(mainrule);
                    CommonFaultBO.createLink0(mainrule);
                    CommonFaultBO.createT1Task(mainrule, dealPerformer, autoDealSop.getOperateUserId(), StaticMethod.nullObject2String(linkrule.get("id")));
                    calendar1.add(13, 20);
                    CommonFaultLink t1Link = CommonFaultBO.createT1Link(mainrule, autoDealSop, "46", calendar1.getTime(), StaticMethod.nullObject2String(linkrule.get("id")));
                    calendar1.add(13, 20);
                    CommonFaultBO.createHoldMessage(mainrule, calendar1.getTime(), StaticMethod.nullObject2String(t1Link.getId()));
                    operate.put("dealPerformer", "");
                    operate.put("dealPerformerLeader", "");
                    operate.put("dealPerformerType", "user");
                    flag = false;
                }
                AutoDealSopSheet SOPsheet2 = new AutoDealSopSheet(autoDealSop.getId(), StaticMethod.nullObject2String(main.get("sheetId")), new Date());
                autoDealSopMgr.saveSopSheet(SOPsheet2);
            }
        }
        if (phaseId.equals("SecondExcuteTask") && operateType.equals("1")) {
            Map map = new HashMap();
            map.put("autoDealTask", "T2");
            map.put("alarmId", StaticMethod.nullObject2String(mainrule.get("mainAlarmId")));
            map.put("autoRule", "0");
            List list = autoDealSopMgr.searchSOP(map);
            if (list != null && list.size() > 0) {
                AutoDealSOP autoDealSop = (AutoDealSOP) list.get(0);
                if ("2".equals(autoDealSop.getAutoDealMode())) {
                    operate.put("phaseId", "");
                    operate.put("hasNextTaskFlag", "true");
//					CommonFaultMain mainObject = CommonFaultBO.createMain(mainrule);
                    CommonFaultBO.createLink0(mainrule);
                    CommonFaultBO.createT2Task(mainrule, autoDealSop.getTranferObject(), autoDealSop.getOperateUserId(), StaticMethod.nullObject2String(linkrule.get("id")));
                    calendar1.add(13, 20);
                    CommonFaultLink t2Link = CommonFaultBO.createT2Link(mainrule, autoDealSop, calendar1.getTime(), StaticMethod.nullObject2String(linkrule.get("id")));
                    calendar1.add(13, 20);
                    CommonFaultBO.createHoldMessage(mainrule, calendar1.getTime(), StaticMethod.nullObject2String(t2Link.getId()));
                    operate.put("dealPerformer", "");
                    operate.put("dealPerformerLeader", "");
                    operate.put("dealPerformerType", "user");
                    AutoDealSopSheet SOPsheet = new AutoDealSopSheet(autoDealSop.getId(), StaticMethod.nullObject2String(main.get("sheetId")), new Date());
                    autoDealSopMgr.saveSopSheet(SOPsheet);
                    if ("".equals(taskName))
                        flag = false;
                }
            }
        }
        boolean inrule = false;

        String obj = "";
        String operateUserId = "";
        String operateDeptId = "";
        String operateRoleId = "";
        String operaterContact = "";
        String netSortOne = StaticMethod.nullObject2String(mainrule.get("mainNetSortOne"));
        if ("101010405".equals(netSortOne) && operateType.equals("46")) {
            String mainFaultResponseLevel = StaticMethod.nullObject2String(mainrule.get("mainFaultResponseLevel"));
            Date faultdealTime = (Date) linkrule.get("faultdealTime");
            Date linkFaultAvoidTime = (Date) linkrule.get("linkFaultAvoidTime");
            String nodeAccessories = StaticMethod.nullObject2String(linkrule.get("nodeAccessories"));
            BocoLog.info(this, "mainFaultResponseLevel:" + mainFaultResponseLevel + ";faultdealTime:" + faultdealTime + ";linkFaultAvoidTime:" + linkFaultAvoidTime + ";nodeAccessories:" + nodeAccessories);
            boolean other = false;
            if (faultdealTime != null && linkFaultAvoidTime != null)
                other = faultdealTime.before(linkFaultAvoidTime);
            if (other && "".equals(nodeAccessories) && !"101030401".equals(mainFaultResponseLevel)) {
                TawSystemDictTypeDaoHibernate dictDao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeDao");
                String faultDealDesc = StaticMethod.nullObject2String(request.getParameter("selectStep"));
                TawSystemDictType dictType = dictDao.getDictByDictName(faultDealDesc, "1010323");
                if (dictType != null)
                    faultDealDesc = StaticMethod.nullObject2String(dictType.getDictId());
                String faultReasonSort1 = StaticMethod.nullObject2String(linkrule.get("linkFaultReasonSort"));
                String faultReasonSort2 = StaticMethod.nullObject2String(linkrule.get("linkFaultReasonSubsection"));
                String faultReasonSort3 = StaticMethod.nullObject2String(linkrule.get("linkFaultReasonSubsectionTwo"));
                String mainPretResultId = StaticMethod.nullObject2String(mainrule.get("mainPretResultId"));
                IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger) ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
                String condition = " where faultDealDesc='" + faultDealDesc + "' and faultReasonSort1='" + faultReasonSort1 + "' and faultReasonSort2='" + faultReasonSort2 + "' and faultReasonSort3='" + faultReasonSort3 + "' and preDealRelation='" + mainPretResultId + "'";
                List isExist = ptnPretreatmentRuleManger.getListByCondition(condition);
                if (isExist != null && !isExist.isEmpty()) {
                    inrule = true;
                } else {
                    condition = " where faultDealDesc='" + faultDealDesc + "' and faultReasonSort1='" + faultReasonSort1 + "' and faultReasonSort2='" + faultReasonSort2 + "' and faultReasonSort3='" + faultReasonSort3 + "' and preDealRelation='101032110'";
                    isExist = ptnPretreatmentRuleManger.getListByCondition(condition);
                    if (isExist != null && !isExist.isEmpty())
                        inrule = true;
                }
                if (inrule) {
                    XMLProperties xmlProperties = XmlManage.getFile("/config/commonfault-util.xml");
                    operateUserId = StaticMethod.nullObject2String(xmlProperties.getProperty("ptnAutoHoldUserId"));
                    operateRoleId = StaticMethod.nullObject2String(xmlProperties.getProperty("ptnAutoHoldRoleId"));
                    ITawSystemUserManager tawSystemUserManager = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
                    TawSystemUser user = tawSystemUserManager.getUserByuserid(operateUserId);
                    if (user != null) {
                        operateDeptId = StaticMethod.nullObject2String(user.getDeptid());
                        operaterContact = StaticMethod.nullObject2String(user.getMobile());
                    }
                }
            }
        } else if (operateType.equals("46") && taskName.equals("SecondExcuteHumTask")) {
            Date mainAlarmSolveDate = (Date) mainrule.get("mainAlarmSolveDate");
            String mainAlarmId = (String) mainrule.get("mainAlarmId");
            String mainFaultResponseLevel = StaticMethod.nullObject2String(mainrule.get("mainFaultResponseLevel"));
            String linkDealStep = (String) linkrule.get("selectStep");
            String nodeAccessories = StaticMethod.nullObject2String(linkrule.get("nodeAccessories"));
            System.out.println("---自动归档---mainAlarmId=" + mainAlarmId + "--mainFaultRespondseLevel=" + mainFaultResponseLevel + "--linkDealStep=" + linkDealStep);
            if (mainAlarmSolveDate != null && mainrule.get("sendContact") != null && operateTime.after(mainAlarmSolveDate) && "".equals(nodeAccessories) && !"101030401".equals(mainFaultResponseLevel)) {
                ICommonFaultAutoManager autoservice = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
                List steplist = autoservice.getStepsbycondition(mainAlarmId, linkDealStep);
                if (steplist.size() > 0 && !"其它".equals(linkDealStep) && !"其他".equals(linkDealStep)) {
                    inrule = true;
                    System.out.println("满足自动归档规则！");
                    Object object[] = (Object[]) steplist.get(0);
                    obj = String.valueOf(object[2]);
                    operateUserId = StaticMethod.nullObject2String(main.get("sendUserId"));
                    operateDeptId = StaticMethod.nullObject2String(main.get("sendDeptId"));
                    operateRoleId = StaticMethod.nullObject2String(main.get("sendRoleId"));
                    operaterContact = StaticMethod.nullObject2String(main.get("sendContact"));
                } else {
                    inrule = false;
                }
            }
        }
        String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
        String roleLeader = StaticMethod.nullObject2String(request.getParameter("roleLeader"));

        //智能质检 start by lyg
        if (operateType.equals("46")) {
            Map contionMap = new HashMap();
            contionMap.put("mainAlarmSolveDate", main.get("mainAlarmSolveDate"));
            contionMap.put("linkFaultDealResult", request.getParameter("linkFaultDealResult"));
            contionMap.put("mainCompleteLimitT1", main.get("mainCompleteLimitT1"));
            contionMap.put("mainCompleteLimitT2", main.get("mainCompleteLimitT2"));
            contionMap.put("linkFaultAvoidTime", request.getParameter("linkFaultAvoidTime"));
            contionMap.put("linkDealStep", request.getParameter("linkDealStep"));
            contionMap.put("operateType", "46");
            contionMap.put("taskName", taskName);
            contionMap.put("sheetId", main.get("sheetId"));
            Map retMap = intelligentQuality(contionMap);
            String linkReserved3 = StaticMethod.nullObject2String(retMap.get("linkReserved3"));
            String linkReserved4 = StaticMethod.nullObject2String(retMap.get("linkReserved4"));
            System.out.println("linkReserved3==" + linkReserved3 + "====linkReserved4=" + linkReserved4);
            ITawSystemUserManager tawSystemUserManager = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            if ("0".equals(linkReserved3)) {//智能质检通过
                inrule = true;
                //将这里的改为  智能质检
                operateUserId = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-intelligent.xml").getProperty("intelligent.archiveUserId"));
                operateRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-intelligent.xml").getProperty("intelligent.archiveRoleId"));
                TawSystemUser user = tawSystemUserManager.getUserByuserid(operateUserId);
                if (user != null) {
                    operateDeptId = StaticMethod.nullObject2String(user.getDeptid());
                    operaterContact = StaticMethod.nullObject2String(user.getMobile());
                }
            }
            String operateUserId1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-intelligent.xml").getProperty("intelligent.intelligentUserId"));
            String operateRoleId1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-intelligent.xml").getProperty("intelligent.intelligentRoleId"));
            TawSystemUser user = tawSystemUserManager.getUserByuserid(operateUserId1);
            String operateDeptId1 = "";
            String operaterContact1 = "";
            if (user != null) {
                operateDeptId1 = StaticMethod.nullObject2String(user.getDeptid());
                operaterContact1 = StaticMethod.nullObject2String(user.getMobile());
            }
            ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
            Calendar calendar = Calendar.getInstance();
            calendar.set(13, 50);
            CommonFaultLink linkbean = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
            linkbean.setId(UUIDHexGenerator.getInstance().getID());
            linkbean.setMainId(StaticMethod.nullObject2String(main.get("id")));
            linkbean.setOperateTime(calendar.getTime());
            linkbean.setOperateType(new Integer(101));
            linkbean.setOperateDay(calendar.get(5));
            linkbean.setOperateMonth(calendar.get(2) + 1);
            linkbean.setOperateYear(calendar.get(1));
            linkbean.setOperateUserId(operateUserId1);
            linkbean.setOperateDeptId(operateDeptId1);
            linkbean.setOperateRoleId(operateRoleId1);
            linkbean.setOperaterContact(operaterContact1);
            linkbean.setToOrgRoleId("");
            linkbean.setToOrgType(new Integer(0));
            linkbean.setAcceptFlag(new Integer(2));
            linkbean.setCompleteFlag(new Integer(2));
            linkbean.setActiveTemplateId("IntelligentQuality");
            linkbean.setLinkReserved2("是");
            if ("0".equals(linkReserved3)) {
                linkbean.setLinkReserved3("合格");
            } else {
                linkbean.setLinkReserved3("不合格");
            }
            linkbean.setLinkReserved4(linkReserved4);
            linkservice.addLink(linkbean);

        }
        //智能质检  end by lyg

        String isTrowerSend = StaticMethod.nullObject2String(request.getAttribute("isTrowerSend"));
        System.out.println("isTrowerSend==" + isTrowerSend);
        if ("isTrowerSend".equals(isTrowerSend)) {
            //表示是铁塔回单
            mainrule.put("mainTownerFlag", "");
        }
        String noSixFlag = "";
        System.out.println("inrule==sheetId==" + inrule + "+++" + mainrule.get("sheetId"));
        if (!inrule && operateType.equals("46") && taskName.equals("SecondExcuteHumTask")) {
			/*判断以上归档条件是否满足，不满足，调用6要素归档方法
			 处理措施 linkDealStep   selectStep
			*  故障消除时间  linkFaultAvoidTime
			 * 故障原因 faultReason    faultdealdesc
			*  处理人  operateUserId
			 * 联系方式  operaterContact
			*  故障处理响应级别 mainFaultResponseLevel  字典值 101030401 一级处理
			*  故障发生时间 mainFaultGenerantTime +*/
            Map ruleCondition = new HashMap();
            ruleCondition.put("sheetId", StaticMethod.nullObject2String(mainrule.get("sheetId")));
            ruleCondition.put("mainFaultResponseLevel", StaticMethod.nullObject2String(mainrule.get("mainFaultResponseLevel")));
            ruleCondition.put("mainFaultGenerantTime", (Date) mainrule.get("mainFaultGenerantTime"));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String linkFaultAvoidTimeStr = StaticMethod.nullObject2String(request.getParameter("linkFaultAvoidTime"));


            if ("isTrowerSend".equals(isTrowerSend)) {
                System.out.println("is trower send");
                linkFaultAvoidTimeStr = format.format(new Date());
            }
            Date linkFaultAvoidTimeDate = format.parse(linkFaultAvoidTimeStr);
            System.out.println("method==linkFaultAvoidTimeStr=" + linkFaultAvoidTimeStr);
            System.out.println("method==linkFaultAvoidTimeDate=" + linkFaultAvoidTimeDate);
            ruleCondition.put("linkFaultAvoidTime", linkFaultAvoidTimeDate);
            ruleCondition.put("linkDealStep", StaticMethod.nullObject2String(request.getParameter("selectStep")));
            ruleCondition.put("faultReason", StaticMethod.nullObject2String(request.getParameter("faultdealdesc")));
            ruleCondition.put("operateUserId", StaticMethod.nullObject2String(request.getParameter("faultTreatment")));
            ruleCondition.put("operaterContact", StaticMethod.nullObject2String(request.getParameter("operaterContact")));

            Map returnMap = autoArchive(ruleCondition);
            String ruleFlag = StaticMethod.nullObject2String(returnMap.get("ruleFlag"));
            noSixFlag = StaticMethod.nullObject2String(returnMap.get("noSixFlag"));
            if ("0".equals(ruleFlag)) {
                inrule = true;
                //将这里的改为  工单助手
                operateUserId = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("autoArchive.archiveUserId"));
                operateRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("autoArchive.archiveRoleId"));
                ITawSystemUserManager tawSystemUserManager = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
                TawSystemUser user = tawSystemUserManager.getUserByuserid(operateUserId);
                if (user != null) {
                    operateDeptId = StaticMethod.nullObject2String(user.getDeptid());
                    operaterContact = StaticMethod.nullObject2String(user.getMobile());
                }
            } else if ("0".equals(noSixFlag)) {
                String linkFalg = "33";
                linkcs.put("linkFalg", linkFalg);
                System.out.println("linkFalg=" + linkFalg);
            }
        }


        if (inrule) {
            ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
            ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
            ICommonFaultMainManager mainservice = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
            operate.put("hasNextTaskFlag", "true");
            operate.put("phaseId", "");
            Calendar calendar = Calendar.getInstance();
            calendar.set(13, 60);
            CommonFaultLink linkbean = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
            linkbean.setId(UUIDHexGenerator.getInstance().getID());
            linkbean.setMainId(StaticMethod.nullObject2String(main.get("id")));
            linkbean.setOperateTime(calendar.getTime());
            linkbean.setOperateType(new Integer(18));
            linkbean.setOperateDay(calendar.get(5));
            linkbean.setOperateMonth(calendar.get(2) + 1);
            linkbean.setOperateYear(calendar.get(1));
            linkbean.setOperateUserId(operateUserId);
            linkbean.setOperateDeptId(operateDeptId);
            linkbean.setOperateRoleId(operateRoleId);
            linkbean.setOperaterContact(operaterContact);
            linkbean.setToOrgRoleId("");
            linkbean.setToOrgType(new Integer(0));
            linkbean.setAcceptFlag(new Integer(2));
            linkbean.setCompleteFlag(new Integer(2));
            linkbean.setActiveTemplateId("HoldHumTask");
            linkservice.addLink(linkbean);
            Object commonfaultMainObj = mainservice.getSingleMainPO(StaticMethod.nullObject2String(main.get("id")));
            if (commonfaultMainObj != null) {
                CommonFaultMain commonfaultMain = (CommonFaultMain) commonfaultMainObj;
                commonfaultMain.setEndResult(obj);
                commonfaultMain.setStatus(new Integer(1));
                commonfaultMain.setHoldStatisfied(Integer.valueOf(0xfb89d));
                mainservice.addMain(commonfaultMain);
            }
            CommonFaultTask task = new CommonFaultTask();
            try {
                task.setId(UUIDHexGenerator.getInstance().getID());
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            task.setTaskName("HoldHumTask");
            task.setTaskDisplayName("待归档");
            task.setFlowName("CommonFaultMainFlowProcess");
            task.setSendTime(new Date());
            task.setSheetKey(StaticMethod.nullObject2String(main.get("id")));
            task.setTaskStatus("5");
            task.setSheetId(StaticMethod.nullObject2String(main.get("sheetId")));
            task.setTitle(StaticMethod.nullObject2String(main.get("title")));
            task.setOperateType("subrole");
            task.setCreateTime(new Date());
            task.setCreateYear(calendar.get(1));
            task.setCreateMonth(calendar.get(2) + 1);
            task.setCreateDay(calendar.get(5));
            task.setOperateRoleId(operateRoleId);
            task.setTaskOwner(operateUserId);
            task.setOperateType("subrole");
            task.setIfWaitForSubTask("false");
            task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
            task.setPreLinkId(linkbean.getId());
            taskservice.addTask(task);
            System.out.println("lizhi:roleLeader=" + roleLeader + "taskStatus=" + taskStatus + "====自动归档完成！");
            String mainIFTowner = StaticMethod.nullObject2String(main.get("mainIFTowner"));
            System.out.println("自动归档后，如果是铁塔工单调用归档接口1==mainIFTowner=" + mainIFTowner);
            if ("2".equals(mainIFTowner)) {
                String filePath = StaticMethod.getFilePathForUrl("classpath:config/commonfaulttowner-util.xml");
                InterfaceUtilProperties properties = new InterfaceUtilProperties();
                Map temMap = new HashMap();
                temMap.put("TASKID", StaticMethod.nullObject2String(main.get("sheetId")));
                ForwardServerToTowerServiceLocator service = new ForwardServerToTowerServiceLocator();
                ForwardServerToTower bing = service.getTaskToTower();
//						String opDetail = "<BACK_TASK><TASKID>"+StaticMethod.nullObject2String(main.get("sheetId"))+"</TASKID><BACK_TYPE>0</BACK_TYPE><BACK_MESSAGE><RECOVERY_TIME></RECOVERY_TIME></BACK_TASK>";
                String opDetail = properties.getXmlFromMap(temMap, filePath, "qualityPass");
                System.out.println("lgy1===lyg1==" + opDetail);
                try {
                    bing.qualityPassWorksheet("", "", "", "", opDetail);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("自动归档后，如果是铁塔工单调用归档接口1,抛出异常");
                }

            }

        }

        System.out.println("lyg:taskName=" + taskName + "===operateType=" + operateType);
        String mainIFTowner = StaticMethod.nullObject2String(main.get("mainIFTowner"));
        System.out.println("mainIFTowner====" + mainIFTowner);
        if (taskName.equals("HoldHumTask") && operateType.equals("18") && "2".equals(mainIFTowner)) {
            System.out.println("自动归档后，如果是铁塔工单调用归档接口2===");
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/commonfaulttowner-util.xml");
            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            Map temMap = new HashMap();
            temMap.put("TASKID", StaticMethod.nullObject2String(main.get("sheetId")));
            ForwardServerToTowerServiceLocator service = new ForwardServerToTowerServiceLocator();
            ForwardServerToTower bing = service.getTaskToTower();
//			String opDetail = "<BACK_TASK><TASKID>"+StaticMethod.nullObject2String(main.get("sheetId"))+"</TASKID><BACK_TYPE>0</BACK_TYPE><BACK_MESSAGE><RECOVERY_TIME></RECOVERY_TIME></BACK_TASK>";
            String opDetail = properties.getXmlFromMap(temMap, filePath, "qualityPass");
            System.out.println("lgy1===lyg1==" + opDetail);
            try {
                bing.qualityPassWorksheet("", "", "", "", opDetail);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("自动归档后，如果是铁塔工单调用归档接口2,抛出异常");
            }

        }


        if (operateType.equals("8") && taskName.equals("SecondExcuteHumTask") && "true".equals(roleLeader) && "2".equals(taskStatus)) {
            ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
            String sheetKey = StaticMethod.nullObject2String(main.get("id"));
            System.out.println("lizhi:sheetKey=" + sheetKey);
            CommonFaultTask commonFaultTask = (CommonFaultTask) taskservice.getTask(sheetKey, taskName);
            String taskOwner = StaticMethod.nullObject2String(commonFaultTask.getTaskOwner());
            System.out.println("lizhi:taskOwner=" + taskOwner);
            operate.put("copyPerformer", taskOwner);
            operate.put("copyPerformerLeader", taskOwner);
            operate.put("copyPerformerType", "subrole");
        }
        String centerMonitor = StaticMethod.nullObject2String(request.getParameter("centerMonitor"));
        String rejectActiveTemplateId = StaticMethod.nullObject2String(request.getParameter("rejectActiveTemplateId"));
        System.out.println("lizhi:centerMonitor=" + centerMonitor + "rejectActiveTemplateId=" + rejectActiveTemplateId);
        if (operateType.equals("1") && taskName.equals("FirstExcuteHumTask") && "true".equals(centerMonitor) || operateType.equals("8") && taskName.equals("SecondExcuteHumTask") && "true".equals(centerMonitor) || operateType.equals("17") && taskName.equals("HoldHumTask") && "true".equals(centerMonitor) && "SecondExcuteHumTask".equals(rejectActiveTemplateId)) {
            String dealPerformerMonitor = StaticMethod.nullObject2String(operate.get("dealPerformer"));
            System.out.println("lizhi:dealPerformerMonitor=" + dealPerformerMonitor);
            operate.put("dealPerformerLeader", dealPerformerMonitor);
        }
        if (operateType.equals("1") && taskName.equals("FirstExcuteHumTask") && "true".equals(centerMonitor)) {
            StringBuffer where = new StringBuffer();
            String dealPerformer1 = StaticMethod.nullObject2String(operate.get("dealPerformer"));
            where.append(" roleid = '8005106' and type7 = '3' and deleted = 0");
            where.append("and id ='" + dealPerformer1 + "'");
            System.out.println("----eomsinfo ---where +" + where.toString());
            ITawSystemSubRoleManager tawsubrole = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
            List subroles = tawsubrole.getTawSystemSubRoles(where.toString());
            if (subroles.size() > 0) {
                String mainNetName = StaticMethod.nullObject2String(mainrule.get("mainNetName"));
                System.out.println("---mainNetName-" + mainNetName);
                INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
                NetOwnershipwireless netownershipwireless = netOwnershipwirelessManager.getNetOwnershipByNetName(mainNetName);
                if (netownershipwireless != null) {
                    String teamroleid = StaticMethod.nullObject2String(netownershipwireless.getTeamRoleId());
                    String ccObject = StaticMethod.nullObject2String(netownershipwireless.getCcObject());
                    String ccsubroleid = StaticMethod.nullObject2String(operate.get("copyPerformer"));
                    String ccsublroletype = StaticMethod.nullObject2String(operate.get("copyPerformerType"));
                    if (!"".equals(teamroleid) && !"".equals(ccObject)) {
                        ccsubroleid = ccsubroleid + "," + ccObject + "," + teamroleid;
                        ccsublroletype = ccsublroletype + ",subrole,subrole";
                    } else if (!"".equals(teamroleid) && "".equals(ccObject)) {
                        ccsubroleid = ccsubroleid + "," + teamroleid;
                        ccsublroletype = ccsublroletype + ",subrole";
                    } else if ("".equals(teamroleid) && !"".equals(ccObject)) {
                        ccsubroleid = ccsubroleid + "," + ccObject;
                        ccsublroletype = ccsublroletype + ",subrole";
                    }
                    operate.put("copyPerformer", ccsubroleid);
                    operate.put("copyPerformerLeader", ccsubroleid);
                    operate.put("copyPerformerType", ccsublroletype);
                }
            }
        }
        if (phaseId.equals("FirstExcuteTask") && operateType.equals("0") || operateType.equals("3") && taskName.equals("DraftHumTask") || operateType.equals("0") && taskName.equals("DraftHumTask")) {
            String toDeptId = StaticMethod.nullObject2String(mainrule.get("toDeptId"));
            String pilotcity = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("pilotcity"));
            System.out.println("lizhi:toDeptId=" + toDeptId + "pilotcity=" + pilotcity);
            String pilotcityIndex[] = pilotcity.split(",");
            boolean pilotcityflag = false;
            for (int i = 0; i < pilotcityIndex.length; i++)
                if (toDeptId.equals(pilotcityIndex[i]) || "".equals(toDeptId))
                    pilotcityflag = true;

            System.out.println("lizhi:pilotcityflag=" + pilotcityflag);
            if (pilotcityflag)
                mainrule.put("mainIfCenterMonitor", "1");
        }
        if (taskName.equals("SecondExcuteHumTask") && operateType.equals("4")) {
            request.setAttribute("subParentPhaseName", "SecondExcuteHumTask");
//			CommonfaultCorrigendumMethod baseSheet = new CommonfaultCorrigendumMethod();
//			String s = baseSheet.getReply(mapping, form, request, response);
        }


        System.out.println("PH------------------tuisong: phaseId=:" + phaseId + " operateType=:" + operateType);
        if (phaseId.equals("SecondExcuteTask") && operateType.equals("1")) {
            System.out.println("开始调用消息推送：phaseId=" + phaseId + ";operateType=" + operateType);
            SendMessageClient sendMessage = new SendMessageClient();
            Map mainmap = (HashMap) sheetMap.get("main");
//			Map operatemap = (HashMap)sheetMap.get("operate");
            String userid = StaticMethod.nullObject2String((String) operate.get("dealPerformer"));
            String sheetid = StaticMethod.nullObject2String((String) mainmap.get("sheetId"));
            String title = StaticMethod.nullObject2String((String) mainmap.get("title"));
            Date completelimit = (Date) mainmap.get("sheetCompleteLimit");
            sendMessage.sendMessage(userid, sheetid, title, completelimit);
        }

        if (taskName.equals("SecondExcuteHumTask") && (operateType.equals("61") || operateType.equals("46") || operateType.equals("4"))) {
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDate = sf.format(date);
            String typeID = "";
            String type = "";
            if (operateType.equals("61")) {
                typeID = "00";
                type = "接单";
            } else if (operateType.equals("46")) {
                typeID = "01";
                type = "回单";
            } else if (operateType.equals("4")) {
                typeID = "02";
                type = "驳回";
            }
            String json = "{'data':" + "{'type':'" + typeID + "','"
                    + "name':['工单号','处理类型', '处理时间'],'" + "values':" + "[ "
                    + "['" + StaticMethod.nullObject2String(main.get("sheetId")) + "','" + type + "','" + currentDate + "']" + "]"
                    + "}" + "}";
            System.out.println("cxfivrcj:json=" + json);
            boolean result = false;
            try {
                Service_ServiceLocator service = new Service_ServiceLocator();
                Service_PortType binding = (Service_PortType) service.getJSONServicePort();
                result = binding.insert(json);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("cxfivrcj:result=" + result);
        }

        String mainInterfaceCaller = StaticMethod.nullObject2String(mainrule.get("mainInterfaceCaller"));
        if (taskName.equals("SecondExcuteHumTask") && operateType.equals("46") && mainInterfaceCaller.equals("jkzc")) {
            String supplier = "HB_JKZC";
            String caller = "HB_EOMS";
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String callTime = sd.format(new Date());
            String nodeName = "dealSheet";
            linkrule.put("sheetId", StaticMethod.nullObject2String(mainrule.get("sheetId")));
            String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
            String mainAlarmNum = StaticMethod.nullObject2String(main.get("mainAlarmNum"));//告警流水号
            String emosSheetId = sheetId + "/" + mainAlarmNum;
            linkrule.put("emosSheetId", emosSheetId);
//			得到告警清除时间
            Date linkFaultAvoidTimejkzc = (Date) linkrule.get("linkFaultAvoidTime");
            System.out.println("linkFaultAvoidTimejkzc111===" + linkFaultAvoidTimejkzc);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String linkFaultAvoidTime = "";
            if (linkFaultAvoidTimejkzc != null) {
                linkFaultAvoidTime = format.format(linkFaultAvoidTimejkzc);
            }
//			String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(linkrule,StaticMethod.getFilePathForUrl("classpath:config/commonfault-crm.xml"),nodeName);
            String opDetail = "<root><request><emosSheetId>" + emosSheetId + "</emosSheetId><coveryTime>" + linkFaultAvoidTime + "</coveryTime></request></root>";
            System.out.println("lizhi:jkzcCoveryInfo=" + opDetail);
            System.out.println("eoms emosSheetId=" + emosSheetId + "====linkFaultAvoidTime=" + linkFaultAvoidTime);
            String result = "";
            try {
                GroupFaultServiceLocator service = new GroupFaultServiceLocator();
                GroupFault_PortType binding = (GroupFault_PortType) service.getGroupFault();
                result = binding.getCoveryInfo(supplier, caller, "", callTime, opDetail);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                System.out.println("lizhi:jkzcCoveryInforesult=" + result);
            }
        }


        if (taskName.equals("reply") && operateType.equals("9") && mainInterfaceCaller.equals("jkzc")) {
            String supplier = "HB_JKZC";
            String caller = "HB_EOMS";
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String callTime = sd.format(new Date());
            String nodeName = "replySheet";
//			linkrule.put("sheetId", StaticMethod.nullObject2String(mainrule.get("sheetId")));
            String sheetId = StaticMethod.nullObject2String(mainrule.get("sheetId"));
            String mainAlarmNum = StaticMethod.nullObject2String(mainrule.get("mainAlarmNum"));//告警流水号
            String emosSheetId = sheetId + "/" + mainAlarmNum;
            linkrule.put("emosSheetId", emosSheetId);
            String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(linkrule, StaticMethod.getFilePathForUrl("classpath:config/commonfault-crm.xml"), nodeName);
            System.out.println("lizhi:jkzcStageFeedback=" + opDetail);
            String result = "";
            try {
                GroupFaultServiceLocator service = new GroupFaultServiceLocator();
                GroupFault_PortType binding = (GroupFault_PortType) service.getGroupFault();
                result = binding.getStageFeedback(supplier, caller, "", callTime, opDetail);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                System.out.println("lizhi:jkzcStageFeedbackresult=" + result);
            }
        }

//		by lyg
        String bohui = "";
        if (taskName.equals("SecondExcuteHumTask") && operateType.equals("4")) {
            String mainT2RejectionNum = StaticMethod.nullObject2String(mainrule.get("mainT2RejectionNum"));
            System.out.println("mainT2RejectionNum=liu=" + mainT2RejectionNum);
            System.out.println("bohui===sheet===" + mainrule.get("sheetId"));
            //自能派单驳回，无线专业 mainNetSortOne（101010401）start
            String mainNetSortOne = StaticMethod.nullObject2String(mainrule.get("mainNetSortOne"));
            String sheetKey = StaticMethod.nullObject2String(mainrule.get("id"));
            System.out.println("mainNetSortOne===" + mainNetSortOne);
            String mainIsOther = StaticMethod.nullObject2String(request.getParameter("mainIsOther"));
            System.out.println("mainIsOther===" + mainIsOther);
            //第3次驳回和跨地市驳回不走自能派单驳回
            if (!"3".equals(mainT2RejectionNum) && "".equals(mainIsOther)) {
                String subroleid = "";//t1自动派给t2的班组
                String linkCiytSubrole = StaticMethod.nullObject2String(request.getParameter("linkCiytSubrole"));
                linkcs.put("linkCiytSubrole", linkCiytSubrole);
                if ("101010401".equals(mainNetSortOne)) {
                    //查询网元对应班组表commonfault_net_team_wx
                    ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
                    String condition = " sheetKey = '" + sheetKey + "' and taskstatus in ('2','8') and taskName = 'SecondExcuteHumTask' ";
                    List taskList = taskservice.getTasksByCondition(condition);
                    String operateRoleId1 = "";
                    if (taskList != null && taskList.size() > 0) {
                        CommonFaultTask commonFaultTask = (CommonFaultTask) taskList.get(0);
                        //得到当前班组
                        operateRoleId1 = StaticMethod.nullObject2String(commonFaultTask.getOperateRoleId());
                    }
                    String mainNetName = StaticMethod.nullObject2String(mainrule.get("mainNetName"));
                    System.out.println("sheetId===lyg=" + mainrule.get("sheetId"));
                    System.out.println("operateRoleId1 === " + operateRoleId1 + "====mainNetName=" + mainNetName);


                    String sheetId = StaticMethod.nullObject2String(mainrule.get("sheetId"));
                    String mainCitySubrole = StaticMethod.nullObject2String(mainrule.get("mainCitySubrole"));
                    String mainFaultGenerantCity = StaticMethod.nullObject2String(mainrule.get("toDeptId"));
                    System.out.println("sheetId=" + sheetId + "=mainCitySubrole=" + mainCitySubrole + "mainNetName=" + mainNetName + "=mainFaultGenerantCity=" + mainFaultGenerantCity + "=" + linkCiytSubrole);
                    Map getRuleMap = new HashMap();
                    getRuleMap.put("sheetId", sheetId);
                    getRuleMap.put("mainCitySubrole", mainCitySubrole);
                    getRuleMap.put("mainNetName", mainNetName);
                    getRuleMap.put("mainFaultGenerantCity", mainFaultGenerantCity);
                    getRuleMap.put("linkCiytSubrole", linkCiytSubrole);
                    getRuleMap.put("operateRoleId", operateRoleId1);
                    Map ruleMap = getRule(getRuleMap);
                    mainCitySubrole = StaticMethod.nullObject2String(ruleMap.get("mainCitySubrole"));
                    mainrule.put("mainCitySubrole", mainCitySubrole);
                    subroleid = StaticMethod.nullObject2String(ruleMap.get("nextSubroleId"));
                } else {
                    subroleid = linkCiytSubrole;
                }
                System.out.println("subroleid====" + subroleid);

                if (!"".equals(subroleid)) {
                    //有可能驳回后，自动派给T2是铁塔班组
                    bohui = "1";
                    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
                    String userId = StaticMethod.nullObject2String(sessionform.getUserid());
//					自动流转时增加link表驳回记录与确认受理记录，task增加驳回记录
                    t1SendT2(mainrule, linkrule, userId, subroleid);

                    Calendar calendar = Calendar.getInstance();
                    calendar.add(13, 15);
                    Date date = calendar.getTime();
//					4.派单到T2
                    Map link = (HashMap) sheetMap.get("link");
                    operateType = "1";
                    taskName = "FirstExcuteTask";
                    phaseId = "SecondExcuteTask";
                    link.put("activeTemplateId", "FirstExcuteHumTask");
                    link.put("operateTime", date);
                    link.put("operateType", operateType);
                    link.put("operateRoleId", "fangmin");
                    link.put("operateUserId", "fangmin");

                    operate.put("phaseId", "SecondExcuteTask");
                    operate.put("dealPerformer", subroleid);//综合班组
                    operate.put("dealPerformerLeader", subroleid);//综合班组
                    operate.put("dealPerformerType", "subrole");//综合班组
                    sheetMap.put("link", link);
                    sheetMap.put("operate", operate);
                }
            }

//			自能派单驳回，无线专业 mainNetSortOne（101010401）end

            if ("".equals(mainT2RejectionNum)) {
                System.out.println("inif1");
                mainT2RejectionNum = "1";
            } else if ("1".equals(mainT2RejectionNum) || "2".equals(mainT2RejectionNum)) {
                System.out.println("inif2");
                mainT2RejectionNum = String.valueOf(Integer.parseInt(mainT2RejectionNum) + 1);
            } else if ("3".equals(mainT2RejectionNum)) {
                System.out.println("inif3");
//				表示以前驳回了3次，这是最后一次驳回（第4次），此次驳回后，流程自动流转到T2
                mainT2RejectionNum = "4";
                //故障地市不为空，自动流转T2
                String mainFaultGenerantCity = StaticMethod.nullObject2String(mainrule.get("toDeptId"));
                System.out.println("故障城市mainFaultGenerantCity=" + mainFaultGenerantCity);
                if (!"".equals(mainFaultGenerantCity)) {
//					流程自动流转到T2
//					//根据故障城市查询对应的subroleid
                    INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
                    String subroleid = netOwnershipwirelessManager.getSubroleId(mainFaultGenerantCity);
                    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
                    String userId = StaticMethod.nullObject2String(sessionform.getUserid());
                    System.out.println("综合班组subroleid=" + subroleid);
                    //自动流转时增加link表驳回记录与确认受理记录，task增加驳回记录
                    t1SendT2(mainrule, linkrule, userId, subroleid);

                    Calendar calendar = Calendar.getInstance();
                    calendar.add(13, 10);
                    Date date = calendar.getTime();
//					4.派单到T2
                    Map link = (HashMap) sheetMap.get("link");
                    operateType = "1";
                    taskName = "FirstExcuteTask";
                    phaseId = "SecondExcuteTask";
                    link.put("activeTemplateId", "FirstExcuteHumTask");
                    link.put("operateTime", date);
                    link.put("operateType", operateType);
                    link.put("operateRoleId", "fangmin");
                    link.put("operateUserId", "fangmin");

                    operate.put("phaseId", "SecondExcuteTask");
                    operate.put("dealPerformer", subroleid);//综合班组
                    operate.put("dealPerformerLeader", subroleid);//综合班组
                    operate.put("dealPerformerType", "subrole");//综合班组
                    sheetMap.put("link", link);
                    sheetMap.put("operate", operate);

                }
            }
            System.out.println("mainT2RejectionNum=yong=" + mainT2RejectionNum);
            mainrule.put("mainT2RejectionNum", mainT2RejectionNum);
            sheetMap.put("main", mainrule);
            flag = true;

        }

        System.out.println("lyg==flag=" + flag);

//		判断该工单是否在T1，并判断是否为驳回
        mainIFTowner = StaticMethod.nullObject2String(main.get("mainIFTowner"));
        String phaseId1 = StaticMethod.nullObject2String(operate.get("phaseId"));
        //条件还需要加入mainIFTowner=3，表示是铁塔驳回的，之后在派到T2时就不给铁塔平台推送了
        System.out.println("taskName=" + taskName + "===phaseId1=" + phaseId1);
        if ("1".equals(bohui) || ("FirstExcuteHumTask".equals(taskName) && "SecondExcuteHumTask".equals(phaseId1) && !"3".equals(mainIFTowner))) {
            mainrule = CommonFaultBO.townerSend(mainrule, operate);
        }

        //工单在T1环节，判断该工单是否是跨地市驳回的 mainIsOther 1表示跨地市驳回，空表示没有
        //T1环节 linkIsPass 同意mainOtherSubrole，不同意mainThisSubrole
        String mainIsOther = StaticMethod.nullObject2String(mainrule.get("mainIsOther"));
        String linkIsPass = StaticMethod.nullObject2String(request.getParameter("linkIsPass"));
        System.out.println("sheetId=T1=" + mainrule.get("sheetId") + "taskName=" + taskName + "==phaseId1=" + phaseId1 + "==operateType=" + operateType + "==mainIsOther=" + mainIsOther + "==linkIsPass=" + linkIsPass);
        if ("FirstExcuteHumTask".equals(taskName) && "SecondExcuteHumTask".equals(phaseId1) && "1".equals(operateType)) {
            String subroleid = "";
            if ("101011201".equals(linkIsPass)) {//同意，派个说选择的跨地市班组
                subroleid = StaticMethod.nullObject2String(mainrule.get("mainOtherSubrole"));
            } else {//不同意，派给原来班组
                subroleid = StaticMethod.nullObject2String(mainrule.get("mainThisSubrole"));
            }
            //将 是否跨地市班组 驳回标准，置空
            mainrule.put("mainIsOther", "");
            operate.put("dealPerformer", subroleid);
            operate.put("dealPerformerLeader", subroleid);
            operate.put("dealPerformerType", "subrole");
            flag = true;
        }

        if (flag) {
            sheetMap.put("main", mainrule);
            sheetMap.put("operate", operate);
            sheetMap.put("link", linkcs);
            setFlowEngineMap(sheetMap);
            request.setAttribute("flag", "true");
        } else {
            request.setAttribute("flag", "false");
        }

    }

    public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        String operateRoleId = StaticMethod.nullObject2String(request.getParameter("operateRoleId"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
//		ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
        System.out.println("===operateRoleId====" + operateRoleId);
        TawSystemSubRole subrole = mgr.getTawSystemSubRole(operateRoleId);
        request.setAttribute("operateRoleId", operateRoleId);
        if (subrole != null)
            request.setAttribute("roleId", (new StringBuffer(String.valueOf(subrole.getRoleId()))).toString());
        request.setAttribute("operateDeptId", sessionform.getDeptid());
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        if (taskName.equals(""))
            taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        if (taskName.equals("ExamineHumTask") || taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") || taskName.equals("ThirdExcuteHumTask"))
            super.setParentTaskOperateWhenRejct(request);
        String roleLeader = StaticMethod.nullObject2String(request.getParameter("roleLeader"));
        System.out.println("lizhi:leadershowInputDealPage=" + roleLeader);
        String centerMonitor = StaticMethod.nullObject2String(request.getParameter("centerMonitor"));
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        if (taskName.equals("HoldHumTask") && operateType.equals("17") && "true".equals(centerMonitor)) {
            String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
            if (!preLinkId.equals("") && !preLinkId.equals("null")) {
                BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
                String activeTemplateId = StaticMethod.nullObject2String(preLink.getActiveTemplateId());
                String operateUserId = StaticMethod.nullObject2String(preLink.getOperateUserId());
                String preOperateRoleId = StaticMethod.nullObject2String(preLink.getOperateRoleId());
                String prePreLinkId = StaticMethod.nullObject2String(preLink.getPreLinkId());
                System.out.println("lizhi:activeTemplateId=" + activeTemplateId + "operateUserId=" + operateUserId + "prePreLinkId=" + prePreLinkId + "preOperateRoleId=" + preOperateRoleId);
                if (!"".equals(preOperateRoleId) && "SecondExcuteHumTask".equals(activeTemplateId)) {
                    TawSystemSubRole presubrole = mgr.getTawSystemSubRole(preOperateRoleId);
                    if (presubrole == null) {
                        CommonFaultLink prePreLink = (CommonFaultLink) getLinkService().getSingleLinkPO(prePreLinkId);
                        preOperateRoleId = prePreLink.getOperateRoleId();
                        presubrole = mgr.getTawSystemSubRole(preOperateRoleId);
                    }
                    String preDeptId = "";
                    if (presubrole != null)
                        preDeptId = StaticMethod.nullObject2String(presubrole.getDeptId());
                    request.setAttribute("preDeptId", preDeptId);
                    System.out.println("lizhi:preDeptId=" + preDeptId);
                }
                request.setAttribute("rejectActiveTemplateId", activeTemplateId);
                request.setAttribute("rejectOperateUserId", operateUserId);
                request.setAttribute("preOperateRoleId", preOperateRoleId);
            }
        }
        System.out.println("fengmin:220151112 The taskName is " + taskName + "  The operateType is " + operateType + "  The centerMonitor is " + centerMonitor);
        if (taskName.equals("FirstExcuteHumTask") && operateType.equals("1") && "true".equals(centerMonitor)) {
            INetOwnershipManager netOwnershipManager = (INetOwnershipManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipManager");
            INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
            CommonFaultMain commonFaultMain = (CommonFaultMain) getMainService().getSingleMainPO(sheetKey);
//			String mainNeId = StaticMethod.nullObject2String(commonFaultMain.getMainNeId());
            String mainNetName = StaticMethod.nullObject2String(commonFaultMain.getMainNetName());
            String toDeptId = StaticMethod.nullObject2String(commonFaultMain.getToDeptId());
            String firstsubroleid = "";
            String tietasubroleid = "";
            String ccsubroleid = "";
            if (!mainNetName.equals("")) {
                NetOwnership netownership = netOwnershipManager.getNetOwnershipByNetName(mainNetName);
                NetOwnershipwireless netownershipwireless = netOwnershipwirelessManager.getNetOwnershipByNetName(mainNetName);
                if (netownership != null) {
                    firstsubroleid = StaticMethod.nullObject2String(netownership.getTeamRoleId());
                    ccsubroleid = StaticMethod.nullObject2String(netownership.getCcObject());
                }
                System.out.println("The mainNetName is " + mainNetName);
                if ("101010401".equals(commonFaultMain.getMainNetSortOne()) && netownershipwireless != null) {
                    System.out.println("The ttrole is " + netownershipwireless.getTtRoleId());
                    firstsubroleid = StaticMethod.nullObject2String(netownershipwireless.getTeamRoleId());
                    tietasubroleid = StaticMethod.nullObject2String(netownershipwireless.getTtRoleId());
                    ccsubroleid = StaticMethod.nullObject2String(netownershipwireless.getCcObject());
                }
            } else {
                firstsubroleid = netOwnershipManager.getSubroleId(toDeptId);
                ccsubroleid = netOwnershipManager.getSubroleId(toDeptId);
            }
            System.out.println("lizhi:firstsubroleid=" + firstsubroleid);
            System.out.println("penghuan:ccsubroleid=" + ccsubroleid);
            System.out.println("fengmin:tietasubroleid=" + tietasubroleid);
            request.setAttribute("firstsubroleid", firstsubroleid);
            request.setAttribute("ccsubroleid", ccsubroleid);
            request.setAttribute("tietasubroleid", tietasubroleid);

            //集客工单默认故障班组 by lyg
            String mainReserved1 = StaticMethod.nullObject2String(commonFaultMain.getMainReserved1());//故障区县
            String mainNetSortOne = StaticMethod.nullObject2String(commonFaultMain.getMainNetSortOne());
            IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            System.out.println("mainReserved1===" + mainReserved1 + "mainNetSortOne===" + mainNetSortOne + "==sheetId=" + commonFaultMain.getSheetId());
            if ("101010417".equals(mainNetSortOne) && !"".equals(mainReserved1)) {
                String jtkhSql = "SELECT * FROM commonfult_jtkh_subrole WHERE alarmcity = '" + mainReserved1 + "'";
                List jtkhList = sqlMgr.getSheetAccessoriesList(jtkhSql);
                if (jtkhList != null && jtkhList.size() > 0) {
                    String subroleidjkgd = StaticMethod.nullObject2String(((Map) jtkhList.get(0)).get("subroleid"));
                    request.setAttribute("subroleidjkgd", subroleidjkgd);
                }
            }

        }
        if (taskName.equals("SecondExcuteHumTask") && (operateType.equals("46") || operateType.equals("11"))) {
            String userid = StaticMethod.nullObject2String(sessionform.getUserid());
            ITawSystemUserRefRoleManager tawSystemUserRefRoleManager = (ITawSystemUserRefRoleManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
            String condition = " and (subrole.roleId = '192' or subrole.roleId = '8005106') and subrole.deptId ='1801' and refrole.userid = '" + userid + "'";
            ArrayList subrolelist = (ArrayList) tawSystemUserRefRoleManager.getSubRoleByCondition(condition);
            if (subrolelist.size() > 0)
                request.setAttribute("needRecoveryUnit", "true");
        }

        //判断在T2环节处理的班组是否是 综合维护班组 by lyg
        if (taskName.equals("SecondExcuteHumTask") && operateType.equals("4")) {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
            String condition = " sheetKey = '" + sheetKey + "' and taskstatus in ('2','8') and taskName = 'SecondExcuteHumTask' ";
            List taskList = taskservice.getTasksByCondition(condition);
            String operateRoleId1 = "";
            if (taskList != null && taskList.size() > 0) {
                CommonFaultTask commonFaultTask = (CommonFaultTask) taskList.get(0);
                //得到当前班组
                operateRoleId1 = StaticMethod.nullObject2String(commonFaultTask.getOperateRoleId());
            }
            String citySubrole = StaticMethod.nullObject2String(ifCitySubrole(operateRoleId1));
            request.setAttribute("citySubrole", citySubrole);

        }

        String batch = StaticMethod.nullObject2String(request.getParameter("batch"));
        BocoLog.info(com.boco.eoms.sheet.commonfault.webapp.action.CommonFaultSheetMethod.class, "batch=" + batch);
        if (!"true".equals(batch) && "46".equals(operateType) && ("FirstExcuteHumTask".equals(taskName) || "SecondExcuteHumTask".equals(taskName) || "ThirdExcuteHumTask".equals(taskName))) {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            CommonFaultMain commonFaultMain = (CommonFaultMain) getMainService().getSingleMainPO(sheetKey);
            String netSortOne = StaticMethod.nullObject2String(commonFaultMain.getMainNetSortOne());
            String alarmID = StaticMethod.nullObject2String(commonFaultMain.getMainAlarmId());
            String sheetId = StaticMethod.nullObject2String(commonFaultMain.getSheetId());
            BocoLog.info(com.boco.eoms.sheet.commonfault.webapp.action.CommonFaultSheetMethod.class, "工单：" + sheetId + "的的网络一级分类：" + netSortOne + "和告警ID是：" + alarmID);
            if (!"".equals(alarmID) && "101010405".equals(netSortOne)) {
                IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger) ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
                ID2NameService id2NameService = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
                List ruelList = ptnPretreatmentRuleManger.getListByCondition(" where alatmID='" + alarmID + "'");
                if (ruelList != null && !ruelList.isEmpty()) {
                    List dealDescList = new ArrayList();
                    PtnPretreatmentRule ptnPretreatmentRule = null;
                    String dictId = "";
                    Set dictIdSet = new HashSet();
                    for (int i = 0; i < ruelList.size(); i++) {
                        ptnPretreatmentRule = (PtnPretreatmentRule) ruelList.get(i);
                        dictId = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultDealDesc());
                        if (!dictIdSet.contains(dictId))
                            dealDescList.add(id2NameService.id2Name(dictId, "ItawSystemDictTypeDao"));
                        dictIdSet.add(dictId);
                    }

                    request.setAttribute("dealDescList", dealDescList);
                }
            }
        }
        request.setAttribute("roleLeader", roleLeader);
        request.setAttribute("centerMonitor", centerMonitor);
    }

    public void showDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showDetailPage(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"), "");
        String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"), "");
        String operateRoleId = StaticMethod.nullObject2String(request.getParameter("operateRoleId"), "");

        String undoFlag = StaticMethod.nullObject2String(request.getParameter("undoFlag"), "");
        request.setAttribute("undoFlag", undoFlag);
        System.out.println("showDetailPage==undoFlag=" + undoFlag);

        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean(beanName);
        if ((taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") || taskName.equals("ThirdExcuteHumTask")) && !taskStatus.equals("") && !taskStatus.equals("5")) {
            ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();
            List showInvokeRelationShipList = commonFaultMainManager.showInvokeRelationShipList(sheetKey);
            if (showInvokeRelationShipList != null && showInvokeRelationShipList.size() > 0)
                request.setAttribute("ifInvokeUrgentFault", "yes");
            else
                request.setAttribute("ifInvokeUrgentFault", "no");
        }
        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
        List preHistoryList = new ArrayList();
        if (!preLinkId.equals("")) {
            BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
            request.setAttribute("preLink", preLink);
            preHistoryList.add(preLink);
            request.setAttribute("preHistoryList", preHistoryList);
        }
        ICommonFaultLinkManager commonFaultLinkManager = (ICommonFaultLinkManager) baseSheet.getLinkService();
        String condition = "operateType = '17' and activeTemplateId = 'HoldHumTask' and mainId = '" + sheetKey + "' order by operateTime desc";
        List commonFaultLinkList = commonFaultLinkManager.getLinksBycondition(condition, "CommonFaultLink");
        if (commonFaultLinkList.size() > 0) {
            CommonFaultLink CommonFaultLink = (CommonFaultLink) commonFaultLinkList.get(0);
            String linkUntreadReason = StaticMethod.nullObject2String(CommonFaultLink.getLinkUntreadReason());
            System.out.println("lizhi:linkUntreadReason=" + linkUntreadReason);
            request.setAttribute("linkUntreadReason", linkUntreadReason);
        }
        ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();
        CommonFaultMain commonFaultMain = (CommonFaultMain) commonFaultMainManager.loadSinglePO(sheetKey);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String alarmId = StaticMethod.null2String(commonFaultMain.getMainAlarmId());
        ApplicationContextHolder holder = ApplicationContextHolder.getInstance();
        ITawSystemUserManager userManager = (ITawSystemUserManager) holder.getBean("ItawSystemUserSaveManagerFlush");
        TawSystemUser user = userManager.getUserByuserid(sessionform.getUserid());
        String email = StaticMethod.null2String(user.getEmail());
        String province = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.province");
        System.out.println("@@@@@@@@@@province" + province);
        String systemName = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.systemName");
        System.out.println("@@@@@@@@@@systemName" + systemName);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = sdf.format(date);
        System.out.println("@@@@@@@@@@timeStamp" + timeStamp);
        String token = province + ";" + systemName + ";" + alarmId + ";" + email + ";" + timeStamp;
        System.out.println(token + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String key = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.key");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        byte raw[] = key.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        cipher.init(1, skeySpec);
        byte encrypted[] = cipher.doFinal(token.getBytes("utf-8"));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encrypted.length; i++) {
            String hex = Integer.toHexString(encrypted[i] & 0xff);
            if (hex.length() == 1)
                hex = '0' + hex;
            sb.append(hex.toUpperCase());
        }

        String ret = (new BASE64Encoder()).encode(sb.toString().getBytes());
        token = ret.toString();
        request.setAttribute("token", token);
        String mainIfCenterMonitor = StaticMethod.nullObject2String(commonFaultMain.getMainIfCenterMonitor(), "");
        if ("1".equals(mainIfCenterMonitor))
            request.setAttribute("centerMonitor", "true");
        if (taskName.equals("SecondExcuteHumTask") && taskStatus.equals("2") && "1".equals(mainIfCenterMonitor)) {
            ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
            String userid = StaticMethod.nullObject2String(sessionform.getUserid(), "");
            System.out.println("lizhi:operateRoleId=" + operateRoleId + "userid=" + userid);
            if (userid.equals(operateRoleId)) {
                String conditionhold = "sheetKey = '" + sheetKey + "' and taskName = 'HoldHumTask'";
                System.out.println("lizhi:conditionhold=" + conditionhold);
                List taskList = taskservice.getTasksByCondition(conditionhold);
                if (taskList.size() > 0) {
                    CommonFaultTask commonFaultTask = (CommonFaultTask) taskList.get(taskList.size() - 1);
                    String preDealRoleId = StaticMethod.nullObject2String(commonFaultTask.getPreDealRoleId());
                    System.out.println("lizhi:preDealRoleId=" + preDealRoleId);
                    operateRoleId = preDealRoleId;
                }
            }
            ITawSystemUserRefRoleManager tawSystemUserRefRoleManager = (ITawSystemUserRefRoleManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
            ArrayList roleLeaderlist = (ArrayList) tawSystemUserRefRoleManager.getRoleLeaderlistBySubRoleid(operateRoleId);
            for (int i = 0; i < roleLeaderlist.size(); i++) {
                TawSystemUserRefRole tawSystemUserRefRole = (TawSystemUserRefRole) roleLeaderlist.get(i);
                String leader = StaticMethod.nullObject2String(tawSystemUserRefRole.getUserid(), "");
                System.out.println("lizhi:leader=" + leader);
                if (leader.equals(userid)) {
                    request.setAttribute("roleLeader", "true");
                    request.setAttribute("operateRoleId", operateRoleId);
                }
            }

        }
    }

    public void showDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showDealPage(mapping, form, request, response);
        String roleLeader = StaticMethod.nullObject2String(request.getParameter("roleLeader"));
        System.out.println("lizhi:leadershowDealPage=" + roleLeader);
        String centerMonitor = StaticMethod.nullObject2String(request.getParameter("centerMonitor"));
        request.setAttribute("roleLeader", roleLeader);
        request.setAttribute("centerMonitor", centerMonitor);
        String undoFlag = StaticMethod.nullObject2String(request.getParameter("undoFlag"));
        System.out.println("showDealPage=undoFlag=" + undoFlag);
        request.setAttribute("undoFlag", undoFlag);
    }

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("subAuditPerformer", "subAuditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public Map getAttachmentAttributeOfOjbect() {
        Map objectMap = new HashMap();
        List mainAttachmentAttributes = new ArrayList();
        mainAttachmentAttributes.add("sheetAccessories");
        List linkAttachmentAttributes = new ArrayList();
        linkAttachmentAttributes.add("nodeAccessories");
        objectMap.put("mainObject", mainAttachmentAttributes);
        objectMap.put("linkObject", linkAttachmentAttributes);
        return objectMap;
    }

    public HashMap makeT1(HashMap sheetMap)
            throws Exception {
        BocoLog.info(this, "====makeT1====");
        Map mainMap = (HashMap) sheetMap.get("main");
        Map tmpLinkMap = (HashMap) sheetMap.get("link");
        Map operateMap = (HashMap) sheetMap.get("operate");
        if (operateMap.get("phaseId").equals("SecondExcuteTask")) {
            CommonFaultTask task = (CommonFaultTask) getTaskService().getTaskModelObject();
            Date nowDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            sheetMap.put("operate", operateMap);
            Map linkMap = new HashMap();
            linkMap.putAll(tmpLinkMap);
            for (Iterator names = linkMap.keySet().iterator(); names.hasNext(); ) {
                String name = (String) names.next();
                Object value = linkMap.get(name);
                if (value != null) {
                    Class clazz = value.getClass();
                    String className = clazz.getName();
                    if (className.equalsIgnoreCase("java.util.Date")) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String datestr = df.format((Date) value);
                        linkMap.put(name, datestr);
                    }
                }
            }

            String tkid = "_AI:" + UUIDHexGenerator.getInstance().getID();
            CommonFaultLink link1 = (CommonFaultLink) getLinkService().getLinkObject().getClass().newInstance();
            SheetBeanUtils.populateMap2Bean(link1, linkMap);
            link1.setId(UUIDHexGenerator.getInstance().getID());
            link1.setMainId((String) mainMap.get("id"));
            link1.setOperateTime(nowDate);
            link1.setToOrgRoleId((String) mainMap.get("sendRoleId"));
            String activeTemplateId = (String) linkMap.get("activeTemplateId");
            if (activeTemplateId.equals("DraftHumTask")) {
                link1.setOperateType(new Integer(3));
                link1.setActiveTemplateId(activeTemplateId);
            } else if (activeTemplateId.equals("BackHumTask")) {
                link1.setOperateType(new Integer(3));
                link1.setActiveTemplateId(activeTemplateId);
            } else {
                link1.setOperateType(new Integer(0));
                link1.setActiveTemplateId("");
            }
            link1.setAiid("");
            link1.setPreLinkId("");
            link1.setNodeAccessories("");
            getLinkService().addLink(link1);
            CommonFaultLink link2 = (CommonFaultLink) getLinkService().getLinkObject().getClass().newInstance();
            SheetBeanUtils.populateMap2Bean(link2, linkMap);
            link2.setId(UUIDHexGenerator.getInstance().getID());
            link2.setOperateType(new Integer("61"));
            link2.setActiveTemplateId("FirstExcuteHumTask");
            link2.setOperateTime(new Date(nowDate.getTime() + 1000L));
            link2.setMainId((String) mainMap.get("id"));
            link2.setToOrgRoleId((String) mainMap.get("sendRoleId"));
            link2.setPreLinkId(link1.getId());
            link2.setAiid(tkid);
            link2.setNodeAccessories("");
            getLinkService().addLink(link2);
            task.setId(tkid);
            task.setCreateTime(nowDate);
            task.setTaskStatus("5");
            task.setProcessId((String) mainMap.get("piid"));
            task.setSheetKey((String) mainMap.get("id"));
            task.setSheetId((String) mainMap.get("sheetId"));
            task.setTitle((String) mainMap.get("title"));
            task.setAcceptTimeLimit(link1.getNodeAcceptLimit());
            task.setCompleteTimeLimit(link1.getNodeCompleteLimit());
            task.setPreLinkId(link1.getId());
            task.setCurrentLinkId((String) linkMap.get("id"));
            task.setIfWaitForSubTask("false");
            task.setCreateDay(calendar.get(5));
            task.setCreateMonth(calendar.get(2) + 1);
            task.setCreateYear(calendar.get(1));
            task.setTaskDisplayName("T1处理");
            task.setTaskName("FirstExcuteHumTask");
            task.setOperateRoleId(link1.getOperateRoleId());
            task.setTaskOwner(link1.getOperateUserId());
            task.setOperateType("subrole");
            task.setFlowName("CommonFaultMainFlowProcess");
            task.setParentTaskId(task.getId());
            task.setSendTime((Date) mainMap.get("sendTime"));
            getTaskService().addTask(task);
            tmpLinkMap.put("operateType", "1");
            tmpLinkMap.put("mainId", (String) mainMap.get("id"));
            tmpLinkMap.put("preLinkId", link1.getId());
            tmpLinkMap.put("aiid", task.getId());
            tmpLinkMap.put("operateTime", new Date(nowDate.getTime() + 2000L));
            tmpLinkMap.put("nodeCompleteLimit", mainMap.get("mainCompleteLimitT2"));
            tmpLinkMap.put("activeTemplateId", "FirstExcuteHumTask");
            tmpLinkMap.put("nodeAccessories", mainMap.get("firstNodeAccessories"));
            sheetMap.put("link", tmpLinkMap);
        }
        return sheetMap;
    }

    public void showInputNewSheetPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputNewSheetPage(mapping, form, request, response);
        BaseMain main = (BaseMain) request.getAttribute("sheetMain");
        main.setId(UUIDHexGenerator.getInstance().getID());
        request.setAttribute("sheetMain", main);
        String processName = getMainService().getFlowTemplateName();
        ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder.getInstance().getBean("ItawSheetAccessManager");
        com.boco.eoms.sheet.tool.access.model.TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName, "FirstExcuteHumTask");
        request.setAttribute("tawSheetAccess1", access);
    }

    public void showInputTemplateSheetPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputTemplateSheetPage(mapping, form, request, response);
        String processName = getMainService().getFlowTemplateName();
        ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder.getInstance().getBean("ItawSheetAccessManager");
        com.boco.eoms.sheet.tool.access.model.TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName, "FirstExcuteHumTask");
        request.setAttribute("tawSheetAccess1", access);
    }

    public void showUndoListForSameTeam(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
        String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
        String orderCondition = "";
        if (!order.equals(""))
            if (order.equals("1"))
                order = " asc";
            else
                order = " desc";
        if (!sort.equals(""))
            orderCondition = " " + sort + order;
        String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
        if (!exportType.equals(""))
            pageSize = new Integer(-1);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        BaseMain mainObject = (BaseMain) getMainService().getMainObject().getClass().newInstance();
        ITask taskObject = (ITask) getTaskService().getTaskModelObject().getClass().newInstance();
        Map condition = new HashMap();
        condition.put("mainObject", mainObject);
        condition.put("taskObject", taskObject);
        condition.put("taskName", "FirstExcuteHumTask");
        condition.put("orderCondition", orderCondition);
        String flowName = getMainService().getFlowTemplateName();
        condition.put("flowName", flowName);
        HashMap taskListMap = getTaskService().getAcceptTaskByRole(condition, userId, pageIndex, pageSize);
        int total = ((Integer) taskListMap.get("taskTotal")).intValue();
        List taskOvertimeList = (List) taskListMap.get("taskList");
        List taskMapList = new ArrayList();
        List taskList = new ArrayList();
        if (taskOvertimeList != null && taskOvertimeList.size() > 0) {
            IOvertimeTipManager service = (IOvertimeTipManager) ApplicationContextHolder.getInstance().getBean("iOvertimeTipManager");
            List timeList = service.getEffectOvertimeTip(getMainService().getFlowTemplateName(), userId);
            HashMap columnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
            HashMap columnMapOverTip = OvertimeTipUtil.getNotOverTimeColumnByMapping(flowName);
            for (int i = 0; i < taskOvertimeList.size(); i++) {
                ITask tmptask = null;
                Map taskMap = new HashMap();
                HashMap conditionMap = new HashMap();
                if (columnMap.size() > 0) {
                    Object tmpObjArr[] = (Object[]) taskOvertimeList.get(i);
                    tmptask = (ITask) tmpObjArr[0];
                    Iterator it = columnMap.keySet().iterator();
                    int j = 0;
                    String elementKey;
                    String tempcolumn;
                    for (; it.hasNext(); taskMap.put(columnMap.get(elementKey), tempcolumn)) {
                        j++;
                        elementKey = (String) it.next();
                        tempcolumn = (String) tmpObjArr[j];
                        conditionMap.put(elementKey, tempcolumn);
                    }

                } else {
                    tmptask = (ITask) taskOvertimeList.get(i);
                }
                if (exportType.equals("")) {
                    String overtimeFlag = OvertimeTipUtil.setOvertimeTipFlag(columnMapOverTip, tmptask.getCompleteTimeLimit(), conditionMap, timeList, flowName);
                    taskMap.put("overtimeType", overtimeFlag);
                }
                taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
                taskList.add(tmptask);
                taskMapList.add(taskMap);
            }

        }
        request.setAttribute("taskList", taskMapList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("findForward", "list");
        request.setAttribute("module", mapping.getPath().substring(1));
        String workflowName = getMainService().getFlowTemplateName();
        ArrayList phaseIdList = new ArrayList();
        Map phaseIdMap = new HashMap();
        FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName, getRoleConfigPath());
        FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
        if (flowDefine != null) {
            PhaseId phaseIds[] = flowDefine.getPhaseId();
            for (int i = 0; i < phaseIds.length; i++) {
                PhaseId phaseId = phaseIds[i];
                if (!phaseId.getId().equals("receive")) {
                    phaseIdMap.put(phaseId.getId(), phaseId.getName());
                    phaseIdList.add(phaseId.getId());
                }
            }

        }
        request.setAttribute("phaseIdMap", phaseIdMap);
        request.setAttribute("stepIdList", phaseIdList);
        String batch = StaticMethod.null2String(request.getParameter("batch"));
        if (!batch.equals("") && batch.equals("true")) {
            Map tempMap = new HashMap();
            String dictName = "dict-sheet-" + mapping.getPath().substring(1);
            List dictItems = DictMgrLocator.getDictService().getDictItems(Util.constituteDictId(dictName, "activeTemplateId"));
            for (Iterator it = dictItems.iterator(); it.hasNext(); ) {
                DictItemXML dictItemXml = (DictItemXML) it.next();
                String description = dictItemXml.getDescription();
                if (description.equals("batch:true"))
                    tempMap.put(dictItemXml.getItemId(), dictItemXml.getItemName());
            }

            Map batchTaskMap = new HashMap();
            if (tempMap.size() > 0) {
                for (Iterator it = tempMap.keySet().iterator(); it.hasNext(); ) {
                    String taskName = (String) it.next();
                    for (Iterator tasks = taskList.iterator(); tasks.hasNext(); ) {
                        ITask task = (ITask) tasks.next();
                        if (taskName.equals(task.getTaskName()) && (task.getSubTaskFlag() == null || task.getSubTaskFlag().equals("false") || task.getSubTaskFlag().equals(""))) {
                            batchTaskMap.put(task.getTaskName(), task.getTaskDisplayName());
                            break;
                        }
                    }

                }

            }
            request.setAttribute("batchTaskMapKey", batchTaskMap.keySet());
            request.setAttribute("batchTaskMap", batchTaskMap);
        }
    }

    public void showAtomDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        showDetailPageAtom(mapping, form, request, response);
        CommonFaultMain mainObject = (CommonFaultMain) request.getAttribute("sheetMain");
        CommonFaultTask task = (CommonFaultTask) request.getAttribute("task");
        String isAccept = null;
        if (task.getTaskStatus().equals("2"))
            isAccept = "0";
        if (task.getTaskStatus().equals("8"))
            isAccept = "1";
        String asXML = showAtomDetail(mainObject, task, isAccept, request);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(asXML);
    }

    public static String showAtomDetail(CommonFaultMain mainObject, ITask task, String isAccept, HttpServletRequest request)
            throws DictServiceException {
        Document document = DocumentFactory.getInstance().createDocument();
        Element root = document.addElement("process");
        Element attributes = root.addElement("attributes");
        Element attribute1 = attributes.addElement("attribute");
        Element title1 = attribute1.addElement("title");
        Element name1 = attribute1.addElement("name");
        title1.setText("工单流水号");
        name1.setText(StaticMethod.null2String(mainObject.getSheetId()));
        Element attribute2 = attributes.addElement("attribute");
        Element title2 = attribute2.addElement("title");
        Element name2 = attribute2.addElement("name");
        title2.setText("工单状态");
        IDictService serviceOne = (IDictService) ApplicationContextHolder.getInstance().getBean("DictService");
        String sheetStatus = (String) serviceOne.itemId2name("dict-sheet-common#sheetStatus", mainObject.getStatus());
        name2.setText(sheetStatus);
        Element attribute3 = attributes.addElement("attribute");
        Element title3 = attribute3.addElement("title");
        Element name3 = attribute3.addElement("name");
        title3.setText("工单主题");
        name3.setText(StaticMethod.null2String(mainObject.getTitle()));
        Element attribute4 = attributes.addElement("attribute");
        Element title4 = attribute4.addElement("title");
        Element name4 = attribute4.addElement("name");
        title4.setText("操作人");
        ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
        String operateName = service.id2Name(mainObject.getSendUserId(), "tawSystemUserDao");
        name4.setText(operateName);
        Element attribute5 = attributes.addElement("attribute");
        Element title5 = attribute5.addElement("title");
        Element name5 = attribute5.addElement("name");
        title5.setText("操作部门");
        String operateDept = service.id2Name(mainObject.getSendDeptId(), "tawSystemDeptDao");
        name5.setText(operateDept);
        Element attribute6 = attributes.addElement("attribute");
        Element title6 = attribute6.addElement("title");
        Element name6 = attribute6.addElement("name");
        title6.setText("操作人当前角色");
        String operateRoleID = service.id2Name(mainObject.getSendRoleId(), "tawSystemSubRoleDao");
        name6.setText(operateRoleID);
        Element attribute7 = attributes.addElement("attribute");
        Element title7 = attribute7.addElement("title");
        Element name7 = attribute7.addElement("name");
        title7.setText("操作人联系方式");
        name7.setText(StaticMethod.null2String(mainObject.getSendContact()));
        Element attribute8 = attributes.addElement("attribute");
        Element title8 = attribute8.addElement("title");
        Element name8 = attribute8.addElement("name");
        title8.setText("操作时间");
        String operatetime = StaticMethod.date2String(mainObject.getSendTime());
        name8.setText(operatetime);
        Element attribute9 = attributes.addElement("attribute");
        Element title9 = attribute9.addElement("title");
        Element name9 = attribute9.addElement("name");
        title9.setText("网管告警ID");
        name9.setText(StaticMethod.null2String(mainObject.getMainAlarmId()));
        Element attribute10 = attributes.addElement("attribute");
        Element title10 = attribute10.addElement("title");
        Element name10 = attribute10.addElement("name");
        title10.setText("网管告警流水号");
        name10.setText(StaticMethod.null2String(mainObject.getMainAlarmNum()));
        Element attribute11 = attributes.addElement("attribute");
        Element title11 = attribute11.addElement("title");
        Element name11 = attribute11.addElement("name");
        title11.setText("告警清除时间");
        String alarmSolveDate = StaticMethod.date2String(mainObject.getMainAlarmSolveDate());
        name11.setText(alarmSolveDate);
        Element attribute12 = attributes.addElement("attribute");
        Element title12 = attribute12.addElement("title");
        Element name12 = attribute12.addElement("name");
        title12.setText("告警来源");
        name12.setText(StaticMethod.null2String(mainObject.getMainAlarmSource()));
        Element attribute13 = attributes.addElement("attribute");
        Element title13 = attribute13.addElement("title");
        Element name13 = attribute13.addElement("name");
        title13.setText("告警逻辑分类");
        name13.setText(StaticMethod.null2String(mainObject.getMainAlarmLogicSort()));
        Element attribute14 = attributes.addElement("attribute");
        Element title14 = attribute14.addElement("title");
        Element name14 = attribute14.addElement("name");
        title14.setText("告警逻辑子类");
        name14.setText(StaticMethod.null2String(mainObject.getMainAlarmLogicSort()));
        Element attribute15 = attributes.addElement("attribute");
        Element title15 = attribute15.addElement("title");
        Element name15 = attribute15.addElement("name");
        title15.setText("告警描述");
        name15.setText(StaticMethod.null2String(mainObject.getMainAlarmDesc()));
        Element attribute16 = attributes.addElement("attribute");
        Element title16 = attribute16.addElement("title");
        Element name16 = attribute16.addElement("name");
        title16.setText("网络分类(一级)");
        String netSortOne = service.id2Name(mainObject.getMainNetSortOne(), "ItawSystemDictTypeDao");
        name16.setText(netSortOne);
        Element attribute17 = attributes.addElement("attribute");
        Element title17 = attribute17.addElement("title");
        Element name17 = attribute17.addElement("name");
        title17.setText("网络分类(二级)");
        String netSortTwo = service.id2Name(mainObject.getMainNetSortTwo(), "ItawSystemDictTypeDao");
        name17.setText(netSortTwo);
        Element attribute18 = attributes.addElement("attribute");
        Element title18 = attribute18.addElement("title");
        Element name18 = attribute18.addElement("name");
        title18.setText("网络分类(三级)");
        String netSortThree = service.id2Name(mainObject.getMainNetSortThree(), "ItawSystemDictTypeDao");
        name18.setText(netSortThree);
        Element attribute19 = attributes.addElement("attribute");
        Element title19 = attribute19.addElement("title");
        Element name19 = attribute19.addElement("name");
        title19.setText("故障处理响应级别");
        String mainFaultResponseLevel = service.id2Name(mainObject.getMainFaultResponseLevel(), "ItawSystemDictTypeDao");
        name19.setText(mainFaultResponseLevel);
        Element attribute20 = attributes.addElement("attribute");
        Element title20 = attribute20.addElement("title");
        Element name20 = attribute20.addElement("name");
        title20.setText("工单受理时限");
        String sheetAcceptLimit = StaticMethod.date2String(mainObject.getSheetAcceptLimit());
        name20.setText(sheetAcceptLimit);
        Element attribute21 = attributes.addElement("attribute");
        Element title21 = attribute21.addElement("title");
        Element name21 = attribute21.addElement("name");
        title21.setText("工单受理时限");
        String sheetCompleteLimit = StaticMethod.date2String(mainObject.getSheetCompleteLimit());
        name21.setText(sheetCompleteLimit);
        Element attribute22 = attributes.addElement("attribute");
        Element title22 = attribute22.addElement("title");
        Element name22 = attribute22.addElement("name");
        title22.setText("T1处理时限");
        String completeLimitT1 = StaticMethod.date2String(mainObject.getMainCompleteLimitT1());
        name22.setText(completeLimitT1);
        Element attribute23 = attributes.addElement("attribute");
        Element title23 = attribute23.addElement("title");
        Element name23 = attribute23.addElement("name");
        title23.setText("T2处理时限");
        String completeLimitT2 = StaticMethod.date2String(mainObject.getMainCompleteLimitT2());
        name23.setText(completeLimitT2);
        Element attribute24 = attributes.addElement("attribute");
        Element title24 = attribute24.addElement("title");
        Element name24 = attribute24.addElement("name");
        title24.setText("T3处理时限");
        String completeLimitT3 = StaticMethod.date2String(mainObject.getMainCompleteLimitT3());
        name24.setText(completeLimitT3);
        Element attribute25 = attributes.addElement("attribute");
        Element title25 = attribute25.addElement("title");
        Element name25 = attribute25.addElement("name");
        title25.setText("故障设备厂商");
        String mainEquipmentFactory = service.id2Name(mainObject.getMainEquipmentFactory(), "ItawSystemDictTypeDao");
        name25.setText(mainEquipmentFactory);
        Element attribute26 = attributes.addElement("attribute");
        Element title26 = attribute26.addElement("title");
        Element name26 = attribute26.addElement("name");
        title26.setText("故障发现方式");
        String mainFaultDiscoverableMode = service.id2Name(mainObject.getMainFaultDiscoverableMode(), "ItawSystemDictTypeDao");
        name26.setText(mainFaultDiscoverableMode);
        Element attribute27 = attributes.addElement("attribute");
        Element title27 = attribute27.addElement("title");
        Element name27 = attribute27.addElement("name");
        title27.setText("派单方式");
        String sendMode = service.id2Name(mainObject.getMainSendMode(), "ItawSystemDictTypeDao");
        name27.setText(sendMode);
        Element attribute28 = attributes.addElement("attribute");
        Element title28 = attribute28.addElement("title");
        Element name28 = attribute28.addElement("name");
        title28.setText("网元名称");
        name28.setText(StaticMethod.null2String(mainObject.getMainNetName()));
        Element attribute29 = attributes.addElement("attribute");
        Element title29 = attribute29.addElement("title");
        Element name29 = attribute29.addElement("name");
        title29.setText("故障设备型号");
        name29.setText(StaticMethod.null2String(mainObject.getMainEquipmentModel()));
        Element attribute30 = attributes.addElement("attribute");
        Element title30 = attribute30.addElement("title");
        Element name30 = attribute30.addElement("name");
        title30.setText("故障省份");
        name30.setText(StaticMethod.null2String(mainObject.getMainFaultGenerantPriv()));
        Element attribute31 = attributes.addElement("attribute");
        Element title31 = attribute31.addElement("title");
        Element name31 = attribute31.addElement("name");
        title31.setText("故障地市");
        String faultGenerantCity = StaticMethod.null2String(service.id2Name(mainObject.getMainFaultGenerantCity(), "ItawSystemDictTypeDao"));
        name31.setText(faultGenerantCity);
        Element attribute32 = attributes.addElement("attribute");
        Element title32 = attribute32.addElement("title");
        Element name32 = attribute32.addElement("name");
        title32.setText("故障发生时间");
        String mainFaultGenerantTime = StaticMethod.date2String(mainObject.getMainFaultGenerantTime());
        name32.setText(mainFaultGenerantTime);
        Element attribute33 = attributes.addElement("attribute");
        Element title33 = attribute33.addElement("title");
        Element name33 = attribute33.addElement("name");
        title33.setText("是否影响业务");
        String mainIfAffectOperation = StaticMethod.null2String(service.id2Name(mainObject.getMainIfAffectOperation(), "ItawSystemDictTypeDao"));
        name33.setText(mainIfAffectOperation);
        String applySheetId = StaticMethod.null2String(mainObject.getMainApplySheetId());
        if (!applySheetId.equals("")) {
            Element attribute34 = attributes.addElement("attribute");
            Element title34 = attribute34.addElement("title");
            Element name34 = attribute34.addElement("name");
            title34.setText("相关投诉处理工单号");
            name34.setText(applySheetId);
        }
        Element attribute35 = attributes.addElement("attribute");
        Element title35 = attribute35.addElement("title");
        Element name35 = attribute35.addElement("name");
        title35.setText("附件");
        ITawCommonsAccessoriesManager mgrr = (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
        try {
            List list = mgrr.getAllFileById(mainObject.getSheetAccessories());
            String url = "";
            for (int i = 0; i < list.size(); i++) {
                TawCommonsAccessories tawCommonsAccessories = (TawCommonsAccessories) list.get(i);
                url = url + "<a href='http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/eoms35/accessories/tawCommonsAccessoriesConfigs.do?method=download&type=interface&userName=" + request.getParameter("userName") + "&id=" + tawCommonsAccessories.getId() + "'>" + tawCommonsAccessories.getAccessoriesCnName() + "</a><br>";
            }

            name35.setText(url);
        } catch (AccessoriesException e) {
            e.printStackTrace();
        }
        Element parameters = root.addElement("parameters");
        Element parameter1 = parameters.addElement("hidden");
        Element id1 = parameter1.addElement("id");
        Element value1 = parameter1.addElement("value");
        id1.setText("beanName");
        value1.setText(request.getParameter("beanName"));
        Element parameter2 = parameters.addElement("hidden");
        Element id2 = parameter2.addElement("id");
        Element value2 = parameter2.addElement("value");
        id2.setText("sheetKey");
        value2.setText(task.getSheetKey());
        Element parameter3 = parameters.addElement("hidden");
        Element id3 = parameter3.addElement("id");
        Element value3 = parameter3.addElement("value");
        id3.setText("taskId");
        value3.setText(task.getId());
        Element parameter4 = parameters.addElement("hidden");
        Element id4 = parameter4.addElement("id");
        Element value4 = parameter4.addElement("value");
        id4.setText("taskName");
        value4.setText(task.getTaskName());
        Element parameter5 = parameters.addElement("hidden");
        Element id5 = parameter5.addElement("id");
        Element value5 = parameter5.addElement("value");
        id5.setText("preLinkId");
        value5.setText(task.getPreLinkId());
        Element parameter6 = parameters.addElement("hidden");
        Element id6 = parameter6.addElement("id");
        Element value6 = parameter6.addElement("value");
        id6.setText("isAccept");
        value6.setText(isAccept);
        Element parameter7 = parameters.addElement("hidden");
        Element id7 = parameter7.addElement("id");
        Element value7 = parameter7.addElement("value");
        id7.setText("activeTemplateId");
        value7.setText(task.getTaskName());
        Element parameter8 = parameters.addElement("hidden");
        Element id8 = parameter8.addElement("id");
        Element value8 = parameter8.addElement("value");
        id8.setText("beanName");
        value8.setText(request.getParameter("beanName"));
        Element parameter9 = parameters.addElement("hidden");
        Element id9 = parameter9.addElement("id");
        Element value9 = parameter9.addElement("value");
        id9.setText("beanId");
        value9.setText("iCommonFaultMainManager");
        Element parameter10 = parameters.addElement("hidden");
        Element id10 = parameter10.addElement("id");
        Element value10 = parameter10.addElement("value");
        id10.setText("mainClassName");
        value10.setText("com.boco.eoms.sheet.commonfault.model.CommonFaultMain");
        Element parameter11 = parameters.addElement("hidden");
        Element id11 = parameter11.addElement("id");
        Element value11 = parameter11.addElement("value");
        id11.setText("linkClassName");
        value11.setText("com.boco.eoms.sheet.commonfault.model.CommonFaultLink");
        Element parameter12 = parameters.addElement("hidden");
        Element id12 = parameter12.addElement("id");
        Element value12 = parameter12.addElement("value");
        id12.setText("aiid");
        value12.setText(task.getId());
        Element parameter13 = parameters.addElement("hidden");
        Element id13 = parameter13.addElement("id");
        Element value13 = parameter13.addElement("value");
        id13.setText("piid");
        value13.setText(StaticMethod.null2String(task.getProcessId()));
        Element parameter14 = parameters.addElement("hidden");
        Element id14 = parameter14.addElement("id");
        Element value14 = parameter14.addElement("value");
        id14.setText("mainId");
        value14.setText(task.getSheetKey());
        Element parameter15 = parameters.addElement("hidden");
        Element id15 = parameter15.addElement("id");
        Element value15 = parameter15.addElement("value");
        id15.setText("TKID");
        value15.setText(task.getId());
        Element parameter01 = parameters.addElement("parameter");
        Element id01 = parameter01.addElement("id");
        Element value01 = parameter01.addElement("value");
        id01.setText("taskStatus");
        value01.setText(task.getTaskStatus());
        System.out.println("------------------------------------" + document.asXML());
        return document.asXML();
    }

    public String getSheetAttachCode() {
        return null;
    }

    public Map initMap(Map map, List attach, String type)
            throws Exception {
        return null;
    }

    public HashMap analyseFlowDefine(HttpServletRequest request, String sheetPageName)
            throws Exception {
        HashMap map = new HashMap();
        String mainSendMode = StaticMethod.nullObject2String(request.getParameter(sheetPageName + "mainSendMode"));
        String personSendMode = XmlManage.getFile("/config/commonfault-util.xml").getProperty("dict.personsheet");
        String taskName = StaticMethod.nullObject2String(request.getParameter(sheetPageName + "activeTemplateId"));
        String operateType = StaticMethod.nullObject2String(request.getParameter(sheetPageName + "operateType"));
        String centerMonitor = StaticMethod.nullObject2String(request.getParameter(sheetPageName + "centerMonitor"));
        String dealPerformer = StaticMethod.nullObject2String(request.getParameter(sheetPageName + "dealPerformer"));
        String mainId = StaticMethod.nullObject2String(request.getParameter(sheetPageName + "mainId"));

        ICommonFaultMainManager mainService = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        CommonFaultMain commonFaultMain = mainService.getCommonFaultMainById(mainId);
        System.out.println("lizhi:taskName=" + taskName + "operateType=" + operateType + "centerMonitor=" + centerMonitor + "dealPerformer=" + dealPerformer);
        if (taskName.equals("FirstExcuteHumTask") && operateType.equals("1") && centerMonitor.equals("true") && dealPerformer.equals("") && commonFaultMain != null && !"1".equals(commonFaultMain.getMainIsOther())) {
            StringBuffer sb = new StringBuffer();
            StringBuffer sbFn = new StringBuffer();

            INetOwnershipManager netOwnershipManager = (INetOwnershipManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipManager");
            INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");

            String mainNeId = StaticMethod.nullObject2String(commonFaultMain.getMainNeId());
            String mainNetName = StaticMethod.nullObject2String(commonFaultMain.getMainNetName());
            String toDeptId = StaticMethod.nullObject2String(commonFaultMain.getToDeptId());
            String subroleid = "";
            System.out.println("lizhi:mainNeId=" + mainNeId + "mainNetName=" + mainNetName);
            if (!mainNetName.equals("")) {
                NetOwnership netownership = netOwnershipManager.getNetOwnershipByNetName(mainNetName);
                NetOwnershipwireless NetOwnershipwireless = netOwnershipwirelessManager.getNetOwnershipByNetName(mainNetName);
                if (netownership != null)
                    subroleid = StaticMethod.nullObject2String(netownership.getTeamRoleId());
                if (commonFaultMain.getMainNetSortOne().equals("101010401") && NetOwnershipwireless != null)
                    subroleid = StaticMethod.nullObject2String(NetOwnershipwireless.getTeamRoleId());
                System.out.println("lizhi:subroleid=" + subroleid);
                if (!"".equals(subroleid)) {
                    Map userNameMap = SheetUtils.getUserNameForSubRole(subroleid);
                    String user = StaticMethod.nullObject2String(userNameMap.get("name"));
                    String subRoleName = StaticMethod.nullObject2String(userNameMap.get("subRoleName"));
                    System.out.println("lizhi:user=" + user + "subRoleName=" + subRoleName);
                    sb.append("根据规则匹配，该步骤的执行角色为:" + subRoleName + "\n");
                    sb.append("该角色对应用户为:" + user + "\n");
                    sbFn.append("function(){");
                    sbFn.append("try{var dealPerformers = document.getElementsByName('dealPerformer');var dealPerformerTypes = document.getElementsByName('dealPerformerType');var dealPerformerLeaders = document.getElementsByName('dealPerformerLeader');for(var i=0;i<dealPerformers.length;i++){dealPerformers[i].value='" + subroleid + "';dealPerformerTypes[i].value='subrole';dealPerformerLeaders[i].value='" + subroleid + "';" + "}" + "}catch(e){}");
                    sbFn.append("}");
                    JSONObject o = new JSONObject();
                    o.put("text", sb.toString());
                    o.put("fn", sbFn.toString());
                    map.put("jsonObject", o);
                    map.put("status", "1");
                } else {
                    sb.append("根据匹配规则，维护班组角色ID为空\n请更改您的选择\n");
                    JSONObject o = new JSONObject();
                    o.put("text", sb.toString());
                    o.put("fn", sbFn.toString());
                    map.put("jsonObject", o);
                    map.put("status", "2");
                }
            } else {
                subroleid = netOwnershipManager.getSubroleId(toDeptId);
                System.out.println("lizhi:subroleid=" + subroleid);
                if (subroleid != null && !"".equals(subroleid)) {
                    Map userNameMap = SheetUtils.getUserNameForSubRole(subroleid);
                    String user = StaticMethod.nullObject2String(userNameMap.get("name"));
                    String subRoleName = StaticMethod.nullObject2String(userNameMap.get("subRoleName"));
                    System.out.println("lizhi:user=" + user + "subRoleName=" + subRoleName);
                    sb.append("根据规则匹配，该步骤的执行角色为:" + subRoleName + "\n");
                    sb.append("该角色对应用户为:" + user + "\n");
                    sbFn.append("function(){");
                    sbFn.append("try{var dealPerformers = document.getElementsByName('dealPerformer');var dealPerformerTypes = document.getElementsByName('dealPerformerType');var dealPerformerLeaders = document.getElementsByName('dealPerformerLeader');for(var i=0;i<dealPerformers.length;i++){dealPerformers[i].value='" + subroleid + "';dealPerformerTypes[i].value='subrole';dealPerformerLeaders[i].value='" + subroleid + "';" + "}" + "}catch(e){}");
                    sbFn.append("}");
                    JSONObject o = new JSONObject();
                    o.put("text", sb.toString());
                    o.put("fn", sbFn.toString());
                    map.put("jsonObject", o);
                    map.put("status", "1");
                } else {
                    sb.append("根据匹配规则，综合维护班组ID为空\n请更改您的选择\n");
                    JSONObject o = new JSONObject();
                    o.put("text", sb.toString());
                    o.put("fn", sbFn.toString());
                    map.put("jsonObject", o);
                    map.put("status", "2");
                }
            }
        } else if (personSendMode != null && mainSendMode.equals(personSendMode)) {
            StringBuffer sb = new StringBuffer();
            StringBuffer sbFn = new StringBuffer();
            if (taskName.equals("") || taskName.equals("DraftHumTask")) {
                String mainFaultGenerantTime = StaticMethod.nullObject2String(request.getParameter(sheetPageName + "mainFaultGenerantTime"));
                Date localTime = StaticMethod.getLocalTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date faultGenerantTime = dateFormat.parse(mainFaultGenerantTime);
                if (localTime.before(faultGenerantTime)) {
                    sb.append("根据您的选择，工单派发时间早于故障开始时间\n请更改您的选择\n");
                    JSONObject o = new JSONObject();
                    o.put("text", sb.toString());
                    o.put("fn", sbFn.toString());
                    System.out.println("o.toString()=" + o.toString());
                    map.put("jsonObject", o);
                    map.put("status", "2");
                } else {
                    map = super.analyseFlowDefine(request, sheetPageName);
                }
            } else if ((taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") || taskName.equals("ThirdExcuteHumTask")) && operateType.equals("46")) {
                String linkFaultAvoidTime = StaticMethod.nullObject2String(request.getParameter(sheetPageName + "linkFaultAvoidTime"));
                Date localTime = StaticMethod.getLocalTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date faultAvoidTime = dateFormat.parse(linkFaultAvoidTime);
                if (localTime.before(faultAvoidTime)) {
                    sb.append("根据您的选择，工单处理完成回复时间早于故障消除时间\n请更改您的选择\n");
                    JSONObject o = new JSONObject();
                    o.put("text", sb.toString());
                    o.put("fn", sbFn.toString());
                    System.out.println("o.toString()=" + o.toString());
                    map.put("jsonObject", o);
                    map.put("status", "2");
                } else {
                    map = super.analyseFlowDefine(request, sheetPageName);
                }
            } else {
                map = super.analyseFlowDefine(request, sheetPageName);
            }
        } else {
            map = super.analyseFlowDefine(request, sheetPageName);
        }
        return map;
    }

    public void performPreCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        if (operateType.equals("100")) {
            boolean ifAllReject = true;
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            List taskList = getTaskService().getCurrentUndoTask(sheetKey);
            for (int i = 0; i < taskList.size(); i++) {
                ITask tmpTask = (ITask) taskList.get(i);
                if (tmpTask.getTaskName().equals("FirstExcuteHumTask"))
                    continue;
                ifAllReject = false;
                break;
            }

            if (ifAllReject) {
                List linkList = getLinkService().getLinksByMainId(sheetKey);
                for (int i = 0; i < linkList.size(); i++) {
                    BaseLink link = (BaseLink) linkList.get(i);
                    if (link.getActiveTemplateId() == null || !link.getActiveTemplateId().equals("ExamineHumTask") && !link.getActiveTemplateId().equals("ThirdExcuteHumTask"))
                        continue;
                    ifAllReject = false;
                    break;
                }

            }
            JSONArray data = new JSONArray();
            JSONObject o = new JSONObject();
            o.put("text", "该工单将结束，请确认是否结束工单！");
            data.put(o);
            JSONObject jsonRoot = new JSONObject();
            jsonRoot.put("data", data);
            if (ifAllReject)
                jsonRoot.put("status", "1");
            else
                jsonRoot.put("status", "0");
            JSONUtil.print(response, jsonRoot.toString());
        } else {
            super.performPreCommit(mapping, form, request, response);
        }
    }

    public void performQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map actionMap = request.getParameterMap();
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String aSql[] = {
                ""
        };
        int aTotal[] = new int[1];
        String queryType = StaticMethod.nullObject2String(request.getParameter("queryType"));
        ICommonFaultMainManager mainService = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        List result = mainService.getQueryResult(aSql, actionMap, pageIndex, new Integer(pageSize.intValue()), aTotal, queryType);
        Integer total = new Integer(aTotal[0]);
        if (queryType != null && queryType.equals("number"))
            request.setAttribute("recordTotal", total);
        request.setAttribute("taskList", result);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);
    }

    public void showListUndo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HashMap filterMap = new HashMap();
        filterMap.put("TEMPLATE_NAME", getMainService().getFlowTemplateName());
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());
        Integer pageSize = new Integer(30);
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
        String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
        String orderCondition = "";
        if (!order.equals(""))
            if (order.equals("1"))
                order = " asc";
            else
                order = " desc";
        if (!sort.equals(""))
            orderCondition = " " + sort + order;
        String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
        if (!exportType.equals(""))
            pageSize = new Integer(-1);
        String userId = sessionform.getUserid();
        String deptId = sessionform.getDeptid();
        BaseMain mainObject = (BaseMain) getMainService().getMainObject().getClass().newInstance();
        ITask taskObject = (ITask) getTaskService().getTaskModelObject().getClass().newInstance();
        Map condition = new HashMap();
        condition.put("mainObject", mainObject);
        condition.put("taskObject", taskObject);
        condition.put("orderCondition", orderCondition);
        String ifAgent = StaticMethod.null2String(request.getParameter("ifAgent"));

        String sheetId = StaticMethod.null2String(request.getParameter("main.sheetId"));//工单号
        String title = StaticMethod.null2String(request.getParameter("main.title"));//工单主题
        String completeTimeLimitStartDate = StaticMethod.null2String(request.getParameter("completeTimeLimitStartDate"));//时间1
        String completeTimeLimitEndDate = StaticMethod.null2String(request.getParameter("completeTimeLimitEndDate"));//时间2
        String showArea = StaticMethod.null2String(request.getParameter("main.toDeptId"));//地域
        String stepId = StaticMethod.null2String(request.getParameter("taskNameChoiceExpression"));//处理环节
        String mainNetSortOne = StaticMethod.null2String(request.getParameter("mainNetSortOneChoiceExpression"));//网络分类1
        String mainNetSortTwo = StaticMethod.null2String(request.getParameter("mainNetSortTwoChoiceExpression"));//网络分类2
        String mainNetSortThree = StaticMethod.null2String(request.getParameter("mainNetSortThreeChoiceExpression"));//网络分类3

        condition.put("sheetIdMain", sheetId);
        condition.put("title", title);
        condition.put("completeTimeLimitStartDate", completeTimeLimitStartDate);
        condition.put("completeTimeLimitEndDate", completeTimeLimitEndDate);
        condition.put("showArea", showArea);
        condition.put("stepId", stepId);
        condition.put("mainNetSortOne", mainNetSortOne);
        condition.put("mainNetSortTwo", mainNetSortTwo);
        condition.put("mainNetSortThree", mainNetSortThree);
        System.out.println("lyg=====sheetId=" + sheetId + "==title=" + title + "==completeTimeLimitStartDate=" + completeTimeLimitStartDate + "==completeTimeLimitEndDate=" + completeTimeLimitEndDate + "==showArea=" + showArea + "==stepId=" + stepId + "==mainNetSortOne=" + mainNetSortOne + "==mainNetSortTwo=" + mainNetSortTwo + "==mainNetSortThree=" + mainNetSortThree);


        condition.put("ifAgent", ifAgent);
        String flowName = getMainService().getFlowTemplateName();
        HashMap taskListOvertimeMap = getTaskService().getUndoTaskByOverTime(condition, userId, deptId, flowName, pageIndex, pageSize);
        int total = ((Integer) taskListOvertimeMap.get("taskTotal")).intValue();
        List taskOvertimeList = (List) taskListOvertimeMap.get("taskList");
        List taskMapList = new ArrayList();
        List taskList = new ArrayList();
        if (taskOvertimeList != null && taskOvertimeList.size() > 0) {
            IOvertimeTipManager service = (IOvertimeTipManager) ApplicationContextHolder.getInstance().getBean("iOvertimeTipManager");
            List timeList = service.getEffectOvertimeTip(getMainService().getFlowTemplateName(), userId);
            HashMap columnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
            System.out.println("==columnMap==" + columnMap);
            HashMap columnMapOverTip = OvertimeTipUtil.getNotOverTimeColumnByMapping(flowName);
            for (int i = 0; i < taskOvertimeList.size(); i++) {
                ITask tmptask = null;
                Map taskMap = new HashMap();
                Map tmptaskMap = new HashMap();
                HashMap conditionMap = new HashMap();
                if (columnMap.size() > 0) {
                    Object tmpObjArr[] = (Object[]) taskOvertimeList.get(i);
                    tmptask = (ITask) tmpObjArr[columnMap.size()];
                    Iterator it = columnMap.keySet().iterator();
                    for (int j = 0; it.hasNext(); j++) {
                        String elementKey = (String) it.next();
                        Object tempcolumn = tmpObjArr[j];
                        conditionMap.put(elementKey, tempcolumn);
                        tmptaskMap.put(columnMap.get(elementKey), tempcolumn);
                    }

                } else {
                    Object tmpObjArr[] = (Object[]) taskOvertimeList.get(i);
                    tmptask = (ITask) tmpObjArr[columnMap.size()];
                }
                if (exportType.equals("")) {
                    String overtimeFlag = OvertimeTipUtil.setOvertimeTipFlag(columnMapOverTip, tmptask.getCompleteTimeLimit(), conditionMap, timeList, flowName);
                    taskMap.put("overtimeType", overtimeFlag);
                    long overtime = OvertimeTipUtil.getOvertime(columnMapOverTip, tmptask.getCompleteTimeLimit(), conditionMap, timeList, flowName);
                    taskMap.put("overtime", new Long(overtime));
                }
                taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
                taskMap.putAll(tmptaskMap);
                taskList.add(tmptask);
                taskMapList.add(taskMap);
            }

        }
        request.setAttribute("taskList", taskMapList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("overTimeTaskCount", new Integer(0));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("findForward", "list");
        request.setAttribute("module", mapping.getPath().substring(1));
        String workflowName = getMainService().getFlowTemplateName();
        ArrayList phaseIdList = new ArrayList();
        Map phaseIdMap = new HashMap();
        FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName, getRoleConfigPath());
        FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
        if (flowDefine != null) {
            PhaseId phaseIds[] = flowDefine.getPhaseId();
            for (int i = 0; i < phaseIds.length; i++) {
                PhaseId phaseId = phaseIds[i];
                if (!phaseId.getId().equals("receive")) {
                    phaseIdMap.put(phaseId.getId(), phaseId.getName());
                    phaseIdList.add(phaseId.getId());
                }
            }

        }
        request.setAttribute("phaseIdMap", phaseIdMap);
        request.setAttribute("stepIdList", phaseIdList);
        String batch = StaticMethod.null2String(request.getParameter("batch"));
        if (!batch.equals("") && batch.equals("true")) {
            Map tempMap = new HashMap();
            String dictName = "dict-sheet-" + mapping.getPath().substring(1);
            List dictItems = DictMgrLocator.getDictService().getDictItems(Util.constituteDictId(dictName, "activeTemplateId"));
            for (Iterator it = dictItems.iterator(); it.hasNext(); ) {
                DictItemXML dictItemXml = (DictItemXML) it.next();
                String description = dictItemXml.getDescription();
                if (description.equals("batch:true"))
                    tempMap.put(dictItemXml.getItemId(), dictItemXml.getItemName());
            }

            Map batchTaskMap = new HashMap();
            if (tempMap.size() > 0) {
                for (Iterator it = tempMap.keySet().iterator(); it.hasNext(); ) {
                    String taskName = (String) it.next();
                    for (Iterator tasks = taskList.iterator(); tasks.hasNext(); ) {
                        ITask task = (ITask) tasks.next();
                        if (taskName.equals(task.getTaskName()) && (task.getSubTaskFlag() == null || task.getSubTaskFlag().equals("false") || task.getSubTaskFlag().equals(""))) {
                            batchTaskMap.put(task.getTaskName(), task.getTaskDisplayName());
                            break;
                        }
                    }

                }

            }
            request.setAttribute("batchTaskMapKey", batchTaskMap.keySet());
            request.setAttribute("batchTaskMap", batchTaskMap);
        }
    }

//	/**
//	 * 待办工单查询
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	public void performListQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//	throws Exception
//{
//	HashMap filterMap = new HashMap();
//	filterMap.put("TEMPLATE_NAME", getMainService().getFlowTemplateName());
//	HashMap sessionMap = new HashMap();
//	TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
//	sessionMap.put("userId", sessionform.getUserid());
//	sessionMap.put("password", sessionform.getPassword());
//	Integer pageSize = new Integer(30);
//	String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
//	Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
//	String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
//	String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
//	String orderCondition = "";
//	if (!order.equals(""))
//		if (order.equals("1"))
//			order = " asc";
//		else
//			order = " desc";
//	if (!sort.equals(""))
//		orderCondition = " " + sort + order;
//	String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
//	if (!exportType.equals(""))
//		pageSize = new Integer(-1);
//	String userId = sessionform.getUserid();
//	String deptId = sessionform.getDeptid();
//	BaseMain mainObject = (BaseMain)getMainService().getMainObject().getClass().newInstance();
//	ITask taskObject = (ITask)getTaskService().getTaskModelObject().getClass().newInstance();
//	Map condition = new HashMap();
//	condition.put("mainObject", mainObject);
//	condition.put("taskObject", taskObject);
//	condition.put("orderCondition", orderCondition);
//	String ifAgent = StaticMethod.null2String(request.getParameter("ifAgent"));
//
//	String sheetId = StaticMethod.null2String(request.getParameter("main.sheetId"));//工单号
//	String title = StaticMethod.null2String(request.getParameter("main.title"));//工单主题
//	String completeTimeLimitStartDate = StaticMethod.null2String(request.getParameter("completeTimeLimitStartDate"));//时间1
//	String completeTimeLimitEndDate = StaticMethod.null2String(request.getParameter("completeTimeLimitEndDate"));//时间2
//	String showArea = StaticMethod.null2String(request.getParameter("main.toDeptId"));//地域
//	String stepId = StaticMethod.null2String(request.getParameter("taskNameChoiceExpression"));//处理环节
//	String mainNetSortOne = StaticMethod.null2String(request.getParameter("mainNetSortOneChoiceExpression"));//网络分类1
//	String mainNetSortTwo = StaticMethod.null2String(request.getParameter("mainNetSortTwoChoiceExpression"));//网络分类2
//	String mainNetSortThree = StaticMethod.null2String(request.getParameter("mainNetSortThreeChoiceExpression"));//网络分类3
//
//	condition.put("sheetId", sheetId);
//	condition.put("title", title);
//	condition.put("completeTimeLimitStartDate", completeTimeLimitStartDate);
//	condition.put("completeTimeLimitEndDate", completeTimeLimitEndDate);
//	condition.put("showArea", showArea);
//	condition.put("stepId", stepId);
//	condition.put("mainNetSortOne", mainNetSortOne);
//	condition.put("mainNetSortTwo", mainNetSortTwo);
//	condition.put("mainNetSortThree", mainNetSortThree);
//	System.out.println("lyg=====sheetId="+sheetId+"==title="+title+"==completeTimeLimitStartDate="+completeTimeLimitStartDate+"==completeTimeLimitEndDate="+completeTimeLimitEndDate+"==showArea="+showArea+"==stepId="+stepId+"==mainNetSortOne="+mainNetSortOne+"==mainNetSortTwo="+mainNetSortTwo+"==mainNetSortThree="+mainNetSortThree);
//
//
//	condition.put("ifAgent", ifAgent);
//	String flowName = getMainService().getFlowTemplateName();
//	HashMap taskListOvertimeMap = getTaskService().getUndoTaskByOverTime(condition, userId, deptId, flowName, pageIndex, pageSize);
//	int total = ((Integer)taskListOvertimeMap.get("taskTotal")).intValue();
//	List taskOvertimeList = (List)taskListOvertimeMap.get("taskList");
//	List taskMapList = new ArrayList();
//	List taskList = new ArrayList();
//	if (taskOvertimeList != null && taskOvertimeList.size() > 0)
//	{
//		IOvertimeTipManager service = (IOvertimeTipManager)ApplicationContextHolder.getInstance().getBean("iOvertimeTipManager");
//		List timeList = service.getEffectOvertimeTip(getMainService().getFlowTemplateName(), userId);
//		HashMap columnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
//		System.out.println("==columnMap==" + columnMap);
//		HashMap columnMapOverTip = OvertimeTipUtil.getNotOverTimeColumnByMapping(flowName);
//		for (int i = 0; i < taskOvertimeList.size(); i++)
//		{
//			ITask tmptask = null;
//			Map taskMap = new HashMap();
//			Map tmptaskMap = new HashMap();
//			HashMap conditionMap = new HashMap();
//			if (columnMap.size() > 0)
//			{
//				Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
//				tmptask = (ITask)tmpObjArr[columnMap.size()];
//				Iterator it = columnMap.keySet().iterator();
//				for (int j = 0; it.hasNext(); j++)
//				{
//					String elementKey = (String)it.next();
//					Object tempcolumn = tmpObjArr[j];
//					conditionMap.put(elementKey, tempcolumn);
//					tmptaskMap.put(columnMap.get(elementKey), tempcolumn);
//				}
//
//			} else
//			{
//				Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
//				tmptask = (ITask)tmpObjArr[columnMap.size()];
//			}
//			if (exportType.equals(""))
//			{
//				String overtimeFlag = OvertimeTipUtil.setOvertimeTipFlag(columnMapOverTip, tmptask.getCompleteTimeLimit(), conditionMap, timeList, flowName);
//				taskMap.put("overtimeType", overtimeFlag);
//				long overtime = OvertimeTipUtil.getOvertime(columnMapOverTip, tmptask.getCompleteTimeLimit(), conditionMap, timeList, flowName);
//				taskMap.put("overtime", new Long(overtime));
//			}
//			taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
//			taskMap.putAll(tmptaskMap);
//			taskList.add(tmptask);
//			taskMapList.add(taskMap);
//		}
//
//	}
//	request.setAttribute("taskList", taskMapList);
//	request.setAttribute("total", new Integer(total));
//	request.setAttribute("overTimeTaskCount", new Integer(0));
//	request.setAttribute("pageSize", pageSize);
//	request.setAttribute("findForward", "list");
//	request.setAttribute("module", mapping.getPath().substring(1));
//	String workflowName = getMainService().getFlowTemplateName();
//	ArrayList phaseIdList = new ArrayList();
//	Map phaseIdMap = new HashMap();
//	FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName, getRoleConfigPath());
//	FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
//	if (flowDefine != null)
//	{
//		PhaseId phaseIds[] = flowDefine.getPhaseId();
//		for (int i = 0; i < phaseIds.length; i++)
//		{
//			PhaseId phaseId = phaseIds[i];
//			if (!phaseId.getId().equals("receive"))
//			{
//				phaseIdMap.put(phaseId.getId(), phaseId.getName());
//				phaseIdList.add(phaseId.getId());
//			}
//		}
//
//	}
//	request.setAttribute("phaseIdMap", phaseIdMap);
//	request.setAttribute("stepIdList", phaseIdList);
//	String batch = StaticMethod.null2String(request.getParameter("batch"));
//	if (!batch.equals("") && batch.equals("true"))
//	{
//		Map tempMap = new HashMap();
//		String dictName = "dict-sheet-" + mapping.getPath().substring(1);
//		List dictItems = DictMgrLocator.getDictService().getDictItems(Util.constituteDictId(dictName, "activeTemplateId"));
//		for (Iterator it = dictItems.iterator(); it.hasNext();)
//		{
//			DictItemXML dictItemXml = (DictItemXML)it.next();
//			String description = dictItemXml.getDescription();
//			if (description.equals("batch:true"))
//				tempMap.put(dictItemXml.getItemId(), dictItemXml.getItemName());
//		}
//
//		Map batchTaskMap = new HashMap();
//		if (tempMap.size() > 0)
//		{
//			for (Iterator it = tempMap.keySet().iterator(); it.hasNext();)
//			{
//				String taskName = (String)it.next();
//				for (Iterator tasks = taskList.iterator(); tasks.hasNext();)
//				{
//					ITask task = (ITask)tasks.next();
//					if (taskName.equals(task.getTaskName()) && (task.getSubTaskFlag() == null || task.getSubTaskFlag().equals("false") || task.getSubTaskFlag().equals("")))
//					{
//						batchTaskMap.put(task.getTaskName(), task.getTaskDisplayName());
//						break;
//					}
//				}
//
//			}
//
//		}
//		request.setAttribute("batchTaskMapKey", batchTaskMap.keySet());
//		request.setAttribute("batchTaskMap", batchTaskMap);
//	}
//}

    public void newPerformNonFlow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BocoLog.info(this, "===优化======非流程动作===");
        HashMap columnMap = getInterfaceObjMap(mapping, form, request, response);
        Map map = newSetDealRequestMap(mapping, form, request, response);
        Object taskIdObject = map.get("TKID");
        if (taskIdObject != null && taskIdObject.getClass().isArray())
            taskIdObject = ((Object[]) taskIdObject)[0];
        String taskId = StaticMethod.nullObject2String(taskIdObject);
        Map serializableMap = SheetUtils.serializableParemeterMap(map);
        Iterator it = serializableMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        HashMap tempWpsMap;
        for (; it.hasNext(); WpsMap.putAll(tempWpsMap)) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            if (taskId.equals("")) {
                Object obj = tempMap.get("aiid");
                if (obj.getClass().isArray()) {
                    Object obja[] = (Object[]) obj;
                    obj = obja[0];
                }
                taskId = StaticMethod.nullObject2String(obj);
            }
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
        }

        setFlowEngineMap(WpsMap);
        dealFlowEngineMap(mapping, form, request, response);
        try {
            HashMap tmpMap = getFlowEngineMap();
            newSaveNonFlowData(taskId, tmpMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMainDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showMainDetailPage(mapping, form, request, response);
        String qctype = StaticMethod.null2String(request.getParameter("qctype"));
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean(beanName);
        ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();
        CommonFaultMain commonFaultMain = (CommonFaultMain) commonFaultMainManager.loadSinglePO(sheetKey);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String alarmId = StaticMethod.null2String(commonFaultMain.getMainAlarmId());
        ApplicationContextHolder holder = ApplicationContextHolder.getInstance();
        ITawSystemUserManager userManager = (ITawSystemUserManager) holder.getBean("ItawSystemUserSaveManagerFlush");
        TawSystemUser user = userManager.getUserByuserid(sessionform.getUserid());
        String email = StaticMethod.null2String(user.getEmail());
        String province = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.province");
        System.out.println("@@@@@@@@@@province" + province);
        String systemName = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.systemName");
        System.out.println("@@@@@@@@@@systemName" + systemName);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = sdf.format(date);
        System.out.println("@@@@@@@@@@timeStamp" + timeStamp);
        String token = province + ";" + systemName + ";" + alarmId + ";" + email + ";" + timeStamp;
        System.out.println(token + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String key = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.key");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        byte raw[] = key.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        cipher.init(1, skeySpec);
        byte encrypted[] = cipher.doFinal(token.getBytes("utf-8"));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encrypted.length; i++) {
            String hex = Integer.toHexString(encrypted[i] & 0xff);
            if (hex.length() == 1)
                hex = '0' + hex;
            sb.append(hex.toUpperCase());
        }

        String ret = (new BASE64Encoder()).encode(sb.toString().getBytes());
        token = ret.toString();
        request.setAttribute("token", token);
        request.setAttribute("qctype", qctype);
    }

    /**
     * LINK的提交
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void performDeal(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /**
         * 由于改为调用wps提供的humantafskManagerAPI，故taskid的取值由原来的aiid改为tkid，modify by
         * qinmin*
         */
        String taskId = StaticMethod.nullObject2String(request
                .getParameter("TKID"));

        String undoFlag = StaticMethod.nullObject2String(request
                .getParameter("undoFlag"));
        request.setAttribute("undoFlag", undoFlag);
        System.out.println("performDeal=undoFlag=" + undoFlag);

        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        System.out.println("basesheet operateType is -----------------------" + operateType);
        // 获取页面输入信息
        HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
                response);
        Map map = request.getParameterMap();
        Map serializableMap = SheetUtils.serializableParemeterMap(map);
        Iterator it = serializableMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        while (it.hasNext()) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            if (taskId.equals("")) {
                Object obj = tempMap.get("aiid");
                if (obj.getClass().isArray()) {
                    Object[] obja = (Object[]) obj;
                    obj = obja[0];
                }
                taskId = StaticMethod.nullObject2String(obj);
            }
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
                    tempColumnMap);
            WpsMap.putAll(tempWpsMap);
        }
        setFlowEngineMap(WpsMap);
        dealFlowEngineMap(mapping, form, request, response);

        /** 获取登陆信息，add by qinmin* */
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
//		sessionMap.put("userId", sessionform.getUserid());
//		sessionMap.put("password", sessionform.getPassword());
//		/** add by qinmin* */
//
//		// 是否调用外部流程，若调用的话，将调用外部流程的个数放置到operate中
//		ITawSheetRelationManager mgr = (ITawSheetRelationManager) ApplicationContextHolder
//				.getInstance().getBean("ITawSheetRelationManager");
//		String mainId = StaticMethod.nullObject2String(request
//				.getParameter("mainId"));
//		String taskName = StaticMethod.nullObject2String(request
//				.getParameter("taskName"));
        //List list = mgr.getRunningSheetByParentId(mainId);
        String userId = "";
        String password = "";
        if (sessionform != null) {
            userId = StaticMethod.nullObject2String(sessionform.getUserid());
            password = StaticMethod.nullObject2String(sessionform.getPassword());
        }
        sessionMap.put("userId", userId);
        sessionMap.put("password", password);
        ITawSheetRelationManager mgr = (ITawSheetRelationManager) ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
        String mainId = StaticMethod.nullObject2String(request.getParameter("mainId"));
        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        String tempUserId = "";
        if (sessionform != null) {
            tempUserId = sessionform.getUserid();
        }
        //查询工单互调表

        List relationAllList = mgr.getRunningSheetByPidAndPhaseIdAndUserId(mainId, taskName, tempUserId);
        HashMap operate = (HashMap) this.getFlowEngineMap().get("operate");

        if (relationAllList != null && relationAllList.size() > 0) {
            operate.put("reInvokeCount", new Integer(relationAllList.size()));
            System.out.println("=======hasNextTaskFlag=======is==invokeProcess");
            operate.put("hasNextTaskFlag", "invokeProcess");
        }

        this.getFlowEngineMap().put("operate", operate);

        // 是否需要回调外部流程
        String ifReplyInvoke = StaticMethod.nullObject2String(request
                .getParameter("ifReplyInvoke"));
        if (ifReplyInvoke.equals("true")) {
            String invokePiid = StaticMethod.nullObject2String(request
                    .getParameter("invokePiid"));
            String invokeOperateName = StaticMethod.nullObject2String(request
                    .getParameter("invokeOperateName"));
            String invokeProcessBeanId = StaticMethod.nullObject2String(request
                    .getParameter("invokeProcessBeanId"));
            String parentSheetKey = StaticMethod.nullObject2String(request
                    .getParameter("invokeSheetId"));
            String toPhaseId = StaticMethod.nullObject2String(request
                    .getParameter("invokePhaseId"));
            IBaseSheet parentSheet = (IBaseSheet) ApplicationContextHolder
                    .getInstance().getBean(invokeProcessBeanId);

            if (parentSheetKey != null) {

                HashMap sheetMap = new HashMap();
                HashMap mainMap = new HashMap();
                HashMap linkMap = new HashMap();
                HashMap operateMap = new HashMap();

                BaseMain parentMain = parentSheet.getMainService().getSingleMainPO(parentSheetKey);
                BaseLink parentLink = (BaseLink) parentSheet.getLinkService()
                        .getLinkObject().getClass().newInstance();
                mainMap = SheetBeanUtils.bean2MapWithNull(parentMain);
                linkMap = SheetBeanUtils.bean2MapWithNull(parentLink);
                operateMap.put("phaseId", toPhaseId);
                operateMap.put("hasNextTaskFlag", "invokeProcess");

                sheetMap.put("main", mainMap);
                sheetMap.put("link", linkMap);
                sheetMap.put("operate", operateMap);

                parentSheet.getBusinessFlowService().reInvokeProcess(invokePiid,
                        invokeOperateName, sheetMap, sessionMap);
            }
        }
        /**判断是否是同组处理模式，若是的话，则先取消之前的claim操作。add by 秦敏**/
        String teamFlag = StaticMethod.nullObject2String(request.getParameter("teamFlag"));
        if (teamFlag.equals("true")) {
            businessFlowService.cancelClaimTask(taskId, getFlowEngineMap(),
                    sessionMap);
        }

        try {
            // 非流程动作处理
            String copyPerformer = StaticMethod.nullObject2String(operate.get("copyPerformer"));
            if (!copyPerformer.equals("")
                    || Integer.parseInt(operateType) == Constants.ACTION_MAKECOPYFOR
                    || Integer.parseInt(operateType) == Constants.ACTION_PHASE_BACKTOUP
                    || Integer.parseInt(operateType) == Constants.ACTION_DRIVERFORWARD) {
                newSaveNonFlowData("", getFlowEngineMap());
                HashMap main = (HashMap) this.getFlowEngineMap().get("main");
                String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
                String title = StaticMethod.nullObject2String(main.get("title"));
                workSM_NON_T(sheetId, copyPerformer, title);
                operate.put("copyPerformer", "");
                operate.put("copyPerformerLeader", "");
                operate.put("copyPerformerType", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // finish task
        businessFlowService.completeHumanTask(taskId, getFlowEngineMap(),
                sessionMap);
    }


    private static final String CONFIG_FILEPATH = "classpath:config/worksheet-sms-service-info.xml";//短信服务信息配置文件

    public void workSM_NON_T(String sheetId, String receiverId, String title) throws Exception {
        try {
            MsgServiceImpl msgService = new MsgServiceImpl();

            //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
            String nodeInstantName = "worksheet.CommonFaultMainFlowProcess.smsServiceId.instant";
            System.out.println("====" + nodeInstantName);
            String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            String receivers = "";
            //拼接短信接受者
            String[] receiverIds = receiverId.split(",");
            for (int i = 0; i < receiverIds.length; i++) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_ROLE + "," + receiverIds[i] + "#";
            }
            System.out.println("receivers=" + receivers);

            //派发通知（即时提醒）


            //拼写发送信息
            String sendContent = "提醒您收取故障工单:" + sheetId + ",主题名:" + title + ",请查阅!";

            //获取当前系统时间
            java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("GMT+08:00"));
            Calendar c = Calendar.getInstance(java.util.TimeZone.getDefault());
            Date localTime = c.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String messageSendTime = formatter.format(localTime);
            System.out.println("lizhi:instantServiceId" + instantServiceId + "sendContent" + sendContent + "sheetKey" + sheetId + "receivers" + receivers + "messageSendTime" + messageSendTime);
            msgService.sendMsg(instantServiceId, sendContent,
                    sheetId, receivers, messageSendTime);


        } catch (Exception e) {
            throw new Exception("send message exception,error info is" + e.getMessage());
        }

    }

    public void t1SendT2(Map mainrule, Map linkrule, String userId, String subroleid) throws Exception {
        try {
            ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
            String condition = " sheetKey = '" + mainrule.get("id") + "' and taskstatus in ('2','8')  and taskName ='SecondExcuteHumTask'";
            List taskList = taskservice.getTasksByCondition(condition);
            String operater = "";
            if ((taskList != null) && (taskList.size() > 0)) {
                CommonFaultTask task = (CommonFaultTask) taskList.get(0);
                operater = task.getOperateRoleId();
            }
            System.out.println("operater===" + operater);


//		    	1.添加link表驳回信息（T2驳回到T1）
            String deptId = "";

            ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            TawSystemUser user = userMgr.getUserByuserid(userId);

            if (user != null) {
                deptId = user.getDeptid();
            }
            String remark = StaticMethod.nullObject2String(linkrule.get("remark"));
            String linkCiytSubrole = StaticMethod.nullObject2String(linkrule.get("linkCiytSubrole"));
            System.out.println("linkCiytSubrole===" + linkCiytSubrole);
            Calendar calendar = Calendar.getInstance();
            calendar.add(13, 5);
            ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
            CommonFaultLink Relink = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
            String linkId = UUIDHexGenerator.getInstance().getID();
            Relink.setId(linkId);
            Relink.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
            Relink.setOperateType(new Integer(4));
            Relink.setOperateTime(calendar.getTime());
            Relink.setOperateDay(calendar.get(5));
            Relink.setOperateMonth(calendar.get(2) + 1);
            Relink.setOperateYear(calendar.get(1));
            Relink.setAcceptFlag(new Integer(0));
            Relink.setPreLinkId(StaticMethod.nullObject2String(linkrule.get("id")));
            Relink.setActiveTemplateId("SecondExcuteHumTask");
            Relink.setToOrgType(new Integer(0));
            Relink.setCompleteFlag(new Integer(0));
            Relink.setOperateUserId(StaticMethod.nullObject2String(userId));
            Relink.setOperateRoleId(StaticMethod.nullObject2String(operater));
            Relink.setOperateDeptId(deptId);
            String correlationKey = UUIDHexGenerator.getInstance().getID();
            Relink.setCorrelationKey(correlationKey);
            Relink.setTemplateFlag(0);
            Relink.setOperaterContact(StaticMethod.nullObject2String(user.getMobile()));
            Relink.setPiid(StaticMethod.nullObject2String(mainrule.get("piid")));
            Relink.setToOrgRoleId("8aa0813b1c6f2386011c6f39c8350027");
            Relink.setRemark(remark);
            Relink.setLinkCiytSubrole(linkCiytSubrole);
            linkservice.addLink(Relink);
            //2.T1确认受理
            calendar.add(13, 5);
            CommonFaultLink T1link61 = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
            T1link61.setId(UUIDHexGenerator.getInstance().getID());
            T1link61.setOperateType(new Integer("61"));
            T1link61.setActiveTemplateId("FirstExcuteHumTask");
            T1link61.setOperateTime(calendar.getTime());
            T1link61.setOperateDay(calendar.get(5));
            T1link61.setOperateMonth(calendar.get(2) + 1);
            T1link61.setOperateYear(calendar.get(1));
            T1link61.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
            T1link61.setToOrgRoleId(subroleid);//综合班组
            T1link61.setPreLinkId(linkId);
            T1link61.setNodeAccessories("");
            T1link61.setToOrgType(new Integer(0));
            T1link61.setCompleteFlag(new Integer(0));
            T1link61.setOperateUserId("fangmin");
            T1link61.setOperateRoleId("8aa0813b1c6f2386011c6f39c8350027");
            T1link61.setOperateDeptId("12201");
            T1link61.setTemplateFlag(0);
            linkservice.addLink(T1link61);

            //3.T1增加task记录
            CommonFaultTask T1Task = (CommonFaultTask) taskservice.getTaskModelObject().getClass().newInstance();
            try {
                T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            T1Task.setTaskName("FirstExcuteHumTask");
            T1Task.setTaskDisplayName("T1处理");
            T1Task.setFlowName("CommonFaultMainFlowProcess");
            T1Task.setSendTime((Date) mainrule.get("sendTime"));
            T1Task.setSheetKey(StaticMethod.nullObject2String(mainrule.get("id")));
            T1Task.setTaskStatus("5");
            T1Task.setSheetId(StaticMethod.nullObject2String(mainrule.get("sheetId")));
            T1Task.setTitle(StaticMethod.nullObject2String(mainrule.get("title")));
            T1Task.setOperateType("subrole");
            T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
            T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
            T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
            T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
            T1Task.setOperateRoleId(StaticMethod.nullObject2String("8aa0813b1c6f2386011c6f39c8350027"));
            T1Task.setTaskOwner(StaticMethod.nullObject2String("fangmin"));
            T1Task.setIfWaitForSubTask("false");
            T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
            T1Task.setPreLinkId(StaticMethod.nullObject2String(linkId));
            taskservice.addTask(T1Task);

        } catch (Exception e) {
            throw new Exception("t1SendT2 exception,error info is" + e.getMessage());
        }
    }

    /***
     * 自动归档规则
     * @param mainMap
     * @param linkMap
     * @return
     * 处理措施 linkDealStep  selectstep
     *  故障消除时间  linkFaultAvoidTime
     * 故障原因 faultReason  字典值  faultdealdesc
     *  处理人  operateUserId  faultTreatment
     * 联系方式  operaterContact
     *  故障处理响应级别 mainFaultResponseLevel  字典值 101030401 一级处理
     *  故障发生时间 mainFaultGenerantTime
     */
    public Map autoArchive(Map ruleCondition) {
        Map ruleMap = new HashMap();
        String sheetId = StaticMethod.nullObject2String(ruleCondition.get("sheetId"));
        String mainFaultResponseLevel = StaticMethod.nullObject2String(ruleCondition.get("mainFaultResponseLevel"));
        Date mainFaultGenerantTime = (Date) ruleCondition.get("mainFaultGenerantTime");

        String linkDealStep = StaticMethod.nullObject2String(ruleCondition.get("linkDealStep"));
        Date linkFaultAvoidTime = (Date) ruleCondition.get("linkFaultAvoidTime");
        String faultReason = StaticMethod.nullObject2String(ruleCondition.get("faultReason"));
        String operateUserId = StaticMethod.nullObject2String(ruleCondition.get("operateUserId"));
        String operaterContact = StaticMethod.nullObject2String(ruleCondition.get("operaterContact"));
        System.out.println("autoArchive=sheetId=" + sheetId);
        System.out.println("autoArchive=mainFaultResponseLevel=" + mainFaultResponseLevel);
        System.out.println("autoArchive=mainFaultGenerantTime=" + mainFaultGenerantTime);
        System.out.println("autoArchive=linkDealStep=" + linkDealStep);
        System.out.println("autoArchive=linkFaultAvoidTime=" + linkFaultAvoidTime);
        System.out.println("autoArchive=faultReason=" + faultReason);
        System.out.println("autoArchive=operateUserId=" + operateUserId);
        System.out.println("autoArchive=operaterContact=" + operaterContact);
        String ruleFlag = "1";//是否自动归档 1：不自动归档  0：自动归档
        //判断六要素
        //处理人至少大于2个汉字；电话号码应介于7-11个阿拉伯数字；故障消除时间必须大于告警发生时间；故障原因和处理措施至少大于2个字符
        String operateUserName = operateUserId;
        String sixFlag = "1";//是否满足六要素 1：不满足  0：满足
        if (!"".equals(operateUserName) && !"".equals(operaterContact) && linkFaultAvoidTime != null && mainFaultGenerantTime != null && !"".equals(linkDealStep)) {
            System.out.println("operateUserName.length()=" + operateUserName.length());
            System.out.println("operaterContact.matches=" + operaterContact.matches("[0-9]+"));
            System.out.println("linkFaultAvoidTime.after(mainFaultGenerantTime)=" + linkFaultAvoidTime.after(mainFaultGenerantTime));
            System.out.println("linkDealStep.length()=" + linkDealStep.length());
            if (operateUserName.length() >= 2 &&
                    operaterContact.length() >= 7 && operaterContact.length() <= 11 && operaterContact.matches("[0-9]+") &&
                    linkFaultAvoidTime.after(mainFaultGenerantTime) &&
                    linkDealStep.length() >= 2 && !"".equals(faultReason)) {
                sixFlag = "0";
                System.out.println("input rule sixFlag=" + sixFlag);
            }
        }


        //得到工单流水号的最后两位
        String sheetIdTwo = sheetId.substring(sheetId.length() - 2);
        String sheetIdTwoBD = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("autoArchive.sheetIdTwo"));
        System.out.println("sheetIdTwo=" + sheetIdTwo + "====sheetIdTwoBD=" + sheetIdTwoBD);
        System.out.println("sheetIdTwoBD.indexOf(sheetIdTwo)=" + sheetIdTwoBD.indexOf(sheetIdTwo));
        if (("".equals(sheetIdTwoBD) || sheetIdTwoBD.indexOf(sheetIdTwo) == -1) && "0".equals(sixFlag) && !"101030401".equals(mainFaultResponseLevel)) {//判断尾号 否
            ruleFlag = "0";
        }

        //由于不满足六要素而导致工单无法自动归档
        String noSixFlag = "1";
        if (("".equals(sheetIdTwoBD) || sheetIdTwoBD.indexOf(sheetIdTwo) == -1) && "1".equals(sixFlag) && !"101030401".equals(mainFaultResponseLevel)) {//判断尾号 否
            noSixFlag = "0";
        }

        ruleMap.put("ruleFlag", ruleFlag);
        ruleMap.put("noSixFlag", noSixFlag);
        System.out.println("ruleFlag=" + ruleFlag + "====noSixFlag=" + noSixFlag);

        return ruleMap;
    }

    /**
     * 判断班组是不是属于综合维护班
     *
     * @param subRoleId
     * @return
     */
    public String ifCitySubrole(String subRoleId) {
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String citySubroleSql = "SELECT * FROM cityid_to_subrole WHERE subroleid = '" + subRoleId + "'";
        List citySubroleList = new ArrayList();
        String flag = "1";//表示该subRoleId 不属于综合维护班
        try {
            citySubroleList = (List) services.getSheetAccessoriesList(citySubroleSql);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (citySubroleList != null && citySubroleList.size() > 0) {
            flag = "0";
        }
        return flag;
    }

    /**
     * 判断班组是否属于 无线网元对应的移动班组、铁塔班组，如果是则还回 对应的班组，不是还回1
     *
     * @param subRoleId
     * @return
     */
    public Map ifOtherSubrole(String subRoleId, String mainNetName) {
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String cxSql = "SELECT teamroleid,ttroleid FROM commonfault_net_team_wx WHERE netname = '" + mainNetName + "'";
        List cxList = new ArrayList();
        try {
            cxList = (List) services.getSheetAccessoriesList(cxSql);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String subroleid = "";
        String flag = "0";
        if (cxList != null && cxList.size() > 0 && !"".equals(subRoleId)) {
            String teamroleid = StaticMethod.nullObject2String(((Map) cxList.get(0)).get("teamroleid"));
            String ttroleid = StaticMethod.nullObject2String(((Map) cxList.get(0)).get("ttroleid"));
            if (subRoleId.equals(teamroleid)) {
                subroleid = ttroleid;
            } else if (subRoleId.equals(ttroleid)) {
                subroleid = teamroleid;
            } else {
                flag = "1";
            }
        } else {
            flag = "1";
        }
        Map otherMap = new HashMap();
        otherMap.put("flag", flag);
        otherMap.put("subroleid", subroleid);
        return otherMap;
    }

    public Map getRule(Map map) throws Exception {
        String nextSubroleId = "";//表示T1重新派到T2的班组
        String sheetId = StaticMethod.nullObject2String(map.get("sheetId"));
        String mainCitySubrole = StaticMethod.nullObject2String(map.get("mainCitySubrole"));//判断移动和铁塔班组驳回
        String mainNetName = StaticMethod.nullObject2String(map.get("mainNetName"));//网元名称
        String mainFaultGenerantCity = StaticMethod.nullObject2String(map.get("mainFaultGenerantCity"));//故障地市
        String operateRoleId = StaticMethod.nullObject2String(map.get("operateRoleId"));//T2处理的班组
        String linkCiytSubrole = StaticMethod.nullObject2String(map.get("linkCiytSubrole"));//页面上选择的班组，或者掌上运维传的班组
        Map otherMap = ifOtherSubrole(operateRoleId, mainNetName);
        String otherFlag = StaticMethod.nullObject2String(otherMap.get("flag"));
        String othersubroleid = StaticMethod.nullObject2String(otherMap.get("subroleid"));

        INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        String subroleid = netOwnershipwirelessManager.getSubroleId(mainFaultGenerantCity);

        Map ruleMap = new HashMap();

//		 1、判断该班组是否是综合维护班
        String citySubRoleFlag = StaticMethod.nullObject2String(ifCitySubrole(operateRoleId));
        System.out.println("sheetId====" + sheetId + "===citySubRoleFlag==" + citySubRoleFlag + "==otherFlag===" + otherFlag + "====othersubroleid==" + othersubroleid);
        if ("0".equals(citySubRoleFlag)) {//该工单是综合维护班
            if (!"".equals(linkCiytSubrole)) {
                //有选择班组---》驳回后到该班组
                nextSubroleId = linkCiytSubrole;
                System.out.println("nextSubroleId==1==" + nextSubroleId);
            }

        } else if ("1".endsWith(otherFlag)) {//判断是否是其他班组（其他班组是除了综合维护班组、无线网元对应的移动班组、铁塔班组以外的班组）
            //驳回后转单到该地市的综合维护班
            nextSubroleId = subroleid;
            System.out.println("nextSubroleId==2==" + nextSubroleId);
        } else if ("0".endsWith(otherFlag)) {//无线网元对应的移动班组、铁塔班组
            if ("".equals(othersubroleid)) {
                //没有查到对应的班组（移动或铁塔班组），给综合维护班
                nextSubroleId = subroleid;
                System.out.println("nextSubroleId==3==" + nextSubroleId);
            } else {
                //判断该工单有关 移动或铁塔 驳回次数，main表字段 mainCitySubrole=1,表示已经驳回了一次，为空或者为0就
                if ("1".equals(mainCitySubrole)) {//此时派给综合维护班
                    nextSubroleId = subroleid;
                    System.out.println("nextSubroleId==4==" + nextSubroleId);
                } else {//此时派给对应的 移动或铁塔班组
                    nextSubroleId = othersubroleid;
                    System.out.println("nextSubroleId==5==" + nextSubroleId);
                    //将状态为置为1
                    mainCitySubrole = "1";
                }
            }
        }
        ruleMap.put("mainCitySubrole", mainCitySubrole);
        ruleMap.put("nextSubroleId", nextSubroleId);
        return ruleMap;
    }

    /**
     * 智能质检判断条件
     * <p>
     * 告警清除时间  mainAlarmSolveDate
     * 故障处理结果   linkFaultDealResult  101030601（已解决）
     * T1处理时限 mainCompleteLimitT1
     * T2处理时限 mainCompleteLimitT2
     * 故障消除时间  linkFaultAvoidTime
     * 处理措施  linkDealStep
     * 环节   taskName
     * 操作步骤 operateType
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Map intelligentQuality(Map map) throws Exception {

        String linkReserved3 = "0";//智能质检结果  合格
        String linkReserved4 = "满足智能质检，质检通过";//智能质检意见
        String linkFaultDealResult = StaticMethod.nullObject2String(map.get("linkFaultDealResult"));
        String taskName = StaticMethod.nullObject2String(map.get("taskName"));
        String operateType = StaticMethod.nullObject2String(map.get("operateType"));
        Date mainAlarmSolveDate = string2date(map.get("mainAlarmSolveDate"));//告警清除时间
        Date mainCompleteLimitT1 = string2date(map.get("mainCompleteLimitT1"));
        Date mainCompleteLimitT2 = string2date(map.get("mainCompleteLimitT2"));

        Date linkFaultAvoidTime = string2date(map.get("linkFaultAvoidTime"));//故障消除时间

        String linkDealStep = StaticMethod.nullObject2String(map.get("linkDealStep"));//处理措施
        String sheetId = StaticMethod.nullObject2String(map.get("sheetId"));
        String ifMobile = StaticMethod.nullObject2String(map.get("ifMobile"));

        System.out.println("lyg==sheetId==" + sheetId + "==ifMobile==" + ifMobile + "==taskName==" + taskName + "==operateType==" + operateType + "==" +
                "linkFaultDealResult==" + linkFaultDealResult + "===linkDealStep==" + linkDealStep);

        System.out.println("lyg==sheetId==time==mainAlarmSolveDate=" + mainAlarmSolveDate + "===mainAlarmSolveDate==" + mainAlarmSolveDate + "==" +
                "==mainCompleteLimitT1=" + mainCompleteLimitT1 + "==mainCompleteLimitT2==" + mainCompleteLimitT2 + "==linkFaultAvoidTime=" + linkFaultAvoidTime);

        System.out.println();
        if ("101030601".equals(linkFaultDealResult) && mainAlarmSolveDate == null) {
            linkReserved3 = "1";
            linkReserved4 = "核实故障现象未消除，请重新处理";
        }

        if ("0".equals(linkReserved3)) {
            Date bijiao = null;
            if ("FirstExcuteHumTask".equals(taskName) && "46".equals(operateType)) {
                bijiao = mainCompleteLimitT1;
            } else if ("SecondExcuteHumTask".equals(taskName) && "46".equals(operateType)) {
                bijiao = mainCompleteLimitT2;
            }
            if (bijiao != null && new Date().getTime() > bijiao.getTime()) {
                linkReserved3 = "1";
                linkReserved4 = "处理超时";
            }
        }

        if ("0".equals(linkReserved3) && linkFaultAvoidTime != null && mainAlarmSolveDate != null) {
            if (linkFaultAvoidTime.getTime() < mainAlarmSolveDate.getTime() || linkFaultAvoidTime.getTime() - mainAlarmSolveDate.getTime() > 30 * 60 * 1000) {
                linkReserved3 = "1";
                linkReserved4 = "故障消除时间错误";
            }
        }

        String gzyy = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-intelligent.xml").getProperty("gzyy"));
        String cljg = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-intelligent.xml").getProperty("cljg"));
        String cznr = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-intelligent.xml").getProperty("cznr"));

        if ("0".equals(linkReserved3) && "101030601".equals(linkFaultDealResult)) {
            if ("0".equals(matchStr(gzyy, linkDealStep))) {
                linkReserved3 = "1";
                linkReserved4 = "故障原因定位缺失";
            } else if ("0".equals(matchStr(cljg, linkDealStep))) {
                linkReserved3 = "1";
                linkReserved4 = "故障处理过程中缺少处理结果";
            } else if ("0".equals(matchStr(cznr, linkDealStep))) {
                linkReserved3 = "1";
                linkReserved4 = "故障处理过程中缺少具体操作内容";
            }
        }
        Map retMap = new HashMap();
        retMap.put("linkReserved3", linkReserved3);
        retMap.put("linkReserved4", linkReserved4);

        System.out.println("lyg==ret==sheetId=" + sheetId + "===linkReserved3=" + linkReserved3 + "===linkReserved4=" + linkReserved4);

        return retMap;
    }

    public Date string2date(Object date) {
        Date retdate = null;
        String strDate = StaticMethod.nullObject2String(date);
        try {
            retdate = SheetUtils.stringToDate(strDate);
        } catch (SheetException e) {
            e.printStackTrace();
        }
        return retdate;
    }

//	 /**
//	  * 将不知道类型的时间，转换成Date类型时间格式
//	  * @param date
//	  * @return
//	  * @throws Exception
//	  */
//	 public Date string2date(Object date){
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		 Date retdate = null;
//		 if(!"".equals(StaticMethod.nullObject2String(date))){
//			 String dateObjectType = date.getClass()+"";
//			 System.out.println("dateObjectType="+dateObjectType);
//			 if(dateObjectType.indexOf("String") >=0){//表示该时间格式是String类型
//				 String strDate = StaticMethod.nullObject2String(date);
////				 try {
////					retdate = sdf.parse(strDate);
////				} catch (ParseException e) {
////					System.out.println("11");
////					SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
////					try {
////						retdate = sdf1.parse(strDate);
////					} catch (ParseException e1) {
////						System.out.println("22");
////					}
////				}
//				 try {
//					retdate = SheetUtils.stringToDate(strDate);
//				} catch (SheetException e) {
//					// TODO Auto-generated catch block
//					System.out.println("11");
//				}
//			 }else if(dateObjectType.indexOf("Date") >=0){
//				 try {
//					 retdate = (Date) date;
//				 } catch (Exception e1) {
//					 System.out.println("33");
//				 }
//			 }else{
//				String date1 = StaticMethod.nullObject2String(date);
//				 try {
//					retdate = sdf.parse(date1);
//				} catch (ParseException e) {
//					System.out.println("44");
//				}
//			 }
//		 }
//		 return retdate;
//	 }

    /**
     * str2中是否含有str1中的关键字
     *
     * @param str1 导致,原因,造成,引起,产生
     * @param str2 这重情况导致了很严重的后果
     * @return
     */
    public String matchStr(String str1, String str2) {
        String flag = "0";//没有匹配到
        if (str1 != null && !"".equals(str1) && str2 != null && !"".equals(str2)) {
            String qian[] = str1.split(",");
            for (int i = 0; i < qian.length; i++) {
                String qian1 = qian[i];
                if (str2.indexOf(qian1) != -1) {
                    flag = "1";
                    break;
                }
            }
        }
        return flag;
    }

}
