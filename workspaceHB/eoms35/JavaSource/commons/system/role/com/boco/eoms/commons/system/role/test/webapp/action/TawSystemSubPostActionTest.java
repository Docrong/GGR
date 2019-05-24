package com.boco.eoms.commons.system.role.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemSubRoleForm;

public class TawSystemSubPostActionTest extends BaseStrutsTestCase {

    public TawSystemSubPostActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSystemSubPost");
        addRequestParameter("method", "Save");

        TawSystemSubRoleForm tawSystemSubPostForm = new TawSystemSubRoleForm();
        // set required fields

        request.setAttribute(Constants.TAWSYSTEMSUBROLE_KEY, tawSystemSubPostForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSystemSubPosts");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMSUBROLE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSystemSubPost");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMSUBROLE_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSystemSubPost");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawSystemSubRoleForm tawSystemSubPostForm = (TawSystemSubRoleForm) request.getAttribute(Constants.TAWSYSTEMSUBROLE_KEY);
        assertNotNull(tawSystemSubPostForm);

        setRequestPathInfo("/saveTawSystemSubPost");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSYSTEMSUBROLE_KEY, tawSystemSubPostForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSystemSubPost.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSystemSubPost");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
