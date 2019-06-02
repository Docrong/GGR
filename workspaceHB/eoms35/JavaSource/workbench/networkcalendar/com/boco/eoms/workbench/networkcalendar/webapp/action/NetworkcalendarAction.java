package com.boco.eoms.workbench.networkcalendar.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager;
import com.boco.eoms.workbench.contact.service.bo.TawWorkbenchContactBO;
import com.boco.eoms.workbench.contact.webapp.form.TawWorkbenchContactForm;
import com.boco.eoms.workbench.contact.webapp.form.TawWorkbenchContactGroupForm;
import com.boco.eoms.workbench.infopub.mgr.IForumsManager;
import com.boco.eoms.workbench.netdisk.fileupload.UploadProcessor;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;

import com.boco.eoms.commons.interfaceMonitoring.mgr.InterfaceMonitoringMgr;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfiguration;

import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceMonitoring;
import com.boco.eoms.commons.interfaceMonitoring.util.InterfaceMonitoringUtil;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceConfigurationForm;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.workbench.netdisk.mgr.ITawWorkbenchNetDiskMgr;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFile;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderShare;
import com.boco.eoms.workbench.netdisk.webapp.form.TawWorkbenchFolderForm;
import com.boco.eoms.workbench.netdisk.webapp.util.FileFilter;
import com.boco.eoms.workbench.netdisk.webapp.util.FilePathProcessor;
import com.boco.eoms.workbench.netdisk.webapp.util.FolderFilter;
import com.boco.eoms.workbench.netdisk.webapp.util.NetDiskAttriubuteLocator;
import com.boco.eoms.workbench.networkcalendar.mgr.NetworkcalendarMgr;
import com.boco.eoms.workbench.networkcalendar.model.Schedule;
import com.boco.eoms.workbench.networkcalendar.webapp.form.NetworkcalendarForm;
import com.boco.eoms.workbench.networkcalendar.webapp.util.networkcalendarMgrLocator;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;

public final class NetworkcalendarAction extends BaseAction {
	TawWorkbenchContactBO contactbo = TawWorkbenchContactBO.getInstance();

	/**
	 * 未指定方法
	 */
	// public ActionForward unspecified(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	//		
	// return newInterfaceMonitoring(mapping, form, request, response);
	// }
	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newNetworkcalendar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String date = getLocalString();
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"networkcalendarList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		NetworkcalendarMgr networkcalendarMgr = (NetworkcalendarMgr) getBean("NetworkcalendarMgr");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String nodeId = request.getParameter("node");
		String usereId = sessionform.getUserid();
		Map filureRecord = networkcalendarMgr.getNetworkcalendarList(pageIndex,
				pageSize, date, usereId);
		List networkcalendarList = (List) filureRecord.get("result");
		request.setAttribute("networkcalendarList", networkcalendarList);
		request.setAttribute("date", date);
		return mapping.findForward("success");
	}

	public ActionForward networkcalendarList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"networkcalendarList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		String date = request.getParameter("date");
		if ("".equals(date) || date == null) {
			HttpSession session = request.getSession();
			date = (String) session.getAttribute("tasKtime");

		}
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		NetworkcalendarMgr networkcalendarMgr = (NetworkcalendarMgr) getBean("NetworkcalendarMgr");
		// FailureRecordForm networkcalendarMgr = (FailureRecordForm) form;
		// interfaceMonitoringForm.setCallDirection("Longitudinal");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String nodeId = request.getParameter("node");
		String usereId = sessionform.getUserid();
		Map filureRecord = networkcalendarMgr.getNetworkcalendarList(pageIndex,
				pageSize, date, usereId);
		List networkcalendarList = (List) filureRecord.get("result");

		Map map = networkcalendarMgr.getNetworkcalendarHistoryList(pageIndex,
				pageSize, date, usereId);
		List historyList = (List) map.get("result");
		if (historyList.size() > 0) {
			for (int i = 0; i < historyList.size(); i++) {
				networkcalendarList.add(historyList.get(i));
			}
		}
		request.setAttribute("date", date);
		request.setAttribute("networkcalendarList", networkcalendarList);
		return mapping.findForward("networkcalendarList");
	}

	public ActionForward saveNetworkcalendar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NetworkcalendarMgr networkcalendarMgr = (NetworkcalendarMgr) getBean("NetworkcalendarMgr");
		NetworkcalendarForm networkcalendarForm = (NetworkcalendarForm) form;
		String taskhours = networkcalendarForm.getTaskhours();
		String taskremarks = networkcalendarForm.getTaskminutes();
		String tasKtime = networkcalendarForm.getTasktime();

		Schedule schedule = new Schedule();
		schedule.setTaskname(networkcalendarForm.getTaskname());
		schedule.setTaskremarks(networkcalendarForm.getTaskremarks());
		schedule.setId(networkcalendarForm.getId());
		String time = tasKtime + " " + taskhours + ":" + taskremarks;

		schedule.setTasktime(time);
		schedule.setTaskday(networkcalendarForm.getTasktime());
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String nodeId = request.getParameter("node");
		String usereId = sessionform.getUserid();
		HttpSession session = request.getSession();
		session.removeAttribute("tasKtime");
		session.setAttribute("tasKtime", tasKtime);
		schedule.setUserId(usereId);
		try {
			String id = networkcalendarMgr.save(schedule);
			String msg = "您在" + time + "有个名称为"
					+ networkcalendarForm.getTaskname() + "预约。请准时参加";
			MsgServiceImpl msgService = new MsgServiceImpl();
			time = tasKtime + " " + transformationTime(taskhours) + ":"
					+ taskremarks;
			String serverid = networkcalendarMgrLocator.getAttributes()
					.getServerId();
			msgService.sendMsg(serverid, msg, id, "1," + usereId, time);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return mapping.findForward("networkcalendarsuccess");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String date = (String) session.getAttribute("tasKtime");
		if (!"".equals(id) && id != null) {
			NetworkcalendarMgr networkcalendarMgr = (NetworkcalendarMgr) getBean("NetworkcalendarMgr");
			Schedule schedule = new Schedule();
			schedule = networkcalendarMgr.getSchedule(id);
			String time = schedule.getTasktime();
			if (date == null) {
				date = schedule.getTaskday();
			}
			String minutes = "00";
			String hours = "00";
			if (!"".equals(time) || time != null) {
				time = time.substring(11, time.length());
				
				if (time.indexOf(":") > 0) {
					String[] str = time.split(":");
					hours = str[0];
					minutes = str[1];
				}
			}

			request.setAttribute("minutes", minutes);
			request.setAttribute("hours", hours);
			request.setAttribute("date", date);
			request.setAttribute("networkcalendarForm", schedule);
		}
		return mapping.findForward("detail");
	}

	public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = (String) request.getParameter("id");
		NetworkcalendarMgr networkcalendarMgr = (NetworkcalendarMgr) getBean("NetworkcalendarMgr");
		networkcalendarMgr.removeSchedule(id);
		return mapping.findForward("xdelete");
	}

	public static String getLocalString() {
		java.util.Date currentDate = new java.util.Date();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		String date = dateFormat.format(currentDate);

		return date;
	}

	public static String transformationTime(String transformationTime) {
		String date = "";
		if (transformationTime.equals("00")) {
			date = String.valueOf(24 - 2);

		} else {
			int time = java.lang.Integer.parseInt(transformationTime);
			date = String.valueOf(time - 2);
		}

		return date;
	}
	
	public ActionForward showCalendar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		return mapping.findForward("showCalendar");
	}

}
