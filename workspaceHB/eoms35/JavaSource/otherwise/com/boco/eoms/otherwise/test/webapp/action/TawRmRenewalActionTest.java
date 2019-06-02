
package com.boco.eoms.otherwise.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.otherwise.util.OtherwiseConstacts;
import com.boco.eoms.otherwise.webapp.form.TawRmRenewalForm;

public class TawRmRenewalActionTest extends BaseStrutsTestCase {

    public TawRmRenewalActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawRmRenewal");
        addRequestParameter("method", "Save");

        TawRmRenewalForm tawRmRenewalForm = new TawRmRenewalForm();
        // set required fields
        tawRmRenewalForm.setTestcardId("UzXhQwEeKqZsMiLmYlVzRmRjDlIkTkNb");
        tawRmRenewalForm.setBorrowerId("TvHzJwOmFdMkIwCwUtVdFnKrMpUyIg");

        request.setAttribute(OtherwiseConstacts.TAWRMRENEWAL_KEY, tawRmRenewalForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawRmRenewals");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(OtherwiseConstacts.TAWRMRENEWAL_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawRmRenewal");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(OtherwiseConstacts.TAWRMRENEWAL_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawRmRenewal");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawRmRenewalForm tawRmRenewalForm = (TawRmRenewalForm) request.getAttribute(OtherwiseConstacts.TAWRMRENEWAL_KEY);
        //assertNotNull(tawRmRenewalForm);

        setRequestPathInfo("/saveTawRmRenewal");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        tawRmRenewalForm.setTestcardId("HwQpPkSvKfYyRvLgPvYzCoAsQaFvEeRa");
        tawRmRenewalForm.setBorrowerId("SuJpVfFqEbCtUaMfRbQoUxCsPfIfXb");

        //request.setAttribute(OtherwiseConstacts.TAWRMRENEWAL_KEY, tawRmRenewalForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawRmRenewal.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawRmRenewal");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
