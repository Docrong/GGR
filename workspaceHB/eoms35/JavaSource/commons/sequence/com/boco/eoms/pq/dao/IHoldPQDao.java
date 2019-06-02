package com.boco.eoms.pq.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.pq.model.HoldPQ;

/**
 * 三级持久队列数据层接口
 * 
 * @author qiuzi
 * 
 */
public interface IHoldPQDao {

	/**
	 * 持久化任务到三级持久队列
	 * 
	 * @param holdPQ
	 *            三级持久化队列任务
	 * @return 任务ID
	 */
	public String saveHoldPQ(HoldPQ holdPQ);

	/**
	 * 根据任务ID查询三级持久队列中的任务
	 * 
	 * @param jobId
	 *            任务ID
	 * @return
	 */
	public HoldPQ getHoldPQ(String jobId);

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
	 * 物理删除三级持久队列指定任务
	 * 
	 * @param holdPQ
	 *            序列化任务
	 */
	public void delHoldPQ(HoldPQ holdPQ);

	/**
	 * 逻辑删除三级持久队列指定任务
	 * 
	 * @param holdPQ
	 *            序列化任务
	 */
	public void removeHoldPQ(HoldPQ holdPQ);

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

}
