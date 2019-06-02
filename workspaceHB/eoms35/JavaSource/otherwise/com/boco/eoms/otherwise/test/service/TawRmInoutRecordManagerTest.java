
package com.boco.eoms.otherwise.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.otherwise.dao.ITawRmInoutRecordDao;
import com.boco.eoms.otherwise.model.TawRmInoutRecord;
import com.boco.eoms.otherwise.service.impl.TawRmInoutRecordManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawRmInoutRecordManagerTest extends BaseManagerTestCase {
    private final String tawRmInoutRecordId = "1";
    private TawRmInoutRecordManagerImpl tawRmInoutRecordManager = new TawRmInoutRecordManagerImpl();
    private Mock tawRmInoutRecordDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawRmInoutRecordDao = new Mock(ITawRmInoutRecordDao.class);
        tawRmInoutRecordManager.setTawRmInoutRecordDao((ITawRmInoutRecordDao) tawRmInoutRecordDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawRmInoutRecordManager = null;
    }

    public void testGetTawRmInoutRecords() throws Exception {
        List results = new ArrayList();
        TawRmInoutRecord tawRmInoutRecord = new TawRmInoutRecord();
        results.add(tawRmInoutRecord);

        // set expected behavior on dao
        tawRmInoutRecordDao.expects(once()).method("getTawRmInoutRecords")
            .will(returnValue(results));

        List tawRmInoutRecords = tawRmInoutRecordManager.getTawRmInoutRecords(null);
        assertTrue(tawRmInoutRecords.size() == 1);
        tawRmInoutRecordDao.verify();
    }

    public void testGetTawRmInoutRecord() throws Exception {
        // set expected behavior on dao
        tawRmInoutRecordDao.expects(once()).method("getTawRmInoutRecord")
            .will(returnValue(new TawRmInoutRecord()));
        TawRmInoutRecord tawRmInoutRecord = tawRmInoutRecordManager.getTawRmInoutRecord(tawRmInoutRecordId);
        assertTrue(tawRmInoutRecord != null);
        tawRmInoutRecordDao.verify();
    }

    public void testSaveTawRmInoutRecord() throws Exception {
        TawRmInoutRecord tawRmInoutRecord = new TawRmInoutRecord();

        // set expected behavior on dao
        tawRmInoutRecordDao.expects(once()).method("saveTawRmInoutRecord")
            .with(same(tawRmInoutRecord)).isVoid();

        tawRmInoutRecordManager.saveTawRmInoutRecord(tawRmInoutRecord);
        tawRmInoutRecordDao.verify();
    }

    public void testAddAndRemoveTawRmInoutRecord() throws Exception {
        TawRmInoutRecord tawRmInoutRecord = new TawRmInoutRecord();

        // set required fields
        tawRmInoutRecord.setTestcardId("ZuFnSkEeXuJgCgXhTbEhAxVcWeVmFvZf");
        tawRmInoutRecord.setBorrowerId("GsLcFyScOwNmFvTvMkMmZoIpLhVyFi");

        // set expected behavior on dao
        tawRmInoutRecordDao.expects(once()).method("saveTawRmInoutRecord")
            .with(same(tawRmInoutRecord)).isVoid();
        tawRmInoutRecordManager.saveTawRmInoutRecord(tawRmInoutRecord);
        tawRmInoutRecordDao.verify();

        // reset expectations
        tawRmInoutRecordDao.reset();

        tawRmInoutRecordDao.expects(once()).method("removeTawRmInoutRecord").with(eq(new String(tawRmInoutRecordId)));
        tawRmInoutRecordManager.removeTawRmInoutRecord(tawRmInoutRecordId);
        tawRmInoutRecordDao.verify();

        // reset expectations
        tawRmInoutRecordDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawRmInoutRecord.class, tawRmInoutRecord.getId());
        tawRmInoutRecordDao.expects(once()).method("removeTawRmInoutRecord").isVoid();
        tawRmInoutRecordDao.expects(once()).method("getTawRmInoutRecord").will(throwException(ex));
        tawRmInoutRecordManager.removeTawRmInoutRecord(tawRmInoutRecordId);
        try {
            tawRmInoutRecordManager.getTawRmInoutRecord(tawRmInoutRecordId);
            fail("TawRmInoutRecord with identifier '" + tawRmInoutRecordId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawRmInoutRecordDao.verify();
    }
}
