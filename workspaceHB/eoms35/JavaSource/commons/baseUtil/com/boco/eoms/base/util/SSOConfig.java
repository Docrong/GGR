package com.boco.eoms.base.util;

/**
 * sso配置文件
 * 
 * @author leo
 * 
 */
public class SSOConfig implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7127109654036682801L;

	/**
	 * cas服务器地址
	 */
	private String casLogin;

	/**
	 * eoms服务器地址
	 */
	private String eomsServerName;

	public String getCasLogin() {
		return casLogin;
	}

	public void setCasLogin(String casLogin) {
		this.casLogin = casLogin;
	}

	public String getEomsServerName() {
		return eomsServerName;
	}

	public void setEomsServerName(String eomsServerName) {
		this.eomsServerName = eomsServerName;
	}

}
