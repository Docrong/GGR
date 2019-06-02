package com.boco.eoms.commons.exceptions.base;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String errorMessage = null;

	protected final Log log = LogFactory.getLog(getClass());

	public BusinessException() {
		super("Error occurred in application.");
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String errorMessage) {
		super(errorMessage);
	}

	public String getMessage() {
		String msg = super.getMessage();
		return msg;
	}

	public void printStackTrace() {
		super.printStackTrace();
	}

	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
	}

	public void printStackTrace(PrintWriter w) {
		super.printStackTrace(w);
	}

}
