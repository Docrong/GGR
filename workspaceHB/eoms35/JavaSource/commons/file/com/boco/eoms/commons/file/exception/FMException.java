package com.boco.eoms.commons.file.exception;

import com.boco.eoms.commons.exceptions.base.BusinessException;

/**
 * <p>
 * Title:异常基类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 28, 2007 2:31:25 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FMException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FMException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	public FMException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
