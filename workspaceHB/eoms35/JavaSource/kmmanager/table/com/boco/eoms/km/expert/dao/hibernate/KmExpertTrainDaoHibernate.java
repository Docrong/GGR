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
import com.boco.eoms.km.expert.dao.KmExpertTrainDao;
import com.boco.eoms.km.expert.model.KmExpertTrain;

/**
 * <p>
 * Title:培训经历 dao的hibernate实现
 * </p>
 * <p>
 * Description:培训经历
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public class KmExpertTrainDaoHibernate extends BaseDaoHibernate implements
        KmExpertTrainDao, ID2NameDAO {

    /**
     * @see com.boco.eoms.expert.train.KmExpertTrainDao#getKmExpertTrains()
     */
    public List getKmExpertTrains() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmExpertTrain kmExpertTrain";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.expert.train.KmExpertTrainDao#getKmExpertTrain(java.lang.String)
     */
    public KmExpertTrain getKmExpertTrain(final String id) {
        KmExpertTrain kmExpertTrain = (KmExpertTrain) getHibernateTemplate().get(KmExpertTrain.class, id);
        if (kmExpertTrain == null) {
            kmExpertTrain = new KmExpertTrain();
        }
        return kmExpertTrain;
    }

    /**
     * @see com.boco.eoms.expert.train.KmExpertTrainDao#saveKmExpertTrains(com.boco.eoms.expert.train.KmExpertTrain)
     */
    public void saveKmExpertTrain(final KmExpertTrain kmExpertTrain) {
        if ((kmExpertTrain.getId() == null)
                || (kmExpertTrain.getId().equals("")))
            getHibernateTemplate().save(kmExpertTrain);
        else
            getHibernateTemplate().saveOrUpdate(kmExpertTrain);
    }

    /**
     * @see com.boco.eoms.expert.train.KmExpertTrainDao#removeKmExpertTrains(java.lang.String)
     */
    public void removeKmExpertTrain(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmExpertTrain kmExpertTrain where kmExpertTrain.id=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, id);
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * 根据id删除批量培训经历
     *
     * @param id 主键
     */
    public void removeKmExpertTrains(final String[] ids) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmExpertTrain kmExpertTrain where kmExpertTrain.id in (:ids)";
                Query query = session.createQuery(queryStr);
                query.setParameterList("ids", ids);
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };

        getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmExpertTrain kmExpertTrain = this.getKmExpertTrain(id);
        if (kmExpertTrain == null) {
            return "";
        }
        //TODO 请修改代码
        return kmExpertTrain.getExpertTrainLesson();
    }

    /**
     * @see com.boco.eoms.expert.train.KmExpertTrainDao#getKmExpertTrains(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmExpertTrains(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmExpertTrain kmExpertTrain";
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

    public Map getKmExpertTrainsByUserId(final Integer curPage, final Integer pageSize, final String userId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryCountStr = "select count(kmExpertTrain.id) from KmExpertTrain kmExpertTrain where kmExpertTrain.userId=?";
                Query queryCount = session.createQuery(queryCountStr);
                queryCount.setString(0, userId);
                int total = ((Integer) queryCount.iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    String queryStr = "from KmExpertTrain kmExpertTrain where kmExpertTrain.userId=?";
                    Query query = session.createQuery(queryStr);
                    query.setString(0, userId);
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