package com.boco.eoms.duty.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.duty.model.TawRmDispatchRecord;
import com.boco.eoms.duty.dao.ITawRmDispatchRecordDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawRmDispatchRecordDaoTest extends BaseDaoTestCase {
    private String tawRmDispatchRecordId = new String("1");
    private ITawRmDispatchRecordDao dao = null;

    public void setTawRmDispatchRecordDao(ITawRmDispatchRecordDao dao) {
        this.dao = dao;
    }

    public void testAddTawRmDispatchRecord() throws Exception {
        TawRmDispatchRecord tawRmDispatchRecord = new TawRmDispatchRecord();

        // set required fields

        java.lang.String fileName = "IwQgTpXaUoDjVwNqDvGyUgHpAxQiBeQmJcTsMaXuZrOoOoOuMv";
        tawRmDispatchRecord.setFileName(fileName);        

        java.lang.String fileSource = "GoReGzHjKlKeSwRxDlSzNqXjMlPsOzIoUoAzUcRlBvFlDpVcOa";
        tawRmDispatchRecord.setFileSource(fileSource);        

        java.lang.String fileProperty = "EiEcOzCkZvOfUeFaWxNwHkOqGqYrZgNxZkRuEnIaCpCaNdDmZh";
        tawRmDispatchRecord.setFileProperty(fileProperty);        

        java.lang.String dispatchDept = "EjJuNfAgZpVbYeDrAbZxOuArRwSwPmBzJoYcNcRlDkFmGkJtDg";
        tawRmDispatchRecord.setDispatchDept(dispatchDept);        

        java.lang.String dispatcher = "OrJsEzOpYsVbFxQxUiJvEqNsSiMbHn";
        tawRmDispatchRecord.setDispatcher(dispatcher);        

        java.lang.String receiver = "AhItNrHhRgUfEqQvQmIfZhYwGbPcZz";
        tawRmDispatchRecord.setReceiver(receiver);        

        dao.saveTawRmDispatchRecord(tawRmDispatchRecord);

        // verify a primary key was assigned
        assertNotNull(tawRmDispatchRecord.getId());

        // verify set fields are same after save
        assertEquals(fileName, tawRmDispatchRecord.getFileName());
        assertEquals(fileSource, tawRmDispatchRecord.getFileSource());
        assertEquals(fileProperty, tawRmDispatchRecord.getFileProperty());
        assertEquals(dispatchDept, tawRmDispatchRecord.getDispatchDept());
        assertEquals(dispatcher, tawRmDispatchRecord.getDispatcher());
        assertEquals(receiver, tawRmDispatchRecord.getReceiver());
    }

    public void testGetTawRmDispatchRecord() throws Exception {
        TawRmDispatchRecord tawRmDispatchRecord = dao.getTawRmDispatchRecord(tawRmDispatchRecordId);
        assertNotNull(tawRmDispatchRecord);
    }

    public void testGetTawRmDispatchRecords() throws Exception {
        TawRmDispatchRecord tawRmDispatchRecord = new TawRmDispatchRecord();

        List results = dao.getTawRmDispatchRecords(tawRmDispatchRecord);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawRmDispatchRecord() throws Exception {
        TawRmDispatchRecord tawRmDispatchRecord = dao.getTawRmDispatchRecord(tawRmDispatchRecordId);

        // update required fields
        java.lang.String fileName = "TtJbDtNnLcYuAjDjQrYjFdGwOgBpZcYbHtAnMdYvMkQrRnRhUp";
        tawRmDispatchRecord.setFileName(fileName);        
        java.lang.String fileSource = "KyUeDlXjRvGpRjBgZvPzJmVfCaZcBaIsRzBdHhHmYuVnOkYsMs";
        tawRmDispatchRecord.setFileSource(fileSource);        
        java.lang.String fileProperty = "AuJbAaVpQxOtByBkSgRcYlNjWfUnUtOhFrXvIkQqNoSfBcJuNv";
        tawRmDispatchRecord.setFileProperty(fileProperty);        
        java.lang.String dispatchDept = "KrAgSyUlZtAyPtRrYrRjCcGdNhCdMwBfCjJoOgJuIwPpDsDhBp";
        tawRmDispatchRecord.setDispatchDept(dispatchDept);        
        java.lang.String dispatcher = "QkIfNeIkZwPhJfEhXvFlPhSnWeIoBp";
        tawRmDispatchRecord.setDispatcher(dispatcher);        
        java.lang.String receiver = "TaDaYeCtLpItGoEjSiEdNlOyGwCqCe";
        tawRmDispatchRecord.setReceiver(receiver);        

        dao.saveTawRmDispatchRecord(tawRmDispatchRecord);

        assertEquals(fileName, tawRmDispatchRecord.getFileName());
        assertEquals(fileSource, tawRmDispatchRecord.getFileSource());
        assertEquals(fileProperty, tawRmDispatchRecord.getFileProperty());
        assertEquals(dispatchDept, tawRmDispatchRecord.getDispatchDept());
        assertEquals(dispatcher, tawRmDispatchRecord.getDispatcher());
        assertEquals(receiver, tawRmDispatchRecord.getReceiver());
    }

    public void testRemoveTawRmDispatchRecord() throws Exception {
        String removeId = new String("3");
        dao.removeTawRmDispatchRecord(removeId);
        try {
            dao.getTawRmDispatchRecord(removeId);
            fail("tawRmDispatchRecord found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
