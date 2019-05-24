package com.boco.eoms.pq.facade;

import java.util.List;

import com.boco.eoms.sequence.Job;

/**
 * 持久队列门面
 * 
 * @author qiuzi
 * 
 */
public interface IPQFacade {

	/**
	 * 将需要执行的任务推入持久队列
	 * 
	 * @param job
	 *            任务
	 * @return 推入队列的任务ID
	 */
	public String putPQ(Job job);

	/**
	 * 将需要执行的任务列表推入持久队列
	 * 
	 * @param jobList
	 *            任务列表
	 * @return 推入队列的任务ID数组
	 */
	public String[] putPQs(List jobList);

	/**
	 * 执行持久队列中所有任务
	 */
	public void doPQs();

	/**
	 * 执行持久队列中指定的任务
	 * 
	 * @param jobId
	 *            任务ID
	 */
	public void doPQ(String jobId);

	/**
	 * 取消持久队列中所有任务
	 */
	public void cancelPQs();

	/**
	 * 取消持久队列中指定的任务
	 * 
	 * @param jobId
	 *            任务ID
	 */
	public void cancelPQ(String jobId);
}
