package com.boco.eoms.otherwise.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.otherwise.model.TawRmInoutRecord;
import com.boco.eoms.otherwise.dao.ITawRmInoutRecordDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawRmInoutRecordDaoTest extends BaseDaoTestCase {
    private String tawRmInoutRecordId = new String("1");
    private ITawRmInoutRecordDao dao = null;

    public void setTawRmInoutRecordDao(ITawRmInoutRecordDao dao) {
        this.dao = dao;
    }

    public void testAddTawRmInoutRecord() throws Exception {
        TawRmInoutRecord tawRmInoutRecord = new TawRmInoutRecord();

        // set required fields

        java.lang.String testcardId = "ThJdSoBsOvYoEaRbUtGoSxPhEnOiAoMo";
        tawRmInoutRecord.setTestcardId(testcardId);        

        java.lang.String borrowerId = "JdKcMtIrNtTwZgUcQfAxRrOyTfIcRv";
        tawRmInoutRecord.setBorrowerId(borrowerId);        

        dao.saveTawRmInoutRecord(tawRmInoutRecord);

        // verify a primary key was assigned
        assertNotNull(tawRmInoutRecord.getId());

        // verify set fields are same after save
        assertEquals(testcardId, tawRmInoutRecord.getTestcardId());
        assertEquals(borrowerId, tawRmInoutRecord.getBorrowerId());
    }

    public void testGetTawRmInoutRecord() throws Exception {
        TawRmInoutRecord tawRmInoutRecord = dao.getTawRmInoutRecord(tawRmInoutRecordId);
        assertNotNull(tawRmInoutRecord);
    }

    public void testGetTawRmInoutRecords() throws Exception {
        TawRmInoutRecord tawRmInoutRecord = new TawRmInoutRecord();

        List results = dao.getTawRmInoutRecords(tawRmInoutRecord);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawRmInoutRecord() throws Exception {
        TawRmInoutRecord tawRmInoutRecord = dao.getTawRmInoutRecord(tawRmInoutRecordId);

        // update required fields
        java.lang.String testcardId = "VyDoSoPkPvExKkRzKiFcSqWwYxPjFnXt";
        tawRmInoutRecord.setTestcardId(testcardId);        
        java.lang.String borrowerId = "JnOyZrObJpBdMyPsHlExCcAfZpJjHb";
        tawRmInoutRecord.setBorrowerId(borrowerId);        

        dao.saveTawRmInoutRecord(tawRmInoutRecord);

        assertEquals(testcardId, tawRmInoutRecord.getTestcardId());
        assertEquals(borrowerId, tawRmInoutRecord.getBorrowerId());
    }

    public void testRemoveTawRmInoutRecord() throws Exception {
        String removeId = new String("3");
        dao.removeTawRmInoutRecord(removeId);
        try {
            dao.getTawRmInoutRecord(removeId);
            fail("tawRmInoutRecord found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
