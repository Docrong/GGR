package com.boco.eoms.km.log.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.boco.eoms.km.log.dao.KmOperateDateLogDao;
import com.boco.eoms.km.log.model.KmOperateDateLog;

/**
 * <p>
 * Title:知识操作日统计表 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识操作日统计表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class KmOperateDateLogDaoHibernate extends BaseDaoHibernate implements KmOperateDateLogDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.log.KmOperateDateLogDao#getKmOperateDateLogs()
     */
    public List getKmOperateDateLogs() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmOperateDateLog kmOperateDateLog";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.log.KmOperateDateLogDao#getKmOperateDateLog(java.lang.String)
     */
    public KmOperateDateLog getKmOperateDateLog(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmOperateDateLog kmOperateDateLog where kmOperateDateLog.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (KmOperateDateLog) result.iterator().next();
                } else {
                    return new KmOperateDateLog();
                }
            }
        };
        return (KmOperateDateLog) getHibernateTemplate().execute(callback);
    }


    public KmOperateDateLog getKmOperateDateLog(final Date date, final String operateUser) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmOperateDateLog kmOperateDateLog where to_char(kmOperateDateLog.operateDate,'yyyy-mm-dd')='" + new SimpleDateFormat("yyyy-MM-dd").format(date) + "' and kmOperateDateLog.operateUser='" + operateUser + "'";
                Query query = session.createQuery(queryStr);
                //	query.setString("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                //	query.setString("user", operateUser);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (KmOperateDateLog) result.get(0);
                } else {
                    return new KmOperateDateLog();
                }
            }
        };
        return (KmOperateDateLog) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.log.KmOperateDateLogDao#saveKmOperateDateLogs(com.boco.eoms.km.log.KmOperateDateLog)
     */
    public void saveKmOperateDateLog(final KmOperateDateLog kmOperateDateLog) {
        if ((kmOperateDateLog.getId() == null) || (kmOperateDateLog.getId().equals("")))
            getHibernateTemplate().save(kmOperateDateLog);
        else
            getHibernateTemplate().saveOrUpdate(kmOperateDateLog);
    }

    /**
     * @see com.boco.eoms.km.log.KmOperateDateLogDao#removeKmOperateDateLogs(java.lang.String)
     */
    public void removeKmOperateDateLog(final String id) {
        getHibernateTemplate().delete(getKmOperateDateLog(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmOperateDateLog kmOperateDateLog = this.getKmOperateDateLog(id);
        if (kmOperateDateLog == null) {
            return "";
        }
        //TODO 请修改代码
        return null;//kmOperateDateLog.yourCode();
    }

    /**
     * @see com.boco.eoms.km.log.KmOperateDateLogDao#getKmOperateDateLogs(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmOperateDateLogs(final Integer curPage, final Integer pageSize,
                                    final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmOperateDateLog kmOperateDateLog";
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

    /**
     * 分页取列表
     * 提供给其他统计查询
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param sqlStr   执行sql
     * @return map中total为条数, result(list) curPage页的记录
     */

    public Map getKmOperateDateLogsForStatistic(final Integer curPage, final Integer pageSize,
                                                final String sqlStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryCountStr = "select count(*) from (" + sqlStr + ")";
			/*	int total = ((Integer) session.createSQLQuery(queryCountStr)
						.iterate().next()).intValue();*/
                Query query = session.createSQLQuery(sqlStr);
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", new Integer(result.size()));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
}

