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
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;
import com.boco.eoms.commons.message.service.TawCommonMessageModelTypeManager;
import com.boco.eoms.commons.message.service.TawCommonMessageOpertypeManager;
import com.boco.eoms.commons.message.webapp.form.TawCommonMessageOpertypeForm;

/**
 * Action class to handle CRUD on a TawCommonMessageOpertype object
 * 
 * @struts.action name="tawCommonMessageOpertypeForm"
 *                path="/tawCommonMessageOpertypes" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawCommonMessageOpertypeForm"
 *                path="/editTawCommonMessageOpertype" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonMessageOpertypeForm"
 *                path="/saveTawCommonMessageOpertype" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonMessageOpertype/tawCommonMessageOpertypeForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonMessageOpertype/tawCommonMessageOpertypeList.jsp"
 * @struts.action-forward name="search" path="/tawCommonMessageOpertypes.html"
 *                        redirect="true"
 */
public final class TawCommonMessageOpertypeAction extends BaseAction {
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
		TawCommonMessageOpertypeForm tawCommonMessageOpertypeForm = (TawCommonMessageOpertypeForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawCommonMessageOpertypeManager mgr = (TawCommonMessageOpertypeManager) getBean("tawCommonMessageOpertypeManager");
		mgr
				.removeTawCommonMessageOpertype(tawCommonMessageOpertypeForm
						.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonMessageOpertype.deleted"));

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

		TawCommonMessageOpertypeForm tawCommonMessageOpertypeForm = (TawCommonMessageOpertypeForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonMessageOpertypeForm.getId() != null) {
			TawCommonMessageOpertypeManager mgr = (TawCommonMessageOpertypeManager) getBean("tawCommonMessageOpertypeManager");
			TawCommonMessageOpertype tawCommonMessageOpertype = mgr
					.getTawCommonMessageOpertype(tawCommonMessageOpertypeForm
							.getId());
			tawCommonMessageOpertypeForm = (TawCommonMessageOpertypeForm) convert(tawCommonMessageOpertype);
			updateFormBean(mapping, request, tawCommonMessageOpertypeForm);
		}
		TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
		TawCommonMessageModelTypeManager mgr = (TawCommonMessageModelTypeManager) getBean("tawCommonMessageModelTypeManager");
		request.setAttribute("modellist", mgr
				.getTawCommonMessageModelTypes(tawCommonMessageModelType));
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
		TawCommonMessageOpertypeForm tawCommonMessageOpertypeForm = (TawCommonMessageOpertypeForm) form;
		boolean isNew = ("".equals(tawCommonMessageOpertypeForm.getId()) || tawCommonMessageOpertypeForm
				.getId() == null);

		TawCommonMessageOpertypeManager mgr = (TawCommonMessageOpertypeManager) getBean("tawCommonMessageOpertypeManager");
		TawCommonMessageOpertype tawCommonMessageOpertype = (TawCommonMessageOpertype) convert(tawCommonMessageOpertypeForm);
		TawCommonMessageModelTypeManager modelmgr = (TawCommonMessageModelTypeManager) getBean("tawCommonMessageModelTypeManager");
		tawCommonMessageOpertype.setModelname(modelmgr.getModeltype(tawCommonMessageOpertype.getModelid()).getModelname());
		mgr.saveTawCommonMessageOpertype(tawCommonMessageOpertype);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageOpertype.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageOpertype.updated"));
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

		TawCommonMessageOpertypeForm tawCommonMessageOpertypeForm = (TawCommonMessageOpertypeForm) form;
		TawCommonMessageOpertype tawCommonMessageOpertype = (TawCommonMessageOpertype) convert(tawCommonMessageOpertypeForm);

		TawCommonMessageOpertypeManager mgr = (TawCommonMessageOpertypeManager) getBean("tawCommonMessageOpertypeManager");
		request.setAttribute(Constants.TAWCOMMONMESSAGEOPERTYPE_LIST, mgr
				.getTawCommonMessageOpertypes(tawCommonMessageOpertype));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
