package com.boco.eoms.commons.job.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.job.exception.ScheduleRunException;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager;
import com.boco.eoms.commons.job.webapp.form.TawCommonsJobmonitorForm;

/**
 * Action class to handle CRUD on a TawCommonsJobmonitor object
 * 
 * @struts.action name="tawCommonsJobmonitorForm" path="/tawCommonsJobmonitors"
 *                scope="request" validate="false" parameter="method"
 *                input="mainMenu"
 * @struts.action name="tawCommonsJobmonitorForm"
 *                path="/editTawCommonsJobmonitor" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonsJobmonitorForm"
 *                path="/saveTawCommonsJobmonitor" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonsJobmonitor/tawCommonsJobmonitorForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonsJobmonitor/tawCommonsJobmonitorList.jsp"
 * @struts.action-forward name="search" path="/tawCommonsJobmonitors.html"
 *                        redirect="true"
 */
public final class TawCommonsJobmonitorAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}
   /**
    * 任务执行情况的删除
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
        try{
		ActionMessages messages = new ActionMessages();
		TawCommonsJobmonitorForm tawCommonsJobmonitorForm = (TawCommonsJobmonitorForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ITawCommonsJobmonitorManager mgr = (ITawCommonsJobmonitorManager) getBean("ItawCommonsJobmonitorManager");
		mgr.removeTawCommonsJobmonitor(tawCommonsJobmonitorForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonsJobmonitor.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);
        }
        catch(Exception e){
          return mapping.findForward("failure");
        }
		return mapping.findForward("search");
	}
	   /**
	    * 任务执行情况的编辑
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

		TawCommonsJobmonitorForm tawCommonsJobmonitorForm = (TawCommonsJobmonitorForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonsJobmonitorForm.getId() != null) {
			ITawCommonsJobmonitorManager mgr = (ITawCommonsJobmonitorManager) getBean("ItawCommonsJobmonitorManager");
			TawCommonsJobmonitor tawCommonsJobmonitor = mgr
					.getTawCommonsJobmonitor(tawCommonsJobmonitorForm.getId());
			tawCommonsJobmonitorForm = (TawCommonsJobmonitorForm) convert(tawCommonsJobmonitor);
			updateFormBean(mapping, request, tawCommonsJobmonitorForm);
		}

		return mapping.findForward("edit");
	}
   /**
    * 保存任务执行信息
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
		TawCommonsJobmonitorForm tawCommonsJobmonitorForm = (TawCommonsJobmonitorForm) form;
		boolean isNew = ("".equals(tawCommonsJobmonitorForm.getId()) || tawCommonsJobmonitorForm
				.getId() == null);

		ITawCommonsJobmonitorManager mgr = (ITawCommonsJobmonitorManager) getBean("ItawCommonsJobmonitorManager");
		TawCommonsJobmonitor tawCommonsJobmonitor = (TawCommonsJobmonitor) convert(tawCommonsJobmonitorForm);
		mgr.saveTawCommonsJobmonitor(tawCommonsJobmonitor);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonsJobmonitor.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonsJobmonitor.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}
   /**
    * 查询任务执行信息
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

		TawCommonsJobmonitorForm tawCommonsJobmonitorForm = (TawCommonsJobmonitorForm) form;
		TawCommonsJobmonitor tawCommonsJobmonitor = (TawCommonsJobmonitor) convert(tawCommonsJobmonitorForm);

		ITawCommonsJobmonitorManager mgr = (ITawCommonsJobmonitorManager) getBean("ItawCommonsJobmonitorManager");
		request.setAttribute(Constants.TAWCOMMONSJOBMONITOR_LIST, mgr
				.getTawCommonsJobmonitors(tawCommonsJobmonitor));

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
	 * @author 秦敏
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
    /**
     * 任务调度器初始化并装载任务
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author 秦敏
     */
	public ActionForward scheduler(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ITawCommonsJobmonitorManager mgr = (ITawCommonsJobmonitorManager) getBean("ItawCommonsJobmonitorManager");
		try {
			// 任务调度器初始化			
			mgr.instance();
			// 系统启动时装载任务			
			mgr.run();			
		} catch (ScheduleRunException e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("list");
	}
}
