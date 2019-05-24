package com.boco.eoms.otherwise.webapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmInoutRecordForm extends BaseForm implements Serializable {
	
	private String id;
	private String testcardId;
	private String borrowDate;
	private String intendingReturnDate;
	private String realReturnDate;
	private String borrowerId;
	private String borrowerName;
	private String userId;
	private String outType;
	private String inType;
	private String inState;
	private String remark;
	private String ids;
	private String cardType;
	private String inStorageUserId;
	private String inStorageRemark;
	private String borrowStartDate;
	private String borrowEndDate;
	private String intendingReturnStartDate;
	private String intendingReturnEndDate;
	private String realReturnStartDate;
	private String realReturnEndDate;
	private String ascriptionPlace;
	private String msisdn;
	private String fromProvince;
	private String fromCity;
	private String fromCountry;
	private String fromOperator;
	private String toProvince;
	private String toCity;
	private String toCountry;
	private String toOperator;
	
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
	
	public String getRealReturnDate() {
		return realReturnDate;
	}

	public void setRealReturnDate(String realReturnDate) {
		this.realReturnDate = realReturnDate;
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
	
	public String getOutType() {
		return outType;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}
	
	public String getInType() {
		return inType;
	}

	public void setInType(String inType) {
		this.inType = inType;
	}
	
	public String getInState() {
		return inState;
	}

	public void setInState(String inState) {
		this.inState = inState;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false" unique="true"
	 */
	public String getInStorageUserId() {
		return inStorageUserId;
	}

	public void setInStorageUserId(String inStorageUserId) {
		this.inStorageUserId = inStorageUserId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="100" not-null="false" unique="true"
	 */
	public String getInStorageRemark() {
		return inStorageRemark;
	}

	public void setInStorageRemark(String inStorageRemark) {
		this.inStorageRemark = inStorageRemark;
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

	public String getRealReturnStartDate() {
		return realReturnStartDate;
	}

	public void setRealReturnStartDate(String realReturnStartDate) {
		this.realReturnStartDate = realReturnStartDate;
	}

	public String getRealReturnEndDate() {
		return realReturnEndDate;
	}

	public void setRealReturnEndDate(String realReturnEndDate) {
		this.realReturnEndDate = realReturnEndDate;
	}
	
	public String getAscriptionPlace() {
		return ascriptionPlace;
	}

	public void setAscriptionPlace(String ascriptionPlace) {
		this.ascriptionPlace = ascriptionPlace;
	}
	
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public String getFromProvince() {
		return fromProvince;
	}

	public void setFromProvince(String fromProvince) {
		this.fromProvince = fromProvince;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	
	public String getFromCountry() {
		return fromCountry;
	}

	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getFromOperator() {
		return fromOperator;
	}

	public void setFromOperator(String fromOperator) {
		this.fromOperator = fromOperator;
	}
	
	public String getToProvince() {
		return toProvince;
	}

	public void setToProvince(String toProvince) {
		this.toProvince = toProvince;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	
	public String getToCountry() {
		return toCountry;
	}

	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}

	public String getToOperator() {
		return toOperator;
	}

	public void setToOperator(String toOperator) {
		this.toOperator = toOperator;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
