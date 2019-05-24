// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultAction.java

package com.boco.eoms.sheet.commonfault.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.*;
import com.boco.eoms.sheet.base.util.flowdefine.xml.*;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.commonfault.knowledage.CommonfaultKnowLedgeBO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.*;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.*;
import org.displaytag.util.ParamEncoder;

public class CommonFaultAction21 extends SheetAction
{

	public CommonFaultAction21()
	{
	}

	public ActionForward showDrawing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return mapping.findForward("draw");
	}

	public ActionForward showPic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return mapping.findForward("pic");
	}

	public ActionForward showKPI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return mapping.findForward("kpi");
	}

	public ActionForward showInvokeRelationShipList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String mainId = StaticMethod.null2String(request.getParameter("id"));
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager)baseSheet.getMainService();
		List showInvokeRelationShipList = commonFaultMainManager.showInvokeRelationShipList(mainId);
		BaseLink baseLink = commonFaultMainManager.getHasInvokeBaseLink(mainId);
		String activeTemplateId = baseLink == null ? "" : baseLink.getActiveTemplateId();
		request.setAttribute("proccessName", "故障处理工单");
		request.setAttribute("invokedproccessName", "紧急故障工单");
		request.setAttribute("showInvokeRelationShipList", showInvokeRelationShipList);
		request.setAttribute("activeTemplateId", activeTemplateId);
		return mapping.findForward("showInvokeRelationShipList");
	}

	public void showLimit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String specialty1 = StaticMethod.null2String(request.getParameter("faultSpecialty"));
		String specialty2 = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
		String specialty3 = StaticMethod.null2String(request.getParameter("faultSpecialty3"));
		String specialty4 = StaticMethod.null2String(request.getParameter("faultSpecialty4"));
		ISheetLimitManager mgr = (ISheetLimitManager)getBean("IsheetLimitManager");
		com.boco.eoms.sheet.tool.limit.model.SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty1, specialty2, specialty3, specialty4);
		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(sheetLimit);
		JSONUtil.print(response, jsonRoot.toString());
	}

	public void showDealLimit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String specialty = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
		ISheetLimitManager mgr = (ISheetLimitManager)getBean("IsheetLimitManager");
		com.boco.eoms.sheet.tool.limit.model.SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty);
		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(sheetLimit);
		JSONUtil.print(response, jsonRoot.toString());
	}

	public void createKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		String url = CommonfaultKnowLedgeBO.showNewknowLedage(sheetKey, sessionform.getUserid());
		System.out.println("url:" + url);
		response.setContentType("text/xml; charset=UTF-8");
		response.getWriter().print(url);
	}

	public void createLeaveKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		String url = CommonfaultKnowLedgeBO.showNewLeaveKnowLedage(sheetKey, sessionform.getUserid());
		System.out.println("url:" + url);
		response.setContentType("text/xml; charset=UTF-8");
		response.getWriter().print(url);
	}

	public void getSearchKnowledgeResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		String url = CommonfaultKnowLedgeBO.searchSheet(sheetKey, sessionform.getUserid());
		System.out.println("知识库检索结果:" + url);
		response.setContentType("text/xml; charset=UTF-8");
		response.getWriter().print(url);
	}

	public ActionForward showInterfaceDraftPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetNo"), "");
		String userid = StaticMethod.nullObject2String(request.getParameter("userName"), "");
		System.out.println("sheetNo=" + sheetId);
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager)baseSheet.getMainService();
		BaseMain main = commonFaultMainManager.getMainBySheetId(sheetId);
		if (main == null)
			throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
		String sheetKey = main.getId();
		if (!sheetKey.equals(""))
		{
			if ("".equals(userid))
				userid = "admin";
			try
			{
				IWorkflowSecutiryService safeService = (IWorkflowSecutiryService)ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
				javax.security.auth.Subject subject = safeService.logIn(userid, "");
				request.getSession().setAttribute("wpsSubject", subject);
			}
			catch (Exception e)
			{
				BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
			}
			String operateUser = XmlManage.getFile("/config/commonfault-util.xml").getProperty("InterfaceUser");
			ITaskService taskService = (ITaskService)getBean("iCommonFaultTaskManager");
			ITask task = taskService.getTask(sheetKey, "DraftHumTask");
			if (task == null)
			{
				throw new Exception("没找到sheetNo＝" + sheetId + "的草稿任务");
			} else
			{
				request.setAttribute("sheetKey", sheetKey);
				request.setAttribute("taskId", task.getId());
				request.setAttribute("taskName", "DraftHumTask");
				request.setAttribute("piid", task.getProcessId());
				request.setAttribute("operateRoleId", task.getOperateRoleId());
				request.setAttribute("taskStatus", task.getTaskStatus());
				request.setAttribute("preLinkId", task.getPreLinkId());
				request.setAttribute("TKID", task.getId());
				ActionForward forward = mapping.findForward("showInterfaceDraftPage");
				String path = forward.getPath() + "&sheetKey=" + sheetKey + "&taskId=" + task.getId() + "&taskName=DraftHumTask&piid=" + task.getProcessId() + "&operateRoleId=" + task.getOperateRoleId() + "&taskStatus=" + task.getTaskStatus() + "&preLinkId=" + task.getPreLinkId() + "&TKID=" + task.getId() + "&userId=" + operateUser + "&type=interface" + "&list=act";
				System.out.println("path=" + path);
				return new ActionForward(path, false);
			}
		} else
		{
			throw new Exception("sheetNo不能为空");
		}
	}

	public ActionForward showHistoryPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetNo"), "");
		String userid = StaticMethod.nullObject2String(request.getParameter("userName"), "");
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager)baseSheet.getMainService();
		BaseMain main = commonFaultMainManager.getMainBySheetId(sheetId);
		String sheetKey = main.getId();
		if (!sheetKey.equals(""))
		{
			if ("".equals(userid))
				userid = "admin";
			try
			{
				IWorkflowSecutiryService safeService = (IWorkflowSecutiryService)ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
				javax.security.auth.Subject subject = safeService.logIn(userid, "");
				request.getSession().setAttribute("wpsSubject", subject);
			}
			catch (Exception e)
			{
				BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
			}
			ActionForward forward = mapping.findForward("showInterfaceDraftPage");
			String path = forward.getPath() + "&sheetKey=" + sheetKey;
			System.out.println("path=" + path);
			return new ActionForward(path, false);
		} else
		{
			throw new Exception("sheetNo不能为空");
		}
	}

	public ActionForward performDeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("SendImmediately"));
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		HashMap sheetMap = baseSheet.getFlowEngineMap(); 
		baseSheet.performDeal(mapping, form, request, response);
		String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
		String enableService = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("EnableService"));
		try {
			if ("true".equalsIgnoreCase(enableService) && sendImmediately.equalsIgnoreCase("true"))
			{
				String status = "";
				String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
				System.out.println("调用告警同步接口 taskName=" + taskName);
				System.out.println("调用告警同步接口 phaseId=" + phaseId);
				if (phaseId.equals("FirstExcuteTask"))
					status = "待受理";
				else
				if (phaseId.equals("HoldTask"))
					status = "处理完成";
				else
				if (taskName.equals("HoldHumTask"))
					status = "已归档";
				if (status.length() > 0)
					CommonFaultBO.updateAlarm(sheetKey, status);
			}
		} catch (Exception e) {
			BocoLog.info(this, "sync error!");
			e.printStackTrace();
		}
		
		//add by weichao 20150604 begin
		try {
			Map link = (HashMap) sheetMap.get("link");
			Map main = (HashMap)sheetMap.get("main");
			Date operateTime = (Date)link.get("operateTime");
			String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
			String mainIfCombine = StaticMethod.nullObject2String(main.get("mainIfCombine"));
			String operateType = StaticMethod.nullObject2String(link.get("operateType"));
		
			//T1的时候不需要自动归档 所以如果是主单在T1回单，则需要将副单流转到待归档环节
			if("46".equals(operateType) && "combine".equals(mainIfCombine) && taskName.equals("FirstExcuteHumTask")){
				this.transferViSheet2HoldTask(request,taskName,main,link);
			}
			//T2的时候需要校验自动归档，如果自动归档则需将副单自动归档，如果不自动归档，则需将副单流转到待归档环节
			if (operateType.equals("46") && taskName.equals("SecondExcuteHumTask")&&"combine".equals(mainIfCombine))
			{
				Date mainAlarmSolveDate = (Date)main.get("mainAlarmSolveDate");
				String mainAlarmId = (String)main.get("mainAlarmId");
				String mainFaultResponseLevel = StaticMethod.nullObject2String(main.get("mainFaultResponseLevel"));
				String linkDealStep = (String)link.get("selectStep");
				String nodeAccessories = StaticMethod.nullObject2String(link.get("nodeAccessories"));
				boolean inrule = true;
				String obj = "";
				if (mainAlarmSolveDate != null && main.get("sendContact") != null && operateTime.after(mainAlarmSolveDate) 
						&& "".equals(nodeAccessories) && !"101030401".equals(mainFaultResponseLevel))
				{
					ICommonFaultAutoManager autoservice = (ICommonFaultAutoManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
					List steplist = autoservice.getStepsbycondition(mainAlarmId, linkDealStep);
					if (steplist.size() > 0 && !"其它".equals(linkDealStep) && !"其他".equals(linkDealStep))
					{
						inrule = true;
						BocoLog.info(this,"满足自动归档规则！");
						Object object[] = (Object[])steplist.get(0);
						obj = String.valueOf(object[2]);
					} else
					{
						inrule = false;
					}
					if (inrule){//满足自动归档条件，自动归档副单
						this.autoHoldViSheet(taskName,obj,main,link);
					}else{//不满足自动归档条件，将副单流转到待归档环节
						this.transferViSheet2HoldTask(request,taskName,main,link);
					}
				}
			}
			//待归档环节 手动归档需先将副单归档
			if("HoldHumTask".equals(taskName)&&"18".equals(operateType)){
				String obj = StaticMethod.nullObject2String(request.getParameter("endResult"));//归档原因
				this.autoHoldViSheet(taskName,obj, main, link);
			}
			//待归档环节 如果退回 则将副单也一并退回到上一级
			//待归档环节 手动归档需先将副单归档
			if("HoldHumTask".equals(taskName)&&"17".equals(operateType)){
				main.put("mainIfReply", "");
				sheetMap.put("main", main);
				String rejectActiveTemplateId = StaticMethod.nullObject2String(request.getParameter("rejectActiveTemplateId"));//归档原因
				this.reject2preTask(request,rejectActiveTemplateId, main, link);
			}
			
		} catch (Exception e) {
			BocoLog.info(this, "auto deal error!");
			e.printStackTrace();
		}		
		//add by weichao 20150604 end
		return mapping.findForward("success");
	}

	public ActionForward performClaimTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		System.out.println("into performClaim ............");
		String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("SendImmediately"));
		if (!sendImmediately.equalsIgnoreCase("true"))
		{
			request.setAttribute("interfaceType", "InterSwitchAlarm");
			request.setAttribute("methodType", "syncSheetState");
		}
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		baseSheet.performClaim(mapping, form, request, response);
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String enableService = XmlManage.getFile("/config/commonfault-util.xml").getProperty("EnableService");
		if ("true".equals(enableService) && sendImmediately.equalsIgnoreCase("true"))
		{
			String status = "";
			String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
			System.out.println("调用告警同步接口 taskName=" + taskName);
			System.out.println("调用告警同步接口 operateType=" + operateType);
			if (operateType.equals("61"))
			{
				if (taskName.equals("FirstExcuteHumTask"))
					status = "T1处理中";
				else
				if (taskName.equals("SecondExcuteHumTask"))
					status = "T2处理中";
				else
				if (taskName.equals("ThirdExcuteHumTask"))
					status = "T3处理中";
				if (status.length() > 0)
					CommonFaultBO.updateAlarm(sheetKey, status);
			}
		}
		return mapping.findForward("detail");
	}

	public ActionForward performFroceHold(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("SendImmediately"));
		if (!sendImmediately.equalsIgnoreCase("true"))
		{
			request.setAttribute("interfaceType", "InterSwitchAlarm");
			request.setAttribute("methodType", "syncSheetState");
		}
		try
		{
			baseSheet.performFroceHold(mapping, form, request, response);
			String enableService = XmlManage.getFile("/config/commonfault-util.xml").getProperty("EnableService");
			if ("true".equals(enableService) && sendImmediately.equalsIgnoreCase("true"))
			{
				String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
				System.out.println("调用告警同步接口 operateType=" + operateType);
				System.out.println("调用告警同步接口 sheetKey=" + sheetKey);
				if (String.valueOf(-12).equals(operateType))
				{
					String status = "作废";
					CommonFaultBO.updateAlarm(sheetKey, status);
				} else
				{
					System.out.println("未调用告警同步接口");
				}
			}
		}
		catch (Exception e)
		{
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	public ActionForward showListUndoForCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		ICommonFaultMainManager commonfaultMainManager = (ICommonFaultMainManager)baseSheet.getMainService();
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		Integer total = commonfaultMainManager.getCountUndoForCheck();
		List result = commonfaultMainManager.getListUndoForCheck(pageIndex, new Integer(pageSize.intValue()));
		request.setAttribute("taskList", result);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("backchecklist");
	}

	public ActionForward showListDoneForCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		ICommonFaultMainManager commonfaultMainManager = (ICommonFaultMainManager)baseSheet.getMainService();
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		Integer total = commonfaultMainManager.getCountDoneForCheck();
		List result = commonfaultMainManager.getListDoneForCheck(pageIndex, new Integer(pageSize.intValue()));
		request.setAttribute("taskList", result);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("backcheckedlist");
	}

	public ActionForward showBackCheckDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		request.setAttribute("mainid", sheetKey);
		return mapping.findForward("backcheckdealpage");
	}

	public ActionForward doBackCheckSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		ICommonFaultMainManager commonfaultMainManager = (ICommonFaultMainManager)baseSheet.getMainService();
		String mainId = StaticMethod.nullObject2String(request.getParameter("mainid"));
		String mainCheckIdea = StaticMethod.nullObject2String(request.getParameter("mainCheckIdea"));
		String mainCheckResult = StaticMethod.nullObject2String(request.getParameter("mainCheckResult"));
		if (!mainId.equals(""))
		{
			System.out.println("事后质检保存开始===mainId==" + mainId);
			CommonFaultMain mainObject = (CommonFaultMain)commonfaultMainManager.loadSinglePO(mainId);
			mainObject.setMainCheckIdea(mainCheckIdea);
			mainObject.setMainCheckResult(mainCheckResult);
			mainObject.setMainIfCheck("2");
			commonfaultMainManager.saveOrUpdateMain(mainObject);
		}
		return mapping.findForward("success");
	}

	public ActionForward showProvinceMainPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetNo"), "");
		String userid = StaticMethod.nullObject2String(request.getParameter("userName"), "");
		if ("".equals(userid))
			userid = "admin";
		try
		{
			IWorkflowSecutiryService safeService = (IWorkflowSecutiryService)ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
			javax.security.auth.Subject subject = safeService.logIn(userid, "");
			request.getSession().setAttribute("wpsSubject", subject);
		}
		catch (Exception e)
		{
			BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
		}
		System.out.println("sheetNo=" + sheetId);
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		ICommonFaultMainManager commonFaultMainManager = (ICommonFaultMainManager)baseSheet.getMainService();
		BaseMain main = commonFaultMainManager.getMainBySheetId(sheetId);
		if (main == null)
		{
			throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
		} else
		{
			String sheetKey = main.getId();
			List linkList = baseSheet.getLinkService().getLinksByMainId(sheetKey);
			request.setAttribute("sheetMain", main);
			request.setAttribute("HISTORY", linkList);
			return mapping.findForward("provinecDetail");
		}
	}

	public ActionForward showProvinceIndexPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawSystemUser user = userMgr.getUserByuserid(sessionform.getUserid());
		request.setAttribute("Username", sessionform.getUserid());
		request.setAttribute("CertID", sessionform.getUserid());
		request.setAttribute("Realname", sessionform.getUsername());
		request.setAttribute("Phone", user.getPhone());
		String url = XmlManage.getFile("/com/boco/eoms/interfaces/province/config/province-util.xml").getProperty("Interface.provinceIndexPage");
		ActionForward af = new ActionForward();
		af.setPath(url);
		af.setRedirect(true);
		return af;
	}

	public ActionForward updateObjectByCondition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		ICommonFaultMainManager commonfaultMainManager = (ICommonFaultMainManager)baseSheet.getMainService();
		ICommonFaultLinkManager commonfaultLinkManager = (ICommonFaultLinkManager)baseSheet.getLinkService();
		ICommonFaultTaskManager commonfaultTaskManager = (ICommonFaultTaskManager)baseSheet.getTaskService();
		String mainId = StaticMethod.nullObject2String(request.getParameter("mainId"));
		String title = StaticMethod.nullObject2String(request.getParameter("title"));
		String mainAlarmDesc = StaticMethod.nullObject2String(request.getParameter("mainAlarmDesc"));
		String mainPretreatment = StaticMethod.nullObject2String(request.getParameter("mainPretreatment"));
		String linkFaultFirstDealDesc = StaticMethod.nullObject2String(request.getParameter("linkFaultFirstDealDesc"));
		String linkFaultDesc = StaticMethod.nullObject2String(request.getParameter("linkFaultDesc"));
		Map conditionMap = new HashMap();
		conditionMap.put("title", title);
		conditionMap.put("mainId", mainId);
		if (!mainId.equals(""))
		{
			CommonFaultMain mainObject = (CommonFaultMain)commonfaultMainManager.loadSinglePO(mainId);
			mainObject.setTitle(title);
			mainObject.setMainAlarmDesc(mainAlarmDesc);
			if (mainPretreatment.equals("1030101"))
			{
				String linkCondition = " mainId = '" + mainId + "' and operateType = '1' order by operateTime asc";
				String str = commonfaultLinkManager.getLinkObject().getClass().getName();
				List linkObject = commonfaultLinkManager.getLinksBycondition(linkCondition, commonfaultLinkManager.getLinkObject().getClass().getName());
				if (linkObject != null && !linkObject.isEmpty())
				{
					CommonFaultLink commonFaultLink = (CommonFaultLink)linkObject.get(0);
					commonFaultLink.setLinkFaultFirstDealDesc(linkFaultFirstDealDesc);
					commonFaultLink.setLinkFaultDesc(linkFaultDesc);
					commonfaultLinkManager.addLink(linkObject.get(0));
				}
				mainObject.setLinkFaultFirstDealDesc(linkFaultFirstDealDesc);
				mainObject.setLinkFaultDesc(linkFaultDesc);
			}
			commonfaultMainManager.saveOrUpdateMain(mainObject);
			String taskTableName = HibernateConfigurationHelper.getTableName(commonfaultTaskManager.getTaskModelObject().getClass());
			String taskSql = "update " + taskTableName + " task set task.title = '" + title + "' where task.sheetKey = '" + mainId + "'";
			IDownLoadSheetAccessoriesService iDownLoad = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
			iDownLoad.updateTasks(taskSql);
		}
		return mapping.findForward("success");
	}

	public ActionForward performBatchDeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		try
		{
			baseSheet.performBatchDeal(mapping, form, request, response);
		}
		catch (Exception e)
		{
			System.out.println("Batch Deal has error!");
		}
		String sheetIds = StaticMethod.nullObject2String(request.getAttribute("succesReturn"));
		BocoLog.info(this, "succesReturn=" + sheetIds);
		if (sheetIds.length() > 0)
		{
			String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("SendImmediately"));
			String enableService = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("EnableService"));
			if ("true".equalsIgnoreCase(enableService) && sendImmediately.equalsIgnoreCase("true"))
			{
				ICommonFaultMainManager mgr = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
				String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
				String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
				String status = "";
				System.out.println("performBatchDeal taskName=" + taskName);
				System.out.println("performBatchDeal phaseId=" + phaseId);
				if (phaseId.equals("HoldTask"))
					status = "处理完成";
				else
				if (taskName.equals("HoldHumTask"))
					status = "已归档";
				if (status.length() > 0)
				{
					String array[] = sheetIds.split(",");
					for (int i = 0; i < array.length; i++)
					{
						String sheetId = array[i];
						BaseMain main = mgr.getMainBySheetId(sheetId);
						String sheetKey = main.getId();
						try
						{
							CommonFaultBO.updateAlarm((CommonFaultMain)main, status);
						}
						catch (Exception e)
						{
							System.out.println("performBatchDeal sheetKey=" + sheetKey);
							e.printStackTrace();
						}
					}

				}
			}
		}
		return mapping.findForward("success");
	}

	public void getJsonLink(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		System.out.println("id======" + id);
		String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		BaseLink baselink = baseSheet.getLinkService().getSingleLinkPO(id);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(baselink);
		jsonArray.put(jsonRoot);
		String fileIdList = baselink.getNodeAccessories();
		if (fileIdList != null && !"".equals(fileIdList))
		{
			System.out.println("fileIdList=" + fileIdList);
			String fileIds[] = fileIdList.split(",");
			fileIdList = "";
			for (int k = 0; k < fileIds.length; k++)
				fileIdList = fileIdList + fileIds[k] + ",";

			System.out.println("fileIdList=" + fileIdList);
			fileIdList = fileIdList.substring(0, fileIdList.length() - 1);
			System.out.println("fileIdList=" + fileIdList);
			List list = null;
			ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager)ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
			list = mgr.getAllFileById(fileIdList);
			if (list != null && list.size() > 0)
			{
				for (int i = 0; i < list.size(); i++)
				{
					JSONObject jsonRoot1 = new JSONObject();
					TawCommonsAccessories accessories = (TawCommonsAccessories)list.get(i);
					jsonRoot1.put("id", accessories.getId());
					jsonRoot1.put("name", accessories.getAccessoriesName());
					jsonRoot1.put("fileName", accessories.getAccessoriesCnName());
					jsonArray.put(jsonRoot1);
				}

			}
		}
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonArray.toString());
	}

	public ActionForward showListsendundoByUserId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ICommonFaultMainManager mainService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
		ITask commonFaultTask = (ITask)ApplicationContextHolder.getInstance().getBean("CommonFaultTask");
		ITawSystemUserManager userManager = (ITawSystemUserManager)getBean("itawSystemUserManager");
		HashMap filterMap = new HashMap();
		filterMap.put("TEMPLATE_NAME", mainService.getFlowTemplateName());
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexNameAgent = (new ParamEncoder("taskListAgent")).encodeParameterName("p");
		Integer pageIndexAgent = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexNameAgent)) ? 0 : Integer.parseInt(request.getParameter(pageIndexNameAgent)) - 1);
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
		String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
		String orderCondition = "";
		if (!order.equals(""))
			if (order.equals("1"))
				order = " asc";
			else
				order = " desc";
		if (!sort.equals(""))
			orderCondition = " " + sort + order;
		String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
		if (!exportType.equals(""))
			pageSize = new Integer(-1);
		String userId = StaticMethod.nullObject2String(request.getParameter("userId"));
		TawSystemUser user = userManager.getUserByuserid(userId);
		String deptId = "";
		if (user != null)
			deptId = StaticMethod.nullObject2String(user.getDeptid());
		BaseMain mainObject = (BaseMain)mainService.getMainObject().getClass().newInstance();
		ITask taskObject = commonFaultTask;
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		List taskList = new ArrayList();
		Integer pageIndexFinal = new Integer(0);
		for (int k = 0; k < 2; k++)
		{
			if (k == 0)
				pageIndexFinal = pageIndexAgent;
			else
				pageIndexFinal = pageIndex;
			condition.put("ifAgent", (new Integer(k)).toString());
			String flowName = mainService.getFlowTemplateName();
			HashMap taskListOvertimeMap = taskService.getUndoTaskByOverTime(condition, userId, deptId, flowName, pageIndexFinal, pageSize);
			int total = ((Integer)taskListOvertimeMap.get("taskTotal")).intValue();
			List taskOvertimeList = (List)taskListOvertimeMap.get("taskList");
			List taskMapList = new ArrayList();
			if (taskOvertimeList != null && taskOvertimeList.size() > 0)
			{
				IOvertimeTipManager service = (IOvertimeTipManager)ApplicationContextHolder.getInstance().getBean("iOvertimeTipManager");
				List timeList = service.getEffectOvertimeTip(mainService.getFlowTemplateName(), userId);
				HashMap columnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
				System.out.println("==columnMap==" + columnMap);
				HashMap columnMapOverTip = OvertimeTipUtil.getNotOverTimeColumnByMapping(flowName);
				for (int i = 0; i < taskOvertimeList.size(); i++)
				{
					ITask tmptask = null;
					Map taskMap = new HashMap();
					Map tmptaskMap = new HashMap();
					HashMap conditionMap = new HashMap();
					if (columnMap.size() > 0)
					{
						Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
						tmptask = (ITask)tmpObjArr[columnMap.size()];
						Iterator it = columnMap.keySet().iterator();
						for (int j = 0; it.hasNext(); j++)
						{
							String elementKey = (String)it.next();
							Object tempcolumn = tmpObjArr[j];
							conditionMap.put(elementKey, tempcolumn);
							tmptaskMap.put(columnMap.get(elementKey), tempcolumn);
						}

					} else
					{
						Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
						tmptask = (ITask)tmpObjArr[columnMap.size()];
					}
					if (exportType.equals(""))
					{
						String overtimeFlag = OvertimeTipUtil.setOvertimeTipFlag(columnMapOverTip, tmptask.getCompleteTimeLimit(), conditionMap, timeList, flowName);
						taskMap.put("overtimeType", overtimeFlag);
						long overtime = OvertimeTipUtil.getOvertime(columnMapOverTip, tmptask.getCompleteTimeLimit(), conditionMap, timeList, flowName);
						taskMap.put("overtime", new Long(overtime));
					}
					taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
					taskMap.putAll(tmptaskMap);
					taskList.add(tmptask);
					taskMapList.add(taskMap);
				}

			}
			if (k == 0)
			{
				request.setAttribute("taskListAgent", taskMapList);
				request.setAttribute("totalAgent", new Integer(total));
			} else
			{
				request.setAttribute("taskList", taskMapList);
				request.setAttribute("total", new Integer(total));
			}
		}

		request.setAttribute("overTimeTaskCount", new Integer(0));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "listall");
		request.setAttribute("module", mapping.getPath().substring(1));
		String workflowName = mainService.getFlowTemplateName();
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName, "classpath:config/commonfault-config.xml");
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		if (flowDefine != null)
		{
			PhaseId phaseIds[] = flowDefine.getPhaseId();
			for (int i = 0; i < phaseIds.length; i++)
			{
				PhaseId phaseId = phaseIds[i];
				if (!phaseId.getId().equals("receive"))
				{
					phaseIdMap.put(phaseId.getId(), phaseId.getName());
					phaseIdList.add(phaseId.getId());
				}
			}

		}
		request.setAttribute("phaseIdMap", phaseIdMap);
		request.setAttribute("stepIdList", phaseIdList);
		String batch = StaticMethod.null2String(request.getParameter("batch"));
		if (!batch.equals("") && batch.equals("true"))
		{
			Map tempMap = new HashMap();
			String dictName = "dict-sheet-" + mapping.getPath().substring(1);
			List dictItems = DictMgrLocator.getDictService().getDictItems(Util.constituteDictId(dictName, "activeTemplateId"));
			for (Iterator it = dictItems.iterator(); it.hasNext();)
			{
				DictItemXML dictItemXml = (DictItemXML)it.next();
				String description = dictItemXml.getDescription();
				if (description.equals("batch:true"))
					tempMap.put(dictItemXml.getItemId(), dictItemXml.getItemName());
			}

			Map batchTaskMap = new HashMap();
			if (tempMap.size() > 0)
			{
				for (Iterator it = tempMap.keySet().iterator(); it.hasNext();)
				{
					String taskName = (String)it.next();
					for (Iterator tasks = taskList.iterator(); tasks.hasNext();)
					{
						ITask task = (ITask)tasks.next();
						if (taskName.equals(task.getTaskName()) && (task.getSubTaskFlag() == null || task.getSubTaskFlag().equals("false") || task.getSubTaskFlag().equals("")))
						{
							batchTaskMap.put(task.getTaskName(), task.getTaskDisplayName());
							break;
						}
					}

				}

			}
			request.setAttribute("batchTaskMapKey", batchTaskMap.keySet());
			request.setAttribute("batchTaskMap", batchTaskMap);
		}
		return mapping.findForward("listall");
	}

	public void updateCheckStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
	ICommonFaultMainManager mainService = (ICommonFaultMainManager)getBean("iCommonFaultMainManager");
	CommonFaultMain main = (CommonFaultMain)mainService.loadSinglePO(sheetKey);


	String checkStatus = main.getMainCheckStatus();


	Integer checkCount = Integer.valueOf(main.getCheckCount());


	String flag = "";




	if (checkCount.intValue() < 3)
	{
		if ("1".equals(checkStatus))
			flag = "此工单正处于核查申请中！";
		else
		if ("2".equals(checkStatus))
			flag = "此工单已核实告警！";
		else
		if ("3".equals(checkStatus))
		{
			flag = "此工单已驳回，再次进行申请告警核实";
			mainService.updateMainBySql("update commonfault_main set mainCheckStatus='1',checkCount=checkCount+1 where id='" + sheetKey + "'");
		} else
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String applyTime = df.format(new Date()).toString();
			mainService.updateMainBySql("update commonfault_main set mainCheckStatus='1',checkCount=checkCount+1,mainApplyCheckTime=to_date('" + applyTime + "','yyyy-MM-dd HH24:mi:ss')  where id='" + sheetKey + "'");
			flag = "已申请告警核实！";

		}
	} else
	{
		flag = "此工单已申请告警核实三次以上，无法再次进行申请";
	}
	JSONArray jsonArray = new JSONArray();
	JSONObject jsonRoot1 = new JSONObject();
	jsonRoot1.put("flag", flag);
	jsonArray.put(jsonRoot1);
	response.setContentType("text/xml;charset=UTF-8");
	response.getWriter().print(jsonArray.toString());
}


	public ActionForward showCheckList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ICommonFaultMainManager mainService = (ICommonFaultMainManager)getBean("iCommonFaultMainManager");
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		Map map = mainService.getCheckList("", pageIndex, pageSize);
		Integer sheetTotal = StaticMethod.nullObject2Integer(map.get("sheetTotal"));
		List sheetList = (List)map.get("sheetList");
		request.setAttribute("taskList", sheetList);
		request.setAttribute("total", sheetTotal);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("checkList");
	}

	public ActionForward showSuccess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	ICommonFaultMainManager mainService = (ICommonFaultMainManager)getBean("iCommonFaultMainManager");
	String alarmSolveTime = StaticMethod.nullObject2String(request.getParameter("alarmSolveTime"));
	String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));




	String ids[] = sheetKey.split(",");
	for (int i = 0; i < ids.length; i++)
		if (!"".equals(ids[i]))
		{
			String sql = "update commonfault_main set mainCheckStatus='2',mainCheckTime=to_date('" + alarmSolveTime + "','yyyy-MM-dd HH24:mi:ss') where id='" + ids[i] + "'";


			mainService.updateMainBySql(sql);
		}

	return mapping.findForward("checksuccess");
}

