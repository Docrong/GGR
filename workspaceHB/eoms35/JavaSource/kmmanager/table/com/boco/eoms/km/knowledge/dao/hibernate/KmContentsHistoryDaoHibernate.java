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
import com.boco.eoms.km.knowledge.dao.KmContentsHistoryDao;
import com.boco.eoms.km.knowledge.model.KmContentsHistory;

/**
 * <p>
 * Title:知识管理 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:32:33 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public class KmContentsHistoryDaoHibernate extends BaseDaoHibernate implements KmContentsHistoryDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.knowledge.KmContentsHistoryDao#getKmContentsHistorys()
     */
    public List getKmContentsHistorys() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmContentsHistory kmContentsHistory";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.knowledge.KmContentsHistoryDao#getKmContentsHistory(java.lang.String)
     */
    public KmContentsHistory getKmContentsHistory(final String id) {
        KmContentsHistory kmContentsHistory = (KmContentsHistory) getHibernateTemplate().get(KmContentsHistory.class, id);
        if (kmContentsHistory == null) {
            kmContentsHistory = new KmContentsHistory();
        }
        return kmContentsHistory;
    }

    /**
     * @see com.boco.eoms.km.knowledge.KmContentsHistoryDao#saveKmContentsHistorys(com.boco.eoms.km.knowledge.KmContentsHistory)
     */
    public void saveKmContentsHistory(final KmContentsHistory kmContentsHistory) {
        if ((kmContentsHistory.getId() == null) || (kmContentsHistory.getId().equals("")))
            getHibernateTemplate().save(kmContentsHistory);
        else
            getHibernateTemplate().saveOrUpdate(kmContentsHistory);
    }

    /**
     * @see com.boco.eoms.km.knowledge.KmContentsHistoryDao#removeKmContentsHistorys(java.lang.String)
     */
    public void removeKmContentsHistory(final String id) {
        getHibernateTemplate().delete(getKmContentsHistory(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmContentsHistory kmContentsHistory = this.getKmContentsHistory(id);
        if (kmContentsHistory == null) {
            return "";
        }
        //TODO 请修改代码
        return null;//kmContentsHistory.yourCode();
    }

    /**
     * @see com.boco.eoms.km.knowledge.KmContentsHistoryDao#getKmContentsHistorys(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmContentsHistorys(final Integer curPage, final Integer pageSize,
                                     final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmContentsHistory kmContentsHistory ");
                if (whereStr != null && whereStr.length() > 0)
                    queryStr.append(whereStr);

                StringBuffer queryCountStr = new StringBuffer("select count(kmContentsHistory.id) ");
                queryCountStr.append(queryStr);

                int total = ((Integer) session.createQuery(queryCountStr.toString())
                        .iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    Query query = session.createQuery(queryStr.toString());
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