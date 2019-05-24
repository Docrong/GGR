package com.boco.eoms.commons.system.cptroom.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.dao.TawSystemCptroomDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemCptroomDaoTest extends BaseDaoTestCase {
    private Integer tawSystemCptroomId = new Integer("1");
    private TawSystemCptroomDao dao = null;

    public void setTawSystemCptroomDao(TawSystemCptroomDao dao) {
        this.dao = dao;
    }

    public void testAddTawSystemCptroom() throws Exception {
        TawSystemCptroom tawSystemCptroom = new TawSystemCptroom();

        // set required fields

        dao.saveTawSystemCptroom(tawSystemCptroom);

        // verify a primary key was assigned
        assertNotNull(tawSystemCptroom.getId());

        // verify set fields are same after save
    }

    public void testGetTawSystemCptroom() throws Exception {
        TawSystemCptroom tawSystemCptroom = dao.getTawSystemCptroom(tawSystemCptroomId);
        assertNotNull(tawSystemCptroom);
    }

    public void testGetTawSystemCptrooms() throws Exception {
        TawSystemCptroom tawSystemCptroom = new TawSystemCptroom();

        List results = dao.getTawSystemCptrooms(tawSystemCptroom);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSystemCptroom() throws Exception {
        TawSystemCptroom tawSystemCptroom = dao.getTawSystemCptroom(tawSystemCptroomId);

        // update required fields

        dao.saveTawSystemCptroom(tawSystemCptroom);

    }

    public void testRemoveTawSystemCptroom() throws Exception {
        Integer removeId = new Integer("3");
        dao.removeTawSystemCptroom(removeId);
        try {
            dao.getTawSystemCptroom(removeId);
            fail("tawSystemCptroom found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
