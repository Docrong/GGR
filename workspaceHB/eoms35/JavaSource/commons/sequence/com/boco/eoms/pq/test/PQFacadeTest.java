package com.boco.eoms.pq.test;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.pq.facade.IPQFacade;
import com.boco.eoms.sequence.Job;

import junit.framework.TestCase;

public class PQFacadeTest extends TestCase {

	public void testPutPQ() {
		IPQFacade pqFacade = (IPQFacade) ApplicationContextHolder.getInstance()
				.getBean("pqFacade");

		PQTestModel model = new PQTestModel();
		String methodName = "out";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class };
		Object[] params = new Object[] { "Hello", "world" };
		Job job = new Job(model, methodName, paramTypes, params, null);

		String jobId = pqFacade.putPQ(job);
		System.out.print(jobId);
	}

	public void testPutPQs() {
		IPQFacade pqFacade = (IPQFacade) ApplicationContextHolder.getInstance()
				.getBean("pqFacade");

		List jobList = new ArrayList();
		for (int i = 0; i < 10; i++) {
			PQTestModel model = new PQTestModel();
			String methodName = "out";
			Class[] paramTypes = new Class[] { java.lang.String.class,
					java.lang.String.class };
			Object[] params = new Object[] { "Hello", "world" + i };
			Job job = new Job(model, methodName, paramTypes, params, null);
			jobList.add(job);
		}
		String[] jobIds = pqFacade.putPQs(jobList);
		System.out.println(jobIds);
	}

	public void testDoPQs() {
		IPQFacade pqFacade = (IPQFacade) ApplicationContextHolder.getInstance()
				.getBean("pqFacade");
		pqFacade.doPQs();
	}

	public void testDoPQ() {
		IPQFacade pqFacade = (IPQFacade) ApplicationContextHolder.getInstance()
				.getBean("pqFacade");
		String jobId = "402881842207f006012207f04a110001";
		pqFacade.doPQ(jobId);
	}

	public void testCancelPQs() {
		IPQFacade pqFacade = (IPQFacade) ApplicationContextHolder.getInstance()
				.getBean("pqFacade");
		pqFacade.cancelPQs();
	}

	public void testCancelPQ() {
		IPQFacade pqFacade = (IPQFacade) ApplicationContextHolder.getInstance()
				.getBean("pqFacade");
		String jobId = "402881842207f006012207f04a110001";
		pqFacade.cancelPQ(jobId);
	}

}
