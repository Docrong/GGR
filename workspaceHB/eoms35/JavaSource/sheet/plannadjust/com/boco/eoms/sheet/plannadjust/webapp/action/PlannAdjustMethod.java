package com.boco.eoms.sheet.plannadjust.webapp.action;

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
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.prm.service.impl.Pojo2PojoServiceImpl;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.plannadjust.model.PlannAdjustLink;
import com.boco.eoms.sheet.plannadjust.model.PlannAdjustMain;
import com.boco.eoms.sheet.plannadjust.model.PlannAdjustTask;
import com.boco.eoms.sheet.plannadjust.service.IPlannAdjustLinkManager;
import com.boco.eoms.sheet.plannadjust.service.IPlannAdjustTaskManager;

/**
 * <p>
 * Title:规划站址调整申请流程
 * </p>
 * <p>
 * Description:规划站址调整申请流程
 * </p>
 * <p>
 * Sat Jun 08 11:16:10 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class PlannAdjustMethod extends BaseSheet  {
     
     public String getPageColumnName() {
		
		return super.getPageColumnName()+"gatherPerformer@java.lang.String,gatherPerformerLeader@java.lang.String,"
		+ "gatherPerformerType@java.lang.String,gatherPhaseId@java.lang.String,";
		
	}
     
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception   {
        
        HashMap hashMap = new HashMap();

    	HashMap sheetMap = new HashMap();
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
		BaseMain main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
		if(!sheetKey.equals("")){
			sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			
		} 
		if(!sheetKey.equals("")){
			main = this.getMainService().getSingleMainPO(sheetKey);
		} 
		sheetMap.put("main", main);	
		sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
		sheetMap.put("operate", getPageColumnName());
		hashMap.put("selfSheet",sheetMap);
    	
    	return hashMap;
    }
    /**
     * 提交流程引擎前作最后一次参数处理
     */
     public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	super.dealFlowEngineMap(mapping, form, request, response);
    	
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
		HashMap sheetMap = this.getFlowEngineMap();
		
		Map main = (HashMap) sheetMap.get("main");
		Map operate = (HashMap)sheetMap.get("operate");
		Map link = (HashMap) sheetMap.get("link");
		
		String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");

		if(taskName.equals("reply") || taskName.equals("advice"))
		{   			
			link.put("id", "");			
		}
		
		if(dealperformers.length>=1){
			
			String corrKey = "";
			String tmp = "";
			for(int i=0;i<dealperformers.length;i++){
				tmp =  UUIDHexGenerator.getInstance().getID();
				if(dealperformers.length == 1){
					corrKey = tmp;
				}else{
					if(corrKey.equals("")){
						corrKey = tmp;
					}else{
						corrKey = corrKey + "," + tmp;	
					}
					
				}
			}
			System.out.println("corrKey"+corrKey);
			operate.put("extendKey1", corrKey);
			
		}
		
		if((null == main.get("title") || "".equals(main.get("title"))) || ("3".equals(operateType) && "DraftTask".equals(taskName))){
			String territorialBranch = StaticMethod.nullObject2String(main.get("territorialBranch"));
			ITawSystemDeptManager itawSystemDeptManager = (ITawSystemDeptManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemDeptManager");
			TawSystemDept tawSystemDept = itawSystemDeptManager.getDeptinfobydeptid(territorialBranch,"0");
			String deptName = StaticMethod.nullObject2String(tawSystemDept.getDeptName());
			String planningNumber = StaticMethod.nullObject2String(main.get("planningNumber"));
			String title = deptName + planningNumber + "站址调整工单";
			main.put("title", title);
			sheetMap.put("main", main);
		}
		
		if (("0".equals(operateType) && "ConfirmTask".equals(phaseId)) || ("3".equals(operateType) && "DraftTask".equals(taskName))) {
			operate.put("phaseId", "AuditTask");
			operate.put("dealPerformer", "8a9982cb3f267a55013f26816997001a");
			operate.put("dealPerformerLeader", "8a9982cb3f267a55013f26816997001a");
			operate.put("dealPerformerType", "subrole");
			Date operateTime = (Date)link.get("operateTime");
			String prelinkId = StaticMethod.nullObject2String(link.get("id"));
			createT1Link(main,operateTime,prelinkId);
			createT1Task(main,prelinkId);
		}
		
		sheetMap.put("link", link);
		sheetMap.put("operate", operate);

		this.setFlowEngineMap(sheetMap);
    }
     
  	public static PlannAdjustLink createT1Link(Map mainrule, Date operateTime, String prelinkId)
	throws Exception
	{
  		IPlannAdjustLinkManager linkservice = (IPlannAdjustLinkManager)ApplicationContextHolder.getInstance().getBean("iPlannAdjustLinkManager");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(operateTime);
		calendar.add(13, 10);
		PlannAdjustLink T1link61 = (PlannAdjustLink)linkservice.getLinkObject().getClass().newInstance();
		T1link61.setId(UUIDHexGenerator.getInstance().getID());
		T1link61.setOperateType(new Integer("61"));
		T1link61.setActiveTemplateId("ConfirmTask");
		T1link61.setOperateTime(calendar.getTime());
		T1link61.setOperateDay(calendar.get(5));
		T1link61.setOperateMonth(calendar.get(2) + 1);
		T1link61.setOperateYear(calendar.get(1));
		T1link61.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
		T1link61.setToOrgRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
		T1link61.setPreLinkId(prelinkId);
		T1link61.setNodeAccessories("");
		T1link61.setToOrgType(new Integer(0));
		T1link61.setCompleteFlag(new Integer(0));
		T1link61.setOperateUserId(StaticMethod.nullObject2String(mainrule.get("sendUserId")));
		T1link61.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
		T1link61.setOperateDeptId(StaticMethod.nullObject2String(mainrule.get("sendDeptId")));
		T1link61.setOperaterContact(StaticMethod.nullObject2String(mainrule.get("sendContact")));
		T1link61.setTemplateFlag(0);
		linkservice.addLink(T1link61);
		calendar.add(13, 10);
		PlannAdjustLink T1link = (PlannAdjustLink)linkservice.getLinkObject().getClass().newInstance();
		T1link.setId(UUIDHexGenerator.getInstance().getID());
		T1link.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
		T1link.setOperateType(new Integer(102));
		T1link.setOperateTime(calendar.getTime());
		T1link.setOperateDay(calendar.get(5));
		T1link.setOperateMonth(calendar.get(2) + 1);
		T1link.setOperateYear(calendar.get(1));
		T1link.setAcceptFlag(new Integer(0));
		T1link.setPreLinkId(prelinkId);
		T1link.setActiveTemplateId("ConfirmTask");
		T1link.setToOrgType(new Integer(0));
		T1link.setCompleteFlag(new Integer(0));
		T1link.setOperateUserId(StaticMethod.nullObject2String(mainrule.get("sendUserId")));
		T1link.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
		T1link.setOperateDeptId(StaticMethod.nullObject2String(mainrule.get("sendDeptId")));
		String correlationKey = UUIDHexGenerator.getInstance().getID();
		T1link.setCorrelationKey(correlationKey);
		T1link.setTemplateFlag(0);
		T1link.setOperaterContact(StaticMethod.nullObject2String(mainrule.get("sendContact")));
		T1link.setPiid(StaticMethod.nullObject2String(mainrule.get("piid")));
		T1link.setToOrgRoleId("8a9982cb3f267a55013f26816997001a");
		linkservice.addLink(T1link);
		return T1link;
	}
 	
	public static void createT1Task(Map mainrule, String preLinkId)
	throws Exception
	{
		IPlannAdjustTaskManager taskservice = (IPlannAdjustTaskManager)ApplicationContextHolder.getInstance().getBean("iPlannAdjustTaskManager");
		PlannAdjustTask T1Task = (PlannAdjustTask)taskservice.getTaskModelObject().getClass().newInstance();
		try
		{
			T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
		}
		catch (Exception e3)
		{
			e3.printStackTrace();
		}
		T1Task.setTaskName("ConfirmTask");
		T1Task.setTaskDisplayName("统一受理");
		T1Task.setFlowName("PlannAdjust");
		T1Task.setSendTime(StaticMethod.nullObject2Timestamp(mainrule.get("sendTime")));
		T1Task.setSheetKey(StaticMethod.nullObject2String(mainrule.get("id")));
		T1Task.setTaskStatus("5");
		T1Task.setSheetId(StaticMethod.nullObject2String(mainrule.get("sheetId")));
		T1Task.setTitle(StaticMethod.nullObject2String(mainrule.get("title")));
		T1Task.setOperateType("subrole");
		T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
		T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
		T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
		T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
		T1Task.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
		T1Task.setTaskOwner(StaticMethod.nullObject2String(mainrule.get("sendUserId")));
		T1Task.setIfWaitForSubTask("false");
		T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
		T1Task.setPreLinkId(StaticMethod.nullObject2String(preLinkId));
		taskservice.addTask(T1Task);
	}
    
    
    /**
     * 设置需要从流程引擎中取的派往对象，包括派发，抄送，送审
     */
    public Map getProcessOjbectAttribute() {
     	Map  attributeMap = new HashMap();
     	attributeMap.put("dealPerformer","dealPerformer");
        attributeMap.put("copyPerformer","copyPerformer");
     	attributeMap.put("auditPerformer","auditPerformer");
     	attributeMap.put("subAuditPerformer","subAuditPerformer");
     	attributeMap.put("objectName", "operate");
  		return attributeMap;	
 	}
 	
	public Map getParameterMap() {
		Map  attributeMap = new HashMap();
		return attributeMap;
	} 
    
    /**
     * 设置main和link保存附件字段属性
     */
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
	 /**
     * 进入处理环节
     */
	public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        super.showInputDealPage(mapping, form, request, response);
   		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
        //驳回上一级（不是移交的任务），需要取出上一级的角色和phaseId
        if (operateType.equals("4")) {
			BaseLink  prelink = this.getLinkService().getSingleLinkPO(preLinkId);
			if (prelink != null) {
				request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
				request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
			}
		}
		//如果是移交后的任务被驳回 
		if (operateType.equals("4")) {
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			IPlannAdjustLinkManager service = (IPlannAdjustLinkManager)ApplicationContextHolder.getInstance().getBean("iPlannAdjustLinkManager");
			String taskId = StaticMethod.nullObject2String(request.getParameter("taskId"));
			String condition = " mainId='" + sheetKey + "' and operateType=8 and aiid='" + taskId + "' order by operateTime desc";
			List linkList = service.getLinksBycondition(condition);
			if (linkList != null && linkList.size() > 0) {
				BaseLink prelink = (BaseLink)linkList.get(0);
				request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
				request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
			}
		}
		
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
		PlannAdjustMain mainObject = (PlannAdjustMain) this.getMainService().getMainObject()
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
		
		ITawSystemUserManager itawSystemUserManager = (ITawSystemUserManager) ApplicationContextHolder
		.getInstance().getBean("itawSystemUserManager");
		TawSystemUser tawSystemUser =itawSystemUserManager.getTawSystemUser(sessionform.getId());
		String centerid = StaticMethod.nullObject2String(tawSystemUser.getOperuserid(), "");
		mainObject.setTerritorialBranch(centerid);
		mainObject.setApplicationTime(StaticMethod.getLocalTime());
		request.setAttribute("sendUserName", sendUserName);
		request.setAttribute("sendDeptName", sendDeptName);
		request.setAttribute("sendRoleName", sendRoleName);
		request.setAttribute("sheetMain", mainObject);
		request.setAttribute("sheetPageName", sheetPageName);
		request.setAttribute("status", Constants.SHEET_RUN);
		request.setAttribute("sendOrgType", UIConstants.NODETYPE_SUBROLE);
		request.setAttribute("methodBeanId", mapping.getAttribute());

	}
	
 }