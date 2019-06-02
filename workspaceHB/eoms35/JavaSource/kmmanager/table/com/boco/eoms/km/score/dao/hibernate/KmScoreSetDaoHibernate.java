package com.boco.eoms.km.score.dao.hibernate;

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
import com.boco.eoms.km.score.dao.KmScoreSetDao;
import com.boco.eoms.km.score.model.KmScoreSet;

/**
 * <p>
 * Title:积分设定表 dao的hibernate实现
 * </p>
 * <p>
 * Description:积分设定表
 * </p>
 * <p>
 * Fri Aug 07 14:32:13 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
public class KmScoreSetDaoHibernate extends BaseDaoHibernate implements KmScoreSetDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.score.KmScoreSetDao#getKmScoreSets()
	 *      
	 */
	public List getKmScoreSets() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmScoreSet kmScoreSet";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.score.KmScoreSetDao#getKmScoreSet(java.lang.String)
	 *
	 */
	public KmScoreSet getKmScoreSet(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmScoreSet kmScoreSet where kmScoreSet.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmScoreSet) result.iterator().next();
				} else {
					return new KmScoreSet();
				}
			}
		};
		return (KmScoreSet) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.score.KmScoreSetDao#saveKmScoreSets(com.boco.eoms.km.score.KmScoreSet)
	 *      
	 */
	public void saveKmScoreSet(final KmScoreSet kmScoreSet) {
		if ((kmScoreSet.getId() == null) || (kmScoreSet.getId().equals("")))
			getHibernateTemplate().save(kmScoreSet);
		else
			getHibernateTemplate().saveOrUpdate(kmScoreSet);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.score.KmScoreSetDao#removeKmScoreSets(java.lang.String)
	 *      
	 */
    public void removeKmScoreSetId(final String id) {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hqlUpdate = "update KmScoreSet kmScoreSet set kmScoreSet.isDeleted=1 where kmScoreSet.id=?";
				Query query = session.createQuery(hqlUpdate);
				query.setString(0, id);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}
    public void removeKmScoreSet(final String id) {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hqlUpdate = "update KmScoreSet kmScoreSet set kmScoreSet.isDeleted=1 where kmScoreSet.scoreWeightId=?";
				Query query = session.createQuery(hqlUpdate);
				query.setString(0, id);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}
    
    public void removeKmScoreSetByPowerName(final String powerName) {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hqlUpdate = "update KmScoreSet kmScoreSet set kmScoreSet.isDeleted=1 where kmScoreSet.powerName=?";
				Query query = session.createQuery(hqlUpdate);
				query.setString(0, powerName);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}
	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmScoreSet kmScoreSet = this.getKmScoreSet(id);
		if(kmScoreSet==null){
			return "";
		}
		//TODO 请修改代码
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.score.KmScoreSetDao#getKmScoreSets(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmScoreSets(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmScoreSet kmScoreSet";
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