
package com.boco.eoms.sheet.itsoftchange.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.itrequirement.model.ITRequirementMain;
import com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeMain;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

public  class ITSoftChangeMethod extends BaseSheet {
   /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        HashMap hashMap = new HashMap();
    	String taskName = StaticMethod.nullObject2String(request
    			.getParameter("activeTemplateId"));
    	String operatName = StaticMethod.nullObject2String(request
				.getParameter("operateName"));
    	try{
    	if (operatName.equals("forceHold")) {
			HashMap map = new HashMap();
			String sheetKey = StaticMethod.nullObject2String(request
					.getParameter("id"));
			if(sheetKey == null ||sheetKey.equals("")){
				sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			  }
			//BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
			BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
			map.put("main", main);
			map.put("link", getLinkService().getLinkObject());
			map.put("operate", getPageColumnName());
			hashMap.put("selfSheet", map);
		}else if (taskName.equals("")) {

    		HashMap sheetMap = new HashMap();

    		sheetMap.put("main", this.getMainService().getMainObject().getClass().newInstance());
    		sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
    		sheetMap.put("operate", getPageColumnName());
    		hashMap.put("selfSheet", sheetMap);
    	}
	   else if(taskName.equals("cc")||taskName.equals("advice")||taskName.equals("reply"))
	   {
		    HashMap sheetMap = new HashMap();
			sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
			sheetMap.put("operate", getPageColumnName());
			hashMap.put("selfSheet", sheetMap);
	   }
	   else {
    		HashMap sheetMap = new HashMap();
    		String sheetKey = StaticMethod.nullObject2String(request
					.getParameter("mainId"));
    		if(sheetKey.equals(""))
    		{
        		 sheetKey = StaticMethod.nullObject2String(request
    					.getParameter("sheetKey"));
    		}
    		BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
    		sheetMap.put("main", main);
    		sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
    		sheetMap.put("operate", getPageColumnName());
    		hashMap.put("selfSheet", sheetMap);

    	}
		}catch(Exception e){
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
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// TODO Auto-generated method stub
    	super.dealFlowEngineMap(mapping, form, request, response);
    	String roleId =StaticMethod.nullObject2String(request.getParameter("roleId"));
    	String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
    	String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		
		//如果是审核不通过则将驳回次数加1		
		if(taskName.equals("TestTask")&& operateType.equals("922")){
			HashMap sheetMap = this.getFlowEngineMap();
			Map main = (HashMap) sheetMap.get("main");			
			int rejectTime=StaticMethod.nullObject2int(main.get("mainRejectTimes"),-1);
			if(rejectTime>-1){
			    Integer mainRejectTimes = new Integer(rejectTime+1);
				main.put("mainRejectTimes", mainRejectTimes);
				sheetMap.put("main", main);
				this.setFlowEngineMap(sheetMap);
			}			
		}
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
		if(taskName.equals("")){
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
		}
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		if (taskName.equals("OperateTask") || taskName.equals("TestTask")|| taskName.equals("OnlineTask")||taskName.equals("HoldTask")) {
			super.setParentTaskOperateWhenRejct(request);
		}
	}       

	public Map getAttachmentAttributeOfOjbect() {
		
		Map objectMap = new HashMap();
		
		List mainAttachmentAttributes = new ArrayList();
		mainAttachmentAttributes.add("sheetAccessories");
		mainAttachmentAttributes.add("mainChangeDetail");
		
		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");
		linkAttachmentAttributes.add("linkTestGuide");
		linkAttachmentAttributes.add("linkTestNote");	
		linkAttachmentAttributes.add("linkUserNoteDesc");
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		
		
		return objectMap;
	}

	  public Map getProcessOjbectAttribute() {
	     	Map  attributeMap = new HashMap();
	     	attributeMap.put("dealPerformer","dealPerformer");
	        attributeMap.put("copyPerformer","copyPerformer");
	     	attributeMap.put("auditPerformer","auditPerformer");
	     	attributeMap.put("subAuditPerformer","subAuditPerformer");
	     	attributeMap.put("objectName", "operate");
	  		return attributeMap;	
	 	}
	
	public void showInputNewSheetPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showInputNewSheetPage(mapping, form, request, response);
		String parentCorrelation = StaticMethod.nullObject2String(request
				.getParameter("parentCorrelation"), "");
		String parentSheetId = StaticMethod.nullObject2String(request
				.getParameter("parentSheetId"), "");
		String parentSheetName = StaticMethod.nullObject2String(request
				.getParameter("parentSheetName"), "");
		String parentPhaseName = StaticMethod.nullObject2String(request
				.getParameter("parentPhaseName"), "");
		if (!parentSheetId.equals("") && !parentSheetName.equals("")) {
			IMainService parentMainSerivce = (IMainService) ApplicationContextHolder
					.getInstance().getBean(parentSheetName);
			BaseMain parentMain = parentMainSerivce.getSingleMainPO(parentSheetId);
			String tmpparentSheetId = parentMain.getSheetId();
			ITRequirementMain itrequirementmain = (ITRequirementMain)parentMain;
			String tmpmainNetSystem = itrequirementmain.getMainNetSystem();
			String tmpmainRequirementDesc = itrequirementmain.getMainRequirementDesc();
			String tmpsendUserId = itrequirementmain.getSendUserId();
			ITawSystemWorkflowManager mgr = (ITawSystemWorkflowManager) ApplicationContextHolder
			.getInstance().getBean("ITawSystemWorkflowManager");
			TawSystemWorkflow workflow = mgr
					.getTawSystemWorkflowByBeanId(parentSheetName);
			String parentProcessName = workflow.getName();
			
			request.setAttribute("parentProcessName",parentProcessName);
			request.setAttribute("parentSheetId", tmpparentSheetId);
			request.setAttribute("mainNetSystem",tmpmainNetSystem);
			request.setAttribute("mainRequirementDesc", tmpmainRequirementDesc);
			request.setAttribute("tmpsendUserId",tmpsendUserId);
			ITSoftChangeMain sheetMain = (ITSoftChangeMain)request.getAttribute("sheetMain");
			sheetMain.setMainApplyer(tmpsendUserId);
			request.setAttribute("sheetMain", sheetMain);
			//将IT需求申请工单的建单角色作为需求开发工单新增操作时的默认抄送对象，add by qinmin 2009-05-25
			String parentSendRoleId=itrequirementmain.getSendRoleId();
			JSONArray copySubRole = new JSONArray();
			JSONObject data = new JSONObject();
			data.put("id", parentSendRoleId);
			data.put("nodeType", "subrole");
			data.put("categoryId", "copyPerformer");
			data.put("leaderId", tmpsendUserId);
			ITawSystemUserManager service = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
			String name = service.id2Name(tmpsendUserId);
			data.put("leaderName", name);
			copySubRole.put(data.toString());
			request.setAttribute("copyUserAndRoles", copySubRole);		
					
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

	
//	public void showNewSheetPage(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//		super.showNewSheetPage(mapping, form, request, response);
//		String parentSheetId = StaticMethod.nullObject2String(request
//				.getParameter("parentSheetId"), "");
//    	ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
//		.getInstance().getBean("ITawSheetRelationManager");
//	     List relationAllList=rmgr.getRelationSheetByParentId(parentSheetId);
//	     if(relationAllList != null&&relationAllList.size()>0){
//	    	 request.setAttribute("count",  new Integer(relationAllList.size()));
//	     }else 
//	    	 request.setAttribute("count",new Integer(0));
//
//	}
}
