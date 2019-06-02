package com.boco.eoms.otherwise.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.otherwise.model.TawRmRenewal;
import com.boco.eoms.otherwise.dao.ITawRmRenewalDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawRmRenewalDaoTest extends BaseDaoTestCase {
    private String tawRmRenewalId = new String("1");
    private ITawRmRenewalDao dao = null;

    public void setTawRmRenewalDao(ITawRmRenewalDao dao) {
        this.dao = dao;
    }

    public void testAddTawRmRenewal() throws Exception {
        TawRmRenewal tawRmRenewal = new TawRmRenewal();

        // set required fields

        java.lang.String testcardId = "RxHoLbKtEhCvEyAbOgYeEbAjDnIxWlGb";
        tawRmRenewal.setTestcardId(testcardId);        

        java.lang.String borrowerId = "RuEbJhJrRpGzUdDjXwQmWbGfQvZrBl";
        tawRmRenewal.setBorrowerId(borrowerId);        

        dao.saveTawRmRenewal(tawRmRenewal);

        // verify a primary key was assigned
        assertNotNull(tawRmRenewal.getId());

        // verify set fields are same after save
        assertEquals(testcardId, tawRmRenewal.getTestcardId());
        assertEquals(borrowerId, tawRmRenewal.getBorrowerId());
    }

    public void testGetTawRmRenewal() throws Exception {
        TawRmRenewal tawRmRenewal = dao.getTawRmRenewal(tawRmRenewalId);
        assertNotNull(tawRmRenewal);
    }

    public void testGetTawRmRenewals() throws Exception {
        TawRmRenewal tawRmRenewal = new TawRmRenewal();

        List results = dao.getTawRmRenewals(tawRmRenewal);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawRmRenewal() throws Exception {
        TawRmRenewal tawRmRenewal = dao.getTawRmRenewal(tawRmRenewalId);

        // update required fields
        java.lang.String testcardId = "WlEeHjJtOiGsRfHqCaAfBxNbYdLkMhWi";
        tawRmRenewal.setTestcardId(testcardId);        
        java.lang.String borrowerId = "SwDpFeDaHgDqOaRuDlCwFePyRoEhBb";
        tawRmRenewal.setBorrowerId(borrowerId);        

        dao.saveTawRmRenewal(tawRmRenewal);

        assertEquals(testcardId, tawRmRenewal.getTestcardId());
        assertEquals(borrowerId, tawRmRenewal.getBorrowerId());
    }

    public void testRemoveTawRmRenewal() throws Exception {
        String removeId = new String("3");
        dao.removeTawRmRenewal(removeId);
        try {
            dao.getTawRmRenewal(removeId);
            fail("tawRmRenewal found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
