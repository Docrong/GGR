package com.boco.eoms.commons.system.code.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.code.model.TawSystemCode;
import com.boco.eoms.commons.system.code.dao.ITawSystemCodeDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemCodeDaoTest extends BaseDaoTestCase {
    private String tawSystemCodeId = new String("1");
    private ITawSystemCodeDao dao = null;

    public void setTawSystemCodeDao(ITawSystemCodeDao dao) {
        this.dao = dao;
    }

    public void testAddTawSystemCode() throws Exception {
        TawSystemCode tawSystemCode = new TawSystemCode();

        // set required fields

        dao.saveTawSystemCode(tawSystemCode);

        // verify a primary key was assigned
        assertNotNull(tawSystemCode.getId());

        // verify set fields are same after save
    }

    public void testGetTawSystemCode() throws Exception {
        TawSystemCode tawSystemCode = dao.getTawSystemCode(tawSystemCodeId);
        assertNotNull(tawSystemCode);
    }

    public void testGetTawSystemCodes() throws Exception {
        TawSystemCode tawSystemCode = new TawSystemCode();

        List results = dao.getTawSystemCodes(tawSystemCode);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSystemCode() throws Exception {
        TawSystemCode tawSystemCode = dao.getTawSystemCode(tawSystemCodeId);

        // update required fields

        dao.saveTawSystemCode(tawSystemCode);

    }

    public void testRemoveTawSystemCode() throws Exception {
        String removeId = new String("3");
        dao.removeTawSystemCode(removeId);
        try {
            dao.getTawSystemCode(removeId);
            fail("tawSystemCode found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
