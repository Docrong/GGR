package com.boco.eoms.commons.log.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.log.dao.TawCommonLogDeployDao;
import com.boco.eoms.commons.log.model.TawCommonLogDeploy;
import com.boco.eoms.commons.log.service.TawCommonLogDeployManager;
import com.boco.eoms.commons.log.webapp.form.TawCommonLogDeployForm;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

/**
 * Action class to handle CRUD on a TawCommonLogDeploy object
 * 
 * @struts.action name="tawCommonLogDeployForm" path="/tawCommonLogDeploys"
 *                scope="request" validate="false" parameter="method"
 *                input="mainMenu"
 * @struts.action name="tawCommonLogDeployForm" path="/editTawCommonLogDeploy"
 *                scope="request" validate="false" parameter="method"
 *                input="list"
 * @struts.action name="tawCommonLogDeployForm" path="/saveTawCommonLogDeploy"
 *                scope="request" validate="true" parameter="method"
 *                input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonLogDeploy/tawCommonLogDeployForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonLogDeploy/tawCommonLogDeployList.jsp"
 * @struts.action-forward name="search" path="/tawCommonLogDeploys.html"
 *                        redirect="true"
 */
public final class TawCommonLogDeployAction extends BaseAction {
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
		TawCommonLogDeployForm tawCommonLogDeployForm = (TawCommonLogDeployForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawCommonLogDeployManager mgr = (TawCommonLogDeployManager) getBean("tawCommonLogDeployManager");
		mgr.removeTawCommonLogDeploy(tawCommonLogDeployForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonLogDeploy.deleted"));

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

		TawCommonLogDeployForm tawCommonLogDeployForm = (TawCommonLogDeployForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonLogDeployForm.getId() != null) {
			TawCommonLogDeployManager mgr = (TawCommonLogDeployManager) getBean("tawCommonLogDeployManager");
			TawCommonLogDeploy tawCommonLogDeploy = mgr
					.getTawCommonLogDeploy(tawCommonLogDeployForm.getId());
			tawCommonLogDeployForm = (TawCommonLogDeployForm) convert(tawCommonLogDeploy);
			updateFormBean(mapping, request, tawCommonLogDeployForm);
		}
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String operuserid = sessionform.getUserid();
		tawCommonLogDeployForm.setUserid(operuserid);
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
		TawCommonLogDeployForm tawCommonLogDeployForm = (TawCommonLogDeployForm) form;
		boolean isNew = ("".equals(tawCommonLogDeployForm.getId()) || tawCommonLogDeployForm
				.getId() == null);

		TawCommonLogDeployManager mgr = (TawCommonLogDeployManager) getBean("tawCommonLogDeployManager");
		TawCommonLogDeploy tawCommonLogDeploy = (TawCommonLogDeploy) convert(tawCommonLogDeployForm);
		TawCommonLogDeployDao tawlogdeoploydao = (TawCommonLogDeployDao) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogDeployDao");
		TawCommonLogDeploy tawCommonLogDeploys = tawlogdeoploydao
				.getDeployByOperID(tawCommonLogDeployForm.getOperid());

		if (tawCommonLogDeploys != null) {
			return mapping.findForward("search");
		} else {
			mgr.saveTawCommonLogDeploy(tawCommonLogDeploy);
		}
		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonLogDeploy.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonLogDeploy.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}

	//AJAX方式进行搜索请求时的数据处理
	public ActionForward xsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'xsearch' method");
		}

		TawCommonLogDeployForm tawCommonLogDeployForm = (TawCommonLogDeployForm) form;
		String userid = tawCommonLogDeployForm.getUserid();
		String modelid = tawCommonLogDeployForm.getModelid();

		TawCommonLogDeployManager mgr = (TawCommonLogDeployManager) getBean("tawCommonLogDeployManager");

		List list = new ArrayList();
		list = mgr.getDeoloyByuseridormodelid(userid, modelid);

		//创建JSON对象
		JSONObject jsonRoot = new JSONObject();

		//将查询结果的行数放入JSON对象中
		jsonRoot.put("results", list.size());

		//将查询记录转换为JSON数组放入JSON对象中
		jsonRoot.put("rows", JSONArray.fromObject(list));

		response.setContentType("text/xml;charset=UTF-8");
		//下为JSON的标准MIME类型，但不利于调试
		//response.setContentType("application/json;charset=UTF-8");

		//返回JSON对象
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		TawCommonLogDeployForm logform = (TawCommonLogDeployForm) form;
		TawCommonLogDeploy tawCommonLogDeploy = (TawCommonLogDeploy) convert(logform);
		TawCommonLogDeployManager mgr = (TawCommonLogDeployManager) getBean("tawCommonLogDeployManager");
		request.setAttribute("tawCommonLogDeployList", mgr
				.getTawCommonLogDeploys(tawCommonLogDeploy));
		ITawSystemPrivOperationManager _objOptMgr = (ITawSystemPrivOperationManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemPrivOperationManager");
		List onemodellist = _objOptMgr.getModules("-1","0");
		request.setAttribute("onemodellist",onemodellist );
		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
