package com.boco.eoms.pq.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.pq.dao.IRetainPQDao;
import com.boco.eoms.pq.exception.CancelPQErrorException;
import com.boco.eoms.pq.exception.DoPQErrorException;
import com.boco.eoms.pq.exception.PutPQErrorException;
import com.boco.eoms.pq.mgr.IHoldPQMgr;
import com.boco.eoms.pq.mgr.IRetainPQMgr;
import com.boco.eoms.pq.model.RetainPQ;
import com.boco.eoms.pq.util.Constants;
import com.boco.eoms.pq.util.PQConfigLocator;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Job;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

public class RetainPQMgrImpl extends BaseManager implements IRetainPQMgr {

	private IRetainPQDao retainPQDao;

	public void setRetainPQDao(IRetainPQDao retainPQDao) {
		this.retainPQDao = retainPQDao;
	}

	public void cancelRetainPQ(String jobId) throws CancelPQErrorException {
		// 从数据库中取出任务
		RetainPQ retainPQ = retainPQDao.getRetainPQ(jobId);
		if (!Constants.DELETED.equals(retainPQ.getDeleted())
				&& Constants.Q_STATUS_WAITING.equals(retainPQ.getStatus())) { // 未删除并且等待执行的任务
			retainPQDao.delRetainPQ(retainPQ);
		}
	}

	public void cancelRetainPQs() throws CancelPQErrorException {
		List list = retainPQDao.listRetainPQs(Constants.Q_STATUS_WAITING,
				Constants.UNDELETED);
		for (Iterator it = list.iterator(); it.hasNext();) {
			RetainPQ retainPQ = (RetainPQ) it.next();
			retainPQDao.delRetainPQ(retainPQ);
		}
	}

	public void doRetainPQs() throws DoPQErrorException {
		// 所有job存入MQ并执行MQ
		ISequenceFacade sequenceFacade = SequenceLocator.getSequenceFacade();
		Sequence retainMQSequence = null;
		try {
			retainMQSequence = sequenceFacade.getSequence("retainMQ");
		} catch (SequenceNotFoundException e) {
			e.printStackTrace();
		}
		List list = retainPQDao.listRetainPQs(Constants.Q_STATUS_WAITING,
				Constants.UNDELETED);
		for (Iterator it = list.iterator(); it.hasNext();) {
			RetainPQ retainPQ = (RetainPQ) it.next();
			sequenceFacade.putMQ(retainPQ.convert2Job(), retainMQSequence);
		}
		retainMQSequence.setChanged();
		sequenceFacade.doJob(retainMQSequence);
	}

	public void doRetainPQ(String jobId) throws DoPQErrorException {
		// 根据jobId查找并转换job
		RetainPQ retainPQ = retainPQDao.getRetainPQ(jobId);
		Job job = retainPQ.convert2Job();

		// job存入MQ并执行MQ
		ISequenceFacade sequenceFacade = SequenceLocator.getSequenceFacade();
		Sequence retainMQSequence = null;
		try {
			retainMQSequence = sequenceFacade.getSequence("retainMQ");
		} catch (SequenceNotFoundException e) {
			e.printStackTrace();
		}
		sequenceFacade.putMQ(job, retainMQSequence);
		retainMQSequence.setChanged();
		sequenceFacade.doJob(retainMQSequence);
	}

	public String putRetainPQ(Job job) throws PutPQErrorException {
		// job存入PQ
		RetainPQ retainPQ = new RetainPQ(job);
		retainPQDao.saveRetainPQ(retainPQ);
		return retainPQDao.saveRetainPQ(retainPQ);
	}

	public String[] putRetainPQs(List jobList) throws PutPQErrorException {
		String[] jobIds = new String[] {};
		List idList = new ArrayList();
		for (Iterator it = jobList.iterator(); it.hasNext();) {
			Job job = (Job) it.next();
			String jobId = putRetainPQ(job);
			idList.add(jobId);
		}
		jobIds = (String[]) idList.toArray();
		return jobIds;
	}

	public void delRetainPQ(String jobId) {
		RetainPQ retainPQ = retainPQDao.getRetainPQ(jobId);
		if (null != retainPQ) {
			retainPQDao.delRetainPQ(retainPQ);
		}
	}

	public void removeRetainPQ(String jobId) {
		RetainPQ retainPQ = retainPQDao.getRetainPQ(jobId);
		if (null != retainPQ) {
			retainPQDao.removeRetainPQ(retainPQ);
		}
	}

	public void processFailJob(Job job) {
		RetainPQ retainPQ = retainPQDao.getRetainPQ(job.getJobId());
		if (null == retainPQ) {
			return;
		}

		int maxCount = StaticMethod.null2int(PQConfigLocator.getPQConfig()
				.getMaxRetainPQFailCount());
		int currentCount = StaticMethod.null2int(retainPQ.getFailCount());

		if (currentCount < maxCount) {// 当前任务在retainPQ中执行失败的次数未超过设定的最大值
			// 将job的failCount加一，重新推入RetainPQ
			retainPQDao.delRetainPQ(retainPQ);
			retainPQ.setFailCount(String.valueOf(currentCount + 1));
			String jobId = retainPQDao.saveRetainPQ(retainPQ);

			// 继续执行retainPQ
			try {
				doRetainPQ(jobId);
			} catch (DoPQErrorException e) {
				System.out
						.println("***Error occurred while run job in RetainPQ!");
				e.printStackTrace();
			}
		} else {// 当前任务在retainPQ中执行失败的次数已超过设定的最大值
			// 将任务推入三级持久队列，但不执行
			retainPQDao.removeRetainPQ(retainPQ);
			IHoldPQMgr holdPQMgr = (IHoldPQMgr) ApplicationContextHolder
					.getInstance().getBean("holdPQMgr");
			// 强制将推入holdPQ的任务的callback方法设置为null
			job.setSequenceCallback(null);
			try {
				holdPQMgr.putHoldPQ(job);
			} catch (PutPQErrorException e) {
				System.out
						.println("***Error occurred while put job into HoldPQ!");
				e.printStackTrace();
			}
		}
	}
}
