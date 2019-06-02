package com.boco.eoms.extra.supplierkpi.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiAssessInstanceDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSupplierkpiAssessInstanceDaoTest extends BaseDaoTestCase {
    private String tawSupplierkpiAssessInstanceId = new String("1");
    private TawSupplierkpiAssessInstanceDao dao = null;

    public void setTawSupplierkpiAssessInstanceDao(TawSupplierkpiAssessInstanceDao dao) {
        this.dao = dao;
    }

    public void testAddTawSupplierkpiAssessInstance() throws Exception {
        TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = new TawSupplierkpiAssessInstance();

        // set required fields

        dao.saveTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstance);

        // verify a primary key was assigned
        assertNotNull(tawSupplierkpiAssessInstance.getId());

        // verify set fields are same after save
    }

    public void testGetTawSupplierkpiAssessInstance() throws Exception {
        TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = dao.getTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstanceId);
        assertNotNull(tawSupplierkpiAssessInstance);
    }

    public void testGetTawSupplierkpiAssessInstances() throws Exception {
        TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = new TawSupplierkpiAssessInstance();

        List results = dao.getTawSupplierkpiAssessInstances(tawSupplierkpiAssessInstance);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSupplierkpiAssessInstance() throws Exception {
        TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = dao.getTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstanceId);

        // update required fields

        dao.saveTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstance);

    }

    public void testRemoveTawSupplierkpiAssessInstance() throws Exception {
        String removeId = new String("3");
        dao.removeTawSupplierkpiAssessInstance(removeId);
        try {
            dao.getTawSupplierkpiAssessInstance(removeId);
            fail("tawSupplierkpiAssessInstance found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
