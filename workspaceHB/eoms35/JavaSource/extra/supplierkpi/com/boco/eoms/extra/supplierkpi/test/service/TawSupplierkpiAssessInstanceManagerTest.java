
package com.boco.eoms.extra.supplierkpi.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiAssessInstanceDao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;
import com.boco.eoms.extra.supplierkpi.service.impl.TawSupplierkpiAssessInstanceManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSupplierkpiAssessInstanceManagerTest extends BaseManagerTestCase {
    private final String tawSupplierkpiAssessInstanceId = "1";
    private TawSupplierkpiAssessInstanceManagerImpl tawSupplierkpiAssessInstanceManager = new TawSupplierkpiAssessInstanceManagerImpl();
    private Mock tawSupplierkpiAssessInstanceDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSupplierkpiAssessInstanceDao = new Mock(TawSupplierkpiAssessInstanceDao.class);
        tawSupplierkpiAssessInstanceManager.setTawSupplierkpiAssessInstanceDao((TawSupplierkpiAssessInstanceDao) tawSupplierkpiAssessInstanceDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSupplierkpiAssessInstanceManager = null;
    }

    public void testGetTawSupplierkpiAssessInstances() throws Exception {
        List results = new ArrayList();
        TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = new TawSupplierkpiAssessInstance();
        results.add(tawSupplierkpiAssessInstance);

        // set expected behavior on dao
        tawSupplierkpiAssessInstanceDao.expects(once()).method("getTawSupplierkpiAssessInstances")
                .will(returnValue(results));

        List tawSupplierkpiAssessInstances = tawSupplierkpiAssessInstanceManager.getTawSupplierkpiAssessInstances(null);
        assertTrue(tawSupplierkpiAssessInstances.size() == 1);
        tawSupplierkpiAssessInstanceDao.verify();
    }

    public void testGetTawSupplierkpiAssessInstance() throws Exception {
        // set expected behavior on dao
        tawSupplierkpiAssessInstanceDao.expects(once()).method("getTawSupplierkpiAssessInstance")
                .will(returnValue(new TawSupplierkpiAssessInstance()));
        TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = tawSupplierkpiAssessInstanceManager.getTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstanceId);
        assertTrue(tawSupplierkpiAssessInstance != null);
        tawSupplierkpiAssessInstanceDao.verify();
    }

    public void testSaveTawSupplierkpiAssessInstance() throws Exception {
        TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = new TawSupplierkpiAssessInstance();

        // set expected behavior on dao
        tawSupplierkpiAssessInstanceDao.expects(once()).method("saveTawSupplierkpiAssessInstance")
                .with(same(tawSupplierkpiAssessInstance)).isVoid();

        tawSupplierkpiAssessInstanceManager.saveTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstance);
        tawSupplierkpiAssessInstanceDao.verify();
    }

    public void testAddAndRemoveTawSupplierkpiAssessInstance() throws Exception {
        TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = new TawSupplierkpiAssessInstance();

        // set required fields

        // set expected behavior on dao
        tawSupplierkpiAssessInstanceDao.expects(once()).method("saveTawSupplierkpiAssessInstance")
                .with(same(tawSupplierkpiAssessInstance)).isVoid();
        tawSupplierkpiAssessInstanceManager.saveTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstance);
        tawSupplierkpiAssessInstanceDao.verify();

        // reset expectations
        tawSupplierkpiAssessInstanceDao.reset();

        tawSupplierkpiAssessInstanceDao.expects(once()).method("removeTawSupplierkpiAssessInstance").with(eq(new String(tawSupplierkpiAssessInstanceId)));
        tawSupplierkpiAssessInstanceManager.removeTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstanceId);
        tawSupplierkpiAssessInstanceDao.verify();

        // reset expectations
        tawSupplierkpiAssessInstanceDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSupplierkpiAssessInstance.class, tawSupplierkpiAssessInstance.getId());
        tawSupplierkpiAssessInstanceDao.expects(once()).method("removeTawSupplierkpiAssessInstance").isVoid();
        tawSupplierkpiAssessInstanceDao.expects(once()).method("getTawSupplierkpiAssessInstance").will(throwException(ex));
        tawSupplierkpiAssessInstanceManager.removeTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstanceId);
        try {
            tawSupplierkpiAssessInstanceManager.getTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstanceId);
            fail("TawSupplierkpiAssessInstance with identifier '" + tawSupplierkpiAssessInstanceId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSupplierkpiAssessInstanceDao.verify();
    }
}
