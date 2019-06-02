
package com.boco.eoms.workbench.memo.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoSendLogDao;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemoSendLog;
import com.boco.eoms.workbench.memo.service.impl.TawWorkbenchMemoSendLogManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchMemoSendLogManagerTest extends BaseManagerTestCase {
    private final String tawWorkbenchMemoSendLogId = "1";
    private TawWorkbenchMemoSendLogManagerImpl tawWorkbenchMemoSendLogManager = new TawWorkbenchMemoSendLogManagerImpl();
    private Mock tawWorkbenchMemoSendLogDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawWorkbenchMemoSendLogDao = new Mock(TawWorkbenchMemoSendLogDao.class);
        tawWorkbenchMemoSendLogManager.setTawWorkbenchMemoSendLogDao((TawWorkbenchMemoSendLogDao) tawWorkbenchMemoSendLogDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawWorkbenchMemoSendLogManager = null;
    }

    public void testGetTawWorkbenchMemoSendLogs() throws Exception {
        List results = new ArrayList();
        TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = new TawWorkbenchMemoSendLog();
        results.add(tawWorkbenchMemoSendLog);

        // set expected behavior on dao
        tawWorkbenchMemoSendLogDao.expects(once()).method("getTawWorkbenchMemoSendLogs")
            .will(returnValue(results));

        List tawWorkbenchMemoSendLogs = tawWorkbenchMemoSendLogManager.getTawWorkbenchMemoSendLogs(null);
        assertTrue(tawWorkbenchMemoSendLogs.size() == 1);
        tawWorkbenchMemoSendLogDao.verify();
    }

    public void testGetTawWorkbenchMemoSendLog() throws Exception {
        // set expected behavior on dao
        tawWorkbenchMemoSendLogDao.expects(once()).method("getTawWorkbenchMemoSendLog")
            .will(returnValue(new TawWorkbenchMemoSendLog()));
        TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = tawWorkbenchMemoSendLogManager.getTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLogId);
        assertTrue(tawWorkbenchMemoSendLog != null);
        tawWorkbenchMemoSendLogDao.verify();
    }

    public void testSaveTawWorkbenchMemoSendLog() throws Exception {
        TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = new TawWorkbenchMemoSendLog();

        // set expected behavior on dao
        tawWorkbenchMemoSendLogDao.expects(once()).method("saveTawWorkbenchMemoSendLog")
            .with(same(tawWorkbenchMemoSendLog)).isVoid();

        tawWorkbenchMemoSendLogManager.saveTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLog);
        tawWorkbenchMemoSendLogDao.verify();
    }

    public void testAddAndRemoveTawWorkbenchMemoSendLog() throws Exception {
        TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = new TawWorkbenchMemoSendLog();

        // set required fields

        // set expected behavior on dao
        tawWorkbenchMemoSendLogDao.expects(once()).method("saveTawWorkbenchMemoSendLog")
            .with(same(tawWorkbenchMemoSendLog)).isVoid();
        tawWorkbenchMemoSendLogManager.saveTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLog);
        tawWorkbenchMemoSendLogDao.verify();

        // reset expectations
        tawWorkbenchMemoSendLogDao.reset();

        tawWorkbenchMemoSendLogDao.expects(once()).method("removeTawWorkbenchMemoSendLog").with(eq(new String(tawWorkbenchMemoSendLogId)));
        tawWorkbenchMemoSendLogManager.removeTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLogId);
        tawWorkbenchMemoSendLogDao.verify();

        // reset expectations
        tawWorkbenchMemoSendLogDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawWorkbenchMemoSendLog.class, tawWorkbenchMemoSendLog.getId());
        tawWorkbenchMemoSendLogDao.expects(once()).method("removeTawWorkbenchMemoSendLog").isVoid();
        tawWorkbenchMemoSendLogDao.expects(once()).method("getTawWorkbenchMemoSendLog").will(throwException(ex));
        tawWorkbenchMemoSendLogManager.removeTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLogId);
        try {
            tawWorkbenchMemoSendLogManager.getTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLogId);
            fail("TawWorkbenchMemoSendLog with identifier '" + tawWorkbenchMemoSendLogId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawWorkbenchMemoSendLogDao.verify();
    }
}
