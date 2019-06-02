/*
 * Created on 2007-11-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util.flowdefine.xml;


/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WorkFlowRule {
	private FlowDefine flowDefine[];

	/**
	 * @return Returns the flowDefine.
	 */
	public FlowDefine[] getFlowDefine() {
		return flowDefine;
	}

	/**
	 * @param flowDefine
	 *            The flowDefine to set.
	 */
	public void setFlowDefine(FlowDefine[] flowDefine) {
		this.flowDefine = flowDefine;
	}

	private String name;

	private String description;

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public FlowDefine getFlowDefineById(String id) {
		FlowDefine flow = new FlowDefine();
		if (this.getFlowDefine() != null && this.getFlowDefine().length > 0) {
			for (int i = 0; i < this.getFlowDefine().length; i++) {
				if((this.getFlowDefine()[i]).getId().equals(id)){
					flow = this.getFlowDefine()[i];
					break;
				}
			}
		}
		return flow;
	}
}