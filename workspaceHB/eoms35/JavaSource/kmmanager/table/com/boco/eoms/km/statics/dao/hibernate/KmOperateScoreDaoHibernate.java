package com.boco.eoms.km.statics.dao.hibernate;

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
import com.boco.eoms.km.statics.dao.KmOperateScoreDao;
import com.boco.eoms.km.statics.model.KmOperateScore;

/**
 * <p>
 * Title:操作积分定义表 dao的hibernate实现
 * </p>
 * <p>
 * Description:操作积分定义表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class KmOperateScoreDaoHibernate extends BaseDaoHibernate implements KmOperateScoreDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.statics.KmOperateScoreDao#getKmOperateScores()
     */
    public List getKmOperateScores() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmOperateScore kmOperateScore";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.statics.KmOperateScoreDao#getKmOperateScore(java.lang.String)
     */
    public KmOperateScore getKmOperateScore(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmOperateScore kmOperateScore where kmOperateScore.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (KmOperateScore) result.iterator().next();
                } else {
                    return new KmOperateScore();
                }
            }
        };
        return (KmOperateScore) getHibernateTemplate().execute(callback);
    }

    public KmOperateScore getKmOperateScore(final Integer operateId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmOperateScore kmOperateScore where kmOperateScore.operateId=:operateId";
                Query query = session.createQuery(queryStr);
                query.setInteger("operateId", operateId.intValue());
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (KmOperateScore) result.iterator().next();
                } else {
                    return new KmOperateScore();
                }
            }
        };
        return (KmOperateScore) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.statics.KmOperateScoreDao#saveKmOperateScores(com.boco.eoms.km.statics.KmOperateScore)
     */
    public void saveKmOperateScore(final KmOperateScore kmOperateScore) {
        if ((kmOperateScore.getId() == null) || (kmOperateScore.getId().equals("")))
            getHibernateTemplate().save(kmOperateScore);
        else
            getHibernateTemplate().saveOrUpdate(kmOperateScore);
    }

    /**
     * @see com.boco.eoms.km.statics.KmOperateScoreDao#removeKmOperateScores(java.lang.String)
     */
    public void removeKmOperateScore(final String id) {
        getHibernateTemplate().delete(getKmOperateScore(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmOperateScore kmOperateScore = this.getKmOperateScore(id);
        if (kmOperateScore == null) {
            return "";
        }
        //TODO 请修改代码
        return null;//kmOperateScore.yourCode();
    }

    /**
     * @see com.boco.eoms.km.statics.KmOperateScoreDao#getKmOperateScores(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmOperateScores(final Integer curPage, final Integer pageSize,
                                  final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmOperateScore kmOperateScore";
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