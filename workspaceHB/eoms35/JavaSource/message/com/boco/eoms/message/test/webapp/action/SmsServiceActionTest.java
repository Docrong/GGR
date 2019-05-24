package com.boco.eoms.message.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.webapp.form.SmsServiceForm;

public class SmsServiceActionTest extends BaseStrutsTestCase {

    public SmsServiceActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveSmsService");
        addRequestParameter("method", "Save");

        SmsServiceForm smsServiceForm = new SmsServiceForm();
        // set required fields
        smsServiceForm.setDeleted("S");
        smsServiceForm.setIsSendImediat(false);
        smsServiceForm.setIsSendNight(false);
        smsServiceForm.setMsgType("J");
        smsServiceForm.setName("OlCgGaLwTgVzReJrEiDgYmTgIdJcFpBuKpVeWzMkIbEyXzRlQfYoNcMcQeOnXkCdDdJfJnWsInZgVzCmZcOdMoRfEsXjLyVxDqRp");
        smsServiceForm.setUserId("SfQxOrIzCuMcEzDbUsRvVwTdAaOhAt");

        request.setAttribute(MsgConstants.SMSSERVICE_KEY, smsServiceForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/smsServices");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(MsgConstants.SMSSERVICE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editSmsService");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(MsgConstants.SMSSERVICE_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editSmsService");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        SmsServiceForm smsServiceForm = (SmsServiceForm) request.getAttribute(MsgConstants.SMSSERVICE_KEY);
        assertNotNull(smsServiceForm);

        setRequestPathInfo("/saveSmsService");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        smsServiceForm.setDeleted("I");
        smsServiceForm.setIsSendImediat(false);
        smsServiceForm.setIsSendNight(false);
        smsServiceForm.setMsgType("U");
        smsServiceForm.setName("NtElRpYdHlLtBsViXeWfGzLpUvBhJmOaHhSyRsVbBbBsAnMyMkGgAuHrYtPmFoUpQjKeVjVcZcYuEmUfRsTnExKjQiLyZiHfFzSs");
        smsServiceForm.setUserId("XmYxAaFzRmTiFaKmNcOaQgVbBzDaJz");

        request.setAttribute(MsgConstants.SMSSERVICE_KEY, smsServiceForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"smsService.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editSmsService");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
