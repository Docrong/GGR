package com.boco.eoms.sequence;

import com.boco.eoms.sequence.exception.SequenceException;

/**
 * <p>
 * Title:序列的通知（回调）接口
 * </p>
 * <p>
 * Description:用户实现该接口，当调用序列中的方法完成时会调用实现此接口方法的类
 * </p>
 * <p>
 * Date:Apr 24, 2008 9:03:05 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ISequenceCallback {
	/**
	 * 工作执行成功通知（回调）方法
	 * 
	 * @param job
	 *            执行的方法
	 * @param result
	 *            执行序列方法返回的结果
	 * @throws SequenceException
	 */
	public void success(Job job, Object result) throws SequenceException;

	/**
	 * 工作执行失败通知（回调）方法
	 * 
	 * @param job
	 *            执行的方法
	 * @param e
	 *            异常信息
	 */
	public void fail(Job job, Throwable e);
}
