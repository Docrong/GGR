package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.dao.ITawRmDutyEventDao;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.model.TawRmDutyEventSub;
import com.boco.eoms.duty.util.CommonTools;



public class TawRmDutyEventDaoHibernate extends BaseDaoHibernate implements ITawRmDutyEventDao{

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#getTawRmDutyEvents(com.boco.eoms.duty.model.TawRmDutyEvent)
	 */
	public List getTawRmDutyEvents(final TawRmDutyEvent TawRmDutyEvent) {
		return getHibernateTemplate().find("from TawRmDutyEvent");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (TawRmDutyEvent == null) {
		 * return getHibernateTemplate().find("from TawRmDutyEvent"); } else { //
		 * filter on properties set in the TawRmDutyEvent HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(TawRmDutyEvent).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawRmDutyEvent.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#getTawRmDutyEvent(String
	 *      id)
	 */
	public TawRmDutyEvent getTawRmDutyEvent(final String id) {
		TawRmDutyEvent TawRmDutyEvent = (TawRmDutyEvent) getHibernateTemplate()
				.get(TawRmDutyEvent.class, id);
		if (TawRmDutyEvent == null) {
			throw new ObjectRetrievalFailureException(TawRmDutyEvent.class,
					id);
		}

		return TawRmDutyEvent;
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#saveTawRmDutyEvent(TawRmDutyEvent
	 *      TawRmDutyEvent)
	 */
	public String  saveTawRmDutyEvent(final TawRmDutyEvent TawRmDutyEvent) {
		if ((TawRmDutyEvent.getId() == null)
				|| (TawRmDutyEvent.getId().equals("")))
			getHibernateTemplate().save(TawRmDutyEvent);
		else
			getHibernateTemplate().saveOrUpdate(TawRmDutyEvent);
		
		return TawRmDutyEvent.getId();
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#removeTawRmDutyEvent(String
	 *      id)
	 */
	public void removeTawRmDutyEvent(final String id) {
		getHibernateTemplate().delete(getTawRmDutyEvent(id));
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#getTawRmDutyEvents(final
	 *      Integer curPage, final Integer pageSize,final String whereStr)
	 */
	public Map getTawRmDutyEvents(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the TawRmDutyEvent
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawRmDutyEvent";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryStr);
				query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#getTawRmDutyEvents(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmDutyEvents(final Integer curPage,
			final Integer pageSize) {
		return this.getTawRmDutyEvents(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#getChildList(String
	 *      parentId)
	 */
	public ArrayList getChildList(String parentId) {
		String hql = " from TawRmDutyEvent obj where obj.parentId='"
				+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
	
	public TawRmDutyEventSub getTawRmDutyEventSub(final String id) {
		TawRmDutyEventSub tawRmDutyEventSub = (TawRmDutyEventSub) getHibernateTemplate()
				.get(TawRmDutyEventSub.class, id);
		if (tawRmDutyEventSub == null) {
			throw new ObjectRetrievalFailureException(TawRmDutyEventSub.class,
					id);
		}

		return tawRmDutyEventSub;
	}
	public void  saveTawRmDutyEventSub(final TawRmDutyEventSub tawRmDutyEventSub) {
		if ((tawRmDutyEventSub.getId() == null)
				|| (tawRmDutyEventSub.getId().equals("")))
			getHibernateTemplate().save(tawRmDutyEventSub);
		else
			getHibernateTemplate().saveOrUpdate(tawRmDutyEventSub);
		
		//return tawRmDutyEventSub.getId();
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#removeTawRmDutyEvent(String
	 *      id)
	 */
	public void removeTawRmDutyEventSub(final String id) {
		getHibernateTemplate().delete(getTawRmDutyEventSub(id));
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#getTawRmDutyEvents(final
	 *      Integer curPage, final Integer pageSize,final String whereStr)
	 */
	public Map getTawRmDutyEventSubs(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the TawRmDutyEvent
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawRmDutyEventSub";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				
				String queryCountStr = "select count(*) " + queryStr;
//				queryStr += " order by eventid,happentime ";

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmDutyEventDao#getTawRmDutyEvents(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmDutyEventSubs(final Integer curPage,
			final Integer pageSize) {
		return this.getTawRmDutyEventSubs(curPage, pageSize, null);
	}

    public ArrayList getTawRmDutyEventSubByEventid(final String eventId){
    	String hql = " from TawRmDutyEventSub obj where obj.eventid='"
			+ eventId + "' order by obj.recordtime";
	return (ArrayList) getHibernateTemplate().find(hql);
    }

    public List getTawRmDutyEventByDept(final String deptid){
    	String sql = " from TawRmDutyEvent obj where obj.deptid like '"+deptid+"' order by obj.recordtime";
    	return (ArrayList) getHibernateTemplate().find(sql);
    }
    
    public List getTawRmDutyEventBySheetId(final String sheetId){
    	String sql = " from TawRmDutyEvent obj where obj.sheetid='"+sheetId+"' order by obj.inputdate";
    	return (ArrayList) getHibernateTemplate().find(sql);
    }
    
    public List getTawRmDutyEventByDeptAndFlag(final String deptid,final String startFlag,final String endFlag){
    	String sql = " from TawRmDutyEvent obj where obj.deptid like '"+deptid+"%' and (flag between '"+startFlag+"' and '"+endFlag+"' ) and" +
    			" complateFlag ='2' order by flag desc,inputdate desc";
    	return (ArrayList) getHibernateTemplate().find(sql);
    }

	public List getTawRmDutyEventByTime(String startTime, String endTime) {
		List returnList = null;
		String hql = "from TawRmDutyEvent de where de.flag >= 3 and de.inputdate<='"+endTime+"' and de.inputdate>='"+startTime+"'";
		returnList = getHibernateTemplate().find(hql);
		return returnList;
	}
	
	/**
	 * 获取满足条件的事件
	 */
	public Map getEventByCondition(final Integer curPage, final Integer pageSize, final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {				
				String queryStr = "from TawRmDutyEvent tawRmDutyEvent ";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += " where 1=1 " + whereStr;
				String queryCountStr = "select count(*) " + queryStr;
				queryStr += " order by tawRmDutyEvent.inputdate desc ";
	
				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryStr);
				query.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 获取满足条件的事件
	 */ 
	public Map getQueryEventList(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawRmDutyEvent tawRmDutyEvent,TawRmDutyEventSub tawRmDutyEventSub "
					+ " where tawRmDutyEventSub.eventid=tawRmDutyEvent.id ";
				
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				
				String queryCountStr = "select count(distinct tawRmDutyEvent.id) " + queryStr;	
				
				int total = ((Integer) session.createQuery(queryCountStr).iterate().next()).intValue();
				queryStr = "select distinct tawRmDutyEvent.id,tawRmDutyEvent.inputuser,tawRmDutyEvent.inputdate, "
					+ "tawRmDutyEvent.flag,tawRmDutyEvent.eventtitle,tawRmDutyEvent.beginTime,"
					+ "tawRmDutyEvent.complateFlag,tawRmDutyEvent.endtime,tawRmDutyEvent.faultType,"
					+ "tawRmDutyEvent.falultid,tawRmDutyEvent.isSubmit,tawRmDutyEvent.deptid,"
					+ "tawRmDutyEvent.workserial,tawRmDutyEvent.finishworkserial,tawRmDutyEvent.regionlist,"
					+ "tawRmDutyEvent.roomid,tawRmDutyEvent.sheetid,tawRmDutyEvent.faultCommontId,"
					+ "tawRmDutyEvent.faultEquipmentId,tawRmDutyEvent.faultCircuitId,tawRmDutyEvent.pubstatus " 
					+ queryStr;
				queryStr += " order by tawRmDutyEvent.inputdate desc ";
				
				Query query = session.createQuery(queryStr);
				query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
				System.out.println(pageSize.intValue() + " * "+curPage.intValue() + "=" + pageSize.intValue() * curPage.intValue());
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
	
	/**
	 *
	 * 取星级故障事件数目
	 * 获取本机房增加的本班次的数据+监控机房本班次增加与本部门相关数据
	 * @return 返回星级故障事件数目列表
	 */
    public List getEventNumByFlag(final String jksWorkserial,final String workserial,final String region,final String roomid){
   	 HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "SELECT flag,count(id) FROM TawRmDutyEvent tawRmDutyEvent "
					+ " WHERE (tawRmDutyEvent.roomid=" + roomid + " AND tawRmDutyEvent.workserial=" + workserial + ") "
					+ " OR (tawRmDutyEvent.regionlist like ('%#" + region +"#%') " + " AND tawRmDutyEvent.workserial=" + jksWorkserial + ") "
					+ " GROUP BY tawRmDutyEvent.flag ";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);	
    }
    
    /**
	 * 获取某个班次的事件
	 * @param userId
	 * @return List
	 */
	public List getEventList(final String jksWorkserial,final String workserial,final String region,final String roomid){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "FROM TawRmDutyEvent tawRmDutyEvent "
					+ " WHERE (tawRmDutyEvent.roomid=" + roomid + " AND tawRmDutyEvent.workserial=" + workserial + ") "
					+ " OR (tawRmDutyEvent.regionlist like ('%#" + region +"#%') " + " AND tawRmDutyEvent.workserial=" + jksWorkserial + ") "
					+ " order by tawRmDutyEvent.inputdate desc";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);	
    }
}
