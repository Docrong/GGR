package com.boco.eoms.commons.system.role.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemOrganizationProxyForm;

public class TawSystemOrganizationProxyActionTest extends BaseStrutsTestCase {

    public TawSystemOrganizationProxyActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSystemOrganizationProxy");
        addRequestParameter("method", "Save");

        TawSystemOrganizationProxyForm tawSystemOrganizationProxyForm = new TawSystemOrganizationProxyForm();
        // set required fields

        request.setAttribute(Constants.TAWSYSTEMORGANIZATIONPROXY_KEY, tawSystemOrganizationProxyForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSystemOrganizationProxys");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMORGANIZATIONPROXY_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSystemOrganizationProxy");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSYSTEMORGANIZATIONPROXY_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSystemOrganizationProxy");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawSystemOrganizationProxyForm tawSystemOrganizationProxyForm = (TawSystemOrganizationProxyForm) request.getAttribute(Constants.TAWSYSTEMORGANIZATIONPROXY_KEY);
        assertNotNull(tawSystemOrganizationProxyForm);

        setRequestPathInfo("/saveTawSystemOrganizationProxy");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSYSTEMORGANIZATIONPROXY_KEY, tawSystemOrganizationProxyForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSystemOrganizationProxy.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSystemOrganizationProxy");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
