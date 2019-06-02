package com.boco.eoms.sheet.proxy.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.proxy.model.Proxy;
import com.boco.eoms.sheet.proxy.service.IProxyManager;
import com.boco.eoms.sheet.proxy.webapp.form.ProxyForm;

public class ProxyAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	/**
	 * 删除代理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangying
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		ActionMessages messages = new ActionMessages();
		ProxyForm proxyForm = (ProxyForm) form;

		// Exceptions are caught by ActionExceptionHandler
		IProxyManager mgr = (IProxyManager) getBean("IproxyManager");
		mgr.removeProxy(proxyForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"IproxyManager.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	/**
	 * 编辑
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangying
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}

		
		ProxyForm proxyForm = (ProxyForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (proxyForm.getId() != null) {
			IProxyManager mgr = (IProxyManager) getBean("IproxyManager");
			Proxy proxy = mgr.getProxy(proxyForm.getId());
			proxyForm = (ProxyForm)convert(proxy);
		   	updateFormBean(mapping, request, proxyForm);
		}

		return mapping.findForward("edit");
	}

	/**
	 * 保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangying
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		ProxyForm proxyForm = (ProxyForm) form;
		boolean isNew = ("".equals(proxyForm.getId()) || proxyForm.getId() == null);

		IProxyManager mgr = (IProxyManager) getBean("IproxyManager");
		Proxy proxy = (Proxy) convert(proxyForm);
		mgr.saveProxy(proxy);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"Proxy.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"Proxy.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}
	 /**
     * 查询
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangying
     */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		ProxyForm proxyForm = (ProxyForm) form;
		Proxy proxy = (Proxy) convert(proxyForm);
		IProxyManager mgr = (IProxyManager) getBean("IproxyManager");
		request.setAttribute("proxyList", mgr.getProxyList(proxy));

		return mapping.findForward("list");
	}
	/**
     * 默认执行方法
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangying
     */
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
