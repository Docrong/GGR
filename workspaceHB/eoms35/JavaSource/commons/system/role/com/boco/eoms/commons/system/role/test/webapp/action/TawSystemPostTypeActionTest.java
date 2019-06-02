package com.boco.eoms.commons.system.role.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemRoleTypeForm;

public class TawSystemPostTypeActionTest extends BaseStrutsTestCase {

    public TawSystemPostTypeActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSystemPostType");
        addRequestParameter("method", "Save");

        TawSystemRoleTypeForm tawSystemPostTypeForm = new TawSystemRoleTypeForm();
        // set required fields

        request.setAttribute(Constants.TAWSYSTEMROLETYPE_KEY, tawSystemPostTypeForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSystemPostTypes");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMROLETYPE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSystemPostType");
        addRequestParameter("method", "Edit");
        addRequestParameter("posttype_id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMROLETYPE_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSystemPostType");
        addRequestParameter("method", "Edit");
        addRequestParameter("posttype_id", "1");

        actionPerform();

        TawSystemRoleTypeForm tawSystemPostTypeForm = (TawSystemRoleTypeForm) request.getAttribute(Constants.TAWSYSTEMROLETYPE_KEY);
        assertNotNull(tawSystemPostTypeForm);

        setRequestPathInfo("/saveTawSystemPostType");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSYSTEMROLETYPE_KEY, tawSystemPostTypeForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSystemPostType.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSystemPostType");
        addRequestParameter("method", "Delete");
        addRequestParameter("posttype_id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
