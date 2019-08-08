
package com.boco.eoms.extra.supplierkpi.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInstanceDao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.service.impl.TawSupplierkpiInstanceManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSupplierkpiInstanceManagerTest extends BaseManagerTestCase {
    private final String tawSupplierkpiInstanceId = "1";
    private TawSupplierkpiInstanceManagerImpl tawSupplierkpiInstanceManager = new TawSupplierkpiInstanceManagerImpl();
    private Mock tawSupplierkpiInstanceDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSupplierkpiInstanceDao = new Mock(TawSupplierkpiInstanceDao.class);
        tawSupplierkpiInstanceManager.setTawSupplierkpiInstanceDao((TawSupplierkpiInstanceDao) tawSupplierkpiInstanceDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSupplierkpiInstanceManager = null;
    }

    public void testGetTawSupplierkpiInstances() throws Exception {
        List results = new ArrayList();
        TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
        results.add(tawSupplierkpiInstance);

        // set expected behavior on dao
        tawSupplierkpiInstanceDao.expects(once()).method("getTawSupplierkpiInstances")
                .will(returnValue(results));

        //List tawSupplierkpiInstances = tawSupplierkpiInstanceManager.getTawSupplierkpiInstances(null);
        //assertTrue(tawSupplierkpiInstances.size() == 1);
        tawSupplierkpiInstanceDao.verify();
    }

    public void testGetTawSupplierkpiInstance() throws Exception {
        // set expected behavior on dao
        tawSupplierkpiInstanceDao.expects(once()).method("getTawSupplierkpiInstance")
                .will(returnValue(new TawSupplierkpiInstance()));
        TawSupplierkpiInstance tawSupplierkpiInstance = tawSupplierkpiInstanceManager.getTawSupplierkpiInstance(tawSupplierkpiInstanceId);
        assertTrue(tawSupplierkpiInstance != null);
        tawSupplierkpiInstanceDao.verify();
    }

    public void testSaveTawSupplierkpiInstance() throws Exception {
        TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();

        // set expected behavior on dao
        tawSupplierkpiInstanceDao.expects(once()).method("saveTawSupplierkpiInstance")
                .with(same(tawSupplierkpiInstance)).isVoid();

        tawSupplierkpiInstanceManager.saveTawSupplierkpiInstance(tawSupplierkpiInstance);
        tawSupplierkpiInstanceDao.verify();
    }

    public void testAddAndRemoveTawSupplierkpiInstance() throws Exception {
        TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();

        // set required fields

        // set expected behavior on dao
        tawSupplierkpiInstanceDao.expects(once()).method("saveTawSupplierkpiInstance")
                .with(same(tawSupplierkpiInstance)).isVoid();
        tawSupplierkpiInstanceManager.saveTawSupplierkpiInstance(tawSupplierkpiInstance);
        tawSupplierkpiInstanceDao.verify();

        // reset expectations
        tawSupplierkpiInstanceDao.reset();

        tawSupplierkpiInstanceDao.expects(once()).method("removeTawSupplierkpiInstance").with(eq(new String(tawSupplierkpiInstanceId)));
        tawSupplierkpiInstanceManager.removeTawSupplierkpiInstance(tawSupplierkpiInstanceId);
        tawSupplierkpiInstanceDao.verify();

        // reset expectations
        tawSupplierkpiInstanceDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSupplierkpiInstance.class, tawSupplierkpiInstance.getId());
        tawSupplierkpiInstanceDao.expects(once()).method("removeTawSupplierkpiInstance").isVoid();
        tawSupplierkpiInstanceDao.expects(once()).method("getTawSupplierkpiInstance").will(throwException(ex));
        tawSupplierkpiInstanceManager.removeTawSupplierkpiInstance(tawSupplierkpiInstanceId);
        try {
            tawSupplierkpiInstanceManager.getTawSupplierkpiInstance(tawSupplierkpiInstanceId);
            fail("TawSupplierkpiInstance with identifier '" + tawSupplierkpiInstanceId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSupplierkpiInstanceDao.verify();
    }
}
