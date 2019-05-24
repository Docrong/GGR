/**
 * 菜单方案及内容部分异常基类
 */
package com.boco.eoms.commons.system.priv.exception;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class MenuBaseException extends PrivBaseException {

	public MenuBaseException() {
		this("Base Exception for Menu Management.");
	}

	public MenuBaseException(String errorMessage) {
		super(errorMessage);
	}

}
