
package com.boco.eoms.workbench.infopub.test.mgr;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao;
import com.boco.eoms.workbench.infopub.mgr.impl.ThreadAuditHistoryManagerImpl;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class ThreadAuditHistoryManagerTest extends BaseManagerTestCase {
    private final String threadAuditHistoryId = "1";
    private ThreadAuditHistoryManagerImpl threadAuditHistoryManager = new ThreadAuditHistoryManagerImpl();
    private Mock threadAuditHistoryDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        threadAuditHistoryDao = new Mock(ThreadAuditHistoryDao.class);
        threadAuditHistoryManager.setThreadAuditHistoryDao((ThreadAuditHistoryDao) threadAuditHistoryDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        threadAuditHistoryManager = null;
    }

    public void testGetThreadAuditHistorys() throws Exception {
        List results = new ArrayList();
        ThreadAuditHistory threadAuditHistory = new ThreadAuditHistory();
        results.add(threadAuditHistory);

        // set expected behavior on dao
        threadAuditHistoryDao.expects(once()).method("getThreadAuditHistorys")
            .will(returnValue(results));

        List threadAuditHistorys = threadAuditHistoryManager.getThreadAuditHistorys(null);
        assertTrue(threadAuditHistorys.size() == 1);
        threadAuditHistoryDao.verify();
    }

    public void testGetThreadAuditHistory() throws Exception {
        // set expected behavior on dao
        threadAuditHistoryDao.expects(once()).method("getThreadAuditHistory")
            .will(returnValue(new ThreadAuditHistory()));
        ThreadAuditHistory threadAuditHistory = threadAuditHistoryManager.getThreadAuditHistory(threadAuditHistoryId);
        assertTrue(threadAuditHistory != null);
        threadAuditHistoryDao.verify();
    }

    public void testSaveThreadAuditHistory() throws Exception {
        ThreadAuditHistory threadAuditHistory = new ThreadAuditHistory();

        // set expected behavior on dao
        threadAuditHistoryDao.expects(once()).method("saveThreadAuditHistory")
            .with(same(threadAuditHistory)).isVoid();

        threadAuditHistoryManager.saveThreadAuditHistory(threadAuditHistory);
        threadAuditHistoryDao.verify();
    }

    public void testAddAndRemoveThreadAuditHistory() throws Exception {
        ThreadAuditHistory threadAuditHistory = new ThreadAuditHistory();

        // set required fields

        // set expected behavior on dao
        threadAuditHistoryDao.expects(once()).method("saveThreadAuditHistory")
            .with(same(threadAuditHistory)).isVoid();
        threadAuditHistoryManager.saveThreadAuditHistory(threadAuditHistory);
        threadAuditHistoryDao.verify();

        // reset expectations
        threadAuditHistoryDao.reset();

        threadAuditHistoryDao.expects(once()).method("removeThreadAuditHistory").with(eq(new String(threadAuditHistoryId)));
        threadAuditHistoryManager.removeThreadAuditHistory(threadAuditHistoryId);
        threadAuditHistoryDao.verify();

        // reset expectations
        threadAuditHistoryDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(ThreadAuditHistory.class, threadAuditHistory.getId());
        threadAuditHistoryDao.expects(once()).method("removeThreadAuditHistory").isVoid();
        threadAuditHistoryDao.expects(once()).method("getThreadAuditHistory").will(throwException(ex));
        threadAuditHistoryManager.removeThreadAuditHistory(threadAuditHistoryId);
        try {
            threadAuditHistoryManager.getThreadAuditHistory(threadAuditHistoryId);
            fail("ThreadAuditHistory with identifier '" + threadAuditHistoryId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        threadAuditHistoryDao.verify();
    }
}
