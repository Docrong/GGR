
package com.boco.eoms.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.message.dao.ISmsContentTemplateDao;
import com.boco.eoms.message.mgr.impl.SmsContentTemplateManagerImpl;
import com.boco.eoms.message.model.SmsContentTemplate;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsContentTemplateManagerTest extends BaseManagerTestCase {
    private final String smsContentTemplateId = "1";
    private SmsContentTemplateManagerImpl smsContentTemplateManager = new SmsContentTemplateManagerImpl();
    private Mock smsContentTemplateDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        smsContentTemplateDao = new Mock(ISmsContentTemplateDao.class);
        smsContentTemplateManager.setSmsContentTemplateDao((ISmsContentTemplateDao) smsContentTemplateDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        smsContentTemplateManager = null;
    }

    public void testGetSmsContentTemplates() throws Exception {
        List results = new ArrayList();
        SmsContentTemplate smsContentTemplate = new SmsContentTemplate();
        results.add(smsContentTemplate);

        // set expected behavior on dao
        smsContentTemplateDao.expects(once()).method("getSmsContentTemplates")
            .will(returnValue(results));

        List smsContentTemplates = smsContentTemplateManager.getSmsContentTemplates(null);
        assertTrue(smsContentTemplates.size() == 1);
        smsContentTemplateDao.verify();
    }

    public void testGetSmsContentTemplate() throws Exception {
        // set expected behavior on dao
        smsContentTemplateDao.expects(once()).method("getSmsContentTemplate")
            .will(returnValue(new SmsContentTemplate()));
        SmsContentTemplate smsContentTemplate = smsContentTemplateManager.getSmsContentTemplate(smsContentTemplateId);
        assertTrue(smsContentTemplate != null);
        smsContentTemplateDao.verify();
    }

    public void testSaveSmsContentTemplate() throws Exception {
        SmsContentTemplate smsContentTemplate = new SmsContentTemplate();

        // set expected behavior on dao
        smsContentTemplateDao.expects(once()).method("saveSmsContentTemplate")
            .with(same(smsContentTemplate)).isVoid();

        smsContentTemplateManager.saveSmsContentTemplate(smsContentTemplate);
        smsContentTemplateDao.verify();
    }

    public void testAddAndRemoveSmsContentTemplate() throws Exception {
        SmsContentTemplate smsContentTemplate = new SmsContentTemplate();

        // set required fields
        smsContentTemplate.setContent("AbGaSrCqMqLkBtWpLkOcLmSdUfEbQsIqMxKoDcCnUeZwAxNnErHlZmEkJyQxNcCpSkVeNlBmLcYaHnIgXuInKfAeNpUqAvSsEbEwKsCrKuBnViDdOzWzVzSnIoUqYeRfBgVdEeWuPtZnSyUrSyYnWeOnQgQfEmNcLjTcVeQxJmRxLpTiPoNzOgZxBxSdDhSqTnTwHzVfOjDrWdHpVfUcTtAcYvQbTmTtEuTcSgRgRaIqZmMbAzPbNtFpVyIkKrOdUwNaDkZgHwNiEeWzWeJoClRiUdJjLxZxLoOkKkQxUyOeWcFwJlXaWcFqLlPgPtUoKzCzLuQbFvPyXrHoIqUeMbBbVeUzAhTwRqScKwCcOaFmWbIkBfZrPhPvZgAzEsViEwVmPkZeEnJoBwJsMzJePzLeGuReJxBsPuQmGeGxJnEbDvBmHtYnQfPjEiHhEmHyAbFkRzLjMmFlRzQiMpZiTlFlQqDlMaDeIdAaRdHzAlVlFyCrCbSpSqIuCjQyKjPbGxIqHtNiAnDyOmViHxVwNxSkEpGiQjPjJfKzOmEjWuPfCjDdNxTgPqMuMuNqGxRzOzIuVtZpCdOgPjKuYyUdAgKyPaMbGcIrQjRhZuAeUvLpEsHjKgOvZxWsNgBsWoFsDfZbJgReSuSdOlElHhZoTaSfEcGpXiUhOxRgJzRnIoLkKnVcSjCrObVrCsRzEsHmPgGvUzUmKeDvGqOqFtLgWvKlTzNbOkRgZqQjGqEfOrIrGqLzHdMrAtXfQxMoWcCuCrWsThQxJmHtPeOzPfLvYnCpXgFwKaJcHvVeQbLoHfYqTjAhAmIdEbGyGnWlLzCxGjQaSpAwLsBfMzVfJiDgFoWgWaXfBpMqMsXxSsPfXmVfMuCyYmLqYwGoUkCnGoYwJtHwYpXhEyIiYyOaPnCqMeUiNhCbJpLvCsPkDpRsQoUdScHbBtHrGyAlUdUhOeSfFbUtRlUtVpKtEeTrJiVwFwJyOiDwSuZlFsHsYgDpJoHkGwZuPqOoIkWnLxJoMmQsIpWjAgIvMrQkNeZyXtGoNjEaIuIgTlQwSiIdOhEzSuEhTgSeVoPdNvZcXcUgCzNdVcSjAeWiXgYiOjTvSnJsFfJhCbSyStJbBdTsYoQiGgCcLjVwJiBnIrUlAnXjMcHcNyKfTuXsVkGlZdBiIhPpXwBzXpPmHyZaKeTgMlEiGpZtInXzXjJbPoQwNoZkRoOwZlHzEkSaRwGhXgAhSaLmWbDnQcAzRuTwQwEyCeArXuRxHmLsLxGwMkFfZpLnUpDzMeWzKfWsWbOxPcGbXfBjHcNlCwFyOrXjOrChGaLsHcLjWuEgFoHuIwDvYeBbZjOgDeZsQsSiRjRlTyOjJeQwBzXbBdHbJyEzOdJrBrEkLlMdNbTqWfNeWmLgVjWlNaRkWhNoBwVjKqEdIhJdViAyZyDjDdTyZfJkAfCjEoFyJuMcPfGiGpUnIdGbCdKxTeAvKlApBmVzFgKzTbHdKnIqVmUrBsVxArTmEpEeYyArAhCuHuRcRlLjDdAnCiJjHdNnJxXgFlPwKnAmWxOwOfFxEvTmKfGuShTzAyXhKkHuKrNfUeOxVxUbHqKqHgBzUdArJoJkGbYxFvKpVpUjFxTmOeSiAdQsBdJgQfKaFyJcOlAoEyHmXnPoGwNpGvChHrPpWgRtCeEaAcIgMdHvSgQkQuSkQeDwTrVgZxFnXkXwReMdOeZgKxUcYkQuOxTpYsKkIaTyAsPcFyKlAeQpYmAdWqPmEpWiGbZaBjVuRtGqZwWxDqRcMrTeWlRmXlOdGdYzFhTcNqYkLbPsGeIuGnTbZiRnTnJsWiYuUbEePgYwXdWnBcCcTaZcZeQsGzVmCuTlCiOvLnCvLhXcNtCcMnCkPoErOcJdSuMlHwFhNsMuAoGxQqAjKtRxZyIwQsHpOyVeMkHvYtLfDfRzVbTsDvGaOkSyAyDxSfSnQgKxFbRfEwRfHyZsEtDdBoSrKyJoFnFpBeYrPqOmSeHrUwIwIpUbUpAxWiBfTfPyBtIlIgCjKjXkOlOpMbJrDrNwChAgZiQuRvSsKyOhQqPhQyZwTvHyLrGqZmEbQkJsYdAcKuWlAbNfFwGtKlOhVyUzNxBfHkUlGkUvTzVrPoPpJjHqUqUxUmRjRySaDwDwXpViZyYqSdMwEhQdRkUrLiJfVwTlAiCnAgQbOaJeQnUsSvVsNtIaLfDjLpPzXdMbRhPuQaWwJyNvRlElRpGeAlMaPlHcMiCdHzSqGlMeFdUwCtTwVrOuExOqGiAuUvVlGhKcVpBjJqTpFcPgSbClJtEePgMjOvAeScXnTdFbBgWhUuHzFvFzDqOsLyYkNqCmXdDpFxNuLcHnNgNlRpTjFyYfEsCeVnKjHyBjOuXwJiNxWxHuDbNmTtXnTrQgAvGsHaVcMbIqToOnUnYhJxOsZeHoAfMbFrLsEiOdVzTxVmWbGuTqTuBzVdDaImDgHiTkGaGvKiEkZpAxNgSyMyGbBgCaIlEvNmTnAmVbHsFvOdHnVdJiDjWwOsXdYcAqMeBoCmQwZlTaFoRfOyEmLfMlTbHtVrEkBjQdUqXhJmUfEyZaHvYwGhKvOlVlToOnWrInNeXjIwPwCwMxHmQnSaMgHtJxRzWqVeVpWyWtDuHkWjJdIhEeYmRuXsPyOjQxAtTvPrMaTzImWfZcMmXdQuJyTdVpTpDaRlZpCfMvMdNgJqUbPuXaBqCsDsZxQuYgRfGxXpPwWpGgHxWnMjVkMhVuLuWmIeGpBuBdUuRhYoUaAkVlVrVsKmWfIoYqYeVmWaVbMyZhNbAjUbLdByVmOzRuMmTqNaCnHdCjSzAyYtJyWsBbRqZvTzLlCtJfBdNoYpIaYhHnXmYnLaCbQkKdGeGfEkKiYkAaNyOiFnLnRkBgMlMsAbFcAsFyQgRhGzXkObTvKcSpMeUpZoZdDwRtWoCtXkZvZyZeRpXuGmOhDnWuGnAwRfVdDdYhSwDwNwWqTpWqOaLdCxBoWbKzEkZwZeIvDkHc");

        // set expected behavior on dao
        smsContentTemplateDao.expects(once()).method("saveSmsContentTemplate")
            .with(same(smsContentTemplate)).isVoid();
        smsContentTemplateManager.saveSmsContentTemplate(smsContentTemplate);
        smsContentTemplateDao.verify();

        // reset expectations
        smsContentTemplateDao.reset();

        smsContentTemplateDao.expects(once()).method("removeSmsContentTemplate").with(eq(new String(smsContentTemplateId)));
        smsContentTemplateManager.removeSmsContentTemplate(smsContentTemplateId);
        smsContentTemplateDao.verify();

        // reset expectations
        smsContentTemplateDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(SmsContentTemplate.class, smsContentTemplate.getId());
        smsContentTemplateDao.expects(once()).method("removeSmsContentTemplate").isVoid();
        smsContentTemplateDao.expects(once()).method("getSmsContentTemplate").will(throwException(ex));
        smsContentTemplateManager.removeSmsContentTemplate(smsContentTemplateId);
        try {
            smsContentTemplateManager.getSmsContentTemplate(smsContentTemplateId);
            fail("SmsContentTemplate with identifier '" + smsContentTemplateId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        smsContentTemplateDao.verify();
    }
}
