package com.boco.eoms.commons.rule.exception;

/**
 * 
 * <p>
 * Title:表达式解析异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 16, 2007 7:52:07 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ExpressionParseException extends ExpressionException {

	public ExpressionParseException() {
		super();
	}

	public ExpressionParseException(String errorMessage) {
		super(errorMessage);
	}

	public ExpressionParseException(Throwable cause) {
		super(cause);
	}

}
