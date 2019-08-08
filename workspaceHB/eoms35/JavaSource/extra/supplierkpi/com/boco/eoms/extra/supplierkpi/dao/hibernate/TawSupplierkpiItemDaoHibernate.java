package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.util.SupplierkpiConstants;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiItemDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSupplierkpiItemDaoHibernate extends BaseDaoHibernate implements
        TawSupplierkpiItemDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiItemDao#getTawSupplierkpiItems(com.boco.eoms.commons.sample.model.TawSupplierkpiItem)
     */
    public List getTawSupplierkpiItems(
            final TawSupplierkpiItem tawSupplierkpiItem) {
        return getHibernateTemplate().find(
                "from TawSupplierkpiItem where deleted="
                        + SupplierkpiConstants.UNDELETED);

        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (tawSupplierkpiItem == null) {
         * return getHibernateTemplate().find("from TawSupplierkpiItem"); } else { //
         * filter on properties set in the tawSupplierkpiItem HibernateCallback
         * callback = new HibernateCallback() { public Object
         * doInHibernate(Session session) throws HibernateException { Example ex =
         * Example.create(tawSupplierkpiItem).ignoreCase().enableLike(MatchMode.ANYWHERE);
         * return
         * session.createCriteria(TawSupplierkpiItem.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiItemDao#getTawSupplierkpiItem(String
     * id)
     */
    public TawSupplierkpiItem getTawSupplierkpiItem(final String id,
                                                    final int deleted) {
        // TawSupplierkpiItem tawSupplierkpiItem = (TawSupplierkpiItem)
        // getHibernateTemplate().get(TawSupplierkpiItem.class, id);
        // if (tawSupplierkpiItem == null) {
        // throw new ObjectRetrievalFailureException(TawSupplierkpiItem.class,
        // id);
        // }
        //
        // return tawSupplierkpiItem;
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSupplierkpiItem where id='" + id
                        + "'";
                queryStr = queryStr + " and deleted=" + deleted;
                Query query = session.createQuery(queryStr);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List list = query.list();
                TawSupplierkpiItem tawSupplierkpiItem = null;

                if (list != null && !list.isEmpty()) {
                    tawSupplierkpiItem = (TawSupplierkpiItem) list.iterator()
                            .next();
                }
                return tawSupplierkpiItem;
            }
        };
        return (TawSupplierkpiItem) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiItemDao#saveTawSupplierkpiItem(TawSupplierkpiItem
     * tawSupplierkpiItem)
     */
    public void saveTawSupplierkpiItem(
            final TawSupplierkpiItem tawSupplierkpiItem) {
        if ((tawSupplierkpiItem.getId() == null)
                || (tawSupplierkpiItem.getId().equals("")))
            getHibernateTemplate().save(tawSupplierkpiItem);
        else
            getHibernateTemplate().saveOrUpdate(tawSupplierkpiItem);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiItemDao#removeTawSupplierkpiItem(String
     * id)
     */
    public void removeTawSupplierkpiItem(final String id) {
        getHibernateTemplate().delete(
                getTawSupplierkpiItem(id, SupplierkpiConstants.UNDELETED));
    }

    /**
     * curPage pageSize whereStr sql filter
     */
    public Map getTawSupplierkpiItems(final int curPage, final int pageSize,
                                      final String whereStr) {
        // filter on properties set in the tawSupplierkpiItem
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSupplierkpiItem";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                queryStr = queryStr + " and deleted="
                        + SupplierkpiConstants.UNDELETED;
                String queryCountStr = "select count(*) " + queryStr;

                Integer total = (Integer) session.createQuery(queryCountStr)
                        .iterate().next();
                Query query = session.createQuery(queryStr);
                query.setFirstResult(pageSize * (curPage));
                query.setMaxResults(pageSize);
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map getTawSupplierkpiItems(final int curPage, final int pageSize) {
        return this.getTawSupplierkpiItems(curPage, pageSize, null);
    }

    public List getSpecialkpi(final String specialType) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSupplierkpiItem "
                        + " where itemtype like('" + specialType
                        + "%') and deleted=" + SupplierkpiConstants.UNDELETED;
                Query query = session.createQuery(queryStr);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public List getSpecialkpiContainDeletedRecently(final String specialType) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSupplierkpiItem "
                        + " where itemtype like('" + specialType
                        + "%') and deleted=" + SupplierkpiConstants.UNDELETED
                        + " or (deleted=" + SupplierkpiConstants.DELETED
                        + " and assessStatus=6)";
                Query query = session.createQuery(queryStr);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public TawSupplierkpiItem getItemByItemType(final String dictType) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSupplierkpiItem where itemType='"
                        + dictType + "'";
                queryStr = queryStr + " and deleted=" + SupplierkpiConstants.UNDELETED;
                Query query = session.createQuery(queryStr);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List list = query.list();
                TawSupplierkpiItem tawSupplierkpiItem = null;

                if (list != null && !list.isEmpty()) {
                    tawSupplierkpiItem = (TawSupplierkpiItem) list.iterator()
                            .next();
                }
                return tawSupplierkpiItem;
            }
        };
        return (TawSupplierkpiItem) getHibernateTemplate().execute(callback);
    }

    public void removeItemByItemType(final String dictId) {
        getHibernateTemplate().delete(getItemByItemType(dictId));
    }

    public void removeItem(final TawSupplierkpiItem tawSupplierkpiItem) {
        if (tawSupplierkpiItem != null) {
            // tawSupplierkpiItem.setAssessStatus(1);
            tawSupplierkpiItem.setDeleted(SupplierkpiConstants.DELETED);
            getHibernateTemplate().update(tawSupplierkpiItem);
        }
    }

    public List getItems(final String whereStr) {
        return getHibernateTemplate().find(whereStr);
    }
}
