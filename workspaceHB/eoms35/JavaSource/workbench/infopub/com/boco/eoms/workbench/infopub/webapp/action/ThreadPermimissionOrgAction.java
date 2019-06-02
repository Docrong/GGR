package com.boco.eoms.workbench.infopub.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadPermimissionOrgForm;

/**
 * 
 * <p>
 * Title:信息（贴子）记录组织结构权限
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:27:15 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 *
 */
public final class ThreadPermimissionOrgAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		ThreadPermimissionOrgForm threadPermimissionOrgForm = (ThreadPermimissionOrgForm) form;

		// Exceptions are caught by ActionExceptionHandler
		IThreadPermimissionOrgManager mgr = (IThreadPermimissionOrgManager) getBean("IthreadPermimissionOrgManager");
		mgr.removeThreadPermimissionOrg(threadPermimissionOrgForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"threadPermimissionOrg.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThreadPermimissionOrgForm threadPermimissionOrgForm = (ThreadPermimissionOrgForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (threadPermimissionOrgForm.getId() != null) {
			IThreadPermimissionOrgManager mgr = (IThreadPermimissionOrgManager) getBean("IthreadPermimissionOrgManager");
			ThreadPermimissionOrg threadPermimissionOrg = mgr
					.getThreadPermimissionOrg(threadPermimissionOrgForm.getId());
			threadPermimissionOrgForm = (ThreadPermimissionOrgForm) convert(threadPermimissionOrg);
			updateFormBean(mapping, request, threadPermimissionOrgForm);
		}

		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		ThreadPermimissionOrgForm threadPermimissionOrgForm = (ThreadPermimissionOrgForm) form;
		boolean isNew = ("".equals(threadPermimissionOrgForm.getId()) || threadPermimissionOrgForm
				.getId() == null);

		IThreadPermimissionOrgManager mgr = (IThreadPermimissionOrgManager) getBean("IthreadPermimissionOrgManager");
		ThreadPermimissionOrg threadPermimissionOrg = (ThreadPermimissionOrg) convert(threadPermimissionOrgForm);
		mgr.saveThreadPermimissionOrg(threadPermimissionOrg);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"threadPermimissionOrg.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"threadPermimissionOrg.updated"));
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
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		IThreadPermimissionOrgManager mgr = (IThreadPermimissionOrgManager) getBean("IthreadPermimissionOrgManager");
		Map map = (Map) mgr.getThreadPermimissionOrgs(pageIndex, pageSize);
		List list = (List) map.get("result");
		request.setAttribute(InfopubConstants.THREADPERMIMISSIONORG_LIST, list);
		request.setAttribute("resultSize", map.get("total"));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
