package com.boco.eoms.pq.callback;

import java.io.Serializable;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.pq.mgr.IRetainPQMgr;
import com.boco.eoms.sequence.ISequenceCallback;
import com.boco.eoms.sequence.Job;
import com.boco.eoms.sequence.exception.SequenceException;

public class RetainPQCallbackImpl implements ISequenceCallback, Serializable {

	/**
	 * 自动生成序列号
	 */
	private static final long serialVersionUID = 4206932204011348742L;

	/**
	 * 二级队列执行失败回调方法
	 */
	public void fail(Job job, Throwable e) {
		IRetainPQMgr retainPQMgr = (IRetainPQMgr) ApplicationContextHolder
				.getInstance().getBean("retainPQMgr");
		// 调用二级持久队列执行失败处理方法
		retainPQMgr.processFailJob(job);
	}

	/**
	 * 二级队列执行成功回调方法
	 */
	public void success(Job job, Object result) throws SequenceException {
		IRetainPQMgr retainPQMgr = (IRetainPQMgr) ApplicationContextHolder
				.getInstance().getBean("retainPQMgr");
		// 执行成功删除二级持久队列中的任务
		retainPQMgr.delRetainPQ(job.getJobId());
	}

}
