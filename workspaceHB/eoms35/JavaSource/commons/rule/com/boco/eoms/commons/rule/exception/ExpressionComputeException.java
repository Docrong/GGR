package com.boco.eoms.commons.rule.exception;

/**
 * <p>
 * Title: 表达式计算异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 22, 2007 2:34:10 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ExpressionComputeException extends ExpressionException {

	public ExpressionComputeException() {
		super();
	}

	public ExpressionComputeException(String errorMessage) {
		super(errorMessage);
	}

	public ExpressionComputeException(Throwable cause) {
		super(cause);
	}

}
