package com.boco.eoms.otherwise.webapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmRenewalForm extends BaseForm implements Serializable {
	private String id;
	private String testcardId;
	private String borrowDate;
	private String intendingReturnDate;
	private String renewDate;
	private String borrowerId;
	private String borrowerName;
	private String userId;
	private String remark;
	private String borrowStartDate;
	private String borrowEndDate;
	private String intendingReturnStartDate;
	private String intendingReturnEndDate;
	private String renewStartDate;
	private String renewEndDate;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTestcardId() {
		return testcardId;
	}

	public void setTestcardId(String testcardId) {
		this.testcardId = testcardId;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getIntendingReturnDate() {
		return intendingReturnDate;
	}

	public void setIntendingReturnDate(String intendingReturnDate) {
		this.intendingReturnDate = intendingReturnDate;
	}

	public String getRenewDate() {
		return renewDate;
	}

	public void setRenewDate(String renewDate) {
		this.renewDate = renewDate;
	}

	public String getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBorrowStartDate() {
		return borrowStartDate;
	}

	public void setBorrowStartDate(String borrowStartDate) {
		this.borrowStartDate = borrowStartDate;
	}

	public String getBorrowEndDate() {
		return borrowEndDate;
	}

	public void setBorrowEndDate(String borrowEndDate) {
		this.borrowEndDate = borrowEndDate;
	}

	public String getIntendingReturnStartDate() {
		return intendingReturnStartDate;
	}

	public void setIntendingReturnStartDate(String intendingReturnStartDate) {
		this.intendingReturnStartDate = intendingReturnStartDate;
	}

	public String getIntendingReturnEndDate() {
		return intendingReturnEndDate;
	}

	public void setIntendingReturnEndDate(String intendingReturnEndDate) {
		this.intendingReturnEndDate = intendingReturnEndDate;
	}

	public String getRenewStartDate() {
		return renewStartDate;
	}

	public void setRenewStartDate(String renewStartDate) {
		this.renewStartDate = renewStartDate;
	}

	public String getRenewEndDate() {
		return renewEndDate;
	}

	public void setRenewEndDate(String renewEndDate) {
		this.renewEndDate = renewEndDate;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
