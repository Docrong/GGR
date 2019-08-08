
package com.boco.eoms.bureaudata.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.bureaudata.dao.ITawBureauDataDao;
import com.boco.eoms.bureaudata.model.TawBureauData;
import com.boco.eoms.bureaudata.service.impl.TawBureauDataManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawBureauDataManagerTest extends BaseManagerTestCase {
    private final String tawBureauDataId = "1";
    private TawBureauDataManagerImpl tawBureauDataManager = new TawBureauDataManagerImpl();
    private Mock tawBureauDataDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawBureauDataDao = new Mock(ITawBureauDataDao.class);
        tawBureauDataManager.setTawBureauDataDao((ITawBureauDataDao) tawBureauDataDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawBureauDataManager = null;
    }

    public void testGetTawBureauDatas() throws Exception {
        List results = new ArrayList();
        TawBureauData tawBureauData = new TawBureauData();
        results.add(tawBureauData);

        // set expected behavior on dao
        tawBureauDataDao.expects(once()).method("getTawBureauDatas")
                .will(returnValue(results));

        List tawBureauDatas = tawBureauDataManager.getTawBureauDatas(null);
        assertTrue(tawBureauDatas.size() == 1);
        tawBureauDataDao.verify();
    }

    public void testGetTawBureauData() throws Exception {
        // set expected behavior on dao
        tawBureauDataDao.expects(once()).method("getTawBureauData")
                .will(returnValue(new TawBureauData()));
        TawBureauData tawBureauData = tawBureauDataManager.getTawBureauData(tawBureauDataId);
        assertTrue(tawBureauData != null);
        tawBureauDataDao.verify();
    }

    public void testSaveTawBureauData() throws Exception {
        TawBureauData tawBureauData = new TawBureauData();

        // set expected behavior on dao
        tawBureauDataDao.expects(once()).method("saveTawBureauData")
                .with(same(tawBureauData)).isVoid();

        tawBureauDataManager.saveTawBureauData(tawBureauData);
        tawBureauDataDao.verify();
    }

    public void testAddAndRemoveTawBureauData() throws Exception {
        TawBureauData tawBureauData = new TawBureauData();

        // set required fields

        // set expected behavior on dao
        tawBureauDataDao.expects(once()).method("saveTawBureauData")
                .with(same(tawBureauData)).isVoid();
        tawBureauDataManager.saveTawBureauData(tawBureauData);
        tawBureauDataDao.verify();

        // reset expectations
        tawBureauDataDao.reset();

        tawBureauDataDao.expects(once()).method("removeTawBureauData").with(eq(new String(tawBureauDataId)));
        tawBureauDataManager.removeTawBureauData(tawBureauDataId);
        tawBureauDataDao.verify();

        // reset expectations
        tawBureauDataDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawBureauData.class, tawBureauData.getId());
        tawBureauDataDao.expects(once()).method("removeTawBureauData").isVoid();
        tawBureauDataDao.expects(once()).method("getTawBureauData").will(throwException(ex));
        tawBureauDataManager.removeTawBureauData(tawBureauDataId);
        try {
            tawBureauDataManager.getTawBureauData(tawBureauDataId);
            fail("TawBureauData with identifier '" + tawBureauDataId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawBureauDataDao.verify();
    }
}
