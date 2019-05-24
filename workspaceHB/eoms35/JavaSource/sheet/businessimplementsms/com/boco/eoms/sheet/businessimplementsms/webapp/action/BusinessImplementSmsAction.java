
package com.boco.eoms.sheet.businessimplementsms.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.service.bo.IrmsResourceBo;
import com.boco.eoms.businessupport.order.webapp.action.OrderSheetMethod;
import com.boco.eoms.businessupport.serviceprepare.mgr.ServicePrepareMgr;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks;
import com.boco.eoms.businessupport.serviceprepare.model.ProfessionalServiceDirectory;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.businessimplementsms.model.BusinessImplementSmsMain;
import com.boco.eoms.sheet.businessimplementsms.service.IBusinessImplementSmsMainManager;


public  class BusinessImplementSmsAction extends SheetAction {
   /**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDrawing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("draw");
	}
	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("pic");
	}
	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showKPI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("kpi");
	}	
	/**
	 * 新增一条任务单跳转页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showInitializeTaskPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ServicePrepareMgr 	servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");		
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));	
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));		
		String serviceId = StaticMethod.nullObject2String(request.getParameter("serviceId"));	
		String specialtyId = StaticMethod.nullObject2String(request.getParameter("specialtyId"));			
		ProfessionalServiceDirectory professionalServiceDirectory = servicePrepareMgr.getProfessionalServiceDirectorySinglePO(specialtyId);
		String serviceCnName = professionalServiceDirectory.getName();				
		String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));	
		String operateRoleId = StaticMethod.nullObject2String(request.getParameter("operateRoleId"));		
		String parentTaskId = StaticMethod.nullObject2String(request.getParameter("parentTaskId"));			
		String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));	
		String supportId = StaticMethod.nullObject2String(request.getParameter("supportId"));
		
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		BaseLink linkObject = (BaseLink) baseSheet.getLinkService().getLinkObject().getClass()
		.newInstance();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");		
		linkObject.setOperaterContact(sessionform.getContactMobile());
		linkObject.setOperateUserId(sessionform.getUserid());
		linkObject.setOperateDeptId(sessionform.getDeptid());
		linkObject.setOperateRoleId(operateRoleId);	
		linkObject.setOperateTime(StaticMethod.getLocalTime());	
		
		request.setAttribute("sheetLink", linkObject);		
		BaseMain baseMain = baseSheet.getMainService().getSingleMainPO(sheetKey);
		if(baseMain!=null){
			request.setAttribute("sheetMain", baseMain);
		}
//		request.setAttribute("operateType", operateType);
		request.setAttribute("sheetKey", sheetKey);
		request.setAttribute("taskName", taskName);
		request.setAttribute("serviceId", serviceId);
		request.setAttribute("serviceCnName", serviceCnName);		
		request.setAttribute("phaseId", phaseId);		
		request.setAttribute("parentTaskId", parentTaskId);			
		request.setAttribute("preLinkId", preLinkId);			
		request.setAttribute("supportId", supportId);			
		return mapping.findForward("InitializeTask");
	}
	/**
	 * 任务单的提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newTaskPerformAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());		
		HashMap columnMap = baseSheet.getInterfaceObjMap(mapping, form, request,
				response);// 获取main link operate对象
		Map map = request.getParameterMap();
		System.out.println("=====request Map parentPhaseName:"+StaticMethod.nullObject2String(request.getParameter("parentPhaseName"), ""));
		Map serializableMap = SheetUtils.serializableParemeterMap(map);
		Iterator it = serializableMap.keySet().iterator();
		HashMap WpsMap = new HashMap();
		while (it.hasNext()) {
			String mapKey = (String) it.next();
			Map tempMap = (Map) serializableMap.get(mapKey);
			HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
			HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
					tempColumnMap);
			WpsMap.putAll(tempWpsMap);
		}

		String processTemplateName = StaticMethod.nullObject2String(request
				.getParameter("processTemplateName"));
		String operateName = StaticMethod.nullObject2String(request
				.getParameter("operateName"));
		
		String supportId = StaticMethod.nullObject2String(request
				.getParameter("supportId"));

		baseSheet.setFlowEngineMap(WpsMap);
		baseSheet.dealFlowEngineMap(mapping, form, request, response);			
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		baseSheet.getBusinessFlowService().initProcess(processTemplateName, operateName,
				baseSheet.getFlowEngineMap(), sessionMap);
		
		String beanId = StaticMethod.null2String(request.getParameter("beanId"));
		ServicePrepareMgr 	servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");		
		ITawSystemWorkflowManager 	tawSystemWorkflowManager = (ITawSystemWorkflowManager)ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		TawSystemWorkflow tawSystemWorkflow = (TawSystemWorkflow)tawSystemWorkflowManager.getTawSystemWorkflowByBeanId(beanId);
		String  flowId= tawSystemWorkflow.getFlowId();
		HashMap sheetMap = baseSheet.getFlowEngineMap();
		Map linkMap = (HashMap)sheetMap.get("link");
		
		Map operateMap = (HashMap)sheetMap.get("operate");		
		String serviceId = StaticMethod.null2String(request.getParameter("serviceId"));
		String serviceCnName = StaticMethod.null2String(request.getParameter("serviceCnName"));	
		String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));			
		String parentTaskId = StaticMethod.null2String(request.getParameter("parentTaskId"));				
		ProcessTasks processTasks = new ProcessTasks();
		processTasks.setSheetkey(sheetKey);
		processTasks.setServicId(serviceId);
		processTasks.setServiceCnName(serviceCnName);
		processTasks.setParentTaskId(parentTaskId);
		processTasks.setProcessTypeId(flowId);
		processTasks.setStatus("2");
		processTasks.setDeleted("0");
		processTasks.setCreatetime((Date)linkMap.get("operateTime"));
		processTasks.setDealRoleId(StaticMethod.nullObject2String(operateMap.get("dealPerformer")));
		processTasks.setParentLinkId(StaticMethod.nullObject2String(linkMap.get("id")));
		servicePrepareMgr.addObject(processTasks);
		return mapping.findForward("taskSuccess");
	}
    /**
     * 预先判断时候还有未处理的任务单
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
	public void performPreJudge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jsonRoot = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject o = new JSONObject();		
		String status = "";
		StringBuffer sb = new StringBuffer();
		StringBuffer sbFn = new StringBuffer();		
		String taskId = StaticMethod.null2String(request.getParameter("aiid"));
		String taskName = StaticMethod.null2String(request.getParameter("taskName"));		
		String beanId = StaticMethod.null2String(request.getParameter("beanId"));	
		 
		String linkDealType = StaticMethod.null2String(request.getParameter("linkDealType"));
		if(!linkDealType.equals("101220402")){
			ITawSystemWorkflowManager 	tawSystemWorkflowManager = (ITawSystemWorkflowManager)ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
			TawSystemWorkflow tawSystemWorkflow = (TawSystemWorkflow)tawSystemWorkflowManager.getTawSystemWorkflowByBeanId(beanId);
			String  flowId= tawSystemWorkflow.getFlowId();		
			ServicePrepareMgr 	servicePrepareMgr = (ServicePrepareMgr)ApplicationContextHolder.getInstance().getBean("IServicePrepareMgr");		
			List processTasksList = servicePrepareMgr.getProcessTasksByParentTaskId(taskId);
			List serviceConfigurationList = servicePrepareMgr.getAllListByCondition(flowId, taskName);	
			if(processTasksList==null ||processTasksList.size()==0){
//				if(serviceConfigurationList!=null){
//					for(int i =0;i<serviceConfigurationList.size();i++){
//						Object[] tmpObjArr = (Object[]) serviceConfigurationList.get(i);
//						ServiceConfiguration serviceConfiguration = (ServiceConfiguration)tmpObjArr[0];
//						ProfessionalServiceDirectory professionalServiceDirectory = (ProfessionalServiceDirectory)tmpObjArr[1];
//						if(serviceConfiguration.getIsNeed().equals("0")){
//							sb.append("任务单："+professionalServiceDirectory.getName()+" 为必须派发任务；\n");						
//						}
//					}
//				}			
				if(sb.toString().equals("")||sb.toString()==""){
					status="0";
				}else{
					status="2";	
					sb.append("您不能提交！\n");
				}			
			}else{
				for(int i=0;i<processTasksList.size();i++){
					ProcessTasks processTasks= (ProcessTasks)processTasksList.get(i);		
					if(processTasks.getStatus().equals("2")){
						Map userNameMap = SheetUtils.getUserNameForSubRole(processTasks.getDealRoleId()); 
						sb.append("任务单："+processTasks.getServiceCnName()+" 还未处理，处理人为："+userNameMap.get("subRoleName")+"\n");
					}
				}
				if(sb.toString().equals("")||sb.toString()==""){
//					String doneServiceId = "";
//					for(int i=0;i<processTasksList.size();i++){
//						ProcessTasks processTasks= (ProcessTasks)processTasksList.get(i);		
//						if(processTasks.getStatus().equals("8")){
//							if(doneServiceId.equals("")){
//								doneServiceId = ""+processTasks.getServicId()+"";
//							}else{
//								doneServiceId = doneServiceId+","+processTasks.getServicId()+"";
//							}
//						}
//					}				
//					if(serviceConfigurationList!=null){
//						for(int i =0;i<serviceConfigurationList.size();i++){
//							Object[] tmpObjArr = (Object[]) serviceConfigurationList.get(i);
//							ServiceConfiguration serviceConfiguration = (ServiceConfiguration)tmpObjArr[0];
//							ProfessionalServiceDirectory professionalServiceDirectory = (ProfessionalServiceDirectory)tmpObjArr[1];
//							if(serviceConfiguration.getIsNeed().equals("0")&&(!doneServiceId.equals("")&&doneServiceId.indexOf(serviceConfiguration.getId())<0)){
//								sb.append("任务单："+professionalServiceDirectory.getName()+" 为必须派发任务；\n");						
//							}
//						}
//					}				
					if(sb.toString().equals("")||sb.toString()==""){
						status="0";	
					}else{
						status="2";	
						sb.append("您不能提交！\n");	
					}
	               
				}else{
					status="2";	
					sb.append("您不能提交！\n");
				}
			}
		}else{
			status="0";	
		}
		o.put("text", sb.toString());
		o.put("fn", sbFn.toString());
		data.put(o);		
		jsonRoot.put("data", data);
		jsonRoot.put("status", String.valueOf(status));
		JSONUtil.print(response, jsonRoot.toString());		
	}	
	/**
     * 校验资源接口
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
	public void performPreValidateResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jsonRoot = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject o = new JSONObject();		
		String status = "";
		StringBuffer sb = new StringBuffer();
		StringBuffer sbFn = new StringBuffer();		
		try{
			String enableService = StaticMethod.nullObject2String(XmlManage.getFile("/com/boco/eoms/businessupport/config/resourceInterface_util.xml").getProperty("base.resourceConfirmEnable"));
			if(enableService.equalsIgnoreCase("true")){	//允许调用资管接口
				
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));	
				IBusinessImplementSmsMainManager mgr = (IBusinessImplementSmsMainManager)ApplicationContextHolder.getInstance().getBean("iResourceConfirmMainManager");
				
				BusinessImplementSmsMain main = (BusinessImplementSmsMain)mgr.getMainDAO().loadSinglePO(sheetKey, new BusinessImplementSmsMain());
				String mainSpecifyType = main.getMainSpecifyType();
				
	            String orderSheetId = main.getOrderSheetId();   
	            List list = OrderSheetMethod.getSpecialLineList(orderSheetId, mainSpecifyType);
				
	            Map orderMap = SheetBeanUtils.bean2Map(main);
	            
	            String taskName = StaticMethod.null2String(request.getParameter("taskName"));
	            
	            BocoLog.info(this, "taskName:" + taskName+mainSpecifyType);
	            orderMap.put("serviceType", taskName+mainSpecifyType);
	            
	            for(int i=0;i<list.size();i++){
	            	List tempList = new ArrayList();
	            	tempList.add(list.get(i));
	            	IrmsResourceBo.preOccupyResFinish(orderMap, tempList); //资源预占校验 
	            }
			}
			else
				status="0";
		}catch(Exception e){
			e.printStackTrace();
			sb.append("资管系统校验数据失败,不能提交,请将数据填写完整："+e.getMessage());
			status="2";	
		}
		
		o.put("text", sb.toString());
		o.put("fn", sbFn.toString());
		data.put(o);		
		jsonRoot.put("data", data);
		jsonRoot.put("status", String.valueOf(status));
		JSONUtil.print(response, jsonRoot.toString());		
	}	
	
	public ActionForward showHistoryPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
	    String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetNo"), "");
	    String userid = StaticMethod.nullObject2String(request.getParameter("userName"), "");
	    String beanName = mapping.getAttribute();
	    IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
	    IBusinessImplementSmsMainManager mgr = (IBusinessImplementSmsMainManager)baseSheet.getMainService();
	    List list = mgr.getMainListByParentSheetId(sheetId);
	    if(list!=null&& list.size()>0){
			BaseMain main = (BaseMain)list.get(0);
			String sheetKey = main.getId();
	
			if (!sheetKey.equals("")) {
				if("".equals(userid))
					userid = "admin";
				try{
					IWorkflowSecutiryService safeService=
						 (IWorkflowSecutiryService)ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
						 Subject subject = safeService.logIn(userid, "");
						 request.getSession().setAttribute("wpsSubject",subject);
				}catch(Exception e){ 
					BocoLog.error(this,"保存流程登陆信息报错："+e.getMessage()); 
				}
				
				ActionForward forward=mapping.findForward("showInterfaceDraftPage");
				String path = forward.getPath() + "&sheetKey="+sheetKey;
				System.out.println("path="+path);
				return new ActionForward(path, false);
			}	
			else
				throw new Exception("sheetNo不能为空");
		}else
			throw new Exception("没找到对应工单，请确认编号正确");
	}	
	public ActionForward showTnmsPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
	    return mapping.findForward("tnms");
	} 
	
}
