// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ComplaintSheetMethodHB.java

package com.boco.eoms.sheet.complaint.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.flowdefine.xml.*;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;
import com.boco.eoms.sheet.complaint.service.IComplaintTaskManager;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.displaytag.util.ParamEncoder;
import com.boco.eoms.sheet.complaint.service.bo.AiNetClient;


// Referenced classes of package com.boco.eoms.sheet.complaint.webapp.action:
//			ComplaintSheetMethod

public class ComplaintSheetMethodHB extends ComplaintSheetMethod
{

	public ComplaintSheetMethodHB()
	{
	}

	public void showListUndoAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		IComplaintMainManager mainService = (IComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
		IComplaintTaskManager taskService = (IComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
		ITask complaintTask = (ITask)ApplicationContextHolder.getInstance().getBean("ComplaintTask");
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
		ITask taskObject = complaintTask;
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		List taskList = new ArrayList();
		int time = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime"));
		Integer pageIndexFinal = new Integer(0);
		Integer pageSizeFinal = new Integer(0);
		String exportTypeFinal = "";
		for (int k = 0; k < 2; k++)
		{
			if (k == 0)
			{
				pageIndexFinal = pageIndexAgent;
				condition.put("orderCondition", orderConditionAgent);
				pageSizeFinal = pageSizeAgent;
				exportTypeFinal = exportTypeAgent;
			} else
			{
				pageIndexFinal = pageIndex;
				condition.put("orderCondition", orderCondition);
				pageSizeFinal = pageSize;
				exportTypeFinal = exportType;
			}
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
						tmptask = (ITask)taskOvertimeList.get(i);
					}
					if (exportTypeFinal.equals(""))
					{
						String overtimeFlag = OvertimeTipUtil.setOvertimeTipFlag(columnMapOverTip, tmptask.getCompleteTimeLimit(), conditionMap, timeList, flowName);
						taskMap.put("overtimeType", overtimeFlag);
					}
					IComplaintMainManager complaintMainManager = (IComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
					String customPhone=StaticMethod.nullObject2String(tmptaskMap.get("customPhone"));
					Date complaintTime=StaticMethod.nullObject2Timestamp(tmptaskMap.get("complaintTime"));
					//by lyg
					if(complaintTime == null){
						complaintTime = new Date();
					}
//					by lyg
					Calendar cal=Calendar.getInstance();
					cal.setTime(complaintTime);
					cal.add(Calendar.DATE, time);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String beforedate = sdf.format(cal.getTime());
					String afterdate = sdf.format(complaintTime );
					System.out.println("lizhi:beforedate="+beforedate+"afterdate="+afterdate+"customPhone="+customPhone);
					int repeatNum = complaintMainManager.getCustomPhoneBySendTime(beforedate,afterdate,customPhone);
					if(repeatNum==0){
						 taskMap.put("customPhoneRepeatCount", "1(无历史投诉)");
					 }else if(repeatNum==1){
						 taskMap.put("customPhoneRepeatCount", "1(无历史投诉)");
					 }else{
						 taskMap.put("customPhoneRepeatCount", repeatNum+"(重复投诉)" );
					 }
					taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
					taskMap.putAll(tmptaskMap);
					taskList.add(tmptask);
					String preLinkId = (String)taskMap.get("preLinkId");
					IDownLoadSheetAccessoriesService accessoriesService = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
					if (preLinkId != null && !"".equals(preLinkId))
					{
						System.out.println("preLinkId===================" + preLinkId);
						String sqlr = "select operateType from complaint_link  link where link.id='" + preLinkId + "'";
						System.out.println("sql==================" + sqlr);
						List oList = accessoriesService.getSheetAccessoriesList(sqlr);
						if (oList != null && oList.size() > 0)
						{
							Map opreMap = (Map)oList.get(0);
							String operateType = StaticMethod.nullObject2String(opreMap.get("operateType"));
							if (operateType != null)
							{
								String taskStatus = (String)taskMap.get("taskStatus");
								if (operateType.equals("4"))
								{
									System.out.println("上步操作为驳回");
									taskMap.put("ifRejectOrYijiao", "1");
									if (taskStatus.equals("2"))
										taskMap.put("taskStatus1", "未接单（驳回）");
									else
										taskMap.put("taskStatus1", "已接单（驳回）");
								}
							}
						}
					}
					String taskStatus = (String)taskMap.get("taskStatus");
					String sheetKey = (String)taskMap.get("sheetKey");
					String taskName = (String)taskMap.get("taskName");
					String sql = "select operateType from complaint_link  link where link.mainId='" + sheetKey + "' and activeTemplateId='" + taskName + "' order by operateTime desc";
					System.out.println("sql==================" + sql);
					List opreList = accessoriesService.getSheetAccessoriesList(sql);
					String operateType = "";
					if (taskStatus.equals("2"))
					{
						if (opreList != null && opreList.size() > 0)
						{
							Map opreMap = (Map)opreList.get(0);
							operateType = StaticMethod.nullObject2String(opreMap.get("operateType"));
							if (operateType.equals("8"))
							{
								taskMap.put("ifRejectOrYijiao", "1");
								taskMap.put("taskStatus1", "未接单（移交）");
							}
						}
					} else
					if (opreList != null && opreList.size() > 1)
					{
						Map opreMap2 = (Map)opreList.get(1);
						operateType = StaticMethod.nullObject2String(opreMap2.get("operateType"));
						if (operateType.equals("8"))
						{
							taskMap.put("ifRejectOrYijiao", "1");
							taskMap.put("taskStatus1", "已接单（移交）");
						}
					}
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
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName, "classpath:config/complaint-config.xml");
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
	
	public String getAiNetResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{   String result="";
	    String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
	    if(!"".equals(sheetKey)){
	   
	    AiNetClient  aiNet = new AiNetClient();
	    result = aiNet.getAiNetResult(sheetKey);
	    }
		return result;
		
	}
}
