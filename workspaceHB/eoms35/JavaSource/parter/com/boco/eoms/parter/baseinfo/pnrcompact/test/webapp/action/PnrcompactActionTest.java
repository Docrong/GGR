
package com.boco.eoms.parter.baseinfo.pnrcompact.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.parter.baseinfo.pnrcompact.webapp.form.PnrcompactForm;

public class PnrcompactActionTest extends BaseStrutsTestCase {

    public PnrcompactActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/savePnrcompact");
        addRequestParameter("method", "Save");

        PnrcompactForm pnrcompactForm = new PnrcompactForm();
        // set required fields

//        request.setAttribute(Constants.PNRCOMPACT_KEY, pnrcompactForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/pnrcompacts");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.PNRCOMPACT_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editPnrcompact");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.PNRCOMPACT_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editPnrcompact");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //PnrcompactForm pnrcompactForm = (PnrcompactForm) request.getAttribute(Constants.PNRCOMPACT_KEY);
        //assertNotNull(pnrcompactForm);

        setRequestPathInfo("/savePnrcompact");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.PNRCOMPACT_KEY, pnrcompactForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"pnrcompact.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editPnrcompact");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
