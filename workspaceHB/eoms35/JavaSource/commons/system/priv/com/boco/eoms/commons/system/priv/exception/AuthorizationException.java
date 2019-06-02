package com.boco.eoms.commons.system.priv.exception;

/**
 * <p>
 * Title:赋权异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jul 3, 2008 3:56:16 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class AuthorizationException extends PrivBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7428937749632380734L;

	public AuthorizationException() {
		super();
	}

	public AuthorizationException(String errorMessage) {
		super(errorMessage);
	}

	public AuthorizationException(Throwable cause) {
		super(cause);
	}
	
	

}
