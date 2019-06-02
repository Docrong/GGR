
package com.boco.eoms.commons.statistic.customstat.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.statistic.customstat.dao.IStSubscriptionDao;
import com.boco.eoms.commons.statistic.customstat.model.StSubscription;

public class StSubscriptionDaoHibernate extends BaseDaoHibernate implements IStSubscriptionDao {

    /**
     * @see com.boco.eoms.piccunflow.dao.NetBiudSafeMgrDao#getNetBiudSafeMgrs(com.boco.eoms.piccunflow.model.NetBiudSafeMgr)
     */
    public List getStSubscription() {
        return getHibernateTemplate().find("from StSubscription");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (netBiudSafeMgr == null) {
            return getHibernateTemplate().find("from NetBiudSafeMgr");
        } else {
            // filter on properties set in the netBiudSafeMgr
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(netBiudSafeMgr).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(NetBiudSafeMgr.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.piccunflow.dao.NetBiudSafeMgrDao#getNetBiudSafeMgr(String id)
     */
    public StSubscription getStSubscription(final String id) {
        StSubscription stSubscription = (StSubscription) getHibernateTemplate().get(StSubscription.class, id);
        if (stSubscription == null) {
            throw new ObjectRetrievalFailureException(StSubscription.class, id);
        }

        return stSubscription;
    }
    
    public StSubscription getStSubscriptionForSubId(final String subId)
    {
        final String hql = " from StSubscription obj where obj.subId='"+ subId+"'" ;
		
    	ArrayList list= (ArrayList) getHibernateTemplate().find(hql);
    	StSubscription st=  (StSubscription)list.get(0) ;
        return st;
    }

    /**
     * @see com.boco.eoms.piccunflow.dao.NetBiudSafeMgrDao#saveNetBiudSafeMgr(NetBiudSafeMgr netBiudSafeMgr)
     */    
    public void saveStSubscription(final StSubscription stSubscription) {
        if ((stSubscription.getId() == null) || (stSubscription.getId().equals("")))
			getHibernateTemplate().save(stSubscription);
		else
			getHibernateTemplate().saveOrUpdate(stSubscription);
    }

    /**
     * @see com.boco.eoms.piccunflow.dao.NetBiudSafeMgrDao#removeNetBiudSafeMgr(String id)
     */
    public void removeStSubscription(final String id) {
        getHibernateTemplate().delete(getStSubscription(id));
    }
    /**
     * @see com.boco.eoms.piccunflow.dao.NetBiudSafeMgrDao#getNetBiudSafeMgrs(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getStSubscription(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the netBiudSafeMgr
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from StSubscription";
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
     * @see com.boco.eoms.piccunflow.dao.NetBiudSafeMgrDao#getNetBiudSafeMgrs(final Integer curPage, final Integer pageSize)
     */    
    public Map getStSubscription(final Integer curPage, final Integer pageSize) {
			return this.getStSubscription(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.piccunflow.dao.NetBiudSafeMgrDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from StSubscription obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
   
	
	/**
	 * @see com.boco.eoms.piccunflow.dao.INetBiudSafeMgrDao#getNetBiudSafeMgrsByCondition(java.lang.String)
	 */

	
	
	public List getStSubscriptionsByCondition(String condition) {
		String hql = "from StSubscription where 1=1 ";
		hql += condition;
		return getHibernateTemplate().find(hql);		
	}

	public Map getStSubscriptions(Integer curPage, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map getStSubscriptions(Integer curPage, Integer pageSize,
			String whereStr) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * countJOB-5742-090107-100-001
	 * @see com.boco.eoms.custom.dao.IStSubscriptionDao#getCountSubId(java.lang.String)
	 */
	  public int getCountSubId(final String subId) {
		  final  String subid=subId.substring(9, 19);
		  HibernateCallback callback = new HibernateCallback() {
	            public Object doInHibernate(Session session) throws HibernateException {
	           String hql = "select count(*) from StSubscription obj where obj.subId like '%"+ subid + "%'";
	              Integer total = (Integer) session.createQuery(hql).iterate().next();
				  return total;
	            }
	        };	  
	        return ((Integer)getHibernateTemplate().execute(callback)).intValue();
	      }
	  public int  getOldcustomdata(final String stat_mode,final String item,final String subscriber,final String statfromdate,final String stattodate,final String remark){
		  HibernateCallback callback = new HibernateCallback() {
	            public Object doInHibernate(Session session) throws HibernateException {
	           String hql=""; 
	           if(stat_mode.equals("3")){
hql = "select count(*) from StSubscription obj where obj.statMode="+stat_mode+" and obj.item='"+item+"' and obj.subscriber='"+subscriber+"' and obj.remark='"+remark+"' and obj.statfromdate=to_date('"+statfromdate+"','yyyy-mm-dd hh24:mi:ss') and obj.stattodate=to_date('"+stattodate+"','yyyy-mm-dd hh24:mi:ss')" ;
	           }else{
hql = "select count(*) from StSubscription obj where obj.statMode="+stat_mode+" and obj.item='"+item+"' and obj.subscriber='"+subscriber+"' and obj.remark='"+remark+"'" ;
	           
	             }
	            Integer total = (Integer) session.createQuery(hql).iterate().next();
				  return total;
	            }
	        };	  
		  return ((Integer)getHibernateTemplate().execute(callback)).intValue();
	  }
}
