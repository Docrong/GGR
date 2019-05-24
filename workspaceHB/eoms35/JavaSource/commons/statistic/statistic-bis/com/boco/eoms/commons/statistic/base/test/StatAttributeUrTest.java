/**
 * 
 */
package com.boco.eoms.commons.statistic.base.test;

import com.boco.eoms.commons.statistic.base.reference.ConsoleTestCase;
import com.boco.eoms.commons.statistic.base.util.StatAttributeUrl;
import com.boco.eoms.commons.statistic.base.util.StatAttributeUrlLocator;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Aug 3, 2008 12:45:24 PM
 * </p>
 * 
 * @author wangbeiying
 * @version 1.0
 * 
 */
public class StatAttributeUrTest extends ConsoleTestCase {
	private StatAttributeUrl aUrl;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.test.console.ConsoleTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		aUrl = (StatAttributeUrl) getBean("statAttributeUrl");
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.base.test.console.ConsoleTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void testMap() {
		this.assertEquals(StatAttributeUrlLocator.getStatAttributeUrl("").getAttributeUrl().getProperty("2"),"222");
	}
}
