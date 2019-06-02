
package com.boco.eoms.parter.baseinfo.basemetermgr.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.parter.baseinfo.basemetermgr.webapp.form.BasemetermgrForm;

public class BasemetermgrActionTest extends BaseStrutsTestCase {

    public BasemetermgrActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveBasemetermgr");
        addRequestParameter("method", "Save");

        BasemetermgrForm basemetermgrForm = new BasemetermgrForm();
        // set required fields

//        request.setAttribute(Constants.BASEMETERMGR_KEY, basemetermgrForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/basemetermgrs");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.BASEMETERMGR_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editBasemetermgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.BASEMETERMGR_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editBasemetermgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //BasemetermgrForm basemetermgrForm = (BasemetermgrForm) request.getAttribute(Constants.BASEMETERMGR_KEY);
        //assertNotNull(basemetermgrForm);

        setRequestPathInfo("/saveBasemetermgr");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.BASEMETERMGR_KEY, basemetermgrForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"basemetermgr.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editBasemetermgr");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
