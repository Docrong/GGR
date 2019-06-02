/*
 * Created on 2008-1-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.test;


import com.boco.eoms.commons.statistic.base.dao.IStatJdbcDAO;
import com.boco.eoms.commons.statistic.base.reference.ConsoleTestCase;

/**
 * @author liuxy
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DaoTest extends ConsoleTestCase {
	private IStatJdbcDAO kpiDAO;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.test.console.ConsoleTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		kpiDAO = (IStatJdbcDAO) getBean("iKpiBaseJdbcDAO");
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.base.test.console.ConsoleTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
}

