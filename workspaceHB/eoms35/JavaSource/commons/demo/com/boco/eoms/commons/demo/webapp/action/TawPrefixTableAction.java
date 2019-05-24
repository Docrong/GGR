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
import com.boco.eoms.commons.demo.model.TawPrefixTable;
import com.boco.eoms.commons.demo.service.TawPrefixTableManager;
import com.boco.eoms.commons.demo.webapp.form.TawPrefixTableForm;

/**
 * Action class to handle CRUD on a TawPrefixTable object
 * 
 * @struts.action name="tawPrefixTableForm" path="/tawPrefixTables"
 *                scope="request" validate="false" parameter="method"
 *                input="mainMenu"
 * @struts.action name="tawPrefixTableForm" path="/editTawPrefixTable"
 *                scope="request" validate="false" parameter="method"
 *                input="list"
 * @struts.action name="tawPrefixTableForm" path="/saveTawPrefixTable"
 *                scope="request" validate="true" parameter="method"
 *                input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawPrefixTable/tawPrefixTableForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawPrefixTable/tawPrefixTableList.jsp"
 * @struts.action-forward name="search" path="/tawPrefixTables.html"
 *                        redirect="true"
 */
public final class TawPrefixTableAction extends BaseAction {
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
		TawPrefixTableForm tawPrefixTableForm = (TawPrefixTableForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawPrefixTableManager mgr = (TawPrefixTableManager) getBean("tawPrefixTableManager");
		mgr.removeTawPrefixTable(tawPrefixTableForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawPrefixTable.deleted"));

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

		TawPrefixTableForm tawPrefixTableForm = (TawPrefixTableForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawPrefixTableForm.getId() != null) {
			TawPrefixTableManager mgr = (TawPrefixTableManager) getBean("tawPrefixTableManager");
			TawPrefixTable tawPrefixTable = mgr
					.getTawPrefixTable(tawPrefixTableForm.getId());
			tawPrefixTableForm = (TawPrefixTableForm) convert(tawPrefixTable);
			updateFormBean(mapping, request, tawPrefixTableForm);
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
		TawPrefixTableForm tawPrefixTableForm = (TawPrefixTableForm) form;
		boolean isNew = ("".equals(tawPrefixTableForm.getId()) || tawPrefixTableForm
				.getId() == null);

		TawPrefixTableManager mgr = (TawPrefixTableManager) getBean("tawPrefixTableManager");
		TawPrefixTable tawPrefixTable = (TawPrefixTable) convert(tawPrefixTableForm);
		mgr.saveTawPrefixTable(tawPrefixTable);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawPrefixTable.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawPrefixTable.updated"));
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

		TawPrefixTableForm tawPrefixTableForm = (TawPrefixTableForm) form;
		TawPrefixTable tawPrefixTable = (TawPrefixTable) convert(tawPrefixTableForm);

		TawPrefixTableManager mgr = (TawPrefixTableManager) getBean("tawPrefixTableManager");
		request.setAttribute(Constants.TAWPREFIXTABLE_LIST, mgr
				.getTawPrefixTables(tawPrefixTable));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
