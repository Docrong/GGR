package com.boco.eoms.commons.interfaceMonitoring.webapp.action;

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
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfigurationGroup;
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

public final class InterfaceMonitoringAction extends BaseAction {
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
	public ActionForward newInterfaceMonitoring(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"InterfaceMonitoringList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");

		InterfaceMonitoringForm interfaceMonitoringForm = (InterfaceMonitoringForm) form;
		interfaceMonitoringForm.setCallDirection("Horizontal");
		Map mapHorizontal = interfaceMonitoringMgr.getMonitoringLogFind(
				pageIndex, pageSize, interfaceMonitoringForm);
		List horizontalList = (List) mapHorizontal.get("result");
		request.setAttribute("InterfaceMonitoringList", horizontalList);
		interfaceMonitoringForm.setCallDirection("Longitudinal");
		Map mapLongitudinal = interfaceMonitoringMgr.getMonitoringLogFind(
				pageIndex, pageSize, interfaceMonitoringForm);
		List LongitudinalList = (List) mapLongitudinal.get("result");
		request.setAttribute("LongitudinalList", LongitudinalList);
		return mapping.findForward("success");
	}

	public ActionForward longitudinalMonitoring(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"InterfaceMonitoringList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");

		InterfaceMonitoringForm interfaceMonitoringForm = (InterfaceMonitoringForm) form;
		interfaceMonitoringForm.setCallDirection("Longitudinal");
		if("".equals(interfaceMonitoringForm.getStartTime())||interfaceMonitoringForm.getStartTime()==null){
			interfaceMonitoringForm.setStartTime(this.getLocalString("yyyy-MM")+"-01 00:00:00");
		}
		if("".equals(interfaceMonitoringForm.getEndTime())||interfaceMonitoringForm.getEndTime()==null){
			interfaceMonitoringForm.setEndTime(this.getLocalString("yyyy-MM-dd")+" 23:59:59");
		}
		Map mapLongitudinal = interfaceMonitoringMgr.getMonitoringLogFind(
				pageIndex, pageSize, interfaceMonitoringForm);
		List longitudinalList = (List) mapLongitudinal.get("result");
		request.setAttribute("LongitudinalList", longitudinalList);
		InterfaceMonitoringForm longitudinalForm = new InterfaceMonitoringForm();
		longitudinalForm.setCallDirection("Horizontal");

		Map mapHorizontal = interfaceMonitoringMgr.getMonitoringLogFind(
				pageIndex, pageSize, longitudinalForm);
		List horizontalList = (List) mapHorizontal.get("result");
		request.setAttribute("InterfaceMonitoringList", horizontalList);
		return mapping.findForward("Longitudinalsuccess");
	}

	public ActionForward horizontalInterfaceMonitoring(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"InterfaceMonitoringList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");

		InterfaceMonitoringForm interfaceMonitoringForm = (InterfaceMonitoringForm) form;
		if("".equals(interfaceMonitoringForm.getStartTime())||interfaceMonitoringForm.getStartTime()==null){
			interfaceMonitoringForm.setStartTime(this.getLocalString("yyyy-MM")+"-01 00:00:00");
		}
		if("".equals(interfaceMonitoringForm.getEndTime())||interfaceMonitoringForm.getEndTime()==null){
			interfaceMonitoringForm.setEndTime(this.getLocalString("yyyy-MM-dd")+" 23:59:59");
		}
		interfaceMonitoringForm.setCallDirection("Horizontal");
		Map mapHorizontal = interfaceMonitoringMgr.getMonitoringLogFind(
				pageIndex, pageSize, interfaceMonitoringForm);
		List horizontalList = (List) mapHorizontal.get("result");
		request.setAttribute("InterfaceMonitoringList", horizontalList);
		InterfaceMonitoringForm longitudinalForm = new InterfaceMonitoringForm();
		longitudinalForm.setCallDirection("Longitudinal");

		Map mapLongitudinal = interfaceMonitoringMgr.getMonitoringLogFind(
				pageIndex, pageSize, longitudinalForm);
		List LongitudinalList = (List) mapLongitudinal.get("result");
		request.setAttribute("LongitudinalList", LongitudinalList);
		return mapping.findForward("success");
	}

	public ActionForward horizontalInterfaceConfiguration(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"InterfaceMonitoringList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");
		Map mapHorizontal = interfaceMonitoringMgr.getConfigurationList(
				pageIndex, pageSize, "Horizontal");
		List horizontalList = (List) mapHorizontal.get("result");
		request.setAttribute("InterfaceMonitoringList", horizontalList);
		Map mapLongitudinal = interfaceMonitoringMgr.getConfigurationList(
				pageIndex, pageSize, "Longitudinal");
		List LongitudinalList = (List) mapLongitudinal.get("result");
		request.setAttribute("LongitudinalList", LongitudinalList);
		return mapping.findForward("configurationsuccess");
	}

	// public ActionForward search(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// String pageIndexName = new org.displaytag.util.ParamEncoder(
	// "InterfaceMonitoringList")
	// .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
	// final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
	// .getPageSize();
	// final Integer pageIndex = new Integer(GenericValidator
	// .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
	// : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
	// IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
	// Map map = (Map) mgr.getForumss(pageIndex, pageSize);
	// List list = (List) map.get("result");
	// request.setAttribute(Constants.FORUMS_LIST, list);
	// request.setAttribute("resultSize", map.get("total"));
	// request.setAttribute("pageSize", pageSize);
	// return mapping.findForward("list");
	// }

	public ActionForward saveLongitudinalInterfaceConfiguration(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"InterfaceMonitoringList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");

		InterfaceMonitoringForm interfaceMonitoringForm = (InterfaceMonitoringForm) form;
		// interfaceMonitoringForm.setDirection("Horizontal");
		InterfaceConfiguration interfaceConfiguration = new InterfaceConfiguration();
		interfaceConfiguration.setInterfaceName(interfaceMonitoringForm
				.getInterfaceName());
		interfaceConfiguration.setInterfaceUrl(interfaceMonitoringForm
				.getInterfaceUrl());
		interfaceConfiguration.setProvider(interfaceMonitoringForm
				.getProvider());
		interfaceConfiguration.setDirection("Longitudinal");
		interfaceMonitoringMgr
				.saveInterfaceConfiguration(interfaceConfiguration);
		return mapping.findForward("saveLongitudinalsuccess");

	}

	public ActionForward saveHorizontalInterfaceConfiguration(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"InterfaceMonitoringList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");

		InterfaceMonitoringForm interfaceMonitoringForm = (InterfaceMonitoringForm) form;
		// interfaceMonitoringForm.setDirection("Horizontal");
		InterfaceConfiguration interfaceConfiguration = new InterfaceConfiguration();
		interfaceConfiguration.setInterfaceName(interfaceMonitoringForm
				.getInterfaceName());
		interfaceConfiguration.setInterfaceUrl(interfaceMonitoringForm
				.getInterfaceUrl());
		interfaceConfiguration.setProvider(interfaceMonitoringForm
				.getProvider());
		interfaceConfiguration.setDirection("Horizontal");
		interfaceConfiguration.setId(interfaceMonitoringForm.getId());
		interfaceConfiguration.setModuleid(interfaceMonitoringForm
				.getModuleid());
		interfaceMonitoringMgr
				.saveInterfaceConfiguration(interfaceConfiguration);
		return mapping.findForward("savesuccess");

	}

	public ActionForward longitudinalInterfaceConfiguration(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"InterfaceMonitoringList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");
		Map mapHorizontal = interfaceMonitoringMgr.getConfigurationList(
				pageIndex, pageSize, "Horizontal");
		List horizontalList = (List) mapHorizontal.get("result");
		request.setAttribute("InterfaceMonitoringList", horizontalList);
		Map mapLongitudinal = interfaceMonitoringMgr.getConfigurationList(
				pageIndex, pageSize, "Longitudinal");
		List LongitudinalList = (List) mapLongitudinal.get("result");
		request.setAttribute("LongitudinalList", LongitudinalList);
		return mapping.findForward("Longitudinalconfigurationsuccess");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		if (!"".equals(id) && id != null) {
			InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");
			InterfaceMonitoring interfaceMonitoring = new InterfaceMonitoring();
			interfaceMonitoring = interfaceMonitoringMgr
					.getInterfaceMonitoring(id);
			request.setAttribute("InterfaceMonitoring", interfaceMonitoring);
		}
		return mapping.findForward("detail");
	}

	public ActionForward monitoringService(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"InterfaceMonitoringList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");
		Map mapHorizontal = interfaceMonitoringMgr.getConfigurationList(
				pageIndex, pageSize, "Horizontal");
		List horizontalList = (List) mapHorizontal.get("result");
		request.setAttribute("InterfaceMonitoringList", horizontalList);
		Map mapLongitudinal = interfaceMonitoringMgr.getConfigurationList(
				pageIndex, pageSize, "Longitudinal");
		List LongitudinalList = (List) mapLongitudinal.get("result");
		request.setAttribute("LongitudinalList", LongitudinalList);

		return mapping.findForward("serviceList");
	}

	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String nodeId = request.getParameter("node");
		String usereId = sessionform.getUserid();
		JSONArray json = getUserDeptTree(nodeId, usereId, "");

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(json.toString());

		return null;
	}

	public JSONArray getUserDeptTree(String nodeId, String userId,
			String chkType) {

		JSONArray json = new JSONArray();
		try {
			InterfaceMonitoringMgr mgr = (InterfaceMonitoringMgr) ApplicationContextHolder
					.getInstance().getBean("InterfaceMonitoringMgr");
			if (nodeId.equals("-1")) {
				ArrayList list = (ArrayList) mgr.getModuleTree(nodeId);
				for (int i = 0; i < list.size(); i++) {
					InterfaceConfigurationGroup group = (InterfaceConfigurationGroup) list
							.get(i);
					String group_id = group.getId();
					String group_name = group.getModuleName();
					JSONObject jitem = new JSONObject();
					jitem.put("id", group_id);
					jitem.put("text", group_name);
					jitem.put(UIConstants.JSON_NODETYPE, "group");
					jitem.put("iconCls", "file");

					jitem.put("leaf", 0);

					jitem.put("allowChild", false);
					jitem.put("allowEdit", true);
					jitem.put("allowChilds", true);
					jitem.put("allowEdits", false);
					jitem.put("allowLists", false);
					jitem.put("allowImp", true);
					jitem.put("allowExp", true);
					jitem.put("allowDelete", true);
					if ("group".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					json.put(jitem);

				}
			}
			ArrayList contactlist = new ArrayList();
			contactlist = (ArrayList) mgr
					.getInterfaceConfigurationModuleTree(nodeId);

			if (contactlist.size() > 0) {
				for (int j = 0; j < contactlist.size(); j++) {
					InterfaceConfiguration interfaceConfiguration = new InterfaceConfiguration();
					interfaceConfiguration = (InterfaceConfiguration) contactlist
							.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", interfaceConfiguration.getId());
					jitem
							.put("text", interfaceConfiguration
									.getInterfaceName());
					jitem.put(UIConstants.JSON_NODETYPE, "contact");

					InterfaceMonitoringUtil interfaceMonitoringUtil = new InterfaceMonitoringUtil();
					String result = interfaceMonitoringUtil
							.testLink(interfaceConfiguration.getInterfaceUrl());
					if (result.equals("Success")) {
						jitem.put("iconCls", "success");
					} else {
						jitem.put("iconCls", "failure");
					}
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowEdit", false);
					jitem.put("allowChilds", false);
					jitem.put("allowEdits", true);
					jitem.put("allowLists", true);
					jitem.put("allowImp", true);
					jitem.put("allowExp", true);
					jitem.put("allowDelete", true);
					if ("contact".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					jitem.put("qtip", "接口提供方:"
							+ interfaceConfiguration.getProvider()
							+ "<br \\/>接口操作名:"
							+ interfaceConfiguration.getInterfaceName()
							+ "<br \\/>接口详细信息:"
							+ interfaceConfiguration.getInterfaceUrl());
					jitem
							.put("qtipTitle", interfaceConfiguration
									.getProvider());

					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}

	public ActionForward treeMain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("contactMain");
	}

	public ActionForward saveToPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("nodeid");
		InterfaceMonitoringForm interfaceMonitoringForm = (InterfaceMonitoringForm) form;
		interfaceMonitoringForm.setId(_strId);
		request
				.setAttribute("tawWorkbenchContactForm",
						interfaceMonitoringForm);
		return mapping.findForward("saveToPage");
	}

	/**
	 * ajax保存
	 */
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			{
		try{
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"InterfaceMonitoringList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");

		InterfaceMonitoringForm interfaceMonitoringForm = (InterfaceMonitoringForm) form;
		// interfaceMonitoringForm.setDirection("Horizontal");
		InterfaceConfigurationGroup interfaceConfigurationGroup = new InterfaceConfigurationGroup();
		interfaceConfigurationGroup.setModuleName(interfaceMonitoringForm
				.getModuleName());
		interfaceConfigurationGroup
				.setModuleDesignation(interfaceMonitoringForm
						.getModuleDesignation());
		interfaceConfigurationGroup.setId(interfaceMonitoringForm.getId());
		interfaceMonitoringMgr
				.saveInterfaceConfigurationGroup(interfaceConfigurationGroup);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return mapping.findForward("xsave");

	}

	/**
	 * 得到新增页面的请求
	 */
	public ActionForward saveToIntfacePage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String _strId = request.getParameter("nodeid");
		TawWorkbenchContactForm tawWorkbenchContactForm = (TawWorkbenchContactForm) form;
		tawWorkbenchContactForm.setGroupId(_strId);
		request
				.setAttribute("tawWorkbenchContactForm",
						tawWorkbenchContactForm);
		return mapping.findForward("saveToIntfacePage");
	}

	public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String nodetype = (String) request.getParameter("nodeid");
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");
		InterfaceConfigurationGroup interfaceConfigurationGroup = new InterfaceConfigurationGroup();
		interfaceConfigurationGroup = interfaceMonitoringMgr
				.getInterfaceConfigurationGroup(nodetype);
		if (interfaceConfigurationGroup != null) {
			interfaceMonitoringMgr
					.removeInterfaceConfigurationGroup(interfaceConfigurationGroup);
			List list = new ArrayList();
			list = interfaceMonitoringMgr
					.getInterfaceConfigurationList(nodetype);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					InterfaceConfiguration interfaceConfiguration = new InterfaceConfiguration();
					interfaceConfiguration = (InterfaceConfiguration) list
							.get(i);
					// if(interfaceConfiguration){
					//						
					// }
				}
			}
		} else {
			InterfaceConfiguration interfaceConfiguration = new InterfaceConfiguration();
			interfaceConfiguration = interfaceMonitoringMgr
					.getinterfaceConfiguration(nodetype);
			interfaceMonitoringMgr
					.removeInterfaceConfiguration(interfaceConfiguration);

		}
		// interfaceMonitoringMgr.

		return mapping.findForward("xsave");
	}

	public ActionForward savePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("nodeid");
		InterfaceMonitoringForm interfaceMonitoringForm = (InterfaceMonitoringForm) form;
		interfaceMonitoringForm.setModuleid(_strId);
		request
				.setAttribute("interfaceMonitoringForm",
						interfaceMonitoringForm);
		return mapping.findForward("savePage");
	}

	public ActionForward xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("nodeid");
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");
		InterfaceConfiguration interfaceConfiguration = new InterfaceConfiguration();
		interfaceConfiguration = interfaceMonitoringMgr
				.getinterfaceConfiguration(_strId);
		request.setAttribute("interfaceMonitoringForm", interfaceConfiguration);
		return mapping.findForward("edit");
	}

	public ActionForward xlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("nodeid");
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");
		InterfaceConfiguration interfaceConfiguration = new InterfaceConfiguration();
		interfaceConfiguration = interfaceMonitoringMgr
				.getinterfaceConfiguration(_strId);
		request.setAttribute("interfaceMonitoringForm", interfaceConfiguration);
		return mapping.findForward("list");
	}

	public ActionForward xgetInterfaceConfigurationGroup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String _strId = request.getParameter("nodeid");
		InterfaceMonitoringMgr interfaceMonitoringMgr = (InterfaceMonitoringMgr) getBean("InterfaceMonitoringMgr");
		InterfaceConfigurationGroup interfaceConfigurationGroup = new InterfaceConfigurationGroup();
		interfaceConfigurationGroup = interfaceMonitoringMgr
				.getInterfaceConfigurationGroup(_strId);
		request.setAttribute("interfaceMonitoringForm",
				interfaceConfigurationGroup);
		return mapping.findForward("interfaceConfigurationGroupedit");
	}
	public static String getLocalString(String format) {
		java.util.Date currentDate = new java.util.Date();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				format);
		//yyyy-MM
		String date = dateFormat.format(currentDate);

		return date;
	}
}
