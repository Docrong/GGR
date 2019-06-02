
package com.boco.eoms.sheet.businessdredgebroad.webapp.action;

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
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.model.CrmWaitInfo;
import com.boco.eoms.crm.service.ICrmWaitInfoManager;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSSecutiryServiceImpl;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.businessdredgebroad.model.BusinessDredgebroadMain;
import com.boco.eoms.sheet.businessdredgebroad.qo.impl.BusinessDredgebroadQoImpl;
import com.boco.eoms.sheet.businessdredgebroad.service.IBusinessDredgebroadMainManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
//import com.boco.local.dao.impl.LocalBusinessDredgebroadDAO;

public  class BusinessDredgebroadAction extends SheetAction {
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
				BusinessDredgebroadMain main = (BusinessDredgebroadMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
				if(main.getIsCustomerFlag()!=null && main.getIsCustomerFlag().equals("1")){	//接口派单
					String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.SendImmediately"));
					
					if(sendImmediately.equalsIgnoreCase("true")){
						String taskName = StaticMethod.nullObject2String(request
								.getParameter("taskName"));
						String operateType = StaticMethod.nullObject2String(request
								.getParameter("operateType"));
						String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
		
						String serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBusinesstype1());
						
						ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager)getBean("ICrmWaitInfoManager");
						CrmWaitInfo info = new CrmWaitInfo();
						info.setSheetType(new Integer(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE));
						info.setSerialNo(main.getParentCorrelation());
						info.setAttachRef(attach);
						try{
							info.setServiceType(Integer.valueOf(serviceType));
						}catch(Exception err){
							System.out.println("serviceType类型错误，serviceType="+serviceType);
						}
				
						System.out.println("taskName="+taskName);
						System.out.println("operateType="+operateType);
						if (taskName.equals("ExcuteHumTask")
								&& operateType.equals("4")) {// 驳回
							dealwithdrawWorkSheet(request, info);
							infoManager.saveOrUpdateCrmWaitInfo(info);
						} else if (operateType.equals("9")) {// 阶段回复
							dealconnotifyWorkSheet(request, info);
							infoManager.saveOrUpdateCrmWaitInfo(info);
						} else if (operateType.equals("205")) {// 回复
							String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
							ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean("iBusinessDredgebroadTaskManager");
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
							if(!parentCorrKey.equalsIgnoreCase(main.getCorrelationKey())){//分派
								info.setReturnType("1");
							}else{
								info.setReturnType("0");							
							}							
							dealreplyWorkSheet(request, info);
							infoManager.saveOrUpdateCrmWaitInfo(info);
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
				BusinessDredgebroadMain main = (BusinessDredgebroadMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
				if(main.getIsCustomerFlag()!=null && main.getIsCustomerFlag().equals("1")){	//接口派单

					String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.SendImmediately"));
					
					if(sendImmediately.equalsIgnoreCase("true")){
						String taskName = StaticMethod.nullObject2String(request
								.getParameter("taskName"));
						String operateType = StaticMethod.nullObject2String(request
								.getParameter("operateType"));
						String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
		
						String serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBusinesstype1());
						ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager)getBean("ICrmWaitInfoManager");
						CrmWaitInfo info = new CrmWaitInfo();
						info.setSheetType(new Integer(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE));
						info.setSerialNo(main.getParentCorrelation());
						info.setAttachRef(attach);
						try{
							info.setServiceType(Integer.valueOf(serviceType));
						}catch(Exception err){
							System.out.println("serviceType类型错误，serviceType="+serviceType);
						}
						System.out.println("taskName:" + taskName);
						System.out.println("operateType:" + operateType);
						if (taskName.equals("ExcuteHumTask") && operateType.equals("61")) {// 受理
							dealconfirmWorkSheet(info);
							infoManager.saveOrUpdateCrmWaitInfo(info);
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
				BusinessDredgebroadMain main = (BusinessDredgebroadMain)baseMain;
				System.out.println("MainInterfaceSheetType="+main.getIsCustomerFlag());
				if(main.getIsCustomerFlag()!=null && main.getIsCustomerFlag().equals("1")){	//接口派单
					
					String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.SendImmediately"));
					
					if(sendImmediately.equalsIgnoreCase("true")){
						System.out.println("MainInterfaceSheetType="+main.getIsCustomerFlag());
						String taskName = StaticMethod.nullObject2String(request
								.getParameter("taskName"));
						String operateType = StaticMethod.nullObject2String(request
								.getParameter("operateType"));
						String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
		
						String serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBusinesstype1());
						ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager)getBean("ICrmWaitInfoManager");
						CrmWaitInfo info = new CrmWaitInfo();
						info.setSheetType(new Integer(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE));
						info.setSerialNo(main.getParentCorrelation());
						info.setAttachRef(attach);
						try{
							info.setServiceType(Integer.valueOf(serviceType));
						}catch(Exception err){
							System.out.println("serviceType类型错误，serviceType="+serviceType);
						}
						System.out.println("taskName:" + taskName);
						System.out.println("operateType:" + operateType);
						if (operateType.equals("9")) {// 阶段回复
							dealconnotifyWorkSheet(request, info);
							infoManager.saveOrUpdateCrmWaitInfo(info);
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
	/**
	 * 受理
	 * 
	 * @param request
	 * @param info
	 */
	private void dealconfirmWorkSheet(	CrmWaitInfo info) {
		info.setInterfaceType("confirmWorkSheet");
		info.setOpType("受理");
		info.setOpDesc("");
	}

	/**
	 * 驳回
	 * 
	 * @param request
	 * @param info
	 */
	private void dealwithdrawWorkSheet(HttpServletRequest request,
			CrmWaitInfo info) {
		info.setInterfaceType("withdrawWorkSheet");
		info.setOpType("驳回");
		String dealDesc = StaticMethod.nullObject2String(request
				.getParameter("rejectReason"));
		info.setOpDesc(dealDesc);
	}

	/**
	 * 阶段回复
	 * 
	 * @param request
	 * @param info
	 */
	private void dealconnotifyWorkSheet(HttpServletRequest request,
			CrmWaitInfo info) {
		info.setInterfaceType("notifyWorkSheet");
		info.setOpType("阶段回复");
		String opDesc = StaticMethod.nullObject2String(request
				.getParameter("remark"));
		info.setOpDesc(opDesc);
	}

	/**
	 * 回复
	 * 
	 * @param request
	 * @param info
	 */
	private void dealreplyWorkSheet(HttpServletRequest request, CrmWaitInfo info) {
		int serviceType = info.getServiceType().intValue();
		info.setInterfaceType("replyWorkSheet");
		info.setOpType("回复");
		String ndeptContact = StaticMethod.nullObject2String(request
				.getParameter("ndeptContact"));
		String ndeptContactPhone = StaticMethod.nullObject2String(request
				.getParameter("ndeptContactPhone"));
		String dealDesc = StaticMethod.nullObject2String(request
				.getParameter("dealDesc"));
		String dealResult = StaticMethod.nullObject2String(request
				.getParameter("dealResult"));
		info.setNdeptContact(ndeptContact);
		info.setNdeptContactPhone(ndeptContactPhone);
		info.setDealDesc(dealDesc);
		info.setDealResult(dealResult);

		if (serviceType == 1) {// GPRS
			// TODO apnid字段页面未提供
			String apnid = StaticMethod.nullObject2String(request
					.getParameter("apnid"));
			info.setApnid(apnid);
		} else if (serviceType == 2) {// 彩信
			String loginUserName = StaticMethod.nullObject2String(request
					.getParameter("loginUserName"));
			String loginUserPassWord = StaticMethod.nullObject2String(request
					.getParameter("loginUserPassWord"));
			info.setLoginUserName(loginUserName);
			info.setLoginUserPassWord(loginUserPassWord);
		} else if (serviceType == 3) {// 短信
			String loginUserName = StaticMethod.nullObject2String(request
					.getParameter("loginUserName"));
			String loginUserPassWord = StaticMethod.nullObject2String(request
					.getParameter("loginUserPassWord"));
			info.setLoginUserName(loginUserName);
			info.setLoginUserPassWord(loginUserPassWord);
		} else if (serviceType == 4) {// 传输专线
			String netResCapacity = StaticMethod.nullObject2String(request
					.getParameter("netResCapacity"));
			String clientPgmCapacity = StaticMethod.nullObject2String(request
					.getParameter("clientPgmCapacity"));
			String expectFinishdays = StaticMethod.nullObject2String(request
					.getParameter("expectFinishdays"));
			String circuitCode = StaticMethod.nullObject2String(request
					.getParameter("circuitCode"));
			info.setNetResCapacity(netResCapacity);
			info.setClientPgmCapacity(clientPgmCapacity);
			info.setExpectFinishdays(expectFinishdays);
			info.setCircuitCode(circuitCode);
		}
	}
	
	public ActionForward performQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		String ifExcel = request.getParameter("ifExcel");
		Map actionMap = request.getParameterMap();
		List listExcel = null;
//add by qinbo begin on 20100729 
		String operatetype = StaticMethod.nullObject2String(request.getParameter("operatetype"));
		if(operatetype.equals("205")){
//			是否导出excel
//			if("1".equals(ifExcel)){
//				com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
//				LocalBusinessDredgebroadDAO businessDredgebroadDAO = new LocalBusinessDredgebroadDAO(ds);
//				BusinessDredgebroadQoImpl  businessDredgebroadQo = (BusinessDredgebroadQoImpl)ApplicationContextHolder.getInstance().getBean("iBusinessDredgebroadQo");
//				String sql = businessDredgebroadQo.getClauseSql(actionMap);
//				String where = sql.substring(sql.indexOf("where") + 5);
//				System.out.println(request.getParameter("businesstype1ChoiceExpression"));
//				String businesstype1 = request.getParameter("businesstype1ChoiceExpression") ;
//				if("101100109".equals(businesstype1)){
//					listExcel = businessDredgebroadDAO.QueryBusinessDredgebroadForExcel2(where);
//					request.setAttribute("listExcel", listExcel);
//					return mapping.findForward("querylistexcel2");
//				}else{
//					listExcel = businessDredgebroadDAO.QueryBusinessDredgebroad2(where);
//					request.setAttribute("listExcel", listExcel);
//					return mapping.findForward("querylistexcel");
//				}
//			}
			
			baseSheet.performQuery(mapping, form, request, response);
			return mapping.findForward("querylist");
		}
//add by qinbo end on 20100729 
		//是否导出excel
//		if("1".equals(ifExcel)){
//			com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
//			LocalBusinessDredgebroadDAO businessDredgebroadDAO = new LocalBusinessDredgebroadDAO(ds);
//			
//			BusinessDredgebroadQoImpl  businessDredgebroadQo = (BusinessDredgebroadQoImpl)ApplicationContextHolder.getInstance().getBean("iBusinessDredgebroadQo");
//			String sql = businessDredgebroadQo.getClauseSql(actionMap);
//			String where = sql.substring(sql.indexOf("where") + 5);
////add by qinbo begin on 20100721  businesstype1  101100109
//			System.out.println(request.getParameter("businesstype1ChoiceExpression"));
//			String businesstype1 = request.getParameter("businesstype1ChoiceExpression") ;
//			if("101100109".equals(businesstype1)){
//				listExcel = businessDredgebroadDAO.QueryBusinessDredgebroadForExcel(where);
//				request.setAttribute("listExcel", listExcel);
//				return mapping.findForward("querylistexcel2");
//			}else{
//				listExcel = businessDredgebroadDAO.QueryBusinessDredgebroad(where);
//				request.setAttribute("listExcel", listExcel);
//				return mapping.findForward("querylistexcel");
//			}
////add by qinbo end on 20100721
//		}
		
		baseSheet.performQuery(mapping, form, request, response);
		return mapping.findForward("querylist");
	} 
	
	/**
	 * 工单的历史页面，查询接口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showHistoryPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		String crmSheetId = StaticMethod.nullObject2String(request
				.getParameter("sheetNo"), "");
		String userid = StaticMethod.nullObject2String(request
				.getParameter("userName"), "");
		
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		IBusinessDredgebroadMainManager mgr = (IBusinessDredgebroadMainManager)baseSheet.getMainService();
		List list = mgr.getMainListByParentSheetId(crmSheetId);	
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

//add by qinbo begin on 20100701
	/**
	 * main工单的"施工单"跳转后信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showMainPrintInfoPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.showMainDetailPage(mapping, form, request, response);
		String forwardstr = "printinfo";
		if(request.getAttribute("distributeForward")!=null&&!request.getAttribute("distributeForward").equals("")){
			forwardstr = (String)request.getAttribute("distributeForward");
		}
		return mapping.findForward(forwardstr);
	}
//add by qinbo end on 20100701

}
