
package com.boco.eoms.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.message.dao.SmsServiceDao;
import com.boco.eoms.message.mgr.impl.SmsServiceManagerImpl;
import com.boco.eoms.message.model.SmsService;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsServiceManagerTest extends BaseManagerTestCase {
    private final String smsServiceId = "1";
    private SmsServiceManagerImpl smsServiceManager = new SmsServiceManagerImpl();
    private Mock smsServiceDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        smsServiceDao = new Mock(SmsServiceDao.class);
        smsServiceManager.setSmsServiceDao((SmsServiceDao) smsServiceDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        smsServiceManager = null;
    }

    public void testGetSmsServices() throws Exception {
        List results = new ArrayList();
        SmsService smsService = new SmsService();
        results.add(smsService);

        // set expected behavior on dao
        smsServiceDao.expects(once()).method("getSmsServices")
            .will(returnValue(results));

        List smsServices = smsServiceManager.getSmsServices(null);
        assertTrue(smsServices.size() == 1);
        smsServiceDao.verify();
    }

    public void testGetSmsService() throws Exception {
        // set expected behavior on dao
        smsServiceDao.expects(once()).method("getSmsService")
            .will(returnValue(new SmsService()));
        SmsService smsService = smsServiceManager.getSmsService(smsServiceId);
        assertTrue(smsService != null);
        smsServiceDao.verify();
    }
    
    public void testSaveSmsService() throws Exception {
        SmsService smsService = new SmsService();

        // set expected behavior on dao
        smsServiceDao.expects(once()).method("saveSmsService")
            .with(same(smsService)).isVoid();

        smsServiceManager.saveSmsService(smsService);
        smsServiceDao.verify();
    }

    public void testAddAndRemoveSmsService() throws Exception {
        SmsService smsService = new SmsService();

        // set required fields
        smsService.setDeleted("Q");
        smsService.setIsSendImediat("C");
        smsService.setIsSendNight("O");
        smsService.setMsgType("H");
        smsService.setName("KzHiQzXsJmStEaMtZhLyBuHgPmBbTdCfSwNmPwQiErVwYzYiDgIgRiSlNbZqRiStWnWdBzZcFtIbWmWcXdEdCoHmQtUpGfZiOeJs");
        smsService.setUserId("SkFcJoCvIuBvZlQqWpVvWuNaGmQoSi");

        // set expected behavior on dao
        smsServiceDao.expects(once()).method("saveSmsService")
            .with(same(smsService)).isVoid();
        smsServiceManager.saveSmsService(smsService);
        smsServiceDao.verify();

        // reset expectations
        smsServiceDao.reset();

        smsServiceDao.expects(once()).method("removeSmsService").with(eq(new String(smsServiceId)));
        smsServiceManager.removeSmsService(smsServiceId);
        smsServiceDao.verify();

        // reset expectations
        smsServiceDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(SmsService.class, smsService.getId());
        smsServiceDao.expects(once()).method("removeSmsService").isVoid();
        smsServiceDao.expects(once()).method("getSmsService").will(throwException(ex));
        smsServiceManager.removeSmsService(smsServiceId);
        try {
            smsServiceManager.getSmsService(smsServiceId);
            fail("SmsService with identifier '" + smsServiceId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        smsServiceDao.verify();
    }
}
