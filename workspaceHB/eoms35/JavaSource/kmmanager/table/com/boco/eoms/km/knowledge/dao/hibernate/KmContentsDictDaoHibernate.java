package com.boco.eoms.km.knowledge.dao.hibernate;

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
import com.boco.eoms.km.knowledge.dao.KmContentsDictDao;
import com.boco.eoms.km.knowledge.model.KmContentsDict;

/**
 * <p>
 * Title:知识管理 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:33:19 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
public class KmContentsDictDaoHibernate extends BaseDaoHibernate implements KmContentsDictDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsDictDao#getKmContentsDicts()
	 *      
	 */
	public List getKmContentsDicts() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmContentsDict kmContentsDict";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.knowledge.KmContentsDictDao#getKmContentsDict(java.lang.String)
	 *
	 */
	public KmContentsDict getKmContentsDict(final String id) {
    	KmContentsDict kmContentsDict = (KmContentsDict) getHibernateTemplate().get(KmContentsDict.class, id);
		if (kmContentsDict == null) {
			kmContentsDict = new KmContentsDict();
		}
		return kmContentsDict;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsDictDao#saveKmContentsDicts(com.boco.eoms.km.knowledge.KmContentsDict)
	 *      
	 */
	public void saveKmContentsDict(final KmContentsDict kmContentsDict) {
		if ((kmContentsDict.getId() == null) || (kmContentsDict.getId().equals("")))
			getHibernateTemplate().save(kmContentsDict);
		else
			getHibernateTemplate().saveOrUpdate(kmContentsDict);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsDictDao#removeKmContentsDicts(java.lang.String)
	 *      
	 */
    public void removeKmContentsDict(final String id) {
		getHibernateTemplate().delete(getKmContentsDict(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmContentsDict kmContentsDict = this.getKmContentsDict(id);
		if(kmContentsDict==null){
			return "";
		}
		//TODO 请修改代码
		return null;//kmContentsDict.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsDictDao#getKmContentsDicts(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmContentsDicts(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmContentsDict kmContentsDict";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();

				List result = new ArrayList();
				if(total >0){
					Query query = session.createQuery(queryStr);
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