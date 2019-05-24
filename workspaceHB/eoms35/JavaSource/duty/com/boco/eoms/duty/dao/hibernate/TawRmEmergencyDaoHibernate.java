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
import com.boco.eoms.duty.dao.ITawRmEmergencyDao;
import com.boco.eoms.duty.model.TawRmEmergency;
 



public class TawRmEmergencyDaoHibernate extends BaseDaoHibernate implements ITawRmEmergencyDao{

	/**
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#getTawRmEmergencys(com.boco.eoms.duty.model.TawRmEmergency)
	 */
	public List getTawRmEmergencys(final TawRmEmergency TawRmEmergency) {
		return getHibernateTemplate().find("from TawRmEmergency");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (TawRmEmergency == null) {
		 * return getHibernateTemplate().find("from TawRmEmergency"); } else { //
		 * filter on properties set in the TawRmEmergency HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(TawRmEmergency).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawRmEmergency.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#getTawRmEmergency(String
	 *      id)
	 */
	public TawRmEmergency getTawRmEmergency(final String id) {
		TawRmEmergency TawRmEmergency = (TawRmEmergency) getHibernateTemplate()
				.get(TawRmEmergency.class, id);
		if (TawRmEmergency == null) {
			throw new ObjectRetrievalFailureException(TawRmEmergency.class,
					id);
		}

		return TawRmEmergency;
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#saveTawRmEmergency(TawRmEmergency
	 *      TawRmEmergency)
	 */
	public String  saveTawRmEmergency(final TawRmEmergency TawRmEmergency) {
		if ((TawRmEmergency.getId() == null)
				|| (TawRmEmergency.getId().equals("")))
			getHibernateTemplate().save(TawRmEmergency);
		else
			getHibernateTemplate().saveOrUpdate(TawRmEmergency);
		
		return TawRmEmergency.getId();
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#removeTawRmEmergency(String
	 *      id)
	 */
	public void removeTawRmEmergency(final String id) {
		getHibernateTemplate().delete(getTawRmEmergency(id));
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#getTawRmEmergencys(final
	 *      Integer curPage, final Integer pageSize,final String whereStr)
	 */
	public Map getTawRmEmergencys(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the TawRmEmergency
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawRmEmergency";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

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
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#getTawRmEmergencys(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmEmergencys(final Integer curPage,
			final Integer pageSize) {
		return this.getTawRmEmergencys(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#getChildList(String
	 *      parentId)
	 */
	public ArrayList getChildList(String parentId) {
		String hql = " from TawRmEmergency obj where obj.parentId='"
				+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
	
  
	/**
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#removeTawRmEmergency(String
	 *      id)
	 */
 
	/**
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#getTawRmEmergencys(final
	 *      Integer curPage, final Integer pageSize,final String whereStr)
	 */
	public Map getTawRmEmergencySubs(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the TawRmEmergency
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawRmEmergencySub";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

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
	 * @see com.boco.eoms.duty.dao.TawRmEmergencyDao#getTawRmEmergencys(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmEmergencySubs(final Integer curPage,
			final Integer pageSize) {
		return this.getTawRmEmergencySubs(curPage, pageSize, null);
	}

    public ArrayList getTawRmEmergencySubByEventid(final String eventId){
    	String hql = " from TawRmEmergencySub obj where obj.eventid='"
			+ eventId + "' order by obj.recordtime";
	return (ArrayList) getHibernateTemplate().find(hql);
    }

    public List getTawRmEmergencyByDept(final String deptid){
    	String sql = " from TawRmEmergency obj where obj.deptid like '"+deptid+"' order by obj.recordtime";
    	return (ArrayList) getHibernateTemplate().find(sql);
    }
    public List getTawRmEmergencyByDeptAndFlag(final String deptid,final String startFlag,final String endFlag){
    	String sql = " from TawRmEmergency obj where obj.deptid like '"+deptid+"%' and (flag between '"+startFlag+"' and '"+endFlag+"' ) and" +
    			" complateFlag ='2' order by flag desc,inputdate desc";
    	return (ArrayList) getHibernateTemplate().find(sql);
    }

	public List getTawRmEmergencyByTime(String startTime, String endTime) {
		List returnList = null;
		String hql = "from TawRmEmergency de where de.flag >= 3 and de.inputdate<='"+endTime+"' and de.inputdate>='"+startTime+"'";
		returnList = getHibernateTemplate().find(hql);
		return returnList;
	}

	 
}
