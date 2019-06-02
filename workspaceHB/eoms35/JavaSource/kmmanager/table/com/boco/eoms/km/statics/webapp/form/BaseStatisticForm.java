package com.boco.eoms.km.statics.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识库统计
 * </p>
 * <p>
 * Description:知识库统计
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 * 
 */
public class BaseStatisticForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * 知识库
	 *
	 */
	private java.lang.String baseName;
   
	public void setBaseName(java.lang.String baseName){
		this.baseName= baseName;       
	}
   
	public java.lang.String getBaseName(){
		return this.baseName;
	}

	/**
	 *
	 * 创建人
	 *
	 */
	private java.lang.String userName;
   
	public void setUserName(java.lang.String userName){
		this.userName= userName;       
	}
   
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *
	 * 有效知识总数
	 *
	 */
	private java.lang.Long availableCount;
   
	public void setAvailableCount(java.lang.Long availableCount){
		this.availableCount= availableCount;       
	}
   
	public java.lang.Long getAvailableCount(){
		return this.availableCount;
	}

	/**
	 *
	 * 失效知识总数
	 *
	 */
	private java.lang.Long invalidCount;
   
	public void setInvalidCount(java.lang.Long invalidCount){
		this.invalidCount= invalidCount;       
	}
   
	public java.lang.Long getInvalidCount(){
		return this.invalidCount;
	}

	/**
	 *
	 * 删除知识总数
	 *
	 */
	private java.lang.Long deleteCount;
   
	public void setDeleteCount(java.lang.Long deleteCount){
		this.deleteCount= deleteCount;       
	}
   
	public java.lang.Long getDeleteCount(){
		return this.deleteCount;
	}

	/**
	 *
	 * 被使用总数
	 *
	 */
	private java.lang.Long utilizationCount;
   
	public void setUtilizationCount(java.lang.Long utilizationCount){
		this.utilizationCount= utilizationCount;       
	}
   
	public java.lang.Long getUtilizationCount(){
		return this.utilizationCount;
	}

	/**
	 *
	 * 知识使用率
	 *
	 */
	private java.lang.String utilizationRate;
   
	public void setUtilizationRate(java.lang.String utilizationRate){
		this.utilizationRate= utilizationRate;       
	}
   
	public java.lang.String getUtilizationRate(){
		return this.utilizationRate;
	}

	/**
	 *
	 * 被阅读次数
	 *
	 */
	private java.lang.Long readCount;
   
	public void setReadCount(java.lang.Long readCount){
		this.readCount= readCount;       
	}
   
	public java.lang.Long getReadCount(){
		return this.readCount;
	}

	/**
	 *
	 * 被引用次数
	 *
	 */
	private java.lang.Long usedCount;
   
	public void setUsedCount(java.lang.Long usedCount){
		this.usedCount= usedCount;       
	}
   
	public java.lang.Long getUsedCount(){
		return this.usedCount;
	}

	/**
	 *
	 * 知识利用率
	 *
	 */
	private java.lang.String usedRate;
   
	public void setUsedRate(java.lang.String usedRate){
		this.usedRate= usedRate;       
	}
   
	public java.lang.String getUsedRate(){
		return this.usedRate;
	}

	/**
	 *
	 * 推荐知识
	 *
	 */
	private java.lang.Long recommendCount;
   
	public void setRecommendCount(java.lang.Long recommendCount){
		this.recommendCount= recommendCount;       
	}
   
	public java.lang.Long getRecommendCount(){
		return this.recommendCount;
	}

}