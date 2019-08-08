// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AgentMaintenanceAction.java

package com.boco.eoms.sheet.agentmaintenance.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.sheet.agentmaintenance.model.*;
import com.boco.eoms.sheet.agentmaintenance.service.*;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commontask.model.CommonTaskMain;
import com.boco.eoms.sheet.commontask.service.ICommonTaskMainManager;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.*;
import org.springframework.web.bind.RequestUtils;

public class AgentMaintenanceAction extends SheetAction {

    public AgentMaintenanceAction() {
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
        String type = StaticMethod.null2String(request.getParameter("type1"));
        String id = StaticMethod.null2String(request.getParameter("parentSheetId"));
        request.setAttribute("type", type);
        request.setAttribute("sheetid", id);
        return super.showNewSheetPage(mapping, form, request, response);
    }

    public ActionForward showNewInputSheetPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserMadeManager mgr = (UserMadeManager) getBean("UserMadeManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String user_id = sessionform.getUserid();
        Map map = mgr.getObjectByUser(user_id);
        int total = Integer.parseInt(map.get("total").toString());
        UserMade userMade = new UserMade();
        request.setAttribute("total", new Integer(total));
        if (total > 0) {
            List list = (List) map.get("result");
            userMade = (UserMade) list.get(0);
            request.setAttribute("userMade", userMade);
        }
        String type = StaticMethod.null2String(request.getParameter("type"));
        String id = StaticMethod.null2String(request.getParameter("sheetid"));
        String sheetAcceptLimit = "";
        String sheetCompleteLimit = "";
        String toDeptId = "";
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        String taskName = StaticMethod.null2String(request.getParameter("parentPhaseName"));
        System.out.println("lizhi:taskName=" + taskName);
        if (type != null && type.equals("commonfault")) {
            ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
            CommonFaultMain commonfaultMain = (CommonFaultMain) commonFaultMainManager.loadSinglePO(id);
            request.setAttribute("commonfaultMain", commonfaultMain);
            request.setAttribute("accessories", StaticMethod.null2String(commonfaultMain.getSheetAccessories()));
            calendar.add(11, 1);
            sheetAcceptLimit = StaticMethod.date2String(calendar.getTime());
            calendar.clear();
            Date maincompletelimitt2 = commonfaultMain.getMainCompleteLimitT2();
            calendar.setTime(maincompletelimitt2);
            calendar.add(11, -1);
            sheetCompleteLimit = StaticMethod.date2String(calendar.getTime());
            calendar.clear();
            toDeptId = StaticMethod.nullObject2String(commonfaultMain.getToDeptId());
        }
        if (type != null && type.equals("commontask")) {
            ICommonTaskMainManager commonTaskMainManager = (ICommonTaskMainManager) getBean("iCommonTaskMainManager");
            CommonTaskMain commonTaskMain = (CommonTaskMain) commonTaskMainManager.getSingleMainPO(id);
            calendar.add(11, 1);
            sheetAcceptLimit = StaticMethod.date2String(calendar.getTime());
            calendar.clear();
            Date completelimit = commonTaskMain.getSheetCompleteLimit();
            calendar.setTime(completelimit);
            calendar.add(11, -1);
            sheetCompleteLimit = StaticMethod.date2String(calendar.getTime());
            calendar.clear();
            toDeptId = StaticMethod.nullObject2String(commonTaskMain.getToDeptId());
            request.setAttribute("commontaskMain", commonTaskMain);
            request.setAttribute("accessories", StaticMethod.null2String(commonTaskMain.getSheetAccessories()));
        }
        if (type != null && type.equals("complaint")) {
            IComplaintMainManager complaintMainManager = (IComplaintMainManager) getBean("iComplaintMainManager");
            ComplaintMain complaintMain = (ComplaintMain) complaintMainManager.loadSinglePO(id);
            calendar.add(11, 1);
            sheetAcceptLimit = StaticMethod.date2String(calendar.getTime());
            calendar.clear();
            if ("FirstExcuteHumTask".equals(taskName)) {
                Date maincompletelimitt1 = complaintMain.getMainCompleteLimitT1();
                calendar.setTime(maincompletelimitt1);
                calendar.add(11, -1);
                sheetCompleteLimit = StaticMethod.date2String(calendar.getTime());
                calendar.clear();
            } else if ("SecondExcuteHumTask".equals(taskName)) {
                Date maincompletelimitt2 = complaintMain.getMainCompleteLimitT2();
                calendar.setTime(maincompletelimitt2);
                calendar.add(11, -1);
                sheetCompleteLimit = StaticMethod.date2String(calendar.getTime());
                calendar.clear();
            } else {
                sheetCompleteLimit = StaticMethod.date2String(complaintMain.getSheetCompleteLimit());
            }
            toDeptId = StaticMethod.nullObject2String(complaintMain.getToDeptId());
            request.setAttribute("complaintMain", complaintMain);
            request.setAttribute("accessories", StaticMethod.null2String(complaintMain.getSheetAccessories()));
        }
        request.setAttribute("sourceId", id);
        request.setAttribute("type", type);
        System.out.println("======todeptid====" + toDeptId);
        request.setAttribute("toDeptId", toDeptId);
        request.setAttribute("sheetAcceptLimit", sheetAcceptLimit);
        request.setAttribute("sheetCompleteLimit", sheetCompleteLimit);
        return super.showNewInputSheetPage(mapping, form, request, response);
    }

