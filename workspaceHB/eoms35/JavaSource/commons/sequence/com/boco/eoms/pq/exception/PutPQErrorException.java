package com.boco.eoms.pq.exception;

/**
 * 向持久队列中put任务出错时抛出此异常
 * 
 * @author qiuzi
 * 
 */
public class PutPQErrorException extends PQException {

	private static final long serialVersionUID = -3857172928476070647L;

	public PutPQErrorException() {
		super();
	}

	public PutPQErrorException(String errorMessage) {
		super(errorMessage);
	}

	public PutPQErrorException(Throwable cause) {
		super(cause);
	}
}
