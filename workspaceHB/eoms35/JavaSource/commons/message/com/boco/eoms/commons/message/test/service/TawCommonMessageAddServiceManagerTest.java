
package com.boco.eoms.commons.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.message.dao.TawCommonMessageAddServiceDao;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;
import com.boco.eoms.commons.message.service.impl.TawCommonMessageAddServiceManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageAddServiceManagerTest extends BaseManagerTestCase {
    private final String tawCommonMessageAddServiceId = "1";
    private TawCommonMessageAddServiceManagerImpl tawCommonMessageAddServiceManager = new TawCommonMessageAddServiceManagerImpl();
    private Mock tawCommonMessageAddServiceDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawCommonMessageAddServiceDao = new Mock(TawCommonMessageAddServiceDao.class);
        tawCommonMessageAddServiceManager.setTawCommonMessageAddServiceDao((TawCommonMessageAddServiceDao) tawCommonMessageAddServiceDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawCommonMessageAddServiceManager = null;
    }

    public void testGetTawCommonMessageAddServices() throws Exception {
        List results = new ArrayList();
        TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();
        results.add(tawCommonMessageAddService);

        // set expected behavior on dao
        tawCommonMessageAddServiceDao.expects(once()).method("getTawCommonMessageAddServices")
            .will(returnValue(results));

        List tawCommonMessageAddServices = tawCommonMessageAddServiceManager.getTawCommonMessageAddServices(null);
        assertTrue(tawCommonMessageAddServices.size() == 1);
        tawCommonMessageAddServiceDao.verify();
    }

    public void testGetTawCommonMessageAddService() throws Exception {
        // set expected behavior on dao
        tawCommonMessageAddServiceDao.expects(once()).method("getTawCommonMessageAddService")
            .will(returnValue(new TawCommonMessageAddService()));
        TawCommonMessageAddService tawCommonMessageAddService = tawCommonMessageAddServiceManager.getTawCommonMessageAddService(tawCommonMessageAddServiceId);
        assertTrue(tawCommonMessageAddService != null);
        tawCommonMessageAddServiceDao.verify();
    }

    public void testSaveTawCommonMessageAddService() throws Exception {
        TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();

        // set expected behavior on dao
        tawCommonMessageAddServiceDao.expects(once()).method("saveTawCommonMessageAddService")
            .with(same(tawCommonMessageAddService)).isVoid();

        tawCommonMessageAddServiceManager.saveTawCommonMessageAddService(tawCommonMessageAddService);
        tawCommonMessageAddServiceDao.verify();
    }

    public void testAddAndRemoveTawCommonMessageAddService() throws Exception {
        TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();

        // set required fields

        // set expected behavior on dao
        tawCommonMessageAddServiceDao.expects(once()).method("saveTawCommonMessageAddService")
            .with(same(tawCommonMessageAddService)).isVoid();
        tawCommonMessageAddServiceManager.saveTawCommonMessageAddService(tawCommonMessageAddService);
        tawCommonMessageAddServiceDao.verify();

        // reset expectations
        tawCommonMessageAddServiceDao.reset();

        tawCommonMessageAddServiceDao.expects(once()).method("removeTawCommonMessageAddService").with(eq(new String(tawCommonMessageAddServiceId)));
        tawCommonMessageAddServiceManager.removeTawCommonMessageAddService(tawCommonMessageAddServiceId);
        tawCommonMessageAddServiceDao.verify();

        // reset expectations
        tawCommonMessageAddServiceDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawCommonMessageAddService.class, tawCommonMessageAddService.getId());
        tawCommonMessageAddServiceDao.expects(once()).method("removeTawCommonMessageAddService").isVoid();
        tawCommonMessageAddServiceDao.expects(once()).method("getTawCommonMessageAddService").will(throwException(ex));
        tawCommonMessageAddServiceManager.removeTawCommonMessageAddService(tawCommonMessageAddServiceId);
        try {
            tawCommonMessageAddServiceManager.getTawCommonMessageAddService(tawCommonMessageAddServiceId);
            fail("TawCommonMessageAddService with identifier '" + tawCommonMessageAddServiceId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawCommonMessageAddServiceDao.verify();
    }
}
