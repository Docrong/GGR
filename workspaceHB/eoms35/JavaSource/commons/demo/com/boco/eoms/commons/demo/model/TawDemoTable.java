package com.boco.eoms.commons.demo.model;

import java.io.Serializable;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

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
 * @hibernate.class table="taw_Demo_Table"
 */
public class TawDemoTable extends BaseObject implements Serializable,
		UserDetails {
	private static final long serialVersionUID = 3832626162173359411L;

	protected Long id;

	protected String addr; // required

	protected String tel; // required

	
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

	public GrantedAuthority[] getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true" unique="true"
	 */
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @hibernate.id column="id" generator-class="uuid" unsaved-value="null"
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true" unique="true"
	 */
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
