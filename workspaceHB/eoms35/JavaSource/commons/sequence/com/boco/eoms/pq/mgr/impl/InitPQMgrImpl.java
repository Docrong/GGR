package com.boco.eoms.pq.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.pq.dao.IInitPQDao;
import com.boco.eoms.pq.exception.CancelPQErrorException;
import com.boco.eoms.pq.exception.DoPQErrorException;
import com.boco.eoms.pq.exception.PutPQErrorException;
import com.boco.eoms.pq.mgr.IInitPQMgr;
import com.boco.eoms.pq.mgr.IRetainPQMgr;
import com.boco.eoms.pq.model.InitPQ;
import com.boco.eoms.pq.util.Constants;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Job;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

public class InitPQMgrImpl extends BaseManager implements IInitPQMgr {

	private IInitPQDao initPQDao;

	public void setInitPQDao(IInitPQDao initPQDao) {
		this.initPQDao = initPQDao;
	}

	public void cancelInitPQ(String jobId) throws CancelPQErrorException {
		// 从数据库中取出任务
		InitPQ initPQ = initPQDao.getInitPQ(jobId);
		if (!Constants.DELETED.equals(initPQ.getDeleted())
				&& Constants.Q_STATUS_WAITING.equals(initPQ.getStatus())) { // 未删除并且等待执行的任务
			initPQDao.delInitPQ(initPQ);
		}
	}

	public void cancelInitPQs() throws CancelPQErrorException {
		List list = initPQDao.listInitPQs(Constants.Q_STATUS_WAITING,
				Constants.UNDELETED);
		for (Iterator it = list.iterator(); it.hasNext();) {
			InitPQ initPQ = (InitPQ) it.next();
			initPQDao.delInitPQ(initPQ);
		}
	}

	public void doInitPQs() throws DoPQErrorException {
		// 所有job存入MQ并执行MQ
		ISequenceFacade sequenceFacade = SequenceLocator.getSequenceFacade();
		Sequence initMQSequence = null;
		try {
			initMQSequence = sequenceFacade.getSequence("initMQ");
		} catch (SequenceNotFoundException e) {
			e.printStackTrace();
		}
		List list = initPQDao.listInitPQs(Constants.Q_STATUS_WAITING,
				Constants.UNDELETED);
		for (Iterator it = list.iterator(); it.hasNext();) {
			InitPQ initPQ = (InitPQ) it.next();
			Job job = initPQ.convert2Job();
			sequenceFacade.putMQ(job, initMQSequence);
		}
		initMQSequence.setChanged();
		sequenceFacade.doJob(initMQSequence);
	}

	public void doInitPQ(String jobId) throws DoPQErrorException {
		// 根据jobId查找并转换job
		InitPQ initPQ = initPQDao.getInitPQ(jobId);
		Job job = initPQ.convert2Job();

		// job存入MQ并执行MQ
		ISequenceFacade sequenceFacade = SequenceLocator.getSequenceFacade();
		Sequence initMQSequence = null;
		try {
			initMQSequence = sequenceFacade.getSequence("initMQ");
		} catch (SequenceNotFoundException e) {
			e.printStackTrace();
		}
		sequenceFacade.putMQ(job, initMQSequence);
		initMQSequence.setChanged();
		sequenceFacade.doJob(initMQSequence);
	}

	public String putInitPQ(Job job) throws PutPQErrorException {
		// job存入PQ
		InitPQ initPQ = new InitPQ(job);
		initPQDao.saveInitPQ(initPQ);
		return initPQDao.saveInitPQ(initPQ);
	}

	public String[] putInitPQs(List jobList) throws PutPQErrorException {
		String[] jobIds = new String[] {};
		List idList = new ArrayList();
		for (Iterator it = jobList.iterator(); it.hasNext();) {
			Job job = (Job) it.next();
			String jobId = putInitPQ(job);
			idList.add(jobId);
		}
		jobIds = (String[]) idList.toArray();
		return jobIds;
	}

	public void delInitPQ(String jobId) {
		InitPQ initPQ = initPQDao.getInitPQ(jobId);
		if (null != initPQ) {
			initPQDao.delInitPQ(initPQ);
		}
	}

	public void removeInitPQ(String jobId) {
		InitPQ initPQ = initPQDao.getInitPQ(jobId);
		if (null != initPQ) {
			initPQDao.removeInitPQ(initPQ);
		}
	}

	public void processFailJob(Job job) {
		removeInitPQ(job.getJobId());

		// 将job存入二级持久队列
		IRetainPQMgr retainPQMgr = (IRetainPQMgr) ApplicationContextHolder
				.getInstance().getBean("retainPQMgr");
		String jobId = "";
		try {
			jobId = retainPQMgr.putRetainPQ(job);
		} catch (PutPQErrorException e1) {
			System.out
					.println("***Error occurred while put job into RetainPQ, job will be dropped!");
			e1.printStackTrace();
		}
		// 执行二级持久队列
		try {
			retainPQMgr.doRetainPQ(jobId);
		} catch (DoPQErrorException e1) {
			System.out
					.println("***Error occurred while do job in RetainPQ, job may be run later, or put into HoldPQ!");
			e1.printStackTrace();
		}
	}

}
