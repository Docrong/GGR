package com.boco.eoms.workbench.infopub.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.workbench.infopub.dao.ForumsDao;
import com.boco.eoms.workbench.infopub.model.Forums;

/**
 * <p>
 * Title:版块操作dao的hibernate实现
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:01:07 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */
public class ForumsDaoHibernate extends BaseDaoHibernate implements ForumsDao,
        ID2NameDAO {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.workbench.infopub.dao.ForumsDao#getForumsByParentId(java.lang.String,
     *      java.lang.Integer)
     */
    public List getForumsByParentId(final String parentId, final Integer num) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Forums forums where forums.parentId=:parentId and forums.isDel<>"
                        + Constants.DELETED_FLAG;

                Query query = session.createQuery(queryStr);
                query.setString("parentId", parentId);
                query.setFirstResult(0);
                query.setMaxResults(num.intValue());
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ForumsDao#getForumss(com.boco.eoms.workbench.infopub.model.Forums)
     */
    public List getForumss(final Forums forums) {
        return getHibernateTemplate().find(
                "from Forums forums where forums.isDel<>"
                        + Constants.DELETED_FLAG);

        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (forums == null) { return
         * getHibernateTemplate().find("from Forums"); } else { // filter on
         * properties set in the forums HibernateCallback callback = new
         * HibernateCallback() { public Object doInHibernate(Session session)
         * throws HibernateException { Example ex =
         * Example.create(forums).ignoreCase().enableLike(MatchMode.ANYWHERE);
         * return session.createCriteria(Forums.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ForumsDao#getForums(String id)
     */
    public Forums getForums(final String id) {
        // filter on properties set in the forums
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Forums forums where forums.isDel<>"
                        + Constants.DELETED_FLAG + " and forums.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (Forums) result.iterator().next();
                } else {
                    return new Forums();
                }
            }
        };
        return (Forums) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ForumsDao#saveForums(Forums
     * forums)
     */
    public void saveForums(final Forums forums) {
        if ((forums.getId() == null) || (forums.getId().equals("")))
            getHibernateTemplate().save(forums);
        else
            getHibernateTemplate().saveOrUpdate(forums);
    }

    /**
     * @see com.boco.eoms.workbench.infopub.dao.ForumsDao#removeForums(String
     * id)
     */
    public void removeForums(final String id) {
        getHibernateTemplate().delete(getForums(id));
    }

    /**
     * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
     * where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getForumss(final Integer curPage, final Integer pageSize,
                          final String whereStr) {
        // filter on properties set in the forums
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Forums forums where forums.isDel<>"
                        + Constants.DELETED_FLAG;
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

    public Map getForumss(final Integer curPage, final Integer pageSize) {
        return this.getForumss(curPage, pageSize, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.workbench.infopub.dao.ForumsDao#getForumsByParentId(java.lang.String)
     */
    public List getForumsByParentId(final String parentId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Forums forums where forums.parentId=:parentId and forums.isDel<>"
                        + Constants.DELETED_FLAG;

                Query query = session.createQuery(queryStr);
                query.setString("parentId", parentId);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        Forums forums = this.getForums(id);
        if (forums == null) {
            return "";
        }
        return forums.getTitle();
    }

    //2009-04-10 根据专题名得到专题对象
    public Forums getForumsByTitle(final String title) {
        String queryStr = "from Forums forums where forums.isDel<>"
                + Constants.DELETED_FLAG + " and forums.title='" + title + "'";
        System.out.println("--------- queryStr = " + queryStr);
        List list = (List) getHibernateTemplate().find(queryStr);
        if (list != null && !list.isEmpty())
            return (Forums) list.get(0);
        else return new Forums();
    }

}
