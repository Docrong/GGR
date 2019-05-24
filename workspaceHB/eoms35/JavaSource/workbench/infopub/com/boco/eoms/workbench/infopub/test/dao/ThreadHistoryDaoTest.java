package com.boco.eoms.workbench.infopub.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;
import com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * 
 * <p>
 * Title:信息（贴子）阅读历史dao测试
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
public class ThreadHistoryDaoTest extends BaseDaoTestCase {
	private String threadHistoryId = new String("1");

	private ThreadHistoryDao dao = null;

	public void setThreadHistoryDao(ThreadHistoryDao dao) {
		this.dao = dao;
	}

	public void testAddThreadHistory() throws Exception {
		ThreadHistory threadHistory = new ThreadHistory();

		// set required fields

		dao.saveThreadHistory(threadHistory);

		// verify a primary key was assigned
		assertNotNull(threadHistory.getId());

		// verify set fields are same after save
	}

	public void testGetThreadHistory() throws Exception {
		ThreadHistory threadHistory = dao.getThreadHistory(threadHistoryId);
		assertNotNull(threadHistory);
	}

	public void testGetThreadHistorys() throws Exception {
		ThreadHistory threadHistory = new ThreadHistory();

		List results = dao.getThreadHistorys(threadHistory);
		assertTrue(results.size() > 0);
	}

	public void testSaveThreadHistory() throws Exception {
		ThreadHistory threadHistory = dao.getThreadHistory(threadHistoryId);

		// update required fields

		dao.saveThreadHistory(threadHistory);

	}

	public void testRemoveThreadHistory() throws Exception {
		String removeId = new String("3");
		dao.removeThreadHistory(removeId);
		try {
			dao.getThreadHistory(removeId);
			fail("threadHistory found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
