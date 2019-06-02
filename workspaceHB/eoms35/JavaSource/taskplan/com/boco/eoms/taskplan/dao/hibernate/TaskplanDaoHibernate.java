
package com.boco.eoms.taskplan.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.taskplan.dao.ITaskplanDao;
import com.boco.eoms.taskplan.model.Taskplan;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TaskplanDaoHibernate extends BaseDaoHibernate implements ITaskplanDao {

    /**
     * @see com.boco.eoms.taskplan.dao.TaskplanDao#getTaskplans(com.boco.eoms.taskplan.model.Taskplan)
     */
    public List getTaskplans(final Taskplan taskplan) {
        return getHibernateTemplate().find("from Taskplan");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (taskplan == null) {
            return getHibernateTemplate().find("from Taskplan");
        } else {
            // filter on properties set in the taskplan
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(taskplan).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Taskplan.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.taskplan.dao.TaskplanDao#getTaskplan(String id)
     */
    public Taskplan getTaskplan(final String id) {
        Taskplan taskplan = (Taskplan) getHibernateTemplate().get(Taskplan.class, id);
        if (taskplan == null) {
            throw new ObjectRetrievalFailureException(Taskplan.class, id);
        }

        return taskplan;
    }

    /**
     * @see com.boco.eoms.taskplan.dao.TaskplanDao#saveTaskplan(Taskplan taskplan)
     */    
    public void saveTaskplan(final Taskplan taskplan) {
        if ((taskplan.getId() == null) || (taskplan.getId().equals("")))
			getHibernateTemplate().save(taskplan);
		else
			getHibernateTemplate().saveOrUpdate(taskplan);
    }

    /**
     * @see com.boco.eoms.taskplan.dao.TaskplanDao#removeTaskplan(String id)
     */
    public void removeTaskplan(final String id) {
        getHibernateTemplate().delete(getTaskplan(id));
    }
    /**
     * @see com.boco.eoms.taskplan.dao.TaskplanDao#getTaskplans(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTaskplans(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the taskplan
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from Taskplan";
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
     * @see com.boco.eoms.taskplan.dao.TaskplanDao#getTaskplans(final Integer curPage, final Integer pageSize)
     */    
    public Map getTaskplans(final Integer curPage, final Integer pageSize) {
			return this.getTaskplans(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.taskplan.dao.TaskplanDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql =""; 
		if(parentId.equals("-1")){
			hql = " from Taskplan obj order by obj.id";
		}
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
