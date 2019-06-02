package com.boco.eoms.parter.baseinfo.pnrcompact.model;

import java.sql.Date;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
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
 * @hibernate.class table="pnr_ compact"
 */
public class Pnrcompact extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	//private String compactNum;               	//合同号
	private String compactNumVal;				//值 
	private String compactNumRem;				//备注
	
	//private String compactName;				//合同名称
	private String compactNameVal;				//值
	private String compactNameRem;				//备注
	
	//private String conmpactUnit;				//合作单位
	private String conmpactUnitVal;				//值
	private String conmpactUnitValId;
	private String conmpactUnitRem;				//备注
		
	//private String compactContent;			//合同内容
	private String compactContentVal;			//值
	private String compactContentRem;			//备注
	
	//private String compactPlace;				//合同地点
	private String compactPlaceVal;				//值
	private String compactPlaceRem;				//备注
	
	//private String projectMeasure;			//工程量
	private String projectMeasureVal;			//值
	private String projectMeasureRem;			//备注
	
	//private String unitPrice;				   	//单价
	private String unitPriceVal;				//值
	private String unitPriceRem;				//备注
	
	//private String compactMoney;				//合同金额（元）
	private String compactMoneyVal;				//值
	private String compactMoneyRem;				//备注
	
	//private String paymentMode;				//付款方式
	private String paymentModeVal;				//值
	private String paymentModeRem;				//备注
	
	// String compactSignedDate;				//合同签订日期
	private Date compactSignedDateVal;		//值
	private String compactSignedDateRem;		//备注
	
	//private String compactValidity;			//合同有效期
	private String compactValidityVal;			//值
	private String compactValidityRem;			//备注
	
	//private String remark;					// 备注
	private String remarkVal;					//值
	private String remarkRem;					//备注
		
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getConmpactUnitValId() {
		return conmpactUnitValId;
	}

	public void setConmpactUnitValId(String conmpactUnitValId) {
		this.conmpactUnitValId = conmpactUnitValId;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactNumVal() {
		return compactNumVal;
	}

	public void setCompactNumVal(String compactNumVal) {
		this.compactNumVal = compactNumVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactNumRem() {
		return compactNumRem;
	}

	public void setCompactNumRem(String compactNumRem) {
		this.compactNumRem = compactNumRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactNameVal() {
		return compactNameVal;
	}

	public void setCompactNameVal(String compactNameVal) {
		this.compactNameVal = compactNameVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactNameRem() {
		return compactNameRem;
	}

	public void setCompactNameRem(String compactNameRem) {
		this.compactNameRem = compactNameRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getConmpactUnitVal() {
		return conmpactUnitVal;
	}

	public void setConmpactUnitVal(String conmpactUnitVal) {
		this.conmpactUnitVal = conmpactUnitVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getConmpactUnitRem() {
		return conmpactUnitRem;
	}

	public void setConmpactUnitRem(String conmpactUnitRem) {
		this.conmpactUnitRem = conmpactUnitRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactContentVal() {
		return compactContentVal;
	}

	public void setCompactContentVal(String compactContentVal) {
		this.compactContentVal = compactContentVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactContentRem() {
		return compactContentRem;
	}

	public void setCompactContentRem(String compactContentRem) {
		this.compactContentRem = compactContentRem;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactPlaceVal() {
		return compactPlaceVal;
	}

	public void setCompactPlaceVal(String compactPlaceVal) {
		this.compactPlaceVal = compactPlaceVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactPlaceRem() {
		return compactPlaceRem;
	}

	public void setCompactPlaceRem(String compactPlaceRem) {
		this.compactPlaceRem = compactPlaceRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getProjectMeasureVal() {
		return projectMeasureVal;
	}

	public void setProjectMeasureVal(String projectMeasureVal) {
		this.projectMeasureVal = projectMeasureVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getProjectMeasureRem() {
		return projectMeasureRem;
	}

	public void setProjectMeasureRem(String projectMeasureRem) {
		this.projectMeasureRem = projectMeasureRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getUnitPriceVal() {
		return unitPriceVal;
	}

	public void setUnitPriceVal(String unitPriceVal) {
		this.unitPriceVal = unitPriceVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getUnitPriceRem() {
		return unitPriceRem;
	}

	public void setUnitPriceRem(String unitPriceRem) {
		this.unitPriceRem = unitPriceRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactMoneyVal() {
		return compactMoneyVal;
	}

	public void setCompactMoneyVal(String compactMoneyVal) {
		this.compactMoneyVal = compactMoneyVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactMoneyRem() {
		return compactMoneyRem;
	}

	public void setCompactMoneyRem(String compactMoneyRem) {
		this.compactMoneyRem = compactMoneyRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getPaymentModeVal() {
		return paymentModeVal;
	}

	public void setPaymentModeVal(String paymentModeVal) {
		this.paymentModeVal = paymentModeVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getPaymentModeRem() {
		return paymentModeRem;
	}

	public void setPaymentModeRem(String paymentModeRem) {
		this.paymentModeRem = paymentModeRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactSignedDateRem() {
		return compactSignedDateRem;
	}

	public void setCompactSignedDateRem(String compactSignedDateRem) {
		this.compactSignedDateRem = compactSignedDateRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactValidityVal() {
		return compactValidityVal;
	}

	public void setCompactValidityVal(String compactValidityVal) {
		this.compactValidityVal = compactValidityVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCompactValidityRem() {
		return compactValidityRem;
	}

	public void setCompactValidityRem(String compactValidityRem) {
		this.compactValidityRem = compactValidityRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getRemarkVal() {
		return remarkVal;
	}

	public void setRemarkVal(String remarkVal) {
		this.remarkVal = remarkVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getRemarkRem() {
		return remarkRem;
	}

	public void setRemarkRem(String remarkRem) {
		this.remarkRem = remarkRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public Date getCompactSignedDateVal() {
		return compactSignedDateVal;
	}

	public void setCompactSignedDateVal(Date compactSignedDateVal) {
		this.compactSignedDateVal = compactSignedDateVal;
	}

	
}
