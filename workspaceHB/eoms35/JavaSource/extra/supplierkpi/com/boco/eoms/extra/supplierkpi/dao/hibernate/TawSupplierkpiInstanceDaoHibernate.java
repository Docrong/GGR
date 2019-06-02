
package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.DateUtil;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInstanceDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSupplierkpiInstanceDaoHibernate extends BaseDaoHibernate implements TawSupplierkpiInstanceDao {
	

    public List getItemType(final String whereStr) {
		String hql = "select distinct itemType from TawSupplierkpiInstance "+whereStr;
		return getHibernateTemplate().find(hql);
	}

	public List getManufacturerName(final String whereStr) {
		String hql = "select distinct manufacturerName from TawSupplierkpiInstance "+whereStr;
		return getHibernateTemplate().find(hql);
	}
	

	public List getManufacturerName() {
		String hql = " from TawSupplierkpiInfo";
		return getHibernateTemplate().find(hql);		
	}	

	/**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInstanceDao#getTawSupplierkpiInstances(com.boco.eoms.commons.sample.model.TawSupplierkpiInstance)
     */
    public List getTawSupplierkpiInstances(final TawSupplierkpiInstance tawSupplierkpiInstance) {
        return getHibernateTemplate().find("from TawSupplierkpiInstance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSupplierkpiInstance == null) {
            return getHibernateTemplate().find("from TawSupplierkpiInstance");
        } else {
            // filter on properties set in the tawSupplierkpiInstance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSupplierkpiInstance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSupplierkpiInstance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInstanceDao#getTawSupplierkpiInstance(String id)
     */
    public TawSupplierkpiInstance getTawSupplierkpiInstance(final String id) {
        TawSupplierkpiInstance tawSupplierkpiInstance = (TawSupplierkpiInstance) getHibernateTemplate().get(TawSupplierkpiInstance.class, id);
        if (tawSupplierkpiInstance == null) {
            throw new ObjectRetrievalFailureException(TawSupplierkpiInstance.class, id);
        }

        return tawSupplierkpiInstance;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInstanceDao#saveTawSupplierkpiInstance(TawSupplierkpiInstance tawSupplierkpiInstance)
     */    
    public void saveTawSupplierkpiInstance(final TawSupplierkpiInstance tawSupplierkpiInstance) {
        if ((tawSupplierkpiInstance.getId() == null) || (tawSupplierkpiInstance.getId().equals("")))
			getHibernateTemplate().save(tawSupplierkpiInstance);
		else
			getHibernateTemplate().saveOrUpdate(tawSupplierkpiInstance);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInstanceDao#removeTawSupplierkpiInstance(String id)
     */
    public void removeTawSupplierkpiInstance(final String id) {
        getHibernateTemplate().delete(getTawSupplierkpiInstance(id));
    }
    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawSupplierkpiInstances(final int curPage, final int pageSize,final String whereStr,final String countStr) {
        // filter on properties set in the tawSupplierkpiInstance
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr1 = "from TawSupplierkpiInstance";
              String queryStr2 = "from TawSupplierkpiInstance";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr1 += whereStr;
                    queryStr2 += countStr;
            	String queryCountStr = "select count(*) " + queryStr2;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr1);
							query.setFirstResult(pageSize
									* (curPage));
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
    public Map getTawSupplierkpiInstances(final int curPage, final int pageSize) {
			return this.getTawSupplierkpiInstances(curPage,pageSize,null,null);
		}

	public List getTawSupplierkpiItems(String queryStr) {
		// TODO Auto-generated method stub		
		return getHibernateTemplate().find(queryStr);
	}

	public String getTawSupplierNameById(String id) {
		// TODO Auto-generated method stub
		TawSupplierkpiInfo supplierkpiInfo = (TawSupplierkpiInfo) getHibernateTemplate().get(TawSupplierkpiInfo.class, id);
        if (supplierkpiInfo == null) {
            throw new ObjectRetrievalFailureException(TawSupplierkpiInstance.class, id);
        }

        return supplierkpiInfo.getSupplierName();

	}
/**
 * 生成供应商考核实例 
 */
	public void makeTawSupplierkpiInstance(TawSupplierkpiInstance tawSupplierkpiInstance) {
			getHibernateTemplate().save(tawSupplierkpiInstance);
//			getHibernateTemplate().flush();
//			getHibernateTemplate().clear();		
	}
	
    public List getTawSupplierkpiInstances(final String whereStr) {
        return getHibernateTemplate().find(whereStr);
    }
    
    public List getTawSupplierkpiInstances(final String whereStr, final String reportTime) {
    	HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
            	Query query = session.createQuery(whereStr);
            	Date date=null;
				try {
					date = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",
							reportTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				query.setDate("reportTime", date);
				List list = query.list();
				return list;
            }
        };     
        return (List) getHibernateTemplate().execute(callback);
        
    }
    
    public List getTawSupplierkpiItemNames(final String specialType){
    	String hql = " from TawSupplierkpiItem as tawSupplierkpiItem where tawSupplierkpiItem.isImpersonality ='1030102' and tawSupplierkpiItem.assessMoment='106010603' and tawSupplierkpiItem.assessStatus=3 and tawSupplierkpiItem.deleted=1 and tawSupplierkpiItem.itemType like '"+specialType+"%'";
    	return getHibernateTemplate().find(hql);
    }
    
	public String getUserNamebySubRoleidUserstatus(String roleid){	
		String hql = " from TawSystemUser tuser,TawSystemUserRefRole refrole where refrole.subRoleid='"
				+ roleid + "' and tuser.userid=refrole.userid";
		List userNamelist = getHibernateTemplate().find(hql);
		String userName = "";
		for(int i=0;i<userNamelist.size();i++){
			TawSystemUser tawSystemUser = (TawSystemUser)((Object[])userNamelist.get(0))[0];
			userName = tawSystemUser.getUsername();
		}
	  return userName;
    }    


	public Map getTawSupplierkpiInstances(final String fillStartTime, final String fillEndTime, final int curPage, final int pageSize, final String whereStr,final String countStr) {
		HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr1 = "from TawSupplierkpiInstance";
              String queryStr2 = "from TawSupplierkpiInstance";
              if(whereStr!=null && whereStr.length()>0){
            		queryStr1 += whereStr;
              }else{
            	  queryStr1 += " where 1=1 ";
              }
              if(countStr!=null && countStr.length()>0){
                    queryStr2 += countStr;
              }else{
            	  queryStr2 += " where 1=1 ";
              }
                    if (null != fillStartTime && !"".equals(fillStartTime)) {
                    	queryStr1 += " and firstFillTime>=:fillStartTime";
                    	queryStr2 += " and firstFillTime>=:fillStartTime";
					}
                    if (null != fillEndTime && !"".equals(fillEndTime)) {
                    	queryStr1 += " and firstFillTime<=:fillEndTime";
                    	queryStr2 += " and firstFillTime<=:fillEndTime";
                    }
            	String queryCountStr = "select count(*) " + queryStr2;

            				Query countQuery = session.createQuery(queryCountStr);
            				Query query = session.createQuery(queryStr1);
            				if (null != fillStartTime && !"".equals(fillStartTime)) {
            					Date date=null;
            					try {
            						date = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",
            								fillStartTime);
            					} catch (ParseException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					}
            					countQuery.setDate("fillStartTime", date);
            					query.setDate("fillStartTime", date);
            				}
            				if (null != fillEndTime && !"".equals(fillEndTime)) {
            					Date date=null;
            					try {
            						date = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",
            								fillEndTime);
            					} catch (ParseException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					}
            					countQuery.setDate("fillEndTime", date);
            					query.setDate("fillEndTime", date);
            				}
							Integer total = (Integer) countQuery.iterate()
									.next();
							query.setFirstResult(pageSize
									* (curPage));
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
}
