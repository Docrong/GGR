package com.boco.eoms.km.expert.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识库专家问答
 * </p>
 * <p>
 * Description:知识库专家问答
 * </p>
 * <p>
 * 2009-07-27
 * </p>
 * 
 * @author daizhigang
 * @version 1.0
 * 
 */
public class KmExpertAnswer extends BaseObject {

	/**
	 * 
	 */
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
	 * 创建时间
	 *
	 */
	private java.lang.String createTime;
   
	public void setCreateTime(java.lang.String createTime){
		this.createTime= createTime;       
	}
   
	public java.lang.String getCreateTime(){
		return this.createTime;
	} 
	
	/**
	 *
	 * 问题主题
	 *
	 */
	private java.lang.String title="";
  
	public void setTitle(java.lang.String title){
		this.title= title;       
	}
  
	public java.lang.String getTitle(){
		return this.title.trim();
	} 
	
	
	/**
	 *
	 * 创建人
	 *
	 */
	private String createUserId;
   
	public void setCreateUserId(String createUserId){
		this.createUserId= createUserId;       
	}
   
	public String getCreateUserId(){
		return this.createUserId;
	}

	/**
	 *
	 * 解答人
	 *
	 */
	private String answerUserId;
   
	public void setAnswerUserId(String answerUserId){
		this.answerUserId= answerUserId;       
	}
   
	public String getAnswerUserId(){
		return this.answerUserId;
	}
	
	/**
	 *
	 * 解答时间
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
	 * 类型
	 *
	 */
	private String type;
   
	public void setType(String type){
		this.type= type;       
	}
   
	public String getType(){
		return this.type;
	}

	/**
	 *
	 * 问题
	 *
	 */
	private String question = "";
   
	public void setQuestion(String question){
		this.question= question;       
	}
   
	public String getQuestion(){
		if(question!=null){
			question = question.trim();
		}
		return this.question;
	}

	/**
	 *
	 * 问题附件
	 *
	 */
	private String questionAtt;
   
	public void setQuestionAtt(String questionAtt){
		this.questionAtt= questionAtt;       
	}
   
	public String getQuestionAtt(){
		return this.questionAtt;
	}

	/**
	 *
	 * 答案
	 *
	 */
	private String answer = "";
   
	public void setAnswer(String answer){
		this.answer= answer;       
	}
   
	public String getAnswer(){
		if(answer!=null){
			answer = answer.trim();
		}
		return this.answer;
	}

	/**
	 *
	 * 答案相关联接
	 *
	 */
	private String answerUrl = "";
   
	public void setAnswerUrl(String answerUrl){
		this.answerUrl= answerUrl;       
	}
   
	public String getAnswerUrl(){
		if(answerUrl!=null){
			answerUrl = answerUrl.trim();
		}
		return this.answerUrl;
	}
	
	/**
	 *
	 * 答案附件
	 *
	 */
	private String answerAtt;
   
	public void setAnswerAtt(String answerAtt){
		this.answerAtt= answerAtt;       
	}
   
	public String getAnswerAtt(){
		return this.answerAtt;
	}

	/**
	 *
	 * 评价
	 *
	 */
	private String score;
   
	public void setScore(String score){
		this.score= score;       
	}
   
	public String getScore(){
		return this.score;
	}

	/**
	 *
	 * 评价备注
	 *
	 */
	private String scoreRemark = "";
   
	public void setScoreRemark(String scoreRemark){
		this.scoreRemark= scoreRemark;       
	}
   
	public String getScoreRemark(){
		if(scoreRemark!=null){
			scoreRemark = scoreRemark.trim();
		}
		return this.scoreRemark;
	}

	/**
	 *
	 * 备注
	 *
	 */
	private String remark = "";
   
	public void setRemark(String remark){
		this.remark= remark;       
	}
   
	public String getRemark(){
		if(remark!=null){
			remark = remark.trim();
		}
		return this.remark;
	}
	
	/**
	 *
	 * 状态
	 *
	 */
	private String state;
   
	public void setState(String state){
		this.state= state;       
	}
   
	public String getState(){
		return this.state;
	}
	

	public boolean equals(Object o) {
		if (o instanceof KmExpertAnswer) {
			KmExpertAnswer kmExpertAnswer = (KmExpertAnswer) o;
			if (this.id != null || this.id.equals(kmExpertAnswer.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}