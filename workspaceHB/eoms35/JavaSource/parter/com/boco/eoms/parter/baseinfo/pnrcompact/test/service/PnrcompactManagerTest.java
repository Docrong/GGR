
package com.boco.eoms.parter.baseinfo.pnrcompact.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.parter.baseinfo.pnrcompact.dao.IPnrcompactDao;
import com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact;
import com.boco.eoms.parter.baseinfo.pnrcompact.service.impl.PnrcompactManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PnrcompactManagerTest extends BaseManagerTestCase {
    private final String pnrcompactId = "1";
    private PnrcompactManagerImpl pnrcompactManager = new PnrcompactManagerImpl();
    private Mock pnrcompactDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        pnrcompactDao = new Mock(IPnrcompactDao.class);
        pnrcompactManager.setPnrcompactDao((IPnrcompactDao) pnrcompactDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        pnrcompactManager = null;
    }

    public void testGetPnrcompacts() throws Exception {
        List results = new ArrayList();
        Pnrcompact pnrcompact = new Pnrcompact();
        results.add(pnrcompact);

        // set expected behavior on dao
        pnrcompactDao.expects(once()).method("getPnrcompacts")
            .will(returnValue(results));

        List pnrcompacts = pnrcompactManager.getPnrcompacts(null);
        assertTrue(pnrcompacts.size() == 1);
        pnrcompactDao.verify();
    }

    public void testGetPnrcompact() throws Exception {
        // set expected behavior on dao
        pnrcompactDao.expects(once()).method("getPnrcompact")
            .will(returnValue(new Pnrcompact()));
        Pnrcompact pnrcompact = pnrcompactManager.getPnrcompact(pnrcompactId);
        assertTrue(pnrcompact != null);
        pnrcompactDao.verify();
    }

    public void testSavePnrcompact() throws Exception {
        Pnrcompact pnrcompact = new Pnrcompact();

        // set expected behavior on dao
        pnrcompactDao.expects(once()).method("savePnrcompact")
            .with(same(pnrcompact)).isVoid();

        pnrcompactManager.savePnrcompact(pnrcompact);
        pnrcompactDao.verify();
    }

    public void testAddAndRemovePnrcompact() throws Exception {
        Pnrcompact pnrcompact = new Pnrcompact();

        // set required fields

        // set expected behavior on dao
        pnrcompactDao.expects(once()).method("savePnrcompact")
            .with(same(pnrcompact)).isVoid();
        pnrcompactManager.savePnrcompact(pnrcompact);
        pnrcompactDao.verify();

        // reset expectations
        pnrcompactDao.reset();

        pnrcompactDao.expects(once()).method("removePnrcompact").with(eq(new String(pnrcompactId)));
        pnrcompactManager.removePnrcompact(pnrcompactId);
        pnrcompactDao.verify();

        // reset expectations
        pnrcompactDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(Pnrcompact.class, pnrcompact.getId());
        pnrcompactDao.expects(once()).method("removePnrcompact").isVoid();
        pnrcompactDao.expects(once()).method("getPnrcompact").will(throwException(ex));
        pnrcompactManager.removePnrcompact(pnrcompactId);
        try {
            pnrcompactManager.getPnrcompact(pnrcompactId);
            fail("Pnrcompact with identifier '" + pnrcompactId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        pnrcompactDao.verify();
    }
}
