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
 * @hibernate.class table="taw_supplierkpi_templateassess"
 */
public class TawSupplierkpiTemplateAssess extends BaseObject{
	private String id;
	private String templateId;
	private String assessRole;
	private Timestamp assessTime;
	private String realAssessor;
	private String assessAttitude;
	private int assessStatus;
	private String assessLevel;
	private int isUpdate;
	private int isKpiChanged;
	
	
	public int getIsKpiChanged() {
		return isKpiChanged;
	}
	public void setIsKpiChanged(int isKpiChanged) {
		this.isKpiChanged = isKpiChanged;
	}
	public int getIsUpdate() {
		return isUpdate;
	}
	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
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
	public String getAssessRole() {
		return assessRole;
	}
	public void setAssessRole(String assessRole) {
		this.assessRole = assessRole;
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
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
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
