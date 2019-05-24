package com.boco.eoms.workbench.contact.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchContactGroupDaoTest extends BaseDaoTestCase {
    private String tawWorkbenchContactGroupId = new String("1");
    private TawWorkbenchContactGroupDao dao = null;

    public void setTawWorkbenchContactGroupDao(TawWorkbenchContactGroupDao dao) {
        this.dao = dao;
    }

    public void testAddTawWorkbenchContactGroup() throws Exception {
        TawWorkbenchContactGroup tawWorkbenchContactGroup = new TawWorkbenchContactGroup();

        // set required fields

        dao.saveTawWorkbenchContactGroup(tawWorkbenchContactGroup);

        // verify a primary key was assigned
        assertNotNull(tawWorkbenchContactGroup.getId());

        // verify set fields are same after save
    }

    public void testGetTawWorkbenchContactGroup() throws Exception {
        TawWorkbenchContactGroup tawWorkbenchContactGroup = dao.getTawWorkbenchContactGroup(tawWorkbenchContactGroupId);
        assertNotNull(tawWorkbenchContactGroup);
    }

    public void testGetTawWorkbenchContactGroups() throws Exception {
        TawWorkbenchContactGroup tawWorkbenchContactGroup = new TawWorkbenchContactGroup();

        List results = dao.getTawWorkbenchContactGroups(tawWorkbenchContactGroup);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawWorkbenchContactGroup() throws Exception {
        TawWorkbenchContactGroup tawWorkbenchContactGroup = dao.getTawWorkbenchContactGroup(tawWorkbenchContactGroupId);

        // update required fields

        dao.saveTawWorkbenchContactGroup(tawWorkbenchContactGroup);

    }

    public void testRemoveTawWorkbenchContactGroup() throws Exception {
        String removeId = new String("3");
        dao.removeTawWorkbenchContactGroup(removeId);
        try {
            dao.getTawWorkbenchContactGroup(removeId);
            fail("tawWorkbenchContactGroup found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
