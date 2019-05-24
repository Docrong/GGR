
package com.boco.eoms.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.message.dao.SmsLogDao;
import com.boco.eoms.message.mgr.impl.SmsLogManagerImpl;
import com.boco.eoms.message.model.SmsLog;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsLogManagerTest extends BaseManagerTestCase {
    private final String smsLogId = "1";
    private SmsLogManagerImpl smsLogManager = new SmsLogManagerImpl();
    private Mock smsLogDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        smsLogDao = new Mock(SmsLogDao.class);
        smsLogManager.setSmsLogDao((SmsLogDao) smsLogDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        smsLogManager = null;
    }

    public void testGetSmsLogs() throws Exception {
        List results = new ArrayList();
        SmsLog smsLog = new SmsLog();
        results.add(smsLog);

        // set expected behavior on dao
        smsLogDao.expects(once()).method("getSmsLogs")
            .will(returnValue(results));

        List smsLogs = smsLogManager.getSmsLogs(null);
        assertTrue(smsLogs.size() == 1);
        smsLogDao.verify();
    }

    public void testGetSmsLog() throws Exception {
        // set expected behavior on dao
        smsLogDao.expects(once()).method("getSmsLog")
            .will(returnValue(new SmsLog()));
        SmsLog smsLog = smsLogManager.getSmsLog(smsLogId);
        assertTrue(smsLog != null);
        smsLogDao.verify();
    }

    public void testSaveSmsLog() throws Exception {
        SmsLog smsLog = new SmsLog();

        // set expected behavior on dao
        smsLogDao.expects(once()).method("saveSmsLog")
            .with(same(smsLog)).isVoid();

        smsLogManager.saveSmsLog(smsLog);
        smsLogDao.verify();
    }

    public void testAddAndRemoveSmsLog() throws Exception {
        SmsLog smsLog = new SmsLog();

        // set required fields
        smsLog.setContent("IiRaOpSsCfYrBiTlOpLuPeRhTtIhCiJnRgZvHhBbDqCbUkHnJjZlWuMhCpPgWyEkXjJsBfUyDcLxHvTdNwOkUrJpQeKpHeFdNfKfAqSfXlDrBhWxLmSoVdAdNjWfSgFbPwHoCvHrDvVhJrNdOuNgKlFzYdFlKwUcGmRgLhLrJuLtOqDpZwIzNtDnPeUoRdOwYjWoPhLwGdByVmKlSsEwWeEoUsVfHhTmHxZfJsBcFqWwCfPuZzXxAiNgNmYeAfOoBaRrIaFpFqScLvOzYaIvBwKsXcHhNlKgLvSjOpQrTyRfHcKoKcQvDlMfYrWyEoUqKjKeJcTeFcYvGcFdZxDbWuJoHlVjWjXnQxOmOnZsKrXuDqDyPiEvCiKpPbKkBuFjIeHbUyGsIdQiAiTcVjGkYmYgUtWtBwKgObRjGaQaJpKzUhIoCnPzCwOlYxUzUwLmTlYjNkInKfQhWbXfNcUvHjRpYwMbKtEhHjTaRlZfLiBzBoGaIdQt");
        smsLog.setDispatchTime(new java.util.Date());
        smsLog.setMobile("CnLbWbVjNzUnTiTsTmAkMmYkZnApQhPxYbVeJhGjKkUbYzJfKvNgMbXcHyKdCeXnJcYhGeZdWoIdLyZqSlAhStRrKdYgEtMhPzQb");
//        smsLog.setBuizId("QeOsOzHxJyAwKuYsErIcSrLnJdWtGvLd");
//        smsLog.setMonitorId("HpDrJuSbBsIgCbXmSiFsXeWqYtLyGvYq");
        smsLog.setReceiverId("CvLgFlDvXyXlBvOgHxRiPjWwMvLsXy");
        smsLog.setServiceId("FkCsEhZvTlTnXkWoRgOiOeEaQeXdWdRt");

        // set expected behavior on dao
        smsLogDao.expects(once()).method("saveSmsLog")
            .with(same(smsLog)).isVoid();
        smsLogManager.saveSmsLog(smsLog);
        smsLogDao.verify();

        // reset expectations
        smsLogDao.reset();

        smsLogDao.expects(once()).method("removeSmsLog").with(eq(new String(smsLogId)));
        smsLogManager.removeSmsLog(smsLogId);
        smsLogDao.verify();

        // reset expectations
        smsLogDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(SmsLog.class, smsLog.getId());
        smsLogDao.expects(once()).method("removeSmsLog").isVoid();
        smsLogDao.expects(once()).method("getSmsLog").will(throwException(ex));
        smsLogManager.removeSmsLog(smsLogId);
        try {
            smsLogManager.getSmsLog(smsLogId);
            fail("SmsLog with identifier '" + smsLogId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        smsLogDao.verify();
    }
}
