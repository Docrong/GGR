package com.boco.eoms.sequence.exception;

import com.boco.eoms.commons.exceptions.base.BusinessException;

/**
 * <p>
 * Title:序列根异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 9:12:01 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SequenceException extends BusinessException {

	/**
	 * 自动生成序列号
	 */
	private static final long serialVersionUID = -5657552181536018418L;

	public SequenceException() {
		super();
	}

	public SequenceException(String errorMessage) {
		super(errorMessage);
	}

	public SequenceException(Throwable cause) {
		super(cause);
	}

}
