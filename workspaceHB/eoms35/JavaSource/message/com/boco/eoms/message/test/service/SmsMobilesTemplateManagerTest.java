
package com.boco.eoms.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.message.dao.ISmsMobilesTemplateDao;
import com.boco.eoms.message.mgr.impl.SmsMobilesTemplateManagerImpl;
import com.boco.eoms.message.model.SmsMobilesTemplate;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsMobilesTemplateManagerTest extends BaseManagerTestCase {
    private final String smsMobilesTemplateId = "1";
    private SmsMobilesTemplateManagerImpl smsMobilesTemplateManager = new SmsMobilesTemplateManagerImpl();
    private Mock smsMobilesTemplateDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        smsMobilesTemplateDao = new Mock(ISmsMobilesTemplateDao.class);
        smsMobilesTemplateManager.setSmsMobilesTemplateDao((ISmsMobilesTemplateDao) smsMobilesTemplateDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        smsMobilesTemplateManager = null;
    }

    public void testGetSmsMobilesTemplates() throws Exception {
        List results = new ArrayList();
        SmsMobilesTemplate smsMobilesTemplate = new SmsMobilesTemplate();
        results.add(smsMobilesTemplate);

        // set expected behavior on dao
        smsMobilesTemplateDao.expects(once()).method("getSmsMobilesTemplates")
            .will(returnValue(results));

        List smsMobilesTemplates = smsMobilesTemplateManager.getSmsMobilesTemplates(null);
        assertTrue(smsMobilesTemplates.size() == 1);
        smsMobilesTemplateDao.verify();
    }

    public void testGetSmsMobilesTemplate() throws Exception {
        // set expected behavior on dao
        smsMobilesTemplateDao.expects(once()).method("getSmsMobilesTemplate")
            .will(returnValue(new SmsMobilesTemplate()));
        SmsMobilesTemplate smsMobilesTemplate = smsMobilesTemplateManager.getSmsMobilesTemplate(smsMobilesTemplateId);
        assertTrue(smsMobilesTemplate != null);
        smsMobilesTemplateDao.verify();
    }

    public void testSaveSmsMobilesTemplate() throws Exception {
        SmsMobilesTemplate smsMobilesTemplate = new SmsMobilesTemplate();

        // set expected behavior on dao
        smsMobilesTemplateDao.expects(once()).method("saveSmsMobilesTemplate")
            .with(same(smsMobilesTemplate)).isVoid();

        smsMobilesTemplateManager.saveSmsMobilesTemplate(smsMobilesTemplate);
        smsMobilesTemplateDao.verify();
    }

    public void testAddAndRemoveSmsMobilesTemplate() throws Exception {
        SmsMobilesTemplate smsMobilesTemplate = new SmsMobilesTemplate();

        // set required fields
        smsMobilesTemplate.setMobiles("TcUeBqYvJsOpEvWwRaIlFhQgYqQjXkXlQoDfMqVlRhWbVfZwNjImQsNzGfTaUzYnUgCrZiMyRmErRoLcHvMmLkZaXiGzReTlIrBaXlItHpOxIpKrDoWvJnZaDiKtGbAbEhLkYwRwJgXbDlNxLjRhRnXjWrXaZrApGoYsKsFaJyLxIiDiWeNgMpJbUmTbUgMeBdOkGiBhDhBlPoCxGySpQrIvAcMbItXuOjGaOvSrFeIgWvXsKyArZeGoRfRzHvLqEfPrJrVeXmMtHmRuMyIjMnUsBvOqEwMgDyWwOnMdGvSwPnVyDxFoYnMkTxAuBjVjAsJsBpNlOpVzPiSjOxRcUiXlGxUyWnCxNuSbIxEoUkDaYcRxZhOjGgTrNwVvSaJyPpWhCyDkVlHvCdDaKxPuMfSoGlDpOgXiSmAzHsOnCnUySrHwKxLfMgWeUnRaDoKhKzTbGrRsKzPiEvQmLmDnQgBdZuNdCwGhDoTdHkGpEaToGuBiGdQrZaCePrItKoCjHbMdGbQuQkSkFkBhLyXyMqDoPpBuYnXkOxEaOlXaRdMdEuFmPfRhQvTbElXhPpVxKiAyNzHqMnFtKgXrJkLdHwSbJcOcDaRuYvZoOiSjXqDwItJwVgHhVrMxUpLoVkPuZxCqGwZhOrNhNcScJcSeHxPnQdUnAuUrKdXwYtTeQzBoUwThKnUgGrRhUqEtBaQaNkNxHzXuUaMxAfKwRiMuLgFbOkBgJfDeCjEwLwKuCqFjQjVcSnMtZcZtGaBdZvLgIiWqYpCoPrReWsRzMvSbPfRrFoPqDoUvDaQaQiWqTxLjXxNeGfAaHoNqCeBkBxOkUcOnYcCcOwXvLnOnWvYaEzMwKmVaKlOjNnLwElJhJjBgNnRiEcXoNeMpBvSrJbTiXhMpJbHuMaNpLqQmRdSiDwWlPgWhKtBhYoJvInUvSzBwEzOeMjXwApJkHbKvIbLoPsKgPqImGbXuTgQlLkZzAiBzTlXrFuJnUcErEtVrSbTdFsDpTfIaBlPmOmTlYrGwGpElBmKaSjTyLxRcJiPvOfVqJgTeVpKaAsZjInQeFsXjQeOoDtMkVfRnBjEiLxFxWvKsGzXdPfQlQtRbApNlZaXrGpYmYaHrDpXpXpDfUtLgWxKrHjFjCmTjEvCcIgIaUwPpSnQwMoHhHmXiMgKnMuGhNiLlBwSqWrDmNdIzWaPgTqLwEuCsJeBjSbOyDpWtVgWlUkEjOpCsJrHeEtZvNnPaLlXsCaKeLyOgZbVnOkLlMoQbAgArQoEiKiLhMpWlXmZaNiMoSoLsMzVqQdJoMdTsJcCpOrNhDmCvLyGmKcHbJkUuFqMgLpOxFfZjAuNeFhDbUfFsIcDxZaYfOfOePtExRwHnIlCjJsEnBlOsOuAdZsYzIrAaPwWmXvCzCsMbXsAdXoBcEjVaTvAmUrUdBwCqPvTtPnJuOkBtCeOwMqQgUtPjVcTvEyKqEwZbWzJeDiNdTpFuIeKqDxTwZgYgPjHaGwAbSwFsDzFoUfFgXqOfFyKkBsMhLkOyLtEjEpGcYmYzPhTdFeJvWiLgRxArRuByIpKiEiNoErVjDwCfTpAoPwItFqKqTbEpTzIcKdUpEgKiAtCnGzHvBwPoDaNjImBtGtKiMuUuRmFrAdUbAlMiXjDbFnKfLaXyVzJsJpYfGtKbUcJyBjHpWjEkKlTtWsKxYeAgBkEpEqBpVrInUiFqVxWlOmJgRaRjTpAiJzDcHeCeTwVvAhEsQrBzXsIlBwFlRbGkEqIjSkBpQcMmZsInQnEjGnTlMkEsBxDpVcWlWcShWzFmPgDrUvNiSfPyYoZzFcLxVnJnVqDdYaIqHpQhZdSbSmXrPjCcPsEgNxYhXmYjYaEyUkBxNiLkEiZeQwZcKqUgPsQdLoXgRhPfFrRnYpOlUhJpIcOnHlPuFpZcTzNhQkWkLmVdYfAkXqKdIjRbIsOdTtXnRdEiXbYmVnIjVxRnKePiLxTlGjEdSwUnXyIoJjYeYhXhXeVpVzStFlCoClSvWeXaWgDtCbPnToDoFhGgUqYcFlZrQxOfRxVyYrCaFiKaBrOyTvMyVpDhIgIfUzCeZqOnLrBlRqPuAoUvQlDzDuMxGuKrLoNuSpLdMpPjTySbBqKdPuXtWmMcMcWlIvCzHePcSkKpDaFxBkLoTtGjPlBgHrEnNfNzYvNzEhEaAfJhNqUwTwWiViWkEkZxMtUuIeVwYtLkViKfLoFpAeYtEhZpInAmKhZyIkQdKuBnNtCgCqDyCcFpJuVsOzDgPhOnNsQlFmOnZxFkBbKwTsNdSyFhIuOoHtTmGjTrIrYjWxOvRhJnRdKwWpVtEdNuQnHcLqFqGaTxMjDiSuFwYjKmRfWyFzVfYnLwNfJpTfErAvNrDdKrQfXiKrVuAvGdDpBfLsEgLnVkYuOlGkVnHlXrCjRhUuNeOkBgHtYuBoGqWtIaFrMgHjOvLmJrLvZwYpFuTvXmPeBlKvFrJuBsXnIzNyIvOiMtQiBpBrNiBvGmKbJhMpMmXpYoBxIcYwEeEvBvFaVwBdNoKwLxObQbJtKvXgThVhBwZnCbJhYlAkGxLoKuJyNkQzYoPqZqFxMlHzCyJvArKxJgWiTrApPlQzWiLjWoJcSlNqDvWbHaIxRtQlYhUdHxLxAnPdSdVbHdSgMaGhMtLjJlWpDzLnVrXxUbPhNdLyCeHyCtFlIeToTtJqWuHjJaWfNhWaGjNzAdAlKaIfObEbGiTjMrDoGhKfWtXdKwSdSfImDzXiWeZcVdPvMbWmRvGfLtGzBxQwPqRiPlIfKkPbGvWrBjUgLbPtYtZjZqDdFtLcBpFwFvTdZhUsFjVjAyGbJgMcXoLsTtXoObGyHcSlFlObVyIiXrUsLvHrGwYsWpJuTfCaNgUgDkWcAcTyGkCsIkGzAdUjPcUxEqIhDnHjItBxXgZnOmErRvDnYjIdWdKuQwCiHoMbSdCaMmMlXzFhVsBuVdBvWnYnYmRjWwYaJdJhWrBaDbTzIiQyCyPiWq");

        // set expected behavior on dao
        smsMobilesTemplateDao.expects(once()).method("saveSmsMobilesTemplate")
            .with(same(smsMobilesTemplate)).isVoid();
        smsMobilesTemplateManager.saveSmsMobilesTemplate(smsMobilesTemplate);
        smsMobilesTemplateDao.verify();

        // reset expectations
        smsMobilesTemplateDao.reset();

        smsMobilesTemplateDao.expects(once()).method("removeSmsMobilesTemplate").with(eq(new String(smsMobilesTemplateId)));
        smsMobilesTemplateManager.removeSmsMobilesTemplate(smsMobilesTemplateId);
        smsMobilesTemplateDao.verify();

        // reset expectations
        smsMobilesTemplateDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(SmsMobilesTemplate.class, smsMobilesTemplate.getId());
        smsMobilesTemplateDao.expects(once()).method("removeSmsMobilesTemplate").isVoid();
        smsMobilesTemplateDao.expects(once()).method("getSmsMobilesTemplate").will(throwException(ex));
        smsMobilesTemplateManager.removeSmsMobilesTemplate(smsMobilesTemplateId);
        try {
            smsMobilesTemplateManager.getSmsMobilesTemplate(smsMobilesTemplateId);
            fail("SmsMobilesTemplate with identifier '" + smsMobilesTemplateId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        smsMobilesTemplateDao.verify();
    }
}
