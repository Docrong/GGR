package com.boco.eoms.otherwise.model;

import com.boco.eoms.base.model.BaseObject;

public class TawRmRenewal extends BaseObject {
	/**
	 * This class is used to generate the Struts Validator Form as well as the This
	 * class is used to generate Spring Validation rules as well as the Hibernate
	 * mapping file.
	 * 
	 * <p>
	 * <a href="User.java.html"><i>View Source</i></a>
	 * 
	 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
	 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
	 *         UserDetails interface by David Carter david@carter.net
	 * 
	 * @struts.form include-all="true" extends="BaseForm"
	 * @hibernate.class table="taw_rm_renewal"
	 */
	private String id;
	private String testcardId;
	private String borrowDate;
	private String intendingReturnDate;
	private String renewDate;
	private String borrowerId;
	private String userId;
	private String remark;
	
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="true" unique="true"
	 */
	public String getTestcardId() {
		return testcardId;
	}

	public void setTestcardId(String testcardId) {
		this.testcardId = testcardId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getIntendingReturnDate() {
		return intendingReturnDate;
	}

	public void setIntendingReturnDate(String intendingReturnDate) {
		this.intendingReturnDate = intendingReturnDate;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getRenewDate() {
		return renewDate;
	}

	public void setRenewDate(String renewDate) {
		this.renewDate = renewDate;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="true" unique="true"
	 */
	public String getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="100" not-null="false" unique="true"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
