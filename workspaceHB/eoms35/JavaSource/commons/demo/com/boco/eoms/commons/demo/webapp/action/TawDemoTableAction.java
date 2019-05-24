package com.boco.eoms.commons.demo.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.demo.model.TawDemoTable;
import com.boco.eoms.commons.demo.service.TawDemoTableManager;
import com.boco.eoms.commons.demo.webapp.form.TawDemoTableForm;

/**
 * Action class to handle CRUD on a TawDemoTable object
 * 
 * @struts.action name="tawDemoTableForm" path="/tawDemoTables" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawDemoTableForm" path="/editTawDemoTable"
 *                scope="request" validate="false" parameter="method"
 *                input="list"
 * @struts.action name="tawDemoTableForm" path="/saveTawDemoTable"
 *                scope="request" validate="true" parameter="method"
 *                input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawDemoTable/tawDemoTableForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawDemoTable/tawDemoTableList.jsp"
 * @struts.action-forward name="search" path="/tawDemoTables.html"
 *                        redirect="true"
 */
public final class TawDemoTableAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		ActionMessages messages = new ActionMessages();
		TawDemoTableForm tawDemoTableForm = (TawDemoTableForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawDemoTableManager mgr = (TawDemoTableManager) getBean("tawDemoTableManager");
		mgr.removeTawDemoTable(tawDemoTableForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawDemoTable.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}

		TawDemoTableForm tawDemoTableForm = (TawDemoTableForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawDemoTableForm.getId() != null) {
			TawDemoTableManager mgr = (TawDemoTableManager) getBean("tawDemoTableManager");
			TawDemoTable tawDemoTable = mgr.getTawDemoTable(tawDemoTableForm
					.getId());
			tawDemoTableForm = (TawDemoTableForm) convert(tawDemoTable);
			updateFormBean(mapping, request, tawDemoTableForm);
		}

		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		TawDemoTableForm tawDemoTableForm = (TawDemoTableForm) form;
		boolean isNew = ("".equals(tawDemoTableForm.getId()) || tawDemoTableForm
				.getId() == null);

		TawDemoTableManager mgr = (TawDemoTableManager) getBean("tawDemoTableManager");
		TawDemoTable tawDemoTable = (TawDemoTable) convert(tawDemoTableForm);
		mgr.saveTawDemoTable(tawDemoTable);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawDemoTable.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawDemoTable.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		TawDemoTableForm tawDemoTableForm = (TawDemoTableForm) form;
		TawDemoTable tawDemoTable = (TawDemoTable) convert(tawDemoTableForm);

		TawDemoTableManager mgr = (TawDemoTableManager) getBean("tawDemoTableManager");
//		request.setAttribute(Constants.TAWDEMOTABLE_LIST, mgr
//				.getTawDemoTables(tawDemoTable));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
