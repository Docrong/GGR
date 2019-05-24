package com.boco.eoms.km.exam.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:答题信息表
 * </p>
 * <p>
 * Description:答题信息表
 * </p>
 * <p>
 * Mon May 11 11:00:31 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public class KmExamAnswersForm extends BaseForm implements java.io.Serializable {

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
	 * 问题Id
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
	 * 答题
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
	 * 是否正确
	 *
	 */
	private java.lang.Integer isRight;
   
	public void setIsRight(java.lang.Integer isRight){
		this.isRight= isRight;       
	}
   
	public java.lang.Integer getIsRight(){
		return this.isRight;
	}

	/**
	 *
	 * 得分
	 *
	 */
	private java.lang.String score;
   
	public void setScore(java.lang.String score){
		this.score= score;       
	}
   
	public java.lang.String getScore(){
		return this.score;
	}

	/**
	 *
	 * 考试信息id
	 *
	 */
	private java.lang.String attendId;
   
	public void setAttendId(java.lang.String attendId){
		this.attendId= attendId;       
	}
   
	public java.lang.String getAttendId(){
		return this.attendId;
	}

}