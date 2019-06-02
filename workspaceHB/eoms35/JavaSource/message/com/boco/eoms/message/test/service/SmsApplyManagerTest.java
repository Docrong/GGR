
package com.boco.eoms.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.message.dao.SmsApplyDao;
import com.boco.eoms.message.mgr.impl.SmsApplyManagerImpl;
import com.boco.eoms.message.model.SmsApply;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsApplyManagerTest extends BaseManagerTestCase {
    private final String smsApplyId = "1";
    private SmsApplyManagerImpl smsApplyManager = new SmsApplyManagerImpl();
    private Mock smsApplyDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        smsApplyDao = new Mock(SmsApplyDao.class);
        smsApplyManager.setSmsApplyDao((SmsApplyDao) smsApplyDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        smsApplyManager = null;
    }

    public void testGetSmsApplys() throws Exception {
        List results = new ArrayList();
        SmsApply smsApply = new SmsApply();
        results.add(smsApply);

        // set expected behavior on dao
        smsApplyDao.expects(once()).method("getSmsApplys")
            .will(returnValue(results));

        List smsApplys = smsApplyManager.getSmsApplys(null);
        assertTrue(smsApplys.size() == 1);
        smsApplyDao.verify();
    }

    public void testGetSmsApply() throws Exception {
        // set expected behavior on dao
        smsApplyDao.expects(once()).method("getSmsApply")
            .will(returnValue(new SmsApply()));
        SmsApply smsApply = smsApplyManager.getSmsApply(smsApplyId);
        assertTrue(smsApply != null);
        smsApplyDao.verify();
    }

    public void testSaveSmsApply() throws Exception {
        SmsApply smsApply = new SmsApply();

        // set expected behavior on dao
        smsApplyDao.expects(once()).method("saveSmsApply")
            .with(same(smsApply)).isVoid();

        smsApplyManager.saveSmsApply(smsApply);
        smsApplyDao.verify();
    }

    public void testAddAndRemoveSmsApply() throws Exception {
        SmsApply smsApply = new SmsApply();

        // set required fields
        smsApply.setCount(new Integer(72903508));
        smsApply.setEndTime(new java.util.Date());
        smsApply.setName("CcCdFxOoIbYfBvAqBeXlPkDnThYcZdYvXdOnWqAxNvDhHkWkXqQuVpMcFyWqXjUsVxJeXqBmFvByKtObLtHiIwYqYsOjMnJyRdPz");
        smsApply.setInterval("OjIzEdZoWh");
        smsApply.setMobile("KrYaQgHxSeLnLoIdRiZw");
        smsApply.setReceiverId("BcYgIgJyWtWaJjStAbNgOkIcVjJgHs");
        smsApply.setReceiverType("X");
        smsApply.setRegetData("Q");
        smsApply.setRemark("LmCjKoDtHxCmQwVnSkPbAkSeWnSiFzHxYhHpElAzQxLtQbIsOsLmXkZhYgLqArUyDeRwBoNwKaIwBeSmAfVzZtTuBgCpZhPwYdAvFxFjUdAjJcMyJiNzAkEaRaBsRpCuZcWlMgCtSkFiLqHlZcKwWmFsPsJjRgBnFlTiXxEaTvBhMmUiFcYdCyTvJpTcUsVfNdYuEpNt");
        
        smsApply.setServiceId("QoWgDkXkBsEnQpBgCgCtRkRbDuDjMgUv");
        smsApply.setStartTime(new java.util.Date());
        smsApply.setUserId("ZeUnTaUgLrSjEkEwBqXtLhMtPnEbPp");

        // set expected behavior on dao
        smsApplyDao.expects(once()).method("saveSmsApply")
            .with(same(smsApply)).isVoid();
        smsApplyManager.saveSmsApply(smsApply);
        smsApplyDao.verify();

        // reset expectations
        smsApplyDao.reset();

        smsApplyDao.expects(once()).method("removeSmsApply").with(eq(new String(smsApplyId)));
        smsApplyManager.removeSmsApply(smsApplyId);
        smsApplyDao.verify();

        // reset expectations
        smsApplyDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(SmsApply.class, smsApply.getId());
        smsApplyDao.expects(once()).method("removeSmsApply").isVoid();
        smsApplyDao.expects(once()).method("getSmsApply").will(throwException(ex));
        smsApplyManager.removeSmsApply(smsApplyId);
        try {
            smsApplyManager.getSmsApply(smsApplyId);
            fail("SmsApply with identifier '" + smsApplyId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        smsApplyDao.verify();
    }
}
