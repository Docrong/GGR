package com.boco.eoms.km.exam.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:选项表
 * </p>
 * <p>
 * Description:选项表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmExamChoice extends BaseObject {

	

	/**
	 *
	 * 主键
	 *
	 */
	private java.lang.String choiceID;
   
	public void setChoiceID(java.lang.String choiceID){
		this.choiceID= choiceID;       
	}
   
	public java.lang.String getChoiceID(){
		return this.choiceID;
	}

	/**
	 *
	 * 问题id
	 *
	 */
	private java.lang.String questionsID;
   
	public void setQuestionsID(java.lang.String questionsID){
		this.questionsID= questionsID;       
	}
   
	public java.lang.String getQuestionsID(){
		return this.questionsID;
	}

	/**
	 *
	 * 选项内容
	 *
	 */
	private java.lang.String content;
   
	public void setContent(java.lang.String content){
		this.content= content;       
	}
   
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *
	 * 排序
	 *
	 */
	private java.lang.String orderBy;
   
	public void setOrderBy(java.lang.String orderBy){
		this.orderBy= orderBy;       
	}
   
	public java.lang.String getOrderBy(){
		return this.orderBy;
	}


	public boolean equals(Object o) {
		if( o instanceof KmExamChoice ) {
			KmExamChoice kmExamChoice=(KmExamChoice)o;
			if (this.getChoiceID() != null || this.choiceID.equals(kmExamChoice.getChoiceID())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}