public ActionForward showReject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	ICommonFaultMainManager mainService = (ICommonFaultMainManager)getBean("iCommonFaultMainManager");
	String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
	String suggestions = StaticMethod.nullObject2String(request.getParameter("suggestions"));




	String ids[] = sheetKey.split(",");
	for (int i = 0; i < ids.length; i++)
		if (!"".equals(ids[i]))
		{
			String sql = "update commonfault_main set mainCheckStatus='3',mainCheckRejected='" + suggestions + "' where id='" + ids[i] + "'";


			mainService.updateMainBySql(sql);
		}

	return mapping.findForward("checksuccess");
}

	public ActionForward showQCScreenPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		request.setAttribute("module", mapping.getPath().substring(1));
		return mapping.findForward("qcscreen");
	}

	public ActionForward showQCQueryPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		request.setAttribute("module", mapping.getPath().substring(1));
		return mapping.findForward("qcquery");
	}

	public ActionForward queryQCSheetByWhere(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String type = StaticMethod.null2String(request.getParameter("type"));
		BocoLog.info(this, "=========工单查询类型为(type:screen,工单质检筛选 type:query,工单质检查询)========type=" + type);
		Map actionMap = StatUtil.ParameterMapToUtilMap(request.getParameterMap());
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
		if (!exportType.equals(""))
			pageSize = new Integer(-1);
		ICommonFaultQCManager qcService = (ICommonFaultQCManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultQCManager");
		if ("screen".equals(type))
		{
			actionMap.put("flag", "on");
			String totalSql = qcService.getSqlByCondition(actionMap);
			int num = qcService.getTotalBySql(totalSql);
			actionMap.put("num", (new StringBuffer(String.valueOf(num))).toString());
			actionMap.put("flag", "off");
		}
		String sql = qcService.getSqlByCondition(actionMap);
		Map resultMap = qcService.selectObject(sql, pageIndex, pageSize);
		Integer total = (Integer)resultMap.get("total");
		List taskList = (List)resultMap.get("result");
		request.setAttribute("taskList", taskList);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
		if ("screen".equals(type))
		{
			TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
			String qcLimit = XmlManage.getFile("/config/commonfault-util.xml").getProperty("LimitOfQC");
			BocoLog.info(this, "当前登录人：" + sessionform.getUserid() + "---------质检权限配置信息：" + qcLimit);
			boolean flag = false;
			if (qcLimit != null && !"".equals(qcLimit))
			{
				String qcUser[] = qcLimit.split(",");
				for (int i = 0; i < qcUser.length; i++)
				{
					if (!sessionform.getUserid().equals(qcUser[i]))
						continue;
					flag = true;
					break;
				}

			}
			if (flag)
			{
				BocoLog.info(this, "------------您拥有质检权限---------------");
				request.setAttribute("flag", "on");
			} else
			{
				request.setAttribute("flag", "off");
			}
			return mapping.findForward("qcscreenlist");
		} else
		{
			return mapping.findForward("qcquerylist");
		}
	}

	public ActionForward showQcPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
		request.setAttribute("module", mapping.getPath().substring(1));
		request.setAttribute("sheetKey", sheetKey);
		return mapping.findForward("qcpage");
	}

	public ActionForward saveQCRemark(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
		String qcRemark = StaticMethod.null2String(request.getParameter("qcRemark"));
		System.out.println("-----sheetKey-----" + sheetKey + "------qcRemark-----" + qcRemark);
		ICommonFaultMainManager mgr = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		CommonFaultMain main = (CommonFaultMain)mgr.getSingleMainPO(sheetKey);
		main.setQcRemark(qcRemark);
		main.setQcMark(1);
		mgr.addMain(main);
		IBaseSheet baseSheet = (IBaseSheet)getBean(mapping.getAttribute());
		baseSheet.showMainDetailPage(mapping, form, request, response);
		return mapping.findForward("maindetail");
	}
	
	/** add by weichao 20150604 begin
	 * 在归档环节将副单退回到上一步
	 * @param rejectActiveTemplateId 上一步的phaseId
	 * @param main
	 * @param link
	 * @author weichao
	 * 2015-6-4 下午04:59:39
	 */
	private void reject2preTask(HttpServletRequest request, String rejectActiveTemplateId, Map main, Map link) {		
		String sheetIdd = "";//循环时将副单sheetId赋值给此变量，用于失败提示
		try {
			//1、找到副单列表
			BocoLog.info(this, "****副单开始至上一环节开始*******");
			ICommonFaultMainManager mainService = (ICommonFaultMainManager)getBean("iCommonFaultMainManager");
			ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)getBean("iCommonFaultTaskManager");
			ICommonFaultLinkManager linkService = (ICommonFaultLinkManager)getBean("iCommonFaultLinkManager");
			ICommonFaultFlowManager flowService = (ICommonFaultFlowManager)getBean("iCommonFaultFlowManager");
			String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
			List list = mainService.getMainsByCondition(" mainIfCombine='"+StaticMethod.nullObject2String(main.get("id"))+"' ");
			BocoLog.info(this, "副单listsize=="+list.size()+"====主单sheetId="+sheetId);			
			TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
			String usrId = sessionform.getUserid();
			for(int i=0;null!=list&&i<list.size();i++){				
				//2、构造main Map
				CommonFaultMain mainbean = (CommonFaultMain)list.get(i);
				sheetIdd = mainbean.getSheetId();
				BocoLog.info(this, "副单开始流转至上一环节=="+mainbean.getSheetId());
				Map maMap = SheetBeanUtils.bean2Map(mainbean);
				//3、查找task待办
				String condition = " sheetKey = '" + mainbean.getId() + "'" + 
				" and taskName='HoldHumTask' and taskStatus='2' and (subTaskFlag is null or subTaskFlag = 'false' )" ;
				List baseTasks = taskService.getTasksByCondition(condition);
				CommonFaultTask baseTask = null;				
				if(null!=baseTasks&&baseTasks.size()>0){
					baseTask = (CommonFaultTask)baseTasks.get(0);
				}else{
					BocoLog.info(this, "工单没有符合条件的HoldHumTask记录=="+sheetId);
					continue;
				}
				//4、构造link Map即处理完成的link记录
				Map liMap = link;
				liMap.put("id", UUIDHexGenerator.getInstance().getID());
				liMap.put("toOrgRoleId", mainbean.getSendUserId());
				//5、构造并处理流程流转所需要的参数及operate Map
				HashMap columnMap = new HashMap();
				columnMap.put("selfSheet", this.setNewInterfacePara(mainService,linkService));	
				HashMap parameters = new HashMap();
				parameters.putAll(maMap);
				parameters.putAll(liMap);
				this.setBaseMap(parameters,mainService,linkService);
				
				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
				HashMap WpsMap = sm.prepareMap(parameters, columnMap);
				Map opMap = (HashMap)WpsMap.get("operate");

				BaseLink baseLink = linkService.getSingleLinkPO(baseTask.getPreLinkId());
				BocoLog.info(this, "副单开始流转至上一环节=="+baseLink.getActiveTemplateId());
				opMap.put("phaseId", baseLink.getActiveTemplateId());
				opMap.put("dealPerformer", baseLink.getOperateUserId());
				opMap.put("dealPerformerLeader",baseLink.getOperateUserId());
				opMap.put("dealPerformerType","user");
				opMap.put("hasNextTaskFlag", "com.boco.eoms.sheet.commonfault.model.CommonFaultMain");
				WpsMap.put("operate", opMap);
				WpsMap.put("main", maMap);
				//6、通过流程引擎调用处理完成的方法																
				HashMap sessionMap = new HashMap();
				sessionMap.put("userId", usrId);
				sessionMap.put("password", "111");
				flowService.completeHumanTask(baseTask.getId(),WpsMap,sessionMap);
				BocoLog.info(this,"sheetid is " +mainbean.getSheetId()+ " 进入待归档成功");				
				//6、修改待办方法使副单不会出现在待办列表							
			}		
		} catch (Exception e) {
			BocoLog.info(this, "副单退回到上一环节失败（preTask back error）==="+sheetIdd+",并检查err日志");
			e.printStackTrace();
		}
	}

	/**
	 * 将满足自动归档条件的副单归档
	 * @param obj 归档原因
	 * @param main
	 * @author weichao
	 * 2015-6-4 下午04:59:39
	 */
	private void autoHoldViSheet(String taskName, String obj,Map main,Map link) {
		String sheetIdd = "";//循环时将副单sheetId赋值给此变量，用于失败提示
		try {
			BocoLog.info(this, "****副单开始自动归档*******");
			ICommonFaultMainManager mainService = (ICommonFaultMainManager)getBean("iCommonFaultMainManager");
			ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)getBean("iCommonFaultTaskManager");
			ICommonFaultLinkManager linkService = (ICommonFaultLinkManager)getBean("iCommonFaultLinkManager");
			ICommonFaultFlowManager flowService = (ICommonFaultFlowManager)getBean("iCommonFaultFlowManager");
			String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
			List list = mainService.getMainsByCondition(" mainIfCombine='"+StaticMethod.nullObject2String(main.get("id"))+"' and status=0 ");
			BocoLog.info(this, "副单listsize=="+list.size()+"====主单sheetId="+sheetId);
			Calendar calendar = Calendar.getInstance();
			for(int i=0;null!=list&&i<list.size();i++){
				//1、构造副单处理完成的link记录
				CommonFaultMain mainbean = (CommonFaultMain)list.get(i);
				sheetIdd = mainbean.getSheetId();
				BocoLog.info(this, "副单自动归档开始=="+mainbean.getSheetId());
				CommonFaultLink linkbean = (CommonFaultLink)linkService.getLinkObject().getClass().newInstance();
				SheetBeanUtils.populate(linkbean, link);
				linkbean.setId(UUIDHexGenerator.getInstance().getID());
				linkbean.setMainId(StaticMethod.nullObject2String(mainbean.getId()));
				linkService.addLink(linkbean);
				//2、将副单当前task记录完成
				String condition = "";
				if("SecondExcuteHumTask".equals(taskName)){
					condition = " sheetKey = '" + mainbean.getId() + "'" + 
					" and taskName='SecondExcuteHumTask' and taskStatus='8' and (subTaskFlag is null or subTaskFlag = 'false' )" ;
				}else{
					condition = " sheetKey = '" + mainbean.getId() + "'" + 
					" and taskName='HoldHumTask' and taskStatus='2' and (subTaskFlag is null or subTaskFlag = 'false' )" ;
				}
				
				List baseTasks = taskService.getTasksByCondition(condition);
				CommonFaultTask baseTask = null;
				if(null!=baseTasks&&baseTasks.size()>0){
					baseTask = (CommonFaultTask)baseTasks.get(0);
				}else{
					BocoLog.info(this, "工单没有符合条件的质检记录=="+sheetId);
					continue;
				}
				baseTask.setTaskStatus("5");	
				baseTask.setCurrentLinkId(linkbean.getId());
				taskService.addTask(baseTask);
				//3.更新副单main表信息
				mainbean.setEndResult(obj);
				mainbean.setStatus(new Integer(1));
				mainbean.setHoldStatisfied(Integer.valueOf(0xfb89d));
				mainService.saveOrUpdateMain(mainbean);
				//4、构造副单归档的link记录
				CommonFaultLink holdlinkbean = (CommonFaultLink)linkService.getLinkObject().getClass().newInstance();
				holdlinkbean.setId(UUIDHexGenerator.getInstance().getID());
				holdlinkbean.setMainId(StaticMethod.nullObject2String(mainbean.getId()));
				holdlinkbean.setOperateTime(calendar.getTime());
				holdlinkbean.setOperateType(new Integer(18));
				holdlinkbean.setOperateDay(calendar.get(5));
				holdlinkbean.setOperateMonth(calendar.get(2) + 1);
				holdlinkbean.setOperateYear(calendar.get(1));
				holdlinkbean.setOperateUserId(StaticMethod.nullObject2String(mainbean.getSendUserId()));
				holdlinkbean.setOperateDeptId(StaticMethod.nullObject2String(mainbean.getSendDeptId()));
				holdlinkbean.setOperateRoleId(StaticMethod.nullObject2String(mainbean.getSendRoleId()));
				holdlinkbean.setOperaterContact(StaticMethod.nullObject2String(mainbean.getSendContact()));
				holdlinkbean.setToOrgRoleId("");
				holdlinkbean.setToOrgType(new Integer(0));
				holdlinkbean.setAcceptFlag(new Integer(2));
				holdlinkbean.setCompleteFlag(new Integer(2));
				holdlinkbean.setActiveTemplateId("HoldHumTask");
				linkService.addLink(holdlinkbean);
				//5、构造副单归档的task记录
				ITask task = (ITask)taskService.getTaskModelObject().getClass().newInstance();
				String holdtkid = "_AI:" + UUIDHexGenerator.getInstance().getID();
				task.setId(holdtkid);								
				task.setTaskName("HoldHumTask");
				task.setTaskDisplayName("待归档");
				task.setFlowName("CommonFaultMainFlowProcess");
				task.setSendTime(new Date());
				task.setSheetKey(StaticMethod.nullObject2String(mainbean.getId()));
				task.setTaskStatus("5");
				task.setSheetId(StaticMethod.nullObject2String(mainbean.getSheetId()));
				task.setTitle(StaticMethod.nullObject2String(mainbean.getTitle()));
				task.setOperateType("subrole");
				task.setCreateTime(new Date());
				task.setCreateYear(calendar.get(1));
				task.setCreateMonth(calendar.get(2) + 1);
				task.setCreateDay(calendar.get(5));
				task.setOperateRoleId(StaticMethod.nullObject2String(main.get("sendRoleId")));
				task.setTaskOwner(StaticMethod.nullObject2String(main.get("sendUserId")));
				task.setOperateType("subrole");
				task.setIfWaitForSubTask("false");
				task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
				task.setPreLinkId(linkbean.getId());
				taskService.addTask(task);
				//6、将副单手动归档 结束流程实例
				HashMap map = new HashMap();
				Map mainMap = SheetBeanUtils.bean2Map(mainbean);
				Map linkMap = SheetBeanUtils.bean2Map(linkbean);
				map.put("main", mainMap);
				map.put("link", linkMap);
				HashMap sessionMap = new HashMap();
				sessionMap.put("userId", mainbean.getSendUserId());
				sessionMap.put("password", "111");				
			    flowService.triggerEvent(mainbean.getPiid(), "CommonFaultMainFlowProcess","Over", map, sessionMap);
				BocoLog.info(this,"sheetid is " +mainbean.getSheetId()+ " 自动归档成功");
			}		
		} catch (Exception e) {
			BocoLog.info(this, "副单自动归档失败(auto hold error)==="+sheetIdd);
			e.printStackTrace();
		}
	}


	/**
	 * 将副单流转至待归档环节	
	 * @param request
	 * @param taskName 要流转的环节名称
	 * @param main
	 * @param link
	 * 2015-6-4 下午04:59:39
	 * weichao
	 */
	private void transferViSheet2HoldTask(HttpServletRequest request,String taskName, Map main, Map link) {	
		String sheetIdd = "";//循环时将副单sheetId赋值给此变量，用于失败提示
		try {
			//1、找到副单列表
			BocoLog.info(this, "****副单开始流转至待归档环节*******");
			String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
			ICommonFaultMainManager mainService = (ICommonFaultMainManager)getBean("iCommonFaultMainManager");
			ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)getBean("iCommonFaultTaskManager");
			ICommonFaultLinkManager linkService = (ICommonFaultLinkManager)getBean("iCommonFaultLinkManager");
			ICommonFaultFlowManager flowService = (ICommonFaultFlowManager)getBean("iCommonFaultFlowManager");
			List list = mainService.getMainsByCondition(" mainIfCombine='"+StaticMethod.nullObject2String(main.get("id"))+"' and status=0 ");
			BocoLog.info(this, "副单listsize=="+list.size()+"====主单sheetId="+sheetId);			
			TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
			String usrId = sessionform.getUserid();
			for(int i=0;null!=list&&i<list.size();i++){				
				//2、构造main Map
				CommonFaultMain mainbean = (CommonFaultMain)list.get(i);
				sheetIdd = mainbean.getSheetId();
				BocoLog.info(this, "副单开始流转至待归档环节=="+mainbean.getSheetId());
				Map maMap = SheetBeanUtils.bean2Map(mainbean);
				//3、查找task待办
				String condition = " sheetKey = '" + mainbean.getId() + "'" + 
				" and taskName='"+taskName+"' and taskStatus='8' and (subTaskFlag is null or subTaskFlag = 'false' )" ;
				List baseTasks = taskService.getTasksByCondition(condition);
				CommonFaultTask baseTask = null;				
				if(null!=baseTasks&&baseTasks.size()>0){
					baseTask = (CommonFaultTask)baseTasks.get(0);
				}else{
					BocoLog.info(this, "工单没有符合条件的"+taskName+"记录=="+sheetId);
					continue;
				}
				//4、构造link Map即处理完成的link记录
				Map liMap = link;
				liMap.put("id", UUIDHexGenerator.getInstance().getID());
				liMap.put("toOrgRoleId", mainbean.getSendUserId());
				//5、构造并处理流程流转所需要的参数及operate Map
				HashMap columnMap = new HashMap();
				columnMap.put("selfSheet", this.setNewInterfacePara(mainService,linkService));	
				HashMap parameters = new HashMap();
				parameters.putAll(maMap);
				parameters.putAll(liMap);
				this.setBaseMap(parameters,mainService,linkService);
				
				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
				HashMap WpsMap = sm.prepareMap(parameters, columnMap);
				Map opMap = (HashMap)WpsMap.get("operate");

				maMap.put("mainIfReplay", StaticMethod.nullObject2Integer(liMap.get("id")));
				opMap.put("phaseId", "HoldHumTask");
				opMap.put("dealPerformer", mainbean.getSendUserId());
				opMap.put("dealPerformerLeader",mainbean.getSendUserId());
				opMap.put("dealPerformerType","user");
				opMap.put("hasNextTaskFlag", "com.boco.eoms.sheet.commonfault.model.CommonFaultMain");
				WpsMap.put("operate", opMap);
				WpsMap.put("main", maMap);
				//6、通过流程引擎调用处理完成的方法																
				HashMap sessionMap = new HashMap();
				sessionMap.put("userId", usrId);
				sessionMap.put("password", "111");
				flowService.completeHumanTask(baseTask.getId(),WpsMap,sessionMap);
				BocoLog.info(this,"sheetid is " +mainbean.getSheetId()+ " 进入待归档成功");				
				//6、修改待办方法使副单不会出现在待办列表							
			}		
		} catch (Exception e) {
			BocoLog.info(this, "副单进入待归档环节失败(auto come in holdtask error)==="+sheetIdd+",并检查err日志");
			e.printStackTrace();
		}
	
	}
	/**
	 * 
	 * @param map
	 * @param mainService
	 * @param linkService
	 * @return
	 * @author weichao
	 */
	public Map setBaseMap(Map map,ICommonFaultMainManager mainService,ICommonFaultLinkManager linkService ) {
		try {
			String mainBeanId = "iCommonFaultMainManager";
			map.put("beanId", new String[] { mainBeanId });
			map.put("mainClassName", new String[] { mainService.getMainObject().getClass().getName() });
			map.put("linkClassName", new String[] { linkService.getLinkObject().getClass().getName() });
		} catch (Exception err) {
			err.printStackTrace();
		}
		return map;
	}
	/**
	 * add by weichao 20150604 end
	 * @param mainService
	 * @param linkService
	 * @return
	 * @throws Exception
	 * @author weichao
	 */
	public Map setNewInterfacePara(ICommonFaultMainManager mainService,ICommonFaultLinkManager linkService) throws Exception{		
		HashMap sheetMap = new HashMap();
		sheetMap.put("main", mainService.getMainObject().getClass().newInstance());
		sheetMap.put("link", linkService.getLinkObject().getClass().newInstance());
		sheetMap.put("operate", Constants.pageColumnName);
		
		return sheetMap;
	}
}
