package com.boco.eoms.commons.rule.exception;

/**
 * <p>
 * Title:计算关键字值异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 18, 2007 3:50:23 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class KeywordResultException extends ExpressionParseException {

	public KeywordResultException() {
		super();
	}

	public KeywordResultException(String errorMessage) {
		super(errorMessage);
	}

	public KeywordResultException(Throwable cause) {
		super(cause);
	}

}
