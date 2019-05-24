
package com.boco.eoms.commons.system.code.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.code.webapp.form.TawSystemCodeForm;

public class TawSystemCodeActionTest extends BaseStrutsTestCase {

    public TawSystemCodeActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSystemCode");
        addRequestParameter("method", "Save");

        TawSystemCodeForm tawSystemCodeForm = new TawSystemCodeForm();
        // set required fields

        request.setAttribute(Constants.TAWSYSTEMCODE_KEY, tawSystemCodeForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSystemCodes");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.TAWSYSTEMCODE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSystemCode");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.TAWSYSTEMCODE_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSystemCode");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //TawSystemCodeForm tawSystemCodeForm = (TawSystemCodeForm) request.getAttribute(Constants.TAWSYSTEMCODE_KEY);
        //assertNotNull(tawSystemCodeForm);

        setRequestPathInfo("/saveTawSystemCode");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.TAWSYSTEMCODE_KEY, tawSystemCodeForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSystemCode.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSystemCode");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
