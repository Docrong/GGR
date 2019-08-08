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
import com.boco.eoms.km.train.dao.TrainRequireDao;
import com.boco.eoms.km.train.model.TrainRequire;

/**
 * <p>
 * Title:培训需求 dao的hibernate实现
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 15:34:49 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainRequireDaoHibernate extends BaseDaoHibernate implements TrainRequireDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.train.TrainRequireDao#getTrainRequires()
     */
    public List getTrainRequires() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TrainRequire trainRequire";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.train.TrainRequireDao#getTrainRequire(java.lang.String)
     */
    public TrainRequire getTrainRequire(final String id) {
        TrainRequire trainRequire = (TrainRequire) getHibernateTemplate().get(TrainRequire.class, id);
        if (trainRequire == null) {
            trainRequire = new TrainRequire();
        }
        return trainRequire;
    }

    /**
     * @see com.boco.eoms.km.train.TrainRequireDao#saveTrainRequires(com.boco.eoms.km.train.TrainRequire)
     */
    public void saveTrainRequire(final TrainRequire trainRequire) {
        if ((trainRequire.getId() == null) || (trainRequire.getId().equals("")))
            getHibernateTemplate().save(trainRequire);
        else
            getHibernateTemplate().saveOrUpdate(trainRequire);
    }

    /**
     * @see com.boco.eoms.km.train.TrainRequireDao#removeTrainRequires(java.lang.String)
     */
    public void removeTrainRequire(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from TrainRequire trainRequire where trainRequire.id=?";
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
        TrainRequire trainRequire = this.getTrainRequire(id);
        if (trainRequire == null) {
            return "";
        }
        //TODO 请修改代码
        return null;
    }

    /**
     * @see com.boco.eoms.km.train.TrainRequireDao#getTrainRequires(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getTrainRequires(final Integer curPage, final Integer pageSize,
                                final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TrainRequire trainRequire";
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