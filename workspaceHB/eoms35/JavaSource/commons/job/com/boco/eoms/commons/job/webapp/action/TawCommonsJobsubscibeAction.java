package com.boco.eoms.commons.job.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.service.ITawCommonsJobsubscibeManager;
import com.boco.eoms.commons.job.webapp.form.TawCommonsJobsubscibeForm;
import com.boco.eoms.commons.job.util.JobStaticMethod;

/**
 * Action class to handle CRUD on a TawCommonsJobsubscibe object
 * 
 * @struts.action name="tawCommonsJobsubscibeForm"
 *                path="/tawCommonsJobsubscibes" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawCommonsJobsubscibeForm"
 *                path="/editTawCommonsJobsubscibe" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonsJobsubscibeForm"
 *                path="/saveTawCommonsJobsubscibe" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonsJobsubscibe/tawCommonsJobsubscibeForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonsJobsubscibe/tawCommonsJobsubscibeList.jsp"
 * @struts.action-forward name="search" path="/tawCommonsJobsubscibes.html"
 *                        redirect="true"
 */
public final class TawCommonsJobsubscibeAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	/**
	 * 任务订阅信息删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		ActionMessages messages = new ActionMessages();
		TawCommonsJobsubscibeForm tawCommonsJobsubscibeForm = (TawCommonsJobsubscibeForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ITawCommonsJobsubscibeManager mgr = (ITawCommonsJobsubscibeManager) getBean("ItawCommonsJobsubscibeManager");
		mgr.removeTawCommonsJobsubscibe(tawCommonsJobsubscibeForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonsJobsubscibe.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	/**
	 * 任务订阅信息的编辑
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		TawCommonsJobsubscibeForm tawCommonsJobsubscibeForm = (TawCommonsJobsubscibeForm) form;
		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonsJobsubscibeForm.getId() != null) {
			ITawCommonsJobsubscibeManager mgr = (ITawCommonsJobsubscibeManager) getBean("ItawCommonsJobsubscibeManager");
			TawCommonsJobsubscibe tawCommonsJobsubscibe = mgr
					.getTawCommonsJobsubscibe(tawCommonsJobsubscibeForm.getId());
			tawCommonsJobsubscibeForm = (TawCommonsJobsubscibeForm) convert(tawCommonsJobsubscibe);
			updateFormBean(mapping, request, tawCommonsJobsubscibeForm);
		}
		//获取任务类型信息
		List jobSortList = JobStaticMethod.getJobSort();
		request.setAttribute("jobSortList", jobSortList);
		return mapping.findForward("edit");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		TawCommonsJobsubscibeForm tawCommonsJobsubscibeForm = (TawCommonsJobsubscibeForm) form;
		boolean isNew = ("".equals(tawCommonsJobsubscibeForm.getId()) || tawCommonsJobsubscibeForm
				.getId() == null);

		ITawCommonsJobsubscibeManager mgr = (ITawCommonsJobsubscibeManager) getBean("ItawCommonsJobsubscibeManager");
		TawCommonsJobsubscibe tawCommonsJobsubscibe = (TawCommonsJobsubscibe) convert(tawCommonsJobsubscibeForm);
		// 先写死，等用户管理模块完成后再修改。 add by qinmin
		tawCommonsJobsubscibe.setSubscriberId("tomcat");
		tawCommonsJobsubscibe.setSubscriberDeptId(new Integer(1));
		try {
			mgr.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe, isNew);
		}catch (Exception e){
			e.printStackTrace(); 
			return mapping.findForward("false");
			
		}
		

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonsJobsubscibe.added"));
			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonsJobsubscibe.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}

	/**
	 * 查询任务订阅信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		ITawCommonsJobsubscibeManager mgr = (ITawCommonsJobsubscibeManager) getBean("ItawCommonsJobsubscibeManager");
		request.setAttribute(Constants.TAWCOMMONSJOBSUBSCIBE_LIST, mgr
				.getTawCommonsJobsubscibes());

		return mapping.findForward("list");
	}

	/**
	 * 默认执行方法
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
