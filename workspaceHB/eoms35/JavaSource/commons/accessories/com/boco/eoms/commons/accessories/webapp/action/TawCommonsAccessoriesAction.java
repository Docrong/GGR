package com.boco.eoms.commons.accessories.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;

/**
 * Action class to handle CRUD on a TawCommonsAccessories object
 * 
 * @struts.action name="tawCommonsAccessoriesForm"
 *                path="/tawCommonsAccessoriess" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawCommonsAccessoriesForm"
 *                path="/editTawCommonsAccessories" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonsAccessoriesForm"
 *                path="/saveTawCommonsAccessories" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonsAccessories/tawCommonsAccessoriesForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonsAccessories/tawCommonsAccessoriesList.jsp"
 * @struts.action-forward name="search" path="/tawCommonsAccessoriess.html"
 *                        redirect="true"
 */
public final class TawCommonsAccessoriesAction extends BaseAction {
	
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}
    /**
     * 文件查询，未使用此方法，预留
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
		
		ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) getBean("ItawCommonsAccessoriesManager");
		request.setAttribute(Constants.TAWCOMMONSACCESSORIES_LIST, mgr
				.getTawCommonsAccessoriess());
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
