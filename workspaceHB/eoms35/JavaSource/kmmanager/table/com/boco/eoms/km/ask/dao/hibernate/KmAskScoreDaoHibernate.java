package com.boco.eoms.km.ask.dao.hibernate;

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
import com.boco.eoms.km.ask.dao.KmAskScoreDao;
import com.boco.eoms.km.ask.model.KmAskScore;

/**
 * <p>
 * Title:问答积分配置 dao的hibernate实现
 * </p>
 * <p>
 * Description:问答积分配置
 * </p>
 * <p>
 * Wed Aug 19 16:41:06 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmAskScoreDaoHibernate extends BaseDaoHibernate implements KmAskScoreDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.ask.KmAskScoreDao#getKmAskScores()
     */
    public List getKmAskScores() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskScore kmAskScore";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据操作id 查询
     *
     * @param operateId
     * @return
     */
    public KmAskScore getKmAskScoreByOperateId(final Integer operateId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskScore kmAskScore where kmAskScore.operateId=:operateId";
                Query query = session.createQuery(queryStr);
                query.setInteger("operateId", operateId.intValue());
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (KmAskScore) result.iterator().next();
                } else {
                    return new KmAskScore();
                }
            }
        };
        return (KmAskScore) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskScoreDao#getKmAskScore(java.lang.String)
     */
    public KmAskScore getKmAskScore(final String id) {
        KmAskScore kmAskScore = (KmAskScore) getHibernateTemplate().get(KmAskScore.class, id);
        if (kmAskScore == null) {
            kmAskScore = new KmAskScore();
        }
        return kmAskScore;
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskScoreDao#saveKmAskScores(com.boco.eoms.km.ask.KmAskScore)
     */
    public void saveKmAskScore(final KmAskScore kmAskScore) {
        if ((kmAskScore.getId() == null) || (kmAskScore.getId().equals("")))
            getHibernateTemplate().save(kmAskScore);
        else
            getHibernateTemplate().saveOrUpdate(kmAskScore);
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskScoreDao#removeKmAskScores(java.lang.String)
     */
    public void removeKmAskScore(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmAskScore kmAskScore where kmAskScore.id=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, id);
                int result = query.executeUpdate();
                return new Integer(result);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmAskScore kmAskScore = this.getKmAskScore(id);
        if (kmAskScore == null) {
            return "";
        }
        //TODO 请修改代码
        return null;
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskScoreDao#getKmAskScores(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmAskScores(final Integer curPage, final Integer pageSize,
                              final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskScore kmAskScore";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();

                List result = null;
                if (total > 0) {
                    Query query = session.createQuery(queryStr);
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));
                    query.setMaxResults(pageSize.intValue());
                    result = query.list();
                } else {
                    result = new ArrayList();
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