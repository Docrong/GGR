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
import com.boco.eoms.partdata.dao.TawpartLacBscDao;
import com.boco.eoms.partdata.model.TawpartLacBsc;

/**
 * <p>
 * Title:LAC的BSC分配 dao的hibernate实现
 * </p>
 * <p>
 * Description:LAC的BSC分配
 * </p>
 * <p>
 * Mon Jul 12 17:27:34 CST 2010
 * </p>
 *
 * @author fengshaohong
 * @version 3.6
 */
public class TawpartLacBscDaoHibernate extends BaseDaoHibernate implements TawpartLacBscDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.partdata.TawpartLacBscDao#getTawpartLacBscs()
     */
    public List getTawpartLacBscs() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartLacBsc tawpartLacBsc";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partdata.TawpartLacBscDao#getTawpartLacBsc(java.lang.String)
     */
    public TawpartLacBsc getTawpartLacBsc(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartLacBsc tawpartLacBsc where tawpartLacBsc.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (TawpartLacBsc) result.iterator().next();
                } else {
                    return new TawpartLacBsc();
                }
            }
        };
        return (TawpartLacBsc) getHibernateTemplate().execute(callback);
    }

    public List getTawpartLacBscList(final String rangeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartLacBsc tawpartLacBsc where tawpartLacBsc.partRangeId=:partRangeId";
                Query query = session.createQuery(queryStr);
                query.setString("partRangeId", rangeId);

                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public void removeTawpartLacBscbyRangeid(final String rangeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from TawpartLacBsc tawpartLacBsc where tawpartLacBsc.partRangeId=:partRangeId";
                Query query = session.createQuery(queryStr);
                query.setString("partRangeId", rangeId);
                query.executeUpdate();
                return null;
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partdata.TawpartLacBscDao#saveTawpartLacBscs(com.boco.eoms.partdata.TawpartLacBsc)
     */
    public void saveTawpartLacBsc(final TawpartLacBsc tawpartLacBsc) {
        if ((tawpartLacBsc.getId() == null) || (tawpartLacBsc.getId().equals("")))
            getHibernateTemplate().save(tawpartLacBsc);
        else
            getHibernateTemplate().saveOrUpdate(tawpartLacBsc);
    }

    /**
     * @see com.boco.eoms.partdata.TawpartLacBscDao#removeTawpartLacBscs(java.lang.String)
     */
    public void removeTawpartLacBsc(final String id) {
        getHibernateTemplate().delete(getTawpartLacBsc(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        TawpartLacBsc tawpartLacBsc = this.getTawpartLacBsc(id);
        if (tawpartLacBsc == null) {
            return "";
        }
        //TODO 请修改代码
        return "";
    }

    /**
     * @see com.boco.eoms.partdata.TawpartLacBscDao#getTawpartLacBscs(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getTawpartLacBscs(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawpartLacBsc tawpartLacBsc";
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

}