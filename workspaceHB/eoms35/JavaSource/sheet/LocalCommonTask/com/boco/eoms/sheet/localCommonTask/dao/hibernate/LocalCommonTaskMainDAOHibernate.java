package com.boco.eoms.sheet.localCommonTask.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.localCommonTask.dao.ILocalCommonTaskMainDAO;
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
 
 public class LocalCommonTaskMainDAOHibernate extends MainDAO implements ILocalCommonTaskMainDAO {
 	  
 	
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getMainName() {
		return "LocalCommonTaskMain";
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
	* 根据类名、更新的值和id更新动态表中的值
	*/
	public void updateDynamicModel(final String modelname, final String condiction, final String condictionValue, final String id) throws Exception {
		HibernateCallback callBack = new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "update " + modelname + " set "+ condiction +"= '" + condictionValue+ "' where id = '" +id + "'";
				Query query = session.createQuery(hql);
				return new Integer(query.executeUpdate());
			}
			
		};
		getHibernateTemplate().execute(callBack);
		
	}
	
	/**
	 * 根据类名，工单主键，查询条件和查询条件的值释放资源模块中已经选择的资源
	 */
	public void updateResourceClear(final String modelname, final String sheetKey, final String contidion, final String condictionValue) throws Exception {
		HibernateCallback callBack = new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "update "+ modelname +" set isOccupation = 0 ,sheetKey = '' where sheetKey = '" + sheetKey + "'and "+contidion +"= '" + condictionValue+"'";
				Query query = session.createQuery(hql);
				return new Integer(query.executeUpdate());
			}
			
		};
		getHibernateTemplate().execute(callBack);
		
	}
	
	/**
	 * 根据类名，工单主键，选中的记录的id将工单中选中的资源在资源模块中更新为占用
	 */
	public void updateResourceOccupied(final String modelname, final String sheetKey, final String id) throws Exception {
		HibernateCallback callBack = new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "update "+modelname +" set isOccupation = 2 ,sheetKey = '" + sheetKey + "' where id = '" + id + "'";
				Query query = session.createQuery(hql);
				return new Integer(query.executeUpdate());
			}
			
		};
		getHibernateTemplate().execute(callBack);
		
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
	
 }