package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.extra.supplierkpi.model.TawSuppKpiInstReportOrder;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportmodelMatching;

import com.boco.eoms.extra.supplierkpi.schedule.TawSuppkpiReportStorageRepair;
import com.boco.eoms.extra.supplierkpi.service.ITawSuppKpiInstReportOrderManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSuppKpiInstReportOrderForm;

public final class TawSuppKpiInstReportOrderAction  extends BaseAction {
	
    
	public ActionForward search (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		
		String specialType = (String)(request.getParameter("dictId"));				
		ArrayList kpiItemNames = new ArrayList();
		kpiItemNames = (ArrayList)mgr.getTawSupplierkpiItemNames(specialType);	
		
		request.setAttribute("specialType", specialType);
		request.setAttribute("kpiItemNames", kpiItemNames);
		return mapping.findForward("order");
	}
	
	public ActionForward save (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionMessages messages = new ActionMessages();
		TawSuppKpiInstReportOrderForm reportOrderForm = (TawSuppKpiInstReportOrderForm)form;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession
		().getAttribute("sessionform");
		String userName = sessionform.getUsername();
		String kpiItemId = "";
		ArrayList kpiItemIds = new ArrayList();
		String kpiItemName = "";
		String specialType = reportOrderForm.getSpecialType();
		
		if(reportOrderForm.getKpiItemId().trim()!= null && !reportOrderForm.getKpiItemId().trim().equals("")){
			String temp = reportOrderForm.getKpiItemId().substring(0,reportOrderForm.getKpiItemId().length()-2);
			String[] temp1 = StaticMethod.TokenizerString2(temp, "##");
			int j = 1;
			for(int i=0;i<temp1.length;i++){
				String[] temp2 = StaticMethod.TokenizerString2(temp1[i], "@@");
				kpiItemId =kpiItemId+","+temp2[0].trim();
				kpiItemIds.add(temp2[0].trim());
				kpiItemName = kpiItemName+"<br>"+j+"."+temp2[1];
				j++;
			}
			kpiItemId = kpiItemId.substring(1, kpiItemId.length());
			
		}
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");		
		if(reportOrderForm.getId()!=null && !reportOrderForm.getId().equals("")){
            //修改报表订制项
			TawSuppKpiInstReportOrder updateReportOrder = mgr.getTawSuppKpiInstReportOrder(reportOrderForm.getId());
			updateReportOrder.setState("0");
			mgr.saveTawSuppKpiInstReportOrder(updateReportOrder);
			
			reportOrderForm.setHistoryModelId(updateReportOrder.getId());
			reportOrderForm.setKpiItemId(kpiItemId);
			reportOrderForm.setKpiItemName(kpiItemName);
			reportOrderForm.setCreateMan(userName);
			reportOrderForm.setCreateTime(StaticMethod.getCurrentDateTime());
			reportOrderForm.setState("1");
			reportOrderForm.setAppendState("0");		
			TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder = (TawSuppKpiInstReportOrder) convert(reportOrderForm);
			TawSuppKpiInstReportOrder newOrder = mgr.save2TawSuppKpiInstReportOrder(tawSuppKpiInstReportOrder);
			
			//保存统计模型项        
			saveReportmodelMmatching( newOrder, kpiItemIds, userName, request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawSupplierkpiInstance.updated"));
			saveMessages(request, messages);			
			
		}else{
			// 保存报表订制项			
			reportOrderForm.setServiceType(StaticMethod.null2String(specialType.substring(0,5)));
			reportOrderForm.setKpiItemId(kpiItemId);
			reportOrderForm.setKpiItemName(kpiItemName);
			reportOrderForm.setCreateMan(userName);
			reportOrderForm.setCreateTime(StaticMethod.getCurrentDateTime());
			reportOrderForm.setState("1");
			reportOrderForm.setAppendState("0");
					
			TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder = (TawSuppKpiInstReportOrder) convert(reportOrderForm);
			
			TawSuppKpiInstReportOrder newOrder = mgr.saveTawSuppKpiInstReportOrder(tawSuppKpiInstReportOrder);
			//保存统计模型项        
			saveReportmodelMmatching( newOrder, kpiItemIds, userName, request); 
			
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawSupplierkpiInstance.updated"));
			        saveMessages(request, messages);    
						        
		}
		return list ( mapping,form,request,response,specialType);		
	}

	public ActionForward append (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionMessages messages = new ActionMessages();
		TawSuppKpiInstReportOrderForm reportOrderForm = (TawSuppKpiInstReportOrderForm)form;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession
		().getAttribute("sessionform");
		String userName = sessionform.getUsername();
		String kpiItemId = "";
		ArrayList kpiItemIds = new ArrayList();
		String kpiItemName = "";
		//String specialType = reportOrderForm.getSpecialType();
		if(reportOrderForm.getKpiItemId().trim()!= null && !reportOrderForm.getKpiItemId().trim().equals("")){
			String temp = reportOrderForm.getKpiItemId().substring(0,reportOrderForm.getKpiItemId().length()-2);
			String[] temp1 = StaticMethod.TokenizerString2(temp, "##");
			int j = 1;
			for(int i=0;i<temp1.length;i++){
				String[] temp2 = StaticMethod.TokenizerString2(temp1[i], "@@");
				kpiItemId =kpiItemId+","+temp2[0].trim();
				kpiItemIds.add(temp2[0].trim());
				kpiItemName = kpiItemName+"<br>"+j+"."+temp2[1];
				j++;
			}
			kpiItemId = kpiItemId.substring(1, kpiItemId.length());			
		}		
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");
		
		if(reportOrderForm.getId()!=null && !reportOrderForm.getId().equals("")){
            //修改报表订制项
			TawSuppKpiInstReportOrder updateReportOrder = mgr.getTawSuppKpiInstReportOrder(reportOrderForm.getId());
			updateReportOrder.setState("0");
			mgr.saveTawSuppKpiInstReportOrder(updateReportOrder);
			
			reportOrderForm.setHistoryModelId(updateReportOrder.getId());
			reportOrderForm.setKpiItemId(kpiItemId);
			reportOrderForm.setKpiItemName(kpiItemName);
			reportOrderForm.setCreateMan(userName);
			reportOrderForm.setCreateTime(StaticMethod.getCurrentDateTime());
			reportOrderForm.setState("1");
			reportOrderForm.setAppendState("1");
					
			TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder = (TawSuppKpiInstReportOrder) convert(reportOrderForm);
			mgr.save2TawSuppKpiInstReportOrder(tawSuppKpiInstReportOrder);
			
			//保存统计模型项        
			saveReportmodelMmatching( tawSuppKpiInstReportOrder, kpiItemIds, userName,request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawSupplierkpiInstance.updated"));
			saveMessages(request, messages);			
			
		}
//		else{
//
//			// 保存报表订制项			
//			reportOrderForm.setServiceType(StaticMethod.null2String(specialType.substring(0,5)));
//			reportOrderForm.setKpiItemId(kpiItemId);
//			reportOrderForm.setKpiItemName(kpiItemName);
//			reportOrderForm.setCreateMan(userName);
//			reportOrderForm.setCreateTime(StaticMethod.getCurrentDateTime());
//			reportOrderForm.setState("2");
//					
//			TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder = (TawSuppKpiInstReportOrder) convert(reportOrderForm);			
//	        mgr.save2TawSuppKpiInstReportOrder(tawSuppKpiInstReportOrder);
//			//保存统计模型
//	        String state = "2";
//			saveReportmodelMmatching( tawSuppKpiInstReportOrder, kpiItemIds, userName, state, request); 			
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"tawSupplierkpiInstance.save"));
//			        saveMessages(request, messages); 						        
//		}	    
	    TawSuppkpiReportStorageRepair storageRepair = new TawSuppkpiReportStorageRepair(reportOrderForm);
	    //删除统计报表中无用的数据
	    storageRepair.cleanHistoryReportStorage();
	    //追加指标后，生成报表
	    storageRepair.executeCreate();
	    //修改订制报表中的状态.
	    storageRepair.modifyReportOrderState();
		return mapping.findForward("succeed");		
	}	
	
