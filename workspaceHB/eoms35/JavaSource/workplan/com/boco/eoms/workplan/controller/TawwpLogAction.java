package com.boco.eoms.workplan.controller;

/**
 * <p>Title: 作业计划日志管理struts控制管理action</p>
 * <p>Description: 作业计划日志信息各页面的管理控制,用户输入数据收集或bo类数据提取</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import com.boco.eoms.base.util.ApplicationContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.common.log.BocoLog;

//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import com.boco.eoms.workplan.dao.Jdbc.TawwpGetZNXJDAO;
import com.boco.eoms.workplan.intfacewebservices.Download;
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;

public class TawwpLogAction
        extends BaseAction {

    //获取属性文件
    static {
        ResourceBundle prop = ResourceBundle.getBundle(
                "resources.application_zh_CN");
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form
            , HttpServletRequest request,
                                 HttpServletResponse response) {

        ActionForward myforward = null;

        //获取请求的action属性
        String myaction = mapping.getParameter();

        //session超时处理
        try {
      /*TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.
          getSession().getAttribute(
              "sessionform");
      if (saveSessionBeanForm == null) {
        return mapping.findForward("timeout");
      }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据用户请求页面请求，进行页面跳转
        if (isCancelled(request)) {
            return mapping.findForward("cancel"); //无效的请求，转向错误页面
        } else if ("".equalsIgnoreCase(myaction)) {
            myforward = mapping.findForward("failure"); //条件为空，转向空页
        } else if ("LOGQUERYLIST".equalsIgnoreCase(myaction)) {
            myforward = performLogQueryList(mapping, form, request, response); //查询日志信息
        } else if ("LOGQUERYLIST".equalsIgnoreCase(myaction)) {
            myforward = performLogQueryList(mapping, form, request, response); // 查询日志信息
        } else if ("PATROLERRLOGLIST".equalsIgnoreCase(myaction)) { // 查询智能巡检错误日志--add
            myforward = performPatrolErrLogList(mapping, form, request, response);
        } else {
            myforward = mapping.findForward("failure"); //无效的请求，转向错误页面
        }
        return myforward;
    }

    private ActionForward performLogQueryList(ActionMapping mapping
            , ActionForm actionForm,
                                              HttpServletRequest request
            , HttpServletResponse response) {
        //获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.
                getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid(); //当前用户

        //权限验证
  /*  try {
     // TawValidatePrivBO tawValidatePrivBO = new TawValidatePrivBO();

      TawSystemAssignBo tawValidatePrivBO =TawSystemAssignBo.getInstance();
      if (tawValidatePrivBO.hasPermission4region(userId,"") == false)
    	  //TawwpStaticVariable.OPE_LOG_MANAGE
      {
    	  
        return mapping.findForward("nopriv"); //转向无权限页面
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return mapping.findForward("failure"); //异常页面
    }*/
        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr"); //初始化作业计划日志BO类
        List list = null;
        Map map = new HashMap();

        String startDate = request.getParameter("startdate"); //开始时间
        String endDate = request.getParameter("enddate"); //结束时间
        String cruser = request.getParameter("cruser"); //操作人
        String logType = request.getParameter("logtype"); //日志类型

        if (startDate != null && !startDate.equals(""))
            map.put("startDate", startDate);
        if (endDate != null && !endDate.equals(""))
            map.put("endDate", endDate);
        if (cruser != null && !cruser.equals(""))
            map.put("cruser", cruser);
        if (logType != null && !logType.equals("0"))
            map.put("logType", logType);

        try {
            list = tawwpLogMgr.searchLog(map); //获取查询日志信息

            request.setAttribute("loglist", list);
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

    /**
     * 管理错误信息，如果有异常出现，则进行处理，将事务回滚
     *
     * @param request HttpServletRequest 请求对象
     * @param e       Exception 异常对象
     */
    private void generalError(HttpServletRequest request, Exception e) {
        ActionMessages aes = new ActionMessages();
        aes.add("EOMS_WORKPLAN_ERROR",
                new ActionMessage("error.general", e.getMessage()));
        saveMessages(request, aes);
        BocoLog.error(this, 2, "[WORKPLAN_LOG] Error -", e);
    }

    //取智能巡检错误日志
    private ActionForward performPatrolErrLogList(ActionMapping mapping,
                                                  ActionForm actionForm, HttpServletRequest request,
                                                  HttpServletResponse response) {
        String date = request.getParameter("startdate");

        // 获取当前用户的session中的信息
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        ITawSystemUserManager user = (ITawSystemUserManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserManager");
        ITawSystemDeptManager dept = (ITawSystemDeptManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemDeptManager");
        // String depname = saveSessionBeanForm.getWrf_DeptName(); //当前部门
        String depname = "";
        String did = "";
        int regionid = 0; // 地市部门的ID
        String deptIdStr = saveSessionBeanForm.getDeptid();
        int deptId = Integer.parseInt(deptIdStr);
        // TawDeptDAO deptDao = new TawDeptDAO();
        if (deptId == 1085) {// 如果是维护交换室，就取省网的错误日志
            depname = "省网";
        } else {// 其他部门就取各地市的错误日志
            try {
                TawSystemDept tawSystemDept = dept.getDeptinfobydeptid(deptId
                        + "", "0");
                String linkId = tawSystemDept.getLinkid();
                if (linkId.length() > 3) {
                    linkId = linkId.substring(0, 3);
                }
                TawSystemDept tawSystemDeptlink = dept.getDeptinfobylinkid(
                        linkId, "0");
                /*
                 * did = deptDao.getId(deptId); did = did.substring(0,3);
                 * regionid = deptDao.getDeptIdById(did);
                 */
                depname = tawSystemDeptlink.getDeptName();
                // 如果是成都分公司，部门名替换为成都
                if (did.equals("104")) {
                    depname = "成都";
                }
            } catch (Exception e1) {
                System.out.print("查询地市部门时出错！");
                e1.printStackTrace();
            }
        }
        TawwpGetZNXJDAO znxj = (TawwpGetZNXJDAO) ApplicationContextHolder
                .getInstance().getBean("tawwpTawwpGetZNXJDao");
        List detail = new ArrayList();
        String filename = "";

        try {
            detail = znxj.getFileDetail(depname, date);
            if (detail.size() != 0) {
                depname = detail.get(0).toString().trim();
                filename = detail.get(1).toString().trim();
                Download dl = new Download();
                dl.downloadPatrolLog(filename);
            }
            request.setAttribute("detail", detail);
        } catch (Exception e) {
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }
}
