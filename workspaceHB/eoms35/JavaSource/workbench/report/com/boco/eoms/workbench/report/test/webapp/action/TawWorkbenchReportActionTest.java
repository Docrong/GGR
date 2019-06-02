
package com.boco.eoms.workbench.report.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.workbench.report.webapp.form.TawWorkbenchReportForm;

public class TawWorkbenchReportActionTest extends BaseStrutsTestCase {

    public TawWorkbenchReportActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawWorkbenchReport");
        addRequestParameter("method", "Save");

        TawWorkbenchReportForm tawWorkbenchReportForm = new TawWorkbenchReportForm();

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawWorkbenchReports");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.TAWWORKBENCHREPORT_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawWorkbenchReport");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.TAWWORKBENCHREPORT_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawWorkbenchReport");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //TawWorkbenchReportForm tawWorkbenchReportForm = (TawWorkbenchReportForm) request.getAttribute(Constants.TAWWORKBENCHREPORT_KEY);
        //assertNotNull(tawWorkbenchReportForm);

        setRequestPathInfo("/saveTawWorkbenchReport");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.TAWWORKBENCHREPORT_KEY, tawWorkbenchReportForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawWorkbenchReport.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawWorkbenchReport");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
