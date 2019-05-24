package com.boco.eoms.km.expert.dao.hibernate;

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
import com.boco.eoms.km.expert.dao.KmExpertExpDao;
import com.boco.eoms.km.expert.model.KmExpertExp;

/**
 * <p>
 * Title:工作经验 dao的hibernate实现
 * </p>
 * <p>
 * Description:工作经验
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertExpDaoHibernate extends BaseDaoHibernate implements KmExpertExpDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.expert.exp.KmExpertExpDao#getKmExpertExps()
	 *      
	 */
	public List getKmExpertExps() {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExpertExp kmExpertExp";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.expert.exp.KmExpertExpDao#getKmExpertExp(java.lang.String)
	 *
	 */
	public KmExpertExp getKmExpertExp(final String id) {
		KmExpertExp kmExpertExp = (KmExpertExp) getHibernateTemplate().get(KmExpertExp.class, id);
		if (kmExpertExp == null) {
			kmExpertExp = new KmExpertExp();
		}
		return kmExpertExp;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.expert.exp.KmExpertExpDao#saveKmExpertExps(com.boco.eoms.expert.exp.KmExpertExp)
	 *      
	 */
	public void saveKmExpertExp(final KmExpertExp kmExpertExp) {
		if ((kmExpertExp.getId() == null) || (kmExpertExp.getId().equals("")))
			getHibernateTemplate().save(kmExpertExp);
		else
			getHibernateTemplate().saveOrUpdate(kmExpertExp);
	}

	/**
	 * 
	 * @see com.boco.eoms.expert.exp.KmExpertExpDao#removeKmExpertExps(java.lang.String)
	 *      
	 */
    public void removeKmExpertExp(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "delete from KmExpertExp kmExpertExp where kmExpertExp.id=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, id);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}
    
    
    /**
     * 根据id批量删除工作经验
     * @param id 主键
     * add by lijun @2009-06-20
     */
    public void removeKmExpertExps(final String ids[]) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "delete from KmExpertExp kmExpertExp where kmExpertExp.id in (:ids)";
				Query query = session.createQuery(queryStr);
				query.setParameterList("ids", ids);
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
		KmExpertExp kmExpertExp = this.getKmExpertExp(id);
		if(kmExpertExp==null){
			return "";
		}
		//TODO 请修改代码
		return kmExpertExp.getExpertCompany();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.expert.exp.KmExpertExpDao#getKmExpertExps(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExpertExps(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExpertExp kmExpertExp";
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

	public Map getKmExpertExpsByUserId(final Integer curPage, final Integer pageSize, final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {								
				String queryCountStr = "select count(kmExpertExp.id) from KmExpertExp kmExpertExp where kmExpertExp.userId=?";
				Query queryCount = session.createQuery(queryCountStr);
				queryCount.setString(0, userId);
				int total = ((Integer)queryCount.iterate().next()).intValue();
				
				List result = new ArrayList();
				if(total >0){
					String queryStr = "from KmExpertExp kmExpertExp where kmExpertExp.userId=?";
					Query query = session.createQuery(queryStr);
					query.setString(0, userId);
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
}