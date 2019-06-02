package com.boco.eoms.duty.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.TawRmThing;
import com.boco.eoms.duty.model.TawRmThingNote;

public interface TawRmThingMgr {
	

	
	public void save(final TawRmThing tawRmThing);
	
	public void save(final TawRmThingNote tawRmThingNote);
	
	public List getThingList(final String room_id);
	
	public void deleteTawRmThingById(final String id);
	
	public void deleteTawTmThingNoteById(final String id);
	
	public List getThingNoteList(final String thingId);
	
	public TawRmThing getTawRmThingById(final String Id);
	
	public Map getQueryList(final Integer curPage, final Integer pageSize,final String whereStr);
	
	public String builderSql(String id,String beginTime,String endTime);
}
