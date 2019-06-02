package com.boco.eoms.sheet.focusresourceerrata.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.commons.util.xml.XmlManage;
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
import com.boco.eoms.sheet.focusresourceerrata.service.IFocusResourceErrataLinkManager;

/**
 * <p>
 * Title:集客资源勘误
 * </p>
 * <p>
 * Description:集客资源勘误
 * </p>
 * <p>
 * Thu May 10 09:23:09 CST 2018
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public class FocusResourceErrataMethod extends BaseSheet  {
     
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
		
		//业务类型
		String mainBusinessType = StaticMethod.nullObject2String(main.get("mainBusinessType"));
		//电路核查失败所属地市
		String mainFailCity = StaticMethod.nullObject2String(main.get("mainFailCity"));
		Map sheetMap1 = new HashMap();
		sheetMap1.put("sheetKey", StaticMethod.nullObject2String(main.get("id")));
		sheetMap1.put("sheetId", StaticMethod.nullObject2String(main.get("sheetId")));
		sheetMap1.put("title", StaticMethod.nullObject2String(main.get("title")));
		sheetMap1.put("timeLine", "crm");
		FocusResourceErrataAction action = new FocusResourceErrataAction();
		String subRoleId = "";
		
		if("105".equals(operateType) || "106".equals(operateType)){
//			调用综资接口核查电路状态
			String mainCheckState = "0";//接口返回值
			sheetMap1.put("mainCheckState", mainCheckState);
			
			if("0".equals(mainCheckState)){
				//自动归档
				if("105".equals(operateType)){
					sheetMap1.put("operateType", "105");
					action.createT2linkData(sheetMap1, request);
				}else{
					sheetMap1.put("operateType", "106");
					action.createT3linkData(sheetMap1, request);
				}
				
				sheetMap1.put("operateType", "102");
				action.createT1Data(sheetMap1);
				action.createHoldTaskData(sheetMap1);
				operate.put("phaseId", "Over");
			}else{
				if("4".equals(mainBusinessType) || "5".equals(mainBusinessType) || "2004".equals(mainBusinessType)){//业务类型  跨省、跨市专线
					//此时工单流转到 传输网部审核(T2)
					subRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T2.subRoleId"));
					//T1自动生成
					sheetMap1.put("operateType", "103");
					action.createT1Data(sheetMap1);
					operate.put("phaseId", "TransmissionNet");
				}else{
					//工单流转到 地市勘误(T3)
					ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
					List subRoleList = mgr.getTawSystemSubRoles(" roleid= '1004' and deptid='"+mainFailCity+"'");
					if(subRoleList != null && subRoleList.size()>0){
						TawSystemSubRole subrole =(TawSystemSubRole) subRoleList.get(0);
						subRoleId = subrole.getId();
						sheetMap1.put("dealPerformer", subRoleId);
						sheetMap1.put("operateType", "103");
						action.createT1Data(sheetMap1);
						sheetMap1.put("operateType", "104");
						action.createT2Data(sheetMap1);
					}
					operate.put("phaseId", "CitieErrata");
				}
			}
		}
		
		System.out.println("subRoleId==="+subRoleId);
		if(!"".equals(subRoleId)){
			operate.put("dealPerformer", subRoleId);
			operate.put("dealPerformerLeader", subRoleId);
			operate.put("dealPerformerType", "subrole");
		}
		
		sheetMap.put("link", link);
		sheetMap.put("operate", operate);

		this.setFlowEngineMap(sheetMap);
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
			IFocusResourceErrataLinkManager service = (IFocusResourceErrataLinkManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataLinkManager");
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
		String acceptLimit = StaticMethod.getLocalString(6);
		String completeLimit = StaticMethod.getLocalString(7);
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
 
 }