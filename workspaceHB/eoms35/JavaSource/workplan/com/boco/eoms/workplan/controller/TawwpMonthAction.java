package com.boco.eoms.workplan.controller;

/**
 * <p>Title: 月度作业计划Action�?/p>
 * <p>Description: 负责页面数据的显示、组织及获取�?/p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.workplan.bo.TawwpUtilBO;
import com.boco.eoms.workplan.dao.ITawwpYearPlanDao;
import com.boco.eoms.workplan.mgr.ITawwpAddonsMgr;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;
import com.boco.eoms.workplan.mgr.ITawwpMonthMgr;
import com.boco.eoms.workplan.mgr.ITawwpNetMgr;
import com.boco.eoms.workplan.mgr.ITawwpTaskManageInterfaceMgr;
import com.boco.eoms.workplan.mgr.ITawwpYearPlanMgr;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpYearPlan;
import com.boco.eoms.workplan.util.Inspection;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpMonthExecuteVO;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;
import com.boco.eoms.workplan.vo.TawwpNetVO;

public class TawwpMonthAction extends BaseAction {

    // 获取属性文�?
    static {
        ResourceBundle prop = ResourceBundle
                .getBundle("resources.application_zh_CN");
    }

    /**
     * 执行控制方法，以跳转到实际Action
     *
     * @param mapping  ActionMapping 集合
     * @param form     ActionForm 数据模型
     * @param request  HttpServletRequest 请求
     * @param response HttpServletResponse 应答
     * @return ActionForward 转向对象
     * @throws Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionForward myforward = null;

        // 获取请求的action属�?
        String myaction = mapping.getParameter();

        // session超时处理
        try {
            /*
             * System.out.print(request. getSession().getAttribute(
             * "sessionform")); TawSystemSessionForm tawSystemSessionForm =
             * (TawSystemSessionForm) request. getSession().getAttribute(
             * "sessionform");
             *
             *
             *
             * if (tawSystemSessionForm == null) { return
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
        } else if ("MONTHLIST".equalsIgnoreCase(myaction)) {
            myforward = performMonthList(mapping, form, request, response); // 显示月度作业计划列表页面
        } else if ("MONTHLISTGETNODES".equalsIgnoreCase(myaction)) {
            monthListGetNodes(mapping, form, request, response); // 显示月度作业计划列表页面--树形
        } else if ("NETSELECT".equalsIgnoreCase(myaction)) {
            myforward = performNetSelect(mapping, form, request, response); // 显示备选网�?
        } else if ("MONTHADD".equalsIgnoreCase(myaction)) {
            myforward = performMonthAdd(mapping, form, request, response); // 月度作业计划添加保存
        } else if ("MONTHVIEW".equalsIgnoreCase(myaction)) {
            myforward = performMonthView(mapping, form, request, response); // 显示月度作业计划浏览页面
        } else if ("MONTHEDIT".equalsIgnoreCase(myaction)) {
            myforward = performMonthEdit(mapping, form, request, response); // 显示月度作业计划编辑页面
        } else if ("MONTHEMODIFY".equalsIgnoreCase(myaction)) {
            myforward = performMonthModify(mapping, form, request, response); // 月度作业计划编辑后保�?
        } else if ("MONTHDEL".equalsIgnoreCase(myaction)) {
            myforward = performMonthDel(mapping, form, request, response); // 月度作业计划删除
        } else if ("MONTHDELETE".equalsIgnoreCase(myaction)) {
            myforward = performMonthDelete(mapping, form, request, response); // 月度作业计划删除(制定通过审批后删除)
        } else if ("MONTHCOPY".equalsIgnoreCase(myaction)) {
            myforward = performMonthCopy(mapping, form, request, response); // 月度作业计划复制
        } else if ("MONTHREFER".equalsIgnoreCase(myaction)) {
            myforward = performMonthRefer(mapping, form, request, response); // 月度作业计划提交审批
        } else if ("EXECUTEEDIT".equalsIgnoreCase(myaction)) {
            myforward = performExecuteEdit(mapping, form, request, response); // 显示月度作业计划执行内容编辑页面
        } else if ("EXECUTEMODIFY".equalsIgnoreCase(myaction)) {
            myforward = performExecuteModify(mapping, form, request, response); // 月度作业计划执行内容编辑保存
        } else if ("EXECUTEADD".equalsIgnoreCase(myaction)) {
            myforward = performExecuteAdd(mapping, form, request, response); // 显示月度作业计划执行内容增加页面
        } else if ("EXECUTESAVE".equalsIgnoreCase(myaction)) {
            myforward = performExecuteSave(mapping, form, request, response); // 月度作业计划执行内容增加后保�?
        } else if ("EXECUTEDEL".equalsIgnoreCase(myaction)) {
            myforward = performExecuteDel(mapping, form, request, response); // 月度作业计划执行内容删除
        } else if ("CHECKLIST".equalsIgnoreCase(myaction)) {
            myforward = performCheckList(mapping, form, request, response); // 月度作业计划待审批列�?
        } else if ("CHECKVIEW".equalsIgnoreCase(myaction)) {
            myforward = performCheckView(mapping, form, request, response); // 显示月度作业计划审批页面
        } else if ("CHECKPASS".equalsIgnoreCase(myaction)) {
            myforward = performCheckPass(mapping, form, request, response); // 月度作业计划审批通过
        } else if ("CHECKLISTPASS".equalsIgnoreCase(myaction)) {
            myforward = performCheckListPass(mapping, form, request, response); // 月度作业计划审批通过
        } else if ("CHECKREJECT".equalsIgnoreCase(myaction)) {
            myforward = performCheckReject(mapping, form, request, response); // 月度作业计划审批驳回
        } else if ("COMMNETSELECT".equalsIgnoreCase(myaction)) {
            myforward = performCommNetSelect(mapping, form, request, response); // 月度作业计划审批驳回
        } else if ("COMMNETSAVE".equalsIgnoreCase(myaction)) {
            myforward = performCommNetSave(mapping, form, request, response); // 月度作业计划审批驳回
        } else if ("MONTHEXPORT".equalsIgnoreCase(myaction)) {
            myforward = performMonthExport(mapping, form, request, response); // 导出年度作业计划到excel
        } else if ("EXECUTEADDTEMP".equalsIgnoreCase(myaction)) {
            myforward = performExecuteAddTemp(mapping, form, request, response); // 添加临时内容
        } else if ("EXECUTESAVETEMP".equalsIgnoreCase(myaction)) {
            myforward = performExecuteSaveTemp(mapping, form, request, response); // 保存临时内容
        } else if ("EXECUTEREFER".equalsIgnoreCase(myaction)) {
            myforward = performExecuteRefer(mapping, form, request, response); // 月执行二次派�?
        } else if ("EXECUTESEND".equalsIgnoreCase(myaction)) {
            myforward = performExecuteSend(mapping, form, request, response); // 月执行二次派发发�?
        } else if ("YEAREXPORT".equalsIgnoreCase(myaction)) {
            myforward = performYearExport(mapping, form, request, response); // 按照月导出年的
        } else if ("CHECKBATCH".equalsIgnoreCase(myaction)) {
            myforward = performCheckBatch(mapping, form, request, response); // 批量提交审批、选择负责人
        } else if ("YEARSELECT".equalsIgnoreCase(myaction)) {
            myforward = performYearSelect(mapping, form, request, response); //月度导出选择�?
//      myforward = performCheckBatch(mapping, form, request, response); //批量提交审批、选择负责�? 
//		}else if ("YEAREXPORT".equalsIgnoreCase(myaction)) {
//			myforward = performYearExport(mapping, form, request, response); // 按照
//		} else if ("YEARSELECT".equalsIgnoreCase(myaction)) {
//			myforward = performYearSelect(mapping, form, request, response); // 月度
//		} else if ("YEAREXPORT".equalsIgnoreCase(myaction)) {
//			myforward = performYearExport(mapping, form, request, response); // 按照
//		} else if ("YEARSELECT".equalsIgnoreCase(myaction)) {
//			myforward = performYearSelect(mapping, form, request, response); // 月度
            // /*
            // } else if ("MONTHREPORT".equalsIgnoreCase(myaction)) {
            // myforward = performMonthReport(mapping, form, request, response);
            // //提交总部作业计划
        } else if ("EXECUTETASK".equalsIgnoreCase(myaction)) {
            myforward = performExecuteTask(mapping, form, request, response); // 按照
        } else if ("EXECUTETASKSAVE".equalsIgnoreCase(myaction)) {
            myforward = performExecuteTaskSave(mapping, form, request, response); // 按照

        } else if ("CHECKVIEWS".equalsIgnoreCase(myaction)) {
            myforward = performCheckViews(mapping, form, request, response); // 显示月度作业计划审批页面
        } else {

            myforward = mapping.findForward("failure"); // 无效的请求，转向错误页面
        }
        return myforward;
    }

    private ActionForward performMonthRefer(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) {
        // TODO Auto-generated method stub
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        // 获取页面传递的参数
        String monthPlanId = request.getParameter("monthplanid");
        String flowSerial = TawwpUtil
                .getRequestValue(request, "flowserial", "");
        String stepSerial = TawwpUtil
                .getRequestValue(request, "stepserial", "");
        String checkUsers[] = request.getParameterValues("checkUsers");
        String checkUser = "";
        String checkType = StaticMethod.nullObject2String(request
                .getParameter("type"));
        if (checkUsers != null) {
            for (int i = 0; i < checkUsers.length; i++) {
                if (!"".equals(StaticMethod.nullObject2String(checkUsers[i]))) {
                    checkUser = checkUser + checkUsers[i] + ",";
                }
            }
            if (checkUser.length() > 1) {
                checkUser = checkUser.substring(0, checkUser.length() - 1);
            }
        }
        String checkDept = String.valueOf(TawSystemSessionForm.getDeptid());

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        ActionMessages messages = new ActionMessages();
        try {
            // 月度作业计划提交审批
            if (tawwpMonthMgr.addMonthCheck(checkDept, checkUser, monthPlanId,
                    flowSerial, stepSerial, checkType) == null) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "submit.title.nochoose"));
                saveMessages(request, messages);
                return mapping.findForward("failure"); // 转向错误页面
            }
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            tawwpLogMgr.addLog(checkUser, "addMonthCheck", "");

            // 转向月度作业计划浏览页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.nochoose"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    private ActionForward performExecuteAddTemp(ActionMapping mapping,
                                                ActionForm form, HttpServletRequest request,
                                                HttpServletResponse response) {
        // TODO Auto-generated method stub
        String monthplanid = request.getParameter("monthplanid");

        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
        List list = new ArrayList();
        try {
            list = tawwpAddonsMgr.listAddonsByWorkPlan();
            request.setAttribute("addonslist", list);
            request.setAttribute("monthplanid", monthplanid);
            return mapping.findForward("success");
        } catch (TawwpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        } // 获取附加表集�?

    }

    private ActionForward performExecuteSaveTemp(ActionMapping mapping,
                                                 ActionForm form, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        String monthplanid = request.getParameter("monthplanid");
        String name = request.getParameter("name");

        String botype = request.getParameter("botype");
        String executedeptlevel = request.getParameter("executedeptlevel");
        String appdesc = request.getParameter("appdesc");

        String cycle = request.getParameter("cycle");
        String format = request.getParameter("format");
        String isHoliday = request.getParameter("isHoliday");
        String isWeekend = request.getParameter("isWeekend");
        String formid = request.getParameter("formid");
        String remark = request.getParameter("remark");
        String fileFlag = request.getParameter("fileFlag");
        String executeDay = StaticMethod.nullObject2String(request.getParameter("executeDay"));
        String accessories = request.getParameter("accessories");
        TawSystemSessionForm tawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        // 封装月度作业计划执行内容对象
        TawwpMonthExecute tawwpMonthExecute = new TawwpMonthExecute();
        tawwpMonthExecute.setName(name);
        tawwpMonthExecute.setAccessories(accessories);
        tawwpMonthExecute.setBotype(botype);
        tawwpMonthExecute.setExecutedeptlevel(executedeptlevel);
        tawwpMonthExecute.setAppdesc(appdesc);
        tawwpMonthExecute.setCycle(cycle);
        tawwpMonthExecute.setRemark(remark);
        tawwpMonthExecute.setFormat(format);
        tawwpMonthExecute.setFormId(formid);
        tawwpMonthExecute.setDeleted("0");
        tawwpMonthExecute.setCruser(tawSystemSessionForm.getUserid());
        tawwpMonthExecute.setExecuteFlag("0");
        tawwpMonthExecute.setIsHoliday(isHoliday);
        tawwpMonthExecute.setIsWeekend(isWeekend);
        tawwpMonthExecute.setFileFlag(fileFlag);
        tawwpMonthExecute.setCrtime(TawwpUtil.getCurrentDateTime());
        tawwpMonthExecute.setTawwpExecuteContents(new HashSet());
        tawwpMonthExecute.setExecuteDay(executeDay);
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        tawwpMonthMgr.addMonthExecuteSave(monthplanid, tawwpMonthExecute);
        tawwpMonthMgr.editMonthPlanSave(monthplanid);
        return mapping.findForward("success");

    }

    /**
     * 初始化节�?
     *
     * @return
     */
    private JSONArray initNodes() {
        JSONArray jsonRoot = new JSONArray();
        JSONObject netType0 = new JSONObject();
        netType0.put("id", "0");
        netType0.put("text", "无线网");
        netType0.put(UIConstants.JSON_NODETYPE, "0");
        netType0.put("allowChild", true);
        netType0.put("allowEdit", true);
        netType0.put("allowDelete", true);
        netType0.put("leaf", true);
        netType0.put("iconCls", "folder");

        JSONObject netType1 = new JSONObject();
        netType1.put("id", "1");
        netType1.put("text", "数据网");
        netType1.put(UIConstants.JSON_NODETYPE, "1");
        netType1.put("allowChild", true);
        netType1.put("allowEdit", true);
        netType1.put("allowDelete", true);
        netType1.put("leaf", true);
        netType1.put("iconCls", "folder");

        JSONObject netType2 = new JSONObject();
        netType2.put("id", "2");
        netType2.put("text", "汇接局");
        netType2.put(UIConstants.JSON_NODETYPE, "2");
        netType2.put("allowChild", true);
        netType2.put("allowEdit", true);
        netType2.put("allowDelete", true);
        netType2.put("leaf", true);
        netType2.put("iconCls", "folder");

        JSONObject netType3 = new JSONObject();
        netType3.put("id", "3");
        netType3.put("text", "端局");
        netType3.put(UIConstants.JSON_NODETYPE, "3");
        netType3.put("allowChild", true);
        netType3.put("allowEdit", true);
        netType3.put("allowDelete", true);
        netType3.put("leaf", true);
        netType3.put("iconCls", "folder");

        JSONObject netType4 = new JSONObject();
        netType4.put("id", "4");
        netType4.put("text", "关口局");
        netType4.put(UIConstants.JSON_NODETYPE, "4");
        netType4.put("allowChild", true);
        netType4.put("allowEdit", true);
        netType4.put("allowDelete", true);
        netType4.put("leaf", true);
        netType4.put("iconCls", "folder");

        JSONObject netType5 = new JSONObject();
        netType5.put("id", "5");
        netType5.put("text", "HLR");
        netType5.put(UIConstants.JSON_NODETYPE, "5");
        netType5.put("allowChild", true);
        netType5.put("allowEdit", true);
        netType5.put("allowDelete", true);
        netType5.put("leaf", true);
        netType5.put("iconCls", "folder");

        JSONObject netType6 = new JSONObject();
        netType6.put("id", "6");
        netType6.put("text", "智能网");
        netType6.put(UIConstants.JSON_NODETYPE, "6");
        netType6.put("allowChild", true);
        netType6.put("allowEdit", true);
        netType6.put("allowDelete", true);
        netType6.put("leaf", true);
        netType6.put("iconCls", "folder");

        JSONObject netType7 = new JSONObject();
        netType7.put("id", "7");
        netType7.put("text", "其他");
        netType7.put(UIConstants.JSON_NODETYPE, "7");
        netType7.put("allowChild", true);
        netType7.put("allowEdit", true);
        netType7.put("allowDelete", true);
        netType7.put("leaf", true);
        netType7.put("iconCls", "folder");

        jsonRoot.put(netType0);
        jsonRoot.put(netType1);
        jsonRoot.put(netType2);
        jsonRoot.put(netType3);
        jsonRoot.put(netType4);
        jsonRoot.put(netType5);
        jsonRoot.put(netType6);
        jsonRoot.put(netType7);
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
    private String monthListGetNodes(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nodeId = request.getParameter("node");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        JSONArray jsonRoot = new JSONArray();
		/*Hashtable hashtable = TawwpMonthAction.monthListCode(mapping, form,
				request, response);*/
        Hashtable hashtable = TawwpStaticVariable.SYS_INF;
        if ("-1".equals(nodeId)) { // 初始化三个节�?
            jsonRoot = initNodes(hashtable);
            // jsonRoot = initNodes();
        } else {
            String nodeType = request.getParameter(UIConstants.JSON_NODETYPE);
            if ("sysType".equals(nodeType)) {
                hashtable = TawwpUtil.getNetypeTable();
                jsonRoot = getSubNodes(hashtable, nodeId);

            }
            System.out.println(jsonRoot.toString());
        }
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
        System.out.println("add by zdl@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
                + jsonRoot.toString());
        return null;

    }

    private static Hashtable monthList(ActionMapping mapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); // 当前用户

        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) ApplicationContextHolder
                .getInstance().getBean("tawwpMonthMgr"); // 初始化作业计划月计划管理BO�?
        try {
            Hashtable hashtable = tawwpMonthMgr.monthListCode(); // 获取作业计划模版集合
            return hashtable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Hashtable monthListCode(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); // 当前用户

        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) ApplicationContextHolder
                .getInstance().getBean("tawwpMonthMgr"); // 初始化作业计划月计划管理BO�?
        try {
            Hashtable hashtable = tawwpMonthMgr.monthListCode(); // 获取作业计划模版集合，改成从年计划表里查询
            return hashtable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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
     * 获得子结得到网元类型
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
     * 显示月度作业计划管理(列表)首页Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performMonthList(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {

        request.getSession().setAttribute("path", mapping.getPath());
        request.getSession().setAttribute("Mtype",
                request.getParameter("Mtype"));

        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String deptId = TawSystemSessionForm.getDeptid(); // 当前部门编号
        String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

        String switchChangeNet = StaticMethod.null2String(request
                .getParameter("flag"));
        String forword = "";
        if (!switchChangeNet.equals("")) {
            if (switchChangeNet.equals("0")) {
                forword = "success";
            } else {
                forword = "changeNet";
            }
        } else {
            forword = "success";
        }

        // 权限验证
        /*
         * try { TawSystemAssignBo tawValidatePrivBO
         * =TawSystemAssignBo.getInstance(); //月度计划管理权限 ssssssssssssss if
         * (tawValidatePrivBO.hasAssign(userId,"") == false) { return
         * mapping.findForward("nopriv"); //转向无权限页�?} if (deptId .equals("1")) {
         *
         * //如果当前用户所属部门为省公�?request.setAttribute("error", "1"); //转向提示页面 return
         * mapping.findForward("failure"); } } catch (Exception e) {
         * e.printStackTrace(); return mapping.findForward("failure"); //异常页面 }
         */

        // 获取年份,如果为空则获取当前年�?
        String year = TawwpUtil.getRequestValue(request, "yearid", TawwpUtil
                .getCurrentDateTime("yyyy"));
        // 获取月份,如果为空则获取当前月�?
        String month = TawwpUtil.getRequestValue(request, "monthid", TawwpUtil
                .getCurrentDateTime("MM"));
        if (request.getAttribute("monthid") != null) {
            month = request.getAttribute("monthid").toString();
            // 初始化数�?
        }
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        Hashtable monthPlanVOHash = null;
        Hashtable yearPlanVOHash = null;

        try {
            // 获取月度作业计划集合，如果是删除月度作业计划，则使用listMonthPlanDelete方法，如果是一般的制定，则使用listMonthPlan
            if (switchChangeNet.equals("0")) {
                monthPlanVOHash = tawwpMonthMgr.listMonthPlan(String
                        .valueOf(deptId), year, month);
            } else {
                monthPlanVOHash = tawwpMonthMgr.listMonthPlanDelete(String
                        .valueOf(deptId), year, month);
            }
            // added by lijia 2005-11-28
            yearPlanVOHash = tawwpMonthMgr.markYearPlan(String.valueOf(deptId),
                    year, month);
            // 为页面显示准备数�?
            request.setAttribute("yearid", year);
            request.setAttribute("monthid", month);
            // ------------------------------------------------
            Enumeration hashKeys = null;
            List listTemp = new ArrayList();
            List listKey = new ArrayList();
            TawwpYearPlan tawwpYearPlan = null;
            if (monthPlanVOHash.size() != 0) {
                hashKeys = monthPlanVOHash.keys();
                while (hashKeys.hasMoreElements()) {
                    tawwpYearPlan = (TawwpYearPlan) (hashKeys.nextElement());
                    if (tawwpYearPlan.getTypeIndex() == null) {
                        tawwpYearPlan.setTypeIndex("");
                    }

                    listKey.add(tawwpYearPlan);
                    tawwpYearPlan = null;
                }
//				Collections.sort(listKey);
                Collections.sort(listKey, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        TawwpYearPlan p1 = (TawwpYearPlan) o1;
                        TawwpYearPlan p2 = (TawwpYearPlan) o2;
                        int k = -1;
                        if (p1.getName().compareTo(p2.getName()) > 0) {
                            k = 1;
                        } else if (p1.getName().compareTo(p2.getName()) == 0) {
                            if (p1.getId().compareTo(p2.getId()) > 0) {
                                k = 1;
                            } else {
                                k = -1;
                            }
                        } else if (p1.getName().compareTo(p2.getName()) < 0) {
                            k = -1;
                        }
                        return k;

                    }
                });
            }
            // ----------------------------------------------------

            request.setAttribute("monthplanvohash", monthPlanVOHash);
            request.setAttribute("listKey", listKey);
            // added by lijia 2005-11-28
            request.setAttribute("yearplanvohash", yearPlanVOHash);
            // 转向月度作业计划管理(列表)页面
            return mapping.findForward(forword);
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 显示备选网�?
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performNetSelect(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String deptId = TawSystemSessionForm.getDeptid(); // 当前部门编号

        // 初始化数�?
        ITawwpNetMgr tawwpNetMgr = (ITawwpNetMgr) getBean("tawwpNetMgr");

        List netVOList = null;
        List netChangeNetList = null;
        List noSelectNetVoList = new ArrayList();
        HashMap map = new HashMap();
        String yearPlanId = "";
        String netTypeId = "";
        String monthFlag = "";
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        String forword = "";

        try {

            // 获取年度作业计划编号
            yearPlanId = TawwpUtil.getRequestValue(request, "yearplanid", "")
                    .trim();
            // 获取网元类型编号
            netTypeId = TawwpUtil.getRequestValue(request, "nettypeid", "");
            // 获取计划月度
            monthFlag = TawwpUtil.getRequestValue(request, "monthid", TawwpUtil
                    .getCurrentDateTime("MM"));
            String changeNetFlag = StaticMethod.nullObject2String(request
                    .getParameter("flag"));

            if (changeNetFlag.equals("1")) {
                // 获取网元VO对象
                netVOList = tawwpNetMgr.listNetByTypeDept(netTypeId, String
                        .valueOf(deptId), changeNetFlag);
                // added by lijia 2005-11-01
                // 获取当月已选择的网元
                netChangeNetList = tawwpMonthMgr.getSelectedNetList(yearPlanId,
                        TawwpStaticVariable.MONTH[(StaticMethod
                                .null2int(monthFlag))]);
                // added by zdl 2009-05
                String isNull = "0";
                for (int i = 0; i < netVOList.size(); i++) {
                    TawwpNetVO netAll = (TawwpNetVO) netVOList.get(i);
                    if (netAll == null) {
                        isNull = "1";
                    }
                    boolean flag = true;
                    for (int j = 0; j < netChangeNetList.size(); j++) {
                        TawwpNetVO netSelect = (TawwpNetVO) netChangeNetList
                                .get(j);
                        if (netAll.getId().equals(netSelect.getId())) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        noSelectNetVoList.add(netAll);
                    }
                }
                request.setAttribute("netvolist", noSelectNetVoList);
                request.setAttribute("isNull", isNull);
                forword = "changeNet";
            } else {
                // 获取网元VO对象
                netVOList = tawwpNetMgr.listNetByTypeDept(netTypeId, String
                        .valueOf(deptId));
                // added by lijia 2005-11-01
                map = tawwpMonthMgr.netFilter(netVOList, yearPlanId, monthFlag);
                request.setAttribute("netvolist", map.get("result"));
                request.setAttribute("isNull", map.get("isNull"));
                forword = "success";
            }

            // 为页面显示准备数�?
            request.setAttribute("yearplanid", yearPlanId);
            request.setAttribute("monthid", monthFlag);
            request.setAttribute("isNull", map.get("isNull"));

            // 转向网元选择页面
            return mapping.findForward(forword);
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 添加月度作业计划保存Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performMonthAdd(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {

        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

        // 获取年度作业计划编号
        String yearPlanId = TawwpUtil
                .getRequestValue(request, "yearplanid", "");

        // 获取计划月度
        String monthFlag = TawwpUtil.getRequestValue(request, "monthid",
                TawwpUtil.getCurrentDateTime("MM"));
        String[] months = request.getParameterValues("months");
        // 获取网元编号字符�?
        String netIds = TawwpUtil.getRequestValue(request, "netids", "");
        String flag = StaticMethod.nullObject2String(request
                .getParameter("flag"));

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");

        try {
            if (flag.equals("1")) {// 开启作业计划制定完成后新增删除网元的信息功能
                ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

                List list = tawwpMonthMgr.getWaitRemoveMonthPlan(yearPlanId,
                        months);
                for (int i = 0; i < list.size(); i++) {
                    TawwpMonthPlan tawwpMonthPlan = (TawwpMonthPlan) list
                            .get(i);
                    TawwpMonthExecute tawwpMonthExecute = (TawwpMonthExecute) tawwpMonthPlan
                            .getTawwpMonthExecutes();
                    TawwpExecuteContent tawwpExecuteContent = (TawwpExecuteContent) tawwpMonthPlan
                            .getTawwpExecuteContents();
                    tawwpMonthMgr.removeMonthPlan(tawwpMonthPlan.getId());
                    tawwpMonthMgr.removeMonthExecute(tawwpMonthExecute.getId());
                    tawwpExecuteMgr.deleteExecuteContent(tawwpExecuteContent);
                }
            }
            for (int i = 0; i < months.length; i++) {
                // 制定月度作业计划_按网元分�?
                tawwpMonthMgr.addMonthPlans(netIds, userId, months[i],
                        yearPlanId);
            }
            // 为页面显示准备数�?
            request.setAttribute("yearplanid", yearPlanId);

            // 转向月度作业计划管理(列表)页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 显示浏览月度作业计划页面Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performMonthView(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        TawwpMonthPlanVO tawwpMonthPlanVO = null;

        try {

            // 浏览月度作业计划
            tawwpMonthPlanVO = tawwpMonthMgr.viewMonthPlan(monthPlanId);

            // 为页面显示准备数�?
            request.setAttribute("monthplanvo", tawwpMonthPlanVO);

            // 转向月度作业计划浏览页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 删除月度作业计划Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performMonthDel(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");

        try {

            // 删除月度作业计划
            tawwpMonthMgr.removeMonthPlan(monthPlanId);

            // 转向月度作业计划列表页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 删除月度作业计划Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performMonthDelete(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");
        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        TawwpMonthPlanVO tawwpMonthPlanVO = null;

        try {

            // 浏览月度作业计划
            tawwpMonthPlanVO = tawwpMonthMgr.viewMonthPlan(monthPlanId);

            // 为页面显示准备数�?
            request.setAttribute("monthplanvo", tawwpMonthPlanVO);

            // 转向月度作业计划浏览页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 显示编辑月度作业计划页面Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performMonthEdit(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String deptId = TawSystemSessionForm.getDeptid(); // 当前部门编号

        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        TawwpUtilBO tawwpUtilBO = new TawwpUtilBO();
        Hashtable principalHash = null;

        // 月度作业计划VO对象
        TawwpMonthPlanVO tawwpMonthPlanVO = null;

        try {

            // 获取月度作业计划VO对象
            tawwpMonthPlanVO = tawwpMonthMgr.editMonthPlanView(monthPlanId);
            if ("".equals(tawwpMonthPlanVO.getIsApp()) || null == tawwpMonthPlanVO.getIsApp()) {
                tawwpMonthPlanVO.setIsApp("1");
            }

            // 获取部门中人员对象集�?
            principalHash = tawwpUtilBO.getUserByDept(String.valueOf(deptId));

            // 为页面显示准备数�?
            request.setAttribute("principalhash", principalHash);
            request.setAttribute("mnonthplanvo", tawwpMonthPlanVO);

            // 转向月度作业计划编辑页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 编辑后保存月度作业计划Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performMonthModify(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");

        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");

        // 获取月度作业计划执行类型
        String executeType = TawwpUtil.getRequestValue(request, "executetype", "");
        // 获取月度作业计划执行负责人用户名
        String principal = TawwpUtil.getRequestValue(request, "principal", "");

        String executeTypeOld = TawwpUtil.getRequestValue(request, "executetypeOld", "");

        String principalOld = TawwpUtil.getRequestValue(request, "principalOld", "");

        String isApp = TawwpUtil.getRequestValue(request, "isApp", "1");


        ActionForward actionForward = null;
        try {
            //判断是否是自动送审的信息
            if ((!executeType.equals(executeTypeOld) || principal.equals(principalOld)) && "0".equals(isApp)) {
                // 获取月度作业计划VO对象 并改变自动送审状态
                tawwpMonthMgr
                        .editMonthPlanSave(monthPlanId, executeType, principal, isApp);
            } else {
                // 获取月度作业计划VO对象
                tawwpMonthMgr
                        .editMonthPlanSave(monthPlanId, executeType, principal);
            }
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = TawSystemSessionForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "editMonthPlanSave", "");

            // 转向月度作业计划浏览页面
            actionForward = new ActionForward(
                    "/tawwpmonth/monthview.do?monthplanid=" + monthPlanId);
            actionForward.setRedirect(true);
            return actionForward;

        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 显示编辑月度作业计划执行内容页面Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performExecuteEdit(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");
        String isApp = "";

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        List monthExecuteVOList = null;
        // added by lijia 2005-11-28
        // TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
        TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan();
        TawwpYearPlan yearplan = new TawwpYearPlan();

        try {

            // added by lijia 2005-11-28
            // tawwpMonthPlan = tawwpMonthPlanDAO.loadMonthPlan(monthPlanId);
            tawwpMonthPlan = tawwpMonthMgr.loadMonthPlan(monthPlanId);
			/*isApp=tawwpMonthPlan.getTawwpYearPlan().getIsApp();
			if("".equals(isApp)||null==isApp){
				isApp="";
			}*/
            // 获取月度作业计划VO对象集合
            monthExecuteVOList = tawwpMonthMgr
                    .editMonthExecuteView(monthPlanId);

            // 为页面显示准备数�?
            request.setAttribute("monthexecutevolist", monthExecuteVOList);
            request.setAttribute("monthplanid", monthPlanId);
            // added by lijia 2005-11-28
            request.setAttribute("month", tawwpMonthPlan.getMonthFlag());
            request.setAttribute("year", tawwpMonthPlan.getYearFlag());
            request.setAttribute("isApp", isApp);
            request.setAttribute("currtime", TawwpUtil.getCurrentDateTime());

            // 转向月度作业计划执行内容编辑页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 执行项与任务类型的对应
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performExecuteTask(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");
        String netserialno = TawwpUtil.getRequestValue(request, "netserialno",
                "");
        if (netserialno == null || "".equals(netserialno)) {
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.notNet"));
            saveMessages(request, messages);
            return mapping.findForward("failure");

        }
        ITawwpTaskManageInterfaceMgr tawwpTaskManageInterfaceMgr = (ITawwpTaskManageInterfaceMgr) getBean("tawwpTaskManageInterfaceMgr");
        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        List monthExecuteVOList = null;
        // added by lijia 2005-11-28
        // TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
        TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan();
        List taskList = new ArrayList();
        List commonList = new ArrayList();
        Inspection inspectionTask = new Inspection();
        try {

            // tawwpMonthPlan = tawwpMonthPlanDAO.loadMonthPlan(monthPlanId);
            tawwpMonthPlan = tawwpMonthMgr.loadMonthPlan(monthPlanId);
            // 获取月度作业计划VO对象集合
            monthExecuteVOList = tawwpMonthMgr
                    .editMonthExecuteView(monthPlanId);
            taskList = tawwpTaskManageInterfaceMgr.getTaskByNet(netserialno);

            /*
             * // for (int i = 0; i < taskList.size(); i++) { inspectionTask =
             * (Inspection)taskList.get(i); commonList.add(new
             * org.apache.struts.util.LabelValueBean(String
             * .valueOf(inspectionTask.getCmdID()+"#"+inspectionTask.getCmdName()),
             * String.valueOf(inspectionTask.getCmdName()))); }
             */
            request.setAttribute("commonList", taskList);
            // 为页面显示准备数�?
            request.setAttribute("monthexecutevolist", monthExecuteVOList);
            request.setAttribute("monthplanid", monthPlanId);
            request.setAttribute("month", tawwpMonthPlan.getMonthFlag());
            request.setAttribute("year", tawwpMonthPlan.getYearFlag());
            request.setAttribute("currtime", TawwpUtil.getCurrentDateTime());

            // 转向月度作业计划执行内容编辑页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    private ActionForward performExecuteTaskSave(ActionMapping mapping,
                                                 ActionForm actionForm, HttpServletRequest request,
                                                 HttpServletResponse response) {

        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");
        ActionForward actionForward = null;
        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");

        // TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan();
        int size = Integer.parseInt(request.getParameter("size"));

        try {
            for (int i = 1; i < size; i++) {
                String executeid = StaticMethod.null2String(request.getParameter("monthExecute" + i));
                String taskid = StaticMethod.null2String(request.getParameter("taskid" + i));
                System.out.println(executeid + "----------" + taskid);
                // 验证是否进行修改
                if (!"0".equals(taskid)) {
                    tawwpMonthMgr.editMonthExecuteSave(executeid, taskid);
                }
            }
            // 转向月度作业计划执行内容编辑页面
            actionForward = new ActionForward(
                    "/tawwpmonth/monthview.do?monthplanid=" + monthPlanId);
            actionForward.setRedirect(true);
            return actionForward;
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 删除月度作业计划执行内容(单个或批�?Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performExecuteDel(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // 获取月度作业计划执行内容编号字符�?
        String monthExecuteIdStr = TawwpUtil.getRequestValue(request,
                "monthexecuteidstr", "");
        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid", "");
        // 获取月度作业计划编号
        String isApp = TawwpUtil.getRequestValue(request, "isApp", "1");
        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");

        try {
            // 如果月度作业计划执行内容编号字符串不为空
            if (monthExecuteIdStr != null && !"".equals(monthExecuteIdStr)) {
                // 删除月度作业计划执行内容
                tawwpMonthMgr.removeMonthExecute(monthExecuteIdStr);
                // 日志
                ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
                // 获取当前用户的session中的信息
                TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                        .getSession().getAttribute("sessionform");
                String userId = TawSystemSessionForm.getUserid(); // 当前用户�?
                tawwpLogMgr.addLog(userId, "removeMonthExecute", "");

            }
            if ("0".equals(isApp)) {
                tawwpMonthMgr.editMonthPlanSave(monthPlanId);
            }
            // 转向月度作业计划执行内容编辑页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 新增月度作业计划执行内容页面Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performExecuteAdd(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {

        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        List yearExecuteVOList = null;

        try {
            // 获取年度作业计划VO对象集合
            yearExecuteVOList = tawwpMonthMgr.addMonthExecuteView(monthPlanId);
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = TawSystemSessionForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "addMonthExecuteView", "");
            // 为页面显示准备数�?
            request.setAttribute("monthplanid", monthPlanId);
            request.setAttribute("yearexecutevolist", yearExecuteVOList);

            // 转向月度作业计划执行内容新增页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 新增月度作业计划执行内容保存Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performExecuteSave(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");
        // 获取年度作业计划执行内容编号
        String yearExecuteId = TawwpUtil.getRequestValue(request,
                "yearexecuteid", "");

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        ActionForward actionForward = null;
        try {

            // 保存新增的月度作业计划执行内�?
            tawwpMonthMgr.addMonthExecuteSave(yearExecuteId, monthPlanId);
            tawwpMonthMgr.editMonthPlanSave(monthPlanId);
            // 转向月度作业计划浏览页面
            actionForward = new ActionForward(
                    "/tawwpmonth/monthview.do?monthplanid=" + monthPlanId);
            actionForward.setRedirect(true);
            return actionForward;
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 复制月度作业计划Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performMonthCopy(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

        // 获取计划月度
        String month = TawwpUtil.getRequestValue(request, "monthid", TawwpUtil
                .getCurrentDateTime("MM"));
        // 获取年度作业计划编号
        String yearPlanId = TawwpUtil
                .getRequestValue(request, "yearplanid", "");

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        ActionMessages messages = new ActionMessages();
        try {

            // 复制月度作业计划
            if (tawwpMonthMgr.copyMonthPlan(month, yearPlanId, userId)) {
                // 转向月度作业计划列表页面
                return mapping.findForward("success");
            } else {
                // 如果上月没有可用于复制的月度作业计划
                request.setAttribute("error", "nocopy");
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "submit.title.failure"));
                saveMessages(request, messages);
                // 转向提示页面
                return mapping.findForward("failure");
            }

        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            request.setAttribute("error", "1");
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 月度作业计划执行转派显示 Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performExecuteRefer(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {
        // 获取当前用户的session中的信息
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        month = TawwpUtil.getCurrentDateTime("MM");
        year = TawwpUtil.getCurrentDateTime("yyyy");
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String deptId = saveSessionBeanForm.getDeptid(); // 当前部门编号
        String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
        ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

        List result = null;

        try {
            result = tawwpExecuteMgr.listMonthExecute(userId, deptId, month,
                    year);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        request.setAttribute("result", result);
        // ----------------------------------------------------

        // 转向日常执行作业计划管理(列表)页面
        return mapping.findForward("success");

    }

    /**
     * 月度作业计划执行转派发�?Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performExecuteSend(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        // 获取当前用户的session中的信息
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        month = TawwpUtil.getCurrentDateTime("MM");
        year = TawwpUtil.getCurrentDateTime("yyyy");
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String deptId = saveSessionBeanForm.getDeptid(); // 当前部门编号
        String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
        String roomId = saveSessionBeanForm.getRoomId();

        String executer = request.getParameter("executer");
        String executerType = request.getParameter("executeType");
        String isSelf = StaticMethod.null2String(
                request.getParameter("isself"), "0");
        String[] monthExecuteIds = request.getParameterValues("executeId");
        List monthExecuteList = null;

        ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        StringBuffer monthPlanIds = new StringBuffer();
        StringBuffer monthExecutes = new StringBuffer();

        // 取所有执行内容ID
        for (int i = 0; i < monthExecuteIds.length; i++) {
            monthExecutes.append("'" + monthExecuteIds[i] + "',");
        }
        if (executerType != null && executerType.equals("user")) {
            executerType = "0";

        } else if (executerType.equals("dept")) {
            executerType = "1";

        } else {
            executerType = "3";

        }
        if (executer.endsWith(",")) {
            executer = executer.substring(0, executer.length() - 1);
        }
        String ids = monthExecutes.toString().substring(0,
                monthExecutes.toString().length() - 1);
        if (executer != null && !executer.equals("")) {
            List list = tawwpMonthMgr.loadMonthExecute(ids);

            // 修改月计划执行内容中的执行人
            tawwpMonthMgr.updateMonthExecute(executer, executerType, ids);
            // 修改日常执行执行�?
            tawwpExecuteMgr.updateExecute(executer, executerType, ids);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    TawwpMonthExecute tawwpMonthExecute = (TawwpMonthExecute) list
                            .get(i);
                    String tempPlanId = tawwpMonthExecute.getTawwpMonthPlan()
                            .getId();
                    // 如果月计划没有修改完�?
                    if (monthPlanIds.indexOf(tempPlanId) == -1) {
                        monthPlanIds.append(tempPlanId + ",");
                        // 修改月计划执行内�?
                        tawwpMonthMgr.updateMonthExecuteUser(tempPlanId);
                    }
                }

            }

        }

        // 转向转派页面
        return mapping.findForward("success");

    }

    /**
     * 显示待审批月度作业计划管�?列表)Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performCheckList(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {

        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?
        ITawwpExecuteMgr mgrr = (ITawwpExecuteMgr) this
                .getBean("tawwpExecuteMgr");
        /*
         * Inspection workplanresult = new Inspection();
         * workplanresult.setCmdID("2"); workplanresult.setCmdName("好好学习");
         * workplanresult.setExecuteFTP("ftp://10.0.2.115/111/EOMS_EOS_提供区域人员练习使用_2009_05_21.rar");
         * workplanresult.setExecuteResult("天天向上");
         * workplanresult.setExecuteStatus("2");
         *
         * mgrr.saveContentByComm("8aa081eb219b356e01219b67b6610005",workplanresult);
         *
         * Inspection workplanresult1 = new Inspection();
         * workplanresult1.setCmdID("9"); workplanresult1.setCmdName("什么命令");
         * workplanresult1.setExecuteFTP("ftp://10.0.2.115/111/EOMS_EOS_提供区域人员练习使用_2009_05_21.rar");
         * workplanresult1.setExecuteResult("hello");
         * workplanresult1.setExecuteStatus("4");
         * mgrr.saveContentByComm("8aa081eb20adc5180120ae0fa58a0005",workplanresult1);
         *
         * Inspection workplanresult2 = new Inspection();
         * workplanresult2.setCmdID("8"); workplanresult2.setCmdName("春暖花开");
         * workplanresult2.setExecuteFTP("ftp://10.0.2.115/111/EOMS_EOS_提供区域人员练习使用_2009_05_21.rar");
         * workplanresult2.setExecuteResult("心踏实了");
         * workplanresult2.setExecuteStatus("1");
         *
         * mgrr.saveContentByComm("8aa081eb20adc5180120ae0fa58a0005",workplanresult2);
         */

        // 权限验证
        /*
         * try { TawSystemAssignBo tawValidatePrivBO =
         * TawSystemAssignBo.getInstance(); //月计划审批权�?ssssssssssssss if
         * (tawValidatePrivBO.hasAssign(userId,"") == false) { return
         * mapping.findForward("nopriv"); //转向无权限页�?} } catch (Exception e) {
         * e.printStackTrace(); return mapping.findForward("failure"); //异常页面 }
         */
        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        List monthPlanVOList = null;

        try {
            // 获取月度作业计划VO对象集合
            monthPlanVOList = tawwpMonthMgr.listMonthCheck(userId);

            // 为页面显示准备数�?
            request.setAttribute("monthplanvolist", monthPlanVOList);

            // 转向待审批月度作业计划管�?列表)页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 显示待审批月度作业计划管�?列表)Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performCheckViews(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {

        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?
        ITawwpExecuteMgr mgrr = (ITawwpExecuteMgr) this
                .getBean("tawwpExecuteMgr");

        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");

        // 获取月度作业计划审批信息编号
        String monthCheckId = TawwpUtil.getRequestValue(request,
                "monthcheckid", "");

        // 权限验证

        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        List monthPlanVOList = null;

        try {
            // 获取月度作业计划VO对象集合
            monthPlanVOList = tawwpMonthMgr.listMonthCheck(userId, monthPlanId);

            // 为页面显示准备数�?
            request.setAttribute("monthplanvolist", monthPlanVOList);

            // 转向待审批月度作业计划管�?列表)页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 显示月度作业计划审批页面Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performCheckView(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");

        // 获取月度作业计划审批信息编号
        String monthCheckId = TawwpUtil.getRequestValue(request,
                "monthcheckid", "");

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        TawwpMonthPlanVO tawwpMonthPlanVO = null;

        try {

            // 浏览月度作业计划
            tawwpMonthPlanVO = tawwpMonthMgr.viewMonthPlanByCheck(monthPlanId);

            // 为页面显示准备数�?
            request.setAttribute("monthcheckid", monthCheckId);
            request.setAttribute("mnonthplanvo", tawwpMonthPlanVO);

            // 转向月度作业计划审批页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 月度作业计划审批_通过Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performCheckPass(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");

        // 获取月度作业计划审批信息编号
        String monthCheckId = TawwpUtil.getRequestValue(request,
                "monthcheckid", "");

        // 获取审批信息(意见)
        String content = TawwpUtil.getRequestValue(request, "content", "");

        // 获取下一级审批人用户�?
        String nextCheckUser = TawwpUtil.getRequestValue(request, "checkuser",
                "");

        try {

            // 通过月度作业计划审批
            tawwpMonthMgr.passMonthCheck(monthCheckId, nextCheckUser, content,
                    userId);

            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            tawwpLogMgr.addLog(userId, "passMonthCheck", "");

            // 转向待审批月度作业计划管�?列表)页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 月度作业计划审批_驳回Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performCheckReject(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");

        // 获取月度作业计划审批信息编号
        String monthCheckId = TawwpUtil.getRequestValue(request,
                "monthcheckid", "");

        // 获取审批信息(意见)
        String content = TawwpUtil.getRequestValue(request, "content", "");

        try {

            // 驳回月度作业计划审批
            tawwpMonthMgr.rejectMonthCheck(monthCheckId, content, userId);

            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            tawwpLogMgr.addLog(userId, "rejectMonthCheck", "");

            // 转向待审批月度作业计划管�?列表)页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 月度作业计划执行内容编辑保存Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performExecuteModify(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {

        // 获取月度作业计划执行内容数量
        String count = TawwpUtil.getRequestValue(request, "count", "0");

        // 获取月度作业计划编号
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");
        // 获取月度作业计划编号
        String isApp = TawwpUtil.getRequestValue(request, "isApp", "1");

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        ActionForward actionForward = null;
        String executeInfo = "";
        String monthExecuteId = "";
        String executer = "";
        String executerType = "";
        String executeRoom = "";
        String executeDuty = "";
        String executeDate = "";
        String startHH = "";
        String endHH = "";
        String checkUser = "";
        try {

            for (int i = 1; i < (Integer.parseInt(count) + 1); i++) {
                // 获取作业计划执行内容信息字符�?
                executeInfo = TawwpUtil.getRequestValue(request, "monthExecute"
                        + String.valueOf(i), "");
                // 处理信息字符�?
                String[] tempStr = executeInfo.split("#");
                monthExecuteId = tempStr[0]; // 执行内容编号
                executer = tempStr[1]; // 执行�?
                executerType = tempStr[2]; // 执行人类�?
                executeDate = tempStr[3]; // 执行日期
                if (tempStr.length >= 5 && !"".equals(tempStr[4])) {
                    startHH = tempStr[4];// 开始时间(分钟)
                } else {
                    startHH = "00";
                }
                if (tempStr.length == 6) {
                    endHH = tempStr[5];// 结束时间(分钟)
                } else {
                    endHH = "23";
                }
                if ("3".equals(executerType)) {
                    executeRoom = tempStr[1]; // 执行机房
                    // executeDuty = tempStr[4]; // 执行班次
                }
                if (tempStr.length == 7) {// 质检人
                    checkUser = StaticMethod.null2String(tempStr[6]);
                }
                // 保存编辑后的月度作业计划执行内容
                tawwpMonthMgr.editMonthExecuteSave(monthExecuteId, executer,
                        executerType, executeRoom, executeDuty, executeDate,
                        startHH, endHH, checkUser);
            }
            if ("0".equals(isApp)) {
                tawwpMonthMgr.editMonthPlanSave(monthPlanId);
            }
            // 转向月度作业计划浏览页面
            actionForward = new ActionForward(
                    "/tawwpmonth/monthview.do?monthplanid=" + monthPlanId);
            actionForward.setRedirect(true);
            return actionForward;
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 查询网元的执行内容列�?
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     */

    private ActionForward performCommNetSelect(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {

        String netserialno = TawwpUtil.getRequestValue(request, "netserialno",
                "");
        String executeId = TawwpUtil.getRequestValue(request, "executeid", "");

        ITawwpTaskManageInterfaceMgr tawwpTaskManageInterfaceMgr = (ITawwpTaskManageInterfaceMgr) getBean("tawwpTaskManageInterfaceMgr");
        // 查询网元的执行内容列�?
        List taskList = tawwpTaskManageInterfaceMgr.getTaskInfor(netserialno);
        // 设置属�?

        request.setAttribute("taskList", taskList);
        request.setAttribute("executeId", executeId);

        return mapping.findForward("success");

    }

    /**
     * 保存网元的执行内�?
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     */

    private ActionForward performCommNetSave(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        String taskName = TawwpUtil.getRequestValue(request, "taskname", "");
        String taskId = TawwpUtil.getRequestValue(request, "taskid", "");
        String executeId = TawwpUtil.getRequestValue(request, "executeid", "");
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        try {
            tawwpMonthMgr.editCommand(executeId, taskId, taskName);

            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = TawSystemSessionForm.getUserid(); // 当前用户�?
            tawwpLogMgr.addLog(userId, "editCommand", "");

            request.setAttribute("close", "true");
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     */
    private ActionForward performMonthExport(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr"); // 初始化作业计划模板管理BO�?
        String monthid = request.getParameter("monthid"); // 模版名称
        String reaction = request.getParameter("reaction");

        String _exportUrl = "";
        _exportUrl = tawwpMonthMgr.exportMonthToExcel(monthid);
        // 日志
        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid(); // 当前用户�?
        tawwpLogMgr.addLog(userId, "exportMonthToExcel", "");

        // 返回页面
        ActionForward actionForward = new ActionForward(reaction + "?url="
                + _exportUrl);
        actionForward.setRedirect(true);

        return actionForward;

    }

    /**
     * 月度作业计划审批_批量通过Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performCheckListPass(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

        // 初始化数�?
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");

        // 获取月度作业计划审批信息编号
        String monthCheckIdStr = TawwpUtil.getRequestValue(request,
                "monthcheckidstr", "");

        try {

            // 通过月度作业计划审批
            tawwpMonthMgr.passMonthCheck(monthCheckIdStr, "", userId);

            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            tawwpLogMgr.addLog(userId, "passMonthCheck", "");

            // 转向待审批月度作业计划管�?列表)页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日�?
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 通过接口向联通总部提交月度作业计划
     *
     * @param mapping
     *            ActionMapping
     * @param actionForm
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     *
     * private ActionForward performMonthReport(ActionMapping mapping ,
     * ActionForm actionForm, HttpServletRequest request , HttpServletResponse
     * response) {
     *
     * TawwpMonthExtendBO tawwpMonthMgr = new TawwpMonthExtendBO();
     * //初始化作业计划模板管理BO�?String monthid = request.getParameter("monthid"); //模版名称
     * String yearmonth = tawwpMonthMgr.monthReport(monthid);
     * request.setAttribute("yearid", yearmonth.substring(0, 4));
     * request.setAttribute("monthid", yearmonth.substring(4, 6)); return
     * mapping.findForward("success"); }
     */

    /**
     * 将错误信息加入到消息队列、写入日�?
     *
     * @param request HttpServletRequest 要进行处理的request
     * @param e       Exception 异常
     */
    private void generalError(HttpServletRequest request, Exception e) {
        ActionMessages aes = new ActionMessages();
        aes.add("EOMS_WORKPLAN_ERROR", new ActionMessage("error.general", e
                .getMessage())); // 将错误信息加入到消息队列�?
        saveMessages(request, aes); // 保存消息队列
        BocoLog.error(this, 2, "[WORK_PLAN_MONTH] Error -", e); // 将错误信息写入日�?
    }

    private ActionForward performCheckBatch(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm tawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        // 日志
        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
        // 初始化数据
        ITawwpMonthMgr mgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");

        // 获取月度作业计划执行负责人用户名
        String principal = TawwpUtil.getRequestValue(request, "principal", "");
        // 获取月度作业计划执行人参数
        String monthExecuteId = "";
        String executer = TawwpUtil
                .getRequestValue(request, "sort1_userid", "");// 执行人ID
        String executerType = ""; // 执行人类型
        String executeRoom = ""; // 执行人机房
        String executeDuty = ""; // 执行人班次
        String executeDate = "";
        String fileflag = "";
        // 获取月度作业提交审核参数
        /*
         * String flowSerial = TawwpUtil.getRequestValue(request, "flowserial",
         * ""); String stepSerial = TawwpUtil.getRequestValue(request,
         * "stepserial", "");
         */
        String checkUser = TawwpUtil.getRequestValue(request, "checkuser", "");
        String checkDept = String.valueOf(tawSystemSessionForm.getUserid());
        String planArr = TawwpUtil.getRequestValue(request, "planArr", "");
        String checkType = request.getParameter("");
        if (planArr.length() != 0) {
            String[] planArray = planArr.split("#");
            executer = planArray[0];// 执行人
            executerType = planArray[1];// 执行人类型
            if ("3".equals(executerType)) {
                executeRoom = planArray[0];
                executeDuty = planArray[2];
            }
        }

        // 获取月度作业计划编号(批量多个monthplanid)
        String PLYearPlanVOCount = request.getParameter("PLYearPlanVOId");
        String[] monthPlanIds = request.getParameterValues("allMonthPlanId"
                + PLYearPlanVOCount);
        if (monthPlanIds != null) {
            try {
                for (int k = 0; k < monthPlanIds.length; k++) {
                    TawwpMonthPlanVO monthPlanVO = mgr
                            .viewMonthPlan(monthPlanIds[k]);
                    // 批量选择执行负责人
                    if (!principal.equals("")) {
                        // 获取月度作业计划VO对象
                        mgr.editMonthPlanSave(monthPlanIds[k], "0", principal);
                        String userId = tawSystemSessionForm.getUserid(); // 当前用户名
                        tawwpLogMgr.addLog(userId, "editMonthPlanSave", "");
                    }
                    // 批量选择执行人
                    if (!executer.equals("")) {
                        List monthExecuteVOList = monthPlanVO
                                .getMonthExecuteVOList();
                        for (int i = 0; i < monthExecuteVOList.size(); i++) {
                            TawwpMonthExecuteVO tawwpMonthExecuteVO = (TawwpMonthExecuteVO) monthExecuteVOList
                                    .get(i);
                            monthExecuteId = tawwpMonthExecuteVO.getId();
                            executeDate = tawwpMonthExecuteVO.getExecuteDate();
                            fileflag = "N";// 默认不必须附件

                            // 保存编辑后的月度作业计划执行内容
                            mgr.editMonthExecuteSave(monthExecuteId, executer,
                                    executerType, executeRoom, executeDuty,
                                    executeDate, fileflag);
                        }
                    }
                    // 批量提交审核
                    if (!checkUser.equals("")) {
                        String flowSerial = String.valueOf(monthPlanVO
                                .getStep().getFlowSerial());
                        String stepSerial = monthPlanVO.getStep().getSerial();
                        // 月度作业计划提交审批
                        if (mgr.addMonthCheck(checkDept, checkUser,
                                monthPlanIds[k], flowSerial, stepSerial,
                                checkType) == null) {
                            return mapping.findForward("fault"); // 转向错误页面
                        }
                        // 日志
                        tawwpLogMgr.addLog(checkUser, "addMonthCheck", "");
                    }
                }
            } catch (Exception e) {
                generalError(request, e); // 将错误信息加入到消息队列、写入日志
                return mapping.findForward("failure"); // 转向错误页面
            }

        }
        return mapping.findForward("success");
    }

    /**
     * add by 龚玉峰 2009-04-21 21：24
     *
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performYearExport(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm tawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String year = request.getParameter("year");
        // 日志
        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
        // 初始化数据
        ITawwpMonthMgr mgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
        try {
            String exportUrl = mgr.YearOrderByMonthUrl(year);
            File file = new File(exportUrl);
            // 读到流中
            InputStream inStream = new FileInputStream(file);// 文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("application/x-msdownload;charset=GBK");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode(file.getName(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(fileName.getBytes("UTF-8"), "GBK"));

            // 循环取出流中的数据
            byte[] b = new byte[128];
            int len;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);

            inStream.close();
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日志
            return mapping.findForward("failure"); // 转向错误页面
        }

        return null;
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performYearSelect(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {

        return mapping.findForward("success");
    }

}
