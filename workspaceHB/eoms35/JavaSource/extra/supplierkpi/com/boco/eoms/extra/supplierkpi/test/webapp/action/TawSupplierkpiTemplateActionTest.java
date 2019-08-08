package com.boco.eoms.extra.supplierkpi.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiTemplateForm;

public class TawSupplierkpiTemplateActionTest extends BaseStrutsTestCase {

    public TawSupplierkpiTemplateActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSupplierkpiTemplate");
        addRequestParameter("method", "Save");

        TawSupplierkpiTemplateForm tawSupplierkpiTemplateForm = new TawSupplierkpiTemplateForm();
        // set required fields

        request.setAttribute(Constants.TAWSUPPLIERKPITEMPLATE_KEY, tawSupplierkpiTemplateForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSupplierkpiTemplates");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSUPPLIERKPITEMPLATE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiTemplate");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSUPPLIERKPITEMPLATE_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiTemplate");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawSupplierkpiTemplateForm tawSupplierkpiTemplateForm = (TawSupplierkpiTemplateForm) request.getAttribute(Constants.TAWSUPPLIERKPITEMPLATE_KEY);
        assertNotNull(tawSupplierkpiTemplateForm);

        setRequestPathInfo("/saveTawSupplierkpiTemplate");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSUPPLIERKPITEMPLATE_KEY, tawSupplierkpiTemplateForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[]{"tawSupplierkpiTemplate.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiTemplate");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
