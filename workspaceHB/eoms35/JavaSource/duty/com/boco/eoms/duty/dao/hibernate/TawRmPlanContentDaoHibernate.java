
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmPlanContent;
import com.boco.eoms.duty.dao.ITawRmPlanContentDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmPlanContentDaoHibernate extends BaseDaoHibernate implements ITawRmPlanContentDao {

    /**
     * @see com.boco.eoms.duty.dao.TawRmPlanContentDao#getTawRmPlanContents(com.boco.eoms.duty.model.TawRmPlanContent)
     */
    public List getTawRmPlanContents(final TawRmPlanContent tawRmPlanContent) {
        return getHibernateTemplate().find("from TawRmPlanContent");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmPlanContent == null) {
            return getHibernateTemplate().find("from TawRmPlanContent");
        } else {
            // filter on properties set in the tawRmPlanContent
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmPlanContent).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmPlanContent.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmPlanContentDao#getTawRmPlanContent(String id)
     */
    public TawRmPlanContent getTawRmPlanContent(final String id) {
        TawRmPlanContent tawRmPlanContent = (TawRmPlanContent) getHibernateTemplate().get(TawRmPlanContent.class, id);
        if (tawRmPlanContent == null) {
            throw new ObjectRetrievalFailureException(TawRmPlanContent.class, id);
        }

        return tawRmPlanContent;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmPlanContentDao#saveTawRmPlanContent(TawRmPlanContent tawRmPlanContent)
     */    
    public void saveTawRmPlanContent(final TawRmPlanContent tawRmPlanContent) {
        if ((tawRmPlanContent.getId() == null) || (tawRmPlanContent.getId().equals("")))
			getHibernateTemplate().save(tawRmPlanContent);
		else
			getHibernateTemplate().saveOrUpdate(tawRmPlanContent);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmPlanContentDao#removeTawRmPlanContent(String id)
     */
    public void removeTawRmPlanContent(final String id) {
        getHibernateTemplate().delete(getTawRmPlanContent(id));
    }
    /**
     * @see com.boco.eoms.duty.dao.TawRmPlanContentDao#getTawRmPlanContents(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmPlanContents(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmPlanContent
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmPlanContent";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							System.out.println("queryStr=============="+queryStr);
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
    /**
     * @see com.boco.eoms.duty.dao.TawRmPlanContentDao#getTawRmPlanContents(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmPlanContents(final Integer curPage, final Integer pageSize) {
			return this.getTawRmPlanContents(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmPlanContentDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmPlanContent obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
