package com.boco.eoms.pq.exception;

/**
 * 执行持久队列中任务出错时抛出此异常
 * 
 * @author qiuzi
 * 
 */
public class DoPQErrorException extends PQException {

	private static final long serialVersionUID = 2266184737873293404L;

	public DoPQErrorException() {
		super();
	}

	public DoPQErrorException(String errorMessage) {
		super(errorMessage);
	}

	public DoPQErrorException(Throwable cause) {
		super(cause);
	}
}
