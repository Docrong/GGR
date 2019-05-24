package com.boco.eoms.sheet.nbproducts.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;
import com.boco.eoms.sheet.nbproducts.service.INBProductsManager;
import com.boco.eoms.sheet.nbproducts.webapp.form.NBProductsForm;

public final class NBProductsAction extends BaseAction {

	/**
	 * ajax保存数据
	 */
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		NBProductsForm nbproductsForm = (NBProductsForm) form;
		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
		NBProducts nbproducts = (NBProducts) convert(nbproductsForm);
		TawSystemSessionForm sessionForm = this.getUser(request);
		String curUserId = sessionForm.getUserid();
		SheetAttributes sheetAttributes = (SheetAttributes) getBean("SheetAttributes");
		String regionId = sheetAttributes.getRegionId();
		regionId = regionId + "-P-";
		String strYYMM = StaticMethod.getYYMMDD().substring(0, 4);
		regionId = regionId + strYYMM + "-";
		String maxcode = mgr.getMaxCode();
		regionId = regionId + maxcode;
		nbproducts.setCode(maxcode);
		String procode=StaticMethod.nullObject2String(nbproducts.getProcode());
		if(procode.equals(""))	nbproducts.setProcode(regionId);
		nbproducts.setRecorder(curUserId);
		nbproducts.setRecordTime(StaticMethod.getCurrentDateTime());
		nbproducts.setRecorderDept(sessionForm.getDeptname());
		nbproducts.setRecorderContact(sessionForm.getContactMobile());
		nbproducts.setDeleted(new Integer(0));
		mgr.saveNBProducts(nbproducts);
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		if(ifReference.equals(""))ifReference=StaticMethod.nullObject2String(request.getAttribute("ifReference"));
		request.setAttribute("ifReference", ifReference);
		if(nbproducts.getId()!=null){//save sucess
			return mapping.findForward("savesuccessjsp");
		}else{//save  failure  
			return mapping.findForward("savefailurejsp");
		}		
	}

	/**
	 * 根据模块或功能的编码，删除该对象
	 */
	public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		request.setAttribute("ifReference", ifReference);
		String id = StaticMethod.nullObject2String(request.getParameter("nbproductsid"));		
		mgr.removeNBProducts(id);
		if(id!=null){//delete sucess
			return mapping.findForward("deletesuccessjsp");
		}else{//delete  failure  
			return mapping.findForward("deletefailurejsp");
		}		
	}

	/**
	 * 根据模块或功能的编码，恢复该对象
	 */
	public ActionForward xrestore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		request.setAttribute("ifReference", ifReference);
		String id = StaticMethod.nullObject2String(request.getParameter("nbproductsid"));		
		mgr.restoreNBProducts(id);
		if(id!=null){//restore sucess
			return mapping.findForward("savesuccessjsp");
		}else{//restore failure  
			return mapping.findForward("savefailurejsp");
		}
		
	}
	
	/**
	 * 保存修改信息
	 */
	public ActionForward xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NBProductsForm nbproductsForm = (NBProductsForm) form;

		TawSystemSessionForm sessionForm = this.getUser(request);
		String curUserId = sessionForm.getUserid();
		String id = nbproductsForm.getId();
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		String type = StaticMethod.nullObject2String(request.getParameter("type"));
		request.setAttribute("ifReference", ifReference);
		if(type.equals("add")) {
			request.setAttribute("type", "add");
		}		
		if (id == null) {
			id = "01";
			return mapping.findForward("formjsp");
		} else if (id != null && !id.equals("")) {
			INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
			NBProducts nbproducts = (NBProducts) convert(nbproductsForm);
			mgr.saveNBProducts(nbproducts);
		}
		
		
		return mapping.findForward("formjsp");
	}

	/**
	 * 根据组合条件查询为删除的对象
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NBProductsForm nbproductsForm = (NBProductsForm) form;
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
		List nbproductsList = mgr.getNBProductss();
		if (!ifReference.equals("")) {
			
			request.setAttribute("ifReference", "true");
		}
		request.setAttribute("nbproductsList", nbproductsList);
		return mapping.findForward("listjsp");
	}
	/**
	 * 根据组合条件查询已删除的对象
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xqueryDeleted(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NBProductsForm nbproductsForm = (NBProductsForm) form;
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		request.setAttribute("ifReference", ifReference);
		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
		List nbproductsList = mgr.getNBProductssDeleted();
		request.setAttribute("nbproductsList", nbproductsList);
		return mapping.findForward("deletedlistjsp");
	}
	
/**
 * 与产品相关的工单列表信息
 * 
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
 	public ActionForward relationSheet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");

		String proCode = StaticMethod.nullObject2String(request
				.getParameter("procode"));

		List planList = mgr.getNBProductssByProCode(proCode,
				"com.boco.eoms.sheet.businessplan.model.BusinessPlanMain");
		List pilotList = mgr.getNBProductssByProCode(proCode,
				"com.boco.eoms.sheet.businesspilot.model.BusinessPilotMain");
		List operationList = mgr.getNBProductssByProCode(proCode,
	            "com.boco.eoms.sheet.businessoperation.model.BusinessOperationMain");

		if (planList != null && planList.size() > 0) {
			request.setAttribute("planList", planList);
		}
		if (pilotList != null && pilotList.size() > 0) {
			request.setAttribute("pilotList", pilotList);
		}
		 if(operationList!=null && operationList.size()>0){
		    request.setAttribute("operationList", operationList);
		 }
		return mapping.findForward("sheetList");
	}

	/**
	 * 
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NBProductsForm nbproductsForm = (NBProductsForm) form;
		String id = nbproductsForm.getId();
		if (id == null) {
			id = (String) request.getParameter("id");
		}
		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
		NBProducts nbproducts = (NBProducts) mgr.getNBProducts(id);
		request.setAttribute("nbproductsForm", nbproducts);
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        if (!ifReference.equals("")) {
			
			request.setAttribute("ifReference", "true");
		  }
		String flag = StaticMethod
				.null2String(request.getParameter("flag"), "");
		request.setAttribute("flag", flag);
		return mapping.findForward("formjsp");
	}

	/**
	 * 显示未删除产品详细信息
	 */
	public ActionForward showDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NBProductsForm nbproductsForm = (NBProductsForm) form;
		String id = nbproductsForm.getId();
		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
		NBProducts nbproducts = (NBProducts) mgr.getNBProducts(id);
		request.setAttribute("nbproductsForm", nbproducts);
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        if (!ifReference.equals("")) {
			
			request.setAttribute("ifReference", "true");
		  }
		String flag = StaticMethod
				.null2String(request.getParameter("flag"), ""); 
		request.setAttribute("flag", flag);
		
		return mapping.findForward("detail");
	}
	/**
	 * 显示已删除产品详细信息
	 */
	public ActionForward showDeletedDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NBProductsForm nbproductsForm = (NBProductsForm) form;
		String id = nbproductsForm.getId();
		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
		NBProducts nbproducts = (NBProducts) mgr.getNBProducts(id);
		request.setAttribute("nbproductsForm", nbproducts);
		String flag = StaticMethod
				.null2String(request.getParameter("flag"), "");
		request.setAttribute("flag", flag);
		return mapping.findForward("deleteddetailjsp");
	}
	/**
	 * 通过关键字查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
/**		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		
		String pageIndexName = new org.displaytag.util.ParamEncoder("process")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		NBProductsForm nbproductsForm = (NBProductsForm) form;
		*/
		String txtwords = request.getParameter("txtwords");
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		INBProductsManager mgr = (INBProductsManager) getBean("INBProductsManager");
		String deleted = StaticMethod.nullObject2String(request.getParameter("deleted"));
		Integer newDeleted = new Integer(deleted);
		List querylist = mgr.getNBProductssByTxtwords(newDeleted,txtwords,"com.boco.eoms.sheet.nbproducts.model.NBProducts");
		if (!ifReference.equals("")) {
			request.setAttribute("ifReference",ifReference);
		}
		request.setAttribute("nbproductsList", querylist);
		if (deleted.equals("0")){ 
		     return mapping.findForward("listjsp");
		}else {
			return mapping.findForward("deletedlistjsp");
		}
	}

	
	
	
}
