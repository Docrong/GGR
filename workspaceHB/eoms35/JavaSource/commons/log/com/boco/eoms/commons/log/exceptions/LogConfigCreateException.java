/**
 * 
 */
package com.boco.eoms.commons.log.exceptions;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 当读取日志配置文件出错时抛出。
 * </p>
 * <p>
 * Date:Jun 4, 2008 10:37:36 AM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class LogConfigCreateException extends LogException {

	/**
	 * 
	 */
	public LogConfigCreateException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorMessage
	 */
	public LogConfigCreateException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public LogConfigCreateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
