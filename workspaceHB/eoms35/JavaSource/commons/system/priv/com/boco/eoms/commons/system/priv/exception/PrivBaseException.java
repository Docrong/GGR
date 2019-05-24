/**
 * 权限部分异常基类
 */
package com.boco.eoms.commons.system.priv.exception;

import com.boco.eoms.commons.exceptions.base.BusinessException;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class PrivBaseException extends BusinessException {

	public PrivBaseException() {
		this("Base Exception for privilege.");
	}

	public PrivBaseException(String errorMessage) {
		super(errorMessage);
	}

	public PrivBaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
