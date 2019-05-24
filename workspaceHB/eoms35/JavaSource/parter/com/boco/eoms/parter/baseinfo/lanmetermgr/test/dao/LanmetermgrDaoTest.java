package com.boco.eoms.parter.baseinfo.lanmetermgr.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr;
import com.boco.eoms.parter.baseinfo.lanmetermgr.dao.ILanmetermgrDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class LanmetermgrDaoTest extends BaseDaoTestCase {
    private String lanmetermgrId = new String("1");
    private ILanmetermgrDao dao = null;

    public void setLanmetermgrDao(ILanmetermgrDao dao) {
        this.dao = dao;
    }

    public void testAddLanmetermgr() throws Exception {
        Lanmetermgr lanmetermgr = new Lanmetermgr();

        // set required fields

        dao.saveLanmetermgr(lanmetermgr);

        // verify a primary key was assigned
        assertNotNull(lanmetermgr.getId());

        // verify set fields are same after save
    }

    public void testGetLanmetermgr() throws Exception {
        Lanmetermgr lanmetermgr = dao.getLanmetermgr(lanmetermgrId);
        assertNotNull(lanmetermgr);
    }

    public void testGetLanmetermgrs() throws Exception {
        Lanmetermgr lanmetermgr = new Lanmetermgr();

        List results = dao.getLanmetermgrs(lanmetermgr);
        assertTrue(results.size() > 0);
    }

    public void testSaveLanmetermgr() throws Exception {
        Lanmetermgr lanmetermgr = dao.getLanmetermgr(lanmetermgrId);

        // update required fields

        dao.saveLanmetermgr(lanmetermgr);

    }

    public void testRemoveLanmetermgr() throws Exception {
        String removeId = new String("3");
        dao.removeLanmetermgr(removeId);
        try {
            dao.getLanmetermgr(removeId);
            fail("lanmetermgr found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
