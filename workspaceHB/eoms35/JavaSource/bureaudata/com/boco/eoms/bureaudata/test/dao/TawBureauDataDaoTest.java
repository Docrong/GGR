package com.boco.eoms.bureaudata.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.bureaudata.model.TawBureauData;
import com.boco.eoms.bureaudata.dao.ITawBureauDataDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawBureauDataDaoTest extends BaseDaoTestCase {
    private String tawBureauDataId = new String("1");
    private ITawBureauDataDao dao = null;

    public void setTawBureauDataDao(ITawBureauDataDao dao) {
        this.dao = dao;
    }

    public void testAddTawBureauData() throws Exception {
        TawBureauData tawBureauData = new TawBureauData();

        // set required fields

        dao.saveTawBureauData(tawBureauData);

        // verify a primary key was assigned
        assertNotNull(tawBureauData.getId());

        // verify set fields are same after save
    }

    public void testGetTawBureauData() throws Exception {
        TawBureauData tawBureauData = dao.getTawBureauData(tawBureauDataId);
        assertNotNull(tawBureauData);
    }

    public void testGetTawBureauDatas() throws Exception {
        TawBureauData tawBureauData = new TawBureauData();

        List results = dao.getTawBureauDatas(tawBureauData);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawBureauData() throws Exception {
        TawBureauData tawBureauData = dao.getTawBureauData(tawBureauDataId);

        // update required fields

        dao.saveTawBureauData(tawBureauData);

    }

    public void testRemoveTawBureauData() throws Exception {
        String removeId = new String("3");
        dao.removeTawBureauData(removeId);
        try {
            dao.getTawBureauData(removeId);
            fail("tawBureauData found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
