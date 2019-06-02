package com.boco.eoms.duty.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmThing;
import com.boco.eoms.duty.model.TawRmThingNote;

public interface ITawRmThingDAO extends Dao {


	
	public void save(final TawRmThing tawRmThing);
	
	public void save(final TawRmThingNote tawRmThingNote);
	
	public List getThingList(final String room_id);
	
	public void deleteTawRmThingById(final String id);
	
	public void deleteTawTmThingNoteById(final String id);
	
	public List getThingNoteList(final String thingId);
	
	public TawRmThing getTawRmThingById(final String Id);
	
	public Map getQueryList(final Integer curPage, final Integer pageSize,final String whereStr);
	
}
