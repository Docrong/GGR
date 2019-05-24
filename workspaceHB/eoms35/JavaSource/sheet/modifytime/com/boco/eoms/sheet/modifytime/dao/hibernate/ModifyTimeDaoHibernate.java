
package com.boco.eoms.sheet.modifytime.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.modifytime.model.ModifyTime;
import com.boco.eoms.sheet.modifytime.dao.IModifyTimeDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ModifyTimeDaoHibernate extends BaseDaoHibernate implements IModifyTimeDao {

    /**
     * @see com.boco.eoms.sheet.modifytime.dao.ModifyTimeDao#getModifyTimes(com.boco.eoms.sheet.modifytime.model.ModifyTime)
     */
    public List getModifyTimes() {
        return getHibernateTemplate().find("from ModifyTime");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (modifytime == null) {
            return getHibernateTemplate().find("from ModifyTime");
        } else {
            // filter on properties set in the modifytime
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(modifytime).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(ModifyTime.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.sheet.modifytime.dao.ModifyTimeDao#getModifyTime(String id)
     */
    public ModifyTime getModifyTime(final String id) {
        ModifyTime modifytime = (ModifyTime) getHibernateTemplate().get(ModifyTime.class, id);
        if (modifytime == null) {
            throw new ObjectRetrievalFailureException(ModifyTime.class, id);
        }

        return modifytime;
    }

    /**
     * @see com.boco.eoms.sheet.modifytime.dao.ModifyTimeDao#saveModifyTime(ModifyTime modifytime)
     */    
    public void saveModifyTime(final ModifyTime modifytime) {
        if ((modifytime.getId() == null) || (modifytime.getId().equals("")))
			getHibernateTemplate().save(modifytime);
		else
			getHibernateTemplate().saveOrUpdate(modifytime);
    }

    /**
     * @see com.boco.eoms.sheet.modifytime.dao.ModifyTimeDao#removeModifyTime(String id)
     */
    public void removeModifyTime(final String id) {
        getHibernateTemplate().delete(getModifyTime(id));
    }
    /**
     * @see com.boco.eoms.sheet.modifytime.dao.ModifyTimeDao#getModifyTimes(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getModifyTimes(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the modifytime
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from ModifyTime";
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
     * @see com.boco.eoms.sheet.modifytime.dao.ModifyTimeDao#getModifyTimes(final Integer curPage, final Integer pageSize)
     */    
    public Map getModifyTimes(final Integer curPage, final Integer pageSize) {
			return this.getModifyTimes(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.sheet.modifytime.dao.ModifyTimeDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from ModifyTime obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
    /**
	 * @see com.boco.eoms.sheet.modifytime.dao.IModifyTimeDao#getModifyTimesByCondition(java.lang.String)
	 */
	public List getModifyTimesByCondition(String condition) {
		String hql ="";//"from ModifyTime where 1=1 ";
		hql += condition;
		return getHibernateTemplate().find(hql);		
	}
}
