
package com.boco.eoms.otherwise.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.otherwise.dao.ITawRmRenewalDao;
import com.boco.eoms.otherwise.model.TawRmRenewal;
import com.boco.eoms.otherwise.service.impl.TawRmRenewalManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawRmRenewalManagerTest extends BaseManagerTestCase {
    private final String tawRmRenewalId = "1";
    private TawRmRenewalManagerImpl tawRmRenewalManager = new TawRmRenewalManagerImpl();
    private Mock tawRmRenewalDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawRmRenewalDao = new Mock(ITawRmRenewalDao.class);
        tawRmRenewalManager.setTawRmRenewalDao((ITawRmRenewalDao) tawRmRenewalDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawRmRenewalManager = null;
    }

    public void testGetTawRmRenewals() throws Exception {
        List results = new ArrayList();
        TawRmRenewal tawRmRenewal = new TawRmRenewal();
        results.add(tawRmRenewal);

        // set expected behavior on dao
        tawRmRenewalDao.expects(once()).method("getTawRmRenewals")
            .will(returnValue(results));

        List tawRmRenewals = tawRmRenewalManager.getTawRmRenewals(null);
        assertTrue(tawRmRenewals.size() == 1);
        tawRmRenewalDao.verify();
    }

    public void testGetTawRmRenewal() throws Exception {
        // set expected behavior on dao
        tawRmRenewalDao.expects(once()).method("getTawRmRenewal")
            .will(returnValue(new TawRmRenewal()));
        TawRmRenewal tawRmRenewal = tawRmRenewalManager.getTawRmRenewal(tawRmRenewalId);
        assertTrue(tawRmRenewal != null);
        tawRmRenewalDao.verify();
    }

    public void testSaveTawRmRenewal() throws Exception {
        TawRmRenewal tawRmRenewal = new TawRmRenewal();

        // set expected behavior on dao
        tawRmRenewalDao.expects(once()).method("saveTawRmRenewal")
            .with(same(tawRmRenewal)).isVoid();

        tawRmRenewalManager.saveTawRmRenewal(tawRmRenewal);
        tawRmRenewalDao.verify();
    }

    public void testAddAndRemoveTawRmRenewal() throws Exception {
        TawRmRenewal tawRmRenewal = new TawRmRenewal();

        // set required fields
        tawRmRenewal.setTestcardId("DcJbBqRcUjQxGcYiXqFzRpLdSsTrTjOm");
        tawRmRenewal.setBorrowerId("XeOaDkReVxHkEuLxZbMuDaMlJvKfSa");

        // set expected behavior on dao
        tawRmRenewalDao.expects(once()).method("saveTawRmRenewal")
            .with(same(tawRmRenewal)).isVoid();
        tawRmRenewalManager.saveTawRmRenewal(tawRmRenewal);
        tawRmRenewalDao.verify();

        // reset expectations
        tawRmRenewalDao.reset();

        tawRmRenewalDao.expects(once()).method("removeTawRmRenewal").with(eq(new String(tawRmRenewalId)));
        tawRmRenewalManager.removeTawRmRenewal(tawRmRenewalId);
        tawRmRenewalDao.verify();

        // reset expectations
        tawRmRenewalDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawRmRenewal.class, tawRmRenewal.getId());
        tawRmRenewalDao.expects(once()).method("removeTawRmRenewal").isVoid();
        tawRmRenewalDao.expects(once()).method("getTawRmRenewal").will(throwException(ex));
        tawRmRenewalManager.removeTawRmRenewal(tawRmRenewalId);
        try {
            tawRmRenewalManager.getTawRmRenewal(tawRmRenewalId);
            fail("TawRmRenewal with identifier '" + tawRmRenewalId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawRmRenewalDao.verify();
    }
}
