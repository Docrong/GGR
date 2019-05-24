/*
 * Created on 2008-5-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.sheet.expert.webapp.form;

import java.io.Serializable;
import java.util.Date;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * @author admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSheetExpertForm extends BaseForm implements Serializable{

	protected Integer id;
	protected String expertName;
	protected String postName;
	protected String deptId;
	protected String phone;
	protected String email;
	protected String postStartTime;
	protected String postEndTime;
	protected int acceptCount;
	protected String speId;
	protected String expertState;
	protected String stateStartTime;
	protected String stateEndTime;
	protected String deleted;
	protected String expertType;
	 public TawSheetExpertForm() {}
	
	/**
	 * @return Returns the deleted.
	 */
	public String getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted The deleted to set.
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	/**
	 * @return Returns the deptId.
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId The deptId to set.
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the expertName.
	 */
	public String getExpertName() {
		return expertName;
	}
	/**
	 * @param expertName The expertName to set.
	 */
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	/**
	 * @return Returns the expertState.
	 */
	public String getExpertState() {
		return expertState;
	}
	/**
	 * @param expertState The expertState to set.
	 */
	public void setExpertState(String expertState) {
		this.expertState = expertState;
	}
	

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * @return Returns the postName.
	 */
	public String getPostName() {
		return postName;
	}
	/**
	 * @param postName The postName to set.
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	/**
	 * @return Returns the speId.
	 */
	public String getSpeId() {
		return speId;
	}
	/**
	 * @param speId The speId to set.
	 */
	public void setSpeId(String speId) {
		this.speId = speId;
	}
	
	
	/**
	 * @return Returns the postEndTime.
	 */
	public String getPostEndTime() {
		return postEndTime;
	}
	/**
	 * @param postEndTime The postEndTime to set.
	 */
	public void setPostEndTime(String postEndTime) {
		this.postEndTime = postEndTime;
	}
	/**
	 * @return Returns the postStartTime.
	 */
	public String getPostStartTime() {
		return postStartTime;
	}
	/**
	 * @param postStartTime The postStartTime to set.
	 */
	public void setPostStartTime(String postStartTime) {
		this.postStartTime = postStartTime;
	}
	/**
	 * @return Returns the stateEndTime.
	 */
	public String getStateEndTime() {
		return stateEndTime;
	}
	/**
	 * @param stateEndTime The stateEndTime to set.
	 */
	public void setStateEndTime(String stateEndTime) {
		this.stateEndTime = stateEndTime;
	}
	/**
	 * @return Returns the stateStartTime.
	 */
	public String getStateStartTime() {
		return stateStartTime;
	}
	/**
	 * @param stateStartTime The stateStartTime to set.
	 */
	public void setStateStartTime(String stateStartTime) {
		this.stateStartTime = stateStartTime;
	}
	/**
	 * @return Returns the expertType.
	 */
	public String getExpertType() {
		return expertType;
	}
	/**
	 * @param expertType The expertType to set.
	 */
	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}
	
	/**
	 * @return Returns the acceptCount.
	 */
	public int getAcceptCount() {
		return acceptCount;
	}
	/**
	 * @param acceptCount The acceptCount to set.
	 */
	public void setAcceptCount(int acceptCount) {
		this.acceptCount = acceptCount;
	}
}
