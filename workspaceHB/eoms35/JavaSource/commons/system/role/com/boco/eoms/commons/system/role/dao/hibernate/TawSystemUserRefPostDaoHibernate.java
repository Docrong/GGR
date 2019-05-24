
package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.role.model.TawSystemUserRefPost;
import com.boco.eoms.commons.system.role.dao.TawSystemUserRefPostDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemUserRefPostDaoHibernate extends BaseDaoHibernate implements TawSystemUserRefPostDao {

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemUserRefPostDao#getTawSystemUserRefPosts(com.boco.eoms.commons.system.role.model.TawSystemUserRefPost)
     */
    public List getTawSystemUserRefPosts(final TawSystemUserRefPost tawSystemUserRefPost) {
        return getHibernateTemplate().find("from TawSystemUserRefPost");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSystemUserRefPost == null) {
            return getHibernateTemplate().find("from TawSystemUserRefPost");
        } else {
            // filter on properties set in the tawSystemUserRefPost
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSystemUserRefPost).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSystemUserRefPost.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemUserRefPostDao#getTawSystemUserRefPost(Long id)
     */
    public TawSystemUserRefPost getTawSystemUserRefPost(final Long id) {
        TawSystemUserRefPost tawSystemUserRefPost = (TawSystemUserRefPost) getHibernateTemplate().get(TawSystemUserRefPost.class, id);
        if (tawSystemUserRefPost == null) {
            throw new ObjectRetrievalFailureException(TawSystemUserRefPost.class, id);
        }

        return tawSystemUserRefPost;
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemUserRefPostDao#saveTawSystemUserRefPost(TawSystemUserRefPost tawSystemUserRefPost)
     */    
    public void saveTawSystemUserRefPost(final TawSystemUserRefPost tawSystemUserRefPost) {
        if ((tawSystemUserRefPost.getId() == null) || (tawSystemUserRefPost.getId().equals("")))
			getHibernateTemplate().save(tawSystemUserRefPost);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemUserRefPost);
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemUserRefPostDao#removeTawSystemUserRefPost(Long id)
     */
    public void removeTawSystemUserRefPost(final Long id) {
        getHibernateTemplate().delete(getTawSystemUserRefPost(id));
    }
    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawSystemUserRefPosts(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawSystemUserRefPost
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSystemUserRefPost";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
							query.setMaxResults(pageSize.intValue());
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", total);
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    public Map getTawSystemUserRefPosts(final Integer curPage, final Integer pageSize) {
			return this.getTawSystemUserRefPosts(curPage,pageSize,null);
		}

}
