package com.boco.eoms.parter.baseinfo.contract.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:代维合同
 * </p>
 * <p>
 * Description:代维合同
 * </p>
 * <p>
 * Wed Apr 01 08:58:31 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public class TawContract extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4643809038525212407L;
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
	 * 合同名称
	 *
	 */
	private java.lang.String contractName;
   
	public void setContractName(java.lang.String contractName){
		this.contractName= contractName;       
	}
   
	public java.lang.String getContractName(){
		return this.contractName;
	}

	/**
	 *
	 * 甲方名称
	 *
	 */
	private java.lang.String name_A;
   
	public void setName_A(java.lang.String name_A){
		this.name_A= name_A;       
	}
   
	public java.lang.String getName_A(){
		return this.name_A;
	}

	/**
	 *
	 * 乙方名称
	 *
	 */
	private java.lang.String name_B;
   
	public void setName_B(java.lang.String name_B){
		this.name_B= name_B;       
	}
   
	public java.lang.String getName_B(){
		return this.name_B;
	}

	/**
	 *
	 * 维护外包范围
	 *
	 */
	private java.lang.String maintenanceRange;
   
	public void setMaintenanceRange(java.lang.String maintenanceRange){
		this.maintenanceRange= maintenanceRange;       
	}
   
	public java.lang.String getMaintenanceRange(){
		return this.maintenanceRange;
	}

	/**
	 *
	 * 甲方职责与权利
	 *
	 */
	private java.lang.String right_A;
   
	public void setRight_A(java.lang.String right_A){
		this.right_A= right_A;       
	}
   
	public java.lang.String getRight_A(){
		return this.right_A;
	}

	/**
	 *
	 * 乙方职责与权利
	 *
	 */
	private java.lang.String right_B;
   
	public void setRight_B(java.lang.String right_B){
		this.right_B= right_B;       
	}
   
	public java.lang.String getRight_B(){
		return this.right_B;
	}

	/**
	 *
	 * 维护外包费用
	 *
	 */
	private java.lang.String cost;
   
	public void setCost(java.lang.String cost){
		this.cost= cost;       
	}
   
	public java.lang.String getCost(){
		return this.cost;
	}

	/**
	 *
	 * 甲方接口规范
	 *
	 */
	private java.lang.String interface_A;
   
	public void setInterface_A(java.lang.String interface_A){
		this.interface_A= interface_A;       
	}
   
	public java.lang.String getInterface_A(){
		return this.interface_A;
	}

	/**
	 *
	 * 甲方联系方式
	 *
	 */
	private java.lang.String contact_A;
   
	public void setContact_A(java.lang.String contact_A){
		this.contact_A= contact_A;       
	}
   
	public java.lang.String getContact_A(){
		return this.contact_A;
	}

	/**
	 *
	 * 乙方接口规范
	 *
	 */
	private java.lang.String interface_B;
   
	public void setInterface_B(java.lang.String interface_B){
		this.interface_B= interface_B;       
	}
   
	public java.lang.String getInterface_B(){
		return this.interface_B;
	}

	/**
	 *
	 * 乙方联系方式
	 *
	 */
	private java.lang.String contact_B;
   
	public void setContact_B(java.lang.String contact_B){
		this.contact_B= contact_B;       
	}
   
	public java.lang.String getContact_B(){
		return this.contact_B;
	}

	/**
	 *
	 * 质量考核内容与方式
	 *
	 */
	private java.lang.String qualityCheck;
   
	public void setQualityCheck(java.lang.String qualityCheck){
		this.qualityCheck= qualityCheck;       
	}
   
	public java.lang.String getQualityCheck(){
		return this.qualityCheck;
	}

	/**
	 *
	 * 业务量变更的处理
	 *
	 */
	private java.lang.String qualityChangeDeal;
   
	public void setQualityChangeDeal(java.lang.String qualityChangeDeal){
		this.qualityChangeDeal= qualityChangeDeal;       
	}
   
	public java.lang.String getQualityChangeDeal(){
		return this.qualityChangeDeal;
	}

	/**
	 *
	 * 付款方式
	 *
	 */
	private java.lang.String payType;
   
	public void setPayType(java.lang.String payType){
		this.payType= payType;       
	}
   
	public java.lang.String getPayType(){
		return this.payType;
	}

	/**
	 *
	 * 付款周期
	 *
	 */
	private java.lang.String payCycle;
   
	public void setPayCycle(java.lang.String payCycle){
		this.payCycle= payCycle;       
	}
   
	public java.lang.String getPayCycle(){
		return this.payCycle;
	}

	/**
	 *
	 * 保密
	 *
	 */
	private java.lang.String secret;
   
	public void setSecret(java.lang.String secret){
		this.secret= secret;       
	}
   
	public java.lang.String getSecret(){
		return this.secret;
	}

	/**
	 *
	 * 提前终止合同条件及操作方式
	 *
	 */
	private java.lang.String stopCondition;
   
	public void setStopCondition(java.lang.String stopCondition){
		this.stopCondition= stopCondition;       
	}
   
	public java.lang.String getStopCondition(){
		return this.stopCondition;
	}

	/**
	 *
	 * 违约
	 *
	 */
	private java.lang.String breachFaith;
   
	public void setBreachFaith(java.lang.String breachFaith){
		this.breachFaith= breachFaith;       
	}
   
	public java.lang.String getBreachFaith(){
		return this.breachFaith;
	}

	/**
	 *
	 * 不可抗力
	 *
	 */
	private java.lang.String beyond;
   
	public void setBeyond(java.lang.String beyond){
		this.beyond= beyond;       
	}
   
	public java.lang.String getBeyond(){
		return this.beyond;
	}

	/**
	 *
	 * 协议期限
	 *
	 */
	private java.lang.String timeLimit;
   
	public void setTimeLimit(java.lang.String timeLimit){
		this.timeLimit= timeLimit;       
	}
   
	public java.lang.String getTimeLimit(){
		return this.timeLimit;
	}

	/**
	 *
	 * 争议解决
	 *
	 */
	private java.lang.String solve;
   
	public void setSolve(java.lang.String solve){
		this.solve= solve;       
	}
   
	public java.lang.String getSolve(){
		return this.solve;
	}

	/**
	 *
	 * 附则
	 *
	 */
	private java.lang.String add_ons;
   
	public void setAdd_ons(java.lang.String add_ons){
		this.add_ons= add_ons;       
	}
   
	public java.lang.String getAdd_ons(){
		return this.add_ons;
	}

	/**
	 *
	 * 合同录入时间
	 *
	 */
	private java.util.Date createTime;
   
	public void setCreateTime(java.util.Date createTime){
		this.createTime= createTime;       
	}
   
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *
	 * 合同录入人
	 *
	 */
	private java.lang.String creator;
   
	public void setCreator(java.lang.String creator){
		this.creator= creator;       
	}
   
	public java.lang.String getCreator(){
		return this.creator;
	}

	/**
	 *
	 * 合同已付金额
	 *
	 */
	private java.lang.String payed;
   
	public void setPayed(java.lang.String payed){
		this.payed= payed;       
	}
   
	public java.lang.String getPayed(){
		return this.payed;
	}

	/**
	 *
	 * 附件
	 *
	 */
	private java.lang.String accessory;
   
	public void setAccessory(java.lang.String accessory){
		this.accessory= accessory;       
	}
   
	public java.lang.String getAccessory(){
		return this.accessory;
	}
    private java.lang.String creatorName;
    public void setCreatorName(java.lang.String creatorName){
		this.creatorName= creatorName;       
	}
   
	public java.lang.String getCreatorName(){
		return this.creatorName;
	}
	
	public boolean equals(Object o) {
		if( o instanceof TawContract ) {
			TawContract tawContract=(TawContract)o;
			if (this.id != null || this.id.equals(tawContract.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}