
package com.boco.eoms.message.test.webapp.action;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.message.webapp.form.SmsContentTemplateForm;

public class SmsContentTemplateActionTest extends BaseStrutsTestCase {

    public SmsContentTemplateActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveSmsContentTemplate");
        addRequestParameter("method", "Save");

        SmsContentTemplateForm smsContentTemplateForm = new SmsContentTemplateForm();
        // set required fields
        smsContentTemplateForm.setContent("RsMoQpCyIxFbJqIxHhApTqZfCuJyVqOdNcAiYpVlSgGaBcRiHbOoAnHeEwJxOnOsXqVeDvIjPvFvCdBdJpZgKjKbCrPaAwTfTnAvYcPyXoZgAcMgGgUzOiUeEqGtYkPnFrTdWyFmVsQnFnLzCkWhBeSrOnUdEsDwLqGmCwLeSxCsEqSfNyHnLwNzZtJpJrSkCfXnNqQrOtMxKrAvArSzJxCaYzQaUdDkSzJlEzRlNlGzDvPhSgPzCjRcTcUuEwYhUiLcTsBwZyEhNeDpIaEhFuTwCbVmFmXkNlSoMtWbKzUbBuWlIlEwPpIiVmAmXbUoLzPbGrFkCrBjHnGnHrDsTqMoAeSbNoPkSmVyWiSbHdFeRpHjSfWaAaYhAyIoLyQfDkZaUuNuQrYuSpUbYaBtNeGvCsRxQaExBiFzVsIbUtUnIuEkGvKgCeUkFxKjBdHjVhLmHnSkGpTiRqKmKrQcKhOnYbVnLoTxGmQxJgVzLzDeWnZcFrBjOxPrVqWmJoOeNdJwSoFlHcVgItQvQzGcQkEkRcJmNiFxBtRrFuYyQuPrYxScVfBwYsZkNiRmIpToDnFfElWoDeSjYfKqSaRtXqOzPgHhOcSmEuLwSzLvOdXbSjPzLpFpUxCxMpYsMnHaSiGyCqJrZnMtAnPpRdJiHcJePbUdCoDgKdWeYqVjMeQiFrNwKsJsPaBlEpCeSnAkIdVpAtUxArIlFzMoRcFgLhCgZdVsQrVmPqJnTwPpNsVoDoOlDmZjVcOxSiTdKhZsCjAvUzAaIkEjOrJeAlUvLhMkMsSxVvXzRqKoTmBwUhJkYsQnGhPlYtHqOaAcPdWvIrHvLkOoDzCxGzCqArIbYzDvJuMrEhZyUkYqGhRpNrRaXgVwKeLoPuQfTgDmSgMgLeSlMaVmOjZzWvMoGoSlFmYdMkRvKhLoGfYnNfRwWxDwDvCeMzZnJeZeYkYhYbJcCtVvRjRvWpXmIuZtMnMrAiVnFbRqImIxMvAcQtNmDvSkPdUoRyHiAnNvHuMdZmGrEgZdClCtLbSkXoCyQuBnWsBdPtFyWdNrHrYhOfOnTdIyGzAoGrVyOhOcQcKwFdRgSzWlKiRaTsShJfCpAtKnXxVhFoCoDkMbPbAuFxLiHwRkVrLsXpTyZbBfMlTkTvJaDrEkNfZcLjXiWkGrDePvYkWxLpOiLpTiRpVtLoQaLnJzChMqMfNnSwKnXxCjJnAsCcZgZwIrIbUwAqRlUpEzNhYlHqXuHpKxGlQrDlDnElUfWxFlIhNzGjQhWqNeOyNlSbIgSuEmMfTjZsRgEkWpBnIrLcXbNzSvEmYgCvTfSzMlXaOxBjMtLhCnYeUnTqVnKwVuGmRhFaQcKtGhYlAiObLlMlNkFhMuYsBxGdWvLyTuBpCvSrYdMqDoOuBhAiDeYoCxRvFkDsHzOtVjBwEmBlFlQnGgVmTtGmOvQhSaEtBxFoJgTvBwVvGdXsJiIfQhQeIiRjWpErZiIsPtZxPwIsUzDuUvDdJvKvXpIxFkYtTsLbOwCuVhJgByBkDfPeCjFzWyNmDyPbDoEvBxPrSqAaAvWoMzInGzCrVbOsFcGnJdXoAzFiVtIiNmYwUbOeEcXxOyErHkArSeGzRfJuUwTsXaUiWbZhGxReCaVsGsYcKcZkQkAdOqLwOvJyEgWlDhNpOhIcXwOoUjJyHxHnIqCrEkPaHlElJtVyVcSnYzIhRbHtPwAkGaWkRmDmEhGvFqJaSrIkNqRfBuDoUqWcTmOnXsDjGpLpPvZkDlDqOpBzXwPgThHrBmFjHcYuEmIzKiJiYeIlAmJtJnWeJoHyTnSdWfCbFtWoEnEyPqEsOaLlFrAaFbPpWySkMgNwRuWtSdDoDfFhHhCgIqStLsHzZwCpGnFbVoZhHaYkUhIwCgElUmZbYgHuSjDtKfQmTrOiWvVpIeSqSuYeUdOkVgJaMuKsChGzRbZjPcSeYzJxQoPuFdDlCdJkIoSjWtDvPrHtTiKqLgLbQmKiGpEjMuQwGdJgVcDgUkSfRvKnIaKqHhPiEaRtSaQhPaNjTyXlCkAwAfLkSfVeYiTnPbInYoZqUkYeUeTmAoNiNnUyUcAgQrPlWvAfHcPgFqBcTyZtIgWyOpJkHeGdQnToOwNnXwBfPiDxThGpTgOqHjKiKfCjZpWhPiEbDdMkElRrSsOdBcFsAxIhSmZuIhYrFfHfUeHhStFsNiXiHmQwQgOiWkCoBjCzIeLsZjKlGnIbFwZkIdNtLtGcOhJxSsKtLeEvBrWvIkSwKgFkPqFnGoKrCmXnNnYyDsUgReHwOsVlEjWzAuQtRmAlToQqFkDpQhQeDbYfErKbVvVsNaRnOyXiUxZgImOgVtNmJvWhDjQmIlBeGgQvYfHzIrCoKnShCmHeNnLqDkHmRjTuPsBgZbAvWnGpPpCuIhRiJmXuJfDpSfPmBkVtJlRoUiEeQdQkRwRyPgJhVoThAoGzMkIwTnNpDiGgVsIpKjUeOsZbPaLhGpAxTrAePzTlUgIcMnCfDhPbClMtYeNyOfXrLbGsGlEgReFgNyYdBsYzVmRtSnQiIaAdTpGcFmZvMzFhHmGgWmWyNaPcBbSdFcSsXwMmQjUuLuXdUqMqFvBrTtOvMaRdGhSxRwBaGmYxHtNlVkBfHlNeWfQlXpWnNqYqBwQmHyDwUhVhWxKgYjNlClHtHjKzTeShIuJdVeDeEdFdQaLnMnSpZtJyDmZqJtKvHsGzKyZfBcEcNqCkIfOzEbKuOpSjQkZzWlPwSwEuJvOgRzFmObYtEeVgPoMlIrGaUrWmPsHtYuWmTeUeVdDsRoThJtJfFjYnQeRxPgUxWpXfTzFrKgXdVbAuBmJkGpFlBfBzGfBbHqPwEbXiUrYfZdEyUrHdVwNoKlApAsUsUwAeHeHaDvOlRkDwTtDwZqMaJtIhFqMlIsWuSwTwDsNkSzReRvJvLyFfCrQfMlFzEpYkMfFiLxFuQsMgReWkNlUxKqLfFhViLjAvYzReSnEtJaKhAyRgFo");

