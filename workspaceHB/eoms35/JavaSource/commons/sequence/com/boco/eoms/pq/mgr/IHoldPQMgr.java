package com.boco.eoms.pq.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.pq.exception.CancelPQErrorException;
import com.boco.eoms.pq.exception.DoPQErrorException;
import com.boco.eoms.pq.exception.PutPQErrorException;
import com.boco.eoms.pq.model.HoldPQ;
import com.boco.eoms.sequence.Job;

/**
 * 三级持久队列业务接口
 * 
 * @author qiuzi
 * 
 */
public interface IHoldPQMgr {

	/**
	 * 持久化任务到三级持久队列
	 * 
	 * @param job
	 *            任务
	 * @return 任务ID
	 * @throws PutPQErrorException
	 */
	public String putHoldPQ(Job job) throws PutPQErrorException;

	/**
	 * 持久化多任务到三级持久队列
	 * 
	 * @param jobList
	 *            List<Job> 任务列表
	 * @return 任务ID数组
	 * @throws PutPQErrorException
	 */
	public String[] putHoldPQs(List jobList) throws PutPQErrorException;

	/**
	 * 执行三级持久队列中的所有任务
	 * 
	 * @throws DoPQErrorException
	 */
	public void doHoldPQs() throws DoPQErrorException;

	/**
	 * 执行三级持久队列中指定任务
	 * 
	 * @param jobId
	 *            任务ID
	 * @throws DoPQErrorException
	 */
	public void doHoldPQ(String jobId) throws DoPQErrorException;

	/**
	 * 取消三级持久队列中所有任务
	 * 
	 * @throws CancelPQErrorException
	 */
	public void cancelHoldPQs() throws CancelPQErrorException;

	/**
	 * 取消三级持久队列指定任务
	 * 
	 * @param jobId
	 *            任务ID
	 * @throws CancelPQErrorException
	 */
	public void cancelHoldPQ(String jobId) throws CancelPQErrorException;

	/**
	 * 物理删除三级持久队列中的任务
	 * 
	 * @param jobId
	 *            任务ID
	 */
	public void delHoldPQ(String jobId);

	/**
	 * 逻辑删除三级持久队列中的任务
	 * 
	 * @param jobId
	 *            任务ID
	 */
	public void removeHoldPQ(String jobId);

	/**
	 * 根据条件查询三级持久队列中所有任务，按进入队列时间排序，先进先出
	 * 
	 * @param status
	 *            状态
	 * @param deleted
	 *            删除标志
	 */
	public List listHoldPQs(String status, String deleted);

	/**
	 * （分页）根据条件查询三级持久队列中所有任务，按进入队列时间排序，先进先出
	 * 
	 * @param curPage
	 *            当前页
	 * @param pageSize
	 *            每页显示数
	 * @param status
	 *            状态
	 * @param deleted
	 *            删除标志
	 * @return
	 */
	public Map listHoldPQs(final Integer curPage, final Integer pageSize,
			final String status, final String deleted);

	/**
	 * 根据jobId查找HoldPQ
	 * 
	 * @param jobId
	 *            任务ID
	 * @return
	 */
	public HoldPQ getHoldPQ(String jobId);
}
