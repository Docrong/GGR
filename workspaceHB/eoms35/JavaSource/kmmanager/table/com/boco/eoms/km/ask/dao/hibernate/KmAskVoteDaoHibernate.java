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
import com.boco.eoms.km.ask.dao.KmAskVoteDao;
import com.boco.eoms.km.ask.model.KmAskVote;

/**
 * <p>
 * Title:投票 dao的hibernate实现
 * </p>
 * <p>
 * Description:投票
 * </p>
 * <p>
 * Fri Aug 14 15:39:20 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmAskVoteDaoHibernate extends BaseDaoHibernate implements KmAskVoteDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.ask.KmAskVoteDao#getKmAskVotes()
     */
    public List getKmAskVotes() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskVote kmAskVote";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据questionId查询所有投票信息
     *
     * @param questionId
     * @return
     */
    public List getKmAskVoteByQuestionId(final String questionId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskVote kmAskVote where kmAskVote.questionId=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, questionId);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据问题id和投票人id查询投票信息
     *
     * @param questionId
     * @param userId
     * @return
     */
    public KmAskVote getKmAskVote(final String questionId, final String userId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskVote kmAskVote where kmAskVote.questionId=? and kmAskVote.voteUser=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, questionId);
                query.setString(1, userId);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (KmAskVote) result.iterator().next();
                } else {
                    return new KmAskVote();
                }
            }
        };
        return (KmAskVote) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskVoteDao#getKmAskVote(java.lang.String)
     */
    public KmAskVote getKmAskVote(final String id) {
        KmAskVote kmAskVote = (KmAskVote) getHibernateTemplate().get(KmAskVote.class, id);
        if (kmAskVote == null) {
            kmAskVote = new KmAskVote();
        }
        return kmAskVote;
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskVoteDao#saveKmAskVotes(com.boco.eoms.km.ask.KmAskVote)
     */
    public void saveKmAskVote(final KmAskVote kmAskVote) {
        if ((kmAskVote.getId() == null) || (kmAskVote.getId().equals("")))
            getHibernateTemplate().save(kmAskVote);
        else
            getHibernateTemplate().saveOrUpdate(kmAskVote);
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskVoteDao#removeKmAskVotes(java.lang.String)
     */
    public void removeKmAskVote(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmAskVote kmAskVote where kmAskVote.id=?";
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
        KmAskVote kmAskVote = this.getKmAskVote(id);
        if (kmAskVote == null) {
            return "";
        }
        //TODO 请修改代码
        return null;
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskVoteDao#getKmAskVotes(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmAskVotes(final Integer curPage, final Integer pageSize,
                             final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskVote kmAskVote";
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