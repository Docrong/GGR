// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ComplaintAction.java

package com.boco.eoms.sheet.complaint.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;

import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.bo.EomsLoader;
import com.boco.eoms.crm.model.CrmWaitInfo;
import com.boco.eoms.crm.service.ICrmWaitInfoManager;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.*;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.complaint.knowledage.ComplaintKnowledgeBO;
import com.boco.eoms.sheet.complaint.service.crminterface.C_eomsProcessHandleWebServiceLocator;
import com.boco.eoms.sheet.complaint.service.crminterface.C_eomsProcessHandleWebServicePortType;
import com.boco.eoms.sheet.complaint.task.impl.ComplaintTask;

import com.boco.eoms.sheet.complaint.model.*;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;
import com.boco.eoms.sheet.complaint.service.IComplaintReturnHouseManager;
import com.boco.eoms.sheet.complaintDuban.webapp.action.ComplaintDubanMethod;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.*;
import org.displaytag.util.ParamEncoder;

public class ComplaintAction extends SheetAction {

    private static final String CONFIG_FILEPATH = "classpath:config/worksheet-sms-service-info.xml";

    public ComplaintAction() {
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

    public void showLimit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String specialty1 = StaticMethod.null2String(request.getParameter("faultSpecialty"));
        String specialty2 = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
        String specialty3 = StaticMethod.null2String(request.getParameter("faultSpecialty3"));
        String specialty4 = StaticMethod.null2String(request.getParameter("faultSpecialty4"));
        ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
        com.boco.eoms.sheet.tool.limit.model.SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty1, specialty2, specialty3, specialty4);
        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(sheetLimit);
        JSONUtil.print(response, jsonRoot.toString());
    }

    public void showDealLimit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String specialty = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
        ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
        com.boco.eoms.sheet.tool.limit.model.SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty);
        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(sheetLimit);
        JSONUtil.print(response, jsonRoot.toString());
    }

    public void createKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String url = ComplaintKnowledgeBO.showNewknowLedage(sheetKey, sessionform.getUserid());
        System.out.println("url:" + url);
        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(url);
    }

    public void getSearchKnowledgeResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String url = ComplaintKnowledgeBO.searchSheet(sheetKey, sessionform.getUserid());
        System.out.println("知识库检索结果:" + url);
        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(url);
    }

    public ActionForward showInterfaceDraftPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetNo"), "");
        if (!sheetKey.equals("")) {
            String operateUser = XmlManage.getFile("/config/complaint-util.xml").getProperty("InterfaceUser");
            ITaskService taskService = (ITaskService) getBean("iComplaintTaskManager");
            ITask task = taskService.getTask(sheetKey, "DraftHumTask");
            request.setAttribute("sheetKey", sheetKey);
            request.setAttribute("taskId", task.getId());
            request.setAttribute("taskName", "DraftHumTask");
            request.setAttribute("piid", task.getProcessId());
            request.setAttribute("operateRoleId", task.getOperateRoleId());
            request.setAttribute("taskStatus", task.getTaskStatus());
            request.setAttribute("preLinkId", task.getPreLinkId());
            request.setAttribute("TKID", task.getId());
            ActionForward forward = mapping.findForward("showInterfaceDraftPage");
            String path = forward.getPath() + "&sheetKey=" + sheetKey + "&taskId=" + task.getId() + "&taskName=DraftHumTask&piid=" + task.getProcessId() + "&operateRoleId=" + task.getOperateRoleId() + "&taskStatus=" + task.getTaskStatus() + "&preLinkId=" + task.getPreLinkId() + "&TKID=" + task.getId() + "&userId=" + operateUser + "&type=interface";
            System.out.println("path=" + path);
            return new ActionForward(path, false);
        } else {
            throw new Exception("sheetNo不能为空");
        }
    }

    public ActionForward showListUndoForCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        IComplaintMainManager complaintMainManager = (IComplaintMainManager) baseSheet.getMainService();
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        Integer total = complaintMainManager.getCountUndoForCheck();
        List result = complaintMainManager.getListUndoForCheck(pageIndex, new Integer(pageSize.intValue()));
        request.setAttribute("taskList", result);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("backchecklist");
    }

    public ActionForward showListDoneForCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        IComplaintMainManager complaintMainManager = (IComplaintMainManager) baseSheet.getMainService();
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        Integer total = complaintMainManager.getCountDoneForCheck();
        List result = complaintMainManager.getListDoneForCheck(pageIndex, new Integer(pageSize.intValue()));
        request.setAttribute("taskList", result);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("backcheckedlist");
    }

    public ActionForward showBackCheckDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        request.setAttribute("mainid", sheetKey);
        return mapping.findForward("backcheckdealpage");
    }

    public ActionForward doBackCheckSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        IComplaintMainManager complaintMainManager = (IComplaintMainManager) baseSheet.getMainService();
        String mainId = StaticMethod.nullObject2String(request.getParameter("mainid"));
        String mainCheckIdea = StaticMethod.nullObject2String(request.getParameter("mainCheckIdea"));
        String mainCheckResult = StaticMethod.nullObject2String(request.getParameter("mainCheckResult"));
        if (!mainId.equals("")) {
            System.out.println("事后质检保存开始===mainId==" + mainId);
            ComplaintMain mainObject = (ComplaintMain) complaintMainManager.loadSinglePO(mainId);
            mainObject.setMainCheckIdea(mainCheckIdea);
            mainObject.setMainCheckResult(mainCheckResult);
            mainObject.setMainIfCheck("2");
            complaintMainManager.saveOrUpdateMain(mainObject);
        }
        return mapping.findForward("success");
    }

    public ActionForward performDeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("into performDeal ............");
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        try {
            String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            if (sheetKey.equals("")) {
                System.out.println("sheetKey is null");
            } else {
                String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
                System.out.println("attach=" + attach);
                ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
                if (main.getMainInterfaceSheetType() != null && main.getMainInterfaceSheetType().equals("crm")) {
                    String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
                    String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
                    String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendImmediately"));
                    if (sendImmediately.equalsIgnoreCase("true")) {
                        String phaseType = "";
                        ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager) ApplicationContextHolder.getInstance().getBean("ICrmWaitInfoManager");
                        CrmWaitInfo info = new CrmWaitInfo();
                        info.setSheetType(new Integer(56));
                        info.setSerialNo(main.getParentCorrelation());
                        info.setAttachRef(attach);
                        System.out.println("taskName:" + taskName);
                        System.out.println("operateType:" + operateType);
                        if (operateType.equals("4")) {
                            String dealPerformer = StaticMethod.nullObject2String(request.getParameter("dealPerformer"));
                            String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.InterfaceUser"));
                            String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendRoleId"));
                            System.out.println("投诉驳回:dealPerformer=" + dealPerformer);
                            if (dealPerformer.equals(userId) || dealPerformer.equals(sendRoleId)) {
                                dealwithdrawWorkSheet(request, info);
                                infoManager.saveOrUpdateCrmWaitInfo(info);
                            }
                        } else if (phaseType.equals("") && operateType.equals("9")) {
                            dealconnotifyWorkSheet(request, info, null);
                            infoManager.saveOrUpdateCrmWaitInfo(info);
                        } else if (!operateType.equals("46") && taskName.equalsIgnoreCase("DeferExamineHumTask") && operateType.equalsIgnoreCase("66") && StaticMethod.nullObject2String(request.getParameter("mainDelayFlag")).equals("1")) {
                            dealconnotifyWorkSheet(request, info, "需要延期解决");
                            infoManager.saveOrUpdateCrmWaitInfo(info);
                        }
                    }
                }
            }
        } catch (Exception err) {
            System.out.println("调用crm接口失败");
            err.printStackTrace();
        }
        baseSheet.performDeal(mapping, form, request, response);
        return mapping.findForward("success");
    }

    public ActionForward performClaimTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("into performClaimTask ............");
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        try {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            if (sheetKey.equals("")) {
                System.out.println("sheetKey is null");
            } else {
                String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
                ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
                if (main.getMainInterfaceSheetType() != null && main.getMainInterfaceSheetType().equals("crm")) {
                    String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
                    String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
                    String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendImmediately"));
                    ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager) getBean("ICrmWaitInfoManager");
                    CrmWaitInfo info = new CrmWaitInfo();
                    info.setSheetType(new Integer(56));
                    info.setSerialNo(main.getParentCorrelation());
                    info.setAttachRef(attach);
                    System.out.println("taskName:" + taskName);
                    System.out.println("operateType:" + operateType);
                    if (sendImmediately.equalsIgnoreCase("true") && taskName.equals("FirstExcuteHumTask") && operateType.equals("61")) {
                        String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
                        ITaskService iTaskService = (ITaskService) ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
                        ITask task = iTaskService.getSinglePO(taskId);
                        if (task != null && !StaticMethod.nullObject2String(task.getSubTaskFlag()).equalsIgnoreCase("true")) {
                            dealconfirmWorkSheet(info);
                            infoManager.saveOrUpdateCrmWaitInfo(info);
                        }
                    }
                }
            }
        } catch (Exception err) {
            System.out.println("调用crm接口失败");
            err.printStackTrace();
        }
        baseSheet.performClaim(mapping, form, request, response);
        return mapping.findForward("detail");
    }

    public ActionForward performProcessEvent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("into performProcessEvent ............");
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        try {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            if (sheetKey.equals("")) {
                System.out.println("sheetKey is null");
            } else {
                String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
                System.out.println("sheetKey=" + sheetKey);
                ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
                System.out.println("MainInterfaceSheetType=" + main.getMainInterfaceSheetType());
                if (main.getMainInterfaceSheetType() != null && main.getMainInterfaceSheetType().equals("crm")) {
                    System.out.println("MainInterfaceSheetType=" + main.getMainInterfaceSheetType());
                    String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
                    String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
                    String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendImmediately"));
                    String serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBtype1());
                    ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager) getBean("ICrmWaitInfoManager");
                    CrmWaitInfo info = new CrmWaitInfo();
                    info.setSheetType(new Integer(56));
                    info.setSerialNo(main.getParentCorrelation());
                    info.setAttachRef(attach);
                    try {
                        info.setServiceType(Integer.valueOf(serviceType));
                    } catch (Exception err) {
                        System.out.println("serviceType类型错误，serviceType=" + serviceType);
                    }
                    System.out.println("taskName:" + taskName);
                    System.out.println("operateType:" + operateType);
                    if (sendImmediately.equalsIgnoreCase("true") && operateType.equals("9")) {
                        dealconnotifyWorkSheet(request, info, null);
                        infoManager.saveOrUpdateCrmWaitInfo(info);
                    }
                }
            }
        } catch (Exception err) {
            System.out.println("调用crm接口失败");
            err.printStackTrace();
        }
        baseSheet.performProcessEvent(mapping, form, request, response);
        return mapping.findForward("success");
    }

    private void dealconfirmWorkSheet(CrmWaitInfo info) {
        System.out.println("into confirmWorkSheet ............");
        info.setInterfaceType("confirmWorkSheet");
        info.setOpType("受理");
        info.setOpDesc("");
    }

    private void dealwithdrawWorkSheet(HttpServletRequest request, CrmWaitInfo info) {
        System.out.println("into withdrawWorkSheet ............");
        info.setInterfaceType("withdrawWorkSheet");
        info.setOpType("驳回");
        String dealDesc = StaticMethod.nullObject2String(request.getParameter("remark"));
        info.setOpDesc(dealDesc);
    }

    private void dealconnotifyWorkSheet(HttpServletRequest request, CrmWaitInfo info, String isDeferReploy) {
        System.out.println("into notifyWorkSheet ............");
        info.setInterfaceType("notifyWorkSheet");
        info.setOpType("阶段回复");
        String dealDesc = "";
        if (isDeferReploy != null && isDeferReploy.length() > 0) {
            dealDesc = isDeferReploy;
            info.setIsDeferReploy("是");
        } else {
            dealDesc = StaticMethod.nullObject2String(request.getParameter("remark"));
        }
        info.setPhaseReployDesc(dealDesc);
    }

    private void dealreplyWorkSheet(HttpServletRequest request, CrmWaitInfo info) {
        System.out.println("into replyWorkSheet ............");
        info.setInterfaceType("replyWorkSheet");
        info.setOpType("回复");
        String ndeptContact = StaticMethod.nullObject2String(request.getParameter("ndeptContact"));
        String ndeptContactPhone = StaticMethod.nullObject2String(request.getParameter("ndeptContactPhone"));
        String compProp = StaticMethod.nullObject2String(request.getParameter("compProp"));
        String isReplied = StaticMethod.nullObject2String(request.getParameter("isReplied"));
        String issueEliminatTime = StaticMethod.nullObject2String(request.getParameter("issueEliminatTime"));
        String issueEliminatReason = StaticMethod.nullObject2String(request.getParameter("issueEliminatReason"));
        String dealDesc = StaticMethod.nullObject2String(request.getParameter("dealDesc"));
        String dealResult = StaticMethod.nullObject2String(request.getParameter("dealResult"));
        try {
            System.out.println("dealResult" + dealResult);
            dealResult = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("dealResult", dealResult);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("转换dealResult失败");
        }
        System.out.println("dealResult" + dealResult);
        try {
            System.out.println("compProp" + compProp);
            compProp = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("compProp", compProp);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("转换compProp失败");
        }
        System.out.println("compProp" + compProp);
        try {
            System.out.println("isReplied" + isReplied);
            isReplied = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("isReplied", isReplied);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("转换isReplied失败");
        }
        System.out.println("isReplied" + isReplied);
        info.setNdeptContact(ndeptContact);
        info.setNdeptContactPhone(ndeptContactPhone);
        info.setCompProp(compProp);
        try {
            System.out.println("isReplied=" + isReplied);
            System.out.println("issueEliminatTime=" + issueEliminatTime);
            info.setIsReplied(isReplied);
            info.setIssueEliminatTime(issueEliminatTime);
        } catch (Exception err) {
            err.printStackTrace();
        }
        info.setIssueEliminatReason(issueEliminatReason);
        info.setDealDesc(dealDesc);
        info.setDealResult(dealResult);
    }

    public ActionForward showProvinceMainPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetNo"), "");
        String userid = StaticMethod.nullObject2String(request.getParameter("userName"), "");
        if ("".equals(userid))
            userid = "admin";
        System.out.println("sheetNo=" + sheetId);
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        IMainService commonFaultMainManager = baseSheet.getMainService();
        BaseMain main = commonFaultMainManager.getMainBySheetId(sheetId);
        if (main == null) {
            throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
        } else {
            String sheetKey = main.getId();
            List linkList = baseSheet.getLinkService().getLinksByMainId(sheetKey);
            request.setAttribute("sheetMain", main);
            request.setAttribute("HISTORY", linkList);
            return mapping.findForward("provinecDetail");
        }
    }

    public ActionForward showDetailReturnCallPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ComplaintMain compliantmain = new ComplaintMain();
        ComplaintLink compliantlink = new ComplaintLink();
        String count = request.getParameter("count");
        String id = request.getParameter("id");
        String sheetid = request.getParameter("sheetid");
        String sheetKey = request.getParameter("sheetKey");
        String preLinkId = request.getParameter("preLinkId");
        IMainService Imainservice = (IMainService) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
        ILinkService Ilinkservice = (ILinkService) ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
        IComplaintReturnHouseManager icomplaintreturnhouse = (IComplaintReturnHouseManager) ApplicationContextHolder.getInstance().getBean("IComplaintReturnHouseManager");
        ComplaintReturnHouse complaintreturnhouse1 = new ComplaintReturnHouse();
        Date returndealtimedate = null;
        String returndealtime = "";
        if (!"".equals(id) && id != null) {
            complaintreturnhouse1 = icomplaintreturnhouse.getReturnHouseByid(id);
            if (!"".equals(count)) {
                returndealtimedate = complaintreturnhouse1.getReturndealtime();
                SimpleDateFormat formattime1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                request.setAttribute("returndealtime", formattime1.format(returndealtimedate));
                request.setAttribute("id", id);
            }
        }
        String condition = " preLinkId ='" + preLinkId + "'";
        List list = Ilinkservice.getLinksBycondition(condition, "ComplaintLink");
        if (list.size() > 0)
            compliantlink = (ComplaintLink) list.get(0);
        compliantmain = (ComplaintMain) Imainservice.getSingleMainPO(sheetid);
        String deptid = compliantlink.getOperateDeptId();
        Date operatetime = compliantlink.getOperateTime();
        Date accepttime = compliantmain.getSheetAcceptLimit();
        Date completetime = compliantmain.getSheetCompleteLimit();
        Date complianttime = compliantmain.getComplaintTime();
        Date faulttime = compliantmain.getFaultTime();
        SimpleDateFormat formattime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        request.setAttribute("formattime", formattime.format(operatetime));
        request.setAttribute("accepttime", formattime.format(accepttime));
        request.setAttribute("completetime", formattime.format(completetime));
        request.setAttribute("complianttime", formattime.format(complianttime));
        request.setAttribute("faulttime", formattime.format(faulttime));
        ITawSystemDeptManager itawsystemdept = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        TawSystemDept tawsystemdept = new TawSystemDept();
        tawsystemdept = itawsystemdept.getDeptinfobydeptid(deptid, "0");
        String deptname = tawsystemdept.getDeptName();
        request.setAttribute("complaintreturnhouse1", complaintreturnhouse1);
        request.setAttribute("compliantmain", compliantmain);
        request.setAttribute("compliantlink", compliantlink);
        request.setAttribute("deptname", deptname);
        request.setAttribute("preLinkId", preLinkId);
        request.setAttribute("count", count);
        return mapping.findForward("returndetail");
    }

    public ActionForward showReturnCallPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ComplaintMain compliantmain = new ComplaintMain();
        ComplaintLink compliantlink = new ComplaintLink();
        String count = request.getParameter("count");
        String id = request.getParameter("id");
        String sheetid = request.getParameter("sheetid");
        String sheetKey = request.getParameter("sheetKey");
        String preLinkId = request.getParameter("preLinkId");
        IMainService Imainservice = (IMainService) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
        ILinkService Ilinkservice = (ILinkService) ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
        IComplaintReturnHouseManager icomplaintreturnhouse = (IComplaintReturnHouseManager) ApplicationContextHolder.getInstance().getBean("IComplaintReturnHouseManager");
        ComplaintReturnHouse complaintreturnhouse1 = new ComplaintReturnHouse();
        Date returndealtimedate = null;
        String returndealtime = "";
        if (!"".equals(id) && id != null) {
            complaintreturnhouse1 = icomplaintreturnhouse.getReturnHouseByid(id);
            if (!"".equals(count)) {
                returndealtimedate = complaintreturnhouse1.getReturndealtime();
                SimpleDateFormat formattime1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                request.setAttribute("returndealtime", formattime1.format(returndealtimedate));
                request.setAttribute("id", id);
            }
        }
        String condition = " preLinkId ='" + preLinkId + "'";
        List list = Ilinkservice.getLinksBycondition(condition, "ComplaintLink");
        if (list.size() > 0)
            compliantlink = (ComplaintLink) list.get(0);
        compliantmain = (ComplaintMain) Imainservice.getSingleMainPO(sheetid);
        String deptid = compliantlink.getOperateDeptId();
        Date operatetime = compliantlink.getOperateTime();
        Date accepttime = compliantmain.getSheetAcceptLimit();
        Date completetime = compliantmain.getSheetCompleteLimit();
        Date complianttime = compliantmain.getComplaintTime();
        Date faulttime = compliantmain.getFaultTime();
        SimpleDateFormat formattime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        request.setAttribute("formattime", formattime.format(operatetime));
        request.setAttribute("accepttime", formattime.format(accepttime));
        request.setAttribute("completetime", formattime.format(completetime));
        request.setAttribute("complianttime", formattime.format(complianttime));
        request.setAttribute("faulttime", formattime.format(faulttime));
        ITawSystemDeptManager itawsystemdept = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        TawSystemDept tawsystemdept = new TawSystemDept();
        tawsystemdept = itawsystemdept.getDeptinfobydeptid(deptid, "0");
        String deptname = tawsystemdept.getDeptName();
        request.setAttribute("complaintreturnhouse1", complaintreturnhouse1);
        request.setAttribute("compliantmain", compliantmain);
        request.setAttribute("compliantlink", compliantlink);
        request.setAttribute("deptname", deptname);
        request.setAttribute("preLinkId", preLinkId);
        request.setAttribute("count", count);
        return mapping.findForward("returncall");
    }

    public ActionForward showBegainReturnListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String deptid;
        String id;
        String type;
        Integer pageSize;
        Integer pageIndex;
        ComplaintReturnHouse complaintreturnhouse1;
        IComplaintReturnHouseManager icomplaintreturnhouse;
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        deptid = sessionform.getDeptid();
        String deptname = sessionform.getDeptname();
        id = (String) request.getAttribute("id");
        type = StaticMethod.null2String(request.getParameter("type"));
        pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
        String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
        Map pagemap = request.getParameterMap();
        complaintreturnhouse1 = new ComplaintReturnHouse();
        SheetBeanUtils.populateMap2Bean(complaintreturnhouse1, pagemap);
        icomplaintreturnhouse = (IComplaintReturnHouseManager) ApplicationContextHolder.getInstance().getBean("IComplaintReturnHouseManager");
        try {
            if (pageIndex.intValue() == 0 && "".equals(type)) {
                icomplaintreturnhouse.saveReturnHouse(complaintreturnhouse1);
                String sheetKey = complaintreturnhouse1.getSheetkey();
                String workflowName = "ComplaintReturnHouse";
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                String sendTime = formatter.format(complaintreturnhouse1.getReturndealtime());
                //liuyu 20110920
                String senddeptid = complaintreturnhouse1.getDealpartment();
                this.workSM_NON_T(workflowName, sheetKey, senddeptid, sendTime);
                return mapping.findForward("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("error");
        }
        Map beginmap = new HashMap();
        String userid = complaintreturnhouse1.getUserid();
        int count = complaintreturnhouse1.getCount();
        String nwecount = (new StringBuffer(String.valueOf(count))).toString();
        Date returndealtimedate = null;
        String returndealtime = "";
        if (!"".equals(id) && id != null) {
            complaintreturnhouse1 = icomplaintreturnhouse.getReturnHouseByid(id);
            if (!"".equals(nwecount)) {
                returndealtimedate = complaintreturnhouse1.getReturndealtime();
                SimpleDateFormat formattime1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                request.setAttribute("returndealtime", formattime1.format(returndealtimedate));
            }
        }
        String queryStr = "from ComplaintReturnHouse as crh where crh.count!=0 and crh.deptid='" + deptid + "'" + "order by crh.returndealtime";
        beginmap = icomplaintreturnhouse.getReturnHouse(userid, queryStr, pageIndex, pageSize);
        int total = ((Integer) beginmap.get("taskTotal")).intValue();
        List taskList = (List) beginmap.get("taskList");
        SimpleDateFormat formattime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        request.setAttribute("complaintreturnhouse1", complaintreturnhouse1);
        request.setAttribute("taskList", taskList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("count", (new StringBuffer(String.valueOf(count))).toString());
        request.setAttribute("id", id);
        return mapping.findForward("begaindetaillist");
    }

    public ActionForward showUndoReturnListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String deptid = sessionform.getDeptid();
        String id = (String) request.getAttribute("id");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar getnowtime = Calendar.getInstance();
        String nowtime = df.format(getnowtime.getTime());
        Date nowdate = df.parse(nowtime);
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
        String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
        Map pagemap = request.getParameterMap();
        ComplaintReturnHouse complaintreturnhouse1 = new ComplaintReturnHouse();
        SheetBeanUtils.populateMap2Bean(complaintreturnhouse1, pagemap);
        IComplaintReturnHouseManager icomplaintreturnhouse = (IComplaintReturnHouseManager) ApplicationContextHolder.getInstance().getBean("IComplaintReturnHouseManager");
        if (pageIndex.intValue() == 0 && complaintreturnhouse1.getId() != null && !complaintreturnhouse1.getId().equals(""))
            icomplaintreturnhouse.saveReturnHouse(complaintreturnhouse1);
        Map beginmap = new HashMap();
        String userid = complaintreturnhouse1.getUserid();
        int count = complaintreturnhouse1.getCount();
        String queryStr = "from ComplaintReturnHouse crh where crh.count!=0 and crh.dealpartment='" + deptid + "'" + " and crh.returndealtime <=to_date('" + nowtime + "','yyyy-mm-dd hh24:mi:ss') order by crh.returndealtime ";
        beginmap = icomplaintreturnhouse.getReturnHouse(deptid, queryStr, pageIndex, pageSize);
        int total = ((Integer) beginmap.get("taskTotal")).intValue();
        List taskList = (List) beginmap.get("taskList");
        request.setAttribute("complaintreturnhouse1", complaintreturnhouse1);
        request.setAttribute("taskList", taskList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("count", (new StringBuffer(String.valueOf(count))).toString());
        request.setAttribute("id", id);
        return mapping.findForward("begainlist");
    }

    public ActionForward showReturnHoldListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String deptid = sessionform.getDeptid();
        Map pagemap = request.getParameterMap();
        String type = StaticMethod.null2String(request.getParameter("type"));
        ComplaintReturnHouse complaintreturnhouse = new ComplaintReturnHouse();
        Map beginmap = new HashMap();
        SheetBeanUtils.populateMap2Bean(complaintreturnhouse, pagemap);
        String id = StaticMethod.null2String(request.getParameter("id"));
        ComplaintReturnHouse complaintreturnhouse1 = new ComplaintReturnHouse();
        IComplaintReturnHouseManager icomplaintreturnhouse = (IComplaintReturnHouseManager) ApplicationContextHolder.getInstance().getBean("IComplaintReturnHouseManager");
        complaintreturnhouse1 = icomplaintreturnhouse.getReturnHouseByid(id);
        if (complaintreturnhouse.getReturnresult() != null && !complaintreturnhouse.getReturnresult().equals(""))
            complaintreturnhouse1.setReturnresult(complaintreturnhouse.getReturnresult());
        if (complaintreturnhouse.getReturnresulttwo() != null && !complaintreturnhouse.getReturnresulttwo().equals(""))
            complaintreturnhouse1.setReturnresulttwo(complaintreturnhouse.getReturnresulttwo());
        if (complaintreturnhouse.getReturnresultthree() != null && !complaintreturnhouse.getReturnresultthree().equals(""))
            complaintreturnhouse1.setReturnresultthree(complaintreturnhouse.getReturnresultthree());
        if (complaintreturnhouse.getCount() == 0 && "".equals(type))
            complaintreturnhouse1.setCount(0);
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
        String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
        String queryStr = "from ComplaintReturnHouse as crh where crh.count=0 and crh.dealpartment= '" + deptid + "' order by crh.returndealtime";
        if (pageIndex.intValue() == 0 && "".equals(type)) {
            icomplaintreturnhouse.saveReturnHouse(complaintreturnhouse1);
            Date returndealtime = complaintreturnhouse1.getReturndealtime();
            SimpleDateFormat formattime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            request.setAttribute("returndealtime", formattime.format(returndealtime));
        }
        beginmap = icomplaintreturnhouse.getReturnHouse(deptid, queryStr, pageIndex, pageSize);
        int total = ((Integer) beginmap.get("taskTotal")).intValue();
        List taskList = (List) beginmap.get("taskList");
        request.setAttribute("taskList", taskList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("begainlist");
    }

    public ActionForward showReturnGoOnListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String deptid = sessionform.getDeptid();
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        Map pagemap = request.getParameterMap();
        ComplaintReturnHouse complaintreturnhouse = new ComplaintReturnHouse();
        Map beginmap = new HashMap();
        SheetBeanUtils.populateMap2Bean(complaintreturnhouse, pagemap);
        String id = StaticMethod.null2String(request.getParameter("id"));
        ComplaintReturnHouse complaintreturnhouse1 = new ComplaintReturnHouse();
        IComplaintReturnHouseManager icomplaintreturnhouse = (IComplaintReturnHouseManager) ApplicationContextHolder.getInstance().getBean("IComplaintReturnHouseManager");
        complaintreturnhouse1 = icomplaintreturnhouse.getReturnHouseByid(id);
        int count = complaintreturnhouse1.getCount();
        complaintreturnhouse1.setReturnresult(complaintreturnhouse.getReturnresult());
        complaintreturnhouse1.setCount(2);
        String dealpartment = complaintreturnhouse1.getDealpartment();
        if (complaintreturnhouse1.getId() != null && !complaintreturnhouse1.getId().equals("") && pageIndex.intValue() == 0)
            icomplaintreturnhouse.saveReturnHouse(complaintreturnhouse1);
        String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
        String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
        String queryStr = "from ComplaintReturnHouse as crh where crh.count!=0  and crh.dealpartment= '" + deptid + "' order by crh.returndealtime";
        beginmap = icomplaintreturnhouse.getReturnHouse(dealpartment, queryStr, pageIndex, pageSize);
        int total = ((Integer) beginmap.get("taskTotal")).intValue();
        List taskList = (List) beginmap.get("taskList");
        SimpleDateFormat formattime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date returndealtime = complaintreturnhouse1.getReturndealtime();
        request.setAttribute("returndealtime", formattime.format(returndealtime));
        request.setAttribute("complaintreturnhouse1", complaintreturnhouse1);
        request.setAttribute("taskList", taskList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("count", (new StringBuffer(String.valueOf(count))).toString());
        request.setAttribute("id", id);
        return mapping.findForward("begainlist");
    }

    public ActionForward showReturnGoOnTwoListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String deptid = sessionform.getDeptid();
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        Map pagemap = request.getParameterMap();
        ComplaintReturnHouse complaintreturnhouse = new ComplaintReturnHouse();
        Map beginmap = new HashMap();
        SheetBeanUtils.populateMap2Bean(complaintreturnhouse, pagemap);
        String id = StaticMethod.null2String(request.getParameter("id"));
        ComplaintReturnHouse complaintreturnhouse1 = new ComplaintReturnHouse();
        IComplaintReturnHouseManager icomplaintreturnhouse = (IComplaintReturnHouseManager) ApplicationContextHolder.getInstance().getBean("IComplaintReturnHouseManager");
        complaintreturnhouse1 = icomplaintreturnhouse.getReturnHouseByid(id);
        int count = complaintreturnhouse1.getCount();
        complaintreturnhouse1.setReturnresulttwo(complaintreturnhouse.getReturnresulttwo());
        complaintreturnhouse1.setCount(3);
        String dealpartment = complaintreturnhouse1.getDealpartment();
        if (pageIndex.intValue() == 0 && complaintreturnhouse1.getId() != null && !complaintreturnhouse1.getId().equals(""))
            icomplaintreturnhouse.saveReturnHouse(complaintreturnhouse1);
        String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
        String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
        String queryStr = "from ComplaintReturnHouse as crh where crh.count!=0  and crh.dealpartment= '" + deptid + "' order by crh.returndealtime";
        beginmap = icomplaintreturnhouse.getReturnHouse(dealpartment, queryStr, pageIndex, pageSize);
        int total = ((Integer) beginmap.get("taskTotal")).intValue();
        List taskList = (List) beginmap.get("taskList");
        SimpleDateFormat formattime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date returndealtime = complaintreturnhouse1.getReturndealtime();
        request.setAttribute("returndealtime", formattime.format(returndealtime));
        request.setAttribute("complaintreturnhouse1", complaintreturnhouse1);
        request.setAttribute("taskList", taskList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("count", (new StringBuffer(String.valueOf(count))).toString());
        request.setAttribute("id", id);
        return mapping.findForward("begainlist");
    }

    public ActionForward showQueryReturnListInputPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String type = StaticMethod.null2String(request.getParameter("type"));
        return mapping.findForward("queryinputreturn");
    }

    public ActionForward showQueryReturnListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String type = StaticMethod.null2String(request.getParameter("type"));
        String sheetkey = StaticMethod.null2String(request.getParameter("sheetkey"));
        String recordTime = StaticMethod.null2String(request.getParameter("recordTime"));
        String recordTimeEnd = StaticMethod.null2String(request.getParameter("recordTimeEnd"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String deptid = sessionform.getDeptid();
        String id = (String) request.getAttribute("id");
        String sql = "from ComplaintReturnHouse as crh where crh.dealpartment ='" + deptid + "' or crh.deptid= " + "'" + deptid + "'";
        String sqllimt = "order by crh.returndealtime desc";
        if (!sheetkey.equals(""))
            sql = sql + "and crh.sheetkey = '" + sheetkey + "'";
        if (!recordTime.equals("") || !recordTimeEnd.equals(""))
            sql = sql + " and crh.returndealtime>= to_date('" + recordTime + "','yyyy-mm-dd hh24:mi:ss') and crh.returndealtime<= to_date('" + recordTimeEnd + "','yyyy-mm-dd hh24:mi:ss')";
        String queryStr = sql + sqllimt;
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
        String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
        IComplaintReturnHouseManager icomplaintreturnhouse = (IComplaintReturnHouseManager) ApplicationContextHolder.getInstance().getBean("IComplaintReturnHouseManager");
        Map beginmap = new HashMap();
        beginmap = icomplaintreturnhouse.getReturnHouse(deptid, queryStr, pageIndex, pageSize);
        int total = ((Integer) beginmap.get("taskTotal")).intValue();
        List taskList = (List) beginmap.get("taskList");
        request.setAttribute("taskList", taskList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("id", id);
        return mapping.findForward("begaindetaillist");
    }

    public void workSM_NON_T(String workflowName, String sheetKey, String receiverId, String sendTime)
            throws Exception {
        try {
            MsgServiceImpl msgService = new MsgServiceImpl();
            String nodeInstantName11 = "worksheet.ComplaintReturnHouse.smsServiceId.instant";
            System.out.println("====" + nodeInstantName11);
            String filePath = SheetStaticMethod.getFilePathForUrl("classpath:config/worksheet-sms-service-info.xml");
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName11);
            String receivers = "";
            receivers = receivers + 2 + "," + receiverId + "#";
            if (!receivers.equals(""))
                receivers = receivers.substring(0, receivers.lastIndexOf("#"));
            System.out.println("receivers=" + receivers);
            String sendContent = "请您及时处理投诉工单:" + sheetKey + "的回访内容。";
            System.out.println("luoyi:instantServiceId=" + instantServiceId + "sendContent=" + sendContent + "sheetKey=" + sheetKey + "receivers=" + receivers + "sendTime=" + sendTime);
            msgService.sendMsg(instantServiceId, sendContent, sheetKey, receivers, sendTime);
        } catch (Exception e) {
            throw new Exception("send message exception,error info is" + e.getMessage());
        }
    }

    public ActionForward ifisDuban(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
        String condition = "mainId = '" + sheetKey + "' and operateType = '46' order by operateTime desc";
        System.out.println("lizhi:condition=" + condition);
        List list = baseSheet.getLinkService().getLinksBycondition(condition, "ComplaintLink");//.getLinksByMainId(sheetKey);
        String linkplansolvetypeparent = "";
        Date linkplansolvedate = new Date();
        JSONObject jitem = new JSONObject();
        ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
        List attach = new ArrayList();
        String complaintSheetId = StaticMethod.nullObject2String(main.getSheetId());
        String correlationKey = StaticMethod.nullObject2String(main.getCorrelationKey());
        String parentCorrelation = StaticMethod.nullObject2String(main.getParentCorrelation());
        String complaintType1 = "";
        String complaintType2 = "";
        String complaintType = "";
        String complaintType4 = "";
        String complaintType5 = "";
        String complaintType6 = "";
        String complaintType7 = "";
        if (!"".equals(main.getComplaintType1()) && main.getComplaintType1() != null)
            complaintType1 = mgr.getDictTypeByDictid(main.getComplaintType1()).getDictName();
        if (!"".equals(main.getComplaintType2()) && main.getComplaintType2() != null)
            complaintType2 = "-" + mgr.getDictTypeByDictid(main.getComplaintType2()).getDictName();
        if (!"".equals(main.getComplaintType()) && main.getComplaintType() != null)
            complaintType = "-" + mgr.getDictTypeByDictid(main.getComplaintType()).getDictName();
        if (!"".equals(main.getComplaintType4()) && main.getComplaintType4() != null)
            complaintType4 = "-" + mgr.getDictTypeByDictid(main.getComplaintType4()).getDictName();
        if (!"".equals(main.getComplaintType5()) && main.getComplaintType5() != null)
            complaintType5 = "-" + mgr.getDictTypeByDictid(main.getComplaintType5()).getDictName();
        if (!"".equals(main.getComplaintType6()) && main.getComplaintType6() != null)
            complaintType6 = "-" + mgr.getDictTypeByDictid(main.getComplaintType6()).getDictName();
        if (!"".equals(main.getComplaintType7()) && main.getComplaintType7() != null)
            complaintType7 = "-" + mgr.getDictTypeByDictid(main.getComplaintType7()).getDictName();
        String complaintSort = complaintType1 + complaintType2 + complaintType + complaintType4 + complaintType5 + complaintType6 + complaintType7;
        String appearance = StaticMethod.nullObject2String(main.getFaultSite());
        String acceptNum = "";
        String complaintInfo = StaticMethod.nullObject2String(main.getComplaintDesc());
        String complaintText = "";
        String operateUserId = "";
        String operateDeptId = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 2);
        Date sheetAcceptLimit = calendar.getTime();
        String title = "请处理未解决投诉" + complaintSheetId;
        String sheetId = "";
        HashMap sheetMap = new HashMap();
        if (list.size() > 0) {
            ComplaintLink complaintlink = (ComplaintLink) list.get(0);
            linkplansolvetypeparent = StaticMethod.nullObject2String(complaintlink.getLinkPlanSolveTypeparent());
            linkplansolvedate = complaintlink.getLinkPlanSolveDate();
            acceptNum = StaticMethod.nullObject2String(complaintlink.getNdeptContactPhone());
            complaintText = StaticMethod.nullObject2String(complaintlink.getLinkDealDesc());
            operateUserId = StaticMethod.nullObject2String(complaintlink.getOperateUserId());
            operateDeptId = StaticMethod.nullObject2String(complaintlink.getOperateDeptId());
            System.out.println("lizhi:operateDeptId=" + operateDeptId);
        }

        if (sheetAcceptLimit.after(linkplansolvedate)) {
            calendar.setTime(linkplansolvedate);
            calendar.add(11, -2);
            sheetAcceptLimit = calendar.getTime();
        }
        sheetMap.put("sheetAcceptLimit", df.format(sheetAcceptLimit).toString());
        sheetMap.put("title", title);
        sheetMap.put("sheetCompleteLimit", df.format(linkplansolvedate).toString());
        sheetMap.put("nodeAcceptLimit", df.format(sheetAcceptLimit).toString());
        sheetMap.put("nodeCompleteLimit", df.format(linkplansolvedate).toString());
        sheetMap.put("trailId", parentCorrelation);
        sheetMap.put("eomsId", complaintSheetId);
        sheetMap.put("complaintSort", complaintSort);
        sheetMap.put("appearance", appearance);
        sheetMap.put("acceptNum", acceptNum);
        sheetMap.put("opinion", "请及时解决后回单");
        sheetMap.put("complaintInfo", complaintInfo);
        sheetMap.put("complaintText", complaintText);
        sheetMap.put("sheetType", "2");
        sheetMap.put("serviceType", "1");
        sheetMap.put("parentSheetId", sheetKey);
        sheetMap.put("parentSheetName", "iComplaintMainManager");
        sheetMap.put("parentCorrelation", correlationKey);
        sheetMap.put("parentPhaseName", taskName);
        sheetMap.put("sheetTemplateName", "ComplaintDubanProcesses");
        sheetMap.put("invokeMode", "asynchronism");
        if ("101061601".equals(linkplansolvetypeparent)) {
            EomsLoader loader = new EomsLoader();
            sheetId = loader.newWorkSheet(sheetMap, attach);
            final String insheetId = sheetId;
            final String inoperateUserId = operateUserId;
            final String inoperateDeptId = operateDeptId;
            Runnable invokeAutoSplit = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(5000);
                        System.out.println("lizhi:sheetId=" + insheetId + "operateDeptId" + inoperateDeptId);
                        ComplaintDubanMethod complaintdubanmethod = new ComplaintDubanMethod();
                        complaintdubanmethod.autoPerformCreateSubTask(insheetId, inoperateDeptId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            Thread t = new Thread(invokeAutoSplit);
            t.start();
        }
        jitem.put("linkplansolvetypeparent", linkplansolvetypeparent);
        jitem.put("sheetId", sheetId);
        jitem.put("operateUserId", operateUserId);
        JSONArray json = new JSONArray();
        json.put(jitem);
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(json.toString());
        return null;
    }

    public ActionForward ifisFenpai(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetId"));
        String operateUserId = StaticMethod.nullObject2String(request.getParameter("operateUserId"));
        ComplaintDubanMethod complaintdubanmethod = new ComplaintDubanMethod();
        System.out.println("lizhi:sheetId=" + sheetId + "operateUserId" + operateUserId);
        complaintdubanmethod.autoPerformCreateSubTask(sheetId, operateUserId);
        return mapping.findForward("success");
    }

    public ActionForward showAsleepOp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String TKID = StaticMethod.nullObject2String(request.getParameter("TKID"));
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        request.setAttribute("taskStatus", taskStatus);
        request.setAttribute("sheetKey", sheetKey);
        request.setAttribute("TKID", TKID);
        request.setAttribute("userName", sessionform.getUsername());
        request.setAttribute("deptName", sessionform.getDeptname());
        request.setAttribute("userId", sessionform.getUserid());
        request.setAttribute("deptId", sessionform.getDeptid());
        request.setAttribute("operaterContact", sessionform.getContactMobile());
        return mapping.findForward("asleep");
    }

    public ActionForward performAsleepOp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String tkid = request.getParameter("TKID");
        String sheetKey = request.getParameter("sheetKey");
        String sleepTime = request.getParameter("sleepTime");
        String sleepReasion = request.getParameter("sleepReason");
        System.out.println("sleepTime==tkid====sheetKey" + sleepTime + "==" + tkid + "==sheetKey");
        ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
        ComplaintTask tks = (ComplaintTask) baseSheet.getTaskService().getSinglePO(tkid);
        //    String t1dealer = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("t1RoleId"));
        //审批人先去找T1移交T2的人，如果为自动移交的话，审批人则从配置文件里读取，否则审批人为T1移交给T2的操作人 alter by weichao  20150714 begin
        List complaintTasks = baseSheet.getTaskService().getTasksByCondition(" sheetKey='" + sheetKey + "' and taskName='FirstExcuteHumTask' ");
        ComplaintTask t1Task = null;
        if (null != complaintTasks && complaintTasks.size() > 0) {
            t1Task = (ComplaintTask) complaintTasks.get(complaintTasks.size() - 1);
        }
        //从配置文件中读取 所配置的 审批人角色id
        String t1dealer = "";
        if (null != t1Task) {
            String operateUserId = t1Task.getTaskOwner();
            String operateRoleId = t1Task.getOperateRoleId();
            if ("fangmin".equals(operateUserId)) {
                BocoLog.info(this, "===T1 auto transfer==");
                t1dealer = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("t1RoleId"));
            } else {
                BocoLog.info(this, "===T1 transfer==");
                t1dealer = operateRoleId;
            }
        } else {
            BocoLog.info(this, "===find none t1 task==");
            t1dealer = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("t1RoleId"));
        }
        System.out.println(">>>>>sleepSheet>>>>>t1dealer" + t1dealer);
        //alter by weichao 20150714 end
        if (t1dealer != null && !"".equals(t1dealer)) {

            main.setMainT1Dealer(t1dealer);
            main.setMainSleepStatus(Integer.valueOf(1));
            main.setMainSleepTkid(tkid);
            main.setMainSleepTime(sleepTime);
            main.setMainSleepReason(sleepReasion);
            main.setMainSleepUser(sessionform.getUserid());
            main.setMainT2ApplyTime(new Date());
            baseSheet.getMainService().saveOrUpdateMain(main);
            tks.setTaskStatus("4");
            baseSheet.getTaskService().addTask(tks);
        }
        return mapping.findForward("success");
    }

    public ActionForward showSleepSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
        if (!exportType.equals(""))
            pageSize = Integer.valueOf(0x186a0);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
