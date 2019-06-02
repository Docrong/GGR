package com.boco.eoms.message.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.webapp.form.SmsLogForm;

public class SmsLogActionTest extends BaseStrutsTestCase {

    public SmsLogActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveSmsLog");
        addRequestParameter("method", "Save");

        SmsLogForm smsLogForm = new SmsLogForm();
        // set required fields
        smsLogForm.setContent("SrEsEhRlZkIaFpQtZaEnDyUmJaGgBuMmTsWzNmNzUdDuVvKmViVwXdOwAmPuWaJgNaCqPaSyCbDiWtUfTlXzYuNbQqExTdNpFtFpFiJfHxZqUaTxOiYtSeUlZiMrScBeSoUtQeYkOlVgLeIkBdOsXcMwJcGwOdWwLtLhJdQaXuGiJhLrScTbDmRlRdHsZyFfRdEkVsKbWnXePlBxFwYcXoBtMxTxAyJoXlNxTgSuDlAuMbNjTqWiXzKeIqDaLaQePoMnBoDpNhSuWwEwJzXnJtRmLqGtNeRqFsEhBrVlKwVpOoIfKrNmYlAxScSdFxWpEbDxUlEfDjSwYkEfHyFkRwNzPxKdJhFsNiTuLbDpSbQeXiLkWpHeAyMeSaLgFxEwQaTdOhHyAxCtCdUtMbKiRlRlLtAyIkBmAoGeTgPrXuSyEySxPwZtYsLzSrFjMuLjPtVwIeBrRxXhQzTiTcWeZwYoHjWnCuXpEsAtXvVcAdMlRoVzKrQm");
        smsLogForm.setDispatchTime("05/02/2008");
        smsLogForm.setInsertTime("05/02/2008");
        smsLogForm.setMobile("ZtFsZwIkQkCrBvXzAtPkYcTaIiVaNzPlNbRoLxPrOtWaEaYtNzAmZdNqWtOcYuMtLmRnAqNjGhYrBdGtTaQiRpWmQvTiHjDfShTn");
        smsLogForm.setBuizId("UvQxIbMeMaAgEzWqOoMvTbMeDaQdVhHj");
        smsLogForm.setMonitorId("AtAzVwXbYeGhIsYxPxEeGpZwYgBfClMs");
        smsLogForm.setReason("UdQcNwWjYmCuKgRjZuTtPeIiDlZbBfBpDmBwGbKsSpVkJdYgVhOpBcTzBnNtRpIaSnByLlZdTvWkYeFoCmOoFfInOmOuMkLfUoUcJbIzOoZxUoNuBtGhEhRcUkJsGsMiUcZiSaDwNpOzMpJrFjXbSzYyQbNmVhYbCnElPpUbYxKoYwAoZqTuAkPkMnApSmSmAoYmSxSfOmFjTkVsGtXhMqAzDoVbMcXzEiBzWsPbWpWhYnDaHaVzStSdEmQtYmLpBbDyOfEmGcQsCiDuYrYoXuKrHfMfAgQpTbBiCoCbAhWlQeBdBsNgLiSyCnHjLrUeZrLvQgClPqPoDsScBlQxQzTzEsBtYwOmKtJsSzJkKcTyHnDqBrWtYmJyCfTuQzFbIeIaYnCtLlKgXbQeUqOcCoIzDwZnSmCgDuReYeSuPcJiZrYbHuZlWqTyWcLmGeRyToUjMdPxXwBsMwRxHjGvUdCxGjDuLtLtOtFtDiXkGfIeXaCcIlXh");
        smsLogForm.setReceiverId("GuZkShGmXiKeIuFoTnWoArAyHdCxXe");
        smsLogForm.setServiceId("GxViOkShZhGwPhOyMpMjZbKoMwSwHmUg");
        smsLogForm.setServiceName("MmVjCxNgQiFrXgZnGtZdWnUxGzNaPlUrTiEnPqCuGjOpGqMeHdZlAlBmGdFaAkQsJlKuBcMiGeNaTpXpTfUdEdWkZdMrIvDdQpEg");
        smsLogForm.setSuccess("M");

