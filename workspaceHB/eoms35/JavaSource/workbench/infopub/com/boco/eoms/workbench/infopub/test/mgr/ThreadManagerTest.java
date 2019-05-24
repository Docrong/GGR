package com.boco.eoms.workbench.infopub.test.mgr;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.infopub.dao.ThreadDao;
import com.boco.eoms.workbench.infopub.mgr.impl.ThreadManagerImpl;
import com.boco.eoms.workbench.infopub.model.Thread;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * <p>
 * Title:信息（贴子）mgr测试类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:10:29 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadManagerTest extends BaseManagerTestCase {
	private final String threadId = "1";

	private ThreadManagerImpl threadManager = new ThreadManagerImpl();

	private Mock threadDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		threadDao = new Mock(ThreadDao.class);
		threadManager.setThreadDao((ThreadDao) threadDao.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		threadManager = null;
	}

	public void testGetThreads() throws Exception {
		List results = new ArrayList();
		Thread thread = new Thread();
		results.add(thread);

		// set expected behavior on dao
		threadDao.expects(once()).method("getThreads").will(
				returnValue(results));

		//List threads = threadManager.getThreads(new Thead());
		//assertTrue(threads.size() == 1);

		threadDao.verify();
	}

	public void testGetThread() throws Exception {
		// set expected behavior on dao
		threadDao.expects(once()).method("getThread").will(
				returnValue(new Thread()));
		Thread thread = threadManager.getThread(threadId);
		assertTrue(thread != null);
		threadDao.verify();
	}

	public void testSaveThread() throws Exception {
		Thread thread = new Thread();

		// set expected behavior on dao
		threadDao.expects(once()).method("saveThread").with(same(thread))
				.isVoid();

		threadManager.saveThread(thread);
		threadDao.verify();
	}

	public void testAddAndRemoveThread() throws Exception {
		Thread thread = new Thread();

		// set required fields

		// set expected behavior on dao
		threadDao.expects(once()).method("saveThread").with(same(thread))
				.isVoid();
		threadManager.saveThread(thread);
		threadDao.verify();

		// reset expectations
		threadDao.reset();

		threadDao.expects(once()).method("removeThread").with(
				eq(new String(threadId)));
		threadManager.removeThread(threadId);
		threadDao.verify();

		// reset expectations
		threadDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(Thread.class, thread
				.getId());
		threadDao.expects(once()).method("removeThread").isVoid();
		threadDao.expects(once()).method("getThread").will(throwException(ex));
		threadManager.removeThread(threadId);
		try {
			threadManager.getThread(threadId);
			fail("Thread with identifier '" + threadId + "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		threadDao.verify();
	}
}
