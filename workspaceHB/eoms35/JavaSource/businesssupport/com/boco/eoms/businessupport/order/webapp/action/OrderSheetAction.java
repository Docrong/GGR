package com.boco.eoms.businessupport.order.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.order.util.OrderSheetConstants;
import com.boco.eoms.businessupport.order.webapp.form.OrderSheetForm;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;
import com.boco.eoms.businessupport.product.model.Smsspecialline;
import com.boco.eoms.businessupport.product.model.TrancePoint;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.TrancePointMgr;
import com.boco.eoms.businessupport.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;

public final class OrderSheetAction extends BaseAction {

	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IOrderSheetManager iOrderSheetManager = (IOrderSheetManager) getBean("IOrderSheetManager");
		OrderSheetForm orderSheetForm = (OrderSheetForm) form;
		boolean isNew = (null == orderSheetForm.getId() || "".equals(orderSheetForm.getId()));		
		OrderSheet orderSheet =new OrderSheet();
		HashMap orderSheetFormMap = SheetBeanUtils.bean2MapWithNull(orderSheet);
		SheetBeanUtils.populateDataObj2ReqMap(orderSheetFormMap, orderSheetForm);
        SheetBeanUtils.populate(orderSheet, orderSheetFormMap);		
		updateFormBean(mapping, request, orderSheetForm);				
		orderSheet.setDeleted(new Integer(0));
		iOrderSheetManager.saveOrderSheet(orderSheet);
		if(orderSheet.getId()!=null){//save sucess
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
		IOrderSheetManager mgr = (IOrderSheetManager) getBean("IOrderSheetManager");
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		request.setAttribute("ifReference", ifReference);
		String id = StaticMethod.nullObject2String(request.getParameter("ordersheetid"));		
		mgr.removeOrderSheet(id);
		if(id!=null){
			return mapping.findForward("deletesuccessjsp");
		}else{  
			return mapping.findForward("deletefailurejsp");
		}		
	}
	
