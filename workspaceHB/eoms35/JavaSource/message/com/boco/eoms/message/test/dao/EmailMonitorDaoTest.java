package com.boco.eoms.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.message.model.EmailMonitor;
import com.boco.eoms.message.dao.IEmailMonitorDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class EmailMonitorDaoTest extends BaseDaoTestCase {
    private String emailMonitorId = new String("1");
    private IEmailMonitorDao dao = null;

    public void setEmailMonitorDao(IEmailMonitorDao dao) {
        this.dao = dao;
    }

    public void testAddEmailMonitor() throws Exception {
        EmailMonitor emailMonitor = new EmailMonitor();

        // set required fields

        java.lang.String applyId = "SpLlUeEqXgBoOzUbAkEwCfAyJtEhMnEp";
        emailMonitor.setApplyId(applyId);        

        java.lang.String content = "TaTfFtYhYgEaSrTzAuLoBbDwAqLmJyQvJzQxZqUwYmSrOcUeNpPbMiAjIsDtPoSuNiXuYhEiIbWfVsPvVmNrTgEbIjCfTtZcRyPpNqKiEkHkVpBkCuNnNaPbJoUuIpDpMgByKlFbUdDaEcAyXoZyTeRvVoGaNyMxPmBpIaLqNcKsQmReLhCbFoSsIiLgGxSlQiFfDiYoXoHiHdYiJsCqCmDnQqYzHpLrMcQkDcVkAtYmWtNnKzGdFzWjNvVrZvEiXtXxNiYxZrOjKzMmAuUfToWyAuSlWoFkFlIaMeOdZbNrFaCbFdHbFwYpYyCkScNvLeElAqJdHqZzWcUqVaGlSyYiOtDzSxVuNuMcIcPkOtMfPcLbWqVuHpYkOhKoEmZeYdCjKsRgLzCmDxVpCqWlOyZoLeYmCnUdKoOrXyQuQfNcHkPkFsKgYfUjQrUbGjPfYpVdExLlRaTvIgCwRmXoOaXyTeLeLpWjEwVmMiVwMyPlHnSrKmLc";
        emailMonitor.setContent(content);        

        java.util.Date dispatchTime = new java.util.Date();
        emailMonitor.setDispatchTime(dispatchTime);        

        java.lang.String email = "email1604385387@dev.java.net";
        emailMonitor.setAddressee(email);        

        java.lang.String receiverId = "PbRyTySzCcOxLhJtBdUzMwNlXlQiNi";
        emailMonitor.setReceiverId(receiverId);        

        dao.saveEmailMonitor(emailMonitor);

        // verify a primary key was assigned
        assertNotNull(emailMonitor.getId());

        // verify set fields are same after save
        assertEquals(applyId, emailMonitor.getApplyId());
        assertEquals(content, emailMonitor.getContent());
        assertEquals(dispatchTime, emailMonitor.getDispatchTime());
        assertEquals(email, emailMonitor.getAddressee());
        assertEquals(receiverId, emailMonitor.getReceiverId());
    }

    public void testGetEmailMonitor() throws Exception {
        EmailMonitor emailMonitor = dao.getEmailMonitor(emailMonitorId);
        assertNotNull(emailMonitor);
    }

    public void testGetEmailMonitors() throws Exception {
        EmailMonitor emailMonitor = new EmailMonitor();

        List results = dao.getEmailMonitors(emailMonitor);
        assertTrue(results.size() > 0);
    }

    public void testSaveEmailMonitor() throws Exception {
        EmailMonitor emailMonitor = dao.getEmailMonitor(emailMonitorId);

        // update required fields
        java.lang.String applyId = "OxMsLtArQoDxFwBdVgYrKuBoMnVqFfHw";
        emailMonitor.setApplyId(applyId);        
        java.lang.String content = "YgHxBaJaDcTqMuCvXtUxQnDiToEzVaFpQdXhReJvYaNqDqGxSuJqFaXeOiYuBdMdHmVpWqHiZzLpCeYdQhGjJzApJcJpEdIiIkUdTnChLiCiHtTpUjGdHtPqGlFvAsSpNgMpEuOgThAlHlZcTiPiOpJpYiIzDvEiSqWePgRhBfOrSeUuLcBcWwKnKnKqWaTuAfLaSgFvXaOhRiGiOtNpAsTlSiBgCgKcGyWpUfSuDbRqHpXtTiOyHwMmZhCuArQtHkUsYtPuZxUaUyJdSxQwUvXiSnCvExGvBvZnSuNcPxRzRxLgRyGfGeGcIxEtWhOcRlSwBdCcOnUrCeEiDtIhCdEdWwDgNuLzRkHwKeQvXbSkOjImXmBnQlZoCdPfSrFtSqBvBvMxGuJoXaDwBbYnNmWkTbQsCwHhIyKdUvHoCdRyMyGrYqUdRrJpFuPfVeIqBoCwTcQfSeRwNsXtDyNeAzDxZaDeXdPiTvYcQpBsVkLbYnMzFiQw";
        emailMonitor.setContent(content);        
        java.util.Date dispatchTime = new java.util.Date();
        emailMonitor.setDispatchTime(dispatchTime);        
        java.lang.String email = "email2075958184@dev.java.net";
        emailMonitor.setAddressee(email);        
        java.lang.String receiverId = "TzDbVnTiVjNjCsZwEzRwVsMeDyRhUi";
        emailMonitor.setReceiverId(receiverId);        

        dao.saveEmailMonitor(emailMonitor);

        assertEquals(applyId, emailMonitor.getApplyId());
        assertEquals(content, emailMonitor.getContent());
        assertEquals(dispatchTime, emailMonitor.getDispatchTime());
        assertEquals(email, emailMonitor.getAddressee());
        assertEquals(receiverId, emailMonitor.getReceiverId());
    }

    public void testRemoveEmailMonitor() throws Exception {
        String removeId = new String("3");
        dao.removeEmailMonitor(removeId);
        try {
            dao.getEmailMonitor(removeId);
            fail("emailMonitor found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
