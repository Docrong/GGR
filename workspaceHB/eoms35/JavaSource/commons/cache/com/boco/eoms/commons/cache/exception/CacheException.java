package com.boco.eoms.commons.cache.exception;

import com.boco.eoms.commons.exceptions.base.BusinessException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 2, 2007 8:58:24 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class CacheException extends BusinessException {

	/**
	 * 
	 */
	public CacheException() {
		super();
	}

	/**
	 * @param errorMessage
	 */
	public CacheException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * @param cause
	 */
	public CacheException(Throwable cause) {
		super(cause);
	}

}