	/**
	 * 保存修改信息
	 */
	public ActionForward xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderSheetForm ordersheetForm = (OrderSheetForm) form;
		String id = ordersheetForm.getId();
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
			IOrderSheetManager mgr = (IOrderSheetManager) getBean("IOrderSheetManager");
			OrderSheet ordersheet = (OrderSheet) convert(ordersheetForm);
			mgr.saveOrderSheet(ordersheet);
		}		
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
		OrderSheetForm ordersheetForm = (OrderSheetForm) form;
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
		IOrderSheetManager mgr = (IOrderSheetManager) getBean("IOrderSheetManager");
		List ordersheetList = mgr.getOrderSheets();
		if (!ifReference.equals("")) {			
			request.setAttribute("ifReference", "true");
		}
		request.setAttribute("ordersheetList", ordersheetList);
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
		IOrderSheetManager mgr = (IOrderSheetManager) getBean("IOrderSheetManager");
		List ordersheetList = mgr.getOrderSheets();
		request.setAttribute("ordersheetList", ordersheetList);
		return mapping.findForward("querypage");
	}
	/**
	 * 
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderSheetForm ordersheetForm = (OrderSheetForm) form;
		String id = ordersheetForm.getId();
		if (id == null) {
			id = (String) request.getParameter("id");
		}
		IOrderSheetManager mgr = (IOrderSheetManager) getBean("IOrderSheetManager");
		OrderSheet ordersheet = (OrderSheet) mgr.getOrderSheet(id);
		request.setAttribute("ordersheetForm", ordersheet);
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
	 * 显示定单详细信息
	 */
	public ActionForward showDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		OrderSheetForm ordersheetForm = (OrderSheetForm) form;
		String id = ordersheetForm.getId();
		IOrderSheetManager mgr = (IOrderSheetManager) getBean("IOrderSheetManager");
		OrderSheet ordersheet = (OrderSheet) mgr.getOrderSheet(id);
		request.setAttribute("ordersheetForm", ordersheet);
		String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
		String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        if (!ifReference.equals("")) {			
			request.setAttribute("ifReference", "true");
		  }
		String flag = StaticMethod
				.null2String(request.getParameter("flag"), ""); 
		request.setAttribute("flag", flag);
		request.setAttribute("sheetType", sheetType);
		request.setAttribute("taskName", taskName);
		return mapping.findForward("detail");
	}
	
	//根据主表中的主键去查找服务存量表 modify by shichuangke
	public ActionForward getSpecialLines(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		   OrderSheetForm ordersheetForm = (OrderSheetForm) form;
		   String orderId = ordersheetForm.getId();
		   IOrderSheetManager mgr = (IOrderSheetManager)ApplicationContextHolder
		              .getInstance().getBean("IOrderSheetManager");
		   OrderSheet orderSheet = mgr.getOrderSheet(orderId);
		   String specialtyType = orderSheet.getOrderBuisnessType();
		   if(orderId != null && !orderId.equals("")){
			   if(specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._GPRS_LINE)){//GPRS专线
	        	   GprsSpecialLine objectName = new GprsSpecialLine();
	        	   List list = mgr.getSpecialLinesByType(orderId,objectName);
	               List gprsspecialList = new ArrayList();
	               for(int i = 0;list!=null&& i<list.size();i++){
	       			Map rasMap = new HashMap();	
	       			GprsSpecialLine gprsSpecialLine = (GprsSpecialLine)list.get(i);
	    			rasMap.putAll(SheetBeanUtils.bean2Map(gprsSpecialLine));
	    			gprsspecialList.add(rasMap);
	               }
	               request.setAttribute("gprsspecialList", gprsspecialList);
	       		   request.setAttribute("total", new Integer(list.size()));
	       		   request.setAttribute("pageSize", new Integer(list.size()));
	       		   request.setAttribute("orderId", orderId);
	           }else if(specialtyType != null && !specialtyType.equals("")&& specialtyType.equals(Constants._IP_LINE)){//IP专线
	        	   IPSpecialLine objectName = new IPSpecialLine();
	        	   List list = mgr.getSpecialLinesByType(orderId,objectName);
	               List ipspecialList = new ArrayList();
	               for(int i = 0;list!=null&& i<list.size();i++){
	       			Map rasMap = new HashMap();	
	       			IPSpecialLine ipSpecialLine = (IPSpecialLine)list.get(i);
	    			rasMap.putAll(SheetBeanUtils.bean2Map(ipSpecialLine));
	    			ipspecialList.add(rasMap);
	               }
	               request.setAttribute("ipspecialList", ipspecialList);
	               
	               request.setAttribute("ipspecialList", ipspecialList);
	       		   request.setAttribute("total", new Integer(list.size()));
	       		   request.setAttribute("pageSize", new Integer(list.size()));
	       		   request.setAttribute("orderId", orderId);
	           }else if(specialtyType != null && !specialtyType.equals("")&& specialtyType.equals(Constants._TRANSFER_LINE)){//IP专线
	        	   TransferSpecialLine objectName = new TransferSpecialLine();
	        	   List list = mgr.getSpecialLinesByType(orderId,objectName);
	               List transferspecialList = new ArrayList();
	               for(int i = 0;list!=null&& i<list.size();i++){
	       			Map rasMap = new HashMap();	
	       			TransferSpecialLine transferSpecialLine = (TransferSpecialLine)list.get(i);
	    			rasMap.putAll(SheetBeanUtils.bean2Map(transferSpecialLine));
	    			transferspecialList.add(rasMap);
	               }
	               request.setAttribute("transferspecialList", transferspecialList);
	       		   request.setAttribute("total", new Integer(list.size()));
	       		   request.setAttribute("pageSize", new Integer(list.size()));
	       		   request.setAttribute("orderId", orderId);
	           }else{
	        	   return mapping.findForward("gprsspecialLineList");
	        }
		}else{
	        	   request.setAttribute("messages", "false");
	           }
	   		 if(!orderId.equals("") && specialtyType.equals(Constants._GPRS_LINE) ){
			    return mapping.findForward("gprsspecialLineList");
			}else if(!orderId.equals("") && specialtyType.equals(Constants._IP_LINE)){
				return mapping.findForward("ipspecialLineList");
			}else if(!orderId.equals("") && specialtyType.equals(Constants._TRANSFER_LINE)){
				return mapping.findForward("transferspecialLineList");
			}else{
				return null;
			}
	}
    //根据定单表id和专业类型查找不同的服务存量表 modify by shichuangke
	public ActionForward getSpecialLinesByType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		   OrderSheetForm ordersheetForm = (OrderSheetForm) form;
		   String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
		   String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
		   																			   
		   String specialtyType = StaticMethod.nullObject2String(request.getParameter("specialtyType"));
		   System.out.println("@@specialtyType@@"+specialtyType);
		   String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		   String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus")); 
		   
		   String addr = StaticMethod.nullObject2String(request.getParameter("addr")); 
		   //added by liufyuan
		   String isShowLanguage = StaticMethod.nullObject2String(request.getParameter("isShowLanguage")); 
		   String isShowSms = StaticMethod.nullObject2String(request.getParameter("isShowSms")); 
		   
