package com.boco.eoms.taskplan.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.log.dao.TawCommonLogOperatorDao;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.voiceMessage.webapp.form.TawHieApplyForm;
import com.boco.eoms.taskplan.dao.TaskplanqueryDao;
import com.boco.eoms.taskplan.webapp.form.TaskplanQueryForm;

public class TaskplanQueryAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		if ("QUERY".equalsIgnoreCase(myaction)) {
			myforward = performQuery(mapping, actionForm, request, response);
		} else if ("QUERYDO".equalsIgnoreCase(myaction)) {
			myforward = performQueryDo(mapping, actionForm, request, response);
		}

		return myforward;

	}

	private ActionForward performQuery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {

		} catch (Exception e) {
			BocoLog.error(this, "打开页面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	private ActionForward performQueryDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		String project_name = null2String((String) request
				.getParameter("project_name"));
		String project_decompose = null2String((String) request
				.getParameter("project_decompose"));
		String deptid = null2String((String) request.getParameter("deptid"));
		String stakeholders = null2String((String) request
				.getParameter("stakeholders"));
		String fenye = null2String((String) request
				.getParameter("fenye"));

		try {

			String pageIndexName = new org.displaytag.util.ParamEncoder(
					"logList")
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
			// 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));
			TaskplanqueryDao dao = (TaskplanqueryDao) ApplicationContextHolder
					.getInstance().getBean("taskplanqueryDao");

			Map map = null;
			map = dao.getTaskplanbycon(pageIndex, pageSize, project_name,
					project_decompose, deptid, stakeholders,fenye);
			List list = (List) map.get("result");
			request.setAttribute("logList", list);
			request.setAttribute("resultSize", map.get("total"));
			System.out.println(map.get("total"));
			if(fenye.equals("0")){
				request.setAttribute("pageSize", String.valueOf(list.size()));
			}
			else{
			request.setAttribute("pageSize", pageSize);
			}

		} catch (Exception e) {
			BocoLog.error(this, "打开页面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	public static String null2String(String s) {
		return s == null ? "" : s;
	}
}
