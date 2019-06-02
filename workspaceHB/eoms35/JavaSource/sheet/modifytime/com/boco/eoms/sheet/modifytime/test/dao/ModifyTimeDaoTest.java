package com.boco.eoms.sheet.modifytime.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.sheet.modifytime.model.ModifyTime;
import com.boco.eoms.sheet.modifytime.dao.IModifyTimeDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class ModifyTimeDaoTest extends BaseDaoTestCase {
    private String modifytimeId = new String("1");
    private IModifyTimeDao dao = null;

    public void setModifyTimeDao(IModifyTimeDao dao) {
        this.dao = dao;
    }

    public void testAddModifyTime() throws Exception {
        ModifyTime modifytime = new ModifyTime();

        // set required fields

        dao.saveModifyTime(modifytime);

        // verify a primary key was assigned
        assertNotNull(modifytime.getId());

        // verify set fields are same after save
    }

    public void testGetModifyTime() throws Exception {
        ModifyTime modifytime = dao.getModifyTime(modifytimeId);
        assertNotNull(modifytime);
    }

    public void testGetModifyTimes() throws Exception {
        ModifyTime modifytime = new ModifyTime();

        List results = dao.getModifyTimes();
        assertTrue(results.size() > 0);
    }

    public void testSaveModifyTime() throws Exception {
        ModifyTime modifytime = dao.getModifyTime(modifytimeId);

        // update required fields

        dao.saveModifyTime(modifytime);

    }

    public void testRemoveModifyTime() throws Exception {
        String removeId = new String("3");
        dao.removeModifyTime(removeId);
        try {
            dao.getModifyTime(removeId);
            fail("modifytime found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
