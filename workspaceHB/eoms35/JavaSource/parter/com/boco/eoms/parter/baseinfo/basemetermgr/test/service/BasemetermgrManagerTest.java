
package com.boco.eoms.parter.baseinfo.basemetermgr.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.parter.baseinfo.basemetermgr.dao.IBasemetermgrDao;
import com.boco.eoms.parter.baseinfo.basemetermgr.model.Basemetermgr;
import com.boco.eoms.parter.baseinfo.basemetermgr.service.impl.BasemetermgrManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class BasemetermgrManagerTest extends BaseManagerTestCase {
    private final String basemetermgrId = "1";
    private BasemetermgrManagerImpl basemetermgrManager = new BasemetermgrManagerImpl();
    private Mock basemetermgrDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        basemetermgrDao = new Mock(IBasemetermgrDao.class);
        basemetermgrManager.setBasemetermgrDao((IBasemetermgrDao) basemetermgrDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        basemetermgrManager = null;
    }

    public void testGetBasemetermgrs() throws Exception {
        List results = new ArrayList();
        Basemetermgr basemetermgr = new Basemetermgr();
        results.add(basemetermgr);

        // set expected behavior on dao
        basemetermgrDao.expects(once()).method("getBasemetermgrs")
            .will(returnValue(results));

        List basemetermgrs = basemetermgrManager.getBasemetermgrs(null);
        assertTrue(basemetermgrs.size() == 1);
        basemetermgrDao.verify();
    }

    public void testGetBasemetermgr() throws Exception {
        // set expected behavior on dao
        basemetermgrDao.expects(once()).method("getBasemetermgr")
            .will(returnValue(new Basemetermgr()));
        Basemetermgr basemetermgr = basemetermgrManager.getBasemetermgr(basemetermgrId);
        assertTrue(basemetermgr != null);
        basemetermgrDao.verify();
    }

    public void testSaveBasemetermgr() throws Exception {
        Basemetermgr basemetermgr = new Basemetermgr();

        // set expected behavior on dao
        basemetermgrDao.expects(once()).method("saveBasemetermgr")
            .with(same(basemetermgr)).isVoid();

        basemetermgrManager.saveBasemetermgr(basemetermgr);
        basemetermgrDao.verify();
    }

    public void testAddAndRemoveBasemetermgr() throws Exception {
        Basemetermgr basemetermgr = new Basemetermgr();

        // set required fields

        // set expected behavior on dao
        basemetermgrDao.expects(once()).method("saveBasemetermgr")
            .with(same(basemetermgr)).isVoid();
        basemetermgrManager.saveBasemetermgr(basemetermgr);
        basemetermgrDao.verify();

        // reset expectations
        basemetermgrDao.reset();

        basemetermgrDao.expects(once()).method("removeBasemetermgr").with(eq(new String(basemetermgrId)));
        basemetermgrManager.removeBasemetermgr(basemetermgrId);
        basemetermgrDao.verify();

        // reset expectations
        basemetermgrDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(Basemetermgr.class, basemetermgr.getId());
        basemetermgrDao.expects(once()).method("removeBasemetermgr").isVoid();
        basemetermgrDao.expects(once()).method("getBasemetermgr").will(throwException(ex));
        basemetermgrManager.removeBasemetermgr(basemetermgrId);
        try {
            basemetermgrManager.getBasemetermgr(basemetermgrId);
            fail("Basemetermgr with identifier '" + basemetermgrId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        basemetermgrDao.verify();
    }
}
