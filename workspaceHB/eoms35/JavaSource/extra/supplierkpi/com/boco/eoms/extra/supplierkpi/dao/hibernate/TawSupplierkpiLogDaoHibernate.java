
package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiLog;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiLogDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSupplierkpiLogDaoHibernate extends BaseDaoHibernate implements TawSupplierkpiLogDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiLogDao#getTawSupplierkpiLogs(com.boco.eoms.commons.sample.model.TawSupplierkpiLog)
     */
    public List getTawSupplierkpiLogs(final TawSupplierkpiLog tawSupplierkpiLog) {
        return getHibernateTemplate().find("from TawSupplierkpiLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSupplierkpiLog == null) {
            return getHibernateTemplate().find("from TawSupplierkpiLog");
        } else {
            // filter on properties set in the tawSupplierkpiLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSupplierkpiLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSupplierkpiLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiLogDao#getTawSupplierkpiLog(String id)
     */
    public TawSupplierkpiLog getTawSupplierkpiLog(final String id) {
        TawSupplierkpiLog tawSupplierkpiLog = (TawSupplierkpiLog) getHibernateTemplate().get(TawSupplierkpiLog.class, id);
        if (tawSupplierkpiLog == null) {
            throw new ObjectRetrievalFailureException(TawSupplierkpiLog.class, id);
        }

        return tawSupplierkpiLog;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiLogDao#saveTawSupplierkpiLog(TawSupplierkpiLog tawSupplierkpiLog)
     */
    public void saveTawSupplierkpiLog(final TawSupplierkpiLog tawSupplierkpiLog) {
        if ((tawSupplierkpiLog.getId() == null) || (tawSupplierkpiLog.getId().equals("")))
            getHibernateTemplate().save(tawSupplierkpiLog);
        else
            getHibernateTemplate().saveOrUpdate(tawSupplierkpiLog);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiLogDao#removeTawSupplierkpiLog(String id)
     */
    public void removeTawSupplierkpiLog(final String id) {
        getHibernateTemplate().delete(getTawSupplierkpiLog(id));
    }

    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawSupplierkpiLogs(final Integer curPage, final Integer pageSize, final String whereStr) {
        // filter on properties set in the tawSupplierkpiLog
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from TawSupplierkpiLog";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                Integer total = (Integer) session.createQuery(queryCountStr).iterate()
                        .next();
                Query query = session.createQuery(queryStr);
                query.setFirstResult(pageSize.intValue()
                        * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map getTawSupplierkpiLogs(final Integer curPage, final Integer pageSize) {
        return this.getTawSupplierkpiLogs(curPage, pageSize, null);
    }

    public List getTawSupplierkpiLogs(final String whereStr) {
        return getHibernateTemplate().find(whereStr);
    }

    public List getTawSupplierkpiLogs(final int startPage, final int row, final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(whereStr);
                query.setFirstResult(startPage);
                query.setMaxResults(row);
                List list = query.list();
                return list;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public int getLogsCount(final String whereStr) {
        String queryCountStr = "select count(*) " + whereStr;
        List list = this.getHibernateTemplate().find(queryCountStr);
        return Integer.parseInt(list.get(0).toString());
    }
}
