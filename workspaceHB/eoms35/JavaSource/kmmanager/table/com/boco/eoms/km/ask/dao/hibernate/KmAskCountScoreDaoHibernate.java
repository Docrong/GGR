package com.boco.eoms.km.ask.dao.hibernate;

import java.util.ArrayList;
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
import com.boco.eoms.km.ask.dao.KmAskCountScoreDao;
import com.boco.eoms.km.ask.model.KmAskCountScore;

/**
 * <p>
 * Title:问答总积分 dao的hibernate实现
 * </p>
 * <p>
 * Description:问答总积分
 * </p>
 * <p>
 * Wed Aug 19 10:09:58 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmAskCountScoreDaoHibernate extends BaseDaoHibernate implements KmAskCountScoreDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskCountScoreDao#getKmAskCountScores()
	 *      
	 */
	public List getKmAskCountScores() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAskCountScore kmAskCountScore order by kmAskCountScore.countScore desc";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 查询某人的积分情况
	 * @param id
	 * @return
	 */
	public KmAskCountScore getKmAskCountScoreByUser(final String userName) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAskCountScore kmAskCountScore where kmAskCountScore.userName=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, userName);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmAskCountScore) result.iterator().next();
				} else {
					return new KmAskCountScore();
				}
			}
		};
		return (KmAskCountScore) getHibernateTemplate().execute(callback);
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.ask.KmAskCountScoreDao#getKmAskCountScore(java.lang.String)
	 *
	 */
	public KmAskCountScore getKmAskCountScore(final String id) {
		KmAskCountScore kmAskCountScore = (KmAskCountScore) getHibernateTemplate().get(KmAskCountScore.class, id);
		if (kmAskCountScore == null) {
			kmAskCountScore = new KmAskCountScore();
		}
		return kmAskCountScore;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskCountScoreDao#saveKmAskCountScores(com.boco.eoms.km.ask.KmAskCountScore)
	 *      
	 */
	public void saveKmAskCountScore(final KmAskCountScore kmAskCountScore) {
		if ((kmAskCountScore.getId() == null) || (kmAskCountScore.getId().equals("")))
			getHibernateTemplate().save(kmAskCountScore);
		else
			getHibernateTemplate().saveOrUpdate(kmAskCountScore);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskCountScoreDao#removeKmAskCountScores(java.lang.String)
	 *      
	 */
    public void removeKmAskCountScore(final String id) {
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
				String queryStr = "delete from KmAskCountScore kmAskCountScore where kmAskCountScore.id=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, id);
				int result = query.executeUpdate();
				return new Integer(result);
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
		KmAskCountScore kmAskCountScore = this.getKmAskCountScore(id);
		if(kmAskCountScore==null){
			return "";
		}
		//TODO 请修改代码
		return kmAskCountScore.getUserName();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskCountScoreDao#getKmAskCountScores(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmAskCountScores(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAskCountScore kmAskCountScore";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();

				List result = null;
				if(total >0){
					Query query = session.createQuery(queryStr);
					query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					result = query.list();					
				}
				else{
					result = new ArrayList();
				}
				
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
}