    public ActionForward performAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performAdd(mapping, form, request, response);
        String type = StaticMethod.null2String(request.getParameter("type"));
        request.setAttribute("type", type);
        return mapping.findForward("success");
    }

    public ActionForward searchContent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IAgentMaintenanceLinkManager mgr = (IAgentMaintenanceLinkManager) getBean("iAgentMaintenanceLinkManager");
        IAgentMaintenanceMainManager mmgr = (IAgentMaintenanceMainManager) getBean("iAgentMaintenanceMainManager");
        String type = StaticMethod.null2String(request.getParameter("type1"));
        String sourceId = StaticMethod.null2String(request.getParameter("sheetid"));
        String operatedeptid = StaticMethod.null2String(request.getParameter("operatedeptid"));
        AgentMaintenanceMain agentMaintenanceMain = (AgentMaintenanceMain) mmgr.getMainBySourceId(sourceId, operatedeptid);
        if (agentMaintenanceMain == null)
            agentMaintenanceMain = new AgentMaintenanceMain();
        String mainSheetState = StaticMethod.nullObject2String(agentMaintenanceMain.getMainSheetState());
        String mainStatus = StaticMethod.nullObject2String(agentMaintenanceMain.getStatus());
        JSONArray json = new JSONArray();
        JSONObject jitem = new JSONObject();
        AgentMaintenanceLink agentMaintenanceLink = new AgentMaintenanceLink();
        Map map = mgr.getLastLinkBeforeHold(sourceId, type, operatedeptid);
        int total = Integer.parseInt(map.get("total").toString());
        int dwTotal = Integer.parseInt(map.get("dwTotal").toString());
        List list = (List) map.get("result");
        jitem.put("total", total);
        jitem.put("dwTotal", dwTotal);
        jitem.put("mainSheetState", mainSheetState);
        jitem.put("mainStatus", mainStatus);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (total > 0) {
            agentMaintenanceLink = (AgentMaintenanceLink) list.get(0);
            String nodeAccessories = StaticMethod.nullObject2String(agentMaintenanceLink.getNodeAccessories());
            jitem.put("nodeAccessories", nodeAccessories);
            System.out.println("lizhi:nodeAccessories=" + nodeAccessories);
            if (type.equals("commonfault")) {
                jitem.put("linkFaultIfGreatFault", StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultIfGreatFault()));
                jitem.put("linkFaultDealResult", StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultDealResult()));
                jitem.put("linkFaultReasonSort", StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultReasonSort()));
                jitem.put("linkFaultReasonSubsection", StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultReasonSubsection()));
                jitem.put("linkFaultIfExcuteNetChange", StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultIfExcuteNetChange()));
                jitem.put("linkFaultIfFinallySolveProject", StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultIfFinallySolveProject()));
                jitem.put("linkFaultIfAddCaseDataBase", StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultIfAddCaseDataBase()));
                jitem.put("linkFaultAffectTimeLength", StaticMethod.null2String(agentMaintenanceLink.getLinkFaultAffectTimeLength()));
                if (agentMaintenanceLink.getLinkFaultAvoidTime() == null)
                    jitem.put("linkFaultAvoidTime", "");
                else
                    jitem.put("linkFaultAvoidTime", sdf.format(agentMaintenanceLink.getLinkFaultAvoidTime()));
                if (agentMaintenanceLink.getLinkFaultOperRenewTime() == null)
                    jitem.put("linkFaultOperRenewTime", "");
                else
                    jitem.put("linkFaultOperRenewTime", sdf.format(agentMaintenanceLink.getLinkFaultOperRenewTime()));
                jitem.put("linkFaultDealStep", StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultDealStep()));
                if (agentMaintenanceLink.getCommonFaultdealTime() == null)
                    jitem.put("commonFaultdealTime", "");
                else
                    jitem.put("commonFaultdealTime", sdf.format(agentMaintenanceLink.getCommonFaultdealTime()));
                jitem.put("commonFaultTreatment", StaticMethod.null2String(agentMaintenanceLink.getCommonFaultTreatment()));
                jitem.put("commonFaultdealdesc", StaticMethod.null2String(agentMaintenanceLink.getCommonFaultdealdesc()));
                jitem.put("commonLinkDealdesc", StaticMethod.null2String(agentMaintenanceLink.getCommonLinkDealdesc()));
                jitem.put("selectStep", StaticMethod.null2String(agentMaintenanceLink.getSelectStep()));
                ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("id2nameService");
                jitem.put("linkFaultReasonSubsectionTwo", StaticMethod.nullObject2String(agentMaintenanceLink.getYuliu1()));
                String linkFaultReasonSortName = service.id2Name(StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultReasonSort()), "ItawSystemDictTypeDao");
                String linkFaultReasonSubsectionTwoName = service.id2Name(StaticMethod.nullObject2String(agentMaintenanceLink.getYuliu1()), "ItawSystemDictTypeDao");
                String linkFaultReasonSubsectionName = service.id2Name(StaticMethod.nullObject2String(agentMaintenanceLink.getLinkFaultReasonSubsection()), "ItawSystemDictTypeDao");
                jitem.put("linkFaultReasonSortName", linkFaultReasonSortName);
                jitem.put("linkFaultReasonSubsectionName", linkFaultReasonSubsectionName);
                jitem.put("linkFaultReasonSubsectionTwoName", linkFaultReasonSubsectionTwoName);

            }
            if (type.equals("complaint")) {
                jitem.put("linkComContactUser", StaticMethod.null2String(agentMaintenanceLink.getLinkComContactUser()));
                jitem.put("linkComContactPhone", StaticMethod.null2String(agentMaintenanceLink.getLinkComContactPhone()));
                jitem.put("linkComcompProp", StaticMethod.null2String(agentMaintenanceLink.getLinkComcompProp()));
                jitem.put("linkComIsContactUser", StaticMethod.null2String(agentMaintenanceLink.getLinkComIsContactUser()));
                if (agentMaintenanceLink.getLinkComFaultEndTime() == null)
                    jitem.put("linkComFaultEndTime", "");
                else
                    jitem.put("linkComFaultEndTime", sdf.format(agentMaintenanceLink.getLinkComFaultEndTime()));
                jitem.put("linkComdealResult", StaticMethod.null2String(agentMaintenanceLink.getLinkComdealResult()));
                jitem.put("linkComTransmitReason", StaticMethod.null2String(agentMaintenanceLink.getLinkComTransmitReason()));
                jitem.put("linkComLocalDealDesc", StaticMethod.null2String(agentMaintenanceLink.getLinkComLocalDealDesc()));
            }
            if (type.equals("commontask"))
                jitem.put("linkTaskComplete", StaticMethod.null2String(agentMaintenanceLink.getLinkTaskComplete()));
        }
        json.put(jitem);
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(json.toString());
        return null;
    }

    public ActionForward showUserMade(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("useradd");
    }

    public ActionForward saveUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserMadeManager mgr = (UserMadeManager) getBean("UserMadeManager");
        TawSystemUserDao tawSystemUserDao = (TawSystemUserDao) ApplicationContextHolder.getInstance().getBean("tawSystemUserDao");
        TawSystemUser tawSystemUser = new TawSystemUser();
        String user = StaticMethod.null2String(request.getParameter("dealPerformer"));
        String subUser = StaticMethod.null2String(request.getParameter("auditPerformer"));
        String id = StaticMethod.null2String(request.getParameter("id"));
        String toUser = "";
        String toDept = "";
        String subUsers[] = subUser.split(",");
        for (int i = 0; i < subUsers.length; i++) {
            tawSystemUser = tawSystemUserDao.getTawSystemUserByuserid(subUsers[i]);
            if (tawSystemUser.getId() != null && !"".equals(tawSystemUser.getId()))
                toUser = toUser + subUsers[i] + ",";
            else
                toDept = toDept + subUsers[i] + ",";
        }

        UserMade useMade = new UserMade();
        if (id != null && !"".equals(id))
            useMade.setId(id);
        useMade.setUser_id(user);
        if (toUser != null && !"".equals(toUser))
            useMade.setUsertoRole(toUser.substring(0, toUser.length() - 1));
        else
            useMade.setUsertoRole("");
        if (toDept != null && !"".equals(toDept))
            useMade.setUsertoDept(toDept.substring(0, toDept.length() - 1));
        else
            useMade.setUsertoDept("");
        mgr.save(useMade);
        return showUserList(mapping, form, request, response);
    }

    public void checkData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserMadeManager mgr = (UserMadeManager) getBean("UserMadeManager");
        JSONArray json = new JSONArray();
        JSONObject jitem = new JSONObject();
        String user = StaticMethod.null2String(request.getParameter("dealPerformer"));
        Map map = mgr.getObjectByUser(user);
        int total = Integer.parseInt(map.get("total").toString());
        jitem.put("result", total);
        json.put(jitem);
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(json.toString());
    }

    public ActionForward showUserList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserMadeManager mgr = (UserMadeManager) getBean("UserMadeManager");
        List list = mgr.getAllUser();
        request.setAttribute("list", list);
        return mapping.findForward("userlist");
    }

    public ActionForward showModify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserMadeManager mgr = (UserMadeManager) getBean("UserMadeManager");
        JSONArray madeToUser = new JSONArray();
        UserMade userMade = new UserMade();
        String id = StaticMethod.null2String(request.getParameter("id"));
        userMade = mgr.getDataById(id);
        String user_id = userMade.getUser_id();
        String toUser = userMade.getUsertoRole();
        String toDept = userMade.getUsertoDept();
        request.setAttribute("madeUser_id", user_id);
        request.setAttribute("id", id);
        if (toUser != null && !"".equals(toUser)) {
            String toUsers[] = toUser.split(",");
            for (int i = 0; i < toUsers.length; i++) {
                JSONObject data1 = new JSONObject();
                data1.put("id", toUsers[i]);
                data1.put("nodeType", "user");
                data1.put("categoryId", "auditPerformer");
                madeToUser.put(data1.toString());
            }

        }
        if (toDept != null && !"".equals(toDept)) {
            String toDepts[] = toDept.split(",");
            for (int i = 0; i < toDepts.length; i++) {
                JSONObject data2 = new JSONObject();
                data2.put("id", toDepts[i]);
                data2.put("nodeType", "dept");
                data2.put("categoryId", "auditPerformer");
                madeToUser.put(data2.toString());
            }

        }
        request.setAttribute("madeToUser", madeToUser);
        return mapping.findForward("usermodify");
    }

    public ActionForward showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserMadeManager mgr = (UserMadeManager) getBean("UserMadeManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String user_id = sessionform.getUserid();
        Map map = mgr.getObjectByUser(user_id);
        int total = Integer.parseInt(map.get("total").toString());
        UserMade userMade = new UserMade();
        request.setAttribute("total", new Integer(total));
        if (total > 0) {
            List list = (List) map.get("result");
            userMade = (UserMade) list.get(0);
            request.setAttribute("userMade", userMade);
        }
        return super.showInputDealPage(mapping, form, request, response);
    }

    public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserMadeManager mgr = (UserMadeManager) getBean("UserMadeManager");
        String id = StaticMethod.null2String(request.getParameter("id"));
        mgr.remove(id);
        return showUserList(mapping, form, request, response);
    }

    public ActionForward performDeal1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sourceId = StaticMethod.null2String(request.getParameter("id"));
        String operateType = StaticMethod.null2String(request.getParameter("operateType"));
        String operatedeptid = StaticMethod.null2String(request.getParameter("operatedeptid"));
        IAgentMaintenanceMainManager mmgr = (IAgentMaintenanceMainManager) getBean("iAgentMaintenanceMainManager");
        AgentMaintenanceMain agentMaintenanceMain = (AgentMaintenanceMain) mmgr.getMainBySourceId(sourceId, operatedeptid);
        String type = StaticMethod.null2String(agentMaintenanceMain.getType());
        IAgentMaintenanceTaskManager tmgr;
        if (operateType.equals("211")) {
            agentMaintenanceMain.setMainSheetState("0");
            IAgentMaintenanceLinkManager lmgr = (IAgentMaintenanceLinkManager) getBean("iAgentMaintenanceLinkManager");
            tmgr = (IAgentMaintenanceTaskManager) getBean("iagentmaintenanceTaskManager");
            Calendar calendar = Calendar.getInstance();
            calendar.add(13, -10);
            AgentMaintenanceLink agentMaintenanceLink1 = (AgentMaintenanceLink) lmgr.getLinkObject().getClass().newInstance();
            agentMaintenanceLink1.setId(UUIDHexGenerator.getInstance().getID());
            agentMaintenanceLink1.setMainId(StaticMethod.nullObject2String(agentMaintenanceMain.getId()));
            agentMaintenanceLink1.setOperateTime(calendar.getTime());
            agentMaintenanceLink1.setOperateType(new Integer(211));
            agentMaintenanceLink1.setOperateDay(calendar.get(5));
            agentMaintenanceLink1.setOperateMonth(calendar.get(2) + 1);
            agentMaintenanceLink1.setOperateYear(calendar.get(1));
            agentMaintenanceLink1.setOperateUserId(StaticMethod.nullObject2String(agentMaintenanceMain.getSendUserId()));
            agentMaintenanceLink1.setOperateDeptId(StaticMethod.nullObject2String(agentMaintenanceMain.getSendDeptId()));
            agentMaintenanceLink1.setOperateRoleId(StaticMethod.nullObject2String(agentMaintenanceMain.getSendRoleId()));
            agentMaintenanceLink1.setOperaterContact(StaticMethod.nullObject2String(agentMaintenanceMain.getSendContact()));
            agentMaintenanceLink1.setToOrgRoleId("");
            agentMaintenanceLink1.setToOrgType(new Integer(0));
            agentMaintenanceLink1.setAcceptFlag(new Integer(0));
            agentMaintenanceLink1.setCompleteFlag(new Integer(0));
            agentMaintenanceLink1.setActiveTemplateId("AffirmHumTask");

            String linkRevertReason = StaticMethod.nullObject2String(request.getParameter("auditReason"));
            agentMaintenanceLink1.setLinkRevertReason(linkRevertReason);

            lmgr.addLink(agentMaintenanceLink1);
            calendar.add(13, 30);
            AgentMaintenanceLink agentMaintenanceLink = (AgentMaintenanceLink) lmgr.getLinkObject().getClass().newInstance();
            agentMaintenanceLink.setId(UUIDHexGenerator.getInstance().getID());
            agentMaintenanceLink.setMainId(StaticMethod.nullObject2String(agentMaintenanceMain.getId()));
            agentMaintenanceLink.setOperateTime(calendar.getTime());
            agentMaintenanceLink.setOperateType(new Integer(18));
            agentMaintenanceLink.setOperateDay(calendar.get(5));
            agentMaintenanceLink.setOperateMonth(calendar.get(2) + 1);
            agentMaintenanceLink.setOperateYear(calendar.get(1));
            agentMaintenanceLink.setOperateUserId(StaticMethod.nullObject2String(agentMaintenanceMain.getSendUserId()));
            agentMaintenanceLink.setOperateDeptId(StaticMethod.nullObject2String(agentMaintenanceMain.getSendDeptId()));
            agentMaintenanceLink.setOperateRoleId(StaticMethod.nullObject2String(agentMaintenanceMain.getSendRoleId()));
            agentMaintenanceLink.setOperaterContact(StaticMethod.nullObject2String(agentMaintenanceMain.getSendContact()));
            agentMaintenanceLink.setToOrgRoleId("");
            agentMaintenanceLink.setToOrgType(new Integer(0));
            agentMaintenanceLink.setAcceptFlag(new Integer(2));
            agentMaintenanceLink.setCompleteFlag(new Integer(2));
            agentMaintenanceLink.setActiveTemplateId("HoldHumTask");
            lmgr.addLink(agentMaintenanceLink);
            AgentMaintenanceTask agentMaintenanceTask = new AgentMaintenanceTask();
            try {
                agentMaintenanceTask.setId(UUIDHexGenerator.getInstance().getID());
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            agentMaintenanceTask.setTaskName("HoldHumTask");
            agentMaintenanceTask.setTaskDisplayName("待归档");
            agentMaintenanceTask.setFlowName("AgentMaintenanceMainFlowProcess");
            agentMaintenanceTask.setSendTime(new Date());
            agentMaintenanceTask.setSheetKey(StaticMethod.nullObject2String(agentMaintenanceMain.getId()));
            agentMaintenanceTask.setTaskStatus("5");
            agentMaintenanceTask.setSheetId(StaticMethod.nullObject2String(agentMaintenanceMain.getSheetId()));
            agentMaintenanceTask.setTitle(StaticMethod.nullObject2String(agentMaintenanceMain.getTitle()));
            agentMaintenanceTask.setOperateType("subrole");
            agentMaintenanceTask.setCreateTime(new Date());
            agentMaintenanceTask.setCreateYear(calendar.get(1));
            agentMaintenanceTask.setCreateMonth(calendar.get(2) + 1);
            agentMaintenanceTask.setCreateDay(calendar.get(5));
            agentMaintenanceTask.setOperateRoleId(StaticMethod.nullObject2String(agentMaintenanceMain.getSendRoleId()));
            agentMaintenanceTask.setTaskOwner(StaticMethod.nullObject2String(agentMaintenanceMain.getSendUserId()));
            agentMaintenanceTask.setOperateType("subrole");
            agentMaintenanceTask.setIfWaitForSubTask("false");
            agentMaintenanceTask.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
            agentMaintenanceTask.setPreLinkId(agentMaintenanceLink.getId());
            tmgr.addTask(agentMaintenanceTask);
            agentMaintenanceMain.setStatus(new Integer(1));
        } else if (operateType.equals("212")) {
            agentMaintenanceMain.setMainSheetState("1");
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
                operatedeptid = "," + operatedeptid + ",";
                if (revert.indexOf(operatedeptid) != -1)
                    revert = revert.replace(operatedeptid, ",");
                commonTaskMain.setRevert(revert);
                commonTaskMainManager.saveOrUpdateMain(commonTaskMain);
            }
        }
        tmgr = (IAgentMaintenanceTaskManager) getBean("iagentmaintenanceTaskManager");
        List tasks = tmgr.getTasksByCondition(" sheetKey='" + agentMaintenanceMain.getId() + "' ");
        AgentMaintenanceTask agentMaintenanceTask = null;
        String aiid = "";
        if (tasks.size() != 0) {
            agentMaintenanceTask = (AgentMaintenanceTask) tasks.get(tasks.size() - 1);
            aiid = StaticMethod.nullObject2String(agentMaintenanceTask.getId());
        }
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        Map map = new HashMap();
        Map sheetMap = new HashMap();
        Map columnMap = new HashMap();
        String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
        AgentMaintenanceLink link = (AgentMaintenanceLink) baseSheet.getLinkService().getLinkObject();
        link.setOperateType(new Integer(Integer.parseInt(operateType)));
        if (!"".equals(aiid) && aiid != null)
            link.setAiid(aiid);
