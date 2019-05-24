// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ComplaintDubanMethod.java

package com.boco.eoms.sheet.complaintDuban.webapp.action;

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

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.service.impl.BusinessFlowServiceImpl;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanLink;
import com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanMain;
import com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanTask;
import com.boco.eoms.sheet.complaintDuban.service.IComplaintDubanLinkManager;
import com.boco.eoms.sheet.complaintDuban.service.IComplaintDubanMainManager;
import com.boco.eoms.sheet.complaintDuban.service.IComplaintDubanTaskManager;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

public class ComplaintDubanMethod extends BaseSheet
{

	public ComplaintDubanMethod()
	{
	}

	public String getPageColumnName()
	{
		return super.getPageColumnName() + ",toMorePhaseId@java.lang.String,supplier1Performer@java.lang.String,supplier1PerformerLeader@java.lang.String," + "supplier1PerformerType@java.lang.String,supplier2Performer@java.lang.String,supplier2PerformerLeader@java.lang.String,supplier2PerformerType@java.lang.String," + "supplier1CorrKey@java.lang.String,supplier2CorrKey@java.lang.String";
	}

	public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		HashMap hashMap = new HashMap();
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
		HashMap sheetMap = new HashMap();
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
		BaseMain main = (BaseMain)getMainService().getMainObject().getClass().newInstance();
		if (!sheetKey.equals(""))
			sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		if (!sheetKey.equals(""))
			main = getMainService().getSingleMainPO(sheetKey);
		sheetMap.put("main", main);
		sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
		sheetMap.put("operate", getPageColumnName());
		hashMap.put("selfSheet", sheetMap);
		return hashMap;
	}

	public Map getProcessOjbectAttribute()
	{
		Map attributeMap = new HashMap();
		attributeMap.put("dealPerformer", "dealPerformer");
		attributeMap.put("copyPerformer", "copyPerformer");
		attributeMap.put("auditPerformer", "auditPerformer");
		attributeMap.put("objectName", "operate");
		return attributeMap;
	}

	public Map getParameterMap()
	{
		return getParameterMap();
	}

	public Map getAttachmentAttributeOfOjbect()
	{
		Map objectMap = new HashMap();
		List mainAttachmentAttributes = new ArrayList();
		mainAttachmentAttributes.add("sheetAccessories");
		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
	}

	public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.showInputDealPage(mapping, form, request, response);
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"), "");
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		if (taskName.equals(""))
			taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		if (taskName.equals("RejectTask") || taskName.equals("AcceptTask") || taskName.equals("ExplorateTask") || taskName.equals("MakeTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask"))
			super.setParentTaskOperateWhenRejct(request);
	}

	public String getProcessTemplateName()
	{
		return "ComplaintDubanProcesses";
	}

	public String getSheetAttachCode()
	{
		return "complaintDuban";
	}

	public Map initMap(Map map, List attach, String type)
		throws Exception
	{
		return null;
	}

	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.dealFlowEngineMap(mapping, form, request, response);
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		HashMap sheetMap = getFlowEngineMap();
		Map operate = (HashMap)sheetMap.get("operate");
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		Map mainMap = (HashMap)sheetMap.get("main");
		Map linkMap = (HashMap)sheetMap.get("link");
		String dealperformers[] = StaticMethod.nullObject2String(operate.get("dealPerformer")).split(",");
		String toMorePhaseIds[] = StaticMethod.nullObject2String(operate.get("toMorePhaseId")).split(",");
		if (taskName.equals("reply") || taskName.equals("advice"))
		{
			Map link = (HashMap)sheetMap.get("link");
			link.put("id", "");
			sheetMap.put("link", link);
		}
		if (dealperformers.length > 1)
		{
			String corrKey = "";
			String tmp = "";
			for (int i = 0; i < dealperformers.length; i++)
			{
				tmp = UUIDHexGenerator.getInstance().getID();
				if (dealperformers.length == 1)
					corrKey = tmp;
				else
				if (corrKey.equals(""))
					corrKey = tmp;
				else
					corrKey = corrKey + "," + tmp;
			}

			System.out.println("corrKey" + corrKey);
			operate.put("extendKey1", corrKey);
			sheetMap.put("operate", operate);
		}
		System.out.println("main=" + mainMap);
		String mainid = StaticMethod.nullObject2String(mainMap.get("id"));
		System.out.println("lizhi:mainid=" + mainid);
		IComplaintDubanTaskManager icomplaintdubantaskmanager = (IComplaintDubanTaskManager)ApplicationContextHolder.getInstance().getBean("iComplaintDubanTaskManager");
		if (operateType != null && "4".equals(operateType) && !"".equals(mainid))
		{
			String condition = "ifWaitForSubTask = 'true' and taskStatus = '8' and taskName = 'Assessor' and sheetKey ='" + mainid + "'";
			System.out.println("lizhi:condition=" + condition);
			List tasklist = icomplaintdubantaskmanager.getTasksByCondition(condition);
			if (tasklist.size() > 0)
			{
				ComplaintDubanTask complaintdubantask = (ComplaintDubanTask)tasklist.get(tasklist.size() - 1);
				complaintdubantask.setIfWaitForSubTask("false");
				icomplaintdubantaskmanager.addTask(complaintdubantask);
			}
		}
		if (operateType != null && "11".equals(operateType) && !"".equals(mainid))
		{
			String condition = "ifWaitForSubTask = 'true' and taskStatus = '8' and taskName = 'Assessor' and sheetKey ='" + mainid + "'";
			System.out.println("lizhi:condition=" + condition);
			List tasklist = icomplaintdubantaskmanager.getTasksByCondition(condition);
			if (tasklist.size() > 0)
			{
				ComplaintDubanTask complaintdubantask = (ComplaintDubanTask)tasklist.get(tasklist.size() - 1);
				complaintdubantask.setIfWaitForSubTask("false");
				complaintdubantask.setReplaceWaitForSubTask("true");
				icomplaintdubantaskmanager.addTask(complaintdubantask);
			}
		}
		if(null != operateType&&"6".equals(operateType)&&!"".equals(mainid)){
			String condition = "replaceWaitForSubTask = 'true' and taskStatus = '8' and taskName = 'Assessor' and sheetKey ='" + mainid + "'";
			System.out.println("lizhi:condition=" + condition);
			List tasklist = icomplaintdubantaskmanager.getTasksByCondition(condition);
			if (tasklist.size()>0) {
				ComplaintDubanTask complaintdubantask = (ComplaintDubanTask)tasklist.get(tasklist.size()-1);
				complaintdubantask.setReplaceWaitForSubTask("false");
				icomplaintdubantaskmanager.addTask(complaintdubantask);
			}
		}
		if(null != operateType&&"7".equals(operateType)&&!"".equals(mainid)){
			String condition = "replaceWaitForSubTask = 'true' and taskStatus = '8' and taskName = 'Assessor' and sheetKey ='" + mainid + "'";
			System.out.println("lizhi:condition=" + condition);
			List tasklist = icomplaintdubantaskmanager.getTasksByCondition(condition);
			if (tasklist.size()>0) {
				ComplaintDubanTask complaintdubantask = (ComplaintDubanTask)tasklist.get(tasklist.size()-1);
				complaintdubantask.setIfWaitForSubTask("true");
				complaintdubantask.setReplaceWaitForSubTask("false");
				icomplaintdubantaskmanager.addTask(complaintdubantask);
			}
		}
		setFlowEngineMap(sheetMap);
	}

	public void autoPerformCreateSubTask(String sheetId, String operateDeptId)
		throws Exception
	{
		IComplaintDubanMainManager mainservice = (IComplaintDubanMainManager)ApplicationContextHolder.getInstance().getBean("iComplaintDubanMainManager");
		IComplaintDubanLinkManager linkservice = (IComplaintDubanLinkManager)ApplicationContextHolder.getInstance().getBean("iComplaintDubanLinkManager");
		IComplaintDubanTaskManager taskservice = (IComplaintDubanTaskManager)ApplicationContextHolder.getInstance().getBean("iComplaintDubanTaskManager");
		ITawSystemUserManager tawsystemusermanager = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		System.out.println("lizhi:sheetId=" + sheetId);
		ComplaintDubanMain main = (ComplaintDubanMain)mainservice.getMainBySheetId(sheetId);
		System.out.println("lizhi:mainid=" + StaticMethod.nullObject2String(main.getId()));
		Calendar calendar = Calendar.getInstance();
		String TKID = "";
		TawSystemUser tawsystemuser = tawsystemusermanager.getTawSystemUserByuserid("fangmin");
		String password = StaticMethod.nullObject2String(tawsystemuser.getPassword());
		String mobile = StaticMethod.nullObject2String(tawsystemuser.getMobile());
		try
		{
			ComplaintDubanTask task = (ComplaintDubanTask)taskservice.getTask(main.getId(), "Assessor");
			TKID = task.getId();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		ComplaintDubanLink linkbean = new ComplaintDubanLink();
		try
		{
			linkbean.setId(UUIDHexGenerator.getInstance().getID());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		linkbean.setMainId(StaticMethod.nullObject2String(main.getId()));
		calendar.add(13, 10);
		linkbean.setOperateTime(calendar.getTime());
		linkbean.setOperateUserId("fangmin");
		linkbean.setToOrgType(new Integer(0));
		linkbean.setAcceptFlag(new Integer(1));
		linkbean.setCompleteFlag(new Integer(1));
		linkbean.setOperateType(new Integer(61));
		linkbean.setOperateDay(calendar.get(5));
		linkbean.setOperateMonth(calendar.get(2) + 1);
		linkbean.setOperateYear(calendar.get(1));
		linkbean.setOperateDeptId("12201");
		linkbean.setOperaterContact(mobile);
		linkbean.setToOrgRoleId(operateDeptId);
		linkbean.setActiveTemplateId("Assessor");
		linkbean.setAiid(TKID);
		try
		{
			linkservice.addLink(linkbean);
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		String dealPerformer = operateDeptId;
		String dealPerformerLeader = operateDeptId;
		String dealPerformerType = "dept";
		String subTtaskName = "ComplaintDubanSubTask";
		String parentTaskName = "Assessor";
		String taskNamespace = "http://ComplaintDubanProcesses";
		String operateType = "10";
		HashMap sessionMap = new HashMap();
		sessionMap.put("userId", "12201");
		sessionMap.put("password", password);
		ComplaintDubanLink commitbean = new ComplaintDubanLink();
		try
		{
			commitbean.setId(UUIDHexGenerator.getInstance().getID());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		commitbean.setMainId(StaticMethod.nullObject2String(main.getId()));
		calendar.add(13, 30);
		commitbean.setOperateTime(calendar.getTime());
		commitbean.setOperateType(new Integer(10));
		commitbean.setOperateDay(calendar.get(5));
		commitbean.setOperateMonth(calendar.get(2) + 1);
		commitbean.setOperateYear(calendar.get(1));
		commitbean.setOperateUserId("fangmin");
		commitbean.setOperateDeptId("12201");
		commitbean.setOperaterContact(mobile);
		commitbean.setToOrgRoleId(operateDeptId);
		commitbean.setToOrgType(new Integer(0));
		commitbean.setAcceptFlag(new Integer(1));
		commitbean.setCompleteFlag(new Integer(1));
		commitbean.setActiveTemplateId("Assessor");
		commitbean.setAiid(TKID);
		HashMap map = new HashMap();
		map = SheetBeanUtils.bean2Map(commitbean);
		String dealPerformers[] = dealPerformer.split(",");
		String dealPerformerLeaders[] = dealPerformerLeader.split(",");
		String dealPerformerTypes[] = dealPerformerType.split(",");
		for (int i = 0; i < dealPerformers.length; i++)
		{
			map.put("dealPerformer", dealPerformers[i]);
			map.put("dealPerformerLeader", dealPerformerLeaders[i]);
			map.put("dealPerformerType", dealPerformerTypes[i]);
			map.put("taskName", subTtaskName);
			map.put("taskNamespace", taskNamespace);
			map.put("parentTaskName", parentTaskName);
			map.put("operateType", operateType);
			map.put("dealPerformer", dealPerformers[i]);
			BusinessFlowServiceImpl businessFlowService = (BusinessFlowServiceImpl)ApplicationContextHolder.getInstance().getBean("businessFlowService");
			System.out.println("lizhi:TKID=" + TKID);
			businessFlowService.createSubTask(TKID, map, sessionMap);
		}

	}
	
	public void showListUndo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap filterMap = new HashMap();
		filterMap.put("TEMPLATE_NAME", getMainService().getFlowTemplateName());

		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		/** add by qinmin* */

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
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject()
				.getClass().newInstance();
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
		String flowName = this.getMainService().getFlowTemplateName();
		HashMap taskListOvertimeMap = this.getTaskService()
				.getUndoTaskByOverTime(condition, userId, deptId, flowName,
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
			List timeList = service.getEffectOvertimeTip(this.getMainService()
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
					tmptask = (ITask) tmpObjArr[columnMap.size()];
					//tmptask = (ITask) tmpObjArr[0];
					// 根据角色细分得到需要匹配的字段
					Iterator it = columnMap.keySet().iterator();
					int j = 0;
					while (it.hasNext()) {
						String elementKey = (String) it.next();
						Object tempcolumn = tmpObjArr[j];
						conditionMap.put(elementKey, tempcolumn);
						tmptaskMap.put(columnMap.get(elementKey), tempcolumn);
						j++;
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
		//Integer overTimeTaskCount = this.getTaskService().getOverTimeTaskCount(condition, userId, deptId);
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
		//request.setAttribute("overTimeTaskCount", overTimeTaskCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "list");
		request.setAttribute("module", mapping.getPath().substring(1));

		// 找出该流程中的节点
		String workflowName = this.getMainService().getFlowTemplateName();
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				workflowName, this.getRoleConfigPath());
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
	}
	
	/**
	 * 工单的详细信息页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
		if (sheetKey.equals("")) {
			sheetKey = StaticMethod.nullObject2String(request.getAttribute("sheetKey"), "");
		}
		String taskId = StaticMethod.nullObject2String(request.getParameter("taskId"), "");
		if (taskId.equals("")) {
			taskId = StaticMethod.nullObject2String(request.getAttribute("taskId"), "");
		}
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"), "");
		if (taskName.equals("")) {
			taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"), "");
		}
		String piid = StaticMethod.nullObject2String(request.getParameter("piid"), "");
		if (piid.equals("")) {
			piid = StaticMethod.nullObject2String(request.getAttribute("piid"),"");
		}
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"), "");
		if (operateRoleId.equals("")) {
			operateRoleId = StaticMethod.nullObject2String(request.getAttribute("operateRoleId"), "");
		}
		String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"), "");
		if (taskStatus.equals("")) {
			taskStatus = StaticMethod.nullObject2String(request.getAttribute("taskStatus"), "");
		}
		String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
		if (preLinkId.equals("")) {
			preLinkId = StaticMethod.nullObject2String(request.getAttribute("preLinkId"), "");
		}
		String TKID = StaticMethod.nullObject2String(request.getParameter("TKID"));
		if (TKID.equals("")) {
			TKID = StaticMethod.nullObject2String(request.getAttribute("TKID"),"");
		}
		/**同组处理模式标志。**/
		String teamFlag=StaticMethod.nullObject2String(request
				.getParameter("teamFlag"));
		BocoLog.debug(this, "sheetKey==" + sheetKey);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		if (!sheetKey.equals("")) {
			BaseMain mainObject = null;
			mainObject = (BaseMain) request.getAttribute("main");
			if (mainObject == null) {
				mainObject = getMainService().getSingleMainPO(sheetKey);
			}
			//BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			String parentSheetKey = StaticMethod.nullObject2String(mainObject
					.getParentSheetId());
			String parentBeanId = StaticMethod.nullObject2String(mainObject
					.getParentSheetName());
			System.out.println("parentSheetKey=" + parentSheetKey
					+ "parentBeanId=" + parentBeanId);
			if (!parentSheetKey.equals("") && !parentBeanId.equals("")) {
				IMainService parentMainSerivce = (IMainService) ApplicationContextHolder
						.getInstance().getBean(parentBeanId);
				BaseMain parentMain = parentMainSerivce.getSingleMainPO(parentSheetKey);
				String parentSheetId = parentMain.getSheetId();

				ITawSystemWorkflowManager mgr = (ITawSystemWorkflowManager) ApplicationContextHolder
						.getInstance().getBean("ITawSystemWorkflowManager");
				TawSystemWorkflow workflow = mgr
						.getTawSystemWorkflowByBeanId(parentBeanId);
				String parentProcessCnName = workflow.getRemark();

				request.setAttribute("parentSheetId", parentSheetId);
				request
						.setAttribute("parentProcessCnName",
								parentProcessCnName);

				System.out.println("parentSheetId=" + parentSheetId
						+ "parentProcessCnName=" + parentProcessCnName);
			}
			request.setAttribute("sheetMain", mainObject);
		}

		String dealTemplateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"));
		if (dealTemplateId != null && !dealTemplateId.equals("")) {
			request.setAttribute("dealTemplateId", dealTemplateId);
			String operateType = StaticMethod.nullObject2String(request
					.getParameter("operateType"));
			request.setAttribute("operateType", operateType);
		}

		// List list = getLinkService().getLinksByMainId(sheetKey);
		// add by leo 草稿需要传入mainId，以便于在wps中修改main对象（DB）
		HashMap sessionMap = new HashMap();
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		if (!TKID.equals("")
				&& (taskStatus.equals(Constants.TASK_STATUS_READY)
						|| taskStatus.equals(Constants.TASK_STATUS_RUNNING) || taskStatus
						.equals(Constants.TASK_STATUS_CLAIMED))) {
			ComplaintDubanTask task = null;
			try {
				task = (ComplaintDubanTask) request.getAttribute("task");
				if (task == null) {
					task = (ComplaintDubanTask)this.getTaskService().getSinglePO(TKID);
				}
				//task = this.getTaskService().getSinglePO(TKID);
				String isWaitForSubTask = task.getIfWaitForSubTask();
				String replaceWaitForSubTask = StaticMethod.nullObject2String(task.getReplaceWaitForSubTask());
				if (replaceWaitForSubTask.equals("true")) {
					List subTaskList = this.getTaskService()
							.getUndealTaskListByParentTaskId(task.getId());
					if (subTaskList != null && subTaskList.size() > 0) {
						request.setAttribute("needDealReply", "true");
					}
				}
				request.setAttribute("task", task);
			} catch (Exception e) {
				request.setAttribute("task", null);
			}
		} else {
			request.setAttribute("task", null);
		}
		
		if(!operateRoleId.equals("")){
			ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemSubRoleManager");
			TawSystemSubRole subrole = mgr.getTawSystemSubRole(operateRoleId);
			if (subrole != null) {
				System.out.println("==roleId==>>" + subrole.getRoleId() + "");
	 			request.setAttribute("roleId", subrole.getRoleId() + "");
			}
		}
		
		
		request.setAttribute("mainId", sheetKey);
		request.setAttribute("taskId", taskId);
		request.setAttribute("taskName", taskName);
		request.setAttribute("operateRoleId", operateRoleId);
		request.setAttribute("operateUserId", sessionform.getUserid());
		request.setAttribute("operateDeptId", sessionform.getDeptid());
		request.setAttribute("operaterContact", sessionform.getContactMobile());
		request.setAttribute("piid", piid);
		request.setAttribute("TKID", TKID);
		//request.setAttribute("taskStatus", taskStatus);
		request.setAttribute("preLinkId", preLinkId);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		request.setAttribute("teamFlag", teamFlag);
		// this.performCreateSubTask(mapping, form, request, response);
		// request.setAttribute("sheetLinks",list);

	}
	
	public void performDealReplyAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map parameterMap = request.getParameterMap();
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"), "");

		String toTaskId = StaticMethod.nullObject2String(request
				.getParameter("toTaskId"), "");

		String[] toTaskIds = toTaskId.split(",");
		ComplaintDubanTask selfTask = (ComplaintDubanTask)this.getTaskService().getSinglePO(TKID);
		List list = this.getTaskService().getUndealTaskListByParentTaskId(TKID);
		Date nowDate = new Date();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		for (int i = 0; i < toTaskIds.length; i++) {
			ITask task = this.getTaskService().getSinglePO(toTaskIds[i]);
			BaseLink linkObject = (BaseLink) (getLinkService().getLinkObject()
					.getClass()).newInstance();
			String id = UUIDHexGenerator.getInstance().getID();
			linkObject.setId(id);
			linkObject.setToOrgRoleId(task.getOperateRoleId());
			linkObject.setToOrgUserId(task.getTaskOwner());
			linkObject.setOperateTime(nowDate);
			linkObject.setOperateUserId(selfTask.getTaskOwner());
			Integer operateType = new Integer(1);
			if (selfTask.getOperateType().equals("subrole")) {
				operateType = new Integer(2);
			} else if (selfTask.getOperateType().equals("user")) {
				operateType = new Integer(3);
			}
			linkObject.setOperateType(operateType);
			linkObject.setOperateDeptId(sessionform.getDeptid());
			Integer toType = new Integer(1);
			if (task.getOperateType().equals("subrole")) {
				toType = new Integer(2);
			} else if (task.getOperateType().equals("user")) {
				toType = new Integer(3);
			}
			linkObject.setToOrgType(toType);
			task.setSubTaskDealFalg("true");
			SheetBeanUtils.populateMap2Bean(linkObject, parameterMap);
			getLinkService().addLink(linkObject);
			getTaskService().addTask(task);
		}
		// 查询没有回复的任务
		List notReversions = this.getTaskService()
				.getNotReversionListByParentTaskId(TKID);
		if (list != null && notReversions.size() == 0) {
			selfTask.setIfWaitForSubTask("false");
			getTaskService().addTask(selfTask);
		}
		selfTask.setReplaceWaitForSubTask("false");
		getTaskService().addTask(selfTask);
	}
	
	public void performDealReplyReject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.performCreateSubTask(mapping, form, request, response);
		String toTaskId = StaticMethod.nullObject2String(request
				.getParameter("toTaskId"));
		String[] toTaskIds = toTaskId.split(",");
		for (int i = 0; toTaskIds.length > 0 && i < toTaskIds.length; i++) {
			ITask task = this.getTaskService().getSinglePO(toTaskIds[i]);
			task.setSubTaskDealFalg("true");
			getTaskService().addTask(task);
		}
		
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		ComplaintDubanTask selfTask = (ComplaintDubanTask)this.getTaskService().getSinglePO(TKID);
		selfTask.setIfWaitForSubTask("true");
		selfTask.setReplaceWaitForSubTask("false");
		getTaskService().addTask(selfTask);
		
	}
	
}
