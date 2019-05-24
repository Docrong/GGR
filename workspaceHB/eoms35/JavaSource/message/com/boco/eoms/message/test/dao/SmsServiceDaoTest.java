package com.boco.eoms.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.dao.SmsServiceDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsServiceDaoTest extends BaseDaoTestCase {
    private String smsServiceId = new String("1");
    private SmsServiceDao dao = null;

    public void setSmsServiceDao(SmsServiceDao dao) {
        this.dao = dao;
    }

    public void testAddSmsService() throws Exception {
        SmsService smsService = new SmsService();

        // set required fields

        java.lang.String deleted = "C";
        smsService.setDeleted(deleted);        

        java.lang.String isSendImediat = "M";
        smsService.setIsSendImediat(isSendImediat);        

        java.lang.String isSendNight = "Q";
        smsService.setIsSendNight(isSendNight);        
 
        java.lang.String msgType = "Z";
        smsService.setMsgType(msgType);        

        java.lang.String name = "OtSnVpWhJhZsMrUvLsNlYwNmBmKjXqZhSoKoPbXqEgIfAmJoFvIgDjJiDgNdEgZwIgBwDqVpMwZsZjNqTaEvJaFfPqZjLmJlPrRg";
        smsService.setName(name);        

        java.lang.String userId = "AsPpOkVrPvAzTtJsVrHcYzAtNlJbBi";
        smsService.setUserId(userId);        

        dao.saveSmsService(smsService);

        // verify a primary key was assigned
        assertNotNull(smsService.getId());

        // verify set fields are same after save
        assertEquals(deleted, smsService.getDeleted());
        assertEquals(isSendImediat, smsService.getIsSendImediat());
        assertEquals(isSendNight, smsService.getIsSendNight());
        assertEquals(msgType, smsService.getMsgType());
        assertEquals(name, smsService.getName());
        assertEquals(userId, smsService.getUserId());
    }

    public void testGetSmsService() throws Exception {
        SmsService smsService = dao.getSmsService(smsServiceId);
        assertNotNull(smsService);
    }

    public void testGetSmsServices() throws Exception {
        SmsService smsService = new SmsService();

        List results = dao.getSmsServices(smsService);
        assertTrue(results.size() > 0);
    }

    public void testSaveSmsService() throws Exception {
        SmsService smsService = dao.getSmsService(smsServiceId);

        // update required fields
        java.lang.String deleted = "L";
        smsService.setDeleted(deleted);        
        java.lang.String isSendImediat = "U";
        smsService.setIsSendImediat(isSendImediat);        
        java.lang.String isSendNight = "W";
        smsService.setIsSendNight(isSendNight);        
        java.lang.String moduleId = "JaAcAtSoWxBvQuYkLaZaJuFwWzPjSaAn";
        java.lang.String moduleName = "TqQzQjUlPfTdOxFxQjYrPaLhVkTkJfBmMnMvHjPlUeXwTqTeIy";
        java.lang.String msgType = "M";
        smsService.setMsgType(msgType);        
        java.lang.String name = "XsFmIaTzZdRiVuJpVoBsRxZcPyIhLwTeFmXiApDfJdCxPgBrUqWhNeBrAlFuJoOgPeXwKpHxWyXuCuBtYmBdAkZxGkCrVyUqOjGz";
        smsService.setName(name);         
        java.lang.String userId = "BfUfWiNxGxCwSwSoPbTyVrCtTeIgBx";
        smsService.setUserId(userId);        

        dao.saveSmsService(smsService);

        assertEquals(deleted, smsService.getDeleted());
        assertEquals(isSendImediat, smsService.getIsSendImediat());
        assertEquals(isSendNight, smsService.getIsSendNight());
        assertEquals(msgType, smsService.getMsgType());
        assertEquals(name, smsService.getName());
        assertEquals(userId, smsService.getUserId());
    }

    public void testRemoveSmsService() throws Exception {
        String removeId = new String("3");
        dao.removeSmsService(removeId);
        try {
            dao.getSmsService(removeId);
            fail("smsService found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
