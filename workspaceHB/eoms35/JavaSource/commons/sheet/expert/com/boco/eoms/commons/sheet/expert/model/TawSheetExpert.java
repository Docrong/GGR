/*
 * Created on 2008-5-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.sheet.expert.model;
import java.io.Serializable;
import com.boco.eoms.base.model.BaseObject;
import java.util.Date;
/**
 * @author admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSheetExpert extends BaseObject implements Serializable{
	private Integer id;
	private String expertName;
	private String postName;
	private String deptId;
	private String phone;
	private String email;
	private String postStartTime;
	private String postEndTime;
	private int acceptCount;
	private String speId;
	private String expertState;
	private String stateStartTime;
	private String stateEndTime;
	private String deleted;
	private String expertType;
	
	
	
	
	public int hashCode() {
		
		return 0;
	}

	public String toString() {
		
		return null;
	}
	public boolean equals(Object o) {
		
			return false;
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
}