        request.setAttribute(Constants.SMSCONTENTTEMPLATE_KEY, smsContentTemplateForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/smsContentTemplates");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.SMSCONTENTTEMPLATE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editSmsContentTemplate");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.SMSCONTENTTEMPLATE_KEY));

    }

    public void testSave() throws Exception {
    	SmsContentTemplateForm smsContentTemplateForm = new SmsContentTemplateForm();
        setRequestPathInfo("/editSmsContentTemplate");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //SmsContentTemplateForm smsContentTemplateForm = (SmsContentTemplateForm) request.getAttribute(Constants.SMSCONTENTTEMPLATE_KEY);
        //assertNotNull(smsContentTemplateForm);

        setRequestPathInfo("/saveSmsContentTemplate");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        smsContentTemplateForm.setContent("BkIzCfIdEfIcAnFyZiLtOeEpRdLyHyWbMlUtDyLjRqNeFfUkEgWhDtTuVuOyGxWpQxTaRqBpDyMrAwIePeZoOqVqMrPdSfMuMvMiJiDrVwPtPxXmVcBeOaPbOtGsLeJjGwWpEeYwSaCpDqGlGoFeTlVeWdDuDvStJyLvDcAlGmKjDyJqJzHvLrYwOxWtSrJpTzGrGqNoWwSrZfMxEbEcYqMzFdElGuLxJcEqVdWdIgVxVrCjSrVhVqVcAdMbTgWcUvJzDcGdVuGgQoGxYmHyPaZeEcJzMoOrKxCuYvAdSjMoQdJsZsJvRvUfNrZzMpNrOzVxWiUfFjPvSiWbHaWlNsOzRiPoBoMuDvHwHjAsLyDoMgMjNuZoBlJhAiStBjKrFpFfFxVsYlDvGhKsFoUeFeHkFlFcXgScBkWtXwZoYaMcLcCfZwJlZjNpVuVpBlJzMiIuWrImLrKpAlByYcUiOsPdMqVkVmLfIlNkGvFuBeKyPiZdHsNwRjJkTzAgIgRvCzXmHwIaNsIwHoZhCvClFoRgUwVqNwWmBiDoCiToCmRjFhIyRvDoJhQuYhWwBcOhLnDaUmHkMnCwZeKlQnEjKiBbVfRkNxAqCmIqPjWaVaKwPiMaUdHgMmDlFvOsWiPlZkCkPmQxCpMuQaMeWxVtZeHvVdTeHkApHjUlJaQwObRxIeIfRvYpRbJlUsEcWtItWaKeYeJyRhKuNjUbNrCcOdDoPuRkGsTgJxBzKxIjCjXuEdVkTbHvRbYyLlLaOsYgIrOuZmQuCbMtReSdUcSkLmNwLgWcTxLiYuXeGfFyZmOhBjSpZyOrQxGcOaFzYsMaNgRpWdBhXvOyUkAwHaKzRuRqJvEsFfAaSuYuYgUbDxOrHfUlSzJyVoZuFjXtOhScPdAqSzLrEeIwKiNwGiUeCjBvWkGvCfUwIvBbCqNbIqUfKnQdXeHnXtNjApDgOpKgTtHiWkHmQqPcWvKpHdQhKpLkHoGcYfXpNnBoWlIsWdVdHtAdApJzVmPkIeEeOoYtEqLjPiHcUbEyIqZhSeKeDcVvMaHuIiZrAhCyZrAhVwGiElQcCjTnYcVpFhNyYfZcHyBhKvUtVsDkYqBqJvYfRaUeEuXtAzMkTsGsXjYdAkHhSlGkWdDpYqBnLeMhRwTxMoXnWeNePvLtPdMmUsYyIjAqVpSfZbIkTvEcSdLnAtGoAbSeOhGtBcGfQjPgJoTqKjLaWdWjXqNlHeSvBwKxJgYmPbUkIgNoFhJsDcMlDuFwOoByGdNlWvRhCvGuKfGjVrMbIkPmFuQuVrLmBwFjBfDsOyLlJgLxNvYlUlAlPgMsJgImPmLvMqOmDnBpWxUnTkNzPsKnWoRyEaXuJhRhQlXyQpMcUfQaIjZbXqSeMgAfZzZlBnGlPeRkUkSaRhTuLkLbGfFfFgKlFzPoIyBvKdVeOaVqMuEhNlGgOoHmSnCvLeLjWlLcIfWoAxEqMsUvMpYgBtZqIfGdYfTrZeEhRsImBhEmRoHjHiOgRaGaDpVfMhOhAoDoMwLwUdJdWpMyRoCyIcBcHnPxNoWbIbTyYcCwUxKaYjUaEmKnZwFyPePyJyIqKpXmMyXpZeReFrXdGlYuPhUzFwOlBdEgDtCrUaSbJuAnHlTyZuKtGkQzXiUiZmFwRyYzJfHdKgOyVhXnSmYkWvBkEcGeAuHkBvQvZiDfKyZtBvXpArZoYlDxXzAfYwUkMcYpVrVrBgYfZrYmBiLgObSbFiPtYkQxRlJbLfHvOkXtEmDwZqTdQwFmKxYqOhHiJtKzHuMdUuWxVfLeScLjOlFzMkOcQzDeLpJaJoMkQrTwWuHxYiBhWsSfLeRuWgCfVcEsZwKtSrWzOvIlMvXcIrEiIaYcDcNoYqNbDdAfLaLdBtJyPzPzEpHdBmHsQyFuJiEaUgLcZxSfLbQoVrIaSgSfBcVjUdYaQhUiOyVgYtEkBbGzZqWfBuBnLfJaOoXwLpQrNqUsXhVzUnYaNiJoGmGiFyZbFoRxAbXcPmStJkCzWtDsWhUuHuBvAbQuAjFcRnYqTkLvYnSwWcBjDdHxZxVyOsOnKfJuSlYaSzFfYcXjZgFdKlDfOgSiHxIhIhKpRlXzXvTjVmTuHhIuQkNqRoSoBoPiUoUdLiRnPuVbIwDeQpBnMdCeTqNwEmVsSaOaFnKoIhWmNpLkQjJfTuFrQfZrVzLwBaHhAfFuNpWmIuZtRzCyPjWtAfLvRjDaGeCaEiYvJuMmFaEgSxNsTmRaYvYrLaLdOeGbLkOnBrRdClObRtLjYkCtQmQtGkNtBxJfRwEqFaCpLaOfQrJsRnAlIlTlRqIpWcToWlBrJeIxAcSlXwBdIwMvBiPdOzWtFxBzZyIaCsUsCdDlQjFhFbRhKzXiRyVwGtAcSgCgAmFyXxGjLdNaJgFcBnEiThJxUtUeRmWxHxNeKpSgInUlXrUkJaUuXmErKzBtQnIdEkKaZlRgCnNtKbRzUuUcFnYbXhSwMtZzChWqZmDqOyQnXpZrJmGgJoRjRcXhQzNsPoHsUrTgNgIzPcUwPeYrXiWsUtYgOrClXkLrXcQhNaPcJcRcIrZvKlFyYyPaXoYyOnCyLmUaGkJzYuPdHhZjDaFsVgBiIcKuGzMtSdKgWaNvDqShEqFeVrRrShIiDuWvBeLwKvMmSySnHnWsZsKhTsMfKuTrQdKpUdHxLdCtMfRdAeYkNkGfAkSbEcDsGdEjSaTpSbFoFcSkHtQxFcPiDvAkGvJhPgMfJvXnPqMyXhHdQpPwCkUxVpCiGuKlSdVjVkYzYeRcBqIoQlOhCoCvDgPeEhGlXwItAwMeIlWuXrCfRtYiOoRdRkFmMmLiJrLbLxWmHeKnLcYqYhQiCyLiGqZaIcNgPiQqBoYhHzQqYqWyRzVbNrKiPhVaGjZnOlUaJjVuLgHrRdQvSaQgFdJjOrMuAhJpHjLmAoKdUnUfXuYvFfEfQrNjHmKoRyPjBaKyEnGuUbDpBuXrPpRxBfRiVkHmYiSwCiUzUoWi");

        //request.setAttribute(Constants.SMSCONTENTTEMPLATE_KEY, smsContentTemplateForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"smsContentTemplate.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editSmsContentTemplate");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
