/**
 * 授权部分异常基类
 */
package com.boco.eoms.commons.system.priv.exception;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class AuthBaseException extends PrivBaseException {

	public AuthBaseException() {
		this("Base Exception for Authention.");
	}

	public AuthBaseException(String errorMessage) {
		super(errorMessage);
	}

}
