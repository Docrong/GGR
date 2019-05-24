package com.boco.eoms.pq.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.pq.dao.IHoldPQDao;
import com.boco.eoms.pq.exception.CancelPQErrorException;
import com.boco.eoms.pq.exception.DoPQErrorException;
import com.boco.eoms.pq.exception.PutPQErrorException;
import com.boco.eoms.pq.mgr.IHoldPQMgr;
import com.boco.eoms.pq.model.HoldPQ;
import com.boco.eoms.pq.util.Constants;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Job;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

public class HoldPQMgrImpl extends BaseManager implements IHoldPQMgr {

	private IHoldPQDao holdPQDao;

	public void setHoldPQDao(IHoldPQDao holdPQDao) {
		this.holdPQDao = holdPQDao;
	}

	public void cancelHoldPQ(String jobId) throws CancelPQErrorException {
		// 从数据库中取出任务
		HoldPQ holdPQ = holdPQDao.getHoldPQ(jobId);
		if (!Constants.DELETED.equals(holdPQ.getDeleted())
				&& Constants.Q_STATUS_WAITING.equals(holdPQ.getStatus())) { // 未删除并且等待执行的任务
			holdPQDao.delHoldPQ(holdPQ);
		}
	}

	public void cancelHoldPQs() throws CancelPQErrorException {
		List list = holdPQDao.listHoldPQs(Constants.Q_STATUS_WAITING,
				Constants.UNDELETED);
		for (Iterator it = list.iterator(); it.hasNext();) {
			HoldPQ holdPQ = (HoldPQ) it.next();
			holdPQDao.delHoldPQ(holdPQ);
		}
	}

	public void doHoldPQs() throws DoPQErrorException {
		// 所有job存入MQ并执行MQ
		ISequenceFacade sequenceFacade = SequenceLocator.getSequenceFacade();
		Sequence initMQSequence = null;
		try {
			initMQSequence = sequenceFacade.getSequence("holdMQ");
		} catch (SequenceNotFoundException e) {
			e.printStackTrace();
		}
		List list = holdPQDao.listHoldPQs(Constants.Q_STATUS_ERROR,
				Constants.UNDELETED);
		for (Iterator it = list.iterator(); it.hasNext();) {
			HoldPQ holdPQ = (HoldPQ) it.next();
			Job job = holdPQ.convert2Job();
			sequenceFacade.putMQ(job, initMQSequence);
		}
		initMQSequence.setChanged();
		sequenceFacade.doJob(initMQSequence);
	}

	public void doHoldPQ(String jobId) throws DoPQErrorException {
		// 根据jobId查找并转换job
		HoldPQ holdPQ = holdPQDao.getHoldPQ(jobId);
		Job job = holdPQ.convert2Job();

		// job存入MQ并执行MQ
		ISequenceFacade sequenceFacade = SequenceLocator.getSequenceFacade();
		Sequence initMQSequence = null;
		try {
			initMQSequence = sequenceFacade.getSequence("holdMQ");
		} catch (SequenceNotFoundException e) {
			e.printStackTrace();
		}
		sequenceFacade.putMQ(job, initMQSequence);
		initMQSequence.setChanged();
		sequenceFacade.doJob(initMQSequence);
	}

	public String putHoldPQ(Job job) throws PutPQErrorException {
		// job存入PQ
		HoldPQ holdPQ = new HoldPQ(job);
		holdPQDao.saveHoldPQ(holdPQ);
		return holdPQDao.saveHoldPQ(holdPQ);
	}

	public String[] putHoldPQs(List jobList) throws PutPQErrorException {
		String[] jobIds = new String[] {};
		List idList = new ArrayList();
		for (Iterator it = jobList.iterator(); it.hasNext();) {
			Job job = (Job) it.next();
			String jobId = putHoldPQ(job);
			idList.add(jobId);
		}
		jobIds = (String[]) idList.toArray();
		return jobIds;
	}

	public void delHoldPQ(String jobId) {
		HoldPQ holdPQ = holdPQDao.getHoldPQ(jobId);
		if (null != holdPQ) {
			holdPQDao.delHoldPQ(holdPQ);
		}
	}

	public void removeHoldPQ(String jobId) {
		HoldPQ holdPQ = holdPQDao.getHoldPQ(jobId);
		if (null != holdPQ) {
			holdPQDao.removeHoldPQ(holdPQ);
		}
	}

	public List listHoldPQs(String status, String deleted) {
		return holdPQDao.listHoldPQs(status, deleted);
	}

	public Map listHoldPQs(final Integer curPage, final Integer pageSize,
			final String status, final String deleted) {
		return holdPQDao.listHoldPQs(curPage, pageSize, status, deleted);
	}

	public HoldPQ getHoldPQ(String jobId) {
		return holdPQDao.getHoldPQ(jobId);
	}

}
