
package com.boco.eoms.workbench.contact.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.service.impl.TawWorkbenchContactGroupManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchContactGroupManagerTest extends BaseManagerTestCase {
    private final String tawWorkbenchContactGroupId = "1";
    private TawWorkbenchContactGroupManagerImpl tawWorkbenchContactGroupManager = new TawWorkbenchContactGroupManagerImpl();
    private Mock tawWorkbenchContactGroupDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawWorkbenchContactGroupDao = new Mock(TawWorkbenchContactGroupDao.class);
        tawWorkbenchContactGroupManager.setTawWorkbenchContactGroupDao((TawWorkbenchContactGroupDao) tawWorkbenchContactGroupDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawWorkbenchContactGroupManager = null;
    }

    public void testGetTawWorkbenchContactGroups() throws Exception {
        List results = new ArrayList();
        TawWorkbenchContactGroup tawWorkbenchContactGroup = new TawWorkbenchContactGroup();
        results.add(tawWorkbenchContactGroup);

        // set expected behavior on dao
        tawWorkbenchContactGroupDao.expects(once()).method("getTawWorkbenchContactGroups")
            .will(returnValue(results));

        List tawWorkbenchContactGroups = tawWorkbenchContactGroupManager.getTawWorkbenchContactGroups(null);
        assertTrue(tawWorkbenchContactGroups.size() == 1);
        tawWorkbenchContactGroupDao.verify();
    }

    public void testGetTawWorkbenchContactGroup() throws Exception {
        // set expected behavior on dao
        tawWorkbenchContactGroupDao.expects(once()).method("getTawWorkbenchContactGroup")
            .will(returnValue(new TawWorkbenchContactGroup()));
        TawWorkbenchContactGroup tawWorkbenchContactGroup = tawWorkbenchContactGroupManager.getTawWorkbenchContactGroup(tawWorkbenchContactGroupId);
        assertTrue(tawWorkbenchContactGroup != null);
        tawWorkbenchContactGroupDao.verify();
    }

    public void testSaveTawWorkbenchContactGroup() throws Exception {
        TawWorkbenchContactGroup tawWorkbenchContactGroup = new TawWorkbenchContactGroup();

        // set expected behavior on dao
        tawWorkbenchContactGroupDao.expects(once()).method("saveTawWorkbenchContactGroup")
            .with(same(tawWorkbenchContactGroup)).isVoid();

        tawWorkbenchContactGroupManager.saveTawWorkbenchContactGroup(tawWorkbenchContactGroup);
        tawWorkbenchContactGroupDao.verify();
    }

    public void testAddAndRemoveTawWorkbenchContactGroup() throws Exception {
        TawWorkbenchContactGroup tawWorkbenchContactGroup = new TawWorkbenchContactGroup();

        // set required fields

        // set expected behavior on dao
        tawWorkbenchContactGroupDao.expects(once()).method("saveTawWorkbenchContactGroup")
            .with(same(tawWorkbenchContactGroup)).isVoid();
        tawWorkbenchContactGroupManager.saveTawWorkbenchContactGroup(tawWorkbenchContactGroup);
        tawWorkbenchContactGroupDao.verify();

        // reset expectations
        tawWorkbenchContactGroupDao.reset();

        tawWorkbenchContactGroupDao.expects(once()).method("removeTawWorkbenchContactGroup").with(eq(new String(tawWorkbenchContactGroupId)));
        tawWorkbenchContactGroupManager.removeTawWorkbenchContactGroup(tawWorkbenchContactGroupId);
        tawWorkbenchContactGroupDao.verify();

        // reset expectations
        tawWorkbenchContactGroupDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawWorkbenchContactGroup.class, tawWorkbenchContactGroup.getId());
        tawWorkbenchContactGroupDao.expects(once()).method("removeTawWorkbenchContactGroup").isVoid();
        tawWorkbenchContactGroupDao.expects(once()).method("getTawWorkbenchContactGroup").will(throwException(ex));
        tawWorkbenchContactGroupManager.removeTawWorkbenchContactGroup(tawWorkbenchContactGroupId);
        try {
            tawWorkbenchContactGroupManager.getTawWorkbenchContactGroup(tawWorkbenchContactGroupId);
            fail("TawWorkbenchContactGroup with identifier '" + tawWorkbenchContactGroupId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawWorkbenchContactGroupDao.verify();
    }
}
