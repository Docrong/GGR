package com.boco.eoms.pq.callback;

import java.io.Serializable;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.pq.mgr.IInitPQMgr;
import com.boco.eoms.sequence.ISequenceCallback;
import com.boco.eoms.sequence.Job;
import com.boco.eoms.sequence.exception.SequenceException;

public class InitPQCallbackImpl implements ISequenceCallback, Serializable {

	/**
	 * 自动生成序列号
	 */
	private static final long serialVersionUID = -2496441880297511940L;

	/**
	 * 一级队列执行失败回调方法
	 */
	public void fail(Job job, Throwable e) {
		IInitPQMgr initPQMgr = (IInitPQMgr) ApplicationContextHolder
				.getInstance().getBean("initPQMgr");
		// 调用一级持久队列执行失败处理方法
		initPQMgr.processFailJob(job);
	}

	/**
	 * 一级队列执行成功回调方法
	 */
	public void success(Job job, Object result) throws SequenceException {
		IInitPQMgr initPQMgr = (IInitPQMgr) ApplicationContextHolder
				.getInstance().getBean("initPQMgr");
		// 执行成功删除一级持久队列中的任务
		initPQMgr.delInitPQ(job.getJobId());
	}

}
