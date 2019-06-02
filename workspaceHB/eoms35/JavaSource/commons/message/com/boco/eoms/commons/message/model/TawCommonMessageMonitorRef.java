package com.boco.eoms.commons.message.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawCommonMessageMonitorRef.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_common_messagemonitorref"
 */
public class TawCommonMessageMonitorRef extends BaseObject implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	private String id;

	private String monitorid;

	private String toobjectid;

	private String userid;

	private String teloremail;

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
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="消息发送映射ID"
	 * @return
	 */
	public String getMonitorid() {
		return monitorid;
	}

	public void setMonitorid(String monitorid) {
		this.monitorid = monitorid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="联系方式手机号或者EMAIL"
	 * @return
	 */
	public String getTeloremail() {
		return teloremail;
	}

	public void setTeloremail(String teloremail) {
		this.teloremail = teloremail;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="发送的目标类型部门 机房 角色 个人 域"
	 * @return
	 */
	public String getToobjectid() {
		return toobjectid;
	}

	public void setToobjectid(String toobjectid) {
		this.toobjectid = toobjectid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="具体的用户ID"
	 * @return
	 */
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
