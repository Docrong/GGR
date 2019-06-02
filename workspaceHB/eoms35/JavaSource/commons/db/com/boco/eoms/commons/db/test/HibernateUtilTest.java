/**
 * com.boco.eoms.commons.db.test.HibernateUtilTest.java
 */
package com.boco.eoms.commons.db.test;

// hibernate library
import org.hibernate.Session;

// eoms classes
import com.boco.eoms.commons.db.bocopool.BocoConnection;
import com.boco.eoms.commons.db.util.HibernateUtil;
import com.boco.eoms.base.test.console.ConsoleTestCase;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class HibernateUtilTest extends ConsoleTestCase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.test.console.ConsoleTestCase#setUp()
	 */
	protected void onSetUpInTransaction() throws Exception {
		super.setUp();
	}

	/**
	 * @see 非等待方式获取数据库连接
	 */
	public void testGetConnection1() throws Exception {
		BocoConnection _objConn = HibernateUtil.getConnection();
		assertNotNull(_objConn);
		_objConn.close();
	}

	/**
	 * @see 等待方式获取数据库连接
	 */
	public void testGetConnection2() throws Exception {
		int _iWaitTime = 1;
		BocoConnection _objConn = HibernateUtil.getConnection(_iWaitTime);
		assertNotNull(_objConn);
		_objConn.close();
	}

	/**
	 * @see 获取Session
	 */
	public void testGetSession() throws Exception {
		int _iWaitTime = 1;
		BocoConnection _objConn = HibernateUtil.getConnection(_iWaitTime);
		Session _objSession = HibernateUtil.currentSession(_objConn);
		assertNotNull(_objSession);
		HibernateUtil.closeSession();
		assertFalse(_objConn.isUse());
	}

}
