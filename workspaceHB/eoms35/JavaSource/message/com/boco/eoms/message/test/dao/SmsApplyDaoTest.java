package com.boco.eoms.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.dao.SmsApplyDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsApplyDaoTest extends BaseDaoTestCase {
    private String smsApplyId = new String("1");
    private SmsApplyDao dao = null;

    public void setSmsApplyDao(SmsApplyDao dao) {
        this.dao = dao;
    }

    public void testAddSmsApply() throws Exception {
        SmsApply smsApply = new SmsApply();

        // set required fields

        java.lang.Integer count = new Integer(1330338700);
        smsApply.setCount(count);        


        java.util.Date endTime = new java.util.Date();
        smsApply.setEndTime(endTime);        

        java.lang.String name = "AlLrHbOmTuTdVrTcGmGfWtGpQjJtTdNgLfDqSfFjJjMpYkMyTvUaQyFyEhWoPcTbHeEbKvIiPvUfMcIlJnBlLmLzSwJfZpDoStNy";
        smsApply.setName(name);        

        java.lang.String interval = "NxNkOuDuYl";
        smsApply.setInterval(interval);        

        java.lang.String mobile = "QmCjDpGwZwCyGdQpFgCb";
        smsApply.setMobile(mobile);        

        java.lang.String receiverId = "YqOnZjUbBoXgEmFmHrSvNgKzPeGnEk";
        smsApply.setReceiverId(receiverId);        

        java.lang.String receiverType = "W";
        smsApply.setReceiverType(receiverType);        

        java.lang.String regetData = "V";
        smsApply.setRegetData(regetData);        

        java.lang.String remark = "MhQfGlLmCmHcZcEcXkKyXcEeQjZrXzGpMnLvPbSlNdFxVyMxJcVlDoDtGyJiIkKbUpWdEwNyVzYxQxDmLrFcGcYnVmLnGyYrZlJgAzMlWiEyEgBbUeEpUyGrMiEsXeNmFnEwSrFxYeKvAmToTfUbFsDuDhVbJpQgZeIzWzOtJzTcAyNuIzYvUlNhKsDlUtWyLyEtFwNj";
        smsApply.setRemark(remark);        

               

        java.lang.String serviceId = "FnEnCkHePrXpPlDxCbSrNxTgYjQuEoRd";
        smsApply.setServiceId(serviceId);        

        java.util.Date startTime = new java.util.Date();
        smsApply.setStartTime(startTime);        

        java.lang.String userId = "BqWaSyZfDbSuRnObRgTqBdYgIoYcIi";
        smsApply.setUserId(userId);        

        dao.saveSmsApply(smsApply);

        // verify a primary key was assigned
        assertNotNull(smsApply.getId());

        // verify set fields are same after save
        assertEquals(count, smsApply.getCount());
        assertEquals(endTime, smsApply.getEndTime());
        assertEquals(name, smsApply.getName());
        assertEquals(interval, smsApply.getInterval());
        assertEquals(mobile, smsApply.getMobile());
        assertEquals(receiverId, smsApply.getReceiverId());
        assertEquals(receiverType, smsApply.getReceiverType());
        assertEquals(regetData, smsApply.getRegetData());
        assertEquals(remark, smsApply.getRemark());
        assertEquals(serviceId, smsApply.getServiceId());
        assertEquals(startTime, smsApply.getStartTime());
        assertEquals(userId, smsApply.getUserId());
    }

    public void testGetSmsApply() throws Exception {
        SmsApply smsApply = dao.getSmsApply(smsApplyId);
        assertNotNull(smsApply);
    }

    public void testGetSmsApplys() throws Exception {
        SmsApply smsApply = new SmsApply();

        List results = dao.getSmsApplys(smsApply);
        assertTrue(results.size() > 0);
    }

    public void testSaveSmsApply() throws Exception {
        SmsApply smsApply = dao.getSmsApply(smsApplyId);

        // update required fields
        java.lang.Integer count = new Integer(1507503857);
        smsApply.setCount(count);       
        java.util.Date endTime = new java.util.Date();
        smsApply.setEndTime(endTime);        
        java.lang.String name = "GxMkXeBzIlQvLnTiWpClZpLsVgPbVkVlOvQqZiToAuIpDiAkQvQsIaAiGjPkWxBgZuFhBgUqGyLjOqYxEfRhYmNjLaPvQbExZoYw";
        smsApply.setName(name);        
        java.lang.String interval = "UvBnTbVfKp";
        smsApply.setInterval(interval);        
        java.lang.String mobile = "RhSdFuQxQrHaGrTvXoQz";
        smsApply.setMobile(mobile);        
        java.lang.String receiverId = "UjOvOhYoLrZmMyKdTeGbVzPtHrGvNn";
        smsApply.setReceiverId(receiverId);        
        java.lang.String receiverType = "M";
        smsApply.setReceiverType(receiverType);        
        java.lang.String regetData = "Y";
        smsApply.setRegetData(regetData);        
        java.lang.String remark = "ZhZvIxCyXsEuDtTsFvLpSjJkDaKmRxClBnGjDdRdEtRmFtUvIsClOyKiTmHmPiJfFrHnXhAbLdGqBxCiYwOfXwDqQfEkEaUiYvTqRfGgJvObJoKxWcXkVsLcCdLqVeAbXhJeTyMyIfEmXqIdXxFdZtEfWkMoCjUgZqPdAbCiHiPvHiFtWuOuFvNsWhGxUoYsRcPjUsQb";
        smsApply.setRemark(remark);  
        java.lang.String serviceId = "HyQpLyXuScFkLpKbMmSzTkEmEjIuGrCt";
        smsApply.setServiceId(serviceId);        
        java.util.Date startTime = new java.util.Date();
        smsApply.setStartTime(startTime);        
        java.lang.String userId = "TcJiRjZnYfLjTnXjRmQlCqGjSdQfWc";
        smsApply.setUserId(userId);        

        dao.saveSmsApply(smsApply);

        assertEquals(count, smsApply.getCount());
        assertEquals(endTime, smsApply.getEndTime());
        assertEquals(name, smsApply.getName());
        assertEquals(interval, smsApply.getInterval());
        assertEquals(mobile, smsApply.getMobile());
        assertEquals(receiverId, smsApply.getReceiverId());
        assertEquals(receiverType, smsApply.getReceiverType());
        assertEquals(regetData, smsApply.getRegetData());
        assertEquals(remark, smsApply.getRemark());
        assertEquals(serviceId, smsApply.getServiceId());
        assertEquals(startTime, smsApply.getStartTime());
        assertEquals(userId, smsApply.getUserId());
    }

    public void testRemoveSmsApply() throws Exception {
        String removeId = new String("3");
        dao.removeSmsApply(removeId);
        try {
            dao.getSmsApply(removeId);
            fail("smsApply found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
