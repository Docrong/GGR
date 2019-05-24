
package com.boco.eoms.parter.baseinfo.metermgr.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.parter.baseinfo.metermgr.dao.IMetermgrDao;
import com.boco.eoms.parter.baseinfo.metermgr.model.Metermgr;
import com.boco.eoms.parter.baseinfo.metermgr.service.impl.MetermgrManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class MetermgrManagerTest extends BaseManagerTestCase {
    private final String metermgrId = "1";
    private MetermgrManagerImpl metermgrManager = new MetermgrManagerImpl();
    private Mock metermgrDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        metermgrDao = new Mock(IMetermgrDao.class);
        metermgrManager.setMetermgrDao((IMetermgrDao) metermgrDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        metermgrManager = null;
    }

    public void testGetMetermgrs() throws Exception {
        List results = new ArrayList();
        Metermgr metermgr = new Metermgr();
        results.add(metermgr);

        // set expected behavior on dao
        metermgrDao.expects(once()).method("getMetermgrs")
            .will(returnValue(results));

        List metermgrs = metermgrManager.getMetermgrs(null);
        assertTrue(metermgrs.size() == 1);
        metermgrDao.verify();
    }

    public void testGetMetermgr() throws Exception {
        // set expected behavior on dao
        metermgrDao.expects(once()).method("getMetermgr")
            .will(returnValue(new Metermgr()));
        Metermgr metermgr = metermgrManager.getMetermgr(metermgrId);
        assertTrue(metermgr != null);
        metermgrDao.verify();
    }

    public void testSaveMetermgr() throws Exception {
        Metermgr metermgr = new Metermgr();

        // set expected behavior on dao
        metermgrDao.expects(once()).method("saveMetermgr")
            .with(same(metermgr)).isVoid();

        metermgrManager.saveMetermgr(metermgr);
        metermgrDao.verify();
    }

    public void testAddAndRemoveMetermgr() throws Exception {
        Metermgr metermgr = new Metermgr();

        // set required fields

        // set expected behavior on dao
        metermgrDao.expects(once()).method("saveMetermgr")
            .with(same(metermgr)).isVoid();
        metermgrManager.saveMetermgr(metermgr);
        metermgrDao.verify();

        // reset expectations
        metermgrDao.reset();

        metermgrDao.expects(once()).method("removeMetermgr").with(eq(new String(metermgrId)));
        metermgrManager.removeMetermgr(metermgrId);
        metermgrDao.verify();

        // reset expectations
        metermgrDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(Metermgr.class, metermgr.getId());
        metermgrDao.expects(once()).method("removeMetermgr").isVoid();
        metermgrDao.expects(once()).method("getMetermgr").will(throwException(ex));
        metermgrManager.removeMetermgr(metermgrId);
        try {
            metermgrManager.getMetermgr(metermgrId);
            fail("Metermgr with identifier '" + metermgrId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        metermgrDao.verify();
    }
}
