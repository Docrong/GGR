
package com.boco.eoms.parter.baseinfo.mainmetermgr.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.parter.baseinfo.mainmetermgr.webapp.form.MainmetermgrForm;

public class MainmetermgrActionTest extends BaseStrutsTestCase {

    public MainmetermgrActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveMainmetermgr");
        addRequestParameter("method", "Save");

        MainmetermgrForm mainmetermgrForm = new MainmetermgrForm();
        // set required fields

//        request.setAttribute(Constants.MAINMETERMGR_KEY, mainmetermgrForm); 

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/mainmetermgrs");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.MAINMETERMGR_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editMainmetermgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.MAINMETERMGR_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editMainmetermgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //MainmetermgrForm mainmetermgrForm = (MainmetermgrForm) request.getAttribute(Constants.MAINMETERMGR_KEY);
        //assertNotNull(mainmetermgrForm);

        setRequestPathInfo("/saveMainmetermgr");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.MAINMETERMGR_KEY, mainmetermgrForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"mainmetermgr.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editMainmetermgr");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
