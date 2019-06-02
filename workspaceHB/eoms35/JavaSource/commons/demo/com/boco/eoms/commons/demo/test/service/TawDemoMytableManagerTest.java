
package com.boco.eoms.commons.demo.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.demo.dao.TawDemoMytableDao;
import com.boco.eoms.commons.demo.model.TawDemoMytable;
import com.boco.eoms.commons.demo.service.impl.TawDemoMytableManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;


public class TawDemoMytableManagerTest extends BaseManagerTestCase {
    private final String tawDemoMytableId = "1";
    private TawDemoMytableManagerImpl tawDemoMytableManager = new TawDemoMytableManagerImpl();
    private Mock tawDemoMytableDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawDemoMytableDao = new Mock(TawDemoMytableDao.class);
        tawDemoMytableManager.setTawDemoMytableDao((TawDemoMytableDao) tawDemoMytableDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawDemoMytableManager = null;
    }

    public void testGetTawDemoMytables() throws Exception {
        List results = new ArrayList();
        TawDemoMytable tawDemoMytable = new TawDemoMytable();
        results.add(tawDemoMytable);

        // set expected behavior on dao
        tawDemoMytableDao.expects(once()).method("getTawDemoMytables")
            .will(returnValue(results));

        List tawDemoMytables = tawDemoMytableManager.getTawDemoMytables(null);
        assertTrue(tawDemoMytables.size() == 1);
        tawDemoMytableDao.verify();
    }

    public void testGetTawDemoMytable() throws Exception {
        // set expected behavior on dao
        tawDemoMytableDao.expects(once()).method("getTawDemoMytable")
            .will(returnValue(new TawDemoMytable()));
        TawDemoMytable tawDemoMytable = tawDemoMytableManager.getTawDemoMytable(tawDemoMytableId);
        assertTrue(tawDemoMytable != null);
        tawDemoMytableDao.verify();
    }

    public void testSaveTawDemoMytable() throws Exception {
        TawDemoMytable tawDemoMytable = new TawDemoMytable();

        // set expected behavior on dao
        tawDemoMytableDao.expects(once()).method("saveTawDemoMytable")
            .with(same(tawDemoMytable)).isVoid();

        tawDemoMytableManager.saveTawDemoMytable(tawDemoMytable);
        tawDemoMytableDao.verify();
    }

    public void testAddAndRemoveTawDemoMytable() throws Exception {
        TawDemoMytable tawDemoMytable = new TawDemoMytable();

        // set required fields
        tawDemoMytable.setRemark("WfKnEdYmJkPaVcClNiBuIlGjErVkKjDaTjPhMdZeNsGjAuHbUo");
        tawDemoMytable.setTableName("VlIvCoSlLjVqVgPxVvYgZaBxDcGgSsYxAcZxRnCkFgVmZvWdRi");

        // set expected behavior on dao
        tawDemoMytableDao.expects(once()).method("saveTawDemoMytable")
            .with(same(tawDemoMytable)).isVoid();
        tawDemoMytableManager.saveTawDemoMytable(tawDemoMytable);
        tawDemoMytableDao.verify();

        // reset expectations
        tawDemoMytableDao.reset();

        tawDemoMytableDao.expects(once()).method("removeTawDemoMytable").with(eq(new Integer(tawDemoMytableId)));
        tawDemoMytableManager.removeTawDemoMytable(tawDemoMytableId);
        tawDemoMytableDao.verify();

        // reset expectations
        tawDemoMytableDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawDemoMytable.class, tawDemoMytable.getId());
        tawDemoMytableDao.expects(once()).method("removeTawDemoMytable").isVoid();
        tawDemoMytableDao.expects(once()).method("getTawDemoMytable").will(throwException(ex));
        tawDemoMytableManager.removeTawDemoMytable(tawDemoMytableId);
        try {
            tawDemoMytableManager.getTawDemoMytable(tawDemoMytableId);
            fail("TawDemoMytable with identifier '" + tawDemoMytableId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawDemoMytableDao.verify();
    }
}
