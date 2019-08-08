package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiTemplateDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSupplierkpiTemplateDaoHibernate extends BaseDaoHibernate
        implements TawSupplierkpiTemplateDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiTemplateDao#getTawSupplierkpiTemplates(com.boco.eoms.commons.sample.model.TawSupplierkpiTemplate)
     */
    public List getTawSupplierkpiTemplates(
            final TawSupplierkpiTemplate tawSupplierkpiTemplate) {
        return getHibernateTemplate().find("from TawSupplierkpiTemplate");

        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (tawSupplierkpiTemplate ==
         * null) { return getHibernateTemplate().find("from
         * TawSupplierkpiTemplate"); } else { // filter on properties set in the
         * tawSupplierkpiTemplate HibernateCallback callback = new
         * HibernateCallback() { public Object doInHibernate(Session session)
         * throws HibernateException { Example ex =
         * Example.create(tawSupplierkpiTemplate).ignoreCase().enableLike(MatchMode.ANYWHERE);
         * return
         * session.createCriteria(TawSupplierkpiTemplate.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiTemplateDao#getTawSupplierkpiTemplate(String
     * id)
     */
    public TawSupplierkpiTemplate getTawSupplierkpiTemplate(final String id) {
        TawSupplierkpiTemplate tawSupplierkpiTemplate = (TawSupplierkpiTemplate) getHibernateTemplate()
                .get(TawSupplierkpiTemplate.class, id);
        if (tawSupplierkpiTemplate == null) {
            throw new ObjectRetrievalFailureException(
                    TawSupplierkpiTemplate.class, id);
        }

        return tawSupplierkpiTemplate;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiTemplateDao#saveTawSupplierkpiTemplate(TawSupplierkpiTemplate
     * tawSupplierkpiTemplate)
     */
    public void saveTawSupplierkpiTemplate(
            final TawSupplierkpiTemplate tawSupplierkpiTemplate) {
        // if ((tawSupplierkpiTemplate.getId() == null) ||
        // (tawSupplierkpiTemplate.getId().equals("")))
        // getHibernateTemplate().save(tawSupplierkpiTemplate);
        // else
        getHibernateTemplate().saveOrUpdate(tawSupplierkpiTemplate);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiTemplateDao#removeTawSupplierkpiTemplate(String
     * id)
     */
    public void removeTawSupplierkpiTemplate(final String id) {
        getHibernateTemplate().delete(getTawSupplierkpiTemplate(id));
    }

    /**
     * curPage pageSize whereStr sql filter
     */
    public Map getTawSupplierkpiTemplates(final int curPage,
                                          final int pageSize, final String whereStr) {
        // filter on properties set in the tawSupplierkpiTemplate
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSupplierkpiTemplate";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
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

    public Map getTawSupplierkpiTemplates(final int curPage, final int pageSize) {
        return this.getTawSupplierkpiTemplates(curPage, pageSize, null);
    }

    public String getTemplateIdBySpecialType(final String specialType) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "select id from TawSupplierkpiTemplate where specialtype='"
                        + specialType + "'";
                Query query = session.createQuery(queryStr);
                List result = query.list();
                String templateId = "";
                if (result.size() > 0) {
                    templateId = result.get(0).toString();
                }
                return templateId;
            }
        };
        return getHibernateTemplate().execute(callback).toString();
    }

    public String getTemplateIdByServiceType(final String serviceType) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "select id from TawSupplierkpiTemplate where serviceType='"
                        + serviceType + "'";
                Query query = session.createQuery(queryStr);
                List result = query.list();
                String templateId = "";
                if (result.size() > 0) {
                    templateId = result.get(0).toString();
                }
                return templateId;
            }
        };
        return getHibernateTemplate().execute(callback).toString();
    }

    public List getNodesFromTemplate(final String whereStr) {
        return getHibernateTemplate().find(whereStr);
    }
}
