/*
 * Created on 2007-8-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.model.Feed;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.RequestUtils;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StringUtil;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.webapp.form.TawCommonsAccessoriesForm;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.prm.service.impl.Pojo2PojoServiceImpl;
import com.boco.eoms.sheet.base.atom.AtomUtilForTask;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.flowchar.WholeGraph;
import com.boco.eoms.sheet.base.flowchar.WorkFlow;
import com.boco.eoms.sheet.base.flowchar.xmltree.Graph;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.EomsInterpreter;
import com.boco.eoms.sheet.base.util.QuerySqlInit;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.base.util.flowdefine.xml.ToPhaseId;
import com.boco.eoms.sheet.dealtypeconfig.util.DealTypeConfigUtil;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.service.ISheetDealLimitManager;
import com.boco.eoms.sheet.limit.util.SheetLimitUtil;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;
import com.boco.eoms.sheet.tool.relation.webapp.form.TawSheetRelationForm;

/** 
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseSheet extends NewBaseSheet implements IBaseSheet {
	/**
	 * 工单的初始化页面 本流程新增调用
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String showNewSheetPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionForward="new";
		//判断当前用户是否具有建单者权限
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
        ITawSystemSubRoleManager roleManager = (ITawSystemSubRoleManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemSubRoleManager");
        String processName=this.getMainService().getFlowTemplateName();
        ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
		    .getInstance().getBean("ITawSystemWorkflowManager");
        TawSystemWorkflow wf=wfManager.getTawSystemWorkflowByName(processName);
        String roleId=StaticMethod.nullObject2String(wf.getRoleId());
        if(!roleId.equals("")){
         List list=roleManager.getSubRoles(sessionform.getUserid(),roleId);
         if(list == null || list.size() == 0) {
        	actionForward="nopriv";
        	return actionForward;
         }
        }
		String parentCorrelation = StaticMethod.nullObject2String(request
				.getParameter("parentCorrelation"), "");
		String parentSheetId = StaticMethod.nullObject2String(request
				.getParameter("parentSheetId"), "");
		String parentSheetName = StaticMethod.nullObject2String(request
				.getParameter("parentSheetName"), "");
		String parentPhaseName = StaticMethod.nullObject2String(request
				.getParameter("parentPhaseName"), "");
		String invokeMode = StaticMethod.nullObject2String(request
				.getParameter("invokeMode"), "");

		request.setAttribute("parentCorrelation", parentCorrelation);
		request.setAttribute("parentSheetId", parentSheetId);
		request.setAttribute("parentSheetName", parentSheetName);
		request.setAttribute("parentPhaseName", parentPhaseName);
		request.setAttribute("invokeMode", invokeMode);
		return actionForward;
	}

	/**
	 * 工单的初始化页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showInputNewSheetPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		// MainForm mainform = this.getMainService().getMainForm();
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		// BaseMain mainObject = (BaseMain) Class.forName(
		// this.getMainService().getMainObject().getClass().getName()).newInstance();
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
		String acceptLimit = StaticMethod.getLocalString(1);
		String completeLimit = StaticMethod.getLocalString(3);
		mainObject.setSheetAcceptLimit(SheetUtils.stringToDate(acceptLimit));
		mainObject.setSheetCompleteLimit(SheetUtils.stringToDate(completeLimit));
		mainObject.setSendTime(StaticMethod.getLocalTime());

		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}
		String parentCorrelation = StaticMethod.nullObject2String(request
				.getParameter("parentCorrelation"), "");
		String parentSheetId = StaticMethod.nullObject2String(request
				.getParameter("parentSheetId"), "");
		String parentSheetName = StaticMethod.nullObject2String(request
				.getParameter("parentSheetName"), "");
		String parentPhaseName = StaticMethod.nullObject2String(request
				.getParameter("parentPhaseName"), "");
		String invokeMode = StaticMethod.nullObject2String(request
				.getParameter("invokeMode"), "");
		
		mainObject.setParentCorrelation(parentCorrelation);
		mainObject.setParentSheetId(parentSheetId);
		mainObject.setParentSheetName(parentSheetName);
		mainObject.setParentPhaseName(parentPhaseName);
		mainObject.setInvokeMode(invokeMode);

		String invokerId = StaticMethod.nullObject2String(request.getParameter("invokerId"), "");

		if (!invokerId.equals("")) {
			String invokerObject = StaticMethod.nullObject2String(request
					.getParameter("invokerObject"), "");
			IBaseSheet basesheet = (IBaseSheet) ApplicationContextHolder
					.getInstance().getBean(invokerObject);
			BaseMain baseMain = basesheet.getMainService().getSingleMainPO(
					invokerId);
			if (baseMain != null) {
				Pojo2PojoServiceImpl p2pService = (Pojo2PojoServiceImpl) ApplicationContextHolder
						.getInstance().getBean("p2pService");
				p2pService.p2p(baseMain, mainObject);
			}
		}
		if (!parentSheetId.equals("") && !parentSheetName.equals("")) {
			IMainService parentMainSerivce = (IMainService) ApplicationContextHolder
					.getInstance().getBean(parentSheetName);
			BaseMain parentMain = parentMainSerivce.getSingleMainPO(parentSheetId);
			String tmpparentSheetId = parentMain.getSheetId();

			ITawSystemWorkflowManager mgr = (ITawSystemWorkflowManager) ApplicationContextHolder
					.getInstance().getBean("ITawSystemWorkflowManager");
			TawSystemWorkflow workflow = mgr
					.getTawSystemWorkflowByBeanId(parentSheetName);
			String parentProcessName = workflow.getName();

			request.setAttribute("parentSheetId", tmpparentSheetId);
			request.setAttribute("parentProcessName",parentProcessName);
			
			System.out.println("parentSheetId=" + parentSheetId
					+ "parentProcessName=" + parentProcessName);
		}
		request.setAttribute("sendUserName", sendUserName);
		request.setAttribute("sendDeptName", sendDeptName);
		request.setAttribute("sendRoleName", sendRoleName);
		request.setAttribute("sheetMain", mainObject);
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("status", Constants.SHEET_RUN);
		request.setAttribute("sendOrgType", UIConstants.NODETYPE_SUBROLE);
		request.setAttribute("methodBeanId", mapping.getAttribute());

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
	 */
	public void performAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);// 获取main link operate对象
		Map map = request.getParameterMap();
		System.out.println("=====request Map parentPhaseName:"+StaticMethod.nullObject2String(request.getParameter("parentPhaseName"), ""));
		Map serializableMap = SheetUtils.serializableParemeterMap(map);
		Iterator it = serializableMap.keySet().iterator();
		HashMap WpsMap = new HashMap();
		while (it.hasNext()) {
			String mapKey = (String) it.next();
			Map tempMap = (Map) serializableMap.get(mapKey);
			HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
			HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
					tempColumnMap);
			WpsMap.putAll(tempWpsMap);
		}

		String processTemplateName = StaticMethod.nullObject2String(request
				.getParameter("processTemplateName"));
		String operateName = StaticMethod.nullObject2String(request
				.getParameter("operateName"));

		setFlowEngineMap(WpsMap);
		dealFlowEngineMap(mapping, form, request, response);
		
		
		Map tmpmain = (Map) WpsMap.get("main");
		String tmpparentPhaseName = (String) tmpmain.get("parentPhaseName");
		System.out.println("==========basesheet:parentPhaseName:"
				+ tmpparentPhaseName);
		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		// Subject subject = (Subject) request.getSession().getAttribute(
		// "wpsSubject");
		// sessionMap.put("wpsSubject", subject);
		/** add by qinmin* */
		businessFlowService.initProcess(processTemplateName, operateName,
				getFlowEngineMap(), sessionMap);
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
			ITask task = null;
			try {
				task = (ITask) request.getAttribute("task");
				if (task == null) {
					task = this.getTaskService().getSinglePO(TKID);
				}
				//task = this.getTaskService().getSinglePO(TKID);
				String isWaitForSubTask = task.getIfWaitForSubTask();
				if (isWaitForSubTask.equals("true")) {
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

	/**
	 * 修改工单后提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	}

	/**
	 * 驳回
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showRejectPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String aiid = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		request.setAttribute("taskId", aiid);
		request.setAttribute("piid", piid);
		request.setAttribute("taskName", taskName);
	}

	/**
	 * 驳回
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showInputRejectPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		String aiid = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		BaseLink linkObject = (BaseLink) getLinkService().getLinkObject()
				.getClass().newInstance();
		String operateUserName = "";
		String operateDeptName = "";
		String operateRoleName = "";
		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
			linkObject.setOperateRoleId(sessionform.getRoleid());
			operateUserName = sessionform.getUsername();
			operateDeptName = sessionform.getDeptname();
			operateRoleName = sessionform.getRolename();
		}
		linkObject.setOperateType(new Integer(4));// 标识驳回
		String acceptLimit = StaticMethod.getLocalString(1);
		String completeLimit = StaticMethod.getLocalString(3);
		linkObject.setNodeAcceptLimit(SheetUtils.stringToDate(acceptLimit));
		linkObject.setNodeCompleteLimit(SheetUtils.stringToDate(completeLimit));
		request.setAttribute("operateUserName", operateUserName);
		request.setAttribute("operateDeptName", operateDeptName);
		request.setAttribute("operateRoleName", operateRoleName);
		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
	}

	/**
	 * 显示草稿
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showDraftPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"), "");
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String templateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"));
		String backFlag = StaticMethod.nullObject2String(request
				.getParameter("backFlag"));
		BocoLog.debug(this, "sheetKey==" + sheetKey);
		if (!taskName.equals("")){
			setParentTaskOperateWhenRejct(request);
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = new BaseMain();
			if (taskName.equalsIgnoreCase("DraftHumTask")
					&& !templateId.equals("")) {
				mainObject = getMainService().getSingleMainPO(templateId);
			} else {
				mainObject = getMainService().getSingleMainPO(sheetKey);
			}
			request.setAttribute("sheetMain", mainObject);
			request.setAttribute("status", Constants.SHEET_RUN);
			request.setAttribute("sheetKey", sheetKey);
			request.setAttribute("taskId", taskId);
			request.setAttribute("taskName", taskName);
			request.setAttribute("piid", piid);
			request.setAttribute("TKID", TKID);
			request.setAttribute("operateType", operateType);
		} else {
			throw new SheetException(this.getClass().getName()
					+ "工单主键为空,请联系管理员!");
		}
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		String preTaskId = "";
		if (!preLinkId.equals("")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			preTaskId = preLink.getAiid();
			request.setAttribute("preLink", preLink);
		}

		// 取得流程听Operate里的角色
		
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		Map processOjbectAttribute = this.getProcessOjbectAttribute();
		JSONArray sendUserAndRoles = new JSONArray();

		String objectName = (processOjbectAttribute.get("objectName") == null ? ""
				: (String) processOjbectAttribute.get("objectName"));
		Map parameterValuemap = businessFlowService.getVariable(piid,
				objectName, sessionMap);

		// 从Map里移出流程对象
		processOjbectAttribute.remove("objectName");

		if (backFlag.equals("")) {
			// 依次循环取出Map里对象中的属性
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
					String[] dealPerformerValues = dealPerformerValue
							.split(",");
					for (int i = 0; i < dealPerformerValues.length; i++) {
						JSONObject data = new JSONObject();
						String finalDealPerformerValue = dealPerformerValues[i];
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
		}
		request.setAttribute("sendUserAndRoles", sendUserAndRoles);
		request.setAttribute("mainId", sheetKey);
		request.setAttribute("taskId", taskId);
		request.setAttribute("taskName", taskName);
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());

	}

	/**
	 * 显示归档页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showHoldPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"), "");
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		BocoLog.debug(this, "sheetKey==" + sheetKey);

		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);

			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String endUserId = "";
			String endDeptId = "";
			String endRoleId = "";
			if (sessionform != null) {

				endUserId = sessionform.getUserid();
				endDeptId = sessionform.getDeptid();
				endRoleId = StaticMethod.nullObject2String(request
						.getParameter("operateRoleId"));
			}
			request.setAttribute("endUserId", endUserId);
			request.setAttribute("endDeptId", endDeptId);
			request.setAttribute("endRoleId", endRoleId);
			request.setAttribute("status", Constants.SHEET_HOLD);
			request.setAttribute("sheetKey", sheetKey);
			request.setAttribute("taskId", taskId);
			request.setAttribute("taskName", taskName);
			request.setAttribute("piid", piid);
			request.setAttribute("TKID", TKID);
		} else {
			throw new SheetException(this.getClass().getName()
					+ "工单主键为空,请联系管理员!");
		}
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		if (!preLinkId.equals("")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}
		// List list = getLinkService().getLinksByMainId(sheetKey);
		// add by leo 草稿需要传入mainId，以便于在wps中修改main对象（DB）
		request.setAttribute("mainId", sheetKey);
		request.setAttribute("taskId", taskId);
		request.setAttribute("taskName", taskName);
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
	}

	/**
	 * 显示撤销框架页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showCancelInputPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String mainId = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));
		request.setAttribute("piid", piid);
		request.setAttribute("mainId", mainId);
	}

	public void showRemarkPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"), "");
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		BaseLink baselink = this.getLinkService().getSingleLinkPO(preLinkId);
		request.setAttribute("baselink", baselink);
		request.setAttribute("sheetLink", baselink);
	//	String remark = baselink.getRemark();
		String mainId = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));
		request.setAttribute("taskName", taskName);
		request.setAttribute("tkid", TKID);
		ITask task = this.getTaskService().getSinglePO(TKID);
		request.setAttribute("task", task);
		request.setAttribute("piid", piid);
		request.setAttribute("mainId", mainId);
	//	request.setAttribute("remark", remark);
		
		
		//在整合中没有必要整合到新的版本中所以就在这里修改添加了几个参数
		String processTemplateName = this.getMainService().getFlowTemplateName();
		//beanId
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		String beanid = flowmanage.getTawSystemWorkflowByName(processTemplateName).getMainServiceBeanId();
		request.setAttribute("beanId", beanid);
		// mainClassName
		Class mainclazz = this.getMainService().getMainObject().getClass();
		request.setAttribute("mainClassName", mainclazz.getName());
		// linkClassName
		Class linkclazz = this.getLinkService().getLinkObject().getClass();
		request.setAttribute("linkClassName", linkclazz.getName());
		request.setAttribute("processTemplateName", processTemplateName);
		
		
	}

	/**
	 * 显示撤销页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showCancelPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		String processId = StaticMethod.nullObject2String(request
				.getParameter("processId"), "");
		BocoLog.debug(this, "sheetKey==" + sheetKey);

		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);

			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String endUserName = "";
			String endDeptName = "";
			String endRoleName = "";
			if (sessionform != null) {
				mainObject.setSendUserId(sessionform.getUserid());
				String deptId = sessionform.getDeptid();
				if ("".equals(deptId.trim())) {
					deptId = "0";
				}
				mainObject.setSendDeptId(deptId);
				String roleId = sessionform.getRoleid();
				if (roleId == null || "".equals(roleId.trim())) {
					roleId = "0";
				}
				mainObject.setSendRoleId(roleId);
				endUserName = sessionform.getUsername();
				endDeptName = sessionform.getDeptname();
				endRoleName = sessionform.getRolename();
			}
			request.setAttribute("endUserName", endUserName);
			request.setAttribute("endDeptName", endDeptName);
			request.setAttribute("endRoleName", endRoleName);
			request.setAttribute("status", Constants.SHEET_CANCEL);

		} else {
			throw new SheetException(this.getClass().getName()
					+ "工单主键为空,请联系管理员!");
		}
		request.setAttribute("mainId", sheetKey);
		request.setAttribute("piid", processId);
	}

	/**
	 * 显示归档页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showDeletePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");
		BocoLog.debug(this, "sheetKey==" + sheetKey);

		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);

			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String endUserName = "";
			String endDeptName = "";
			String endRoleName = "";
			if (sessionform != null) {
				mainObject.setSendUserId(sessionform.getUserid());
				String deptId = sessionform.getDeptid();
				if ("".equals(deptId.trim())) {
					deptId = "0";
				}
				mainObject.setSendDeptId(deptId);
				String roleId = sessionform.getRoleid();
				if (roleId == null || "".equals(roleId.trim())) {
					roleId = "0";
				}
				mainObject.setSendRoleId(roleId);
				endUserName = sessionform.getUsername();
				endDeptName = sessionform.getDeptname();
				endRoleName = sessionform.getRolename();
			}
			request.setAttribute("endUserName", endUserName);
			request.setAttribute("endDeptName", endDeptName);
			request.setAttribute("endRoleName", endRoleName);
			request.setAttribute("status", Constants.SHEET_DELETE);

		} else {
			throw new SheetException(this.getClass().getName()
					+ "工单主键为空,请联系管理员!");
		}
		// List list = getLinkService().getLinksByMainId(sheetKey);
		// add by leo 草稿需要传入mainId，以便于在wps中修改main对象（DB）
		request.setAttribute("mainId", sheetKey);
		request.setAttribute("taskId", taskId);
		request.setAttribute("taskName", taskName);
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
	}

	/**
	 * 显示工单查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showQueryPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String workflowName = this.getMainService().getFlowTemplateName();
		//找出流程的ID号
		ITawSystemWorkflowManager wfManager=
    		(ITawSystemWorkflowManager)ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		TawSystemWorkflow workflow = wfManager.getTawSystemWorkflowByName(workflowName);
		request.setAttribute("flowId", workflow.getFlowId());
		
		//设置初始化时间
		Calendar startDay = Calendar.getInstance();
		startDay.add(Calendar.DAY_OF_MONTH, -3);
		Calendar endDay = Calendar.getInstance();		
		String startDate = DateUtil.formatDate(startDay.getTime(), "yyyy-MM-dd hh:mm:ss");
		String endDate = DateUtil.formatDate(endDay.getTime(), "yyyy-MM-dd hh:mm:ss");
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		
		//找出该流程中的节点
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName,this.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		if (flowDefine != null) {
			PhaseId phaseIds[] = flowDefine.getPhaseId();
			for (int i = 0; i < phaseIds.length; i ++) {
				PhaseId phaseId = phaseIds[i];
				if (!phaseId.getId().equals("receive")) {
					phaseIdMap.put(phaseId.getId(), phaseId.getName());
					phaseIdList.add(phaseId.getId());
				}
			}
		}
		request.setAttribute("phaseIdMap", phaseIdMap);
		request.setAttribute("stepIdList", phaseIdList);
		request.setAttribute("module", mapping.getPath().substring(1));
	}


	/**
	 * 查询提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map actionMap = request.getParameterMap();

		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
        //页数的参数名
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		// 分页取得列表
		// wps端分页取得列表

		String[] aSql = { "" };
		int[] aTotal = { 0 };
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
		Map condition = new HashMap();
		condition.put("orderCondition", orderCondition);
		String exportType = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		String queryType = StaticMethod.nullObject2String(request
				.getParameter("queryType"));

		List result = this.getMainService().getQueryResult(aSql, actionMap,
				condition, pageIndex, new Integer(pageSize.intValue()), aTotal,
				queryType);

		Integer total = new Integer(aTotal[0]);

		if (queryType != null && queryType.equals("number")) {
			request.setAttribute("recordTotal", total);
		}
		request.setAttribute("taskList", result);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);

	}
	
	
	/**
	 * 列表查询提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performListQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map actionMap = request.getParameterMap();

		// 获取当前用户的角色列表
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject()
				.getClass().newInstance();
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

		String flowName = this.getMainService().getFlowTemplateName();
		String beanName = mapping.getAttribute();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		condition.put("userId", userId);
		condition.put("deptId", deptId);
		condition.put("flowName", flowName);
		condition.put("beanName", beanName);		

		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		String exportType = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}

		// 分页取得列表
		String[] aSql = { "" };
		int[] aTotal = { 0 };
		List result = this.getMainService().getQueryAclListResult(aSql,
				actionMap, condition, pageIndex,
				new Integer(pageSize.intValue()), aTotal, userId, deptId);
		Integer total = new Integer(aTotal[0]);
		List taskList = new ArrayList();
		List taskMapList = new ArrayList();
		List mainMapList = new ArrayList();	
		Object done = actionMap.get("doneType");
		if (done != null && done.getClass().isArray()) {
			done = ((Object[]) done)[0];
		}
		if (done != null) {
			if (done.equals("senddone")) {
		        String[] variables= QuerySqlInit.getAllDictItemsName(beanName).split(",");	
                for(int i = 0;result!=null && ! result.isEmpty()&& i < result.size();i++ ){
        			Object[] objectList  = (Object[]) result.get(i);
        			Map ListMap = new HashMap();
        			for(int j= 0;j < objectList.length; j++){
        				String variablesKey = variables[j];
        				if(variablesKey.indexOf("main.")>=0 || variablesKey.indexOf("task.")>=0){
        					variablesKey = variablesKey.substring(5);
        				}
        				ListMap.put(variablesKey, objectList[j]);			
        			}
        			mainMapList.add(ListMap);
                }
				request.setAttribute("taskList", mainMapList);

			} else if (done.equals("sendundo")) {

				// 设置每条task超时标识
				if (result != null && result.size() > 0) {
					// 查询超时配置表
					IOvertimeTipManager service = (IOvertimeTipManager) ApplicationContextHolder
							.getInstance().getBean("iOvertimeTipManager");
					List timeList = service.getEffectOvertimeTip(this
							.getMainService().getFlowTemplateName(), userId);
					// 得到角色细分字段
					HashMap columnMap = OvertimeTipUtil
							.getAllMainColumnByMapping(flowName);
					HashMap columnMapOverTip = OvertimeTipUtil
							.getNotOverTimeColumnByMapping(flowName);
					// 循环为task超时标识赋值
					for (int i = 0; i < result.size(); i++) {
						ITask tmptask = null;
						Map taskMap = new HashMap();
						Map tmptaskMap = new HashMap();
						HashMap conditionMap = new HashMap();
						if (columnMap.size() > 0) {
							Object[] tmpObjArr = (Object[]) result.get(i);
							tmptask = (ITask) tmpObjArr[0];
							// 根据角色细分得到需要匹配的字段
							Iterator it = columnMap.keySet().iterator();
							int j = 0;
							while (it.hasNext()) {
								j++;
								String elementKey = (String) it.next();
								Object tempcolumn = tmpObjArr[j];
								conditionMap.put(elementKey, tempcolumn);
								tmptaskMap.put(columnMap.get(elementKey),
										tempcolumn);
							}
						} else {
							tmptask = (ITask) result.get(i);
						}
						// 得到超时类型
						if (exportType.equals("")) {
							String overtimeFlag = OvertimeTipUtil
									.setOvertimeTipFlag(columnMapOverTip,
											tmptask.getCompleteTimeLimit(),
											conditionMap, timeList, flowName);
							taskMap.put("overtimeType", overtimeFlag);
						}
						taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
						taskMap.putAll(tmptaskMap);
						taskList.add(tmptask);
						taskMapList.add(taskMap);
					}
				}
				request.setAttribute("taskList", taskMapList);
			}
		}

		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);

		String findForward = StaticMethod.null2String(request
				.getParameter("findForward"));
		request.setAttribute("findForward", findForward);

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
		request.setAttribute("module", mapping.getPath().substring(1));

		// 是否要进行批量处理
		String batch = StaticMethod.nullObject2String(request
				.getParameter("batch"));
		if (!batch.equals("")) {
			request.setAttribute("batch", batch);
			if (!batch.equals("") && batch.equals("true")) {
				// 需要进行批量回复和批量归档的步骤放入到tempMap中
				Map tempMap = new HashMap();
				String dictName = "dict-sheet-"
						+ mapping.getPath().substring(1);
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
					for (Iterator it = tempMap.keySet().iterator(); it
							.hasNext();) {
						String taskName = (String) it.next();
						if (result != null) {
							for (Iterator tasks = taskList.iterator(); tasks
									.hasNext();) {
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
				}

				request.setAttribute("batchTaskMapKey", batchTaskMap.keySet());
				request.setAttribute("batchTaskMap", batchTaskMap);
			}
		}
	}

	public void showDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
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
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String dealTemplateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"));
		String backFlag = StaticMethod.nullObject2String(request
				.getParameter("backFlag"));
		/**同组处理标志**/
		String teamFlag = StaticMethod.nullObject2String(request
				.getParameter("teamFlag"));

		if (!backFlag.equals("")) {
			request.setAttribute("backFlag", backFlag);
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		String taskStatus = StaticMethod.nullObject2String(request
				.getParameter("taskStatus"));

		request.setAttribute("taskStatus", taskStatus);
		request.setAttribute("sheetKey", sheetKey);
		request.setAttribute("taskId", aiid);
		request.setAttribute("piid", piid);
		request.setAttribute("taskName", taskName);
		request.setAttribute("operateRoleId", operateRoleId);
		request.setAttribute("TKID", TKID);
		request.setAttribute("preLinkId", preLinkId);
		request.setAttribute("operateType", operateType);
		request.setAttribute("dealTemplateId", dealTemplateId);
		request.setAttribute("module",mapping.getPath().substring(1));
		request.setAttribute("teamFlag", teamFlag);
		//是否是批量回复的转向
		String batch = StaticMethod.nullObject2String(request.getParameter("batch"));
		if (!batch.equals("")) {
			request.setAttribute("batch", batch);
			String taskIds = StaticMethod.nullObject2String(request.getParameter("taskIds"));
			request.setAttribute("taskIds", taskIds);
		}

	}

	public void showInputDealPage(ActionMapping mapping, ActionForm form,
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
		String processName = this.getMainService().getFlowTemplateName();
		String teamFlag = StaticMethod.nullObject2String(request
				.getParameter("teamFlag"));

		// 获取当前task对象
		BaseLink linkObject = null;
		ITask taskModel = this.getTaskService().getSinglePO(TKID);
		String linkId = "";
		if (taskModel != null) {
			linkId = StaticMethod.nullObject2String(taskModel.getCurrentLinkId());
		}
		if (!linkId.equals(""))
			linkObject = this.getLinkService().getSingleLinkPO(linkId);
		if (linkObject == null) {
			linkObject = (BaseLink) getLinkService().getLinkObject().getClass()
					.newInstance();
			linkObject.setOperaterContact(sessionform.getContactMobile());
			String acceptLimit = StaticMethod.getLocalString(1);
			String completeLimit = StaticMethod.getLocalString(3);
			linkObject.setNodeAcceptLimit(SheetUtils.stringToDate(acceptLimit));
			linkObject.setNodeCompleteLimit(SheetUtils
					.stringToDate(completeLimit));
		}

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
			linkObject.setOperaterContact(StaticMethod.nullObject2String(
					linkObject.getOperaterContact(), sessionform
							.getContactMobile()));
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		if (!preLinkId.equals("") && !preLinkId.equals("null")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}
		// linkObject.setOperateType(new Integer(1));// 转派标识
		linkObject.setOperateRoleId(operateRoleId);
		// linkObject.setNodeAcceptLimit(StaticMethod.getLocalTime());
		// linkObject.setNodeCompleteLimit(StaticMethod.getLocalTime());
		linkObject.setOperateTime(StaticMethod.getLocalTime());

		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);
		// add by leo
		// 取mainId
		String mainId = RequestUtils.getStringParameter(request, "mainId");
		linkObject.setMainId(mainId);
		request.setAttribute("task", taskModel);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);
		request.setAttribute("taskStatus", taskStatus);

		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}

		ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
				.getInstance().getBean("ItawSheetAccessManager");
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName,
				taskName);

		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		request.setAttribute("tawSheetAccess", access);
		request.setAttribute("module", mapping.getPath().substring(1));
		request.setAttribute("teamFlag", teamFlag);
		//批量回复或批量归档
		String taskIds = StaticMethod.nullObject2String(request.getParameter("taskIds"));
		if (!taskIds.equals("")) {
			request.setAttribute("taskIds", taskIds);
			Map tempId = new HashMap();
			StringBuffer mainIds = new StringBuffer();
			String taskCondition = " id in  ('" + taskIds.replaceAll(",", "','") + "')";
			//查出所有的任务列表
			List tasks = this.getTaskService().getTasksByCondition(taskCondition);
			for (Iterator it = tasks.iterator(); it.hasNext(); ) {
				ITask task = (ITask)it.next();
				String thismainId = task.getSheetKey();
				if (tempId.get(thismainId) == null) {
					tempId.put(thismainId, thismainId);
					if (!mainIds.toString().equals("")) {
						mainIds.append(",");
					}
					mainIds.append("'").append(thismainId).append("'");
				}
			}
			
			//查出所有的main表
			String condition = " id in (" + mainIds.toString() + ")";
			List mains = this.getMainService().getMainsByCondition(condition);
			request.setAttribute("mains", mains);
		}
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		Object tempMain = request.getAttribute("sheetMain");
		if(tempMain!=null){
			linkObject = this.setStepLimitToLink((BaseMain)tempMain,linkObject, taskName, operateType);
			request.setAttribute("sheetLink", linkObject);
		}
		
	}
	/**
	 * 为环节设置默认的受理时限和处理时限
	 * @param main
	 * @param link
	 * @param taskName
	 * @param operateType
	 * @return
	 * @throws Exception
	 */
	public BaseLink setStepLimitToLink(BaseMain main,BaseLink link,String taskName,String operateType) throws Exception{
		//查询时限配置得到受理时限和处理时限的默认值
		//查询下一步的phaseid
		FlowDefineExplain explain = new FlowDefineExplain();
		if (taskName.equals("")) {
			explain.setFlowId(this.getMainService().getFlowTemplateName());
			explain.setCurrentPhaseId("receive");
		} else {
			explain.setFlowId(this.getMainService().getFlowTemplateName());
			explain.setCurrentPhaseId(taskName);
		}
		String toPhaseIdStr = "";
		if (this.getRoleConfigPath() != null
				&& !this.getRoleConfigPath().equals("")) {
			List list = explain.explain(this.getRoleConfigPath());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					ToPhaseId toPhaseId = (ToPhaseId)list.get(i);
					String condition = toPhaseId.getCondition();
					if (!condition.equals("")) {
						Hashtable conhash = EomsInterpreter
								.getParamNameFromCondition(condition);
						Enumeration eunm = conhash.keys();

						while (eunm.hasMoreElements()) {
							String key = (String) eunm.nextElement();
							String value = operateType;
							if (value.equals("")) {
								value = "null";
							}
							condition = condition.replaceAll(
									"\\$\\{" + key + "\\}", value);
						}
						boolean isPass = EomsInterpreter
								.getbooleanFromExpression(condition);
						System.out.println("condition===" + condition
								+ "isPass====" + isPass);
						if (isPass == false) {
							continue;
						}else{
							toPhaseIdStr = toPhaseId.getId();
							break;
						}
					}
				}
			}
		}
		//查询下一步的处理时限和处理时限
		int acceptLimit = 0;
		int completeLimit = 0;
		int stepId = 0;
			String flowTemplateName = this.getMainService().getFlowTemplateName();
			HashMap conditionMap = SheetLimitUtil.getConditionByMapping(main,flowTemplateName);
		
			ISheetDealLimitManager sheetlimitmgr = (ISheetDealLimitManager) ApplicationContextHolder.getInstance().getBean("iSheetDealLimitManager");
			conditionMap.put("flowName", flowTemplateName);
			List sheetLimitList = sheetlimitmgr.getlevelLimitBySpecials(conditionMap);
			LevelLimit limit = null;
			String levelId = "";
			if (sheetLimitList != null && sheetLimitList.size() > 0) {
				limit = (LevelLimit) sheetLimitList.get(0);
				levelId = limit.getId();
				List stepList = sheetlimitmgr.getStepLimitByLevel(levelId);
				if(stepList!=null&&stepList.size()>0){
					for(int i=0;i<stepList.size();i++){
						StepLimit tmpStepLimit = (StepLimit)stepList.get(i);
						String tmptaskName = tmpStepLimit.getTaskName();
						String tmpAcceptLimit = tmpStepLimit.getAcceptLimit();
						String tmpCompleteLimit = tmpStepLimit.getCompleteLimit();
						if(tmpAcceptLimit==null) tmpAcceptLimit="0";
						if(tmpCompleteLimit==null) tmpCompleteLimit="0";
						//如果按照operateType没有查到下一步的name，则通过stepid得到下一步
						if(toPhaseIdStr==null||toPhaseIdStr.equals("")){
							if(stepId==0){
								if(tmptaskName.equals(taskName)){
									stepId = Integer.parseInt(tmpStepLimit.getStepId());
									stepId++;
								}
							}else{
								if(stepId==Integer.parseInt(tmpStepLimit.getStepId())){
									acceptLimit = Integer.parseInt(tmpAcceptLimit);
									completeLimit = Integer.parseInt(tmpCompleteLimit);
								}
							}
						}else{
							if(tmptaskName.equals(toPhaseIdStr)){
								acceptLimit = Integer.parseInt(tmpAcceptLimit);
								completeLimit = Integer.parseInt(tmpCompleteLimit);
							}
						}
					}
				}
			}
		if(acceptLimit!=0||completeLimit!=0){
			Date currentDate = new Date();
			//将工作时间和休息时间计算进来
			acceptLimit = SheetLimitUtil.getTimeLimitByConfig(currentDate,0,acceptLimit,flowTemplateName);
			completeLimit = SheetLimitUtil.getTimeLimitByConfig(currentDate,acceptLimit,completeLimit,flowTemplateName);
			
			java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = dateFormat.format(currentDate);
			
			Date tempAcceptLimit = SheetUtils.stringToDate(StaticMethod.getDateForMinute(date,acceptLimit));
			Date tempCompleteLimit = SheetUtils.stringToDate(StaticMethod.getDateForMinute(date,completeLimit));
			link.setNodeAcceptLimit(tempAcceptLimit);
			link.setNodeCompleteLimit(tempCompleteLimit);
		}
		return link;
	}
	/**
	 * LINK的提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/**
		 * 由于改为调用wps提供的humantaskManagerAPI，故taskid的取值由原来的aiid改为tkid，modify by
		 * qinmin*
		 */
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
    	System.out.println("basesheet operateType is -----------------------"+operateType);
		// 获取页面输入信息
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = request.getParameterMap();
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

		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		/** add by qinmin* */

		// 是否调用外部流程，若调用的话，将调用外部流程的个数放置到operate中
		ITawSheetRelationManager mgr = (ITawSheetRelationManager) ApplicationContextHolder
				.getInstance().getBean("ITawSheetRelationManager");
		String mainId = StaticMethod.nullObject2String(request
				.getParameter("mainId"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		//List list = mgr.getRunningSheetByParentId(mainId);
		String tempUserId="";
		if (sessionform != null) {
			tempUserId = sessionform.getUserid();
		}
	    //查询工单互调表
		
		List relationAllList = mgr.getRunningSheetByPidAndPhaseIdAndUserId(mainId, taskName, tempUserId);
		HashMap operate = (HashMap) this.getFlowEngineMap().get("operate");

		if(relationAllList != null && relationAllList.size()>0){
			operate.put("reInvokeCount", new Integer(relationAllList.size()));
			System.out.println("=======hasNextTaskFlag=======is==invokeProcess");
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

				BaseMain parentMain = parentSheet.getMainService().getSingleMainPO(parentSheetKey);
				BaseLink parentLink = (BaseLink) parentSheet.getLinkService()
						.getLinkObject().getClass().newInstance();
				mainMap = SheetBeanUtils.bean2MapWithNull(parentMain);
				linkMap = SheetBeanUtils.bean2MapWithNull(parentLink);
				operateMap.put("phaseId", toPhaseId);
				operateMap.put("hasNextTaskFlag", "invokeProcess");

				sheetMap.put("main", mainMap);
				sheetMap.put("link", linkMap);
				sheetMap.put("operate", operateMap);

				parentSheet.getBusinessFlowService().reInvokeProcess(invokePiid,
						invokeOperateName, sheetMap, sessionMap);
			}
		}
		 /**判断是否是同组处理模式，若是的话，则先取消之前的claim操作。add by 秦敏**/
		String teamFlag=StaticMethod.nullObject2String(request.getParameter("teamFlag"));
		if(teamFlag.equals("true")){
			businessFlowService.cancelClaimTask(taskId, getFlowEngineMap(),
					sessionMap);	
		}
		// finish task
		businessFlowService.completeHumanTask(taskId, getFlowEngineMap(),
				sessionMap);
	}

	/**
	 * 显示未处理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
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
		Integer overTimeTaskCount = this.getTaskService().getOverTimeTaskCount(condition, userId, deptId);
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
		request.setAttribute("overTimeTaskCount", overTimeTaskCount);
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
	 * 显示已处理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showListsenddone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap filterMap = new HashMap();
		filterMap.put("PROCESS_INSTANCE.TEMPLATE_NAME", getMainService()
				.getFlowTemplateName());
		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		// modified by Leo
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
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
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject()
				.getClass().newInstance();
		String beanName = mapping.getAttribute();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		condition.put("beanName", beanName);
		HashMap taskListMap = this.getTaskService().getDoneTask(condition,
				userId, deptId, this.getMainService().getFlowTemplateName(),
				pageIndex, pageSize);
		int total = ((Integer) taskListMap.get("taskTotal")).intValue();
		List taskList = (List) taskListMap.get("taskList");
		StringBuffer taskCondition = new StringBuffer();
		taskCondition.append(" taskOwner = '" + userId+ "' and ifWaitForSubTask='true' and taskStatus<>'"+ Constants.TASK_STATUS_FINISHED + "'");
		List tasks = this.getTaskService().getTasksByCondition(
				taskCondition.toString());
		Map tmpTaskMap = new HashMap();
		if (tasks != null && !tasks.isEmpty()) {
			for (int i = 0; i < tasks.size(); i++) {
				ITask task = (ITask) tasks.get(i);
				tmpTaskMap.put(task.getSheetKey(), task);
			}
		}
		List tmpTaskList = new  ArrayList();
        String[] variables= QuerySqlInit.getAllDictItemsName(beanName).split(",");		
		for (int i = 0; taskList != null && i < taskList.size(); i++) {
			Object[] objectList  = (Object[]) taskList.get(i);
			Map ListMap = new HashMap();
			for(int j= 0;j < objectList.length; j++){
				String variablesKey = variables[j];
				if(variablesKey.indexOf("main.")>=0 || variablesKey.indexOf("task.")>=0){
					variablesKey = variablesKey.substring(5);
				}
				ListMap.put(variablesKey, objectList[j]);			
			}			
			if (tmpTaskMap.containsKey(ListMap.get("id"))) {
				ListMap.put("status", new Integer(-11));
			}	
			tmpTaskList.add(ListMap);
		}
		request.setAttribute("taskList", tmpTaskList);
		request.setAttribute("total", new Integer(total));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "mainlist");
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
	}

	/**
	 * 显示草稿列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showDraftList(ActionMapping mapping, ActionForm form,
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
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject()
				.getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("userId", userId);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		String exportType = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		HashMap taskListMap = this.getTaskService().getDraftList(condition,
				this.getMainService().getFlowTemplateName(), pageIndex,
				pageSize);
		int total = ((Integer) taskListMap.get("taskTotal")).intValue();
		List taskOvertimeList = (List) taskListMap.get("taskList");
		List taskMapList = new ArrayList();
		HashMap columnMap = OvertimeTipUtil.getAllMainColumnByMapping(this
				.getMainService().getFlowTemplateName());
		for (int i = 0; i < taskOvertimeList.size(); i++) {
			ITask tmptask = null;
			Map taskMap = new HashMap();
			Map tmptaskMap = new HashMap();
			HashMap conditionMap = new HashMap();
			if (columnMap.size() > 0) {
				Object[] tmpObjArr = (Object[]) taskOvertimeList.get(i);
				tmptask = (ITask) tmpObjArr[0];
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
			taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
			taskMap.putAll(tmptaskMap);
			taskMapList.add(taskMap);
		}
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
		request.setAttribute("pageSize", pageSize);

	}

	/**
	 * 显示归档工单列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showHoldedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// modified by Leo
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
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
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		String beanName = mapping.getAttribute();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("orderCondition", orderCondition);
		condition.put("beanName", beanName);		
		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		// 分页取得列表
		Integer total = this.getMainService().getHoldsCount();
		// wps端分页取得列表

		List result = this.getMainService().getHolds(condition, pageIndex,
				new Integer(pageSize.intValue()));
		List holdedList = new ArrayList();
        String[] variables= QuerySqlInit.getAllDictItemsName(beanName).split(",");		
		for (int i = 0; result != null && i < result.size(); i++) {
			Object[] objectList  = (Object[]) result.get(i);
			Map ListMap = new HashMap();
			for(int j= 0;j < objectList.length; j++){
				String variablesKey = variables[j];
				if(variablesKey.indexOf("main.")>=0 || variablesKey.indexOf("task.")>=0){
					variablesKey = variablesKey.substring(5);
				}
				ListMap.put(variablesKey, objectList[j]);			
			}			
			holdedList.add(ListMap);
		}
		request.setAttribute("taskList", holdedList);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "holdlist");
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
	}

	/**
	 * 显示撤销工单列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showCancelList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// modified by Leo
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
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
		String beanName = mapping.getAttribute();
		HashMap condition = new HashMap();
		condition.put("orderCondition", orderCondition);
		condition.put("beanName", beanName);
		// 分页取得列表
		Integer total = this.getMainService().getCancelCount();
		// wps端分页取得列表
		String exportType = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		List result = this.getMainService().getCancelList(pageIndex,
				new Integer(pageSize.intValue()), condition);
		List cancelList = new ArrayList();	
        String[] variables= QuerySqlInit.getAllDictItemsName(beanName).split(",");		
		for (int i = 0; result != null && i < result.size(); i++) {
			Object[] objectList  = (Object[]) result.get(i);
			Map ListMap = new HashMap();
			for(int j= 0;j < objectList.length; j++){
				String variablesKey = variables[j];
				if(variablesKey.indexOf("main.")>=0 || variablesKey.indexOf("task.")>=0){
					variablesKey = variablesKey.substring(5);
				}
				ListMap.put(variablesKey, objectList[j]);			
			}			
			cancelList.add(ListMap);
		}		
		request.setAttribute("taskList", cancelList);
		request.setAttribute("total", total);
		// request.setAttribute("method", "showHoldedList");
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "cancellist");
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
	}

	/**
	 * 由我启动
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void showOwnStarterList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		// modified by Leo
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
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
		HashMap condition = new HashMap();
		String beanName = mapping.getAttribute();
		condition.put("orderCondition", orderCondition);
		condition.put("beanName", beanName);
		String exportType = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		// 分页取得列表

		// wps端分页取得列表
		HashMap taskListMap = this.getMainService().getStarterList(
				sessionform.getUserid(), pageIndex,
				new Integer(pageSize.intValue()), condition);
		List result = (List) taskListMap.get("sheetList");
		Integer total = (Integer) taskListMap.get("sheetTotal");
		List startList = new ArrayList();
        String[] variables= QuerySqlInit.getAllDictItemsName(beanName).split(",");		
		for (int i = 0; result != null && i < result.size(); i++) {
			Object[] objectList  = (Object[]) result.get(i);
			Map ListMap = new HashMap();
			for(int j= 0;j < objectList.length; j++){
				String variablesKey = variables[j];
				if(variablesKey.indexOf("main.")>=0 || variablesKey.indexOf("task.")>=0){
					variablesKey = variablesKey.substring(5);
				}
				ListMap.put(variablesKey, objectList[j]);			
			}			
			startList.add(ListMap);
		}	
		request.setAttribute("taskList", startList);
		request.setAttribute("total", total);
		// request.setAttribute("method", "showOwnStarterList");
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("findForward", "startlist");
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
	}

	public void showSheetDealList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");	
		String ifWaitForSubTask = StaticMethod.nullObject2String(request
				.getParameter("ifWaitForSubTask"), "");			
		if (!sheetKey.equals("")) {
			BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
			List list = getLinkService().getLinksByMainId(sheetKey);
			request.setAttribute("HISTORY", list);
			request.setAttribute("taskName", taskName);
			request.setAttribute("ifWaitForSubTask", ifWaitForSubTask);
			request.setAttribute("sheetMain", main);
		}
		BaseLink link = this.getLinkService().getLinkObject();
		request.setAttribute("linkClassName", link);*/
		
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");	
		String ifWaitForSubTask = StaticMethod.nullObject2String(request
				.getParameter("ifWaitForSubTask"), "");			
		if (!sheetKey.equals("")) {
			BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
			List list = getLinkService().getLinksByMainId(sheetKey);
			
			String orderByDetp = StaticMethod.nullObject2String(request
					.getParameter("orderByDetp"), "");
			if (orderByDetp.equals("true")) {
				//历史列表按部门排列
				Map deptIdMap = new HashMap();
				List deptIdarray = new ArrayList();
				Map numberMap = new HashMap();
				int i = 0;
				//循环把部门的ID归类
				for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
					BaseLink baseLink = (BaseLink)iterator.next();
					i ++;
					numberMap.put(baseLink.getId(), new Integer(i));
					
					if (deptIdMap.get(baseLink.getOperateDeptId()) == null) {
						List links = new ArrayList();
						links.add(baseLink);
						deptIdarray.add(baseLink.getOperateDeptId());
						deptIdMap.put(baseLink.getOperateDeptId(), links);
					} else {
						List links = (List)deptIdMap.get(baseLink.getOperateDeptId());
						links.add(baseLink);
					}
				}
				
				request.setAttribute("numberMap", numberMap);
				request.setAttribute("deptIdtable", deptIdarray);
				request.setAttribute("deptIdMap", deptIdMap);
			}
			
			request.setAttribute("HISTORY", list);
			request.setAttribute("taskName", taskName);
			request.setAttribute("ifWaitForSubTask", ifWaitForSubTask);
			request.setAttribute("sheetMain", main);
		}
		BaseLink link = this.getLinkService().getLinkObject();
		request.setAttribute("linkClassName", link);
	}

	public HashMap getTaskBOMap() {
		HashMap returnMap = new HashMap();
		HashMap map = new HashMap();
		map.put("id", "id");
		map.put("taskName", "taskName");
		map.put("taskDisplayName", "taskDisplayName");
		map.put("createTime", "createTime");
		map.put("taskStatus", "taskStatus");
		map.put("processId", "processId");
		map.put("sheetKey", "sheetKey");
		map.put("sheetId", "sheetId");
		map.put("title", "title");
		map.put("acceptTimeLimit", "acceptTimeLimit");
		map.put("completeTimeLimit", "completeTimeLimit");
		map.put("operateRoleId", "operateRoleId");
		map.put("taskId", "taskId");
		map.put("preLinkId", "preLinkId");
		map.put("taskOwner", "taskOwner");
		String taskBoName = "task";
		returnMap.put(taskBoName, map);
		return returnMap;
	}

	/**
	 * main工单的详细信息页面，如归档，由我启动
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showMainDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String isAdmin = StaticMethod.nullObject2String(request
				.getParameter("isAdmin"));
		String sheetKey = RequestUtils.getStringParameter(request, "sheetKey");
		BaseMain mainObject = this.getMainService().getSingleMainPO(sheetKey);
		String parentSheetKey = StaticMethod.nullObject2String(mainObject
				.getParentSheetId());
		String parentBeanId = StaticMethod.nullObject2String(mainObject
				.getParentSheetName());

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
			request.setAttribute("parentProcessCnName",
							parentProcessCnName);
		}
		request.setAttribute("sheetMain", mainObject);
		if (!isAdmin.equals("")) {
			request.setAttribute("isAdmin", isAdmin);  
		}
		
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		
		List roleList = sessionform.getRolelist();
		int listSize = roleList.size();
		BocoLog.debug(this, "==listSize==>>" + listSize);
		System.out.println("==listSize==>>" + listSize);
		String roleId = "";
		for(int i=0;i<listSize;i++){
			TawSystemSubRole subRole = (TawSystemSubRole)roleList.get(i);
			roleId = roleId + "," + subRole.getRoleId();
		}
		System.out.println("==roleId==>>" + roleId);
		request.setAttribute("roleId", roleId);
		// 分派的处理
		if (mainObject.getStatus().intValue() == 0) {
			String userId = sessionform.getUserid();
			String deptId = sessionform.getDeptid();

			String condition = "sheetKey='"
					+ sheetKey
					+ "' and taskStatus<>'5' and ifWaitForSubTask='true' "
					+ " and ((taskOwner='"
					+ userId
					+ "'"
					+ "or taskOwner='"
					+ deptId
					+ "')"
					+ " or taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='"
					+ userId + "')) ";
			List undoTaskList = this.getTaskService().getTasksByCondition(
					condition);
			ITask task = null;
			if (undoTaskList != null && undoTaskList.size() > 0) {
				if (undoTaskList.size() == 1) {
					task = (ITask) undoTaskList.get(0);
				} else {
					task = (ITask) undoTaskList.get(0);
					String taskId = task.getId();
					for (int i = 0; i < undoTaskList.size(); i++) {
						ITask tmpTask = (ITask) undoTaskList.get(i);
						if (tmpTask.getSubTaskFlag() != null
								&& tmpTask.getSubTaskFlag().equals("true")
								&& !taskId.equals(tmpTask.getId())) {
							taskId = tmpTask.getId();
							task = tmpTask;
						}
						for (int j = i + 1; j < undoTaskList.size(); j++) {
							ITask tmptmpTask = (ITask) undoTaskList.get(j);
							if (taskId.equals(tmptmpTask.getParentTaskId())) {
								taskId = tmptmpTask.getId();
								task = tmptmpTask;
								i = j;
								break;
							}
						}
					}
				}
				request.setAttribute("sheetKey", task.getSheetKey());
				request.setAttribute("taskId", task.getId());
				request.setAttribute("taskName", task.getTaskName());
				request.setAttribute("piid", task.getProcessId());
				request.setAttribute("operateRoleId", task.getOperateRoleId());
				request.setAttribute("taskStatus", task.getTaskStatus());
				request.setAttribute("preLinkId", task.getPreLinkId());
				request.setAttribute("TKID", task.getId());
				this.showDetailPage(mapping, form, request, response);
				request.setAttribute("distributeForward", "detail");
			}
		}
	}

	/**
	 * main工单的撤销
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		Map parameterMap = request.getParameterMap();

		BaseLink linkObject = (BaseLink) (getLinkService().getLinkObject()
				.getClass()).newInstance();
		BaseMain mainObject = (BaseMain) getMainService().getMainObject()
				.getClass().newInstance();
		if (!sheetKey.equals("")) {
			mainObject = getMainService().getSingleMainPO(sheetKey);
		}
		SheetBeanUtils.populateMap2Bean(linkObject, parameterMap);
		SheetBeanUtils.populateMap2Bean(mainObject, parameterMap);

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		linkObject.setOperaterContact(sessionform.getContactMobile());
		linkObject.setId(UUIDHexGenerator.getInstance().getID());
		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
		}

		getLinkService().addLink(linkObject);
		getMainService().addMain(mainObject);

		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		String processId = RequestUtils.getStringParameter(request, "piid");
		businessFlowService.cancel(processId, sessionMap);

	}

	/**
	 * 个性工单main service的spring bean id
	 */
	private String mainServiceBeanId;

	/**
	 * @return the mainServiceBeanId
	 */
	public String getMainServiceBeanId() {
		return mainServiceBeanId;
	}

	/**
	 * @param mainServiceBeanId
	 *            the mainServiceBeanId to set
	 */
	public void setMainServiceBeanId(String mainServiceBeanId) {
		this.mainServiceBeanId = mainServiceBeanId;
	}
	
	/**
	 * 对处理人、抄送人以及审核人的解析，modify by 秦敏
	 */
	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 设置后续处理对象：派给角色 或 派给人员， add by jialei 2009-04-14
		String dealPerformerType = DealTypeConfigUtil.getDealTypeByHold(this
				.getMainService().getFlowTemplateName());
		HashMap wpsMap = this.getFlowEngineMap();
		HashMap operateMap = (HashMap) wpsMap.get("operate");
		String phaseId = (String) operateMap.get("phaseId");
		String tmpstr = phaseId.toLowerCase();
		String dealPerformer = StaticMethod.nullObject2String(operateMap.get("dealPerformer"));
		if (tmpstr.indexOf("hold") != -1 && dealPerformer.equals("")) {
			HashMap mainMap = (HashMap) wpsMap.get("main");
			ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
			String name = service.id2Name(StaticMethod.nullObject2String(operateMap.get("dealPerformerLeader")),"tawSystemUserDao");
			if (dealPerformerType.equals("0")) {
				// 派给人员
				if (name.equals("")) {
					operateMap.put("dealPerformer", StaticMethod.nullObject2String(mainMap.get("sendRoleId")));
					operateMap.put("dealPerformerLeader", StaticMethod.nullObject2String(mainMap.get("sendUserId")));
					operateMap.put("dealPerformerType", StaticMethod.nullObject2String(mainMap.get("sendOrgType")));
				}
			} else if (dealPerformerType.equals("1")) {
				// 派给角色
				if (!name.equals("")
						|| StaticMethod.nullObject2String(
								operateMap.get("dealPerformerLeader")).equals(
								"")) {
					operateMap.put("dealPerformer", StaticMethod.nullObject2String(mainMap.get("sendRoleId")));
					operateMap.put("dealPerformerLeader", StaticMethod.nullObject2String(mainMap.get("sendRoleId")));
					operateMap.put("dealPerformerType", StaticMethod.nullObject2String(mainMap.get("sendOrgType")));
				}
			}
		}
		wpsMap.put("operate", operateMap);
		//驳回时设置环节时限
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		HashMap linkMap = (HashMap) wpsMap.get("link");
		HashMap mainMap = (HashMap) wpsMap.get("main");
		if(operateType.equals("4")){
			Date nodeAcceptLimit = (Date)linkMap.get("nodeAcceptLimit");
			Date nodeCompleteLimit = (Date)linkMap.get("nodeCompleteLimit");
			if(nodeAcceptLimit==null&&nodeCompleteLimit==null){
				String flowTemplateName = this.getMainService().getFlowTemplateName();
				String mainid = (String)mainMap.get("id");
				BaseMain main = this.getMainService().getSingleMainPO(mainid);
				HashMap conditionMap = SheetLimitUtil.getConditionByMapping(main,flowTemplateName);
			
				ISheetDealLimitManager sheetlimitmgr = (ISheetDealLimitManager) ApplicationContextHolder.getInstance().getBean("iSheetDealLimitManager");
				conditionMap.put("flowName", flowTemplateName);
				List sheetLimitList = sheetlimitmgr.getlevelLimitBySpecials(conditionMap);
				LevelLimit limit = null;
				String levelId = "";
				if (sheetLimitList != null && sheetLimitList.size() > 0) {
					limit = (LevelLimit) sheetLimitList.get(0);
					levelId = limit.getId();
					List stepList = sheetlimitmgr.getStepLimitByLevel(levelId);
					if(stepList!=null&&stepList.size()>0){
						for(int i=0;i<stepList.size();i++){
							StepLimit tmpStepLimit = (StepLimit)stepList.get(i);
							String tmptaskName = tmpStepLimit.getTaskName();
							String tmpAcceptLimit = tmpStepLimit.getAcceptLimit();
							String tmpCompleteLimit = tmpStepLimit.getCompleteLimit();
							if(tmpAcceptLimit==null) tmpAcceptLimit="0";
							if(tmpCompleteLimit==null) tmpCompleteLimit="0";
							if(tmptaskName.equals(phaseId)){
								Date currentDate = new Date();
								//将工作时间和休息时间计算进来
								tmpAcceptLimit = ""+SheetLimitUtil.getTimeLimitByConfig(currentDate,0,Integer.parseInt(tmpAcceptLimit),flowTemplateName);
								tmpCompleteLimit = ""+SheetLimitUtil.getTimeLimitByConfig(currentDate,Integer.parseInt(tmpAcceptLimit),Integer.parseInt(tmpCompleteLimit),flowTemplateName);
								java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = dateFormat.format(currentDate);
								Date tempAcceptLimit = SheetUtils.stringToDate(StaticMethod.getDateForMinute(date,Integer.parseInt(tmpAcceptLimit)));
								Date tempCompleteLimit = SheetUtils.stringToDate(StaticMethod.getDateForMinute(date,Integer.parseInt(tmpCompleteLimit)));
								linkMap.put("nodeAcceptLimit", tempAcceptLimit);
								linkMap.put("nodeCompleteLimit", tempCompleteLimit);
								break;
							}
						}
					}
				}
			}
		//新增时赋第一个环节的时限
		}else if(operateType.equals("0")||operateType.equals("3")||operateType.equals("54")){
			Date nodeAcceptLimit = (Date)linkMap.get("nodeAcceptLimit");
			Date nodeCompleteLimit = (Date)linkMap.get("nodeCompleteLimit");
			Date sheetAcceptLimit = (Date)mainMap.get("sheetAcceptLimit");
			Date sheetCompleteLimit = (Date)mainMap.get("sheetCompleteLimit");
			if(nodeAcceptLimit!=null&&nodeCompleteLimit!=null&&sheetAcceptLimit!=null&&sheetCompleteLimit!=null
					&&nodeAcceptLimit.getTime()==sheetAcceptLimit.getTime()
					&&nodeCompleteLimit.getTime()==sheetCompleteLimit.getTime()){
				String flowTemplateName = this.getMainService().getFlowTemplateName();
				BaseMain main = (BaseMain)this.getMainService().getMainObject().getClass().newInstance();
				SheetBeanUtils.populate(main,mainMap);
				HashMap conditionMap = SheetLimitUtil.getConditionByMapping(main,flowTemplateName);
				ISheetDealLimitManager sheetlimitmgr = (ISheetDealLimitManager) ApplicationContextHolder.getInstance().getBean("iSheetDealLimitManager");
				conditionMap.put("flowName", flowTemplateName);
				List sheetLimitList = sheetlimitmgr.getlevelLimitBySpecials(conditionMap);
				LevelLimit limit = null;
				String levelId = "";
				if (sheetLimitList != null && sheetLimitList.size() > 0) {
					limit = (LevelLimit) sheetLimitList.get(0);
					levelId = limit.getId();
					List stepList = sheetlimitmgr.getStepLimitByLevel(levelId);
					if(stepList!=null&&stepList.size()>0){
						for(int i=0;i<stepList.size();i++){
							StepLimit tmpStepLimit = (StepLimit)stepList.get(i);
							String tmptaskName = tmpStepLimit.getTaskName();
							String tmpAcceptLimit = tmpStepLimit.getAcceptLimit();
							String tmpCompleteLimit = tmpStepLimit.getCompleteLimit();
							if(tmpAcceptLimit==null) tmpAcceptLimit="0";
							if(tmpCompleteLimit==null) tmpCompleteLimit="0";
							if(tmptaskName.equals(phaseId)){
								String localTime = StaticMethod.date2String((Date)mainMap.get("sendTime"));
								Date currentDate = (Date)mainMap.get("sendTime");
								//将工作时间和休息时间计算进来
								tmpAcceptLimit = ""+SheetLimitUtil.getTimeLimitByConfig(currentDate,0,Integer.parseInt(tmpAcceptLimit),flowTemplateName);
								tmpCompleteLimit = ""+SheetLimitUtil.getTimeLimitByConfig(currentDate,Integer.parseInt(tmpAcceptLimit),Integer.parseInt(tmpCompleteLimit),flowTemplateName);
								Date tempAcceptLimit = SheetUtils.stringToDate(StaticMethod.getDateForMinute(localTime,Integer.parseInt(tmpAcceptLimit)));
								Date tempCompleteLimit = SheetUtils.stringToDate(StaticMethod.getDateForMinute(localTime,Integer.parseInt(tmpCompleteLimit)));
								linkMap.put("nodeAcceptLimit", tempAcceptLimit);
								linkMap.put("nodeCompleteLimit", tempCompleteLimit);
								break;
							}
						}
					}
				}
			}
		}
		wpsMap.put("link", linkMap);
		this.setFlowEngineMap(wpsMap);
	}

	public Map getUndoList(HttpServletRequest request) throws Exception {
		HashMap filterMap = new HashMap();

		filterMap.put("PROCESS_INSTANCE.TEMPLATE_NAME", getMainService()
				.getFlowTemplateName());
		HashMap taskMap = getTaskBOMap();
		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());

		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		System.out.println("begin select wps list:"
				+ StaticMethod.getCurrentDateTime());
		// wps端分页取得列表
		List list = businessFlowService.getUndoTasksList(pageIndex.intValue()
				* pageSize.intValue(), pageSize.intValue(), taskMap, filterMap,
				sessionMap);
		System.out.println("end select wps list:"
				+ StaticMethod.getCurrentDateTime());
		List taskList = new ArrayList();
		for (int i = 0; list.size() > 0 && i < list.size(); i++) {
			ITask task = (ITask) Class.forName(
					getSheetTask().getClass().getName()).newInstance();
			Map map = (Map) list.get(i);
			SheetBeanUtils.populate(task, map);

			taskList.add(task);
		}

		int total = businessFlowService
				.getUndoTasksCount(filterMap, sessionMap);
		Map map = new HashMap();
		map.put("taskList", taskList);
		map.put("total", new Integer(total));
		return map;
	}

	/**
	 * 工单提交预处理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public void performPreCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] sheetPageName = request.getParameterValues("sheetPageName");
		String[] methodBeanId = request.getParameterValues("methodBeanId");
		String operateType = StaticMethod.nullObject2String(request
				.getParameterValues("operateType"));
		JSONObject jsonRoot = new JSONObject();
		JSONArray data = new JSONArray();
		String status = "";
		if (sheetPageName != null
				&& (!operateType.equals("4") || !operateType.equals("61"))) {
			for (int i = 0; i < sheetPageName.length; i++) {
				if (sheetPageName[i].equals("")) {
					HashMap map = analyseFlowDefine(request, sheetPageName[i]);
					status = StaticMethod.nullObject2String(map.get("status"));
					data.put(map.get("jsonObject"));
				} else {
					IBaseSheet basesheet = (IBaseSheet) ApplicationContextHolder
							.getInstance().getBean(methodBeanId[i]);
					HashMap map = basesheet.analyseFlowDefine(request,
							sheetPageName[i]);
					status = StaticMethod.nullObject2String(map.get("status"));
					data.put(map.get("jsonObject"));
				}
			}

			jsonRoot.put("data", data);
			jsonRoot.put("status", String.valueOf(status));
			JSONUtil.print(response, jsonRoot.toString());
		}
	}

	/**
	 * 解析流程配置文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public HashMap analyseFlowDefine(HttpServletRequest request,
			String sheetPageName) throws Exception {
		String status = ""; // 匹配结果返回值
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		// if (tkid.trim().equals("")) {
		// documentation = getBusinessFlowService()
		// .getDocumentationFromReceive(flowName, operateName,
		// sessionMap);
		// } else {
		// documentation = getBusinessFlowService()
		// .getDocumentationFromHumanTask(tkid, sessionMap);
		// }
		// FlowDefine define = new FlowDefine(documentation);
		// List list = define.getToPhaseIdList();
		String taskName = StaticMethod.nullObject2String(request
				.getParameter(sheetPageName + "activeTemplateId"));

		String toDeptId = StaticMethod.nullObject2String(request
				.getParameter(sheetPageName + "toDeptId"));

		FlowDefineExplain explain = new FlowDefineExplain();

		if (taskName.equals("")) {
			explain.setFlowId(this.getMainService().getFlowTemplateName());
			explain.setCurrentPhaseId("receive");
		} else {
			explain.setFlowId(this.getMainService().getFlowTemplateName());
			explain.setCurrentPhaseId(taskName);
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer sbFn = new StringBuffer();
		if (this.getRoleConfigPath() != null
				&& !this.getRoleConfigPath().equals("")) {
			List list = explain.explain(this.getRoleConfigPath());

			HashMap columnMap = RoleMapSchema.getInstance().getStyleIDsBySheet(
					this.getMainService().getFlowTemplateName());

			Hashtable hash = new Hashtable();
			hash.put("deptId", toDeptId);
			Set filterSet = columnMap.keySet();
			Iterator filterIt = filterSet.iterator();
			while (filterIt.hasNext()) {
				String hashName = StaticMethod.nullObject2String(filterIt
						.next());
				String name = StaticMethod.nullObject2String(columnMap
						.get(hashName));
				if (!name.equals("")) {
					String value = StaticMethod.nullObject2String(request
							.getParameter(sheetPageName + name));
					hash.put(hashName, value);
				}
			}

			for (int phaseCount = 0; list != null && phaseCount < list.size(); phaseCount++) {
				String dealPerformer = "";
				String roleString = "";
				String roleId = "";

				ToPhaseId toPhaseId = (ToPhaseId) list.get(phaseCount);
				String role = toPhaseId.getRole();
				String bigRole = "";

				String condition = toPhaseId.getCondition();
				if (!condition.equals("")) {
					Hashtable conhash = EomsInterpreter
							.getParamNameFromCondition(condition);
					Enumeration eunm = conhash.keys();

					while (eunm.hasMoreElements()) {
						String key = (String) eunm.nextElement();
						String value = StaticMethod.nullObject2String(request
								.getParameter(sheetPageName + key));
						if (value.equals("")) {
							value = "null";
						}
						condition = condition.replaceAll(
								"\\$\\{" + key + "\\}", value);
					}
					boolean isPass = EomsInterpreter
							.getbooleanFromExpression(condition);
					System.out.println("condition===" + condition
							+ "isPass====" + isPass);
					if (isPass == false) {
						continue;
					}
				}
				System.out.println("aaaaaaaaaaaa====");
				// Hashtable hash = ei.getParamNameFromCondition(condition);
				sb.append(explain.getFlowDefine().getDescription()
						+ "工单即将流转到步骤:"
						+ explain.getFlowDefine().getPhasesByPhaseId(
								toPhaseId.getId()).getName() + "\n");
				// 流程config文件中配置了roleid
				if (!role.equals("")) {
					String temp[] = role.split(",");
					for (int ii = 0; temp != null && ii < temp.length; ii++) {
						String temp2[] = temp[ii].split("@");
						dealPerformer = StaticMethod.nullObject2String(request
								.getParameter(sheetPageName + temp2[0]));
						bigRole = temp2[1];
						try {
							String moduleId = RoleMapSchema.getInstance()
									.getModelIdBySheet(
											this.getMainService()
													.getFlowTemplateName());
							ITawSystemRoleManager mgr = (ITawSystemRoleManager) ApplicationContextHolder
									.getInstance().getBean(
											"ItawSystemRoleManager");
							TawSystemRole trole = mgr.getTawSystemRole(
									new Integer(moduleId), bigRole);
							bigRole = String.valueOf(trole.getRoleId());
						} catch (Exception e) {
					
						}
						/**
						 * 当界面上选择了派发角色时，查询所选择的派发角色下的人员
						 */
						if (!dealPerformer.equals("")) {
							Map userNameMap = SheetUtils
									.getUserNameForSubRole(dealPerformer);
							String user = StaticMethod
									.nullObject2String(userNameMap.get("name"));
							String subRoleName = StaticMethod
									.nullObject2String(userNameMap
											.get("subRoleName"));
							sb.append("根据您的选择，工单即将派发给角色:" + subRoleName + "\n");
							sb.append("该角色对应用户为:" + user + "\n");
						}
						/**
						 * 根据规则匹配
						 */
						String Leader = "";
						List perform = RoleManage.getInstance().getSubRoles(
								bigRole, hash);

						Iterator it = filterSet.iterator();
						TawSystemSubRole subRole = SheetUtils
								.getMaxFilterSubRole(perform, it);

						if (subRole != null && subRole.getId() != null
								&& !subRole.getId().equals("")) {
							roleId = subRole.getId();
							Map userNameMap = SheetUtils
									.getUserNameForSubRole(subRole.getId());
							String user = StaticMethod
									.nullObject2String(userNameMap.get("name"));
							String subRoleName = StaticMethod
									.nullObject2String(userNameMap
											.get("subRoleName"));
							Leader = StaticMethod.nullObject2String(userNameMap
									.get("leaderId"));
							if (Leader.equals("")) {
								Leader = roleId;
							}
							sb.append("根据规则匹配，该步骤的执行角色为:" + subRoleName + "\n");
							sb.append("该角色对应用户为:" + user + "\n");
							sbFn.append("function(){");
							sbFn
									.append("try{"
											+ "var "
											+ sheetPageName
											+ temp2[0]
											+ "s = document.getElementsByName('"


											+ sheetPageName
											+ temp2[0]
											+ "');"
											+ "var "
											+ sheetPageName
											+ temp2[0]
											+ "Types = document.getElementsByName('"
											+ sheetPageName
											+ temp2[0]
											+ "Type');"
											+ "var "
											+ sheetPageName
											+ temp2[0]
											+ "Leaders = document.getElementsByName('"
											+ sheetPageName
											+ temp2[0]
											+ "Leader');"
											+ "for(var i=0;i<dealPerformers.length;i++){"
											+ sheetPageName + temp2[0]
											+ "s[i].value='" + roleId + "';"
											+ sheetPageName + temp2[0]
											+ "Types[i].value='subrole';"
											+ sheetPageName + temp2[0]
											+ "Leaders[i].value='" + Leader
											+ "';" + "}"
											// +"document.forms[0]['"
											// + sheetPageName
											// + temp2[0]
											// + "'].value='"
											// + roleId
											// + "';document.forms[0]['"
											// + sheetPageName
											// + temp2[0]
											// +
											// "Type'].value='subrole';document.forms[0]['"
											// + sheetPageName + temp2[0]
											// + "Leader'].value='" + Leader
											+ "}catch(e){}");
							sbFn.append("}");
							status = "1";
						}

						if (!dealPerformer.equals("")) {
							sb.append("工单将流转到您所选择的角色\n");
							sbFn.append("null");
							status = "1";
						}
						if (roleId.equals("") && dealPerformer.equals("")) {
							sb
									.append("根据您的选择，该步骤没有匹配出具体的角色\n请更改您的选择或者联系管理员配置相关角色\n");
							status = "2";
						}
					}
				} else {
					// 流程config文件中没有配置roleid,就直接提交，不显示任何提示框
					status = "0";
				}
			}

			if (list == null || 0 == list.size()) {
				status = "0";
			}
		} else {
			status = "0";
		}

		HashMap map = new HashMap();

		JSONObject o = new JSONObject();
		o.put("text", sb.toString());
		o.put("fn", sbFn.toString());
		System.out.println("o.toString()=" + o.toString());

		map.put("jsonObject", o);
		map.put("status", status);

		return map;
	}

	/**
	 * 申明一个任务
	 */
	public void performClaim(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String taskId = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		// 获取页面输入信息
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = request.getParameterMap();
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

		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		businessFlowService.claimTask(taskId, getFlowEngineMap(), sessionMap);
		request.setAttribute("taskStatus", "8");
		// 返回详细信息页面
		this.showDetailPage(mapping, form, request, response);
	}

	/**
	 * 创建一个子任务
	 */
	public void performCreateSubTask(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String dealPerformer = StaticMethod.nullObject2String(request
				.getParameter("dealPerformer"));
		String dealPerformerLeader = StaticMethod.nullObject2String(request
				.getParameter("dealPerformerLeader"));
		String dealPerformerType = StaticMethod.nullObject2String(request
				.getParameter("dealPerformerType"));
		String subTtaskName = StaticMethod.nullObject2String(request
				.getParameter("subtaskName"));
		String parentTaskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		String taskNamespace = StaticMethod.nullObject2String(request
				.getParameter("taskNamespace"));
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
		Map requestMap = request.getParameterMap();
		BaseLink link = (BaseLink) this.getLinkService().getLinkObject()
				.getClass().newInstance();
		SheetBeanUtils.populate(link, requestMap);
		String operateTime = StaticMethod.nullObject2String(request
				.getParameter("operateTime"));
		if (operateTime.equals("")) {
			link.setOperateTime(new Date());
		}
		HashMap map = new HashMap();
		map = SheetBeanUtils.bean2Map(link);
		/**判断是否是同组处理模式，若是的话，则先取消之前的claim操作。add by 秦敏**/
		String teamFlag=StaticMethod.nullObject2String(request.getParameter("teamFlag"));
		if(teamFlag.equals("true")){
			businessFlowService.cancelClaimTask(TKID, getFlowEngineMap(),
					sessionMap);	
		}
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
	 * 执行流程的事件页面
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showEventPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		String aiid = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		System.out.println("taskName:" + taskName);

		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"));
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}

		request.setAttribute("sheetKey", sheetKey);
		request.setAttribute("taskId", aiid);
		request.setAttribute("piid", piid);
		request.setAttribute("taskName", taskName);
		request.setAttribute("operateRoleId", operateRoleId);
		request.setAttribute("TKID", TKID);
		request.setAttribute("preLinkId", preLinkId);
	}

	/**
	 * 执行流程的事件输入页面
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showInputEventPage(ActionMapping mapping, ActionForm form,
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
		System.out.println("taskName:" + taskName);
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"));
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");

		String dealPerformer = null;
		String dealPerformerLeader = null;
		String dealPerformerType = null;
		if (taskName.equals("reply")) {
			// 阶段回复
			BaseLink preLink = this.getLinkService().getSingleLinkPO(preLinkId);
			String parentTaskId = preLink.getAiid();
			ITask parentTask = this.getTaskService().getSinglePO(parentTaskId);

		}
		if (taskName.equals("advice")) {
			ITask task = (ITask) this.getTaskService().getOperateRoleId(sheetKey);
			dealPerformer = task.getOperateRoleId();
			dealPerformerLeader = StaticMethod.nullObject2String(task
					.getTaskOwner(), "");
			if (dealPerformerLeader.equals("")) {
				dealPerformerLeader = dealPerformer;
			}
			dealPerformerType = task.getOperateType();

		}
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		BaseLink linkObject = (BaseLink) getLinkService().getLinkObject()
				.getClass().newInstance();
		

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
			if (dealPerformer == null) {
				if (taskName.equals("reply")) {
					dealPerformer = mainObject.getSendRoleId();
				}
			}
		}
		if (taskName.equals("reply")) {
			Map LeaderMap = SheetUtils.getUserNameForSubRole(dealPerformer);
			String leaderId = StaticMethod.nullObject2String(LeaderMap
					.get("leaderId"), "");
			if (leaderId.equals("")) {
				dealPerformerLeader = dealPerformer;
			} else {
				Object obj = LeaderMap.get("leaderId");
				dealPerformerLeader = StaticMethod.nullObject2String(obj);
			}
		}

		request.setAttribute("dealPerformer", dealPerformer);
		request.setAttribute("dealPerformerLeader", dealPerformerLeader);
		request.setAttribute("dealPerformerType", dealPerformerType);
		if (!preLinkId.equals("") && !preLinkId.equals("null")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}
		linkObject.setOperateType(new Integer(1));// 转派标识
		linkObject.setOperateRoleId(operateRoleId);
		linkObject.setOperaterContact(sessionform.getContactMobile());
		String acceptLimit = StaticMethod.getLocalString(1);
		String completeLimit = StaticMethod.getLocalString(3);
		linkObject.setNodeAcceptLimit(SheetUtils.stringToDate(acceptLimit));
		linkObject.setNodeCompleteLimit(SheetUtils.stringToDate(completeLimit));

		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);
		String mainId = RequestUtils.getStringParameter(request, "mainId");
		linkObject.setMainId(mainId);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);

		String correlationKey = StaticMethod.nullObject2String(request
				.getParameter("correlationKey"), "");

		request.setAttribute("correlationKey", correlationKey);
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}

		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		request.setAttribute("sheetKey", sheetKey);
		request.setAttribute("preLinkId", preLinkId);
	}

	/**
	 * 执行阶段回复输入页面
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showPhaseBackToUpPage(ActionMapping mapping, ActionForm form,
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
		System.out.println("taskName:" + taskName);
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"));
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");

		List toList = new ArrayList();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
				.getInstance().getBean("ID2NameGetServiceCatch");
		// boolean ifAddReceive = true;
		if (!preLinkId.equals(""))// 当preLinkId不为空时，则表示需要从派发给我的link中取出operateRoleId
		{
			// 阶段回复
			String linkClassName = this.getLinkService().getLinkObject()
					.getClass().getName();
			List linkList = this.getTaskService().getExceptMeTask(sheetKey,
					linkClassName, userId);
			for (int i = 0; linkList != null && i < linkList.size(); i++) {
				ITask task = (ITask) this.getTaskService().getTaskModelObject()
						.getClass().newInstance();
				Object[] obj = (Object[]) linkList.get(i);
				task.setOperateRoleId(StaticMethod.nullObject2String(obj[0]));
				task.setOperateType(StaticMethod.nullObject2String(obj[1]));
				task.setTaskOwner(StaticMethod.nullObject2String(obj[2]));
				task.setTaskDisplayName(StaticMethod.nullObject2String(obj[3]));

				if (task.getTaskDisplayName() != null
						&& (task.getTaskDisplayName().indexOf("草稿") == -1 && task
								.getTaskDisplayName().indexOf("驳回") == -1)) {
					// ifAddReceive = false;
					toList.add(task);
				}
			}
		}
		// if (preLinkId.equals("") || toList == null || toList.size() == 0)//
		// 当preLinkId为空时，则表示需要从main中取出sendRoleId
		// {
		// if (ifAddReceive == true) {
		ITask task = (ITask) this.getTaskService().getTaskModelObject()
				.getClass().newInstance();
		BaseMain mainObj = this.getMainService().getSingleMainPO(sheetKey);
		task.setOperateRoleId(mainObj.getSendRoleId());
		String name = service.id2Name(mainObj.getSendRoleId(),
				"tawSystemUserDao");
		if (name.equals("")) {
			task.setOperateType("subrole");
			task.setTaskOwner(mainObj.getSendRoleId());
		} else {
			task.setOperateType("user");
			task.setTaskOwner(mainObj.getSendUserId());
		}
		task.setTaskDisplayName("新增工单");
		toList.add(task);

		// }
		// }



		BaseLink linkObject = (BaseLink) getLinkService().getLinkObject()
				.getClass().newInstance();

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
		}
		request.setAttribute("toOperaterList", toList);

		if (!preLinkId.equals("") && !preLinkId.equals("null")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}
		linkObject.setOperateType(Integer
				.valueOf(Constants.ACTION_PHASE_BACKTOUP));
		linkObject.setOperateRoleId(operateRoleId);
		linkObject.setOperaterContact(sessionform.getContactMobile());
		String acceptLimit = StaticMethod.getLocalString(1);
		String completeLimit = StaticMethod.getLocalString(3);
		linkObject.setNodeAcceptLimit(SheetUtils.stringToDate(acceptLimit));
		linkObject.setNodeCompleteLimit(SheetUtils.stringToDate(completeLimit));
		// zhangying 添加操作时间 090223
		linkObject.setOperateTime(StaticMethod.getLocalTime());
		// end
		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);
		String mainId = RequestUtils.getStringParameter(request, "mainId");
		linkObject.setMainId(mainId);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);

		String correlationKey = StaticMethod.nullObject2String(request
				.getParameter("correlationKey"), "");

		request.setAttribute("correlationKey", correlationKey);
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		request.setAttribute("sheetKey", sheetKey);
		request.setAttribute("preLinkId", preLinkId);
		
		
		
		//在整合中没有必要整合到新的版本中所以就在这里修改添加了几个参数
		String processTemplateName = this.getMainService().getFlowTemplateName();
		//beanId
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		String beanid = flowmanage.getTawSystemWorkflowByName(processTemplateName).getMainServiceBeanId();
		request.setAttribute("beanId", beanid);
		// mainClassName
		Class mainclazz = this.getMainService().getMainObject().getClass();
		request.setAttribute("mainClassName", mainclazz.getName());
		// linkClassName
		Class linkclazz = this.getLinkService().getLinkObject().getClass();
		request.setAttribute("linkClassName", linkclazz.getName());
		request.setAttribute("processTemplateName", processTemplateName);
		
	}

	/**
	 * 执行显示阶段通知页面
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showPhaseAdvicePage(ActionMapping mapping, ActionForm form,
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
		System.out.println("taskName:" + taskName);
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"));
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");

		String dealPerformer = null;
		String dealPerformerLeader = null;
		String dealPerformerType = null;

		List list = this.getTaskService().getCurrentUndoTask(sheetKey);
		List toList = new ArrayList();
		for (int i = 0; list != null && i < list.size(); i++) {
			ITask task = (ITask) list.get(i);
			ITask tempTask = (ITask) this.getTaskService().getTaskModelObject()
					.getClass().newInstance();
			tempTask.setOperateRoleId(task.getOperateRoleId());
			if (task.getOperateType() == null
					|| task.getOperateType().equals("")
					|| task.getOperateType().equals("null")) {
				ID2NameService service = (ID2NameService) ApplicationContextHolder
						.getInstance().getBean("ID2NameGetServiceCatch");
				String name = service.id2Name(task.getOperateRoleId(),
						"tawSystemUserDao");
				if (name.equals("")) {
					tempTask.setOperateType("subrole");
				} else {
					tempTask.setOperateType("user");
				}
			} else {
				tempTask.setOperateType(task.getOperateType());
			}
			tempTask.setTaskOwner(task.getTaskOwner());
			tempTask.setTaskDisplayName(task.getTaskDisplayName());
			toList.add(tempTask);
		}
		request.setAttribute("toOperaterList", toList);
		System.out.println("toOperaterList>>>>>>>" + toList);

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		BaseLink linkObject = (BaseLink) getLinkService().getLinkObject()
				.getClass().newInstance();

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
			linkObject.setOperaterContact(sessionform.getContactMobile());
		}

		linkObject.setOperateType(Integer
				.valueOf(Constants.ACTION_DRIVERFORWARD));// 阶段通知标识
		linkObject.setOperateRoleId(operateRoleId);
		String acceptLimit = StaticMethod.getLocalString(1);
		String completeLimit = StaticMethod.getLocalString(3);
		linkObject.setNodeAcceptLimit(SheetUtils.stringToDate(acceptLimit));
		linkObject.setNodeCompleteLimit(SheetUtils.stringToDate(completeLimit));
		// zhangying 添加操作时间 090223
		linkObject.setOperateTime(StaticMethod.getLocalTime());
		// end
		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);
		String mainId = RequestUtils.getStringParameter(request, "mainId");
		linkObject.setMainId(mainId);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);

		String correlationKey = StaticMethod.nullObject2String(request
				.getParameter("correlationKey"), "");

		request.setAttribute("correlationKey", correlationKey);
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		request.setAttribute("sheetKey", sheetKey);
		request.setAttribute("preLinkId", preLinkId);
		
		
		//在整合中没有必要整合到新的版本中所以就在这里修改添加了几个参数
		String processTemplateName = this.getMainService().getFlowTemplateName();
		//beanId
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		String beanid = flowmanage.getTawSystemWorkflowByName(processTemplateName).getMainServiceBeanId();
		request.setAttribute("beanId", beanid);
		// mainClassName
		Class mainclazz = this.getMainService().getMainObject().getClass();
		request.setAttribute("mainClassName", mainclazz.getName());
		// linkClassName
		Class linkclazz = this.getLinkService().getLinkObject().getClass();
		request.setAttribute("linkClassName", linkclazz.getName());
		request.setAttribute("processTemplateName", processTemplateName);
		request.setAttribute("TKID", TKID);
		String taskNamespace = "http://" + mapping.getPath().substring(1)+ "Process";
		request.setAttribute("taskNamespace", taskNamespace);
	}

	/**
	 * 执行流程的事件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void performProcessEvent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("----------performProcessEvent");
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));

		String processTemplateName = StaticMethod.nullObject2String(request
				.getParameter("processTemplateName"));
		String operateName = StaticMethod.nullObject2String(request
				.getParameter("operateName"));
		System.out.println(request.getParameter("dealPerformerLeader"));

		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		if (!piid.equals("")) {
			Map map = request.getParameterMap();
			Map serializableMap = SheetUtils.serializableParemeterMap(map);
			Iterator it = serializableMap.keySet().iterator();
			HashMap WpsMap = new HashMap();
			while (it.hasNext()) {
				String mapKey = (String) it.next();
				Map tempMap = (Map) serializableMap.get(mapKey);
				HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
				HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
						tempColumnMap);
				WpsMap.putAll(tempWpsMap);
			}
			setFlowEngineMap(WpsMap);

			dealFlowEngineMap(mapping, form, request, response);

			Map link = (HashMap) this.getFlowEngineMap().get("link");
			BaseMain main = this.getMainService().getMainDAO()
					.loadSinglePOByProcessId(piid, this.getMainService().getMainObject());

			System.out.println("getFlowEngineMap().get(main):"
					+ main.getCorrelationKey());
			link.put("correlationKey", main.getCorrelationKey());
			this.getFlowEngineMap().put("link", link);

			/** 获取登陆信息，add by qinmin* */
			HashMap sessionMap = new HashMap();
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");
			sessionMap.put("userId", sessionform.getUserid());
			sessionMap.put("password", sessionform.getPassword());

			/** add by qinmin* */
			// finish task
			businessFlowService.triggerEvent(piid, processTemplateName,
					operateName, getFlowEngineMap(), sessionMap);
		}
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
	public void performSaveDealInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String taskId = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String linkId = "";

		// 从页面上获取处理信息
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = request.getParameterMap();
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
				linkModel.setMainId(null);
				linkId = this.getLinkService().addLink(linkModel);
			}

		}
		if (!linkId.equals("")) {
			ITask taskModel = this.getTaskService().getSinglePO(taskId);
			taskModel.setCurrentLinkId(linkId);
			this.getTaskService().addTask(taskModel);
		}
	}

	/**
	 * 工单移交输入页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void showTransferWorkItemPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		BaseLink linkObject = (BaseLink) getLinkService().getLinkObject()
				.getClass().newInstance();

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
			linkObject.setOperaterContact(sessionform.getContactMobile());
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		if (!preLinkId.equals("") && !preLinkId.equals("null")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(this
				.getMainService().getFlowTemplateName(), this
				.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		String bigRoleId = "0";
		if (flowDefine != null) {
			PhaseId phaseIds[] = flowDefine.getPhaseId();
			for (int i = 0; i < phaseIds.length; i++) {
				PhaseId phaseId = phaseIds[i];
				ToPhaseId[] toPhaseIds = phaseId.getToPhaseId();
				for (int j = 0; toPhaseIds != null && j < toPhaseIds.length; j++) {
					ToPhaseId toPhaseId = toPhaseIds[j];
					if (toPhaseId.getId().equals(taskName)) {
						String roleId = toPhaseId.getRole();
						if (roleId != null && !roleId.equals("")
								&& roleId.indexOf("@") != 0) {
							bigRoleId = roleId.substring(
									roleId.indexOf("@") + 1, roleId.length());
							break;
						}
					}
				}
			}
		}
		request.setAttribute("bigRoleId", bigRoleId);
		linkObject.setOperateRoleId(operateRoleId);
		String acceptLimit = StaticMethod.getLocalString(1);
		String completeLimit = StaticMethod.getLocalString(3);
		linkObject.setNodeAcceptLimit(SheetUtils.stringToDate(acceptLimit));
		linkObject.setNodeCompleteLimit(SheetUtils.stringToDate(completeLimit));
		linkObject.setOperateTime(StaticMethod.getLocalTime());
		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);

		String mainId = RequestUtils.getStringParameter(request, "mainId");
		linkObject.setMainId(mainId);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);
		request.setAttribute("operateType", operateType);
		String correlationKey = StaticMethod.nullObject2String(request
				.getParameter("correlationKey"), "");

		request.setAttribute("correlationKey", correlationKey);
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}

		// 在整合中没有必要整合到新的版本中所以就在这里修改添加了几个参数
		String processTemplateName = this.getMainService()
				.getFlowTemplateName();
		// beanId
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder
				.getInstance().getBean("ITawSystemWorkflowManager");
		String beanid = flowmanage.getTawSystemWorkflowByName(
				processTemplateName).getMainServiceBeanId();
		request.setAttribute("beanId", beanid);
		// mainClassName
		Class mainclazz = this.getMainService().getMainObject().getClass();
		request.setAttribute("mainClassName", mainclazz.getName());
		// linkClassName
		Class linkclazz = this.getLinkService().getLinkObject().getClass();
		request.setAttribute("linkClassName", linkclazz.getName());

	}

	/**
	 * 工单移交,包括同一角色内部移交，以及不同角色之间移交。
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performTransferWorkItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());

		String taskId = StaticMethod.nullObject2String(request
				.getParameter("TKID"));

		if (sessionform != null) {

			// 获取页面输入信息
			HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
					response);
			Map map = request.getParameterMap();
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

			/**
			 * modify by chenyuanshu 此处不处理，此处存在问题需要修改
			 */
			// 转移到目的执行用户ID，这里需要判断后台传递过来的是用户ID还是角色ID。
			HashMap operate = (HashMap) this.getFlowEngineMap().get("operate");

			String toOwnerUserId = StaticMethod.nullObject2String(operate
					.get("dealPerformerLeader"));
			String toOwnerRoleId = StaticMethod.nullObject2String(operate
					.get("dealPerformer"));
			String opeOrgType = StaticMethod.nullObject2String(operate
					.get("dealPerformerType"));

			// 获取当前操作用户ID以及角色ID
			String fromOwnerUserId = sessionform.getUserid();
			HashMap link = (HashMap) this.getFlowEngineMap().get("link");
			String fromOwnerRoleId = StaticMethod.nullObject2String(link
					.get("operateRoleId"));
			// 修改link表中保存的下一步操作对象
			link.put("toOrgRoleId", toOwnerRoleId);
			this.getFlowEngineMap().put("link", link);

			System.out.println("toOwnerRoleId=" + toOwnerRoleId
					+ ";toOwnerUserId=" + toOwnerUserId + ";opeOrgType="
					+ opeOrgType + ";fromOwnerUserId=" + fromOwnerUserId
					+ ";fromOwnerRoleId=" + fromOwnerRoleId);
			// 工单转移
			businessFlowService.transferWorkItem(taskId, fromOwnerUserId,
					fromOwnerRoleId, toOwnerUserId, toOwnerRoleId, opeOrgType,
					this.getFlowEngineMap(), sessionMap);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getTemplatesByUserId(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void getTemplatesByUserId(ActionMapping mapping, ActionForm form,
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
		List templates = this.getMainService().getTemplatesByUserIds(userId,
				pageIndex, new Integer(pageSize.intValue()), aTotal);
		Integer total = new Integer(aTotal[0]);

		String type = request.getParameter("type");
		if (type != null && !type.equals("")) {
			request.setAttribute("templateManage", "templateManage");
		}
		// 将分页后的列表写入页面供使用
		request.setAttribute("taskList", templates);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getDealTemplatesByUserId(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void getDealTemplatesByUserId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();

		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"), "");

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

		int[] aTotal = { 0 };
		String codition = "";
		if (operateType != null && !operateType.equals("")) {
			codition = " and link.operateType = " + operateType;
		}
		String linkName = (getLinkService().getLinkObject().getClass())
				.getName();
		// 分页取得列表
		// 获取模板页面分页列表
		List templates = null;
		String draft = request.getParameter("draft");
		if (draft != null && draft.equalsIgnoreCase("DraftHumTask")) {
			request.setAttribute("draft", "DraftHumTask");
			templates = this.getMainService().getTemplatesByUserIds(userId,
					pageIndex, new Integer(pageSize.intValue()), aTotal);
		} else {
			templates = this.getLinkService().getDealTemplatesByUserIds(userId,
					pageIndex, new Integer(pageSize.intValue()), aTotal,
					linkName, codition);
		}

		Integer total = new Integer(aTotal[0]);

		// 将分页后的列表写入页面供使用
		request.setAttribute("taskList", templates);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);

		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"), "");
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"), "");
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"), "");
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		String taskStatus = StaticMethod.nullObject2String(request
				.getParameter("taskStatus"), "");

		String type = request.getParameter("type");

		if (type != null && !type.equals("")) {
			request.setAttribute("templateManage", "templateManage");
		}

		request.setAttribute("operateType", operateType);
		request.setAttribute("sheetKey", sheetKey);
		request.setAttribute("taskId", taskId);
		request.setAttribute("piid", piid);
		request.setAttribute("taskName", taskName);
		request.setAttribute("operateRoleId", operateRoleId);
		request.setAttribute("TKID", TKID);
		request.setAttribute("preLinkId", preLinkId);
		request.setAttribute("taskStatus", taskStatus);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#showInputTemplateSheetPage(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void showInputTemplateSheetPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String id = request.getParameter("templateId");
		BaseMain mainObject = (BaseMain) this.getMainService().getSingleMainPO(
				id);

		BaseMain newMainObject = (BaseMain) this.getMainService()
				.getMainObject().getClass().newInstance();

		Map mainObjectMap = SheetBeanUtils.describe(mainObject);
		SheetBeanUtils.populateMap2Bean(newMainObject, mainObjectMap);
		newMainObject.setId(null);
		newMainObject.setSendTime(StaticMethod.getLocalTime());
		newMainObject.setSheetAcceptLimit(StaticMethod.getLocalTime());
		newMainObject.setSheetCompleteLimit(StaticMethod.getLocalTime());

		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}

		String parentCorrelation = StaticMethod.nullObject2String(request
				.getParameter("parentCorrelation"), "");
		String parentSheetId = StaticMethod.nullObject2String(request
				.getParameter("parentSheetId"), "");
		String parentSheetName = StaticMethod.nullObject2String(request
				.getParameter("parentSheetName"), "");
		request.setAttribute("sendUserName", sessionform.getUsername());
		request.setAttribute("sendDeptName", sessionform.getDeptname());
		request.setAttribute("sendRoleName", sessionform.getRolename());
		request.setAttribute("sheetMain", newMainObject);
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("parentCorrelation", parentCorrelation);
		request.setAttribute("parentSheetId", parentSheetId);
		request.setAttribute("parentSheetName", parentSheetName);
		request.setAttribute("status", Constants.SHEET_RUN);
		request.setAttribute("methodBeanId", mapping.getAttribute());

		ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
				.getInstance().getBean("ItawSheetAccessManager");
		String processName = this.getMainService().getFlowTemplateName();
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName,
				"");
		request.setAttribute("tawSheetAccess", access);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#showDealInputTemplate(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void showDealInputTemplate(ActionMapping mapping, ActionForm form,
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
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"), "");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		// 获取当前task对象
		BaseLink linkObject = null;
		ITask taskModel = this.getTaskService().getSinglePO(TKID);
		String curId = StaticMethod.nullObject2String(taskModel
				.getCurrentLinkId());

		String dealTemplateId = request.getParameter("dealTemplateId");
		String linkId = dealTemplateId;

		BaseLink newLinkObject = (BaseLink) getLinkService().getLinkObject()
				.getClass().newInstance();

		if (!linkId.equals(""))
			linkObject = this.getLinkService().getSingleLinkPO(linkId);
		if (linkObject == null) {
			linkObject = newLinkObject;
		}
		Map linkMap = new HashMap();
		linkMap = SheetBeanUtils.describe(linkObject);
		SheetBeanUtils.populateMap2Bean(newLinkObject, linkMap);
		if (sessionform != null) {
			newLinkObject.setOperateUserId(sessionform.getUserid());
			newLinkObject.setOperateDeptId(sessionform.getDeptid());
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		if (!preLinkId.equals("") && !preLinkId.equals("null")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}

		newLinkObject.setOperateRoleId(operateRoleId);
		String acceptLimit = StaticMethod.getLocalString(1);
		String completeLimit = StaticMethod.getLocalString(3);
		newLinkObject.setNodeAcceptLimit(SheetUtils.stringToDate(acceptLimit));
		newLinkObject.setNodeCompleteLimit(SheetUtils
				.stringToDate(completeLimit));

		newLinkObject.setPiid(piid);
		newLinkObject.setAiid(aiid);
		newLinkObject.setTkid(TKID);
		newLinkObject.setId(null);
		// add by leo
		// 取mainId
		String mainId = RequestUtils.getStringParameter(request, "mainId");

		newLinkObject.setMainId(mainId);
		request.setAttribute("sheetLink", newLinkObject);
		request.setAttribute("taskName", taskName);
		request.setAttribute("operateType", operateType);

		String correlationKey = StaticMethod.nullObject2String(request
				.getParameter("correlationKey"), "");

		request.setAttribute("correlationKey", correlationKey);
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
				.getInstance().getBean("ItawSheetAccessManager");
		String processName = this.getMainService().getFlowTemplateName();
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName,
				taskName);
		request.setAttribute("tawSheetAccess", access);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#saveTemplate(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void saveTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map parameterMap = request.getParameterMap();
		String templateId = StaticMethod.nullObject2String(request
				.getParameter("templateId"), "");

		String type = StaticMethod.nullObject2String(request
				.getParameter("type"), "");

		BaseMain mainObject = (BaseMain) (getMainService().getMainObject()
				.getClass()).newInstance();
		String id = UUIDHexGenerator.getInstance().getID();
		String oldTemplateName = "";
		if (templateId != null && !templateId.equals("")) {
			mainObject = getMainService().getSingleMainPO(templateId);
			id = templateId;
			oldTemplateName = mainObject.getSheetTemplateName();
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
					.getParameter("newSheetTemplateName"), "");
			templateName.append(sheetTemplateName);
		}
		mainObject.setSheetTemplateName(templateName.toString());

		mainObject.setId(id);
		mainObject.setTemplateFlag(Constants.TEMPLATE_STATUS_FLAG);

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
		// 保存模板
		getMainService().mergeObject(mainObject);

		String addUrl = request.getRequestURI() + "?method=showNewSheetPage";
		String listUrl = request.getRequestURI()
				+ "?method=getTemplatesByUserId&type=templateManage";
		request.setAttribute("addUrl", addUrl);
		request.setAttribute("listUrl", listUrl);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#saveDealTemplate(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void saveDealTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map parameterMap = request.getParameterMap();
		String dealTemplateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"), "");

		String type = StaticMethod.nullObject2String(request
				.getParameter("type"), "");

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		BaseLink linkObject = (BaseLink) (getLinkService().getLinkObject()
				.getClass()).newInstance();
		String id = UUIDHexGenerator.getInstance().getID();
		if (dealTemplateId != null && !dealTemplateId.equals("")) {
			id = dealTemplateId;
			linkObject = getLinkService().getSingleLinkPO(id);
		}

		SheetBeanUtils.populateMap2Bean(linkObject, parameterMap);

		StringBuffer templateName = new StringBuffer();
		if (!type.equals("") && type.equals("dealTemplateManage")) {
			String oldTemplateName = StaticMethod.nullObject2String(request
					.getParameter("templateName"), "");
			templateName.append(oldTemplateName);
		} else {
			if (dealTemplateId.equals("")) {
				String dealTemplateNameRule = StaticMethod.nullObject2String(
						request.getParameter("dealTemplateNameRule"), "");
				String dictKey = StaticMethod.nullObject2String(request
						.getParameter("dictKey"), "");
				String dealTemplateNameRuleArray[] = dealTemplateNameRule
						.split(";");
				for (int i = 0; i < dealTemplateNameRuleArray.length; i++) {
					String attributte = dealTemplateNameRuleArray[i];
					String attriName = StaticMethod.nullObject2String(request
							.getParameter(attributte), "");
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
				+ "?method=showListsendundo";
		String dealTemplateListUrl = request.getRequestURI()
				+ "?method=getDealTemplatesByUserId&type=templateManage";
		request.setAttribute("dealOperateListUrl", dealOperateListUrl);
		request.setAttribute("dealTemplateListUrl", dealTemplateListUrl);

		getLinkService().mergeObject(linkObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#referenceTemplate(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void referenceTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String templateId = StaticMethod.nullObject2String(request
				.getParameter("templateId"));
		request.setAttribute("templateId", templateId);
		String dealTemplateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"));
		request.setAttribute("dealTemplateId", dealTemplateId);

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
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
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
	}

	/**
	 * 得到main模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String templateId = StaticMethod.nullObject2String(request
				.getParameter("templateId"));
		if (templateId != null && !templateId.equals("")) {
			request.setAttribute("templateId", templateId);
		}
		BaseMain mainObject = getMainService().getSingleMainPO(templateId);
		if (mainObject == null) {
			mainObject = (BaseMain) getMainService().getMainObject().getClass()
					.newInstance();
		}
		String type = StaticMethod.nullObject2String(request
				.getParameter("type"));
		if (type != null && type.equals("templateManage")) {
			request.setAttribute("type", "templateManage");
		}
		request.setAttribute("sheetMain", mainObject);
		request.setAttribute("templateManage", "templateManage");
	}

	/**
	 * 得到工单模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getDealTemplate(ActionMapping mapping, ActionForm form,
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
	}

	/**
	 * 删除main模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void removeTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String templateId = StaticMethod.nullObject2String(request
				.getParameter("templateId"));
		BaseMain mainObject = getMainService().getSingleMainPO(templateId);
		if (mainObject != null) {
			getMainService().clearObjectOfCurrentSession();
			getMainService().removeMain(mainObject);
		}

		String addUrl = request.getRequestURI() + "?method=showNewSheetPage";
		String type = StaticMethod.nullObject2String(request
				.getParameter("type"), "");
		String listUrl = "";
		if (type != null && !type.equals("")) {
			listUrl = request.getRequestURI()
					+ "?method=getTemplatesByUserId&type=" + type;
		} else {
			listUrl = request.getRequestURI()
					+ "?method=getTemplatesByUserId&type=";
		}
		request.setAttribute("addUrl", addUrl);
		request.setAttribute("listUrl", listUrl);
	}

	/**
	 * 删除link模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void removeDealTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dealTemplateId = StaticMethod.nullObject2String(request
				.getParameter("dealTemplateId"));
		BaseLink linkObject = getLinkService().getSingleLinkPO(dealTemplateId);
		if (linkObject != null) {
			getLinkService().clearObjectOfCurrentSession();
			getLinkService().removeLink(linkObject);
		}

		String dealOperateListUrl = request.getRequestURI()
				+ "?method=showListsendundo";
		String dealTemplateListUrl = request.getRequestURI()
				+ "?method=getDealTemplatesByUserId&type=templateManage";
		request.setAttribute("dealOperateListUrl", dealOperateListUrl);
		request.setAttribute("dealTemplateListUrl", dealTemplateListUrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#showSheetAccessoriesList(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void showSheetAccessoriesList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String mainId = StaticMethod.null2String(request.getParameter("id"));

		BaseMain sheetMain = this.getMainService().getSingleMainPO(mainId);
		List sheetLinks = this.getLinkService().getLinksByMainId(mainId);

		Map ObjectMap = this.getAttachmentAttributeOfOjbect();
		List mainAttributeList = (List) ObjectMap.get("mainObject");
		List linkAttributeList = (List) ObjectMap.get("linkObject");

		Map operationMap = new HashMap();
		Map userIdMap = new HashMap();
		StringBuffer where = new StringBuffer();

		for (Iterator iterator = mainAttributeList.iterator(); iterator
				.hasNext();) {
			String attribute = (String) iterator.next();
			String getMethod = "get" + StaticMethod.firstToUpperCase(attribute);
			Method setterMethod = sheetMain.getClass().getMethod(getMethod,
					null);
			String accessories = (String) setterMethod.invoke(sheetMain, null);

			if (accessories != null && !accessories.equals("")) {
				String attachments[] = accessories.split(",");
				for (int i = 0; i < attachments.length; i++) {
					String attachmentName = attachments[i].replaceAll("'", "");
					operationMap.put(attachmentName, "");
					userIdMap.put(attachmentName, sheetMain.getSendUserId());
				}
				where.append(accessories);
			}
		}

		if (sheetLinks.size() > 0) {
			for (Iterator it = sheetLinks.iterator(); it.hasNext();) {
				BaseLink baseLink = (BaseLink) it.next();

				for (Iterator iterator = linkAttributeList.iterator(); iterator
						.hasNext();) {
					String attribute = (String) iterator.next();
					String getMethod = "get"
							+ StaticMethod.firstToUpperCase(attribute);
					Method setterMethod = baseLink.getClass().getMethod(
							getMethod, null);
					String accessories = (String) setterMethod.invoke(baseLink,
							null);

					if (accessories != null && !accessories.equals("")) {
						String nodeAccessoriesArray[] = accessories.split(",");
						for (int i = 0; i < nodeAccessoriesArray.length; i++) {
							String attachmentName = nodeAccessoriesArray[i]
									.replaceAll("'", "");
							operationMap.put(attachmentName, baseLink
									.getActiveTemplateId());
							userIdMap.put(attachmentName, baseLink
									.getOperateUserId());
						}
						if (!where.toString().equals("")) {
							where.append(",");
						}
						where.append(accessories);
					}
				}
			}
		}

		if (!where.toString().equals("")) {
			List newAttachments = new ArrayList();
			List attachments = this.getMainService().getAllAttachmentsBySheet(
					where.toString());
			if (attachments.size() > 0) {
				for (Iterator it = attachments.iterator(); it.hasNext();) {
					TawCommonsAccessories attachment = (TawCommonsAccessories) it
							.next();
					TawCommonsAccessoriesForm tawCommonsAccessoriesForm = new TawCommonsAccessoriesForm();
					Map attachmentMap = SheetBeanUtils.describe(attachment);
					SheetBeanUtils.populateMap2Bean(tawCommonsAccessoriesForm,
							attachmentMap);
					String ActiveTemplateId = (operationMap.get(attachment
							.getAccessoriesName()) == null ? ""
							: (String) operationMap.get(attachment
									.getAccessoriesName()));
					tawCommonsAccessoriesForm
							.setActiveTemplateId(ActiveTemplateId);
					tawCommonsAccessoriesForm
							.setAccessoriesUploadDate(attachment
									.getAccessoriesUploadTime());
					String userId = (userIdMap.get(attachment
							.getAccessoriesName()) == null ? ""
							: (String) userIdMap.get(attachment
									.getAccessoriesName()));
					ITawSystemUserManager service = (ITawSystemUserManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemUserSaveManagerFlush");
					TawSystemUser user = service.getTawSystemUserByuserid(userId);
					String accessoriesdep = user.getDeptid()==null?"":user.getDeptid();
					tawCommonsAccessoriesForm.setAccessoriesdep(accessoriesdep);
					tawCommonsAccessoriesForm.setAccessoriesUploader(userId);
					newAttachments.add(tawCommonsAccessoriesForm);
				}
			}
			request.setAttribute("sheetAccessories", newAttachments);
		}
		operationMap.clear();
		userIdMap.clear();
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
		String tempType = StaticMethod.nullObject2String(request
				.getParameter("operateType"), "");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		BaseLink linkObject = (BaseLink) getLinkService().getLinkObject()
				.getClass().newInstance();

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
			linkObject.setOperaterContact(sessionform.getContactMobile());
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		if (!preLinkId.equals("") && !preLinkId.equals("null")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}
		linkObject.setOperateRoleId(operateRoleId);
		linkObject.setOperateTime(StaticMethod.getLocalTime());
		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);

		String mainId = RequestUtils.getStringParameter(request, "mainId");
		linkObject.setMainId(mainId);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);
		request.setAttribute("operateType", tempType);
		String correlationKey = StaticMethod.nullObject2String(request
				.getParameter("correlationKey"), "");

		request.setAttribute("correlationKey", correlationKey);
		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}
		
		
		//在整合中没有必要整合到新的版本中所以就在这里修改添加了几个参数
		String processTemplateName = this.getMainService().getFlowTemplateName();
		//beanId
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		String beanid = flowmanage.getTawSystemWorkflowByName(processTemplateName).getMainServiceBeanId();
		request.setAttribute("beanId", beanid);
		// mainClassName
		Class mainclazz = this.getMainService().getMainObject().getClass();
		request.setAttribute("mainClassName", mainclazz.getName());
		// linkClassName
		Class linkclazz = this.getLinkService().getLinkObject().getClass();
		request.setAttribute("linkClassName", linkclazz.getName());
		request.setAttribute("processTemplateName", processTemplateName);
	}

	/**
	 * 处理工单强制归档、作废等操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request.
	 * @param response
	 * @throws Exception
	 */
	public void performFroceHold(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));
		String processTemplateName = StaticMethod.nullObject2String(request
				.getParameter("processTemplateName"));
		String operateName = StaticMethod.nullObject2String(request
				.getParameter("operateName"));
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);

		try {
			Map map = request.getParameterMap();
			Map serializableMap = SheetUtils.serializableParemeterMap(map);
			Iterator it = serializableMap.keySet().iterator();
			HashMap WpsMap = new HashMap();
			while (it.hasNext()) {
				String mapKey = (String) it.next();
				Map tempMap = (Map) serializableMap.get(mapKey);
				HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
				HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
						tempColumnMap);
				WpsMap.putAll(tempWpsMap);
			}
			setFlowEngineMap(WpsMap);
			dealFlowEngineMap(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		/** add by jialei* */

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

				BaseMain parentMain = parentSheet.getMainService().getSingleMainPO(parentSheetKey);
				BaseLink parentLink = (BaseLink) parentSheet.getLinkService()
						.getLinkObject().getClass().newInstance();
				mainMap = SheetBeanUtils.bean2MapWithNull(parentMain);
				linkMap = SheetBeanUtils.bean2MapWithNull(parentLink);
				operateMap.put("phaseId", toPhaseId);
				operateMap.put("hasNextTaskFlag", "invokeProcess");

				sheetMap.put("main", mainMap);
				sheetMap.put("link", linkMap);
				sheetMap.put("operate", operateMap);

				// businessFlowService.reInvokeProcess(invokePiid,
				// invokeOperateName, sheetMap, sessionMap);
				parentSheet.getBusinessFlowService().reInvokeProcess(
						invokePiid, invokeOperateName, sheetMap, sessionMap);
			}
		}
		businessFlowService.triggerEvent(piid, processTemplateName,
				operateName, getFlowEngineMap(), sessionMap);
	}

	/**
	 * 显示未归档工单列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showListForAdmin(ActionMapping mapping, ActionForm form,
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
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("orderCondition", orderCondition);
		HashMap taskListMap = this.getMainService().getListForAdmin(condition,
				pageIndex, pageSize);
		List sheetList = (List) taskListMap.get("sheetList");
		Integer total = (Integer) taskListMap.get("sheetTotal");

		// 将分页后的列表写入页面供使用
		request.setAttribute("taskList", sheetList);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
	}

	public void getAllDoRoleNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		System.out.println(">>>>" + sheetKey);
		List operateRoleIdAll = this.getTaskService().getOperateRoleIdAll(sheetKey);
		String isShowUser = StaticMethod.null2String(request
				.getParameter("isShowUser"));
		String roleString = "";
		for (int i = 0; i < operateRoleIdAll.size(); i++) {
			if (!roleString.equals(""))
				roleString = roleString + ",";
			roleString = roleString + "'" + (String) operateRoleIdAll.get(i)
					+ "'";
		}
		String whereStr = " id in (" + roleString + ")";
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemSubRoleManager");
		List list = mgr.getTawSystemSubRoles(whereStr);

		ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");

		JSONArray jsonList = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			TawSystemSubRole subrole = (TawSystemSubRole) list.get(i);
			// String deptName =
			// ID2NameServiceFactory.getId2nameServiceDB().id2Name(subrole.getDeptId(),"tawSystemDeptDao");
			JSONObject jitem = new JSONObject();
			jitem.put("id", subrole.getId());
			jitem.put("text", subrole.getSubRoleName());
			jitem.put("iconCls", "subrole");
			jitem.put("nodeType", "subrole");
			jitem.put("name", subrole.getSubRoleName());

			if ("true".equals(isShowUser)) {
				List userList = userMgr.getUserbyroleid(subrole.getId());
				JSONArray userArray = new JSONArray();
				for (int j = 0; j < userList.size(); j++) {
					TawSystemUser user = (TawSystemUser) userList.get(j);
					JSONObject useritem = new JSONObject();

					useritem.put("id", user.getUserid());
					useritem.put("name", user.getUsername());
					useritem.put("text", user.getUsername());
					useritem.put("leader", false);
					useritem.put("iconCls", "user");
					useritem.put("nodeType", "user");
					useritem.put("leaf", true);

					userArray.put(useritem);
				}
				jitem.put("user", userArray);
				// if(userList.size()>0){
				// jitem.put("leaf",true);
				// }
			}

			jsonList.put(jitem);
			System.out.println("......" + jsonList);
		}

		JSONUtil.print(response, jsonList.toString());
	}

	public void getNowDoRoleNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String operateRoleId = this.getTaskService().getOperateRoleId(sheetKey)
				.getOperateRoleId();
		String isShowUser = StaticMethod.null2String(request
				.getParameter("isShowUser"));

		String roleString = "'" + operateRoleId + "'";
		String whereStr = " id in (" + roleString + ")";

		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemSubRoleManager");
		List list = mgr.getTawSystemSubRoles(whereStr);

		ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");

		JSONArray jsonList = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			TawSystemSubRole subrole = (TawSystemSubRole) list.get(i);
			// String deptName =
			// ID2NameServiceFactory.getId2nameServiceDB().id2Name(subrole.getDeptId(),"tawSystemDeptDao");
			JSONObject jitem = new JSONObject();
			jitem.put("id", subrole.getId());
			jitem.put("text", subrole.getSubRoleName());
			jitem.put("iconCls", "subrole");
			jitem.put("nodeType", "subrole");
			jitem.put("name", subrole.getSubRoleName());

			if ("true".equals(isShowUser)) {
				List userList = userMgr.getUserbyroleid(subrole.getId());
				JSONArray userArray = new JSONArray();
				for (int j = 0; j < userList.size(); j++) {
					TawSystemUser user = (TawSystemUser) userList.get(j);
					JSONObject useritem = new JSONObject();

					useritem.put("id", user.getUserid());
					useritem.put("name", user.getUsername());
					useritem.put("text", user.getUsername());
					useritem.put("leader", false);
					useritem.put("iconCls", "user");
					useritem.put("nodeType", "user");
					useritem.put("leaf", true);

					userArray.put(useritem);
				}
				jitem.put("user", userArray);

			}

			jsonList.put(jitem);
			System.out.println("......" + jsonList);
		}

		JSONUtil.print(response, jsonList.toString());
	}

	public void getPreRoleNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		String operateRoleId = this.getLinkService().getOperateRoleId(preLinkId);
		String isShowUser = StaticMethod.null2String(request
				.getParameter("isShowUser"));

		String roleString = "'" + operateRoleId + "'";
		String whereStr = " id in (" + roleString + ")";

		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemSubRoleManager");
		List list = mgr.getTawSystemSubRoles(whereStr);

		ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");

		JSONArray jsonList = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			TawSystemSubRole subrole = (TawSystemSubRole) list.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("id", subrole.getId());
			jitem.put("text", subrole.getSubRoleName());
			jitem.put("iconCls", "subrole");
			jitem.put("nodeType", "subrole");
			jitem.put("name", subrole.getSubRoleName());

			if ("true".equals(isShowUser)) {
				List userList = userMgr.getUserbyroleid(subrole.getId());
				JSONArray userArray = new JSONArray();
				for (int j = 0; j < userList.size(); j++) {
					TawSystemUser user = (TawSystemUser) userList.get(j);
					JSONObject useritem = new JSONObject();

					useritem.put("id", user.getUserid());
					useritem.put("name", user.getUsername());
					useritem.put("text", user.getUsername());
					useritem.put("leader", false);
					useritem.put("iconCls", "user");
					useritem.put("nodeType", "user");
					useritem.put("leaf", true);

					userArray.put(useritem);
				}
				jitem.put("user", userArray);

			}

			jsonList.put(jitem);
			System.out.println("......" + jsonList);
		}

		JSONUtil.print(response, jsonList.toString());
	}



	/**
	 * 呈现分派页面
	 */
	public void showInputSplitPage(ActionMapping mapping, ActionForm form,
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

		// 获取当前task对象
		BaseLink linkObject = null;
		ITask taskModel = this.getTaskService().getSinglePO(TKID);
		String linkId = StaticMethod.nullObject2String(taskModel
				.getCurrentLinkId());
		if (!linkId.equals(""))
			linkObject = this.getLinkService().getSingleLinkPO(linkId);
		if (linkObject == null) {
			linkObject = (BaseLink) getLinkService().getLinkObject().getClass()
					.newInstance();
		}

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
			linkObject.setOperaterContact(sessionform.getContactMobile());
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		if (!preLinkId.equals("") && !preLinkId.equals("null")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}
		// linkObject.setOperateType(new Integer(1));// 转派标识
		linkObject.setOperateRoleId(operateRoleId);
		String acceptLimit = StaticMethod.getLocalString(1);
		String completeLimit = StaticMethod.getLocalString(3);
		linkObject.setNodeAcceptLimit(SheetUtils.stringToDate(acceptLimit));
		linkObject.setNodeCompleteLimit(SheetUtils.stringToDate(completeLimit));
		linkObject.setOperateTime(StaticMethod.getLocalTime());

		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);
		// add by leo
		// 取mainId
		String mainId = RequestUtils.getStringParameter(request, "mainId");
		// //通过main id取 main对象
		// BaseMain mainObject = getMainService().getSingleMainPO(mainId);
		// //供web使用
		// request.setAttribute("sheetMain", mainObject);
		// end

		linkObject.setMainId(mainId);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);
		request.setAttribute("taskStatus", taskStatus);
		request.setAttribute("operateType", operateType);

		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		ID2NameService service = (ID2NameService) ApplicationContextHolder
				.getInstance().getBean("ID2NameGetServiceCatch");
		String name = service.id2Name(operateRoleId, "tawSystemSubRoleDao");
		if (!name.equals("")) {
			ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemSubRoleManager");
			TawSystemSubRole subRole = mgr.getTawSystemSubRole(operateRoleId);
			String roleId = Long.toString(subRole.getRoleId());
			request.setAttribute("roleId", roleId);
		}
		// 取得会审的人员列表
		if (operateType.equals(Integer.toString(Constants.ACTION_JOIN_AUDIT))) {
			JSONArray sendUserAndRoles = this.getSubAuditPerformer(sessionform,
					piid);
			request.setAttribute("sendUserAndRoles", sendUserAndRoles);
		}
		
		//在整合中没有必要整合到新的版本中所以就在这里修改添加了几个参数
		String processTemplateName = this.getMainService().getFlowTemplateName();
		//beanId
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		String beanid = flowmanage.getTawSystemWorkflowByName(processTemplateName).getMainServiceBeanId();
		request.setAttribute("beanId", beanid);
		// mainClassName
		Class mainclazz = this.getMainService().getMainObject().getClass();
		request.setAttribute("mainClassName", mainclazz.getName());
		// linkClassName
		Class linkclazz = this.getLinkService().getLinkObject().getClass();
		request.setAttribute("linkClassName", linkclazz.getName());
		request.setAttribute("processTemplateName", processTemplateName);
		String subtaskName = StaticMethod.nullObject2String(request.getParameter("subtaskName"));
		request.setAttribute("subtaskName", subtaskName);
		request.setAttribute("TKID", TKID);
		String workflow = this.getMainService().getFlowTemplateName();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflow,this.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		request.setAttribute("taskNamespace", flowDefine.getTasknamespace());

	}

	/**
	 * 分派回复
	 */
	public void performSplitReply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String taskId = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		// 获取页面输入信息
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = request.getParameterMap();
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

		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		/** add by qinmin* */
		businessFlowService.completeHumanTask(taskId, getFlowEngineMap(),
				sessionMap);

	}

	/**
	 * ADD PANLONG 当驳回的时候查询上一条任务的执行者对象
	 * 从request中通过request.getAttribute(fOperateroleid/ftaskOwner/fOperateroleidType)
	 * 取得上一条任务的Operateroleid taskOwner OperateroleidType
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void setParentTaskOperateWhenRejct(HttpServletRequest request)
			throws Exception {
		String prelinkid = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"));
		BaseLink preLink = (BaseLink) this.getLinkService()
				.getSingleLinkPO(prelinkid);
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
				String sheetKey = preLink.getMainId();
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
	}

	/**
	 * 1、页面显示步骤从1开始循环递增到90之间，步骤最多只能有89个
	 * 2、连接线是从0开始相加，并在最后的结果前加上9，例90,91,92,93....99,910,912,999,9100,9101,9999,91000
	 */
	public void showWorkFlow(ActionMapping mapping, ActionForm form,
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
		String flowTemplateName = StaticMethod.nullObject2String(request
				.getParameter("flowTemplateName"), "");
		if (flowTemplateName.equals("")) {
			flowTemplateName = this.getMainService().getFlowTemplateName();
		}
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				flowTemplateName, this.getRoleConfigPath());
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

		
		//设置传入的参数，一个都不能少
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
		
		//在单独类里进行产生动态流程图
		Map resultMap = new WorkFlow().createWorkFlow(parameterMap);
		
		//将得到的结果传入页面
		String workflowXML = flowTemplateName + ".xml";
		String workFlowName = flowDefine.getDescription();
		request.setAttribute("workflowXML", workflowXML);
		request.setAttribute("workFlowName", workFlowName);
		request.setAttribute("stepList", resultMap.get("stepList"));
		request.setAttribute("joinLineList", resultMap.get("joinLineList"));
		request.setAttribute("historyList", resultMap.get("historyList"));
		request.setAttribute("currentList", resultMap.get("currentList"));
	}

	/**
	 * 显示流程实例图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb
	 * @since 2008-09-09
	 */
	public void showWorkFlowInstance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String mainId = StaticMethod.nullObject2String(request
				.getParameter("mainId"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");
		String dictSheetName = StaticMethod.nullObject2String(request
				.getParameter("dictSheetName"), "");
		String description = StaticMethod.nullObject2String(request
				.getParameter("description"), "");
		String linkServiceName = StaticMethod.nullObject2String(request
				.getParameter("linkServiceName"), "");

		// 该环节下的所有任务
		List allAiTaskList = this.getTaskService()
				.getAiTasksByTaskNameAndSheetId(taskName, mainId);
		List allTiTaskList = this.getTaskService()
				.getTkiTasksByTaskNameAndSheetId(taskName, mainId);


		Graph graph = new Graph(linkServiceName, description, dictSheetName);
		String[] vml = graph.draw(allAiTaskList, allTiTaskList);
		request.setAttribute("vml", vml);
		request.setAttribute("module", mapping.getPath());
	}
	
	/**
	 * 显示流程实例图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb
	 * @since 2008-09-09
	 */
	public void getLinkOperatePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		String aiid = StaticMethod.nullObject2String(request
				.getParameter("aiid"), "");
		String sheetKey = "";
		if (!aiid.equals("")) {
			ITask task = (ITask) this.getTaskService().getSinglePO(aiid);
			if (task != null) {
				sheetKey = task.getSheetKey();
			}
		}
		if (!sheetKey.equals("")) {
			BaseMain sheetMain = (BaseMain) this.getMainService()
					.getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", sheetMain);
		}		
		String linkName = (getLinkService().getLinkObject().getClass()).getName();
		
		List links = this.getLinkService().getLinkOperateByAiid(aiid, linkName);
		
		String module = mapping.getPath();
		module = module.substring(1, module.length());
		
		request.setAttribute("module", module);
		request.setAttribute("HISTORY", links);
	}

	/**
	 * 呈现处理回复通过界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showDealReplyAcceptPage(ActionMapping mapping, ActionForm form,
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

		// 获取当前task对象
		BaseLink linkObject = null;
		ITask taskModel = this.getTaskService().getSinglePO(TKID);
		String linkId = StaticMethod.nullObject2String(taskModel
				.getCurrentLinkId());
		if (!linkId.equals(""))
			linkObject = this.getLinkService().getSingleLinkPO(linkId);
		if (linkObject == null) {
			linkObject = (BaseLink) getLinkService().getLinkObject().getClass()
					.newInstance();
		}

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
			linkObject.setOperaterContact(StaticMethod.nullObject2String(
					linkObject.getOperaterContact(), sessionform
							.getContactMobile()));
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		if (!preLinkId.equals("") && !preLinkId.equals("null")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}
		// linkObject.setOperateType(new Integer(1));// 转派标识
		linkObject.setOperateRoleId(operateRoleId);
		// linkObject.setNodeAcceptLimit(StaticMethod.getLocalTime());
		// linkObject.setNodeCompleteLimit(StaticMethod.getLocalTime());
		linkObject.setOperateTime(StaticMethod.getLocalTime());

		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);
		// add by leo
		// 取mainId
		String mainId = RequestUtils.getStringParameter(request, "mainId");
		// //通过main id取 main对象
		// BaseMain mainObject = getMainService().getSingleMainPO(mainId);
		// //供web使用
		// request.setAttribute("sheetMain", mainObject);
		// end

		linkObject.setMainId(mainId);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);
		request.setAttribute("taskStatus", taskStatus);

		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		List list = this.getTaskService().getUndealTaskListByParentTaskId(TKID);
		request.setAttribute("acceptList", list);
		
		//在整合中没有必要整合到新的版本中所以就在这里修改添加了几个参数
		String processTemplateName = this.getMainService().getFlowTemplateName();
		//beanId
		ITawSystemWorkflowManager flowmanage = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		String beanid = flowmanage.getTawSystemWorkflowByName(processTemplateName).getMainServiceBeanId();
		request.setAttribute("beanId", beanid);
		// mainClassName
		Class mainclazz = this.getMainService().getMainObject().getClass();
		request.setAttribute("mainClassName", mainclazz.getName());
		// linkClassName
		Class linkclazz = this.getLinkService().getLinkObject().getClass();
		request.setAttribute("linkClassName", linkclazz.getName());
		request.setAttribute("processTemplateName", processTemplateName);
		request.setAttribute("TKID", TKID);

	}

	/**
	 * 呈现处理回复不通过界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showDealReplyRejectPage(ActionMapping mapping, ActionForm form,
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

		String subtaskName = StaticMethod.nullObject2String(request
				.getParameter("subtaskName"));
		request.setAttribute("subtaskName", subtaskName);
		// 获取当前task对象
		BaseLink linkObject = null;
		ITask taskModel = this.getTaskService().getSinglePO(TKID);
		String linkId = StaticMethod.nullObject2String(taskModel
				.getCurrentLinkId());
		if (!linkId.equals(""))
			linkObject = this.getLinkService().getSingleLinkPO(linkId);
		if (linkObject == null) {
			linkObject = (BaseLink) getLinkService().getLinkObject().getClass()
					.newInstance();
		}

		if (sessionform != null) {
			linkObject.setOperateUserId(sessionform.getUserid());
			linkObject.setOperateDeptId(sessionform.getDeptid());
			linkObject.setOperaterContact(StaticMethod.nullObject2String(
					linkObject.getOperaterContact(), sessionform
							.getContactMobile()));
		}
		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			request.setAttribute("sheetMain", mainObject);
		}
		if (!preLinkId.equals("") && !preLinkId.equals("null")) {
			BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
			request.setAttribute("preLink", preLink);
		}
		// linkObject.setOperateType(new Integer(1));// 转派标识
		linkObject.setOperateRoleId(operateRoleId);
		// linkObject.setNodeAcceptLimit(StaticMethod.getLocalTime());
		// linkObject.setNodeCompleteLimit(StaticMethod.getLocalTime());
		linkObject.setOperateTime(StaticMethod.getLocalTime());

		linkObject.setPiid(piid);
		linkObject.setAiid(aiid);
		linkObject.setTkid(TKID);
		// add by leo
		// 取mainId
		String mainId = RequestUtils.getStringParameter(request, "mainId");
		// //通过main id取 main对象
		// BaseMain mainObject = getMainService().getSingleMainPO(mainId);
		// //供web使用
		// request.setAttribute("sheetMain", mainObject);
		// end

		linkObject.setMainId(mainId);
		request.setAttribute("sheetLink", linkObject);
		request.setAttribute("taskName", taskName);
		request.setAttribute("taskStatus", taskStatus);

		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
			sheetPageName = sheetPageName + ".";
		}
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());
		List list = this.getTaskService().getUndealTaskListByParentTaskId(TKID);
		request.setAttribute("acceptList", list);
		
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
	}

	/**
	 * 呈现接口回调回复页面(接口互调)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showInvokeReplyPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	}

	/**
	 * 执行接口回调动作:恢复流程为活动状态,
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performInvokeReply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");// 取出主调流程mainId
		if (!sheetKey.equals("")) {
			// 恢复主调流程为活动状态
		}

	}
	/**
	 * 工单互调提交预处理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public void performInvokePreCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jsonRoot = new JSONObject();
		
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		if(taskName.equals(""))
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
		//查询工单互调表
    	ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
			.getInstance().getBean("ITawSheetRelationManager");
    	List relationAllList=rmgr.getAllSheetByParentIdAndPhaseId(sheetKey, taskName);
    	//如果已经调用了其他工单则返回true,否则返回false
    	if(relationAllList != null&&relationAllList.size()>0){
    		JSONUtil.success(response, "");
    	}else{
    		JSONUtil.fail(response, "");
    	}
	}
	
	public boolean performIsInvokeProcess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String tempUserId = "";
		ITask task = null;
		BocoLog.info(this, "operateType is:"+operateType);
		if(taskName.equals("")){
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
			
		}
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"), "");
		if(taskId != null && !taskId.equals("")){
			task = (ITask) this.getTaskService().getSinglePO(taskId);
		}
	
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		if (sessionform != null) {
			tempUserId = sessionform.getUserid();
		}
		// 查询工单互调表
		if (task != null) {
			ITawSheetRelationManager mgr = (ITawSheetRelationManager) ApplicationContextHolder
					.getInstance().getBean("ITawSheetRelationManager");
			List relationAllList = mgr.getSheetByProcessIdAndUserId(sheetKey,
					taskName, tempUserId, task.getProcessId());
			System.out.println("sheetKey=" + sheetKey + "==tempUserId="
					+ tempUserId);
			if (relationAllList != null && relationAllList.size() > 0
					&& !operateType.equals("111")) {
				return true;
			}else{
			    return false;
			}
		}else{
			return false;
		}
		
	}
	/**
	 * 显示本角色未处理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showListUndoByRole(ActionMapping mapping, ActionForm form,
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
		String flowName = this.getMainService().getFlowTemplateName();
		condition.put("flowName", flowName);
		HashMap taskListOvertimeMap = this.getTaskService().getUndoTaskByRole(
				condition, userId, pageIndex, pageSize);
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
			HashMap columnMapOverTip = OvertimeTipUtil
					.getNotOverTimeColumnByMapping(flowName);
			// 循环为task超时标识赋值
			for (int i = 0; i < taskOvertimeList.size(); i++) {
				ITask tmptask = null;
				Map taskMap = new HashMap();
				Map tmptaskMap = new HashMap();
				HashMap conditionMap = new HashMap();
				if (columnMap.size() > 0) {
					Object[] tmpObjArr = (Object[]) taskOvertimeList.get(i);
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
				}
				taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
				taskMap.putAll(tmptaskMap);
				taskList.add(tmptask);
				taskMapList.add(taskMap);
			}
		}

		// 将分页后的列表写入页面供使用
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
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
	 * 显示本角色已处理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showListsenddoneByRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap filterMap = new HashMap();
		filterMap.put("PROCESS_INSTANCE.TEMPLATE_NAME", getMainService()
				.getFlowTemplateName());
		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		// modified by Leo
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
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
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject()
				.getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		HashMap taskListMap = this.getTaskService().getDoneTaskByRole(
				condition, userId, pageIndex, pageSize);
		int total = ((Integer) taskListMap.get("taskTotal")).intValue();
		List taskList = (List) taskListMap.get("taskList");
		
		request.setAttribute("taskList", taskList);	
		request.setAttribute("total", new Integer(total));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "mainlist");
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
	}
	/**
	 * 显示工单隐藏的查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showQueryHidePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//设置初始化时间
		Calendar startDay = Calendar.getInstance();
		startDay.add(Calendar.DAY_OF_MONTH, -3);
		Calendar endDay = Calendar.getInstance();		
		String startDate = DateUtil.formatDate(startDay.getTime(), "yyyy-MM-dd hh:mm:ss");
		String endDate = DateUtil.formatDate(endDay.getTime(), "yyyy-MM-dd hh:mm:ss");
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		
		//找出该流程中的节点
		String workflowName = this.getMainService().getFlowTemplateName();
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName,this.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		if (flowDefine != null) {
			PhaseId phaseIds[] = flowDefine.getPhaseId();
			for (int i = 0; i < phaseIds.length; i ++) {
				PhaseId phaseId = phaseIds[i];
				if (!phaseId.getId().equals("receive")) {
					phaseIdMap.put(phaseId.getId(), phaseId.getName());
					phaseIdList.add(phaseId.getId());
				}
			}
		}
		request.setAttribute("phaseIdMap", phaseIdMap);
		request.setAttribute("stepIdList", phaseIdList);
		request.setAttribute("module", mapping.getPath().substring(1));
	}
	/**
	 * 工单隐藏查询提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performQueryHide(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ifQueryByHided = StaticMethod.nullObject2String(request.getParameter("ifQueryByHided"));
		String ifQueryByOvertime = StaticMethod.nullObject2String(request.getParameter("ifQueryByOvertime"));
		String stepId = StaticMethod.nullObject2String(request.getParameter("stepId"));
		String sendTimeStartDate = StaticMethod.nullObject2String(request.getParameter("sendTimeStartDate"));
		String sendTimeEndDate = StaticMethod.nullObject2String(request.getParameter("sendTimeEndDate"));
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		// 分页取得列表
		// wps端分页取得列表
		HashMap condition = new HashMap();
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
		BaseLink linkObject = (BaseLink) this.getLinkService().getLinkObject().getClass().newInstance();
		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject().getClass().newInstance();
		condition.put("mainObject", mainObject);
		condition.put("linkObject", linkObject);
		condition.put("taskObject", taskObject);
		//组成查询条件
		//是否查询已经隐藏的工单
		StringBuffer hql = new StringBuffer();
		hql.append(" and main.deleted='"+ifQueryByHided+"'");
		request.setAttribute("isHided", ifQueryByHided);
		//是否查询超时工单
		if(ifQueryByOvertime.equals("1")){
			hql.append(" and (");
			hql.append("link.nodeCompleteLimit<link.operateTime");
			hql.append(" or (");
			Calendar nowDay = Calendar.getInstance();
			String nowDate = DateUtil.formatDate(nowDay.getTime(), "yyyy-MM-dd hh:mm:ss");
			hql.append("task.completeTimeLimit<to_date('"+nowDate+"','YYYY-MM-DD HH24:MI:SS')");
			hql.append(" and (");
			hql.append("task.taskStatus='"+Constants.TASK_STATUS_READY+"' or task.taskStatus='"+Constants.TASK_STATUS_CLAIMED+"'");
			hql.append(")))");
		}
		//是否按照步骤查询
		if(stepId.equals("")==false){
			if(stepId.indexOf("status1")!=-1){
				stepId = stepId.substring(0,stepId.indexOf("status1"));
				hql.append(" and main.status='1'");
			}else{
				hql.append(" and main.status<>'1'");
			}
			hql.append(" and task.taskName='"+stepId+"'");
		}
		//根据派单时间查询
		hql.append(" and main.sendTime>=to_date('"+sendTimeStartDate+"','YYYY-MM-DD HH24:MI:SS')");
		hql.append(" and main.sendTime<=to_date('"+sendTimeEndDate+"','YYYY-MM-DD HH24:MI:SS')");
		
		BocoLog.info(this, "===工单隐藏 hql:"+hql);
		
		condition.put("hql", hql.toString());
		
		HashMap resultMap = this.getMainService().getHideList(condition, pageIndex, pageSize);
	    Integer total = (Integer)resultMap.get("sheetTotal");
	    List result = (List)resultMap.get("sheetList");

		request.setAttribute("taskList", result);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
		
		//查询互调表
		ITawSheetRelationManager mgr = (ITawSheetRelationManager) (ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager"));
		ITawSystemWorkflowManager fmgr=(ITawSystemWorkflowManager) (ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager"));
		List toList = new ArrayList();
		List fromList = new ArrayList();
		List tmpNextResult = result;
		List tmpResult = result;
		Map tmpLastResult = new HashMap();
		while(tmpNextResult.size()>0){
			tmpResult = tmpNextResult;
			tmpNextResult = new ArrayList();
			for(int i=0;i<tmpResult.size();i++){
				BaseMain tmpMain = (BaseMain)tmpResult.get(i);
				tmpLastResult.put(tmpMain.getId(), tmpMain.getId());
				//查询调用我的工单
				TawSheetRelation sheetRelation = mgr.getRelationSheetByCurrentId(tmpMain.getId());
				if(sheetRelation!=null){
					TawSystemWorkflow parentWorkflow=fmgr.getTawSystemWorkflowByName(sheetRelation.getParentFlowName());
					String pfBeanId=parentWorkflow.getMainServiceBeanId();
					IMainService pfMainSerivce=(IMainService)(ApplicationContextHolder.getInstance().getBean(pfBeanId)); 
					BaseMain relationmain = (BaseMain) pfMainSerivce.getSingleMainPO(sheetRelation.getParentId());
					//防止重复添加
					if(tmpLastResult.size()==0||tmpLastResult.containsKey(relationmain.getId())==false){
						//是否查询已经隐藏的工单
						if(relationmain.getDeleted().intValue()==Integer.parseInt(ifQueryByHided)){
							TawSheetRelationForm relationForm=new TawSheetRelationForm();
							SheetBeanUtils.copyProperties(relationForm, sheetRelation);
							relationForm.setParentFlowCnName(parentWorkflow.getRemark());
							toList.add(relationForm);
							
							tmpNextResult.add(relationmain);
						}
					}
				}
				//查询我调用的工单
				List tmpList = mgr.getRelationSheetByParentId(tmpMain.getId());
				if(tmpList!=null&&tmpList.size()>0){
					for(int j=0;j<tmpList.size();j++){
						TawSheetRelation tmpsheetRelation =(TawSheetRelation)tmpList.get(j);
						TawSystemWorkflow currentWorkflow=fmgr.getTawSystemWorkflowByName(tmpsheetRelation.getCurrentFlowName());
						String crbeanId=currentWorkflow.getMainServiceBeanId();
						IMainService crMainSerivce=(IMainService)(ApplicationContextHolder.getInstance().getBean(crbeanId));
						BaseMain relationmain = (BaseMain) crMainSerivce.getSingleMainPO(tmpsheetRelation.getCurrentId());
						//防止重复添加
						if(tmpLastResult.size()==0||tmpLastResult.containsKey(relationmain.getId())==false){
							//是否查询已经隐藏的工单
							if(relationmain.getDeleted().intValue()==Integer.parseInt(ifQueryByHided)){
								TawSheetRelationForm relationForm=new TawSheetRelationForm();
								SheetBeanUtils.copyProperties(relationForm, tmpsheetRelation);
								relationForm.setCurrentFlowCnName(currentWorkflow.getRemark());
								fromList.add(relationForm);
								
								tmpNextResult.add(relationmain);
							}
						}
					}
				}
			}
		}
		if(toList.size()>0)
			request.setAttribute("TORELATIONLIST", toList);
		if(fromList.size()>0)
			request.setAttribute("FROMRELATIONLIST", fromList);
	}
	/**
	 * 工单隐藏
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performHide(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = StaticMethod.nullObject2String(request.getParameter("ids"));
		String rids = StaticMethod.nullObject2String(request.getParameter("rids"));
		String flowNames = StaticMethod.nullObject2String(request.getParameter("flowNames"));
		String isHide = StaticMethod.nullObject2String(request.getParameter("isHide"));
		String[] idsArr = ids.split(",");
		for(int i=0;i<idsArr.length;i++){
			BaseMain main = (BaseMain)this.getMainService().getSingleMainPO(idsArr[i]);
			main.setDeleted(new Integer(isHide));
			this.getMainService().saveOrUpdateMain(main);
		}
		//隐藏相关工单
		if(rids.equals("")==false){
			String[] ridsArr = rids.split(",");
			String[] flowNamesArr = flowNames.split(",");
			ITawSystemWorkflowManager fmgr=(ITawSystemWorkflowManager) (ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager"));
			for(int i=0;i<ridsArr.length;i++){
				TawSystemWorkflow workflow=fmgr.getTawSystemWorkflowByName(flowNamesArr[i]);
				String rBeanId=workflow.getMainServiceBeanId();
				IMainService rMainSerivce=(IMainService)(ApplicationContextHolder.getInstance().getBean(rBeanId));
				BaseMain main = (BaseMain)rMainSerivce.getSingleMainPO(ridsArr[i]);
				main.setDeleted(new Integer(isHide));
				rMainSerivce.saveOrUpdateMain(main);
			}
		}
	}
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#performBatchDeal(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void performBatchDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String taskIds = StaticMethod.nullObject2String(request
				.getParameter("taskIds"));
		String[] taskIdArray = taskIds.split(",");
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());

		String succesReturn = "";
		String faultReturn = "";
		HashMap tempColumMap=new HashMap();
		HashMap taskMap=new HashMap();
		for (int i = 0; i < taskIdArray.length; i++) {
		  String taskId = taskIdArray[i];
		  ITask task=null;
		  try {
		    task = this.getTaskService().getSinglePO(taskId);
			request.setAttribute("mainId", task.getSheetKey());
			HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
					response);
			tempColumMap.put(taskId, columnMap);
			if(task!=null)taskMap.put(taskId, task);
		    }catch(Exception e){
		      if(!faultReturn.equals("")){
					faultReturn += ",";
			  }
		      if(task!=null){
				faultReturn = faultReturn + task.getSheetId();
			  }else {
				faultReturn = faultReturn + taskId;
			  }
			  continue;
			}
		}
		
		Iterator iterator =taskMap.keySet().iterator();
		while(iterator.hasNext()){
		  ITask task =(ITask)taskMap.get(iterator.next().toString());
		  try {			   
		   String taskId=task.getId();
		   HashMap columnMap = (HashMap) tempColumMap.get(taskId);
		   Map map = request.getParameterMap();
		   map.put("aiid", task.getId());
		   map.put("preLinkId", task.getPreLinkId());
		   map.put("mainId", task.getSheetKey());
				map.put("sheetId", task.getSheetId());

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
					HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(
							tempMap, tempColumnMap);
					WpsMap.putAll(tempWpsMap);
				}
				setFlowEngineMap(WpsMap);
				dealFlowEngineMap(mapping, form, request, response);

				// finish task
				businessFlowService.completeHumanTask(taskId,
						getFlowEngineMap(), sessionMap);
				if(!succesReturn.equals("")){
					succesReturn += ",";
				}
				succesReturn = succesReturn + task.getSheetId();
			} catch (Exception e) {
				e.printStackTrace();
				if(!faultReturn.equals("")){
					faultReturn += ",";
				}
				faultReturn = faultReturn + task.getSheetId();
				continue;
			}	
			


		}
//		for (int i = 0; i < taskIdArray.length; i++) {
//			String taskId = taskIdArray[i];
//			ITask task = null;
//			try {
//				task =(ITask)taskMap.get(taskId);
//				request.setAttribute("mainId", task.getSheetKey());
//				HashMap columnMap = this.getInterfaceObjMap(mapping, form,
//						request, response);
//
//				Map map = request.getParameterMap();
//				map.put("aiid", task.getId());
//				map.put("preLinkId", task.getPreLinkId());
//				map.put("mainId", task.getSheetKey());
//				map.put("sheetId", task.getSheetId());
//
//				Map serializableMap = SheetUtils.serializableParemeterMap(map);
//				Iterator it = serializableMap.keySet().iterator();
//
//				HashMap WpsMap = new HashMap();
//				while (it.hasNext()) {
//					String mapKey = (String) it.next();
//					Map tempMap = (Map) serializableMap.get(mapKey);
//					if (taskId.equals("")) {
//						Object obj = tempMap.get("aiid");
//						if (obj.getClass().isArray()) {
//							Object[] obja = (Object[]) obj;
//							obj = obja[0];
//						}
//						taskId = StaticMethod.nullObject2String(obj);
//					}
//					HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
//					HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(
//							tempMap, tempColumnMap);
//					WpsMap.putAll(tempWpsMap);
//				}
//				setFlowEngineMap(WpsMap);
//				dealFlowEngineMap(mapping, form, request, response);
//
//				// finish task
//				businessFlowService.completeHumanTask(taskId,
//						getFlowEngineMap(), sessionMap);
//				if(!succesReturn.equals("")){
//					succesReturn += ",";
//				}
//				succesReturn = succesReturn + task.getSheetId();
//			} catch (Exception e) {
//				e.printStackTrace();
//				if(!faultReturn.equals("")){
//					faultReturn += ",";
//				}
//				if(task!=null){
//				faultReturn = faultReturn + task.getSheetId();
//				}else {
//					faultReturn = faultReturn + taskId;
//				}
//				continue;
//			}
//		}

		request.setAttribute("succesReturn", succesReturn);
		request.setAttribute("faultReturn", faultReturn);
	}
	
	/**
	 * @author yyk
	 *  根据历史工单生成一个新增工单页面
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showNewSendPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"));
		BocoLog.debug(this, "sheetKey==" + sheetKey);
		if (!sheetKey.equals("")) {
			
			BaseMain mainObject = (BaseMain) this.getMainService().getSingleMainPO(sheetKey);		

			BaseMain mainObjectNew = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
			//SheetBeanUtils.eomsDB2Model(mainObject, mainObjectNew);
			//SheetBeanUtils.copyProperties(mainObjectNew, mainObject);
			Map mainObjectMap = SheetBeanUtils.describe(mainObject);
			mainObjectMap = SheetUtils.clearCommonSheetData(mainObjectMap);			
			SheetBeanUtils.populateMap2Bean(mainObjectNew, mainObjectMap);
			mainObjectNew.setSheetAccessories("");
			String acceptLimit = StaticMethod.getLocalString(1);
			String completeLimit = StaticMethod.getLocalString(3);
			mainObjectNew.setSheetAcceptLimit(SheetUtils
					.stringToDate(acceptLimit));
			mainObjectNew.setSheetCompleteLimit(SheetUtils
					.stringToDate(completeLimit));
			mainObjectNew.setSendTime(StaticMethod.getLocalTime());
			request.setAttribute("status", Constants.SHEET_RUN);
			request.setAttribute("sheetMain", mainObjectNew);

		} else {
			throw new SheetException(this.getClass().getName()
					+ "工单主键为空,请联系管理员!");
		}

		

		request.setAttribute("mainId", sheetKey);

		String sheetPageName = StaticMethod.nullObject2String(request
				.getParameter("sheetPageName"), "");
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("methodBeanId", mapping.getAttribute());

	}	
	
	/**
	 * 调用接口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void invokeWfInterface(String mainBeanId, String sheetKey,
			String linkId, String linkBeanId, String interfaceType,
			String methodType, String sendType) {
		BocoLog.info(this, "invokeWfInterface mehtod start!!!!!!!!");
		if (interfaceType != null && !interfaceType.equals("")
				&& methodType != null && !methodType.equals("")) {
			BocoLog.info(this, "invokeWfInterface start!!!!!!!!");
			IWfInterfaceInfoManager wfInterfaceInfoManager = (IWfInterfaceInfoManager) ApplicationContextHolder
					.getInstance().getBean("iWfInterfaceInfoManager");
			try {
				wfInterfaceInfoManager.saveWfInterfaceInto(mainBeanId,
						linkBeanId, sheetKey, linkId, interfaceType,
						methodType, sendType);
				BocoLog.info(this, "invokeWfInterface end!!!!!!!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 取得会审人员
	 */
	public JSONArray getSubAuditPerformer(TawSystemSessionForm sessionform,
			String piid) throws Exception {
		ID2NameService service = (ID2NameService) ApplicationContextHolder
				.getInstance().getBean("ID2NameGetServiceCatch");
		// 从operate中取得会审角色
		HashMap sessionMap = new HashMap();
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		Map processOjbectAttribute = this.getProcessOjbectAttribute();
		String objectName = (processOjbectAttribute.get("objectName") == null ? ""
				: (String) processOjbectAttribute.get("objectName"));
		Map parameterValuemap = businessFlowService.getVariable(piid,
				objectName, sessionMap);
		processOjbectAttribute.remove("objectName");
		JSONArray sendUserAndRoles = new JSONArray();
		String temp = "";
		for (Iterator it = processOjbectAttribute.keySet().iterator(); it
				.hasNext();) {
			temp = it.next().toString();
			if (temp.equals("subAuditPerformer")) {
				String subAuditPerformer = temp.toString();
				String subAuditPerformerType = subAuditPerformer + "Type";
				String subAuditPerformerLeader = subAuditPerformer + "Leader";

				String subAuditPerformerValue = (String) parameterValuemap
						.get(subAuditPerformer);
				String subAuditPerformerTypeValue = (String) parameterValuemap
						.get(subAuditPerformerType);
				String subAuditPerformerLeaderValue = (String) parameterValuemap
						.get(subAuditPerformerLeader);
				if (subAuditPerformerValue != null
						&& !subAuditPerformerValue.equals("")) {
					String[] performerValues = subAuditPerformerValue
							.split(",");
					for (int i = 0; i < performerValues.length; i++) {
						JSONObject data = new JSONObject();
						String finalValue = performerValues[i];
						String finalTypeValue = subAuditPerformerTypeValue
								.split(",")[i];
						String finalLeaderValue = subAuditPerformerLeaderValue
								.split(",")[i];
						BocoLog.debug(this, "finalValueis==" + finalValue
								+ "finalLeaderValue =" + finalLeaderValue);
						data.put("id", finalValue);
						data.put("nodeType", finalTypeValue);
						data.put("categoryId", "dealPerformer");
						data.put("leaderId", finalLeaderValue);
						String tempname = service.id2Name(
								subAuditPerformerLeaderValue,
								"tawSystemUserDao");
						data.put("leaderName", tempname);
						sendUserAndRoles.put(data.toString());
					}
				}

			}
		}
		return sendUserAndRoles;

	}

	
	/**
	 * 显示未处理列表(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getAtomLists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 获取登陆信息，add by qinmin* */
		String userId = StaticMethod.nullObject2String(request
				.getParameter("userName"));
		ITawSystemUserManager userManager = (ITawSystemUserManager)  ApplicationContextHolder.getInstance().getBean("ItawSystemUserSaveManagerFlush");		
		TawSystemUser user = new TawSystemUser();
		user = userManager.getUserByuserid(userId);
		String deptId = StaticMethod.null2String(user.getDeptid());			
		// --------------用于分页，得到当前页号-------------
		final Integer pageIndex = new Integer(request.getParameter("curPage"));
		Integer pageSize = new Integer(request.getParameter("pageSize"));

		// 增加map对象，存放main，传入taskService.getUndoTask
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		HashMap taskListMap = null;
		if (pageSize != null && pageSize.intValue() != 0) {
			taskListMap = this.getTaskService().getUndoTask(condition, userId, deptId, this
					.getMainService().getFlowTemplateName(), pageIndex,
					pageSize);

		} else {
			System.out.println("===========" + pageSize);
			try {
				taskListMap = this.getTaskService().getAllUndoTask(condition, userId, deptId,
						this

						.getMainService().getFlowTemplateName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Feed feed = AtomUtilForTask.makeFeedByTask(taskListMap, request);

		OutputStream os = response.getOutputStream();
		PrintStream ps = new PrintStream(os);
		feed.getDocument().writeTo(ps);
	}
	/**
	 * 显示工单详细信息页面(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showAtomDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	}

	/**
	 * 申明任务(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performClaimTaskAtom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		String operateUserId = StaticMethod.nullObject2String(request
				.getParameter("userName"));
		ITask task = this.getTaskService().getSinglePO(taskId);
		String operateRoleId = task.getOperateRoleId();
		// 获取页面输入信息
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = request.getParameterMap();
		map.put("operateUserId", operateUserId);
		map.put("operateRoleId", operateRoleId);
		Map serializableMap = SheetUtils.serializableParemeterMap(map);
		serializableMap = SheetUtils.SetEncodingParemeterMap(serializableMap,
				"UTF-8");
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

		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		// TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		// .getSession().getAttribute("sessionform");
		sessionMap.put("userId", StaticMethod.nullObject2String(request
				.getParameter("userName")));
		sessionMap.put("password", StaticMethod.nullObject2String(request
				.getParameter("password")));
		businessFlowService.claimTask(taskId, getFlowEngineMap(), sessionMap);
		response.getWriter().write("true");

	}

	/**
	 * 处理任务(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performDealAtom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		String operateUserId = StaticMethod.nullObject2String(request
				.getParameter("userName"));
		ITask task = this.getTaskService().getSinglePO(taskId);
		String operateRoleId = task.getOperateRoleId();
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		// 获取页面输入信息
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = request.getParameterMap();
		map.put("operateUserId", operateUserId);
		map.put("operateRoleId", operateRoleId);
		Map serializableMap = SheetUtils.serializableParemeterMap(map);
		serializableMap = SheetUtils.SetEncodingParemeterMap(serializableMap,
				"UTF-8");
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

		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		// TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		// .getSession().getAttribute("sessionform");
		// sessionMap.put("userId", sessionform.getUserid());
		// sessionMap.put("password", sessionform.getPassword());
		sessionMap.put("userId", StaticMethod.nullObject2String(request
				.getParameter("userName")));
		sessionMap.put("password", StaticMethod.nullObject2String(request
				.getParameter("password")));
		/** add by qinmin* */
		// finish task
		businessFlowService.completeHumanTask(taskId, getFlowEngineMap(),
				sessionMap);
		response.getWriter().write("true");
	}

	/**
	 * 工单移交,包括同一角色内部移交，以及不同角色之间移交（Atom只支持人员移交，暂时没有使用）。
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performTransferAtom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		// TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		// .getSession().getAttribute("sessionform");
		// sessionMap.put("userId", sessionform.getUserid());
		// sessionMap.put("password", sessionform.getPassword());
		sessionMap.put("userId", StaticMethod.nullObject2String(request
				.getParameter("userName")));
		sessionMap.put("password", StaticMethod.nullObject2String(request
				.getParameter("password")));

		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"));

		if (sessionMap != null) {

			// 获取页面输入信息
			HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
					response);
			Map map = request.getParameterMap();
			Map serializableMap = SheetUtils.serializableParemeterMap(map);
			serializableMap = SheetUtils.SetEncodingParemeterMap(
					serializableMap, "UTF-8");
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

			/**
			 * modify by chenyuanshu 此处不处理，此处存在问题需要修改
			 */
			// 转移到目的执行用户ID，这里需要判断后台传递过来的是用户ID还是角色ID。
			HashMap operate = (HashMap) this.getFlowEngineMap().get("operate");

			String toOwnerUserId = StaticMethod.nullObject2String(operate
					.get("dealPerformerLeader"));
			String toOwnerRoleId = StaticMethod.nullObject2String(operate
					.get("dealPerformer"));
			String opeOrgType = StaticMethod.nullObject2String(operate
					.get("dealPerformerType"));

			// 获取当前操作用户ID以及角色ID
			String fromOwnerUserId = StaticMethod.nullObject2String(request
					.getParameter("userName"));
			HashMap link = (HashMap) this.getFlowEngineMap().get("link");
			String fromOwnerRoleId = StaticMethod.nullObject2String(link
					.get("operateRoleId"));
			// 修改link表中保存的下一步操作对象
			link.put("toOrgRoleId", toOwnerRoleId);
			this.getFlowEngineMap().put("link", link);

			System.out.println("toOwnerRoleId=" + toOwnerRoleId
					+ ";toOwnerUserId=" + toOwnerUserId + ";opeOrgType="
					+ opeOrgType + ";fromOwnerUserId=" + fromOwnerUserId
					+ ";fromOwnerRoleId=" + fromOwnerRoleId);
			// 工单转移
			businessFlowService.transferWorkItem(taskId, fromOwnerUserId,
					fromOwnerRoleId, toOwnerUserId, toOwnerRoleId, opeOrgType,
					this.getFlowEngineMap(), sessionMap);
		}
	}

	/**
	 * 执行阶段回复输入页面(ATOM)
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showPhaseReplyPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		System.out.println("=======>" + sheetKey);
		String aiid = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		String piid = StaticMethod.nullObject2String(request
				.getParameter("piid"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		System.out.println("taskName:" + taskName);
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"));
		String TKID = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");

		List toList = new ArrayList();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
				.getInstance().getBean("ID2NameGetServiceCatch");
		// boolean ifAddReceive = true;
		if (!preLinkId.equals(""))// 当preLinkId不为空时，则表示需要从派发给我的link中取出operateRoleId
		{
			// 阶段回复
			String linkClassName = this.getLinkService().getLinkObject()
					.getClass().getName();
			List linkList = this.getTaskService().getExceptMeTask(sheetKey,
					linkClassName, userId);
			for (int i = 0; linkList != null && i < linkList.size(); i++) {
				ITask task = (ITask) this.getTaskService().getTaskModelObject()
						.getClass().newInstance();
				Object[] obj = (Object[]) linkList.get(i);
				task.setOperateRoleId(StaticMethod.nullObject2String(obj[0]));
				task.setOperateType(StaticMethod.nullObject2String(obj[1]));
				task.setTaskOwner(StaticMethod.nullObject2String(obj[2]));
				task.setTaskDisplayName(StaticMethod.nullObject2String(obj[3]));

				if (task.getTaskDisplayName() != null
						&& (task.getTaskDisplayName().indexOf("草稿") == -1 && task
								.getTaskDisplayName().indexOf("驳回") == -1)) {
					// ifAddReceive = false;
					toList.add(task);
				}
			}
		}
		// if (preLinkId.equals("") || toList == null || toList.size() == 0)//
		// 当preLinkId为空时，则表示需要从main中取出sendRoleId
		// {
		// if (ifAddReceive == true) {
		ITask task = (ITask) this.getTaskService().getTaskModelObject()
				.getClass().newInstance();
		BaseMain mainObj = this.getMainService().getSingleMainPO(sheetKey);
		task.setOperateRoleId(mainObj.getSendRoleId());
		String name = service.id2Name(mainObj.getSendRoleId(),
				"tawSystemUserDao");
		if (name.equals("")) {
			task.setOperateType("subrole");
			task.setTaskOwner(mainObj.getSendRoleId());
		} else {
			task.setOperateType("user");
			task.setTaskOwner(mainObj.getSendUserId());
		}
		task.setTaskDisplayName("新增工单");
		toList.add(task);
		Feed feed = AtomUtilForTask.showPreOperateList(toList, request);
		OutputStream os = response.getOutputStream();
		PrintStream ps = new PrintStream(os);
		feed.getDocument().writeTo(ps);
	}

	/**
	 * 阶段回复(Atom)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performNonFlowAtom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BocoLog.info(this, "===优化======非流程动作===");
		// 获取页面输入信息
		String operateUserId = StaticMethod.nullObject2String(request
				.getParameter("userName"));
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		ITask task = this.getTaskService().getSinglePO(taskId);
		String operateRoleId = task.getOperateRoleId();
		String processTemplateName = StaticMethod.nullObject2String(request
				.getParameter("processTemplateName"));
		HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
				response);
		Map map = request.getParameterMap();
		map.put("operateUserId", operateUserId);
		map.put("operateRoleId", operateRoleId);
		Map serializableMap = SheetUtils.serializableParemeterMap(map);
		serializableMap = SheetUtils.SetEncodingParemeterMap(serializableMap,
				"UTF-8");

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
			newSaveNonFlowData(taskId, this.getFlowEngineMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().write("true");
	}

	/**
	 * 工单的详细信息页面(Atom)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showDetailPageAtom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// String sheetKey = StaticMethod.nullObject2String(request
		// .getParameter("sheetKey"), "");
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"), "");
		ITask itask = this.getTaskService().getSinglePO(taskId);
		String sheetKey = itask.getSheetKey();
		String taskName = itask.getTaskName();
		String piid = itask.getProcessId();
		String operateRoleId = itask.getOperateRoleId();
		String taskStatus = itask.getTaskStatus();
		String preLinkId = itask.getPreLinkId();
		String TKID = itask.getId();
		BocoLog.debug(this, "sheetKey==" + sheetKey);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		if (!sheetKey.equals("")) {
			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
			String parentSheetKey = StaticMethod.nullObject2String(mainObject
					.getParentSheetId());
			String parentBeanId = StaticMethod.nullObject2String(mainObject
					.getParentSheetName());
			System.out.println("parentSheetKey=" + parentSheetKey
					+ "parentBeanId=" + parentBeanId);
			if (!parentSheetKey.equals("") && !parentBeanId.equals("")) {
				IMainService parentMainSerivce = (IMainService) ApplicationContextHolder
						.getInstance().getBean(parentBeanId);
				BaseMain parentMain = parentMainSerivce
						.getSingleMainPO(parentSheetKey);
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

		// List list = getLinkService().getLinksByMainId(sheetKey);
		// add by leo 草稿需要传入mainId，以便于在wps中修改main对象（DB）
		HashMap sessionMap = new HashMap();
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		if (!TKID.equals("")
				&& (taskStatus.equals(Constants.TASK_STATUS_READY)
						|| taskStatus.equals(Constants.TASK_STATUS_RUNNING) || taskStatus
						.equals(Constants.TASK_STATUS_CLAIMED))) {
			ITask task = null;
			try {
				task = this.getTaskService().getSinglePO(TKID);
				String isWaitForSubTask = task.getIfWaitForSubTask();
				if (isWaitForSubTask.equals("true")) {
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

	}

	/**
	 * 显示未处理列表(portal)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getUndoListsForPortal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 获取登陆信息，add by qinmin* */
		String userId = StaticMethod.nullObject2String(request
				.getParameter("userName"));
		/**若无法从URL中获取userid，则从session中获取，modify by 秦敏 2009-08-12 begin*/
		if(userId.equals("")){
           TawSystemSessionForm sessionform = (TawSystemSessionForm) 
                     request.getSession().getAttribute("sessionform");
           userId=sessionform.getUserid();
		
		}
		ITawSystemUserManager userManager = (ITawSystemUserManager)  ApplicationContextHolder.getInstance().getBean("ItawSystemUserSaveManagerFlush");		
		TawSystemUser user = new TawSystemUser();
		user = userManager.getUserByuserid(userId);
		String deptId = StaticMethod.null2String(user.getDeptid());			
		// --------------用于分页，得到当前页号-------------
		final Integer pageIndex = new Integer(request.getParameter("curPage"));
		Integer pageSize = new Integer(request.getParameter("pageSize"));

		// 增加map对象，存放main，传入taskService.getUndoTask
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		HashMap taskListMap = null;
		if (pageSize != null && pageSize.intValue() != 0) {
			taskListMap = this.getTaskService().getUndoTask(condition, userId, deptId, this
					.getMainService().getFlowTemplateName(), pageIndex,
					pageSize);

		} else {
			System.out.println("===========" + pageSize);
			try {
				taskListMap = this.getTaskService().getAllUndoTask(condition, userId, deptId,
						this

						.getMainService().getFlowTemplateName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Integer overTimeTaskCount = this.getTaskService().getOverTimeTaskCount(condition, userId, deptId); 
		taskListMap.put("overTimeTaskCount", overTimeTaskCount);
		Feed feed = AtomUtilForTask.showUndoListForPortal(taskListMap, request);
		OutputStream os = response.getOutputStream();
		PrintStream ps = new PrintStream(os);
		feed.getDocument().writeTo(ps);
	}

	public void getDoneListsForPortal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 获取登陆信息，add by qinmin* */
		String userId = StaticMethod.nullObject2String(request
				.getParameter("userName"));
		ITawSystemUserManager userManager = (ITawSystemUserManager)  ApplicationContextHolder.getInstance().getBean("ItawSystemUserSaveManagerFlush");		
		TawSystemUser user = new TawSystemUser();
		user = userManager.getUserByuserid(userId);
		String deptId = StaticMethod.null2String(user.getDeptid());			
		// --------------用于分页，得到当前页号-------------
		final Integer pageIndex = new Integer(request.getParameter("curPage"));
		Integer pageSize = new Integer(request.getParameter("pageSize"));

		// 增加map对象，存放main，传入taskService.getUndoTask
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
	
		HashMap taskListMap = null;
		// if(pageSize!=null && pageSize.intValue() != 0){
		taskListMap = this.getTaskService().getDoneTask(condition, userId, deptId, this
				.getMainService().getFlowTemplateName(), pageIndex, pageSize);
		//		
		// }else {
		// System.out.println("===========" + pageSize);
		// try{
		// taskListMap = taskService.getAllDoneTask(condition,userId,"", this
		//	     
		// .getMainService().getFlowTemplateName());
		// }
		// catch(Exception e){
		// e.printStackTrace();
		// }
		// }
		Feed feed = AtomUtilForTask.showdoneListForPortal(taskListMap, request);
		OutputStream os = response.getOutputStream();
		PrintStream ps = new PrintStream(os);
		feed.getDocument().writeTo(ps);
	}
	/**
	 * 同组模式待处理列表（本角色已接单未处理工单） add by 秦敏
	 * 目前只有故障工单T1阶段实现此功能，所以写在故障工单私有类中，以后在扩展为base方法。
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public  void showUndoListForSameTeam(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	}
	
	/**
	 * LINK的提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newPerformDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("TKID"));
		String copyPerformer = StaticMethod.nullObject2String(request
				.getParameter("copyPerformer"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));

		this.performDeal(mapping, form, request, response);

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
	public void newPerformAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String copyPerformer = StaticMethod.nullObject2String(request
				.getParameter("copyPerformer"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		this.performAdd(mapping, form, request, response);
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
	}
	/**
	 * 显示待归档列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showUnHoldList(ActionMapping mapping, ActionForm form,
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
		String holdTaskName = StaticMethod.null2String(request
				.getParameter("holdTaskName"));
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("holdTaskName", holdTaskName);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		String flowName = this.getMainService().getFlowTemplateName();
		HashMap taskListOvertimeMap = this.getTaskService().getUnHoldTask(
				condition, userId, deptId, flowName, pageIndex, pageSize);
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
			HashMap columnMapOverTip = OvertimeTipUtil
					.getNotOverTimeColumnByMapping(flowName);
			// 循环为task超时标识赋值
			for (int i = 0; i < taskOvertimeList.size(); i++) {
				ITask tmptask = null;
				Map taskMap = new HashMap();
				Map tmptaskMap = new HashMap();
				HashMap conditionMap = new HashMap();
				if (columnMap.size() > 0) {
					Object[] tmpObjArr = (Object[]) taskOvertimeList.get(i);
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
				}
				taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
				taskMap.putAll(tmptaskMap);
				taskList.add(tmptask);
				taskMapList.add(taskMap);
			}
		}

		// 将分页后的列表写入页面供使用
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
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
	 * 获取待处理工单任务列表（不含过滤步骤的任务）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showUndoListByFilter(ActionMapping mapping, ActionForm form,
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
		String filterName = StaticMethod.null2String(request
				.getParameter("filterName"));
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("filterName", filterName);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		String flowName = this.getMainService().getFlowTemplateName();
		HashMap taskListOvertimeMap = this.getTaskService()
				.getUndoListByFilter(condition, userId, deptId, flowName,
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
			HashMap columnMapOverTip = OvertimeTipUtil
					.getNotOverTimeColumnByMapping(flowName);
			// 循环为task超时标识赋值
			for (int i = 0; i < taskOvertimeList.size(); i++) {
				ITask tmptask = null;
				Map taskMap = new HashMap();
				Map tmptaskMap = new HashMap();
				HashMap conditionMap = new HashMap();
				if (columnMap.size() > 0) {
					Object[] tmpObjArr = (Object[]) taskOvertimeList.get(i);
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
				}
				taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
				taskMap.putAll(tmptaskMap);
				taskList.add(tmptask);
				taskMapList.add(taskMap);
			}
		}

		// 将分页后的列表写入页面供使用
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
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
	 * 显示所有待处理任务列表(portal)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getUndoAllListsForPortal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/** 获取登陆信息，add by qinmin* */
		String userId = StaticMethod.nullObject2String(request
				.getParameter("userName"));
	
		
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		TawSystemUser user = new TawSystemUser();
		user = userManager.getUserByuserid(userId);
		String deptId = StaticMethod.null2String(user.getDeptid());
		// --------------用于分页，得到当前页号-------------
		final Integer pageIndex = new Integer(request.getParameter("curPage"));
		Integer pageSize = new Integer(request.getParameter("pageSize"));

		// 增加map对象，存放main，传入taskService.getUndoTask
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		HashMap taskListMap = null;
		if (pageSize != null && pageSize.intValue() != 0) {
			taskListMap = this.getTaskService().getUndoAllSheetTask(condition,
					userId, deptId, "", pageIndex, pageSize);

		} else {
			System.out.println("===========" + pageSize);
			try {
				taskListMap = this.getTaskService().getAllUndoAllSheetTask(
						condition, userId, deptId, "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Feed feed = AtomUtilForTask.showUndoAllSheetListForPortal(taskListMap,
				request);
		OutputStream os = response.getOutputStream();
		PrintStream ps = new PrintStream(os);
		feed.getDocument().writeTo(ps);
	}
	
	
	
	
	/**
	 * 显示所有待处理任务列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getUndoAllLists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/** 获取登陆信息，add by qinmin* */
		String userId = StaticMethod.nullObject2String(request
				.getParameter("userName"));
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		TawSystemUser user = new TawSystemUser();
		String deptId = "";

		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 当前页数
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request
				.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
				.getParameter(pageIndexName)) - 1));
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		userId = sessionform.getUserid();
		deptId = sessionform.getDeptid();

		String exportType = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}

		// 增加map对象，存放main，传入taskService.getUndoTask
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		HashMap taskListMap = null;
		// if (!userId.equals("")pageSize != null && pageSize.intValue() != 0) {
		taskListMap = this.getTaskService().getUndoAllSheetTask(condition,
				userId, deptId, "", pageIndex, pageSize);
		int total = ((Integer) taskListMap.get("taskTotal")).intValue();
		request.setAttribute("total", new Integer(total));
		request.setAttribute("pageSize", pageSize);

		// } else {
		// try {
		// taskListMap = this.getTaskService().getAllUndoAllSheetTask(
		// condition, userId, deptId, "");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }

		// Feed feed =
		// AtomUtilForTask.showUndoAllSheetListForPortal(taskListMap, request);
		// OutputStream os = response.getOutputStream();
		// PrintStream ps = new PrintStream(os);
		// feed.getDocument().writeTo(ps);

		List tasList = (List) taskListMap.get("taskList");
		request.setAttribute("taskList", tasList);
	}
	
	
	
	/**
	 * 显示全流程实例图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb
	 * @since 2008-09-09
	 */
	public void shoWholeWorkFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String mainId = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");

		String dictSheetName = StaticMethod.nullObject2String(request
				.getParameter("dictSheetName"), "");
		String description = StaticMethod.nullObject2String(request
				.getParameter("description"), "");
		String linkServiceName = StaticMethod.nullObject2String(request
				.getParameter("linkServiceName"), "");
		StringBuffer prelinkCondition = new StringBuffer();
		prelinkCondition.append("(");

		// 该环节下的所有任务(不包括回复中的任务,草稿,审批环节)
		String taskCondition = " sheetkey = '" + mainId + "' and taskName <> '" + Constants.WHOLE_TASK_REPLY + "' and taskName <> 'DraftHumTask' and taskName <> 'TaskCreateAuditHumTask' and taskName <> 'TaskCompleteAuditHumTask' and taskName <> 'ByRejectHumTask' and taskName <> 'cc' ";
		BaseMain baseMain = (BaseMain)this.getMainService().getSingleMainPO(mainId);
		List allTiTaskList = this.getTaskService().getTasksByCondition(taskCondition);
		
		for (Iterator it = allTiTaskList.iterator(); it.hasNext(); ) {
			ITask task = (ITask)it.next();
			if (prelinkCondition.length() > 1) {
				prelinkCondition.append(" or ");
				
			}
			if (task.getPreLinkId() != null && !task.getPreLinkId().equals("")) {
				prelinkCondition.append("id = '").append(task.getPreLinkId()).append("'");
			}
			
		}
		prelinkCondition.append(")");
		
		List preLinks = new ArrayList();
		if (prelinkCondition.length() > 2) { 
			preLinks = this.getLinkService().getLinksBycondition(prelinkCondition.toString(), ((BaseLink)getLinkService().getLinkObject()).getClass().getName());
		}

		//该环节下的回复中的任务 
		String replyCondition = " sheetkey = '" + mainId + "' and taskName = '" + Constants.WHOLE_TASK_REPLY + "'";
		List allReplyTaskList = this.getTaskService().getTasksByCondition(replyCondition);
		
		//确认link表
		String confirmCondition = " mainId = '" + mainId + "' and operateType =" + Constants.ACTION_ACCEPT;
		List confirmLinks = this.getLinkService().getLinksBycondition(confirmCondition, ((BaseLink)getLinkService().getLinkObject()).getClass().getName());
		
		//驳回link表
		//String rejectCondition = " mainId = '" + mainId + "' and operateType =" + Constants.ACTION_REJECTDONE;
		//List rejectLinks = this.getLinkService().getLinksBycondition(rejectCondition, ((BaseLink)getLinkService().getLinkObject()).getClass().getName());
		
		WholeGraph graph = new WholeGraph(linkServiceName, description, dictSheetName, baseMain);
		//主要是针对目前新工单操作
		if (allTiTaskList.size() > 0) { 
			ITask itask = (ITask)allTiTaskList.get(0);
			if(itask.getParentProcessName() != null && !itask.getParentProcessName().equals("")) {
				String[] vml = graph.draw(allTiTaskList, preLinks, allReplyTaskList, confirmLinks);
				request.setAttribute("vml", vml);
				request.setAttribute("module", mapping.getPath());
			}
		}
		
	}
	
	/**
	 * 显示全流程步骤说细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb
	 * @since 2008-09-09
	 */
	public void getAllWorkflowStepInfoPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		String aiid = StaticMethod.nullObject2String(request.getParameter("aiid"), "");
		String condition = " aiid='" + aiid + "'";
		if (aiid.equals("0")) {
			String mainId = StaticMethod.nullObject2String(request.getParameter("mainId"), "");
			condition = " mainId = '" + mainId + "' and (aiid = '' or aiid is null) ";
		}
		String linkName = (getLinkService().getLinkObject().getClass()).getName();
		
		List links = this.getLinkService().getLinksBycondition(condition, linkName);
		
		String module = mapping.getPath();
		module = module.substring(1, module.length());
		
		request.setAttribute("module", module);
		request.setAttribute("HISTORY", links);
	}
	/**
	 * 显示延期申请列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deferAppList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap filterMap = new HashMap();
		filterMap.put("PROCESS_INSTANCE.TEMPLATE_NAME", getMainService()
				.getFlowTemplateName());
		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());

		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String order = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_ORDER)));
		String sort = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_SORT)));
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
		String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject().getClass().newInstance();
		
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		
		HashMap taskListMap = this.getTaskService().deferAppList(condition,userId, deptId,pageIndex, pageSize);
		
		int total = ((Integer) taskListMap.get("taskTotal")).intValue();
		List taskList = (List) taskListMap.get("taskList");

		request.setAttribute("taskList", taskList);
		request.setAttribute("total", new Integer(total));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "mainlist");
		request.setAttribute("module", mapping.getPath().substring(1));
	}
	/**
	 * 通用任务的批量处理
	 */
	public void showBatchDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskIds = StaticMethod.nullObject2String(request.getParameter("taskIds"), "");
		String batchType = StaticMethod.nullObject2String(request.getParameter("batchType"), "");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String operateUserId = sessionform.getUserid();
		String operateDetpId = sessionform.getDeptid();
		String contactMobile = sessionform.getContactMobile();

		
		String[] taskIdsArray = taskIds.split(",");
		if (taskIdsArray.length == 0) return;
		if (batchType.equals("")) return;
		
		//batchType为sameSheet表示为同一工单，同一环节的情况处理
		if (batchType.equals("sameSheet")) {
			//找出BaseMain
			String taskId = taskIdsArray[0];
			ITask taskObject = this.getTaskService().getSinglePO(taskId);
			BaseMain baseMain = this.getMainService().getMainBySheetId(taskObject.getSheetId());
			
			
			//找出需要处理的task任务列表
			String taskIdSql = taskIds.replace(",","','");
			taskIdSql = "'" + taskIdSql + "'";
			String condition = "id in (" + taskIdSql + ")";
			List tasks = this.getTaskService().getTasksByCondition(condition);
			
			//从tasks列表里找出每条tasks的上一条处理信息
			StringBuilder prelinkIds = new StringBuilder();
			for (Iterator it = tasks.iterator(); it.hasNext(); ) {
				ITask task = (ITask)it.next();
				if (prelinkIds.length() > 0) {
					prelinkIds.append(","); 
				}
				prelinkIds.append("'").append(task.getPreLinkId()).append("'");
			}
			String linkcondition = "id in (" + prelinkIds.toString() + ")";
			List preLinks = this.getLinkService().getLinksBycondition(linkcondition, ((BaseLink)getLinkService().getLinkObject()).getClass().getName());
			
			//将所有的link放入到map里
			Map prelinkMap = new HashMap();
			for (Iterator linkit = preLinks.iterator(); linkit.hasNext(); ) {
				BaseLink link = (BaseLink) linkit.next();
				prelinkMap.put(link.getId(), link);
			}
						
			String	sheetKey = baseMain.getId();
			String 	taskName = taskObject.getTaskName();
			String 	piid = taskObject.getProcessId();
						
			request.setAttribute("taskName", taskName);
			request.setAttribute("piid", piid);
			request.setAttribute("methodBeanId", mapping.getAttribute());
			request.setAttribute("mainId", sheetKey);
			request.setAttribute("taskIds", taskIds);
			request.setAttribute("sheetMain", baseMain);
			request.setAttribute("prelinkMap", prelinkMap);
			request.setAttribute("tasks", tasks);
		} else if (batchType.equals("differSheet")) {
			//TODO 做不同工单，同一操作
		}

		request.setAttribute("operateUserId", operateUserId);
		request.setAttribute("contactMobile", contactMobile);
		request.setAttribute("operateDetpId", operateDetpId);
	}
	
	/**
	 * 同组处理模式已处理列表（本角色其他人员已经处理完成工单） add by 秦敏 20090907
	 * 目前只有故障工单T1阶段实现此功能，所以写在故障工单私有类中，以后在扩展为base方法。
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public  void showDoneListForSameTeam(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	}
	/**
	 * 列表中列出由登录人员处理的已归档的工单
	 */
	public void showHoldedListForUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();

		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
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
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject()
		.getClass().newInstance();		
		String beanName = mapping.getAttribute();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		condition.put("beanName", beanName);		
		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		int[] aTotal = { 0 };
		List result = this.getMainService().getHoldedListForUser(condition, pageIndex, pageSize, aTotal, userId, deptId);
		Integer total = new Integer(aTotal[0]);
		List holdedList = new ArrayList();
        String[] variables= QuerySqlInit.getAllDictItemsName(beanName).split(",");		
		for (int i = 0; result != null && i < result.size(); i++) {
			Object[] objectList  = (Object[]) result.get(i);
			Map ListMap = new HashMap();
			for(int j= 0;j < objectList.length; j++){
				String variablesKey = variables[j];
				if(variablesKey.indexOf("main.")>=0 || variablesKey.indexOf("task.")>=0){
					variablesKey = variablesKey.substring(5);
				}
				ListMap.put(variablesKey, objectList[j]);			
			}			
			holdedList.add(ListMap);
		}
		request.setAttribute("taskList", holdedList);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "holdlist");
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
	}
	
}
