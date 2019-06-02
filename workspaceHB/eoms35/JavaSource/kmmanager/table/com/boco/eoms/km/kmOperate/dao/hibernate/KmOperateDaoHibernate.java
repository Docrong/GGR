package com.boco.eoms.km.kmOperate.dao.hibernate;

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
import com.boco.eoms.km.kmOperate.dao.KmOperateDao;
import com.boco.eoms.km.kmOperate.model.KmOperate;

/**
 * <p>
 * Title:知识管理权限配置 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识管理权限配置
 * </p>
 * <p>
 * Fri May 22 14:03:33 CST 2009
 * </p>
 * 
 * @author 戴志刚
 * @version 1.0
 * 
 */
public class KmOperateDaoHibernate extends BaseDaoHibernate implements KmOperateDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.kmoperate.KmOperateDao#getKmOperates()
	 *      
	 */
	public List getKmOperates(final String nodeId,final String nodeType) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmOperate kmOperate where kmOperate.nodeId=? and kmOperate.nodeType=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, nodeId);
				query.setString(1, nodeType);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
    /**
	 * 
	 * @see com.boco.eoms.km.kmoperate.KmOperateDao#getKmOperates()
	 *      
	 */
	public List getKmOperates(final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmOperate kmOperate " + whereStr;
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
    /**
	 * 
	 * @see com.boco.eoms.km.kmoperate.KmOperateDao#getKmOperates()
	 *      
	 */
	public List getKmOperates() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmOperate kmOperate";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.kmoperate.KmOperateDao#getKmOperate(java.lang.String)
	 *
	 */
	public KmOperate getKmOperate(final String id) {
		KmOperate kmOperate = (KmOperate) getHibernateTemplate().get(KmOperate.class, id);
		if (kmOperate == null) {
			kmOperate = new KmOperate();
		}
		return kmOperate;
	}

	/**
	 *
	 * @see com.boco.eoms.km.kmOperate.KmOperateDao#getKmOperate(java.lang.String,java.lang.String)
	 *
	 */
	public KmOperate getKmOperate(final String nodeId,final String nodeType,final String orgId,final String orgType) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmOperate kmOperate ");
				queryStr.append("where kmOperate.nodeId=? and kmOperate.nodeType=? ");
				queryStr.append("and kmOperate.orgId=? and kmOperate.orgType=?");
				Query query = session.createQuery(queryStr.toString());
				query.setString(0, nodeId);
				query.setString(1, nodeType);
				query.setString(2, orgId);
				query.setString(3, orgType);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmOperate) result.iterator().next();
				} else {
					return new KmOperate();
				}
			}
		};
		return (KmOperate) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.kmoperate.KmOperateDao#saveKmOperates(com.boco.eoms.km.kmoperate.KmOperate)
	 *      
	 */
	public void saveKmOperate(final KmOperate kmOperate) {
		if ((kmOperate.getId() == null) || (kmOperate.getId().equals("")))
			getHibernateTemplate().save(kmOperate);
		else
			getHibernateTemplate().saveOrUpdate(kmOperate);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.kmoperate.KmOperateDao#removeKmOperates(java.lang.String)
	 *      
	 */
    public void removeKmOperate(final String id) {
		getHibernateTemplate().delete(getKmOperate(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmOperate kmOperate = this.getKmOperate(id);
		if(kmOperate==null){
			return "";
		}
		//TODO 请修改代码
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.kmoperate.KmOperateDao#getKmOperates(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmOperates(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmOperate kmOperate ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);

				StringBuffer queryCountStr = new StringBuffer("select count(kmOperate.id) ");
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

	public List getKmOperatesByOrgIdAndOrgType(final String nodeType, final String orgId, final String orgType) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmOperate kmOperate where kmOperate.nodeType=? and kmOperate.orgType=? and kmOperate.orgId=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, nodeType);
				query.setString(1, orgType);
				query.setString(2, orgId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
}