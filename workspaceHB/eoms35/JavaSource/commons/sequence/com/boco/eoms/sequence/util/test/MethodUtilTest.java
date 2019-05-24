/**
 * 
 */
package com.boco.eoms.sequence.util.test;

import junit.framework.TestCase;

import com.boco.eoms.sequence.Job;
import com.boco.eoms.sequence.util.MethodUtil;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 4:18:56 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 4:18:56 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class MethodUtilTest extends TestCase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link com.boco.eoms.sequence.util.MethodUtil#invoke(com.boco.eoms.sequence.Job)}.
	 */
	public void testInvoke() {
		Job job = new Job();
		Job method = new Job();

		job.setClz(method);
		job.setMethodName("setMethodName");
		job.setParams(new Object[] { "testmethod" });
		job.setParamTypes(new Class[] { java.lang.String.class });
		try {
			MethodUtil.invoke(job);
			assertEquals(method.getMethodName(), "testmethod");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
