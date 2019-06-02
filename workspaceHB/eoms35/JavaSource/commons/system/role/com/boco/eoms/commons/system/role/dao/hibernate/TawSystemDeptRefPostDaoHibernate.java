
package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.role.model.TawSystemDeptRefPost;
import com.boco.eoms.commons.system.role.dao.TawSystemDeptRefPostDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemDeptRefPostDaoHibernate extends BaseDaoHibernate implements TawSystemDeptRefPostDao {

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemDeptRefPostDao#getTawSystemDeptRefPosts(com.boco.eoms.commons.system.role.model.TawSystemDeptRefPost)
     */
    public List getTawSystemDeptRefPosts(final TawSystemDeptRefPost tawSystemDeptRefPost) {
        return getHibernateTemplate().find("from TawSystemDeptRefPost");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSystemDeptRefPost == null) {
            return getHibernateTemplate().find("from TawSystemDeptRefPost");
        } else {
            // filter on properties set in the tawSystemDeptRefPost
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSystemDeptRefPost).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSystemDeptRefPost.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemDeptRefPostDao#getTawSystemDeptRefPost(Long id)
     */
    public TawSystemDeptRefPost getTawSystemDeptRefPost(final Long id) {
        TawSystemDeptRefPost tawSystemDeptRefPost = (TawSystemDeptRefPost) getHibernateTemplate().get(TawSystemDeptRefPost.class, id);
        if (tawSystemDeptRefPost == null) {
            throw new ObjectRetrievalFailureException(TawSystemDeptRefPost.class, id);
        }

        return tawSystemDeptRefPost;
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemDeptRefPostDao#saveTawSystemDeptRefPost(TawSystemDeptRefPost tawSystemDeptRefPost)
     */    
    public void saveTawSystemDeptRefPost(final TawSystemDeptRefPost tawSystemDeptRefPost) {
        if ((tawSystemDeptRefPost.getId() == null) || (tawSystemDeptRefPost.getId().equals("")))
			getHibernateTemplate().save(tawSystemDeptRefPost);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemDeptRefPost);
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemDeptRefPostDao#removeTawSystemDeptRefPost(Long id)
     */
    public void removeTawSystemDeptRefPost(final Long id) {
        getHibernateTemplate().delete(getTawSystemDeptRefPost(id));
    }
    public void removeDeptRefPostByPostId(Long postId){
    	String hql = "from TawSystemDeptRefPost t where t.postId="+postId;
    	List list = getHibernateTemplate().find(hql);
    	for(int i=0;i<list.size();i++){
    		TawSystemDeptRefPost t = (TawSystemDeptRefPost)list.get(i);
    		getHibernateTemplate().delete(t);
    	}
    	getHibernateTemplate().flush();
    }

    public Map getTawSystemDeptRefPosts(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawSystemDeptRefPost
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSystemDeptRefPost";
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
    public Map getTawSystemDeptRefPosts(final Integer curPage, final Integer pageSize) {
			return this.getTawSystemDeptRefPosts(curPage,pageSize,null);
		}
    
    /**
     * @param deptId
     * @return <TawSystemPost>
     */
    public List getPostByDeptId(String deptId){
    	List postList = new ArrayList();
    	String hql = "from TawSystemPost post,TawSystemDeptRefPost ref where post.postId=ref.postId and ref.deptId='"+deptId+"'";
    	List list = getHibernateTemplate().find(hql);
    	for(int i=0;i<list.size();i++){
    		postList.add(((Object[])list.get(i))[0]);
    	}
    	
    	return postList;
    }

}
