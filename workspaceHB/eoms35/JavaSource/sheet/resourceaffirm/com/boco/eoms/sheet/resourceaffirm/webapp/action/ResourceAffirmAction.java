// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ResourceAffirmAction.java

package com.boco.eoms.sheet.resourceaffirm.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSSecutiryServiceImpl;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.resourceaffirm.model.ResourceAffirmMain;

import java.io.PrintStream;
import java.util.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;

public class ResourceAffirmAction extends SheetAction {

    public ResourceAffirmAction() {
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

    public ActionForward performDeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performDeal(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        if (sheetKey.equals("")) {
            System.out.println("sheetKey is null");
        } else {
            ResourceAffirmMain main = (ResourceAffirmMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
            if (main.getIsCustomerFlag() != null && main.getIsCustomerFlag().equals("1")) {
                String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/resourceAffirm-crm.xml").getProperty("base.SendImmediately"));

                if (sendImmediately.equalsIgnoreCase("true")) {

                    String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
                    String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
                    String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
                    String phaseType = "";
                    String sType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBtype1());
                    int serviceType = 0;
                    try {
                        serviceType = Integer.valueOf(sType).intValue();
                    } catch (Exception err) {
                        System.out.println("serviceType类型错误，serviceType=" + sType);
                    }
                    String attachRef = CrmLoader.createAttachRefXml(attach);
                    if (taskName.equals("ExcuteHumTask") && operateType.equals("4")) {
                        String nodeName = "withdrawWorkSheet";
                        String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(request.getParameterMap(), StaticMethod.getFilePathForUrl("classpath:config/resourceAffirm-crm.xml"), nodeName);
                        CrmLoader.withdrawWorkSheet(31, serviceType, main.getParentCorrelation(), opDetail, attachRef);
                    } else if (operateType.equals("9")) {
                        String nodeName = "notifyWorkSheet";
                        String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(request.getParameterMap(), StaticMethod.getFilePathForUrl("classpath:config/resourceAffirm-crm.xml"), nodeName);
                        CrmLoader.notifyWorkSheet(31, serviceType, main.getParentCorrelation(), opDetail, attachRef);
                    } else if (operateType.equals("205") || operateType.equals("208")) {
                        String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
                        ITaskService iTaskService = (ITaskService) ApplicationContextHolder.getInstance().getBean("iresourceaffirmTaskManager");
                        ITask task = iTaskService.getSinglePO(taskId);
                        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
                        WPSSecutiryServiceImpl safeService = new WPSSecutiryServiceImpl();
                        javax.security.auth.Subject subject = safeService.logIn(sessionform.getUserid(), "111");
                        HashMap sessionMap = new HashMap();
                        sessionMap.put("wpsSubject", subject);
                        sessionMap.put("userId", sessionform.getUserid());
                        sessionMap.put("password", "111");
                        Map variableMap = baseSheet.getBusinessFlowService().getVariable(task.getProcessId(), "parentCorrKey", sessionMap);
                        String parentCorrKey = StaticMethod.nullObject2String(variableMap.get("parentCorrKey"));
                        List chNameList = new ArrayList();
                        List enNameList = new ArrayList();
                        List contentList = new ArrayList();
                        if (!parentCorrKey.equalsIgnoreCase(main.getCorrelationKey())) {
                            enNameList.add("returnType");
                            chNameList.add("回复类型");
                            contentList.add("1");
                        } else {
                            enNameList.add("returnType");
                            chNameList.add("回复类型");
                            contentList.add("0");
                        }
                        String nodeName = "replyWorkSheet";
                        String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(request.getParameterMap(), StaticMethod.getFilePathForUrl("classpath:config/resourceAffirm-crm.xml"), nodeName, chNameList, enNameList, contentList);
                        CrmLoader.replyWorkSheet(31, serviceType, main.getParentCorrelation(), opDetail, attachRef);
                    }

                }
            }
        }
        return mapping.findForward("success");
    }

    public ActionForward performClaimTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("into performClaimTask ............");
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performClaim(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        if (sheetKey.equals("")) {
            System.out.println("sheetKey is null");
        } else {
            ResourceAffirmMain main = (ResourceAffirmMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
            if (main.getIsCustomerFlag() != null && main.getIsCustomerFlag().equals("1")) {
                String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/resourceAffirm-crm.xml").getProperty("base.SendImmediately"));

                if (sendImmediately.equalsIgnoreCase("true")) {
                    String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
                    String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
                    String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
                    String attachRef = CrmLoader.createAttachRefXml(attach);
                    String sType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBtype1());
                    int serviceType = 0;
                    try {
                        serviceType = Integer.valueOf(sType).intValue();
                    } catch (Exception err) {
                        System.out.println("serviceType类型错误，serviceType=" + sType);
                    }
                    System.out.println("taskName:" + taskName);
                    System.out.println("operateType:" + operateType);
                    if (taskName.equals("ExcuteHumTask") && operateType.equals("61")) {
                        String nodeName = "confirmWorkSheet";
                        String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(request.getParameterMap(), StaticMethod.getFilePathForUrl("classpath:config/resourceAffirm-crm.xml"), nodeName);
                        CrmLoader.confirmWorkSheet(31, serviceType, main.getParentCorrelation(), opDetail, attachRef);
                    }
                }

            }
        }
        return mapping.findForward("detail");
    }

    public ActionForward performProcessEvent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("into performProcessEvent ............");
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performProcessEvent(mapping, form, request, response);
        try {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            if (sheetKey.equals("")) {
                System.out.println("sheetKey is null");
            } else {
                System.out.println("sheetKey=" + sheetKey);
                ResourceAffirmMain main = (ResourceAffirmMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
                System.out.println("MainInterfaceSheetType=" + main.getIsCustomerFlag());
                if (main.getIsCustomerFlag() != null && main.getIsCustomerFlag().equals("1")) {
                    String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
                    String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
                    String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
                    String attachRef = CrmLoader.createAttachRefXml(attach);
                    String sType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBtype1());
                    int serviceType = 0;
                    try {
                        serviceType = Integer.valueOf(sType).intValue();
                    } catch (Exception err) {
                        System.out.println("serviceType类型错误，serviceType=" + sType);
                    }
                    System.out.println("taskName:" + taskName);
                    System.out.println("operateType:" + operateType);
                    if (operateType.equals("9")) {
                        String nodeName = "notifyWorkSheet";
                        String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(request.getParameterMap(), StaticMethod.getFilePathForUrl("classpath:config/resourceAffirm-crm.xml"), nodeName);
                        CrmLoader.notifyWorkSheet(31, serviceType, main.getParentCorrelation(), opDetail, attachRef);
                    }
                }
            }
        } catch (Exception err) {
            System.out.println("调用crm接口失败");
            err.printStackTrace();
        }
        return mapping.findForward("success");
    }
}
