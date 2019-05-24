
package com.boco.eoms.sheet.businessdredge.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSSecutiryServiceImpl;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.businessdredge.model.BusinessDredgeMain;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public  class BusinessDredgeAction extends SheetAction {
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
	public ActionForward performDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sheetAccessories = StaticMethod.nullObject2String(request.getParameter("sheetAccessories"));
		System.out.println("sheetAccessories="+sheetAccessories);
		
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performDeal(mapping, form, request, response);

		try{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			if(sheetKey.equals(""))
				System.out.println("sheetKey is null");
			else{
				BusinessDredgeMain main = (BusinessDredgeMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
				if(main.getIsCustomerFlag()!=null && main.getIsCustomerFlag().equals("1")){	//接口派单
					String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredge-crm.xml").getProperty("base.SendImmediately"));
					
					if(sendImmediately.equalsIgnoreCase("true")){
						String taskName = StaticMethod.nullObject2String(request
								.getParameter("taskName"));
						String operateType = StaticMethod.nullObject2String(request
								.getParameter("operateType"));
						String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
		
						String sType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBusinesstype1());
						int serviceType = 0;
						try{
							serviceType = Integer.valueOf(sType).intValue();
						}catch(Exception err){
							System.out.println("serviceType类型错误，serviceType="+sType);
						}
				
						String attachRef = CrmLoader.createAttachRefXml(attach);

						System.out.println("taskName="+taskName);
						System.out.println("operateType="+operateType);
						if (taskName.equals("ExcuteHumTask")
								&& operateType.equals("4")) {// 驳回
							String nodeName = "withdrawWorkSheet";
							String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(request.getParameterMap(), StaticMethod.getFilePathForUrl("classpath:config/businessDredge-crm.xml"), nodeName);
							CrmLoader.withdrawWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, serviceType,
									main.getParentCorrelation(), opDetail,attachRef);
						} else if (operateType.equals("9")) {// 阶段回复
							String nodeName = "notifyWorkSheet";
							String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(request.getParameterMap(), StaticMethod.getFilePathForUrl("classpath:config/businessDredge-crm.xml"), nodeName);
							CrmLoader.notifyWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, serviceType,
									main.getParentCorrelation(), opDetail,attachRef);
						} else if (operateType.equals("205")) {// 回复
							String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
							ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean("iBusinessDredgeTaskManager");
							ITask task = iTaskService.getSinglePO(taskId);
							
							TawSystemSessionForm sessionform = (TawSystemSessionForm) request
							.getSession().getAttribute("sessionform");
							WPSSecutiryServiceImpl safeService = new WPSSecutiryServiceImpl();						
							Subject subject = safeService.logIn(sessionform.getUserid(), "111");
							
							HashMap sessionMap = new HashMap();
							sessionMap.put("wpsSubject", subject);
							sessionMap.put("userId", sessionform.getUserid());
							sessionMap.put("password", "111");
							
							Map variableMap = baseSheet.getBusinessFlowService().getVariable(task.getProcessId(), "parentCorrKey", sessionMap);
							String parentCorrKey = StaticMethod.nullObject2String(variableMap.get("parentCorrKey"));
							
							List chNameList = new ArrayList();
							List enNameList = new ArrayList();
							List contentList = new ArrayList();
							
							
							if(!parentCorrKey.equalsIgnoreCase(main.getCorrelationKey())){//分派
								
								
								
									chNameList.add("returnType");
									enNameList.add("回复类型");
									contentList.add("1");
									
									
								
							}else{
								chNameList.add("returnType");
								enNameList.add("回复类型");
								contentList.add("0");		
								
								
							}							
							
							
						}
					}
				}
			}
		}catch(Exception err){
			System.out.println("调用crm接口失败");
			err.printStackTrace();
		}
		//如果所有的分派任务都处理回复通过，则直接转向detail页面
		String forwardStr = StaticMethod.nullObject2String(request.getAttribute("forwardStr"));
		if(forwardStr.equals("detail")){
			return mapping.findForward(forwardStr);
		}
		return mapping.findForward("success");
	}
	public ActionForward performClaimTask(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("into performClaimTask ............");
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performClaim(mapping, form, request, response);

		try{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			if(sheetKey.equals(""))
				System.out.println("sheetKey is null");
			else{
				BusinessDredgeMain main = (BusinessDredgeMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
				if(main.getIsCustomerFlag()!=null && main.getIsCustomerFlag().equals("1")){	//接口派单

					String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredge-crm.xml").getProperty("base.SendImmediately"));
					
					if(sendImmediately.equalsIgnoreCase("true")){
						String taskName = StaticMethod.nullObject2String(request
								.getParameter("taskName"));
						String operateType = StaticMethod.nullObject2String(request
								.getParameter("operateType"));
						String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
						String attachRef = CrmLoader.createAttachRefXml(attach);
		
						String sType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBusinesstype1());
	
						int serviceType = 0;
						try{
							serviceType = Integer.valueOf(sType).intValue();
						}catch(Exception err){
							System.out.println("serviceType类型错误，serviceType="+sType);
						}
						System.out.println("taskName:" + taskName);
						System.out.println("operateType:" + operateType);
						if (taskName.equals("ExcuteHumTask") && operateType.equals("61")) {// 受理
							
							String nodeName = "confirmWorkSheet";
							
									String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(request.getParameterMap(), StaticMethod.getFilePathForUrl("classpath:config/businessDredge-crm.xml"), nodeName);
									CrmLoader.confirmWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, serviceType,
											main.getParentCorrelation(), opDetail,attachRef);
								
						
						}
					}
				}
			}
		}catch(Exception err){
			System.out.println("调用crm接口失败");
			err.printStackTrace();
		}
		return mapping.findForward("detail");
	}
	public ActionForward performProcessEvent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("into performProcessEvent ............");
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performProcessEvent(mapping, form, request, response);

		try{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			if(sheetKey.equals(""))
				System.out.println("sheetKey is null");
			else{
				System.out.println("sheetKey="+sheetKey);
				BaseMain baseMain = (BaseMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
				System.out.println("baseMain:"+baseMain.getClass().getName());
				BusinessDredgeMain main = (BusinessDredgeMain)baseMain;
				System.out.println("MainInterfaceSheetType="+main.getIsCustomerFlag());
				if(main.getIsCustomerFlag()!=null && main.getIsCustomerFlag().equals("1")){	//接口派单
					
					String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredge-crm.xml").getProperty("base.SendImmediately"));
					
					if(sendImmediately.equalsIgnoreCase("true")){
						System.out.println("MainInterfaceSheetType="+main.getIsCustomerFlag());
						String taskName = StaticMethod.nullObject2String(request
								.getParameter("taskName"));
						String operateType = StaticMethod.nullObject2String(request
								.getParameter("operateType"));
						String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
						String attachRef = CrmLoader.createAttachRefXml(attach);
		
						String sType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBusinesstype1());
						
						int serviceType = 0;
						try{
							serviceType = Integer.valueOf(sType).intValue();
						}catch(Exception err){
							System.out.println("serviceType类型错误，serviceType="+sType);
						}
						System.out.println("taskName:" + taskName);
						System.out.println("operateType:" + operateType);
						if (operateType.equals("9")) {// 阶段回复
							String nodeName = "notifyWorkSheet";
							String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(request.getParameterMap(), StaticMethod.getFilePathForUrl("classpath:config/businessDredge-crm.xml"), nodeName);
							CrmLoader.notifyWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, serviceType,
									main.getParentCorrelation(), opDetail,attachRef);
						}				
					}
				}
			}
		}catch(Exception err){
			System.out.println("调用crm接口失败");
			err.printStackTrace();
		}
		return mapping.findForward("success");
	}
	public ActionForward getMainInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String piid = "";
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		
		return mapping.findForward("success");
	} 
}
