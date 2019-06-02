package com.boco.eoms.km.table.dao.hibernate;

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
import com.boco.eoms.km.table.dao.KmTableDictDao;
import com.boco.eoms.km.table.model.KmTableDict;

/**
 * <p>
 * Title:知识字段字典 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识字段字典
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 * 
 * @author 吕卫华
 * @version 1.0
 * 
 */
public class KmTableDictDaoHibernate extends BaseDaoHibernate implements KmTableDictDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.table.KmTableDictDao#getKmTableDicts()
	 *      
	 */
	public List getKmTableDicts() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmTableDict kmTableDict";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.table.KmTableDictDao#getKmTableDict(java.lang.String)
	 *
	 */
	public KmTableDict getKmTableDict(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmTableDict kmTableDict where kmTableDict.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmTableDict) result.iterator().next();
				} else {
					return new KmTableDict();
				}
			}
		};
		return (KmTableDict) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.table.KmTableDictDao#saveKmTableDicts(com.boco.eoms.km.table.KmTableDict)
	 *      
	 */
	public void saveKmTableDict(final KmTableDict kmTableDict) {
		if ((kmTableDict.getId() == null) || (kmTableDict.getId().equals("")))
			getHibernateTemplate().save(kmTableDict);
		else
			getHibernateTemplate().saveOrUpdate(kmTableDict);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.table.KmTableDictDao#removeKmTableDicts(java.lang.String)
	 *      
	 */
    public void removeKmTableDict(final String id) {
		getHibernateTemplate().delete(getKmTableDict(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		if(id == null || id.equals("")){
			return "";
		}		
		KmTableDict kmTableDict = this.getKmTableDict(id);
		if(kmTableDict==null){
			return "";
		}
		//TODO 请修改代码
		return null;//kmTableDict.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.table.KmTableDictDao#getKmTableDicts(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmTableDicts(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmTableDict kmTableDict";
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