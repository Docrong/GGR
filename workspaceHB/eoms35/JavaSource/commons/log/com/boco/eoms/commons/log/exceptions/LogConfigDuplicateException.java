/**
 * 
 */
package com.boco.eoms.commons.log.exceptions;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 当发现在同一个模块里（modelId），有多个相同的operId节点时报
 * </p>
 * <p>
 * Date:Jun 11, 2008 1:55:56 PM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class LogConfigDuplicateException extends LogException {

	/**
	 * 
	 */
	public LogConfigDuplicateException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorMessage
	 */
	public LogConfigDuplicateException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public LogConfigDuplicateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
