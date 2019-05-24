
package com.boco.eoms.parter.baseinfo.carmgr.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.parter.baseinfo.carmgr.dao.ICarMgrDao;
import com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr;
import com.boco.eoms.parter.baseinfo.carmgr.service.impl.CarMgrManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class CarMgrManagerTest extends BaseManagerTestCase {
    private final String carMgrId = "1";
    private CarMgrManagerImpl carMgrManager = new CarMgrManagerImpl();
    private Mock carMgrDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        carMgrDao = new Mock(ICarMgrDao.class);
        carMgrManager.setCarMgrDao((ICarMgrDao) carMgrDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        carMgrManager = null;
    }

    public void testGetCarMgrs() throws Exception {
        List results = new ArrayList();
        CarMgr carMgr = new CarMgr();
        results.add(carMgr);

        // set expected behavior on dao
        carMgrDao.expects(once()).method("getCarMgrs")
            .will(returnValue(results));

        List carMgrs = carMgrManager.getCarMgrs(null);
        assertTrue(carMgrs.size() == 1);
        carMgrDao.verify();
    }

    public void testGetCarMgr() throws Exception {
        // set expected behavior on dao
        carMgrDao.expects(once()).method("getCarMgr")
            .will(returnValue(new CarMgr()));
        CarMgr carMgr = carMgrManager.getCarMgr(carMgrId);
        assertTrue(carMgr != null);
        carMgrDao.verify();
    }

    public void testSaveCarMgr() throws Exception {
        CarMgr carMgr = new CarMgr();

        // set expected behavior on dao
        carMgrDao.expects(once()).method("saveCarMgr")
            .with(same(carMgr)).isVoid();

        carMgrManager.saveCarMgr(carMgr);
        carMgrDao.verify();
    }

    public void testAddAndRemoveCarMgr() throws Exception {
        CarMgr carMgr = new CarMgr();

        // set required fields

        // set expected behavior on dao
        carMgrDao.expects(once()).method("saveCarMgr")
            .with(same(carMgr)).isVoid();
        carMgrManager.saveCarMgr(carMgr);
        carMgrDao.verify();

        // reset expectations
        carMgrDao.reset();

        carMgrDao.expects(once()).method("removeCarMgr").with(eq(new String(carMgrId)));
        carMgrManager.removeCarMgr(carMgrId);
        carMgrDao.verify();

        // reset expectations
        carMgrDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(CarMgr.class, carMgr.getId());
        carMgrDao.expects(once()).method("removeCarMgr").isVoid();
        carMgrDao.expects(once()).method("getCarMgr").will(throwException(ex));
        carMgrManager.removeCarMgr(carMgrId);
        try {
            carMgrManager.getCarMgr(carMgrId);
            fail("CarMgr with identifier '" + carMgrId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        carMgrDao.verify();
    }
}
