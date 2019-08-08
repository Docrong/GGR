// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 
// Source File Name:   ComplaintDubanAction.java

package com.boco.eoms.sheet.complaintDuban.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.service.impl.BusinessFlowServiceImpl;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanLink;
import com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanMain;
import com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanTask;
import com.boco.eoms.sheet.complaintDuban.service.IComplaintDubanLinkManager;
import com.boco.eoms.sheet.complaintDuban.service.IComplaintDubanMainManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ComplaintDubanAction extends SheetAction {

    public ComplaintDubanAction() {
    }

    public ActionForward showDrawing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("draw");
    }

    public ActionForward showPic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("pic");
    }

    public ActionForward showKPI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("kpi");
    }

    public ActionForward showNewSheetPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String linkcomplaintsolvedate = StaticMethod.nullObject2String(request.getParameter("linkcomplaintsolvedate"));
        System.out.println(linkcomplaintsolvedate);
        request.setAttribute("linkcomplaintsolvedate", linkcomplaintsolvedate);
        return super.showNewSheetPage(mapping, form, request, response);
    }

    public ActionForward showNewInputSheetPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String linkcomplaintsolvedate = StaticMethod.null2String(request.getParameter("linkcomplaintsolvedate"));
        request.setAttribute("linkcomplaintsolvedate", linkcomplaintsolvedate);
        return super.showNewInputSheetPage(mapping, form, request, response);
    }

    /**
     * 重写处理回复通过方法
     * add by mashuting 20120411
     */
    public ActionForward performDealReplyAccept(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("in complaintduban action===========");
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performDealReplyAccept(mapping, form, request, response);//调用处理回复通过方法

        IComplaintDubanLinkManager linkMgr = (IComplaintDubanLinkManager) ApplicationContextHolder.getInstance().getBean("iComplaintDubanLinkManager");
        IComplaintDubanMainManager mainMgr = (IComplaintDubanMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintDubanMainManager");
        ComplaintDubanLink link = new ComplaintDubanLink();
        ComplaintDubanLink userLink = null;
        ComplaintDubanMain main = null;
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String sessionUser = sessionform.getUserid();        // 登录人
        HashMap sheetMap = new HashMap();
        String mainid = StaticMethod.nullObject2String(request.getParameter("mainId"));        //得到工单main表id
        String linkName = StaticMethod.nullObject2String(request.getParameter("linkClassName"));
        main = (ComplaintDubanMain) mainMgr.getSingleMainPO(mainid);
        sheetMap.put("main", main);
        String getUserCondition = " mainId='" + mainid + "' and operateType='61' order by operateTime";        //得到确认受理的人员
        List userList = linkMgr.getLinksBycondition(getUserCondition, linkName);
        if (userList != null && userList.size() > 0) {
            userLink = (ComplaintDubanLink) userList.get(0);
        }
        if (userLink != null && !"".equals(userLink)) {
            String userId = userLink.getOperateUserId();
            System.out.println("lizhi:userId=" + userId);
            //if(sessionUser.equals(userId)){			//如果新建后的确认受理和当前登录人相同，则自动归档
            String condition = " mainId='" + mainid + "' and operateType='11' order by operateTime desc";        //得到分派回复的记录，并赋给审批通过那条记录
            List linkList = linkMgr.getLinksBycondition(condition, linkName);
            if (linkList != null && linkList.size() > 0) {
                link = (ComplaintDubanLink) linkList.get(0);
                String solveCond = "";
                //String solveCondYes = StaticMethod.nullObject2String(XmlManage.getFile("/config/ComplaintDuban-info.xml").getProperty("link.solveCondYes"));		//解决情况(已解决)
                solveCond = StaticMethod.nullObject2String(link.getSolveCond());//得到解决情况
                System.out.println("lizhi:solveCond=" + solveCond);
                if (solveCond.equals("101011001")) {        //如果解决情况为已解决，自动归档
                    System.out.println("to makeHold method==========");
                    makeHold(mapping, form, request, response, link, userLink, baseSheet);//调用自动归档方法
                }
            }
            //}
        }
        return mapping.findForward("success");
    }

    /**
     * 自动归档方法
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param link
     * @param userLink
     * @param baseSheet
     * @throws Exception add by mashuting 20120411
     */
    public void makeHold(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ComplaintDubanLink link, ComplaintDubanLink userLink, IBaseSheet baseSheet) throws Exception {
        BusinessFlowServiceImpl businessFlowService = (BusinessFlowServiceImpl) ApplicationContextHolder.getInstance().getBean("businessFlowService");

        System.out.println("in makeHold method==========");
        ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String sessionUser = sessionform.getUserid();        // 登录人
        String sessionDept = sessionform.getDeptid();
        String sessionMobile = sessionform.getContactMobile();
        TawSystemUser user = userMgr.getUserByuserid(sessionUser);
        HashMap sessionMap = new HashMap();
        sessionMap.put("userId", sessionUser);
        sessionMap.put("password", user.getPassword());

        //生成由审批到待归档的一条link
        System.out.println("make linkObj=============");
        ComplaintDubanLink linkObj = (ComplaintDubanLink) (baseSheet.getLinkService().getLinkObject().getClass()).newInstance();
        linkObj.setId(UUIDHexGenerator.getInstance().getID());
        linkObj.setMainId(link.getMainId());
        linkObj.setAcceptTime(link.getNodeAcceptLimit());
        linkObj.setCompleteTime(link.getNodeCompleteLimit());
        linkObj.setOperateType(new Integer(200));
        linkObj.setActiveTemplateId("Assessor");
        linkObj.setOperateTime(new Date());
        linkObj.setOperateUserId(sessionUser);
        linkObj.setOperateDeptId(sessionDept);
        linkObj.setOperaterContact(sessionMobile);
        linkObj.setPreLinkId(userLink.getId());
        linkObj.setAiid(userLink.getAiid());
        linkObj.setPiid(link.getPiid());
        linkObj.setCorrelationKey(link.getCorrelationKey());
        linkObj.setDelayCase(link.getDelayCase());
        linkObj.setDelayTime(link.getDelayTime());
        linkObj.setApplyTime(link.getApplyTime());
        linkObj.setApproveTime(link.getApproveTime());
        linkObj.setSolveCond(link.getSolveCond());
        linkObj.setSolveTool(link.getSolveTool());
        linkObj.setProjectName(link.getProjectName());
        linkObj.setApproveResult(link.getApproveResult());
        linkObj.setApproveOpinion(link.getApproveOpinion());
        linkObj.setLinkTdComplaint(link.getLinkTdComplaint());
        linkObj.setRemark(link.getRemark());
        baseSheet.getLinkService().addLink(linkObj);

        Date nowDate = new Date();
        String tkid = "_TKI:" + UUIDHexGenerator.getInstance().getID();
        //生成归档link
        ComplaintDubanLink linkHoldObj = (ComplaintDubanLink) (baseSheet.getLinkService().getLinkObject().getClass()).newInstance();
        linkHoldObj.setId(UUIDHexGenerator.getInstance().getID());
        linkHoldObj.setMainId(link.getMainId());
        linkHoldObj.setOperateType(new Integer(18));
        linkHoldObj.setActiveTemplateId("HoldTask");
        linkHoldObj.setOperateTime(new Date(nowDate.getTime() + 2000));
        linkHoldObj.setOperateUserId(sessionUser);
        linkHoldObj.setOperateDeptId(sessionDept);
        linkHoldObj.setOperaterContact(sessionMobile);
        linkHoldObj.setPreLinkId(linkObj.getId());
        linkHoldObj.setPiid(link.getPiid());
        linkHoldObj.setAiid(tkid);
        linkHoldObj.setCorrelationKey(link.getCorrelationKey());
        baseSheet.getLinkService().addLink(linkHoldObj);

        HashMap columnMap = baseSheet.getInterfaceObjMap(mapping, form, request, response);
        Map map = request.getParameterMap();
        String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
        Map serializableMap = SheetUtils.serializableParemeterMap(map);
        Iterator it = serializableMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        while (it.hasNext()) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            if (taskId.equals("")) {
                Object obj = tempMap.get("aiid");
                if (obj.getClass().isArray()) {
                    Object[] obja = (Object[]) obj;
                    obj = obja[0];
                }
                taskId = StaticMethod.nullObject2String(obj);
            }
            HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
            WpsMap.putAll(tempWpsMap);
        }
        HashMap operateMap = (HashMap) WpsMap.get("operate");
        operateMap.put("phaseId", "Over");
        WpsMap.put("operate", operateMap);

        BocoLog.info(this, "====makeHold====");
        Map mainMap = (HashMap) WpsMap.get("main");
        Map tmpLinkMap = (HashMap) WpsMap.get("link");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        Map linkMap = new HashMap();
        linkMap.putAll(tmpLinkMap);

        Iterator names = linkMap.keySet().iterator();
        while (names.hasNext()) {
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

        //生成接入室的link记录
        ComplaintDubanLink link1 = (ComplaintDubanLink) baseSheet.getLinkService().getLinkObject().getClass().newInstance();
        SheetBeanUtils.populateMap2Bean(link1, linkMap);

        //生成归档的Task记录
        ComplaintDubanTask task = (ComplaintDubanTask) baseSheet.getTaskService().getTaskModelObject();

        String linkId = UUIDHexGenerator.getInstance().getID();
        task.setId(tkid);
        task.setCreateTime(nowDate);
        task.setTaskStatus(Constants.TASK_STATUS_FINISHED);
        task.setProcessId((String) mainMap.get("piid"));
        task.setSheetKey((String) mainMap.get("id"));
        task.setSheetId((String) mainMap.get("sheetId"));
        task.setTitle((String) mainMap.get("title"));
        task.setAcceptTimeLimit(link1.getNodeAcceptLimit());
        task.setCompleteTimeLimit(link1.getNodeCompleteLimit());
        task.setPreLinkId(link1.getId());
        task.setCurrentLinkId(linkId);
        task.setIfWaitForSubTask("false");
        task.setCreateDay(calendar.get(calendar.DATE));
        task.setCreateMonth(calendar.get(calendar.MONTH) + 1);
        task.setCreateYear(calendar.get(calendar.YEAR));
        task.setTaskDisplayName("待归档");
        task.setTaskName("HoldTask");
        task.setOperateRoleId(link1.getOperateRoleId());
        task.setTaskOwner(link1.getOperateUserId());
        task.setOperateType("user");
        task.setFlowName("ComplaintDubanProcesses");
        task.setParentTaskId(task.getId());
        task.setSendTime((Date) mainMap.get("sendTime"));
        System.out.println("in complaintdubanmethod=====");
        baseSheet.getTaskService().addTask(task);

        tmpLinkMap.put("id", linkId);
        tmpLinkMap.put("mainId", (String) mainMap.get("id"));
        tmpLinkMap.put("operateType", "18");
        tmpLinkMap.put("activeTemplateId", "HoldTask");
        tmpLinkMap.put("operateTime", new Date(nowDate.getTime() + 2000));
        tmpLinkMap.put("preLinkId", linkObj.getId());
        tmpLinkMap.put("aiid", tkid);
        tmpLinkMap.put("nodeCompleteLimit", mainMap.get("sheetCompleteLimit"));
        tmpLinkMap.put("nodeAccessories", mainMap.get("firstNodeAccessories"));

        mainMap.put("status", Constants.SHEET_HOLD);// 归档意见
        WpsMap.put("main", mainMap);
        WpsMap.put("link", tmpLinkMap);

        businessFlowService.completeHumanTask(taskId, WpsMap, sessionMap);
    }
}
