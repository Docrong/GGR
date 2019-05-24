package com.boco.eoms.parter.baseinfo.contract.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @author xyb
 * @version jldx
 * 
 */
public class TawContractPay extends BaseObject {
	
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

	/**
	 * 操作人
	 */
	private java.lang.String operator;
	
	public java.lang.String getOperator() {
		return operator;
	}

	public void setOperator(java.lang.String operator) {
		this.operator = operator;
	}

	
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
	private java.util.Date paytime;
   
	public void setPaytime(java.util.Date paytime){
		this.paytime= paytime;       
	}
   
	public java.util.Date getPaytime(){
		return this.paytime;
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

	

	public boolean equals(Object o) {
		if( o instanceof TawContractPay ) {
			TawContractPay tawContractPay=(TawContractPay)o;
			if (this.id != null || this.id.equals(tawContractPay.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	
	

}