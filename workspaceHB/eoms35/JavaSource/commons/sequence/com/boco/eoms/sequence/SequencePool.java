package com.boco.eoms.sequence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

/**
 * <p>
 * Title:序列池
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 2:30:01 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SequencePool {
	/**
	 * 单例
	 */
	private static SequencePool instance = null;

	/**
	 * 序列池
	 */
	private static Map pool;

	/**
	 * 统一(全局)序列
	 */
	private Sequence sequence;

	/**
	 * 构造序列池
	 * 
	 * 
	 */
	private SequencePool() {
		// 初使化全局序列
		sequence = new Sequence();
		SequencePool.pool = new HashMap();
		// 初始化模块池
		for (Iterator it = SequenceLocator.getSequenceRegister().getRegister()
				.iterator(); it.hasNext();) {
			String key = (String) it.next();
			SequencePool.pool.put(key, new Sequence());
		}

	}

	/**
	 * 获取pool实例
	 * 
	 * @return
	 */
	public synchronized static SequencePool getInstance() {
		if (null == instance) {
			// 初使化1个序列池
			instance = new SequencePool();
		}
		return instance;

	}

	/**
	 * 在线程池中取序列
	 * 
	 * @return 线池中的序列
	 */
	public Sequence getSequence() {
		// 目前只针对一个序列
		return sequence;
	}

	/**
	 * 通过注册序列中取出相应的序列池
	 * 
	 * @param key
	 *            注册序列关键字
	 * @return
	 */
	public Sequence getSequence(String key) throws SequenceNotFoundException {
		if (!SequencePool.pool.containsKey(key)) {
			throw new SequenceNotFoundException("The " + key
					+ " Sequence is not found");
		} else {
			return (Sequence) SequencePool.pool.get(key);
		}
	}
}
