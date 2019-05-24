// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultSheetMethodHB.java

package com.boco.eoms.sheet.commonfault.webapp.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.util.ParamEncoder;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.qo.ICommonFaultQo;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultHBManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

// Referenced classes of package com.boco.eoms.sheet.commonfault.webapp.action:
//			CommonFaultSheetMethod

public class CommonFaultSheetMethodHB extends CommonFaultSheetMethod
{

	public CommonFaultSheetMethodHB()
	{
	}

	public void performQueryHB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		Map actionMap = request.getParameterMap();
		List result = null;
		Integer pageSize = new Integer(100);
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		String aSql[] = {
			""
		};
		int aTotal[] = new int[1];
		String queryType = StaticMethod.nullObject2String(request.getParameter("queryType"));
		ICommonFaultMainManager mainService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		ICommonFaultQo workSheetQO = (ICommonFaultQo)mainService.getWorkSheetQO();
		String oldsql = workSheetQO.getClauseSql(actionMap);
		oldsql = oldsql.replace("order by", "and commonFaultMain.status = '0' order by");
		aSql[0] = oldsql;
		String sql = request.getParameter("oldsql");
		if (sql != null && !"".equals(sql))
		{
			oldsql = sql;
			aSql[0] = sql;
		}
		if (request.getParameter("excelid") == null || "null".equals(request.getParameter("excelid")))
			result = mainService.getQueryResult(aSql, actionMap, pageIndex, new Integer(pageSize.intValue()), aTotal, queryType);
		else
		if (request.getParameter("excelid") != null && !"".equals(request.getParameter("excelid")))
		{
			String excelsql = "select distinct commonfaul0_.* from commonfault_main commonfaul0_, commonfault_importfordel t where commonfaul0_.templateFlag <> 1 and (commonfaul0_.title is not null) and commonfaul0_.sheetid = t.sheetid and commonfaul0_.deleted <> '1' and commonfaul0_.status = '0' and t.excelid = '" + request.getParameter("excelid") + "' order by commonfaul0_.sendTime desc";
			ICommonFaultHBManager commonfaultHBManager = (ICommonFaultHBManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultHBManager");
			result = commonfaultHBManager.selectObject(excelsql, pageIndex, new Integer(pageSize.intValue()));
			String countexcelsql = "select count(*) from (select distinct commonfaul0_.* from commonfault_main commonfaul0_, commonfault_importfordel t where commonfaul0_.templateFlag <> 1 and (commonfaul0_.title is not null) and commonfaul0_.sheetid = t.sheetid and commonfaul0_.deleted <> '1' and commonfaul0_.status = '0' and t.excelid = '" + request.getParameter("excelid") + "' order by commonfaul0_.sendTime desc)";
			aTotal[0] = commonfaultHBManager.selectCount(countexcelsql);
		}
		Integer total = new Integer(aTotal[0]);
		if (queryType != null && queryType.equals("number"))
			request.setAttribute("recordTotal", total);
		request.setAttribute("oldsql", oldsql);
		request.setAttribute("taskList", result);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
	}

	public void showListUndoAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ICommonFaultMainManager mainService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
		ITask commonFaultTask = (ITask)ApplicationContextHolder.getInstance().getBean("CommonFaultTask");
		HashMap filterMap = new HashMap();
		filterMap.put("TEMPLATE_NAME", mainService.getFlowTemplateName());
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
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
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
		String ifNetOpt = StaticMethod.nullObject2String(request.getParameter("ifNetOpt"));	
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

			System.out.println("-ifNetOpt-"+ifNetOpt);
			condition.put("ifNetOpt", ifNetOpt);
			Map taskListOvertimeMap = new HashMap();
			if("2".equals(ifNetOpt)){
			 taskListOvertimeMap = taskService.getUndoTaskByOverTimeByNet(condition, userId, deptId, flowName, pageIndexFinal, pageSize);
			}else {
			 taskListOvertimeMap = taskService.getUndoTaskByOverTime(condition, userId, deptId, flowName, pageIndexFinal, pageSize);				
			}
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
		request.setAttribute("ifNetOpt", ifNetOpt);
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
		
