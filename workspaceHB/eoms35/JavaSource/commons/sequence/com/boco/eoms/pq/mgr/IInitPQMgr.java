package com.boco.eoms.pq.mgr;

import java.util.List;

import com.boco.eoms.pq.exception.CancelPQErrorException;
import com.boco.eoms.pq.exception.DoPQErrorException;
import com.boco.eoms.pq.exception.PutPQErrorException;
import com.boco.eoms.sequence.Job;

/**
 * 一级持久队列业务接口
 * 
 * @author qiuzi
 * 
 */
public interface IInitPQMgr {

	/**
	 * 持久化任务到一级持久队列
	 * 
	 * @param job
	 *            任务
	 * @return 任务ID
	 * @throws PutPQErrorException
	 */
	public String putInitPQ(Job job) throws PutPQErrorException;

	/**
	 * 持久化多任务到一级持久队列
	 * 
	 * @param jobList
	 *            List<Job> 任务列表
	 * @return 任务ID数组
	 * @throws PutPQErrorException
	 */
	public String[] putInitPQs(List jobList) throws PutPQErrorException;

	/**
	 * 执行一级持久队列中的所有任务
	 * 
	 * @throws DoPQErrorException
	 */
	public void doInitPQs() throws DoPQErrorException;

	/**
	 * 执行一级持久队列中指定任务
	 * 
	 * @param jobId
	 *            任务ID
	 * @throws DoPQErrorException
	 */
	public void doInitPQ(String jobId) throws DoPQErrorException;

	/**
	 * 取消一级持久队列中所有任务
	 * 
	 * @throws CancelPQErrorException
	 */
	public void cancelInitPQs() throws CancelPQErrorException;

	/**
	 * 取消一级持久队列指定任务
	 * 
	 * @param jobId
	 *            任务ID
	 * @throws CancelPQErrorException
	 */
	public void cancelInitPQ(String jobId) throws CancelPQErrorException;

	/**
	 * 物理删除一级持久队列中的任务
	 * 
	 * @param jobId
	 *            任务ID
	 */
	public void delInitPQ(String jobId);

	/**
	 * 逻辑删除一级持久队列中的任务
	 * 
	 * @param jobId
	 *            任务ID
	 */
	public void removeInitPQ(String jobId);

	/**
	 * InitPQ任务执行失败后的处理方法
	 * 
	 * @param job
	 *            执行失败的任务
	 */
	public void processFailJob(Job job);
}
