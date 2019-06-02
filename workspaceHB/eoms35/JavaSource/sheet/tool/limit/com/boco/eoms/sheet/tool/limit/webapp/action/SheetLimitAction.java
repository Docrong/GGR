package com.boco.eoms.sheet.tool.limit.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;
import com.boco.eoms.sheet.tool.limit.webapp.form.SheetLimitForm;

public class SheetLimitAction extends BaseAction{
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}
	
	/**
	 * 删除时限
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
		SheetLimitForm sheetLimitForm = (SheetLimitForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		mgr.removeSheetLimit(sheetLimitForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"IproxyManager.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}
	
	/**
	 * 环节时限编辑
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
		
		SheetLimitForm sheetLimitForm = (SheetLimitForm) form;

		if (sheetLimitForm.getId() != null) {
			ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
			SheetLimit sheetLimit = mgr.getSheetLimit(sheetLimitForm.getId());
			sheetLimitForm = (SheetLimitForm)convert(sheetLimit);
		    updateFormBean(mapping, request, sheetLimitForm);
		}

		return mapping.findForward("edit");
	}
	
	/**
	 * 环节时限保存
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
		SheetLimitForm sheetLimitForm = (SheetLimitForm) form;
		boolean isNew = ("".equals(sheetLimitForm.getId()) || sheetLimitForm.getId() == null);
		ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		if (isNew) {
//			SheetLimit tempSheetLimit = mgr.getSheetLimitBySpecial(sheetLimitForm.getSpecialty1(),sheetLimitForm.getSpecialty2());
			SheetLimit tempSheetLimit =  mgr.getSheetLimitBySpecial(sheetLimitForm.getSpecialty1(), sheetLimitForm.getSpecialty2(), sheetLimitForm.getSpecialty3(), sheetLimitForm.getSpecialty4());
			if(tempSheetLimit.getId()!= null && tempSheetLimit.getSpecialty1()!= null){
				
				return mapping.findForward("saveFailure");
			}else{
				
				SheetLimit sheetLimit = (SheetLimit) convert(sheetLimitForm);
				sheetLimit.setMoudleId("stepLimit");
				mgr.saveSheetLimit(sheetLimit);
				return mapping.findForward("search");
			}
		}else{
			
			SheetLimit sheetLimit = (SheetLimit) convert(sheetLimitForm);
			sheetLimit.setMoudleId("stepLimit");
			mgr.saveSheetLimit(sheetLimit);
			return mapping.findForward("search");
		}
	}
	 /**
     * 工单处理时限查询
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangying
     */
	public ActionForward getSheetLimitList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		SheetLimitForm sheetLimitForm = (SheetLimitForm) form;
		SheetLimit sheetLimit = (SheetLimit) convert(sheetLimitForm);
		ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		request.setAttribute("sheetLimitList", mgr.getDealLimitList(sheetLimit));

		return mapping.findForward("dealLimitList");
	}
	
	 /**
     * 环节时限查询
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

		SheetLimitForm sheetLimitForm = (SheetLimitForm) form;
		SheetLimit sheetLimit = (SheetLimit) convert(sheetLimitForm);
		ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		request.setAttribute("sheetLimitList", mgr.getSheetLimitList(sheetLimit));
		System.out.println("mgr.getSheetLimitList(sheetLimit)==="+mgr.getSheetLimitList(sheetLimit).size());

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
	 /**
     * 查询专业
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangying
     */
	public ActionForward getSpecialty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
        String moudleId = StaticMethod.null2String(request.getParameter("moduleId"));
        List filters = RoleMapSchema.getInstance().getRoleMappingListById(moudleId);
       
        request.setAttribute("specialtyList", filters);
     	return mapping.findForward("edit");
	}
	/**
	 * 工单处理时限编辑
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangying
	 */
	public ActionForward editDealLimit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}

		SheetLimitForm sheetLimitForm = (SheetLimitForm) form;
		if (sheetLimitForm.getId() != null) {
			ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
			SheetLimit sheetLimit = mgr.getSheetLimit(sheetLimitForm.getId());
			sheetLimitForm = (SheetLimitForm)convert(sheetLimit);
		    updateFormBean(mapping, request, sheetLimitForm);
		}

		return mapping.findForward("editLimit");
	}
	/**
	 * 工单处理时限保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangying
	 */
	public ActionForward saveDealLimit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		SheetLimitForm sheetLimitForm = (SheetLimitForm) form;
		boolean isNew = ("".equals(sheetLimitForm.getId()) || sheetLimitForm.getId() == null);
		ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		
		if (isNew) {
			
			SheetLimit tempSheetLimit = mgr.getSheetLimitBySpecial(sheetLimitForm.getSpecialty2());
			if(tempSheetLimit.getId()!= null){
				return mapping.findForward("saveFailure");
			}else{
				
				SheetLimit sheetLimit = (SheetLimit) convert(sheetLimitForm);
				sheetLimit.setMoudleId("dealLimit");
				mgr.saveSheetLimit(sheetLimit);
				return mapping.findForward("searchDeaList");
			}
		}else{
			
			SheetLimit sheetLimit = (SheetLimit) convert(sheetLimitForm);
			sheetLimit.setMoudleId("dealLimit");
			mgr.saveSheetLimit(sheetLimit);
			return mapping.findForward("searchDeaList");
		}
	 }
	/**
	 * 删除时限
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangying
	 */
	public ActionForward deleteDealLimit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
      
		ActionMessages messages = new ActionMessages();
		SheetLimitForm sheetLimitForm = (SheetLimitForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		mgr.removeSheetLimit(sheetLimitForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"IproxyManager.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("searchDeaList");
	}
	/**
	 * 工单处理时限保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangying
	 */
	public void getDealLimitBySpecial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		 String specialty = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
		 ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		 SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty);
		 JSONObject jsonRoot = new JSONObject();
		 jsonRoot = JSONObject.fromObject(sheetLimit);
		 JSONUtil.print(response, jsonRoot.toString());
	
	 }
	 

}
