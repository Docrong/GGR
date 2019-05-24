package com.boco.eoms.workplan.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.workplan.mgr.ITawwpLogShowMgr;
import com.boco.eoms.workplan.model.TawwpNewLog;


/**
 * @author Administrator
 * 
 */
public class TawwpLogShowAction extends BaseAction {

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

	    //session超时处理
	    try {
	      /*TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.
	          getSession().getAttribute(
	              "sessionform");
	      if (saveSessionBeanForm == null) {
	        return mapping.findForward("timeout");
	      }*/
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }

		// 根据用户请求页面请求，进行页面跳转
		if (isCancelled(request)) {
			return mapping.findForward("cancel"); // 无效的请求，转向错误页面
		} else if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");// 条件为空，转向空页
		} else if ("SHOWALL".equalsIgnoreCase(myaction)) {

			myforward = performLogShowAll(mapping, form, request, response); // 查询网元信息

		} else if ("SEARCH".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("success");
		} else if ("SEARCHDONE".equalsIgnoreCase(myaction)) {

			myforward = performLogShowSearch(mapping, form, request, response);

		} else if ("SHOWONE".equalsIgnoreCase(myaction)) {
			myforward = performLogShowOne(mapping, form, request, response);
		} else {
			myforward = mapping.findForward("failure"); // 无效的请求，转向错误页面
		}

		return myforward;
	}

	private ActionForward performLogShowAll(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		List list = null;
		ITawwpLogShowMgr tawwpLogShowMgr = (ITawwpLogShowMgr) getBean("tawwpLogShowMgr"); //初始化作业计划日志BO类
		list = tawwpLogShowMgr.listTable();
		request.setAttribute("listAll", list);
		return mapping.findForward("success");
	}

	private ActionForward performLogShowSearch(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		String timeStart = request.getParameter("startdate");

		String timeEnd = request.getParameter("enddate");

		String sheetId = request.getParameter("sheetId");

		List list = new ArrayList();

		ITawwpLogShowMgr tawwpLogShowMgr = (ITawwpLogShowMgr) getBean("tawwpLogShowMgr"); //初始化作业计划日志BO类

		list = tawwpLogShowMgr.listTableSearch(sheetId, timeStart, timeEnd);

		request.setAttribute("listAll", list);

		return mapping.findForward("success");
	}

	private ActionForward performLogShowOne(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		String id = request.getParameter("id");

		ITawwpLogShowMgr tawwpLogShowMgr = (ITawwpLogShowMgr) getBean("tawwpLogShowMgr"); //初始化作业计划日志BO类

		TawwpNewLog tawwpNewLog = new TawwpNewLog();

		tawwpNewLog = tawwpLogShowMgr.getOne(id);

		request.setAttribute("showOne", tawwpNewLog);

		return mapping.findForward("success");
	}

}
