package com.boco.eoms.pq.dao;

import java.util.List;

import com.boco.eoms.pq.model.InitPQ;

/**
 * 一级持久队列数据层接口
 * 
 * @author qiuzi
 * 
 */
public interface IInitPQDao {

	/**
	 * 持久化任务到一级持久队列
	 * 
	 * @param initPQ
	 *            一级持久化队列任务
	 * @return 任务ID
	 */
	public String saveInitPQ(InitPQ initPQ);

	/**
	 * 根据任务ID查询一级持久队列中的任务
	 * 
	 * @param jobId
	 *            任务ID
	 * @return
	 */
	public InitPQ getInitPQ(String jobId);

	/**
	 * 根据条件查询一级持久队列中所有任务，按进入队列时间排序，先进先出
	 * @param status 状态
	 * @param deleted 删除标志
	 */
	public List listInitPQs(String status, String deleted);

	/**
	 * 物理删除一级持久队列指定任务
	 * 
	 * @param initPQ
	 *            序列化任务
	 */
	public void delInitPQ(InitPQ initPQ);

	/**
	 * 逻辑删除一级持久队列指定任务
	 * 
	 * @param initPQ
	 *            序列化任务
	 */
	public void removeInitPQ(InitPQ initPQ);
}