        request.setAttribute(MsgConstants.SMSLOG_KEY, smsLogForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/smsLogs");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(MsgConstants.SMSLOG_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editSmsLog");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(MsgConstants.SMSLOG_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editSmsLog");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        SmsLogForm smsLogForm = (SmsLogForm) request.getAttribute(MsgConstants.SMSLOG_KEY);
        assertNotNull(smsLogForm);

        setRequestPathInfo("/saveSmsLog");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        smsLogForm.setContent("MaXdDnOpJrIpFhSiFkUeYlLpLrTsDnAqUkObEaOoCxVuXhWwEwOqEdPoJrCmQyFrRiYyToEjWuZkJuBaEfUdUhYdYwNhDmLgIbBgLiNwAxPjJuUlFtXvIjPtPbFhEuNuXpJbKrDrOhFtSePfKlRxVvYgZvYkTbIyQrAaIyTbEgMjAzVuQgUfQnFnLeRmTnUnAoKzOtEyAvDmLuIsKnJlYrWdPhOpItOiHiBnLkNhFgZrFmIyXfBjYrNkKuCtZxKpEkQaBgQtEqOhScYbIsAmAmBkBfVsHiIjWgDuYyXjQiFuLdMdPfQsGcUtRrYcPfMlCiQlKhDuMxRnXyJkBnVqCiGzUpFuHzShXjZrRuTpCuKwPmCqRsInPuYuHeJoSiGkBwUeTgIvYeSeUeMiJhMeLcJbRpLxJaLsErXsEpFwIyRqXeGrPoGpNpMkIrTgZmAlBgUuEdMmLxQsSvSyEhCaSwCxGzNfFcKfWoKbBvGhQoShGiWiLlTk");
        smsLogForm.setDispatchTime("05/02/2008");
        smsLogForm.setInsertTime("05/02/2008");
        smsLogForm.setMobile("XxYcZuMgOkNdDwHlVyBfWrRzPtRpOlPrEiOzTqCpFsLnOhVgAcOkQkGkHbMlChVwKxSvVxLkOxToJuSsZeNgBrDfJuYuZmNzXhZp");
        smsLogForm.setBuizId("HxGxPmWhQkYkBvBvWnZvOpWnMpAiNaOb");
        smsLogForm.setMonitorId("XtEyNsCeMjIpAsTqRtSoNdLtEuKzEvXj");
        smsLogForm.setReason("RuGzVrWbAtLaZwPrYtBzScZcZjHjTdFxFgLzSyFbPrDiRcCgQfQcDcPmDdZtHuZqGkAjCuYpWxMnPjMyTpQjNaUeAlNiQpAdWvGrMlMfOcMzHfRhLdOnRuXjRsAvSpUvArYwYqVhGkOaYoEtRvOgBpOnXmDqKnCuXgOyVfBjChIsDsDpQoBkDzTiTeFhFrPyCxDoHdRnZtMcYuTgXoKmLrBqJvUiVnWlEzVeKwOaBuZiBcWqOeZgLiXzXyPbAeTeOhNhHoXkDwOlQaVfPtIpEtIvXxQwVnRuMnRzQdRmUvLdTzSoQwXtPbAsSkJtYfOtMgOrKaYvGfIuPxKbYuPpNnGbEhIxKfQqUvMfWjWqBjKxTnOzLcYkUfNpTmEsMjVyFwTbQtRiMjDrTlHqJtHsLgDwUiMcWkCsHsDwNsZaKsPtUyEyUgInNdDdWaEvAyKwLhLgRtHvRmUkWjVfSaXqVfMhSkHhZiLnSgPvFrXpEvPzWrMyDdAh");
        smsLogForm.setReceiverId("YuMjFcMmBqAoJeMnXyVuRoJdJiShNk");
        smsLogForm.setServiceId("BeTaOvMmIeEgPmHfQzBnGsRwMcHdRnMi");
        smsLogForm.setServiceName("KkUiZrNmSkFpXqXzRoVsYzKvOmWrKqVtDeLcEgBhFfQuYoBlKbQqCbJkLsAuMnPnAeSiDrHeZpMdImSnRhYjKjYjSqYlXbRiXpSz");
        smsLogForm.setSuccess("D");

        request.setAttribute(MsgConstants.SMSLOG_KEY, smsLogForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"smsLog.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editSmsLog");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
