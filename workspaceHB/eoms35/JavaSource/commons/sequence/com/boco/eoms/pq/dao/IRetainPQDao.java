package com.boco.eoms.pq.dao;

import java.util.List;

import com.boco.eoms.pq.model.RetainPQ;

/**
 * 二级持久队列数据层接口
 * 
 * @author qiuzi
 * 
 */
public interface IRetainPQDao {

	/**
	 * 持久化任务到二级持久队列
	 * 
	 * @param retainPQ
	 *            二级持久化队列任务
	 * @return 任务ID
	 */
	public String saveRetainPQ(RetainPQ retainPQ);

	/**
	 * 根据任务ID查询二级持久队列中的任务
	 * 
	 * @param jobId
	 *            任务ID
	 * @return
	 */
	public RetainPQ getRetainPQ(String jobId);

	/**
	 * 根据条件查询二级持久队列中所有任务，按进入队列时间排序，先进先出
	 * 
	 * @param status
	 *            状态
	 * @param deleted
	 *            删除标志
	 */
	public List listRetainPQs(String status, String deleted);

	/**
	 * 物理删除二级持久队列指定任务
	 * 
	 * @param retainPQ
	 *            序列化任务
	 */
	public void delRetainPQ(RetainPQ retainPQ);

	/**
	 * 逻辑删除二级持久队列指定任务
	 * 
	 * @param retainPQ
	 *            序列化任务
	 */
	public void removeRetainPQ(RetainPQ retainPQ);
}
