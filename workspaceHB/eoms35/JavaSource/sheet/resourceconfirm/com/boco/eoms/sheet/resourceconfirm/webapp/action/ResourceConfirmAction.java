// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ResourceConfirmAction.java

package com.boco.eoms.sheet.resourceconfirm.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.service.bo.IrmsResourceBo;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.order.webapp.action.OrderSheetMethod;
import com.boco.eoms.businessupport.product.model.TrancePoint;
import com.boco.eoms.businessupport.product.service.TrancePointMgr;
import com.boco.eoms.businessupport.serviceprepare.mgr.ServicePrepareMgr;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks;
import com.boco.eoms.businessupport.serviceprepare.model.ProfessionalServiceDirectory;
import com.boco.eoms.businessupport.util.Constants;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.model.*;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmMain;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmMainManager;

import java.io.PrintStream;
import java.util.*;
import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.*;

public class ResourceConfirmAction extends SheetAction {

    public ResourceConfirmAction() {
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

    public ActionForward showInitializeTaskPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr) ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        String serviceId = StaticMethod.nullObject2String(request.getParameter("serviceId"));
        String specialtyId = StaticMethod.nullObject2String(request.getParameter("specialtyId"));
        ProfessionalServiceDirectory professionalServiceDirectory = servicePrepareMgr.getProfessionalServiceDirectorySinglePO(specialtyId);
        String serviceCnName = professionalServiceDirectory.getName();
        String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
        String operateRoleId = StaticMethod.nullObject2String(request.getParameter("operateRoleId"));
        String parentTaskId = StaticMethod.nullObject2String(request.getParameter("parentTaskId"));
        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
        String supportId = StaticMethod.nullObject2String(request.getParameter("supportId"));
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        BaseLink linkObject = (BaseLink) baseSheet.getLinkService().getLinkObject().getClass().newInstance();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        linkObject.setOperaterContact(sessionform.getContactMobile());
        linkObject.setOperateUserId(sessionform.getUserid());
        linkObject.setOperateDeptId(sessionform.getDeptid());
        linkObject.setOperateRoleId(operateRoleId);
        linkObject.setOperateTime(StaticMethod.getLocalTime());
        request.setAttribute("sheetLink", linkObject);
        BaseMain baseMain = baseSheet.getMainService().getSingleMainPO(sheetKey);
        if (baseMain != null)
            request.setAttribute("sheetMain", baseMain);
        request.setAttribute("sheetKey", sheetKey);
        request.setAttribute("taskName", taskName);
        request.setAttribute("serviceId", serviceId);
        request.setAttribute("serviceCnName", serviceCnName);
        request.setAttribute("phaseId", phaseId);
        request.setAttribute("parentTaskId", parentTaskId);
        request.setAttribute("preLinkId", preLinkId);
        request.setAttribute("supportId", supportId);
        return mapping.findForward("InitializeTask");
    }

    public ActionForward newTaskPerformAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        HashMap columnMap = baseSheet.getInterfaceObjMap(mapping, form, request, response);
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
        String linkDevicePort = StaticMethod.nullObject2String(request.getParameter("linkDevicePort"));
        String addr = "";
        if (!linkDevicePort.equals("")) {
            TrancePointMgr businessupportMgr = (TrancePointMgr) ApplicationContextHolder.getInstance().getBean("businessupportMgr");
            TrancePoint tp = businessupportMgr.getBusinessupport(linkDevicePort);
            if (tp != null && tp.getPortDetailAdd() != null)
                addr = tp.getPortDetailAdd();
        }
        baseSheet.setFlowEngineMap(WpsMap);
        baseSheet.dealFlowEngineMap(mapping, form, request, response);
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());
        baseSheet.getBusinessFlowService().initProcess(processTemplateName, operateName, baseSheet.getFlowEngineMap(), sessionMap);
        String beanId = StaticMethod.null2String(request.getParameter("beanId"));
        ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr) ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");
        ITawSystemWorkflowManager tawSystemWorkflowManager = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
        TawSystemWorkflow tawSystemWorkflow = tawSystemWorkflowManager.getTawSystemWorkflowByBeanId(beanId);
        String flowId = tawSystemWorkflow.getFlowId();
        HashMap sheetMap = baseSheet.getFlowEngineMap();
        Map linkMap = (HashMap) sheetMap.get("link");
        Map operateMap = (HashMap) sheetMap.get("operate");
        String serviceId = StaticMethod.null2String(request.getParameter("serviceId"));
        String serviceCnName = StaticMethod.null2String(request.getParameter("serviceCnName"));
        String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
        String parentTaskId = StaticMethod.null2String(request.getParameter("parentTaskId"));
        ProcessTasks processTasks = new ProcessTasks();
        processTasks.setSheetkey(sheetKey);
        processTasks.setServicId(serviceId);
        processTasks.setServiceCnName(serviceCnName);
        processTasks.setParentTaskId(parentTaskId);
        processTasks.setProcessTypeId(flowId);
        processTasks.setStatus("2");
        processTasks.setDeleted("0");
        processTasks.setCreatetime((Date) linkMap.get("operateTime"));
        processTasks.setDealRoleId(StaticMethod.nullObject2String(operateMap.get("dealPerformer")));
        processTasks.setParentLinkId(StaticMethod.nullObject2String(linkMap.get("id")));
        if (addr.length() > 0)
            processTasks.setRemark(addr);
        servicePrepareMgr.addObject(processTasks);
        return mapping.findForward("taskSuccess");
    }

    public void performPreJudge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jsonRoot = new JSONObject();
        JSONArray data = new JSONArray();
        JSONObject o = new JSONObject();
        String status = "";
        StringBuffer sb = new StringBuffer();
        StringBuffer sbFn = new StringBuffer();
        String taskId = StaticMethod.null2String(request.getParameter("aiid"));
        String taskName = StaticMethod.null2String(request.getParameter("taskName"));
        String beanId = StaticMethod.null2String(request.getParameter("beanId"));
        String linkDealType = StaticMethod.null2String(request.getParameter("linkDealType"));
        if (!linkDealType.equals("101220402")) {
            ITawSystemWorkflowManager tawSystemWorkflowManager = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
            TawSystemWorkflow tawSystemWorkflow = tawSystemWorkflowManager.getTawSystemWorkflowByBeanId(beanId);
            String flowId = tawSystemWorkflow.getFlowId();
            ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr) ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");
            List processTasksList = servicePrepareMgr.getProcessTasksByParentTaskId(taskId);
            List serviceConfigurationList = servicePrepareMgr.getAllListByCondition(flowId, taskName);
            if (processTasksList == null || processTasksList.size() == 0) {
                if (sb.toString().equals("") || sb.toString() == "") {
                    status = "0";
                } else {
                    status = "2";
                    sb.append("�������ύ��\n");
                }
            } else {
                for (int i = 0; i < processTasksList.size(); i++) {
                    ProcessTasks processTasks = (ProcessTasks) processTasksList.get(i);
                    if (processTasks.getStatus().equals("2")) {
                        Map userNameMap = SheetUtils.getUserNameForSubRole(processTasks.getDealRoleId());
                        sb.append("���񵥣�" + processTasks.getServiceCnName() + " ��δ����������Ϊ��" + userNameMap.get("subRoleName") + "\n");
                    }
                }

                if (sb.toString().equals("") || sb.toString() == "") {
                    if (sb.toString().equals("") || sb.toString() == "") {
                        status = "0";
                    } else {
                        status = "2";
                        sb.append("�������ύ��\n");
                    }
                } else {
                    status = "2";
                    sb.append("�������ύ��\n");
                }
            }
        } else {
            status = "0";
        }
        o.put("text", sb.toString());
        o.put("fn", sbFn.toString());
        data.put(o);
        jsonRoot.put("data", data);
        jsonRoot.put("status", String.valueOf(status));
        JSONUtil.print(response, jsonRoot.toString());
    }

    public void performPreValidateResource(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jsonRoot = new JSONObject();
        JSONArray data = new JSONArray();
        JSONObject o = new JSONObject();
        String status = "";
        StringBuffer sb = new StringBuffer();
        StringBuffer sbFn = new StringBuffer();
        try {
            String enableService = StaticMethod.nullObject2String(XmlManage.getFile("/com/boco/eoms/businessupport/config/resourceInterface_util.xml").getProperty("base.resourceConfirmEnable"));
            if (enableService.equalsIgnoreCase("true")) {
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                IResourceConfirmMainManager mgr = (IResourceConfirmMainManager) ApplicationContextHolder.getInstance().getBean("iResourceConfirmMainManager");
                ResourceConfirmMain main = (ResourceConfirmMain) mgr.getMainDAO().loadSinglePO(sheetKey, new ResourceConfirmMain());
                String mainSpecifyType = main.getMainSpecifyType();
                String orderSheetId = main.getOrderSheetId();
                List list = OrderSheetMethod.getSpecialLineList(orderSheetId, mainSpecifyType);
                Map orderMap = SheetBeanUtils.bean2Map(main);
                String taskName = StaticMethod.null2String(request.getParameter("taskName"));
                BocoLog.info(this, "taskName:" + taskName + mainSpecifyType);
                orderMap.put("serviceType", taskName + mainSpecifyType);
                for (int i = 0; i < list.size(); i++) {
                    List tempList = new ArrayList();
                    tempList.add(list.get(i));
                    IrmsResourceBo.preOccupyResFinish(orderMap, tempList);
                }

            } else {
                status = "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb.append("�ʹ�ϵͳУ������ʧ��,�����ύ,�뽫������д������" + e.getMessage());
            status = "2";
        }
        o.put("text", sb.toString());
        o.put("fn", sbFn.toString());
        data.put(o);
        jsonRoot.put("data", data);
        jsonRoot.put("status", String.valueOf(status));
        JSONUtil.print(response, jsonRoot.toString());
    }

    public void validateData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jsonRoot = new JSONObject();
        JSONArray data = new JSONArray();
        JSONObject o = new JSONObject();
        String status = "0";
        StringBuffer sb = new StringBuffer();
        StringBuffer sbFn = new StringBuffer();
        try {
            String taskName = StaticMethod.null2String(request.getParameter("taskName"));
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            IResourceConfirmMainManager mgr = (IResourceConfirmMainManager) ApplicationContextHolder.getInstance().getBean("iResourceConfirmMainManager");
            ResourceConfirmMain main = (ResourceConfirmMain) mgr.getSingleMainPO(sheetKey);
            if (!main.getMainSpecifyType().equals(Constants._TRANSFER_LINE)) {
                String orderSheetId = main.getOrderSheetId();
                IOrderSheetManager iOrderSheetManager = (IOrderSheetManager) ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
                boolean b = iOrderSheetManager.validateProductData(orderSheetId, taskName);
                System.out.println("bababababababa:" + b);
                if (!b) {
                    sb.append("�����ύ,�뽫ר��������д������");
                    status = "2";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb.append("����У��ʧ�ܣ�" + e.getMessage());
            status = "2";
        }
        o.put("text", sb.toString());
        o.put("fn", sbFn.toString());
        data.put(o);
        jsonRoot.put("data", data);
        jsonRoot.put("status", String.valueOf(status));
        JSONUtil.print(response, jsonRoot.toString());
    }

    public ActionForward showHistoryPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetNo"), "");
        String userid = StaticMethod.nullObject2String(request.getParameter("userName"), "");
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        IResourceConfirmMainManager mgr = (IResourceConfirmMainManager) baseSheet.getMainService();
        List list = mgr.getMainListByParentSheetId(sheetId);
        if (list != null && list.size() > 0) {
            BaseMain main = (BaseMain) list.get(0);
            String sheetKey = main.getId();
            if (!sheetKey.equals("")) {
                if ("".equals(userid))
                    userid = "admin";
                try {
                    IWorkflowSecutiryService safeService = (IWorkflowSecutiryService) ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
                    javax.security.auth.Subject subject = safeService.logIn(userid, "");
                    request.getSession().setAttribute("wpsSubject", subject);
                } catch (Exception e) {
                    BocoLog.error(this, "�������̵�½��Ϣ����" + e.getMessage());
                }
                ActionForward forward = mapping.findForward("showInterfaceDraftPage");
                String path = forward.getPath() + "&sheetKey=" + sheetKey;
                System.out.println("path=" + path);
                return new ActionForward(path, false);
            } else {
                throw new Exception("sheetNo����Ϊ��");
            }
        } else {
            throw new Exception("û�ҵ���Ӧ��������ȷ�ϱ����ȷ");
        }
    }

    public ActionForward showTnmsPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("tnms");
    }
}
