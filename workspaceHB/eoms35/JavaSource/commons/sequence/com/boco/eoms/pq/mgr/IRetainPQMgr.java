package com.boco.eoms.pq.mgr;

import java.util.List;

import com.boco.eoms.pq.exception.CancelPQErrorException;
import com.boco.eoms.pq.exception.DoPQErrorException;
import com.boco.eoms.pq.exception.PutPQErrorException;
import com.boco.eoms.sequence.Job;

/**
 * 二级持久队列业务接口
 * 
 * @author qiuzi
 * 
 */
public interface IRetainPQMgr {

	/**
	 * 持久化任务到二级持久队列
	 * 
	 * @param job
	 *            任务
	 * @return 任务ID
	 * @throws PutPQErrorException
	 */
	public String putRetainPQ(Job job) throws PutPQErrorException;

	/**
	 * 持久化多任务到二级持久队列
	 * 
	 * @param jobList
	 *            List<Job> 任务列表
	 * @return 任务ID数组
	 * @throws PutPQErrorException
	 */
	public String[] putRetainPQs(List jobList) throws PutPQErrorException;

	/**
	 * 执行二级持久队列中的所有任务
	 * 
	 * @throws DoPQErrorException
	 */
	public void doRetainPQs() throws DoPQErrorException;

	/**
	 * 执行二级持久队列中指定任务
	 * 
	 * @param jobId
	 *            任务ID
	 * @throws DoPQErrorException
	 */
	public void doRetainPQ(String jobId) throws DoPQErrorException;

	/**
	 * 取消二级持久队列中所有任务
	 * 
	 * @throws CancelPQErrorException
	 */
	public void cancelRetainPQs() throws CancelPQErrorException;

	/**
	 * 取消二级持久队列指定任务
	 * 
	 * @param jobId
	 *            任务ID
	 * @throws CancelPQErrorException
	 */
	public void cancelRetainPQ(String jobId) throws CancelPQErrorException;

	/**
	 * 物理删除二级持久队列中的任务
	 * 
	 * @param jobId
	 *            任务ID
	 */
	public void delRetainPQ(String jobId);

	/**
	 * 逻辑删除二级持久队列中的任务
	 * 
	 * @param jobId
	 *            任务ID
	 */
	public void removeRetainPQ(String jobId);
	
	/**
	 * RetainPQ任务执行失败后的处理方法
	 * 
	 * @param job
	 *            执行失败的任务
	 */
	public void processFailJob(Job job);
}
