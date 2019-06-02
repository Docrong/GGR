package com.boco.eoms.workbench.infopub.test.mgr;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao;
import com.boco.eoms.workbench.infopub.mgr.impl.ThreadHistoryManagerImpl;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * <p>
 * Title:信息（贴子）阅读历史mgr测试
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:11:04 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 *
 */
public class ThreadHistoryManagerTest extends BaseManagerTestCase {
	private final String threadHistoryId = "1";

	private ThreadHistoryManagerImpl threadHistoryManager = new ThreadHistoryManagerImpl();

	private Mock threadHistoryDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		threadHistoryDao = new Mock(ThreadHistoryDao.class);
		threadHistoryManager
				.setThreadHistoryDao((ThreadHistoryDao) threadHistoryDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		threadHistoryManager = null;
	}

	public void testGetThreadHistorys() throws Exception {
		List results = new ArrayList();
		ThreadHistory threadHistory = new ThreadHistory();
		results.add(threadHistory);

		// set expected behavior on dao
		threadHistoryDao.expects(once()).method("getThreadHistorys").will(
				returnValue(results));

		List threadHistorys = threadHistoryManager.getThreadHistorys(null);
		assertTrue(threadHistorys.size() == 1);
		threadHistoryDao.verify();
	}

	public void testGetThreadHistory() throws Exception {
		// set expected behavior on dao
		threadHistoryDao.expects(once()).method("getThreadHistory").will(
				returnValue(new ThreadHistory()));
		ThreadHistory threadHistory = threadHistoryManager
				.getThreadHistory(threadHistoryId);
		assertTrue(threadHistory != null);
		threadHistoryDao.verify();
	}

	public void testSaveThreadHistory() throws Exception {
		ThreadHistory threadHistory = new ThreadHistory();

		// set expected behavior on dao
		threadHistoryDao.expects(once()).method("saveThreadHistory").with(
				same(threadHistory)).isVoid();

		threadHistoryManager.saveThreadHistory(threadHistory);
		threadHistoryDao.verify();
	}

	public void testAddAndRemoveThreadHistory() throws Exception {
		ThreadHistory threadHistory = new ThreadHistory();

		// set required fields

		// set expected behavior on dao
		threadHistoryDao.expects(once()).method("saveThreadHistory").with(
				same(threadHistory)).isVoid();
		threadHistoryManager.saveThreadHistory(threadHistory);
		threadHistoryDao.verify();

		// reset expectations
		threadHistoryDao.reset();

		threadHistoryDao.expects(once()).method("removeThreadHistory").with(
				eq(new String(threadHistoryId)));
		threadHistoryManager.removeThreadHistory(threadHistoryId);
		threadHistoryDao.verify();

		// reset expectations
		threadHistoryDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(ThreadHistory.class,
				threadHistory.getId());
		threadHistoryDao.expects(once()).method("removeThreadHistory").isVoid();
		threadHistoryDao.expects(once()).method("getThreadHistory").will(
				throwException(ex));
		threadHistoryManager.removeThreadHistory(threadHistoryId);
		try {
			threadHistoryManager.getThreadHistory(threadHistoryId);
			fail("ThreadHistory with identifier '" + threadHistoryId
					+ "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		threadHistoryDao.verify();
	}
}
