// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AgentMaintenanceMethod.java

package com.boco.eoms.sheet.agentmaintenance.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.interfaces.eomsDaiWeiNet.ReceiveDaiweiServiceLocator;
import com.boco.eoms.interfaces.eomsDaiWeiNet.ReceiveDaiweiServiceSoap_PortType;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.sheet.agentmaintenance.model.*;
import com.boco.eoms.sheet.agentmaintenance.service.IAgentMaintenanceMainManager;
import com.boco.eoms.sheet.agentmaintenance.service.IAgentMaintenanceTaskManager;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.*;
import com.boco.eoms.sheet.base.util.flowdefine.xml.*;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commontask.model.CommonTaskMain;
import com.boco.eoms.sheet.commontask.service.ICommonTaskMainManager;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import com.boco.eoms.util.InterfaceUtil;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.util.ParamEncoder;

public class AgentMaintenanceMethod extends BaseSheet {

    public AgentMaintenanceMethod() {
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

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("subAuditPerformer", "subAuditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        if (operatName.equals("forceHold")) {
            HashMap map = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
            if (sheetKey == null || sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            AgentMaintenanceMain main = (AgentMaintenanceMain) getMainService().getSingleMainPO(sheetKey);
            String sourceId = StaticMethod.nullObject2String(main.getSourceId());
            BocoLog.info(this, "代维mainid为" + sheetKey + " 的工单撤销与父工单:" + sourceId + " 的关系");
            if (!"".equals(sourceId)) {
                main.setSourceId("");
                main.setParentSheetId("");
                main.setMainSheetState("-1");
                main.setParentSheetId("");
            } else {
                BocoLog.info(this, "此代维工单对应的父工单id为空");
            }
            BaseLink bl = getLinkService().getLinkObject();
            if ("-17".equals(operateType)) {
                main.setStatus(new Integer(-12));
                bl.setOperateType(new Integer(-17));
            }
            map.put("main", main);
            map.put("link", getLinkService().getLinkObject());
            map.put("operate", getPageColumnName());
            hashMap.put("selfSheet", map);
        } else if (taskName.equals("")) {
            HashMap sheetMap = new HashMap();
            try {
                sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException illegalaccessexception) {
            } catch (InstantiationException instantiationexception) {
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else if (taskName.equals("DraftHumTask") || taskName.equals("ExcuteHumTask") || taskName.equals("ByRejectHumTask")) {
            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey == null || sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            BaseMain main = getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException illegalaccessexception1) {
            } catch (InstantiationException instantiationexception1) {
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else if (taskName.equals("advice") || taskName.equals("reply") || taskName.equals("cc")) {
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
        } else if (operateType.equals("211") || operateType.equals("212") || operateType.equals("205")) {
            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            String mainSheetState = StaticMethod.nullObject2String(request.getParameter("mainSheetState"));
            if (sheetKey == null || sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            AgentMaintenanceMain main = (AgentMaintenanceMain) getMainService().getSingleMainPO(sheetKey);
            main.setMainSheetState(mainSheetState);
            sheetMap.put("main", main);
            sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else {
            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            BaseMain main = getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException illegalaccessexception2) {
            } catch (InstantiationException instantiationexception2) {
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
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String sourceId = StaticMethod.null2String(request.getParameter("sourceId"));
        String type = StaticMethod.null2String(request.getParameter("type"));
        System.out.println("lizhi:taskName=" + taskName + "operateType=" + operateType + "sourceId=" + sourceId + "type=" + type);
        if ((taskName == null || "".equals(taskName)) && (operateType == null || "".equals(operateType)) && sourceId != null && !"".equals(sourceId) && (taskName == null || "".equals(taskName)) && (operateType == null || "".equals(operateType))) {
            if (type != null && type.equals("commonfault")) {
                ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
                CommonFaultMain commonfaultMain = (CommonFaultMain) commonFaultMainManager.loadSinglePO(sourceId);
                commonfaultMain.setIfAgent("0");
                commonFaultMainManager.saveOrUpdateMain(commonfaultMain);
            }
            if (type != null && type.equals("complaint")) {
                IComplaintMainManager complaintMainManager = (IComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
                ComplaintMain complaintMain = (ComplaintMain) complaintMainManager.loadSinglePO(sourceId);
                complaintMain.setIfAgent("0");
                complaintMainManager.saveOrUpdateMain(complaintMain);
            }
            if (type != null && type.equals("commontask")) {
                ICommonTaskMainManager commonTaskMainManager = (ICommonTaskMainManager) ApplicationContextHolder.getInstance().getBean("iCommonTaskMainManager");
                CommonTaskMain commonTaskMain = (CommonTaskMain) commonTaskMainManager.getSingleMainPO(sourceId);
                String senddeptid = StaticMethod.nullObject2String(request.getParameter("sendDeptId"));
                String ifagent = StaticMethod.nullObject2String(commonTaskMain.getIfAgent());
                if ("1".equals(ifagent))
                    ifagent = ifagent + "," + senddeptid + ",";
                else
                    ifagent = ifagent + senddeptid + ",";
                System.out.println("lizhi:ifagent=" + ifagent);
                commonTaskMain.setIfAgent(ifagent);
                commonTaskMainManager.saveOrUpdateMain(commonTaskMain);
            }
        }
        HashMap sheetMap = getFlowEngineMap();
        if (taskName.equals("TaskCreateAuditHumTask") && operateType.equals("201")) {
            Map operateMap = (HashMap) sheetMap.get("operate");
            if (operateMap.get("dealPerformer") == null || operateMap.get("dealPerformer").equals("")) {
                Map processOjbectAttribute = getProcessOjbectAttribute();
                HashMap sessionMap = new HashMap();
                TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
                sessionMap.put("userId", sessionform.getUserid());
                sessionMap.put("password", sessionform.getPassword());
                String objectName = processOjbectAttribute.get("objectName") != null ? (String) processOjbectAttribute.get("objectName") : "";
                System.out.println("objectName is =============" + objectName);
                Map parameterValuemap = businessFlowService.getVariable(StaticMethod.null2String(request.getParameter("piid")), objectName, sessionMap);
                String dealPerformerValue = (String) parameterValuemap.get("dealPerformer");
                String dealPerformerLeaderValue = (String) parameterValuemap.get("dealPerformerLeader");
                String dealPerformerTypeValue = (String) parameterValuemap.get("dealPerformerType");
                operateMap.put("dealPerformer", dealPerformerValue);
                operateMap.put("dealPerformerLeader", dealPerformerLeaderValue);
                operateMap.put("dealPerformerType", dealPerformerTypeValue);
                System.out.println("dealPerformerValue>>>>>>>" + dealPerformerValue + "dealPerformerLeaderValue>>>>>>>" + dealPerformerLeaderValue + "dealPerformerTypeValue>>>>>>>>" + dealPerformerTypeValue);
                processOjbectAttribute.remove("objectName");
                sheetMap.put("operate", operateMap);
            }
        }
        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);
        }
        Map main = (HashMap) sheetMap.get("main");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String user_id = sessionform.getUserid();
        String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
        if (operateType.equals("211") && user_id.equals(main.get("sendUserId"))) {
            phaseId = "Over";
            Map operateMap = (Map) sheetMap.get("operate");
            operateMap.put("phaseId", phaseId);
            sheetMap.put("operate", operateMap);
            sheetMap = makeHold(sheetMap);
        }
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        if (!operatName.equals("forceHold") && (taskName.equals("") || taskName.equals("TaskCreateAuditHumTask") || taskName.equals("ExcuteHumTask") || taskName.equals("DraftHumTask") || taskName.equals("ByRejectHumTask"))) {
            Map link = (HashMap) sheetMap.get("link");
            Map operate = (HashMap) sheetMap.get("operate");
            String dealperformers[] = StaticMethod.nullObject2String(operate.get("dealPerformer")).split(",");
            String auditperformer = StaticMethod.nullObject2String(operate.get("auditPerformer"));
            if (dealperformers.length > 0 && auditperformer.equals("")) {
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
                sheetMap.put("corrKey", corrKey);
            }
            sheetMap.put("link", link);
        }
        Map operate = (HashMap) sheetMap.get("operate");
        sheetMap.put("operate", operate);
        setFlowEngineMap(sheetMap);
        sourceId = StaticMethod.nullObject2String(main.get("sourceId"));
        type = StaticMethod.nullObject2String(main.get("type"));
        String sheetType = StaticMethod.nullObject2String(main.get("mainSheetType"));
        String sendDeptId = StaticMethod.nullObject2String(main.get("sendDeptId"));
        System.out.println("lizhi:sourceId=" + sourceId + "type=" + type + "sendDeptId=" + sendDeptId);
        Map linkrule = (HashMap) sheetMap.get("link");
        //	Map mainrule = (HashMap)sheetMap.get("main");
        if ("ExcuteHumTask".equals(taskName) && "205".equals(operateType) && sourceId != null && !"".equals(sourceId)) {
            if (type != null && type.equals("commonfault")) {
                ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
                CommonFaultMain commonfaultMain = (CommonFaultMain) commonFaultMainManager.loadSinglePO(sourceId);
                commonfaultMain.setRevert("1");
                commonFaultMainManager.saveOrUpdateMain(commonfaultMain);
            }
            if (type != null && type.equals("complaint")) {
                IComplaintMainManager complaintMainManager = (IComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
                ComplaintMain complaintMain = (ComplaintMain) complaintMainManager.loadSinglePO(sourceId);
                complaintMain.setRevert("1");
                complaintMainManager.saveOrUpdateMain(complaintMain);
            }
            if (type != null && type.equals("commontask")) {
                ICommonTaskMainManager commonTaskMainManager = (ICommonTaskMainManager) ApplicationContextHolder.getInstance().getBean("iCommonTaskMainManager");
                CommonTaskMain commonTaskMain = (CommonTaskMain) commonTaskMainManager.getSingleMainPO(sourceId);
                String revert = StaticMethod.nullObject2String(commonTaskMain.getRevert());
                if ("".equals(revert))
                    revert = "," + sendDeptId + ",";
                else
                    revert = revert + sendDeptId + ",";
                commonTaskMain.setRevert(revert);
                commonTaskMainManager.saveOrUpdateMain(commonTaskMain);
            }
        }
        if ("ExcuteHumTask".equals(taskName) && "205".equals(operateType) && type != null && type.equals("complaint") && sheetType != null && "10185010201".equals(sheetType)) {
            HashMap xmlMap = new HashMap();
            xmlMap.put("Sheet_no", StaticMethod.nullObject2String(main.get("sheetId")));
            xmlMap.put("Operate_time", StaticMethod.nullObject2String(linkrule.get("operateTime")));
            xmlMap.put("Operate_time", StaticMethod.nullObject2String(linkrule.get("operateTime")));
            xmlMap.put("Operate_userid", StaticMethod.nullObject2String(linkrule.get("operateUserId")));
            xmlMap.put("Operate_contact", StaticMethod.nullObject2String(linkrule.get("operaterContact")));
            xmlMap.put("Operate_department", StaticMethod.nullObject2String(linkrule.get("operateDeptId")));
            xmlMap.put("Operate_task", StaticMethod.nullObject2String("操作完成"));
            xmlMap.put("linkman", StaticMethod.nullObject2String(linkrule.get("linkComContactUser")));
            xmlMap.put("Linkman_phone", StaticMethod.nullObject2String(linkrule.get("linkComContactPhone")));
            xmlMap.put("Eliminate_time", StaticMethod.nullObject2String(linkrule.get("linkComFaultEndTime")));
            xmlMap.put("Dispose_result", StaticMethod.nullObject2String(linkrule.get("linkComdealResult")));
            xmlMap.put("Reason_deal", StaticMethod.nullObject2String(linkrule.get("linkComTransmitReason")));
            xmlMap.put("Solution", StaticMethod.nullObject2String(linkrule.get("linkComLocalDealDesc")));
            try {
                InterfaceUtil interfaceUtil = new InterfaceUtil();
                String opDetail = interfaceUtil.mapToXml(xmlMap);
                System.out.println("--opDetail--" + opDetail);
                ReceiveDaiweiServiceLocator locator = new ReceiveDaiweiServiceLocator();
                ReceiveDaiweiServiceSoap_PortType binding = locator.getReceiveDaiweiServiceSoap();
                String result = binding.newWorkSheet("", "", "", "", opDetail);
                System.out.println("--netdaiwei--sheetid--" + main.get("sheetId") + "--result--" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if ("AffirmHumTask".equals(taskName) && "212".equals(operateType) && sourceId != null && !"".equals(sourceId)) {
            if (type != null && type.equals("commonfault")) {
                ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
                CommonFaultMain commonfaultMain = (CommonFaultMain) commonFaultMainManager.loadSinglePO(sourceId);
                commonfaultMain.setRevert("0");
                commonFaultMainManager.saveOrUpdateMain(commonfaultMain);
            }
            if (type != null && type.equals("complaint")) {
                IComplaintMainManager complaintMainManager = (IComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
                ComplaintMain complaintMain = (ComplaintMain) complaintMainManager.loadSinglePO(sourceId);
                complaintMain.setRevert("0");
                complaintMainManager.saveOrUpdateMain(complaintMain);
            }
            if (type != null && type.equals("commontask")) {
                ICommonTaskMainManager commonTaskMainManager = (ICommonTaskMainManager) ApplicationContextHolder.getInstance().getBean("iCommonTaskMainManager");
                CommonTaskMain commonTaskMain = (CommonTaskMain) commonTaskMainManager.getSingleMainPO(sourceId);
                String revert = StaticMethod.nullObject2String(commonTaskMain.getRevert());
                sendDeptId = "," + sendDeptId + ",";
                if (revert.indexOf(sendDeptId) != -1)
                    revert = revert.replace(sendDeptId, ",");
                commonTaskMain.setRevert(revert);
                commonTaskMainManager.saveOrUpdateMain(commonTaskMain);
            }
        }
    }

    public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
        if (!preLinkId.equals("") && !preLinkId.equals("null")) {
            AgentMaintenanceLink preLink = (AgentMaintenanceLink) getLinkService().getSingleLinkPO(preLinkId);
            request.setAttribute("dwpreLink", preLink);
        }
    }

    public void showListUndo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HashMap filterMap = new HashMap();
        filterMap.put("TEMPLATE_NAME", getMainService().getFlowTemplateName());
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());
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
        String userId = sessionform.getUserid();
        String deptId = sessionform.getDeptid();
        BaseMain mainObject = (BaseMain) getMainService().getMainObject().getClass().newInstance();
        ITask taskObject = (ITask) getTaskService().getTaskModelObject().getClass().newInstance();
        Map condition = new HashMap();
        condition.put("mainObject", mainObject);
        condition.put("taskObject", taskObject);
        condition.put("orderCondition", orderCondition);
        String type = StaticMethod.null2String(request.getParameter("type1"));
        condition.put("type", type);
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
            HashMap columnMapOverTip = OvertimeTipUtil.getNotOverTimeColumnByMapping(flowName);
            for (int i = 0; i < taskOvertimeList.size(); i++) {
                ITask tmptask = null;
                Map taskMap = new HashMap();
                Map tmptaskMap = new HashMap();
                HashMap conditionMap = new HashMap();
                if (columnMap.size() > 0) {
                    Object tmpObjArr[] = (Object[]) taskOvertimeList.get(i);
                    tmptask = (ITask) tmpObjArr[0];
                    Iterator it = columnMap.keySet().iterator();
                    int j = 0;
                    String elementKey;
                    Object tempcolumn;
                    for (; it.hasNext(); tmptaskMap.put(columnMap.get(elementKey), tempcolumn)) {
                        j++;
                        elementKey = (String) it.next();
                        tempcolumn = tmpObjArr[j];
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
                taskMap.putAll(tmptaskMap);
                taskList.add(tmptask);
                taskMapList.add(taskMap);
            }

        }
        Integer overTimeTaskCount = getTaskService().getOverTimeTaskCount(condition, userId, deptId);
        request.setAttribute("taskList", taskMapList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("overTimeTaskCount", overTimeTaskCount);
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

    public HashMap makeHold(HashMap sheetMap)
            throws Exception {
        BocoLog.info(this, "====makeHold====");
        Map mainMap = (HashMap) sheetMap.get("main");
        Map tmpLinkMap = (HashMap) sheetMap.get("link");
        Map operateMap = (HashMap) sheetMap.get("operate");
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
        AgentMaintenanceLink link1 = (AgentMaintenanceLink) getLinkService().getLinkObject().getClass().newInstance();
        SheetBeanUtils.populateMap2Bean(link1, linkMap);
        getLinkService().addLink(link1);
        AgentMaintenanceTask task = (AgentMaintenanceTask) getTaskService().getTaskModelObject();
        String linkId = UUIDHexGenerator.getInstance().getID();
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
        task.setCurrentLinkId(linkId);
        task.setIfWaitForSubTask("false");
        task.setCreateDay(calendar.get(5));
        task.setCreateMonth(calendar.get(2) + 1);
        task.setCreateYear(calendar.get(1));
        task.setTaskDisplayName("待归档");
        task.setTaskName("HoldHumTask");
        task.setOperateRoleId(link1.getOperateRoleId());
        task.setTaskOwner(link1.getOperateUserId());
        task.setOperateType("subrole");
        task.setFlowName("AgentMaintenanceMainFlowProcess");
        task.setParentTaskId(task.getId());
        task.setSendTime((Date) mainMap.get("sendTime"));
        getTaskService().addTask(task);
        tmpLinkMap.put("operateType", "18");
        tmpLinkMap.put("id", linkId);
        tmpLinkMap.put("mainId", (String) mainMap.get("id"));
        tmpLinkMap.put("preLinkId", link1.getId());
        tmpLinkMap.put("aiid", task.getId());
        tmpLinkMap.put("operateTime", new Date(nowDate.getTime() + 2000L));
        tmpLinkMap.put("nodeCompleteLimit", mainMap.get("sheetCompleteLimit"));
        tmpLinkMap.put("activeTemplateId", "HoldHumTask");
        tmpLinkMap.put("nodeAccessories", mainMap.get("firstNodeAccessories"));
        mainMap.put("status", Constants.SHEET_HOLD);
        mainMap.put("status", Constants.SHEET_HOLD);
        sheetMap.put("main", mainMap);
        sheetMap.put("link", tmpLinkMap);
        return sheetMap;
    }

    public void performFroceHold(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String piid = StaticMethod.nullObject2String(request.getParameter("piid"));
        String processTemplateName = StaticMethod.nullObject2String(request.getParameter("processTemplateName"));
        String operateName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        HashMap columnMap = getInterfaceObjMap(mapping, form, request, response);
        HashMap WpsMap = new HashMap();
        try {
            Map map = request.getParameterMap();
            Map serializableMap = SheetUtils.serializableParemeterMap(map);
            HashMap tempWpsMap;
            for (Iterator it = serializableMap.keySet().iterator(); it.hasNext(); WpsMap.putAll(tempWpsMap)) {
                String mapKey = (String) it.next();
                Map tempMap = (Map) serializableMap.get(mapKey);
                HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
                tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
            }

            setFlowEngineMap(WpsMap);
            dealFlowEngineMap(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());
        String ifReplyInvoke = StaticMethod.nullObject2String(request.getParameter("ifReplyInvoke"));
        if (ifReplyInvoke.equals("true")) {
            String invokePiid = StaticMethod.nullObject2String(request.getParameter("invokePiid"));
            String invokeOperateName = StaticMethod.nullObject2String(request.getParameter("invokeOperateName"));
            String invokeProcessBeanId = StaticMethod.nullObject2String(request.getParameter("invokeProcessBeanId"));
            String parentSheetKey = StaticMethod.nullObject2String(request.getParameter("invokeSheetId"));
            String toPhaseId = StaticMethod.nullObject2String(request.getParameter("invokePhaseId"));
            IBaseSheet parentSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean(invokeProcessBeanId);
            if (parentSheetKey != null) {
                HashMap sheetMap = new HashMap();
                HashMap mainMap = new HashMap();
                HashMap linkMap = new HashMap();
                HashMap operateMap = new HashMap();
                BaseMain parentMain = parentSheet.getMainService().getSingleMainPO(parentSheetKey);
                BaseLink parentLink = (BaseLink) parentSheet.getLinkService().getLinkObject().getClass().newInstance();
                mainMap = SheetBeanUtils.bean2MapWithNull(parentMain);
                linkMap = SheetBeanUtils.bean2MapWithNull(parentLink);
                operateMap.put("phaseId", toPhaseId);
                operateMap.put("hasNextTaskFlag", "invokeProcess");
                sheetMap.put("main", mainMap);
                sheetMap.put("link", linkMap);
                sheetMap.put("operate", operateMap);
                parentSheet.getBusinessFlowService().reInvokeProcess(invokePiid, invokeOperateName, sheetMap, sessionMap);
            }
        }
        businessFlowService.triggerEvent(piid, processTemplateName, operateName, getFlowEngineMap(), sessionMap);
        Map tmpmain = (Map) WpsMap.get("main");
        String parentSheetName = StaticMethod.nullObject2String(tmpmain.get("parentSheetName"));
        String sheetKey = StaticMethod.nullObject2String(tmpmain.get("id"));
        IAgentMaintenanceMainManager pms = (IAgentMaintenanceMainManager) ApplicationContextHolder.getInstance().getBean("iAgentMaintenanceMainManager");
        IAgentMaintenanceTaskManager taskmanager = (IAgentMaintenanceTaskManager) ApplicationContextHolder.getInstance().getBean("iagentmaintenanceTaskManager");
        if (!"".equals(parentSheetName)) {
            String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
            BocoLog.info(this, parentSheetName + " 取消与代维工单的关联 operateType is----" + operateType);
            if (!"".equals(sheetKey) && "-17".equals(operateType)) {
                AgentMaintenanceMain mainObject = (AgentMaintenanceMain) getMainService().getSingleMainPO(sheetKey);
                System.out.println("lizhi:type=" + mainObject.getType());
                if (mainObject.getType().equalsIgnoreCase("commontask")) {
                    ICommonTaskMainManager commonTaskMainManager = (ICommonTaskMainManager) ApplicationContextHolder.getInstance().getBean("iCommonTaskMainManager");
                    CommonTaskMain commonTaskMain = (CommonTaskMain) commonTaskMainManager.getSingleMainPO(mainObject.getSourceId());
                    String deptid = "," + mainObject.getSendDeptId() + ",";
                    String ifagent = commonTaskMain.getIfAgent();
                    System.out.println("lizhi:deptid=" + deptid + "ifagent=" + ifagent);
                    if (ifagent.indexOf(deptid) != -1) {
                        ifagent = ifagent.replace(deptid, ",");
                        commonTaskMain.setIfAgent(ifagent);
                        commonTaskMainManager.saveOrUpdateMain(commonTaskMain);
                    }
                } else if (mainObject.getType().equalsIgnoreCase("commonfault")) {
                    ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
                    CommonFaultMain commonfaultMain = (CommonFaultMain) commonFaultMainManager.loadSinglePO(mainObject.getSourceId());
                    commonfaultMain.setIfAgent("1");
                    commonFaultMainManager.saveOrUpdateMain(commonfaultMain);
                } else if (mainObject.getType().equalsIgnoreCase("complaint")) {
                    IComplaintMainManager complaintMainManager = (IComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
                    ComplaintMain complaintMain = (ComplaintMain) complaintMainManager.loadSinglePO(mainObject.getSourceId());
                    complaintMain.setIfAgent("1");
                    complaintMainManager.saveOrUpdateMain(complaintMain);
                }
                BocoLog.info(this, "主工单的 status：" + mainObject.getStatus());
                mainObject.setStatus(new Integer(-12));
                mainObject.setSourceId("");
                pms.saveOrUpdateMain(mainObject);
                List tasks = getTaskService().getTasksByCondition("sheetkey='" + sheetKey + "'");
                for (int i = 0; i < tasks.size(); i++) {
                    AgentMaintenanceTask agentmaintenancetask = (AgentMaintenanceTask) tasks.get(i);
                    agentmaintenancetask.setTaskStatus("5");
                    taskmanager.addTask(agentmaintenancetask);
                }

            }
        } else {
            AgentMaintenanceMain mainObject = (AgentMaintenanceMain) getMainService().getSingleMainPO(sheetKey);
            mainObject.setStatus(new Integer(-12));
            pms.saveOrUpdateMain(mainObject);
            List tasks = getTaskService().getTasksByCondition("sheetkey='" + sheetKey + "'");
            for (int i = 0; i < tasks.size(); i++) {
                AgentMaintenanceTask agentmaintenancetask = (AgentMaintenanceTask) tasks.get(i);
                agentmaintenancetask.setTaskStatus("5");
                taskmanager.addTask(agentmaintenancetask);
            }

            BocoLog.info(this, "撤销手动派单，mainid is：" + tmpmain.get("id"));
        }
    }

    public void performAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HashMap columnMap = getInterfaceObjMap(mapping, form, request, response);
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

        String processTemplateName = StaticMethod.nullObject2String(request.getParameter("processTemplateName"));
        String operateName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        setFlowEngineMap(WpsMap);
        dealFlowEngineMap(mapping, form, request, response);
        Map tmpmain = (Map) WpsMap.get("main");
        String tmpparentPhaseName = (String) tmpmain.get("parentPhaseName");
        System.out.println("==========basesheet:parentPhaseName:" + tmpparentPhaseName);
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());
        businessFlowService.initProcess(processTemplateName, operateName, getFlowEngineMap(), sessionMap);
        String copyPerformer = StaticMethod.nullObject2String(request.getParameter("copyPerformer"));
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String sheetId = StaticMethod.nullObject2String(tmpmain.get("sheetId"));
        BocoLog.info(this, "sheetId is :::" + sheetId);
        if (!copyPerformer.equals("")) {
            try {
                newSaveNonFlowData("", getFlowEngineMap());
            } catch (Exception e) {
                e.printStackTrace();
            }
            addMsgWithCopy(request, copyPerformer, sheetId);
        }
    }

    public void addMsgWithCopy(HttpServletRequest request, String copyPerformer, String sheetId) {
        try {
            String copyMsgId = XmlManage.getFile("/config/agentmaintenance-msg.xml").getProperty("copymsgid");
            String content = XmlManage.getFile("/config/agentmaintenance-msg.xml").getProperty("content");
            if (copyMsgId != null && !"".equals(copyMsgId)) {
                ITawSystemUserManager tsum = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("ItawSystemUserSaveManagerFlush");
                TawSystemUser tsu = tsum.getUserByuserid(copyPerformer);
                if (tsu.getId() != null && !"".equals(tsu.getId())) {
                    String mobile = StaticMethod.nullObject2String(tsu.getMobile());
                    if ("".equals(tsu.getMobile()) || mobile.length() != 11) {
                        BocoLog.info(this, "用户：" + tsu.getUserid() + " 的手机号不合格：" + tsu.getMobile());
                    } else {
                        String title = StaticMethod.nullObject2String(request.getParameter("title"));
                        System.out.println("lizhi:title=" + title);
                        ISmsMonitorManager mgr = (ISmsMonitorManager) ApplicationContextHolder.getInstance().getBean("IsmsMonitorManager");
                        SmsMonitor smsMonitor = new SmsMonitor();
                        smsMonitor.setServiceId(copyMsgId);
                        smsMonitor.setReceiverId(copyPerformer);
                        smsMonitor.setMobile(mobile);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new Date());
                        cal.add(13, 1);
                        smsMonitor.setDispatchTime(cal.getTime());
                        smsMonitor.setContent("工单号为：" + sheetId + "主题为:" + title + "的代维流程工单，" + content);
                        smsMonitor.setIsSendImediat("false");
                        smsMonitor.setRegetData("false");
                        smsMonitor.setDeleted("0");
                        mgr.saveSmsMonitor(smsMonitor);
                        BocoLog.info(this, "服务id为：" + copyMsgId + ",工单号为：" + sheetId + "，接收对象为：" + tsu.getUserid() + " 的短信抄送提醒已发送。");
                    }
                } else {
                    BocoLog.info(this, "没查到被抄送的对象user信息，user is：" + copyPerformer);
                }
            } else {
                BocoLog.info(this, "请至 agentmaintenance-msg.xml文件中查看 copymsgid中是否配置了短信服务id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newPerformDeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.newPerformDeal(mapping, form, request, response);
        String copyPerformer = StaticMethod.nullObject2String(request.getParameter("copyPerformer"));
        BocoLog.info(this, "deal copyPerformer is==" + copyPerformer);
        if (!"".equals(copyPerformer)) {
            String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetId"));
            addMsgWithCopy(request, copyPerformer, sheetId);
        }
    }

    public void newPerformNonFlow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BocoLog.info(this, "进入 agentMaintance 的 newPerformNonFlow");
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        HashMap columnMap = getInterfaceObjMap(mapping, form, request, response);
        Map map = newSetDealRequestMap(mapping, form, request, response);
        Object taskIdObject = map.get("TKID");
        if (taskIdObject != null && taskIdObject.getClass().isArray())
            taskIdObject = ((Object[]) taskIdObject)[0];
        String taskId = StaticMethod.nullObject2String(taskIdObject);
        Map serializableMap = SheetUtils.serializableParemeterMap(map);
        System.out.println("=====request Map parentPhaseName:" + StaticMethod.nullObject2String(request.getParameter("parentPhaseName"), ""));
        Iterator it = serializableMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        HashMap tempWpsMap;
        for (; it.hasNext(); WpsMap.putAll(tempWpsMap)) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            if (taskId.equals("")) {
                Object obj = tempMap.get("aiid");
                if (obj != null) {
                    if (obj.getClass().isArray()) {
                        Object obja[] = (Object[]) obj;
                        obj = obja[0];
                    }
                    taskId = StaticMethod.nullObject2String(obj);
                }
            }
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
        }

        setFlowEngineMap(WpsMap);
        dealFlowEngineMap(mapping, form, request, response);
        BocoLog.info(this, "是否进入阶段回复 operateType=============" + operateType);
        if (Integer.parseInt(operateType) == 9)
            newSaveNonFlowData(taskId, getFlowEngineMap());
    }

    public void showOwnStarterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
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
        HashMap condition = new HashMap();
        String beanName = mapping.getAttribute();
        condition.put("orderCondition", orderCondition);
        condition.put("beanName", beanName);
        String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
        if (!exportType.equals(""))
            pageSize = new Integer(-1);
        String type = StaticMethod.null2String(request.getParameter("type1"));
        condition.put("type", type);
        HashMap taskListMap = getMainService().getStarterList(sessionform.getUserid(), pageIndex, new Integer(pageSize.intValue()), condition);
        List result = (List) taskListMap.get("sheetList");
        Integer total = (Integer) taskListMap.get("sheetTotal");
        List startList = new ArrayList();
        String variables[] = QuerySqlInit.getAllDictItemsName(beanName).split(",");
        for (int i = 0; result != null && i < result.size(); i++) {
            Object objectList[] = (Object[]) result.get(i);
            Map ListMap = new HashMap();
            for (int j = 0; j < objectList.length; j++) {
                String variablesKey = variables[j];
                if (variablesKey.indexOf("main.") >= 0 || variablesKey.indexOf("task.") >= 0)
                    variablesKey = variablesKey.substring(5);
                ListMap.put(variablesKey, objectList[j]);
            }

            startList.add(ListMap);
        }

        request.setAttribute("taskList", startList);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("findForward", "startlist");
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
    }

    public void showListsenddone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HashMap filterMap = new HashMap();
        filterMap.put("PROCESS_INSTANCE.TEMPLATE_NAME", getMainService().getFlowTemplateName());
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());
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
        String userId = sessionform.getUserid();
        String deptId = sessionform.getDeptid();
        BaseMain mainObject = (BaseMain) getMainService().getMainObject().getClass().newInstance();
        ITask taskObject = (ITask) getTaskService().getTaskModelObject().getClass().newInstance();
        String beanName = mapping.getAttribute();
        Map condition = new HashMap();
        condition.put("mainObject", mainObject);
        condition.put("taskObject", taskObject);
        condition.put("orderCondition", orderCondition);
        condition.put("beanName", beanName);
        String type = StaticMethod.null2String(request.getParameter("type1"));
        condition.put("type", type);
        HashMap taskListMap = getTaskService().getDoneTask(condition, userId, deptId, getMainService().getFlowTemplateName(), pageIndex, pageSize);
        int total = ((Integer) taskListMap.get("taskTotal")).intValue();
        List taskList = (List) taskListMap.get("taskList");
        StringBuffer taskCondition = new StringBuffer();
        taskCondition.append(" taskOwner = '" + userId + "' and ifWaitForSubTask='true' and taskStatus<>'" + "5" + "'");
        List tasks = getTaskService().getTasksByCondition(taskCondition.toString());
        Map tmpTaskMap = new HashMap();
        if (tasks != null && !tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                ITask task = (ITask) tasks.get(i);
                tmpTaskMap.put(task.getSheetKey(), task);
            }

        }
        List tmpTaskList = new ArrayList();
        String variables[] = QuerySqlInit.getAllDictItemsName(beanName).split(",");
        for (int i = 0; taskList != null && i < taskList.size(); i++) {
            Object objectList[] = (Object[]) taskList.get(i);
            Map ListMap = new HashMap();
            for (int j = 0; j < objectList.length; j++) {
                String variablesKey = variables[j];
                if (variablesKey.indexOf("main.") >= 0 || variablesKey.indexOf("task.") >= 0)
                    variablesKey = variablesKey.substring(5);
                ListMap.put(variablesKey, objectList[j]);
            }

            if (tmpTaskMap.containsKey(ListMap.get("id")))
                ListMap.put("status", new Integer(-11));
            tmpTaskList.add(ListMap);
        }

        request.setAttribute("taskList", tmpTaskList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("findForward", "mainlist");
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
    }

    public void performPreCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String mainid = StaticMethod.nullObject2String(request.getParameter("mainId"));
        int receivecount = 0;
        int excutehumtaskcount = 0;
        System.out.println("lizhi:taskName=" + taskName + "operateType=" + operateType + "mainid=" + mainid);
        if ("AffirmHumTask".equals(taskName) && "211".equals(operateType)) {
            String status = "0";
            StringBuffer sb = new StringBuffer();
            StringBuffer sbFn = new StringBuffer();
            JSONObject o = new JSONObject();
            JSONArray data = new JSONArray();
            JSONObject jsonRoot = new JSONObject();
            String beanName = mapping.getAttribute();
            IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean(beanName);
            List list = baseSheet.getLinkService().getLinksByMainId(mainid);
            for (int i = 0; i < list.size(); i++) {
                AgentMaintenanceLink agentMaintenanceLink = (AgentMaintenanceLink) list.get(i);
                String operatetype = StaticMethod.nullObject2String(agentMaintenanceLink.getOperateType());
                if ("0".equals(operatetype))
                    receivecount++;
                if ("205".equals(operatetype))
                    excutehumtaskcount++;
            }

            if (receivecount > 1 && receivecount > excutehumtaskcount) {
                status = "2";
                sb.append("请查看所有子工单回复满意后再归档!");
            }
            sbFn.append("function(){}");
            o.put("text", sb.toString());
            o.put("fn", sbFn.toString());
            data.put(o);
            jsonRoot.put("data", data);
            jsonRoot.put("status", status);
            JSONUtil.print(response, jsonRoot.toString());
        } else {
            super.performPreCommit(mapping, form, request, response);
        }
    }

    public void performQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String deptId = sessionform.getDeptid();
        Map actionMap = request.getParameterMap();
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String aSql[] = {
                ""
        };
        int aTotal[] = new int[1];
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
        Map condition = new HashMap();
        condition.put("loginuserdeptId", deptId);
        condition.put("orderCondition", orderCondition);
        String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
        if (!exportType.equals(""))
            pageSize = new Integer(-1);
        String queryType = StaticMethod.nullObject2String(request.getParameter("queryType"));
        List result = getMainService().getQueryResult(aSql, actionMap, condition, pageIndex, new Integer(pageSize.intValue()), aTotal, queryType);
        Integer total = new Integer(aTotal[0]);
        if (queryType != null && queryType.equals("number"))
            request.setAttribute("recordTotal", total);
        request.setAttribute("taskList", result);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);
    }
}
