package com.boco.eoms.duty.dao.hibernate;

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
import com.boco.eoms.duty.dao.AttemperLogDao;
import com.boco.eoms.duty.model.AttemperLog;

/**
 * <p>
 * Title:网调日志记录 dao的hibernate实现
 * </p>
 * <p>
 * Description:网调日志记录
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class AttemperLogDaoHibernate extends BaseDaoHibernate implements AttemperLogDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.duty.AttemperLogDao#getAttemperLogs()
	 *      
	 */
	public List getAttemperLogs() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperLog attemperLog";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.duty.AttemperLogDao#getAttemperLog(java.lang.String)
	 *
	 */
	public AttemperLog getAttemperLog(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperLog attemperLog where attemperLog.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (AttemperLog) result.iterator().next();
				} else {
					return new AttemperLog();
				}
			}
		};
		return (AttemperLog) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperLogDao#saveAttemperLogs(com.boco.eoms.duty.AttemperLog)
	 *      
	 */
	public void saveAttemperLog(final AttemperLog attemperLog) {
		if ((attemperLog.getId() == null) || (attemperLog.getId().equals("")))
			getHibernateTemplate().save(attemperLog);
		else
			getHibernateTemplate().saveOrUpdate(attemperLog);
	}

	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperLogDao#removeAttemperLogs(java.lang.String)
	 *      
	 */
    public void removeAttemperLog(final String id) {
		getHibernateTemplate().delete(getAttemperLog(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		AttemperLog attemperLog = this.getAttemperLog(id);
		if(attemperLog==null){
			return "";
		}
		//TODO 请修改代码
		return attemperLog.getTitle();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperLogDao#getAttemperLogs(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getAttemperLogs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperLog attemperLog";
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