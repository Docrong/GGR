package com.boco.eoms.pq.exception;

/**
 * 取消持久队列中任务出错时抛出此异常
 * 
 * @author qiuzi
 * 
 */
public class CancelPQErrorException extends PQException {

	private static final long serialVersionUID = -1566555380004309143L;

	public CancelPQErrorException() {
		super();
	}

	public CancelPQErrorException(String errorMessage) {
		super(errorMessage);
	}

	public CancelPQErrorException(Throwable cause) {
		super(cause);
	}
}