//		String linkRevertReason = StaticMethod.nullObject2String(request.getParameter("auditReason"));
//		if (operateType.equals("211") && !"".equals(linkRevertReason))
//		{
//			link.setLinkRevertReason(linkRevertReason);
//			System.out.println("linkRevertReason-------" + linkRevertReason);
//		}
        String linkNoAuditReason = StaticMethod.nullObject2String(request.getParameter("noAuditReason"));
        if (operateType.equals("212") && !"".equals(linkNoAuditReason)) {
            link.setLinkNoAuditReason(linkNoAuditReason);
            System.out.println("linkNoAuditReason-------" + linkNoAuditReason);
        }
        sheetMap.put("main", agentMaintenanceMain);
        sheetMap.put("link", link);
        sheetMap.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
        columnMap.put("selfSheet", sheetMap);
        map.put("phaseId", "DealTask");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        map.put("operateUserId", userId);
        map.put("sheetId", agentMaintenanceMain.getSheetId());
        map.put("correlationKey", agentMaintenanceMain.getCorrelationKey());
        map.put("mainId", agentMaintenanceMain.getId());
        map.put("beanId", "iDiffprobSolveMainManager");
        map.put("mainClassName", "com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceMain");
        map.put("linkClassName", "com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceLink");
        map.put("phaseId", phaseId);
        String sheetKey = StaticMethod.nullObject2String(agentMaintenanceMain.getId());
        WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
        sm.dealSheet(sheetKey, map, columnMap, userId, baseSheet.getTaskService());
        return null;
    }

    public ActionForward ifAllReply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONArray json = new JSONArray();
        JSONObject jitem = new JSONObject();

        int receivecount = 0;
        int excutehumtaskcount = 0;
        String sourceId = StaticMethod.null2String(request.getParameter("id"));
        String operatedeptid = StaticMethod.null2String(request.getParameter("operatedeptid"));
        System.out.println("lizhi:sourceId=" + sourceId + "operatedeptid=" + operatedeptid);
        IAgentMaintenanceMainManager mmgr = (IAgentMaintenanceMainManager) getBean("iAgentMaintenanceMainManager");
        AgentMaintenanceMain agentMaintenanceMain = (AgentMaintenanceMain) mmgr.getMainBySourceId(sourceId, operatedeptid);
        String mainid = StaticMethod.nullObject2String(agentMaintenanceMain.getId());
        System.out.println("lizhi:mainid=" + mainid);
        IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("AgentMaintenance");
        List list = baseSheet.getLinkService().getLinksByMainId(mainid);
        for (int i = 0; i < list.size(); i++) {
            AgentMaintenanceLink agentMaintenanceLink = (AgentMaintenanceLink) list.get(i);
            String operatetype = StaticMethod.nullObject2String(agentMaintenanceLink.getOperateType());
            System.out.println("lizhi:operatetype=" + operatetype);
            if ("0".equals(operatetype)) {
                receivecount++;
            }
            if ("205".equals(operatetype)) {
                excutehumtaskcount++;
            }
        }
        if (receivecount > 1 && receivecount > excutehumtaskcount) {
            jitem.put("flag", "false");
        } else {
            jitem.put("flag", "true");
        }
        json.put(jitem);
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(json.toString());
        return null;
    }
	
	/*public ActionForward showDealReplyPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		IBaseSheet baseSheet = (IBaseSheet)getBean(mapping.getAttribute());
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String operateRoleId = StaticMethod.nullObject2String(request.getParameter("operateRoleId"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		if (!sheetKey.equals(""))
		{
			BaseMain mainObject = baseSheet.getMainService().getSingleMainPO(sheetKey);
			String condition = "select task,link.operateUserId ,link.operateDeptId ,link.linkTaskComplete from " + baseSheet.getTaskService().getTaskModelObject().getClass().getName() + " task ," + baseSheet.getLinkService().getLinkObject().getClass().getName() + " link where ((task.taskOwner='" + sessionform.getUserid() + "' or task.operateRoleId='" + sessionform.getDeptid() + "')" + " or task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + sessionform.getUserid() + "'))" + " and task.sheetKey='" + sheetKey + "' and task.taskName ='AffirmHumTask' and task.taskStatus<>5 and task.preLinkId =link.id";
			List taskList = baseSheet.getMainService().getMainDAO().getMainListBySql(condition);
			BaseLink linkObject = (BaseLink)baseSheet.getLinkService().getLinkObject().getClass().newInstance();
			if (sessionform != null)
			{
				linkObject.setOperateUserId(sessionform.getUserid());
				linkObject.setOperateDeptId(sessionform.getDeptid());
			}
			linkObject.setOperateType(new Integer(operateType));
			linkObject.setOperateRoleId(operateRoleId);
			linkObject.setOperaterContact(sessionform.getContactMobile());
			linkObject.setOperateTime(StaticMethod.getLocalTime());
			linkObject.setPiid(mainObject.getPiid());
			linkObject.setAiid("");
			linkObject.setTkid("");
			String mainId = RequestUtils.getStringParameter(request, "mainId");
			linkObject.setMainId(mainId);
			List mapList = new ArrayList();
			if (taskList != null && taskList.size() != 0)
			{
				for (int i = 0; i < taskList.size(); i++)
				{
					HashMap tmpMap = new HashMap();
					Object tmpObjArr[] = (Object[])taskList.get(i);
					ITask task = (ITask)tmpObjArr[0];
					tmpMap.putAll(SheetBeanUtils.bean2Map(task));
					tmpMap.put("operateUserId", tmpObjArr[1]);
					tmpMap.put("operateDeptId", tmpObjArr[2]);
					tmpMap.put("linkTaskComplete", tmpObjArr[3]);
					mapList.add(tmpMap);
				}
	
				request.setAttribute("mapList", mapList);
				request.setAttribute("operateType", operateType);
				request.setAttribute("taskName", taskName);
				request.setAttribute("sheetMain", mainObject);
				request.setAttribute("sheetLink", linkObject);
			}
		}
		return mapping.findForward("dealreply");
	}
	
	public ActionForward batchPerformDeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		IBaseSheet baseSheet = (IBaseSheet)getBean(mapping.getAttribute());
		String taskIds = StaticMethod.nullObject2String(request.getParameter("taskIds"));
		String taskIdArray[] = taskIds.split(",");
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		String succesReturn = "";
		String faultReturn = "";
		HashMap tempColumMap = new HashMap();
		HashMap taskMap = new HashMap();
		for (int i = 0; i < taskIdArray.length; i++)
		{
			String taskId = taskIdArray[i];
			ITask task = null;
			try
			{
				task = baseSheet.getTaskService().getSinglePO(taskId);
				request.setAttribute("mainId", task.getSheetKey());
				HashMap columnMap = baseSheet.getInterfaceObjMap(mapping, form, request, response);
				tempColumMap.put(taskId, columnMap);
				if (task != null)
					taskMap.put(taskId, task);
			}
			catch (Exception e)
			{
				if (!faultReturn.equals(""))
					faultReturn = faultReturn + ",";
				if (task != null)
					faultReturn = faultReturn + task.getSheetId();
				else
					faultReturn = faultReturn + taskId;
			}
		}
	
		for (Iterator iterator = taskMap.keySet().iterator(); iterator.hasNext();)
		{
			ITask task = (ITask)taskMap.get(iterator.next().toString());
			try
			{
				String taskId = task.getId();
				HashMap columnMap = (HashMap)tempColumMap.get(taskId);
				Map map = request.getParameterMap();
				map.put("aiid", task.getId());
				map.put("preLinkId", task.getPreLinkId());
				map.put("mainId", task.getSheetKey());
				map.put("sheetId", task.getSheetId());
				Map serializableMap = SheetUtils.serializableParemeterMap(map);
				Iterator it = serializableMap.keySet().iterator();
				HashMap WpsMap = new HashMap();
				HashMap tempWpsMap;
				for (; it.hasNext(); WpsMap.putAll(tempWpsMap))
				{
					String mapKey = (String)it.next();
					Map tempMap = (Map)serializableMap.get(mapKey);
					if (taskId.equals(""))
					{
						Object obj = tempMap.get("aiid");
						if (obj.getClass().isArray())
						{
							Object obja[] = (Object[])obj;
							obj = obja[0];
						}
						taskId = StaticMethod.nullObject2String(obj);
					}
					HashMap tempColumnMap = (HashMap)columnMap.get(mapKey);
					tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
				}
	
				baseSheet.setFlowEngineMap(WpsMap);
				baseSheet.dealFlowEngineMap(mapping, form, request, response);
				baseSheet.getBusinessFlowService().completeHumanTask(taskId, baseSheet.getFlowEngineMap(), sessionMap);
				if (!succesReturn.equals(""))
					succesReturn = succesReturn + ",";
				succesReturn = succesReturn + task.getSheetId();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				if (!faultReturn.equals(""))
					faultReturn = faultReturn + ",";
				faultReturn = faultReturn + task.getSheetId();
			}
		}
	
		return mapping.findForward("success");
	}*/
}
