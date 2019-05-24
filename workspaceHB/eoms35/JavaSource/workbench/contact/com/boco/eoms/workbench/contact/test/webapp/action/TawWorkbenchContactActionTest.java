
package com.boco.eoms.workbench.contact.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.workbench.contact.webapp.form.TawWorkbenchContactForm;
import com.boco.eoms.workbench.contact.util.ContactConstants;
public class TawWorkbenchContactActionTest extends BaseStrutsTestCase {

    public TawWorkbenchContactActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawWorkbenchContact");
        addRequestParameter("method", "Save");

        TawWorkbenchContactForm tawWorkbenchContactForm = new TawWorkbenchContactForm();
        // set required fields

        //request.setAttribute(Constants.TAWWORKBENCHCONTACT_KEY, tawWorkbenchContactForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawWorkbenchContacts");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.TAWWORKBENCHCONTACT_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawWorkbenchContact");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.TAWWORKBENCHCONTACT_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawWorkbenchContact");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawWorkbenchContactForm tawWorkbenchContactForm = (TawWorkbenchContactForm) request.getAttribute(ContactConstants.TAWWORKBENCHCONTACT_KEY);
        assertNotNull(tawWorkbenchContactForm);

        setRequestPathInfo("/saveTawWorkbenchContact");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.TAWWORKBENCHCONTACT_KEY, tawWorkbenchContactForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawWorkbenchContact.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawWorkbenchContact");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