	public ActionForward eidt (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		TawSuppKpiInstReportOrderForm reportOrderForm = (TawSuppKpiInstReportOrderForm)form;
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");		
		TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder = mgr.getTawSuppKpiInstReportOrder(reportOrderForm.getId());
		
		String startTime = StaticMethod.getCurrentDateTime(String.valueOf(tawSuppKpiInstReportOrder.getReportStartTime()),"yyyy-MM-dd HH:mm:ss");
		String endTime = StaticMethod.getCurrentDateTime(String.valueOf(tawSuppKpiInstReportOrder.getReportEndTime()),"yyyy-MM-dd HH:mm:ss");		
		reportOrderForm = (TawSuppKpiInstReportOrderForm) convert(tawSuppKpiInstReportOrder);
		
		ITawSupplierkpiInstanceManager mgr2 = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");

		ArrayList kpiItemNames = new ArrayList();
		String specialType = tawSuppKpiInstReportOrder.getSpecialType();
		kpiItemNames = (ArrayList)mgr2.getTawSupplierkpiItemNames(specialType);
		
		request.setAttribute("id", reportOrderForm.getId());
		request.setAttribute("kpiItemNames", kpiItemNames);		
		request.setAttribute("reportName", reportOrderForm.getReportName());
		request.setAttribute("serviceType", reportOrderForm.getServiceType());
		request.setAttribute("specialType", reportOrderForm.getSpecialType());
		request.setAttribute("latitude", reportOrderForm.getLatitude());
		request.setAttribute("reportStartTime", startTime);
		request.setAttribute("reportEndTime", endTime);
		request.setAttribute("reportType", reportOrderForm.getReportType());
		request.setAttribute("kpiItemId", reportOrderForm.getKpiItemId());
		
		return mapping.findForward("edit");
	}

