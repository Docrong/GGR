package com.boco.eoms.km.exam.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:题型信息表
 * </p>
 * <p>
 * Description:题型信息表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 * 
 */
public class KmExamTestTypeForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	

	/**
	 *
	 * 主键
	 *
	 */
	private java.lang.String testTypeId;
   
	public void setTestTypeId(java.lang.String testTypeId){
		this.testTypeId= testTypeId;       
	}
   
	public java.lang.String getTestTypeId(){
		return this.testTypeId;
	}

	/**
	 *
	 * 试卷ID
	 *
	 */
	private java.lang.String testID;
   
	public void setTestID(java.lang.String testID){
		this.testID= testID;       
	}
   
	public java.lang.String getTestID(){
		return this.testID;
	}

	/**
	 *
	 * 题目类型
	 *
	 */
	private java.lang.String type;
   
	public void setType(java.lang.String type){
		this.type= type;       
	}
   
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *
	 * 题目描述
	 *
	 */
	private java.lang.String description;
   
	public void setDescription(java.lang.String description){
		this.description= description;       
	}
   
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *
	 * 题目数量
	 *
	 */
	private java.lang.Integer quantity;
   
	public void setQuantity(java.lang.Integer quantity){
		this.quantity= quantity;       
	}
   
	public java.lang.Integer getQuantity(){
		return this.quantity;
	}

	/**
	 *
	 * 分值
	 *
	 */
	private java.lang.String score;
   
	public void setScore(java.lang.String score){
		this.score= score;       
	}
   
	public java.lang.String getScore(){
		return this.score;
	}


}