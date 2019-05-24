package com.boco.eoms.extra.supplierkpi.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInstanceDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSupplierkpiInstanceDaoTest extends BaseDaoTestCase {
    private String tawSupplierkpiInstanceId = new String("1");
    private TawSupplierkpiInstanceDao dao = null;

    public void setTawSupplierkpiInstanceDao(TawSupplierkpiInstanceDao dao) {
        this.dao = dao;
    }

    public void testAddTawSupplierkpiInstance() throws Exception {
        TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();

        // set required fields

        dao.saveTawSupplierkpiInstance(tawSupplierkpiInstance);

        // verify a primary key was assigned
        assertNotNull(tawSupplierkpiInstance.getId());

        // verify set fields are same after save
    }

    public void testGetTawSupplierkpiInstance() throws Exception {
        TawSupplierkpiInstance tawSupplierkpiInstance = dao.getTawSupplierkpiInstance(tawSupplierkpiInstanceId);
        assertNotNull(tawSupplierkpiInstance);
    }

    public void testGetTawSupplierkpiInstances() throws Exception {
        TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();

        List results = dao.getTawSupplierkpiInstances(tawSupplierkpiInstance);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSupplierkpiInstance() throws Exception {
        TawSupplierkpiInstance tawSupplierkpiInstance = dao.getTawSupplierkpiInstance(tawSupplierkpiInstanceId);

        // update required fields

        dao.saveTawSupplierkpiInstance(tawSupplierkpiInstance);

    }

    public void testRemoveTawSupplierkpiInstance() throws Exception {
        String removeId = new String("3");
        dao.removeTawSupplierkpiInstance(removeId);
        try {
            dao.getTawSupplierkpiInstance(removeId);
            fail("tawSupplierkpiInstance found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
