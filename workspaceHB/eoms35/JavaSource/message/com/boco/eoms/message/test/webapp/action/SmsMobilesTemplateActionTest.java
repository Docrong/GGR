
package com.boco.eoms.message.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.message.webapp.form.SmsMobilesTemplateForm;

public class SmsMobilesTemplateActionTest extends BaseStrutsTestCase {

    public SmsMobilesTemplateActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveSmsMobilesTemplate");
        addRequestParameter("method", "Save");

        SmsMobilesTemplateForm smsMobilesTemplateForm = new SmsMobilesTemplateForm();
        // set required fields
        smsMobilesTemplateForm.setMobiles("PqYiJkRmAoYhZaPdUmMdIzNcApYfEgJrRyMdNySvWdBlEkJbYlLwEpUcMaGyYaHbJlFcHuXzGcFkCfZuOhVpImMvXkZdIeMwQjXcWgUcYuQrUpJzYcAzSoQeXsZbLxSvOySwSjUzKqQnFlFzNsQxAwPwYiSdBtMkWtElMnKeLuDmZnMtCbHrCxZaRhKuUtLgMvUcPiZzHuHtYlLqDqUfHzBvUsOtDrXpSxQiVuLaBsTcAiRwJkFbMzZpViJjKqSiMsJzDmMtAgRyTbQtSvTwUlThLkYmIrCbRuIyYhWoSfLsLtIsHgLwHoBuTxAfGbEkDjEuLfAwWnFjZiCaZkDaZwQvCvTiLjKsJpJxImAnRzCgYlWgYdYyExNcXwCpSdJuByIrRwMoJnLhPqRpJrVmZcBkBeTnFoUfJwMaYiCaDyEdLwDsWvHbZbSiZtKkNqAkUxShSuEfBlSwCyMpPoPbLcJbRmZhCiFpDsFaZiNeMqXtXrSbBwAkLxThMcHrShNlTnHmIlNdMzIkNvZxUrMoNmEsHwBfJjWeZyPbWtLtVbVdDtJxSjGlIkPjYsXlLwIbBuPyVrDcYnDlHuQsHiDdTiUqArTfXvSxXgHkDsXaGaCvWlNaGnUiFqTdQnPdZjIaNnDvEsNaRiUbEhDfMxHfAnPfUpHbBsYfMiEgHmPbNeAhPqPzElWtKdKgPaPjPcHhOvRjToGuMpGuCyYiZwCyHyNqDxSxQbHoXzPeVkXuAhJsKyJwYmFoWuAtJfZrIzFuBwVqIvMpVrFcCrQgEbPyTmLkFmMfVwKfRlAxBjPiRkUmBxOxBjSiChScCzMzEmUaFjJfZgJzPfJdGvGdLqDzMhMeOdRfAmIuTjDxZpCvXwFeRaZeQjTcDbOnIbTmTaVpOnQsWoLdDjAjOmVwWlPoPvQgFoLoUpYzGcMdIdWbLoHvVdEeRkNbInEvYeOcHyPfVhYmKvBuBvHfVkTmEcDpXgViLrMcSnHdMrTqHvXbHcUwIhSoThMbGfWbBmPfMuFwWmZkXlRtGdQsRiCcTaHcBjTeQsKmMhZzTgGdNnDvQyArHeOrXtXkTxIaYiOiNjExNxGiQlKpSqHfIbDjKwLwHsXeKjEuLtRjJxIvBdRzSmDeUaOjRrBmSyVeYsRuWyQlMqMxQnKjJyOzNsQfHjKbKbEcGxLdXyJqOdPxHaMkSjRrYaWqZiSlAdTzGhDgHkVeKbYcQjDeEsGwMsLmAsQwOfGsRkZmTwZqEuQkPxNuUmXiVgGcXiQrMcWyBpLnPjQwNaYzDqAwXcEbWcPpFqReJiYlYxDeNcNbYhEoPdJnVgFuIzOvTaVtRjYvGfWfSnRdTbGhAdOqBvLmLqCeTnPiRcRaAnDqFpTiZuMtUwLeLsOdZsDyUlAwXmWqJtTfMlBcPnJeQeAwWgYvIxAtLqLoNtVdPnRaGzKeAkZzRfOeWqXeBlEiLpDpChKgPhDoQcNyYtDsUjOnYrRvPqAlBgNwWlFbTtCwJtPeFeLsKhHnTuKiHvEeUoNlTzFwPjWzPtAsCdZsJeYyPiHfKqVlDuZoXfJmElYrMiLrWmPfZnGkHzEgWeKsVrPsRfQvKcGnNhBvMlIgOvRpHrIdTaMyBaAzLfCwTrKuRhEeQxFnYtRuXkKjKuCxFqBvCkSiIkJhDhAvWuDmNlOnXnScMoXoTeKzHwUjThMuAmScGsYaIhVhIlVrPbDzFsJkNuAvXeLcQeRzZzDdLlAcLdEgAnFcScXxWaViLfThPfCoMsIzQfGoIhGkMnFeNxCoNrSaVlHbPjRaOlKeIjOtBlRkPbBeXkPwEeAcIpKpEjTuYqMiSnFdUrVnCdKlHbMsUkBgTkTiXoRsEqQqBhFoUdQmVhHwQuJtTkBmUvYiMpCyGcIfOdHzOhMlLnMzAbRfYtXfHuAqPsZoFaOcCxWvRyPdUlZmNwGpNvUnCxKbSnRuOxBvKgOfHwKxJvKzIzFaMwBtVuCbYfZkVhNjOqRxMoLpXbFzGpXlUoSeIiXaSsWfVfPwNiCvKgIgOeQfCeKkRpQiQkGqCgHdRmPfCvLsVdYeWaGxSjXxCwLxFpYsUbCbMoYiBjKbAuBjKqOtBnTmMnLuKnIaDqKjZfHaRwFkUcAzLxYuRdItZmYkCaTpPvGoMbObDzTmUdVgWmYqBpKyMeOaPzEdCiCaItRiQkKdBnGnMzJaWnXuFoCbWcZsCtEfCtWmMqPfWuPqKiHlStMsQfNoZzDvOhPbQgApNnJrTgFlUlWiTcTgIzCzUpSlKhZjWkNgRsDyUySsXfJaIfMpKnXhTxIpXsGbCtCuAsVwHxZdCoToPlKqWlRgJrEzTuPvOzAxUtKjKuZsOwJdYkUyNjQzJsCbZhVxAqOeVhGrKiJjUtRgDjPhApUvNeAdJpZmYqUlJnBvTrMrUuOaDcXgZrJfLdOwBiXoRlLuQjCkXkPaAxUkTyKdSiSdBpUyIeQdWyAeIhKfYfNyEmOgYhCzVyBsSnScVvImJgKeWbNtRmJqNiNzDwRdKtUuAjGhQiBxBaPcAwDjClLxGlVeVuPxDsCfVkDdFqNmGtKcVrNdQjOeLdRrKlQhAkNaHzUrXiBeApXvVuNtIbStPoYsLzAeZkAbKiHfIsZhEjUdJvMpNpNpKfBnWtBwYnErQdYsKcEaZlBbIoWhMlOzPaDySfXpEsRxVeUbHhTgRoYhZpOhUoLpHkQpYxUlTaCpNzQyYjExTeGjDrAwEyIbJsQvPxBnJsTfFfBqIuOyWuPtPsAgQmTeFvEpDeDiHuDjDaUiTsHkTrHsTvObSxSeSdCuCqXkTwFcGlNqLiPxJuEoNsUzXyRjKtQpXbHhLvEaGpOdKxUxSdYfNeNtVzKvAuAjBkZnFkNyDoCjMpOcEuKbJbRdWrLuCcJpNmQfFiGpZvSgTyIbUeIuMiKsUdJoBdAzFsEnAhHxPcSgZeIxMkKuXiXeDyVmLfTfBcItJvGyYvGgSfIqJcJtKoZnEwFlDwMmZsLgTnMv");

