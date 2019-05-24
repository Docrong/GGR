package com.boco.eoms.parter.baseinfo.contract.webapp.form;

import java.util.List;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:tawcontractpay
 * </p>
 * <p>
 * Description:tawcontractpay
 * </p>
 * <p>
 * Mon Apr 13 10:13:22 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() xyb
 * @moudle.getVersion() jldx
 * 
 */
public class TawContractPayForm extends BaseForm implements java.io.Serializable {

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
	 * contractname
	 *
	 */
	private java.lang.String contractname;
   
	public void setContractname(java.lang.String contractname){
		this.contractname= contractname;       
	}
   
	public java.lang.String getContractname(){
		return this.contractname;
	}

	/**
	 *
	 * payer
	 *
	 */
	private java.lang.String payer;
   
	public void setPayer(java.lang.String payer){
		this.payer= payer;       
	}
   
	public java.lang.String getPayer(){
		return this.payer;
	}
	
	private java.lang.String operator;	
	
	
	/**
	 *
	 * paymethod
	 *
	 */
	private java.lang.String paymethod;
   
	public void setPaymethod(java.lang.String paymethod){
		this.paymethod= paymethod;       
	}
   
	public java.lang.String getPaymethod(){
		return this.paymethod;
	}

	/**
	 *
	 * payaccount
	 *
	 */
	private java.lang.String payaccount;
   
	public void setPayaccount(java.lang.String payaccount){
		this.payaccount= payaccount;       
	}
   
	public java.lang.String getPayaccount(){
		return this.payaccount;
	}

	/**
	 *
	 * money
	 *
	 */
	private java.lang.String money;
   
	public void setMoney(java.lang.String money){
		this.money= money;       
	}
   
	public java.lang.String getMoney(){
		return this.money;
	}

	/**
	 *
	 * paytime
	 *
	 */
	private java.lang.String paytime;
   
	public java.lang.String getPaytime() {
		return paytime;
	}

	public void setPaytime(java.lang.String paytime) {
		this.paytime = paytime;
	}

	/**
	 *
	 * remark
	 *
	 */
	private java.lang.String remark;
   
	public void setRemark(java.lang.String remark){
		this.remark= remark;       
	}
   
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *
	 * accessory
	 *
	 */
	private java.lang.String accessory;
   
	public void setAccessory(java.lang.String accessory){
		this.accessory= accessory;       
	}
   
	public java.lang.String getAccessory(){
		return this.accessory;
	}

	/**
	 *
	 * contractid
	 *
	 */
	private java.lang.String contractid;
   
	public void setContractid(java.lang.String contractid){
		this.contractid= contractid;       
	}
   
	public java.lang.String getContractid(){
		return this.contractid;
	}

	/**
	 * 合同付款情况
	 */
	private List payinfoList;

	public List getPayinfoList() {
		return payinfoList;
	}

	public void setPayinfoList(List payinfoList) {
		this.payinfoList = payinfoList;
	}

	public java.lang.String getOperator() {
		return operator;
	}

	public void setOperator(java.lang.String operator) {
		this.operator = operator;
	}

	

	
	
	
}