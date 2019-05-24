
package com.boco.eoms.extra.supplierkpi.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInfoDao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.service.impl.TawSupplierkpiInfoManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSupplierkpiInfoManagerTest extends BaseManagerTestCase {
    private final String tawSupplierkpiInfoId = "1";
    private TawSupplierkpiInfoManagerImpl tawSupplierkpiInfoManager = new TawSupplierkpiInfoManagerImpl();
    private Mock tawSupplierkpiInfoDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSupplierkpiInfoDao = new Mock(TawSupplierkpiInfoDao.class);
        tawSupplierkpiInfoManager.setTawSupplierkpiInfoDao((TawSupplierkpiInfoDao) tawSupplierkpiInfoDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSupplierkpiInfoManager = null;
    }

    public void testGetTawSupplierkpiInfos() throws Exception {
        List results = new ArrayList();
        TawSupplierkpiInfo tawSupplierkpiInfo = new TawSupplierkpiInfo();
        results.add(tawSupplierkpiInfo);

        // set expected behavior on dao
        tawSupplierkpiInfoDao.expects(once()).method("getTawSupplierkpiInfos")
            .will(returnValue(results));

        List tawSupplierkpiInfos = tawSupplierkpiInfoManager.getTawSupplierkpiInfos(null);
        assertTrue(tawSupplierkpiInfos.size() == 1);
        tawSupplierkpiInfoDao.verify();
    }

    public void testGetTawSupplierkpiInfo() throws Exception {
        // set expected behavior on dao
        tawSupplierkpiInfoDao.expects(once()).method("getTawSupplierkpiInfo")
            .will(returnValue(new TawSupplierkpiInfo()));
        TawSupplierkpiInfo tawSupplierkpiInfo = tawSupplierkpiInfoManager.getTawSupplierkpiInfo(tawSupplierkpiInfoId);
        assertTrue(tawSupplierkpiInfo != null);
        tawSupplierkpiInfoDao.verify();
    }

    public void testSaveTawSupplierkpiInfo() throws Exception {
        TawSupplierkpiInfo tawSupplierkpiInfo = new TawSupplierkpiInfo();

        // set expected behavior on dao
        tawSupplierkpiInfoDao.expects(once()).method("saveTawSupplierkpiInfo")
            .with(same(tawSupplierkpiInfo)).isVoid();

        tawSupplierkpiInfoManager.saveTawSupplierkpiInfo(tawSupplierkpiInfo);
        tawSupplierkpiInfoDao.verify();
    }

    public void testAddAndRemoveTawSupplierkpiInfo() throws Exception {
        TawSupplierkpiInfo tawSupplierkpiInfo = new TawSupplierkpiInfo();

        // set required fields

        // set expected behavior on dao
        tawSupplierkpiInfoDao.expects(once()).method("saveTawSupplierkpiInfo")
            .with(same(tawSupplierkpiInfo)).isVoid();
        tawSupplierkpiInfoManager.saveTawSupplierkpiInfo(tawSupplierkpiInfo);
        tawSupplierkpiInfoDao.verify();

        // reset expectations
        tawSupplierkpiInfoDao.reset();

        tawSupplierkpiInfoDao.expects(once()).method("removeTawSupplierkpiInfo").with(eq(new String(tawSupplierkpiInfoId)));
        tawSupplierkpiInfoManager.removeTawSupplierkpiInfo(tawSupplierkpiInfoId);
        tawSupplierkpiInfoDao.verify();

        // reset expectations
        tawSupplierkpiInfoDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSupplierkpiInfo.class, tawSupplierkpiInfo.getId());
        tawSupplierkpiInfoDao.expects(once()).method("removeTawSupplierkpiInfo").isVoid();
        tawSupplierkpiInfoDao.expects(once()).method("getTawSupplierkpiInfo").will(throwException(ex));
        tawSupplierkpiInfoManager.removeTawSupplierkpiInfo(tawSupplierkpiInfoId);
        try {
            tawSupplierkpiInfoManager.getTawSupplierkpiInfo(tawSupplierkpiInfoId);
            fail("TawSupplierkpiInfo with identifier '" + tawSupplierkpiInfoId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSupplierkpiInfoDao.verify();
    }
}
