package com.boco.eoms.remind.webapp.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.remind.util.RemindUtil;

public final class RemindAction extends BaseAction {
	public ActionForward getRemindList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		List remindList = RemindUtil.getCurrentRemindList(userId);
		if (null == remindList || 0 >= remindList.size()) {
			return mapping.findForward("emptyList");
		} else {
			Iterator remindIt = remindList.iterator();
			request.setAttribute("remindIt", remindIt);
			return mapping.findForward("remindList");
		}
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getRemindList(mapping, form, request, response);
	}
}
