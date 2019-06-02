
package com.boco.eoms.parter.baseinfo.carmgr.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;

import com.boco.eoms.parter.baseinfo.carmgr.webapp.form.CarMgrForm;

public class CarMgrActionTest extends BaseStrutsTestCase {

    public CarMgrActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveCarMgr");
        addRequestParameter("method", "Save");

        CarMgrForm carMgrForm = new CarMgrForm();
        // set required fields

    //    request.setAttribute(Constants.CARMGR_KEY, carMgrForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/carMgrs");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.CARMGR_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editCarMgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.CARMGR_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editCarMgr");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //CarMgrForm carMgrForm = (CarMgrForm) request.getAttribute(Constants.CARMGR_KEY);
        //assertNotNull(carMgrForm);

        setRequestPathInfo("/saveCarMgr");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.CARMGR_KEY, carMgrForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"carMgr.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editCarMgr");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