	public ActionForward list (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, String specialType)
			throws Exception {

		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");		
		        
		//显示订制列表        
        String pageIndexName = new org.displaytag.util.ParamEncoder(
			      "tawSuppKpiInstReportOrder")
			   .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
	    final int pageSize = 15;
	    final int pageIndex = GenericValidator.isBlankOrNull(request
			   .getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
			   .getParameter(pageIndexName)) - 1);		        
	    String pageNum = String.valueOf(pageIndex);        
	    String hql = " where state = 1 and specialType = "+specialType+" order by createTime desc";
        Map map = (Map) mgr.getTawSuppKpiInstReportOrders(pageIndex, pageSize, hql, specialType);
        List list = (List) map.get("result");
        
	    request.setAttribute("tawSuppKpiInstReportOrders", list);
	    request.setAttribute("size", map.get("total"));
	    request.setAttribute("pageNum", pageNum);
		return mapping.findForward("list");
	}
	
	public ActionForward list (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");		
		        
		//显示订制列表 
		String specialType = (String)(request.getParameter("dictId"));		
        String pageIndexName = new org.displaytag.util.ParamEncoder(
			      "tawSuppKpiInstReportOrder")
			   .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
	    final int pageSize = 15;
	    final int pageIndex = GenericValidator.isBlankOrNull(request
			   .getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
			   .getParameter(pageIndexName)) - 1);		        
	    String pageNum = String.valueOf(pageIndex);        
	    String hql = " where state = 1 and specialType = "+specialType+" order by createTime desc";
        Map map = (Map) mgr.getTawSuppKpiInstReportOrders(pageIndex, pageSize, hql, specialType);
        List list = (List) map.get("result");
        
	    request.setAttribute("tawSuppKpiInstReportOrders", list);
	    request.setAttribute("size", map.get("total"));
	    request.setAttribute("pageNum", pageNum);
		return mapping.findForward("list");
	}	

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
	
	public void saveReportmodelMmatching(TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder,ArrayList kpiItemIds,String userName, HttpServletRequest request){
		
		//保存统计模型项		
		ActionMessages messages = new ActionMessages();		
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");		
		//String whereStr = " where state = '1' and kpiItemId = '" + tawSuppKpiInstReportOrder.getKpiItemId() + "' and serviceType = '" + tawSuppKpiInstReportOrder.getServiceType() +"' and specialType = '" + tawSuppKpiInstReportOrder.getSpecialType() +"' and reportType = '" + tawSuppKpiInstReportOrder.getReportType() + "'";
        //Map Map = (Map) mgr.getTawSuppKpiInstReportOrders(0, 2, whereStr, tawSuppKpiInstReportOrder.getSpecialType());
        //List orders = (List) Map.get("result");
        //if(orders.size()>0&&orders!=null){
        	
            //TawSuppKpiInstReportOrder reportOrder = (TawSuppKpiInstReportOrder)orders.get(0);
            Iterator iter = kpiItemIds.iterator();
            int i = 1;   //报表列序号
            while(iter.hasNext()){
            	String itemID = (String)iter.next();
            	TawSuppkpiReportmodelMatching matching = new TawSuppkpiReportmodelMatching();
            	matching.setModelId(tawSuppKpiInstReportOrder.getId());
            	matching.setKpiItemId(itemID.trim());
            	matching.setCreateMan(userName);
            	matching.setReportcol("col_"+ i++);
            	matching.setCreateTime(StaticMethod.getTimestamp(StaticMethod.getCurrentDateTime()));
            	matching.setState("1");
            	
            	mgr.saveTawSuppkpiReportmodelMatching(matching);       	
    	        
    			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
    					"tawSupplierkpiInstance.updated"));
    			        saveMessages(request, messages);        	
            }        	
        //}
		
	}

    /**
	 * 根据父节点的id得到所有子节点的JSON数据，只显示两层 071204
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");
		ArrayList list = new ArrayList();

		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		list = mgr.getDictSonsByDictid(nodeId);

		JSONArray jsonRoot = new JSONArray();

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawSystemDictType _objDictType = (TawSystemDictType) rowIt.next();

			JSONObject jitem = new JSONObject();
			jitem.put("id", _objDictType.getDictId());
			jitem.put("dictId", _objDictType.getDictId());
			jitem.put("parentDictId", _objDictType.getParentDictId());
			jitem.put("text", _objDictType.getDictName());
			jitem.put("allowChild", "true");
			jitem.put("allowDelete", "true");
			jitem.put("qtip", "代码:" + _objDictType.getDictId() + "<br \\/>取值:"
					+ _objDictType.getDictCode() + "<br \\/>备注:"
					+ _objDictType.getDictRemark());
			jitem.put("qtipTitle", _objDictType.getDictName());
			if ((_objDictType.getDictId().length() == 7)) {
				jitem.put("leaf", true);
			}
			jsonRoot.put(jitem);
		}

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
	
	public void checkReportName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
    throws Exception {
    	String result = "0";
		String temp = StaticMethod.null2String(request.getParameter("reportName"));
		String reportName = URLDecoder.decode(temp, "UTF-8");
    	ITawSuppKpiInstReportOrderManager reportMgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");
    	String queryStr = "from TawSuppKpiInstReportOrder where reportName='" + reportName + "'";
    	List list = reportMgr.getTawSuppKpiInstReOrderQuerys(queryStr);
    	if (list.size() > 0) {
    		result = "1";
    	}
    	response.setContentType("text/xml; charset=UTF-8");
    	response.getWriter().print(result);
    }    
	  
	//删除已订制报表，同时得删除taw_suppkpi_report_storage,taw_suppkpi_reportmodel_matching
	public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		String id = StaticMethod.null2String(request.getParameter("id"));
		String specialType = StaticMethod.null2String(request.getParameter("specialType"));
		if ("".equals(id)) {
			return list (mapping, form,request, response, specialType);
		}
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");		
		TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder = mgr.getTawSuppKpiInstReportOrder(id);
		mgr.delete(tawSuppKpiInstReportOrder);
		return list (mapping, form,request, response, specialType);
	}

}
