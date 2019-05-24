package com.boco.eoms.parter.baseinfo.mainmetermgr.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;
import com.boco.eoms.parter.baseinfo.mainmetermgr.dao.IMainmetermgrDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class MainmetermgrDaoTest extends BaseDaoTestCase {
    private String mainmetermgrId = new String("1");
    private IMainmetermgrDao dao = null;

    public void setMainmetermgrDao(IMainmetermgrDao dao) {
        this.dao = dao;
    }

    public void testAddMainmetermgr() throws Exception {
        Mainmetermgr mainmetermgr = new Mainmetermgr();

        // set required fields

        dao.saveMainmetermgr(mainmetermgr);

        // verify a primary key was assigned
        assertNotNull(mainmetermgr.getId());

        // verify set fields are same after save
    }

    public void testGetMainmetermgr() throws Exception {
        Mainmetermgr mainmetermgr = dao.getMainmetermgr(mainmetermgrId);
        assertNotNull(mainmetermgr);
    }

    public void testGetMainmetermgrs() throws Exception {
        Mainmetermgr mainmetermgr = new Mainmetermgr();

        List results = dao.getMainmetermgrs(mainmetermgr);
        assertTrue(results.size() > 0);
    }

    public void testSaveMainmetermgr() throws Exception {
        Mainmetermgr mainmetermgr = dao.getMainmetermgr(mainmetermgrId);

        // update required fields

        dao.saveMainmetermgr(mainmetermgr);

    }

    public void testRemoveMainmetermgr() throws Exception {
        String removeId = new String("3");
        dao.removeMainmetermgr(removeId);
        try {
            dao.getMainmetermgr(removeId);
            fail("mainmetermgr found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
