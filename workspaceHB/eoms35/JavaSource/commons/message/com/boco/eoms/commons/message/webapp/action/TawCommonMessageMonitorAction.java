package com.boco.eoms.commons.message.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitor;
import com.boco.eoms.commons.message.service.TawCommonMessageMonitorManager;
import com.boco.eoms.commons.message.webapp.form.TawCommonMessageMonitorForm;

/**
 * Action class to handle CRUD on a TawCommonMessageMonitor object
 * 
 * @struts.action name="tawCommonMessageMonitorForm"
 *                path="/tawCommonMessageMonitors" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawCommonMessageMonitorForm"
 *                path="/editTawCommonMessageMonitor" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonMessageMonitorForm"
 *                path="/saveTawCommonMessageMonitor" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonMessageMonitor/tawCommonMessageMonitorForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonMessageMonitor/tawCommonMessageMonitorList.jsp"
 * @struts.action-forward name="search" path="/tawCommonMessageMonitors.html"
 *                        redirect="true"
 */
public final class TawCommonMessageMonitorAction extends BaseAction {
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
		TawCommonMessageMonitorForm tawCommonMessageMonitorForm = (TawCommonMessageMonitorForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawCommonMessageMonitorManager mgr = (TawCommonMessageMonitorManager) getBean("tawCommonMessageMonitorManager");
		mgr.removeTawCommonMessageMonitor(tawCommonMessageMonitorForm
				.getMonitorId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonMessageMonitor.deleted"));

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

		TawCommonMessageMonitorForm tawCommonMessageMonitorForm = (TawCommonMessageMonitorForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonMessageMonitorForm.getMonitorId() != null) {
			TawCommonMessageMonitorManager mgr = (TawCommonMessageMonitorManager) getBean("tawCommonMessageMonitorManager");
			TawCommonMessageMonitor tawCommonMessageMonitor = mgr
					.getTawCommonMessageMonitor(tawCommonMessageMonitorForm
							.getMonitorId());
			tawCommonMessageMonitorForm = (TawCommonMessageMonitorForm) convert(tawCommonMessageMonitor);
			updateFormBean(mapping, request, tawCommonMessageMonitorForm);
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
		TawCommonMessageMonitorForm tawCommonMessageMonitorForm = (TawCommonMessageMonitorForm) form;
		boolean isNew = ("".equals(tawCommonMessageMonitorForm.getMonitorId()) || tawCommonMessageMonitorForm
				.getMonitorId() == null);

		TawCommonMessageMonitorManager mgr = (TawCommonMessageMonitorManager) getBean("tawCommonMessageMonitorManager");
		TawCommonMessageMonitor tawCommonMessageMonitor = (TawCommonMessageMonitor) convert(tawCommonMessageMonitorForm);
		mgr.saveTawCommonMessageMonitor(tawCommonMessageMonitor);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageMonitor.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageMonitor.updated"));
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

		TawCommonMessageMonitorForm tawCommonMessageMonitorForm = (TawCommonMessageMonitorForm) form;
		TawCommonMessageMonitor tawCommonMessageMonitor = (TawCommonMessageMonitor) convert(tawCommonMessageMonitorForm);

		TawCommonMessageMonitorManager mgr = (TawCommonMessageMonitorManager) getBean("tawCommonMessageMonitorManager");
		request.setAttribute(Constants.TAWCOMMONMESSAGEMONITOR_LIST, mgr
				.getTawCommonMessageMonitors(tawCommonMessageMonitor));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
