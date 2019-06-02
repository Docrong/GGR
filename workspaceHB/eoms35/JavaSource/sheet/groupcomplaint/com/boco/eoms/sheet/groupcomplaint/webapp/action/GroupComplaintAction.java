/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.groupcomplaint.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.crm.model.CrmWaitInfo;
import com.boco.eoms.crm.service.ICrmWaitInfoManager;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.groupcomplaint.knowledage.GroupComplaintKnowledgeBO;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintMainManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupComplaintAction extends SheetAction {

    /**
	 * 显示草图
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
	 * 显示流程VISO图
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
	 * 显示KPI
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
	 * 根据专业，查询时限
	 * @author wangjianhua
	 * @date 2008-08-02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showLimit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 String specialty1 = StaticMethod.null2String(request.getParameter("faultSpecialty"));
	     String specialty2 = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
	     String specialty3 = StaticMethod.null2String(request.getParameter("faultSpecialty3"));
	     String specialty4 = StaticMethod.null2String(request.getParameter("faultSpecialty4"));
		 ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		 SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty1,specialty2,specialty3,specialty4);
		 JSONObject jsonRoot = new JSONObject();
		 jsonRoot = JSONObject.fromObject(sheetLimit);
		 JSONUtil.print(response, jsonRoot.toString());
	}
	
	/**
	 * 根据专业，查询时限
	 * @author wangjianhua
	 * @date 2008-08-02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showDealLimit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	     String specialty = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
		 ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		 SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty);
		 JSONObject jsonRoot = new JSONObject();
		 jsonRoot = JSONObject.fromObject(sheetLimit);
		 JSONUtil.print(response, jsonRoot.toString());
	}
	
	/**
	 * 获取新增知识库的url
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void createKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
        
        String url = GroupComplaintKnowledgeBO.showNewknowLedage(sheetKey,sessionform.getUserid());
        System.out.println("url:" + url);

        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(url);
    }

	/**
	 * 工单的草稿页面，告警接口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showInterfaceDraftPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetNo"), "");


		if (!sheetKey.equals("")) {
			String operateUser = XmlManage.getFile("/config/groupcomplaint-util.xml").getProperty("InterfaceUser");
			
			ITaskService taskService = (ITaskService)getBean("iGroupComplaintTaskManager");
			ITask task = taskService.getTask(sheetKey, "DraftHumTask");
			
			request.setAttribute("sheetKey", sheetKey);
			request.setAttribute("taskId", task.getId());
			request.setAttribute("taskName", "DraftHumTask");
			request.setAttribute("piid", task.getProcessId());
			request.setAttribute("operateRoleId", task.getOperateRoleId());
			request.setAttribute("taskStatus", task.getTaskStatus());
			request.setAttribute("preLinkId", task.getPreLinkId());
			request.setAttribute("TKID", task.getId());
			
			ActionForward forward=mapping.findForward("showInterfaceDraftPage");
			String path = forward.getPath() + "&sheetKey="+sheetKey+"&taskId="+task.getId()+"&taskName=DraftHumTask&piid="
				+task.getProcessId()+"&operateRoleId="+task.getOperateRoleId()+"&taskStatus="+task.getTaskStatus()+"&preLinkId="+task.getPreLinkId()
				+"&TKID="+task.getId()+"&userId="+operateUser+"&type=interface";
			System.out.println("path="+path);
			return new ActionForward(path, false);
		}	
		else
			throw new Exception("sheetNo不能为空");

	}
	
	/**
	 * 显示所有待质检（后质检）的已归档工单
	 * @author yyk
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showListUndoForCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		IGroupComplaintMainManager complaintMainManager = (IGroupComplaintMainManager)baseSheet.getMainService();
		
		// modified by 
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		// 分页取得列表
		Integer total = complaintMainManager.getCountUndoForCheck();
		// wps端分页取得列表

		List result = complaintMainManager.getListUndoForCheck(pageIndex, new Integer(pageSize.intValue()));

		request.setAttribute("taskList", result);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);		
		
		return mapping.findForward("backchecklist");
	}	

	
	/**
	 * 显示所有已质检（后质检）的已归档工单
	 * @author yyk
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showListDoneForCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		IGroupComplaintMainManager complaintMainManager = (IGroupComplaintMainManager)baseSheet.getMainService();
		
		// modified by 
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		// 分页取得列表
		Integer total = complaintMainManager.getCountDoneForCheck();
		// wps端分页取得列表

		List result = complaintMainManager.getListDoneForCheck(pageIndex, new Integer(pageSize.intValue()));

		request.setAttribute("taskList", result);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);		
		
		return mapping.findForward("backcheckedlist");
	}		
	
	/**
	 * 显示事后质检处理页面
	 * @author yyk
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showBackCheckDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		
		request.setAttribute("mainid", sheetKey);
		return mapping.findForward("backcheckdealpage");
	}	
	
	/**
	 * @see 事后质检保存
	 * @author yyk
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doBackCheckSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		IGroupComplaintMainManager complaintMainManager = (IGroupComplaintMainManager)baseSheet.getMainService();		
		String mainId = StaticMethod.nullObject2String(request.getParameter("mainid"));
		String mainCheckIdea = StaticMethod.nullObject2String(request.getParameter("mainCheckIdea"));
		String mainCheckResult = StaticMethod.nullObject2String(request.getParameter("mainCheckResult"));		
		if (!mainId.equals("")) {
			System.out.println("事后质检保存开始===mainId=="+mainId);
			GroupComplaintMain mainObject = (GroupComplaintMain)complaintMainManager.loadSinglePO(mainId);			
			mainObject.setMainCheckIdea(mainCheckIdea);
			mainObject.setMainCheckResult(mainCheckResult);	
			mainObject.setMainIfCheck("2");
			complaintMainManager.saveOrUpdateMain(mainObject);
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward performDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("into performDeal ............");
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performDeal(mapping, form, request, response);

		try{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			if(sheetKey.equals(""))
				System.out.println("sheetKey is null");
			else{
				GroupComplaintMain main = (GroupComplaintMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
				if(main.getMainInterfaceSheetType()!=null && main.getMainInterfaceSheetType().equals("crm")){	//crm接口派单
					String taskName = StaticMethod.nullObject2String(request
							.getParameter("taskName"));
					String operateType = StaticMethod.nullObject2String(request
							.getParameter("operateType"));
					String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));

					String serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBservType());
					String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.SendImmediately"));
					if (sendImmediately.equalsIgnoreCase("true"))
					{
						ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager)ApplicationContextHolder.getInstance().getBean("ICrmWaitInfoManager");
						CrmWaitInfo info = new CrmWaitInfo();
						info.setSheetType(new Integer(57));
						info.setSerialNo(main.getParentCorrelation());
						info.setAttachRef(attach);
						try
						{
							info.setServiceType(Integer.valueOf(serviceType));
						}
						catch (Exception err)
						{
							System.out.println("serviceType类型错误，serviceType=" + serviceType);
						}

						System.out.println("taskName:" + taskName);
						System.out.println("operateType:" + operateType);
						
						String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
						ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
						ITask task = iTaskService.getSinglePO(taskId);
						
						if (operateType.equals("4")) {// 驳回
							String dealPerformer = StaticMethod.nullObject2String(request
									.getParameter("dealPerformer"));
							String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.InterfaceUser"));
							String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.SendRoleId"));
							System.out.println("集团投诉驳回:dealPerformer="+dealPerformer);
							if(dealPerformer.equals(userId)||dealPerformer.equals(sendRoleId)){
								dealwithdrawWorkSheet(request, info);
								infoManager.saveOrUpdateCrmWaitInfo(info);
							}
						} else if (operateType.equals("9")) {// 阶段回复
							dealconnotifyWorkSheet(request, info, null);
							infoManager.saveOrUpdateCrmWaitInfo(info);
						} else if (operateType.equals("46")) {//处理完成
//							if(main.getSheetCompleteLimit()!=null){
//								Date d = main.getSheetCompleteLimit();
//								Date currentDate = new Date();
//													
//								System.out.println("SheetCompleteLimit="+d);
//								long l = (d.getTime()-currentDate.getTime())/1000/60;
//								System.out.println("time="+l);
//								String limit = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.zhijianLimit"));
//								System.out.println("limit="+limit);
//								if(limit.length()>0){
//									if(l<0||l<=Integer.parseInt(limit)){	//不进质检，调用回复接口
//										if(task!=null&&task.getSubTaskFlag()!=null){//是子任务
//											info.setReturnType("1");
//										}else{
//											info.setReturnType("0");							
//										}	
//										dealreplyWorkSheet(request, info);
//										infoManager.saveOrUpdateCrmWaitInfo(info);
//									}
//								}	
//							}
						}else if (operateType.equals("56")) {//质检通过，调用回复接口
							
							if(task!=null&&task.getSubTaskFlag()!=null){//是子任务
								info.setReturnType("1");
							}else{
								info.setReturnType("0");
							}
							dealreplyWorkSheet(request, info);
							infoManager.saveOrUpdateCrmWaitInfo(info);	
						}else if(taskName.equalsIgnoreCase("DeferExamineHumTask") && operateType.equalsIgnoreCase("66")){
							if(StaticMethod.nullObject2String(request.getParameter("mainDelayFlag")).equals("1")){
								dealconnotifyWorkSheet(request, info, "需要延期解决");
								infoManager.saveOrUpdateCrmWaitInfo(info);
							}
							
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
				System.out.println("sheetKey="+sheetKey);
				GroupComplaintMain main = (GroupComplaintMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
				System.out.println("MainInterfaceSheetType="+main.getMainInterfaceSheetType());
				if(main.getMainInterfaceSheetType()!=null && main.getMainInterfaceSheetType().equals("crm")){	//crm接口派单
					System.out.println("MainInterfaceSheetType="+main.getMainInterfaceSheetType());
					String taskName = StaticMethod.nullObject2String(request
							.getParameter("taskName"));
					String operateType = StaticMethod.nullObject2String(request
							.getParameter("operateType"));
					String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
					
					String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.SendImmediately"));
					
					if(sendImmediately.equalsIgnoreCase("true")){
						String serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBservType());
						ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager)getBean("ICrmWaitInfoManager");
						CrmWaitInfo info = new CrmWaitInfo();
						info.setSheetType(new Integer(57));
						info.setSerialNo(main.getParentCorrelation());
						info.setAttachRef(attach);
						try
						{
							info.setServiceType(Integer.valueOf(serviceType));
						}
						catch (Exception err)
						{
							System.out.println("serviceType类型错误，serviceType=" + serviceType);
						}

						System.out.println("taskName:" + taskName);
						System.out.println("operateType:" + operateType);
						if (taskName.equals("FirstExcuteHumTask") && operateType.equals("61")) {// 受理
							String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
							ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
							ITask task = iTaskService.getSinglePO(taskId);
							if(task!=null&&!StaticMethod.nullObject2String(task.getSubTaskFlag()).equalsIgnoreCase("true")){//不是子任务
								dealconfirmWorkSheet(info);
								infoManager.saveOrUpdateCrmWaitInfo(info);
							}
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
				GroupComplaintMain main = (GroupComplaintMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
				System.out.println("MainInterfaceSheetType="+main.getMainInterfaceSheetType());
				if(main.getMainInterfaceSheetType()!=null && main.getMainInterfaceSheetType().equals("crm")){	//crm接口派单
					System.out.println("MainInterfaceSheetType="+main.getMainInterfaceSheetType());
					
					String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.SendImmediately"));
					if(sendImmediately.equalsIgnoreCase("true")){
						String taskName = StaticMethod.nullObject2String(request
								.getParameter("taskName"));
						String operateType = StaticMethod.nullObject2String(request
								.getParameter("operateType"));
						String attach = StaticMethod.nullObject2String(request.getParameter("nodeAccessories"));
						String serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", main.getBservType());
						ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager)getBean("ICrmWaitInfoManager");
						CrmWaitInfo info = new CrmWaitInfo();
						info.setSheetType(new Integer(57));
						info.setSerialNo(main.getParentCorrelation());
						info.setAttachRef(attach);
						try
						{
							info.setServiceType(Integer.valueOf(serviceType));
						}
						catch (Exception err)
						{
							System.out.println("serviceType类型错误，serviceType=" + serviceType);
						}

						System.out.println("taskName:" + taskName);
						System.out.println("operateType:" + operateType);
						if (operateType.equals("9")) {// 阶段回复
							dealconnotifyWorkSheet(request, info, null);
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
	private void dealconfirmWorkSheet(CrmWaitInfo info)
	{
		System.out.println("into confirmWorkSheet ............");
		info.setInterfaceType("confirmWorkSheet");
		info.setOpType("受理");
		info.setOpDesc("");
	}

	private void dealwithdrawWorkSheet(HttpServletRequest request, CrmWaitInfo info)
	{
		System.out.println("into withdrawWorkSheet ............");
		info.setInterfaceType("withdrawWorkSheet");
		info.setOpType("驳回");
		String dealDesc = StaticMethod.nullObject2String(request.getParameter("remark"));
		info.setOpDesc(dealDesc);
	}

	private void dealconnotifyWorkSheet(HttpServletRequest request, CrmWaitInfo info, String isDeferReploy)
	{
		System.out.println("into notifyWorkSheet ............");
		info.setInterfaceType("notifyWorkSheet");
		info.setOpType("阶段回复");
		String dealDesc = "";
		if (isDeferReploy != null && isDeferReploy.length() > 0)
		{
			dealDesc = isDeferReploy;
			info.setIsDeferReploy("是");
		} else
		{
			dealDesc = StaticMethod.nullObject2String(request.getParameter("remark"));
		}
		info.setPhaseReployDesc(dealDesc);
		info.setIsDeferReploy("");
	}

	private void dealreplyWorkSheet(HttpServletRequest request, CrmWaitInfo info)
	{
		System.out.println("into replyWorkSheet ............");
		info.setInterfaceType("replyWorkSheet");
		info.setOpType("回复");
		String ndeptContact = StaticMethod.nullObject2String(request.getParameter("ndeptContact"));
		String ndeptContactPhone = StaticMethod.nullObject2String(request.getParameter("ndeptContactPhone"));
		String compProp = StaticMethod.nullObject2String(request.getParameter("compProp"));
		String isReplied = StaticMethod.nullObject2String(request.getParameter("isReplied"));
		String isCorrect = StaticMethod.nullObject2String(request.getParameter("isCorrect"));
		String affectedAreas = StaticMethod.nullObject2String(request.getParameter("affectedAreas"));
		String issueEliminatTime = StaticMethod.nullObject2String(request.getParameter("issueEliminatTime"));
		String issueEliminatReason = StaticMethod.nullObject2String(request.getParameter("issueEliminatReason"));
		String dealDesc = StaticMethod.nullObject2String(request.getParameter("dealDesc"));
		String dealResult = StaticMethod.nullObject2String(request.getParameter("dealResult"));
		try
		{
			System.out.println("dealResult" + dealResult);
			dealResult = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("dealResult", dealResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("转换dealResult失败");
		}
		System.out.println("dealResult" + dealResult);
		try
		{
			System.out.println("compProp" + compProp);
			compProp = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("compProp", compProp);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("转换compProp失败");
		}
		System.out.println("compProp" + compProp);
		try
		{
			System.out.println("isReplied" + isReplied);
			isReplied = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("isReplied", isReplied);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("转换isReplied失败");
		}
		System.out.println("isReplied" + isReplied);
		try
		{
			System.out.println("isCorrect" + isCorrect);
			isCorrect = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("isCorrect", isCorrect);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("转换isCorrect失败");
		}
		System.out.println("isCorrect" + isCorrect);
		try
		{
			System.out.println("affectedAreas" + affectedAreas);
			affectedAreas = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("affectedAreas", affectedAreas);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("转换affectedAreas失败");
		}
		System.out.println("affectedAreas" + affectedAreas);
		info.setNdeptContact(ndeptContact);
		info.setNdeptContactPhone(ndeptContactPhone);
		info.setCompProp(compProp);
		try
		{
			System.out.println("isReplied=" + isReplied);
			System.out.println("isCorrect=" + isCorrect);
			System.out.println("issueEliminatTime=" + issueEliminatTime);
			info.setIsReplied(isReplied);
			info.setIsCorrect(isCorrect);
			info.setIssueEliminatTime(issueEliminatTime);
			info.setAffectedAreas(affectedAreas);
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
		info.setIssueEliminatReason(issueEliminatReason);
		info.setDealDesc(dealDesc);
		info.setDealResult(dealResult);
	}
}
