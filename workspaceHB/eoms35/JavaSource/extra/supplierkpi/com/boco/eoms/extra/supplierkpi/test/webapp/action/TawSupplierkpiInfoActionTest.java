package com.boco.eoms.extra.supplierkpi.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiInfoForm;

public class TawSupplierkpiInfoActionTest extends BaseStrutsTestCase {

    public TawSupplierkpiInfoActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSupplierkpiInfo");
        addRequestParameter("method", "Save");

        TawSupplierkpiInfoForm tawSupplierkpiInfoForm = new TawSupplierkpiInfoForm();
        // set required fields

        request.setAttribute(Constants.TAWSUPPLIERKPIINFO_KEY, tawSupplierkpiInfoForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSupplierkpiInfos");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSUPPLIERKPIINFO_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiInfo");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSUPPLIERKPIINFO_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiInfo");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawSupplierkpiInfoForm tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) request.getAttribute(Constants.TAWSUPPLIERKPIINFO_KEY);
        assertNotNull(tawSupplierkpiInfoForm);

        setRequestPathInfo("/saveTawSupplierkpiInfo");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSUPPLIERKPIINFO_KEY, tawSupplierkpiInfoForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSupplierkpiInfo.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSupplierkpiInfo");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
