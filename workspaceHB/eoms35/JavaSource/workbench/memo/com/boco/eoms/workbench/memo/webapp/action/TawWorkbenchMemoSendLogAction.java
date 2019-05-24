package com.boco.eoms.workbench.memo.webapp.action;

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

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemoSendLog;
import com.boco.eoms.workbench.memo.service.ITawWorkbenchMemoManager;
import com.boco.eoms.workbench.memo.service.ITawWorkbenchMemoSendLogManager;
import com.boco.eoms.workbench.memo.util.MemoConstants;
import com.boco.eoms.workbench.memo.webapp.form.TawWorkbenchMemoForm;
import com.boco.eoms.workbench.memo.webapp.form.TawWorkbenchMemoSendLogForm;

public final class TawWorkbenchMemoSendLogAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		TawWorkbenchMemoSendLogForm tawWorkbenchMemoSendLogForm = (TawWorkbenchMemoSendLogForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ITawWorkbenchMemoSendLogManager mgr = (ITawWorkbenchMemoSendLogManager) getBean("ItawWorkbenchMemoSendLogManager");
		mgr.removeTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLogForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawWorkbenchMemoSendLog.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawWorkbenchMemoForm tawWorkbenchMemoSendLogForm = (TawWorkbenchMemoForm) form;
		String str = request.getParameter("folderPath");

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String workSerial = sessionform.getWorkSerial();
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		if ((id == null || id.trim().equals("")) && (str.length() < 2)) {
			if (sessionform == null) {
				return mapping.findForward("timeout");
			}
			// 判断是否值班
			if (workSerial.equals("0")) {
				return mapping.findForward("notonduty");
			}
		}
		if (tawWorkbenchMemoSendLogForm.getId() != null) {
			ITawWorkbenchMemoSendLogManager mgr = (ITawWorkbenchMemoSendLogManager) getBean("ItawWorkbenchMemoSendLogManager");
			TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = mgr
					.getTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLogForm
							.getId());
			tawWorkbenchMemoSendLogForm = (TawWorkbenchMemoForm) convert(tawWorkbenchMemoSendLog);
			updateFormBean(mapping, request, tawWorkbenchMemoSendLogForm);
		} else {
			String strSub = null;
			if (str.length() < 2) {
				strSub = str;
			} else {
				strSub = str.substring(1, 2);
			}
			tawWorkbenchMemoSendLogForm.setLevel(strSub);
		}

		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		TawWorkbenchMemoForm TawWorkbenchMemoForm = (TawWorkbenchMemoForm) form;
		boolean isNew = ("".equals(TawWorkbenchMemoForm.getId()) || TawWorkbenchMemoForm
				.getId() == null);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String str = request.getParameter("folderPath");
		request.setAttribute("folderPath", str);
		ITawWorkbenchMemoManager mgrr = (ITawWorkbenchMemoManager) getBean("ItawWorkbenchMemoManager");
		TawWorkbenchMemo tawWorkbenchMemo = new TawWorkbenchMemo();
		tawWorkbenchMemo.setTitle(TawWorkbenchMemoForm.getTitle());
		String creattime = StaticMethod.getLocalString(); // dangqianshijian
		tawWorkbenchMemo.setCreattime(creattime);
		tawWorkbenchMemo.setUserid(sessionform.getUserid());
		tawWorkbenchMemo.setContent(TawWorkbenchMemoForm.getContent());
		tawWorkbenchMemo.setLevel(TawWorkbenchMemoForm.getLevel());
		String recievers = tawWorkbenchMemo.getReciever();
		String sendManner = tawWorkbenchMemo.getSendmanner();
		try {
			mgrr.sendMemo(tawWorkbenchMemo, recievers, sendManner,sessionform.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("falseMessage", "便签发送错误");
			return mapping.findForward("false");
		}
		try {
			mgrr.saveTawWorkbenchMemo(tawWorkbenchMemo);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("falseMessage", "便签保存错误");
			return mapping.findForward("false");
		}

		/*
		 * // 保存到TawWorkbenchMemoSendLog表 TawWorkbenchMemoSendLog
		 * tawWorkbenchMemoSendLog = (TawWorkbenchMemoSendLog)
		 * convert(tawWorkbenchMemoSendLogForm);
		 * mgrr.saveTawWorkbenchMemoLog(tawWorkbenchMemoSendLog);
		 */

		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawWorkbenchMemo.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawWorkbenchMemo.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}

		// add success messages

	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"tawDemoMytableList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE); // ҳ��Ĳ�����,����"tawDemoMytableList"��ҳ����displayTag��id
		final Integer pageSize = new Integer(25);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1)); // ��ǰҳ��

		ITawWorkbenchMemoSendLogManager mgr = (ITawWorkbenchMemoSendLogManager) getBean("ItawWorkbenchMemoSendLogManager");
		Map map = (Map) mgr.getTawWorkbenchMemoSendLogs(pageIndex, pageSize); // map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
		List list = (List) map.get("result");
		request.setAttribute(MemoConstants.TAWWORKBENCHMEMOSENDLOG_LIST, list);
		request.setAttribute("resultSize", map.get("total"));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