//		String t1dealer = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("t1RoleId"));
        ITawSystemUserRefRoleManager manager = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
//		List userids = manager.getUserIdBySubRoleids(t1dealer);
        String userId = sessionform.getUserid();
        List userRoleIds = manager.getRoleidByuserid(userId);
        String userRoleId = "''";
        for (int i = 0; i < userRoleIds.size(); i++) {
            userRoleId = userRoleId + ",'" + userRoleIds.get(i).toString() + "'";
        }
        System.out.println("0000sleepSheet000+" + userRoleId);
//		boolean ift1dealer = false;
//		if (userids != null && userids.size() > 0)
//			ift1dealer = userids.contains(sessionform.getUserid());
        IComplaintMainManager mgr = (IComplaintMainManager) getBean("iComplaintMainManager");
        String condition = "";
//		if (ift1dealer)
        condition = " from ComplaintMain  where status='0' and ((mainSleepStatus in ('1','2') and maint1dealer in (" + userRoleId + ")) or (mainSleepStatus='2' and mainSleepUser='" + sessionform.getUserid() + "' ) ) order by createTime";
//		condition = " from ComplaintMain  where status='0' and (mainSleepStatus='1' or mainSleepStatus='2') and  maint1dealer in (select subRoleid from taw_system_userrefrole  refrole where refrole.userID='" + sessionform.getUserid() + "')";
//		else
//			condition = " from ComplaintMain where status='0' and  mainSleepStatus='2' and   mainSleepUser='" + sessionform.getUserid() + "'";
        System.out.println("---------------sleepsheet-sql---------" + condition);
        Map mains = mgr.getMainsByConditionSQL(condition, pageIndex, pageSize);
        int total = ((Integer) mains.get("sheetTotal")).intValue();
        List list = (List) mains.get("sheetList");
        request.setAttribute("taskList", list);
        request.setAttribute("total", Integer.valueOf(total));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("sleeplist");
    }

    public ActionForward showDealAsleep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        String sheetKey = request.getParameter("sheetKey");
        ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        request.setAttribute("sheetKey", sheetKey);
        request.setAttribute("TKID", main.getMainSleepTkid());
        request.setAttribute("userName", sessionform.getUsername());
        request.setAttribute("deptName", sessionform.getDeptname());
        request.setAttribute("userId", sessionform.getUserid());
        request.setAttribute("deptId", sessionform.getDeptid());
        request.setAttribute("operaterContact", sessionform.getContactMobile());
        request.setAttribute("sleepTime", main.getMainSleepTime());
        request.setAttribute("sleepReason", main.getMainSleepReason());
        request.setAttribute("SleepUser", main.getMainSleepUser());
        return mapping.findForward("dealsleep");
    }

    public ActionForward dealAsleepSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        String sleepApply = request.getParameter("sleepApply");
        String sheetKey = request.getParameter("sheetKey");
        ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
        ComplaintTask task = (ComplaintTask) baseSheet.getTaskService().getSinglePO(main.getMainSleepTkid());
        if ("no".equals(sleepApply)) {
            System.out.println("nooooooooo");
            main.setMainSleepStatus(Integer.valueOf(3));
            main.setMainT1Dealer(sessionform.getUserid());
            main.setMainT1DealTime(new Date());
            task.setTaskStatus("8");
            baseSheet.getTaskService().addTask(task);
            baseSheet.getMainService().saveOrUpdateMain(main);
            return mapping.findForward("success");
        } else {
            System.out.println("yessssssss");
            task.setTaskStatus("6");
            main.setMainSleepStatus(Integer.valueOf(2));
            main.setMainT1DealTime(new Date());
            String time = main.getMainSleepTime();
            Date sheetCompleteLimit = main.getSheetCompleteLimit();
            Date completelimit = task.getCompleteTimeLimit();
            Calendar c = Calendar.getInstance();
            c.setTime(sheetCompleteLimit);
            c.add(6, Integer.parseInt(time));
            Date temp_date = c.getTime();
            main.setSheetCompleteLimit(temp_date);
            main.setMainOldCompleteLimit(sheetCompleteLimit);
            c.clear();
            c.setTime(completelimit);
            c.add(6, Integer.parseInt(time));
            Date temp_dates = c.getTime();
            task.setCompleteTimeLimit(temp_dates);
            main.setMainT1Dealer(sessionform.getUserid());
            baseSheet.getTaskService().addTask(task);
            baseSheet.getMainService().saveOrUpdateMain(main);
            return showSleepSheet(mapping, form, request, response);
        }
    }

    public void performSleepPreCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        String sleepApply = request.getParameter("sleepApply");
        String sheetKey = request.getParameter("sheetKey");
        ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
        ComplaintTask task = (ComplaintTask) baseSheet.getTaskService().getSinglePO(main.getMainSleepTkid());
        JSONObject jsonRoot = new JSONObject();
        JSONArray data = new JSONArray();
        String status = "";
        String text = "";
        String in4 = main.getParentCorrelation();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String in3 = sessionform.getContactMobile();
        String in2 = sessionform.getUsername();
        String in0 = main.getMainSleepReason();
        String time = main.getMainSleepTime();
        Date sheetCompleteLimit = main.getSheetCompleteLimit();
        Calendar c = Calendar.getInstance();
        c.setTime(sheetCompleteLimit);
        c.add(6, Integer.parseInt(time));
        Date temp_date = c.getTime();
        String in5 = StaticMethod.date2String(temp_date);
        if (sleepApply != null && !"".equals(sleepApply)) {
            if ("no".equals(sleepApply)) {
                System.out.println("nooooooooo");
                status = "0";
            } else {
                System.out.println("yessssssss");
                System.out.println("sleeping...............");
                System.out.println("staring interface......");
                String in1 = "0";
                try {
                    C_eomsProcessHandleWebServiceLocator locator = new C_eomsProcessHandleWebServiceLocator();
                    URL portAddress = new URL(XmlManage.getFile("/config/complaint-util.xml").getProperty("url"));
                    System.out.println("URL---" + portAddress);
                    String returning = locator.getc_eomsProcessHandleWebServiceHttpPort(portAddress).suspendProcess(in0, in1, in2, in3, in4, in5, "");
                    System.out.println("crm休眠接口返回值==" + returning);
                    if ("0".equals(returning)) {
                        status = "0";
                    } else {
                        status = "2";
                        text = "crm接口调用失败，请联系管理员";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    status = "2";
                    text = "crm接口调用失败，请联系管理员";
                    throw new Exception("crm接口调用失败，请联系管理员");
                }
            }
        } else {
            System.out.println("activation...............");
            System.out.println("staring interface......");
            String in1 = "1";
            try {
                C_eomsProcessHandleWebServiceLocator locator = new C_eomsProcessHandleWebServiceLocator();
                URL portAddress = new URL(XmlManage.getFile("/config/complaint-util.xml").getProperty("url"));
                System.out.println("URL---" + portAddress);
                String returning = locator.getc_eomsProcessHandleWebServiceHttpPort(portAddress).suspendProcess(in0, in1, in2, in3, in4, in5, "");
                System.out.println("crm休眠接口返回值==" + returning);
                String aa[] = returning.split("\\|");
                for (int i = 0; i < aa.length; i++)
                    System.out.println(";aa" + aa[i]);

                if (aa[0] != null && !"".equals(aa[0]) && "0".equals(aa[0])) {
                    status = "0";
                    if (aa[1] != null && !"".equals(aa[1])) {
                        Date completeDate = SheetUtils.stringToDate(aa[1]);
                        main.setSheetCompleteLimit(completeDate);
                        task.setCompleteTimeLimit(completeDate);
                    } else {
                        task.setCompleteTimeLimit(main.getSheetCompleteLimit());
                    }
                    main.setMainActivateTime(new Date());
                    main.setMainActivateDealer("eoms");
                    baseSheet.getTaskService().addTask(task);
                    baseSheet.getMainService().saveOrUpdateMain(main);
                } else {
                    status = "2";
                    text = "crm接口调用失败，请联系管理员";
                }
            } catch (Exception e) {
                e.printStackTrace();
                status = "2";
                text = "crm接口调用失败，请联系管理员";
                throw new Exception("crm接口调用失败，请联系管理员");
            }
        }
        System.out.println("complete interface......");
        JSONObject o = new JSONObject();
        o.put("text", text);
        data.put(o);
        jsonRoot.put("data", data);
        jsonRoot.put("status", String.valueOf(status));
        JSONUtil.print(response, jsonRoot.toString());
    }

    public ActionForward showActivationSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        String sheetKey = request.getParameter("sheetKey");
        ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        request.setAttribute("sheetKey", sheetKey);
        request.setAttribute("userName", sessionform.getUsername());
        request.setAttribute("deptName", sessionform.getDeptname());
        request.setAttribute("userId", sessionform.getUserid());
        request.setAttribute("deptId", sessionform.getDeptid());
        request.setAttribute("operaterContact", sessionform.getContactMobile());
        request.setAttribute("sheetMain", main);
        return mapping.findForward("activation");
    }

    public ActionForward performActivationSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        String sheetKey = request.getParameter("sheetKey");
        ComplaintMain main = (ComplaintMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
        ComplaintTask task = (ComplaintTask) baseSheet.getTaskService().getSinglePO(main.getMainSleepTkid());
        main.setMainSleepStatus(Integer.valueOf(4));
        task.setTaskStatus("8");
        baseSheet.getTaskService().addTask(task);
        baseSheet.getMainService().saveOrUpdateMain(main);
        System.out.println("activation ok..............");
        return showSleepSheet(mapping, form, request, response);
    }

}
