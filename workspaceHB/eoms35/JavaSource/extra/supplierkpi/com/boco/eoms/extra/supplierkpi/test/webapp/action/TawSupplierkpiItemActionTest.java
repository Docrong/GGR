package com.boco.eoms.extra.supplierkpi.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiItemForm;

public class TawSupplierkpiItemActionTest extends BaseStrutsTestCase {

    public TawSupplierkpiItemActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSupplierkpiItem");
        addRequestParameter("method", "Save");

        TawSupplierkpiItemForm tawSupplierkpiItemForm = new TawSupplierkpiItemForm();
        // set required fields

        request.setAttribute(Constants.TAWSUPPLIERKPIITEM_KEY, tawSupplierkpiItemForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSupplierkpiItems");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSUPPLIERKPIITEM_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiItem");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSUPPLIERKPIITEM_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiItem");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawSupplierkpiItemForm tawSupplierkpiItemForm = (TawSupplierkpiItemForm) request.getAttribute(Constants.TAWSUPPLIERKPIITEM_KEY);
        assertNotNull(tawSupplierkpiItemForm);

        setRequestPathInfo("/saveTawSupplierkpiItem");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSUPPLIERKPIITEM_KEY, tawSupplierkpiItemForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSupplierkpiItem.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiItem");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
