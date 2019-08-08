package com.boco.eoms.partdata.dao.hibernate;

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
import com.boco.eoms.partdata.dao.TawpartMscidDao;
import com.boco.eoms.partdata.model.TawpartMscid;

/**
 * <p>
 * Title:MSCID码号管理 dao的hibernate实现
 * </p>
 * <p>
 * Description:MSCID码号管理
 * </p>
 * <p>
 * Mon Jul 05 09:11:48 CST 2010
 * </p>
 *
 * @author fengshaohong
 * @version 3.6
 */
public class TawpartMscidDaoHibernate extends BaseDaoHibernate implements
        TawpartMscidDao, ID2NameDAO {

    /**
     * @see com.boco.eoms.partdata.TawpartMscidDao#getTawpartMscids()
     */
    public List getTawpartMscids() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartMscid tawpartMscid";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partdata.TawpartMscidDao#getTawpartMscid(java.lang.String)
     */
    public TawpartMscid getTawpartMscid(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartMscid tawpartMscid where tawpartMscid.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (TawpartMscid) result.iterator().next();
                } else {
                    return new TawpartMscid();
                }
            }
        };
        return (TawpartMscid) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partdata.TawpartMscidDao#saveTawpartMscids(com.boco.eoms.partdata.TawpartMscid)
     */
    public void saveTawpartMscid(final TawpartMscid tawpartMscid) {
        if ((tawpartMscid.getId() == null) || (tawpartMscid.getId().equals("")))
            getHibernateTemplate().save(tawpartMscid);
        else
            getHibernateTemplate().saveOrUpdate(tawpartMscid);
    }

    /**
     * @see com.boco.eoms.partdata.TawpartMscidDao#removeTawpartMscids(java.lang.String)
     */
    public void removeTawpartMscid(final String id) {
        getHibernateTemplate().delete(getTawpartMscid(id));
    }

    public void removeTawpartMscid(final String headNumber,
                                   final String numberM0, final String numberM1, final String numberM2) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from TawpartMscid tawpartMscid where 1=1 ";
                if (headNumber != null && headNumber.length() > 0) {
                    queryStr += " and tawpartMscid.headNumber=:headNumber";
                }
                if (numberM0 != null && numberM0.length() > 0) {
                    queryStr += " and tawpartMscid.numberM0=:numberM0";
                }
                if (numberM1 != null && numberM1.length() > 0) {
                    queryStr += " and tawpartMscid.numberM1=:numberM1";
                }
                if (numberM2 != null && numberM2.length() > 0) {
                    queryStr += " and tawpartMscid.numberM2=:numberM2";
                }

                Query query = session.createQuery(queryStr);
                if (headNumber != null && headNumber.length() > 0) {
                    query.setString("headNumber", headNumber);
                }
                if (numberM0 != null && numberM0.length() > 0) {
                    query.setString("numberM0", numberM0);
                }
                if (numberM1 != null && numberM1.length() > 0) {
                    query.setString("numberM1", numberM1);
                }
                if (numberM2 != null && numberM2.length() > 0) {
                    query.setString("numberM2", numberM2);
                }
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
        TawpartMscid tawpartMscid = this.getTawpartMscid(id);
        if (tawpartMscid == null) {
            return "";
        }
        // TODO 请修改代码
        return "";
    }

    /**
     * @see com.boco.eoms.partdata.TawpartMscidDao#getTawpartMscids(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getTawpartMscids(final Integer curPage, final Integer pageSize,
                                final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartMscid tawpartMscid";
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

    public List getM0byHeadNumber(final String headNumber) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "select distinct tawpartMscid.numberM0 from TawpartMscid tawpartMscid where tawpartMscid.headNumber=:headNumber order by tawpartMscid.numberM0";
                Query query = session.createQuery(queryStr);
                query.setString("headNumber", headNumber);
                // query.setFirstResult(0);
                // query.setMaxResults(1);
                List result = query.list();
                return result;

            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public List getM1byHeadNumber(final String headNumber) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "select distinct tawpartMscid.numberM1 from TawpartMscid tawpartMscid where tawpartMscid.headNumber=:headNumber order by tawpartMscid.numberM1";
                Query query = session.createQuery(queryStr);
                query.setString("headNumber", headNumber);
                // query.setFirstResult(0);
                // query.setMaxResults(1);
                List result = query.list();
                return result;

            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public List getIDbyHeadnumber(final String headNumber) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "select distinct tawpartMscid.id from TawpartMscid tawpartMscid where tawpartMscid.headNumber=:headNumber order by tawpartMscid.id";
                Query query = session.createQuery(queryStr);
                query.setString("headNumber", headNumber);
                // query.setFirstResult(0);
                // query.setMaxResults(1);
                List result = query.list();
                return result;

            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public List getM2byHeadNumber(final String headNumber) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "select distinct tawpartMscid.numberM2 from TawpartMscid tawpartMscid where tawpartMscid.headNumber=:headNumber order by tawpartMscid.numberM2";
                Query query = session.createQuery(queryStr);
                query.setString("headNumber", headNumber);
                // query.setFirstResult(0);
                // query.setMaxResults(1);
                List result = query.list();
                return result;

            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public List getTawpartMscidsbyHeadnumber(final String headNumber) {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartMscid tawpartMscid where tawpartMscid.headNumber=:headNumber";
                Query query = session.createQuery(queryStr);
                query.setString("headNumber", headNumber);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }
}