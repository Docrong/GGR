/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.webapp.action;

import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.commonfault.knowledage.CommonfaultKnowLedgeBO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;

/**
 * @author TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonFaultAction extends SheetAction {

    /**
     * 显示草图
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showDrawing(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("draw");
    }

    /**
     * 显示流程VISO图
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showPic(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("pic");
    }

    /**
     * 显示KPI
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showKPI(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("kpi");
    }

    /**
     * 调用关系列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 2008-08-02
     */
    public ActionForward showInvokeRelationShipList(ActionMapping mapping,
                                                    ActionForm form, HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception {
        String mainId = StaticMethod.null2String(request.getParameter("id"));
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();
        List showInvokeRelationShipList = commonFaultMainManager.showInvokeRelationShipList(mainId);

        //得到处理环节的工单
        BaseLink baseLink = commonFaultMainManager.getHasInvokeBaseLink(mainId);
        String activeTemplateId = (baseLink == null ? "" : baseLink.getActiveTemplateId());

        request.setAttribute("proccessName", "故障处理工单");
        request.setAttribute("invokedproccessName", "紧急故障工单");
        request.setAttribute("showInvokeRelationShipList", showInvokeRelationShipList);
        request.setAttribute("activeTemplateId", activeTemplateId);
        return mapping.findForward("showInvokeRelationShipList");
    }

    /**
     * 根据专业，查询时限
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 2008-08-02
     */
    public void showLimit(ActionMapping mapping,
                          ActionForm form, HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        String specialty1 = StaticMethod.null2String(request.getParameter("faultSpecialty"));
        String specialty2 = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
        String specialty3 = StaticMethod.null2String(request.getParameter("faultSpecialty3"));
        String specialty4 = StaticMethod.null2String(request.getParameter("faultSpecialty4"));
        ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
        SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty1, specialty2, specialty3, specialty4);
        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(sheetLimit);
        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 根据专业，查询时限
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 2008-08-02
     */
    public void showDealLimit(ActionMapping mapping,
                              ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String specialty = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
        ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
        SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty);
        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(sheetLimit);
        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 获取新增知识库的url
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void createKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String url = CommonfaultKnowLedgeBO.showNewknowLedage(sheetKey, sessionform.getUserid());
        System.out.println("url:" + url);

        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(url);
    }

    /**
     * 获取新增遗留问题库的url
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void createLeaveKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String url = CommonfaultKnowLedgeBO.showNewLeaveKnowLedage(sheetKey, sessionform.getUserid());
        System.out.println("url:" + url);

        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(url);
    }

    /**
     * 获取知识库检索结果的url
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void getSearchKnowledgeResult(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String url = CommonfaultKnowLedgeBO.searchSheet(sheetKey, sessionform.getUserid());
        System.out.println("知识库检索结果:" + url);

        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(url);
    }

    /**
     * 工单的草稿页面，告警接口
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showInterfaceDraftPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception {
        String sheetId = StaticMethod.nullObject2String(request
                .getParameter("sheetNo"), "");
        String userid = StaticMethod.nullObject2String(request
                .getParameter("userName"), "");

        System.out.println("sheetNo=" + sheetId);


        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();
        BaseMain main = (BaseMain) commonFaultMainManager.getMainBySheetId(sheetId);
        if (main == null)
            throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
        String sheetKey = main.getId();


        if (!sheetKey.equals("")) {

            if ("".equals(userid))
                userid = "admin";
            try {
                IWorkflowSecutiryService safeService =
                        (IWorkflowSecutiryService) ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
                Subject subject = safeService.logIn(userid, "");
                request.getSession().setAttribute("wpsSubject", subject);
            } catch (Exception e) {
                BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
            }


            String operateUser = XmlManage.getFile("/config/commonfault-util.xml").getProperty("InterfaceUser");

            ITaskService taskService = (ITaskService) getBean("iCommonFaultTaskManager");
            ITask task = taskService.getTask(sheetKey, "DraftHumTask");

            if (task == null)
                throw new Exception("没找到sheetNo＝" + sheetId + "的草稿任务");

            request.setAttribute("sheetKey", sheetKey);
            request.setAttribute("taskId", task.getId());
            request.setAttribute("taskName", "DraftHumTask");
            request.setAttribute("piid", task.getProcessId());
            request.setAttribute("operateRoleId", task.getOperateRoleId());
            request.setAttribute("taskStatus", task.getTaskStatus());
            request.setAttribute("preLinkId", task.getPreLinkId());
            request.setAttribute("TKID", task.getId());

            ActionForward forward = mapping.findForward("showInterfaceDraftPage");
            String path = forward.getPath() + "&sheetKey=" + sheetKey + "&taskId=" + task.getId() + "&taskName=DraftHumTask&piid="
                    + task.getProcessId() + "&operateRoleId=" + task.getOperateRoleId() + "&taskStatus=" + task.getTaskStatus() + "&preLinkId=" + task.getPreLinkId()
                    + "&TKID=" + task.getId() + "&userId=" + operateUser + "&type=interface" + "&list=act";
            System.out.println("path=" + path);
            return new ActionForward(path, false);
        } else
            throw new Exception("sheetNo不能为空");

    }

    /**
     * 工单的历史页面，告警接口
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showHistoryPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        String sheetId = StaticMethod.nullObject2String(request
                .getParameter("sheetNo"), "");
        String userid = StaticMethod.nullObject2String(request
                .getParameter("userName"), "");

        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();
        BaseMain main = (BaseMain) commonFaultMainManager.getMainBySheetId(sheetId);
        String sheetKey = main.getId();

        if (!sheetKey.equals("")) {
            if ("".equals(userid))
                userid = "admin";
            try {
                IWorkflowSecutiryService safeService =
                        (IWorkflowSecutiryService) ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
                Subject subject = safeService.logIn(userid, "");
                request.getSession().setAttribute("wpsSubject", subject);
            } catch (Exception e) {
                BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
            }

            ActionForward forward = mapping.findForward("showInterfaceDraftPage");
            String path = forward.getPath() + "&sheetKey=" + sheetKey;
            System.out.println("path=" + path);
            return new ActionForward(path, false);
        } else
            throw new Exception("sheetNo不能为空");

    }

    public ActionForward performDeal(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("SendImmediately"));

        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performDeal(mapping, form, request, response);
        String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
        String enableService = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("EnableService"));
        if ("true".equalsIgnoreCase(enableService) && sendImmediately.equalsIgnoreCase("true")) {//调用告警接口
            String status = "";
            String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));

            System.out.println("调用告警同步接口 taskName=" + taskName);
            System.out.println("调用告警同步接口 phaseId=" + phaseId);
            if (phaseId.equals("FirstExcuteTask"))
                status = "待受理";
            else if (phaseId.equals("HoldTask"))
                status = "处理完成";
            else if (taskName.equals("HoldHumTask"))
                status = "已归档";
            CommonFaultBO.updateAlarm(sheetKey, status);
        }

        return mapping.findForward("success");
    }

    public ActionForward performClaimTask(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("into performClaim ............");
        String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("SendImmediately"));
        if (!sendImmediately.equalsIgnoreCase("true")) {//不是立即调用接口，存到轮循表中
            request.setAttribute("interfaceType", "InterSwitchAlarm");
            request.setAttribute("methodType", "syncSheetState");
        }

        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performClaim(mapping, form, request, response);

        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String enableService = XmlManage.getFile("/config/commonfault-util.xml").getProperty("EnableService");
        if ("true".equals(enableService) && sendImmediately.equalsIgnoreCase("true")) {//调用告警接口
            String status = "";
            String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
            System.out.println("调用告警同步接口 taskName=" + taskName);
            System.out.println("调用告警同步接口 operateType=" + operateType);
            if (operateType.equals("61")) {
                if (taskName.equals("FirstExcuteHumTask"))
                    status = "T1处理中";
                else if (taskName.equals("SecondExcuteHumTask"))
                    status = "T2处理中";
                else if (taskName.equals("ThirdExcuteHumTask"))
                    status = "T3处理中";
                CommonFaultBO.updateAlarm(sheetKey, status);
            }
        }

        return mapping.findForward("detail");
    }

    /**
     * 工单作废
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performFroceHold(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);

        String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("SendImmediately"));
        if (!sendImmediately.equalsIgnoreCase("true")) {//不是立即调用接口，存到轮循表中
            request.setAttribute("interfaceType", "InterSwitchAlarm");
            request.setAttribute("methodType", "syncSheetState");
        }

        try {
            // 转移工单
            baseSheet.performFroceHold(mapping, form, request, response);

            String enableService = XmlManage.getFile("/config/commonfault-util.xml").getProperty("EnableService");
            if ("true".equals(enableService) && sendImmediately.equalsIgnoreCase("true")) {//调用告警接口
                String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
                System.out.println("调用告警同步接口 operateType=" + operateType);
                System.out.println("调用告警同步接口 sheetKey=" + sheetKey);
                if (String.valueOf(Constants.ACTION_NULLITY).equals(operateType)) {
                    String status = "作废";
                    CommonFaultBO.updateAlarm(sheetKey, status);
                } else
                    System.out.println("未调用告警同步接口");
            }
        } catch (Exception e) {
            // 失败，有可能现有activity状态不正确
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    /**
     * 显示所有待质检（后质检）的已归档工单
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author yyk
     */
    public ActionForward showListUndoForCheck(ActionMapping mapping, ActionForm form,
                                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        ICommonFaultMainManager commonfaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();


        // modified by
        // 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();

        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

        // 当前页数
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        // 分页取得列表
        Integer total = commonfaultMainManager.getCountUndoForCheck();
        // wps端分页取得列表

        List result = commonfaultMainManager.getListUndoForCheck(pageIndex, new Integer(pageSize.intValue()));

        request.setAttribute("taskList", result);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("backchecklist");
    }


    /**
     * 显示所有已质检（后质检）的已归档工单
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author yyk
     */
    public ActionForward showListDoneForCheck(ActionMapping mapping, ActionForm form,
                                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        ICommonFaultMainManager commonfaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();

        // modified by
        // 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();

        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

        // 当前页数
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        // 分页取得列表
        Integer total = commonfaultMainManager.getCountDoneForCheck();
        // wps端分页取得列表

        List result = commonfaultMainManager.getListDoneForCheck(pageIndex, new Integer(pageSize.intValue()));

        request.setAttribute("taskList", result);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("backcheckedlist");
    }

    /**
     * 显示事后质检处理页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author yyk
     */
    public ActionForward showBackCheckDealPage(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));

        request.setAttribute("mainid", sheetKey);
        return mapping.findForward("backcheckdealpage");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author yyk
     * @see 事后质检保存
     */
    public ActionForward doBackCheckSave(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        ICommonFaultMainManager commonfaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();
        String mainId = StaticMethod.nullObject2String(request.getParameter("mainid"));
        String mainCheckIdea = StaticMethod.nullObject2String(request.getParameter("mainCheckIdea"));
        String mainCheckResult = StaticMethod.nullObject2String(request.getParameter("mainCheckResult"));
        if (!mainId.equals("")) {
            System.out.println("事后质检保存开始===mainId==" + mainId);
            CommonFaultMain mainObject = (CommonFaultMain) commonfaultMainManager.loadSinglePO(mainId);
            mainObject.setMainCheckIdea(mainCheckIdea);
            mainObject.setMainCheckResult(mainCheckResult);
            mainObject.setMainIfCheck("2");
            commonfaultMainManager.saveOrUpdateMain(mainObject);
        }

        return mapping.findForward("success");
    }

    /**
     * 省部上报接口页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public ActionForward showProvinceMainPage(ActionMapping mapping, ActionForm form,
                                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetId = StaticMethod.nullObject2String(request
                .getParameter("sheetNo"), "");
        String userid = StaticMethod.nullObject2String(request
                .getParameter("userName"), "");

        if ("".equals(userid))
            userid = "admin";
        try {
            IWorkflowSecutiryService safeService =
                    (IWorkflowSecutiryService) ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
            Subject subject = safeService.logIn(userid, "");
            request.getSession().setAttribute("wpsSubject", subject);
        } catch (Exception e) {
            BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
        }

        System.out.println("sheetNo=" + sheetId);

        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager) baseSheet.getMainService();
        BaseMain main = (BaseMain) commonFaultMainManager.getMainBySheetId(sheetId);
        if (main == null)
            throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
        String sheetKey = main.getId();

        List linkList = baseSheet.getLinkService().getLinksByMainId(sheetKey);

        request.setAttribute("sheetMain", main);
        request.setAttribute("HISTORY", linkList);

        return mapping.findForward("provinecDetail");

    }

    /**
     * 省部主工作页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showProvinceIndexPage(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        ITawSystemUserManager userMgr =
                (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        TawSystemUser user = userMgr.getUserByuserid(sessionform.getUserid());

        request.setAttribute("Username", sessionform.getUserid());
        request.setAttribute("CertID", sessionform.getUserid());
        request.setAttribute("Realname", sessionform.getUsername());
        request.setAttribute("Phone", user.getPhone());

        String url = XmlManage.getFile("/com/boco/eoms/interfaces/province/config/province-util.xml").getProperty("Interface.provinceIndexPage");

        ActionForward af = new ActionForward();
        af.setPath(url);
        af.setRedirect(true);
        return af;

    }
}
