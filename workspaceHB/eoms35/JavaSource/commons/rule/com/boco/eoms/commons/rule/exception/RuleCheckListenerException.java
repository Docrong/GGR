package com.boco.eoms.commons.rule.exception;

/**
 * <p>
 * Title: 规则验证错误listener,当规则配置文件不正确,将抛出该exception
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 8:13:39 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleCheckListenerException extends RuleException {

	/**
	 * 
	 */
	public RuleCheckListenerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorMessage
	 */
	public RuleCheckListenerException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public RuleCheckListenerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
