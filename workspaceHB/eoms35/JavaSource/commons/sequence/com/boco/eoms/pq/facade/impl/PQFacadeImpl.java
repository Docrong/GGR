package com.boco.eoms.pq.facade.impl;

import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.pq.exception.CancelPQErrorException;
import com.boco.eoms.pq.exception.DoPQErrorException;
import com.boco.eoms.pq.exception.PutPQErrorException;
import com.boco.eoms.pq.facade.IPQFacade;
import com.boco.eoms.pq.mgr.IInitPQMgr;
import com.boco.eoms.sequence.Job;

public class PQFacadeImpl implements IPQFacade {

	public void cancelPQ(String jobId) {
		IInitPQMgr initPQMgr = (IInitPQMgr) ApplicationContextHolder
				.getInstance().getBean("initPQMgr");
		try {
			initPQMgr.cancelInitPQ(jobId);
		} catch (CancelPQErrorException e) {
			System.out.println("***Error occurred when cancel jobs in PQ!");
			e.printStackTrace();
		}
	}

	public void cancelPQs() {
		IInitPQMgr initPQMgr = (IInitPQMgr) ApplicationContextHolder
				.getInstance().getBean("initPQMgr");
		try {
			initPQMgr.cancelInitPQs();
		} catch (CancelPQErrorException e) {
			System.out.println("***Error occurred when cancel jobs in PQ!");
			e.printStackTrace();
		}
	}

	public void doPQ(String jobId) {
		IInitPQMgr initPQMgr = (IInitPQMgr) ApplicationContextHolder
				.getInstance().getBean("initPQMgr");
		try {
			initPQMgr.doInitPQ(jobId);
		} catch (DoPQErrorException e) {
			System.out
					.println("***Error occurred when do job in PQ, PQ with deal it later!");
			e.printStackTrace();
		}
	}

	public void doPQs() {
		IInitPQMgr initPQMgr = (IInitPQMgr) ApplicationContextHolder
				.getInstance().getBean("initPQMgr");
		try {
			initPQMgr.doInitPQs();
		} catch (DoPQErrorException e) {
			System.out
					.println("***Error occurred when do some jobs in PQ, PQ with deal them later!");
			e.printStackTrace();
		}
	}

	public String putPQ(Job job) {
		IInitPQMgr initPQMgr = (IInitPQMgr) ApplicationContextHolder
				.getInstance().getBean("initPQMgr");
		String jobId = "";
		try {
			jobId = initPQMgr.putInitPQ(job);
		} catch (PutPQErrorException e) {
			System.out
					.println("***Error occurred when put job in PQ, the job will be dropped!");
			e.printStackTrace();
		}
		return jobId;
	}

	public String[] putPQs(List jobList) {
		IInitPQMgr initPQMgr = (IInitPQMgr) ApplicationContextHolder
				.getInstance().getBean("initPQMgr");
		String[] jobIds = new String[] {};
		try {
			jobIds = initPQMgr.putInitPQs(jobList);
		} catch (PutPQErrorException e) {
			System.out
					.println("***Error occurred when put job in PQ, some jobs will be dropped!");
			e.printStackTrace();
		}
		return jobIds;
	}

}
