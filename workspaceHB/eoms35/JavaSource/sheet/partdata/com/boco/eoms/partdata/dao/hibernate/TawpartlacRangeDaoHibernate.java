package com.boco.eoms.partdata.dao.hibernate;

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
import com.boco.eoms.partdata.dao.TawpartlacRangeDao;
import com.boco.eoms.partdata.model.TawpartlacRange;

/**
 * <p>
 * Title:LAC码号地市分配 dao的hibernate实现
 * </p>
 * <p>
 * Description:LAC码号地市分配
 * </p>
 * <p>
 * Mon Jul 12 09:21:06 CST 2010
 * </p>
 *
 * @author fengshaohong
 * @version 3.6
 */
public class TawpartlacRangeDaoHibernate extends BaseDaoHibernate implements TawpartlacRangeDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.partdata.TawpartlacRangeDao#getTawpartlacRanges()
     */
    public List getTawpartlacRanges() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartlacRange tawpartlacRange";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partdata.TawpartlacRangeDao#getTawpartlacRange(java.lang.String)
     */
    public TawpartlacRange getTawpartlacRange(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartlacRange tawpartlacRange where tawpartlacRange.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (TawpartlacRange) result.iterator().next();
                } else {
                    return new TawpartlacRange();
                }
            }
        };
        return (TawpartlacRange) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partdata.TawpartlacRangeDao#saveTawpartlacRanges(com.boco.eoms.partdata.TawpartlacRange)
     */
    public void saveTawpartlacRange(final TawpartlacRange tawpartlacRange) {
        if ((tawpartlacRange.getId() == null) || (tawpartlacRange.getId().equals("")))
            getHibernateTemplate().save(tawpartlacRange);
        else
            getHibernateTemplate().saveOrUpdate(tawpartlacRange);
    }

    /**
     * @see com.boco.eoms.partdata.TawpartlacRangeDao#removeTawpartlacRanges(java.lang.String)
     */
    public void removeTawpartlacRange(final String id) {
        getHibernateTemplate().delete(getTawpartlacRange(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        TawpartlacRange tawpartlacRange = this.getTawpartlacRange(id);
        if (tawpartlacRange == null) {
            return "";
        }
        //TODO 请修改代码
        return "";
    }

    /**
     * @see com.boco.eoms.partdata.TawpartlacRangeDao#getTawpartlacRanges(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getTawpartlacRanges(final Integer curPage, final Integer pageSize,
                                   final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartlacRange tawpartlacRange";
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

    public boolean isavailable(final int start, final int end) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartlacRange tawpartlacRange where (tawpartlacRange.tenStart<=:start and tawpartlacRange.tenEnd>=:start) or " +
                        "(tawpartlacRange.tenStart<=:end and tawpartlacRange.tenEnd>=:end)";
                Query query = session.createQuery(queryStr);
                query.setInteger("start", start);
                query.setInteger("end", end);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return Boolean.FALSE;
                } else {
                    return Boolean.TRUE;
                }
            }
        };
        return ((Boolean) getHibernateTemplate().execute(callback)).booleanValue();
    }

    public boolean isavailablenotself(final int start, final int end, final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartlacRange tawpartlacRange where ((tawpartlacRange.tenStart<=:start and tawpartlacRange.tenEnd>=:start) or " +
                        "(tawpartlacRange.tenStart<=:end and tawpartlacRange.tenEnd>=:end)) and id<>:id";
                Query query = session.createQuery(queryStr);
                query.setInteger("start", start);
                query.setInteger("end", end);
                query.setString("id", id);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return Boolean.FALSE;
                } else {
                    return Boolean.TRUE;
                }
            }
        };
        return ((Boolean) getHibernateTemplate().execute(callback)).booleanValue();
    }

    public List getTawpartlacRangebyL1L2(final String l1l2) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartlacRange tawpartlacRange where tawpartlacRange.loneLtwo=:l1l2";
                Query query = session.createQuery(queryStr);
                query.setString("l1l2", l1l2);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return result;
                } else {
                    return new ArrayList();
                }
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }
}