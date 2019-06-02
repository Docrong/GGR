package com.boco.eoms.commons.message.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawCommonMessageOpertype.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_common_messageopertype"
 */
public class TawCommonMessageOpertype extends BaseObject implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String modelid;

	private String modelname;

	private String operid;

	private String opername;

	private String userid;

	private String operremark;

	private String operrefmodelid;

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
	 * @eoms.cn name="业务ID"
	 * @return
	 */
	public String getOperid() {
		return operid;
	}

	public void setOperid(String operid) {
		this.operid = operid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="业务名称"
	 * @return
	 */
	public String getOpername() {
		return opername;
	}

	public void setOpername(String opername) {
		this.opername = opername;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="备注"
	 * @return
	 */
	public String getOperremark() {
		return operremark;
	}

	public void setOperremark(String operremark) {
		this.operremark = operremark;
	}

	/**
	 * @hibernate.property length="500"
	 * @eoms.show
	 * @eoms.cn name="模块主键ID"
	 * @return
	 */
	public String getOperrefmodelid() {
		return operrefmodelid;
	}

	public void setOperrefmodelid(String operrefmodelid) {
		this.operrefmodelid = operrefmodelid;
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
