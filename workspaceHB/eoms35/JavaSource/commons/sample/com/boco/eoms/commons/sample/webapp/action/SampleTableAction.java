package com.boco.eoms.commons.sample.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.sample.model.SampleTable;
import com.boco.eoms.commons.sample.service.ISampleTableManager;
import com.boco.eoms.commons.sample.webapp.form.SampleTableForm;
import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a SampleTable object
 * 
 * @struts.action name="sampleTableForm" path="/sampleTables" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="sampleTableForm" path="/editSampleTable" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="sampleTableForm" path="/saveSampleTable" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/sampleTable/sampleTableForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/sampleTable/sampleTableList.jsp"
 * @struts.action-forward name="search" path="/sampleTables.html"
 *                        redirect="true"
 */
public final class SampleTableAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		SampleTableForm sampleTableForm = (SampleTableForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ISampleTableManager mgr = (ISampleTableManager) getBean("IsampleTableManager");
		mgr.removeSampleTable(sampleTableForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"sampleTable.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SampleTableForm sampleTableForm = (SampleTableForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (sampleTableForm.getId() != null) {
			ISampleTableManager mgr = (ISampleTableManager) getBean("IsampleTableManager");
			SampleTable sampleTable = mgr.getSampleTable(sampleTableForm
					.getId());
			sampleTableForm = (SampleTableForm) convert(sampleTable);
			updateFormBean(mapping, request, sampleTableForm);
		}

		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		SampleTableForm sampleTableForm = (SampleTableForm) form;
		boolean isNew = ("".equals(sampleTableForm.getId()) || sampleTableForm
				.getId() == null);

		ISampleTableManager mgr = (ISampleTableManager) getBean("IsampleTableManager");
		SampleTable sampleTable = (SampleTable) convert(sampleTableForm);
		mgr.saveSampleTable(sampleTable);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"sampleTable.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"sampleTable.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"tawDemoMytableList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final int pageSize = 25;
		final int pageIndex = GenericValidator.isBlankOrNull(request
				.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
				.getParameter(pageIndexName)) - 1); 

		ISampleTableManager mgr = (ISampleTableManager) getBean("IsampleTableManager");
		Map map = (Map) mgr.getSampleTables(new Integer(pageIndex),
				new Integer(pageSize));
		List list = (List) map.get("result");
		request.setAttribute(Constants.SAMPLETABLE_LIST, list);
		request.setAttribute("resultSize", map.get("total"));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
