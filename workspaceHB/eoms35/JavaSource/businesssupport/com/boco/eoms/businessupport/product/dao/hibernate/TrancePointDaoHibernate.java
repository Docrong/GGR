package com.boco.eoms.businessupport.product.dao.hibernate;

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
import com.boco.eoms.businessupport.product.dao.TrancePointDao;
import com.boco.eoms.businessupport.product.model.TrancePoint;

/**
 * <p>
 * Title:业务接入点 dao的hibernate实现
 * </p>
 * <p>
 * Description:业务接入点
 * </p>
 * <p>
 * Sun May 16 14:18:55 CST 2010
 * </p>
 * 
 * @author lizhigang
 * @version 3.5
 * 
 */
public class TrancePointDaoHibernate extends BaseDaoHibernate implements TrancePointDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.businessupport.product.TrancePointDao#getBusinessupports()
	 *      
	 */
	public List getBusinessupports() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrancePoint trancePoint";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.businessupport.product.TrancePointDao#getBusinessupport(java.lang.String)
	 *
	 */
	public TrancePoint getBusinessupport(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrancePoint trancePoint where trancePoint.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TrancePoint) result.iterator().next();
				} else {
					return new TrancePoint();
				}
			}
		};
		return (TrancePoint) getHibernateTemplate().execute(callback);
	}
	
	
	/**
	 *
	 * @see com.boco.eoms.businessupport.product.TrancePointDao#getBusinessupport(java.lang.String)
	 *
	 */
	public List getBusinessupportBySheetId(final String orderSheetId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrancePoint trancePoint where trancePoint.orderSheetId=:orderSheetId";
				Query query = session.createQuery(queryStr);
				query.setString("orderSheetId", orderSheetId);
//				query.setFirstResult(0);
//				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
//					return (Businessupport) result.iterator().next();
					return result;
				} else {
					return new ArrayList();
				}
			}
		};
		return (List)getHibernateTemplate().execute(callback);
	}
	
	/**
	 *
	 * @根据portDetailAdd 和 orderSheetid
	 *
	 */
	public List getTrancePointBySheetAndId(final String orderSheetId,final String portDetailAdd) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrancePoint trancePoint where trancePoint.orderSheetId=:orderSheetId and trancePoint.portDetailAdd=:portDetailAdd";
				Query query = session.createQuery(queryStr);
				query.setString("orderSheetId", orderSheetId);
				query.setString("portDetailAdd", portDetailAdd);
//				query.setFirstResult(0);
//				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
//					return (Businessupport) result.iterator().next();
					return result;
				} else {
					return new ArrayList();
				}
			}
		};
		return (List)getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.businessupport.product.TrancePointDao#saveBusinessupports(com.boco.eoms.businessupport.product.TrancePoint)
	 *      
	 */
	public void saveBusinessupport(final TrancePoint businessupport) {
		if ((businessupport.getId() == null) || (businessupport.getId().equals("")))
			getHibernateTemplate().save(businessupport);
		else
			getHibernateTemplate().saveOrUpdate(businessupport);
	}

	/**
	 * 
	 * @see com.boco.eoms.businessupport.product.TrancePointDao#removeBusinessupports(java.lang.String)
	 *      
	 */
    public void removeBusinessupport(final String id) {
		getHibernateTemplate().delete(getBusinessupport(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		TrancePoint businessupport = this.getBusinessupport(id);
		if(businessupport==null){
			return "";
		}
		//TODO 请修改代码
		return null;
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.businessupport.product.TrancePointDao#getBusinessupports(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getBusinessupports(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrancePoint trancePoint";
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