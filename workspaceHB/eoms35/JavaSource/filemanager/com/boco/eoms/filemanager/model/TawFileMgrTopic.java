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
public class TawFileMgrTopic extends BaseObject implements Serializable {

	private Integer topic_id;

	private Integer topic_type;

	private String topic_type_name;	
	
	private Integer par_topic_id;

	private String topic_name;

	private String topic_memo;

	private Integer topic_order;

	private String topic_path;

	private Integer class_id;

	private String creat_time;


	/**
	 * @return the class_id
	 */
	public Integer getClass_id() {
		return class_id;
	}

	/**
	 * @param class_id the class_id to set
	 */
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}

	/**
	 * @return the creat_time
	 */
	public String getCreat_time() {
		return creat_time;
	}

	/**
	 * @param creat_time the creat_time to set
	 */
	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}

	/**
	 * @return the par_topic_id
	 */
	public Integer getPar_topic_id() {
		return par_topic_id;
	}

	/**
	 * @param par_topic_id the par_topic_id to set
	 */
	public void setPar_topic_id(Integer par_topic_id) {
		this.par_topic_id = par_topic_id;
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

	/**
	 * @return the topic_memo
	 */
	public String getTopic_memo() {
		return topic_memo;
	}

	/**
	 * @param topic_memo the topic_memo to set
	 */
	public void setTopic_memo(String topic_memo) {
		this.topic_memo = topic_memo;
	}

	/**
	 * @return the topic_name
	 */
	public String getTopic_name() {
		return topic_name;
	}

	/**
	 * @param topic_name the topic_name to set
	 */
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}

	/**
	 * @return the topic_order
	 */
	public Integer getTopic_order() {
		return topic_order;
	}

	/**
	 * @param topic_order the topic_order to set
	 */
	public void setTopic_order(Integer topic_order) {
		this.topic_order = topic_order;
	}

	/**
	 * @return the topic_path
	 */
	public String getTopic_path() {
		return topic_path;
	}

	/**
	 * @param topic_path the topic_path to set
	 */
	public void setTopic_path(String topic_path) {
		this.topic_path = topic_path;
	}

	/**
	 * @return the topic_type
	 */
	public Integer getTopic_type() {
		return topic_type;
	}

	/**
	 * @param topic_type the topic_type to set
	 */
	public void setTopic_type(Integer topic_type) {
		this.topic_type = topic_type;
	}

	/**
	 * @return the topic_type_name
	 */
	public String getTopic_type_name() {
		return topic_type_name;
	}

	/**
	 * @param topic_type_name the topic_type_name to set
	 */
	public void setTopic_type_name(String topic_type_name) {
		this.topic_type_name = topic_type_name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof TawFileMgrTopic) {
			TawFileMgrTopic tawFileMgrTopic = (TawFileMgrTopic) o;
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

}
