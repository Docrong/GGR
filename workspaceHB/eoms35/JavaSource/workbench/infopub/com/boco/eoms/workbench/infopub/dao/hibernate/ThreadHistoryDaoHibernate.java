package com.boco.eoms.workbench.infopub.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;
import com.boco.eoms.workbench.infopub.model.ThreadCountHistory;
import com.boco.eoms.common.util.StaticMethod;

/**
 * <p>
 * Title:信息阅读历史记录dao的hibernate实现
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 5:28:02 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */
public class ThreadHistoryDaoHibernate extends BaseDaoHibernate implements
        ThreadHistoryDao {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao#getThreadHistorys(java.lang.Integer,
     *      java.lang.Integer, java.lang.String, java.lang.String)
     */
    public Map getThreadHistorys(Integer curPage, Integer pageSize,
                                 String whereStr, String orderby) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao#getThreadHistorys(com.boco.eoms.workbench.infopub.model.ThreadHistory)
     */
    public List getThreadHistorys(final ThreadHistory threadHistory) {
        return getHibernateTemplate().find("from ThreadHistory");

        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (threadHistory == null) {
         * return getHibernateTemplate().find("from ThreadHistory"); } else { //
         * filter on properties set in the threadHistory HibernateCallback
         * callback = new HibernateCallback() { public Object
         * doInHibernate(Session session) throws HibernateException { Example ex =
         * Example.create(threadHistory).ignoreCase().enableLike(MatchMode.ANYWHERE);
         * return session.createCriteria(ThreadHistory.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao#getThreadHistory(String
     * id)
     */
    public ThreadHistory getThreadHistory(final String id) {
        ThreadHistory threadHistory = (ThreadHistory) getHibernateTemplate()
                .get(ThreadHistory.class, id);
        // if (threadHistory == null) {
        // throw new ObjectRetrievalFailureException(ThreadHistory.class, id);
        // }

        return threadHistory;
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao#saveThreadHistory(ThreadHistory
     * threadHistory)
     */
    public void saveThreadHistory(final ThreadHistory threadHistory) {
        if ((threadHistory.getId() == null)
                || (threadHistory.getId().equals("")))
            getHibernateTemplate().save(threadHistory);
        else
            getHibernateTemplate().saveOrUpdate(threadHistory);
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao#removeThreadHistory(String
     * id)
     */
    public void removeThreadHistory(final String id) {
        ThreadHistory history = getThreadHistory(id);
        // 若有查看历史记录则删除
        if (history != null) {
            // getHibernateTemplate().delete(history);
            // 设置删除标置
            history.setIsDel(Constants.DELETED_FLAG);
            this.saveThreadHistory(history);
        }

    }

    /**
     * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
     * where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getThreadHistorys(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        // filter on properties set in the threadHistory
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from ThreadHistory";

                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;

                String queryCountStr = "select count(*) " + queryStr;
                // 按时间排序
                queryStr += " order by readTime desc";

                Integer total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next());
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

    public Map getThreadHistorys(final Integer curPage, final Integer pageSize) {
        return this.getThreadHistorys(curPage, pageSize, null);
    }

    /**
     * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
     * where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getThreadCountHistory(final Integer curPage,
                                     final Integer pageSize, final String whereStr) {
        // filter on properties set in the threadHistory
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                String queryStr = "";
                queryStr = "select count(*),threadId,userId from ThreadHistory";
                Integer total = null;
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                // 按时间排序
                queryStr += " group by userId,threadId";
                Query query = session.createQuery(queryStr);
                query
                        .setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List result = query.list();
                HashMap map = new HashMap();
                List resultCount = new ArrayList();
                for (int i = 0; i < result.size(); i++) {
                    Object[] obj = (Object[]) result.get(i);
                    ThreadCountHistory threadCountHistory = new ThreadCountHistory();
                    threadCountHistory.setCount(StaticMethod
                            .nullObject2String(obj[0]));
                    threadCountHistory.setThreadId(StaticMethod
                            .nullObject2String(obj[1]));
                    threadCountHistory.setUserId(StaticMethod
                            .nullObject2String(obj[2]));
                    resultCount.add(threadCountHistory);
                }
                total = Integer.valueOf(Integer.toString(result.size()));
                map.put("total", total);
                map.put("result", resultCount);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao#getThreadHistorysByThreadId(java.lang.String)
     */
    public List getThreadHistorysByThreadId(String threadId) {
        return this.getHibernateTemplate().find(
                "from ThreadHistory threadHistory where threadHistory.threadId='"
                        + threadId + "'");
    }

}
