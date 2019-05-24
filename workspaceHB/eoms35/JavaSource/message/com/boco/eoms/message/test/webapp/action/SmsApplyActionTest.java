package com.boco.eoms.message.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.webapp.form.SmsApplyForm;

public class SmsApplyActionTest extends BaseStrutsTestCase {

    public SmsApplyActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveSmsApply");
        addRequestParameter("method", "Save");

        SmsApplyForm smsApplyForm = new SmsApplyForm();
        // set required fields
        smsApplyForm.setCount("1096522924");
        smsApplyForm.setEndTime("05/02/2008");
        smsApplyForm.setName("YkWdJhXgFjTlSdFpNaEqTmZmJxGtQtElDnPwLqAtVpWkUuKtNkBjRwEyHlIaDjVmZbCuNoUeOcAwKcYkWgPfHcNwMbWwHhXhEhLu");
        smsApplyForm.setInterval("QwLfZrMlZh");
        smsApplyForm.setMobile("IiBaOyIwVkVrBsFzQtYt");
        smsApplyForm.setReceiverId("LtDjYgKcGrYoZvHwFuAtObLbHoLlWu");
        smsApplyForm.setReceiverType("C");
        smsApplyForm.setRegetData(true);
        smsApplyForm.setRemark("TpStGkRwQiBfEnXnKxKhAeAuZiOfZbRoEaVrXgIlAzOrZlBuCvPeIlNnIrHsEeRkYyYeQiXoZvZzUiOrXnNsBdCpOwQwAjXmTwQjToJcQsQqYeWkAjTsQsKwOzKxXaLsQtIfDyEvEmEiKfSgKqUkPvSnIeViMrAbGrWtWvZqRnNfBxNtBzIsIlEqDsRjLgUzKsUkExJv");
        smsApplyForm.setServiceId("OnHnInIeVrGrGyCbQlSyWlItAoGmXeLa");
        smsApplyForm.setStartTime("05/02/2008");
        smsApplyForm.setUserId("BpIgBtXgFmBzTjYyYfKvMrFmYnNeIm");

        request.setAttribute(MsgConstants.SMSAPPLY_KEY, smsApplyForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/smsApplys");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(MsgConstants.SMSAPPLY_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editSmsApply");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(MsgConstants.SMSAPPLY_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editSmsApply");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        SmsApplyForm smsApplyForm = (SmsApplyForm) request.getAttribute(MsgConstants.SMSAPPLY_KEY);
        assertNotNull(smsApplyForm);

        setRequestPathInfo("/saveSmsApply");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        smsApplyForm.setCount("1081785666");
        smsApplyForm.setEndTime("05/02/2008");
        smsApplyForm.setName("RkEiCvYwJrVvFoAkIuYhCeXoZbPzWwHtYnCwNgWcYbTfWuTcVgNqFqUgJeJaBsHhAnLaPuXdZwDdTaSyMuYdBvEtZhQtNzHtIgAv");
        smsApplyForm.setInterval("ObQyBvNrZq");
        smsApplyForm.setMobile("GeEbBwLgKfQpIhLwAuDh");
        smsApplyForm.setReceiverId("AgKoEoMsCxGgPjGpUyBkPvIrWlGcPd");
        smsApplyForm.setReceiverType("L");
        smsApplyForm.setRegetData(true);
        smsApplyForm.setRemark("RmJwEgWeYnShJbTtXbMbRlZkVhOiWeKuVnGcQjYqDfYgTdTuTaEvCfGbZyFsZsNzDcFnIyKmOuGsScSyNwAbSqUqEoYwPzOnWnTuDxHsGcIdTvPjJxRtAsMnZrUaVaLjZfYvYmOpYpXhMjUmGfSgFnEiHoFiSfIdCfXnPsTrWxWuYhLlMlLjHuQbDaDsBxFzHwMgAiAy");
        smsApplyForm.setServiceId("AoMnGsRhOeLcXyLmDmMrFzMcHkSeHrWm");
        smsApplyForm.setStartTime("05/02/2008");
        smsApplyForm.setUserId("DlUwLoQdWwKvLgMbVzQiNvPsKmZzUq");

        request.setAttribute(MsgConstants.SMSAPPLY_KEY, smsApplyForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"smsApply.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editSmsApply");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
