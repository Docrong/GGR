package com.boco.eoms.commons.message.webapp.action;

import java.util.ArrayList;
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
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;
import com.boco.eoms.commons.message.service.TawCommonMessageAddServiceManager;
import com.boco.eoms.commons.message.service.TawCommonMessageOpertypeManager;
import com.boco.eoms.commons.message.util.TawCommonMessageDictUtil;
import com.boco.eoms.commons.message.webapp.form.TawCommonMessageAddServiceForm;

/**
 * Action class to handle CRUD on a TawCommonMessageAddService object
 * 
 * @struts.action name="tawCommonMessageAddServiceForm"
 *                path="/tawCommonMessageAddServices" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawCommonMessageAddServiceForm"
 *                path="/editTawCommonMessageAddService" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonMessageAddServiceForm"
 *                path="/saveTawCommonMessageAddService" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonMessageAddService/tawCommonMessageAddServiceForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonMessageAddService/tawCommonMessageAddServiceList.jsp"
 * @struts.action-forward name="search" path="/tawCommonMessageAddServices.html"
 *                        redirect="true"
 */
public final class TawCommonMessageAddServiceAction extends BaseAction {
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
		TawCommonMessageAddServiceForm tawCommonMessageAddServiceForm = (TawCommonMessageAddServiceForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawCommonMessageAddServiceManager mgr = (TawCommonMessageAddServiceManager) getBean("tawCommonMessageAddServiceManager");
		mgr.removeTawCommonMessageAddService(tawCommonMessageAddServiceForm
				.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonMessageAddService.deleted"));

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

		TawCommonMessageAddServiceForm addServiceForm = (TawCommonMessageAddServiceForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (addServiceForm.getId() != null) {
			TawCommonMessageAddServiceManager mgr = (TawCommonMessageAddServiceManager) getBean("tawCommonMessageAddServiceManager");
			TawCommonMessageAddService tawCommonMessageAddService = mgr
					.getTawCommonMessageAddService(addServiceForm
							.getId());
			TawCommonMessageDictUtil dict = new TawCommonMessageDictUtil();
			addServiceForm = (TawCommonMessageAddServiceForm) convert(tawCommonMessageAddService);
			
			updateFormBean(mapping, request, addServiceForm);
		}
		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();
		TawCommonMessageOpertypeManager mgr = (TawCommonMessageOpertypeManager) getBean("tawCommonMessageOpertypeManager");
		
		List list = new ArrayList();
		List operlist = null;
		list = mgr.getTawCommonMessageOpertypes(tawCommonMessageOpertype);
		if( list != null ){
			if( list.size() >0 ){
				operlist = new ArrayList();
				for( int i=0;i<list.size();i++){
				tawCommonMessageOpertype = (TawCommonMessageOpertype)list.get(i);
				tawCommonMessageOpertype.setOpername(tawCommonMessageOpertype.getModelname()+"_"+tawCommonMessageOpertype.getOpername());
			
				operlist.add(tawCommonMessageOpertype);
				}
			}
		}

		request.setAttribute("opertypelist", operlist);
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
		TawCommonMessageAddServiceForm tawCommonMessageAddServiceForm = (TawCommonMessageAddServiceForm) form;
		boolean isNew = ("".equals(tawCommonMessageAddServiceForm.getId()) || tawCommonMessageAddServiceForm
				.getId() == null);
		TawCommonMessageOpertype opertype = new TawCommonMessageOpertype();
		TawCommonMessageOpertypeManager opermgr = (TawCommonMessageOpertypeManager) getBean("tawCommonMessageOpertypeManager");
		opertype = opermgr.getTawCommonMessageOpertype(tawCommonMessageAddServiceForm.getModeloperid());
		
		
		TawCommonMessageAddServiceManager mgr = (TawCommonMessageAddServiceManager) getBean("tawCommonMessageAddServiceManager");
		TawCommonMessageAddService tawCommonMessageAddService = (TawCommonMessageAddService) convert(tawCommonMessageAddServiceForm);
		tawCommonMessageAddService.setModelid(opertype.getModelid());
		tawCommonMessageAddService.setModelname(opertype.getModelname());
		tawCommonMessageAddService.setOperid(opertype.getOperid());
		tawCommonMessageAddService.setOpername(opertype.getOpername());
		mgr.saveTawCommonMessageAddService(tawCommonMessageAddService);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageAddService.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageAddService.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		TawCommonMessageAddServiceForm tawCommonMessageAddServiceForm = (TawCommonMessageAddServiceForm) form;
		TawCommonMessageAddService tawCommonMessageAddService = (TawCommonMessageAddService) convert(tawCommonMessageAddServiceForm);
       
		List list = new ArrayList();
        List reaultlist = null;
        TawCommonMessageAddService addservice = null;
		TawCommonMessageAddServiceManager mgr = (TawCommonMessageAddServiceManager) getBean("tawCommonMessageAddServiceManager");
		 list = mgr.getTawCommonMessageAddServices(tawCommonMessageAddService);
		 if( list != null){
			 if( list.size() > 0 ){
				 addservice = new TawCommonMessageAddService();
				 reaultlist = new ArrayList();
				 TawCommonMessageDictUtil dict = new TawCommonMessageDictUtil();
				 for( int i=0;i< list.size();i++){
					 addservice = (TawCommonMessageAddService)list.get(i);
					 addservice.setIssendimediat(dict.getIssendimediatname(addservice.getIssendimediat()));
					 addservice.setIssendnight(dict.getIssendnightname(addservice.getIssendnight()));
					 addservice.setMessagetype(dict.getMessagetypename(addservice.getMessagetype()));
					 addservice.setUrgency(dict.getUrgencyname(addservice.getUrgency()));
					 reaultlist.add(addservice);
				 }
			 }
		 }
		
		request.setAttribute(Constants.TAWCOMMONMESSAGEADDSERVICE_LIST, reaultlist);
        
		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
