/*
 * Created on 2007-11-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.util;

import java.util.List;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RoleMapping {
	private String modelId;
	private String modelName;
	private String condition;
	private String filePath;
	private List roleFilters;
	
	/**
	 * @return Returns the roleFilters.
	 */
	public RoleFilter[] getRoleFilters() {
		int size = roleFilters.size();
		RoleFilter[] mapping = new RoleFilter[size];
		for(int i=0;i<size;i++){
			mapping[i] = (RoleFilter)roleFilters.get(i);
		}
		return mapping;
	}
	/**
	 * @param roleFilters The roleFilters to set.
	 */
	public void setRoleFilters(List roleFilters) {
		this.roleFilters = roleFilters;
	}
	/**
	 * @return Returns the modelName.
	 */
	public String getModelName() {
		return modelName;
	}
	/**
	 * @param modelName The modelName to set.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	/**
	 * @return Returns the condition.
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition The condition to set.
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @return Returns the filePath.
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath The filePath to set.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return Returns the modelId.
	 */
	public String getModelId() {
		return modelId;
	}
	/**
	 * @param modelId The modelId to set.
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
}
