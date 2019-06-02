package com.boco.eoms.commons.fileconfig.exception;

import com.boco.eoms.commons.exceptions.base.BusinessException;

/**
 * <p>
 * Title: 角析xml异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 3:04:42 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ParseXMLException extends BusinessException {

	public ParseXMLException() {
		super();
	}

	public ParseXMLException(String errorMessage) {
		super(errorMessage);
	}

	public ParseXMLException(Throwable cause) {
		super(cause);
	}

}
