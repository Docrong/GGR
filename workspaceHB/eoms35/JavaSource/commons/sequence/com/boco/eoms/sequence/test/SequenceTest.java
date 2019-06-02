package com.boco.eoms.sequence.test;

import junit.framework.TestCase;

import com.boco.eoms.sequence.Job;
import com.boco.eoms.sequence.Sequence;

/**
 * <p>
 * Title:序列测试
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 11:51:27 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SequenceTest extends TestCase {

	private Sequence sequence;

	protected void setUp() throws Exception {
		super.setUp();
		sequence = new Sequence();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPut() {
		sequence.put(new Job(null, "methodName1", null, null, null));
		sequence.put(new Job(null, "methodName2", null, null, null));
		Job job = sequence.next();
		assertEquals("methodName1", job.getMethodName());
		job = sequence.next();
		assertEquals("methodName2", job.getMethodName());
	}

	public void testGet() {
		
	}

	public void testTotal() {
		assertEquals(0, sequence.total());
	}

}
