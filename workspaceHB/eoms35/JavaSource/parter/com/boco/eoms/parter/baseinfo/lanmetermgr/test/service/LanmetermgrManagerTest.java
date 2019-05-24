
package com.boco.eoms.parter.baseinfo.lanmetermgr.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.parter.baseinfo.lanmetermgr.dao.ILanmetermgrDao;
import com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr;
import com.boco.eoms.parter.baseinfo.lanmetermgr.service.impl.LanmetermgrManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class LanmetermgrManagerTest extends BaseManagerTestCase {
    private final String lanmetermgrId = "1";
    private LanmetermgrManagerImpl lanmetermgrManager = new LanmetermgrManagerImpl();
    private Mock lanmetermgrDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        lanmetermgrDao = new Mock(ILanmetermgrDao.class);
        lanmetermgrManager.setLanmetermgrDao((ILanmetermgrDao) lanmetermgrDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        lanmetermgrManager = null;
    }

    public void testGetLanmetermgrs() throws Exception {
        List results = new ArrayList();
        Lanmetermgr lanmetermgr = new Lanmetermgr();
        results.add(lanmetermgr);

        // set expected behavior on dao
        lanmetermgrDao.expects(once()).method("getLanmetermgrs")
            .will(returnValue(results));

        List lanmetermgrs = lanmetermgrManager.getLanmetermgrs(null);
        assertTrue(lanmetermgrs.size() == 1);
        lanmetermgrDao.verify();
    }

    public void testGetLanmetermgr() throws Exception {
        // set expected behavior on dao
        lanmetermgrDao.expects(once()).method("getLanmetermgr")
            .will(returnValue(new Lanmetermgr()));
        Lanmetermgr lanmetermgr = lanmetermgrManager.getLanmetermgr(lanmetermgrId);
        assertTrue(lanmetermgr != null);
        lanmetermgrDao.verify();
    }

    public void testSaveLanmetermgr() throws Exception {
        Lanmetermgr lanmetermgr = new Lanmetermgr();

        // set expected behavior on dao
        lanmetermgrDao.expects(once()).method("saveLanmetermgr")
            .with(same(lanmetermgr)).isVoid();

        lanmetermgrManager.saveLanmetermgr(lanmetermgr);
        lanmetermgrDao.verify();
    }

    public void testAddAndRemoveLanmetermgr() throws Exception {
        Lanmetermgr lanmetermgr = new Lanmetermgr();

        // set required fields

        // set expected behavior on dao
        lanmetermgrDao.expects(once()).method("saveLanmetermgr")
            .with(same(lanmetermgr)).isVoid();
        lanmetermgrManager.saveLanmetermgr(lanmetermgr);
        lanmetermgrDao.verify();

        // reset expectations
        lanmetermgrDao.reset();

        lanmetermgrDao.expects(once()).method("removeLanmetermgr").with(eq(new String(lanmetermgrId)));
        lanmetermgrManager.removeLanmetermgr(lanmetermgrId);
        lanmetermgrDao.verify();

        // reset expectations
        lanmetermgrDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(Lanmetermgr.class, lanmetermgr.getId());
        lanmetermgrDao.expects(once()).method("removeLanmetermgr").isVoid();
        lanmetermgrDao.expects(once()).method("getLanmetermgr").will(throwException(ex));
        lanmetermgrManager.removeLanmetermgr(lanmetermgrId);
        try {
            lanmetermgrManager.getLanmetermgr(lanmetermgrId);
            fail("Lanmetermgr with identifier '" + lanmetermgrId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        lanmetermgrDao.verify();
    }
}
