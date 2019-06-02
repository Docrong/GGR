package com.boco.eoms.parter.baseinfo.pnrcompact.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact;
import com.boco.eoms.parter.baseinfo.pnrcompact.dao.IPnrcompactDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class PnrcompactDaoTest extends BaseDaoTestCase {
    private String pnrcompactId = new String("1");
    private IPnrcompactDao dao = null;

    public void setPnrcompactDao(IPnrcompactDao dao) {
        this.dao = dao;
    }

    public void testAddPnrcompact() throws Exception {
        Pnrcompact pnrcompact = new Pnrcompact();

        // set required fields

        dao.savePnrcompact(pnrcompact);

        // verify a primary key was assigned
        assertNotNull(pnrcompact.getId());

        // verify set fields are same after save
    }

    public void testGetPnrcompact() throws Exception {
        Pnrcompact pnrcompact = dao.getPnrcompact(pnrcompactId);
        assertNotNull(pnrcompact);
    }

    public void testGetPnrcompacts() throws Exception {
        Pnrcompact pnrcompact = new Pnrcompact();

        List results = dao.getPnrcompacts(pnrcompact);
        assertTrue(results.size() > 0);
    }

    public void testSavePnrcompact() throws Exception {
        Pnrcompact pnrcompact = dao.getPnrcompact(pnrcompactId);

        // update required fields

        dao.savePnrcompact(pnrcompact);

    }

    public void testRemovePnrcompact() throws Exception {
        String removeId = new String("3");
        dao.removePnrcompact(removeId);
        try {
            dao.getPnrcompact(removeId);
            fail("pnrcompact found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
