/**
 * 基础数据配置部分异常基类
 */
package com.boco.eoms.commons.system.priv.exception;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class CfgBaseException extends PrivBaseException {

	public CfgBaseException() {
		this("Base Exception for Basic Information.");
	}

	public CfgBaseException(String errorMessage) {
		super(errorMessage);
	}

}
