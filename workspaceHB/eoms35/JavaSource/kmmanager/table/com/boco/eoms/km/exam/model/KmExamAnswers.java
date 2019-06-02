package com.boco.eoms.km.exam.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmExamAnswers extends BaseObject {

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
	
	private java.lang.String attendUser;
	
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
	 * 用户得分
	 *
	 */
	private java.lang.String score;
	
	/**
	 * 
	 * 考试试卷id
	 * 
	 */
	private java.lang.String testID;
	
	/**
	 *
	 * 考试信息id
	 *
	 */
	private java.lang.String attendId;
   
	/**
	 * 参考答案
	 */
	private java.lang.String referenceAnswer;
	
	/**
	 * 题目满分
	 */
	private java.lang.String referenceScore;
	
	public void setAttendId(java.lang.String attendId){
		this.attendId= attendId;       
	}
   
	public java.lang.String getAttendId(){
		return this.attendId;
	}

	public boolean equals(Object o) {
		if( o instanceof KmExamAnswers ) {
			KmExamAnswers kmExamAnswers=(KmExamAnswers)o;
			if (this.id != null || this.id.equals(kmExamAnswers.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public java.lang.String getTestID() {
		return testID;
	}

	public void setTestID(java.lang.String testID) {
		this.testID = testID;
	}

	public java.lang.String getAttendUser() {
		return attendUser;
	}

	public void setAttendUser(java.lang.String attendUser) {
		this.attendUser = attendUser;
	}

	public java.lang.String getReferenceAnswer() {
		return referenceAnswer;
	}

	public void setReferenceAnswer(java.lang.String referenceAnswer) {
		this.referenceAnswer = referenceAnswer;
	}

	public java.lang.String getScore() {
		return score;
	}

	public void setScore(java.lang.String score) {
		this.score = score;
	}

	public java.lang.String getReferenceScore() {
		return referenceScore;
	}

	public void setReferenceScore(java.lang.String referenceScore) {
		this.referenceScore = referenceScore;
	}

}