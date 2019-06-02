package com.boco.eoms.parter.baseinfo.carmgr.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr;
import com.boco.eoms.parter.baseinfo.carmgr.dao.ICarMgrDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class CarMgrDaoTest extends BaseDaoTestCase {
    private String carMgrId = new String("1");
    private ICarMgrDao dao = null;

    public void setCarMgrDao(ICarMgrDao dao) {
        this.dao = dao;
    }

    public void testAddCarMgr() throws Exception {
        CarMgr carMgr = new CarMgr();

        // set required fields

        dao.saveCarMgr(carMgr);

        // verify a primary key was assigned
        assertNotNull(carMgr.getId());

        // verify set fields are same after save
    }

    public void testGetCarMgr() throws Exception {
        CarMgr carMgr = dao.getCarMgr(carMgrId);
        assertNotNull(carMgr);
    }

    public void testGetCarMgrs() throws Exception {
        CarMgr carMgr = new CarMgr();

        List results = dao.getCarMgrs(carMgr);
        assertTrue(results.size() > 0);
    }

    public void testSaveCarMgr() throws Exception {
        CarMgr carMgr = dao.getCarMgr(carMgrId);

        // update required fields

        dao.saveCarMgr(carMgr);

    }

    public void testRemoveCarMgr() throws Exception {
        String removeId = new String("3");
        dao.removeCarMgr(removeId);
        try {
            dao.getCarMgr(removeId);
            fail("carMgr found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
