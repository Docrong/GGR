package com.boco.eoms.km.ask.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:投票
 * </p>
 * <p>
 * Description:投票
 * </p>
 * <p>
 * Fri Aug 14 15:39:20 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmAskVote extends BaseObject {

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
	 * 投票人
	 *
	 */
	private java.lang.String voteUser;
   
	public void setVoteUser(java.lang.String voteUser){
		this.voteUser= voteUser;       
	}
   
	public java.lang.String getVoteUser(){
		return this.voteUser;
	}

	/**
	 *
	 * 投票时间
	 *
	 */
	private Date voteDate;
   
	public void setVoteDate(Date voteDate){
		this.voteDate= voteDate;       
	}
   
	public Date getVoteDate(){
		return this.voteDate;
	}

	/**
	 *
	 * 投票部门
	 *
	 */
	private java.lang.String voteDept;
   
	public void setVoteDept(java.lang.String voteDept){
		this.voteDept= voteDept;       
	}
   
	public java.lang.String getVoteDept(){
		return this.voteDept;
	}
	
	/**
	 * 投票对应的问题id
	 */
	private java.lang.String questionId;
	
	/**
	 * 投票对应的回答id
	 */
	private java.lang.String replyId;
	
	public boolean equals(Object o) {
		if( o instanceof KmAskVote ) {
			KmAskVote kmAskVote=(KmAskVote)o;
			if (this.id != null || this.id.equals(kmAskVote.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public java.lang.String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(java.lang.String questionId) {
		this.questionId = questionId;
	}

	public java.lang.String getReplyId() {
		return replyId;
	}

	public void setReplyId(java.lang.String replyId) {
		this.replyId = replyId;
	}
}