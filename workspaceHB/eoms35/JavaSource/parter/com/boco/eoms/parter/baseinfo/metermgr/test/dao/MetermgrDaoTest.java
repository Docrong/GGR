package com.boco.eoms.parter.baseinfo.metermgr.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.parter.baseinfo.metermgr.model.Metermgr;
import com.boco.eoms.parter.baseinfo.metermgr.dao.IMetermgrDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class MetermgrDaoTest extends BaseDaoTestCase {
    private String metermgrId = new String("1");
    private IMetermgrDao dao = null;

    public void setMetermgrDao(IMetermgrDao dao) {
        this.dao = dao;
    }

    public void testAddMetermgr() throws Exception {
        Metermgr metermgr = new Metermgr();

        // set required fields

        dao.saveMetermgr(metermgr);

        // verify a primary key was assigned
        assertNotNull(metermgr.getId());

        // verify set fields are same after save
    }

    public void testGetMetermgr() throws Exception {
        Metermgr metermgr = dao.getMetermgr(metermgrId);
        assertNotNull(metermgr);
    }

    public void testGetMetermgrs() throws Exception {
        Metermgr metermgr = new Metermgr();

        List results = dao.getMetermgrs(metermgr);
        assertTrue(results.size() > 0);
    }

    public void testSaveMetermgr() throws Exception {
        Metermgr metermgr = dao.getMetermgr(metermgrId);

        // update required fields

        dao.saveMetermgr(metermgr);

    }

    public void testRemoveMetermgr() throws Exception {
        String removeId = new String("3");
        dao.removeMetermgr(removeId);
        try {
            dao.getMetermgr(removeId);
            fail("metermgr found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
