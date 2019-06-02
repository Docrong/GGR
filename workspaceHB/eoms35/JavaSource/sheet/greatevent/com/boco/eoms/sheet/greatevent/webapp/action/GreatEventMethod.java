// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GreatEventMethod.java

package com.boco.eoms.sheet.greatevent.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;
import java.io.PrintStream;
import java.util.*;
import javax.servlet.http.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GreatEventMethod extends BaseSheet
{

	public GreatEventMethod()
	{
	}

	public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		HashMap hashMap = new HashMap();
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
		System.out.println("operateName is -----------------------" + operatName);
		try
		{
			if (operatName.equals("forceHold"))
			{
				HashMap map = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
				if (sheetKey == null || sheetKey.equals(""))
					sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
				BaseMain main = getMainService().getSingleMainPO(sheetKey);
				map.put("main", main);
				map.put("link", getLinkService().getLinkObject().getClass().newInstance());
				map.put("operate", getPageColumnName());
				hashMap.put("selfSheet", map);
			} else
			if (taskName.equals(""))
			{
				HashMap sheetMap = new HashMap();
				sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else
			if (taskName.equals("DraftTask") || taskName.equals("reject"))
			{
				HashMap sheetMap = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
				if (sheetKey.equals(""))
					sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
				BaseMain main = getMainService().getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else
			if (taskName.equals("advice") || taskName.equals("reply"))
			{
				HashMap sheetMap = new HashMap();
				sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else
			if (taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("ApprovalTask") || taskName.equals("PerformTask") || taskName.equals("AuditEndTask") || taskName.equals("AssessmentTask") || taskName.equals("AuditReportTask") || taskName.equals("ModifyTask") || taskName.equals("HoldTask"))
			{
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
				if (sheetKey.equals(""))
					sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
				HashMap sheetMap = new HashMap();
				BaseMain main = getMainService().getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else
			{
				HashMap sheetMap = new HashMap();
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return hashMap;
	}

	public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.showInputDealPage(mapping, form, request, response);
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		if (taskName.equals(""))
			taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		if (taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("PerformTask") || taskName.equals("ApprovalTask") || taskName.equals("AuditEndTask") || taskName.equals("AssessmentTask") || taskName.equals("AuditReportTask") || taskName.equals("ModifyTask") || taskName.equals("HoldTask"))
			super.setParentTaskOperateWhenRejct(request);
		if (taskName.equals("PerformTask") || taskName.equals("AssessmentTask"))
		{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			ITawSheetRelationManager rmgr = (ITawSheetRelationManager)ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
			List relationAllList = rmgr.getAllSheetByParentIdAndPhaseId(sheetKey, taskName);
			if (relationAllList != null)
			{
				for (int i = 0; i < relationAllList.size(); i++)
				{
					TawSheetRelation relation = (TawSheetRelation)relationAllList.get(i);
					int state = relation.getInvokeState();
					if (state == 1)
					{
						request.setAttribute("ifInvoke", "no");
						break;
					}
					request.setAttribute("ifInvoke", "yes");
				}

			} else
			{
				request.setAttribute("ifInvoke", "no");
			}
		}
	}

	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.dealFlowEngineMap(mapping, form, request, response);
		String roleId = StaticMethod.nullObject2String(request.getParameter("roleId"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
		HashMap sheetMap = getFlowEngineMap();
		setFlowEngineMap(sheetMap);
	}

	public Map getParameterMap()
	{
		return getParameterMap();
	}

	public Map getProcessOjbectAttribute()
	{
		Map attributeMap = new HashMap();
		attributeMap.put("dealPerformer", "dealPerformer");
		attributeMap.put("copyPerformer", "copyPerformer");
		attributeMap.put("auditPerformer", "auditPerformer");
		attributeMap.put("subAuditPerformer", "subAuditPerformer");
		attributeMap.put("objectName", "operate");
		return attributeMap;
	}

	public Map getAttachmentAttributeOfOjbect()
	{
		Map objectMap = new HashMap();
		List mainAttachmentAttributes = new ArrayList();
		mainAttachmentAttributes.add("sheetAccessories");
		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");
		linkAttachmentAttributes.add("linkGreatSecurityProgram");
		linkAttachmentAttributes.add("linkSencePerformReport");
		linkAttachmentAttributes.add("linkSenceSecuritySummary");
		linkAttachmentAttributes.add("linkSenceSecurityReport");
		linkAttachmentAttributes.add("linkAssessReport");
		linkAttachmentAttributes.add("linkEmergencyPlan");
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
	}

	public void showForceHoldPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.showForceHoldPage(mapping, form, request, response);
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
		ITawSheetRelationManager rmgr = (ITawSheetRelationManager)ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
		List relationAllList = rmgr.getRunningSheetByParentId(sheetKey);
		if (relationAllList != null && relationAllList.size() > 0)
			request.setAttribute("invoke", "true");
		else
			request.setAttribute("invoke", "false");
		BaseMain main = getMainService().getSingleMainPO(sheetKey);
		String parentSheetName = main.getParentSheetName();
		String parentSheetKey = main.getParentSheetId();
		if (parentSheetName != null && !parentSheetName.equals(""))
		{
			IMainService parentMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(parentSheetName);
			BaseMain parentMain = parentMainService.getSingleMainPO(parentSheetKey);
			String parentPhaseName = main.getParentPhaseName();
			if (parentPhaseName.indexOf("@") != -1)
			{
				request.setAttribute("parentPiid", parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
				System.out.println("回调：parentProcessId：" + parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
			} else
			{
				request.setAttribute("parentPiid", parentMain.getPiid());
			}
			request.setAttribute("parentMain", parentMain);
			request.setAttribute("parentProcessName", parentSheetName);
		}
	}

	public boolean performIsInvokeProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String tempUserId = "";
		ITask task = null;
		BocoLog.info(this, "operateType is:" + operateType);
		if (taskName.equals(""))
			taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		String taskId = StaticMethod.nullObject2String(request.getParameter("taskId"), "");
		if (taskId != null && !taskId.equals(""))
			task = getTaskService().getSinglePO(taskId);
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		if (sessionform != null)
			tempUserId = sessionform.getUserid();
		if (task != null && !operateType.equals("61"))
		{
			ITawSheetRelationManager mgr = (ITawSheetRelationManager)ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
			List relationAllList = new ArrayList();
			if (taskName.equals("AssessmentTask"))
				relationAllList = mgr.getAllSheetByParentIdAndPhaseId(sheetKey, taskName);
			else
				relationAllList = mgr.getSheetByProcessIdAndUserId(sheetKey, taskName, tempUserId, task.getProcessId());
			System.out.println("sheetKey=" + sheetKey + "==tempUserId=" + tempUserId);
			return relationAllList != null && relationAllList.size() > 0 && !operateType.equals("93") && !operateType.equals("95");
		} else
		{
			return false;
		}
	}

	public void performPreCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		if (taskName.equals(""))
			taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		if (taskName.equals("PerformTask") && (operateType.equals("93") || operateType.equals("111")))
		{
			ITawSheetRelationManager rmgr = (ITawSheetRelationManager)ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
			List relationAllList = rmgr.getRunningSheetByParentIdAndPhaseId(sheetKey, taskName);
			if (operateType.equals("93"))
			{
				if (relationAllList != null && relationAllList.size() > 0)
				{
					JSONArray data = new JSONArray();
					JSONObject o = new JSONObject();
					o.put("text", "你已经调用了其他流程，请在“是否启动变更流程”中选择“是”！");
					data.put(o);
					JSONObject jsonRoot = new JSONObject();
					jsonRoot.put("data", data);
					jsonRoot.put("status", "2");
					JSONUtil.print(response, jsonRoot.toString());
				} else
				{
					super.performPreCommit(mapping, form, request, response);
				}
			} else
			if (relationAllList != null && relationAllList.size() > 0)
			{
				super.performPreCommit(mapping, form, request, response);
			} else
			{
				JSONArray data = new JSONArray();
				JSONObject o = new JSONObject();
				o.put("text", "请选择你要调用的流程！");
				data.put(o);
				JSONObject jsonRoot = new JSONObject();
				jsonRoot.put("data", data);
				jsonRoot.put("status", "2");
				JSONUtil.print(response, jsonRoot.toString());
			}
		} else
		{
			super.performPreCommit(mapping, form, request, response);
		}
	}

	public String getSheetAttachCode()
	{
		return null;
	}

	public Map initMap(Map map, List attach, String type)
		throws Exception
	{
		return null;
	}
}
