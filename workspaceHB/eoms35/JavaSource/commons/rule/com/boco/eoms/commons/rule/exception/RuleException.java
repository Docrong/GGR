package com.boco.eoms.commons.rule.exception;

import com.boco.eoms.commons.exceptions.base.BusinessException;

/**
 * <p>
 * Title:规则异常基类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 10:08:40 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleException extends BusinessException {

	/**
	 * 
	 */
	public RuleException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorMessage
	 */
	public RuleException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public RuleException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
