package com.boco.eoms.duty.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawRmDutyEventPub;

public interface ITawRmDutyEventPubManager extends Manager{
	public List getPubStatus(final String pubStatus);
	public List getEventId(final String EventId);
	public void saveTawRmDutyEventPub(TawRmDutyEventPub tawRmDutyEventPub);
	public void removeTawRmDutyEventPub(final String eventId);
}
