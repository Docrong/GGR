package com.boco.eoms.extra.supplierkpi.model;

import java.sql.Timestamp;

import com.boco.eoms.base.model.BaseObject;

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
 * @hibernate.class table="Taw_Supplierkpi_InstanceAss"
 */
public class TawSupplierkpiInstanceAss extends BaseObject{

	private String id;
	private String instanceId;
	private String subRole;
	private Timestamp assessTime;
	private String realAssessor;
	private String assessAttitude;
	private int assessStatus;
	private String assessLevel;
	private String timeLatitude;
	private String serviceType;
	private String specialType;
	private String supplierId;
	private String degree;
	private String year;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="256" not-null="false" unique="false"
	 */
	public String getAssessAttitude() {
		return assessAttitude;
	}
	public void setAssessAttitude(String assessAttitude) {
		this.assessAttitude = assessAttitude;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public String getAssessLevel() {
		return assessLevel;
	}
	public void setAssessLevel(String assessLevel) {
		this.assessLevel = assessLevel;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public String getSubRole() {
		return subRole;
	}
	public void setSubRole(String subRole) {
		this.subRole = subRole;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public int getAssessStatus() {
		return assessStatus;
	}
	public void setAssessStatus(int assessStatus) {
		this.assessStatus = assessStatus;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public Timestamp getAssessTime() {
		return assessTime;
	}
	public void setAssessTime(Timestamp assessTime) {
		this.assessTime = assessTime;
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
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public String getRealAssessor() {
		return realAssessor;
	}
	public void setRealAssessor(String realAssessor) {
		this.realAssessor = realAssessor;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="false"
	 */
	public String getTimeLatitude() {
		return timeLatitude;
	}
	public void setTimeLatitude(String timeLatitude) {
		this.timeLatitude = timeLatitude;
	}
	public boolean equals(Object o) {
		return false;
	}
	public int hashCode() {
		return 0;
	}
	public String toString() {
		return null;
	}
}
