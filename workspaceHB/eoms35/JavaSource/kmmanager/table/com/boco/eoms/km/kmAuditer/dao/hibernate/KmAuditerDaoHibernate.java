package com.boco.eoms.km.kmAuditer.dao.hibernate;

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
import com.boco.eoms.km.kmAuditer.dao.KmAuditerDao;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;

/**
 * <p>
 * Title:知识管理审核人配置表 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识管理审核人配置表
 * </p>
 * <p>
 * Wed Apr 29 15:46:36 CST 2009
 * </p>
 * 
 * @author 戴志刚
 * @version 1.0
 * 
 */
public class KmAuditerDaoHibernate extends BaseDaoHibernate implements KmAuditerDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.kmauditer.KmAuditerDao#getKmAuditers()
	 *      
	 */
	public List getKmAuditers() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAuditer kmAuditer";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.kmauditer.KmAuditerDao#getKmAuditer(java.lang.String)
	 *
	 */
	public KmAuditer getKmAuditer(final String id) {
		KmAuditer kmAuditer = (KmAuditer) getHibernateTemplate().get(KmAuditer.class, id);
		if (kmAuditer == null) {
			kmAuditer = new KmAuditer();
		}
		return kmAuditer;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.kmauditer.KmAuditerDao#saveKmAuditers(com.boco.eoms.km.kmauditer.KmAuditer)
	 *      
	 */
	public void saveKmAuditer(final KmAuditer kmAuditer) {
		if ((kmAuditer.getId() == null) || (kmAuditer.getId().equals("")))
			getHibernateTemplate().save(kmAuditer);
		else
			getHibernateTemplate().saveOrUpdate(kmAuditer);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.kmauditer.KmAuditerDao#removeKmAuditers(java.lang.String)
	 *      
	 */
    public void removeKmAuditer(final String id) {
		//getHibernateTemplate().delete(getKmAuditer(id));
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "deleted KmAuditer kmAuditer where kmAuditer.id=?";				
				Query query = session.createQuery(queryStr);
				query.setString(0, id);
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
		KmAuditer kmAuditer = this.getKmAuditer(id);
		if(kmAuditer==null){
			return "";
		}
		//TODO 请修改代码
		return "";
//		return kmAuditer.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.kmauditer.KmAuditerDao#getKmAuditers(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmAuditers(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmAuditer kmAuditer ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);
				else
					queryStr.append("where kmTableGeneral.isDeleted='0' ");

				StringBuffer queryCountStr = new StringBuffer("select count(kmAuditer.id) ");
				queryCountStr.append(queryStr);
				
				int total = ((Integer) session.createQuery(queryCountStr.toString())
						.iterate().next()).intValue();
				
				List result = new ArrayList();
				if(total >0){
					Query query = session.createQuery(queryStr.toString());
					query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					result = query.list();					
				}

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
	 * @see com.boco.eoms.km.kmauditer.KmAuditerDao#getKmAuditer(java.lang.String)
	 *
	 */
	public KmAuditer getKmAuditerByTheme(final String themeId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAuditer kmAuditer where kmAuditer.themeId=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, themeId);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmAuditer) result.iterator().next();
				} else {
					return new KmAuditer();
				}
			}
		};
		return (KmAuditer) getHibernateTemplate().execute(callback);
	}

	/**
	 *
	 * @see com.boco.eoms.km.kmauditer.KmAuditerDao#getKmAuditerByNodeid(java.lang.String)
	 *
	 */
	public KmAuditer getKmAuditerByNodeid(final String nodeId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAuditer kmAuditer where kmAuditer.nodeId=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, nodeId);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmAuditer) result.iterator().next();
				} else {
					return new KmAuditer();
				}
			}
		};
		return (KmAuditer) getHibernateTemplate().execute(callback);
	}
}