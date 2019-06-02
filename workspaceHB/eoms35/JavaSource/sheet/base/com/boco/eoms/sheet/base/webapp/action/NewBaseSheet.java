/*
 * Created on 2009-2-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.webapp.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.RequestUtils;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.dao.IDictDao;
import com.boco.eoms.commons.system.dict.model.IDictItem;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSSecutiryServiceImpl;
import com.boco.eoms.sheet.base.flowchar.NewWorkFlow;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.IHumanTaskService;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.base.util.flowdefine.xml.ToPhaseId;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;
import com.boco.eoms.util.AttachRef;
import com.ibm.task.api.LocalHumanTaskManager;
import com.ibm.task.api.LocalHumanTaskManagerHome;
import com.ibm.task.api.Task;

/**
 * @author wangjianhua
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class NewBaseSheet implements NewIBaseSheet {
	protected IBusinessFlowService businessFlowService;

	private IHumanTaskService humanTaskService;

	private ILinkService linkService;

	private IMainService mainService;

	private int pageLength = 0;

	private HashMap flowEngineMap;

	private ITask sheetTask;

	private String flowTemplateName;

	private ThreadLocal storeFlowEngineMap = new ThreadLocal();

	private String roleConfigPath;

	private ITaskService taskService;

	public Map getMainSendObject() {
		Map attributeMap = new HashMap();
		attributeMap.put("sendObject", "sendObject");
		return attributeMap;
	}

	public Map getLinkSendObject() {
		Map attributeMap = new HashMap();
		attributeMap.put("sendObject", "sendObject");
		return attributeMap;
	}

	/**
	 * @return Returns the taskService.
	 */
	public ITaskService getTaskService() {
		return taskService;
	}

	/**
	 * @param taskService
	 *            The taskService to set.
	 */
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * @return Returns the flowTemplateName.
	 */
	public String getFlowTemplateName() {
		return flowTemplateName;
	}

	/**
	 * @param flowTemplateName
	 *            The flowTemplateName to set.
	 */
	public void setFlowTemplateName(String flowTemplateName) {
		this.flowTemplateName = flowTemplateName;
	}

	/**
	 * @return Returns the sheetTask.
	 */
	public ITask getSheetTask() {
		return sheetTask;
	}

	/**
	 * @param sheetTask
	 *            The sheetTask to set.
	 */
	public void setSheetTask(ITask sheetTask) {
		this.sheetTask = sheetTask;
	}

	/**
	 * @return Returns the flowEngineMap.
	 */
	public HashMap getFlowEngineMap() {
		HashMap map = new HashMap();
		if (this.storeFlowEngineMap.get() != null) {
			map = (HashMap) this.storeFlowEngineMap.get();
		}
		return map;
	}

	/**
	 * @param flowEngineMap
	 *            The flowEngineMap to set.
	 */
	public void setFlowEngineMap(HashMap flowEngineMap) {
		this.storeFlowEngineMap.set(flowEngineMap);
		this.flowEngineMap = flowEngineMap;
	}

	/**
	 * @return Returns the pageLength.
	 */
	public int getPageLength() {
		return pageLength;
	}

	/**
	 * @param pageLength
	 *            The pageLength to set.
	 */
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	public String getPageColumnName() {
		return Constants.pageColumnName;
	}

	/**
	 * @return the humanTaskService
	 */
	public IHumanTaskService getHumanTaskService() {
		return humanTaskService;
	}

	/**
	 * @param humanTaskService
	 *            the humanTaskService to set
	 */
	public void setHumanTaskService(IHumanTaskService humanTaskService) {
		this.humanTaskService = humanTaskService;
	}

	/**
	 * @return the linkService
	 */
	public ILinkService getLinkService() {
		return linkService;
	}

	/**
	 * @param linkService
	 *            the linkService to set
	 */
	public void setLinkService(ILinkService linkService) {
		this.linkService = linkService;
	}

	/**
	 * @return the mainService
	 */
	public IMainService getMainService() {
		return mainService;
	}

	/**
	 * @param mainService
	 *            the mainService to set
	 */
	public void setMainService(IMainService mainService) {
		this.mainService = mainService;
	}

	/**
	 * @return the businessFlowService
	 */
	public IBusinessFlowService getBusinessFlowService() {
		return businessFlowService;
	}

	/**
	 * @param businessFlowService
	 *            the businessFlowService to set
	 */
	public void setBusinessFlowService(IBusinessFlowService businessFlowService) {
		this.businessFlowService = businessFlowService;
	}

	public String getRoleConfigPath() {
		return roleConfigPath;
	}

	public void setRoleConfigPath(String roleConfigPath) {
		this.roleConfigPath = roleConfigPath;
	}

	/**
	 * 新增工单的初始化页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newShowInputNewSheetPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();

		String sendUserName = "";
		String sendDeptName = "";
		String sendRoleName = "";
		if (sessionform != null) {
			mainObject.setSendUserId(sessionform.getUserid());
			mainObject.setSendDeptId(sessionform.getDeptid());
			mainObject.setSendRoleId(StaticMethod.nullObject2String(sessionform
					.getRoleid()));
			mainObject.setSendOrgType(UIConstants.NODETYPE_SUBROLE);
			mainObject.setSendContact(sessionform.getContactMobile());
			sendUserName = sessionform.getUsername();
			sendDeptName = sessionform.getDeptname();
			sendRoleName = sessionform.getRolename();
		}
		mainObject.setSheetAcceptLimit(StaticMethod.getLocalTime());
		mainObject.setSheetCompleteLimit(StaticMethod.getLocalTime());
		mainObject.setSendTime(StaticMethod.getLocalTime());
		String parentCorrelation = StaticMethod.nullObject2String(request
				.getParameter("parentCorrelation"), "");
		String parentSheetId = StaticMethod.nullObject2String(request
				.getParameter("parentSheetId"), "");
		String parentSheetName = StaticMethod.nullObject2String(request
				.getParameter("parentSheetName"), "");
		String parentPhaseName = StaticMethod.nullObject2String(request
				.getParameter("parentPhaseName"), "");
		mainObject.setParentCorrelation(parentCorrelation);
		mainObject.setParentSheetId(parentSheetId);
		mainObject.setParentSheetName(parentSheetName);
		mainObject.setParentPhaseName(parentPhaseName);

		if (!parentSheetId.equals("") && !parentSheetName.equals("")) {
			IMainService parentMainSerivce = (IMainService) ApplicationContextHolder
					.getInstance().getBean(parentSheetName);
			BaseMain parentMain = parentMainSerivce
					.getSingleMainPO(parentSheetId);
			;
			String tmpparentSheetId = parentMain.getSheetId();
			ITawSystemWorkflowManager mgr = (ITawSystemWorkflowManager) ApplicationContextHolder
					.getInstance().getBean("ITawSystemWorkflowManager");
			TawSystemWorkflow workflow = mgr
					.getTawSystemWorkflowByBeanId(parentSheetName);
			String parentProcessName = workflow.getName();

			request.setAttribute("parentSheetId", tmpparentSheetId);
			request.setAttribute("parentProcessName", parentProcessName);
			BocoLog.debug(this, "parentSheetId=" + parentSheetId
					+ "parentProcessName=" + parentProcessName);
		}

		// 草稿页面和新的输入页面为同一页面，如果是草稿页面
		String draftId = StaticMethod.nullObject2String(request
				.getParameter("draftId"));
		if (!draftId.equals("")) {
			BaseMain draftMainObject = (BaseMain) this.getMainService()
					.getSingleMainPO(draftId);
			Map mainMap = SheetBeanUtils.bean2MapWithNull(draftMainObject);
			SheetBeanUtils.populateMap2Bean(mainObject, mainMap);
			request.setAttribute("draft", "true");
			request.setAttribute("mainId", draftId);
		}

		// 驳回状态驳回到草稿页面
		String rejectId = StaticMethod.nullObject2String(request
				.getParameter("rejectId"));
		if (!rejectId.equals("")) {
			BaseMain rejectMainObject = (BaseMain) this.getMainService()
					.getSingleMainPO(rejectId);
			Map mainMap = SheetBeanUtils.bean2MapWithNull(rejectMainObject);
			SheetBeanUtils.populateMap2Bean(mainObject, mainMap);
			request.setAttribute("mainId", rejectId);
			String taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
			request.setAttribute("taskName", taskName);
			request.setAttribute("reject", "true");
		}

		request.setAttribute("sendUserName", sendUserName);
		request.setAttribute("sendDeptName", sendDeptName);
		request.setAttribute("sendRoleName", sendRoleName);
		request.setAttribute("sheetMain", mainObject);
		request.setAttribute("status", Constants.SHEET_RUN);
		request.setAttribute("sendOrgType", UIConstants.NODETYPE_SUBROLE);
		request.setAttribute("methodBeanId", mapping.getAttribute());

		String path = mapping.getPath();
		path = path.substring(1);
		IDictDao dictDao = (IDictDao) ApplicationContextHolder.getInstance()
				.getBean("DictDaoXML");
		IDictItem item = dictDao
				.findItem(
						"dict-sheet-"
								+ path
								+ com.boco.eoms.commons.system.dict.util.Constants.DICT_ID_SPLIT_CHAR
								+ "mainOperateType", "22");

		String draftDescription = (String) item.getItemDescription();
		request.setAttribute("draftTaskName", draftDescription);

		String processTemplateName = this.getMainService()
				.getFlowTemplateName();
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder
				.getInstance().getBean("ITawSystemWorkflowManager");
		TawSystemWorkflow workflow = flowmanage
				.getTawSystemWorkflowByName(processTemplateName);
		String flowId = workflow.getFlowId();
		request.setAttribute("flowId", flowId);
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				processTemplateName, this.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		PhaseId[] phaseIds = flowDefine.getPhaseId();
		for (int i = 0; i < phaseIds.length; i++) {
			PhaseId phaseId = phaseIds[i];
			// 新增要提取roleId，tophaseid,flowid到页面去
			if (phaseId.getId().equals("receive")) {
				ToPhaseId[] toPhaseIds = phaseId.getToPhaseId();
				for (int j = 0; j < toPhaseIds.length; j++) {
					ToPhaseId toPhaseId = toPhaseIds[j];
					String actionsend = String.valueOf(Constants.ACTION_SEND);
					if (toPhaseId.getOperatetype().equals(actionsend)) {
						request.setAttribute("roleId", toPhaseId.getRoleid());
						request.setAttribute("toPhaseId", toPhaseId.getId());
						break;
					}
				}
			}
		}

	}

	/**
	 * 新增提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 */
	public void performAddNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		BocoLog.info(this, "=====request Map parentPhaseName:"
				+ StaticMethod.nullObject2String(request
						.getParameter("parentPhaseName"), ""));

		String copyPerformer = StaticMethod.nullObject2String(request
				.getParameter("copyPerformer"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));

		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);// 获取main link operate对象

		Map map = newSetAddRequestMap(mapping, form, request, response);

		WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		HashMap WpsMap = sm.prepareMap(map, columnMap);

		String operateName = StaticMethod.nullObject2String(request
				.getParameter("operateName"));

		setFlowEngineMap(WpsMap);
		dealFlowEngineMap(mapping, form, request, response);

		sm.sendNewSheet(WpsMap, sessionform.getUserid(), (String) map
				.get("processTemplateName"), operateName);

		// 非流程动作处理
		if (!copyPerformer.equals("")
				|| Integer.parseInt(operateType) == Constants.ACTION_MAKECOPYFOR
				|| Integer.parseInt(operateType) == Constants.ACTION_PHASE_BACKTOUP
				|| Integer.parseInt(operateType) == Constants.ACTION_DRIVERFORWARD) {
			try {
				newSaveNonFlowData("", getFlowEngineMap());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 处理如果是草稿派发
		Map mainMap = (Map) columnMap.get("selfSheet");
		BaseMain baseMain = (BaseMain) mainMap.get("main");
		if (baseMain != null) {
			if (baseMain.getStatus().intValue() == Constants.SHEET_DRAFT
					.intValue()) {
				String dictKey = "dict-sheet-" + mapping.getPath().substring(1);
				String draftName = (String) DictMgrLocator.getDictService()
						.itemId2description(
								Util.constituteDictId(dictKey,
										"mainOperateType"),
								new Integer(Constants.ACTION_DRAFT));
				ITask task = this.getTaskService().getTask(baseMain.getId(),
						draftName);
				task.setTaskStatus(Constants.TASK_STATUS_FINISHED);
				this.getTaskService().addTask(task);
			}
		}

	}

	/**
	 * 保存草稿
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newSaveDraft(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map parameterMap = request.getParameterMap();
		BaseMain mainObject = (BaseMain) (getMainService().getMainObject()
				.getClass()).newInstance();
		String mainId = UUIDHexGenerator.getInstance().getID();
		SheetBeanUtils.populateMap2Bean(mainObject, parameterMap);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (sessionform == null)
			sessionform = new TawSystemSessionForm();

		mainObject.setId(mainId);
		mainObject.setSendUserId(sessionform.getUserid());
		mainObject.setSendDeptId(sessionform.getDeptid());
		mainObject.setSendOrgType(UIConstants.NODETYPE_SUBROLE);
		mainObject.setSendContact(sessionform.getContactMobile());
		mainObject.setSendTime(StaticMethod.getLocalTime());
		mainObject.setSheetId(this.mainService.getSheetId());
		mainObject.setStatus(Constants.SHEET_DRAFT);
		mainObject.setSendYear(calendar.get(Calendar.YEAR));
		mainObject.setSendMonth(calendar.get(Calendar.MONTH));
		mainObject.setSendDay(calendar.get(Calendar.DAY_OF_MONTH + 1));

		// 取派往对象
		Map mainSendDomain = this.getMainSendObject();
		for (Iterator it = mainSendDomain.keySet().iterator(); it.hasNext();) {
			String mainDomain = (String) it.next();
			String sendObjectToalJSON = mainDomain + "TotalJSON";
			String[] totalJson = (String[]) parameterMap
					.get(sendObjectToalJSON);
			String setMethod = "set"
					+ StaticMethod.firstToUpperCase(mainDomain);
			if (totalJson != null && totalJson.length > 0) {
				try {
					Method setterMethod = mainObject.getClass().getMethod(
							setMethod, new Class[] { String.class });
					setterMethod.invoke(mainObject,
							new Object[] { totalJson[0] });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			} else {
				try {
					Method setterMethod = mainObject.getClass().getMethod(
							setMethod, new Class[] { String.class });
					setterMethod.invoke(mainObject, new Object[] { null });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}

		// 保存草稿
		getMainService().addMain(mainObject);

		// 保存link
		BaseLink linkObject = (BaseLink) (getLinkService().getLinkObject()
				.getClass()).newInstance();
		String linkId = UUIDHexGenerator.getInstance().getID();
		linkObject.setId(linkId);
		linkObject.setMainId(mainObject.getId());
		linkObject.setOperateType(new Integer(Constants.ACTION_DRAFT));
		linkObject.setOperateTime(new Date());
		linkObject.setOperateUserId(mainObject.getSendUserId());
		linkObject.setOperateDeptId(sessionform.getDeptid());
		linkObject.setOperateRoleId(mainObject.getSendRoleId());
		linkObject.setOperaterContact(sessionform.getContactMobile());
		linkObject.setOperateYear(calendar.get(Calendar.YEAR));
		linkObject.setOperateMonth(calendar.get(Calendar.MONTH));
		linkObject.setOperateDay(calendar.get(Calendar.DAY_OF_MONTH + 1));
		linkObject.setToOrgUserId(mainObject.getSendUserId());
		linkObject.setToOrgRoleId(mainObject.getSendRoleId());
		linkObject.setToOrgDeptId(mainObject.getToDeptId());
		linkObject.setCorrelationKey(UUIDHexGenerator.getInstance().getID());
		getLinkService().addLink(linkObject);

		// 保存task
		ITask taskObject = (ITask) (getTaskService().getTaskModelObject()
				.getClass()).newInstance();
		String taskId = UUIDHexGenerator.getInstance().getID();
		taskObject.setId(taskId);
		taskObject.setTaskName(Constants.TASK_NAME_DRAFT);
		taskObject.setTaskDisplayName("草稿");
		taskObject.setCreateTime(new Date());
		taskObject.setTaskStatus(Constants.TASK_STATUS_READY);
		taskObject.setSheetId(mainObject.getSheetId());
		taskObject.setSheetKey(mainObject.getId());
		taskObject.setTitle(mainObject.getTitle());
		taskObject.setOperateRoleId(mainObject.getSendRoleId());
		taskObject.setTaskOwner(sessionform.getUserid());
		taskObject
				.setFlowName((String) parameterMap.get("processTemplateName"));
		taskObject.setOperateType(UIConstants.NODETYPE_SUBROLE);
		taskObject.setIfWaitForSubTask("false");
		taskObject.setCreateYear(calendar.get(Calendar.YEAR));
		taskObject.setCreateMonth(calendar.get(Calendar.MONTH));
		taskObject.setCreateDay(calendar.get(Calendar.DAY_OF_MONTH + 1));
		getTaskService().addTask(taskObject);
	}

	/**
	 * 草稿列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newShowDraftList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		// 总数
		int[] aTotal = { 0 };

		// 获取当前用户的角色列表
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();

		// 草稿记录
		BaseMain mainObject = (BaseMain) (getMainService().getMainObject()
				.getClass()).newInstance();
		List taskList = this.getMainService().getDraftList(userId, pageIndex,
				pageSize, aTotal, mainObject);

		// 将分页后的列表写入页面供使用
		request.setAttribute("taskList", taskList);
		request.setAttribute("total", new Integer(aTotal[0]));
		request.setAttribute("pageSize", pageSize);
	}

	/**
	 * 保存main表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newSaveMainSheet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map parameterMap = request.getParameterMap();
		BaseMain mainObject = (BaseMain) (getMainService().getMainObject()
				.getClass()).newInstance();

		SheetBeanUtils.populateMap2Bean(mainObject, parameterMap);

		// 取派往对象
		Map mainSendDomain = this.getMainSendObject();
		for (Iterator it = mainSendDomain.keySet().iterator(); it.hasNext();) {
			String mainDomain = (String) it.next();
			String sendObjectToalJSON = mainDomain + "TotalJSON";
			String[] totalJson = (String[]) parameterMap
					.get(sendObjectToalJSON);
			String setMethod = "set"
					+ StaticMethod.firstToUpperCase(mainDomain);
			if (totalJson != null && totalJson.length > 0) {
				try {
					Method setterMethod = mainObject.getClass().getMethod(
							setMethod, new Class[] { String.class });
					setterMethod.invoke(mainObject,
							new Object[] { totalJson[0] });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			} else {
				try {
					Method setterMethod = mainObject.getClass().getMethod(
							setMethod, new Class[] { String.class });
					setterMethod.invoke(mainObject, new Object[] { null });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}

		BaseMain oldMainObject = this.getMainService().getSingleMainPO(
				mainObject.getId());
		mainObject.setStatus(oldMainObject.getStatus());
		mainObject.setSheetId(oldMainObject.getSheetId());
		mainObject.setSendUserId(oldMainObject.getSendUserId());
		mainObject.setSendDeptId(oldMainObject.getSendDeptId());
		mainObject.setSendOrgType(oldMainObject.getSendOrgType());
		mainObject.setSendContact(oldMainObject.getSendContact());
		mainObject.setSendTime(oldMainObject.getSendTime());
		mainObject.setSendYear(oldMainObject.getSendYear());
		mainObject.setSendMonth(oldMainObject.getSendMonth());
		mainObject.setSendDay(oldMainObject.getSendDay());
		// 保存草稿
		getMainService().addMain(mainObject);
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
	public void newShowDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"), "");
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"), "");
		String taskStatus = StaticMethod.nullObject2String(request
				.getParameter("taskStatus"), "");
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));

		BocoLog.info(this, "sheetKey==" + sheetKey);

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			String parentSheetKey = StaticMethod.nullObject2String(mainObject
					.getParentSheetId());
			String parentBeanId = StaticMethod.nullObject2String(mainObject
					.getParentSheetName());
			BocoLog.debug(this, "parentSheetKey=" + parentSheetKey
					+ "parentBeanId=" + parentBeanId);
			if (!parentSheetKey.equals("") && !parentBeanId.equals("")) {
				IMainService parentMainSerivce = (IMainService) ApplicationContextHolder
						.getInstance().getBean(parentBeanId);
				BaseMain parentMain = parentMainSerivce.getMainDAO()
						.loadSinglePO(parentSheetKey, mainObject);
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

				BocoLog.info(this, "parentSheetId=" + parentSheetId
						+ "parentProcessCnName=" + parentProcessCnName);
			}
			request.setAttribute("sheetMain", mainObject);
		}

		ITask task = getTaskService().getSinglePO(taskId);

		String dealTemplateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"));
		if (dealTemplateId != null && !dealTemplateId.equals("")) {
			request.setAttribute("dealTemplateId", dealTemplateId);
		}

		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		request.setAttribute("operateType", operateType);

		// 获取登陆信息
		HashMap sessionMap = new HashMap();
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());

		// 步骤名称及URL处理
		Map stempNameAndUrl = new HashMap();
		List stempNameList = new ArrayList();

		// detail.jsp页面里的处理环节
		if (!TKID.equals("")
				&& (taskStatus.equals(Constants.TASK_STATUS_READY)
						|| taskStatus.equals(Constants.TASK_STATUS_RUNNING) || taskStatus
						.equals(Constants.TASK_STATUS_CLAIMED))) {

			String isWaitForSubTask = task.getIfWaitForSubTask();
			if (isWaitForSubTask.equals("true")) {
				List subTaskList = this.getTaskService()
						.getUndealTaskListByParentTaskId(task.getId());
				if (subTaskList != null && subTaskList.size() > 0) {
					request.setAttribute("needDealReply", "true");
				}
			}
			String ifsub = StaticMethod
					.nullObject2String(task.getSubTaskFlag());
			request.setAttribute("ifsub", ifsub);
			request.setAttribute("ifwaitfor", task.getIfWaitForSubTask());
			request.setAttribute("task", task);

			String path = mapping.getPath();
			path = path.substring(1);

			String workflow = this.getMainService().getFlowTemplateName();
			try {
				FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
						workflow, this.getRoleConfigPath());
				FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
				PhaseId[] phaseIds = flowDefine.getPhaseId();
				for (int i = 0; i < phaseIds.length; i++) {
					PhaseId phaseId = phaseIds[i];
					String phaseActiveId = phaseId.getId();
					if (phaseActiveId.equals(task.getTaskName())) {
						// 是否显示确认受理操作
						request.setAttribute("displayconfirm", phaseId
								.getDisplayconfirm());
						// 是否显示驳回操作
						request.setAttribute("displayreject", phaseId
								.getDisplayreject());
						// 是否显示公共操作
						request.setAttribute("displaycommonoperate", phaseId
								.getDisplaycommonoperate());
						// 是否将移交，分派显示为转审和会审
						request.setAttribute("displaytransferaudit", phaseId
								.getDisplaytransferaudit());

						ToPhaseId[] toPhaseIds = phaseId.getToPhaseId();
						for (int j = 0; j < toPhaseIds.length; j++) {
							ToPhaseId toPhaseId = toPhaseIds[j];
							if (toPhaseId.getStepdisplay().equals("true")) {
								String stepName = toPhaseId.getName();
								String url = path
										+ ".do?method=newShowInputDealPage&sheetKey="
										+ sheetKey + "&piid=" + piid
										+ "&taskId=" + taskId + "&taskName="
										+ taskName + "&mainId=" + sheetKey
										+ "&operateRoleId=" + operateRoleId
										+ "&TKID=" + TKID + "&taskStatus="
										+ taskStatus + "&preLinkId="
										+ preLinkId + "&operateType="
										+ toPhaseId.getOperatetype();
								stempNameAndUrl.put(stepName, url);
								stempNameList.add(stepName);
							}
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("task", null);
			}
		} else {
			request.setAttribute("task", null);
		}
		if (!preLinkId.equals("")) {
			BaseLink preLink = this.getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
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
		request.setAttribute("taskStatus", taskStatus);
		request.setAttribute("preLinkId", preLinkId);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		request.setAttribute("stempNameAndUrl", stempNameAndUrl);
		request.setAttribute("stempNameList", stempNameList);

		String processTemplateName = this.getMainService()
				.getFlowTemplateName();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				processTemplateName, this.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		String linkServiceName = flowDefine.getLinkservicename();
		String dictSheetName = flowDefine.getDictfilename();
		request.setAttribute("linkServiceName", linkServiceName);
		request.setAttribute("dictSheetName", dictSheetName);

		// 是否以管理者身份进入详细页面
		String entryAdmin = StaticMethod.nullObject2String(request
				.getParameter("entryAdmin"), "");
		if (entryAdmin != null && entryAdmin.equals("true")) {
			request.setAttribute("entryAdmin", entryAdmin);
		}
		// 是否以管理者身份进入详细页面
		String isAdmin = StaticMethod.nullObject2String(request
				.getParameter("isAdmin"), "");
		if (isAdmin != null && isAdmin.equals("true")) {
			request.setAttribute("isAdmin", isAdmin);
		}
	}

	/**
	 * 新保存模板(main表)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newSaveTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map parameterMap = request.getParameterMap();
		String templateId = StaticMethod.nullObject2String(request
				.getParameter("templateId"), "");
		String type = StaticMethod.nullObject2String(request
				.getParameter("type"), "");
		Date createDate = new Date();

		String mainId = (String) request.getParameter("id");
		if (mainId != null && !mainId.equals("")) {
			parameterMap.remove("id");
		}

		BaseMain mainObject = (BaseMain) (getMainService().getMainObject()
				.getClass()).newInstance();
		String id = UUIDHexGenerator.getInstance().getID();
		String oldTemplateName = "";
		if (templateId != null && !templateId.equals("")) {
			mainObject = getMainService().getSingleMainPO(templateId);
			id = templateId;
			oldTemplateName = mainObject.getSheetTemplateName();
			createDate = mainObject.getSendTime();
		}

		SheetBeanUtils.populateMap2Bean(mainObject, parameterMap);

		StringBuffer templateName = new StringBuffer();
		if (templateId.equals("")) {
			ID2NameService service = (ID2NameService) ApplicationContextHolder
					.getInstance().getBean("ID2NameGetServiceCatch");
			String templateNameRule = StaticMethod.nullObject2String(request
					.getParameter("templateNameRule"), "");
			String templateNameRuleArray[] = templateNameRule.split(";");

			templateName.append("我的模板");
			for (int i = 0; i < templateNameRuleArray.length; i++) {
				String attributte = templateNameRuleArray[i];
				String attriName = StaticMethod.nullObject2String(request
						.getParameter(attributte), "");
				String attriName2DB = service.id2Name(attriName,
						"ItawSystemDictTypeDao");
				if (attriName2DB != null && !attriName2DB.equals("")) {
					templateName.append("---").append(attriName2DB);
				} else if (!attriName.equals("")) {
					templateName.append("---").append(attriName);
				}
			}
			String time = StaticMethod.date2String(new Date());
			templateName.append("---").append(time);
			request.setAttribute("noTemplateManage", "noTemplateManage");
		} else if (!templateId.equals("") && type.equals("")) {
			templateName.append(oldTemplateName);
			request.setAttribute("noTemplateManage", "noTemplateManage");
		} else {
			String sheetTemplateName = StaticMethod.nullObject2String(request
					.getParameter("sheetTemplateName"), "");
			templateName.append(sheetTemplateName);
		}

		mainObject.setSheetTemplateName(templateName.toString());
		mainObject.setId(id);
		mainObject.setTemplateFlag(Constants.TEMPLATE_STATUS_FLAG);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (sessionform != null) {
			mainObject.setSendUserId(sessionform.getUserid());
			mainObject.setSendDeptId(sessionform.getDeptid());
			mainObject.setSendRoleId(StaticMethod.nullObject2String(sessionform
					.getRoleid()));
			mainObject.setSendOrgType(UIConstants.NODETYPE_SUBROLE);
			mainObject.setSendContact(sessionform.getContactMobile());
		}
		mainObject.setSheetAcceptLimit(new Date());
		mainObject.setSheetCompleteLimit(new Date());
		mainObject.setSendTime(createDate);

		// 将所有的时间属性设为空
		Field[] fields = mainObject.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getType().getName().equals(Date.class.getName())) {
				String setMethod = "set"
						+ StaticMethod.firstToUpperCase(field.getName());
				try {
					Method setterMethod = mainObject.getClass().getMethod(
							setMethod, new Class[] { Date.class });
					setterMethod.invoke(mainObject, new Object[] { null });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}

		// 取派往对象
		Map mainSendDomain = this.getMainSendObject();
		for (Iterator it = mainSendDomain.keySet().iterator(); it.hasNext();) {
			String mainDomain = (String) it.next();
			String sendObjectToalJSON = mainDomain + "TotalJSON";
			String[] totalJson = (String[]) parameterMap
					.get(sendObjectToalJSON);
			String setMethod = "set"
					+ StaticMethod.firstToUpperCase(mainDomain);
			if (totalJson != null && totalJson.length > 0) {
				try {
					Method setterMethod = mainObject.getClass().getMethod(
							setMethod, new Class[] { String.class });
					setterMethod.invoke(mainObject,
							new Object[] { totalJson[0] });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			} else {
				try {
					Method setterMethod = mainObject.getClass().getMethod(
							setMethod, new Class[] { String.class });
					setterMethod.invoke(mainObject, new Object[] { null });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		// 保存模板
		getMainService().addMain(mainObject);

		String addUrl = request.getRequestURI() + "?method=showNewSheetPage";
		String listUrl = request.getRequestURI()
				+ "?method=getTemplatesByUserId&type=templateManage";

		request.setAttribute("addUrl", addUrl);
		request.setAttribute("listUrl", listUrl);
	}

	/**
	 * 根据用户查找所有的模板(main表)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newGetTemplatesByUserId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();

		// 每页大小
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		// 当前页数的参数名
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		// 分页取得列表
		int[] aTotal = { 0 };
		// 获取模板页面分页列表
		List templates = this.mainService.getTemplatesByUserIds(userId,
				pageIndex, new Integer(pageSize.intValue()), aTotal);
		Integer total = new Integer(aTotal[0]);

		String type = request.getParameter("type");
		if (type != null && !type.equals("")) {
			request.setAttribute("templateManage", "templateManage");
		}

		String mainId = StaticMethod
				.null2String(request.getParameter("mainId"));
		request.setAttribute("mainId", mainId);

		// 将分页后的列表写入页面供使用
		request.setAttribute("taskList", templates);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
	}

	/**
	 * 呈现工单处理界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newShowInputDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String aiid = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"));
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String taskStatus = StaticMethod.nullObject2String(request
				.getParameter("taskStatus"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String processName = this.getMainService().getFlowTemplateName();

		// 获取当前task对象
		BaseLink linkObject = null;
		ITask taskModel = this.getTaskService().getSinglePO(TKID);
		String linkId = "";

		if (taskModel != null) {
			linkId = StaticMethod.nullObject2String(taskModel
					.getCurrentLinkId());
		}

		if (!linkId.equals(""))
			linkObject = this.getLinkService().getSingleLinkPO(linkId);

		if (linkObject == null) {
			linkObject = (BaseLink) getLinkService().getLinkObject().getClass()
					.newInstance();
			linkObject.setOperaterContact(sessionform.getContactMobile());
		}

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
			linkObject.setOperaterContact(StaticMethod.nullObject2String(
					linkObject.getOperaterContact(), sessionform
							.getContactMobile()));
		}

		linkObject.setOperateRoleId(operateRoleId);
		linkObject.setOperateTime(StaticMethod.getLocalTime());
		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);
		linkObject.setId(null);

		// link模
		String dealTemplateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"));
		if (!dealTemplateId.equals("")) {
			linkObject = getLinkService().getSingleLinkPO(dealTemplateId);
			linkObject.setId(null);
			request.setAttribute("dealTemplateId", dealTemplateId);
		}

		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}

		if (!preLinkId.equals("")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}

		// 取mainId
		String mainId = RequestUtils.getStringParameter(request, "mainId");
		linkObject.setMainId(mainId);
		request.setAttribute("task", taskModel);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);
		request.setAttribute("taskStatus", taskStatus);

		// 转向页面参数
		String forwordJsp = taskModel.getTaskName();
		request.setAttribute("forwordJsp", forwordJsp);

		String processTemplateName = this.getMainService()
				.getFlowTemplateName();
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder
				.getInstance().getBean("ITawSystemWorkflowManager");
		TawSystemWorkflow workflow = flowmanage
				.getTawSystemWorkflowByName(processTemplateName);
		String flowId = workflow.getFlowId();
		request.setAttribute("flowId", flowId);
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				processTemplateName, this.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		PhaseId[] phaseIds = flowDefine.getPhaseId();
		for (int i = 0; i < phaseIds.length; i++) {
			PhaseId phaseId = phaseIds[i];
			// 新增要提取roleId，tophaseid,flowid到页面去
			if (phaseId.getId().equals(forwordJsp)) {
				ToPhaseId[] toPhaseIds = phaseId.getToPhaseId();
				for (int j = 0; j < toPhaseIds.length; j++) {
					ToPhaseId toPhaseId = toPhaseIds[j];
					String actionsend = String.valueOf(Constants.ACTION_RESEND);
					if (toPhaseId.getOperatetype().equals(actionsend)) {
						request.setAttribute("roleId", toPhaseId.getRoleid());
						request.setAttribute("toPhaseId", toPhaseId.getId());
						break;
					}
				}
			}
		}

		ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
				.getInstance().getBean("ItawSheetAccessManager");
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName,
				taskName);

		request.setAttribute("methodBeanId", mapping.getAttribute());
		request.setAttribute("tawSheetAccess", access);
		request.setAttribute("module", mapping.getPath().substring(1));
		request.setAttribute("operateType", operateType);
		request.setAttribute("taskId", aiid);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		request.setAttribute("tawSheetAccess", access);
		request.setAttribute("operateRoleId", operateRoleId);
		request.setAttribute("preLinkId", preLinkId);

		// 批量回复或批量归档
		String taskIds = StaticMethod.nullObject2String(request
				.getParameter("taskIds"));
		if (!taskIds.equals("")) {
			request.setAttribute("taskIds", taskIds);
			Map tempId = new HashMap();
			StringBuffer mainIds = new StringBuffer();
			String taskCondition = " id in  ('"
					+ taskIds.replaceAll(",", "','") + "')";
			// 查出所有的任务列表
			List tasks = this.getTaskService().getTasksByCondition(
					taskCondition);
			for (Iterator it = tasks.iterator(); it.hasNext();) {
				ITask task = (ITask) it.next();
				String thismainId = task.getSheetKey();
				if (tempId.get(thismainId) == null) {
					tempId.put(thismainId, thismainId);
					if (!mainIds.toString().equals("")) {
						mainIds.append(",");
					}
					mainIds.append("'").append(thismainId).append("'");
				}
			}

			// 查出所有的main表
			String condition = " id in (" + mainIds.toString() + ")";
			List mains = this.getMainService().getMainsByCondition(condition);
			request.setAttribute("mains", mains);
		}

		// beanId
		request.setAttribute("flowId", flowId);

		String beanid = workflow.getMainServiceBeanId();
		request.setAttribute("beanId", beanid);
		// mainClassName
		Class mainclazz = this.getMainService().getMainObject().getClass();
		request.setAttribute("mainClassName", mainclazz.getName());
		// linkClassName
		Class linkclazz = this.getLinkService().getLinkObject().getClass();
		request.setAttribute("linkClassName", linkclazz.getName());
		request.setAttribute("processTemplateName", processTemplateName);

		Map toPhaseIdMap = new HashMap();

		for (int i = 0; i < phaseIds.length; i++) {
			PhaseId phaseId = phaseIds[i];
			String phaseActiveId = phaseId.getId();
			if (phaseActiveId.equals(taskModel.getTaskName())) {
				ToPhaseId[] toPhaseIds = phaseId.getToPhaseId();
				for (int j = 0; j < toPhaseIds.length; j++) {
					ToPhaseId toPhaseId = toPhaseIds[j];
					// 将驳回到另一个环节的taskName抛到页面
					if (toPhaseId.getIsrejecttask().equals("true")) {
						request.setAttribute("fPreTaskName", toPhaseId.getId());
					}
					// 每个环节中的operateType值
					if (operateType.equals(toPhaseId.getOperatetype())) {
						toPhaseIdMap.put(operateType, toPhaseId.getId());
						// 派单树的roleId
						request.setAttribute("roleId", toPhaseId.getRoleid());

					} else if (toPhaseId.getCommonoperatetype() != null
							&& toPhaseId.getCommonoperatetype().contains(
									operateType)) {
						// 每个环节中的operateType公共值（目前包括55和11）
						toPhaseIdMap.put(operateType, toPhaseId.getId());
					}

				}
				break;
			}
		}
		request.setAttribute("toPhaseIdMap", toPhaseIdMap);

	}

	/**
	 * 申明一个任务
	 */
	public void newPerformClaim(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("TKID"));

		// 获取页面输入信息
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = newSetDealRequestMap(mapping, form, request, response);
		Map serializableMap = SheetUtils.serializableParemeterMap(map);

		Iterator it = columnMap.keySet().iterator();
		HashMap WpsMap = new HashMap();
		while (it.hasNext()) {
			String mapKey = (String) it.next();
			Map tempMap = (Map) serializableMap.get(mapKey);
			HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
			if (tempMap != null) {
				// 页面上有相应的输入值
				HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
						tempColumnMap);
				WpsMap.putAll(tempWpsMap);
			} else {
				// 页面上没有相应的输入值，需要自行构建
				Iterator iterator = tempColumnMap.keySet().iterator();
				HashMap temp = new HashMap();
				while (iterator.hasNext()) {
					String key = iterator.next().toString();
					temp.put(key, null);
				}
				WpsMap.putAll(temp);
			}
		}
		setFlowEngineMap(WpsMap);
		dealFlowEngineMap(mapping, form, request, response);

		HashMap sessionMap = new HashMap();
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());

		// 申请任务
		businessFlowService.claimTask(taskId, getFlowEngineMap(), sessionMap);

		mainService.clearObjectOfCurrentSession();
		ITask task = taskService.getSinglePO(taskId);
		String path = mapping.getPath();
		path = path.substring(1);
		String forwardAction = path + ".do?method=newShowDetailPage"
				+ "&sheetKey=" + task.getSheetKey() + "&taskId=" + task.getId()
				+ "&taskName=" + task.getTaskName() + "&operateRoleId="
				+ task.getOperateRoleId() + "&TKID=" + task.getId() + "&piid="
				+ task.getProcessId() + "&taskStatus=" + task.getTaskStatus()
				+ "&preLinkId=" + task.getPreLinkId();
		request.setAttribute("findForwardAction", forwardAction);
		request.setAttribute("taskName", task.getTaskName());
		request.setAttribute("sheetKey", task.getSheetKey());

	}

	/**
	 * 中间处理环节提交时，补充添加参数
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Map newSetDealRequestMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map map = request.getParameterMap();
		// processTemplateName
		if (!map.containsKey("processTemplateName")) {
			String processTemplateName = this.getMainService()
					.getFlowTemplateName();
			map.put("processTemplateName", processTemplateName);
			// beanId
			if (!map.containsKey("beanId")) {
				ITawSystemWorkflowManager mgr = (ITawSystemWorkflowManager) ApplicationContextHolder
						.getInstance().getBean("ITawSystemWorkflowManager");
				String beanid = mgr.getTawSystemWorkflowByName(
						processTemplateName).getMainServiceBeanId();
				map.put("beanId", beanid);
			}
		}

		// mainClassName
		if (!map.containsKey("mainClassName")) {
			Class mainclazz = this.getMainService().getMainObject().getClass();
			map.put("mainClassName", mainclazz.getName());
		}

		// linkClassName
		if (!map.containsKey("linkClassName")) {
			Class linkclazz = this.getLinkService().getLinkObject().getClass();
			map.put("linkClassName", linkclazz.getName());
		}

		// link参数
		if (map.get("sheetKey") != null) {
			BaseMain main = this.getMainService().getSingleMainPO(
					((String[]) map.get("sheetKey"))[0]);

			if (!map.containsKey("piid")) {
				map.put("piid", main.getPiid());
			}
			if (!map.containsKey("correlationKey")) {
				map.put("correlationKey", main.getCorrelationKey());
			}
			if (!map.containsKey("mainId")) {
				map.put("mainId", main.getId());
			}
			if (!map.containsKey("sheetKey")) {
				map.put("sheetKey", main.getId());
			}
			if (!map.containsKey("sheetId")) {
				map.put("sheetId", main.getSheetId());
			}
			if (!map.containsKey("correlationKey")) {
				map.put("correlationKey", main.getCorrelationKey());
			}
			if (!map.containsKey("toDeptId")) {
				map.put("toDeptId", StaticMethod
						.null2String(main.getToDeptId()));
			}
			if (!map.containsKey("mainId")) {
				map.put("mainId", main.getId());
			}
		}

		if (map.get("taskId") != null) {
			ITask task = this.getTaskService().getSinglePO(
					((String[]) map.get("taskId"))[0]);

			if (!map.containsKey("TKID")) {
				map.put("TKID", task.getId());
			}
			if (!map.containsKey("aiid")) {
				map.put("aiid", task.getId());
			}
			if (!map.containsKey("activeTemplateId")) {
				map.put("activeTemplateId", task.getTaskName());
			}
			if (!map.containsKey("perLinkId")) {
				map.put("perLinkId", StaticMethod.null2String(task
						.getPreLinkId()));
			}
			if (!map.containsKey("taskName")) {
				map.put("taskName", task.getTaskName());
			}
			if (!map.containsKey("taskStatus")) {
				map.put("taskStatus", task.getTaskStatus());
			}
			if (!map.containsKey("sheetKey")) {
				map.put("mainId", task.getSheetKey());
				map.put("sheetKey", task.getSheetKey());
			}
		}

		if (!map.containsKey("taskNamespace")) {
			// 得到配置文件如：netdata-config.xml里的内容
			String workflow = this.getMainService().getFlowTemplateName();
			FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
					workflow, this.getRoleConfigPath());
			if (flowDefineExplain != null
					&& flowDefineExplain.getFlowDefine() != null) {
				map.put("taskNamespace", StaticMethod
						.null2String(flowDefineExplain.getFlowDefine()
								.getTasknamespace()));
			}
		}
		request.setAttribute("methodBeanId", mapping.getAttribute());

		// 确认受理确面里的hasNextTaskFlag
		String operateType = StaticMethod.null2String(request
				.getParameter("operateType"));
		if (operateType.equals(String
				.valueOf(Constants.OPERATEYPE_NAME_SPLIT_REPLY))
				|| operateType.equals(String
						.valueOf(Constants.OPERATEYPE_NAME_DEFER_APPLY))
				|| operateType.equals(String
						.valueOf(Constants.OPERATEYPE_NAME_REJECT))) {
			map.put("hasNextTaskFlag", "true");
		}
		return map;
	}

	/**
	 * 新增工单提交时，补充添加参数
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Map newSetAddRequestMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map map = request.getParameterMap();

		// processTemplateName
		String processTemplateName = this.getMainService()
				.getFlowTemplateName();
		map.put("processTemplateName", processTemplateName);

		// beanId
		ITawSystemWorkflowManager mgr = (ITawSystemWorkflowManager) ApplicationContextHolder
				.getInstance().getBean("ITawSystemWorkflowManager");
		String beanid = mgr.getTawSystemWorkflowByName(processTemplateName)
				.getMainServiceBeanId();
		map.put("beanId", beanid);

		// mainClassName
		Class mainclazz = this.getMainService().getMainObject().getClass();
		map.put("mainClassName", mainclazz.getName());

		// linkClassName
		Class linkclazz = this.getLinkService().getLinkObject().getClass();
		map.put("linkClassName", linkclazz.getName());

		// main参数
		map.put("status", Constants.SHEET_RUN);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("sendTime", df.format(StaticMethod.getLocalTime()));
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (sessionform != null) {
			map.put("sendUserId", sessionform.getUserid());
			map.put("sendDeptId", sessionform.getDeptid());
			map.put("sendOrgType", UIConstants.NODETYPE_SUBROLE);
			map.put("operateUserId", sessionform.getUserid());
			map.put("operateDeptId", sessionform.getDeptid());
		}

		request.setAttribute("methodBeanId", mapping.getAttribute());
		return map;
	}

	/**
	 * 对处理人、抄送人以及审核人的解析，modify by 秦敏
	 */
	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	}

	public void newSaveNonFlowData(Map dataMap) throws Exception {
		this.newSaveNonFlowData("", dataMap);
	}

	/**
	 * 非流程动作：目前包括抄送、阶段回复、阶段通知中进行link和task的保存
	 * 
	 * @param taskId
	 *            任务id
	 * @param dataMap
	 *            输入参数为Map类型，其中包括：Map类型的main、Map类型的link、Map类型的operate
	 * @return
	 * @throws Exception
	 */
	public void newSaveNonFlowData(String taskId, Map dataMap) throws Exception {

		HashMap linkMap = (HashMap) dataMap.get("link");
		HashMap operateMap = (HashMap) dataMap.get("operate");
		String operateType = StaticMethod.nullObject2String(linkMap
				.get("operateType"));
		String copyPerformer = StaticMethod.nullObject2String(operateMap
				.get("copyPerformer"));
		System.out.println("lizhi:operateType="+operateType+"copyPerformer="+copyPerformer+"taskId="+taskId);
		ITask task = null;
		BaseLink linkbean = null;
		String processTemplateName = this.getMainService()
				.getFlowTemplateName();
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		// 得到main对象Start--通过link中的mainId得到main对象；当新增时还没有mainId，则从MainMap中取得main的数据
		BaseMain main = null;
		String mainId = StaticMethod.nullObject2String(linkMap.get("mainId"));
		if(mainId.equals(""))
        {
		if (dataMap.get("main") != null) {
			HashMap mainMap = (HashMap) dataMap.get("main");
			mainId = StaticMethod.nullObject2String(mainMap.get("id"));
			main = this.getMainService().getMainObject();
			main.setId(mainId);
			main.setSheetId(StaticMethod.nullObject2String(mainMap
					.get("sheetId")));
			main.setTitle(StaticMethod.nullObject2String(mainMap.get("title")));
			main.setPiid(StaticMethod.nullObject2String(mainMap.get("piid")));
			main.setSendTime((Date) mainMap.get("sendTime"));
		}
		} else {
			main = (BaseMain)this.getMainService().getSingleMainPO(mainId);
			//throw new Exception("数据对象中工单信息为空，请通知管理员");
			 if(main == null)
	            {
	                HashMap mainMap = (HashMap)dataMap.get("main");
	                mainId = StaticMethod.nullObject2String(mainMap.get("id"));
	                main = getMainService().getMainObject();
	                main.setId(mainId);
	                main.setSheetId(StaticMethod.nullObject2String(mainMap.get("sheetId")));
	                main.setTitle(StaticMethod.nullObject2String(mainMap.get("title")));
	                main.setPiid(StaticMethod.nullObject2String(mainMap.get("piid")));
	            }
		}

		// 得到main对象end
		if (Integer.parseInt(operateType) == Constants.ACTION_DRIVERFORWARD) {
			// 阶段通知
			if (!taskId.equals("")) {
				BocoLog.info(this, "===优化======已阅===");
				task = (ITask) this.getTaskService().getSinglePO(taskId);
				task.setTaskStatus(Constants.TASK_STATUS_FINISHED);
				this.getTaskService().addTask(task);
			} else {
				BocoLog.info(this, "===优化======阶段通知===");
				String dealPerformer = StaticMethod
						.nullObject2String(operateMap.get("dealPerformer"));
				String dealPerformerLeader = StaticMethod
						.nullObject2String(operateMap
								.get("dealPerformerLeader"));
				String dealPerformerType = StaticMethod
						.nullObject2String(operateMap.get("dealPerformerType"));

				String[] dealPerformers = dealPerformer.split(",");
				String[] dealPerformerLeaders = dealPerformerLeader.split(",");
				String[] dealPerformerTypes = dealPerformerType.split(",");

				for (int i = 0; i < dealPerformers.length; i++) {
					linkbean = (BaseLink) this.getLinkService().getLinkObject()
							.getClass().newInstance();
					SheetBeanUtils.populateEngineMap2Bean(linkbean, linkMap);
					linkbean.setId(UUIDHexGenerator.getInstance().getID());
					linkbean.setMainId(mainId);
					linkbean.setOperateType(new Integer(operateType));
					linkbean.setOperateTime(nowDate);
					linkbean.setToOrgRoleId(dealPerformers[i]);
					linkbean.setOperateDay(calendar.get(Calendar.DATE));
					linkbean.setOperateMonth(calendar.get(Calendar.MONTH) + 1);
					linkbean.setOperateYear(calendar.get(Calendar.YEAR));
					// 保存task数据
					task = (ITask) this.getTaskService().getTaskModelObject()
							.getClass().newInstance();
					task.setId(UUIDHexGenerator.getInstance().getID());
					task.setTaskName("advice");
					task.setTaskDisplayName("阶段通知");
					task.setOperateRoleId(dealPerformers[i]);
					task.setTaskOwner(dealPerformerLeaders[i]);
					task.setFlowName(processTemplateName);
					task.setOperateType(dealPerformerTypes[i]);
					newSaveTask(main, linkbean, task);
					linkbean.setAiid(task.getId());
					this.getLinkService().addLink(linkbean);
				}
			}
		} else if (Integer.parseInt(operateType) == Constants.ACTION_PHASE_BACKTOUP) {
			// 阶段回复
			task = this.getTaskService().getSinglePO(taskId);
			if (task.getTaskName().equals("reply")) {
				BocoLog.info(this, "===优化======已阅===");
				task = (ITask) this.getTaskService().getSinglePO(taskId);
				task.setTaskStatus(Constants.TASK_STATUS_FINISHED);
				this.getTaskService().addTask(task);
			} else {
				BocoLog.info(this, "===优化======阶段回复===");

				String dealPerformer = StaticMethod
						.nullObject2String(operateMap.get("dealPerformer"));
				String dealPerformerLeader = StaticMethod
						.nullObject2String(operateMap
								.get("dealPerformerLeader"));
				String dealPerformerType = StaticMethod
						.nullObject2String(operateMap.get("dealPerformerType"));

				String[] dealPerformers = dealPerformer.split(",");
				String[] dealPerformerLeaders = dealPerformerLeader.split(",");
				String[] dealPerformerTypes = dealPerformerType.split(",");

				for (int i = 0; i < dealPerformers.length; i++) {
					linkbean = (BaseLink) this.getLinkService().getLinkObject()
							.getClass().newInstance();
					SheetBeanUtils.populateEngineMap2Bean(linkbean, linkMap);
					String Aiid = StaticMethod.nullObject2String(linkbean
							.getAiid(), "");
					ITask tmptask = this.getTaskService().getSinglePO(Aiid);
					linkbean.setActiveTemplateId(tmptask.getTaskName());
					linkbean.setId(UUIDHexGenerator.getInstance().getID());
					linkbean.setMainId(mainId);
					linkbean.setOperateType(new Integer(operateType));
					linkbean.setOperateTime(nowDate);
					linkbean.setToOrgRoleId(dealPerformers[i]);
					linkbean.setOperateDay(calendar.get(Calendar.DATE));
					linkbean.setOperateMonth(calendar.get(Calendar.MONTH) + 1);
					linkbean.setOperateYear(calendar.get(Calendar.YEAR));
					// 保存task数据
					task = (ITask) this.getTaskService().getTaskModelObject()
							.getClass().newInstance();
					task.setId(UUIDHexGenerator.getInstance().getID());
					task.setTaskName("reply");
					task.setTaskDisplayName("阶段回复");
					task.setOperateRoleId(dealPerformers[i]);
					task.setTaskOwner(dealPerformerLeaders[i]);
					task.setFlowName(processTemplateName);
					task.setOperateType(dealPerformerTypes[i]);
					newSaveTask(main, linkbean, task);
					linkbean.setAiid(task.getId());
					this.getLinkService().addLink(linkbean);
				}
			}
		} else if (copyPerformer.equals("")
				&& Integer.parseInt(operateType) == Constants.ACTION_READCOPY) {
			// 抄送确认
			BocoLog.info(this, "===优化======抄送确认===");
			linkbean = (BaseLink) this.getLinkService().getLinkObject()
					.getClass().newInstance();
			SheetBeanUtils.populateEngineMap2Bean(linkbean, linkMap);
			linkbean.setId(UUIDHexGenerator.getInstance().getID());
			linkbean.setOperateType(new Integer(Constants.ACTION_READCOPY));
			this.getLinkService().addLink(linkbean);
			String preLinkId = StaticMethod.nullObject2String(linkbean.getPreLinkId());
			ITask perTask = null;
			if (!"".equals(preLinkId)) {
				perTask = this.getTaskService().getTaskByPreLinkid(
						linkbean.getPreLinkId());
			}else {
				perTask = (ITask) this.getTaskService().getSinglePO(taskId);
			}
			perTask.setTaskStatus(Constants.TASK_STATUS_FINISHED);
			perTask.setCurrentLinkId(linkbean.getId());
			this.getTaskService().addTask(perTask);
		} else if (!copyPerformer.equals("")
				&& Integer.parseInt(operateType) != Constants.ACTION_DRAFT) {
			BocoLog.info(this, "===优化======抄送===");
			// 抄送
			String[] copyPerformers = copyPerformer.split(",");
			// 抄送多人
			String copyPerformerLeader = StaticMethod
					.nullObject2String(operateMap.get("copyPerformerLeader"));
			String copyPerformerType = StaticMethod
					.nullObject2String(operateMap.get("copyPerformerType"));
			String[] copyPerformerLeaders = copyPerformerLeader.split(",");
			String[] copyPerformerTypes = copyPerformerType.split(",");

			for (int i = 0; i < copyPerformers.length; i++) {
				linkbean = (BaseLink) this.getLinkService().getLinkObject()
						.getClass().newInstance();
				SheetBeanUtils.populateEngineMap2Bean(linkbean, linkMap);
				linkbean.setId(UUIDHexGenerator.getInstance().getID());
				linkbean.setOperateType(new Integer(
						Constants.ACTION_MAKECOPYFOR));
				linkbean.setOperateTime(nowDate);
				linkbean.setMainId(mainId);
				linkbean.setToOrgRoleId(copyPerformers[i]);
				linkbean.setOperateDay(calendar.get(Calendar.DATE));
				linkbean.setOperateMonth(calendar.get(Calendar.MONTH) + 1);
				linkbean.setOperateYear(calendar.get(Calendar.YEAR));
				// 保存task数据
				task = (ITask) this.getTaskService().getTaskModelObject()
						.getClass().newInstance();
				task.setTaskName("cc");
				task.setTaskDisplayName("抄送");
				task.setOperateRoleId(copyPerformers[i]);
				task.setTaskOwner(copyPerformerLeaders[i]);
				task.setFlowName(processTemplateName);
				task.setOperateType(copyPerformerTypes[i]);
				newSaveTask(main, linkbean, task);
				linkbean.setAiid(task.getId());
				this.getLinkService().addLink(linkbean);
				// 如果为抄送再抄送，则需要结束上条task
				if (Integer.parseInt(operateType) == Constants.ACTION_READCOPY) {
					ITask perTask = this.getTaskService().getTaskByPreLinkid(
							linkbean.getPreLinkId());
					perTask.setTaskStatus(Constants.TASK_STATUS_FINISHED);
					perTask.setCurrentLinkId(linkbean.getId());
					this.getTaskService().addTask(perTask);
				}
			}
		}
	}

	/**
	 * 整合用 LINK的提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performDealNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String copyPerformer = StaticMethod.nullObject2String(request
				.getParameter("copyPerformer"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));

		BocoLog.info(this, "basesheet operateType is -----------------------"
				+ operateType);

		// 获取页面输入信息
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = newSetDealRequestMap(mapping, form, request, response);

		Map serializableMap = SheetUtils.serializableParemeterMap(map);
		Iterator it = serializableMap.keySet().iterator();
		HashMap WpsMap = new HashMap();
		while (it.hasNext()) {
			String mapKey = (String) it.next();
			Map tempMap = (Map) serializableMap.get(mapKey);
			if (taskId.equals("")) {
				Object obj = tempMap.get("aiid");
				if (obj.getClass().isArray()) {
					Object[] obja = (Object[]) obj;
					obj = obja[0];
				}
				taskId = StaticMethod.nullObject2String(obj);
			}
			HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
			HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
					tempColumnMap);
			WpsMap.putAll(tempWpsMap);
		}
		setFlowEngineMap(WpsMap);
		dealFlowEngineMap(mapping, form, request, response);

		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());

		// 是否调用外部流程，若调用的话，将调用外部流程的个数放置到operate中
		ITawSheetRelationManager mgr = (ITawSheetRelationManager) ApplicationContextHolder
				.getInstance().getBean("ITawSheetRelationManager");
		String mainId = StaticMethod.nullObject2String(request
				.getParameter("mainId"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		String tempUserId = "";
		if (sessionform != null) {
			tempUserId = sessionform.getUserid();
		}

		// 查询工单互调表
		List relationAllList = mgr.getRunningSheetByPidAndPhaseIdAndUserId(
				mainId, taskName, tempUserId);
		HashMap operate = (HashMap) this.getFlowEngineMap().get("operate");
		if (relationAllList != null && relationAllList.size() > 0) {
			operate.put("reInvokeCount", new Integer(relationAllList.size()));
			BocoLog
					.info(this,
							"=======hasNextTaskFlag=======is==invokeProcess");
			operate.put("hasNextTaskFlag", "invokeProcess");
		}
		this.getFlowEngineMap().put("operate", operate);

		// 是否需要回调外部流程
		String ifReplyInvoke = StaticMethod.nullObject2String(request
				.getParameter("ifReplyInvoke"));
		if (ifReplyInvoke.equals("true")) {
			String invokePiid = StaticMethod.nullObject2String(request
					.getParameter("invokePiid"));
			String invokeOperateName = StaticMethod.nullObject2String(request
					.getParameter("invokeOperateName"));
			String invokeProcessBeanId = StaticMethod.nullObject2String(request
					.getParameter("invokeProcessBeanId"));
			String parentSheetKey = StaticMethod.nullObject2String(request
					.getParameter("invokeSheetId"));
			String toPhaseId = StaticMethod.nullObject2String(request
					.getParameter("invokePhaseId"));
			IBaseSheet parentSheet = (IBaseSheet) ApplicationContextHolder
					.getInstance().getBean(invokeProcessBeanId);

			if (parentSheetKey != null) {
				HashMap sheetMap = new HashMap();
				HashMap mainMap = new HashMap();
				HashMap linkMap = new HashMap();
				HashMap operateMap = new HashMap();

				BaseMain parentMain = parentSheet.getMainService()
						.getSingleMainPO(parentSheetKey);
				BaseLink parentLink = (BaseLink) parentSheet.getLinkService()
						.getLinkObject().getClass().newInstance();
				mainMap = SheetBeanUtils.bean2MapWithNull(parentMain);
				linkMap = SheetBeanUtils.bean2MapWithNull(parentLink);

				operateMap.put("phaseId", toPhaseId);
				operateMap.put("hasNextTaskFlag", "invokeProcess");
				sheetMap.put("main", mainMap);
				sheetMap.put("link", linkMap);
				sheetMap.put("operate", operateMap);

				parentSheet.getBusinessFlowService().reInvokeProcess(
						invokePiid, invokeOperateName, sheetMap, sessionMap);
			}
		}

		// finish task
		businessFlowService.completeHumanTask(taskId, getFlowEngineMap(),
				sessionMap);

		// 非流程动作处理
		if (!copyPerformer.equals("")
				|| Integer.parseInt(operateType) == Constants.ACTION_MAKECOPYFOR
				|| Integer.parseInt(operateType) == Constants.ACTION_PHASE_BACKTOUP
				|| Integer.parseInt(operateType) == Constants.ACTION_DRIVERFORWARD) {
			try {
				newSaveNonFlowData(taskId, getFlowEngineMap());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 整合用 非流程动作的处理：目前包括抄送、阶段回复、阶段通知
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newPerformNonFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BocoLog.info(this, "===优化======非流程动作===");
		// 获取页面输入信息

		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = this.newSetDealRequestMap(mapping, form, request, response);
		Object taskIdObject = map.get("TKID");
		if (taskIdObject != null && taskIdObject.getClass().isArray()) {
			taskIdObject = ((Object[]) taskIdObject)[0];
		}
		String taskId = StaticMethod.nullObject2String(taskIdObject);
		Map serializableMap = SheetUtils.serializableParemeterMap(map);

		Iterator it = serializableMap.keySet().iterator();
		HashMap WpsMap = new HashMap();
		while (it.hasNext()) {
			String mapKey = (String) it.next();
			Map tempMap = (Map) serializableMap.get(mapKey);
			if (taskId.equals("")) {
				Object obj = tempMap.get("aiid");
				if (obj.getClass().isArray()) {
					Object[] obja = (Object[]) obj;
					obj = obja[0];
				}
				taskId = StaticMethod.nullObject2String(obj);
			}
			HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
			HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
					tempColumnMap);
			WpsMap.putAll(tempWpsMap);
		}
		setFlowEngineMap(WpsMap);
		dealFlowEngineMap(mapping, form, request, response);

		try {
			// 如果是老流程，走老流程的抄送，确认
			InitialContext initialContext = new InitialContext();
			LocalHumanTaskManagerHome taskHome = (LocalHumanTaskManagerHome) initialContext
					.lookup("Local:ejb/com/ibm/task/api/HumanTaskManagerHome");
			final LocalHumanTaskManager humanTaskManager = taskHome.create();
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			HashMap sessionMap = new HashMap();
			String useId = sessionform.getUserid();
			String password = sessionform.getPassword();
			sessionMap.put("userId", useId);
			sessionMap.put("password", password);
			final String pbcTaskId = StaticMethod
					.nullObject2String(taskIdObject);
			if (!pbcTaskId.equals("")) {
				WPSSecutiryServiceImpl safeService = new WPSSecutiryServiceImpl();
				Subject subject = safeService.logIn(useId, password);
				java.security.PrivilegedAction getActivite = new java.security.PrivilegedAction() {
					public Object run() {
						String ishaveActivite = null;
						try {
							Task task = humanTaskManager.getTask(pbcTaskId);
							if(task != null)
								ishaveActivite = "true";
						
						} catch (Exception e) {
							e.printStackTrace();
						}
						return ishaveActivite;
					}
				};
				Object respone = com.ibm.websphere.security.auth.WSSubject
						.doAs(subject, getActivite);
				// 说明是老流程
				if (respone != null) {
					businessFlowService.completeHumanTask(taskId,
								getFlowEngineMap(), sessionMap);
				} else {
					// 新流程
					newSaveNonFlowData(taskId, this.getFlowEngineMap());
				}
			} else {
				// 新流程
				newSaveNonFlowData(taskId, this.getFlowEngineMap());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 整合用 保存task数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newSaveTask(BaseMain main, BaseLink link, ITask task)
			throws Exception {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);

		if (task.getId() == null)
			task.setId(UUIDHexGenerator.getInstance().getID());
		if (task.getCreateTime() == null)
			task.setCreateTime(nowDate);
		if (task.getTaskStatus() == null)
			task.setTaskStatus(Constants.TASK_STATUS_READY);
		if (task.getProcessId() == null && main.getPiid() != null)
			task.setProcessId(main.getPiid());
		if (task.getSheetKey() == null)
			task.setSheetKey(main.getId());
		if (task.getSheetId() == null)
			task.setSheetId(main.getSheetId());
		if (task.getTitle() == null)
			task.setTitle(main.getTitle());
		if (task.getAcceptTimeLimit() == null)
			task.setAcceptTimeLimit(link.getNodeAcceptLimit());
		if (task.getCompleteTimeLimit() == null)
			task.setCompleteTimeLimit(link.getNodeCompleteLimit());
		if (task.getPreLinkId() == null)
			task.setPreLinkId(link.getId());
		if (task.getIfWaitForSubTask() == null)
			task.setIfWaitForSubTask("false");

		task.setCreateDay(calendar.get(Calendar.DATE));
		task.setCreateMonth(calendar.get(Calendar.MONTH) + 1);
		task.setCreateYear(calendar.get(Calendar.YEAR));

		this.getTaskService().addTask(task);
	}

	/**
	 * 整合用 引用模板 (新增页面)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newReferenceTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String templateId = StaticMethod.nullObject2String(request
				.getParameter("templateId"));
		request.setAttribute("templateId", templateId);

		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		String aiid = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"));
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String taskStatus = StaticMethod.nullObject2String(request
				.getParameter("taskStatus"), "");
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"), "");
		if (!templateId.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(templateId);
			mainObject.setSendTime(StaticMethod.getLocalTime());
			request.setAttribute("sheetMain", mainObject);
		}
		request.setAttribute("sheetKey", sheetKey);
		request.setAttribute("taskId", aiid);
		request.setAttribute("piid", piid);
		request.setAttribute("taskName", taskName);
		request.setAttribute("operateRoleId", operateRoleId);
		request.setAttribute("TKID", TKID);
		request.setAttribute("preLinkId", preLinkId);
		request.setAttribute("taskStatus", taskStatus);
		request.setAttribute("operateType", operateType);

		// 设置参数flowid,roleid,tophaseid
		String processTemplateName = this.getMainService()
				.getFlowTemplateName();
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder
				.getInstance().getBean("ITawSystemWorkflowManager");
		TawSystemWorkflow workflow = flowmanage
				.getTawSystemWorkflowByName(processTemplateName);
		String flowId = workflow.getFlowId();
		request.setAttribute("flowId", flowId);
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				processTemplateName, this.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		PhaseId[] phaseIds = flowDefine.getPhaseId();
		for (int i = 0; i < phaseIds.length; i++) {
			PhaseId phaseId = phaseIds[i];
			// 新增要提取roleId，tophaseid,flowid到页面去
			if (phaseId.getId().equals("receive")) {
				ToPhaseId[] toPhaseIds = phaseId.getToPhaseId();
				for (int j = 0; j < toPhaseIds.length; j++) {
					ToPhaseId toPhaseId = toPhaseIds[j];
					String actionsend = String.valueOf(Constants.ACTION_SEND);
					if (toPhaseId.getOperatetype().equals(actionsend)) {
						request.setAttribute("roleId", toPhaseId.getRoleid());
						request.setAttribute("toPhaseId", toPhaseId.getId());
						break;
					}
				}
			}
		}

	}

	/**
	 * 得到处理环节中的工单模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newGetDealTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dealTemplateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"));

		if (dealTemplateId != null && !dealTemplateId.equals("")) {
			request.setAttribute("dealTemplateId", dealTemplateId);
		}

		BaseLink linkObject = getLinkService().getSingleLinkPO(dealTemplateId);

		if (linkObject == null) {
			linkObject = (BaseLink) getLinkService().getLinkObject().getClass()
					.newInstance();
		}

		String type = StaticMethod.nullObject2String(request
				.getParameter("type"));
		if (type != null && type.equals("templateManage")) {
			request.setAttribute("type", "templateManage");
		}
		request.setAttribute("sheetLink", linkObject);

		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		request.setAttribute("forwordJsp", taskName);

		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		request.setAttribute("operateType", operateType);

	}

	/**
	 * 工单处理信息的保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void newPerformSaveDealInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map map = this.newSetDealRequestMap(mapping, form, request, response);

		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"), "");
		String linkId = "";

		// 从页面上获取处理信息
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);

		Map serializableMap = SheetUtils.serializableParemeterMap(map);

		Iterator it = serializableMap.keySet().iterator();
		HashMap WpsMap = new HashMap();
		while (it.hasNext()) {
			String mapKey = (String) it.next();
			Map tempMap = (Map) serializableMap.get(mapKey);
			if (taskId.equals("")) {
				Object obj = tempMap.get("aiid");
				if (obj.getClass().isArray()) {
					Object[] obja = (Object[]) obj;
					obj = obja[0];
				}
				taskId = StaticMethod.nullObject2String(obj);
			}
			HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
			HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
					tempColumnMap);
			WpsMap.putAll(tempWpsMap);
		}

		// 保存处理信息，目前还有点问题，若流程互调时，如何保存被调流程的信息
		Iterator iterator = WpsMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();

			if (key.equalsIgnoreCase("main")) {
				HashMap mainHashMap = (HashMap) WpsMap.get(key);
				BaseMain mainModel = (BaseMain) this.getMainService()
						.getMainObject().getClass().newInstance();
				SheetBeanUtils.populate(mainModel, mainHashMap);
				mainModel.setStatus(new Integer(0));
				this.getMainService().addMain(mainModel);
			} else if (key.equalsIgnoreCase("link")) {
				HashMap linkHashMap = (HashMap) WpsMap.get(key);
				BaseLink linkModel = (BaseLink) this.getLinkService()
						.getLinkObject().getClass().newInstance();
				SheetBeanUtils.populate(linkModel, linkHashMap);
				// 取派往对象
				Map linkSendDomain = this.getLinkSendObject();
				for (Iterator sendIt = linkSendDomain.keySet().iterator(); sendIt
						.hasNext();) {
					String linkDomain = (String) sendIt.next();
					String sendObjectToalJSON = linkDomain + "TotalJSON";
					String[] totalJson = (String[]) map.get(sendObjectToalJSON);
					String setMethod = "set"
							+ StaticMethod.firstToUpperCase(linkDomain);
					if (totalJson != null && totalJson.length > 0) {
						try {
							Method setterMethod = linkModel.getClass()
									.getMethod(setMethod,
											new Class[] { String.class });
							setterMethod.invoke(linkModel,
									new Object[] { totalJson[0] });
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					} else {
						try {
							Method setterMethod = linkModel.getClass()
									.getMethod(setMethod,
											new Class[] { String.class });
							setterMethod.invoke(linkModel,
									new Object[] { null });
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					}
				}
				linkModel.setMainId(null);

				// 如果是在引用模板后在进行保存，linkModel里的ID就会有值，这样linkMode只会是更新模板了，不会是新增
				String dealTemplateId = request.getParameter("dealTemplateId");
				if (dealTemplateId != null && !dealTemplateId.equals("")) {
					linkModel.setId("");
				}

				linkId = this.getLinkService().addLink(linkModel);
			}

		}

		if (!linkId.equals("")) {
			ITask taskModel = this.getTaskService().getSinglePO(taskId);
			taskModel.setCurrentLinkId(linkId);
			this.getTaskService().addTask(taskModel);
		}
	}

	public void newSaveDealTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map parameterMap = request.getParameterMap();
		String dealTemplateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"), "");

		String type = StaticMethod.nullObject2String(request
				.getParameter("type"), "");

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		Date operateDate = new Date();

		BaseLink linkObject = (BaseLink) (getLinkService().getLinkObject()
				.getClass()).newInstance();
		String id = UUIDHexGenerator.getInstance().getID();
		if (dealTemplateId != null && !dealTemplateId.equals("")) {
			id = dealTemplateId;
			linkObject = getLinkService().getSingleLinkPO(id);
			operateDate = linkObject.getOperateTime();
		}

		SheetBeanUtils.populateMap2Bean(linkObject, parameterMap);

		StringBuffer templateName = new StringBuffer();
		if (!type.equals("") && type.equals("dealTemplateManage")) {
			String oldTemplateName = StaticMethod.nullObject2String(request
					.getParameter("templateName"), "");
			templateName.append(oldTemplateName);
		} else {
			if (dealTemplateId.equals("")) {
				String dealTemplateNameRule = StaticMethod
						.nullObject2String(request
								.getParameter("dealTemplateNameRule"));
				String dictKey = StaticMethod.nullObject2String(request
						.getParameter("dictKey"));
				String dealTemplateNameRuleArray[] = dealTemplateNameRule
						.split(";");
				for (int i = 0; i < dealTemplateNameRuleArray.length; i++) {
					String attributte = dealTemplateNameRuleArray[i];
					String attriName = StaticMethod.nullObject2String(request
							.getParameter(attributte));
					if (attributte.equals("operateType")) {
						String attriName2XML = (String) DictMgrLocator
								.getDictService().itemId2name(
										Util.constituteDictId(dictKey,
												"mainOperateType"),
										linkObject.getOperateType());
						templateName.append(attriName2XML).append("---");
					} else {
						templateName.append(attriName).append("---");
					}
				}
				String time = StaticMethod.date2String(new Date());
				templateName.append(time);
			} else {
				templateName.append(linkObject.getTemplateName());
			}
			request.setAttribute("noTemplateManage", "noTemplateManage");
		}

		linkObject.setTemplateName(templateName.toString());
		linkObject.setId(id);
		linkObject.setTemplateCreateUserId(sessionform.getUserid());
		linkObject.setTemplateFlag(Constants.TEMPLATE_STATUS_FLAG);
		linkObject.setMainId(null);
		linkObject.setOperateTime(operateDate);

		// 将所有的时间属性设为空
		Field[] fields = linkObject.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getType().getName().equals(Date.class.getName())) {
				String setMethod = "set"
						+ StaticMethod.firstToUpperCase(field.getName());
				try {
					Method setterMethod = linkObject.getClass().getMethod(
							setMethod, new Class[] { Date.class });
					setterMethod.invoke(linkObject, new Object[] { null });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}

		String dealOperateListUrl = request.getRequestURI()
				+ "?method=newShowListsendundo";
		String dealTemplateListUrl = request.getRequestURI()
				+ "?method=newGetDealTemplatesByUserId&type=templateManage";
		request.setAttribute("dealOperateListUrl", dealOperateListUrl);
		request.setAttribute("dealTemplateListUrl", dealTemplateListUrl);

		Map linkSendDomain = this.getLinkSendObject();
		for (Iterator sendIt = linkSendDomain.keySet().iterator(); sendIt
				.hasNext();) {
			String linkDomain = (String) sendIt.next();
			String sendObjectToalJSON = linkDomain + "TotalJSON";
			String[] totalJson = (String[]) parameterMap
					.get(sendObjectToalJSON);
			String setMethod = "set"
					+ StaticMethod.firstToUpperCase(linkDomain);
			if (totalJson != null && totalJson.length > 0) {
				try {
					Method setterMethod = linkObject.getClass().getMethod(
							setMethod, new Class[] { String.class });
					setterMethod.invoke(linkObject,
							new Object[] { totalJson[0] });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			} else {
				try {
					Method setterMethod = linkObject.getClass().getMethod(
							setMethod, new Class[] { String.class });
					setterMethod.invoke(linkObject, new Object[] { null });
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}

		getLinkService().addLink(linkObject);
	}

	/**
	 * 1、页面显示步骤从1开始循环递增到90之间，步骤最多只能有89个
	 * 2、连接线是从0开始相加，并在最后的结果前加上9，例90,91,92,93....99,910,912,999,9100,9101,9999,91000
	 */
	public void newShowWorkFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 工单的ID号
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String description = StaticMethod.nullObject2String(request
				.getParameter("description"), "");
		String dictSheetName = StaticMethod.nullObject2String(request
				.getParameter("dictSheetName"), "");
		String linkServiceName = StaticMethod.nullObject2String(request
				.getParameter("linkServiceName"), "");

		// 得到配置文件如：netdata-config.xml里的内容
		String workflow = this.getMainService().getFlowTemplateName();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflow,
				this.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		if (flowDefine == null)
			return;

		// 将link转成map 这里主要是为了在步骤列表上显示处理完成时间
		List links = this.getLinkService().getLinksByMainId(sheetKey);
		Map linkObjectMap = new HashMap();
		for (Iterator it = links.iterator(); it.hasNext();) {
			BaseLink link = (BaseLink) it.next();
			if (link.getActiveTemplateId() != null
					&& !link.getActiveTemplateId().equals("")) {
				// 确认受理
				if (link.getOperateType().intValue() == Constants.ACTION_ACCEPT) {
					linkObjectMap.put(link.getActiveTemplateId() + "1", link);
				} else {
					// 一般的
					linkObjectMap.put(link.getActiveTemplateId() + "2", link);
				}

			}
		}

		// 设置传入的参数，一个都不能少
		Map parameterMap = new HashMap();
		parameterMap.put("sheetKey", sheetKey);
		parameterMap.put("description", description);
		parameterMap.put("dictSheetName", dictSheetName);
		parameterMap.put("linkServiceName", linkServiceName);
		parameterMap.put("path", mapping.getPath());
		parameterMap.put("flowDefine", flowDefine);
		parameterMap.put("linkObjectMap", linkObjectMap);
		parameterMap.put("tasks", linkObjectMap);
		parameterMap.put("taskService", this.getTaskService());

		// 在单独类里进行产生动态流程图
		Map resultMap = new NewWorkFlow().createWorkFlow(parameterMap);

		// 将得到的结果传入页面
		String workflowXML = workflow + ".xml";
		String workFlowName = flowDefine.getDescription();
		request.setAttribute("workflowXML", workflowXML);
		request.setAttribute("workFlowName", workFlowName);
		request.setAttribute("stepList", resultMap.get("stepList"));
		request.setAttribute("joinLineList", resultMap.get("joinLineList"));
		request.setAttribute("historyList", resultMap.get("historyList"));
		request.setAttribute("currentList", resultMap.get("currentList"));
	}

	/**
	 * 处理回复通过
	 */
	public void newPerformDealReplyAccept(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map parameterMap = newSetDealRequestMap(mapping, form, request,
				response);

		String TKID = StaticMethod.nullObject2String(parameterMap.get("TKID"),
				"");

		String toTaskId = StaticMethod.nullObject2String(request
				.getParameter("toTaskId"), "");

		String[] toTaskIds = toTaskId.split(",");
		ITask selfTask = this.getTaskService().getSinglePO(TKID);
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
	}

	/**
	 * 执行处理回复不通过 实际处理是使用了重新产生子任务的方式，同时将原来任务的子任务处理标识改为true
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newPerformDealReplyReject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.newPerformCreateSubTask(mapping, form, request, response);
		String toTaskId = StaticMethod.nullObject2String(request
				.getParameter("toTaskId"));
		String[] toTaskIds = toTaskId.split(",");
		for (int i = 0; toTaskIds.length > 0 && i < toTaskIds.length; i++) {
			ITask task = this.getTaskService().getSinglePO(toTaskIds[i]);
			task.setSubTaskDealFalg("true");
			getTaskService().addTask(task);
		}
	}

	/**
	 * 创建一个子任务
	 */
	public void newPerformCreateSubTask(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map parameterMap = newSetDealRequestMap(mapping, form, request,
				response);
		String TKID = StaticMethod.nullObject2String(parameterMap.get("TKID"));
		String dealPerformer = StaticMethod.nullObject2String(request
				.getParameter("dealPerformer"));
		String dealPerformerLeader = StaticMethod.nullObject2String(request
				.getParameter("dealPerformerLeader"));
		String dealPerformerType = StaticMethod.nullObject2String(request
				.getParameter("dealPerformerType"));
		String subTtaskName = StaticMethod.nullObject2String(request
				.getParameter("subtaskName"));
		String parentTaskName = StaticMethod.nullObject2String(parameterMap
				.get("taskName"));
		String taskNamespace = StaticMethod.nullObject2String(parameterMap
				.get("taskNamespace"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		/**
		 * 从界面中获取用户输入的link信息，将这些值赋值到传递给引擎的数据map中 add by chenyuanshu 2008-10-14
		 * begin
		 */
		BaseLink link = (BaseLink) this.getLinkService().getLinkObject()
				.getClass().newInstance();
		SheetBeanUtils.populate(link, parameterMap);
		String operateTime = StaticMethod.nullObject2String(request
				.getParameter("operateTime"));
		if (operateTime.equals("")) {
			link.setOperateTime(new Date());
		}
		HashMap map = new HashMap();
		map = SheetBeanUtils.bean2Map(link);
		/**
		 * 从界面中获取用户输入的link信息，将这些值赋值到传递给引擎的数据map中 add by chenyuanshu 2008-10-14
		 * end
		 */
		String[] dealPerformers = dealPerformer.split(",");
		String[] dealPerformerLeaders = dealPerformerLeader.split(",");
		String[] dealPerformerTypes = dealPerformerType.split(",");
		for (int i = 0; i < dealPerformers.length; i++) {
			map.put("dealPerformer", dealPerformers[i]);
			map.put("dealPerformerLeader", dealPerformerLeaders[i]);
			map.put("dealPerformerType", dealPerformerTypes[i]);
			map.put("taskName", subTtaskName);
			map.put("taskNamespace", taskNamespace);
			map.put("parentTaskName", parentTaskName);
			map.put("operateType", operateType);
			map.put("dealPerformer", dealPerformers[i]);
			businessFlowService.createSubTask(TKID, map, sessionMap);
		}
	}

	/**
	 * 归档
	 */
	public String checkinWorkSheet(HashMap interfaceMap, List attach)
			throws Exception {
		Map map = this.initMap(interfaceMap, attach,
				InterfaceConstants.WORKSHEET_HOLD);
		map = setBaseMap(map);

		if (map.get("phaseId") == null)
			throw new Exception("phaseId为空");

		String parentCorrelation = StaticMethod.nullObject2String(map
				.get("serialNo"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			parentCorrelation = StaticMethod.nullObject2String(map
					.get("parentCorrelation"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			throw new Exception("parentCorrelation为空");
		System.out.println("parentCorrelation=" + parentCorrelation);

		List list = this.getMainService().getMainListByParentSheetId(
				parentCorrelation);
		if (list.size() > 0) {
			BaseMain main = (BaseMain) list.get(0);
			map.put("id", main.getId());
			map.put("operateUserId", main.getSendUserId());
			map.put("endUserId", main.getSendUserId());
			map.put("endDeptId", main.getSendDeptId());
			map.put("endRoleId", main.getSendRoleId());
			map.put("status", Constants.SHEET_HOLD);

			return this.dealSheet(main, map, attach);
		} else {
			throw new Exception("没找到parentCorrelation＝" + parentCorrelation
					+ "对应的工单");
		}
	}

	/**
	 * 派发工单
	 */
	public String newWorkSheet(HashMap interfaceMap, List attach)
			throws Exception {
		HashMap columnMap = new HashMap();
		columnMap.put("selfSheet", this.setNewInterfacePara());

		Map map = this.initMap(interfaceMap, attach,
				InterfaceConstants.WORKSHEET_NEW);
		map = setBaseMap(map);

		if (map.get("phaseId") == null)
			throw new Exception("phaseId为空");

		System.out.println("setBaseMap complete");
		map.put("correlationKey", new String[] { UUIDHexGenerator.getInstance()
				.getID() });

		WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		String processTemplateName = this.getMainService()
				.getFlowTemplateName();
		// String operateName = this.getOperateName();
		String operateName = StaticMethod.nullObject2String(interfaceMap
				.get("operateName"));
		if (operateName.length() == 0)
			throw new Exception("interfaceMap没有定义工单新增入口方法'operateName'");

		// String userId = this.getSendUser(map);
		String userId = StaticMethod.nullObject2String(interfaceMap
				.get("operateUserId"));
		if (userId.length() == 0)
			throw new Exception("interfaceMap没有定义工单接口派单人'operateUserId'");

		System.out.println("userId=" + userId);
		map.put("sendUserId", userId);
		map.put("operateUserId", userId);
		map.put("operateRoleId", map.get("sendRoleId"));

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		map.put("sendTime", new String[] { dateFormat.format(date) });

		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		TawSystemUser user = userMgr.getUserByuserid(userId);
		if (user != null) {
			map.put("sendDeptId", user.getDeptid());
			map.put("sendContact", user.getMobile());

			map.put("operateDeptId", user.getDeptid());
			map.put("operaterContact", user.getMobile());
		}

		System.out.println("prepareMap start");
		HashMap WpsMap = sm.prepareMap(map, columnMap);
		System.out.println("start addpara");
		WpsMap = this.addPara(WpsMap);
		if (WpsMap.get("corrKey") != null)
			System.out.println("add corrKey:"
					+ WpsMap.get("corrKey").toString());
		Map mainMap = sm.sendNewSheet(WpsMap, userId, processTemplateName,
				operateName);
		System.out.println("sendNewSheet over");
		return mainMap.get("sheetId").toString();
	}

	/**
	 * 重派
	 */
	public String renewWorkSheet(HashMap interfaceMap, List attach)
			throws Exception {
		Map map = this.initMap(interfaceMap, attach,
				InterfaceConstants.WORKSHEET_RENEW);
		map = setBaseMap(map);

		if (map.get("phaseId") == null)
			throw new Exception("phaseId为空");

		String parentCorrelation = StaticMethod.nullObject2String(map
				.get("serialNo"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			parentCorrelation = StaticMethod.nullObject2String(map
					.get("parentCorrelation"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			throw new Exception("parentCorrelation为空");

		List list = this.getMainService().getMainListByParentSheetId(
				parentCorrelation);
		if (list.size() > 0) {
			BaseMain main = (BaseMain) list.get(0);
			map.put("id", main.getId());
			map.put("operateRoleId", main.getSendRoleId());
			System.out.println("renew sendRoleId=" + main.getSendRoleId());
			System.out.println("renew operateRoleId="
					+ map.get("operateRoleId"));
			return this.dealSheet(main, map, attach);
		} else {
			throw new Exception("没找到parentCorrelation=" + parentCorrelation
					+ "对应的工单");
		}
	}

	/**
	 * 阶段通知
	 * 
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String suggestWorkSheet(HashMap interfaceMap, List attach)
			throws Exception {
		Map map = this.initMap(interfaceMap, attach,
				InterfaceConstants.WORKSHEET_SUGGEST);
		map = setBaseMap(map);
		map.put("operateType", "-11"); // 阶段通知标识

		HashMap columnMap = new HashMap();
		HashMap sheetMap = new HashMap();

		String parentCorrelation = StaticMethod.nullObject2String(map
				.get("serialNo"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			parentCorrelation = StaticMethod.nullObject2String(map
					.get("parentCorrelation"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			throw new Exception("parentCorrelation为空");
		BaseMain main = null;
		List list = this.getMainService().getMainListByParentSheetId(
				parentCorrelation);
		if (list.size() > 0)
			main = (BaseMain) list.get(0);
		if (main == null)
			throw new Exception("没找到parentCorrelation=" + parentCorrelation
					+ "对应的工单");

		// map.put("title", main.getTitle());
		// map.put("sheetId", main.getSheetId());

		sheetMap.put("main", main);
		sheetMap.put("link", this.getLinkService().getLinkObject());
		sheetMap.put("operate", this.getPageColumnName());
		columnMap.put("selfSheet", sheetMap);
		map.put("operateRoleId", main.getSendRoleId());

		// String operateUserId =
		// StaticMethod.nullObject2String(interfaceMap.get("operateUserId"));
		// if(operateUserId.length()==0)
		// throw new Exception("interfaceMap没有定义工单接口派单人'operateUserId'");

		WPSEngineServiceMethod sm = new WPSEngineServiceMethod();

		List taskList = this.getTaskService().getCurrentUndoTask(main.getId());
		for (int i = 0; taskList != null && i < taskList.size(); i++) {
			ITask task = (ITask) taskList.get(i);
			String roleId = task.getTaskOwner();
			map = sm.setAcceptRole(roleId, map);
			this.newSaveNonFlowData(sm.prepareMap(map, columnMap));
		}
		// sm.performProcessEvent(main.getId(),this.getMainService(),this.getTaskService(),
		// map, columnMap, operateUserId,
		// this.getMainService().getFlowTemplateName(), "nonFlowOperate");

		return main.getSheetId();
	}

	/**
	 * 退回
	 * 
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String untreadWorkSheet(HashMap interfaceMap, List attach)
			throws Exception {

		Map map = this.initMap(interfaceMap, attach,
				InterfaceConstants.WORKSHEET_UNTREAD);
		map = setBaseMap(map);
		String parentCorrelation = StaticMethod.nullObject2String(map
				.get("serialNo"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			parentCorrelation = StaticMethod.nullObject2String(map
					.get("parentCorrelation"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			throw new Exception("parentCorrelation为空");

		BaseMain main = null;
		List list = this.getMainService().getMainListByParentSheetId(
				parentCorrelation);
		if (list.size() > 0)
			main = (BaseMain) list.get(0);
		if (main == null)
			throw new Exception("没找到parentCorrelation=" + parentCorrelation
					+ "对应的工单");
		map.put("id", main.getId());
		map.put("operateRoleId", main.getSendRoleId());

		return this.dealSheet(main, map, attach);
	}

	/**
	 * 取消工单
	 * 
	 * @throws Exception
	 */
	public String cancelWorkSheet(HashMap interfaceMap) throws Exception {

		List attach = null;
		Map map = this.initMap(interfaceMap, attach,
				InterfaceConstants.WORKSHEET_CANCEL);
		map = setBaseMap(map);
		String parentCorrelation = StaticMethod.nullObject2String(map
				.get("serialNo"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			parentCorrelation = StaticMethod.nullObject2String(map
					.get("parentCorrelation"));
		if (parentCorrelation == null || parentCorrelation.equals(""))
			throw new Exception("parentCorrelation为空");

		List list = this.getMainService().getMainListByParentSheetId(
				parentCorrelation);
		if (list.size() > 0) {
			BaseMain main = (BaseMain) list.get(0);
			map.put("id", main.getId());
			map.put("status", Constants.SHEET_CANCEL);
			map.put("operateRoleId", main.getSendRoleId());
			return this.dealSheet(main, map, attach);
		} else
			throw new Exception("没找到parentCorrelation=" + parentCorrelation
					+ "对应的工单");

	}

	public Map loadDefaultMap(Map interfaceMap, String filePath, String nodePath)
			throws Exception {
		InterfaceUtilProperties properties = new InterfaceUtilProperties();
		filePath = StaticMethod.getFilePathForUrl("classpath:" + filePath);
		return properties.getMapFromXml(interfaceMap, filePath, nodePath);
	}

	public Map setBaseMap(Map map) {
		try {
			// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss");
			// Date date = new Date();

			ITawSystemWorkflowManager mgr = (ITawSystemWorkflowManager) ApplicationContextHolder
					.getInstance().getBean("ITawSystemWorkflowManager");
			System.out.println("mgr=" + mgr);
			System.out
					.println("this.getMainService()=" + this.getMainService());
			System.out.println("this.getMainService().getFlowTemplateName()="
					+ this.getMainService().getFlowTemplateName());
			System.out
					.println("mgr.getTawSystemWorkflowByName(this.getMainService().getFlowTemplateName())="
							+ mgr.getTawSystemWorkflowByName(this
									.getMainService().getFlowTemplateName()));

			String mainBeanId = mgr.getTawSystemWorkflowByName(
					this.getMainService().getFlowTemplateName())
					.getMainServiceBeanId();
			System.out.println("mainBeanId=" + mainBeanId);

			map.put("beanId", new String[] { mainBeanId });
			System.out.println("mainClassName="
					+ this.getMainService().getMainObject().getClass()
							.getName());
			System.out.println("linkClassName="
					+ this.getLinkService().getLinkObject().getClass()
							.getName());
			map.put("mainClassName", new String[] { this.getMainService()
					.getMainObject().getClass().getName() });
			map.put("linkClassName", new String[] { this.getLinkService()
					.getLinkObject().getClass().getName() });

			// if(map.get("sheetCompleteLimit")==null)
			// map.put("sheetCompleteLimit",new
			// String[]{dateFormat.format(date)});
			// if(map.get("taskCompleteTime")==null)
			// map.put("taskCompleteTime",new
			// String[]{dateFormat.format(date)});

		} catch (Exception err) {
			err.printStackTrace();
		}
		return map;
	}

	public String dealSheet(BaseMain main, Map map, List attach)
			throws Exception {

		HashMap columnMap = new HashMap();
		HashMap sheetMap = new HashMap();

		sheetMap.put("main", main);
		sheetMap.put("link", this.getLinkService().getLinkObject());
		sheetMap.put("operate", Constants.pageColumnName);
		columnMap.put("selfSheet", sheetMap);

		String operateUserId = StaticMethod.nullObject2String(map
				.get("operateUserId"));
		if (operateUserId.length() == 0)
			throw new Exception("interfaceMap没有定义工单接口派单人'operateUserId'");

		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		TawSystemUser user = userMgr.getUserByuserid(operateUserId);
		if (user != null) {
			map.put("sendDeptId", user.getDeptid());
			map.put("sendContact", user.getMobile());

			map.put("operateDeptId", user.getDeptid());
			map.put("operaterContact", user.getMobile());
		}

		map.put("operateUserId", operateUserId);
		map.put("sheetId", main.getSheetId());
		map.put("correlationKey", main.getCorrelationKey());
		map.put("mainId", main.getId());
		if (map.get("phaseId") == null)
		{
			throw new Exception("phaseId");
		} else
		{
			String sheetKey = main.getId();
			WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
			return sm.dealSheet(sheetKey, map, columnMap, operateUserId, this
					.getTaskService());
		}

	}

	public void createNewSheetInstance(HashMap interfaceMap, List attach)
			throws Exception {
		Map map = this.initMap(interfaceMap, attach,
				InterfaceConstants.WORKSHEET_NEWINSTANCE);
		map = setBaseMap(map);

		String sheetKey = StaticMethod.nullObject2String(map.get("sheetKey"));
		if (sheetKey.equals(""))
			throw new Exception("sheetKey不能为空");

		String taskId = StaticMethod.nullObject2String(map.get("taskId"));
		if (taskId.equals(""))
			throw new Exception("taskId不能为空");

		String phaseId = StaticMethod.nullObject2String(map.get("phaseId"));
		if (phaseId.equals(""))
			throw new Exception("phaseId不能为空");

		String ifSaveLink = StaticMethod.nullObject2String(map
				.get("ifSaveLink"));
		if (ifSaveLink.equals(""))
			throw new Exception("ifSaveLink不能为空");

		WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		sm.singleEventPerform(sheetKey, taskId, phaseId, this, Boolean
				.valueOf(ifSaveLink), map);
	}

	// public abstract String getMainBeanId();
	// public abstract String getLinkBeanId();
	// public abstract String getTaskBeanId();
	// public abstract String getProcessTemplateName();
	// public abstract String getOperateName();
	// public abstract String getSendUser(Map map);
	public String getSheetAttachCode() {
		return null;
	}

	public Map initMap(Map map, List attach, String type) throws Exception {
		return null;
	}

	public HashMap addPara(HashMap hashMap) {
		return hashMap;
	}

	public Map setNewInterfacePara() throws Exception {
		HashMap sheetMap = new HashMap();
		sheetMap.put("main", this.getMainService().getMainObject().getClass()
				.newInstance());
		sheetMap.put("link", this.getLinkService().getLinkObject().getClass()
				.newInstance());
		sheetMap.put("operate", this.getPageColumnName());

		return sheetMap;
	}

	public String getAttach(List attachList)
	{
		if (attachList != null && attachList.size() > 0)
		{
			String attachCode = getSheetAttachCode();
			if (attachCode.length() == 0)
			{
				System.out.println("attachCodeÎª¿Õ");
			} else
			{
				String fileName = "";
				ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager)ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
				for (int i = 0; i < attachList.size(); i++)
				{
					AttachRef attach = (AttachRef)attachList.get(i);
					String cnName = attach.getAttachName();
					String url = attach.getAttachURL();
					fileName = fileName + mgr.downFromOtherSystem(cnName, url, getSheetAttachCode()) + ",";
				}

				if (fileName.length() > 0)
					fileName = fileName.substring(0, fileName.length() - 1);
				return "'" + fileName + "'";
			}
		}
		return null;
	}

	public Map initMap(Map map) {
		return map;
	}

}