//		   add by lizhigang
		   String trancePointId = StaticMethod.nullObject2String(request.getParameter("id"));
		   TrancePointMgr businessupportMgr = (TrancePointMgr) getBean("businessupportMgr");
		   
		   TrancePoint tp = businessupportMgr.getBusinessupport(trancePointId);
//		   String portDetailAdd = tp.getPortDetailAdd();
		   
		   request.setAttribute("taskName", taskName);
		   if(specialtyType.equals("yuyin")){
			   specialtyType=Constants._LANGUAGE_LINE;
		   }
		   request.setAttribute("specialtyType", specialtyType);
		   request.setAttribute("taskStatus", taskStatus);
		   
		   IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder
		           .getInstance().getBean("IOrderSheetManager");
		   if(orderId != null && !orderId.equals("")){
			   if(specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._GPRS_LINE)){//GPRS专线
	        	   GprsSpecialLine objectName = new GprsSpecialLine();
	        	   List list = iOrderSheetManager.getSpecialLinesByType(orderId,objectName);
	        	   
	               List gprsspecialList = new ArrayList();
	               for(int i = 0;list!=null&& i<list.size();i++){
	       			Map rasMap = new HashMap();	
	       			GprsSpecialLine gprsSpecialLine = (GprsSpecialLine)list.get(i);
	    			rasMap.putAll(SheetBeanUtils.bean2Map(gprsSpecialLine));
	    			gprsspecialList.add(rasMap);
	               }
	               request.setAttribute("gprsspecialList", gprsspecialList);
	               request.setAttribute("sheetType", sheetType);
	               request.setAttribute("taskName", taskName);
	       		   request.setAttribute("total", new Integer(list.size()));
	       		   request.setAttribute("pageSize", new Integer(list.size()));
	       		   request.setAttribute("orderId", orderId);
	           }else if(specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._IP_LINE)){//IP专线
	        	   IPSpecialLine objectName = new IPSpecialLine();
	        	   List list = iOrderSheetManager.getSpecialLinesByType(orderId,objectName);
	        	   
	               List ipspecialList = new ArrayList();
	               for(int i = 0;list!=null&& i<list.size();i++){
	       			Map rasMap = new HashMap();	
	       			IPSpecialLine ipSpecialLine = (IPSpecialLine)list.get(i);
	    			rasMap.putAll(SheetBeanUtils.bean2Map(ipSpecialLine));
	    			ipspecialList.add(rasMap);
	               }
	               request.setAttribute("ipspecialList", ipspecialList);
	               request.setAttribute("sheetType", sheetType);
	               request.setAttribute("taskName", taskName);
	       		   request.setAttribute("total", new Integer(list.size()));
	       		   request.setAttribute("pageSize", new Integer(list.size()));
	       		   request.setAttribute("orderId", orderId);
	           }else if(specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._TRANSFER_LINE)){//传输专线
	        	   TransferSpecialLine objectName = new TransferSpecialLine();
	        	   List list = iOrderSheetManager.getSpecialLinesByType(orderId,objectName);
	        	   
	        	   List transByTranceList = new ArrayList();
	        	   if(!"".equals(addr)){
	        		   for(int i = 0;list!=null&& i<list.size();i++){
	            		   TransferSpecialLine transferSpecialLine = (TransferSpecialLine)list.get(i);
	            		   String portADetailAdd = transferSpecialLine.getPortADetailAdd();
	            		   String portZDetailAdd = transferSpecialLine.getPortZDetailAdd();
	            		   if(portADetailAdd.equals(addr) || portZDetailAdd.equals(addr)){
	            			   transByTranceList.add(transferSpecialLine);
	            		   }
	            	   }
	        	   }else{
	        		   transByTranceList = list;
	        	   }
	        	  
	        	   
	               List transferspecialList = new ArrayList();
	               for(int i = 0;transByTranceList!=null&& i<transByTranceList.size();i++){
	       			Map rasMap = new HashMap();	
	       			TransferSpecialLine transferSpecialLine = (TransferSpecialLine)transByTranceList.get(i);
	    			rasMap.putAll(SheetBeanUtils.bean2Map(transferSpecialLine));
	    			transferspecialList.add(rasMap);
	               }
	               request.setAttribute("transferspecialList", transferspecialList);
	               request.setAttribute("sheetType", sheetType);
	               request.setAttribute("taskName", taskName);
	       		   request.setAttribute("total", new Integer(list.size()));
	       		   request.setAttribute("pageSize", new Integer(list.size()));
	       		   request.setAttribute("orderId", orderId);
	           }else if(specialtyType != null && !specialtyType.equals("") && (specialtyType.equals(Constants._LANGUAGE_LINE) || specialtyType.equals("yuyin")|| specialtyType.equals("yuyin"))){//IP专线
	        	   LanguageSpecialLine objectName = new LanguageSpecialLine();
	        	   List list = iOrderSheetManager.getSpecialLinesByType(orderId,objectName);
	        	   
	               List languagespecialList = new ArrayList();
	               for(int i = 0;list!=null&& i<list.size();i++){
	       			Map rasMap = new HashMap();	
	       			LanguageSpecialLine languageSpecialLine = (LanguageSpecialLine)list.get(i);
	    			rasMap.putAll(SheetBeanUtils.bean2Map(languageSpecialLine));
	    			languagespecialList.add(rasMap);
	               }
	               request.setAttribute("languagespecialList", languagespecialList);
	               //request.setAttribute("sheetType", sheetType);
	               request.setAttribute("taskName", taskName);
	       		   request.setAttribute("total", new Integer(list.size()));
	       		   request.setAttribute("pageSize", new Integer(list.size()));
	       		   request.setAttribute("orderId", orderId);
	       		   request.setAttribute("isShowLanguage", isShowLanguage);
	       		   System.out.println("@@isShowLanguage");
	           
	          
           }else if(specialtyType != null && !specialtyType.equals("") && (specialtyType.equals("101230106") ||specialtyType.equals("101230105") || specialtyType.equals(Constants._DUANXIN_LINE)|| specialtyType.equals(Constants._CAIXIN_LINE))){
        	   Smsspecialline objectName = new Smsspecialline();
        	   List list = iOrderSheetManager.getSpecialLinesByType(orderId,objectName);
        	   
               List smsspeciallineList = new ArrayList();
               for(int i = 0;list!=null&& i<list.size();i++){
       			Map rasMap = new HashMap();	
       			Smsspecialline smsspecialLine = (Smsspecialline)list.get(i);
    			rasMap.putAll(SheetBeanUtils.bean2Map(smsspecialLine));
    			smsspeciallineList.add(rasMap);
               }
               request.setAttribute("smsspeciallineList", smsspeciallineList);
               //request.setAttribute("sheetType", sheetType);
               request.setAttribute("taskName", taskName);
       		   request.setAttribute("total", new Integer(list.size()));
       		   request.setAttribute("pageSize", new Integer(list.size()));
       		   request.setAttribute("orderId", orderId);
       		   request.setAttribute("isShowSms", isShowSms);
       		   request.setAttribute("specialtyType", specialtyType);
       		   System.out.println("@@isShowSms");
           
           }else{
	        	   return mapping.findForward("gprsspecialLineList");
	        }
		} else {
			request.setAttribute("messages", "false");
		}
		if (!orderId.equals("") && specialtyType.equals(Constants._GPRS_LINE)) {
			return mapping.findForward("gprsspecialLineList");
		} else if (!orderId.equals("") && specialtyType.equals(Constants._IP_LINE)) {
			return mapping.findForward("ipspecialLineList");
		} else if (!orderId.equals("") && specialtyType.equals(Constants._TRANSFER_LINE)) {
			return mapping.findForward("transferspecialLineList");
		} else if (!orderId.equals("") && (specialtyType.equals("yuyin") ||specialtyType.equals("101230104") )) {
			return mapping.findForward("languagespecialLineList");
		} else if (!orderId.equals("") && (specialtyType.equals("101230105") ||specialtyType.equals("101230106") )) {
			return mapping.findForward("smsspeciallineList");
		
		} else {
			return null;
		}
	}
	/*
	 * 查询结果列表展现方法，根据所选条件组合查询未删除的定单记录信息 modify by shichuangke
	 */
	public ActionForward showListQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//生成时间
		String creatTimeStartDateExpression = StaticMethod.nullObject2String(request.getParameter("creatTimeStartDate"));
		String creatTimeEndDateExpression = StaticMethod.nullObject2String(request.getParameter("creatTimeEndDate"));
		//完成时限
		String completeLimitStartDateExpression = StaticMethod.nullObject2String(request.getParameter("completeLimitStartDate"));
		String completeLimitEndDateExpression = StaticMethod.nullObject2String(request.getParameter("completeLimitEndDate"));
		//变更时间
		String changeTimeStartDateExpression = StaticMethod.nullObject2String(request.getParameter("changeTimeStartDate"));
		String changeTimeEndDateExpression = StaticMethod.nullObject2String(request.getParameter("changeTimeEndDate"));
		//撤销时间
		String cancelTimeStartDateExpression = StaticMethod.nullObject2String(request.getParameter("cancelTimeStartDate"));
		String cancelTimeEndDateExpression = StaticMethod.nullObject2String(request.getParameter("cancelTimeEndDate"));
		//竣工时间
		String endTimeStartDateExpression = StaticMethod.nullObject2String(request.getParameter("endTimeStartDate"));
		String endTimeEndDateExpression = StaticMethod.nullObject2String(request.getParameter("endTimeEndDate"));

		String orderType = StaticMethod.nullObject2String(request.getParameter("orderTypeChoiceExpression"));
		String urgentDegree = StaticMethod.nullObject2String(request.getParameter("urgentDegreeChoiceExpression"));
		String orderBuisnessType = StaticMethod.nullObject2String(request.getParameter("orderBuisnessTypeChoiceExpression"));	
		String orderNumber = StaticMethod.nullObject2String(request.getParameter("orderNumber"));
		String isCompleted = StaticMethod.nullObject2String(request.getParameter("isCompletedChoiceExpression"));
		String mainProductInstanceCode = StaticMethod.nullObject2String(request.getParameter("mainProductInstanceCode"));
		Map queryMap = new HashMap();
		
		queryMap.put("creatTimeStartDateExpression", creatTimeStartDateExpression);
		queryMap.put("creatTimeEndDateExpression", creatTimeEndDateExpression);
		queryMap.put("changeTimeStartDateExpression", changeTimeStartDateExpression);
		queryMap.put("changeTimeEndDateExpression", changeTimeEndDateExpression);
		queryMap.put("completeLimitEndDateExpression", completeLimitEndDateExpression);
		queryMap.put("completeLimitStartDateExpression", completeLimitStartDateExpression);
		queryMap.put("cancelTimeStartDateExpression", cancelTimeStartDateExpression);
		queryMap.put("cancelTimeEndDateExpression", cancelTimeEndDateExpression);
		queryMap.put("endTimeStartDateExpression", endTimeStartDateExpression);
		queryMap.put("endTimeEndDateExpression", endTimeEndDateExpression);
		queryMap.put("orderType", orderType);
		queryMap.put("urgentDegree", urgentDegree);
		queryMap.put("orderBuisnessType", orderBuisnessType);
		queryMap.put("orderNumber", orderNumber);
		queryMap.put("isCompleted", isCompleted);
		queryMap.put("mainProductInstanceCode", mainProductInstanceCode);
		
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				OrderSheetConstants.ORDERSHEET_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));		
			
		IOrderSheetManager iOrderSheetManager = (IOrderSheetManager) getBean("IOrderSheetManager");
		Map map = (Map) iOrderSheetManager.getQueryOrderSheets(queryMap, pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(OrderSheetConstants.ORDERSHEET_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("querylist");		
		}
	
	
	/**
	 * 
	 * 进入存量查询页面
	 * 
	 */
	public ActionForward showProductsQueryPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IOrderSheetManager mgr = (IOrderSheetManager) getBean("IOrderSheetManager");
		List ordersheetList = mgr.getOrderSheets();
		request.setAttribute("ordersheetList", ordersheetList);
		return mapping.findForward("productquerypage");
	}
	/*
	 * 查询结果列表展现方法，根据所选条件组合查询未删除的存量信息  modify by shichuangke 
	 */
	public ActionForward showProductsListQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//取到所填写的客户名称和专业类别		
		String customName = StaticMethod.nullObject2String(request.getParameter("customName"));	
		String orderBuisnessType = StaticMethod.nullObject2String(request.getParameter("orderBuisnessType"));
		String customNo = StaticMethod.nullObject2String(request.getParameter("customNo"));	
		String customLevel = StaticMethod.nullObject2String(request.getParameter("customName"));
		//将所选结果放在map中
        Map queryMap = new HashMap();      
		queryMap.put("customName", customName);
		queryMap.put("orderBuisnessType", orderBuisnessType);
		queryMap.put("customNo", customNo);
		queryMap.put("customLevel", customLevel);
		String whereCondition = OrderSheetConstants.getWhereSql(queryMap);
		IOrderSheetManager mgr = (IOrderSheetManager) getBean("IOrderSheetManager");		
		Map  conditionMap = new HashMap();
		conditionMap.put("whereCondition", whereCondition);
		conditionMap.put("gprsClassName", "com.boco.eoms.businessupport.product.model.GprsSpecialLine");
		conditionMap.put("ipClassName", "com.boco.eoms.businessupport.product.model.IPSpecialLine");
		conditionMap.put("transferClassName", "com.boco.eoms.businessupport.product.model.TransferSpecialLine");
		conditionMap.put("orderClassName", "com.boco.eoms.businessupport.order.model.OrderSheet");		
		List gprsspeciallineList = mgr.getGprsProductsById(conditionMap);
		List ipspeciallineList = mgr.getIPProductsById(conditionMap);
		List transferspeciallineList = mgr.getTransferProductsById(conditionMap);
		if (gprsspeciallineList != null && gprsspeciallineList.size() > 0) {
 	       request.setAttribute("gprsspeciallineList", gprsspeciallineList);
          }
		if (transferspeciallineList != null && transferspeciallineList.size() > 0) {
	 	       request.setAttribute("transferspeciallineList", transferspeciallineList);
	      }
		if (ipspeciallineList != null && ipspeciallineList.size() > 0) {
 	       request.setAttribute("ipspeciallineList", ipspeciallineList);
          }
		return mapping.findForward("difproductsList");		
	}
	
	//得到和定单有关的工单
	 public ActionForward getRelationSheetById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		   //OrderSheetForm ordersheetForm = (OrderSheetForm) form;
		   String orderId = StaticMethod.nullObject2String(request
					.getParameter("id"));
		   IOrderSheetManager mgr = (IOrderSheetManager) getBean("IOrderSheetManager");
			List resourceconfirmList = mgr.getOrderSheetsById(orderId,
					"com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmMain");
			List businessimplementList = mgr.getOrderSheetsById(orderId,
					"com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain");
			List operationchangeList = mgr.getOrderSheetsById(orderId,
		            "com.boco.eoms.sheet.operationchange.model.OperationChangeMain");
			List operationbackoutList = mgr.getOrderSheetsById(orderId,
                    "com.boco.eoms.sheet.operationbackout.model.OperationBackOutMain");
			if (resourceconfirmList != null && resourceconfirmList.size() > 0) {
				request.setAttribute("resourceconfirmList", resourceconfirmList);
			}
			if (businessimplementList != null && businessimplementList.size() > 0) {
				request.setAttribute("businessimplementList", businessimplementList);
			}
			 if(operationchangeList!=null && operationchangeList.size()>0){
			    request.setAttribute("operationchangeList", operationchangeList);
			 }
			 if(operationbackoutList!=null && operationbackoutList.size()>0){
				 request.setAttribute("operationbackoutList", operationbackoutList);
			 }
			return mapping.findForward("sheetList");
		}
	 
}
