package com.boco.eoms.commons.mms.msssubscribe.dao.hibernate;

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
import com.boco.eoms.commons.mms.msssubscribe.dao.MsssubscribeDao;
import com.boco.eoms.commons.mms.msssubscribe.model.Msssubscribe;

/**
 * <p>
 * Title:彩信报订阅 dao的hibernate实现
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Fri Feb 20 11:32:20 CST 2009
 * </p>
 * 
 * @author 李志刚
 * @version 3.5
 * 
 */
public class MsssubscribeDaoHibernate extends BaseDaoHibernate implements MsssubscribeDao,
		ID2NameDAO {
	
    public Msssubscribe getMsssubscribeForMmsreportTemplateId(final String id) {
    	Msssubscribe msssubscribe = null;
    	HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Msssubscribe msssubscribe where msssubscribe.mmsreport_templateId='" + id + "'";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		
		List list = (List)getHibernateTemplate().execute(callback);
		if(list.size() == 1)
		{
			msssubscribe = (Msssubscribe)list.get(0);
		}
		
		return msssubscribe;
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.mms.msssubscribe.MsssubscribeDao#getMsssubscribes()
	 *      
	 */
	public List getMsssubscribes() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Msssubscribe msssubscribe";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.commons.mms.msssubscribe.MsssubscribeDao#getMsssubscribe(java.lang.String)
	 *
	 */
	public Msssubscribe getMsssubscribe(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Msssubscribe msssubscribe where msssubscribe.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (Msssubscribe) result.iterator().next();
				} else {
					return new Msssubscribe();
				}
			}
		};
		return (Msssubscribe) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.commons.mms.msssubscribe.MsssubscribeDao#saveMsssubscribes(com.boco.eoms.commons.mms.msssubscribe.Msssubscribe)
	 *      
	 */
	public void saveMsssubscribe(final Msssubscribe msssubscribe) {
		if ((msssubscribe.getId() == null) || (msssubscribe.getId().equals("")))
			getHibernateTemplate().save(msssubscribe);
		else
			getHibernateTemplate().saveOrUpdate(msssubscribe);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.mms.msssubscribe.MsssubscribeDao#removeMsssubscribes(java.lang.String)
	 *      
	 */
    public void removeMsssubscribe(final String id) {
		getHibernateTemplate().delete(getMsssubscribe(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		Msssubscribe msssubscribe = this.getMsssubscribe(id);
		if(msssubscribe==null){
			return "";
		}
		//TODO 请修改代码
		return "";//msssubscribe.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.commons.mms.msssubscribe.MsssubscribeDao#getMsssubscribes(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getMsssubscribes(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Msssubscribe msssubscribe ";
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