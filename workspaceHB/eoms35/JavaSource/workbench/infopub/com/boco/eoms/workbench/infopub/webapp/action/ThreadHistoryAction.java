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

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.util.BulletinMgrLocator;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.InterfaceUtilVariable;
import com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadHistoryForm;
import com.boco.eoms.common.util.StaticMethod;
import com.huawei.csp.si.service.BulletinLocator;
import com.huawei.csp.si.service.BulletinPortType;

/**
 * 
 * <p>
 * Title:信息查看历史信息
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 4:06:42 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public final class ThreadHistoryAction extends BaseAction {
	// TODO 未使用
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	// TODO 未使用
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		ThreadHistoryForm threadHistoryForm = (ThreadHistoryForm) form;

		// Exceptions are caught by ActionExceptionHandler
		IThreadHistoryManager mgr = (IThreadHistoryManager) getBean("IthreadHistoryManager");
		mgr.removeThreadHistory(threadHistoryForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"threadHistory.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	// TODO 未使用
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThreadHistoryForm threadHistoryForm = (ThreadHistoryForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (threadHistoryForm.getId() != null) {
			IThreadHistoryManager mgr = (IThreadHistoryManager) getBean("IthreadHistoryManager");
			ThreadHistory threadHistory = mgr
					.getThreadHistory(threadHistoryForm.getId());
			threadHistoryForm = (ThreadHistoryForm) convert(threadHistory);
			updateFormBean(mapping, request, threadHistoryForm);
		}

		return mapping.findForward("edit");
	}

	// TODO 未使用
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();

		ThreadHistoryForm threadHistoryForm = (ThreadHistoryForm) form;
		boolean isNew = ("".equals(threadHistoryForm.getId()) || threadHistoryForm
				.getId() == null);
		IThreadHistoryManager mgr = (IThreadHistoryManager) getBean("IthreadHistoryManager");
		ThreadHistory threadHistory = (ThreadHistory) convert(threadHistoryForm);
		IThreadManager msg = (IThreadManager) ApplicationContextHolder
		.getInstance().getBean("IthreadManager");
		Thread thread = new Thread();
		thread=msg.getThread(threadHistory.getThreadId());
		mgr.saveThreadHistory(threadHistory);
		try {
			BulletinLocator bing = new BulletinLocator();
			BulletinPortType service = bing.getBulletinHttpPort();
			InterfaceUtil interfaceUtil = new InterfaceUtil();
			service.confirmBulletin(new Integer(031), new Integer(999), thread.getSerialNo(),
					BulletinMgrLocator.getAttributes().getBulletinSerSupplier(),
					BulletinMgrLocator.getAttributes().getBulletinSerCaller(),
					StaticMethod.getLocalString(), "",
					InterfaceUtilVariable.BULLETION_CREATER_ID, "", "",
					"", "", "", interfaceUtil.getOpDetail(threadHistory));
		} catch (Exception e) {
			e.printStackTrace();

		}
		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"threadHistory.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"threadHistory.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}

	/**
	 * 查询详细历史信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 相关信息id
		String threadId = request.getParameter("threadId");
		String userId = request.getParameter("userId");
		String pageIndexName = "";
		pageIndexName = new org.displaytag.util.ParamEncoder(InfopubConstants.THREADHISTORY_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IThreadHistoryManager mgr = (IThreadHistoryManager) getBean("IthreadHistoryManager");
		Map map = (Map) mgr.getThreadHistorys(pageIndex, pageSize,
				" where threadId='" + threadId + "' and userId='" + userId + "'");
		List list = (List) map.get("result");
		request.setAttribute(InfopubConstants.THREADHISTORY_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		// 信息id写入页面，供显示信息名称
		request.setAttribute("threadId", threadId);
		return mapping.findForward("list");
	}

	// TODO 未使用
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
