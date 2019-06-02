/*
 * Created on 2007-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util.flowdefine.xml;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FlowDefineExplain {
	private String path;
	private String currentPhaseId;
	private String flowId;
	private FlowDefine flowDefine;
	
	
	public FlowDefineExplain() {
	}
	
	public FlowDefineExplain(String flowId, String path) {
		try {
			WorkFlowRule rule = FlowDefineSchema.getInstance().loadXml(path);
			this.flowDefine = rule.getFlowDefineById(flowId);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return Returns the flowDefine.
	 */
	public FlowDefine getFlowDefine() {
		return flowDefine;
	}

	/**
	 * @return Returns the currentPhaseId.
	 */
	public String getCurrentPhaseId() {
		return currentPhaseId;
	}
	/**
	 * @param currentPhaseId The currentPhaseId to set.
	 */
	public void setCurrentPhaseId(String currentPhaseId) {
		this.currentPhaseId = currentPhaseId;
	}
	/**
	 * @return Returns the flowId.
	 */
	public String getFlowId() {
		return flowId;
	}
	/**
	 * @param flowId The flowId to set.
	 */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	/**
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	
	public List explain(){
		List list = new ArrayList();
		try {
			WorkFlowRule rule = FlowDefineSchema.getInstance().loadXml();
			this.flowDefine = rule.getFlowDefineById(this.getFlowId());			
			PhaseId phaseId = this.flowDefine.getPhasesByPhaseId(this.getCurrentPhaseId());
			list = phaseId.getToPhasesByPhaseId();
			for (int i=0;list!=null && i<list.size();i++){
				ToPhaseId toPhaseId = (ToPhaseId)list.get(i);
				toPhaseId.getRole();
				toPhaseId.getName();
				toPhaseId.getCondition();
				toPhaseId.getOperatetype();
				toPhaseId.getStepdisplay();
				toPhaseId.getWorkflowdisplay();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List explain(String path){
		List list = new ArrayList();
		try {
			WorkFlowRule rule = FlowDefineSchema.getInstance().loadXml(path);
			this.flowDefine = rule.getFlowDefineById(this.getFlowId());			
			PhaseId phaseId = this.flowDefine.getPhasesByPhaseId(this.getCurrentPhaseId());
			list = phaseId.getToPhasesByPhaseId();
			for (int i=0;list!=null && i<list.size();i++){
				ToPhaseId toPhaseId = (ToPhaseId)list.get(i);
				toPhaseId.getRole();
				toPhaseId.getName();
				toPhaseId.getCondition();
				toPhaseId.getOperatetype();
				toPhaseId.getStepdisplay();
				toPhaseId.getWorkflowdisplay();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}