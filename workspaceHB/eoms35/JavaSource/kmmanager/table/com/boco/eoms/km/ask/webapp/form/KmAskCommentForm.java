package com.boco.eoms.km.ask.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:问答评论
 * </p>
 * <p>
 * Description:问答评论
 * </p>
 * <p>
 * Fri Aug 14 15:49:40 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public class KmAskCommentForm extends BaseForm implements java.io.Serializable {

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
	 * 评论人
	 *
	 */
	private java.lang.String commentUser;
   
	public void setCommentUser(java.lang.String commentUser){
		this.commentUser= commentUser;       
	}
   
	public java.lang.String getCommentUser(){
		return this.commentUser;
	}

	/**
	 *
	 * 评论时间
	 *
	 */
	private java.lang.String commentDate;
   
	public void setCommentDate(java.lang.String commentDate){
		this.commentDate= commentDate;       
	}
   
	public java.lang.String getCommentDate(){
		return this.commentDate;
	}

	/**
	 *
	 * 评论部门
	 *
	 */
	private java.lang.String commentDept;
   
	public void setCommentDept(java.lang.String commentDept){
		this.commentDept= commentDept;       
	}
   
	public java.lang.String getCommentDept(){
		return this.commentDept;
	}

	/**
	 *
	 * 评论内容
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
	 * 问答
	 *
	 */
	private java.lang.String questionId;
   
	public void setQuestionId(java.lang.String questionId){
		this.questionId= questionId;       
	}
   
	public java.lang.String getQuestionId(){
		return this.questionId;
	}

}