
package com.boco.eoms.parter.baseinfo.lanmetermgr.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.parter.baseinfo.lanmetermgr.webapp.form.LanmetermgrForm;

public class LanmetermgrActionTest extends BaseStrutsTestCase {

    public LanmetermgrActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveLanmetermgr");
        addRequestParameter("method", "Save");

        LanmetermgrForm lanmetermgrForm = new LanmetermgrForm();
        // set required fields

//        request.setAttribute(Constants.LANMETERMGR_KEY, lanmetermgrForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/lanmetermgrs");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.LANMETERMGR_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editLanmetermgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.LANMETERMGR_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editLanmetermgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //LanmetermgrForm lanmetermgrForm = (LanmetermgrForm) request.getAttribute(Constants.LANMETERMGR_KEY);
        //assertNotNull(lanmetermgrForm);

        setRequestPathInfo("/saveLanmetermgr");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.LANMETERMGR_KEY, lanmetermgrForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"lanmetermgr.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editLanmetermgr");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
