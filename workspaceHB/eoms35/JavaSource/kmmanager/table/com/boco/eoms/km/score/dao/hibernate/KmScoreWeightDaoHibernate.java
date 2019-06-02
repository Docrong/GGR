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
import com.boco.eoms.km.score.dao.KmScoreWeightDao;
import com.boco.eoms.km.score.model.KmScoreTree;
import com.boco.eoms.km.score.model.KmScoreWeight;

/**
 * <p>
 * Title:积分权重 dao的hibernate实现
 * </p>
 * <p>
 * Description:积分权重表
 * </p>
 * <p>
 * Fri Aug 21 09:06:28 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
public class KmScoreWeightDaoHibernate extends BaseDaoHibernate implements KmScoreWeightDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.score.KmScoreWeightDao#getKmScoreWeights()
	 *      
	 */
	public List getKmScoreWeights() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmScoreWeight kmScoreWeight";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
//    public KmScoreWeight getKmScoreWeightByNodeId(final String nodeId) {
//    	KmScoreWeight kmScoreWeight = new KmScoreWeight();
//		String hql = " from KmScoreWeight kmScoreWeight where kmScoreWeight.nodeId like '"
//				+ nodeId + "%'";
//		List list = getHibernateTemplate().find(hql);
//		if (list.iterator().hasNext()) {
//			kmScoreWeight = (KmScoreWeight) list.iterator().next();
//		}
//		return kmScoreWeight;
//    }

	/**
	 *
	 * @see com.boco.eoms.km.score.KmScoreWeightDao#getKmScoreWeight(java.lang.String)
	 *
	 */
	public KmScoreWeight getKmScoreWeight(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmScoreWeight kmScoreWeight where kmScoreWeight.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmScoreWeight) result.iterator().next();
				} else {
					return new KmScoreWeight();
				}
			}
		};
		return (KmScoreWeight) getHibernateTemplate().execute(callback);
	}
	
    public KmScoreWeight getKmScoreWeightByNodeId(final String nodeId) {
    	KmScoreWeight kmScoreWeight = new KmScoreWeight();
		String hql = " from KmScoreWeight kmScoreWeight where kmScoreWeight.nodeId='" + nodeId + "'";
		List list = getHibernateTemplate().find(hql);
		if (list.iterator().hasNext()) {
			kmScoreWeight = (KmScoreWeight) list.iterator().next();
		}
		return kmScoreWeight;
    }

	/**
	 * 
	 * @see com.boco.eoms.km.score.KmScoreWeightDao#saveKmScoreWeights(com.boco.eoms.km.score.KmScoreWeight)
	 *      
	 */
	public void saveKmScoreWeight(final KmScoreWeight kmScoreWeight) {
		if ((kmScoreWeight.getId() == null) || (kmScoreWeight.getId().equals("")))
			getHibernateTemplate().save(kmScoreWeight);
		else
			getHibernateTemplate().saveOrUpdate(kmScoreWeight);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.score.KmScoreWeightDao#removeKmScoreWeights(java.lang.String)
	 *      
	 */
//  public void removeKmScoreWeight(final String id) {
//		getHibernateTemplate().delete(getKmScoreWeight(id));
//	}
    
    public void removeKmScoreWeight(final String id) {
		getHibernateTemplate().delete(getKmScoreWeight(id));
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hqlUpdate = "update KmScoreSet kmScoreSet set kmScoreSet.isDeleted=1 where kmScoreSet.score_weight_id=?";
				Query query = session.createQuery(hqlUpdate);
				query.setString(0, id);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}
    public List getNextLevelKmScoreWeights(final String nodeId,final Integer nodeIdLength) {
    	String hql = " from KmScoreWeight kmScoreWeight where kmScoreWeight.isDeleted='0' and length(kmScoreWeight.nodeId)='"
				+ nodeIdLength + "' and kmScoreWeight.nodeId like '"+nodeId+"%'";
		hql += " order by kmScoreWeight.nodeId ";
		return getHibernateTemplate().find(hql);
    }

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmScoreWeight kmScoreWeight = this.getKmScoreWeight(id);
		if(kmScoreWeight==null){
			return "";
		}
		//TODO 请修改代码
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.score.KmScoreWeightDao#getKmScoreWeights(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmScoreWeights(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmScoreWeight kmScoreWeight";
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

	public void removeKmScoreWeightByNodeId(String nodeId) {
		final String hqlUpdate = "update KmScoreWeight kmScoreWeight set kmScoreWeight.isDeleted=1 where kmScoreWeight.nodeId like '"+nodeId+"%'";
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hqlUpdate);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}
	
}