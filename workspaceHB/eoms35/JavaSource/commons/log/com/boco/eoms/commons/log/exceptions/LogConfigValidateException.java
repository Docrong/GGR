/**
 * 
 */
package com.boco.eoms.commons.log.exceptions;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:验证日志配置时抛出
 * </p>
 * <p>
 * Date:Jun 4, 2008 11:29:07 AM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class LogConfigValidateException extends LogException {

	/**
	 * 
	 */
	public LogConfigValidateException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorMessage
	 */
	public LogConfigValidateException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public LogConfigValidateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
