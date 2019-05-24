package com.boco.eoms.commons.system.priv.webapp.action;

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
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;
import com.boco.eoms.commons.system.priv.webapp.form.TawSystemPrivUserAssignForm;
import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a TawSystemPrivUserAssign object
 * 
 * @struts.action name="tawSystemPrivUserAssignForm"
 *                path="/tawSystemPrivUserAssigns" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSystemPrivUserAssignForm"
 *                path="/editTawSystemPrivUserAssign" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawSystemPrivUserAssignForm"
 *                path="/saveTawSystemPrivUserAssign" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawSystemPrivUserAssign/tawSystemPrivUserAssignForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawSystemPrivUserAssign/tawSystemPrivUserAssignList.jsp"
 * @struts.action-forward name="search" path="/tawSystemPrivUserAssigns.html"
 *                        redirect="true"
 */
public final class TawSystemPrivUserAssignAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		TawSystemPrivUserAssignForm tawSystemPrivUserAssignForm = (TawSystemPrivUserAssignForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ITawSystemPrivUserAssignManager mgr = (ITawSystemPrivUserAssignManager) getBean("ItawSystemPrivUserAssignManager");
		mgr.removeTawSystemPrivUserAssign(tawSystemPrivUserAssignForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawSystemPrivUserAssign.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemPrivUserAssignForm tawSystemPrivUserAssignForm = (TawSystemPrivUserAssignForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawSystemPrivUserAssignForm.getId() != null) {
			ITawSystemPrivUserAssignManager mgr = (ITawSystemPrivUserAssignManager) getBean("ItawSystemPrivUserAssignManager");
			TawSystemPrivUserAssign tawSystemPrivUserAssign = mgr
					.getTawSystemPrivUserAssign(tawSystemPrivUserAssignForm
							.getId());
			tawSystemPrivUserAssignForm = (TawSystemPrivUserAssignForm) convert(tawSystemPrivUserAssign);
			updateFormBean(mapping, request, tawSystemPrivUserAssignForm);
		}

		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		TawSystemPrivUserAssignForm tawSystemPrivUserAssignForm = (TawSystemPrivUserAssignForm) form;
		boolean isNew = ("".equals(tawSystemPrivUserAssignForm.getId()) || tawSystemPrivUserAssignForm
				.getId() == null);

		ITawSystemPrivUserAssignManager mgr = (ITawSystemPrivUserAssignManager) getBean("ItawSystemPrivUserAssignManager");
		TawSystemPrivUserAssign tawSystemPrivUserAssign = (TawSystemPrivUserAssign) convert(tawSystemPrivUserAssignForm);
		mgr.saveTawSystemPrivUserAssign(tawSystemPrivUserAssign);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawSystemPrivUserAssign.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawSystemPrivUserAssign.updated"));
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

		ITawSystemPrivUserAssignManager mgr = (ITawSystemPrivUserAssignManager) getBean("ItawSystemPrivUserAssignManager");
		Map map = (Map) mgr.getTawSystemPrivUserAssigns(new Integer(pageIndex),
				new Integer(pageSize));

		List list = (List) map.get("result");
		request.setAttribute(Constants.TAWSYSTEMPRIVUSERASSIGN_LIST, list);
		request.setAttribute("resultSize", map.get("total"));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
