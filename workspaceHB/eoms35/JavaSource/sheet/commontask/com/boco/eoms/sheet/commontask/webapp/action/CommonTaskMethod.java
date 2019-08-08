// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonTaskMethod.java

package com.boco.eoms.sheet.commontask.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.exception.AccessoriesException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.commontask.model.*;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.dom4j.*;

import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.boco.eoms.sheet.base.util.Constants;

public class CommonTaskMethod extends BaseSheet {

    public CommonTaskMethod() {
    }

    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        if (operatName.equals("forceHold")) {
            HashMap map = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
            if (sheetKey == null || sheetKey.equals(""))
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            com.boco.eoms.sheet.base.model.BaseMain main = getMainService().getSingleMainPO(sheetKey);
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
            com.boco.eoms.sheet.base.model.BaseMain main = getMainService().getSingleMainPO(sheetKey);
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
            com.boco.eoms.sheet.base.model.BaseMain main = getMainService().getSingleMainPO(sheetKey);
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
            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            com.boco.eoms.sheet.base.model.BaseMain main = getMainService().getSingleMainPO(sheetKey);
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
        String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
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
            //抄送短信派发begin
            Map main = (HashMap) sheetMap.get("main");
            String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
            String title = StaticMethod.nullObject2String(main.get("title"));
            String copyPerformer = StaticMethod.nullObject2String(operate.get("copyPerformer"));
            if (!("".equals(copyPerformer) || null == copyPerformer)) {
                this.workSM_NON_T(sheetId, copyPerformer, title);
            }
//			抄送短信派发end
        }
        String confirmMainIfReListed = request.getParameter("confirmMainIfReListed");
        Map main = (HashMap) sheetMap.get("main");
        String mainId = (String) main.get("id");
        System.out.println(mainId);
        System.out.println(confirmMainIfReListed);
        System.out.println(taskName);
        System.out.println(operateType);
        setFlowEngineMap(sheetMap);
    }

    private static final String CONFIG_FILEPATH = "classpath:config/worksheet-sms-service-info.xml";//短信服务信息配置文件

    public void workSM_NON_T(String sheetId, String receiverId, String title) throws Exception {
        try {
            MsgServiceImpl msgService = new MsgServiceImpl();

            //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
            String nodeInstantName = "worksheet.CommonTaskMainFlowProcess.smsServiceId.instant";
            System.out.println("====" + nodeInstantName);
            String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            String receivers = "";
            //拼接短信接受者
            String[] receiverIds = receiverId.split(",");
            for (int i = 0; i < receiverIds.length; i++) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_USER + "," + receiverIds[i] + "#";
            }
            System.out.println("receivers=" + receivers);

            //派发通知（即时提醒）


            //拼写发送信息
            String sendContent = "提醒您收取通用任务工单:" + sheetId + ",主题名:" + title + ",请查阅!";

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

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("subAuditPerformer", "subAuditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public Map getParameterMap() {
        return getParameterMap();
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

    public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        String taskName = (String) request.getAttribute("taskName");
        System.out.println("taskName is =============" + taskName);
        if (taskName != null && taskName.equals("TaskCreateAuditHumTask")) {
            CommonTaskLink link = (CommonTaskLink) request.getAttribute("sheetLink");
            HashMap sessionMap = new HashMap();
            TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
            sessionMap.put("userId", sessionform.getUserid());
            sessionMap.put("password", sessionform.getPassword());
            Map processOjbectAttribute = getProcessOjbectAttribute();
            JSONArray sendUserAndRoles = new JSONArray();
            String objectName = processOjbectAttribute.get("objectName") != null ? (String) processOjbectAttribute.get("objectName") : "";
            System.out.println("objectName is =============" + objectName);
            Map parameterValuemap = businessFlowService.getVariable(link.getPiid(), objectName, sessionMap);
            processOjbectAttribute.remove("objectName");
            for (Iterator it = processOjbectAttribute.keySet().iterator(); it.hasNext(); ) {
                String dealPerformer = (String) it.next();
                String dealPerformerLeader = dealPerformer + "Leader";
                String dealPerformerType = dealPerformer + "Type";
                String dealPerformerValue = (String) parameterValuemap.get(dealPerformer);
                String dealPerformerLeaderValue = (String) parameterValuemap.get(dealPerformerLeader);
                String dealPerformerTypeValue = (String) parameterValuemap.get(dealPerformerType);
                ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
                if (dealPerformerValue != null && !dealPerformerValue.equals("")) {
                    String dealPerformerValues[] = dealPerformerValue.split(",");
                    for (int i = 0; i < dealPerformerValues.length; i++) {
                        JSONObject data = new JSONObject();
                        String finalDealPerformerValue = dealPerformerValues[i];
                        System.out.println("finalDealPerformerValue is =============" + finalDealPerformerValue);
                        data.put("id", finalDealPerformerValue);
                        String finalDealPerformerTypeValue = dealPerformerTypeValue.split(",")[i];
                        data.put("nodeType", finalDealPerformerTypeValue);
                        data.put("categoryId", dealPerformer);
                        String finalDealPerformerLeaderValue = dealPerformerLeaderValue.split(",")[i];
                        data.put("leaderId", finalDealPerformerLeaderValue);
                        String name = service.id2Name(finalDealPerformerLeaderValue, "tawSystemUserDao");
                        data.put("leaderName", name);
                        sendUserAndRoles.put(data.toString());
                    }

                }
            }

            request.setAttribute("sendUserAndRoles", sendUserAndRoles);
        }
    }

    public String getSheetAttachCode() {
        return null;
    }

    public Map initMap(Map map, List attach, String type)
            throws Exception {
        return null;
    }

    public void showAtomDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        showDetailPageAtom(mapping, form, request, response);
        CommonTaskMain mainObject = (CommonTaskMain) request.getAttribute("sheetMain");
        CommonTaskTask task = (CommonTaskTask) request.getAttribute("task");
        String isAccept = null;
        if (task.getTaskStatus().equals("2"))
            isAccept = "0";
        if (task.getTaskStatus().equals("8"))
            isAccept = "1";
        String asXML = showAtomDetail(mainObject, task, isAccept, request);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(asXML);
    }

    public static String showAtomDetail(CommonTaskMain mainObject, ITask task, String isAccept, HttpServletRequest request)
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
        title9.setText("受理时限");
        name9.setText(StaticMethod.date2String(mainObject.getSheetAcceptLimit()));
        Element attribute10 = attributes.addElement("attribute");
        Element title10 = attribute10.addElement("title");
        Element name10 = attribute10.addElement("name");
        title10.setText("处理时限");
        name10.setText(StaticMethod.date2String(mainObject.getSheetCompleteLimit()));
        Element attribute11 = attributes.addElement("attribute");
        Element title11 = attribute11.addElement("title");
        Element name11 = attribute11.addElement("name");
        title11.setText("网络分类(一级)");
        String netSortOne = service.id2Name(mainObject.getMainNetSort1(), "ItawSystemDictTypeDao");
        name11.setText(netSortOne);
        Element attribute12 = attributes.addElement("attribute");
        Element title12 = attribute12.addElement("title");
        Element name12 = attribute12.addElement("name");
        title12.setText("网络分类(二级)");
        String netSortTwo = service.id2Name(mainObject.getMainNetSort2(), "ItawSystemDictTypeDao");
        name12.setText(netSortTwo);
        Element attribute13 = attributes.addElement("attribute");
        Element title13 = attribute13.addElement("title");
        Element name13 = attribute13.addElement("name");
        title13.setText("网络分类(三级)");
        String netSortThree = service.id2Name(mainObject.getMainNetSort3(), "ItawSystemDictTypeDao");
        name13.setText(netSortThree);
        Element attribute14 = attributes.addElement("attribute");
        Element title14 = attribute14.addElement("title");
        Element name14 = attribute14.addElement("name");
        title14.setText("网络分类(三级)");
        String mainTaskType = service.id2Name(mainObject.getMainTaskType(), "ItawSystemDictTypeDao");
        name14.setText(mainTaskType);
        Element attribute15 = attributes.addElement("attribute");
        Element title15 = attribute15.addElement("title");
        Element name15 = attribute15.addElement("name");
        title15.setText("任务描述");
        name15.setText(StaticMethod.null2String(mainObject.getMainTaskDescription()));
        Element attribute16 = attributes.addElement("attribute");
        Element title16 = attribute16.addElement("title");
        Element name16 = attribute16.addElement("name");
        title16.setText("备注");
        name16.setText(StaticMethod.null2String(mainObject.getMainRemark()));
        Element attribute17 = attributes.addElement("attribute");
        Element title17 = attribute17.addElement("title");
        Element name17 = attribute17.addElement("name");
        title17.setText("附件");
        ITawCommonsAccessoriesManager mgrr = (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
        try {
            String str = mainObject.getSheetAccessories();
            List list = mgrr.getAllFileById(mainObject.getSheetAccessories());
            String url = "";
            for (int i = 0; i < list.size(); i++) {
                TawCommonsAccessories tawCommonsAccessories = (TawCommonsAccessories) list.get(i);
                url = url + "<a href='http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/eoms35/accessories/tawCommonsAccessoriesConfigs.do?method=download&type=interface&userName=" + request.getParameter("userName") + "&id=" + tawCommonsAccessories.getId() + "'>" + tawCommonsAccessories.getAccessoriesCnName() + "</a><br>";
            }

            name17.setText(url);
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
        value10.setText("com.boco.eoms.sheet.commontask.model.CommonTaskMain");
        Element parameter11 = parameters.addElement("hidden");
        Element id11 = parameter11.addElement("id");
        Element value11 = parameter11.addElement("value");
        id11.setText("linkClassName");
        value11.setText("com.boco.eoms.sheet.commontask.model.CommonTaskLink");
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
}
