
package com.boco.eoms.sheet.securitydeal.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

public  class SecurityDealMethod extends BaseSheet {
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
		System.out.println("operateName is -----------------------"
				+ operatName);
		try{
			if (operatName.equals("forceHold")) {
				HashMap map = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("id"));
				if(sheetKey.equals("")){
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				}
				//BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				map.put("main", main);
				map.put("link", getLinkService().getLinkObject().getClass().newInstance());
				map.put("operate", getPageColumnName());
				hashMap.put("selfSheet", map);
			} else if (taskName.equals("")) {
				// 新增工单
				HashMap sheetMap = new HashMap();
				sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else if (taskName.equals("DraftTask")||taskName.equals("reject")) {
				// 草稿状态
				HashMap sheetMap = new HashMap();
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("mainId"));
				if(sheetKey.equals("")){
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				}
				//BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else if(taskName.equals("advice")||taskName.equals("reply")){
			    HashMap sheetMap = new HashMap();
			    sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
		   }else if (taskName.equals("MakeTask")||
					taskName.equals("AuditTask")||
					taskName.equals("PerformTask")||
					taskName.equals("HoldTask")) {
				// 待归档
				String sheetKey = StaticMethod.nullObject2String(request
						.getParameter("mainId"));
				if(sheetKey.equals("")){
					sheetKey = StaticMethod.nullObject2String(request
							.getParameter("sheetKey"));
				}
				// System.out.println("==$$$$==sheetKey==="+sheetKey);
				HashMap sheetMap = new HashMap();
				//BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				sheetMap.put("main", main);
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
				sheetMap.put("operate", getPageColumnName());
				hashMap.put("selfSheet", sheetMap);
			} else {
				// 其他人工处理
				HashMap sheetMap = new HashMap();
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
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.dealFlowEngineMap(mapping, form, request, response);
		String roleId =StaticMethod.nullObject2String(request
				.getParameter("roleId"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request
				.getParameter("phaseId"));
		HashMap sheetMap = this.getFlowEngineMap();
		
		this.setFlowEngineMap(sheetMap);
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
 		mainAttachmentAttributes.add("sheetAccessories");

 		List linkAttachmentAttributes = new ArrayList();
 		linkAttachmentAttributes.add("nodeAccessories");
 			
 		objectMap.put("mainObject", mainAttachmentAttributes);
 		objectMap.put("linkObject", linkAttachmentAttributes);
 		return objectMap;
}

     
     
     public void showForceHoldPage(ActionMapping mapping, ActionForm form,
 			HttpServletRequest request, HttpServletResponse response)
 			throws Exception {
 		super.showForceHoldPage(mapping, form, request, response);
 		//需要回调外部流程
 			String sheetKey = StaticMethod.nullObject2String(request
 					.getParameter("sheetKey"), "");
 			BaseMain main=this.getMainService().getSingleMainPO(sheetKey);
 			String parentSheetName=main.getParentSheetName();
 		    String parentSheetKey=main.getParentSheetId();
 		    if(parentSheetName!=null&&!parentSheetName.equals("")){
 		    IMainService parentMainService = (IMainService) ApplicationContextHolder
 			                        .getInstance().getBean(parentSheetName);
 		    BaseMain parentMain=parentMainService.getSingleMainPO(parentSheetKey);
 		    String parentPhaseName = main.getParentPhaseName();
 		    
 		    if(parentPhaseName.indexOf("@")!=-1){
 		    	request.setAttribute("parentPiid", parentPhaseName.substring(parentPhaseName.indexOf("@")+1));
 		    	System.out.println("回调：parentProcessId："+parentPhaseName.substring(parentPhaseName.indexOf("@")+1));
 			 }else{
 				 request.setAttribute("parentPiid",parentMain.getPiid());
 			 }
 		    request.setAttribute("parentMain", parentMain);
 		    request.setAttribute("parentProcessName", parentSheetName);
 		    }
 	}
     
     
     public void showInputDealPage(ActionMapping mapping, ActionForm form,
 			HttpServletRequest request, HttpServletResponse response)
 			throws Exception {
 		super.showInputDealPage(mapping, form, request, response);
 		//////////驳回////////////
 		// 取上条TASK
 		String taskName = StaticMethod.nullObject2String(request
 				.getParameter("activeTemplateId"));
 		if(taskName.equals("")){
 			taskName = StaticMethod.nullObject2String(request
 					.getParameter("taskName"));
 		}
 		String operateType = StaticMethod.nullObject2String(request
 				.getParameter("operateType"));
 		//碰到需要驳回操作的节点时，去取上一个Task记录
 		if (taskName.equals("MakeTask") || taskName.equals("AuditTask") ||taskName.equals("PerformTask") || taskName.equals("HoldTask")) {
 			//下面这个方法写在base类中，会取出4个值，传递到inputdealpage页面中
 			super.setParentTaskOperateWhenRejct(request);
 		}
 		//////////驳回////////////
 		
 	    //需要调用外部流程         
         if(taskName.equals("PerformTask")){
         	String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
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
              
 		if(taskName.equals("HoldTask")){
 				String sheetKey = StaticMethod.nullObject2String(request
 						.getParameter("sheetKey"), "");
 				BaseMain main=this.getMainService().getSingleMainPO(sheetKey);
 				String parentSheetName=main.getParentSheetName();
 			    String parentSheetKey=main.getParentSheetId();
 			    if(parentSheetName!=null&&!parentSheetName.equals("")){
 			    IMainService parentMainService = (IMainService) ApplicationContextHolder
 				                        .getInstance().getBean(parentSheetName);
 			    BaseMain parentMain=parentMainService.getSingleMainPO(parentSheetKey);

 			    String parentPhaseName = main.getParentPhaseName();
 			    
 			    if(parentPhaseName.indexOf("@")!=-1){
 			    	request.setAttribute("parentPiid", parentPhaseName.substring(parentPhaseName.indexOf("@")+1));
 			    	System.out.println("回调：parentProcessId："+parentPhaseName.substring(parentPhaseName.indexOf("@")+1));
 				 }else{
 					 request.setAttribute("parentPiid",parentMain.getPiid());
 				 }
 			    
 			    request.setAttribute("parentMain", parentMain);
 			    request.setAttribute("parentProcessName", parentSheetName);
 				System.out.println("securitydeal 执行了回调 ===========");
 			    }
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
	    //查询工单互调表
		if(task != null){
			ITawSheetRelationManager mgr = (ITawSheetRelationManager)ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
			List relationAllList = mgr.getSheetByProcessIdAndUserId(sheetKey, taskName, tempUserId, task.getProcessId());
			System.out.println("sheetKey="+sheetKey+"==tempUserId="+tempUserId);
			if(relationAllList != null && relationAllList.size()>0 && !operateType.equals("92")){
				return true;
			}else{
			    return false;
			}
		}else{
			return false;
		}
		
	}
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
  		if(taskName.equals("PerformTask")&&operateType.equals("111")){
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
  		    		o.put("text", "请选择你要调用的流程！");
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

	public String getSheetAttachCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map initMap(Map map, List attach, String type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
