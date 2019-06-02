package com.boco.eoms.businessupport.product.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.ITransferSpecialLineManager;
import com.boco.eoms.businessupport.product.webapp.form.TransferSpecialLineForm;

public final class TransferSpecialLineAction extends BaseAction {

	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITransferSpecialLineManager iTransferSpecialLineManager = (ITransferSpecialLineManager) getBean("ITransferSpecialLineManager");
		TransferSpecialLineForm transferSpecialLineForm = (TransferSpecialLineForm) form;
		String ordersheetid = StaticMethod.nullObject2String(request.getParameter("ordersheetid"));
		System.out.println("!!!!!!!!!!!!!!!!!1" + ordersheetid);
//		form转Object
		TransferSpecialLine transferSpecialLine =new TransferSpecialLine();
		HashMap transferSpecialLineFormMap = SheetBeanUtils.bean2MapWithNull(transferSpecialLine);
		SheetBeanUtils.populateDataObj2ReqMap(transferSpecialLineFormMap, transferSpecialLineForm);
        SheetBeanUtils.populate(transferSpecialLine, transferSpecialLineFormMap);
		
		updateFormBean(mapping, request, transferSpecialLineForm);				
		transferSpecialLine.setDeleted(new Integer(0));
		iTransferSpecialLineManager.saveTransferSpecialLine(transferSpecialLine);
		if(transferSpecialLine.getId()!=null){//save sucess
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
		ITransferSpecialLineManager mgr = (ITransferSpecialLineManager) getBean("ITransferSpecialLineManager");
		String id = StaticMethod.nullObject2String(request.getParameter("transferid"));		
		mgr.removeTransferSpecialLine(id);
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
		ITransferSpecialLineManager mgr = (ITransferSpecialLineManager) getBean("ITransferSpecialLineManager");
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		request.setAttribute("ifReference", ifReference);
		String id = StaticMethod.nullObject2String(request.getParameter("transferspeciallineid"));		
		mgr.restoreTransferSpecialLine(id);
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
		TransferSpecialLineForm transferspeciallineForm = (TransferSpecialLineForm) form;
		String id = transferspeciallineForm.getId();
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		String type = StaticMethod.nullObject2String(request.getParameter("type"));
		String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
		String orderSheet_Id = StaticMethod.nullObject2String(request.getParameter("orderId"));
		
		request.setAttribute("ifReference", ifReference);
		if(type.equals("add")) {
			return mapping.findForward("formjsp");
		}		

		ITransferSpecialLineManager mgr = (ITransferSpecialLineManager) getBean("ITransferSpecialLineManager");
		TransferSpecialLine transferspecialline = (TransferSpecialLine) convert(transferspeciallineForm);
		if(orderSheet_Id.equals(""))
			throw new Exception("orderId is null");
		transferspecialline.setOrderSheet_Id(orderSheet_Id);
		mgr.saveTransferSpecialLine(transferspecialline);
		
		request.setAttribute("sheetType", sheetType);
		return mapping.findForward("formjsp");
	}
	/**
	 * 进入订单目录的查询未删除的对象
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
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		ITransferSpecialLineManager mgr = (ITransferSpecialLineManager) getBean("ITransferSpecialLineManager");
		List transferspeciallineList = mgr.getTransferSpecialLines();
		if (!ifReference.equals("")) {			
			request.setAttribute("ifReference", "true");
		}
		request.setAttribute("transferspeciallineList", transferspeciallineList);
		return mapping.findForward("listjsp");
	}
	/**
	 * 
	 * 进入查询页面
	 * 
	 */
	public ActionForward showQueryPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITransferSpecialLineManager mgr = (ITransferSpecialLineManager) getBean("ITransferSpecialLineManager");
		List transferspeciallineList = mgr.getTransferSpecialLines();
		request.setAttribute("transferspeciallineList", transferspeciallineList);
		return mapping.findForward("querypage");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
//		ITawSystemDeptManager itawSystemDeptManager = (ITawSystemDeptManager)ApplicationContextHolder
//        .getInstance().getBean("ItawSystemDeptManager");
//		TawSystemDept tawSystemDept = (TawSystemDept)itawSystemDeptManager.getTawSystemDeptById(new Integer(sessionform.getDeptid()));
//		String areaId = tawSystemDept.getAreaid();
        TransferSpecialLineForm transferspeciallineForm = (TransferSpecialLineForm) form;
		String id = transferspeciallineForm.getId();
		if (id == null) {
			id = (String) request.getParameter("id");
		}
		ITransferSpecialLineManager mgr = (ITransferSpecialLineManager) getBean("ITransferSpecialLineManager");
		TransferSpecialLine transferspecialline = (TransferSpecialLine) mgr.getTransferSpecialLine(id);
		String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
		request.setAttribute("sheetType", sheetType);
		//request.setAttribute("areaId", areaId);
		request.setAttribute("transferspeciallineForm", transferspecialline);
		request.setAttribute("orderId", transferspecialline.getOrderSheet_Id());
		return mapping.findForward("formjsp");
	}

	/**
	 * 显示专线详细信息
	 */
	public ActionForward showDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TransferSpecialLineForm transferspeciallineForm = (TransferSpecialLineForm) form;
		String id = transferspeciallineForm.getId();
//		String orderSheet_id = StaticMethod.nullObject2String(request.getParameter("orderid"));
		ITransferSpecialLineManager mgr = (ITransferSpecialLineManager) getBean("ITransferSpecialLineManager");
		TransferSpecialLine transferspecialline = (TransferSpecialLine) mgr.getTransferSpecialLine(id);
		//区分不同的工单，此变量是通过url传入
		String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		request.setAttribute("sheetType", sheetType);
		request.setAttribute("taskName", taskName);
		request.setAttribute("orderId", transferspecialline.getOrderSheet_Id());
		request.setAttribute("transferspeciallineForm", transferspecialline);		
		return mapping.findForward("detail");
	}
}
