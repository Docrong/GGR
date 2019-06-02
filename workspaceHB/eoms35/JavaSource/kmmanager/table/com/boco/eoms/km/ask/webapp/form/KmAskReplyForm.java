package com.boco.eoms.km.ask.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:答题
 * </p>
 * <p>
 * Description:回答答案
 * </p>
 * <p>
 * Tue Aug 04 15:52:08 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public class KmAskReplyForm extends BaseForm implements java.io.Serializable {

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
	 * 问题id
	 *
	 */
	private java.lang.String questionId;
   
	public void setQuestionId(java.lang.String questionId){
		this.questionId= questionId;       
	}
   
	public java.lang.String getQuestionId(){
		return this.questionId;
	}

	/**
	 *
	 * 回答内容
	 *
	 */
	private java.lang.String answer;
   
	public void setAnswer(java.lang.String answer){
		this.answer= answer;       
	}
   
	public java.lang.String getAnswer(){
		return this.answer;
	}

	/**
	 *
	 * 提问者评论
	 *
	 */
	private java.lang.String conment;
   
	public void setConment(java.lang.String conment){
		this.conment= conment;       
	}
   
	public java.lang.String getConment(){
		return this.conment;
	}

	/**
	 *
	 * 是否是最佳
	 *
	 */
	private java.lang.String isBest;
   
	public void setIsBest(java.lang.String isBest){
		this.isBest= isBest;       
	}
   
	public java.lang.String getIsBest(){
		return this.isBest;
	}

	/**
	 *
	 * 回答人
	 *
	 */
	private java.lang.String answerUser;
   
	public void setAnswerUser(java.lang.String answerUser){
		this.answerUser= answerUser;       
	}
   
	public java.lang.String getAnswerUser(){
		return this.answerUser;
	}

	/**
	 *
	 * 回答部门
	 *
	 */
	private java.lang.String answerDept;
   
	public void setAnswerDept(java.lang.String answerDept){
		this.answerDept= answerDept;       
	}
   
	public java.lang.String getAnswerDept(){
		return this.answerDept;
	}

	/**
	 *
	 * 回答时间
	 *
	 */
	private java.lang.String answerTime;
   
	public void setAnswerTime(java.lang.String answerTime){
		this.answerTime= answerTime;       
	}
   
	public java.lang.String getAnswerTime(){
		return this.answerTime;
	}

	/**
	 *
	 * 附件名称
	 *
	 */
	private java.lang.String fileName;
   
	public void setFileName(java.lang.String fileName){
		this.fileName= fileName;       
	}
   
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *
	 * 是否匿名
	 *
	 */
	private java.lang.String isAnonymity;
   
	public void setIsAnonymity(java.lang.String isAnonymity){
		this.isAnonymity= isAnonymity;       
	}
   
	public java.lang.String getIsAnonymity(){
		return this.isAnonymity;
	}

}