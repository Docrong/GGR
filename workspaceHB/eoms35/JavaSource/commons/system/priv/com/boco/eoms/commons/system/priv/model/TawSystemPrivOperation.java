package com.boco.eoms.commons.system.priv.model;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.base.util.StaticMethod;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSystemOperation.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_priv_operation"
 */
public class TawSystemPrivOperation extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6661218572130594292L;

	private String id;

	private String code;

	private String name;

	private String parentcode = "-1";

	private String url;

	private String isApp;

	private String remark;

	private String deleted;

	private String orderby;
	
	private String loginType;
	
	 
	/**
	 * 菜单是否隐藏
	 */
	private String hided;

	/**
	 * @return the hided
	 */
	public String getHided() {
		return hided;
	}

	/**
	 * @param hided
	 *            the hided to set
	 */
	public void setHided(String hided) {
		this.hided = hided;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10"
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	 * @hibernate.property length="5"
	 */
	public String getIsApp() {
		return isApp;
	}

	public void setIsApp(String isApp) {
		this.isApp = isApp;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="10"
	 */
	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="200" not-null="false"
	 */
	public String getRemark() {
		return StaticMethod.nullObject2String(remark);
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="30" not-null="false"
	 */
	public String getUrl() {
		return StaticMethod.nullObject2String(url);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isLeaf() {
		if ("1".equals(getIsApp()) || "2".equals(getIsApp())) {
			return true;
		}
		return false;
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
	/**
	 * 新增登陆方式
	 */
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

}
