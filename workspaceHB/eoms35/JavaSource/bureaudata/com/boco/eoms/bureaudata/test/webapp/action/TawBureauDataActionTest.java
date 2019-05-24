
package com.boco.eoms.bureaudata.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.bureaudata.webapp.form.TawBureauDataForm;

public class TawBureauDataActionTest extends BaseStrutsTestCase {

    public TawBureauDataActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawBureauData");
        addRequestParameter("method", "Save");

        TawBureauDataForm tawBureauDataForm = new TawBureauDataForm();
        // set required fields

        request.setAttribute(Constants.TAWBUREAUDATA_KEY, tawBureauDataForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawBureauDatas");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.TAWBUREAUDATA_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawBureauData");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.TAWBUREAUDATA_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawBureauData");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //TawBureauDataForm tawBureauDataForm = (TawBureauDataForm) request.getAttribute(Constants.TAWBUREAUDATA_KEY);
        //assertNotNull(tawBureauDataForm);

        setRequestPathInfo("/saveTawBureauData");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.TAWBUREAUDATA_KEY, tawBureauDataForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawBureauData.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawBureauData");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
