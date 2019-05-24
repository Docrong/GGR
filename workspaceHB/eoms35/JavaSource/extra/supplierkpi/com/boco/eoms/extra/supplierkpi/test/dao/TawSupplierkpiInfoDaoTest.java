package com.boco.eoms.extra.supplierkpi.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInfoDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSupplierkpiInfoDaoTest extends BaseDaoTestCase {
    private String tawSupplierkpiInfoId = new String("1");
    private TawSupplierkpiInfoDao dao = null;

    public void setTawSupplierkpiInfoDao(TawSupplierkpiInfoDao dao) {
        this.dao = dao;
    }

    public void testAddTawSupplierkpiInfo() throws Exception {
        TawSupplierkpiInfo tawSupplierkpiInfo = new TawSupplierkpiInfo();

        // set required fields

        dao.saveTawSupplierkpiInfo(tawSupplierkpiInfo);

        // verify a primary key was assigned
        assertNotNull(tawSupplierkpiInfo.getId());

        // verify set fields are same after save
    }

    public void testGetTawSupplierkpiInfo() throws Exception {
        TawSupplierkpiInfo tawSupplierkpiInfo = dao.getTawSupplierkpiInfo(tawSupplierkpiInfoId);
        assertNotNull(tawSupplierkpiInfo);
    }

    public void testGetTawSupplierkpiInfos() throws Exception {
        TawSupplierkpiInfo tawSupplierkpiInfo = new TawSupplierkpiInfo();

        List results = dao.getTawSupplierkpiInfos(tawSupplierkpiInfo);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSupplierkpiInfo() throws Exception {
        TawSupplierkpiInfo tawSupplierkpiInfo = dao.getTawSupplierkpiInfo(tawSupplierkpiInfoId);

        // update required fields

        dao.saveTawSupplierkpiInfo(tawSupplierkpiInfo);

    }

    public void testRemoveTawSupplierkpiInfo() throws Exception {
        String removeId = new String("3");
        dao.removeTawSupplierkpiInfo(removeId);
        try {
            dao.getTawSupplierkpiInfo(removeId);
            fail("tawSupplierkpiInfo found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
