
package com.boco.eoms.sheet.emergencydrill.webapp.action;

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
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;

public  class EmergencyDrillMethod extends BaseSheet {
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
	    HashMap sheetMap = this.getFlowEngineMap();

       //     如果是审批不通过则将驳回次数加1
		if(taskName.equals("AuditSummaryTask")&& operateType.equals("952")){
			Map main = (HashMap) sheetMap.get("main");
			Integer mainRejectTimes = (Integer)main.get("mainRejectTimes");
			mainRejectTimes = new Integer(mainRejectTimes.intValue()+1);
			main.put("mainRejectTimes", mainRejectTimes);
			sheetMap.put("main", main);
			this.setFlowEngineMap(sheetMap);
		}
     }
     public Map getParameterMap() {
		// TODO Auto-generated method stub
		return this.getParameterMap();
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
     
 	
 	public Map getAttachmentAttributeOfOjbect() {
 		Map objectMap = new HashMap();
 		
 		List mainAttachmentAttributes = new ArrayList();
 		mainAttachmentAttributes.add("mainEmergencyPlan");
 		
 		List linkAttachmentAttributes = new ArrayList();
 		linkAttachmentAttributes.add("linkEmergencyDrill");
 		linkAttachmentAttributes.add("linkDrillReport");	
		linkAttachmentAttributes.add("linkDrillSummaryReport");	
		linkAttachmentAttributes.add("linkEmergencyPreparedness");	
 		objectMap.put("mainObject", mainAttachmentAttributes);
 		objectMap.put("linkObject", linkAttachmentAttributes);
 		
 		return objectMap;
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
		if (taskName.equals("MakeProgramTask") || taskName.equals("AuditProgramTask")|| taskName.equals("ImplementProgramTask")|| 
			taskName.equals("SubmitSummaryTask")||taskName.equals("AuditSummaryTask")||taskName.equals("AmendmentProgramTask")||
			taskName.equals("HoldTask")) {
			super.setParentTaskOperateWhenRejct(request);
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