		//add by weichao 20150513 T1/T2批量接单 begin
		ITawSystemSubRoleManager subMgr = (ITawSystemSubRoleManager)
				ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
		List subrolesT2 = subMgr.getSubRoles(userId, "8005106");//T2
		List subrolesT1 = subMgr.getSubRoles(userId, "191");//T1
		if((null!=subrolesT1&&subrolesT1.size()>0) || (null!=subrolesT2&&subrolesT2.size()>0) ){
			BocoLog.info(this, "显示批量接单按钮(show the batchAcceptButton)");
			request.setAttribute("showBatchAcceptBtn", "true");
		} 
	//	request.setAttribute("showBatchAcceptBtn", "true");
		//add by weichao 20150513 T1/T2批量接单 end
	}
	
	
/*	
	public void showListUndoAllPerformace(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		ICommonFaultMainManager mainService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
		ITask commonFaultTask = (ITask)ApplicationContextHolder.getInstance().getBean("CommonFaultTask");
		HashMap filterMap = new HashMap();
		filterMap.put("TEMPLATE_NAME", mainService.getFlowTemplateName());
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
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
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
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
			HashMap taskListOvertimeMap = taskService.getUndoTaskByOverTimePerformace(condition, userId, deptId, flowName, pageIndexFinal, pageSize);
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
	}*/
