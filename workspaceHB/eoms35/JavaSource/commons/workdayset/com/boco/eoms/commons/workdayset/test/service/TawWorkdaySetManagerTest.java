
package com.boco.eoms.commons.workdayset.test.service;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.workdayset.dao.ITawWorkdaySetDao;
import com.boco.eoms.commons.workdayset.model.TawWorkdaySet;
import com.boco.eoms.commons.workdayset.service.impl.TawWorkdaySetManagerImpl;

public class TawWorkdaySetManagerTest extends BaseManagerTestCase {
    private final String tawWorkdaySetId = "1";
    private TawWorkdaySetManagerImpl tawWorkdaySetManager = new TawWorkdaySetManagerImpl();
    private Mock tawWorkdaySetDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawWorkdaySetDao = new Mock(ITawWorkdaySetDao.class);
        tawWorkdaySetManager.setTawWorkdaySetDao((ITawWorkdaySetDao) tawWorkdaySetDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawWorkdaySetManager = null;
    }

    public void testGetTawWorkdaySets() throws Exception {
        List results = new ArrayList();
        TawWorkdaySet tawWorkdaySet = new TawWorkdaySet();
        results.add(tawWorkdaySet);

        // set expected behavior on dao
        tawWorkdaySetDao.expects(once()).method("getTawWorkdaySets")
            .will(returnValue(results));

        List tawWorkdaySets = tawWorkdaySetManager.getTawWorkdaySets(null);
        assertTrue(tawWorkdaySets.size() == 1);
        tawWorkdaySetDao.verify();
    }

    public void testGetTawWorkdaySet() throws Exception {
        // set expected behavior on dao
        tawWorkdaySetDao.expects(once()).method("getTawWorkdaySet")
            .will(returnValue(new TawWorkdaySet()));
        TawWorkdaySet tawWorkdaySet = tawWorkdaySetManager.getTawWorkdaySet(tawWorkdaySetId);
        assertTrue(tawWorkdaySet != null);
        tawWorkdaySetDao.verify();
    }

    public void testSaveTawWorkdaySet() throws Exception {
        TawWorkdaySet tawWorkdaySet = new TawWorkdaySet();

        // set expected behavior on dao
        tawWorkdaySetDao.expects(once()).method("saveTawWorkdaySet")
            .with(same(tawWorkdaySet)).isVoid();

        tawWorkdaySetManager.saveTawWorkdaySet(tawWorkdaySet);
        tawWorkdaySetDao.verify();
    }

    public void testAddAndRemoveTawWorkdaySet() throws Exception {
        TawWorkdaySet tawWorkdaySet = new TawWorkdaySet();

        // set required fields
        tawWorkdaySet.setAreaId("TgOgSjFqMbUqHoIpKoGqUyNmJuNnJjJq");
        tawWorkdaySet.setCreateTime((new java.util.Date()).toString());

        // set expected behavior on dao
        tawWorkdaySetDao.expects(once()).method("saveTawWorkdaySet")
            .with(same(tawWorkdaySet)).isVoid();
        tawWorkdaySetManager.saveTawWorkdaySet(tawWorkdaySet);
        tawWorkdaySetDao.verify();

        // reset expectations
        tawWorkdaySetDao.reset();

        tawWorkdaySetDao.expects(once()).method("removeTawWorkdaySet").with(eq(new String(tawWorkdaySetId)));
        tawWorkdaySetManager.removeTawWorkdaySet(tawWorkdaySetId);
        tawWorkdaySetDao.verify();

        // reset expectations
        tawWorkdaySetDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawWorkdaySet.class, tawWorkdaySet.getId());
        tawWorkdaySetDao.expects(once()).method("removeTawWorkdaySet").isVoid();
        tawWorkdaySetDao.expects(once()).method("getTawWorkdaySet").will(throwException(ex));
        tawWorkdaySetManager.removeTawWorkdaySet(tawWorkdaySetId);
        try {
            tawWorkdaySetManager.getTawWorkdaySet(tawWorkdaySetId);
            fail("TawWorkdaySet with identifier '" + tawWorkdaySetId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawWorkdaySetDao.verify();
    }
}
