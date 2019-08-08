package com.boco.eoms.sheet.daiweiindexreduction.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.daiweiindexreduction.dao.IDaiSubtractTableDao;
import com.boco.eoms.sheet.daiweiindexreduction.model.DaiSubtractTable;

/**
 * @author wangmingming
 * <p>
 * 2017-8-4
 */

public class DaiSubtractTableDaoHibernate extends BaseDaoHibernate implements IDaiSubtractTableDao {

    // 根据主键查询核减内容表
    public DaiSubtractTable getDaiSubtractTable(final String id) {
        return (DaiSubtractTable) this.getObject(DaiSubtractTable.class, id);
    }

    // 取核减内容列表
    public List getDaiSubtractTables() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                String queryStr = "from DaiSubtractTable daisubtractTable";
                queryStr += " order by id ";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    // 分页取列表
    public Map getDaiSubtractTables(final Integer curPage, final Integer pageSize, final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                String queryStr = "from DaiSubtractTable daisubtractTable";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                queryStr += " order by id ";
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();
                Query query = session.createQuery(queryStr);
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
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

    // 根据id删除核减内容表
    public void removeDaiSubtractTable(String id) {
        getHibernateTemplate().delete(getDaiSubtractTable(id));

    }

    // 保存核减内容表
    public void saveDaiSubtractTable(DaiSubtractTable daisubtractTable) {
        if ((daisubtractTable.getId() == null) || (daisubtractTable.getId().equals("")))
            getHibernateTemplate().save(daisubtractTable);
        else
            getHibernateTemplate().saveOrUpdate(daisubtractTable);

    }

    /**
     * 根据条件查询
     */
    public List getDaiSubtractTablesByCondition(final String condition) {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                String queryStr = "from DaiSubtractTable daisubtractTable ";
                if (condition != null && condition.length() > 0) {
                    queryStr += condition;
                }
                queryStr += " order by id ";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }
}