/*
	public void showListUnAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ICommonFaultMainManager mainService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
		ITask commonFaultTask = (ITask)ApplicationContextHolder.getInstance().getBean("CommonFaultTask");
		HashMap filterMap = new HashMap();
		filterMap.put("TEMPLATE_NAME", getMainService().getFlowTemplateName());

		*//** 获取登陆信息，add by qinmin* *//*
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		*//** add by qinmin* *//*

		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String order = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_ORDER)));
		String sort = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
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
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
		// 增加map对象，存放main，传入taskService.getUndoTask
		BaseMain mainObject = (BaseMain)mainService.getMainObject().getClass().newInstance();
		ITask taskObject = commonFaultTask;
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		// HashMap taskListMap = taskService.getUndoTask(condition, userId,
		// deptId, this.getMainService().getFlowTemplateName(), pageIndex,
		// pageSize);
		// int total = ((Integer) taskListMap.get("taskTotal")).intValue();
		// List taskList = (List) taskListMap.get("taskList");

		// 得到待处理列表
		String flowName = mainService.getFlowTemplateName();
		HashMap taskListOvertimeMap = taskService
				.getUndoAcceptByOverTime(condition, userId, deptId, flowName,
						pageIndex, pageSize);
		int total = ((Integer) taskListOvertimeMap.get("taskTotal")).intValue();
		List taskOvertimeList = (List) taskListOvertimeMap.get("taskList");
		List taskMapList = new ArrayList();
		List taskList = new ArrayList();
		// 设置每条task超时标识
		if (taskOvertimeList != null && taskOvertimeList.size() > 0) {
			// 查询超时配置表
			IOvertimeTipManager service = (IOvertimeTipManager) ApplicationContextHolder
					.getInstance().getBean("iOvertimeTipManager");
			List timeList = service.getEffectOvertimeTip(mainService
					.getFlowTemplateName(), userId);
			// 得到角色细分字段
			HashMap columnMap = OvertimeTipUtil
					.getAllMainColumnByMapping(flowName);
			System.out.println("wanghao1====cloumnMap:"+columnMap.size());
			HashMap columnMapOverTip = OvertimeTipUtil
					.getNotOverTimeColumnByMapping(flowName);
			// 循环为task超时标识赋值
			for (int i = 0; i < taskOvertimeList.size(); i++) {
				ITask tmptask = null;
				Map taskMap = new HashMap();
				Map tmptaskMap = new HashMap();
				HashMap conditionMap = new HashMap();
				if (columnMap.size() > 0) { 
					System.out.println("=========wanghao1============"); 
					Object[] tmpObjArr = (Object[]) taskOvertimeList.get(i);
					//tmptask = (ITask) tmpObjArr[columnMap.size()];
					tmptask = (ITask) tmpObjArr[0];
					// 根据角色细分得到需要匹配的字段
					Iterator it = columnMap.keySet().iterator();
					int j = 0;
					while (it.hasNext()) {
						j++;
						String elementKey = (String) it.next();
						Object tempcolumn = tmpObjArr[j];
						conditionMap.put(elementKey, tempcolumn);
						tmptaskMap.put(columnMap.get(elementKey), tempcolumn);
					}
				} else {
					tmptask = (ITask) taskOvertimeList.get(i);
				}
				// 得到超时类型
				if (exportType.equals("")) {
					String overtimeFlag = OvertimeTipUtil.setOvertimeTipFlag(
							columnMapOverTip, tmptask.getCompleteTimeLimit(),
							conditionMap, timeList, flowName);
					taskMap.put("overtimeType", overtimeFlag);
					long overtime = OvertimeTipUtil.getOvertime(
							columnMapOverTip, tmptask.getCompleteTimeLimit(),
							conditionMap, timeList, flowName);
					taskMap.put("overtime", new Long(overtime));
				}
				taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
				taskMap.putAll(tmptaskMap);
				taskList.add(tmptask);
				taskMapList.add(taskMap);
			}
		}

		// 将分页后的列表写入页面供使用
		Integer overTimeTaskCount = taskService.getOverTimeTaskCount(condition, userId, deptId);
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
		request.setAttribute("overTimeTaskCount", overTimeTaskCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "list");
		request.setAttribute("module", mapping.getPath().substring(1));

		// 找出该流程中的节点
		String workflowName = mainService.getFlowTemplateName();
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				workflowName, "classpath:config/commonfault-config.xml");
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		if (flowDefine != null) {
			PhaseId phaseIds[] = flowDefine.getPhaseId();
			for (int i = 0; i < phaseIds.length; i++) {
				PhaseId phaseId = phaseIds[i];
				if (!phaseId.getId().equals("receive")) {
					phaseIdMap.put(phaseId.getId(), phaseId.getName());
					phaseIdList.add(phaseId.getId());
				}
			}
		}
		request.setAttribute("phaseIdMap", phaseIdMap);
		request.setAttribute("stepIdList", phaseIdList);

		// 需要进行批量回复和批量归档的节点
		String batch = StaticMethod.null2String(request.getParameter("batch"));
		if (!batch.equals("") && batch.equals("true")) {
			// 需要进行批量回复和批量归档的步骤放入到tempMap中
			Map tempMap = new HashMap();
			String dictName = "dict-sheet-" + mapping.getPath().substring(1);
			List dictItems = DictMgrLocator.getDictService().getDictItems(
					Util.constituteDictId(dictName, "activeTemplateId"));
			for (Iterator it = dictItems.iterator(); it.hasNext();) {
				DictItemXML dictItemXml = (DictItemXML) it.next();
				String description = dictItemXml.getDescription();
				if (description.equals("batch:true")) {
					tempMap.put(dictItemXml.getItemId(), dictItemXml
							.getItemName());
				}
			}

			// 和所查找的任务列表进行对比，如果该列表中有批量回复和批量归档的步骤就放入到batchTaskMap中
			Map batchTaskMap = new HashMap();
			if (tempMap.size() > 0) {
				for (Iterator it = tempMap.keySet().iterator(); it.hasNext();) {
					String taskName = (String) it.next();
					for (Iterator tasks = taskList.iterator(); tasks.hasNext();) {
						ITask task = (ITask) tasks.next();
						if (taskName.equals(task.getTaskName())
								&& (task.getSubTaskFlag() == null
										|| task.getSubTaskFlag()
												.equals("false") || task
										.getSubTaskFlag().equals(""))) {
							batchTaskMap.put(task.getTaskName(), task
									.getTaskDisplayName());
							break;
						} else {
							continue;
						}
					}
				}
			}

			request.setAttribute("batchTaskMapKey", batchTaskMap.keySet());
			request.setAttribute("batchTaskMap", batchTaskMap);
		}
	}*/
	
	public List showListUndoAllBysheetId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String sheetId)
		throws Exception
	{
		ICommonFaultMainManager mainService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		ICommonFaultTaskManager taskService = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
		ITask commonFaultTask = (ITask)ApplicationContextHolder.getInstance().getBean("CommonFaultTask");
		HashMap filterMap = new HashMap();
		String ifAgent = StaticMethod.nullObject2String(request.getParameter("ifGent"));
		filterMap.put("TEMPLATE_NAME", mainService.getFlowTemplateName());
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
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
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
		BaseMain mainObject = (BaseMain)mainService.getMainObject().getClass().newInstance();
		ITask taskObject = commonFaultTask;
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		condition.put("ifAgent", ifAgent);
		condition.put("sheetId", sheetId);
		List taskList = new ArrayList();
		String flowName = mainService.getFlowTemplateName();
		HashMap taskListOvertimeMap = taskService.getUndoTaskByOverTime(condition, userId, deptId, flowName, pageIndex, pageSize);
		int total = ((Integer)taskListOvertimeMap.get("taskTotal")).intValue();
		List taskOvertimeList = (List)taskListOvertimeMap.get("taskList");
		List taskMapList = new ArrayList();
		if (taskOvertimeList != null && taskOvertimeList.size() > 0)
		{
			IOvertimeTipManager service = (IOvertimeTipManager)ApplicationContextHolder.getInstance().getBean("iOvertimeTipManager");
			List timeList = service.getEffectOvertimeTip(mainService.getFlowTemplateName(), userId);
			HashMap columnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
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
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
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
		return taskMapList;
	}
	
	public String getReply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		String reply = "false";
		String alarmStatus ="";
		String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
		ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
		ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
		if (!"".equals(sheetKey)) {
		//	String condition = "sheetKey = '" + sheetKey + "' and (taskStatus = 2 or (taskStatus = 8 and taskowner = '" + userId + "')) and taskName = 'FirstExcuteHumTask'";
			String condition = "sheetKey = '" + sheetKey + "' and taskStatus in (2,8) and taskName = 'FirstExcuteHumTask'";
			System.out.println("lizhi:condition=" + condition);
			List list = taskservice.getTasksByCondition(condition);
			System.out.println("lizhi:size=" + list.size());
			if (list.size()>0) {
				reply = "true";
				CommonFaultTask commonFaultTask = (CommonFaultTask)list.get(0);
				String taskStatus = StaticMethod.nullObject2String(commonFaultTask.getTaskStatus());
				System.out.println("lizhi:taskStatus=" + taskStatus);
				CommonFaultMain commonFaultMain = (CommonFaultMain)mainservice.getSingleMainPO(sheetKey);
				TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
				String userId = StaticMethod.null2String(sessionform.getUserid());
			
				String deptId = StaticMethod.null2String(sessionform.getDeptid());
				String contactMobile = StaticMethod.null2String(sessionform.getContactMobile());
				
				Calendar calendar = Calendar.getInstance();
				if ("2".equals(taskStatus)) {
					calendar.add(13, -10);
					CommonFaultLink T1link61 = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
					T1link61.setId(UUIDHexGenerator.getInstance().getID());
					T1link61.setMainId(StaticMethod.nullObject2String(commonFaultMain.getId()));
					T1link61.setOperateTime(calendar.getTime());
					T1link61.setOperateType(new Integer(61));
					T1link61.setOperateDay(calendar.get(5));
					T1link61.setOperateMonth(calendar.get(2) + 1);
					T1link61.setOperateYear(calendar.get(1));
					T1link61.setOperateUserId(StaticMethod.nullObject2String(userId));
					T1link61.setOperateDeptId(StaticMethod.nullObject2String(deptId));
					T1link61.setOperateRoleId(StaticMethod.nullObject2String(StaticMethod.nullObject2String(commonFaultTask.getOperateRoleId())));
					T1link61.setOperaterContact(StaticMethod.nullObject2String(contactMobile));
					T1link61.setToOrgRoleId(StaticMethod.nullObject2String(commonFaultMain.getSendRoleId()));
					T1link61.setPreLinkId(StaticMethod.nullObject2String(commonFaultTask.getPreLinkId()));
					T1link61.setNodeAccessories("");
					T1link61.setToOrgType(new Integer(0));
					T1link61.setAcceptFlag(new Integer(0));
					T1link61.setCompleteFlag(new Integer(0));
					T1link61.setActiveTemplateId("FirstExcuteHumTask");
					T1link61.setTemplateFlag(0);		
					linkservice.addLink(T1link61);
				}
				
//				commonFaultTask.setTaskStatus("5");
//				commonFaultTask.setTaskOwner(StaticMethod.nullObject2String(userId));
//				getTaskService().addTask(commonFaultTask);
				
				if (commonFaultMain != null)
				{
					commonFaultMain.setStatus(new Integer(1));
					commonFaultMain.setHoldStatisfied(Integer.valueOf(0xfb89d));
					mainservice.addMain(commonFaultMain);
				}				
				calendar.add(13, 30);
				CommonFaultLink T1link = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
				String linkId = UUIDHexGenerator.getInstance().getID();
				T1link.setId(linkId);
				T1link.setMainId(StaticMethod.nullObject2String(commonFaultMain.getId()));
				T1link.setOperateType(new Integer(46));
				T1link.setOperateTime(calendar.getTime());
				T1link.setOperateDay(calendar.get(5));
				T1link.setOperateMonth(calendar.get(2) + 1);
				T1link.setOperateYear(calendar.get(1));
				T1link.setOperateUserId(StaticMethod.nullObject2String(StaticMethod.nullObject2String(userId)));
				T1link.setOperateDeptId(StaticMethod.nullObject2String(deptId));
				T1link.setOperateRoleId(StaticMethod.nullObject2String(StaticMethod.nullObject2String(commonFaultTask.getOperateRoleId())));
				T1link.setOperaterContact(StaticMethod.nullObject2String(contactMobile));
				String correlationKey = UUIDHexGenerator.getInstance().getID();
				T1link.setCorrelationKey(correlationKey);
				T1link.setPreLinkId(StaticMethod.nullObject2String(commonFaultTask.getPreLinkId()));
				T1link.setToOrgRoleId(StaticMethod.nullObject2String(commonFaultMain.getSendRoleId()));
				T1link.setToOrgType(new Integer(0));
				T1link.setAcceptFlag(new Integer(0));
				T1link.setCompleteFlag(new Integer(0));
				T1link.setTemplateFlag(0);
				T1link.setPiid(StaticMethod.nullObject2String(commonFaultMain.getPiid()));
				T1link.setActiveTemplateId("FirstExcuteHumTask");
				T1link.setNodeAcceptLimit(commonFaultMain.getMainCompleteLimitT1());
				T1link.setNodeCompleteLimit(commonFaultMain.getMainCompleteLimitT1());
				T1link.setLinkDealStep("T1转派前故障已恢复,通过T1一键回复");
				T1link.setLinkFaultDealResult("101030601");
				T1link.setLinkIfGreatFault("1030102");
				T1link.setLinkIfExcuteNetChange("1030102");
				T1link.setLinkIfFinallySolveProject("1030101");
				T1link.setLinkIfAddCaseDataBase("1030102");
				T1link.setRecoveryUnit("101031115");
				T1link.setLinkFaultAvoidTime(commonFaultMain.getMainAlarmSolveDate());
				T1link.setLinkOperRenewTime(commonFaultMain.getMainAlarmSolveDate());
				linkservice.addLink(T1link);
				
				calendar.add(13, 30);
				CommonFaultLink linkbean = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
				linkbean.setId(UUIDHexGenerator.getInstance().getID());
				linkbean.setMainId(StaticMethod.nullObject2String(commonFaultMain.getId()));
				linkbean.setOperateTime(calendar.getTime());
				linkbean.setOperateType(new Integer(18));
				linkbean.setOperateDay(calendar.get(5));
				linkbean.setOperateMonth(calendar.get(2) + 1);
				linkbean.setOperateYear(calendar.get(1));
				linkbean.setOperateUserId(StaticMethod.nullObject2String(commonFaultMain.getSendUserId()));
				linkbean.setOperateDeptId(StaticMethod.nullObject2String(commonFaultMain.getSendDeptId()));
				linkbean.setOperateRoleId(StaticMethod.nullObject2String(commonFaultMain.getSendRoleId()));
				linkbean.setOperaterContact(StaticMethod.nullObject2String(commonFaultMain.getSendContact()));
				linkbean.setToOrgRoleId("");
				linkbean.setToOrgType(new Integer(0));
				linkbean.setAcceptFlag(new Integer(2));
				linkbean.setCompleteFlag(new Integer(2));
				linkbean.setActiveTemplateId("HoldHumTask");
				linkservice.addLink(linkbean);
				
				CommonFaultTask T1Task = (CommonFaultTask)taskservice.getTaskModelObject().getClass().newInstance();
				try
				{
					T1Task.setId(UUIDHexGenerator.getInstance().getID());
				}
				catch (Exception e3)
				{
					e3.printStackTrace();
				}
				T1Task.setTaskName("HoldHumTask");
				T1Task.setTaskDisplayName("待归档");
				T1Task.setFlowName("CommonFaultMainFlowProcess");
				T1Task.setSendTime(commonFaultMain.getSendTime());
				T1Task.setSheetKey(StaticMethod.nullObject2String(commonFaultMain.getId()));
				T1Task.setTaskStatus("5");
				T1Task.setSheetId(StaticMethod.nullObject2String(commonFaultMain.getSheetId()));
				T1Task.setTitle(StaticMethod.nullObject2String(commonFaultMain.getTitle()));
				T1Task.setOperateType("subrole");
				T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
				T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
				T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
				T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
				T1Task.setOperateRoleId(StaticMethod.nullObject2String(commonFaultTask.getOperateRoleId()));
				T1Task.setTaskOwner(StaticMethod.nullObject2String(commonFaultTask.getOperateRoleId()));
				T1Task.setIfWaitForSubTask("false");
				T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
				T1Task.setPreLinkId(StaticMethod.nullObject2String(linkId));
				T1Task.setAcceptTimeLimit(commonFaultMain.getMainCompleteLimitT1());
				T1Task.setCompleteTimeLimit(commonFaultMain.getMainCompleteLimitT1());
				taskservice.addTask(T1Task);
				
				
				Map map = new HashMap();
				Map sheetMap = new HashMap();
				Map columnMap = new HashMap();
//				Map sheetMainMap=SheetBeanUtils.bean2Map(commonFaultMain);
//				Map sheetLinkMap=SheetBeanUtils.bean2Map(T1link);
//				Map sheetTaskMap=SheetBeanUtils.bean2Map(T1Task);
				CommonFaultLink link = (CommonFaultLink)linkservice.getLinkObject();
				
				sheetMap.put("main", commonFaultMain);
				sheetMap.put("link", link);
				sheetMap.put("operate", Constants.pageColumnName);
				columnMap.put("selfSheet", sheetMap);
				map.put("phaseId", "");
//				map.put("operateUserId", userId);
//				map.put("operateDeptId", deptId);
//				map.put("operaterContact", contactMobile);
//				map.put("operateRoleId", StaticMethod.nullObject2String(commonFaultTask.getOperateRoleId()));
				map.put("sheetId", StaticMethod.nullObject2String(commonFaultMain.getSheetId()));
				map.put("correlationKey", StaticMethod.nullObject2String(commonFaultMain.getCorrelationKey()));
				map.put("mainId", StaticMethod.nullObject2String(commonFaultMain.getId()));
				map.put("beanId", "iCommonFaultMainManager");
				map.put("linkBeanId", "iCommonFaultLinkManager");
				map.put("mainClassName", "com.boco.eoms.sheet.commonfault.model.CommonFaultMain");
				map.put("linkClassName", "com.boco.eoms.sheet.commonfault.model.CommonFaultLink");
//				map.put("dealPerformerType", "subrole");
//				map.put("dealPerformer", StaticMethod.nullObject2String(commonFaultMain.getSendRoleId()));
//				map.put("dealPerformerLeader", StaticMethod.nullObject2String(commonFaultMain.getSendRoleId()));
				map.put("operateType", "18");
//				map.put("completeFlag", "0");
//				map.put("operateTime", calendar.getTime());
//				map.put("aiid", commonFaultTask.getId());

				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
				alarmStatus ="已归档";
				sm.dealSheet(sheetKey, map, columnMap, userId, taskservice);
				if (alarmStatus.length() > 0)
					CommonFaultBO.updateAlarm(sheetKey, alarmStatus);

//				businessFlowService.completeHumanTask(taskId, WpsMap,
//						sessionMap);
			}
		}
		return reply;
	}
	
/*	
	*//***
	 * add by weichao  20160602
	 *//*
	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		super.dealFlowEngineMap(mapping, form, request, response);
		HashMap sheetMap = getFlowEngineMap();
		Map link = (HashMap)sheetMap.get("link");
		Map main = (HashMap)sheetMap.get("main");
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(link.get("operateType"));
		if("46".equals(operateType)){
			String linkid = StaticMethod.nullObject2String(link.get("id"));
			main.put("mainIfReply", linkid);			
		}
		if("HoldHumTask".equals(taskName)&&"17".equals(operateType)){
			main.put("mainIfReply", "");
		}
		sheetMap.put("main", main);
		setFlowEngineMap(sheetMap);	
	}*/

}
