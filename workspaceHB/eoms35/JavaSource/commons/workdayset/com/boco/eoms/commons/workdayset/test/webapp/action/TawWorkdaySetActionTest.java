
package com.boco.eoms.commons.workdayset.test.webapp.action;

import java.util.Date;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.commons.workdayset.webapp.form.TawWorkdaySetForm;

public class TawWorkdaySetActionTest extends BaseStrutsTestCase {

    public TawWorkdaySetActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawWorkdaySet");
        addRequestParameter("method", "Save");

        TawWorkdaySetForm tawWorkdaySetForm = new TawWorkdaySetForm();
        // set required fields
        tawWorkdaySetForm.setAreaId("RfUlUvIdCsBrFxBiGtAcHtLbFwMuPfPk");
        tawWorkdaySetForm.setCreateTime("07/11/2008");

        request.setAttribute(Constants.TAWWORKDAYSET_KEY, tawWorkdaySetForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawWorkdaySets");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.TAWWORKDAYSET_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawWorkdaySet");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.TAWWORKDAYSET_KEY));

    }

    public void testSave() throws Exception {
    	TawWorkdaySetForm tawWorkdaySetForm = new TawWorkdaySetForm();
        setRequestPathInfo("/editTawWorkdaySet");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //TawWorkdaySetForm tawWorkdaySetForm = (TawWorkdaySetForm) request.getAttribute(Constants.TAWWORKDAYSET_KEY);
        //assertNotNull(tawWorkdaySetForm);

        setRequestPathInfo("/saveTawWorkdaySet");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        tawWorkdaySetForm.setAreaId("PqIvJpRcByBrNfOoMjQfQsXrKlAsRhKo");
        tawWorkdaySetForm.setCreateTime("07/11/2008");

        //request.setAttribute(Constants.TAWWORKDAYSET_KEY, tawWorkdaySetForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawWorkdaySet.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawWorkdaySet");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
