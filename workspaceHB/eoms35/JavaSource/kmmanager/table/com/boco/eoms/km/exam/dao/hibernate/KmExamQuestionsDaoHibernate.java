package com.boco.eoms.km.exam.dao.hibernate;

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
import com.boco.eoms.km.exam.dao.KmExamQuestionsDao;
import com.boco.eoms.km.exam.model.KmExamQuestions;

/**
 * <p>
 * Title:题库管理 dao的hibernate实现
 * </p>
 * <p>
 * Description:题库管理
 * </p>
 * <p>
 * Fri May 08 16:40:11 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public class KmExamQuestionsDaoHibernate extends BaseDaoHibernate implements KmExamQuestionsDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.exam.KmExamQuestionsDao#getKmExamQuestionss()
     */
    public List getKmExamQuestionss() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmExamQuestions kmExamQuestions where kmExamQuestions.isDeleted='0'";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.exam.KmExamQuestionsDao#getKmExamQuestions(java.lang.String)
     */
    public KmExamQuestions getKmExamQuestions(final String id) {
        KmExamQuestions kmExamQuestions = (KmExamQuestions) getHibernateTemplate().get(KmExamQuestions.class, id);
        if (kmExamQuestions == null) {
            kmExamQuestions = new KmExamQuestions();
        }
        return kmExamQuestions;
    }

    /**
     * @see com.boco.eoms.km.exam.KmExamQuestionsDao#saveKmExamQuestionss(com.boco.eoms.km.exam.KmExamQuestions)
     */
    public void saveKmExamQuestions(final KmExamQuestions kmExamQuestions) {
        if ((kmExamQuestions.getQuestionID() == null) || (kmExamQuestions.getQuestionID().equals("")))
            getHibernateTemplate().save(kmExamQuestions);
        else
            getHibernateTemplate().saveOrUpdate(kmExamQuestions);
    }

    /**
     * @see com.boco.eoms.km.exam.KmExamQuestionsDao#removeKmExamQuestionss(java.lang.String)
     */
    public void removeKmExamQuestions(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hql = "update KmExamQuestions kmExamQuestions set kmExamQuestions.isDeleted=1 where kmExamQuestions.questionID=?";
                Query query = session.createQuery(hql);
                query.setString(0, id);
                query.executeUpdate();
                return null;
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmExamQuestions kmExamQuestions = this.getKmExamQuestions(id);
        if (kmExamQuestions == null) {
            return "";
        }
        //TODO 请修改代码
        //return kmExamQuestions.yourCode();
        return "";
    }

    /**
     * @see com.boco.eoms.km.exam.KmExamQuestionsDao#getKmExamQuestionss(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmExamQuestionss(final Integer curPage, final Integer pageSize,
                                   final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                StringBuffer queryStr = new StringBuffer("from KmExamQuestions kmExamQuestions ");
                if (whereStr != null && whereStr.length() > 0)
                    queryStr.append(whereStr);
                else
                    queryStr.append(" where kmExamQuestions.isDeleted='0' ");

                StringBuffer queryCountStr = new StringBuffer("select count(kmExamQuestions.questionID) ");
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