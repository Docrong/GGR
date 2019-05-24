package com.boco.eoms.commons.system.role.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemRoleForm;

public class TawSystemPostActionTest extends BaseStrutsTestCase {

    public TawSystemPostActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSystemPost");
        addRequestParameter("method", "Save");

        TawSystemRoleForm tawSystemPostForm = new TawSystemRoleForm();
        // set required fields

        request.setAttribute(Constants.TAWSYSTEMROLE_KEY, tawSystemPostForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSystemPosts");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMROLE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSystemPost");
        addRequestParameter("method", "Edit");
        addRequestParameter("postId", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMROLE_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSystemPost");
        addRequestParameter("method", "Edit");
        addRequestParameter("postId", "1");

        actionPerform();

        TawSystemRoleForm tawSystemPostForm = (TawSystemRoleForm) request.getAttribute(Constants.TAWSYSTEMROLE_KEY);
        assertNotNull(tawSystemPostForm);

        setRequestPathInfo("/saveTawSystemPost");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSYSTEMROLE_KEY, tawSystemPostForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSystemPost.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSystemPost");
        addRequestParameter("method", "Delete");
        addRequestParameter("postId", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
