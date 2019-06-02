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
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
import com.boco.eoms.commons.job.service.ITawCommonsJobsortManager;
import com.boco.eoms.commons.job.webapp.form.TawCommonsJobsortForm;

/**
 * Action class to handle CRUD on a TawCommonsJobsort object
 * 
 * @struts.action name="tawCommonsJobsortForm" path="/tawCommonsJobsorts"
 *                scope="request" validate="false" parameter="method"
 *                input="mainMenu"
 * @struts.action name="tawCommonsJobsortForm" path="/editTawCommonsJobsort"
 *                scope="request" validate="false" parameter="method"
 *                input="list"
 * @struts.action name="tawCommonsJobsortForm" path="/saveTawCommonsJobsort"
 *                scope="request" validate="true" parameter="method"
 *                input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonsJobsort/tawCommonsJobsortForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonsJobsort/tawCommonsJobsortList.jsp"
 * @struts.action-forward name="search" path="/tawCommonsJobsorts.html"
 *                        redirect="true"
 */
public final class TawCommonsJobsortAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}
    /**
     * 删除任务类型
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
		TawCommonsJobsortForm tawCommonsJobsortForm = (TawCommonsJobsortForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ITawCommonsJobsortManager mgr = (ITawCommonsJobsortManager) getBean("ItawCommonsJobsortManager");
		mgr.removeTawCommonsJobsort(tawCommonsJobsortForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonsJobsort.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}
   /**
    * 任务类型编辑
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

		TawCommonsJobsortForm tawCommonsJobsortForm = (TawCommonsJobsortForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonsJobsortForm.getId() != null) {
			ITawCommonsJobsortManager mgr = (ITawCommonsJobsortManager) getBean("ItawCommonsJobsortManager");
			TawCommonsJobsort tawCommonsJobsort = mgr
					.getTawCommonsJobsort(tawCommonsJobsortForm.getId());
			tawCommonsJobsortForm = (TawCommonsJobsortForm) convert(tawCommonsJobsort);
			updateFormBean(mapping, request, tawCommonsJobsortForm);
		}

		return mapping.findForward("edit");
	}
    /**
     * 任务类型保存
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
		TawCommonsJobsortForm tawCommonsJobsortForm = (TawCommonsJobsortForm) form;
		boolean isNew = ("".equals(tawCommonsJobsortForm.getId()) || tawCommonsJobsortForm
				.getId() == null);

		ITawCommonsJobsortManager mgr = (ITawCommonsJobsortManager) getBean("ItawCommonsJobsortManager");
		TawCommonsJobsort tawCommonsJobsort = (TawCommonsJobsort) convert(tawCommonsJobsortForm);
		
		//----2009-5-21 任务类型名称唯一性验证
		if(isNew){
			String jobSortName = tawCommonsJobsortForm.getJobSortName();
			if(jobSortName!=null&&!jobSortName.equals("")){
				List list = mgr.getTawCommonsJobsortByJobSortName(jobSortName);
				if(list!=null&&list.size()>0){
					updateFormBean(mapping, request, tawCommonsJobsortForm);
					request.setAttribute("exist", "相同任务类型名称已经存在！");
					return mapping.findForward("edit");
				}
				else mgr.saveTawCommonsJobsort(tawCommonsJobsort);
			}
		}
		else {
			String jobSortName = tawCommonsJobsortForm.getJobSortName();
			TawCommonsJobsort jobsort = mgr.getTawCommonsJobsort(tawCommonsJobsortForm.getId());
			if(jobSortName!=null&&!jobSortName.equals("")){
				if(jobsort.getJobSortName()!=null&&jobsort.getJobSortName().equals(jobSortName)){
					mgr.saveTawCommonsJobsort(tawCommonsJobsort);
				}
				else{
					List list = mgr.getTawCommonsJobsortByJobSortName(jobSortName);
					if(list!=null&&list.size()>0){
						updateFormBean(mapping, request, tawCommonsJobsortForm);
						request.setAttribute("exist", "相同任务类型名称已经存在！");
						return mapping.findForward("edit");
					}
					else mgr.saveTawCommonsJobsort(tawCommonsJobsort);
				}
			}
			
		}
		
		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonsJobsort.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonsJobsort.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}
    /**
     * 任务类型查询
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

		TawCommonsJobsortForm tawCommonsJobsortForm = (TawCommonsJobsortForm) form;
		TawCommonsJobsort tawCommonsJobsort = (TawCommonsJobsort) convert(tawCommonsJobsortForm);

		ITawCommonsJobsortManager mgr = (ITawCommonsJobsortManager) getBean("ItawCommonsJobsortManager");
		request.setAttribute(Constants.TAWCOMMONSJOBSORT_LIST, mgr
				.getTawCommonsJobsorts(tawCommonsJobsort));

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
}
