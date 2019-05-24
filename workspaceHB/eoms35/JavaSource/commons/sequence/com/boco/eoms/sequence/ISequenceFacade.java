package com.boco.eoms.sequence;

import com.boco.eoms.sequence.exception.SequenceNotFoundException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 9:52:13 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ISequenceFacade {

	/**
	 * 进入序列，准备执行方法
	 * 
	 * @param clz
	 *            要调用的类
	 * @param methodName
	 *            调用类的某个方法
	 * @param paramTypes
	 *            参数类型
	 * @param params
	 *            参数
	 * @param callback
	 *            序列方法调用完成后回调（通知）的实现类
	 */
	public void doJob(Object clz, String methodName, Class paramTypes[],
			Object params[], ISequenceCallback callback);

	/**
	 * 进入序列，准备执行方法
	 * 
	 * @param job
	 *            要调用的类
	 * 
	 */
	public void doJob(Job job);

	/**
	 * 从序列池中取一个序列
	 * 
	 * @return 序列
	 */
	public Sequence getSequence();

	/**
	 * 推入序列
	 * 
	 * @param clz
	 *            要调用的类相关信息
	 * 
	 * @param sequence
	 *            序列
	 * 
	 */
	public void put(Job job, Sequence sequence);

	/**
	 * 执行sequence
	 * 
	 * @param sequence
	 *            序列
	 */
	public void doJob(Sequence sequence);

	/**
	 * 进入序列，准备执行方法
	 * 
	 * @param clz
	 *            要调用的类
	 * @param methodName
	 *            调用类的某个方法
	 * @param paramTypes
	 *            参数类型
	 * @param params
	 *            参数
	 * @param callback
	 *            序列方法调用完成后回调（通知）的实现类
	 * @param sequence
	 *            进入sequence序列
	 */
	public void put(Object clz, String methodName, Class[] paramTypes,
			Object[] params, ISequenceCallback callback, Sequence sequence);

	/**
	 * 根据序列名称从序列池中取一个序列
	 * 
	 * @param key
	 *            序列名称
	 * @return 序列
	 * @throws SequenceNotFoundException
	 *             当没有此序列时抛出异常
	 */
	public Sequence getSequence(String key) throws SequenceNotFoundException;
	public void putMQ(Job job, Sequence sequence);
}
