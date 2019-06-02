/**
 * 
 */
package com.boco.eoms.sheet.sheetkpi.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.sheetkpi.service.ISheetKpiBaseManager;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.*;
import org.displaytag.util.ParamEncoder;

/**
 * @author Administrator
 * 
 */
public class SheetKpiBaseAction extends SheetAction {

	/**
	 * @param args
	 */
	public SheetKpiBaseAction() {
	}

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = StaticMethod.nullObject2String(request
				.getParameter("filename"), "");
		request.setAttribute("fileName", fileName);
		request.setAttribute("searchInit", "SearchInit");
		if (fileName == "" || fileName == null) {
			return mapping.findForward("init");
		} else {
			return mapping.findForward("groupInit");
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		java.util.Map actionMap = request.getParameterMap();
		String fileName = StaticMethod.nullObject2String(request
				.getParameter("filename"), "");
		String deptid = StaticMethod.nullObject2String(request
				.getParameter("deptid"), "");
		String sendTimeStartDate = StaticMethod.nullObject2String(request
				.getParameter("sendTimeStartDate"), "");
		String sendTimeEndDate = StaticMethod.nullObject2String(request
				.getParameter("sendTimeEndDate"), "");
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = (new ParamEncoder("taskList"))
				.encodeParameterName("p");
		Integer index = new Integer(GenericValidator.isBlankOrNull(request
				.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request
				.getParameter(pageIndexName)) - 1);
		Integer pageIndex = index;
		String aSql[] = { "" };
		int aTotal[] = new int[1];
		java.util.Map condition = new HashMap();
		ISheetKpiBaseManager mgr = (ISheetKpiBaseManager) getBean("iSheetKpiBaseManager");
		java.util.List colMap = mgr.getXMLList(fileName);
		java.util.List result = new ArrayList();
		if (deptid == "" || deptid == null) {
			result = mgr.getReportByDept(aSql, actionMap, fileName);
			Integer total = new Integer(result.size());
			request.setAttribute("total", total);
			request.setAttribute("pageSize", "20");
		} else {
			result = mgr.getQuerySheetByCondition(aSql, actionMap, condition,
					pageIndex, pageSize, aTotal, deptid, fileName);
			Integer total = new Integer(aTotal[0]);
			request.setAttribute("total", total);
			request.setAttribute("pageSize", pageSize);
		}
		request.setAttribute("filename", request.getParameter("filename"));
		request.setAttribute("taskList", result);
		request.setAttribute("sendTimeStartDate", request
				.getParameter("sendTimeStartDate"));
		request.setAttribute("sendTimeEndDate", request
				.getParameter("sendTimeEndDate"));

		request.setAttribute("colMap", colMap);
		if (deptid == "" || deptid == null) {
			return mapping.findForward("search");
		} else {
			return mapping.findForward("searchList");
		}
	}

	public ActionForward groupSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		java.util.Map actionMap = request.getParameterMap();
		String fileName = StaticMethod.nullObject2String(request
				.getParameter("filename"), "");
		String deptid = StaticMethod.nullObject2String(request
				.getParameter("deptid"), "");
		String sendTimeStartDate = StaticMethod.nullObject2String(request
				.getParameter("sendTimeStartDate"), "");
		String sendTimeEndDate = StaticMethod.nullObject2String(request
				.getParameter("sendTimeEndDate"), "");
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = (new ParamEncoder("taskList"))
				.encodeParameterName("p");
		Integer index = new Integer(GenericValidator.isBlankOrNull(request
				.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request
				.getParameter(pageIndexName)) - 1);
		Integer pageIndex = index;
		String aSql[] = { "" };
		int aTotal[] = new int[1];
		java.util.Map condition = new HashMap();
		ISheetKpiBaseManager mgr = (ISheetKpiBaseManager) getBean("iSheetKpiBaseManager");
		java.util.List colMap = mgr.getXMLList(fileName);
		java.util.List result = new ArrayList();
		if (deptid == "" || deptid == null) {
			result = mgr.getReportByDept(aSql, actionMap, fileName);
			Integer total = new Integer(result.size());
			request.setAttribute("total", total);
			request.setAttribute("pageSize", "20");
		} else {
			result = mgr.getQuerySheetByCondition(aSql, actionMap, condition,
					pageIndex, pageSize, aTotal, deptid, fileName);
			Integer total = new Integer(aTotal[0]);
			request.setAttribute("total", total);
			request.setAttribute("pageSize", pageSize);
		}
		request.setAttribute("filename", request.getParameter("filename"));
		request.setAttribute("taskList", result);
		request.setAttribute("sendTimeStartDate", request
				.getParameter("sendTimeStartDate"));
		request.setAttribute("sendTimeEndDate", request
				.getParameter("sendTimeEndDate"));

		request.setAttribute("colMap", colMap);
		if (deptid == "" || deptid == null) {
			return mapping.findForward("groupSearch");
		} else {
			return mapping.findForward("searchList");
		}
	}

	public ActionForward excelsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int total = StaticMethod.nullObject2int(request
				.getParameter("filename"));
		int page = total / 5000;
		for (int i = 0; i < page; i++)
			;
		return mapping.findForward("search");
	}

}
