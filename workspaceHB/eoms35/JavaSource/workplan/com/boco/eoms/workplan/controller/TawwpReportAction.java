package com.boco.eoms.workplan.controller;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.mgr.ITawwpWorkplanReportMgr;


public class TawwpReportAction extends BaseAction{

	// 获取属性文件
	static {
		ResourceBundle prop = ResourceBundle
				.getBundle("resources.application_zh_CN");
	}

	/**
	 * 根据用户请求页面请求，进行页面跳转
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception  
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionForward myforward = null;

		// 获取请求的action属性
		String myaction = mapping.getParameter();
		// session超时处理
		try {
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据用户请求页面请求，进行页面跳转
		if (isCancelled(request)) {
			return mapping.findForward("cancel"); // 无效的请求，转向错误页面
		}
        else if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure"); // 条件为空，转向空页
		}
        else if ("TIME".equalsIgnoreCase(myaction)) {
			myforward = performTime(mapping, form, request, response); 
		}
        else if ("REPORT".equalsIgnoreCase(myaction)) {
			myforward = performReport(mapping, form, request, response); 
		}
		else {
			myforward = mapping.findForward("failure"); // 无效的请求，转向错误页面
		}
		return myforward;
	}
	private ActionForward performTime(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		String flag = request.getParameter("flag");
		String direct = null;
		try {
			if (flag.equals("1")){
				direct = "year";
			}
			if (flag.equals("2")){
				direct = "month";
			}
			if (flag.equals("3")){
				direct = "day";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return mapping.findForward(direct);

	}
	/**
	 * 通过接口向总部提交作业计划(年度、月度、日执行)
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
	 */
	private ActionForward performReport(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		String time = request.getParameter("time");
		int type = StaticMethod.null2int(request.getParameter("type"));
		ITawwpExecuteMgr executeMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ITawwpWorkplanReportMgr reportMgr = (ITawwpWorkplanReportMgr) getBean("tawwpWorkplanReportMgr");
		String mapFlag = null;
		try {
			if (type == 6) {
				// 生成年计划
				reportMgr.reportAllPlan(1, time, "");
				mapFlag = "year";
			}
			if (type == 8) {
				// 上报年计划
				executeMgr.newReportDir("year");
				mapFlag = "year";
			}
			
			
			
			if (type == 7) {
				// 生成月计划
				time = "0" + time;
				time = time.substring(time.length() - 2, time.length());
				String year = request.getParameter("year");
				reportMgr.reportAllPlan(2, time, year);
				mapFlag = "month";
			}
			if (type == 9) {
				// 上报月计划
				executeMgr.reportDir("month");
				executeMgr.newReportDir("month");
				mapFlag = "month";
			}
			
			
			
			if (type == 1) {
				// 生成日计划文件
				executeMgr.reportDayExecute(time);		 
				mapFlag = "day";
			}
			if (type == 2) {
				// 上报日计划
				executeMgr.reportExcel(time);
				executeMgr.newReportExcel(time);
				mapFlag = "day";
			}
			if (type == 3) {
				// 生成并上报日计划
				executeMgr.reportDayExecute(time);
				executeMgr.reportExcel(time);
				executeMgr.newReportExcel(time);
				mapFlag = "day";
			}
			// if (type == 4) {
			// 上报目录
			// executeBO.reportDir("");
			// executeBO.newReportDir("");
			// mapFlag = "day";
			//
			// }
			// if (type == 5) {
			// 改文件的日期
			// executeBO.specialRename(time, "temp");
			// mapFlag = "day";
			// }

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return mapping.findForward(mapFlag);

	}		
}
