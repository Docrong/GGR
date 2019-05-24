// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BusinessChangeMethod.java

package com.boco.eoms.sheet.businesschange.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.UUIDHexGenerator;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSSecutiryServiceImpl;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.businesschange.model.BusinessChangeLink;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;
import java.io.PrintStream;
import java.util.*;

import javax.servlet.http.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BusinessChangeMethod extends BaseSheet {

	public BusinessChangeMethod() {
	}

	public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap hashMap = new HashMap();
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		String operatName = StaticMethod.nullObject2String(request
				.getParameter("operateName"));
		try {
			if (operatName.equals("forceHold")) {
				HashMap map = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("id"));
				if (sheetKey.equals(""))
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				com.boco.eoms.sheet.base.model.BaseMain main = getMainService()
						.getSingleMainPO(sheetKey);
				map.put("main", main);
				map.put("link", getLinkService().getLinkObject().getClass()
						.newInstance());
				map.put("operate", getPageColumnName());
				hashMap.put("selfSheet", map);
			} else if (taskName.equals("")) {
				HashMap sheetMap = new HashMap();
				sheetMap.put("main", getMainService().getMainObject()
						.getClass().newInstance());
				sheetMap.put("link", getLinkService().getLinkObject()
						.getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else if (taskName.equals("DraftHumTask")
					|| taskName.equals("HoldHumTask")
					|| taskName.equals("ByRejectHumTask")
					|| taskName.equals("ExcuteHumTask")) {
				HashMap sheetMap = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("mainId"));
				if (sheetKey.equals(""))
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				com.boco.eoms.sheet.base.model.BaseMain main = getMainService()
						.getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject()
						.getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else if (taskName.equals("advice") || taskName.equals("reply")
					|| taskName.equals("cc")) {
				HashMap sheetMap = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("mainId"));
				if (sheetKey == null || sheetKey.equals(""))
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				com.boco.eoms.sheet.base.model.BaseMain main = getMainService()
						.getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject()
						.getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else {
				System.out.println("其他人工处理.......");
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("mainId"));
				if (sheetKey.equals(""))
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				HashMap sheetMap = new HashMap();
				com.boco.eoms.sheet.base.model.BaseMain main = getMainService()
						.getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject()
						.getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashMap;
	}

	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.dealFlowEngineMap(mapping, form, request, response);
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request
				.getParameter("phaseId"));
		HashMap sheetMap = getFlowEngineMap();
		if (taskName.equals("reply") || taskName.equals("advice")) {
			Map link = (HashMap) sheetMap.get("link");
			link.put("id", "");
			sheetMap.put("link", link);
		}
		String operatName = StaticMethod.nullObject2String(request
				.getParameter("operateName"));
		Map main = (HashMap) sheetMap.get("main");
		Map link = (HashMap) sheetMap.get("link");
		Map operate = (HashMap) sheetMap.get("operate");
		if (!operatName.equals("forceHold")
				&& (taskName.equals("")
						|| taskName.equals("TaskCreateAuditHumTask")
						|| taskName.equals("ExcuteHumTask")
						|| taskName.equals("DraftHumTask")
						|| taskName.equals("ByRejectHumTask") || taskName
						.equals("HoldHumTask"))) {
			String dealperformers[] = StaticMethod.nullObject2String(
					operate.get("dealPerformer")).split(",");
			String auditperformer = StaticMethod.nullObject2String(operate
					.get("auditPerformer"));
			if (dealperformers.length > 0 && auditperformer.equals("")) {
				String corrKey = "";
				String tmp = "";
				for (int i = 0; i < dealperformers.length; i++) {
					tmp = UUIDHexGenerator.getInstance().getID();
					if (dealperformers.length == 1)
						corrKey = tmp;
					else if (corrKey.equals(""))
						corrKey = tmp;
					else
						corrKey = corrKey + "," + tmp;
				}

				System.out.println("corrKey" + corrKey);
				sheetMap.put("corrKey", corrKey);
			}
			sheetMap.put("link", link);
		}
		if (taskName.equals("ExcuteHumTask") && operateType.equals("111")) {
			System.out.println("互调-------taskname is ==========" + taskName);
			String sheetKey = StaticMethod.nullObject2String(request
					.getParameter("sheetKey"));
			System.out.println("互调-------sheetKey is ==========" + sheetKey);
			if (sheetKey != null || !sheetKey.equals("")) {
				ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
						.getInstance().getBean("ITawSheetRelationManager");
				List relationAllList = rmgr
						.getRunningSheetByParentIdAndPhaseId(sheetKey, taskName);
				System.out.println("互调-------relationAllList is =========="
						+ relationAllList.size());
				if (relationAllList != null && relationAllList.size() > 0) {
					link.put("operateType", new Integer(111));
					operate.put("phaseId", "receiveNet");
					sheetMap.put("operate", operate);
					sheetMap.put("link", link);
					System.out.println("互调-------被执行了！！！！！");
				}
			}
		}
		System.out.println("main=" + main);
		if (main != null&& StaticMethod.nullObject2String(main.get("isCustomerFlag")).equalsIgnoreCase("1")) {
			String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessChange-crm.xml").getProperty("base.SendImmediately"));
			if (!sendImmediately.equalsIgnoreCase("true")) {
				String taskId = StaticMethod.nullObject2String(request
						.getParameter("TKID"));
				ITaskService iTaskService = getTaskService();
				ITask task = iTaskService.getSinglePO(taskId);
				TawSystemSessionForm sessionform = (TawSystemSessionForm) request
						.getSession().getAttribute("sessionform");
				WPSSecutiryServiceImpl safeService = new WPSSecutiryServiceImpl();
				javax.security.auth.Subject subject = safeService.logIn(
						sessionform.getUserid(), "1");
				HashMap sessionMap = new HashMap();
				sessionMap.put("wpsSubject", subject);
				sessionMap.put("userId", sessionform.getUserid());
				sessionMap.put("password", "1");
				Map variableMap = getBusinessFlowService().getVariable(
						task.getProcessId(), "parentCorrKey", sessionMap);
				String parentCorrKey = StaticMethod
						.nullObject2String(variableMap.get("parentCorrKey"));
				String invokeReplyAll = StaticMethod
						.nullObject2String(XmlManage.getFile(
								"/config/businessChange-crm.xml").getProperty(
								"base.InvokeReplyAll"));
				if (taskName.equals("ExcuteHumTask") && operateType.equals("4")) {
					if (!parentCorrKey.equalsIgnoreCase(StaticMethod
							.nullObject2String(main.get("correlationKey")))) {
						if (invokeReplyAll.equalsIgnoreCase("true")) {
							operate.put("interfaceType", "withdrawWorkSheet");
							operate.put("methodType", "withdrawWorkSheet");
						}
					} else {
						operate.put("interfaceType", "withdrawWorkSheet");
						operate.put("methodType", "withdrawWorkSheet");
					}
				} else if (operateType.equals("9")) {
					operate.put("interfaceType", "notifyWorkSheet");
					operate.put("methodType", "notifyWorkSheet");
				} else if (taskName.equals("ExcuteHumTask")
						&& operateType.equals("61")) {
					if (!parentCorrKey.equalsIgnoreCase(StaticMethod
							.nullObject2String(main.get("correlationKey")))) {
						if (invokeReplyAll.equalsIgnoreCase("true")) {
							operate.put("interfaceType", "confirmWorkSheet");
							operate.put("methodType", "confirmWorkSheet");
						}
					} else {
						String preLinkId = StaticMethod
								.nullObject2String(request
										.getParameter("preLinkId"));
						System.out.println("preLinkId=" + preLinkId);
						BaseLink baseLink = getLinkService().getSingleLinkPO(
								preLinkId);
						if (baseLink.getOperateType() == null
								|| baseLink.getOperateType().intValue() == 8) {
							operate.put("interfaceType", "confirmWorkSheet");
							operate.put("methodType", "confirmWorkSheet");
						}
					}
				} else if (operateType.equals("205"))
					if (!parentCorrKey.equalsIgnoreCase(StaticMethod
							.nullObject2String(main.get("correlationKey")))) {
						if (invokeReplyAll.equalsIgnoreCase("true")) {
							operate.put("interfaceType", "replyWorkSheet");
							operate.put("methodType", "replyWorkSheet");
						}
					} else {
						operate.put("interfaceType", "replyWorkSheet");
						operate.put("methodType", "replyWorkSheet");
					}
			}
		}
		sheetMap.put("operate", operate);
		sheetMap.put("link", link);
		sheetMap.put("main", main);
		setFlowEngineMap(sheetMap);
	}

	public Map getAttachmentAttributeOfOjbect() {
		Map objectMap = new HashMap();
		List mainAttachmentAttributes = new ArrayList();
		mainAttachmentAttributes.add("sheetAccessories");
		mainAttachmentAttributes.add("numDetailFile");
		mainAttachmentAttributes.add("appProgramme");
		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
	}

	public Map getProcessOjbectAttribute() {
		Map attributeMap = new HashMap();
		attributeMap.put("dealPerformer", "dealPerformer");
		attributeMap.put("copyPerformer", "copyPerformer");
		attributeMap.put("auditPerformer", "auditPerformer");
		attributeMap.put("subAuditPerformer", "subAuditPerformer");
		attributeMap.put("objectName", "operate");
		return attributeMap;
	}

	public Map getParameterMap() {
		return getParameterMap();
	}

	public void performPreCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		if (taskName.equals(""))
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		if (taskName.equals("ExcuteHumTask") && operateType.equals("111")) {
			ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
					.getInstance().getBean("ITawSheetRelationManager");
			List relationAllList = rmgr.getRunningSheetByParentIdAndPhaseId(
					sheetKey, taskName);
			System.out.println("relationAllList.size() is ========="
					+ relationAllList.size());
			if (relationAllList != null && relationAllList.size() > 0) {
				super.performPreCommit(mapping, form, request, response);
			} else {
				JSONArray data = new JSONArray();
				JSONObject o = new JSONObject();
				o.put("text", "您还没有调用其他流程，不能提交工单！");
				data.put(o);
				JSONObject jsonRoot = new JSONObject();
				jsonRoot.put("data", data);
				jsonRoot.put("status", "2");
				JSONUtil.print(response, jsonRoot.toString());
			}
		} else {
			super.performPreCommit(mapping, form, request, response);
		}
	}

	public void showInputDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showInputDealPage(mapping, form, request, response);
		String taskName = (String) request.getAttribute("taskName");
		System.out.println("taskName is =============" + taskName);
		if(taskName != null && taskName.equals("ExcuteHumTask")){
			String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
			ITaskService iTaskService = getTaskService();
			ITask task = iTaskService.getSinglePO(taskId);
			TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
			WPSSecutiryServiceImpl safeService = new WPSSecutiryServiceImpl();
			javax.security.auth.Subject subject = safeService.logIn(sessionform.getUserid(), "111");
			HashMap sessionMap = new HashMap();
			sessionMap.put("wpsSubject", subject);
			sessionMap.put("userId", sessionform.getUserid());
			sessionMap.put("password", "111");
			Map variableMap = getBusinessFlowService().getVariable(task.getProcessId(), "parentCorrKey", sessionMap);
			String parentCorrKey = StaticMethod.nullObject2String(variableMap.get("parentCorrKey"));
			request.setAttribute("mainparentCorrKey", parentCorrKey);
		}
		if (taskName != null && taskName.equals("TaskCreateAuditHumTask")) {
			BusinessChangeLink link = (BusinessChangeLink) request
					.getAttribute("sheetLink");
			HashMap sessionMap = new HashMap();
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			sessionMap.put("userId", sessionform.getUserid());
			sessionMap.put("password", sessionform.getPassword());
			Map processOjbectAttribute = getProcessOjbectAttribute();
			JSONArray sendUserAndRoles = new JSONArray();
			String objectName = processOjbectAttribute.get("objectName") == null ? ""
					: (String) processOjbectAttribute.get("objectName");
			System.out.println("objectName is =============" + objectName);
			Map parameterValuemap = businessFlowService.getVariable(link
					.getPiid(), objectName, sessionMap);
			processOjbectAttribute.remove("objectName");
			for (Iterator it = processOjbectAttribute.keySet().iterator(); it
					.hasNext();) {
				String dealPerformer = (String) it.next();
				String dealPerformerLeader = dealPerformer + "Leader";
				String dealPerformerType = dealPerformer + "Type";
				String dealPerformerValue = (String) parameterValuemap
						.get(dealPerformer);
				String dealPerformerLeaderValue = (String) parameterValuemap
						.get(dealPerformerLeader);
				String dealPerformerTypeValue = (String) parameterValuemap
						.get(dealPerformerType);
				ID2NameService service = (ID2NameService) ApplicationContextHolder
						.getInstance().getBean("ID2NameGetServiceCatch");
				if (dealPerformerValue != null
						&& !dealPerformerValue.equals("")) {
					String dealPerformerValues[] = dealPerformerValue
							.split(",");
					for (int i = 0; i < dealPerformerValues.length; i++) {
						JSONObject data = new JSONObject();
						String finalDealPerformerValue = dealPerformerValues[i];
						System.out
								.println("finalDealPerformerValue is ============="
										+ finalDealPerformerValue);
						data.put("id", finalDealPerformerValue);
						String finalDealPerformerTypeValue = dealPerformerTypeValue
								.split(",")[i];
						data.put("nodeType", finalDealPerformerTypeValue);
						data.put("categoryId", dealPerformer);
						String finalDealPerformerLeaderValue = dealPerformerLeaderValue
								.split(",")[i];
						data.put("leaderId", finalDealPerformerLeaderValue);
						String name = service.id2Name(
								finalDealPerformerLeaderValue,
								"tawSystemUserDao");
						data.put("leaderName", name);
						sendUserAndRoles.put(data.toString());
					}

				}
			}

			request.setAttribute("sendUserAndRoles", sendUserAndRoles);
		}
	}

	public void showDealInputTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showDealInputTemplate(mapping, form, request, response);
		String taskName = (String) request.getAttribute("taskName");
		System.out.println("taskName is =============" + taskName);
		if (taskName != null && taskName.equals("TaskCreateAuditHumTask")) {
			BusinessChangeLink link = (BusinessChangeLink) request
					.getAttribute("sheetLink");
			HashMap sessionMap = new HashMap();
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			sessionMap.put("userId", sessionform.getUserid());
			sessionMap.put("password", sessionform.getPassword());
			Map processOjbectAttribute = getProcessOjbectAttribute();
			JSONArray sendUserAndRoles = new JSONArray();
			String objectName = processOjbectAttribute.get("objectName") == null ? ""
					: (String) processOjbectAttribute.get("objectName");
			System.out.println("objectName is =============" + objectName);
			String piid = (String) request.getAttribute("piid");
			Map parameterValuemap = businessFlowService.getVariable(piid,
					objectName, sessionMap);
			processOjbectAttribute.remove("objectName");
			for (Iterator it = processOjbectAttribute.keySet().iterator(); it
					.hasNext();) {
				String dealPerformer = (String) it.next();
				String dealPerformerLeader = dealPerformer + "Leader";
				String dealPerformerType = dealPerformer + "Type";
				String dealPerformerValue = (String) parameterValuemap
						.get(dealPerformer);
				String dealPerformerLeaderValue = (String) parameterValuemap
						.get(dealPerformerLeader);
				String dealPerformerTypeValue = (String) parameterValuemap
						.get(dealPerformerType);
				ID2NameService service = (ID2NameService) ApplicationContextHolder
						.getInstance().getBean("ID2NameGetServiceCatch");
				if (dealPerformerValue != null
						&& !dealPerformerValue.equals("")) {
					String dealPerformerValues[] = dealPerformerValue
							.split(",");
					for (int i = 0; i < dealPerformerValues.length; i++) {
						JSONObject data = new JSONObject();
						String finalDealPerformerValue = dealPerformerValues[i];
						System.out
								.println("finalDealPerformerValue is ============="
										+ finalDealPerformerValue);
						data.put("id", finalDealPerformerValue);
						String finalDealPerformerTypeValue = dealPerformerTypeValue
								.split(",")[i];
						data.put("nodeType", finalDealPerformerTypeValue);
						data.put("categoryId", dealPerformer);
						String finalDealPerformerLeaderValue = dealPerformerLeaderValue
								.split(",")[i];
						data.put("leaderId", finalDealPerformerLeaderValue);
						String name = service.id2Name(
								finalDealPerformerLeaderValue,
								"tawSystemUserDao");
						data.put("leaderName", name);
						sendUserAndRoles.put(data.toString());
					}

				}
			}

			request.setAttribute("sendUserAndRoles", sendUserAndRoles);
		}
	}

	public String getSheetAttachCode() {
		return null;
	}

	public Map initMap(Map map, List attach, String type) throws Exception {
		return null;
	}
}
