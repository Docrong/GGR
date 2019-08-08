package com.boco.eoms.km.expert.dao.hibernate;

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
import com.boco.eoms.km.expert.dao.KmExpertAnswerDao;
import com.boco.eoms.km.expert.dao.KmExpertBasicDao;
import com.boco.eoms.km.expert.model.KmExpertAnswer;
import com.boco.eoms.km.expert.model.KmExpertBasic;

public class KmExpertAnswerDaoHibernate extends BaseDaoHibernate implements KmExpertAnswerDao,
        ID2NameDAO {


    /**
     * 取所有问题记录列表
     *
     * @return 返回所有问题记录列表
     */
    public List getKmExpertAnswers() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmExpertAnswer kmExpertAnswer";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 取所有问题记录列表
     *
     * @return 返回所有问题记录列表
     */
    public Map getKmExpertAnswers(final Integer curPage, final Integer pageSize) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryCountStr = "select count(kmExpertAnswer.id) from KmExpertAnswer kmExpertAnswer";
                Query queryCount = session.createQuery(queryCountStr);
                int total = ((Integer) queryCount.iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    String queryStr = "from KmExpertAnswer kmExpertAnswer";
                    Query query = session.createQuery(queryStr);
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

    /**
     * 取所有问题记录列表
     *
     * @return 返回所有问题记录列表
     */
    public Map getKmExpertAnswers(final Integer curPage, final Integer pageSize, final String whereSql) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryCountStr = "select count(kmExpertAnswer.id) from KmExpertAnswer kmExpertAnswer" + whereSql;
                Query queryCount = session.createQuery(queryCountStr);
                int total = ((Integer) queryCount.iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    String queryStr = "from KmExpertAnswer kmExpertAnswer" + whereSql;
                    Query query = session.createQuery(queryStr);
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

    /**
     * 根据主键查询问题记录
     *
     * @param id 主键
     * @return 返回某id的问题记录
     */
    public KmExpertAnswer getKmExpertAnswer(final String id) {
        KmExpertAnswer kmExpertAnswer = (KmExpertAnswer) getHibernateTemplate().get(KmExpertAnswer.class, id);
        if (kmExpertAnswer == null) {
            kmExpertAnswer = new KmExpertAnswer();
        }
        return kmExpertAnswer;
    }

    /**
     * 保存问题记录
     *
     * @param kmExpertAnswer 问题记录
     */
    public void saveKmExpertAnswer(KmExpertAnswer kmExpertAnswer) {
        if ((kmExpertAnswer.getId() == null) || (kmExpertAnswer.getId().equals("")))
            getHibernateTemplate().save(kmExpertAnswer);
        else
            getHibernateTemplate().saveOrUpdate(kmExpertAnswer);
    }

    /**
     * 根据主键删除问题记录
     *
     * @param id 主键
     */
    public void removeKmExpertAnswer(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmExpertAnswer kmExpertAnswer where kmExpertAnswer.id=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, id);
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * 根据用户ID查询该用户提出的问题记录
     *
     * @param userId 用户ID
     * @return 返回某用户提出的问题记录
     */
    public Map getKmExpertAnswerBySendUserId(final String userId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryCountStr = "select count(kmExpertAnswer.id) from KmExpertAnswer kmExpertAnswer where kmExpertAnswer.userId=?";
                Query queryCount = session.createQuery(queryCountStr);
                queryCount.setString(0, userId);
                int total = ((Integer) queryCount.iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    String queryStr = "from KmExpertAnswer kmExpertAnswer where kmExpertAnswer.userId=?";
                    Query query = session.createQuery(queryStr);
                    query.setString(0, userId);
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

    /**
     * 根据用户ID查询该用户提出的问题记录
     *
     * @param userId 用户ID
     * @return 返回某用户提出的问题记录
     */
    public Map getKmExpertAnswerBySendUserId(final Integer curPage, final Integer pageSize, final String userId, final String state) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryCountStr = "select count(kmExpertAnswer.id) from KmExpertAnswer kmExpertAnswer where kmExpertAnswer.createUserId='" + userId + "' and kmExpertAnswer.state in (" + state + ")";
                Query queryCount = session.createQuery(queryCountStr);
                //queryCount.setString(0, userId);
                //queryCount.setString(1, state);
                int total = ((Integer) queryCount.iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    String queryStr = "from KmExpertAnswer kmExpertAnswer where kmExpertAnswer.createUserId='" + userId + "' and kmExpertAnswer.state in (" + state + ")";
                    Query query = session.createQuery(queryStr);
                    //	query.setString(0, userId);
                    //	query.setString(1, state);
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

    /**
     * 根据用户ID查询该用户回答的问题记录
     *
     * @param userId 用户ID
     * @return 返回某用户回答的问题记录
     */
    public Map getKmExpertAnswerByAnswerUserId(final String userId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryCountStr = "select count(kmExpertAnswer.id) from KmExpertAnswer kmExpertAnswer where kmExpertAnswer.answerUser=?";
                Query queryCount = session.createQuery(queryCountStr);
                queryCount.setString(0, userId);
                int total = ((Integer) queryCount.iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    String queryStr = "from KmExpertAnswer kmExpertAnswer where kmExpertAnswer.answerUser=?";
                    Query query = session.createQuery(queryStr);
                    query.setString(0, userId);
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

    /**
     * 根据用户ID查询该用户提出的问题记录
     *
     * @param userId 用户ID
     * @return 返回某用户提出的问题记录
     */
    public Map getKmExpertAnswerByAnswerUserId(final Integer curPage, final Integer pageSize, final String userId, final String state) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryCountStr = "select count(kmExpertAnswer.id) from KmExpertAnswer kmExpertAnswer where kmExpertAnswer.answerUserId='" + userId + "' and kmExpertAnswer.state in (" + state + ")";
                Query queryCount = session.createQuery(queryCountStr);
//			queryCount.setString(0, userId);
//			queryCount.setString(1, state);
                int total = ((Integer) queryCount.iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    String queryStr = "from KmExpertAnswer kmExpertAnswer where kmExpertAnswer.answerUserId='" + userId + "' and kmExpertAnswer.state in (" + state + ")";
                    Query query = session.createQuery(queryStr);
//				query.setString(0, userId);
//				query.setString(1, state);
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


    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        //TODO 请修改代码
        return null;
    }
}