package com.boco.eoms.commons.failureRecord.webapp.action;

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
import com.boco.eoms.commons.failureRecord.mgr.FailureRecordMgr;
import com.boco.eoms.commons.failureRecord.model.FailureRecord;
import com.boco.eoms.commons.failureRecord.webapp.form.FailureRecordForm;
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

public final class FailureRecordAction extends BaseAction {
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
	public ActionForward newFailureRecord(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String usereId = sessionform.getUsername();
			String roomname=sessionform.getRoomname();
		String workSerial=sessionform.getWorkSerial();
		request.setAttribute("usereId", usereId);
		request.setAttribute("roomname", roomname);
		request.setAttribute("workSerial", workSerial);
		return mapping.findForward("success");
	}

	public ActionForward failureRecordList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"failureRecordList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		FailureRecordMgr filureRecordMgr = (FailureRecordMgr) getBean("failureRecordMgr");
		FailureRecordForm failureRecordForm = (FailureRecordForm) form;
		// interfaceMonitoringForm.setCallDirection("Longitudinal");
		Map filureRecord = filureRecordMgr.getMonitoringLogFind(pageIndex,
				pageSize, failureRecordForm);
		List filureRecordList = (List) filureRecord.get("result");
		request.setAttribute("FailureRecordList", filureRecordList);
		return mapping.findForward("FailureRecordList");
	}

	public ActionForward saveFailureRecord(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FailureRecordMgr filureRecordMgr = (FailureRecordMgr) getBean("failureRecordMgr");
		FailureRecordForm failureRecordForm = (FailureRecordForm) form;
		FailureRecord failureRecord = new FailureRecord();
		// failureRecord.setFailureAddress(failureRecordForm.getFailureAddress());
		// failureRecord.setFailureDescription(failureRecordForm
		// .getFailureDescription());
		// failureRecord.setFailureFounder("admin");
		// failureRecord.setFailureProfessional(failureRecordForm
		// .getFailureProfessional());
		// failureRecord.setFailureRecordTime("");
		// failureRecord.setFailureResults("");
		// failureRecord.setFailureTime(failureRecordForm.getFailureTime());
		failureRecord = (FailureRecord) convert(failureRecordForm);
		filureRecordMgr.save(failureRecord);
		failureRecordForm.reset(mapping, request);
		return mapping.findForward("failureRecordsuccess");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		if (!"".equals(id) && id != null) {
			FailureRecordMgr filureRecordMgr = (FailureRecordMgr) getBean("failureRecordMgr");
			FailureRecord failureRecord = new FailureRecord();
			failureRecord = filureRecordMgr.getFailureRecord(id);
			request.setAttribute("FailureRecord", failureRecord);
		}
		return mapping.findForward("detail");
	}
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		if (!"".equals(id) && id != null) {
			FailureRecordMgr filureRecordMgr = (FailureRecordMgr) getBean("failureRecordMgr");
			FailureRecord failureRecord = new FailureRecord();
			failureRecord = filureRecordMgr.getFailureRecord(id);
			request.setAttribute("failureRecordForm", failureRecord);
		}
		return mapping.findForward("edit");
	}

	public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		if (!"".equals(id) && id != null) {
			FailureRecordMgr filureRecordMgr = (FailureRecordMgr) getBean("failureRecordMgr");
			FailureRecord failureRecord = new FailureRecord();
			failureRecord = filureRecordMgr.getFailureRecord(id);
			filureRecordMgr.removeFailureRecord(failureRecord);
		}
		// interfaceMonitoringMgr.

		return mapping.findForward("List");
	}
	public ActionForward failureRecordInterface(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (!"".equals(id) && id != null) {
			FailureRecordMgr filureRecordMgr = (FailureRecordMgr) getBean("failureRecordMgr");
			FailureRecord failureRecord = new FailureRecord();
			failureRecord = filureRecordMgr.getFailureRecord(id);
			request.setAttribute("FailureRecord", failureRecord);
		}
		return mapping.findForward("InterfaceSuccess");
	}
}
