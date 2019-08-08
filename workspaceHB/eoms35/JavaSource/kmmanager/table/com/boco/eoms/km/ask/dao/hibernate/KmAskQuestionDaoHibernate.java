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
import com.boco.eoms.km.ask.dao.KmAskQuestionDao;
import com.boco.eoms.km.ask.model.KmAskQuestion;

/**
 * <p>
 * Title:问题 dao的hibernate实现
 * </p>
 * <p>
 * Description:问题
 * </p>
 * <p>
 * Tue Aug 04 15:17:03 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmAskQuestionDaoHibernate extends BaseDaoHibernate implements KmAskQuestionDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.ask.KmAskQuestionDao#getKmAskQuestions()
     */
    public List getKmAskQuestions(final String nodeId, final String questionStatus) {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = null;
                if ("".equals(nodeId)) {
                    queryStr = "from KmAskQuestion kmAskQuestion where kmAskQuestion.questionStatus=? order by kmAskQuestion.askTime desc";
                    Query query = session.createQuery(queryStr);
                    query.setString(0, questionStatus);
//					query.setString(1, "ask_time");
                    query.setFirstResult(0);
                    query.setMaxResults(10);
                    List result = query.list();
                    return result;
                }
                queryStr = "from KmAskQuestion kmAskQuestion where kmAskQuestion.speciality like ? and kmAskQuestion.questionStatus=? order by kmAskQuestion.askTime desc";
                Query query = session.createQuery(queryStr);
                query.setString(0, nodeId + "%");
                query.setString(1, questionStatus);
                //query.setString(2, "ask_time");
                query.setFirstResult(0);
                query.setMaxResults(10);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询待解决的所用问题
     *
     * @return
     */
    public List getKmAskQuestions0() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskQuestion kmAskQuestion where kmAskQuestion.questionStatus=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, "0");
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询已解决问题
     *
     * @return
     */
    public List getKmAskQuestions1() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskQuestion kmAskQuestion where kmAskQuestion.questionStatus=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, "1");
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询投票中的问题
     *
     * @return
     */
    public List getKmAskQuestions2() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskQuestion kmAskQuestion where kmAskQuestion.questionStatus=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, "2");
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskQuestionDao#getKmAskQuestion(java.lang.String)
     */
    public KmAskQuestion getKmAskQuestion(final String id) {
        KmAskQuestion kmAskQuestion = (KmAskQuestion) getHibernateTemplate().get(KmAskQuestion.class, id);
        if (kmAskQuestion == null) {
            kmAskQuestion = new KmAskQuestion();
        }
        return kmAskQuestion;
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskQuestionDao#saveKmAskQuestions(com.boco.eoms.km.ask.KmAskQuestion)
     */
    public void saveKmAskQuestion(final KmAskQuestion kmAskQuestion) {
        if ((kmAskQuestion.getId() == null) || (kmAskQuestion.getId().equals("")))
            getHibernateTemplate().save(kmAskQuestion);
        else
            getHibernateTemplate().saveOrUpdate(kmAskQuestion);
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskQuestionDao#removeKmAskQuestions(java.lang.String)
     */
    public void removeKmAskQuestion(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmAskQuestion kmAskQuestion where kmAskQuestion.id=?";
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
        KmAskQuestion kmAskQuestion = this.getKmAskQuestion(id);
        if (kmAskQuestion == null) {
            return "";
        }
        //TODO 请修改代码
        return kmAskQuestion.getName();
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskQuestionDao#getKmAskQuestions(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmAskQuestions(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmAskQuestion kmAskQuestion";
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