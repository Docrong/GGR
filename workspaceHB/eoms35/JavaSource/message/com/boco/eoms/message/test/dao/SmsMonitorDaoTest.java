package com.boco.eoms.message.test.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.duty.service.ITawRmAssignworkManager;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.model.SmsMonitorBak;
import com.boco.eoms.message.dao.SmsMonitorDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsMonitorDaoTest extends BaseDaoTestCase {
    private String smsMonitorId = new String("1");
    private SmsMonitorDao dao = null;
//    private ITawRmAssignworkManager manager = null;
//
//    public void setManager(ITawRmAssignworkManager manager) {
//		this.manager = manager;
//	}

//	public void setSmsMonitorDao(SmsMonitorDao dao) {
//        this.dao = dao;
//    }
//
//    public void testSendMsg() throws Exception{
//    	String serviceId = "8aa081f92065b641012065e45b670006"; 
//    	String msg = "454545";
//    	String buizId = "22";
//    	String orgIds = "1,liqiuye#";
//		Date currentTime = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		String dateString = formatter.format(currentTime);
//		
//    	dao.sendMsg(serviceId, msg, buizId, orgIds, dateString);
//    	System.out.println("---------------------- dateString = "+dateString);
//    	SmsMonitorBak smsMonitorBak = dao.getSmsMonitorBakByServiceId(serviceId);
//    	assertNotNull(smsMonitorBak);
//    	assertEquals(serviceId, smsMonitorBak.getServiceId());
////    	assertEquals(msg, smsMonitorBak.getContent());
//    	dao.removeSmsMonitorBak(smsMonitorBak.getId());
//    }
    public void testIsDuty(){
    	ITawRmAssignworkManager tawRmAssignworkManager = (ITawRmAssignworkManager) ApplicationContextHolder.getInstance().getBean("ItawRmAssignworkManager");
    	String userId = "liqiuye";
    		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String date = "2009-03-15 07:00:00";
		assertTrue(tawRmAssignworkManager.isDuty(userId, date));
    }
    /*
    public void testAddSmsMonitor() throws Exception {
        SmsMonitor smsMonitor = new SmsMonitor();

        // set required fields

        java.lang.String applyId = "GaUwGdJmXdKoJvTbLaQcPqNrJrGpSwWl";
        smsMonitor.setApplyId(applyId);        

        java.lang.String content = "VjGjCuUoVnOsRwChXmOgKuQkUyKjXqDyUoAmCyHiXzHmPfEhLoBtKbExVtRlDbEmBvHgSiYpBaLjOvDeCuBvNkIlJwIiSeOkFmBaRqYvVpIlMdUaIgNsQfWbGrCaHoTxIyCpRjYwWaIsZyJkUmMnBvEsYmToSqLcOyTcLyRzRdTgPhLeHbHsAlQbEiFdYmGoSdPrYiAeLkZgLvHoZdTmPcEgBxPmCkNbWuNrXoEoDtSqVfKnUmDlUbDdGvYjAiLkTzZaNsDhZkUiOzEkGkVrAjIsUdQhYdQvXtSsMeIpCvSvAlYoIpRyQpGrJvUsLfPgJqGyHaPxKeCrZaHqTfIfTaWhWdAjOiOcJpFxNvJkUxWlCqNxLkWiOvHrGmLoEvVfQvKiYiExKqUbHsNqEwUdLvVcKdTgZbBfOhPdCyHuNdMzUvNfPvFnVbLqPuZxZvUqWaAfYaThHuLcXiRyMfDbXbGaWpXaDlMyKdTmApKcVeKmQmIrBzIt";
        smsMonitor.setContent(content);        

        java.util.Date dispatchTime = new java.util.Date();
        smsMonitor.setDispatchTime(dispatchTime);        

        java.lang.String mobile = "WcIzXnIkKuQlIrPjJgNwWwXsZpXcXvBwEtViZoDeSrDgHzDlOmMpDoHeYtXtUfIkFrFxJqVsRnXcGhZeVzPpLkRxIbCcYfKiEsZg";
        smsMonitor.setMobile(mobile);        

        java.lang.String receiverId = "RpEbZfSsBiGaRiMhTqHhMlEuLiGnGh";
        smsMonitor.setReceiverId(receiverId);        

        dao.saveSmsMonitor(smsMonitor);

        // verify a primary key was assigned
        assertNotNull(smsMonitor.getId());

        // verify set fields are same after save
        assertEquals(applyId, smsMonitor.getApplyId());
        assertEquals(content, smsMonitor.getContent());
        assertEquals(dispatchTime, smsMonitor.getDispatchTime());
        assertEquals(mobile, smsMonitor.getMobile());
        assertEquals(receiverId, smsMonitor.getReceiverId());
    }

    public void testGetSmsMonitor() throws Exception {
        SmsMonitor smsMonitor = dao.getSmsMonitor(smsMonitorId);
        assertNotNull(smsMonitor);
    }

    public void testGetSmsMonitors() throws Exception {
        SmsMonitor smsMonitor = new SmsMonitor();

        List results = dao.getSmsMonitors(smsMonitor);
        assertTrue(results.size() > 0);
    }

    public void testSaveSmsMonitor() throws Exception {
        SmsMonitor smsMonitor = dao.getSmsMonitor(smsMonitorId);

        // update required fields
        java.lang.String applyId = "LyVhIjPeMaToRzKaIqQlFkIbIgBvQqWv";
        smsMonitor.setApplyId(applyId);        
        java.lang.String content = "RtXeAtSvOhVkCaNsSnVyDlSmLpDvHmJrZoQzXiLhJlTsKbMmPsHtMiVxFrVjGjXdMcQsNnEzHaXoLzRqJpFgVpIsUhNxNvQsTaNtBrXdVxSjKbTqKwLvJtBeJaPvBuVxDmRqTrZuArUdIpXhVoHqWlMkJvMpYqWsYtRjGfAsZyTzWsZgGfFfVlLsRgQsTuTzZxRtJzZdXgYxMnBmEqDwTgOcBqGfFkLoNfKjEfXzCvNuLnDpKkRpRoRxZzVfFjGvGmYgBoEdKxVxQbCqOcAhGlYzPjFbVoHtRpGjIaWbZdZdJkBvJlSxQeMtDlThWiMsPlLwWkRxWiRyCrUbOwSgAeEyElTbOgRvCfTcGsIhMlJkEiNxLgBpAcRbRaJxIbXsKoNeUbLzTrNmZsWyQbFzVgUsUwInBxEeLlLqCsKkWzFmZaBxXkDoZjXpExGcNyGfFwCmXiMlDxAyQeQrNuNbAlZdPqEnZxRlUdTlXoHsDiCpVnYcNrZr";
        smsMonitor.setContent(content);        
        java.util.Date dispatchTime = new java.util.Date();
        smsMonitor.setDispatchTime(dispatchTime);        
        java.lang.String mobile = "YzOfOsEfEgKrGhIiAvMxDtLuFeFaQsVgAjYdKsKyNhCqApUtAkTiPxPtFzYcAvBoApXbOgFbKrIdOmPhAsWwGfKrXrFcNuGxNdJd";
        smsMonitor.setMobile(mobile);        
        java.lang.String receiverId = "AjTtYhKfDuMmQwGdCwFqKeMcHcHzMd";
        smsMonitor.setReceiverId(receiverId);        

        dao.saveSmsMonitor(smsMonitor);

        assertEquals(applyId, smsMonitor.getApplyId());
        assertEquals(content, smsMonitor.getContent());
        assertEquals(dispatchTime, smsMonitor.getDispatchTime());
        assertEquals(mobile, smsMonitor.getMobile());
        assertEquals(receiverId, smsMonitor.getReceiverId());
    }

    public void testRemoveSmsMonitor() throws Exception {
        String removeId = new String("3");
        dao.removeSmsMonitor(removeId);
        try {
            dao.getSmsMonitor(removeId);
            fail("smsMonitor found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }*/
}
