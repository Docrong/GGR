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
 * Date:Jun 11, 2008 1:36:47 PM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public interface ILogConfig {
	
	/**
	 * @return the modelId
	 */
	public String getModelId();

	/**
	 * @param modelId the modelId to set
	 */
	public void setModelId(String modelId);

	/**
	 * @return the modelName
	 */
	public String getModelName();

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName);

	/**
	 * @return the operDesc
	 */
	public String getOperDesc();

	/**
	 * @param operDesc the operDesc to set
	 */
	public void setOperDesc(String operDesc);

	/**
	 * @return the operId
	 */
	public String getOperId();

	/**
	 * @param operId the operId to set
	 */
	public void setOperId(String operId);

	/**
	 * @return the operName
	 */
	public String getOperName();

	/**
	 * @param operName the operName to set
	 */
	public void setOperName(String operName);
	
	/**
	 * @return the operName
	 */
	public String getLeaf();

	/**
	 * @param operName the operName to set
	 */
	public void setLeaf(String leaf);
	
	
	public String getStatus() ;

	/**
	 * @param operName the operName to set
	 */
	public void setStatus(String status) ;
}
