/**
 * 
 */
package com.boco.eoms.commons.log.test.dao;

import java.util.List;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.log.dao.impl.LogConfigXmlDom4jDAO;
import com.boco.eoms.commons.log.service.impl.LogConfigDom4jFactoryBean;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 11, 2008 5:21:35 PM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class TestLogConfigXmlDom4jDAO extends ConsoleTestCase {
	
	LogConfigXmlDom4jDAO LogConfigDAO = null;
	/**
	 * @see com.boco.eoms.base.test.console.ConsoleTestCase#setUp()
	 */
	protected void onSetUpInTransaction() throws Exception {
		super.setUp();
		LogConfigDAO = (LogConfigXmlDom4jDAO)getBean("LogConfigDAO");
	}

	
	public void testListChildLogConfig() {
		try {
			List list=LogConfigDAO.listChildLogConfig("aa1", "0");
			System.out.println("f");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testFindLogConfig() {
		try {
			LogConfigDAO.findLogConfig("0001");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void testFindALLLogConfig() {
		try {
			LogConfigDAO.findAllLogConfig("A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
