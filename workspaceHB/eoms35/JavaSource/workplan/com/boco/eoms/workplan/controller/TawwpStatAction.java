package com.boco.eoms.workplan.controller;

/**
 * <p>
 * Title: 作业计划统计查询struts控制管理action
 * </p>
 * <p>
 * Description: 作业计划统计查询信息各页面的管理控制,用户输入数据收集或bo类数据提取
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: boco
 * </p>
 *
 * @author eoms
 * @version 1.0
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.mgr.ITawwpExecuteCheckMgr;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.mgr.ITawwpMonthMgr;
import com.boco.eoms.workplan.mgr.ITawwpNetMgr;
import com.boco.eoms.workplan.mgr.ITawwpStatMgr;
import com.boco.eoms.workplan.mgr.ITawwpYearPlanMgr;
import com.boco.eoms.workplan.model.TawwpExecuteCheck;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpStatVO;
import com.boco.eoms.workplan.vo.TawwpStatYearPlanVO;
import com.boco.eoms.workplan.vo.TawwpExecuteContentVO;
import com.boco.eoms.workplan.vo.TawwpYearPlanVO;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;

public class TawwpStatAction extends BaseAction {

    // 获取属性文件
    static {
        ResourceBundle prop = ResourceBundle
                .getBundle("resources.application_zh_CN");
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {

        ActionForward myforward = null;

        // 获取请求的action属性
        String myaction = mapping.getParameter();

        // session超时处理
        try {
            /*
             * TawSystemSessionForm TawSystemSessionForm =
             * (TawSystemSessionForm) request. getSession().getAttribute(
             * "sessionform"); if (TawSystemSessionForm == null) { return
             * mapping.findForward("timeout"); }
             */
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 根据用户请求页面请求，进行页面跳转
        if (isCancelled(request)) {
            return mapping.findForward("cancel"); // 无效的请求，转向错误页面
        } else if ("".equalsIgnoreCase(myaction)) {
            myforward = mapping.findForward("failure"); // 条件为空，转向空页
        } else if ("LISTYEARPLAN".equalsIgnoreCase(myaction)) {
            myforward = performListYearPlan(mapping, form, request, response); // 查询符合条件的年度作业计划
        } else if ("QUERYYEARPLANRESULT".equalsIgnoreCase(myaction)) {
            myforward = performQueryYearPlanResult(mapping, form, request,
                    response); // 显示具体的年度作业计划内容
        } else if ("LISTMONTHPLAN".equalsIgnoreCase(myaction)) {
            myforward = performListMonthPlan(mapping, form, request, response); // 查询符合条件的月度作业计划
        } else if ("QUERYMONTHPLANRESULT".equalsIgnoreCase(myaction)) {
            myforward = performQueryMonthPlanResult(mapping, form, request,
                    response); // 显示具体的月度作业计划内容
        } else if ("DOWN".equalsIgnoreCase(myaction)) {
            myforward = performDown(mapping, form, request, response); // 显示具体的月度作业计划内容
        } else if ("VIEWMONTHCONTENT".equalsIgnoreCase(myaction)) {
            myforward = performViewMonthContent(mapping, form, request,
                    response); // 显示具体的执行内容整体信息
        } else if ("STATYEARALL".equalsIgnoreCase(myaction)) {
            myforward = performStatYearAll(mapping, form, request, response); // 统计全省各地市的执行数据
        } else if ("STATYEARREGION".equalsIgnoreCase(myaction)) {
            myforward = performStatYearRegion(mapping, form, request, response); // 统计全省各地市的执行数据
        } else if ("STATYEARMONTHPLAN".equalsIgnoreCase(myaction)) {
            myforward = performStatYearMonthPlan(mapping, form, request,
                    response); // 统计部门中不合格的月度作业计划
        } else if ("STATYEARMONTHPLANNEW".equalsIgnoreCase(myaction)) {
            myforward = performStatYearMonthPlanNew(mapping, form, request,
                    response); // 统计部门中不合格的月度作业计划
        } else if ("REPORTYEARPLAN".equalsIgnoreCase(myaction)) {
            myforward = performReportYearPlan(mapping, form, request, response); // 统计年度作业计划执行情况
        } else if ("SAVEASSESS".equalsIgnoreCase(myaction)) {
            myforward = performSaveExecuteAssess(mapping, form, request,
                    response); // 执行意见保存
        }
        // begin add by denden 贵州本地 增加作业计划统计到人
        else if ("GZLISTMONTHPLAN".equalsIgnoreCase(myaction)) {
            myforward = performListGZMonthPlan(mapping, form, request, response); // 查询符合条件的月度作业计划
        } else if ("GZQUERYMONTHPLANUSER".equalsIgnoreCase(myaction)) {
            myforward = performListGZMonthUser(mapping, form, request, response); // 查询符合条件的月度作业计划
        } else if ("STATSELECT".equalsIgnoreCase(myaction)) {
            myforward = performStatSelect(mapping, form, request, response); // 选择条件页面
        } else if ("STATQUERY".equalsIgnoreCase(myaction)) {
            myforward = performStatQuery(mapping, form, request, response); // 选择条件页面
        } else if ("NETSELECT".equalsIgnoreCase(myaction)) {
            myforward = performNetSelect(mapping, form, request, response); // 根据网元类型获取模版信息
        } else if ("DOWNEXCEL".equalsIgnoreCase(myaction)) {
            myforward = performDownExcel(mapping, form, request, response); // 导出excel
        } else if ("CHECKMONHPLANRESULT".equalsIgnoreCase(myaction)) {
            myforward = performCheckMonthPlanResult(mapping, form, request, response); //选择地市信息
        } else if ("CHECKMONHPLANCONTENT".equalsIgnoreCase(myaction)) {
            myforward = performCheckMonthPlanContent(mapping, form, request, response); //选择地市信息
        } else if ("CONTENTEXPORT".equalsIgnoreCase(myaction)) {
            myforward = performContentExport(mapping, form, request, response); //巡检结果导出
        } /*else if ("CHECKALLRESULT".equalsIgnoreCase(myaction)) {
	      myforward = performCheckAllResult(mapping, form, request, response); //选择地市信息
	    } else if ("CHECKALLCONTENT".equalsIgnoreCase(myaction)) {
	      myforward = performCheckAllContent(mapping, form, request, response); //选择地市信息*/
        // end by denden 贵州本地 增加作业计划统计到人
        else {
            myforward = mapping.findForward("failure"); // 无效的请求，转向错误页面
        }
        return myforward;
    }

    private ActionForward performListYearPlan(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {
        ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr");
        // 初始化作业计划查询统计管理BO类
        List list = null;
        List deptIdlist = null;
        String modelId = request.getParameter("modellist");
        String deptId = request.getParameter("deptid");
        String yearFlag = request.getParameter("yearflag");
        String sysTypeId = request.getParameter("systype");
        String netTypeId = request.getParameter("nettype");
        String state = request.getParameter("state");
        String flag = request.getParameter("flag");
        TawSystemDeptManagerImpl mgr = (TawSystemDeptManagerImpl) getBean("ItawSystemDeptManager");

        HashMap hashMap = new HashMap();

        // 组织查询条件
        if (deptId != null && !deptId.equals("")) {
            hashMap.put("deptId", deptId); // 部门信息
        }
        if (yearFlag != null && !yearFlag.equals("0")) {
            hashMap.put("yearFlag", yearFlag); // 年度
        }
        if (sysTypeId != null && !sysTypeId.equals("0")) {
            hashMap.put("sysTypeId", sysTypeId); // 系统类别
        }
        if (netTypeId != null && !netTypeId.equals("0")) {
            hashMap.put("netTypeId", netTypeId); // 网元类型
        }
        if (state != null && !state.equals("-1")) {
            hashMap.put("state", state);
        }
        if (modelId != null && !modelId.equals("")) {
            hashMap.put("modelId", modelId); // 网元信息
        }
        if (flag != null && flag.equals("1")) {
            try {
                list = tawwpStatMgr.queryYearPlan(hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag != null && flag.equals("0")) {
            if (deptId != null && !deptId.equals("")) {
                deptIdlist = mgr.getSubDepts(deptId);
            }
            if (deptIdlist != null) {
                hashMap.put("deptIdlist", deptIdlist); // 部门信息
            }
            try {
                list = tawwpStatMgr.queryYearPlanLink(hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            if (list == null) {
                list = tawwpStatMgr.queryYearPlan(hashMap); // 查询符合条件的年度作业计划
            }
            Collections.sort(list, new Comparator() {
                public int compare(Object o1, Object o2) {
                    TawwpYearPlanVO p1 = (TawwpYearPlanVO) o1;
                    TawwpYearPlanVO p2 = (TawwpYearPlanVO) o2;
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
            request.setAttribute("queryyearplan", list);
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

    private ActionForward performQueryYearPlanResult(ActionMapping mapping,
                                                     ActionForm actionForm, HttpServletRequest request,
                                                     HttpServletResponse response) {

        String yearplanid = request.getParameter("yearplanid");
        ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
        // 初始化年度作业计划管理BO类
        TawwpYearPlanVO tawwpYearPlanVO = new TawwpYearPlanVO();
        try {
            tawwpYearPlanVO = tawwpYearPlanMgr.viewYearPlanVO(yearplanid);
            request.setAttribute("tawwpYearPlanVO", tawwpYearPlanVO);

            return mapping.findForward("success");
        } catch (Exception e) {
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            generalError(request, e);
            return mapping.findForward("failure");
        }
    }

    private ActionForward performListMonthPlan(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {

        ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr"); // 初始化作业计划查询统计管理BO类
        List list = null;

        String deptId = request.getParameter("deptid");
        String yearFlag = request.getParameter("yearflag");
        String monthFlag = request.getParameter("monthflag");
        String sysTypeId = request.getParameter("systype");
        String netTypeId = request.getParameter("nettype");
        String constituteState = request.getParameter("constitutestate");
        String executeState = request.getParameter("executestate");
        String netId = request.getParameter("netlist");
        String executer = request.getParameter("saved2");
        String flag = request.getParameter("flag");
        String id = "";
        String executerType = "";
        List deptIdlist = null;
        TawSystemDeptManagerImpl mgr = (TawSystemDeptManagerImpl) getBean("ItawSystemDeptManager");
        JSONArray data = JSONArray.fromString(executer);
        for (Iterator it = data.iterator(); it.hasNext(); ) {
            JSONObject obj1 = (JSONObject) it.next();
            id = (String) obj1.get("id");
            executerType = (String) obj1.get("nodeType");

            if (executerType != null && executerType.equals("user")) {
                executerType = "0";

            } else if (executerType.equals("dept")) {
                executerType = "1";

            } else {
                executerType = "3";

            }
        }

        HashMap hashMap = new HashMap();

        // 组织查询条件
        if (deptId != null && !deptId.equals("")) {
            hashMap.put("deptId", deptId); // 部门信息
        }
        if (!yearFlag.equals("0")) {
            hashMap.put("yearFlag", yearFlag); // 年度
        }
        if (!monthFlag.equals("0")) {
            hashMap.put("monthFlag", monthFlag); // 月度
        }
        if (!sysTypeId.equals("0")) {
            hashMap.put("sysTypeId", sysTypeId); // 系统类别
        }
        if (!netTypeId.equals("0")) {
            hashMap.put("netTypeId", netTypeId); // 网元类型
        }
        if (!constituteState.equals("-1")) {
            hashMap.put("constituteState", constituteState); // 制定状态
        }
        if (!executeState.equals("-1")) {
            hashMap.put("executeState", executeState); // 执行状态
        }
        if (!netId.equals("")) {
            hashMap.put("netId", netId); // 网元信息
        }
        hashMap.put("deleted", "0"); // 删除标志位
        if (flag != null && flag.equals("1")) {
            try {
                list = tawwpStatMgr.queryMonthPlan(hashMap, id, executerType); // 查询符合条件的月度作业计划
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag != null && flag.equals("0")) {
            if (deptId != null && !deptId.equals("")) {
                deptIdlist = mgr.getSubDepts(deptId);
            }
            if (deptIdlist != null) {
                hashMap.put("deptIdlist", deptIdlist); // 部门信息
            }
            try {
                list = tawwpStatMgr.queryMonthPlanLink(hashMap, id, executerType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                TawwpMonthPlanVO p1 = (TawwpMonthPlanVO) o1;
                TawwpMonthPlanVO p2 = (TawwpMonthPlanVO) o2;
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
        try {
            request.setAttribute("querymonthplan", list);
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

    private ActionForward performQueryMonthPlanResult(ActionMapping mapping,
                                                      ActionForm actionForm, HttpServletRequest request,
                                                      HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String userId = TawSystemSessionForm.getUserid(); // 当前用户名

        // 初始化数据
        ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
        TawwpMonthPlanVO tawwpMonthPlanVO = null;

        // 获取页面传递过来的参数
        String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
        request.getSession().setAttribute("monthPlanId", monthPlanId);

        try {

            // 获取VO对象
            tawwpMonthPlanVO = tawwpExecuteMgr.viewExecutePlan(monthPlanId);

            // 为页面显示准备数据
            request.setAttribute("tawwpmonthplanvo", tawwpMonthPlanVO);
            request.setAttribute("userid", userId);

            // 转向执行作业计划详细信息浏览页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日志
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }

    }

    private ActionForward performDown(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid(); // 当前用户名
        // 初始化数据
        ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
        TawwpMonthPlanVO tawwpMonthPlanVO = null;
        // 获取页面传递过来的参数
        String monthPlanId = (String) request.getSession().getAttribute(
                "monthPlanId");// 月度作业计划编号
        try {
            // 获取VO对象
            tawwpMonthPlanVO = tawwpExecuteMgr.viewExecutePlan(monthPlanId);
            // 为页面显示准备数据
            request.setAttribute("tawwpmonthplanvo", tawwpMonthPlanVO);
            request.setAttribute("userid", userId);
            // 转向执行作业计划详细信息浏览页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日志
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * 显示作业计划执行内容整体执行情况
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     */
    private ActionForward performViewMonthContent(ActionMapping mapping,
                                                  ActionForm actionForm, HttpServletRequest request,
                                                  HttpServletResponse response) {

        // 初始化数据
        ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
        TawwpExecuteContentVO tawwpExecuteContentVO = null;
        ITawwpExecuteCheckMgr tawwpExecuteCheckMgr = (ITawwpExecuteCheckMgr) getBean("tawwpExecuteCheckMgr");
        // 获取页面传递过来的参数
        String executeContentId = request.getParameter("executecontentid"); // 执行作业计划执行内容编号

        try {
            // 获取VO对象
            tawwpExecuteContentVO = tawwpExecuteMgr
                    .viewExecuteContent(executeContentId);
            TawwpExecuteCheck tawwpExecuteCheck = tawwpExecuteCheckMgr
                    .loadExecuteCheck(executeContentId);
            String reason = tawwpExecuteContentVO.getReason();
            if (reason == null || "".equals(reason)) {
                tawwpExecuteContentVO.setReason("");
            }
            // 为页面显示准备数据
            request
                    .setAttribute("tawwpexecutecontentvo",
                            tawwpExecuteContentVO);
            request.setAttribute("tawwpExecuteCheck", tawwpExecuteCheck);
            // 转向执行作业计划详细信息浏览页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日志
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    //	private ActionForward performStatYearAll(ActionMapping mapping,
//			ActionForm actionForm, HttpServletRequest request,
//			HttpServletResponse response) {
//		ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr"); // 初始化作业计划查询统计管理BO类
//
//		String yearFlag = request.getParameter("yearflag");
//		String monthFlag = request.getParameter("monthflag");
//		int dayNow = StaticMethod.null2int(TawwpUtil.getCurrentDateTime("dd"));
//		int dayFlag = StaticMethod.null2int(WorkplanMgrLocator.getAttributes().getStatday());
//		String yesrNow = TawwpUtil.getCurrentDateTime("yyyy");
//		String monthNow = TawwpUtil.getCurrentDateTime("MM");
//		if (yearFlag == null) {
//			yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
//			monthFlag = TawwpUtil.getCurrentDateTime("MM");
//		} else {
//			if (StaticMethod.null2int(monthFlag) < 10) {
//				monthFlag = "0" + monthFlag;
//			}
//		}
//		StringBuffer str = new StringBuffer();
//		;
//		Enumeration enumeration = null;
//		Object object = null;
//		TawwpStatVO tawwpStatVO = null;
//
//		String url = "../tawwpstat/statyearregion.do?yearflag=" + yearFlag
//				+ "&monthflag=" + monthFlag + "&deptid=";
//		String urltemp = null;
//		Hashtable hashtable = new Hashtable();
//		try {
//			hashtable = tawwpStatMgr.statMonthPlan(yearFlag, monthFlag);
//		} catch (TawwpException e1) {
//			e1.printStackTrace();
//		}
//		enumeration = hashtable.keys();
//
//		// 循环增加统计数据
//		while (enumeration.hasMoreElements()) {
//			object = enumeration.nextElement();
//			tawwpStatVO = (TawwpStatVO) hashtable.get(object);
//
//			urltemp = url + tawwpStatVO.getTawDept().getDeptId();
//			str.append(tawwpStatVO.writeHTML(urltemp));
//		}
//		try {
//
//			request.setAttribute("yearflag", yearFlag);
//			request.setAttribute("monthflag", monthFlag);
//			request.setAttribute("str", str.toString());
//			request.setAttribute("dayFlag", Integer.toString(dayFlag));
//			request.setAttribute("dayNow", Integer.toString(dayNow));
//			request.setAttribute("yearNow", yesrNow);
//			request.setAttribute("monthNow", monthNow);
//			return mapping.findForward("success");
//		} catch (Exception e) {
//			generalError(request, e);
//			ActionMessages messages = new ActionMessages();
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"submit.title.failure"));
//			saveMessages(request, messages);
//			return mapping.findForward("failure");
//		}
//	}
//	private ActionForward performStatYearAll(ActionMapping mapping,
//			ActionForm actionForm, HttpServletRequest request,
//			HttpServletResponse response) {
//		ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr"); // 初始化作业计划查询统计管理BO类
//
//		String yearFlag = request.getParameter("yearflag");
//		String monthFlag = request.getParameter("monthflag");
//		String  deptId=StaticMethod.null2String(request.getParameter("deptid"));
//		if("".equals(deptId)){
//			deptId="-1";
//		}
//		ITawSystemDeptManager iTawSystemDeptManager = (ITawSystemDeptManager) ApplicationContextHolder
//		.getInstance().getBean("ItawSystemDeptManager");
//		int dayNow = StaticMethod.null2int(TawwpUtil.getCurrentDateTime("dd"));
//		int dayFlag = StaticMethod.null2int(WorkplanMgrLocator.getAttributes().getStatday());
//		String yesrNow = TawwpUtil.getCurrentDateTime("yyyy");
//		String monthNow = TawwpUtil.getCurrentDateTime("MM");
//		if (yearFlag == null) {
//			yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
//			monthFlag = TawwpUtil.getCurrentDateTime("MM");
//		} else {
//			if (StaticMethod.null2int(monthFlag) < 10 && monthFlag.length()==1) {
//				monthFlag = "0" + monthFlag;
//			}
//		}
//		StringBuffer str = new StringBuffer();
//		;
//		Enumeration enumeration = null;
//		Object object = null;
//		TawwpStatVO tawwpStatVO = null;
//
//		String url = "../tawwpstat/statyearregion.do?yearflag=" + yearFlag
//		+ "&monthflag=" + monthFlag + "&deptid=";
//		List deptList=iTawSystemDeptManager.getNextLevecDepts(deptId,"0");
//		if(deptList.size()>0){
//		for(int i=0;i<deptList.size();i++){
//			TawSystemDept tawSystemDept =new TawSystemDept();
//			TawwpStatVO tawwpStatvO=new TawwpStatVO();
//			tawSystemDept=(TawSystemDept) deptList.get(i);
//		String urltemp = null;
//		Hashtable hashtable = new Hashtable();
//			try {
//			hashtable = tawwpStatMgr.statMonthPlan(yearFlag, monthFlag,tawSystemDept.getDeptId());
//		} catch (TawwpException e1) {
//			e1.printStackTrace();
//		}
//		enumeration = hashtable.keys();
//
//		// 循环增加统计数据
//		
//		while (enumeration.hasMoreElements()) {
//			object = enumeration.nextElement();
//			tawwpStatVO = (TawwpStatVO) hashtable.get(object);
//			tawwpStatVO.getTawDept().getParentDeptid();
//			System.out.print(tawwpStatVO.getTawDept().getParentDeptid());
//			//周期为天的部门统计的的总数，及时数，完成数
//			tawwpStatvO.setDayCount(tawwpStatvO.getDayCount()+tawwpStatVO.getDayCount());
//			tawwpStatvO.setDayInTimeCount(tawwpStatvO.getDayInTimeCount()+tawwpStatVO.getDayInTimeCount());
//			tawwpStatvO.setDayOutTimeCount(tawwpStatvO.getDayOutTimeCount()+tawwpStatVO.getDayOutTimeCount());
//			tawwpStatvO.setDayNormalCount(tawwpStatvO.getDayNormalCount()+tawwpStatVO.getDayNormalCount());
//			//周期为年的的部门统计的总数，及时数，完成数
//			tawwpStatvO.setYearCount(tawwpStatvO.getYearCount()+tawwpStatVO.getYearCount());
//			tawwpStatvO.setYearInTimeCount(tawwpStatvO.getYearInTimeCount()+tawwpStatVO.getYearInTimeCount());
//			tawwpStatvO.setYearOutTimeCount(tawwpStatvO.getYearOutTimeCount()+tawwpStatVO.getYearOutTimeCount());
//			tawwpStatvO.setYearNormalCount(tawwpStatvO.getYearNormalCount()+tawwpStatVO.getYearNormalCount());
//			/*
//			 * 周期为月的的统计
//			 * 总数
//			 * 及时数
//			 * 完成数
//			 * */
//			tawwpStatvO.setMonthCount(tawwpStatvO.getMonthCount()+tawwpStatVO.getMonthCount());
//			tawwpStatvO.setMonthInTimeCount(tawwpStatvO.getMonthInTimeCount()+tawwpStatVO.getMonthInTimeCount());
//			tawwpStatvO.setMonthNormalCount(tawwpStatvO.getMonthNormalCount()+tawwpStatVO.getMonthNormalCount());
//			tawwpStatvO.setMonthOutTimeCount(tawwpStatvO.getMonthOutTimeCount()+tawwpStatVO.getMonthOutTimeCount());
//			
////			tawwpStatvO.setFourmonthCount(tawwpStatvO.getFourmonthCount()+tawwpStatVO.getFourmonthCount());
////			tawwpStatvO.setFourmonthInTimeCount(tawwpStatvO.getFourmonthInTimeCount()+tawwpStatVO.getFourmonthInTimeCount());
////			tawwpStatvO.setFourmonthNormalCount(tawwpStatvO.getFourmonthNormalCount()+tawwpStatVO.getFourmonthNormalCount());
////			tawwpStatvO.setFourmonthOutTimeCount(tawwpStatvO.getFourmonthOutTimeCount()+tawwpStatVO.getFourmonthOutTimeCount());
//			/*
//			 * 周期为周的的统计
//			 * 总数
//			 * 及时数
//			 * 完成数
//			 * */
//			tawwpStatvO.setWeekCount(tawwpStatvO.getWeekCount()+tawwpStatVO.getWeekCount());
//			tawwpStatvO.setWeekInTimeCount(tawwpStatvO.getWeekInTimeCount()+tawwpStatVO.getWeekInTimeCount());
//			tawwpStatvO.setWeekNormalCount(tawwpStatvO.getWeekNormalCount()+tawwpStatVO.getWeekNormalCount());
//			tawwpStatvO.setWeekOutTimeCount(tawwpStatvO.getWeekOutTimeCount()+tawwpStatVO.getWeekOutTimeCount());
//			/*
//			 * 周期为季度的的统计
//			 * 总数
//			 * 及时数
//			 * 完成数
//			 * */
//			tawwpStatvO.setQuarterCount(tawwpStatvO.getQuarterCount()+tawwpStatVO.getQuarterCount());
//			tawwpStatvO.setQuarterInTimeCount(tawwpStatvO.getQuarterInTimeCount()+tawwpStatVO.getQuarterInTimeCount());
//			tawwpStatvO.setQuarterNormalCount(tawwpStatvO.getQuarterNormalCount()+tawwpStatVO.getQuarterNormalCount());
//			tawwpStatvO.setQuarterOutTimeCount(tawwpStatvO.getQuarterOutTimeCount()+tawwpStatVO.getQuarterOutTimeCount());
//			/*
//			 * 周期为季度的的统计
//			 * 总数
//			 * 及时数
//			 * 完成数
//			 * */
//			tawwpStatvO.setHalfmonthCount(tawwpStatvO.getHalfmonthCount()+tawwpStatVO.getHalfmonthCount());
//			tawwpStatvO.setHalfmonthInTimeCount(tawwpStatvO.getHalfmonthInTimeCount()+tawwpStatVO.getHalfmonthInTimeCount());
//			tawwpStatvO.setHalfmonthNormalCount(tawwpStatvO.getHalfmonthNormalCount()+tawwpStatVO.getHalfmonthNormalCount());
//			tawwpStatvO.setHalfmonthOutTimeCount(tawwpStatvO.getHalfmonthOutTimeCount()+tawwpStatVO.getHalfmonthOutTimeCount());
//			/*
//			 * 周期为半年的的统计
//			 * 总数
//			 * 及时数
//			 * 完成数
//			 * */
//			tawwpStatvO.setHalfyearCount(tawwpStatvO.getHalfyearCount()+tawwpStatVO.getHalfyearCount());
//			tawwpStatvO.setHalfyearInTimeCount(tawwpStatvO.getHalfyearInTimeCount()+tawwpStatVO.getHalfyearInTimeCount());
//			tawwpStatvO.setHalfyearInTimeCount(tawwpStatvO.getHalfyearInTimeCount()+tawwpStatVO.getHalfyearInTimeCount());
//			tawwpStatvO.setHalfyearNormalCount(tawwpStatvO.getHalfyearNormalCount()+tawwpStatVO.getHalfyearNormalCount());
//			tawwpStatvO.setHalfyearOutTimeCount(tawwpStatvO.getHalfyearOutTimeCount()+tawwpStatVO.getHalfyearOutTimeCount());
//			tawwpStatvO.setTempCount(tawwpStatvO.getTempCount()+tawwpStatVO.getTowmonthCount()+tawwpStatVO.getFourmonthCount()+tawwpStatVO.getTempCount());
//			tawwpStatvO.setTempInTimeCount(tawwpStatvO.getTempInTimeCount()+tawwpStatVO.getTowmonthInTimeCount()+tawwpStatVO.getFourmonthInTimeCount()+tawwpStatVO.getTempInTimeCount());
//			tawwpStatvO.setTempNormalCount(tawwpStatvO.getTempNormalCount()+tawwpStatVO.getTempNormalCount()+tawwpStatVO.getTowmonthNormalCount()+tawwpStatVO.getFourmonthNormalCount()+tawwpStatVO.getTempNormalCount());
//			tawwpStatvO.setTempOutTimeCount(tawwpStatvO.getTempOutTimeCount()+tawwpStatVO.getTempOutTimeCount()+tawwpStatVO.getTowmonthOutTimeCount()+tawwpStatVO.getFourmonthOutTimeCount());
//			
//			tawwpStatvO.setAllCount(tawwpStatvO.getAllCount()+tawwpStatVO.getAllCount());
//			tawwpStatvO.setAllinTimeCount(tawwpStatvO.getAllinTimeCount()+tawwpStatVO.getAllinTimeCount());
//			tawwpStatvO.setAllNormalCount(tawwpStatvO.getAllNormalCount()+tawwpStatVO.getAllNormalCount());
//			tawwpStatvO.setAlloutTimeCount(tawwpStatvO.getAlloutTimeCount()+tawwpStatVO.getAlloutTimeCount());
//			tawwpStatvO.setConstituteCount(tawwpStatvO.getConstituteCount()+tawwpStatVO.getConstituteCount());
//		}
//		
//		if(tawwpStatvO!=null){
//			urltemp = url + tawSystemDept.getDeptId();
//			tawwpStatvO.setTawDept(tawSystemDept);
////			tawwpStatvO.getCycle()
//		    str.append(tawwpStatvO.writeStatHTML(yearFlag,monthFlag,tawSystemDept.getDeptId()));
//		}
//		}
//		}
//		try {
//
//			request.setAttribute("yearflag", yearFlag);
//			request.setAttribute("monthflag", monthFlag);
//			request.setAttribute("str", str.toString());
//			request.setAttribute("dayFlag", Integer.toString(dayFlag));
//			request.setAttribute("dayNow", Integer.toString(dayNow));
//			request.setAttribute("yearNow", yesrNow);
//			request.setAttribute("monthNow", monthNow);
//			return mapping.findForward("success");
//		} catch (Exception e) {
//			generalError(request, e);
//			ActionMessages messages = new ActionMessages();
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"submit.title.failure"));
//			saveMessages(request, messages);
//			return mapping.findForward("failure");
//		}
//	}
    private ActionForward performStatYearAll(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr"); // 初始化作业计划查询统计管理BO类

        String yearFlag = request.getParameter("yearflag");
        String monthFlag = request.getParameter("monthflag");
        int dayNow = StaticMethod.null2int(TawwpUtil.getCurrentDateTime("dd"));
        int dayFlag = StaticMethod.null2int(WorkplanMgrLocator.getAttributes().getStatday());
        String yesrNow = TawwpUtil.getCurrentDateTime("yyyy");
        String monthNow = TawwpUtil.getCurrentDateTime("MM");
        if (yearFlag == null) {
            yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
            monthFlag = TawwpUtil.getCurrentDateTime("MM");
        } else {
            if (StaticMethod.null2int(monthFlag) < 10) {
                monthFlag = "0" + monthFlag;
            }
        }
        StringBuffer str = new StringBuffer();
        ;
        Enumeration enumeration = null;
        Object object = null;
        TawwpStatVO tawwpStatVO = null;

        String url = "../tawwpstat/statyearregion.do?yearflag=" + yearFlag
                + "&monthflag=" + monthFlag + "&deptid=";
        String urltemp = null;
        Hashtable hashtable = new Hashtable();
        try {
            hashtable = tawwpStatMgr.statMonthPlan(yearFlag, monthFlag);
        } catch (TawwpException e1) {
            e1.printStackTrace();
        }
        enumeration = hashtable.keys();

        // 循环增加统计数据
        while (enumeration.hasMoreElements()) {
            object = enumeration.nextElement();
            tawwpStatVO = (TawwpStatVO) hashtable.get(object);

            urltemp = url + tawwpStatVO.getTawDept().getDeptId();
            str.append(tawwpStatVO.writeHTML(urltemp));
        }
        try {

            request.setAttribute("yearflag", yearFlag);
            request.setAttribute("monthflag", monthFlag);
            request.setAttribute("str", str.toString());
            request.setAttribute("dayFlag", Integer.toString(dayFlag));
            request.setAttribute("dayNow", Integer.toString(dayNow));
            request.setAttribute("yearNow", yesrNow);
            request.setAttribute("monthNow", monthNow);
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

    private ActionForward performStatYearRegion(ActionMapping mapping,
                                                ActionForm actionForm, HttpServletRequest request,
                                                HttpServletResponse response) {
        ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr"); // 初始化作业计划查询统计管理BO类
        String yearFlag = null;
        String monthFlag = null;
        yearFlag = request.getParameter("yearflag");
        monthFlag = request.getParameter("monthflag");
        String deptId = request.getParameter("deptid");
        Hashtable hashtable = null;
        Enumeration enumeration = null;
        Object object = null;
        TawwpStatVO tawwpStatVO = null;
        String url = "../tawwpstat/statyeardept.do?yearflag=" + yearFlag
                + "&monthflag=" + monthFlag + "&deptid=";
        String urltemp = null;
        try {
            StringBuffer str = new StringBuffer();
            hashtable = tawwpStatMgr.statMonthPlanByRegion(yearFlag, monthFlag,
                    deptId);
            enumeration = hashtable.keys();

            while (enumeration.hasMoreElements()) {
                object = enumeration.nextElement();
                tawwpStatVO = (TawwpStatVO) hashtable.get(object);
                urltemp = url + tawwpStatVO.getTawDept().getDeptId();
//				str.append(tawwpStatVO.writeHTML(urltemp));
                str.append(tawwpStatVO.writeStatHTML(yearFlag, monthFlag, tawwpStatVO.getTawDept().getDeptId()));
            }
            request.setAttribute("str", str.toString());
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

    private ActionForward performStatYearMonthPlan(ActionMapping mapping,
                                                   ActionForm actionForm, HttpServletRequest request,
                                                   HttpServletResponse response) {

        ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr"); // 初始化作业计划查询统计管理BO类
        List list = null;

        String yearFlag = null;
        String monthFlag = null;
        String deptId = null;

        yearFlag = request.getParameter("yearflag");
        monthFlag = request.getParameter("monthflag");
        deptId = request.getParameter("deptid");

        try {
            list = tawwpStatMgr.statMonthPlanByMonthPlan(yearFlag, monthFlag,
                    deptId); // 获取月份的统计信息
            request.setAttribute("statyearall", list);
            request.setAttribute("yearflag", yearFlag);
            request.setAttribute("monthflag", monthFlag);
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

    private ActionForward performReportYearPlan(ActionMapping mapping,
                                                ActionForm actionForm, HttpServletRequest request,
                                                HttpServletResponse response) {
        ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr"); // 初始化作业计划查询统计管理BO类

        String yearPlanId = null;
        String netId = null;

        yearPlanId = request.getParameter("yearPlanId");
        netId = request.getParameter("netId");

        TawwpStatYearPlanVO tawwpStatYearPlanVO = null;

        try {
            tawwpStatYearPlanVO = tawwpStatMgr
                    .reportYearPlan(yearPlanId, netId);

            request.setAttribute("statyearplan", tawwpStatYearPlanVO);
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
     * 执行意见保存Action
     *
     * @param mapping    ActionMapping 集合
     * @param actionForm ActionForm 数据模型
     * @param request    HttpServletRequest 请求
     * @param response   HttpServletResponse 应答
     * @return ActionForward 转向对象
     */
    private ActionForward performSaveExecuteAssess(ActionMapping mapping,
                                                   ActionForm actionForm, HttpServletRequest request,
                                                   HttpServletResponse response) {
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        // 获取页面传递的参数
        String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
                "");
        String content = TawwpUtil.getRequestValue(request, "content", "");
        String state = TawwpUtil.getRequestValue(request, "state", "");
        String checkDept = String.valueOf(TawSystemSessionForm.getDeptid());
        String checkUser = TawSystemSessionForm.getUserid();

        // 初始化数据
        ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr");

        try {

            // 保存执行意见
            tawwpStatMgr.saveExecuteAssess(checkDept, checkUser, content,
                    state, monthPlanId);

            // 转向月度作业计划查询结果页面
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); // 将错误信息加入到消息队列、写入日志
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure"); // 转向错误页面
        }
    }

    /**
     * add by denden 贵州本地
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     */
    private ActionForward performListGZMonthPlan(ActionMapping mapping,
                                                 ActionForm actionForm, HttpServletRequest request,
                                                 HttpServletResponse response) {

        ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr"); // 初始化作业计划查询统计管理BO类
        List list = null;

        String deptId = request.getParameter("deptid");
        String yearFlag = request.getParameter("yearflag");
        String monthFlag = request.getParameter("monthflag");
        // 开始时间
        String startDate = request.getParameter("sDate");
        // 结束时间
        String endDate = request.getParameter("eDate");
        //是否关联子部门
        String isNext = request.getParameter("isNext");
        // 一个月的总天数
        int dayCount = 0;
        // String sysTypeId = request.getParameter("systype");
        // String netTypeId = request.getParameter("nettype");
        String constituteState = request.getParameter("constitutestate");
        String executeState = request.getParameter("executestate");
        // String netId = request.getParameter("netlist");

        HashMap hashMap = new HashMap();

        // 组织查询条件

        if (deptId != null && !deptId.equals("")) {
            hashMap.put("deptId", deptId); // 部门信息
        }
        hashMap.put("isNext", isNext); // 部门信息
        if (!yearFlag.equals("0")) {
            hashMap.put("yearFlag", yearFlag); // 年度
        }
        if (!monthFlag.equals("0")) {
            hashMap.put("monthFlag", monthFlag); // 月度
        }
        hashMap.put("deleted", "0"); // 月度
        // 传时间参数
        /**
         * 判断传过去的时间参数 add by denden
         */
        if (startDate.equals("01") && endDate.equals("01")) {

            try {
                dayCount = TawwpUtil.getDayCountOfMonth(yearFlag, monthFlag);
            } catch (Exception e) {
                e.printStackTrace();
            }
            startDate = yearFlag + "-" + monthFlag + "-" + startDate
                    + " 00:00:00";
            request.setAttribute("startDategz", startDate);
            String countday = String.valueOf(dayCount);
            endDate = yearFlag + "-" + monthFlag + "-" + countday + " 00:00:00";
            request.setAttribute("endDategz", endDate);
        } else {
            if (startDate != null && !startDate.equals("0")) {
                startDate = yearFlag + "-" + monthFlag + "-" + startDate
                        + " 00:00:00";
                request.setAttribute("startDategz", startDate);
            } else {
                request.setAttribute("startDategz", "0");
            }
            if (endDate != null && !endDate.equals("0")) {
                endDate = yearFlag + "-" + monthFlag + "-" + endDate
                        + " 00:00:00";
                request.setAttribute("endDategz", endDate);
            } else {
                request.setAttribute("endDategz", "0");
            }
        }
        // end by denden

        // if (!sysTypeId.equals("0")) {
        // hashMap.put("sysTypeId", sysTypeId); //系统类别
        // }
        // if (!netTypeId.equals("0")) {
        // hashMap.put("netTypeId", netTypeId); //网元类型
        // }
        if (!constituteState.equals("-1")) {
            hashMap.put("constituteState", constituteState); // 制定状态
        }
        if (!executeState.equals("-1")) {
            hashMap.put("executeState", executeState); // 执行状态
            request.getSession().removeAttribute("executeState");
            request.getSession().setAttribute("executeState", executeState);
        }
        // if (!netId.equals("")) {
        // hashMap.put("netId", netId); //网元信息
        // }

        try {
            list = tawwpStatMgr.queryMonthPlan(hashMap); // 查询符合条件的月度作业计划
            request.setAttribute("querymonthplan", list);
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

    private ActionForward performListGZMonthUser(ActionMapping mapping,
                                                 ActionForm actionForm, HttpServletRequest request,
                                                 HttpServletResponse response) {

        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr"); // 初始化作业计划查询统计管理BO类
        // List list = null;

        String monthPlanId = request.getParameter("monthplanid");
        // 开始时间
        String startDategz = request.getParameter("startDategz");
        // 结束时间
        String endDategz = request.getParameter("endDategz");
        String executeState = (String) request.getSession().getAttribute("executeState");
        //将原来的	List monthExecuteUser = tawwpMonthMgr.getMonthExecuteUser(monthPlanId)修改为
        List monthExecuteUser = tawwpMonthMgr.getMonthExecuteUser(monthPlanId, executeState);

        List monthExecuteuserId = tawwpMonthMgr.getMonthExecuteStat(
                monthPlanId, startDategz, endDategz);
        List User = new ArrayList();
        List Type = new ArrayList();
        List monthPlan = new ArrayList();
        List name = new ArrayList();
        List monthExecuteuserList = new ArrayList();
        for (int i = 0; i < monthExecuteuserId.size(); i++) {
            Object[] object = (Object[]) monthExecuteuserId.get(i);
            if (object.length >= 4) {
                User.add(object[0]);
                Type.add(object[1]);
                monthPlan.add(object[2]);
                name.add(object[3]);
            }
        }
        monthExecuteuserList.add(User);
        monthExecuteuserList.add(Type);
        monthExecuteuserList.add(monthPlan);
        monthExecuteuserList.add(name);
        List getUser = new ArrayList();
        if (monthExecuteuserList.size() > 0) {
            getUser = (List) monthExecuteuserList.get(0);
        }
        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        String users = "";
        if (getUser.size() > 0) {
            users = StaticMethod.nullObject2String(getUser.get(0));
        }
        String executer = tawwpUtilDAO.getUserNames(users);
        List anshi = tawwpMonthMgr.getMonthExecuteUserAll(monthPlanId, 1);
        List chaoshi = tawwpMonthMgr.getMonthExecuteUserAll(monthPlanId, 2);
        List weizhixing = tawwpMonthMgr.getMonthExecuteUserAll(monthPlanId, 0);
        if ("".equals(monthPlanId)) {
            return mapping.findForward("failure");
        }
        if (!startDategz.equals("0")) {
            request.setAttribute("startDategz", startDategz);
        } else {
            request.setAttribute("startDategz", "0");
        }
        if (!endDategz.equals("0")) {
            request.setAttribute("endDategz", endDategz);
        } else {
            request.setAttribute("endDategz", "0");
        }

        request.setAttribute("monthExecuteUser", monthExecuteUser);
        request.setAttribute("monthExecuteuserId", monthExecuteuserList);
        request.setAttribute("monthPlanId", monthPlanId);
        request.setAttribute("anshi", anshi);
        request.setAttribute("chaoshi", chaoshi);
        request.setAttribute("executer", executer);
        request.setAttribute("weizhixing", weizhixing);
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 统计选择查询页面
     */
    private ActionForward performStatSelect(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) {

        int _yy = 0;
        int _mm = 0;
        Calendar c;
        c = Calendar.getInstance();
        long realtime = c.getTimeInMillis();
        c.setTimeInMillis(realtime);
        _yy = c.get(Calendar.YEAR);
        _mm = c.get(Calendar.MONTH);

        request.setAttribute("year", _yy + "");
        request.setAttribute("month", _mm + "");
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 统计
     */
    private ActionForward performStatQuery(ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) {
        // TODO 统计....脑子有点乱
        String forward = "";
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String deptId = TawSystemSessionForm.getDeptid();
        String userid = request.getParameter("userid");
        String deptid = request.getParameter("deptid");
        String roomid = request.getParameter("roomid");
        String systype = request.getParameter("systype");
        String nettype = request.getParameter("nettype");
        String netlist = request.getParameter("netlist");
        String yearflag = request.getParameter("yearflag");
        String monthflag = request.getParameter("monthflag");
        String type = request.getParameter("type");
        StringBuffer str = new StringBuffer();
        Enumeration enumeration = null;
        Object object = null;
        TawwpStatVO tawwpStatVO = null;
        try {
            // 为前台准备数据
            request.setAttribute("userid", userid);
            request.setAttribute("deptid", deptid);
            request.setAttribute("roomid", roomid);
            request.setAttribute("systype", systype);
            request.setAttribute("nettype", nettype);
            request.setAttribute("netlist", netlist);
            request.setAttribute("yearflag", yearflag);
            request.setAttribute("monthflag", monthflag);
            request.setAttribute("type", type);

            ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr");

            Hashtable hashtable = new Hashtable();

            if ("nettyperadio".equals(type)) { // 如果是按照网元统计

                try {
                    hashtable = tawwpStatMgr.statMonthPlan(systype, nettype,
                            netlist, yearflag, monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);

                    str.append(tawwpStatVO.writeHTMLNet());

                }
                request.setAttribute("str", str.toString());
                forward = "netforward";
            } else if ("roomType".equals(type)) { // 机房
                try {
                    hashtable = tawwpStatMgr.statMonthPlanRoom(roomid,
                            yearflag, monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);

                    str.append(tawwpStatVO.writeHTMLRoom());

                }
                request.setAttribute("str", str.toString());
                forward = "roomforward";

            } else if ("userType".equals(type)) { // 人员

                try {
                    hashtable = tawwpStatMgr.statMonthPlanUser(userid, deptId,
                            yearflag, monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);

                    str.append(tawwpStatVO.writeHTMLUser());

                }
                request.setAttribute("str", str.toString());

                forward = "userforward";

            } else if ("deptType".equals(type)) { // 部门
                userid = "";
                if ("".equals(deptid))
                    deptid = deptId;
                try {
                    hashtable = tawwpStatMgr.statMonthPlanUser(userid, deptid,
                            yearflag, monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);
                    str.append(tawwpStatVO.writeHTMLUser());

                }
                request.setAttribute("str", str.toString());

                forward = "userforward";
            } else { // 按照月份统计
                userid = "";
                if ("".equals(deptid))
                    deptid = deptId;
                try {
                    hashtable = tawwpStatMgr.statMonthPlanTime(yearflag,
                            monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);
                    str.append(tawwpStatVO.writeHTMLTime());

                }
                request.setAttribute("str", str.toString());

                forward = "timeforward";
            }

        } catch (Exception e) {
            e.printStackTrace();
            forward = "false";
        }
        return mapping.findForward(forward);
    }

    /**
     * 统计导出
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    private ActionForward performDownExcel(ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) {
        String forward = "";
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String deptId = TawSystemSessionForm.getDeptid();
        String userid = request.getParameter("userid");
        String deptid = request.getParameter("deptid");
        String roomid = request.getParameter("roomid");
        String systype = request.getParameter("systype");
        String nettype = request.getParameter("nettype");
        String netlist = request.getParameter("netlist");
        String yearflag = request.getParameter("yearflag");
        String monthflag = request.getParameter("monthflag");
        String type = request.getParameter("type");
        StringBuffer str = new StringBuffer();
        Enumeration enumeration = null;
        Object object = null;
        TawwpStatVO tawwpStatVO = null;
        try {
            // 为前台准备数据
            request.setAttribute("userid", userid);
            request.setAttribute("deptid", deptid);
            request.setAttribute("roomid", roomid);
            request.setAttribute("systype", systype);
            request.setAttribute("nettype", nettype);
            request.setAttribute("netlist", netlist);
            request.setAttribute("yearflag", yearflag);
            request.setAttribute("monthflag", monthflag);
            request.setAttribute("type", type);

            ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr");

            Hashtable hashtable = new Hashtable();

            if ("nettyperadio".equals(type)) { // 如果是按照网元统计

                try {
                    hashtable = tawwpStatMgr.statMonthPlan(systype, nettype,
                            netlist, yearflag, monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);

                    str.append(tawwpStatVO.writeHTMLNet());

                }
                request.setAttribute("str", str.toString());
                forward = "netforward";
            } else if ("roomType".equals(type)) { // 机房
                try {
                    hashtable = tawwpStatMgr.statMonthPlanRoom(roomid,
                            yearflag, monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);

                    str.append(tawwpStatVO.writeHTMLRoom());

                }
                request.setAttribute("str", str.toString());
                forward = "roomforward";

            } else if ("userType".equals(type)) { // 人员

                try {
                    hashtable = tawwpStatMgr.statMonthPlanUser(userid, deptId,
                            yearflag, monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);

                    str.append(tawwpStatVO.writeHTMLUser());

                }
                request.setAttribute("str", str.toString());

                forward = "userforward";

            } else if ("deptType".equals(type)) { // 部门
                userid = "";
                if ("".equals(deptid))
                    deptid = deptId;
                try {
                    hashtable = tawwpStatMgr.statMonthPlanUser(userid, deptid,
                            yearflag, monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);
                    str.append(tawwpStatVO.writeHTMLUser());

                }
                request.setAttribute("str", str.toString());

                forward = "userforward";
            } else { // 按照月份统计
                userid = "";
                if ("".equals(deptid))
                    deptid = deptId;
                try {
                    hashtable = tawwpStatMgr.statMonthPlanTime(yearflag,
                            monthflag);
                } catch (TawwpException e1) {
                    e1.printStackTrace();
                }
                enumeration = hashtable.keys();
                // 循环增加统计数据
                while (enumeration.hasMoreElements()) {
                    object = enumeration.nextElement();
                    tawwpStatVO = (TawwpStatVO) hashtable.get(object);
                    str.append(tawwpStatVO.writeHTMLTime());

                }
                request.setAttribute("str", str.toString());

                forward = "timeforward";
            }

        } catch (Exception e) {
            e.printStackTrace();
            forward = "false";
        }
        return mapping.findForward(forward);
    }

    // *****************网元ACTION*********************//

    /**
     * 显示编辑网元
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */
    private ActionForward performNetSelect(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        try {
            TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");

            String userId = TawSystemSessionForm.getUserid(); // 当前用户
            String deptId = TawSystemSessionForm.getDeptid();
            String netTypeId = request.getParameter("nettypeid"); // 网元类型

            ITawwpNetMgr tawwpNetMgr = (ITawwpNetMgr) getBean("tawwpNetMgr");
            List netlist = tawwpNetMgr.listNetByTypeDept(netTypeId, deptId);

            request.setAttribute("netlist", netlist);

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
        BocoLog.error(this, 2, "[WORKPLAN_STAT] Error -", e);
    }

    private ActionForward performCheckMonthPlanResult(ActionMapping mapping
            , ActionForm actionForm,
                                                      HttpServletRequest request
            , HttpServletResponse response) {
        //获取页面传递的参数
        String yearFlag = request.getParameter("yearflag");
        String monthFlag = request.getParameter("monthflag");
        String deptId = request.getParameter("deptid");
        //added 2007-08-13 添加查询条件"周期"
        String cycle = request.getParameter("cycle");
        String cycleName = "全部";
        List list = null;

        //初始化数据
        ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr"); // 初始化作业计划查询统计管理BO类
        //ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr");

        try {
            //部门的月度作业计划
            list = tawwpMonthMgr.listMonthPlanOfChildDept(deptId, yearFlag,
                    monthFlag, cycle);

            //added 2007-08-13 如果周期条件选择不为"全部",添加月度作业计划过滤逻辑
            if (cycle != null && !"".equals(cycle) && !"-1".equals(cycle)) {
                cycleName = TawwpStaticVariable.CYCLENAME[Integer.parseInt(cycle)];
            }

            //转向月度作业计划查询结果页面
            request.setAttribute("monthplan", list);
            //added 2007-08-13 增加传递给查询结果页面的参数
            request.setAttribute("cycle", cycle);
            request.setAttribute("cyclename", cycleName);
            return mapping.findForward("success");

        } catch (Exception e) {
            generalError(request, e); //将错误信息加入到消息队列、写入日志
            return mapping.findForward("failure"); //转向错误页面
        }
    }

    private ActionForward performCheckMonthPlanContent(ActionMapping mapping
            , ActionForm actionForm,
                                                       HttpServletRequest request
            , HttpServletResponse response) {
        //获取页面传递的参数

        String monthPlanId = request.getParameter("monthplanid");
        if (monthPlanId.equals("null")) {
            monthPlanId = (String) request.getAttribute("monthplanid");
        }

        //added 2007-08-13 增加了周期参数的传入
        String cycle = request.getParameter("cycle");
        String cycleName = "";
        if (cycle == null || "".equals(cycle) || "null".equals(cycle)) {
            cycle = "-1";
        }

        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");

        if (startDate == null) {
            startDate = TawwpUtil.getCurrentDateTime("yyyy-MM-dd 00:00:00");
            endDate = TawwpUtil.getCurrentDateTime("yyyy-MM-dd 23:59:59");
        }

        //初始化数据
        ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
        Hashtable hashtable = null;
        Vector vector = TawwpUtil.getDateArray(startDate, endDate);

        try {
            //如果为全部周期
            if ("-1".equals(cycle)) {
                //获取执行内容
                hashtable = tawwpExecuteMgr.listExecuteContent(monthPlanId,
                        startDate, endDate);
                cycleName = "全部";
            }
            //其他周期的情况
            else {
                hashtable = tawwpExecuteMgr.listExecuteContent(monthPlanId,
                        startDate, endDate, cycle);
                cycleName = TawwpStaticVariable.CYCLENAME[Integer.parseInt(cycle)];
            }

            //转向月度作业计划查询结果页面
            request.setAttribute("executecontenthash", hashtable);
            request.setAttribute("monthplanid", monthPlanId);
            request.setAttribute("datearray", vector);
            request.setAttribute("startdate", startDate);
            request.setAttribute("enddate", endDate);
            //added 2007-08-13 增加传递给查询结果页面的参数
            request.setAttribute("cycle", cycle);
            request.setAttribute("cyclename", cycleName);

            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e); //将错误信息加入到消息队列、写入日志
            return mapping.findForward("failure"); //转向错误页面
        }
    }

    /*
      private ActionForward performCheckAllResult(ActionMapping mapping
                                                  , ActionForm actionForm,
                                                  HttpServletRequest request
                                                  , HttpServletResponse response) {
        //获取页面传递的参数
        String yearFlag = request.getParameter("yearflag");
        String monthFlag = request.getParameter("monthflag");
        String deptId = request.getParameter("deptid");
        String modelId = request.getParameter("modellist");
        List list = null;

        //初始化数据
        TawwpStatExtendBO tawwpStatExtendBO = new TawwpStatExtendBO();

        try {

          //部门的月度作业计划
          list = tawwpStatExtendBO.listCheckAll(deptId, yearFlag, modelId);

          //转向月度作业计划查询结果页面
          request.setAttribute("monthplan", list);
          return mapping.findForward("success");
        }
        catch (Exception e) {
          generalError(request, e); //将错误信息加入到消息队列、写入日志
          return mapping.findForward("failure"); //转向错误页面
        }
      }

      private ActionForward performCheckAllContent(ActionMapping mapping
                                                   , ActionForm actionForm,
                                                   HttpServletRequest request
                                                   , HttpServletResponse response) {
        //获取页面传递的参数

        String modelplanId = request.getParameter("modelplanid");
        if (modelplanId.equals("null")) {
          modelplanId = (String) request.getAttribute("modelplanid");
        }
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");

        String yearflag = request.getParameter("yearflag");
        String monthflag = request.getParameter("monthflag");
        String netid = request.getParameter("netid");
        String deptid = request.getParameter("deptid");

        if (startDate == null) {
          startDate = TawwpUtil.getCurrentDateTime("yyyy-MM-dd 00:00:00");
          endDate = TawwpUtil.getCurrentDateTime("yyyy-MM-dd 00:00:00");
        }

        //初始化数据
        TawwpStatExtendBO tawwpStatExtendBO = new TawwpStatExtendBO();
        Hashtable hashtable = null;
        List list = null;
        Vector vector = TawwpUtil.getDateArray(startDate, endDate);

        try {
          //获取执行内容
          list = tawwpStatExtendBO.listCheckAllResult(deptid, yearflag, monthflag,
                                                      modelplanId, netid, startDate,
                                                      endDate);

          //转向月度作业计划查询结果页面
          request.setAttribute("executecontentlist", list);

          request.setAttribute("modelplanid", modelplanId);
          request.setAttribute("datearray", vector);
          request.setAttribute("startdate", startDate);
          request.setAttribute("enddate", endDate);
          return mapping.findForward("success");
        }
        catch (Exception e) {
          generalError(request, e); //将错误信息加入到消息队列、写入日志
          return mapping.findForward("failure"); //转向错误页面
        }
      }
      */
    //added 2007-08-15 添加月度巡检导出功能
    private ActionForward performContentExport(ActionMapping mapping
            , ActionForm actionForm,
                                               HttpServletRequest request
            , HttpServletResponse response) {
        //获取页面传递的参数
        String monthPlanId = request.getParameter("monthplanid");
        if (monthPlanId.equals("null")) {
            monthPlanId = (String) request.getAttribute("monthplanid");
        }

        //added 2007-08-13 增加了周期参数的传入
        String cycle = request.getParameter("cycle");
        String cycleName = "";
        if (cycle == null || "".equals(cycle) || "null".equals(cycle) ||
                "-1".equals(cycle)) {
            cycle = "-1";
            cycleName = "全部";
        } else {
            cycleName = TawwpStaticVariable.CYCLENAME[Integer.parseInt(cycle)];
        }

        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");

        if (startDate == null) {
            startDate = TawwpUtil.getCurrentDateTime("yyyy-MM-dd 00:00:00");
            endDate = TawwpUtil.getCurrentDateTime("yyyy-MM-dd 23:59:59");
        }
        try {
            ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
            //Vector vector = TawwpUtil.getDateArray(startDate, endDate);
            String reaction = request.getParameter("reaction");
            String _exportUrl = tawwpExecuteMgr.exportToExcel(monthPlanId,
                    startDate, endDate, cycle);
            String systemPath = request.getRealPath("/");
            systemPath = com.boco.eoms.base.util.StaticMethod.getWebPath();

            String path = systemPath + "/" + _exportUrl;
            File file = new File(_exportUrl);
            // 读到流中
            InputStream inStream = new FileInputStream("/" + path);// 文件的存放路径
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
            //返回页面
            ActionForward actionForward =
                    new ActionForward(reaction + "?url=" +
                            _exportUrl);
            actionForward.setRedirect(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    private ActionForward performStatYearMonthPlanNew(ActionMapping mapping,
                                                      ActionForm actionForm, HttpServletRequest request,
                                                      HttpServletResponse response) {


        ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr"); // 初始化作业计划查询统计管理BO类
        List list = null;

        String yearFlag = null;
        String monthFlag = null;
        String deptId = null;
        String cycle = request.getParameter("cycle");
        String executeFlag = request.getParameter("executeFlag");
        yearFlag = request.getParameter("yearflag");
        monthFlag = request.getParameter("monthflag");
        deptId = request.getParameter("deptid");


        try {
            list = tawwpStatMgr.statMonthPlanByMonthPlanNew(yearFlag, monthFlag,
                    deptId, cycle, executeFlag); // 获取月份的统计信息
            request.setAttribute("statyearall", list);
            request.setAttribute("yearflag", yearFlag);
            request.setAttribute("monthflag", monthFlag);
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
}
