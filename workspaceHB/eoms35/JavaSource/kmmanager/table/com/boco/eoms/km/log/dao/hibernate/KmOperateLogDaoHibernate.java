package com.boco.eoms.km.log.dao.hibernate;

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
import com.boco.eoms.km.log.dao.KmOperateLogDao;
import com.boco.eoms.km.log.model.KmOperateLog;

/**
 * <p>
 * Title:知识操作历史记录表 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识操作历史记录表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class KmOperateLogDaoHibernate extends BaseDaoHibernate implements KmOperateLogDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.log.KmOperateLogDao#getKmOperateLogs()
	 *      
	 */
	public List getKmOperateLogs() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmOperateLog kmOperateLog";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.log.KmOperateLogDao#getKmOperateLog(java.lang.String)
	 *
	 */
	public KmOperateLog getKmOperateLog(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmOperateLog kmOperateLog where kmOperateLog.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmOperateLog) result.iterator().next();
				} else {
					return new KmOperateLog();
				}
			}
		};
		return (KmOperateLog) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.log.KmOperateLogDao#saveKmOperateLogs(com.boco.eoms.km.log.KmOperateLog)
	 *      
	 */
	public void saveKmOperateLog(final KmOperateLog kmOperateLog) {
		if ((kmOperateLog.getId() == null) || (kmOperateLog.getId().equals("")))
			getHibernateTemplate().save(kmOperateLog);
		else
			getHibernateTemplate().saveOrUpdate(kmOperateLog);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.log.KmOperateLogDao#removeKmOperateLogs(java.lang.String)
	 *      
	 */
    public void removeKmOperateLog(final String id) {
		getHibernateTemplate().delete(getKmOperateLog(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmOperateLog kmOperateLog = this.getKmOperateLog(id);
		if(kmOperateLog==null){
			return "";
		}
		//TODO 请修改代码
		return null;//kmOperateLog.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.log.KmOperateLogDao#getKmOperateLogs(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmOperateLogs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmOperateLog kmOperateLog";
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