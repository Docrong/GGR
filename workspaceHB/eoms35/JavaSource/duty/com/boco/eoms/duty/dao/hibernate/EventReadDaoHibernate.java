package com.boco.eoms.duty.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.duty.dao.EventReadDao;
import com.boco.eoms.duty.model.EventRead;

/**
 * <p>
 * Title:故障事件阅读 dao的hibernate实现
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
public class EventReadDaoHibernate extends BaseDaoHibernate implements EventReadDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.duty.EventReadDao#getEventReads()
	 *      
	 */
	public List getEventReads() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from EventRead eventRead";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.EventReadDao#getEventReads()
	 *      
	 */
	public List getEventReads(final String eventid) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from EventRead eventRead where eventRead.eventid=:eventid";
				Query query = session.createQuery(queryStr);
				query.setString("eventid", eventid);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.duty.EventReadDao#getEventReads()
	 *      
	 */
	public List getEventReads(final String eventid,final String userid) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from EventRead eventRead where eventRead.eventid=:eventid and eventRead.userid=:userid";
				Query query = session.createQuery(queryStr);
				query.setString("eventid", eventid);
				query.setString("userid", userid);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.duty.EventReadDao#getEventRead(java.lang.String)
	 *
	 */
	public EventRead getEventRead(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from EventRead eventRead where eventRead.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (EventRead) result.iterator().next();
				} else {
					return new EventRead();
				}
			}
		};
		return (EventRead) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.EventReadDao#saveEventReads(com.boco.eoms.duty.EventRead)
	 *      
	 */
	public void saveEventRead(final EventRead eventRead) {
		if ((eventRead.getId() == null) || (eventRead.getId().equals("")))
			getHibernateTemplate().save(eventRead);
		else
			getHibernateTemplate().saveOrUpdate(eventRead);
	}

	/**
	 * 
	 * @see com.boco.eoms.duty.EventReadDao#removeEventReads(java.lang.String)
	 *      
	 */
    public void removeEventRead(final String id) {
		getHibernateTemplate().delete(getEventRead(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		EventRead eventRead = this.getEventRead(id);
		if(eventRead==null){
			return "";
		}
		//TODO 请修改代码
		return eventRead.getEventid();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.duty.EventReadDao#getEventReads(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getEventReads(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from EventRead eventRead";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
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