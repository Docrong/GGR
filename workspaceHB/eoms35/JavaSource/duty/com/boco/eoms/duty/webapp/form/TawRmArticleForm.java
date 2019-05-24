package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmArticleForm extends BaseForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -613220255869577020L;
	private String id;
	private String articleName;
	private String articleType;
	private int onlineNum;
	private int loanNum;
	private String describe;
	private int allNum;
	
	/**
	 * @return articleName
	 */
	public String getArticleName() {
		return articleName;
	}
	/**
	 * @param articleName 要设置的 articleName
	 */
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	/**
	 * @return articleType
	 */
	public String getArticleType() {
		return articleType;
	}
	/**
	 * @param articleType 要设置的 articleType
	 */
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	/**
	 * @return describe
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * @param describe 要设置的 describe
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	 public int getAllNum() {
		return allNum;
	}
	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}
	public int getLoanNum() {
		return loanNum;
	}
	public void setLoanNum(int loanNum) {
		this.loanNum = loanNum;
	}
	public int getOnlineNum() {
		return onlineNum;
	}
	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	        // reset any boolean data types to false

	    }
}
