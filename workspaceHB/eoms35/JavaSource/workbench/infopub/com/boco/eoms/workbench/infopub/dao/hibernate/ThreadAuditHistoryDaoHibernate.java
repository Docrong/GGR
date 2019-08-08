package com.boco.eoms.workbench.infopub.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ThreadAuditHistoryDaoHibernate extends BaseDaoHibernate implements
        ThreadAuditHistoryDao {

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao#getThreadAuditHistorys(com.boco.eoms.workbench.infopub.model.ThreadAuditHistory)
     */
    public List getThreadAuditHistorys(
            final ThreadAuditHistory threadAuditHistory) {
        return getHibernateTemplate().find("from ThreadAuditHistory");

        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (threadAuditHistory == null) {
         * return getHibernateTemplate().find("from ThreadAuditHistory"); } else { //
         * filter on properties set in the threadAuditHistory HibernateCallback
         * callback = new HibernateCallback() { public Object
         * doInHibernate(Session session) throws HibernateException { Example ex =
         * Example.create(threadAuditHistory).ignoreCase().enableLike(MatchMode.ANYWHERE);
         * return
         * session.createCriteria(ThreadAuditHistory.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao#getThreadAuditHistory(String
     * id)
     */
    public ThreadAuditHistory getThreadAuditHistory(final String id) {
        ThreadAuditHistory threadAuditHistory = (ThreadAuditHistory) getHibernateTemplate()
                .get(ThreadAuditHistory.class, id);
        if (threadAuditHistory == null) {
            throw new ObjectRetrievalFailureException(ThreadAuditHistory.class,
                    id);
        }

        return threadAuditHistory;
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao#saveThreadAuditHistory(ThreadAuditHistory
     * threadAuditHistory)
     */
    public void saveThreadAuditHistory(
            final ThreadAuditHistory threadAuditHistory) {
        if ((threadAuditHistory.getId() == null)
                || (threadAuditHistory.getId().equals("")))
            getHibernateTemplate().save(threadAuditHistory);
        else
            getHibernateTemplate().saveOrUpdate(threadAuditHistory);
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao#removeThreadAuditHistory(String
     * id)
     */
    public void removeThreadAuditHistory(final String id) {
        getHibernateTemplate().delete(getThreadAuditHistory(id));
    }

    /**
     * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
     * where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getThreadAuditHistorys(final Integer curPage,
                                      final Integer pageSize, final String whereStr) {
        // filter on properties set in the threadAuditHistory
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from ThreadAuditHistory";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;
                // 按审核时间及提交时间排序
                queryStr += " order by submitTime desc,auditTime desc";
                Integer total = (Integer) session.createQuery(queryCountStr)
                        .iterate().next();
                Query query = session.createQuery(queryStr);
                query
                        .setFirstResult(pageSize.intValue()
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

    public Map getThreadAuditHistorys(final Integer curPage,
                                      final Integer pageSize) {
        return this.getThreadAuditHistorys(curPage, pageSize, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao#getAuditHistoryByThreadId(java.lang.String)
     */
    public List getAuditHistoryByThreadId(String threadId) {
        return (List) getHibernateTemplate().find(
                "from ThreadAuditHistory t where threadId='" + threadId + "'");
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao#removeThreadAuditHistory(com.boco.eoms.workbench.infopub.model.ThreadAuditHistory)
     */
    public void removeThreadAuditHistory(ThreadAuditHistory auditHistory) {
        getHibernateTemplate().delete(auditHistory);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao#getCurrentThreadAuditHistory(java.lang.String)
     */
    public ThreadAuditHistory getCurrentThreadAuditHistory(final String threadId) {
        // filter on properties set in the threadAuditHistory
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from ThreadAuditHistory where threadId=:threadId and isCurrent=:isCurrent";

                Query query = session.createQuery(queryStr);
                query.setString("threadId", threadId);
                query.setString("isCurrent", InfopubConstants.TRUE_STR);
                query.setFirstResult(0);
                query.setMaxResults(1);
                ThreadAuditHistory history = null;
                if (query.list().size() > 0) {
                    history = (ThreadAuditHistory) query.iterate().next();
                }
                return history;
            }
        };
        return (ThreadAuditHistory) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao#getThreadAuditHistorsByThreadId(java.lang.String)
     */
    public List getThreadAuditHistorsByThreadId(String threadId) {
        return getHibernateTemplate().find(
                "from ThreadAuditHistory where threadId='" + threadId + "'");
    }

}
