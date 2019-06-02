
package com.boco.eoms.workbench.contact.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.workbench.contact.webapp.form.TawWorkbenchContactGroupForm;
import com.boco.eoms.workbench.contact.util.ContactConstants;
public class TawWorkbenchContactGroupActionTest extends BaseStrutsTestCase {

    public TawWorkbenchContactGroupActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawWorkbenchContactGroup");
        addRequestParameter("method", "Save");

        TawWorkbenchContactGroupForm tawWorkbenchContactGroupForm = new TawWorkbenchContactGroupForm();
        // set required fields

        //request.setAttribute(Constants.TAWWORKBENCHCONTACTGROUP_KEY, tawWorkbenchContactGroupForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawWorkbenchContactGroups");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.TAWWORKBENCHCONTACTGROUP_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawWorkbenchContactGroup");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.TAWWORKBENCHCONTACTGROUP_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawWorkbenchContactGroup");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawWorkbenchContactGroupForm tawWorkbenchContactGroupForm = (TawWorkbenchContactGroupForm) request.getAttribute(ContactConstants.TAWWORKBENCHCONTACTGROUP_KEY);
        assertNotNull(tawWorkbenchContactGroupForm);

        setRequestPathInfo("/saveTawWorkbenchContactGroup");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.TAWWORKBENCHCONTACTGROUP_KEY, tawWorkbenchContactGroupForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawWorkbenchContactGroup.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawWorkbenchContactGroup");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
