// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TechniqueSupportHbMethod.java

package com.boco.eoms.sheet.techniquesupporthb.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbLink;
import com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbMain;
import com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbTask;
import com.boco.eoms.sheet.techniquesupporthb.service.ITechniqueSupportHbFlowManager;
import com.boco.eoms.sheet.techniquesupporthb.service.ITechniqueSupportHbLinkManager;
import com.boco.eoms.sheet.techniquesupporthb.service.ITechniqueSupportHbMainManager;
import com.boco.eoms.sheet.techniquesupporthb.service.ITechniqueSupportHbTaskManager;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.boco.eoms.sheet.techniquesupporthb.db.TechniqueSupportHbDB;

public class TechniqueSupportHbMethod extends BaseSheet {

    public TechniqueSupportHbMethod() {
    }

    public String getPageColumnName() {
        return super.getPageColumnName() + "gatherPerformer@java.lang.String,gatherPerformerLeader@java.lang.String," + "gatherPerformerType@java.lang.String,gatherPhaseId@java.lang.String,";
    }

    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        HashMap sheetMap = new HashMap();
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
        BaseMain main = (BaseMain) getMainService().getMainObject().getClass().newInstance();
        if (!sheetKey.equals(""))
            sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        if (!sheetKey.equals(""))
            main = getMainService().getSingleMainPO(sheetKey);
        sheetMap.put("main", main);
        sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
        sheetMap.put("operate", getPageColumnName());
        hashMap.put("selfSheet", sheetMap);
        return hashMap;
    }

    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
        HashMap sheetMap = getFlowEngineMap();
        Map operate = (HashMap) sheetMap.get("operate");
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());
        String dealperformers[] = StaticMethod.nullObject2String(operate.get("dealPerformer")).split(",");
        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);
        }
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
            sheetMap.put("operate", operate);
        }
        setFlowEngineMap(sheetMap);
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
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
        Object obj = request.getAttribute("task");
        if (obj != null) {
            ITask taskModel = (ITask) obj;
            Map objectMap = new HashMap();
            objectMap = SheetBeanUtils.bean2Map(taskModel);
            ITechniqueSupportHbTaskManager itechniquesupporthbTaskManager = (ITechniqueSupportHbTaskManager) getTaskService();
            Integer tempCount = itechniquesupporthbTaskManager.getCountOfBrother(sheetKey, StaticMethod.nullObject2String(objectMap.get("parentLevelId")));
            if (tempCount.intValue() > 1) {
                request.setAttribute("hasbrother", "hasbrother");
                System.out.println("=========hasbrother========");
            }
        }
    }

    public void performClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.performClaim(mapping, form, request, response);
        String activeTemplateId = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        if (activeTemplateId.equals("ExcuteHumTask")) {
            System.out.println("ȷ������");
            HashMap sessionMap = new HashMap();
            TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
            sessionMap.put("userId", sessionform.getUserid());
            sessionMap.put("password", sessionform.getPassword());
            String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
            ITask task = getTaskService().getSinglePO(taskId);
            ITechniqueSupportHbFlowManager service = (ITechniqueSupportHbFlowManager) getBusinessFlowService();
            service.setProcessInstanceCustomProperty(task.getProcessId(), "ifAccepted", "true", sessionMap);
        }
    }

    public void showAtomDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        showDetailPageAtom(mapping, form, request, response);
        TechniqueSupportHbMain mainObject = (TechniqueSupportHbMain) request.getAttribute("sheetMain");
        TechniqueSupportHbTask task = (TechniqueSupportHbTask) request.getAttribute("task");
        String isAccept = null;
        if (task.getTaskStatus().equals("2"))
            isAccept = "0";
        if (task.getTaskStatus().equals("8"))
            isAccept = "1";
        String asXML = showAtomDetail(mainObject, task, isAccept, request);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(asXML);
    }

    public static String showAtomDetail(TechniqueSupportHbMain mainObject, ITask task, String isAccept, HttpServletRequest request)
            throws DictServiceException {
        return null;
    }

    public String getProcessTemplateName() {
        return "techniquesupporthbMainFlowProcess";
    }

    public String getSheetAttachCode() {
        return "techniquesupporthb";
    }

    public Map initMap(Map map, List attach, String type)
            throws Exception {
        String configPath = "config/techniquesupporthb-interface-config.xml";
        map = loadDefaultMap(map, configPath, type);
        if (type.equals("newWorkSheet")) {
            String NeTypeCode = StaticMethod.nullObject2String(map.get("netType"));
            String mainNetSortThree = "";
            if (NeTypeCode.length() > 0) {
                String rootId = XmlManage.getFile("/config/techniquesupporthb-interface-config.xml").getProperty("base.rootNeTypeId");
                ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
                mainNetSortThree = dictMgr.getDictIdByDictCode(rootId, NeTypeCode);
                if (mainNetSortThree == null || mainNetSortThree.length() == 0)
                    System.out.println("û���ҵ�ӳ����������");
                else
                    try {
                        System.out.println("mainNetSortThree=" + mainNetSortThree);
                        TawSystemDictType dict3 = dictMgr.getDictByDictId(mainNetSortThree);
                        if (dict3 != null) {
                            map.put("mainNetSort3", mainNetSortThree);
                            String pId = dict3.getParentDictId();
                            TawSystemDictType dict2 = dictMgr.getDictByDictId(pId);
                            if (dict2 != null) {
                                map.put("mainNetSort2", dict2.getDictId());
                                pId = dict2.getParentDictId();
                                if (!pId.equals("-1")) {
                                    TawSystemDictType dict1 = dictMgr.getDictByDictId(pId);
                                    if (dict1 != null)
                                        map.put("mainNetSort1", dict1.getDictCode());
                                }
                            }
                        } else {
                            System.out.println("dict3 is null");
                        }
                    } catch (Exception err) {
                        System.out.println("û���ҵ�ӳ����������");
                    }
            }
        }
        return map;
    }

    public void showDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showDetailPage(mapping, form, request, response);
        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
        if (!preLinkId.equals(""))
            request.setAttribute("preLink", getLinkService().getSingleLinkPO(preLinkId));
    }

    public void performDeal(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("taskName"));
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        String mainId = StaticMethod.null2String(request.getParameter("mainId"));
        TechniqueSupportHbMain userMain = null;
        TechniqueSupportHbLink userLink = null;
        String sheetid = "";
        String workTime = "";
        String timeleader = "";
        String sql = "";
        Connection conn = null;
        PreparedStatement ps = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date()).toString();
        System.out.println("lizhi:taskName=" + taskName);
        System.out.println("lizhi:operateType=" + operateType);
        System.out.println("lizhi:mainId=" + mainId);
        if ("ImplValidTask".equals(taskName) && "921".equals(operateType)) {
            String linkName = StaticMethod.nullObject2String(request.getParameter("linkClassName"));
            System.out.println("lizhi:linkName=" + linkName);
            String getUserCondition = " mainId='" + mainId + "' and operateType='911' order by operateTime desc";
            ITechniqueSupportHbMainManager mainMgr = (ITechniqueSupportHbMainManager) ApplicationContextHolder.getInstance().getBean("iTechniqueSupportHbMainManager");
            ITechniqueSupportHbLinkManager linkMgr = (ITechniqueSupportHbLinkManager) ApplicationContextHolder.getInstance().getBean("iTechniqueSupportHbLinkManager");
            userMain = (TechniqueSupportHbMain) mainMgr.getSingleMainPO(mainId);
            sheetid = StaticMethod.nullObject2String(userMain.getSheetId());
            System.out.println("lizhi:sheetid=" + sheetid);
            List userList = linkMgr.getLinksBycondition(getUserCondition, linkName);
            if (userList != null && userList.size() > 0) {
                userLink = (TechniqueSupportHbLink) userList.get(0);
            }
            workTime = StaticMethod.nullObject2String(userLink.getWorkTime());
            timeleader = StaticMethod.nullObject2String(userLink.getOperateUserId());
            System.out.println("lizhi:workTime=" + workTime);
            System.out.println("lizhi:timeleader=" + timeleader);
            if (!"".equals(workTime)) {
                sql = "insert into KM_EXPERT_TIME (id,worktime,time_leader,sheetid,operatetime) values (?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";
                try {
                    conn = TechniqueSupportHbDB.getConnection();
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, UUIDHexGenerator.getInstance().getID());
                    ps.setString(2, workTime);
                    ps.setString(3, timeleader);
                    ps.setString(4, sheetid);
                    ps.setString(5, time);
                    ps.executeUpdate();
                } catch (RuntimeException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        super.performDeal(mapping, form, request, response);
    }
}
