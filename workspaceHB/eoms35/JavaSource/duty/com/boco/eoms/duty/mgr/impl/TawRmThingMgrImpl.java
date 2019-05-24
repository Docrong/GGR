package com.boco.eoms.duty.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.dao.ITawRmThingDAO;
import com.boco.eoms.duty.mgr.TawRmThingMgr;
import com.boco.eoms.duty.model.TawRmThing;
import com.boco.eoms.duty.model.TawRmThingNote;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * 2009-4-29
 * </p>
 * 
 * @Author panyunfu
 * @Version 3.5
 *
 */
public class TawRmThingMgrImpl implements TawRmThingMgr {

	private ITawRmThingDAO tawRmThingDAO ;
	
	public ITawRmThingDAO getTawRmThingDAO() {
		return tawRmThingDAO;
	}

	public void setTawRmThingDAO(ITawRmThingDAO tawRmThingDAO) {
		this.tawRmThingDAO = tawRmThingDAO;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmThingMgr#deleteById(java.lang.String)
	 */
	public void deleteTawRmThingById(String id) {
		tawRmThingDAO.deleteTawRmThingById(id);
	}

	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmThingMgr#save(com.boco.eoms.duty.model.TawRmThing)
	 */
	public void save(TawRmThing tawRmThing) {
		tawRmThingDAO.save(tawRmThing);
	}

	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmThingMgr#save(com.boco.eoms.duty.model.TawRmThingNote)
	 */
	public void save(TawRmThingNote tawRmThingNote) {
		tawRmThingDAO.save(tawRmThingNote);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmThingMgr#getThingList(java.lang.String)
	 */
	public List getThingList(final String room_id){
		return tawRmThingDAO.getThingList(room_id);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmThingMgr#getThingNoteList(java.lang.String)
	 */
	public List getThingNoteList(final String thingId){
		return tawRmThingDAO.getThingNoteList(thingId);
	}
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmThingMgr#getTawRmThingById(java.lang.String)
	 */
	public TawRmThing getTawRmThingById(final String Id){
		return tawRmThingDAO.getTawRmThingById(Id);
	}
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmThingMgr#deleteTawTmThingNoteById(java.lang.String)
	 */
	public void deleteTawTmThingNoteById(final String id){
		tawRmThingDAO.deleteTawTmThingNoteById(id);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmThingMgr#getQueryList(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public Map getQueryList(final Integer curPage, final Integer pageSize,final String whereStr){
		return tawRmThingDAO.getQueryList(curPage, pageSize, whereStr);
	}
	
	public String builderSql(String id,String beginTime,String endTime){
		StringBuffer sb=new StringBuffer();
		if(null != id && !"".equals(id)){
			sb.append(" AND obj.thingId='").append(id).append("'");
		}
		if(beginTime !=null && !"".equals(beginTime)){
			sb.append(" AND obj.beginTime >='").append(beginTime).append("'");
		}
		if(endTime !=null && !"".equals(endTime)){
			sb.append(" AND obj.endTime <='").append(endTime).append("'");
		}
		return sb.toString();
	}
}
