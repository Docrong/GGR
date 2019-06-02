/**
 * 
 */
package com.boco.eoms.commons.log.model;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 11, 2008 2:19:17 PM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class LogConfig implements ILogConfig {
	
	private String modelId;
	
	private String modelName;
	
	private String operId;
	
	private String operName;
	
	private String operDesc;
	
	private String leaf ;
	
	private String status ;

	/**
	 * @return the modelId
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * @param modelId the modelId to set
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the operDesc
	 */
	public String getOperDesc() {
		return operDesc;
	}

	/**
	 * @param operDesc the operDesc to set
	 */
	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}

	/**
	 * @return the operId
	 */
	public String getOperId() {
		return operId;
	}

	/**
	 * @param operId the operId to set
	 */
	public void setOperId(String operId) {
		this.operId = operId;
	}

	/**
	 * @return the operName
	 */
	public String getOperName() {
		return operName;
	}

	/**
	 * @param operName the operName to set
	 */
	public void setOperName(String operName) {
		this.operName = operName;
	}
	
	public String getLeaf() {
		return leaf;
	}

	/**
	 * @param operName the operName to set
	 */
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	
	public String getStatus() {
		return status;
	}

	/**
	 * @param operName the operName to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