        request.setAttribute(Constants.SMSMOBILESTEMPLATE_KEY, smsMobilesTemplateForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/smsMobilesTemplates");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.SMSMOBILESTEMPLATE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editSmsMobilesTemplate");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.SMSMOBILESTEMPLATE_KEY));

    }

    public void testSave() throws Exception {
    	SmsMobilesTemplateForm smsMobilesTemplateForm = new SmsMobilesTemplateForm();
        setRequestPathInfo("/editSmsMobilesTemplate");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //SmsMobilesTemplateForm smsMobilesTemplateForm = (SmsMobilesTemplateForm) request.getAttribute(Constants.SMSMOBILESTEMPLATE_KEY);
        //assertNotNull(smsMobilesTemplateForm);

        setRequestPathInfo("/saveSmsMobilesTemplate");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        smsMobilesTemplateForm.setMobiles("OtLlCpGlTeMdZiNaUnLiKkKmIdOaPmDfAxRrJsKeYbEnMbKwWkUySbUuKkVgKeXqGmFrLoOkPiDsNjRsYcIzVvUgNyAfIeNpHpGzSeTkDcKhNxTnXvImMfUsAwGlAqTeAoYsDaTrOfViHeFzAfDgZrMbOtHeOpUyVzTyQdNgKjTpNlLtIfCzGpCkWePxVtRuJuEcPnCzCkCoWjYeHlTzXmQqOcFhXlWmVtOoTfKgFhIcXgNeHtOtVhRcHwIiZwMaOtIvKbXoNtCgPiBoBsLnXeHqByLvIpInKhFoLfVeSsYeDkFgSzAzYiDvWuTwOfMfWwDbImBvVpCkLoQeLgPnVeYkRsRxFjYvJaWaQiJyScUmUnGaIxXlCvDaKmOsLwUdImEnGpKySaRqWsYmIvToQrUiHpByEjDrIkMdSeJzSpOuNdItRfDcOiVdRhDsGxVvPeCmWgYqJvHyUvWbGaJsOtIvRpVkBaDeNeLvOdDdPgZoUoJqShRwFtHiMmAaFtSzYwCfWmQsKvPjLlJaGdZiWmNvOrLkRlUcTkZlHjKdSeInWgDjCoUeMpVeMhExAcRxCyIsOvLsVyXqJsUbXrQqXdJjNaCkXtNoFuIgHxUiSqOmRaOuTkMrGeMgCjRfWgIvEkJiBiWsWkOsGsKsCcMjUiImWeEvLdDoKtJjZcChJjYvXwZsBpZxSsIvMpVeHdJxRbNlZtMvYxKoUkRhAkXmNdNpNbOuVeXqFnAdXkWqRmMiOoWiMgCvIlCmJoTwAoRpFqHeLqDyOpBxSySkBxFrUiLkYiFrHrFqNkLyNwSmQwCqGlYbTbPqTzHvYmQeYjWnObTwYsLiDoMpPsVrRsThWmChBiGwPeUiKwSxDaPdDfHdVtSePmQkReUcWlKcAlSzMjAxGsFjAqGoVqQjHpOsPePgGlUiCwHaXsBkRfMyXdJjIuYiRtEpUmFwBpTsFdNrReNjKwVcJvZgLeTbIkRfGaKjZbYgPyMfVgHtYsPqKlXwAzXkMgJwPcIpQeXpGeLkSpOpMtQaYhWlBnZgXaMnKzWnAyMvBmWiWlGaCwUgWhFuFeWzHlArMzKnYwVrTyCoVkQwOqMpFpXtWwYcXpFmWlOzDdFuVxYhKcEkXdBsWeRmDqNeDgXyLfRyGuRrYoTaZkVeTrWaCuMlKlHaVuJrPjTeEjFdHyRfJwEgLmXiQgEgWhIcMbGpDzShVsDzQdYoPiHrWwPuXtNaNqDiTtZePlTiVbMuZwWaFrNhBjTtQnLwHxZtIvCnRuXlTgAdFxQrQePnNkAlJzGuJjPyBkDeXiFaMlEcGnQfVqCrHyHiQtPlYfVkCmRvRqSqWwLqIgVgJgCkXsOeHeHrCyAbPoMnIrWxKxAtJtRkKhYjVeZzRyLwYzEmLvZvXfHxRhLmAbYbIkNhVpBmSbDtZmPtKcHpMkPkJyWgKfXzHvMaFdJrTeXoXjTdHuYoMxQxFlFuPuAnGdBbMoDvTtTbGwMqRcHsRuYhQkWqAwOvBcXjCyNiXbKfUxFwPpZkRdGmArNqImNlZuDeNuRiJvSzWbOmRzSaTpOkYtDbLwXaHzUeSdNeCrIgZrIgJzIuSkIiZxInSfGmHrNeSiGjJkTeReMbPsZyCiShJzXqMvJgLgIpFpSqBuKrWpPnNePyTlJtHwWfYbFyWgXpBiLhLmFdBpJnOyWaMiJuEyEuEzFwXbUdHaHwGdDoDkNhTfZhXlIdIcIdHeSjFgFmTcGzVzIuCgAhJcDlYxXvUoWgAkScUuWzCrNlTiCxJyEcPbUkYeJuNhCjOeWiOwBaTkQqKvRfErLpRrStSnMySsKmOzRbWmEaHyFbDgNcEgIhDrTnIcWlJhHyJeAvRxFfBlShKiPhGfGeNfHlHzGaIsEkOuWfLsSdYgHcKcMpWsDlAiPdCqEzFhAvUwFeXwWqLdIrQcSjJcYvPrRtZoYyYhRbSaJcJbZdFhWkZlOtPgLbIpGuTeUfFaXiJwKpZhBzJeLqAiBwWtLiXjCeDcGfPtFgQiYzTyTgNcZuKrAhCnCjNqLqEjWgIzLxBqBtAsHrViKwTzSyFtMsVgTfYgDoZdBgVyOmDbFwHjVvLvJaSyMxIgWdOdPlZiJtCxLrJxQtLmPsDwFhUfYhXcCxHqJuLlCjMtRgDjMfAlElYzQnFyXzPeWrGbRvHkFmWnDbLaJeFtOpLvTnXsIaUoJsTyVeStZpJmTqNgBvYrTpOhXuQeXtHbRiSfNzSnMuUxGhVuFwLwQwWvVcTqEkEkXxLcZkAhUnXyPjBtCmDgVwAeRuLnVjJtUiKrYlDlGaKaCvXpWbRzFjMhNuEfJzTnNlWlGxUuGnVdSbKrWqItIeRnEjEhAdPkOyTiJpOiOzApJzCsIdPfGaAbYnPtRzZkBmZvLvRjDxJgWdBvGfXyNnQrXoMoKtQqJxXjGeFxRnWhRnMlMeYlPqWqNuDqBbBxRbZyBmMaVfXcIeFzBeSgGkNwAnZzXgUtEhUdQzMuOcUtWjVfDrKsLnXkQmFuCaXrGwGeWbZkQaBbLvRgLfZqJtNcQdEiRmYtYaZmIpOlLpJxMiMtUfNiSbOpVpPnLfXgVsFdFhYjEqLkVjWdGkHyGkEmHoJiQvNrYmPyNvQkZwNbFmJjXaOyNkNsHqJvEmYbAkJmKeVtUrShAhOrQvJzAtJiMyQaWyKnElCrAqOoVlVaEeRxHuOuKfTkHpUoFqIxLdAgFmZuYtKyPaYqDjErDnDhUcPvAgSsXjKvVpZaXeAhLkYaMcAuAdKwMiAmTfPsAkJzDoGhTbUxYrKpMeOfQjRnOeJtRsXzYiQrJqDqYvEhJsYkHjZtHsSvKjLoPzMaNnExXvDnEeXeHoNhPkLhSmEwEhNlTmLqBnCqBtQvWwLiApNsAgVlWuUsWnBzTvTaVfYzXpOrQpIiJvXnGxMeYjVaMdYlAmYrKaEoPmQyDaCyPjPzAjIyRhUzJqWnNeGrNnSeNrTtFkFoShElNvExWiNqEtVkJcXbAjEuJs");

        //request.setAttribute(Constants.SMSMOBILESTEMPLATE_KEY, smsMobilesTemplateForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"smsMobilesTemplate.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editSmsMobilesTemplate");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
