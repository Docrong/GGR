package com.boco.eoms.sheet.tool.relation.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;
import com.boco.eoms.sheet.tool.relation.webapp.form.TawSheetRelationForm;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;

public class TawSheetRelationAction extends BaseAction {
	
	public ActionForward showInvokeRelationShipList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List fromRelationList=new ArrayList();
		List toRelationList=new ArrayList();
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");	
		ITawSheetRelationManager mgr = (ITawSheetRelationManager) getBean("ITawSheetRelationManager");	
		ITawSystemWorkflowManager fmgr=(ITawSystemWorkflowManager)getBean("ITawSystemWorkflowManager");	
	    //查询我调用的工单
	    List fromList = mgr.getRelationSheetByParentId(sheetKey);	
	    for(int i=0;i<fromList.size();i++){
			   TawSheetRelationForm relationForm=new TawSheetRelationForm();
			   TawSheetRelation relation=(TawSheetRelation)fromList.get(i);
			   SheetBeanUtils.copyProperties(relationForm, relation);
			       
			   String currentProcessName=relation.getCurrentFlowName();
			   String parentProcessName=relation.getParentFlowName(); 
			   String taskName=relation.getTaskName();
			   TawSystemWorkflow currentWorkflow=fmgr.getTawSystemWorkflowByName(currentProcessName);
			   TawSystemWorkflow parentWorkflow=fmgr.getTawSystemWorkflowByName(parentProcessName);
			   String pfBeanId=parentWorkflow.getMainServiceBeanId();
			   IMainService pfMainSerivce=(IMainService)getBean(pfBeanId);   
			       		       
			   FlowDefineExplain flowDefineExplain = new FlowDefineExplain(pfMainSerivce.getFlowTemplateName(),
					   pfMainSerivce.getRoleConfigPath());
			   FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
			   System.out.println("flowDefine is ======"+flowDefine.getId());
			   PhaseId phaseId=flowDefine.getPhasesByPhaseId(taskName);
			       	       
			   relationForm.setCurrentFlowCnName(currentWorkflow.getRemark());
			   relationForm.setParentFlowCnName(parentWorkflow.getRemark());
			   relationForm.setTaskCnName(phaseId.getName());
			   
			   fromRelationList.add(relationForm);
		  }
	    
        //查询调用我的工单
		TawSheetRelation sheetRelation = mgr.getRelationSheetByCurrentId(sheetKey);			
		if(sheetRelation!=null){
			TawSheetRelationForm relationForm=new TawSheetRelationForm();
			   
			   SheetBeanUtils.copyProperties(relationForm, sheetRelation);			       
			   String currentProcessName=sheetRelation.getCurrentFlowName();
			   String parentProcessName=sheetRelation.getParentFlowName(); 
			   String taskName=sheetRelation.getTaskName();
			   TawSystemWorkflow currentWorkflow=fmgr.getTawSystemWorkflowByName(currentProcessName);
			   TawSystemWorkflow parentWorkflow=fmgr.getTawSystemWorkflowByName(parentProcessName);
			   String pfBeanId=parentWorkflow.getMainServiceBeanId();
			   IMainService pfMainSerivce=(IMainService)getBean(pfBeanId);   
			       		       
			   FlowDefineExplain flowDefineExplain = new FlowDefineExplain(pfMainSerivce.getFlowTemplateName(),
					   pfMainSerivce.getRoleConfigPath());
			   FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
			   PhaseId phaseId=flowDefine.getPhasesByPhaseId(taskName);
			       	       
			   relationForm.setCurrentFlowCnName(currentWorkflow.getRemark());
			   relationForm.setParentFlowCnName(parentWorkflow.getRemark());
			   relationForm.setTaskCnName(phaseId.getName());
			   
			   toRelationList.add(relationForm);	
		}		
		request.setAttribute("FROMRELATIONLIST", fromRelationList);	
		request.setAttribute("TORELATIONLIST", toRelationList);	
		return mapping.findForward("showInvokeRelationShipList");
	}	
}
