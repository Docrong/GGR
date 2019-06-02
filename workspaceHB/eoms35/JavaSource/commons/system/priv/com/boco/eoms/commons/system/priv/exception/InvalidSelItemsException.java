/**
 * 
 */
package com.boco.eoms.commons.system.priv.exception;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class InvalidSelItemsException extends MenuBaseException {

	public InvalidSelItemsException() {
		this("No invalid items for select.");
	}

	public InvalidSelItemsException(String errorMessage) {
		super(errorMessage);
	}

}
