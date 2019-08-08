package com.boco.eoms.partdata.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.partdata.dao.TawPartFourteenSignalDao;
import com.boco.eoms.partdata.model.TawPartFourteenSignal;

/**
 * <p>
 * Title:14位信令点 dao的hibernate实现
 * </p>
 * <p>
 * Description:14位信令点范围
 * </p>
 * <p>
 * Tue Jul 06 14:18:23 CST 2010
 * </p>
 *
 * @author Josh
 * @version 3.5
 */
public class TawPartFourteenSignalDaoHibernate extends BaseDaoHibernate implements
        TawPartFourteenSignalDao, ID2NameDAO {

    /**
     * @see com.boco.eoms.partdata.TawPartFourteenSignalDao#getTawPartLacs()
     */
    public List getTawPartFourteenSignals() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawPartFourteenSignal tawPartFourteenSignal";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partdata.TawPartLacDao#getTawPartLac(java.lang.String)
     */
    public TawPartFourteenSignal getTawPartFourteenSignal(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawPartFourteenSignal tawPartFourteenSignal where tawPartFourteenSignal.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (TawPartFourteenSignal) result.iterator().next();
                } else {
                    return new TawPartFourteenSignal();
                }
            }
        };
        return (TawPartFourteenSignal) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partdata.TawPartLacDao#saveTawPartLacs(com.boco.eoms.partdata.TawPartLac)
     */
    public void saveTawPartFourteenSignal(final TawPartFourteenSignal tawPartFourteenSignal) {
        if ((tawPartFourteenSignal.getId() == null) || (tawPartFourteenSignal.getId().equals("")))
            getHibernateTemplate().save(tawPartFourteenSignal);
        else
            getHibernateTemplate().saveOrUpdate(tawPartFourteenSignal);
    }

    /**
     * @see com.boco.eoms.partdata.TawPartLacDao#removeTawPartLacs(java.lang.String)
     */
    public void removeTawPartFourteenSignal(final String id) {
        getHibernateTemplate().delete(getTawPartFourteenSignal(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        TawPartFourteenSignal tawPartFourteenSignal = this.getTawPartFourteenSignal(id);
        if (tawPartFourteenSignal == null) {
            return "";
        }
        // TODO 请修改代码（需要添加代码）
        return null; // tawPartLac.yourCode();
    }

    /**
     * @see com.boco.eoms.partdata.TawPartLacDao#getTawPartLacs(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getTawPartFourteenSignals(final Integer curPage, final Integer pageSize,
                                         final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawPartFourteenSignal tawPartFourteenSignal";
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

    public Map getExistTawPartFourteenSignals(final Integer startNum, final Integer endNum) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from part_FourteenSignal ";
                String whereStr = " where Signal_NUM >= " + startNum.toString()
                        + " AND Signal_NUM <= " + endNum.toString() + "";
                String queryCountStr = "select * " + queryStr + whereStr;

                Query query = session.createSQLQuery(queryCountStr);
                List result = query.list();

                Map map = new HashMap();

                for (int i = 0; i < result.size(); i++) {
                    map.put(StaticMethod.nullObject2String(((Object[]) result
                            .get(i))[3]), StaticMethod
                            .nullObject2String(((Object[]) result.get(i))[4]));
                }

                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map getAllTawPartFourteenSignals(final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawPartFourteenSignal tawPartFourteenSignal";

                Query query = session.createQuery(queryStr);
                List result = query.list();
                HashMap map = new HashMap();
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
}
