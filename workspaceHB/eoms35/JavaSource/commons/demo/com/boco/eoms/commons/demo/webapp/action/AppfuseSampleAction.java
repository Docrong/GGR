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
import com.boco.eoms.commons.demo.model.AppfuseSample;
import com.boco.eoms.commons.demo.service.AppfuseSampleManager;
import com.boco.eoms.commons.demo.webapp.form.AppfuseSampleForm;

/**
 * Action class to handle CRUD on a AppfuseSample object
 * 
 * @struts.action name="appfuseSampleForm" path="/appfuseSamples"
 *                scope="request" validate="false" parameter="method"
 *                input="mainMenu"
 * @struts.action name="appfuseSampleForm" path="/editAppfuseSample"
 *                scope="request" validate="false" parameter="method"
 *                input="list"
 * @struts.action name="appfuseSampleForm" path="/saveAppfuseSample"
 *                scope="request" validate="true" parameter="method"
 *                input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/appfuseSample/appfuseSampleForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/appfuseSample/appfuseSampleList.jsp"
 * @struts.action-forward name="search" path="/appfuseSamples.html"
 *                        redirect="true"
 */
public final class AppfuseSampleAction extends BaseAction {
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
		AppfuseSampleForm appfuseSampleForm = (AppfuseSampleForm) form;

		// Exceptions are caught by ActionExceptionHandler
		AppfuseSampleManager mgr = (AppfuseSampleManager) getBean("appfuseSampleManager");
		mgr.removeAppfuseSample(appfuseSampleForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"appfuseSample.deleted"));

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

		AppfuseSampleForm appfuseSampleForm = (AppfuseSampleForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (appfuseSampleForm.getId() != null) {
			AppfuseSampleManager mgr = (AppfuseSampleManager) getBean("appfuseSampleManager");
			AppfuseSample appfuseSample = mgr
					.getAppfuseSample(appfuseSampleForm.getId());
			appfuseSampleForm = (AppfuseSampleForm) convert(appfuseSample);
			updateFormBean(mapping, request, appfuseSampleForm);
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
		AppfuseSampleForm appfuseSampleForm = (AppfuseSampleForm) form;
		boolean isNew = ("".equals(appfuseSampleForm.getId()) || appfuseSampleForm
				.getId() == null);

		AppfuseSampleManager mgr = (AppfuseSampleManager) getBean("appfuseSampleManager");
		AppfuseSample appfuseSample = (AppfuseSample) convert(appfuseSampleForm);
		mgr.saveAppfuseSample(appfuseSample);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"appfuseSample.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"appfuseSample.updated"));
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

		AppfuseSampleForm appfuseSampleForm = (AppfuseSampleForm) form;
		AppfuseSample appfuseSample = (AppfuseSample) convert(appfuseSampleForm);

		AppfuseSampleManager mgr = (AppfuseSampleManager) getBean("appfuseSampleManager");
//		request.setAttribute(Constants.APPFUSESAMPLE_LIST, mgr
//				.getAppfuseSamples(appfuseSample));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
