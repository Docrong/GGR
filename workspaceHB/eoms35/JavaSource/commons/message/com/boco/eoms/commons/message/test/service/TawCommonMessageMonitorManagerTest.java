
package com.boco.eoms.commons.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.message.dao.TawCommonMessageMonitorDao;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitor;
import com.boco.eoms.commons.message.service.impl.TawCommonMessageMonitorManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageMonitorManagerTest extends BaseManagerTestCase {
    private final String tawCommonMessageMonitorId = "1";
    private TawCommonMessageMonitorManagerImpl tawCommonMessageMonitorManager = new TawCommonMessageMonitorManagerImpl();
    private Mock tawCommonMessageMonitorDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawCommonMessageMonitorDao = new Mock(TawCommonMessageMonitorDao.class);
        tawCommonMessageMonitorManager.setTawCommonMessageMonitorDao((TawCommonMessageMonitorDao) tawCommonMessageMonitorDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawCommonMessageMonitorManager = null;
    }

    public void testGetTawCommonMessageMonitors() throws Exception {
        List results = new ArrayList();
        TawCommonMessageMonitor tawCommonMessageMonitor = new TawCommonMessageMonitor();
        results.add(tawCommonMessageMonitor);

        // set expected behavior on dao
        tawCommonMessageMonitorDao.expects(once()).method("getTawCommonMessageMonitors")
            .will(returnValue(results));

        List tawCommonMessageMonitors = tawCommonMessageMonitorManager.getTawCommonMessageMonitors(null);
        assertTrue(tawCommonMessageMonitors.size() == 1);
        tawCommonMessageMonitorDao.verify();
    }

    public void testGetTawCommonMessageMonitor() throws Exception {
        // set expected behavior on dao
        tawCommonMessageMonitorDao.expects(once()).method("getTawCommonMessageMonitor")
            .will(returnValue(new TawCommonMessageMonitor()));
        TawCommonMessageMonitor tawCommonMessageMonitor = tawCommonMessageMonitorManager.getTawCommonMessageMonitor(tawCommonMessageMonitorId);
        assertTrue(tawCommonMessageMonitor != null);
        tawCommonMessageMonitorDao.verify();
    }

    public void testSaveTawCommonMessageMonitor() throws Exception {
        TawCommonMessageMonitor tawCommonMessageMonitor = new TawCommonMessageMonitor();

        // set expected behavior on dao
        tawCommonMessageMonitorDao.expects(once()).method("saveTawCommonMessageMonitor")
            .with(same(tawCommonMessageMonitor)).isVoid();

        tawCommonMessageMonitorManager.saveTawCommonMessageMonitor(tawCommonMessageMonitor);
        tawCommonMessageMonitorDao.verify();
    }

    public void testAddAndRemoveTawCommonMessageMonitor() throws Exception {
        TawCommonMessageMonitor tawCommonMessageMonitor = new TawCommonMessageMonitor();

        // set required fields

        // set expected behavior on dao
        tawCommonMessageMonitorDao.expects(once()).method("saveTawCommonMessageMonitor")
            .with(same(tawCommonMessageMonitor)).isVoid();
        tawCommonMessageMonitorManager.saveTawCommonMessageMonitor(tawCommonMessageMonitor);
        tawCommonMessageMonitorDao.verify();

        // reset expectations
        tawCommonMessageMonitorDao.reset();

        tawCommonMessageMonitorDao.expects(once()).method("removeTawCommonMessageMonitor").with(eq(tawCommonMessageMonitorId));
        tawCommonMessageMonitorManager.removeTawCommonMessageMonitor(tawCommonMessageMonitorId);
        tawCommonMessageMonitorDao.verify();

        // reset expectations
        tawCommonMessageMonitorDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawCommonMessageMonitor.class, tawCommonMessageMonitor.getMonitorId());
        tawCommonMessageMonitorDao.expects(once()).method("removeTawCommonMessageMonitor").isVoid();
        tawCommonMessageMonitorDao.expects(once()).method("getTawCommonMessageMonitor").will(throwException(ex));
        tawCommonMessageMonitorManager.removeTawCommonMessageMonitor(tawCommonMessageMonitorId);
        try {
            tawCommonMessageMonitorManager.getTawCommonMessageMonitor(tawCommonMessageMonitorId);
            fail("TawCommonMessageMonitor with identifier '" + tawCommonMessageMonitorId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawCommonMessageMonitorDao.verify();
    }
}
