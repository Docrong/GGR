package com.boco.eoms.duty.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.faultrecord.model.Faultrecord;
import com.boco.eoms.duty.model.TawRmDutyEventPub;

public interface ITawRmDutyEventPubDao extends Dao{
	public List getPubStatus(final String pubStatus);
	public List getEventId(final String EventId);
	public void saveTawRmDutyEventPub(final TawRmDutyEventPub tawRmDutyEventPub);
	public void removeTawRmDutyEventPub(final String eventId);
	

}
