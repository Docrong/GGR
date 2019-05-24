package com.boco.eoms.commons.rule.exception;

/**
 * <p>
 * Title: 分析关键字异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 18, 2007 2:55:09 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class KeywordSyntaxException extends KeywordResultException {

	public KeywordSyntaxException() {
		super();
	}

	public KeywordSyntaxException(String errorMessage) {
		super(errorMessage);
	}

	public KeywordSyntaxException(Throwable cause) {
		super(cause);
	}

}
