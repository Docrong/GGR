package com.boco.eoms.commons.log.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.log.model.TawCommonLogOperator;
import com.boco.eoms.commons.log.service.TawCommonLogOperatorManager;
import com.boco.eoms.commons.log.service.logSave;
import com.boco.eoms.commons.log.webapp.form.TawCommonLogOperatorForm;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

/**
 * Action class to handle CRUD on a TawCommonLogOperator object
 * 
 * @struts.action name="tawCommonLogOperatorForm" path="/tawCommonLogOperators"
 *                scope="request" validate="false" parameter="method"
 *                input="mainMenu"
 * @struts.action name="tawCommonLogOperatorForm"
 *                path="/editTawCommonLogOperator" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonLogOperatorForm"
 *                path="/saveTawCommonLogOperator" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonLogOperator/tawCommonLogOperatorForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonLogOperator/tawCommonLogOperatorList.jsp"
 * @struts.action-forward name="search" path="/tawCommonLogOperators.html"
 *                        redirect="true"
 */
public final class TawCommonLogOperatorAction extends BaseAction {

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
		TawCommonLogOperatorForm tawCommonLogOperatorForm = (TawCommonLogOperatorForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawCommonLogOperatorManager mgr = (TawCommonLogOperatorManager) getBean("tawCommonLogOperatorManager");
		mgr.removeTawCommonLogOperator(tawCommonLogOperatorForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonLogOperator.deleted"));

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

		TawCommonLogOperatorForm tawCommonLogOperatorForm = (TawCommonLogOperatorForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonLogOperatorForm.getId() != null) {
			TawCommonLogOperatorManager mgr = (TawCommonLogOperatorManager) getBean("tawCommonLogOperatorManager");
			TawCommonLogOperator tawCommonLogOperator = mgr
					.getTawCommonLogOperator(tawCommonLogOperatorForm.getId());
			tawCommonLogOperatorForm = (TawCommonLogOperatorForm) convert(tawCommonLogOperator);
			updateFormBean(mapping, request, tawCommonLogOperatorForm);
		}

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
		TawCommonLogOperatorForm tawCommonLogOperatorForm = (TawCommonLogOperatorForm) form;
		boolean isNew = ("".equals(tawCommonLogOperatorForm.getId()) || tawCommonLogOperatorForm
				.getId() == null);

		TawCommonLogOperatorManager mgr = (TawCommonLogOperatorManager) getBean("tawCommonLogOperatorManager");
		TawCommonLogOperator tawCommonLogOperator = (TawCommonLogOperator) convert(tawCommonLogOperatorForm);
		mgr.saveTawCommonLogOperator(tawCommonLogOperator);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonLogOperator.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonLogOperator.updated"));
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
		
		TawCommonLogOperatorForm tawCommonLogOperatorForm = (TawCommonLogOperatorForm) form;
		TawCommonLogOperator tawCommonLogOperator = (TawCommonLogOperator) convert(tawCommonLogOperatorForm);

		TawCommonLogOperatorManager mgr = (TawCommonLogOperatorManager) getBean("tawCommonLogOperatorManager");
		request.setAttribute(Constants.TAWCOMMONLOGOPERATOR_LIST, mgr
				.getTawCommonLogOperators(tawCommonLogOperator));

		return mapping.findForward("list");
	}
	public void saveLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String sequenceOpen = StaticMethod
		.null2String(((EOMSAttributes) ApplicationContextHolder
				.getInstance().getBean("eomsAttributes"))
				.getSequenceOpen());
		
		String modelId = request.getParameter("id");
		//TawSystemPrivAssignOut privassimgr = TawSystemPrivAssignOut.getInstance();
		TawSystemPrivOperation tawSystemPrivOperation = null;
		ITawSystemPrivOperationManager operationmanager = (ITawSystemPrivOperationManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemPrivOperationManager");
		List list = operationmanager.getAllFatherModules(modelId);
		int length = list.size();
		
		tawSystemPrivOperation = (TawSystemPrivOperation)list.get(length-1);
		String operName = tawSystemPrivOperation.getName();
		
		//String operName = privassimgr.getNameBycode(modelId);
		
		String url = request.getParameter("href");
		String modname = request.getParameter("text");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		String operuserid = sessionform.getUserid(); 
		String currentTime = StaticMethod.getCurrentDateTime();
		TawSystemPrivAssignOut mgr = TawSystemPrivAssignOut.getInstance();
		String operRemark = mgr.getWholeNameByCode(request.getParameter("id"));
		operRemark = "用户："+operuserid+"于"+currentTime+"进行了"+operRemark;
		logSave log= logSave.getInstance(operName, operuserid, modelId, request.getRemoteAddr(), operRemark, modelId, modname, url, currentTime);				
		if ("true".equals(sequenceOpen)) {
//			 初始化队列
			ISequenceFacade sequenceFacade = SequenceLocator
					.getSequenceFacade();
			Sequence savelogSequence = null;
			try {
				savelogSequence = sequenceFacade.getSequence("savelog");
			} catch (SequenceNotFoundException e) {
				e.printStackTrace();
			}			
//			 把action撇队列里
			sequenceFacade.put(log, "info", 
					null,
					null, null,
					savelogSequence);
			savelogSequence.setChanged();
			sequenceFacade.doJob(savelogSequence);
		} else {
			log.info();
		}
	}
	public void saveLogBySequence(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sequenceOpen = StaticMethod
		.null2String(((EOMSAttributes) ApplicationContextHolder
				.getInstance().getBean("eomsAttributes"))
				.getSequenceOpen());
		if ("true".equals(sequenceOpen)) {
			// 初始化队列
			ISequenceFacade sequenceFacade = SequenceLocator
					.getSequenceFacade();
			Sequence savelogSequence = null;
			try {
				savelogSequence = sequenceFacade.getSequence("savelog");
			} catch (SequenceNotFoundException e) {
				e.printStackTrace();
			}			
//			 把action撇队列里
			sequenceFacade.put(this.getClass(), "saveLog", 
					new Class[] {ActionMapping.class,ActionForm.class,HttpServletRequest.class,HttpServletResponse.class},
					new Object[] {mapping, form, request, response}, null,
					savelogSequence);
			savelogSequence.setChanged();
			sequenceFacade.doJob(savelogSequence);
		} else {
			saveLog(mapping, form, request, response);
		}
		
	}
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
