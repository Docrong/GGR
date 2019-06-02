package com.boco.eoms.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.message.model.SmsLog;
import com.boco.eoms.message.dao.SmsLogDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsLogDaoTest extends BaseDaoTestCase {
    private String smsLogId = new String("1");
    private SmsLogDao dao = null;

    public void setSmsLogDao(SmsLogDao dao) {
        this.dao = dao;
    }

    public void testAddSmsLog() throws Exception {
        SmsLog smsLog = new SmsLog();

        // set required fields

        java.lang.String content = "XeDjUlCvCsXtXxPiRvVtAkWxCuQsJnWoGlEjHpTvOgNvEqEyTrNhHfAsJjWqBrGqHmXkTeOqEyQmNmXxWnDkNoVrOhTmTqNzSjTjBzZgBkUvBeDyScHiKqYlRpCbCyXfEtLtHeFbQiBpOyVcXaTbFxHeHmBeZxOqTwQnIiAfZuJwQnApDiPwEpQlUvZxTsBgEiNwSoEhUfDqSxEqXwVtUnKpSdWwPiBsYpEpYwQeJgHcWyJnJkXkVdYdLxWcCyEtWhUlVzOeZpIoCjUiKtVdHvRkHxVrAoDpWbFaCdDjMuQjTyQlFyHkRlWvNyRdMzJzYrVlJeZeYvHjIbBjRcDhLcNyWuKqHoKwJaPyGgJkXqWgJkQfKfBiAfHaGmPwQaBgIzMyXfGdRwOkUySwTjQrRuAqSnMrRrPdPxYcMoBiVzQwEeKuKyJjRbKxOyOmYnXyMrSbFwBiYrDuSoLzSlKzFcPqUzFkMgLzFcDtNvUqYgNvAiSbVjZk";
        smsLog.setContent(content);        

        java.util.Date dispatchTime = new java.util.Date();
        smsLog.setDispatchTime(dispatchTime);        

        java.util.Date insertTime = new java.util.Date();
//        smsLog.setInsertTime(insertTime);        

        java.lang.String mobile = "VzQsXgZhNpAiJiEvHgShDrFuKkOuSeMbNeNyTmNdIzDwQqFmVsJyYlNbJrUsNwTaGuDdSyNtIjCnUiUfNzNtPdTkIlPdGoIwDrTf";
        smsLog.setMobile(mobile);        

        java.lang.String buizId = "GmXsKhAyAgVfBfSaIsUwNeXgQiLhOiLr";
//        smsLog.setBuizId(buizId);        
   

        java.lang.String monitorId = "AsWdRyUiKyEtNiIgWdZxQgYeHlYhAxVr";
//        smsLog.setMonitorId(monitorId);        

        java.lang.String reason = "DmOeJeEoZnZeJdMoHqLyKaIkArSwNoOvQdIrWrWfZhZvUvOzVqXyDlKsGiImHbVnMrArBjWaXoLhLgIfBcYrDkWwGtXeReYoYqMhHqEzUkHjQjGjIdFiQwOqPgEtIyFuChOoVwCaVmWcJcWtTtByQbNrHnVhAlTqTmJpQdToTvWkYkCuWeKyMvFfEbCgZdIpPhPwHmWpZcPlAoVjFkSjJgUgFyTuDaZiKgFpJuCuQyLpKvXhMvGuJoAtPcZdAtFqSpNvVqCaBsRrMfHfQgSwIhElMgBjPfXwSaChWwEbKhIdUnGnGeZzLpDkKtTmKbHvGjKuXsFoNjOsVsJrPdHgXbAlXhDoDrIgHxKsRwSiFwCmDoBsEpIqNhTcCvZiFoCkJnMzAhBpOmXtOhZbIvZjFjBqZdTuVnQfPlQyLsDzPtGnVuInKgZtDaUkLzPkBqXwRbYrDuHiJcZeMzKpTtAyIjEoZhXeIzCaEzCsIiDxTiXfVfTaMmEf";
//        smsLog.setReason(reason);        

        java.lang.String receiverId = "SuRpMzOeCyGrInNtPtFwHgPpFfYjUa";
        smsLog.setReceiverId(receiverId);        

        java.lang.String serviceId = "GlEyYvChHkWrUqScKrYzGbMtJtSfJkKg";
        smsLog.setServiceId(serviceId);        

        java.lang.String serviceName = "QpXkWjUiJrGsUhJjExLhHxZpMrYcOcIdNyAcHzPwGvNzCiGaGaRrPsNjThAfHeVjYbMfNuQmYjGdWcSkUlJjZyNpDaYyPcNhOgHl";
//        smsLog.setServiceName(serviceName);        

        java.lang.String success = "I";  

        dao.saveSmsLog(smsLog);

        // verify a primary key was assigned
        assertNotNull(smsLog.getId());

        // verify set fields are same after save
        assertEquals(content, smsLog.getContent());
        assertEquals(dispatchTime, smsLog.getDispatchTime());
        assertEquals(mobile, smsLog.getMobile());
//        assertEquals(buizId, smsLog.getBuizId());
//        assertEquals(monitorId, smsLog.getMonitorId());
//        assertEquals(reason, smsLog.getReason());
        assertEquals(receiverId, smsLog.getReceiverId());
        assertEquals(serviceId, smsLog.getServiceId());
//        assertEquals(serviceName, smsLog.getServiceName());
    }

    public void testGetSmsLog() throws Exception {
        SmsLog smsLog = dao.getSmsLog(smsLogId);
        assertNotNull(smsLog);
    }

    public void testGetSmsLogs() throws Exception {
        SmsLog smsLog = new SmsLog();

        List results = dao.getSmsLogs(smsLog);
        assertTrue(results.size() > 0);
    }

    public void testSaveSmsLog() throws Exception {
        SmsLog smsLog = dao.getSmsLog(smsLogId);

        // update required fields
        java.lang.String content = "KzQdChQkVkZcPuOnEpDwWfYhWdEiIrFfGcKaEyXdHjFuDdKfWpZmEnMlJySoNzMuGeFcIkXvYkRsPnJgTrZpKzXlXvTgPhOnMeWdJkPfTpKsEoPdDrGkJfNaCpFtKsEtKpVtWgEsVhPwRgUnRzGtNgRvSiJgJuZuEaWwIyYlZbGzBtFtKeYmPzBtBgJeTyEkFlStEsEmPcOdTuXgXjCqMmJeMmXhLnHqCdEyPoJkYxZpPdFqSnDpKgZwDmFhRyJpPsJqWhPdJaBgWiOhEfWsDuEnAkHrRyMtAkLmTtJtCeDqQuMkGkUsViIhIxKrMgFkVnDfMeJzCoOaFvUwVtNfTaOvVzUpDpOkLhZyHzWpEoAbDmGrExHkKfOhThTnHpDbLaNkJoBgNoCzZyFmRfBzYzTtWqQvTjOhScWqOpSmFyFfYwRlSqQnVaIzNrPnJbAhGvNwVoHxEoOvGiMiAxYwQqEdNvEiJsFaUzLnQeAvIhTrWpZvYyZx";
        smsLog.setContent(content);        
        java.util.Date dispatchTime = new java.util.Date();
        smsLog.setDispatchTime(dispatchTime);        
        java.util.Date insertTime = new java.util.Date();
//        smsLog.setInsertTime(insertTime);        
        java.lang.String mobile = "DcMcPpGkRkLhDzGqTpHzBvCiVtQdLlLoVdWtNaKyQrEwFdVpFnNpSyTsIpDvTzBaLySrPgInYcJbYeDuPnZoSoOtJfNuVwAfTbUa";
        smsLog.setMobile(mobile);        
        java.lang.String buizId = "VvLbWdPjXbFwOxCcNsReOcYvRyOpWtYq";
//        smsLog.setBuizId(buizId);       
        java.lang.String monitorId = "IzUwZaMyXdXjBmUlPaYsZmQkIzKwIwTt";
//        smsLog.setMonitorId(monitorId);        
        java.lang.String reason = "ZtZdFbShCaQlLoFhKoVyTtXwHwLzIeCtVpNhUnWxKfYuBzOyHcQoCwYkZnAlQbRxZgSzSwVdPcNwMwDgSlQxWjGsReJgLtAdPjMqQzZoLdPoRaIlEcAtQhXsUlHhVrLwJtOkOjLdJaTvHvZcIzVvNbEwJpOtSwZuApXqLiPaRuHyKgPtIkBtEgAgRrBsHxGzJiFaRlXhNdHqLjFpFfSbZuFaAkMeLqVbOhYlZpZfSkXnFtVeZoKnMiFgYoAyOrNyHzJyMsUjDfSdKmAuIjEtDaYuQmMiOpIlTiAyYgImBwJqFdRwIpKaHgCrKaSpDuFkJoFpOqIfErTiIhIpPhFjKxLnRkByKpNdCsDrQdHhDrXcWxJvMeTwKwPeAsFnOdHqTdUgTuUuIrRdBhPjVkFjOlUxRlJcVfEuWpMhAtCwXfKzRhGiLqTdIoElAgLkBsDdKbKrWxWpYiUvBkYuQcPaRqEqMaRdWtDgEnQiPlFyByBbErEsXiUv";
        
        java.lang.String receiverId = "KfHcSfNhSwZiAcCvTcDiZxQxDgSeEr";
        smsLog.setReceiverId(receiverId);        
        java.lang.String serviceId = "XwBoSeXmQeTkUzSoCwHjUeTjNpTrSwCn";
        smsLog.setServiceId(serviceId);        
        java.lang.String serviceName = "VsRoWgJlGmFmGgUsWnEbMrVwOpOdSnKnKkDtNeQqUtXoLyUnPcJyScPkLbTeXdYxQuQbQcFcPzWcXdPaJaRbKxGbDqWkVxQlOxHc";
//        smsLog.setServiceName(serviceName);  
        dao.saveSmsLog(smsLog);

        assertEquals(content, smsLog.getContent());
        assertEquals(dispatchTime, smsLog.getDispatchTime());
        assertEquals(mobile, smsLog.getMobile());
//        assertEquals(buizId, smsLog.getBuizId());
//        assertEquals(monitorId, smsLog.getMonitorId());
        assertEquals(receiverId, smsLog.getReceiverId());
        assertEquals(serviceId, smsLog.getServiceId());
    }

    public void testRemoveSmsLog() throws Exception {
        String removeId = new String("3");
        dao.removeSmsLog(removeId);
        try {
            dao.getSmsLog(removeId);
            fail("smsLog found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
