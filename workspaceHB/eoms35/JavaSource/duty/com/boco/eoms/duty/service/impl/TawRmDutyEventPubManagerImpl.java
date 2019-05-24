package com.boco.eoms.duty.service.impl;

import java.io.Serializable;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.dao.ITawRmDutyEventPubDao;
import com.boco.eoms.duty.model.TawRmDutyEventPub;
import com.boco.eoms.duty.service.ITawRmDutyEventPubManager;

public class TawRmDutyEventPubManagerImpl extends BaseManager implements ITawRmDutyEventPubManager {
	
	 private ITawRmDutyEventPubDao tawRmDutyEventPubDao;

	public List getPubStatus(final String pubStatus) {
		// TODO Auto-generated method stub
		return tawRmDutyEventPubDao.getPubStatus(pubStatus);
	}
	public List getEventId(final String EventId) {
		return tawRmDutyEventPubDao.getEventId(EventId);
	}
	public void saveTawRmDutyEventPub(TawRmDutyEventPub tawRmDutyEventPub) {
		tawRmDutyEventPubDao.saveTawRmDutyEventPub(tawRmDutyEventPub);
		
	}
	public ITawRmDutyEventPubDao getTawRmDutyEventPubDao() {
		return tawRmDutyEventPubDao;
	}
	public void setTawRmDutyEventPubDao(ITawRmDutyEventPubDao tawRmDutyEventPubDao) {
		this.tawRmDutyEventPubDao = tawRmDutyEventPubDao;
	}
	public void removeTawRmDutyEventPub(String eventId) {
		tawRmDutyEventPubDao.removeTawRmDutyEventPub(eventId);
		
	}
	
}
