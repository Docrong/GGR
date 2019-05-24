
package com.boco.eoms.parter.baseinfo.mainmetermgr.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.parter.baseinfo.mainmetermgr.dao.IMainmetermgrDao;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;
import com.boco.eoms.parter.baseinfo.mainmetermgr.service.impl.MainmetermgrManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class MainmetermgrManagerTest extends BaseManagerTestCase {
    private final String mainmetermgrId = "1";
    private MainmetermgrManagerImpl mainmetermgrManager = new MainmetermgrManagerImpl();
    private Mock mainmetermgrDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        mainmetermgrDao = new Mock(IMainmetermgrDao.class);
        mainmetermgrManager.setMainmetermgrDao((IMainmetermgrDao) mainmetermgrDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        mainmetermgrManager = null;
    }

    public void testGetMainmetermgrs() throws Exception {
        List results = new ArrayList();
        Mainmetermgr mainmetermgr = new Mainmetermgr();
        results.add(mainmetermgr);

        // set expected behavior on dao
        mainmetermgrDao.expects(once()).method("getMainmetermgrs")
            .will(returnValue(results));

        List mainmetermgrs = mainmetermgrManager.getMainmetermgrs(null);
        assertTrue(mainmetermgrs.size() == 1);
        mainmetermgrDao.verify();
    }

    public void testGetMainmetermgr() throws Exception {
        // set expected behavior on dao
        mainmetermgrDao.expects(once()).method("getMainmetermgr")
            .will(returnValue(new Mainmetermgr()));
        Mainmetermgr mainmetermgr = mainmetermgrManager.getMainmetermgr(mainmetermgrId);
        assertTrue(mainmetermgr != null);
        mainmetermgrDao.verify();
    }

    public void testSaveMainmetermgr() throws Exception {
        Mainmetermgr mainmetermgr = new Mainmetermgr();

        // set expected behavior on dao
        mainmetermgrDao.expects(once()).method("saveMainmetermgr")
            .with(same(mainmetermgr)).isVoid();

        mainmetermgrManager.saveMainmetermgr(mainmetermgr);
        mainmetermgrDao.verify();
    }

    public void testAddAndRemoveMainmetermgr() throws Exception {
        Mainmetermgr mainmetermgr = new Mainmetermgr();

        // set required fields

        // set expected behavior on dao
        mainmetermgrDao.expects(once()).method("saveMainmetermgr")
            .with(same(mainmetermgr)).isVoid();
        mainmetermgrManager.saveMainmetermgr(mainmetermgr);
        mainmetermgrDao.verify();

        // reset expectations
        mainmetermgrDao.reset();

        mainmetermgrDao.expects(once()).method("removeMainmetermgr").with(eq(new String(mainmetermgrId)));
        mainmetermgrManager.removeMainmetermgr(mainmetermgrId);
        mainmetermgrDao.verify();

        // reset expectations
        mainmetermgrDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(Mainmetermgr.class, mainmetermgr.getId());
        mainmetermgrDao.expects(once()).method("removeMainmetermgr").isVoid();
        mainmetermgrDao.expects(once()).method("getMainmetermgr").will(throwException(ex));
        mainmetermgrManager.removeMainmetermgr(mainmetermgrId);
        try {
            mainmetermgrManager.getMainmetermgr(mainmetermgrId);
            fail("Mainmetermgr with identifier '" + mainmetermgrId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        mainmetermgrDao.verify();
    }
}
