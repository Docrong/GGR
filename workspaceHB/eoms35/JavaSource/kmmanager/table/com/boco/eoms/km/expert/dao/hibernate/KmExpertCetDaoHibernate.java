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
import com.boco.eoms.km.expert.dao.KmExpertCetDao;
import com.boco.eoms.km.expert.model.KmExpertCet;

/**
 * <p>
 * Title:证书管理 dao的hibernate实现
 * </p>
 * <p>
 * Description:证书管理
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public class KmExpertCetDaoHibernate extends BaseDaoHibernate implements KmExpertCetDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.expert.cet.KmExpertCetDao#getKmExpertCets()
     */
    public List getKmExpertCets() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmExpertCet kmExpertCet";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.expert.cet.KmExpertCetDao#getKmExpertCet(java.lang.String)
     */
    public KmExpertCet getKmExpertCet(final String id) {
        KmExpertCet kmExpertCet = (KmExpertCet) getHibernateTemplate().get(KmExpertCet.class, id);
        if (kmExpertCet == null) {
            kmExpertCet = new KmExpertCet();
        }
        return kmExpertCet;
    }

    /**
     * @see com.boco.eoms.expert.cet.KmExpertCetDao#saveKmExpertCets(com.boco.eoms.expert.cet.KmExpertCet)
     */
    public void saveKmExpertCet(final KmExpertCet kmExpertCet) {
        if ((kmExpertCet.getId() == null) || (kmExpertCet.getId().equals("")))
            getHibernateTemplate().save(kmExpertCet);
        else
            getHibernateTemplate().saveOrUpdate(kmExpertCet);
    }

    /**
     * @see com.boco.eoms.expert.cet.KmExpertCetDao#removeKmExpertCets(java.lang.String)
     */
    public void removeKmExpertCet(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmExpertCet kmExpertCet where kmExpertCet.id=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, id);
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * 根据id批量删除证书
     *
     * @param id 主键
     *           add by liju @ 2009-06-20
     */
    public void removeKmExpertCets(final String[] ids) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmExpertCet kmExpertCet where kmExpertCet.id in (:ids)";
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
        KmExpertCet kmExpertCet = this.getKmExpertCet(id);
        if (kmExpertCet == null) {
            return "";
        }
        //TODO 请修改代码
        return kmExpertCet.getExpertCetName();
    }

    /**
     * @see com.boco.eoms.expert.cet.KmExpertCetDao#getKmExpertCets(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmExpertCets(final Integer curPage, final Integer pageSize,
                               final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmExpertCet kmExpertCet";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();
                Query query = session.createQuery(queryStr);
                query.setFirstResult(pageSize.intValue()
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

    public Map getKmExpertCetsByUserId(final Integer curPage, final Integer pageSize, final String userId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryCountStr = "select count(kmExpertCet.id) from KmExpertCet kmExpertCet where kmExpertCet.userId=?";
                Query queryCount = session.createQuery(queryCountStr);
                queryCount.setString(0, userId);
                int total = ((Integer) queryCount.iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    String queryStr = "from KmExpertCet kmExpertCet where kmExpertCet.userId=?";
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