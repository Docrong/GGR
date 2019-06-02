package com.boco.eoms.duty.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.dao.ITawRmThingDAO;
import com.boco.eoms.duty.model.TawRmGuestform;
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
public class TawRmThingDAOHibernate extends BaseDaoHibernate implements
		ITawRmThingDAO {

	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmThingDAO#save(com.boco.eoms.duty.model.TawRmThing)
	 */
	public void save(TawRmThing tawRmThing) {
		if(tawRmThing.getId() == null || "".equals(tawRmThing.getId())){
			this.getHibernateTemplate().save(tawRmThing);
		}else{
			this.getHibernateTemplate().update(tawRmThing);
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmThingDAO#save(com.boco.eoms.duty.model.TawRmThingNote)
	 */
	public void save(final TawRmThingNote tawRmThingNote){
		if(tawRmThingNote.getId() == null || "".equals(tawRmThingNote.getId())){
			this.getHibernateTemplate().save(tawRmThingNote);
		}else{
			this.getHibernateTemplate().update(tawRmThingNote);
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmThingDAO#getThingList(java.lang.String)
	 */
	public List getThingList(final String room_id){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawRmThing obj where obj.roomId=:room_id order by obj.inputTime desc";
				Query query = session.createQuery(queryStr);
				query.setString("room_id", room_id);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);	
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmThingDAO#deleteById(java.lang.String)
	 */
	public void deleteTawRmThingById(final String id){
		getHibernateTemplate().delete(getTawRmThingById(id));
	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmThingDAO#deleteTawTmThingNoteById(java.lang.String)
	 */
	public void deleteTawTmThingNoteById(final String id){
		getHibernateTemplate().delete(getTawRmThingNote(id));
	}
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmThingDAO#getThingNoteList(java.lang.String)
	 */
	public List getThingNoteList(final String thingId){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawRmThingNote obj where obj.thingId=:thingId order by obj.inputTime desc";
				Query query = session.createQuery(queryStr);
				query.setString("thingId", thingId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);	
	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmThingDAO#getTawRmThingById(java.lang.String)
	 */
	public TawRmThing getTawRmThingById(final String id){
		String hql="from TawRmThing obj where obj.id='"+id+"'";
		List result = getHibernateTemplate().find(hql);
		if(null != result && result.size() !=0){
			return  (TawRmThing)result.iterator().next();
		}else{
			return new TawRmThing();
		}
	}
	
	public TawRmThingNote  getTawRmThingNote(final String id){
		String hql="from TawRmThingNote obj where obj.id='"+id+"'";
		List result = getHibernateTemplate().find(hql);
		if(null != result && result.size() !=0){
			return  (TawRmThingNote)result.iterator().next();
		}else{
			return new TawRmThingNote();
		}
	}
	
	
	public Map getQueryList(final Integer curPage, final Integer pageSize,final String whereStr){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hql="from TawRmThingNote obj ";
				if(whereStr !=null && whereStr.length()>1){
					hql+="where 1=1 " +whereStr;
				}
				String queryCountStr="select count(*) "+hql;
				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(hql);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
				}
			};
		 return (Map) getHibernateTemplate().execute(callback);
	}
}
