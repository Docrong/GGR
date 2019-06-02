package com.boco.eoms.commons.system.area.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.area.webapp.form.TawSystemAreaForm;

public class TawSystemAreaActionTest extends BaseStrutsTestCase {

    public TawSystemAreaActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSystemArea");
        addRequestParameter("method", "Save");

        TawSystemAreaForm tawSystemAreaForm = new TawSystemAreaForm();
        // set required fields

        request.setAttribute(Constants.TAWSYSTEMAREA_KEY, tawSystemAreaForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSystemAreas");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMAREA_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSystemArea");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMAREA_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSystemArea");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawSystemAreaForm tawSystemAreaForm = (TawSystemAreaForm) request.getAttribute(Constants.TAWSYSTEMAREA_KEY);
        assertNotNull(tawSystemAreaForm);

        setRequestPathInfo("/saveTawSystemArea");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSYSTEMAREA_KEY, tawSystemAreaForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSystemArea.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSystemArea");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
