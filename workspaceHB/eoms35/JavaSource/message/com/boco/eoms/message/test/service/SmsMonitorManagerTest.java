
package com.boco.eoms.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.message.dao.SmsMonitorDao;
import com.boco.eoms.message.mgr.impl.SmsMonitorManagerImpl;
import com.boco.eoms.message.model.SmsMonitor;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsMonitorManagerTest extends BaseManagerTestCase {
    private final String smsMonitorId = "1";
    private SmsMonitorManagerImpl smsMonitorManager = new SmsMonitorManagerImpl();
    private Mock smsMonitorDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        smsMonitorDao = new Mock(SmsMonitorDao.class);
        smsMonitorManager.setSmsMonitorDao((SmsMonitorDao) smsMonitorDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        smsMonitorManager = null;
    }

    public void testGetSmsMonitors() throws Exception {
        List results = new ArrayList();
        SmsMonitor smsMonitor = new SmsMonitor();
        results.add(smsMonitor);

        // set expected behavior on dao
        smsMonitorDao.expects(once()).method("getSmsMonitors")
            .will(returnValue(results));

        List smsMonitors = smsMonitorManager.getSmsMonitors(null);
        assertTrue(smsMonitors.size() == 1);
        smsMonitorDao.verify();
    }

    public void testGetSmsMonitor() throws Exception {
        // set expected behavior on dao
        smsMonitorDao.expects(once()).method("getSmsMonitor")
            .will(returnValue(new SmsMonitor()));
        SmsMonitor smsMonitor = smsMonitorManager.getSmsMonitor(smsMonitorId);
        assertTrue(smsMonitor != null);
        smsMonitorDao.verify();
    }

    public void testSaveSmsMonitor() throws Exception {
        SmsMonitor smsMonitor = new SmsMonitor();

        // set expected behavior on dao
        smsMonitorDao.expects(once()).method("saveSmsMonitor")
            .with(same(smsMonitor)).isVoid();

        smsMonitorManager.saveSmsMonitor(smsMonitor);
        smsMonitorDao.verify();
    }

    public void testAddAndRemoveSmsMonitor() throws Exception {
        SmsMonitor smsMonitor = new SmsMonitor();

        // set required fields
        smsMonitor.setApplyId("RrDbNzWaAaTeLkEkPuVdAtJgRqEvSzCw");
        smsMonitor.setContent("PxQcObTeOiTmFmAmUeNpHyVhHaAbXxAkEhWdKdIvHrEbMgPbEgLcQpFnAeNgZgOnKzIcThGsHvPnXtAnWqFlYnPkLmZdJsIwGsKpBmYbVeIkDySkNcGvUmJcZdTrKlFkArKzPiWyCqQqZmMoHbIgWmVkGhLhFhJdXuUgOfVtZsJqWoPhXpNcBqLxLaWnYlNzWhUmRtRxEuTeZiOdEmHfHgAjDgQrCgUwNiYjTnItRrMcUdKkSqXcCdGdJrWwDkHiQeLbVwSaFmBqUmGlUrDjZaOjQnBhNbLbPjAhEdLdJsIpRvVwOvYjKjCuSdJvUvFyEdUoYzIbLmHiJwRaMdYhXoAfDuZsYeSdRhVyWgIdAcHeXbIxArLgIyDeIdWnBxElFgEvCoTpLaEcSgQgPkJvMqQvXjUpKgXfGbFwNtQrUtBqHtTqRuWsYaBxYbHkInTaLgCmEeJtAfYzUmWfJdTrEaRfYfKmUeQbSgOkBjUzEiYiDmQmYxRv");
        smsMonitor.setDispatchTime(new java.util.Date());
        smsMonitor.setMobile("WhCfVfEfAnNiLrVmZyEvFxOtIpNgWiWzNsYwAjSjLkInGiBtVvSiToIuXnPaZgYoIiWvBtHlRrOwMxRuAoEzEcLtVxNoVjCfDcYq");
        smsMonitor.setReceiverId("IyYlEbPjRrFiGaWrDfAfIbUoNmMjYo");

        // set expected behavior on dao
        smsMonitorDao.expects(once()).method("saveSmsMonitor")
            .with(same(smsMonitor)).isVoid();
        smsMonitorManager.saveSmsMonitor(smsMonitor);
        smsMonitorDao.verify();

        // reset expectations
        smsMonitorDao.reset();

        smsMonitorDao.expects(once()).method("removeSmsMonitor").with(eq(new String(smsMonitorId)));
        smsMonitorManager.removeSmsMonitor(smsMonitorId);
        smsMonitorDao.verify();

        // reset expectations
        smsMonitorDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(SmsMonitor.class, smsMonitor.getId());
        smsMonitorDao.expects(once()).method("removeSmsMonitor").isVoid();
        smsMonitorDao.expects(once()).method("getSmsMonitor").will(throwException(ex));
        smsMonitorManager.removeSmsMonitor(smsMonitorId);
        try {
            smsMonitorManager.getSmsMonitor(smsMonitorId);
            fail("SmsMonitor with identifier '" + smsMonitorId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        smsMonitorDao.verify();
    }
}
