package com.boco.eoms.message.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.webapp.form.SmsMonitorForm;

public class SmsMonitorActionTest extends BaseStrutsTestCase {

    public SmsMonitorActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveSmsMonitor");
        addRequestParameter("method", "Save");

        SmsMonitorForm smsMonitorForm = new SmsMonitorForm();
        // set required fields
        smsMonitorForm.setApplyId("HoCfZaQdUaLkKaMjTwBaLlLiCyOgXnKa");
        smsMonitorForm.setContent("QfRfDpExNuKvGpAdYfZlTkGkHxAzNySpNxXjOaFgMhTxSeErFfVfRyWiBnAuGrPcNvCyKfZvHdLsKtTvCfRuMeLcMsIyXsVhXvUjPiIbLhIlOfHuZrKuOzIhFeKqAnQqGbClOvHrIqAqLfAjMoNjVdSbMpPqQqRwQhFfPfPeQeKrRsMhEgHlNhImVkLpKsBcWfFtHqVrZwWnDjFwNkHeMjPuOjLiDtSdCzWnEbEbPkNvVoQaZeTvGjSiBwZpGkYyLeEsSkJiRpUbKhLhLvOySaJlOzMuVyIpHxMzMbSfPmHbIqWjYjLnBdLpEeZcQwTbHcRhSuQwOiTwZhNsOpFqQyAbEaPbLmUsDeThUrFgJiMpLjIkOgNgKpQkHhOaZcGtIxJpLiCdQaUsTpUlUqPjOjYrPjNzKvDyCmEnRnVbFiVrRmHbMgNcGmInLnSyUoNvUaRcOwAxMrUnCcAiZwJpSrCpRaZaIdDxJyCcOxBcPpYyIrVeDqDn");
        smsMonitorForm.setDispatchTime("05/02/2008");
        smsMonitorForm.setMobile("NvOkDxJjXuScGbUsVhEzJcNpHxKoLtJkGvThLnNrGyCpIpOaCmJjQyGmWcVuYaIsHuBnWzCtUcBbGmNbIhZkWlOwZzIfCzNnYhKx");
        smsMonitorForm.setReceiverId("PvKnGzWoAcBaMaGqDiWbHhAeVbBmOe");

        request.setAttribute(MsgConstants.SMSMONITOR_KEY, smsMonitorForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/smsMonitors");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(MsgConstants.SMSMONITOR_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editSmsMonitor");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(MsgConstants.SMSMONITOR_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editSmsMonitor");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        SmsMonitorForm smsMonitorForm = (SmsMonitorForm) request.getAttribute(MsgConstants.SMSMONITOR_KEY);
        assertNotNull(smsMonitorForm);

        setRequestPathInfo("/saveSmsMonitor");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        smsMonitorForm.setApplyId("JiGjHvEdRlScEpDuGlCyByQyKdEcUoLk");
        smsMonitorForm.setContent("UaRuMiUoQxPmTuAxCpLpLeMnRcQdGrJdOnTqVnGqQeHzOsItEbInRgTpJhLhCcUhQfSwEhZmZrNsByLuAdNgFgUtOzMbSgFxHdTqDiGcSpTyUxVvCtFlRhNpWtTvCxRiRxNvIeEnQtFiUlHnZfOuWwGbAxCpAiRgQvHlYhTiIoJeIzLbRwFcPtAcMaEyHaBaPpClFmHfNhGdYnJjKiFsHvClPyLfLlPjZwFvKkBeOqLbRfOiLtCkHpToOyTgRbIiTeAzEoFkMpYzOyDcHmFzTjDiEeYtWtOiAjTbUkTdWiPqLjIgBxItShXfGfRpZfXqGwXzXtEyJoQcWcCjDkOsNfEkUaWzRaLyNnTjMiXcFcHmHbEsAaJlBzYtFeUtGlNhWnWxBqGjYoCeVhFlQrAwZxKeOfYiQyMfIpGvZhPxSoBdRuGoOiEmKpYcQgXtXqSeUqRuHdRmNuCoJuJwVxQvLvVoOaNqVfBdIfWvOaIaUsBvWcSfQrPc");
        smsMonitorForm.setDispatchTime("05/02/2008");
        smsMonitorForm.setMobile("SzXyFxHySxFnRsMvCpIeGhOjDyRdUwYdCjQrSlCpOtVhDmAwLqLjZaLhVtGlUpUnWeGmYyBlLlIkLgAeWcAxUgFuKlXjTtMdXePl");
        smsMonitorForm.setReceiverId("TjToFuImGbPcNcLpUqUfEwKuCoOrIj");

        request.setAttribute(MsgConstants.SMSMONITOR_KEY, smsMonitorForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"smsMonitor.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editSmsMonitor");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
