
package com.boco.eoms.parter.baseinfo.metermgr.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.parter.baseinfo.metermgr.webapp.form.MetermgrForm;

public class MetermgrActionTest extends BaseStrutsTestCase {

    public MetermgrActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveMetermgr");
        addRequestParameter("method", "Save");

        MetermgrForm metermgrForm = new MetermgrForm();
        // set required fields

//        request.setAttribute(Constants.METERMGR_KEY, metermgrForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/metermgrs");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.METERMGR_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editMetermgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.METERMGR_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editMetermgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //MetermgrForm metermgrForm = (MetermgrForm) request.getAttribute(Constants.METERMGR_KEY);
        //assertNotNull(metermgrForm);

        setRequestPathInfo("/saveMetermgr");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.METERMGR_KEY, metermgrForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"metermgr.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editMetermgr");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
