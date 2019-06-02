package com.boco.eoms.extra.supplierkpi.model;

import java.sql.Timestamp;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSupplierkpiItem.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_supplierkpi_log"
 */
public class TawSupplierkpiLog extends BaseObject{
	private String id;
	private String operator;
	private String operatorName;
	private String operContent;
	private String operType;
	private String operTime;
	private String serviceType;
	private String specialType;
	private int deleted;
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10" not-null="false" unique="false"
	 */
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
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
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="false"
	 */
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="500" not-null="false" unique="false"
	 */
	public String getOperContent() {
		return operContent;
	}
	public void setOperContent(String operContent) {
		this.operContent = operContent;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="false"
	 */
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="false"
	 */
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
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
}
