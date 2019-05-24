/**
 * 
 */
package com.boco.eoms.commons.log.exceptions;

import com.boco.eoms.commons.exceptions.base.BusinessException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:	日志异常超类
 * </p>
 * <p>
 * Date:Jun 4, 2008 10:24:34 AM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class LogException extends BusinessException {

	/**
	 * 
	 */
	public LogException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorMessage
	 */
	public LogException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public LogException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
