package com.boco.eoms.filemanager.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSystemDept.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_dept"
 */
public class TawFileMgrScheme extends BaseObject implements Serializable {

	private Integer file_mgr_scheme_id;

	private Integer scheme_cycle_type;

	private String scheme_cycle;	
	
	private String scheme_description;

	private Integer scheme_ahead;

	private String scheme_time;

	private Integer topic_id;

	private String title;

	private String report_description;

	private String fault_class;
	
	private String send_dept_id;
	
	private String send_contact;
	
	private String send_dept_name;
	
	private String create_user_id;
	
	private String accept_dept_id;
	
	private String accept_dept_name;
	
	private String specialty_id;
	
	private String combintype;
	
//	wagnsixuan add
    private String audit_user_id; //审核人ID
    private String report_user_id; //接收人ID
    private String audit_user_name; //审核人NAME
    private String report_user_name; //接收人NAME
    private String is_audit; //是否审核

	public String getIs_audit() {
		return is_audit;
	}

	public void setIs_audit(String is_audit) {
		this.is_audit = is_audit;
	}

	public String getAudit_user_id() {
		return audit_user_id;
	}

	public void setAudit_user_id(String audit_user_id) {
		this.audit_user_id = audit_user_id;
	}

	public String getReport_user_id() {
		return report_user_id;
	}

	public void setReport_user_id(String report_user_id) {
		this.report_user_id = report_user_id;
	}

	/**
	 * @return the accept_dept_id
	 */
	public String getAccept_dept_id() {
		return accept_dept_id;
	}

	/**
	 * @param accept_dept_id the accept_dept_id to set
	 */
	public void setAccept_dept_id(String accept_dept_id) {
		this.accept_dept_id = accept_dept_id;
	}

	/**
	 * @return the accept_dept_name
	 */
	public String getAccept_dept_name() {
		return accept_dept_name;
	}

	/**
	 * @param accept_dept_name the accept_dept_name to set
	 */
	public void setAccept_dept_name(String accept_dept_name) {
		this.accept_dept_name = accept_dept_name;
	}

	/**
	 * @return the combintype
	 */
	public String getCombintype() {
		return combintype;
	}

	/**
	 * @param combintype the combintype to set
	 */
	public void setCombintype(String combintype) {
		this.combintype = combintype;
	}

	/**
	 * @return the create_user_id
	 */
	public String getCreate_user_id() {
		return create_user_id;
	}

	/**
	 * @param create_user_id the create_user_id to set
	 */
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	/**
	 * @return the fault_class
	 */
	public String getFault_class() {
		return fault_class;
	}

	/**
	 * @param fault_class the fault_class to set
	 */
	public void setFault_class(String fault_class) {
		this.fault_class = fault_class;
	}

	/**
	 * @return the file_mgr_scheme_id
	 */
	public Integer getFile_mgr_scheme_id() {
		return file_mgr_scheme_id;
	}

	/**
	 * @param file_mgr_scheme_id the file_mgr_scheme_id to set
	 */
	public void setFile_mgr_scheme_id(Integer file_mgr_scheme_id) {
		this.file_mgr_scheme_id = file_mgr_scheme_id;
	}

	/**
	 * @return the report_description
	 */
	public String getReport_description() {
		return report_description;
	}

	/**
	 * @param report_description the report_description to set
	 */
	public void setReport_description(String report_description) {
		this.report_description = report_description;
	}

	/**
	 * @return the scheme_ahead
	 */
	public Integer getScheme_ahead() {
		return scheme_ahead;
	}

	/**
	 * @param scheme_ahead the scheme_ahead to set
	 */
	public void setScheme_ahead(Integer scheme_ahead) {
		this.scheme_ahead = scheme_ahead;
	}

	/**
	 * @return the scheme_cycle
	 */
	public String getScheme_cycle() {
		return scheme_cycle;
	}

	/**
	 * @param scheme_cycle the scheme_cycle to set
	 */
	public void setScheme_cycle(String scheme_cycle) {
		this.scheme_cycle = scheme_cycle;
	}

	/**
	 * @return the scheme_cycle_type
	 */
	public Integer getScheme_cycle_type() {
		return scheme_cycle_type;
	}

	/**
	 * @param scheme_cycle_type the scheme_cycle_type to set
	 */
	public void setScheme_cycle_type(Integer scheme_cycle_type) {
		this.scheme_cycle_type = scheme_cycle_type;
	}

	/**
	 * @return the scheme_description
	 */
	public String getScheme_description() {
		return scheme_description;
	}

	/**
	 * @param scheme_description the scheme_description to set
	 */
	public void setScheme_description(String scheme_description) {
		this.scheme_description = scheme_description;
	}

	/**
	 * @return the scheme_time
	 */
	public String getScheme_time() {
		return scheme_time;
	}

	/**
	 * @param scheme_time the scheme_time to set
	 */
	public void setScheme_time(String scheme_time) {
		this.scheme_time = scheme_time;
	}

	/**
	 * @return the send_contact
	 */
	public String getSend_contact() {
		return send_contact;
	}

	/**
	 * @param send_contact the send_contact to set
	 */
	public void setSend_contact(String send_contact) {
		this.send_contact = send_contact;
	}

	/**
	 * @return the send_dept_id
	 */
	public String getSend_dept_id() {
		return send_dept_id;
	}

	/**
	 * @param send_dept_id the send_dept_id to set
	 */
	public void setSend_dept_id(String send_dept_id) {
		this.send_dept_id = send_dept_id;
	}

	/**
	 * @return the send_dept_name
	 */
	public String getSend_dept_name() {
		return send_dept_name;
	}

	/**
	 * @param send_dept_name the send_dept_name to set
	 */
	public void setSend_dept_name(String send_dept_name) {
		this.send_dept_name = send_dept_name;
	}

	/**
	 * @return the specialty_id
	 */
	public String getSpecialty_id() {
		return specialty_id;
	}

	/**
	 * @param specialty_id the specialty_id to set
	 */
	public void setSpecialty_id(String specialty_id) {
		this.specialty_id = specialty_id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the topic_id
	 */
	public Integer getTopic_id() {
		return topic_id;
	}

	/**
	 * @param topic_id the topic_id to set
	 */
	public void setTopic_id(Integer topic_id) {
		this.topic_id = topic_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof TawFileMgrScheme) {
			TawFileMgrScheme tawFileMgrTopic = (TawFileMgrScheme) o;
			if (this.topic_id != null || this.topic_id.equals(tawFileMgrTopic.getTopic_id())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return this.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#toString()
	 */
	public String toString() {
		return this.toString();
	}

	public String getAudit_user_name() {
		return audit_user_name;
	}

	public void setAudit_user_name(String audit_user_name) {
		this.audit_user_name = audit_user_name;
	}

	public String getReport_user_name() {
		return report_user_name;
	}

	public void setReport_user_name(String report_user_name) {
		this.report_user_name = report_user_name;
	}

}
