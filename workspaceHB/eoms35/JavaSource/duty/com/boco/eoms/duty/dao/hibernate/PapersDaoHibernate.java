package com.boco.eoms.duty.dao.hibernate;

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
import com.boco.eoms.duty.dao.PapersDao;
import com.boco.eoms.duty.model.Papers;
import com.boco.eoms.duty.model.PapersPart;

/**
 * <p>
 * Title:资料 dao的hibernate实现
 * </p>
 * <p>
 * Description:资料
 * </p>
 * <p>
 * Tue Apr 07 10:05:44 CST 2009
 * </p>
 *
 * @author lixiaoming
 * @version 3.5
 */
public class PapersDaoHibernate extends BaseDaoHibernate implements PapersDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.duty.PapersDao#getPaperss()
     */
    public List getPaperss() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Papers papers";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.duty.PapersDao#getPapers(java.lang.String)
     */
    public Papers getPapers(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Papers papers where papers.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (Papers) result.iterator().next();
                } else {
                    return new Papers();
                }
            }
        };
        return (Papers) getHibernateTemplate().execute(callback);
    }

    public PapersPart getPapersPart(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from PapersPart paperspart where paperspart.papersId=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (PapersPart) result.iterator().next();
                } else {
                    return new PapersPart();
                }
            }
        };
        return (PapersPart) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.duty.PapersDao#savePaperss(com.boco.eoms.duty.Papers)
     */
    public void savePapers(final Papers papers) {
        if ((papers.getId() == null) || (papers.getId().equals("")))
            getHibernateTemplate().save(papers);
        else
            getHibernateTemplate().saveOrUpdate(papers);
    }

    public void savePapersPart(final PapersPart paperspart) {
        if ((paperspart.getId() == null) || (paperspart.getId().equals("")))
            getHibernateTemplate().save(paperspart);
        else
            getHibernateTemplate().saveOrUpdate(paperspart);
    }

    /**
     * @see com.boco.eoms.duty.PapersDao#removePaperss(java.lang.String)
     */
    public void removePapers(final String id) {
        getHibernateTemplate().delete(getPapers(id));
    }

    public void removePapersPart(final String id) {
        getHibernateTemplate().delete(getPapersPart(id));
    }


    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        Papers papers = this.getPapers(id);
        if (papers == null) {
            return "";
        }
        //TODO 请修改代码
        return papers.getTitle();
    }

    /**
     * @see com.boco.eoms.duty.PapersDao#getPaperss(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getPaperss(final Integer curPage, final Integer pageSize,
                          final String whereStr, final String userId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Papers papers where papers.insertUserId = '" + userId + "'";
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

    public Map getsearchTixing(final Integer curPage, final Integer pageSize,
                               final String whereStr, final String userId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Papers papers where id not in  (select papersId from PapersPart where insertUserId = '" + userId + "')";
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

    public Map getPerson() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSystemUser tawSystemuser where deptId = 104";
                Query query = session.createQuery(queryStr);
                List result = query.list();
                HashMap map = new HashMap();
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map getPapersId() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from PapersPart paperspart ";
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