package com.boco.eoms.businessupport.serviceprepare.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.businessupport.serviceprepare.mgr.ServicePrepareMgr;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessType;
import com.boco.eoms.businessupport.serviceprepare.model.ProductSpecification;
import com.boco.eoms.businessupport.serviceprepare.model.ProfessionalServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ServiceConfiguration;
import com.boco.eoms.businessupport.serviceprepare.model.TaskLinks;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;


public final class ServicePrepareAction extends BaseAction  {
/**
 * 呈现服务配置表的列表
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */	
	public ActionForward showServiceConfigurationPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("resultList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);	
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String order = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"resultList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_ORDER)));
		String sort = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"resultList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_SORT)));
		String orderCondition = "";
		if (!order.equals("")) {
			if (order.equals("1")) {
				order = " asc";
			} else {
				order = " desc";
			}
		}
		if (!sort.equals("")) {
			orderCondition = " " + sort + order;
		}
		String exportType = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		Map condition = new HashMap();	
		String flowId = StaticMethod.nullObject2String(request.getParameter("flowId"));
		String taskId = StaticMethod.nullObject2String(request.getParameter("taskId"));	
		condition.put("orderCondition", orderCondition);	
		condition.put("flowId", flowId);	
		condition.put("taskId", taskId);
		ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");	
		HashMap resultMap  = servicePrepareMgr.getAllServiceConfigurationListByCondition(condition, pageIndex, pageSize);
		List resultList = new ArrayList();
		List tmpResultList = (List)resultMap.get("queryList");
		for(int i=0;tmpResultList!=null&&i<tmpResultList.size();i++){
	        Map tmpMap = new HashMap();
			Object[] tmpObjArr = (Object[]) tmpResultList.get(i);        
	        ServiceConfiguration  serviceConfiguration = (ServiceConfiguration)tmpObjArr[0];
	        ProfessionalServiceDirectory  professionalServiceDirectory =(ProfessionalServiceDirectory)tmpObjArr[1];
	        TaskLinks  taskLinks = (TaskLinks)tmpObjArr[2];
	        ProcessType  processType =(ProcessType)tmpObjArr[3];
	        ProductSpecification  productSpecification = (ProductSpecification)tmpObjArr[4]; 
	        tmpMap.put("serviceConfiguration_id", StaticMethod.null2String(serviceConfiguration.getId()));
	        tmpMap.put("taskLinks_chName", StaticMethod.null2String(taskLinks.getChName()));
	        tmpMap.put("professionalServiceDirectory_name", StaticMethod.null2String(professionalServiceDirectory.getName()));
	        tmpMap.put("processType_name", StaticMethod.null2String(processType.getName()));
	        tmpMap.put("productSpecification_name", StaticMethod.null2String(productSpecification.getName()));
	        tmpMap.put("serviceConfiguration_isNeed", StaticMethod.null2String(serviceConfiguration.getIsNeed()));  
	        resultList.add(tmpMap);
		}
		Integer total = (Integer) resultMap.get("queryTotal");		
		request.setAttribute("resultList", resultList);
		request.setAttribute("total", total);	
		request.setAttribute("pageSize", pageSize);			
		return mapping.findForward("showServiceConfiguration");

	}	
	/**
	 * 显示某一个服务配置的详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showServiceConfigurationDetailPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String type = StaticMethod.null2String(request.getParameter("type"));
		String id = StaticMethod.null2String(request.getParameter("id"));
		Map condition = new HashMap();
		condition.put("id", id);
		ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");	
		HashMap resultMap  = servicePrepareMgr.getAllServiceConfigurationListByCondition(condition, new Integer(0), new Integer(-1));
        Map tmpMap = new HashMap();
		List tmpResultList = (List)resultMap.get("queryList");
		for(int i=0;tmpResultList!=null&&i<tmpResultList.size();i++){

			Object[] tmpObjArr = (Object[]) tmpResultList.get(0);        
	        ServiceConfiguration  serviceConfiguration = (ServiceConfiguration)tmpObjArr[0];
	        ProfessionalServiceDirectory  professionalServiceDirectory =(ProfessionalServiceDirectory)tmpObjArr[1];
	        TaskLinks  taskLinks = (TaskLinks)tmpObjArr[2];
	        ProcessType  processType =(ProcessType)tmpObjArr[3];
	        ProductSpecification  productSpecification = (ProductSpecification)tmpObjArr[4]; 
	        tmpMap.put("serviceConfiguration_id", StaticMethod.null2String(serviceConfiguration.getId()));
	        tmpMap.put("taskLinks_chName", StaticMethod.null2String(taskLinks.getChName()));
	        tmpMap.put("professionalServiceDirectory_name", StaticMethod.null2String(professionalServiceDirectory.getName()));
	        tmpMap.put("processType_name", StaticMethod.null2String(processType.getName()));
	        tmpMap.put("productSpecification_name", StaticMethod.null2String(productSpecification.getName()));
	        tmpMap.put("serviceConfiguration_isNeed", StaticMethod.null2String(serviceConfiguration.getIsNeed()));  
	        tmpMap.put("serviceConfiguration_remark", StaticMethod.null2String(serviceConfiguration.getRemark()));  	        
		}	
		request.setAttribute("tmpMap", tmpMap);	
		request.setAttribute("type", type);			
		return mapping.findForward("showServiceConfigurationDetailPage");

	}
	/**
	 * 删除一条服务配置信息（逻辑删除）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteServiceConfiguration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = StaticMethod.null2String(request.getParameter("id"));
		ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");
		ServiceConfiguration serviceConfiguration  = servicePrepareMgr.getServiceConfigurationSinglePO(id);
		if(serviceConfiguration!=null){
			serviceConfiguration.setDeleted("1");
		}
		servicePrepareMgr.addObject(serviceConfiguration);
        return this.showServiceConfigurationPage(mapping, form, request, response);

	}
	/**
	 * 跳转服务配置新增页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showAddServiceConfiguration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String type = StaticMethod.null2String(request.getParameter("type"));		
		ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");		
		Map condition = new HashMap(); 
		List ProcessTypeList = servicePrepareMgr.getProcessTypListByCondition(condition);
		List productSpecificationList = servicePrepareMgr.getAllProductSpecificationList();		
		request.setAttribute("ProcessTypeList", ProcessTypeList);
		request.setAttribute("type", type);		
		request.setAttribute("productSpecificationList", productSpecificationList);		
		return mapping.findForward("addServiceConfigurationPage");
          
	}	
	/**
	 * 根据流程的flowid产生相关的下拉框
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getTaskLinksByFlowId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			String flowId = StaticMethod.nullObject2String(request.getParameter("flowId"));
			ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");
			List taskLinks = servicePrepareMgr.getTaskLinksByFlowId(flowId);
			List professionalServiceDirectoryList = servicePrepareMgr.getAllProfessionalServiceDirectoryList();
			IDictService dictService = (IDictService) ApplicationContextHolder.getInstance().getBean("DictService"); 
			List commonList = dictService.getDictItems("dict-businesssupport-professionalservicedirectory#common");
			List newProfessionalServiceDirectoryList = new ArrayList();
			for(int i=0;professionalServiceDirectoryList!=null&&i<professionalServiceDirectoryList.size();i++){
				ProfessionalServiceDirectory professionalServiceDirectory  = (ProfessionalServiceDirectory)professionalServiceDirectoryList.get(i);
			    for(int j=0;commonList!=null&&j<commonList.size();j++){
                  	 DictItemXML dictItemXML= (DictItemXML)commonList.get(j);
			    	if(professionalServiceDirectory.getId().equals(dictItemXML.getItemId())&&dictItemXML.getName().equals(flowId)){
			    		newProfessionalServiceDirectoryList.add(professionalServiceDirectory);
			    	}
			    }
			}
			JSONArray jsonRoot = new JSONArray();
			jsonRoot.put(taskLinks);
			jsonRoot.put(newProfessionalServiceDirectoryList);		
			JSONUtil.print(response, jsonRoot.toString());
		}	
	/**
	 * 根据流程flowid和环节来查该流程和该环节下是否有相关服务，如果有讲相关服务勾上，再提交的时候删除相关服务，新增页面上选择的服务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getServiceConfigurationListByCondition(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			String flowId = StaticMethod.nullObject2String(request.getParameter("flowId"));
			String taskId = StaticMethod.null2String(request.getParameter("taskId"));

			ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");
			TaskLinks taskLinks = servicePrepareMgr.getTaskLinksSinglePO(taskId);
			List resultList = servicePrepareMgr.getAllListByCondition(flowId, taskLinks.getPhaseId());
			IDictService dictService = (IDictService) ApplicationContextHolder.getInstance().getBean("DictService"); 
			List commonList = dictService.getDictItems("dict-businesssupport-professionalservicedirectory#common");		
			List serviceList = new ArrayList();
			if(resultList!=null){
				for(int i =0;i<resultList.size();i++){
					Map serviceConfigurationMap = new HashMap();
					Object[] tmpObjArr = (Object[]) resultList.get(i);
					ServiceConfiguration serviceConfiguration = (ServiceConfiguration)tmpObjArr[0];
					ProfessionalServiceDirectory professionalServiceDirectory = (ProfessionalServiceDirectory)tmpObjArr[1];
					serviceConfigurationMap.put("professionalServiceDirectory_id", professionalServiceDirectory.getId());
					serviceConfigurationMap.put("serviceConfiguration_id", serviceConfiguration.getId());					
                    if(professionalServiceDirectory!=null&&commonList!=null){
                    	for(int j=0;j<commonList.size();j++){
                       	 DictItemXML dictItemXML= (DictItemXML)commonList.get(i);
                       	 if((dictItemXML.getItemId().equals(professionalServiceDirectory.getId()))&&
                       		(dictItemXML.getItemName().equals(flowId))){
                       		serviceConfigurationMap.put("taskName", dictItemXML.getItemDescription());
                       		break;
                       	 }
                    	}
                    }
					serviceConfigurationMap.put("serviceCnName", professionalServiceDirectory.getName());
					serviceList.add(serviceConfigurationMap);
				}
			}			
			JSONArray jsonRoot = new JSONArray();	
			jsonRoot.put(serviceList);			
			JSONUtil.print(response, jsonRoot.toString());
		}	
	public ActionForward addServiceConfiguration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");
            String taskId = StaticMethod.null2String(request.getParameter("taskId"));
            String specialtyIdArray = StaticMethod.null2String(request.getParameter("specialtyIdArray"));
            String processId = StaticMethod.null2String(request.getParameter("processId"));    
            String businessId = StaticMethod.null2String(request.getParameter("businessId"));  
            String isNeed = StaticMethod.null2String(request.getParameter("isNeed"));   
            String remark = StaticMethod.null2String(request.getParameter("remark"));
            String isServiceConfigurationIdArray = StaticMethod.null2String(request.getParameter("isServiceConfigurationIdArray"));            
            if(!isServiceConfigurationIdArray.equals("")){
                String[]  isServiceConfigurationIdArr = 	isServiceConfigurationIdArray.split(",");
                for(int j = 0;isServiceConfigurationIdArr!=null&&j <isServiceConfigurationIdArr.length;j++){
                	ServiceConfiguration serviceConfiguration  = servicePrepareMgr.getServiceConfigurationSinglePO(isServiceConfigurationIdArr[j]);
                	serviceConfiguration.setDeleted("1");
                	servicePrepareMgr.addObject(serviceConfiguration);
                }
                	
                }
            if(!specialtyIdArray.equals("")){
            String[]  specialtyIdArr = 	specialtyIdArray.split(",");
            for(int i = 0;specialtyIdArr!=null&&i <specialtyIdArr.length;i++){
            	ServiceConfiguration serviceConfiguration = new ServiceConfiguration();
            	serviceConfiguration.setProcessId(processId);
            	serviceConfiguration.setSpecialtyId(specialtyIdArr[i]);
            	serviceConfiguration.setTaskId(taskId);
            	serviceConfiguration.setBusinessId(businessId);
            	serviceConfiguration.setIsNeed(isNeed);
            	serviceConfiguration.setRemark(remark);
            	serviceConfiguration.setDeleted("0");
            	serviceConfiguration.setCreateTime(StaticMethod.getLocalTime());
            	servicePrepareMgr.addObject(serviceConfiguration);
            }
            	
            }
		   return mapping.findForward("addsuccess");        
	}
	/**
	 * 产品规格列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showProductSpecificationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("resultList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);	
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String order = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"resultList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_ORDER)));
		String sort = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"resultList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_SORT)));
		String orderCondition = "";
		if (!order.equals("")) {
			if (order.equals("1")) {
				order = " asc";
			} else {
				order = " desc";
			}
		}
		if (!sort.equals("")) {
			orderCondition = " " + sort + order;
		}
		String exportType = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		Map condition = new HashMap();	
		condition.put("orderCondition", orderCondition);	
		ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");	
		HashMap resultMap  = servicePrepareMgr.getAllProductSpecificationListByCondition(condition, pageIndex, pageSize);

		List resultList = (List)resultMap.get("queryList");
		Integer total = (Integer) resultMap.get("queryTotal");		
		request.setAttribute("resultList", resultList);
		request.setAttribute("total", total);	
		request.setAttribute("pageSize", pageSize);			
		return mapping.findForward("showProductSpecificationList");

	}
	/**
	 * 跳转到产品规格表编辑、新增详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showEditProductSpecification(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");		
		String id = StaticMethod.null2String(request.getParameter("id"));
		String type = "";
		if(id.equals("")){
			type ="add";
		}else{
			type ="modify";
			ProductSpecification productSpecification = servicePrepareMgr.getProductSpecificationSinglePO(id);
			request.setAttribute("productSpecification", productSpecification);
		}
        request.setAttribute("type", type);
        return mapping.findForward("showEditProductSpecification");

	}
	/**
	 * 产品规格表新增、编辑、删除提交方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editProductSpecification(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");		
		String type = StaticMethod.null2String(request.getParameter("type"));
		String id = StaticMethod.null2String(request.getParameter("id"));		
		String name = StaticMethod.null2String(request.getParameter("name"));
		String comment = StaticMethod.null2String(request.getParameter("comment"));
		String code = StaticMethod.null2String(request.getParameter("code"));		
		if(type.equals("add")){
			ProductSpecification productSpecification = new ProductSpecification();
			productSpecification.setCode(code);
			productSpecification.setName(name);
			productSpecification.setComment(comment);
			productSpecification.setDeleted("0");
			servicePrepareMgr.addObject(productSpecification);
		}else if(type.equals("modify")){
			ProductSpecification productSpecification = servicePrepareMgr.getProductSpecificationSinglePO(id);
			productSpecification.setCode(code);
			productSpecification.setName(name);
			productSpecification.setComment(comment);   
			servicePrepareMgr.addObject(productSpecification);
		}else if(type.equals("delete")){
			ProductSpecification productSpecification = servicePrepareMgr.getProductSpecificationSinglePO(id);	
			productSpecification.setDeleted("1");
			servicePrepareMgr.addObject(productSpecification);
		}

        return mapping.findForward("editsuccess");

	}	
}