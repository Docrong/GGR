package com.boco.eoms.workbench.infopub.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;
import com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class ThreadAuditHistoryDaoTest extends BaseDaoTestCase {
    private String threadAuditHistoryId = new String("1");
    private ThreadAuditHistoryDao dao = null;

    public void setThreadAuditHistoryDao(ThreadAuditHistoryDao dao) {
        this.dao = dao;
    }

    public void testAddThreadAuditHistory() throws Exception {
        ThreadAuditHistory threadAuditHistory = new ThreadAuditHistory();

        // set required fields

        dao.saveThreadAuditHistory(threadAuditHistory);

        // verify a primary key was assigned
        assertNotNull(threadAuditHistory.getId());

        // verify set fields are same after save
    }

    public void testGetThreadAuditHistory() throws Exception {
        ThreadAuditHistory threadAuditHistory = dao.getThreadAuditHistory(threadAuditHistoryId);
        assertNotNull(threadAuditHistory);
    }

    public void testGetThreadAuditHistorys() throws Exception {
        ThreadAuditHistory threadAuditHistory = new ThreadAuditHistory();

        List results = dao.getThreadAuditHistorys(threadAuditHistory);
        assertTrue(results.size() > 0);
    }

    public void testSaveThreadAuditHistory() throws Exception {
        ThreadAuditHistory threadAuditHistory = dao.getThreadAuditHistory(threadAuditHistoryId);

        // update required fields

        dao.saveThreadAuditHistory(threadAuditHistory);

    }

    public void testRemoveThreadAuditHistory() throws Exception {
        String removeId = new String("3");
        dao.removeThreadAuditHistory(removeId);
        try {
            dao.getThreadAuditHistory(removeId);
            fail("threadAuditHistory found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
