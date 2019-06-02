/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.businesspilot.webapp.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.webapp.form.TawCommonsAccessoriesForm;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink;
import com.boco.eoms.sheet.businesspilot.model.BusinessPilotMain;
import com.boco.eoms.sheet.businesspilot.service.IBusinessPilotMainManager;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

/**
 * @author 
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class BusinessPilotSheetMethod extends BaseSheet {

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
		String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
		System.out.println("operateName is -----------------------"+operatName);
		if(operatName.equals("forceHold") ){
			try{
				HashMap map = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
				if(sheetKey == null ||sheetKey.equals("")){
					sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
				}
				//BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				map.put("main",main);
				map.put("link", getLinkService().getLinkObject().getClass().newInstance());
				map.put("operate", getPageColumnName());
				hashMap.put("selfSheet",map);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if (taskName.equals("")) {
			//新增工单
			try{
				HashMap sheetMap = new HashMap();
				sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(taskName.equals("draft")||taskName.equals("reject")){
			//草稿状态
			try{
				HashMap sheetMap = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
				if(sheetKey == null ||sheetKey.equals("")){
					sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
				}
				//BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		} else if (taskName.equals("hold")) {
			try{
				//待归档
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
				
				HashMap sheetMap = new HashMap();
				//BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			}catch(Exception e){
				e.printStackTrace();
				
			}
			
		} else if(taskName.equals("advice")||taskName.equals("reply")||taskName.equals("cc")){
			try{
				HashMap sheetMap = new HashMap();
				sheetMap.put("link", getLinkService().getLinkObject());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else {
			try{
				System.out.println("其他人工处理.......");
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
				
				HashMap sheetMap = new HashMap();
				//BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			}catch(Exception e){
				e.printStackTrace();
			}
            
		}
		return hashMap;
	}

	/**
	 * 主要是实现角色的自动匹配。
	 */
	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	super.dealFlowEngineMap(mapping, form, request, response);
    	String roleId =StaticMethod.nullObject2String(request.getParameter("roleId"));
    	String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
       System.out.println("activeTemplateId="+taskName);
       HashMap sheetMap = this.getFlowEngineMap();
		if(taskName.equals("reply") || taskName.equals("advice"))
		{   
			Map link = (HashMap) sheetMap.get("link");
			link.put("id", "");
			sheetMap.put("link", link);
			
		}
		this.setFlowEngineMap(sheetMap);	 

	}
	public void showDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		super.showDealPage(mapping, form, request, response);
		
       //需要调用外部流程
		/**
		 * String taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
		if(taskName.equals("pilot")){
			String sheetKey = StaticMethod.nullObject2String(request
					.getParameter("sheetKey"));
			
			ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSheetRelationManager");	
			List relationAllList=rmgr.getAllSheetByParentIdAndPhaseId(sheetKey, taskName);
			if(relationAllList != null){
              for(int i=0;i<relationAllList.size();i++){
            	  TawSheetRelation relation=(TawSheetRelation)relationAllList.get(i);
            	  int state=relation.getInvokeState();
            	  if(state==Constants.INVOKE_STATE_RUNNING){
            		  request.setAttribute("ifInvoke", "no");	
            		  break;
            	  }
            	  request.setAttribute("ifInvoke", "yes");  
              }				
			}else{
				request.setAttribute("ifInvoke", "no");			
			}	
		}*/
		
	}
	public void showInputDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showInputDealPage(mapping, form, request, response);
		BusinessPilotLink link =(BusinessPilotLink) request.getAttribute("preLink");
		
		// add by yangyankuang
		String operateRoleId = StaticMethod.nullObject2String(request
				.getParameter("operateRoleId"));

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemSubRoleManager");
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
		if (taskName.equals("pilot")||taskName.equals("strategy")
				|| taskName.equals("prepare")
				|| taskName.equals("run")|| taskName.equals("report")|| taskName.equals("check")|| taskName.equals("note")) {
			super.setParentTaskOperateWhenRejct(request);
		}
		
		//需要调用外部流程
		if(taskName.equals("pilot")){
			String sheetKey = StaticMethod.nullObject2String(request
					.getParameter("sheetKey"));
			
			ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSheetRelationManager");	
			List relationAllList=rmgr.getAllSheetByParentIdAndPhaseId(sheetKey, taskName);
			if(relationAllList != null){
              for(int i=0;i<relationAllList.size();i++){
            	  TawSheetRelation relation=(TawSheetRelation)relationAllList.get(i);
            	  int state=relation.getInvokeState();
            	  if(state==Constants.INVOKE_STATE_RUNNING){
            		  request.setAttribute("ifInvoke", "no");	
            		  break;
            	  }
            	  request.setAttribute("ifInvoke", "yes");  
              }				
			}else{
				request.setAttribute("ifInvoke", "no");			
			}	
		}
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
		mainAttachmentAttributes.add("sheetAccessories");
		mainAttachmentAttributes.add("mainAcc");
		
		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");
		linkAttachmentAttributes.add("linkOperateDesc");
		linkAttachmentAttributes.add("linkPilotScheme");
		linkAttachmentAttributes.add("linkTGPolicyAcc");
		linkAttachmentAttributes.add("linkMeetingAcc");
		linkAttachmentAttributes.add("linkRunReport");
		linkAttachmentAttributes.add("linkConsult");
		linkAttachmentAttributes.add("linkSummarizeAcc");
		
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
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
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		if(taskName.equals("")){
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
		}
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		if(taskName.equals("pilot")&&operateType.equals("111")){
			//查询工单互调表
		    	ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
					.getInstance().getBean("ITawSheetRelationManager");
		    	List relationAllList=rmgr.getRunningSheetByParentIdAndPhaseId(sheetKey, taskName);
		    	//如果已经调用了其他工单则继续执行父类的performPreCommit方法,否则返回status为-1
		    	if(relationAllList != null&&relationAllList.size()>0){
		    		super.performPreCommit(mapping, form, request, response);
		    	}else{
		    		JSONArray data = new JSONArray();
		    		JSONObject o = new JSONObject();
		    		o.put("text", "请调用其他流程！");
		    		data.put(o);
		    		JSONObject jsonRoot = new JSONObject();
		    		jsonRoot.put("data",data);
		    		jsonRoot.put("status", "2");
					JSONUtil.print(response, jsonRoot.toString());
		    	}
		}else{
			super.performPreCommit(mapping, form, request, response);
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
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		//已经调用了其他流程
		//查询工单互调表
    	ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
			.getInstance().getBean("ITawSheetRelationManager");
    	List relationAllList=rmgr.getRunningSheetByParentId(sheetKey);
    	if(relationAllList != null&&relationAllList.size()>0){
    		request.setAttribute("invoke", "true");
    	}else{
    		request.setAttribute("invoke", "false");
    	}
	}
	public boolean performIsInvokeProcess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String tempUserId = "";
		System.out.println("opetateType==========="+operateType);
		if(taskName.equals("")){
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
		}
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		if (sessionform != null) {
			tempUserId = sessionform.getUserid();
		}
	    //查询工单互调表
		ITawSheetRelationManager mgr = (ITawSheetRelationManager)ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
		List relationAllList = mgr.getRunningSheetByPidAndPhaseIdAndUserId(sheetKey, taskName, tempUserId);
		System.out.println("sheetKey==========="+sheetKey+"==tempUserId===="+tempUserId);
		if(relationAllList != null && relationAllList.size()>0 && !operateType.equals("1")){
			return true;
		}else{
		    return false;
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
