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
import com.boco.eoms.commons.message.model.TawCommonMessageMonitorRef;
import com.boco.eoms.commons.message.service.TawCommonMessageMonitorRefManager;
import com.boco.eoms.commons.message.webapp.form.TawCommonMessageMonitorRefForm;

/**
 * Action class to handle CRUD on a TawCommonMessageMonitorRef object
 * 
 * @struts.action name="tawCommonMessageMonitorRefForm"
 *                path="/tawCommonMessageMonitorRefs" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawCommonMessageMonitorRefForm"
 *                path="/editTawCommonMessageMonitorRef" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonMessageMonitorRefForm"
 *                path="/saveTawCommonMessageMonitorRef" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonMessageMonitorRef/tawCommonMessageMonitorRefForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonMessageMonitorRef/tawCommonMessageMonitorRefList.jsp"
 * @struts.action-forward name="search" path="/tawCommonMessageMonitorRefs.html"
 *                        redirect="true"
 */
public final class TawCommonMessageMonitorRefAction extends BaseAction {
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
		TawCommonMessageMonitorRefForm tawCommonMessageMonitorRefForm = (TawCommonMessageMonitorRefForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawCommonMessageMonitorRefManager mgr = (TawCommonMessageMonitorRefManager) getBean("tawCommonMessageMonitorRefManager");
		mgr.removeTawCommonMessageMonitorRef(tawCommonMessageMonitorRefForm
				.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonMessageMonitorRef.deleted"));

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

		TawCommonMessageMonitorRefForm tawCommonMessageMonitorRefForm = (TawCommonMessageMonitorRefForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonMessageMonitorRefForm.getId() != null) {
			TawCommonMessageMonitorRefManager mgr = (TawCommonMessageMonitorRefManager) getBean("tawCommonMessageMonitorRefManager");
			TawCommonMessageMonitorRef tawCommonMessageMonitorRef = mgr
					.getTawCommonMessageMonitorRef(tawCommonMessageMonitorRefForm
							.getId());
			tawCommonMessageMonitorRefForm = (TawCommonMessageMonitorRefForm) convert(tawCommonMessageMonitorRef);
			updateFormBean(mapping, request, tawCommonMessageMonitorRefForm);
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
		TawCommonMessageMonitorRefForm tawCommonMessageMonitorRefForm = (TawCommonMessageMonitorRefForm) form;
		boolean isNew = ("".equals(tawCommonMessageMonitorRefForm.getId()) || tawCommonMessageMonitorRefForm
				.getId() == null);

		TawCommonMessageMonitorRefManager mgr = (TawCommonMessageMonitorRefManager) getBean("tawCommonMessageMonitorRefManager");
		TawCommonMessageMonitorRef tawCommonMessageMonitorRef = (TawCommonMessageMonitorRef) convert(tawCommonMessageMonitorRefForm);
		mgr.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRef);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageMonitorRef.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageMonitorRef.updated"));
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

		TawCommonMessageMonitorRefForm tawCommonMessageMonitorRefForm = (TawCommonMessageMonitorRefForm) form;
		TawCommonMessageMonitorRef tawCommonMessageMonitorRef = (TawCommonMessageMonitorRef) convert(tawCommonMessageMonitorRefForm);

		TawCommonMessageMonitorRefManager mgr = (TawCommonMessageMonitorRefManager) getBean("tawCommonMessageMonitorRefManager");
		request.setAttribute(Constants.TAWCOMMONMESSAGEMONITORREF_LIST, mgr
				.getTawCommonMessageMonitorRefs(tawCommonMessageMonitorRef));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
