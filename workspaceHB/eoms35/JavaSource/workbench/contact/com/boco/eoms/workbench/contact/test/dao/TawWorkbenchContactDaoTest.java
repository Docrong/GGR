package com.boco.eoms.workbench.contact.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchContactDaoTest extends BaseDaoTestCase {
    private String tawWorkbenchContactId = new String("1");
    private TawWorkbenchContactDao dao = null;

    public void setTawWorkbenchContactDao(TawWorkbenchContactDao dao) {
        this.dao = dao;
    }

    public void testAddTawWorkbenchContact() throws Exception {
        TawWorkbenchContact tawWorkbenchContact = new TawWorkbenchContact();

        // set required fields

        dao.saveTawWorkbenchContact(tawWorkbenchContact);

        // verify a primary key was assigned
        assertNotNull(tawWorkbenchContact.getId());

        // verify set fields are same after save
    }

    public void testGetTawWorkbenchContact() throws Exception {
        TawWorkbenchContact tawWorkbenchContact = dao.getTawWorkbenchContact(tawWorkbenchContactId);
        assertNotNull(tawWorkbenchContact);
    }

    public void testGetTawWorkbenchContacts() throws Exception {
        TawWorkbenchContact tawWorkbenchContact = new TawWorkbenchContact();

        List results = dao.getTawWorkbenchContacts(tawWorkbenchContact);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawWorkbenchContact() throws Exception {
        TawWorkbenchContact tawWorkbenchContact = dao.getTawWorkbenchContact(tawWorkbenchContactId);

        // update required fields

        dao.saveTawWorkbenchContact(tawWorkbenchContact);

    }

    public void testRemoveTawWorkbenchContact() throws Exception {
        String removeId = new String("3");
        dao.removeTawWorkbenchContact(removeId);
        try {
            dao.getTawWorkbenchContact(removeId);
            fail("tawWorkbenchContact found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
