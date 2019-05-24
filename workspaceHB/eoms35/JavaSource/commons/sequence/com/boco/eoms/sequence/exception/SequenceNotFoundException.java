package com.boco.eoms.sequence.exception;

/**
 * <p>
 * Title:当找不到sequence时抛出异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 28, 2008 4:44:38 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SequenceNotFoundException extends SequenceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2808255486664843615L;

	/**
	 * 
	 */
	public SequenceNotFoundException() {
		super();
	}

	/**
	 * @param errorMessage
	 */
	public SequenceNotFoundException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * @param cause
	 */
	public SequenceNotFoundException(Throwable cause) {
		super(cause);
	}

}
