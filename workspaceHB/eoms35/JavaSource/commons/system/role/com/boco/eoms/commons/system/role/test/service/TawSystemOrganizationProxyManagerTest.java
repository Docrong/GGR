
package com.boco.eoms.commons.system.role.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.role.dao.TawSystemOrganizationProxyDao;
import com.boco.eoms.commons.system.role.model.TawSystemOrganizationProxy;
import com.boco.eoms.commons.system.role.service.impl.TawSystemOrganizationProxyManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemOrganizationProxyManagerTest extends BaseManagerTestCase {
    private final String tawSystemOrganizationProxyId = "1";
    private TawSystemOrganizationProxyManagerImpl tawSystemOrganizationProxyManager = new TawSystemOrganizationProxyManagerImpl();
    private Mock tawSystemOrganizationProxyDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSystemOrganizationProxyDao = new Mock(TawSystemOrganizationProxyDao.class);
        tawSystemOrganizationProxyManager.setTawSystemOrganizationProxyDao((TawSystemOrganizationProxyDao) tawSystemOrganizationProxyDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSystemOrganizationProxyManager = null;
    }

    public void testGetTawSystemOrganizationProxys() throws Exception {
        List results = new ArrayList();
        TawSystemOrganizationProxy tawSystemOrganizationProxy = new TawSystemOrganizationProxy();
        results.add(tawSystemOrganizationProxy);

        // set expected behavior on dao
        tawSystemOrganizationProxyDao.expects(once()).method("getTawSystemOrganizationProxys")
            .will(returnValue(results));

        List tawSystemOrganizationProxys = tawSystemOrganizationProxyManager.getTawSystemOrganizationProxys(null);
        assertTrue(tawSystemOrganizationProxys.size() == 1);
        tawSystemOrganizationProxyDao.verify();
    }

    public void testGetTawSystemOrganizationProxy() throws Exception {
        // set expected behavior on dao
        tawSystemOrganizationProxyDao.expects(once()).method("getTawSystemOrganizationProxy")
            .will(returnValue(new TawSystemOrganizationProxy()));
        TawSystemOrganizationProxy tawSystemOrganizationProxy = tawSystemOrganizationProxyManager.getTawSystemOrganizationProxy(tawSystemOrganizationProxyId);
        assertTrue(tawSystemOrganizationProxy != null);
        tawSystemOrganizationProxyDao.verify();
    }

    public void testSaveTawSystemOrganizationProxy() throws Exception {
        TawSystemOrganizationProxy tawSystemOrganizationProxy = new TawSystemOrganizationProxy();

        // set expected behavior on dao
        tawSystemOrganizationProxyDao.expects(once()).method("saveTawSystemOrganizationProxy")
            .with(same(tawSystemOrganizationProxy)).isVoid();

        tawSystemOrganizationProxyManager.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);
        tawSystemOrganizationProxyDao.verify();
    }

    public void testAddAndRemoveTawSystemOrganizationProxy() throws Exception {
        TawSystemOrganizationProxy tawSystemOrganizationProxy = new TawSystemOrganizationProxy();

        // set required fields

        // set expected behavior on dao
        tawSystemOrganizationProxyDao.expects(once()).method("saveTawSystemOrganizationProxy")
            .with(same(tawSystemOrganizationProxy)).isVoid();
        tawSystemOrganizationProxyManager.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);
        tawSystemOrganizationProxyDao.verify();

        // reset expectations
        tawSystemOrganizationProxyDao.reset();

        tawSystemOrganizationProxyDao.expects(once()).method("removeTawSystemOrganizationProxy").with(eq(new String(tawSystemOrganizationProxyId)));
        tawSystemOrganizationProxyManager.removeTawSystemOrganizationProxy(tawSystemOrganizationProxyId);
        tawSystemOrganizationProxyDao.verify();

        // reset expectations
        tawSystemOrganizationProxyDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSystemOrganizationProxy.class, tawSystemOrganizationProxy.getId());
        tawSystemOrganizationProxyDao.expects(once()).method("removeTawSystemOrganizationProxy").isVoid();
        tawSystemOrganizationProxyDao.expects(once()).method("getTawSystemOrganizationProxy").will(throwException(ex));
        tawSystemOrganizationProxyManager.removeTawSystemOrganizationProxy(tawSystemOrganizationProxyId);
        try {
            tawSystemOrganizationProxyManager.getTawSystemOrganizationProxy(tawSystemOrganizationProxyId);
            fail("TawSystemOrganizationProxy with identifier '" + tawSystemOrganizationProxyId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSystemOrganizationProxyDao.verify();
    }
}
