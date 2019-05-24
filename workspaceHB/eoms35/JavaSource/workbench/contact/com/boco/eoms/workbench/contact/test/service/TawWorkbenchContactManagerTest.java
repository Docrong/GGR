
package com.boco.eoms.workbench.contact.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.service.impl.TawWorkbenchContactManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchContactManagerTest extends BaseManagerTestCase {
    private final String tawWorkbenchContactId = "1";
    private TawWorkbenchContactManagerImpl tawWorkbenchContactManager = new TawWorkbenchContactManagerImpl();
    private Mock tawWorkbenchContactDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawWorkbenchContactDao = new Mock(TawWorkbenchContactDao.class);
        tawWorkbenchContactManager.setTawWorkbenchContactDao((TawWorkbenchContactDao) tawWorkbenchContactDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawWorkbenchContactManager = null;
    }

    public void testGetTawWorkbenchContacts() throws Exception {
        List results = new ArrayList();
        TawWorkbenchContact tawWorkbenchContact = new TawWorkbenchContact();
        results.add(tawWorkbenchContact);

        // set expected behavior on dao
        tawWorkbenchContactDao.expects(once()).method("getTawWorkbenchContacts")
            .will(returnValue(results));

        List tawWorkbenchContacts = tawWorkbenchContactManager.getTawWorkbenchContacts(null);
        assertTrue(tawWorkbenchContacts.size() == 1);
        tawWorkbenchContactDao.verify();
    }

    public void testGetTawWorkbenchContact() throws Exception {
        // set expected behavior on dao
        tawWorkbenchContactDao.expects(once()).method("getTawWorkbenchContact")
            .will(returnValue(new TawWorkbenchContact()));
        TawWorkbenchContact tawWorkbenchContact = tawWorkbenchContactManager.getTawWorkbenchContact(tawWorkbenchContactId);
        assertTrue(tawWorkbenchContact != null);
        tawWorkbenchContactDao.verify();
    }

    public void testSaveTawWorkbenchContact() throws Exception {
        TawWorkbenchContact tawWorkbenchContact = new TawWorkbenchContact();

        // set expected behavior on dao
        tawWorkbenchContactDao.expects(once()).method("saveTawWorkbenchContact")
            .with(same(tawWorkbenchContact)).isVoid();

        tawWorkbenchContactManager.saveTawWorkbenchContact(tawWorkbenchContact);
        tawWorkbenchContactDao.verify();
    }

    public void testAddAndRemoveTawWorkbenchContact() throws Exception {
        TawWorkbenchContact tawWorkbenchContact = new TawWorkbenchContact();

        // set required fields

        // set expected behavior on dao
        tawWorkbenchContactDao.expects(once()).method("saveTawWorkbenchContact")
            .with(same(tawWorkbenchContact)).isVoid();
        tawWorkbenchContactManager.saveTawWorkbenchContact(tawWorkbenchContact);
        tawWorkbenchContactDao.verify();

        // reset expectations
        tawWorkbenchContactDao.reset();

        tawWorkbenchContactDao.expects(once()).method("removeTawWorkbenchContact").with(eq(new String(tawWorkbenchContactId)));
        tawWorkbenchContactManager.removeTawWorkbenchContact(tawWorkbenchContactId);
        tawWorkbenchContactDao.verify();

        // reset expectations
        tawWorkbenchContactDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawWorkbenchContact.class, tawWorkbenchContact.getId());
        tawWorkbenchContactDao.expects(once()).method("removeTawWorkbenchContact").isVoid();
        tawWorkbenchContactDao.expects(once()).method("getTawWorkbenchContact").will(throwException(ex));
        tawWorkbenchContactManager.removeTawWorkbenchContact(tawWorkbenchContactId);
        try {
            tawWorkbenchContactManager.getTawWorkbenchContact(tawWorkbenchContactId);
            fail("TawWorkbenchContact with identifier '" + tawWorkbenchContactId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawWorkbenchContactDao.verify();
    }
}
