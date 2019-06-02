package com.boco.eoms.duty.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.EventRead;
import com.boco.eoms.duty.mgr.EventReadMgr;
import com.boco.eoms.duty.dao.EventReadDao;

/**
 * <p>
 * Title:故障事件阅读
 * </p>
 * <p>
 * Description:故障事件阅读
 * </p>
 * <p>
 * Tue Apr 21 10:34:39 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version EOMS3.5
 * 
 */
public class EventReadMgrImpl implements EventReadMgr {
 
	private EventReadDao  eventReadDao;
 	
	public EventReadDao getEventReadDao() {
		return this.eventReadDao;
	}
 	
	public void setEventReadDao(EventReadDao eventReadDao) {
		this.eventReadDao = eventReadDao;
	}
 	
    public List getEventReads() {
    	return eventReadDao.getEventReads();
    }
    
    public List getEventReads(String eventid) {
    	return eventReadDao.getEventReads(eventid);
    }
    
    public List getEventReads(String eventid,String userid) {
    	return eventReadDao.getEventReads(eventid,userid);
    }
    
    public EventRead getEventRead(final String id) {
    	return eventReadDao.getEventRead(id);
    }
    
    public void saveEventRead(EventRead eventRead) {
    	eventReadDao.saveEventRead(eventRead);
    }
    
    public void removeEventRead(final String id) {
    	eventReadDao.removeEventRead(id);
    }
    
    public Map getEventReads(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return eventReadDao.getEventReads(curPage, pageSize, whereStr);
	}
	
}