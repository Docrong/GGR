package com.boco.eoms.extra.supplierkpi.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiInstanceForm;

public class TawSupplierkpiInstanceActionTest extends BaseStrutsTestCase {

    public TawSupplierkpiInstanceActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSupplierkpiInstance");
        addRequestParameter("method", "Save");

        TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = new TawSupplierkpiInstanceForm();
        // set required fields
       
        request.setAttribute(Constants.TAWSUPPLIERKPIINSTANCE_KEY, tawSupplierkpiInstanceForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSupplierkpiInstances");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSUPPLIERKPIINSTANCE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiInstance");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSUPPLIERKPIINSTANCE_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiInstance");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) request.getAttribute(Constants.TAWSUPPLIERKPIINSTANCE_KEY);
        assertNotNull(tawSupplierkpiInstanceForm);

        setRequestPathInfo("/saveTawSupplierkpiInstance");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSUPPLIERKPIINSTANCE_KEY, tawSupplierkpiInstanceForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSupplierkpiInstance.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiInstance");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
