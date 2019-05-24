
package com.boco.eoms.message.test.service;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.message.dao.IEmailMonitorDao;
import com.boco.eoms.message.mgr.impl.EmailMonitorManagerImpl;
import com.boco.eoms.message.model.EmailMonitor;

public class EmailMonitorManagerTest extends BaseManagerTestCase {
    private final String emailMonitorId = "1";
    private EmailMonitorManagerImpl emailMonitorManager = new EmailMonitorManagerImpl();
    private Mock emailMonitorDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        emailMonitorDao = new Mock(IEmailMonitorDao.class);
        emailMonitorManager.setEmailMonitorDao((IEmailMonitorDao) emailMonitorDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        emailMonitorManager = null;
    }

    public void testGetEmailMonitors() throws Exception {
        List results = new ArrayList();
        EmailMonitor emailMonitor = new EmailMonitor();
        results.add(emailMonitor);

        // set expected behavior on dao
        emailMonitorDao.expects(once()).method("getEmailMonitors")
            .will(returnValue(results));

        List emailMonitors = emailMonitorManager.getEmailMonitors(null);
        assertTrue(emailMonitors.size() == 1);
        emailMonitorDao.verify();
    }

    public void testGetEmailMonitor() throws Exception {
        // set expected behavior on dao
        emailMonitorDao.expects(once()).method("getEmailMonitor")
            .will(returnValue(new EmailMonitor()));
        EmailMonitor emailMonitor = emailMonitorManager.getEmailMonitor(emailMonitorId);
        assertTrue(emailMonitor != null);
        emailMonitorDao.verify();
    }

    public void testSaveEmailMonitor() throws Exception {
        EmailMonitor emailMonitor = new EmailMonitor();

        // set expected behavior on dao
        emailMonitorDao.expects(once()).method("saveEmailMonitor")
            .with(same(emailMonitor)).isVoid();

        emailMonitorManager.saveEmailMonitor(emailMonitor);
        emailMonitorDao.verify();
    }

    public void testAddAndRemoveEmailMonitor() throws Exception {
        EmailMonitor emailMonitor = new EmailMonitor();

        // set required fields
        emailMonitor.setApplyId("YuAdQyXbYwCyNlAdSnZhFjCnIlHfFbEs");
        emailMonitor.setContent("WmOvCjVdRmWsToWhTxSxNrDzSeCxAzWgEnSwMzQaMyYkRlHgTxNrJtSqBoXfQtQpInFxGjAhGmUqDsIxZiYoDkSuFtLhJbMnQhEmYjAkFdVxYpNeShBvQqEdJwYjTmIaKxNsXqVkGrTmQdJeNnBcYlYwRjQxOuEaLoPyCkCaJoGwOmZzQbYtKmHqSaWbBkIzUxBvSlBzMkViDkFmCwLkEnBcOmUkGcDaSjXnFkLnOjDrOjPlQnJhRnWmTvTwVwNvTfSiMjBeRfGnIjZvCuZqQoPrYsYxViKoKgPiPrHxUzJiPlFmZcSvAsDgFgDzYcQiReRlAtDhHuTgIeYaHrFnOyMzHvQjBlDdCfLtIqDeExWuVpYvRgPvFxWkOkPvRmXjYpXzTjWiGzVyQhOnQzVfFhLyIvTfBbZbSxLfEvQsQuZqOrJlInMeCwWtTqXoJnZqUyJzOtGnQwYzVqCsDjTnYqJoOxEyZiHbLoHmGyFzSrRoZjWeYoRu");
        emailMonitor.setDispatchTime(new java.util.Date());
        emailMonitor.setAddressee("email652503654@dev.java.net");
        emailMonitor.setReceiverId("EaYfNgNhXkXzFhCsYcXsVyBiNqSkMp");

        // set expected behavior on dao
        emailMonitorDao.expects(once()).method("saveEmailMonitor")
            .with(same(emailMonitor)).isVoid();
        emailMonitorManager.saveEmailMonitor(emailMonitor);
        emailMonitorDao.verify();

        // reset expectations
        emailMonitorDao.reset();

        emailMonitorDao.expects(once()).method("removeEmailMonitor").with(eq(new String(emailMonitorId)));
        emailMonitorManager.removeEmailMonitor(emailMonitorId);
        emailMonitorDao.verify();

        // reset expectations
        emailMonitorDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(EmailMonitor.class, emailMonitor.getId());
        emailMonitorDao.expects(once()).method("removeEmailMonitor").isVoid();
        emailMonitorDao.expects(once()).method("getEmailMonitor").will(throwException(ex));
        emailMonitorManager.removeEmailMonitor(emailMonitorId);
        try {
            emailMonitorManager.getEmailMonitor(emailMonitorId);
            fail("EmailMonitor with identifier '" + emailMonitorId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        emailMonitorDao.verify();
    }
}
