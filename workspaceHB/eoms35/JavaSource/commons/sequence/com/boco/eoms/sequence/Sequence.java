package com.boco.eoms.sequence;

import java.util.Observable;
import java.util.Vector;

/**
 * <p>
 * Title:序列
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 11:20:33 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class Sequence extends Observable {

	/**
	 * 序列,vector同步
	 */
	private Vector sequence;
	
	private static SequenceObserver sequenceObserver=new SequenceObserver();

	/**
	 * 是否有线程在运行
	 */
	private boolean isThreadRunning;

	/**
	 * 构造方法
	 */
	public Sequence() {
		super();
		this.sequence = new Vector();
		// 设置观察者
		this.addObserver(sequenceObserver);
		// 设置无线程在执行
		this.isThreadRunning = false;
	}

	/**
	 * 判断是否有线程在执行
	 * 
	 * @return 有线程执行否
	 */
	public boolean isThreadRunning() {
		return isThreadRunning;
	}

	/**
	 * 设置是否有线程在执行
	 * 
	 * @param isThreadRunning
	 *            线程的运行状态
	 * 
	 */
	public synchronized void setThreadRunning(boolean isThreadRunning) {
		this.isThreadRunning = isThreadRunning;
	}

	/**
	 * 将调用方法加入序列
	 * 
	 * @param job
	 *            调用方法
	 */
	public synchronized void put(Job job) {
		this.sequence.add(job);
		// 改变,观察者模式
		// this.setChanged();
	}

	/**
	 * 使观察者察觉
	 */
	public synchronized void setChanged() {
		super.setChanged();
	}

	/**
	 * 取序列工作
	 * 
	 * @return 序列内容
	 */
	public synchronized Job next() throws SecurityException {
		// 判断序列是否为空
		if (this.sequence.isEmpty()) {
			throw new SecurityException("序列中暂无工作");
		}
		// 取序列第一个工作
		Job job = (Job) this.sequence.firstElement();
		// 删除第一个工作
		this.sequence.remove(job);
		return job;
	}

	/**
	 * 序列总数
	 * 
	 * @return
	 */
	public int total() {
		return this.sequence.size();
	}

	/**
	 * 序列是否为空
	 * 
	 * @return
	 */
	public boolean hasNext() {
		return this.sequence.isEmpty();
	}

}
