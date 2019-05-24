
package com.boco.eoms.otherwise.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.otherwise.util.OtherwiseConstacts;
import com.boco.eoms.otherwise.webapp.form.TawRmInoutRecordForm;

public class TawRmInoutRecordActionTest extends BaseStrutsTestCase {

    public TawRmInoutRecordActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawRmInoutRecord");
        addRequestParameter("method", "Save");

        TawRmInoutRecordForm tawRmInoutRecordForm = new TawRmInoutRecordForm();
        // set required fields
        tawRmInoutRecordForm.setTestcardId("LnJwAyIgInJhOeAuUePhIdMmInRgGyLh");
        tawRmInoutRecordForm.setBorrowerId("UlDlBmKoYnQxQqTgRoNxXfNgFzGoYn");

        request.setAttribute(OtherwiseConstacts.TAWRMINOUTRECORD_KEY, tawRmInoutRecordForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawRmInoutRecords");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(OtherwiseConstacts.TAWRMINOUTRECORD_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawRmInoutRecord");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(OtherwiseConstacts.TAWRMINOUTRECORD_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawRmInoutRecord");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawRmInoutRecordForm tawRmInoutRecordForm = (TawRmInoutRecordForm) request.getAttribute(OtherwiseConstacts.TAWRMINOUTRECORD_KEY);
        //assertNotNull(tawRmInoutRecordForm);

        setRequestPathInfo("/saveTawRmInoutRecord");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        tawRmInoutRecordForm.setTestcardId("ZyUmZaDyQsUoBhJpRxXbYuVqOzCrPiPa");
        tawRmInoutRecordForm.setBorrowerId("HvQvSpYaUuBaEoBfHlVcMwDhPlMnIh");

        //request.setAttribute(OtherwiseConstacts.TAWRMINOUTRECORD_KEY, tawRmInoutRecordForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawRmInoutRecord.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawRmInoutRecord");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
