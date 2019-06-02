
package com.boco.eoms.commons.system.area.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.area.dao.TawSystemAreaDao;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.impl.TawSystemAreaManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemAreaManagerTest extends BaseManagerTestCase {
    private final String tawSystemAreaId = "1";
    private TawSystemAreaManagerImpl tawSystemAreaManager = new TawSystemAreaManagerImpl();
    private Mock tawSystemAreaDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSystemAreaDao = new Mock(TawSystemAreaDao.class);
        tawSystemAreaManager.setTawSystemAreaDao((TawSystemAreaDao) tawSystemAreaDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSystemAreaManager = null;
    }

    public void testGetTawSystemAreas() throws Exception {
        List results = new ArrayList();
        TawSystemArea tawSystemArea = new TawSystemArea();
        results.add(tawSystemArea);

        // set expected behavior on dao
        tawSystemAreaDao.expects(once()).method("getTawSystemAreas")
            .will(returnValue(results));

        List tawSystemAreas = tawSystemAreaManager.getTawSystemAreas(null);
        assertTrue(tawSystemAreas.size() == 1);
        tawSystemAreaDao.verify();
    }

    public void testGetTawSystemArea() throws Exception {
        // set expected behavior on dao
        tawSystemAreaDao.expects(once()).method("getTawSystemArea")
            .will(returnValue(new TawSystemArea()));
        TawSystemArea tawSystemArea = tawSystemAreaManager.getTawSystemArea(tawSystemAreaId);
        assertTrue(tawSystemArea != null);
        tawSystemAreaDao.verify();
    }

    public void testSaveTawSystemArea() throws Exception {
        TawSystemArea tawSystemArea = new TawSystemArea();

        // set expected behavior on dao
        tawSystemAreaDao.expects(once()).method("saveTawSystemArea")
            .with(same(tawSystemArea)).isVoid();

        tawSystemAreaManager.saveTawSystemArea(tawSystemArea);
        tawSystemAreaDao.verify();
    }

    public void testAddAndRemoveTawSystemArea() throws Exception {
        TawSystemArea tawSystemArea = new TawSystemArea();

        // set required fields

        // set expected behavior on dao
        tawSystemAreaDao.expects(once()).method("saveTawSystemArea")
            .with(same(tawSystemArea)).isVoid();
        tawSystemAreaManager.saveTawSystemArea(tawSystemArea);
        tawSystemAreaDao.verify();

        // reset expectations
        tawSystemAreaDao.reset();

        tawSystemAreaDao.expects(once()).method("removeTawSystemArea").with(eq(new Integer(tawSystemAreaId)));
        tawSystemAreaManager.removeTawSystemArea(tawSystemAreaId);
        tawSystemAreaDao.verify();

        // reset expectations
        tawSystemAreaDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSystemArea.class, tawSystemArea.getId());
        tawSystemAreaDao.expects(once()).method("removeTawSystemArea").isVoid();
        tawSystemAreaDao.expects(once()).method("getTawSystemArea").will(throwException(ex));
        tawSystemAreaManager.removeTawSystemArea(tawSystemAreaId);
        try {
            tawSystemAreaManager.getTawSystemArea(tawSystemAreaId);
            fail("TawSystemArea with identifier '" + tawSystemAreaId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSystemAreaDao.verify();
    }
}
