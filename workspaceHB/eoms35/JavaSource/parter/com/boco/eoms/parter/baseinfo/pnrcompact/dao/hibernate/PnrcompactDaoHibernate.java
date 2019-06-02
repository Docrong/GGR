
package com.boco.eoms.parter.baseinfo.pnrcompact.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact;
import com.boco.eoms.parter.baseinfo.pnrcompact.dao.IPnrcompactDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class PnrcompactDaoHibernate extends BaseDaoHibernate implements IPnrcompactDao {

    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.dao.PnrcompactDao#getPnrcompacts(com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact)
     */
    public List getPnrcompacts(final Pnrcompact pnrcompact) {
        return getHibernateTemplate().find("from Pnrcompact");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pnrcompact == null) {
            return getHibernateTemplate().find("from Pnrcompact");
        } else {
            // filter on properties set in the pnrcompact
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pnrcompact).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Pnrcompact.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.dao.PnrcompactDao#getPnrcompact(String id)
     */
    public Pnrcompact getPnrcompact(final String id) {
        Pnrcompact pnrcompact = (Pnrcompact) getHibernateTemplate().get(Pnrcompact.class, id);
        if (pnrcompact == null) {
            throw new ObjectRetrievalFailureException(Pnrcompact.class, id);
        }

        return pnrcompact;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.dao.PnrcompactDao#savePnrcompact(Pnrcompact pnrcompact)
     */    
    public void savePnrcompact(final Pnrcompact pnrcompact) {
        if ((pnrcompact.getId() == null) || (pnrcompact.getId().equals("")))
			getHibernateTemplate().save(pnrcompact);
		else
			getHibernateTemplate().saveOrUpdate(pnrcompact);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.dao.PnrcompactDao#removePnrcompact(String id)
     */
    public void removePnrcompact(final String id) {
        getHibernateTemplate().delete(getPnrcompact(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.dao.PnrcompactDao#getPnrcompacts(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getPnrcompacts(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the pnrcompact
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from Pnrcompact";
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
    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.dao.PnrcompactDao#getPnrcompacts(final Integer curPage, final Integer pageSize)
     */    
    public Map getPnrcompacts(final Integer curPage, final Integer pageSize) {
			return this.getPnrcompacts(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.dao.PnrcompactDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from Pnrcompact obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
