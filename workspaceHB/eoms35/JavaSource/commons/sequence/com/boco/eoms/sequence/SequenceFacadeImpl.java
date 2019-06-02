package com.boco.eoms.sequence;

import com.boco.eoms.sequence.exception.SequenceNotFoundException;

/**
 * <p>
 * Title:sequence对外暴露的门面
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 10:05:49 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SequenceFacadeImpl implements ISequenceFacade {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sequence.ISequenceJob#doJob(java.lang.Object,
	 *      java.lang.String, java.lang.Class[], java.lang.Object[],
	 *      com.boco.eoms.sequence.ISequenceCallback)
	 */
	public void doJob(Object clz, String methodName, Class[] paramTypes,
			Object[] params, ISequenceCallback callback) {
		Job job = new Job(clz, methodName, paramTypes, params, callback);
		SequencePool.getInstance().getSequence().put(job);
		SequencePool.getInstance().getSequence().notifyObservers();
	}

	/**
	 * 从序列池中取一个序列
	 * 
	 * @return 序列
	 */
	public Sequence getSequence() {
		return SequencePool.getInstance().getSequence();
	}

	/**
	 * 根据序列名称从序列池中取一个序列
	 * 
	 * @param key
	 *            序列名称
	 * @return 序列
	 * @throws SequenceNotFoundException
	 *             当没有此序列时抛出异常
	 */
	public Sequence getSequence(String key) throws SequenceNotFoundException {
		return SequencePool.getInstance().getSequence(key);
	}

	/**
	 * 推入序列
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
	 *            序列
	 * 
	 */
	public void put(Object clz, String methodName, Class[] paramTypes,
			Object[] params, ISequenceCallback callback, Sequence sequence) {
		Job job = new Job(clz, methodName, paramTypes, params, callback);
		sequence.put(job);
	}

	/**
	 * 执行sequence
	 * 
	 * @param sequence
	 *            序列
	 */
	public void doJob(Sequence sequence) {
		sequence.notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sequence.ISequenceFacade#doJob(com.boco.eoms.sequence.Job)
	 */
	public void doJob(Job job) {
		this.doJob(job.getClz(), job.getMethodName(), job.getParamTypes(), job
				.getParams(), job.getSequenceCallback());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sequence.ISequenceFacade#put(com.boco.eoms.sequence.Job,
	 *      com.boco.eoms.sequence.Sequence)
	 */
	public void put(Job job, Sequence sequence) {
		this.put(job.getClz(), job.getMethodName(), job.getParamTypes(), job
				.getParams(), job.getSequenceCallback(), sequence);
	}
	public void putMQ(Job job, Sequence sequence) {
		sequence.put(job);
	}
}
