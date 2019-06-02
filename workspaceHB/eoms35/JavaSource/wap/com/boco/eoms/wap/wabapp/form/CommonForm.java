package com.boco.eoms.wap.wabapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * @author User
 * wap用到的form
 */
public class CommonForm extends BaseForm implements Serializable{
	/**
	 * y用戶填寫的名稱或者手機號碼
	 */
	private String name;

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
    /**
     * 
     * @param name
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 *  默認手機號
	 */
	private String tel;
	/**
	 * 
	 * @return
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 密碼
	 */
	private String password;
	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
