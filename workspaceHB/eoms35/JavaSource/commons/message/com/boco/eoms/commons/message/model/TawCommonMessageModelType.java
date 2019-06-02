package com.boco.eoms.commons.message.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawCommonMessageModelType.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_common_messagemodeltype"
 */
public class TawCommonMessageModelType extends BaseObject implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String userid;

	private String modelid;

	private String modelname;

	private String modelremark;

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
	 * @eoms.cn name="模块ID"
	 * @return
	 */
	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="模块NAME"
	 * @return
	 */
	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="备注"
	 * @return
	 */
	public String getModelremark() {
		return modelremark;
	}

	public void setModelremark(String modelremark) {
		this.modelremark = modelremark;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="用户ID"
	 * @return
	 */
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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
