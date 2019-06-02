// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SafetyProblemHBMethod.java

package com.boco.eoms.sheet.safetyproblem.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
//import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.model.BaseMain;
//import com.boco.eoms.sheet.base.service.IMainService;
//import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.flowdefine.xml.*;
import com.boco.eoms.sheet.safetyproblem.service.ISafetyProblemMainManager;
import com.boco.eoms.sheet.safetyproblem.service.ISafetyProblemTaskManager;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
//import java.io.PrintStream;
import java.util.*;
import javax.servlet.http.*;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.util.ParamEncoder;

// Referenced classes of package com.boco.eoms.sheet.safetyproblem.webapp.action:
//			SafetyProblemMethod

public class SafetyProblemHBMethod extends SafetyProblemMethod
{

	public SafetyProblemHBMethod()
	{
	}

	/*public void showListUndo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		HashMap filterMap = new HashMap();
		filterMap.put("TEMPLATE_NAME", getMainService().getFlowTemplateName());
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
		BaseMain mainObject = (BaseMain)getMainService().getMainObject().getClass().newInstance();
		ITask taskObject = (ITask)getTaskService().getTaskModelObject().getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		condition.put("type", request.getParameter("type"));
		String ifAgent = StaticMethod.null2String(request.getParameter("ifAgent"));
		condition.put("ifAgent", ifAgent);
		String flowName = getMainService().getFlowTemplateName();
		HashMap taskListOvertimeMap = getTaskService().getUndoTaskByOverTime(condition, userId, deptId, flowName, pageIndex, pageSize);
		int total = ((Integer)taskListOvertimeMap.get("taskTotal")).intValue();
		List taskOvertimeList = (List)taskListOvertimeMap.get("taskList");
		List taskMapList = new ArrayList();
		List taskList = new ArrayList();
		if (taskOvertimeList != null && taskOvertimeList.size() > 0)
		{
			IOvertimeTipManager service = (IOvertimeTipManager)ApplicationContextHolder.getInstance().getBean("iOvertimeTipManager");
			List timeList = service.getEffectOvertimeTip(getMainService().getFlowTemplateName(), userId);
			HashMap columnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
			System.out.println("wanghao1====cloumnMap:" + columnMap.size());
			HashMap columnMapOverTip = OvertimeTipUtil.getNotOverTimeColumnByMapping(flowName);
			for (int i = 0; i < taskOvertimeList.size(); i++)
			{
				ITask tmptask = null;
				Map taskMap = new HashMap();
				Map tmptaskMap = new HashMap();
				HashMap conditionMap = new HashMap();
				if (columnMap.size() > 0)
				{
					if (request.getParameter("type") != null && request.getParameter("type").equals("1"))
					{
						System.out.println("=========wanghao1============");
						Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
						tmptask = (ITask)tmpObjArr[0];
						Iterator it = columnMap.keySet().iterator();
						int j = 0;
						String elementKey;
						Object tempcolumn;
						for (; it.hasNext(); tmptaskMap.put(columnMap.get(elementKey), tempcolumn))
						{
							j++;
							elementKey = (String)it.next();
							tempcolumn = tmpObjArr[j];
							conditionMap.put(elementKey, tempcolumn);
						}

					} else
					{
						System.out.println("=========wanghao1============");
						Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
						tmptask = (ITask)tmpObjArr[2];
						Iterator it = columnMap.keySet().iterator();
						int j = 0;
						String elementKey;
						Object tempcolumn;
						for (; it.hasNext(); tmptaskMap.put(columnMap.get(elementKey), tempcolumn))
						{
							elementKey = (String)it.next();
							tempcolumn = tmpObjArr[j];
							conditionMap.put(elementKey, tempcolumn);
							j++;
						}

					}
				} else
				{
					tmptask = (ITask)taskOvertimeList.get(i);
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
		Integer overTimeTaskCount = getTaskService().getOverTimeTaskCount(condition, userId, deptId);
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
		request.setAttribute("overTimeTaskCount", overTimeTaskCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "list");
		request.setAttribute("module", mapping.getPath().substring(1));
		String workflowName = getMainService().getFlowTemplateName();
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName, getRoleConfigPath());
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

	public void showListUndoAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ISafetyProblemMainManager mainService = (ISafetyProblemMainManager)ApplicationContextHolder.getInstance().getBean("iSafetyProblemMainManager");
		ISafetyProblemTaskManager taskService = (ISafetyProblemTaskManager)ApplicationContextHolder.getInstance().getBean("isafetyproblemTaskManager");
		ITask safetyProblemTask = (ITask)ApplicationContextHolder.getInstance().getBean("SafetyProblemTask");
		HashMap filterMap = new HashMap();
		filterMap.put("TEMPLATE_NAME", mainService.getFlowTemplateName());
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		Integer pageSizeAgent = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexNameAgent = (new ParamEncoder("taskListAgent")).encodeParameterName("p");
		Integer pageIndexAgent = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexNameAgent)) ? 0 : Integer.parseInt(request.getParameter(pageIndexNameAgent)) - 1);
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
		String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
		String orderCondition = "";
		String orderAgent = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskListAgent")).encodeParameterName("o")));
		String sortAgent = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskListAgent")).encodeParameterName("s")));
		String orderConditionAgent = "";
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
		
		if (!orderAgent.equals(""))
			if (orderAgent.equals("1"))
				orderAgent = " asc";
			else
				orderAgent = " desc";
		if (!sortAgent.equals(""))
			orderConditionAgent = " " + sortAgent + orderAgent;
		String exportTypeAgent = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskListAgent")).encodeParameterName("e")));
		if (!exportTypeAgent.equals(""))
			pageSizeAgent = new Integer(-1);
		
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
		BaseMain mainObject = (BaseMain)mainService.getMainObject().getClass().newInstance();
		ITask taskObject = safetyProblemTask;
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		//condition.put("orderCondition", orderCondition);
		List taskList = new ArrayList();
		Integer pageIndexFinal = new Integer(0);
		Integer pageSizeFinal = new Integer(0);
		String exportTypeFinal = "";
		for (int k = 0; k < 2; k++)
		{
			if (k == 0){
				pageIndexFinal = pageIndexAgent;
				condition.put("orderCondition", orderConditionAgent);
				pageSizeFinal = pageSizeAgent;
				exportTypeFinal = exportTypeAgent;
			}else{
				pageIndexFinal = pageIndex;
				condition.put("orderCondition", orderCondition);
				pageSizeFinal = pageSize;
				exportTypeFinal = exportType;
			}
			condition.put("type", (new Integer(k)).toString());
			condition.put("ifAgent", (new Integer(k)).toString());
			String flowName = mainService.getFlowTemplateName();
			HashMap taskListOvertimeMap = taskService.getUndoTaskByOverTime(condition, userId, deptId, flowName, pageIndexFinal, pageSizeFinal);
			int total = ((Integer)taskListOvertimeMap.get("taskTotal")).intValue();
			List taskOvertimeList = (List)taskListOvertimeMap.get("taskList");
			List taskMapList = new ArrayList();
			if (taskOvertimeList != null && taskOvertimeList.size() > 0)
			{
				IOvertimeTipManager service = (IOvertimeTipManager)ApplicationContextHolder.getInstance().getBean("iOvertimeTipManager");
				List timeList = service.getEffectOvertimeTip(mainService.getFlowTemplateName(), userId);
				HashMap columnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
				System.out.println("wanghao1====cloumnMap:" + columnMap.size());
				HashMap columnMapOverTip = OvertimeTipUtil.getNotOverTimeColumnByMapping(flowName);
				for (int i = 0; i < taskOvertimeList.size(); i++)
				{
					ITask tmptask = null;
					Map taskMap = new HashMap();
					Map tmptaskMap = new HashMap();
					HashMap conditionMap = new HashMap();
					if (columnMap.size() > 0)
					{
						if (k == 1)
						{
							System.out.println("=========wanghao1============");
							Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
							tmptask = (ITask)tmpObjArr[0];
							Iterator it = columnMap.keySet().iterator();
							int j = 0;
							String elementKey;
							Object tempcolumn;
							for (; it.hasNext(); tmptaskMap.put(columnMap.get(elementKey), tempcolumn))
							{
								j++;
								elementKey = (String)it.next();
								tempcolumn = tmpObjArr[j];
								conditionMap.put(elementKey, tempcolumn);
							}

						} else
						{
							System.out.println("=========wanghao1============");
							Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
							tmptask = (ITask)tmpObjArr[2];
							Iterator it = columnMap.keySet().iterator();
							int j = 0;
							String elementKey;
							Object tempcolumn;
							for (; it.hasNext(); tmptaskMap.put(columnMap.get(elementKey), tempcolumn))
							{
								elementKey = (String)it.next();
								tempcolumn = tmpObjArr[j];
								conditionMap.put(elementKey, tempcolumn);
								j++;
							}

						}
					} else
					{
						tmptask = (ITask)taskOvertimeList.get(i);
					}
					if (exportTypeFinal.equals(""))
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

		Integer overTimeTaskCount = taskService.getOverTimeTaskCount(condition, userId, deptId);
		request.setAttribute("overTimeTaskCount", overTimeTaskCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "listall");
		request.setAttribute("module", mapping.getPath().substring(1));
		String workflowName = mainService.getFlowTemplateName();
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName, "classpath:config/safetyproblem-config.xml");
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
	}

	public void showListUndoPersonal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ISafetyProblemMainManager mainService = (ISafetyProblemMainManager)ApplicationContextHolder.getInstance().getBean("iSafetyProblemMainManager");
		ISafetyProblemTaskManager taskService = (ISafetyProblemTaskManager)ApplicationContextHolder.getInstance().getBean("isafetyproblemTaskManager");
		ITask safetyProblemTask = (ITask)ApplicationContextHolder.getInstance().getBean("SafetyProblemTask");
		HashMap filterMap = new HashMap();
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
		ITask taskObject = safetyProblemTask;
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		List taskList = new ArrayList();
		for (int k = 0; k < 2; k++)
		{
			condition.put("type", (new Integer(0)).toString());
			//condition.put("ifAgent", (new Integer(k)).toString());
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
				System.out.println("wanghao1====cloumnMap:" + columnMap.size());
				HashMap columnMapOverTip = OvertimeTipUtil.getNotOverTimeColumnByMapping(flowName);
				for (int i = 0; i < taskOvertimeList.size(); i++)
				{
					ITask tmptask = null;
					Map taskMap = new HashMap();
					Map tmptaskMap = new HashMap();
					HashMap conditionMap = new HashMap();
					if (columnMap.size() > 0)
					{
						System.out.println("=========wanghao1============");
						Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
						tmptask = (ITask)tmpObjArr[2];
						Iterator it = columnMap.keySet().iterator();
						int j = 0;
						String elementKey;
						Object tempcolumn;
						for (; it.hasNext(); tmptaskMap.put(columnMap.get(elementKey), tempcolumn))
						{
							elementKey = (String)it.next();
							tempcolumn = tmpObjArr[j];
							conditionMap.put(elementKey, tempcolumn);
							j++;
						}

					} else
					{
						tmptask = (ITask)taskOvertimeList.get(i);
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

		Integer overTimeTaskCount = taskService.getOverTimeTaskCount(condition, userId, deptId);
		request.setAttribute("overTimeTaskCount", overTimeTaskCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "listall");
		request.setAttribute("module", mapping.getPath().substring(1));
		String workflowName = mainService.getFlowTemplateName();
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName, "classpath:config/safetyproblem-config.xml");
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
	}
}
