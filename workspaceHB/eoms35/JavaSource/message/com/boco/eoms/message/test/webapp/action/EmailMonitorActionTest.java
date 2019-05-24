
package com.boco.eoms.message.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.message.webapp.form.EmailMonitorForm;

public class EmailMonitorActionTest extends BaseStrutsTestCase {

    public EmailMonitorActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveEmailMonitor");
        addRequestParameter("method", "Save");

        EmailMonitorForm emailMonitorForm = new EmailMonitorForm();
        // set required fields
        emailMonitorForm.setApplyId("SsKhOjOxMqDlXhCpHtUcXlTzYeJxBpCw");
        emailMonitorForm.setContent("HcKtAkYtVlMyRoElIlVeRlLiUdQpOtFuVzNbSnMiVqAaTcAeWwKuMaNbIrFhFkEcJeAgAsBlBiGqTtFnHuYrZmQhHvGtBsRzInJiPfWpPuIfWfPhMoBeIrXrYjMlYcYhJgNtKfJuMpOjRsKgYmMjHtTjOaArDzDfOcUwAqBsLdDcZfUbYfIzChBiHeVsRyVnUdLuPkSvUdZyHgPvDoPyYkKmEmFcLbCgIkFcNuWbSrGtAuJoEmAxElPqPbWqDlNeXhJmRbLnQlKjKrMcYhCfQoQfUrQzVeMxNrQzXjPnYbIuKoWfQeIzCzPoOlIfFfZfFrYxRzEzAuBiBlMxKxSmDaZfNnSyYyJhKoRqXnXvJmZkUsQbAwSvDvMsMhAfEqOqZqXfGuDvAuMfHeXiZiCuPaLkSmMlQiIaIxMkMoXtJkOvLvXcRvEzSvFrEvAmMuZpUlGaEsYlCdToOeWyZzQeEfLhGyPeTdFpHnYiPsHnGjKnWsUiCpAb");
        emailMonitorForm.setDispatchTime("07/24/2008");
        emailMonitorForm.setEmail("email268122462@dev.java.net");
        emailMonitorForm.setReceiverId("OjEjFkCaBgUcJaUyGvEwOgJhNmGiSw");

        request.setAttribute(Constants.EMAILMONITOR_KEY, emailMonitorForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/emailMonitors");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.EMAILMONITOR_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editEmailMonitor");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.EMAILMONITOR_KEY));

    }

    public void testSave() throws Exception {
    	EmailMonitorForm emailMonitorForm = new EmailMonitorForm();
        setRequestPathInfo("/editEmailMonitor");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //EmailMonitorForm emailMonitorForm = (EmailMonitorForm) request.getAttribute(Constants.EMAILMONITOR_KEY);
        //assertNotNull(emailMonitorForm);

        setRequestPathInfo("/saveEmailMonitor");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        emailMonitorForm.setApplyId("ZpGzFgGnIhBhShYsEqBiAnQwMiGhKpCz");
        emailMonitorForm.setContent("EyYxLdKpUzCyQsFoVdEnRkOxFkLsSvGcDrQsPmMhSiIiJaGbAwEsBmHdMiXkPtCkEoGmNoYfFbLaQiFlVeCiHyBbPxMnFxJvKvMsIyDvHgOoIjVzFbZwSxRnKeXdFxCbWtShKgTcBgKyPbWkBpJeDjHsKiBxIzIkUnIvZkXsFjMrIxDlXaMvMkIiNnPjWtEgJfIgOuVkPeUsZbOsOuXsMqQgVhJwOdZjOaXnBzOmHoSyJkNaGcPfUyWzTwOfSvUyIzSvYjCsTsVvCyMsQmVfDlGuCbYkOpFhWrYwLmQlEqOeYlXhQtWmApMyXcGsVeJpHeXiLfFwKpQkNiIhXeIpLtYaGcVsPeAwRpWuJgHtRbXuKwTqAzHsZuBwDcUbKpHjYjAtEeFdWuJfFlFsRwKsYeZhYlZbPyDaJeSxAiZnAgTwEjRjFzEtCbQnYdRqZlOeGbBuRhLeXdTpXoGpIhTzHsUoGnFuQsTtToSoQhKtChFcDyItEqSh");
        emailMonitorForm.setDispatchTime("07/24/2008");
        emailMonitorForm.setEmail("email1466231121@dev.java.net");
        emailMonitorForm.setReceiverId("KmYrLuYzDqMzLuAeGjEdVyQiPlBlTj");

        //request.setAttribute(Constants.EMAILMONITOR_KEY, emailMonitorForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"emailMonitor.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editEmailMonitor");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
