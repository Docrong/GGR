package com.boco.eoms.commons.cache.exception;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 2, 2007 8:59:50 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class EHCacheMgrException extends CacheException {

	/**
	 * 
	 */
	public EHCacheMgrException() {
		super();
	}

	/**
	 * @param errorMessage
	 */
	public EHCacheMgrException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * @param cause
	 */
	public EHCacheMgrException(Throwable cause) {
		super(cause);
	}

}
