package com.boco.eoms.commons.mms.statreport.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.mms.statreport.dao.StatreportDao;
import com.boco.eoms.commons.mms.statreport.model.Statreport;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

/**
 * <p>
 * Title:报表实例 dao的hibernate实现
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 11:35:28 CST 2009
 * </p>
 * 
 * @author 李振友
 * @version 3.5
 * 
 */
public class StatreportDaoHibernate extends BaseDaoHibernate implements StatreportDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.commons.mms.statreport.StatreportDao#getStatreports()
	 *      
	 */
	public List getStatreports() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Statreport statreport";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	public List getStatreportForMmsreportId(final String mmsreportId)
	{
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Statreport statreport where mmsreport_id='" + mmsreportId +"'";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		
		return (List)getHibernateTemplate().execute(callback);
	}
	
	/**
	 *
	 * @see com.boco.eoms.commons.mms.statreport.StatreportDao#getStatreport(java.lang.String)
	 *
	 */
	public Statreport getStatreport(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Statreport statreport where statreport.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (Statreport) result.iterator().next();
				} else {
					return new Statreport();
				}
			}
		};
		return (Statreport) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.commons.mms.statreport.StatreportDao#saveStatreports(com.boco.eoms.commons.mms.statreport.Statreport)
	 *      
	 */
	public void saveStatreport(final Statreport statreport) {
		if ((statreport.getId() == null) || (statreport.getId().equals("")))
			getHibernateTemplate().save(statreport);
		else
			getHibernateTemplate().saveOrUpdate(statreport);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.mms.statreport.StatreportDao#removeStatreports(java.lang.String)
	 *      
	 */
    public void removeStatreport(final String id) {
		getHibernateTemplate().delete(getStatreport(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		Statreport statreport = this.getStatreport(id);
		if(statreport==null){
			return "";
		}
		//TODO 请修改代码
		return "请修改代码";//statreport.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.commons.mms.statreport.StatreportDao#getStatreports(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getStatreports(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Statreport statreport";
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