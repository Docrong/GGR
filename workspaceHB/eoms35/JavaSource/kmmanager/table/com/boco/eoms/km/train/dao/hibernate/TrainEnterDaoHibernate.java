package com.boco.eoms.km.train.dao.hibernate;

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
import com.boco.eoms.km.train.dao.TrainEnterDao;
import com.boco.eoms.km.train.model.TrainEnter;

/**
 * <p>
 * Title:报名信息 dao的hibernate实现
 * </p>
 * <p>
 * Description:报名信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainEnterDaoHibernate extends BaseDaoHibernate implements TrainEnterDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.train.TrainEnterDao#getTrainEnters()
     */
    public List getTrainEnters() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TrainEnter trainEnter";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 培训计划的报名信息
     *
     * @return
     */
    public List getTrainEntersByPlanId(final String trainPlanId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TrainEnter trainEnter where trainEnter.trainPlanId=?";
                Query query = session.createQuery(queryStr).setString(0, trainPlanId);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.train.TrainEnterDao#getTrainEnter(java.lang.String)
     */
    public TrainEnter getTrainEnter(final String id) {
        TrainEnter trainEnter = (TrainEnter) getHibernateTemplate().get(TrainEnter.class, id);
        if (trainEnter == null) {
            trainEnter = new TrainEnter();
        }
        return trainEnter;
    }

    /**
     * @see com.boco.eoms.km.train.TrainEnterDao#saveTrainEnters(com.boco.eoms.km.train.TrainEnter)
     */
    public void saveTrainEnter(final TrainEnter trainEnter) {
        if ((trainEnter.getId() == null) || (trainEnter.getId().equals("")))
            getHibernateTemplate().save(trainEnter);
        else
            getHibernateTemplate().saveOrUpdate(trainEnter);
    }

    /**
     * @see com.boco.eoms.km.train.TrainEnterDao#removeTrainEnters(java.lang.String)
     */
    public void removeTrainEnter(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from TrainEnter trainEnter where trainEnter.id=?";
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
        TrainEnter trainEnter = this.getTrainEnter(id);
        if (trainEnter == null) {
            return "";
        }
        //TODO 请修改代码
        return getTrainEnter(id).getEnterName();
    }

    /**
     * @see com.boco.eoms.km.train.TrainEnterDao#getTrainEnters(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getTrainEnters(final Integer curPage, final Integer pageSize,
                              final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TrainEnter trainEnter";
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