package com.boco.eoms.commons.rule.exception;

/**
 * <p>
 * Title: 表达式异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 22, 2007 2:35:31 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ExpressionException extends RuleException {

	public ExpressionException() {
		super();
	}

	public ExpressionException(String errorMessage) {
		super(errorMessage);
	}

	public ExpressionException(Throwable cause) {
		super(cause);
	}

}
