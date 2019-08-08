package com.boco.eoms.workplan.controller;

/**
 * <p>Title: 作业计划模板struts控制管理action</p>
 * <p>Description: 作业计划模板信息各页面的管理控制,用户输入数据收集或bo类数据提�?/p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

// import com.boco.eoms.common.controller.TawSystemSessionForm;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;

import com.boco.eoms.workplan.vo.TawwpModelPlanVO;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpModelGroupManageVO;
import com.boco.eoms.workplan.mgr.ITawwpAddonsMgr;
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;
import com.boco.eoms.workplan.mgr.ITawwpModelMgr;
import com.boco.eoms.workplan.util.TawwpUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.boco.eoms.workplan.vo.TawwpUpLoadVO;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import java.io.FileOutputStream;

import com.boco.eoms.workplan.util.TawwpStaticVariable;

/*
 * import com.boco.eoms.jbzl.bo.TawValidatePrivBO; import
 * com.boco.eoms.gzjh.util.gzUtil;
 */

public class TawwpModelAction extends BaseAction {

    // 获取属性文�?
    static {
        ResourceBundle prop = ResourceBundle
                .getBundle("resources.application_zh_CN");
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {

        ActionForward myforward = null;

        // 获取请求的action属�?
        String myaction = mapping.getParameter();

        // session超时处理
        try {
            /*
             * TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
             * request. getSession().getAttribute( "sessionform"); if
             * (saveSessionBeanForm == null) { return
             * mapping.findForward("timeout"); }
             */
        } catch (Exception e) {
            e.printStackTrace();

        }

        // 根据用户请求页面请求，进行页面跳�?
        if (isCancelled(request)) {
            return mapping.findForward("cancel"); // 无效的请求，转向错误页面
        } else if ("".equalsIgnoreCase(myaction)) {
            myforward = mapping.findForward("failure"); // 条件为空，转向空�?
        } else if ("MODELLIST".equalsIgnoreCase(myaction)) {
            myforward = performModelList(mapping, form, request, response); // 列表作业计划模版列表
        } else if ("MODELADD".equalsIgnoreCase(myaction)) {
            myforward = performModelADD(mapping, form, request, response); // 模板新增
        } else if ("MODELLISTGETNODES".equalsIgnoreCase(myaction)) {// 列表作业计划模版列表--树形
            try {
                modelListGetNodes(mapping, form, request, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ("MODELSAVE".equalsIgnoreCase(myaction)) {
            myforward = performModelSave(mapping, form, request, response); // 保存作业计划模版
        } else if ("MODELEDIT".equalsIgnoreCase(myaction)) {
            myforward = performModelEdit(mapping, form, request, response); // 编辑作业计划模版
        } else if ("MODELMODIFY".equalsIgnoreCase(myaction)) {
            myforward = performModelModify(mapping, form, request, response); // 更新作业计划模版
        } else if ("MODELREMOVE".equalsIgnoreCase(myaction)) {
            myforward = performModelRemove(mapping, form, request, response); // 删除作业计划模版
        } else if ("MODELSELECT".equalsIgnoreCase(myaction)) {
            myforward = performModelSelect(mapping, form, request, response); // 获取作作业计划模版组织结�?
        } else if ("ITEMLIST".equalsIgnoreCase(myaction)) {
            myforward = performItemList(mapping, form, request, response); // 列表作业计划模版执行内容
        } else if ("ITEMSAVE".equalsIgnoreCase(myaction)) {
            myforward = performItemSave(mapping, form, request, response); // 保存作业计划模版执行内容
        } else if ("ITEMADD".equalsIgnoreCase(myaction)) {
            myforward = performItemADD(mapping, form, request, response); // 编辑作业计划模版执行内容
        } else if ("ITEMEDIT".equalsIgnoreCase(myaction)) {
            myforward = performItemEdit(mapping, form, request, response); // 编辑作业计划模版执行内容
        } else if ("ITEMMODIFY".equalsIgnoreCase(myaction)) {
            myforward = performItemModify(mapping, form, request, response); // 更新作业计划模版执行内容
        } else if ("ITEMREMOVE".equalsIgnoreCase(myaction)) {
            myforward = performItemRemove(mapping, form, request, response); // 删除作作业计划模版执行内�?
        } else if ("GROUPLIST".equalsIgnoreCase(myaction)) {
            myforward = performGroupList(mapping, form, request, response); // 获取作作业计划模版组织结�?
        } else if ("GROUPSAVE".equalsIgnoreCase(myaction)) {
            myforward = performGroupSave(mapping, form, request, response); // 获取作作业计划模版组织结�?
        } else if ("GROUPEDIT".equalsIgnoreCase(myaction)) {
            myforward = performGroupEdit(mapping, form, request, response); // 获取作作业计划模版组织结�?
        } else if ("GROUPMODIFY".equalsIgnoreCase(myaction)) {
            myforward = performGroupModify(mapping, form, request, response); // 获取作作业计划模版组织结�?
        } else if ("GROUPREMOVE".equalsIgnoreCase(myaction)) {
            myforward = performGroupRemove(mapping, form, request, response); // 获取作作业计划模版组织结�?
        } else if ("MODELEXPORT".equalsIgnoreCase(myaction)) {
            myforward = performModelExport(mapping, form, request, response); // 导出模版到excel
        } else if ("MODELIMPORT".equalsIgnoreCase(myaction)) {
            myforward = performModelImport(mapping, form, request, response); // 从excel导入到年度作业计�?
        } else if ("MODELIMPORTGX".equalsIgnoreCase(myaction)) {
            myforward = performModelImportGx(mapping, form, request, response); // 从excel导入到年度作业计�?
        } else if ("MODELIMPORTGXSAVE".equalsIgnoreCase(myaction)) {
            myforward = performModelImportSave(mapping, form, request, response); // 从excel导入到年度作业计�?
        } else if ("MODELIMPORTADD".equalsIgnoreCase(myaction)) {
            myforward = performModelImportAdd(mapping, form, request, response); // 从excel导入到年度作业计�?
        } else {
            myforward = mapping.findForward("failure"); // 无效的请求，转向错误页面
        }
        return myforward;
    }

    private ActionForward performModelADD(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) {
        // TODO Auto-generated method stub

        ITawSystemDictTypeManager tawWsDictTypeDAO = (ITawSystemDictTypeManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemDictTypeManager");

        LabelValueBean netLabelValueBean = null;

        List list = null;
        List netList = null;
        List newNetList = null;

        String[] sysType = null;
        String[] netType = null;

        Hashtable hashtable = new Hashtable(); // 创建字典类型结构对象

        Hashtable sysHashtable = new Hashtable(); // 创建系统类型对象
        Hashtable netHashtable = new Hashtable(); // 创建网元类别对象

        try {
            list = tawWsDictTypeDAO.getDictSonsByDictid(String
                    .valueOf(TawwpStaticVariable.GZJHDICTFLAG));

            // getDictTypeSelect(TawwpStaticVariable. GZJHDICTFLAG);
            // //获取作业计划的所有系统类�?

            for (int i = 0; i < list.size(); i++) {
                TawSystemDictType tawSystemDictType = new TawSystemDictType();
                tawSystemDictType = (TawSystemDictType) list.get(i);
                sysType = new String[2];
                sysType[0] = tawSystemDictType.getDictName();
                sysType[1] = tawSystemDictType.getDictId();

                sysHashtable.put(tawSystemDictType.getDictId(),
                        tawSystemDictType.getDictName());

                newNetList = new ArrayList();
                hashtable.put(sysType, newNetList);

                // netList =
                // tawWsDictBO.getWsDictSelect(Integer.parseInt(sysType[1]));
                netList = tawWsDictTypeDAO.getDictSonsByDictid(sysType[1]);

                for (int j = 0; j < netList.size(); j++) {
                    TawSystemDictType netSystemDictType = (TawSystemDictType) netList
                            .get(j);
                    netType = new String[2];
                    netType[0] = netSystemDictType.getDictName();
                    netType[1] = netSystemDictType.getDictId();
                    netHashtable.put(netSystemDictType.getDictId(),
                            netSystemDictType.getDictName());
                    newNetList.add(netType);
                }
            }
            TawwpStaticVariable.SYS_NET_INF = hashtable;
            TawwpStaticVariable.SYS_INF = sysHashtable;
            TawwpStaticVariable.NET_INF = netHashtable;
            request.setAttribute("addonslist", list);
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }

    }

    private ActionForward performItemADD(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) {
        // TODO Auto-generated method stub
        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
        try {
            List list = tawwpAddonsMgr.listAddonsByWorkPlan(); // 获取附加表集�?
            request.setAttribute("addonslist", list);
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
    }

    /**
     * 初始化节�?得到系统类型
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    private JSONArray initNodes(Hashtable hashtable) {

        Hashtable sysInfHashtable = TawwpStaticVariable.SYS_INF;

        String sysTypeId = null;

        JSONArray jsonRoot = new JSONArray();

        if (hashtable != null && hashtable.size() != 0) {
            Enumeration sysTypeEnumeration = hashtable.keys();
            while (sysTypeEnumeration.hasMoreElements()) {
                sysTypeId = (String) sysTypeEnumeration.nextElement();
                String sysType = (String) sysInfHashtable.get(sysTypeId);

                JSONObject sysTypeObj = new JSONObject();
                sysTypeObj.put("id", sysTypeId);
                sysTypeObj.put("text", sysType);
                sysTypeObj.put(UIConstants.JSON_NODETYPE, "sysType");
                sysTypeObj.put("allowChild", true);
                sysTypeObj.put("allowEdit", true);
                sysTypeObj.put("allowDelete", true);
                sysTypeObj.put("leaf", false);
                sysTypeObj.put("iconCls", "folder");
                if (sysType != null) {
                    jsonRoot.put(sysTypeObj);
                }
            }
        }
        return jsonRoot;
    }

    /**
     * 获得子结�?得到网元类型
     *
     * @param hashtable
     * @param sysTypeId
     * @return
     */
    private JSONArray getSubNodes(Hashtable hashtable, String sysTypeId) {
        JSONArray jsonRoot = new JSONArray();
        Hashtable netInfHashtable = TawwpStaticVariable.NET_INF;

        Hashtable netHashtable = null;
        Enumeration netTypeEnumeration = null;
        String netTypeId = null;

        netHashtable = (Hashtable) hashtable.get(sysTypeId);
        netTypeEnumeration = netHashtable.keys();
        while (netTypeEnumeration.hasMoreElements()) {
            netTypeId = (String) netTypeEnumeration.nextElement();
            String netType = (String) netInfHashtable.get(netTypeId);

            JSONObject netTypeObj = new JSONObject();
            netTypeObj.put("id", netTypeId);
            netTypeObj.put("text", netType);
            netTypeObj.put(UIConstants.JSON_NODETYPE, "netType");
            netTypeObj.put("allowChild", true);
            netTypeObj.put("allowEdit", true);
            netTypeObj.put("allowDelete", true);
            netTypeObj.put("leaf", true);
            netTypeObj.put("iconCls", "file");
            if (netType != null) {
                jsonRoot.put(netTypeObj);
            }
        }
        return jsonRoot;
    }

    /**
     * 生成文件夹树JSON数据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private String modelListGetNodes(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nodeId = request.getParameter("node");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        // Hashtable hashtable = TawwpModelAction.modelList(mapping, form,
        // request, response);
        Hashtable sysHashtable = TawwpStaticVariable.SYS_INF;

        JSONArray jsonRoot = new JSONArray();
        if ("-1".equals(nodeId)) { // 初始化三个节�?
            jsonRoot = initNodes(sysHashtable);
        } else {
            String nodeType = request.getParameter(UIConstants.JSON_NODETYPE);
            if ("sysType".equals(nodeType)) {
                sysHashtable = TawwpUtil.getNetypeTable();
                jsonRoot = getSubNodes(sysHashtable, nodeId);
                // } else if ("myShare".equals(nodeType)) {
                // jsonRoot = getMyShareNodes(userId);
                // } else if ("shareToMe".equals(nodeType)) {
                // jsonRoot = initShareToMeNodes(userId);
                // } else if ("shareToMeNodes".equals(nodeType)) {
                // jsonRoot = getShareToMeNodes(nodeId, userId);
            }
            System.out.println(nodeType);
        }
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
        return null;
    }

    private static Hashtable modelList(ActionMapping mapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); // 当前用户

        // 权限验证
        try {
            TawSystemAssignBo tawValidatePrivBO = TawSystemAssignBo
                    .getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) ApplicationContextHolder
                .getInstance().getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?
        try {
            Hashtable hashtable = tawwpModelMgr.listModeLPlan(); // 获取作业计划模版集合
            return hashtable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ActionForward performModelList(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {

        request.getSession().setAttribute("path", mapping.getPath());
        request.getSession().setAttribute("Mtype",
                request.getParameter("Mtype"));
        // System.out.println(request.getParameter("netTypeId"));

        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); // 当前用户

        // 权限验证
        try {
            // TawValidatePrivBO tawValidatePrivBO = new TawValidatePrivBO();

            TawSystemAssignBo tawValidatePrivBO = TawSystemAssignBo
                    .getInstance();
            /*
             * if (tawValidatePrivBO.hasPermission4region(userId,"") == false) {
             * return mapping.findForward("nopriv"); //转向无权限页�?}
             */
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure"); // 异常页面
        }
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        try {
            Hashtable hashtable = tawwpModelMgr.listModeLPlan(); // 获取作业计划模版集合

            request.setAttribute("modelhash", hashtable);
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performModelSave(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); // 当前用户

        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String sysTypeId = request.getParameter("systype"); // 系统类型
        String netTypeId = request.getParameter("nettype"); // 网元类别
        String name = request.getParameter("name"); // 模版名称
        String typeIndex = request.getParameter("typeIndex");// 类型名称
        try {
            tawwpModelMgr.addModelPlan(name, sysTypeId, netTypeId, typeIndex,
                    userId); // 增加作业计划模版
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            tawwpLogMgr.addLog(userId, "removeModelPlan", "");

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performModelEdit(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String mondelPlanId = request.getParameter("modelplanid"); // 作业计划模板标识
        TawwpModelPlanVO tawwpModelPlanVO = null;

        try {
            tawwpModelPlanVO = tawwpModelMgr.viewModeLPlan(mondelPlanId); // 显示作业计划模版
            request.setAttribute("modelplan", tawwpModelPlanVO);
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performModelModify(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String mondelPlanId = request.getParameter("modelplanid"); // 作业计划模板标识
        String name = request.getParameter("name"); // 模版名称
        String typeIndex = request.getParameter("typeIndex"); // 模版名称

        try {
            tawwpModelMgr.editModelPlan(typeIndex, name, mondelPlanId); // 显示作业计划模版
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "editModelPlan", "");

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performModelRemove(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String mondelPlanId = request.getParameter("modelplanid"); // 作业计划模板标识

        try {
            tawwpModelMgr.removeModelPlan(mondelPlanId); // 显示作业计划模版
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "removeModelPlan", "");

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performItemList(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String mondelPlanId = request.getParameter("modelplanid"); // 作业计划模板标识
        TawwpModelPlanVO tawwpModelPlanVO = null;

        try {
            tawwpModelPlanVO = tawwpModelMgr.viewModeLPlan(mondelPlanId); // 显示作业计划模版
            request.setAttribute("modelplan", tawwpModelPlanVO);
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performItemSave(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {

        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); // 当前用户

        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String modelPlanId = request.getParameter("modelplanid"); // 作业计划模板标识
        String name = request.getParameter("name"); // 模版名称

        String botype = request.getParameter("botype"); // 业务名称
        String executedeptlevel = request.getParameter("executedeptlevel"); // 执行单位级别
        String appdesc = request.getParameter("appdesc"); // 适用说明

        String cycle = request.getParameter("cycle"); // 周期
        String format = request.getParameter("format"); // 格式
        String formId = request.getParameter("formid"); // 填写表格
        String groupId = request.getParameter("groupid"); // 填写表格
        String isHoliday = request.getParameter("isHoliday");// 假日是否排班
        String isWeekend = request.getParameter("isWeekend");// 周末是否排班
        String fileFlag = request.getParameter("fileFlag");// 是否必须上传附件
        String remark = request.getParameter("remark"); // 执行帮助
        String note = request.getParameter("note"); // 备注
        String accessories = request.getParameter("accessories"); // 指导文件
        String executeDay = StaticMethod.nullObject2String(request.getParameter("executeDay"));// 执行日期（用户选择?
        String taskid = request.getParameter("taskid");
        String monthFlag = request.getParameter("monthFlag");
        int executeTimes = StaticMethod.null2int(request
                .getParameter("executeTimes"), 0);
        ActionForward actionForward = null;

        try {
            tawwpModelMgr.addModelExecute(name, botype, executedeptlevel,
                    appdesc, modelPlanId, groupId, cycle, remark, note, accessories,
                    formId, format, userId, isHoliday, isWeekend, fileFlag,
                    executeTimes, executeDay, taskid, monthFlag);
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            tawwpLogMgr.addLog(userId, "removeModelExecute", "");

            // 返回到年度作业计划管理页�?
            actionForward = new ActionForward(
                    "/tawwpmodel/itemlist.do?modelplanid=" + modelPlanId);
            actionForward.setRedirect(true);

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return actionForward;
    }

    private ActionForward performItemEdit(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String modelExecuteId = request.getParameter("modelexecuteid"); // 作业计划模板执行内容标识
        TawwpModelExecuteVO tawwpModelExecuteVO = null;
        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");

        try {

            tawwpModelExecuteVO = tawwpModelMgr
                    .viewModeLExecute(modelExecuteId); // 显示作业计划模版执行内容
            List list = tawwpAddonsMgr.listAddonsByWorkPlan(); // 获取附加表集�?
            request.setAttribute("addonslist", list);
            request.setAttribute("modelexecute", tawwpModelExecuteVO);
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performItemModify(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String modelExecuteId = request.getParameter("modelexecuteid"); // 作业计划模板执行内容标识
        String name = request.getParameter("name"); // 模版名称

        String botype = request.getParameter("botype"); // 业务名称
        String executedeptlevel = request.getParameter("executedeptlevel"); // 执行单位级别
        String appdesc = request.getParameter("appdesc"); // 适用说明

        String cycle = request.getParameter("cycle"); // 周期
        String format = request.getParameter("format"); // 格式
        String formId = request.getParameter("formid"); // 填写表格
        String isHoliday = request.getParameter("isHoliday");// 是否假日排班
        String isWeekend = request.getParameter("isWeekend");// 是否节日排班
        String fileFlag = request.getParameter("fileFlag");// 是否必须上传附件
        String remark = request.getParameter("remark"); // 执行帮助
        String note = request.getParameter("note"); // 备注
        String accessories = request.getParameter("accessories"); // 指导文件
        String executeDay = StaticMethod.nullObject2String(request.getParameter("executeDay"));// 执行日期（用户选择?
        String taskid = request.getParameter("taskid");// 任务ID
        String monthFlag = request.getParameter("monthFlag");//  月度标志
        try {
            tawwpModelMgr.editModelExecute(modelExecuteId, name, botype,
                    executedeptlevel, appdesc, cycle, remark, note, accessories,
                    formId, format, isHoliday, isWeekend, fileFlag, executeDay, monthFlag, taskid); // 修改作业计划模版执行内容
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "editModelExecute", "");

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performItemRemove(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String modelExecuteId = request.getParameter("modelexecuteid"); // 作业计划模板执行内容标识

        try {
            tawwpModelMgr.removeModelExecute(modelExecuteId); // 删除作业计划模版执行内容
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "removeModelExecute", "");

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performGroupList(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?
        TawwpModelGroupManageVO tawwpModelGroupManageVO = null;

        String modelPlanId = request.getParameter("modelplanid"); // 作业计划模板标识
        String modelGroupId = request.getParameter("modelgroupid"); // 作业计划组织标识

        try {
            tawwpModelGroupManageVO = tawwpModelMgr.viewGroupList(modelPlanId); // 获取作业计划模版组织结构
            request.setAttribute("modelgroup", tawwpModelGroupManageVO);

            if (modelGroupId != null) {
                request.setAttribute("modelgroupid", modelGroupId);
            }
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performGroupSave(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String modelPlanId = request.getParameter("modelplanid"); // 作业计划模板标识
        String fatherGroupId = request.getParameter("fathergroupid"); // 父作业计划组织标�?
        String name = request.getParameter("name"); // 作业计划模板组织名称
        try {
            if (fatherGroupId != null) {
                tawwpModelMgr.addModelGroup(name, fatherGroupId, null); // 保存作业计划模版组织结构
            } else {
                tawwpModelMgr.addModelGroup(name, fatherGroupId, modelPlanId); // 保存作业计划模版组织结构
            }
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "addModelGroup", "");

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }

        ActionForward actionForward = null;

        // 返回到年度作业计划组织管理页�?
        actionForward = new ActionForward(
                "/tawwpmodel/grouplist.do?modelplanid=" + modelPlanId);
        actionForward.setRedirect(true);
        return actionForward;
    }

    private ActionForward performGroupEdit(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String modelGroupId = request.getParameter("modelgroupid"); // 作业计划组织标识

        try {
            tawwpModelMgr.viewModeLGroup(modelGroupId); // 获取作业计划模版组织结构
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performGroupModify(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String modelGroupId = request.getParameter("modelgroupid"); // 作业计划组织标识
        String name = request.getParameter("name"); // 作业计划模板组织名称
        try {
            tawwpModelMgr.editModelGroup(name, modelGroupId); // 修改作业计划模版组织结构
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "editModelGroup", "");

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performGroupRemove(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?

        String modelGroupId = request.getParameter("modelgroupid"); // 作业计划组织标识

        try {
            tawwpModelMgr.removeModelGroup(modelGroupId); // 删除作业计划模版组织结构
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "removeModelGroup", "");

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performModelSelect(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?
        String netTypeId = request.getParameter("nettype"); // 网元类别
        List list = null;

        try {
            list = tawwpModelMgr.listModeLPlanByNetType(netTypeId); // 获取模板列表
            request.setAttribute("tawwpmodellist", list);
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performModelExport(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?
        String modelid = request.getParameter("modelid"); // 模版名称
        String reaction = request.getParameter("reaction");
        String _exportUrl = tawwpModelMgr.exportModelToExcel(modelid);
        // 日志
        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
        tawwpLogMgr.addLog(userId, "exportModelToExcel", "");

        // 返回页面
        ActionForward actionForward = new ActionForward(reaction + "?url="
                + _exportUrl);
        actionForward.setRedirect(true);

        return actionForward;

    }

    private ActionForward performModelImport(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?
        String reaction = request.getParameter("reaction");
        String sysTypeId = request.getParameter("systype"); // 系统类型
        String netTypeId = request.getParameter("nettype"); // 网元类别
        String typeIndex = request.getParameter("typeIndex");

        if (netTypeId != null && !netTypeId.equals("")) {
            reaction = reaction + "?Mtype=" + netTypeId;
        }

        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); // 当前用户
        ActionMessages messages = new ActionMessages();
        if (actionForm instanceof TawwpUpLoadVO) { // 如果form是uploadsForm
            String encoding = request.getCharacterEncoding();
            if ((encoding != null) && (encoding.equalsIgnoreCase("utf-8"))) {
                response.setContentType("text/html; charset=gb2312"); // 如果没有指定编码，编码格式为gb2312
            }
            TawwpUpLoadVO theForm = (TawwpUpLoadVO) actionForm;
            FormFile file = theForm.getTheFile(); // 取得上传的文�?

            // 如果文件不为�?
            if (file != null) {
                // 如果扩展名正�?
                if (TawwpUtil.upCase(file.getFileName()).endsWith("xls")) {
                    String timeTag = TawwpUtil
                            .getCurrentDateTime("yyyy_MM_dd_HHmmss");
                    // 写数�?
                    try {
                        InputStream stream = file.getInputStream(); // 把文件读�?
                        String filePath = request.getRealPath("/"); // 取当前系统路�?
                        TawwpUtil
                                .mkDir(filePath + TawwpStaticVariable.tmpUpDir);
                        String sourceExcel = (filePath
                                + TawwpStaticVariable.tmpUpDir + timeTag + ".xls");
                        OutputStream bos = new FileOutputStream(sourceExcel); // 建立一个上传文件的输出�?

                        int bytesRead = 0;
                        byte[] buffer = new byte[8192];
                        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                            bos.write(buffer, 0, bytesRead); // 将文件写入服务器
                        }
                        bos.close();
                        stream.close();
                        tawwpModelMgr.importModelFromExcel(sourceExcel,
                                sysTypeId, netTypeId, userId, typeIndex);
                        // 日志
                        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
                        tawwpLogMgr.addLog(userId, "importModelFromExcel", "");

                    } catch (Exception e) {
                        System.err.print(e);
                        messages.add(ActionMessages.GLOBAL_MESSAGE,
                                new ActionMessage("submit.title.failure"));
                        saveMessages(request, messages);
                        return mapping.findForward("failure");
                    }
                    // 返回页面
                    ActionForward actionForward = new ActionForward(reaction);
                    actionForward.setRedirect(true);
                    return actionForward;

                } else {
                    ActionErrors errors = new ActionErrors();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
                            "errors.detail", "文件格式错误"));
                    saveErrors(request, errors);
                    messages.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("submit.title.failure"));
                    saveMessages(request, messages);
                    return mapping.findForward("failure");
                }
            } else {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "submit.title.failure"));
                saveMessages(request, messages);
                return mapping.findForward("failure");
            }
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }

    }

    /**
     * 导入增加界面
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     */
    private ActionForward performModelImportAdd(ActionMapping mapping,
                                                ActionForm actionForm, HttpServletRequest request,
                                                HttpServletResponse response) {
        return mapping.findForward("success");
    }

    /**
     * 管理错误信息，如果有异常出现，则进行处理，将事务回滚
     *
     * @param request HttpServletRequest 请求对象
     * @param e       Exception 异常对象
     */
    private void generalError(HttpServletRequest request, Exception e) {
        ActionMessages aes = new ActionMessages();
        aes.add("EOMS_WORKPLAN_ERROR", new ActionMessage("error.general", e
                .getMessage()));
        saveMessages(request, aes);
        BocoLog.error(this, 2, "[WORKPLAN_MODEL] Error -", e);
    }

    /**
     * 模版导入新增
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    private ActionForward performModelImportGx(ActionMapping mapping,
                                               ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) {
        request.setAttribute("Mtypeid", request.getParameter("Mtypeid"));
        return mapping.findForward("success");
    }


    /**
     * 保存
     *
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performModelImportSave(ActionMapping mapping,
                                                 ActionForm actionForm, HttpServletRequest request,
                                                 HttpServletResponse response) {

        ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr"); // 初始化作业计划模板管理BO�?
        String reaction = request.getParameter("reaction");
        String sysTypeId = request.getParameter("systype"); // 系统类型
        String netTypeId = request.getParameter("nettype"); // 网元类别
        String typeIndex = request.getParameter("typeIndex");


        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); // 当前用户
        ActionMessages messages = new ActionMessages();
        if (actionForm instanceof TawwpUpLoadVO) { // 如果form是uploadsForm
            String encoding = request.getCharacterEncoding();
            if ((encoding != null) && (encoding.equalsIgnoreCase("utf-8"))) {
                response.setContentType("text/html; charset=gb2312"); // 如果没有指定编码，编码格式为gb2312
            }
            TawwpUpLoadVO theForm = (TawwpUpLoadVO) actionForm;
            FormFile file = theForm.getTheFile(); // 取得上传的文�?

            // 如果文件不为�?
            if (file != null) {
                // 如果扩展名正�?
                if (TawwpUtil.upCase(file.getFileName()).endsWith("xls")) {
                    String timeTag = TawwpUtil
                            .getCurrentDateTime("yyyy_MM_dd_HHmmss");
                    // 写数�?
                    try {
                        InputStream stream = file.getInputStream(); // 把文件读�?
                        String filePath = request.getRealPath("/"); // 取当前系统路�?
                        TawwpUtil
                                .mkDir(filePath + TawwpStaticVariable.tmpUpDir);
                        String sourceExcel = (filePath
                                + TawwpStaticVariable.tmpUpDir + timeTag + ".xls");
                        OutputStream bos = new FileOutputStream(sourceExcel); // 建立一个上传文件的输出�?

                        int bytesRead = 0;
                        byte[] buffer = new byte[8192];
                        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                            bos.write(buffer, 0, bytesRead); // 将文件写入服务器
                        }
                        bos.close();
                        stream.close();

                        String returnFlag = tawwpModelMgr.importModelFromExcel(sourceExcel, userId);
                        if (!"".equals(returnFlag)) {
                            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("submit.sysnet.failure"));
                            saveMessages(request, messages);
                            return mapping.findForward("failure");
                        }
                        // 日志
                        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
                        tawwpLogMgr.addLog(userId, "importModelFromExcel", "");

                    } catch (Exception e) {
                        System.err.print(e);
                        messages.add(ActionMessages.GLOBAL_MESSAGE,
                                new ActionMessage("submit.title.failure"));
                        saveMessages(request, messages);
                        return mapping.findForward("failure");
                    }
                    // 返回页面
                    ActionForward actionForward = new ActionForward(reaction);
                    actionForward.setRedirect(true);
                    return actionForward;

                } else {
                    ActionErrors errors = new ActionErrors();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
                            "errors.detail", "文件格式错误"));
                    saveErrors(request, errors);
                    messages.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("submit.title.failure"));
                    saveMessages(request, messages);
                    return mapping.findForward("failure");
                }
            } else {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "submit.title.failure"));
                saveMessages(request, messages);
                return mapping.findForward("failure");
            }
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }

    }
}
