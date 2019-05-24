package com.boco.eoms.base.test.console;

import org.apache.log4j.Logger;
import org.springframework.test.AbstractTransactionalSpringContextTests;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 5:29:15 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ConsoleTestCase extends AbstractTransactionalSpringContextTests {

	protected Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
	 */
	protected String[] getConfigLocations() {
		return new String[] { "config/applicationContext-all.xml" };
	}

	protected Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}

}
