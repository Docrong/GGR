package com.boco.eoms.parter.baseinfo.basemetermgr.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.parter.baseinfo.basemetermgr.model.Basemetermgr;
import com.boco.eoms.parter.baseinfo.basemetermgr.dao.IBasemetermgrDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class BasemetermgrDaoTest extends BaseDaoTestCase {
    private String basemetermgrId = new String("1");
    private IBasemetermgrDao dao = null;

    public void setBasemetermgrDao(IBasemetermgrDao dao) {
        this.dao = dao;
    }

    public void testAddBasemetermgr() throws Exception {
        Basemetermgr basemetermgr = new Basemetermgr();

        // set required fields

        dao.saveBasemetermgr(basemetermgr);

        // verify a primary key was assigned
        assertNotNull(basemetermgr.getId());

        // verify set fields are same after save
    }

    public void testGetBasemetermgr() throws Exception {
        Basemetermgr basemetermgr = dao.getBasemetermgr(basemetermgrId);
        assertNotNull(basemetermgr);
    }

    public void testGetBasemetermgrs() throws Exception {
        Basemetermgr basemetermgr = new Basemetermgr();

        List results = dao.getBasemetermgrs(basemetermgr);
        assertTrue(results.size() > 0);
    }

    public void testSaveBasemetermgr() throws Exception {
        Basemetermgr basemetermgr = dao.getBasemetermgr(basemetermgrId);

        // update required fields

        dao.saveBasemetermgr(basemetermgr);

    }

    public void testRemoveBasemetermgr() throws Exception {
        String removeId = new String("3");
        dao.removeBasemetermgr(removeId);
        try {
            dao.getBasemetermgr(removeId);
            fail("basemetermgr found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
