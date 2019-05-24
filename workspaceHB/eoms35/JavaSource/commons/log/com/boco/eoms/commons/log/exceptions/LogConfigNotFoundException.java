/**
 * 
 */
package com.boco.eoms.commons.log.exceptions;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 在树中找不到对应的节点是抛出
 * </p>
 * <p>
 * Date:Jun 6, 2008 5:23:24 PM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class LogConfigNotFoundException extends LogException {

	/**
	 * 
	 */
	public LogConfigNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorMessage
	 */
	public LogConfigNotFoundException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public LogConfigNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
