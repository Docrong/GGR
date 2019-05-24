package com.boco.eoms.pq.exception;

import com.boco.eoms.commons.exceptions.base.BusinessException;

public class PQException extends BusinessException {

	private static final long serialVersionUID = -8869256639955056354L;

	public PQException() {
		super();
	}

	public PQException(String errorMessage) {
		super(errorMessage);
	}

	public PQException(Throwable cause) {
		super(cause);
	}
}
