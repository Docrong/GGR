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
import com.boco.eoms.commons.message.model.TawCommonMessageModelType;
import com.boco.eoms.commons.message.service.TawCommonMessageModelTypeManager;
import com.boco.eoms.commons.message.webapp.form.TawCommonMessageModelTypeForm;

/**
 * Action class to handle CRUD on a TawCommonMessageModelType object
 * 
 * @struts.action name="tawCommonMessageModelTypeForm"
 *                path="/tawCommonMessageModelTypes" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawCommonMessageModelTypeForm"
 *                path="/editTawCommonMessageModelType" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonMessageModelTypeForm"
 *                path="/saveTawCommonMessageModelType" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/TawCommonMessageModelType/tawCommonMessageModelTypeForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/TawCommonMessageModelType/tawCommonMessageModelTypeList.jsp"
 * @struts.action-forward name="search" path="/tawCommonMessageModelTypes.html"
 *                        redirect="true"
 */
public final class TawCommonMessageModelTypeAction extends BaseAction {
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
		TawCommonMessageModelTypeForm tawCommonMessageModelTypeForm = (TawCommonMessageModelTypeForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawCommonMessageModelTypeManager mgr = (TawCommonMessageModelTypeManager) getBean("tawCommonMessageModelTypeManager");
		mgr.removeTawCommonMessageModelType(tawCommonMessageModelTypeForm
				.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonMessageModelType.deleted"));

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

		TawCommonMessageModelTypeForm tawCommonMessageModelTypeForm = (TawCommonMessageModelTypeForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonMessageModelTypeForm.getId() != null) {
			TawCommonMessageModelTypeManager mgr = (TawCommonMessageModelTypeManager) getBean("tawCommonMessageModelTypeManager");
			TawCommonMessageModelType tawCommonMessageModelType = mgr
					.getTawCommonMessageModelType(tawCommonMessageModelTypeForm
							.getId());
			tawCommonMessageModelTypeForm = (TawCommonMessageModelTypeForm) convert(tawCommonMessageModelType);
			updateFormBean(mapping, request, tawCommonMessageModelTypeForm);
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
		TawCommonMessageModelTypeForm tawCommonMessageModelTypeForm = (TawCommonMessageModelTypeForm) form;
		boolean isNew = ("".equals(tawCommonMessageModelTypeForm.getId()) || tawCommonMessageModelTypeForm
				.getId() == null);

		TawCommonMessageModelTypeManager mgr = (TawCommonMessageModelTypeManager) getBean("tawCommonMessageModelTypeManager");
		TawCommonMessageModelType tawCommonMessageModelType = (TawCommonMessageModelType) convert(tawCommonMessageModelTypeForm);
		mgr.saveTawCommonMessageModelType(tawCommonMessageModelType);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageModelType.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageModelType.updated"));
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

		TawCommonMessageModelTypeForm tawCommonMessageModelTypeForm = (TawCommonMessageModelTypeForm) form;
		TawCommonMessageModelType tawCommonMessageModelType = (TawCommonMessageModelType) convert(tawCommonMessageModelTypeForm);

		TawCommonMessageModelTypeManager mgr = (TawCommonMessageModelTypeManager) getBean("tawCommonMessageModelTypeManager");
		request.setAttribute(Constants.TAWCOMMONMESSAGEMODELTYPE_LIST, mgr
				.getTawCommonMessageModelTypes(tawCommonMessageModelType));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
