package com.boco.eoms.sheet.softchange.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.softchange.dao.ISoftChangeMainDAO;
import com.boco.eoms.sheet.softchange.model.SoftChangeLink;
import com.boco.eoms.sheet.softchange.model.SoftChangeTask;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

public class SoftChangeMethod extends BaseSheet {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		HashMap hashMap = new HashMap();
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		if (taskName.equals("ProjectCreateTask")) {
			String mainIsNeedDesign = StaticMethod.nullObject2String(request
					.getParameter("mainIsNeedDesign"));
			if (mainIsNeedDesign.equals("1030101")) {
				taskName = StaticMethod.nullObject2String(request
						.getParameter("trueActiveTemplateId"));
			}
		}
		String operatName = StaticMethod.nullObject2String(request
				.getParameter("operateName"));
		System.out.println("operateName is -----------------------"
				+ operatName);
		try {
			if (operatName.equals("forceHold")) {
				HashMap map = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("id"));
				if (sheetKey.equals("")) {
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				}
				// BaseMain main =
				// this.getMainService().getMainDAO().loadSinglePO(sheetKey,
				// this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				map.put("main", main);
				map.put("link", getLinkService().getLinkObject());
				map.put("operate", getPageColumnName());
				hashMap.put("selfSheet", map);
			} else if (taskName.equals("")) {
				// 新增工单
				HashMap sheetMap = new HashMap();
				sheetMap.put("main", getMainService().getMainObject()
						.getClass().newInstance());
				sheetMap.put("link", getLinkService().getLinkObject()
						.getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else if (taskName.equals("DraftTask")) {
				// 草稿状态
				try {
					HashMap sheetMap = new HashMap();
					String sheetKey = StaticMethod.nullObject2String(request
							.getParameter("mainId"));
					System.out.println("task is=============" + sheetKey);
					if (sheetKey.equals("")) {
						sheetKey = StaticMethod.nullObject2String(request
								.getParameter("sheetKey"));
					}
					// BaseMain main =
					// this.getMainService().getMainDAO().loadSinglePO(sheetKey,
					// this.getMainService().getMainObject());
					BaseMain main = this.getMainService().getSingleMainPO(
							sheetKey);
					System.out.println("main is=============" + main);
					sheetMap.put("main", main);
					sheetMap.put("link", getLinkService().getLinkObject()
							.getClass().newInstance());
					sheetMap.put("operate", getPageColumnName());
					hashMap.put("selfSheet", sheetMap);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (taskName.equals("HoldTask")) {
				// 待归档
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("mainId"));
				if (sheetKey.equals("")) {
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				}
				HashMap sheetMap = new HashMap();
				// BaseMain main =
				// this.getMainService().getMainDAO().loadSinglePO(sheetKey,
				// this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject()
						.getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else if (taskName.equals("advice") || taskName.equals("reply")
					|| taskName.equals("cc")) {
				HashMap sheetMap = new HashMap();
				sheetMap.put("link", getLinkService().getLinkObject()
						.getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else {
				System.out.println("其他人工处理.......");
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("mainId"));
				if (sheetKey.equals("")) {
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				}
				HashMap sheetMap = new HashMap();
				// BaseMain main =
				// this.getMainService().getMainDAO().loadSinglePO(sheetKey,
				// this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#dealFlowEngineMap(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.dealFlowEngineMap(mapping, form, request, response);
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		if (taskName.equals("ProjectCreateTask")) {
			String mainIsNeedDesign = StaticMethod.nullObject2String(request
					.getParameter("mainIsNeedDesign"));
			if (mainIsNeedDesign.equals("1030101")) {
				taskName = StaticMethod.nullObject2String(request
						.getParameter("trueActiveTemplateId"));
			}
		}
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request
				.getParameter("phaseId"));
		HashMap sheetMap = this.getFlowEngineMap();
		if (taskName.equals("reply") || taskName.equals("advice")) {
			Map link = (HashMap) sheetMap.get("link");
			link.put("id", "");
			sheetMap.put("link", link);
		}

		// 如果是驳回则将驳回次数加1
		if (taskName.equals("PermitTask")
				&& (phaseId.equals("DraftTask") || phaseId
						.equals("ProjectCreateTask"))) {
			Map main = (HashMap) sheetMap.get("main");
			Integer mainrejecttimes = (Integer) main.get("mainrejecttimes");
			mainrejecttimes = new Integer(mainrejecttimes.intValue() + 1);
			main.put("mainrejecttimes", mainrejecttimes);
			sheetMap.put("main", main);
		}
		// 将实施完成时限数据保存到main的mainExecuteEndDate中
		if (taskName.equals("ProjectCreateTask")) {
			Map main = (HashMap) sheetMap.get("main");
			Map link = (HashMap) sheetMap.get("link");
			Map operate = (HashMap) sheetMap.get("operate");
			main.put("mainExecuteEndDate", link.get("linkExecuteEndDate"));
			sheetMap.put("main", main);

			String[] extendPerformers = (StaticMethod.nullObject2String(operate
					.get("extendPerformer"))).split(",");
			if (extendPerformers.length > 1) {
				String linkId = "";
				String tmpLink = "";
				for (int i = 0; i < extendPerformers.length; i++) {
					tmpLink = UUIDHexGenerator.getInstance().getID() + ",";
					linkId = linkId + tmpLink;
				}
				operate.put("extendKey1", linkId);
			}
		}
		// 如果调用子流程则为每个子流程单独生成correlationKey
		if (taskName.equals("PermitTask")) {
			// Map link = (HashMap) sheetMap.get("link");
			Map operate = (HashMap) sheetMap.get("operate");
			String[] dealperformers = ((String) operate.get("dealPerformer"))
					.split(",");
			if (dealperformers.length > 1) {
				// String corrKey = "";
				// String tmp = "";
				// for(int i=0;i<dealperformers.length;i++){
				// tmp = UUIDHexGenerator.getInstance().getID()+",";
				// corrKey = corrKey + tmp;
				// }
				// link.put("correlationKey", corrKey);
				if (operateType.equals("112")) {
					String linkId = "";
					String tmpLink = "";
					for (int i = 0; i < dealperformers.length; i++) {
						tmpLink = UUIDHexGenerator.getInstance().getID() + ",";
						linkId = linkId + tmpLink;
					}
					operate.put("extendKey1", linkId);
				}
			}
			sheetMap.put("operate", operate);
		}
		// 自动派单
		String mainIsNeedDesign = StaticMethod.nullObject2String(request
				.getParameter("mainIsNeedDesign"));
		BocoLog.info(this, "====makeStep1==== taskName:" + taskName);
		BocoLog.info(this, "====makeStep1==== mainIsNeedDesign:"
				+ mainIsNeedDesign);
		if ((taskName.equals("") || taskName.equals("DraftTask") || taskName
				.equals("RejectHumTask"))
				&& mainIsNeedDesign.equals("1030101")
				&& phaseId.equals("AuditTask")) {
			HashMap link = (HashMap) sheetMap.get("link");
			if (taskName.equals("")) {
				link.put("operateType", new Integer(0));
			} else if (taskName.equals("DraftTask")) {
				link.put("operateType", new Integer(3));
			} else if (taskName.equals("RejectHumTask")) {
				link.put("operateType", new Integer(54));
			}
			link.put("activeTemplateId", taskName);
			sheetMap.put("link", link);
			sheetMap = makeStep1(sheetMap);
		}
		// 设置排程一派多所需的id和correlationKey
		Map operate = (HashMap) sheetMap.get("operate");
		String extendPerformer = StaticMethod.nullObject2String(operate
				.get("extendPerformer"));
		if (!extendPerformer.equals("")) {
			String[] extendPerformerArr = extendPerformer.split(",");
			String tmpExtendKey1 = "";
			String tmpExtendKey2 = "";
			for (int i = 0; i < extendPerformerArr.length; i++) {
				tmpExtendKey1 += UUIDHexGenerator.getInstance().getID() + ",";
				tmpExtendKey2 += UUIDHexGenerator.getInstance().getID() + ",";
			}
			operate.put("extendKey1", tmpExtendKey1.substring(0, tmpExtendKey1
					.length() - 1));
			operate.put("extendKey2", tmpExtendKey2.substring(0, tmpExtendKey2
					.length() - 1));
			sheetMap.put("operate", operate);
		}
		this.setFlowEngineMap(sheetMap);
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

	public Map getAttachmentAttributeOfOjbect() {
		Map objectMap = new HashMap();

		List mainAttachmentAttributes = new ArrayList();
		mainAttachmentAttributes.add("sheetAccessories");

		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");

		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
	}

	public void newPerformAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.newPerformAdd(mapping, form, request, response);
		// 如果是报备则不进行工单的流转，只保存main数据
		String mainChangeSource = StaticMethod.nullObject2String(request
				.getParameter("mainChangeSource"));
		// 变更来源如果是故障处理或投诉处理，则为报备
		if (mainChangeSource.equals("101090101")
				|| mainChangeSource.equals("101090102")) {
			BaseMain mainbean = this.getMainService().getMainObject();
			Map map = this.getFlowEngineMap();
			Map main = (Map) map.get("main");
			Iterator names = main.keySet().iterator();
			while (names.hasNext()) {
				String name = (String) names.next();
				Object value = main.get(name);
				if (value != null) {
					Class clazz = value.getClass();
					String className = clazz.getName();
					if (className.equalsIgnoreCase("java.util.Date")) {
						DateFormat df = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String datestr = df.format((Date) value);
						main.put(name, datestr);
					}
				}
			}
			SheetBeanUtils.populateMap2Bean(mainbean, main);
			this.getMainService().addMain(mainbean);
		}
	}

	public void showInputNewSheetPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showInputNewSheetPage(mapping, form, request, response);
		try {
			// 删除今天以前main表中所有的空记录（只有id数据的记录）
			ISoftChangeMainDAO mainDAO = (ISoftChangeMainDAO) this
					.getMainService().getMainDAO();

			mainDAO.DeleteEarlyEmptyMain(this.getMainService().getMainObject());
			// 新增一条记录，该记录中只有id和sendtime字段的数据
			BaseMain main = (BaseMain) request.getAttribute("sheetMain");
			main.setId(UUIDHexGenerator.getInstance().getID());
			main.setSendTime(new Date());
			mainDAO.saveObject(main);
			request.setAttribute("sheetMain", main);
			request.setAttribute("sheetKey", main.getId());
			request.setAttribute("mainAssortSpeciality", UUIDHexGenerator
					.getInstance().getID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 增加Step1处理的附件模板
		String processName = this.getMainService().getFlowTemplateName();
		ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
				.getInstance().getBean("ItawSheetAccessManager");
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName,
				"ProjectCreateTask");
		request.setAttribute("tawSheetAccess1", access);
	}

	public void showInputDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showInputDealPage(mapping, form, request, response);
		// add by yangyankuang
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"));

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemSubRoleManager");
		List subRoleList = new ArrayList();
		int listLength = subRoleList.size();
		long roleId = 0;
		System.out.println("===operateRoleId====" + operateRoleId);

		TawSystemSubRole subrole = mgr.getTawSystemSubRole(operateRoleId);

		request.setAttribute("operateRoleId", operateRoleId);
		if (subrole != null) {
			request.setAttribute("roleId", subrole.getRoleId() + "");
		}
		request.setAttribute("operateDeptId", sessionform.getDeptid());

		/*
		 * add by panlong
		 */
		// 取上条TASK
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		if (taskName.equals("")) {
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
		}
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		if (taskName.equals("RejectTask")
				|| taskName.equals("ProjectCreateTask")
				|| taskName.equals("PlanTask")
				|| taskName.equals("ExecuteTask")
				|| taskName.equals("ValidateTask")
				|| taskName.equals("AuditTask")) {
			super.setParentTaskOperateWhenRejct(request);
		}
		if (taskName.equals("PermitTask")) {
			setParentTaskOperateWhenRejct("", request);
		}
		// 调用其他流程
		taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		if (taskName.equals("ValidateTask")
				|| taskName.equals("ProjectCreateTask")) {
			String sheetKey = StaticMethod.nullObject2String(request
					.getParameter("sheetKey"));
			ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
					.getInstance().getBean("ITawSheetRelationManager");
			List relationAllList = rmgr.getAllSheetByParentIdAndPhaseId(
					sheetKey, taskName);
			if (relationAllList != null) {
				for (int i = 0; i < relationAllList.size(); i++) {
					TawSheetRelation relation = (TawSheetRelation) relationAllList
							.get(i);
					int state = relation.getInvokeState();
					if (state == Constants.INVOKE_STATE_RUNNING) {
						request.setAttribute("ifInvoke", "no");
						break;
					}
					request.setAttribute("ifInvoke", "yes");
				}
			} else {
				request.setAttribute("ifInvoke", "no");
			}
		}
		// 需要回调外部流程
		if (taskName.equals("HoldTask")) {
			String sheetKey = StaticMethod.nullObject2String(request
					.getParameter("sheetKey"), "");
			BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
			String parentSheetName = main.getParentSheetName();
			String parentSheetKey = main.getParentSheetId();
			if (parentSheetName != null && !parentSheetName.equals("")) {
				IMainService parentMainService = (IMainService) ApplicationContextHolder
						.getInstance().getBean(parentSheetName);
				BaseMain parentMain = parentMainService
						.getSingleMainPO(parentSheetKey);

				String parentPhaseName = main.getParentPhaseName();

				if (parentPhaseName.indexOf("@") != -1) {
					request.setAttribute("parentPiid", parentPhaseName
							.substring(parentPhaseName.indexOf("@") + 1));
					System.out.println("回调：parentProcessId："
							+ parentPhaseName.substring(parentPhaseName
									.indexOf("@") + 1));
				} else {
					request.setAttribute("parentPiid", parentMain.getPiid());
				}

				request.setAttribute("parentMain", parentMain);
				request.setAttribute("parentProcessName", parentSheetName);
				System.out.println("softchang 执行了回调 ===========");
			}
		}
	}

	/**
	 * 工单强制归档、作废页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void showForceHoldPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showForceHoldPage(mapping, form, request, response);
		// 需要回调外部流程
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
		String parentSheetName = main.getParentSheetName();
		String parentSheetKey = main.getParentSheetId();
		if (parentSheetName != null && !parentSheetName.equals("")) {
			IMainService parentMainService = (IMainService) ApplicationContextHolder
					.getInstance().getBean(parentSheetName);
			BaseMain parentMain = parentMainService
					.getSingleMainPO(parentSheetKey);
			String parentPhaseName = main.getParentPhaseName();

			if (parentPhaseName.indexOf("@") != -1) {
				request.setAttribute("parentPiid", parentPhaseName
						.substring(parentPhaseName.indexOf("@") + 1));
				System.out.println("回调：parentProcessId："
						+ parentPhaseName.substring(parentPhaseName
								.indexOf("@") + 1));
			} else {
				request.setAttribute("parentPiid", parentMain.getPiid());
			}
			request.setAttribute("parentMain", parentMain);
			request.setAttribute("parentProcessName", parentSheetName);
		}
	}

	/**
	 * 自动派单生成T1数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap makeStep1(HashMap sheetMap) throws Exception {
		BocoLog.info(this, "====makeT1====");
		Map mainMap = (HashMap) sheetMap.get("main");
		Map tmpLinkMap = (HashMap) sheetMap.get("link");
		Map operateMap = (HashMap) sheetMap.get("operate");

		if (operateMap.get("phaseId").equals("AuditTask")) {
			Date nowDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);

			sheetMap.put("operate", operateMap);

			Map linkMap = new HashMap();
			linkMap.putAll(tmpLinkMap);

			Iterator names = linkMap.keySet().iterator();
			while (names.hasNext()) {
				String name = (String) names.next();
				Object value = linkMap.get(name);
				if (value != null) {
					Class clazz = value.getClass();
					String className = clazz.getName();
					if (className.equalsIgnoreCase("java.util.Date")) {
						DateFormat df = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String datestr = df.format((Date) value);
						linkMap.put(name, datestr);
					}
				}
			}

			String tkid = "_AI:" + UUIDHexGenerator.getInstance().getID();

			// 生成新增工单的link记录
			SoftChangeLink link1 = (SoftChangeLink) this.getLinkService()
					.getLinkObject().getClass().newInstance();
			SheetBeanUtils.populateMap2Bean(link1, linkMap);
			link1.setId(UUIDHexGenerator.getInstance().getID());
			link1.setMainId((String) mainMap.get("id"));
			link1.setOperateTime(new Date());
			link1.setOperateType((Integer) linkMap.get("operateType"));
			link1.setToOrgRoleId((String) mainMap.get("sendRoleId"));
			link1.setActiveTemplateId((String) linkMap.get("activeTemplateId"));
			link1.setAiid("");
			link1.setPreLinkId("");
			link1.setNodeAccessories("");
			this.getLinkService().addLink(link1);

			// 生成T1处理的确认受理的link记录
			SoftChangeLink link2 = (SoftChangeLink) this.getLinkService()
					.getLinkObject().getClass().newInstance();
			SheetBeanUtils.populateMap2Bean(link2, linkMap);
			link2.setId(UUIDHexGenerator.getInstance().getID());
			link2.setOperateType(new Integer("61"));
			link2.setActiveTemplateId("ProjectCreateTask");
			link2.setOperateTime(new Date(nowDate.getTime() + 1000));
			link2.setMainId((String) mainMap.get("id"));
			link2.setToOrgRoleId((String) mainMap.get("sendRoleId"));
			link2.setPreLinkId(link1.getId());
			link2.setAiid(tkid);
			link2.setNodeAccessories("");
			this.getLinkService().addLink(link2);

			// 生成T1处理的Task记录
			SoftChangeTask task = (SoftChangeTask) this.getTaskService()
					.getTaskModelObject();

			task.setId(tkid);
			task.setCreateTime(nowDate);
			task.setTaskStatus(Constants.TASK_STATUS_FINISHED);
			task.setProcessId((String) mainMap.get("piid"));
			task.setSheetKey((String) mainMap.get("id"));
			task.setSheetId((String) mainMap.get("sheetId"));
			task.setTitle((String) mainMap.get("title"));
			task.setAcceptTimeLimit(link1.getNodeAcceptLimit());
			task.setCompleteTimeLimit(link1.getNodeCompleteLimit());
			task.setPreLinkId(link1.getId());
			task.setCurrentLinkId((String) linkMap.get("id"));
			task.setIfWaitForSubTask("false");
			task.setCreateDay(calendar.get(calendar.DATE));
			task.setCreateMonth(calendar.get(calendar.MONTH) + 1);
			task.setCreateYear(calendar.get(calendar.YEAR));
			task.setTaskDisplayName("方案制定中");
			task.setTaskName("ProjectCreateTask");
			task.setOperateRoleId(link1.getOperateRoleId());
			task.setTaskOwner(link1.getOperateUserId());
			task.setOperateType("subrole");
			task.setFlowName("SoftChangeMainProcess");
			task.setParentTaskId(task.getId());
			task.setSendTime((Date) mainMap.get("sendTime"));
			this.getTaskService().addTask(task);

			// 生成Step1处理的link记录
			tmpLinkMap.put("operateType", "110");
			tmpLinkMap.put("mainId", (String) mainMap.get("id"));
			tmpLinkMap.put("preLinkId", link1.getId());
			tmpLinkMap.put("aiid", task.getId());
			tmpLinkMap.put("operateTime", new Date(nowDate.getTime() + 2000));
			tmpLinkMap.put("nodeCompleteLimit", mainMap
					.get("sheetCompleteLimit"));
			tmpLinkMap.put("activeTemplateId", "ProjectCreateTask");
			tmpLinkMap.put("nodeAccessories", mainMap
					.get("firstNodeAccessories"));
			sheetMap.put("link", tmpLinkMap);
		}
		return sheetMap;
	}

	public void showInputTemplateSheetPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.showInputTemplateSheetPage(mapping, form, request, response);
		// 增加T1处理的模板
		String processName = this.getMainService().getFlowTemplateName();
		ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
				.getInstance().getBean("ItawSheetAccessManager");
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName,
				"ProjectCreateTask");
		request.setAttribute("tawSheetAccess1", access);

	}

	/**
	 * ADD jialei 当驳回的时候查询之前特定的任务的执行者对象
	 * 从request中通过request.getAttribute(fOperateroleid/ftaskOwner/fOperateroleidType)
	 * 取得上一条任务的Operateroleid taskOwner OperateroleidType
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void setParentTaskOperateWhenRejct(String taskName,
			HttpServletRequest request) throws Exception {
		String prelinkid = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"));
		BaseLink preLink = (BaseLink) this.getLinkService().getSingleLinkPO(
				prelinkid);
		String sheetKey = preLink.getMainId();
		String fOperateroleid = "";
		String ftaskOwner = "";
		String fOperateroleidType = "";
		String fPreTaskName = "";
		if (preLink != null) {
			// 不是流程第一个操作步骤
			String parentTaskId = StaticMethod.nullObject2String(preLink
					.getAiid());
			if (!parentTaskId.equals("")) {
				ITask task = this.getTaskService().getSinglePO(parentTaskId);
				fOperateroleid = task.getOperateRoleId();
				ftaskOwner = task.getTaskOwner();
				fOperateroleidType = task.getOperateType();
				fPreTaskName = task.getTaskName();
			} else {
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				fOperateroleid = main.getSendRoleId();
				ftaskOwner = main.getSendUserId();
				fOperateroleidType = UIConstants.NODETYPE_SUBROLE;
			}
		}
		request.setAttribute("fOperateroleid", fOperateroleid);
		request.setAttribute("ftaskOwner", ftaskOwner);
		request.setAttribute("fOperateroleidType", fOperateroleidType);
		request.setAttribute("fPreTaskName", fPreTaskName);

		// 得到特定的任务的执行者对象
		if (taskName != null) {
			while (!taskName.equals(StaticMethod
					.nullObject2String(fPreTaskName))) {
				prelinkid = StaticMethod.nullObject2String(preLink
						.getPreLinkId());
				if (!prelinkid.equals("")) {
					preLink = (BaseLink) this.getLinkService().getSingleLinkPO(
							prelinkid);
				} else {
					preLink = null;
				}
				fOperateroleid = "";
				ftaskOwner = "";
				fOperateroleidType = "";
				fPreTaskName = "";
				if (preLink == null) {
					BaseMain main = this.getMainService().getSingleMainPO(
							sheetKey);
					fOperateroleid = main.getSendRoleId();
					ftaskOwner = main.getSendUserId();
					fOperateroleidType = UIConstants.NODETYPE_SUBROLE;
					fPreTaskName = "";

				} else {
					// 不是流程第一个操作步骤
					String parentTaskId = StaticMethod
							.nullObject2String(preLink.getAiid());
					if (!parentTaskId.equals("")) {
						ITask task = this.getTaskService().getSinglePO(
								parentTaskId);
						fOperateroleid = task.getOperateRoleId();
						ftaskOwner = task.getTaskOwner();
						fOperateroleidType = task.getOperateType();
						fPreTaskName = task.getTaskName();
					} else {
						BaseMain main = this.getMainService().getSingleMainPO(
								sheetKey);
						fOperateroleid = main.getSendRoleId();
						ftaskOwner = main.getSendUserId();
						fOperateroleidType = UIConstants.NODETYPE_SUBROLE;
						fPreTaskName = "";
					}
				}
				request.setAttribute(fPreTaskName + "Operateroleid",
						fOperateroleid);
				request.setAttribute(fPreTaskName + "TaskOwner", ftaskOwner);
				request.setAttribute(fPreTaskName + "OperateroleidType",
						fOperateroleidType);
				if (fPreTaskName.equals("")) {
					break;
				}
			}
		}
	}

	public String getSheetAttachCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map initMap(Map map, List attach, String type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
