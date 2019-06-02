package com.boco.eoms.workbench.memo.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemoSendLog;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoSendLogDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchMemoSendLogDaoTest extends BaseDaoTestCase {
    private String tawWorkbenchMemoSendLogId = new String("1");
    private TawWorkbenchMemoSendLogDao dao = null;

    public void setTawWorkbenchMemoSendLogDao(TawWorkbenchMemoSendLogDao dao) {
        this.dao = dao;
    }

    public void testAddTawWorkbenchMemoSendLog() throws Exception {
        TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = new TawWorkbenchMemoSendLog();

        // set required fields

        dao.saveTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLog);

        // verify a primary key was assigned
        assertNotNull(tawWorkbenchMemoSendLog.getId());

        // verify set fields are same after save
    }

    public void testGetTawWorkbenchMemoSendLog() throws Exception {
        TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = dao.getTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLogId);
        assertNotNull(tawWorkbenchMemoSendLog);
    }

    public void testGetTawWorkbenchMemoSendLogs() throws Exception {
        TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = new TawWorkbenchMemoSendLog();

        List results = dao.getTawWorkbenchMemoSendLogs(tawWorkbenchMemoSendLog);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawWorkbenchMemoSendLog() throws Exception {
        TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = dao.getTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLogId);

        // update required fields

        dao.saveTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLog);

    }

    public void testRemoveTawWorkbenchMemoSendLog() throws Exception {
        String removeId = new String("3");
        dao.removeTawWorkbenchMemoSendLog(removeId);
        try {
            dao.getTawWorkbenchMemoSendLog(removeId);
            fail("tawWorkbenchMemoSendLog found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
