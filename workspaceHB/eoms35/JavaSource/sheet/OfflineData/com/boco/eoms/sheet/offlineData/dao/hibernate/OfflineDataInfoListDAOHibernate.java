package com.boco.eoms.sheet.offlineData.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.businessupport.product.model.TrancePoint;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.offlineData.dao.IOfflineDataInfoListDAO;
import com.boco.eoms.sheet.offlineData.dao.IOfflineDataMainDAO;
import com.boco.eoms.sheet.offlineData.model.OfflineDataInfoList;
/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 * 
 * @author liuyang
 * @version 3.5
 * 
 */
 
 public class OfflineDataInfoListDAOHibernate extends BaseSheetDaoHibernate implements IOfflineDataInfoListDAO {
 	  
 	
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getInfoListName() {
		return "OfflineDataInfoList";
	  }
	  
	  public HashMap getListByHqlBy(final Integer curPage,final  Integer pageSize, final String hql,final String condictions, final String modelname) throws Exception {
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					HashMap map = new HashMap();
				    Integer totalCount;
					String queryCountStr = "select count(*)  from " + modelname + condictions  ;
					Query queryCount = session.createQuery(queryCountStr);
					List list = queryCount.list();
					if (!list.isEmpty()) {
						totalCount = (Integer) list.get(0);
					} else
						totalCount = new Integer(0);
				    
				    Query query = session.createQuery(hql);
				    //分页查询条
					if (pageSize.intValue()!= -1) {
						query.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
						query.setMaxResults(pageSize.intValue());
					}
					 map.put("total", totalCount);
					 map.put("taskList", query.list());
					 return map;
				}
				};
				return (HashMap)getHibernateTemplate().execute(callback);
		}
	

	/**
	 * 根据拼写的hql去更新model类
	 */
	public void updateModelByHql(final String hql) throws Exception {
		HibernateCallback callBack = new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return new Integer(query.executeUpdate());
			}
			
		};
		getHibernateTemplate().execute(callBack);
	}

	public void delete(String id) throws Exception {
		getHibernateTemplate().delete(getBusinessupport(id));
	}
	
	public OfflineDataInfoList getBusinessupport(final String id) throws Exception{
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from OfflineDataInfoList t where t.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (OfflineDataInfoList) result.iterator().next();
				} else {
					return new OfflineDataInfoList();
				}
			}
		};
		return (OfflineDataInfoList) getHibernateTemplate().execute(callback);
	}
	public void saveOrupdate(Object obj) throws Exception {
		OfflineDataInfoList info = (OfflineDataInfoList) obj;
		getHibernateTemplate().saveOrUpdate(info);
	}
	/**
	 * 根据工单号获得全部信息
	 */
	public HashMap getAllNumberApplyInfoidByMainid(final String mainid ,final  Integer pageSize, final Integer curPage)
			throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				HashMap map = new HashMap();
				String hql = "from OfflineDataInfoList as  agent where (agent.mainid =  '" + mainid + "' and agent.isdelete='0')";
			    Integer totalCount;
			    String queryCountStr = "select count(*) c from (select * from  OfflineData_InfoList    agent where agent.mainid =  '" + mainid + "' and agent.isdelete='0')";
			    SQLQuery queryCount = session.createSQLQuery(queryCountStr);
			    queryCount.addScalar("c", Hibernate.INTEGER);
				List list = queryCount.list();
				if (!list.isEmpty()) {
					totalCount = (Integer) list.get(0);
				} else
					totalCount = new Integer(0);
			    
			    Query query = session.createQuery(hql);
//			  分页查询条
				if (pageSize.intValue() != -1) {
					query.setFirstResult(pageSize.intValue()
							* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
				}
				 map.put("total", totalCount);
				 map.put("taskList", query.list());
				 return map;
			}
			};
			return (HashMap)getHibernateTemplate().execute(callback);
	}
 }