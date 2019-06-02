/*
 * Created on 2008-4-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.dao.hibernate.TaskDAOImpl;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultTaskDAO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;

/**
 * @author IBM_USER
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CommonFaultTaskDAOHibernate extends TaskDAOImpl implements
		ICommonFaultTaskDAO {
	/**
	 * 根据查询条件查询任务信息, 并进行分页处理
	 * 
	 * @param hsql
	 *            查询语句
	 * @param curPage
	 *            分页起始
	 * @param pageSize
	 *            单页显示数量
	 * @return HashMap, 包括任务总量和任务列表
	 * @throws HibernateException
	 */
	public HashMap getCommonfaultTaskListByCondition(final HashMap entityMap,final String queryStr,final Integer curPage, final Integer pageSize)throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// 获取任务总数的查询sql
				HashMap map = new HashMap();
				try {
					int sql_distinct = queryStr.indexOf("distinct");

					int sql_index = queryStr.indexOf("from");
					int sql_orderby = queryStr.indexOf("order by");

					String queryCountStr;
					if (sql_distinct > 0) {
						int sql_comma = (queryStr.substring(sql_distinct,
								sql_index)).indexOf(",");
						if (sql_comma > 0) {
							queryCountStr = "select count("
									+ queryStr.substring(sql_distinct,
											sql_distinct + sql_comma)
									+ ") as c  ";
						} else {
							queryCountStr = "select count("
									+ queryStr.substring(sql_distinct,sql_index) + 
									") as c  ";
						}
					} else {
						queryCountStr = "select count(*) as c ";
					}
					if (sql_orderby > 0)
						queryCountStr += queryStr.substring(sql_index,
								sql_orderby);
					else
						queryCountStr += queryStr.substring(sql_index);

					Integer totalCount;

					SQLQuery totalQuery = session.createSQLQuery(queryCountStr);
					totalQuery.addScalar("c", Hibernate.INTEGER);
					List result = totalQuery.list();
					if (result != null && !result.isEmpty()) {
						totalCount = (Integer) result.get(0);
					} else
						totalCount = new Integer(0);

					SQLQuery query = session.createSQLQuery(queryStr);
					
					Iterator it=entityMap.keySet().iterator();
					while(it.hasNext()){
						String key = StaticMethod.nullObject2String(it.next());
						String value=StaticMethod.nullObject2String(entityMap.get(key));
						query.addScalar(value,Hibernate.STRING);
					}

					query.addEntity(CommonFaultTask.class);
					

					if (pageSize.intValue() != -1) {
						query.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
						query.setMaxResults(pageSize.intValue());
					}
					List resultList = query.list();
					List newList = new ArrayList();
					for (int i = 0; resultList != null && i < resultList.size(); i++) {
						Object[] o = (Object[]) resultList.get(i);
						if (o != null && o.length > 0) {
							Object[] temObject = new Object[o.length];
							temObject[0] = o[6];
							temObject[1] = o[0];
							temObject[2] = o[1];
							temObject[3] = o[2];
							temObject[4] = o[3];
							temObject[5] = o[4];
							temObject[6] = o[5];
							newList.add(temObject);
						}
					}

					map.put("taskTotal", totalCount);
					map.put("taskList", newList);
					
				} catch (Exception e) {
					System.out.println("-------task list error!---------");
					e.printStackTrace();
					throw new HibernateException("task list error");
				}
				return map;
			}
		};
		return (HashMap) getHibernateTemplate().execute(callback);
	}

	/**
	 * 根据count SQL语句查询
	 * 
	 * @param sql
	 *            查询语句
	 * @param curPage
	 *            分页起始
	 * @param pageSize
	 *            单页显示数量
	 * @return HashMap, 包括任务总量和任务列表
	 * @throws HibernateException
	 */
	public Integer getCountTaskBySQL(final String queryCountStr)
			throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				// 获取任务总数的查询sql
				Integer totalCount;
				try {
					Query totalQuery = session.createSQLQuery(queryCountStr);
					if (!totalQuery.list().isEmpty()) {
						totalCount = new Integer(((BigDecimal) totalQuery.list().get(0)).intValue());
					} else {
						totalCount = new Integer(0);
					}
				} catch (Exception e) {
					System.out.println("-------task list error!---------");
					e.printStackTrace();
					throw new HibernateException("task list error");
				}
				return totalCount;
			}
		};
		return (Integer) getHibernateTemplate().execute(callback);
	}
	
	/**
     * 根据SQL语句查询任务信息, 并进行分页处理
     * @param hsql 查询语句
     * @param curPage 分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
	public HashMap getTaskListBySQL(final String queryStr, 
	          final Integer curPage,final Integer pageSize)
	          throws HibernateException {   	   
	   HibernateCallback callback = new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {        
            //获取任务总数的查询sql
        	HashMap map = new HashMap();
        try{
		    Query query = session.createSQLQuery(queryStr).addEntity(CommonFaultTask.class)
		    												  .addScalar("mainNetSortOne")
		    												  .addScalar("mainNetSortTwo")
		    												  .addScalar("mainNetSortThree")
		    												  .addScalar("mainFaultResponseLevel")
														      .addScalar("mainNetName")
			    											  .addScalar("title")
			    											  .addScalar("revert")
			    											  .addScalar("perAllocatedUser")
			    											  .addScalar("mainAlarmSolveDate", Hibernate.STRING)
			    											  .addScalar("mainCheckStatus");
			    											  //.addScalar("mainAlarmSolveDate");
		    if(pageSize.intValue()!=-1)   { 
		    	System.out.println(pageSize.intValue()* (curPage.intValue()));
		    	System.out.println(pageSize.intValue());
			    query.setFirstResult(pageSize.intValue()* (curPage.intValue())); 
			    query.setMaxResults(pageSize.intValue());
		    }
		    List resultList = query.list();
		    map.put("taskList", resultList);
        }
        catch(Exception e){
        	System.out.println("-------task list error!---------");
        	e.printStackTrace();
        	throw new HibernateException("task list error");
        }
		    return map;
        }
     };
     return (HashMap) getHibernateTemplate().execute(callback);
	}
	
	/**
	    * @author qinmin 
	    * 2009-07-29
	    * @param title 主题 
	    * @param mainId main表主键
	    * 针对故障工单,修改task记录的主题信息
	    */
	   public void updateTitleByMainId(final String mainId, final String title) throws HibernateException{
		   HibernateCallback callback = new HibernateCallback() {
		        public Object doInHibernate(Session session) throws HibernateException {        
		        try{
		           String hsql="update CommonFaultTask task set task.title=:title where task.sheetKey=:sheetKey";
		           Query query = session.createQuery(hsql);
		           query.setParameter("title", title); 
		           query.setParameter("sheetKey", mainId);
		           query.executeUpdate();
		        }
		        catch(Exception e){
		        	e.printStackTrace();
		        	throw new HibernateException("updateTitleByMainId error,error info:"+e.getMessage());
		         }	
		         return "";
		        }
		     };
		     getHibernateTemplate().execute(callback);
	   }
	   
		public HashMap getTasksomelengthListBySQL(final String queryStr, final Integer startIndex, final Integer length) throws HibernateException {
			 HibernateCallback callback = new HibernateCallback() {
			        public Object doInHibernate(Session session) throws HibernateException {        
			            //获取任务总数的查询sql
			        	HashMap map = new HashMap();
			        try{
			        	int sql_distinct = queryStr.indexOf("distinct");
			        	
			    		int sql_index = queryStr.indexOf("from");
			    		int sql_orderby = queryStr.indexOf("order by");

			    		String queryCountStr;
			    		if (sql_distinct > 0){
			    			int sql_comma = (queryStr.substring(sql_distinct, sql_index)).indexOf(",");
			    			if(sql_comma>0){
			     			queryCountStr = "select count("
			    					+ queryStr.substring(sql_distinct, sql_distinct+sql_comma) + ") ";
			    			}else{
			    				queryCountStr = "select count("
			    					+ queryStr.substring(sql_distinct, sql_index) + ") ";
			    			}
			    		}
			    		else{
			    			queryCountStr = "select count(*) ";
			    		}
			    		if (sql_orderby > 0)
			    			queryCountStr += queryStr.substring(sql_index, sql_orderby);
			    		else
			    			queryCountStr += queryStr.substring(sql_index);
			    		  		
				        Integer totalCount;
				        
				        Query totalQuery = session.createQuery(queryCountStr);
				        List result = totalQuery.list();
						if (result!=null&&!result.isEmpty()) {
							totalCount = (Integer) result.get(0);
						} else
							totalCount = new Integer(0);
				        
				        
					    Query query = session.createQuery(queryStr);
					    if(length.intValue()!=-1)   { 
					    	query.setFirstResult(startIndex.intValue());	
					    	query.setMaxResults(length.intValue());
					    }
					    List resultList = query.list();
					    
					    map.put("taskTotal", totalCount);
					    map.put("taskList", resultList);
			        }
			        catch(Exception e){
			        	System.out.println("-------task list error!---------");
			        	e.printStackTrace();
			        	throw new HibernateException("task list error");
			        }
					    return map;
			        }
			     };
			     return (HashMap) getHibernateTemplate().execute(callback);
		}
		/**
		 * 根据sql分页查询
		 * add by weichao 201501515
		 * @param queryStr
		 * @param curPage
		 * @param pageSize
		 * @return
		 * @throws HibernateException
		 */
		public HashMap getTasksListBySQL(final String queryStr, final Integer startIndex, final Integer length)
				throws HibernateException {
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					// 获取任务总数的查询sql
					HashMap map = new HashMap();
					try {
						int sql_distinct = queryStr.indexOf("distinct");

						int sql_index = queryStr.indexOf("from");
						int sql_orderby = queryStr.indexOf("order by");

						String queryCountStr;
						if (sql_distinct > 0) {
							int sql_comma = (queryStr.substring(sql_distinct, sql_index)).indexOf(",");
							if (sql_comma > 0) {
								queryCountStr = "select count(" + queryStr.substring(sql_distinct, sql_distinct + sql_comma) + ") ";
							} else {
								queryCountStr = "select count(" + queryStr.substring(sql_distinct, sql_index) + ") ";
							}
						} else {
							queryCountStr = "select count(*) ";
						}
						if (sql_orderby > 0)
							queryCountStr += queryStr.substring(sql_index, sql_orderby);
						else
							queryCountStr += queryStr.substring(sql_index);

						Integer totalCount;

						Query totalQuery = session.createSQLQuery(queryCountStr);
						List result = totalQuery.list();
						if (result != null && !result.isEmpty()) {
							totalCount = new Integer(((BigDecimal) totalQuery.list().get(0)).intValue());
						} else
							totalCount = new Integer(0);

						Query query = session.createSQLQuery(queryStr).addEntity(CommonFaultMain.class);
						if (length.intValue() != -1) {
							query.setFirstResult(startIndex.intValue());
							query.setMaxResults(length.intValue());
						}
						List resultList = query.list();

						map.put("taskTotal", totalCount);
						map.put("taskList", resultList);
					} catch (Exception e) {
						System.out.println("-------task list error!---------");
						e.printStackTrace();
						throw new HibernateException("task list error");
					}
					return map;
				}
			};
			return (HashMap) getHibernateTemplate().execute(callback);

		}
		
		/**
	     * add by liuyonggang 201700601 显示不同情况下的待办列表
	     * @param hsql 查询语句
	     * @param curPage 分页起始
	     * @param pageSize 单页显示数量
	     * @return HashMap, 包括任务总量和任务列表
	     * @throws HibernateException
	     */
		public HashMap getEeveundo(final String queryStr, 
		          final Integer curPage,final Integer pageSize)
		          throws HibernateException {   	   
		   HibernateCallback callback = new HibernateCallback() {
	        public Object doInHibernate(Session session) throws HibernateException {
	        	Integer totalCount;
	        	
	        	String queryCountStr = "select count(*) from ("+queryStr+") ";
	        	
				Query totalQuery = session.createSQLQuery(queryCountStr);
				List result = totalQuery.list();
				if (result != null && !result.isEmpty()) {
					totalCount = new Integer(((BigDecimal) totalQuery.list().get(0)).intValue());
				} else{
					totalCount = new Integer(0);
				}
	            //获取任务总数的查询sql
	        	HashMap map = new HashMap();
	        try{
			    Query query = session.createSQLQuery(queryStr);//.addEntity(CommonFaultTask.class)
//			    												  .addScalar("mainNetSortOne")
//			    												  .addScalar("mainNetSortTwo")
//			    												  .addScalar("mainNetSortThree")
//			    												  .addScalar("mainFaultResponseLevel")
//															      .addScalar("mainNetName")
//				    											  .addScalar("revert")
//				    											  .addScalar("perAllocatedUser")
//				    											  .addScalar("mainAlarmSolveDate", Hibernate.STRING)
//				    											  .addScalar("mainCheckStatus");
				    											  //.addScalar("mainAlarmSolveDate");
			    if(pageSize.intValue()!=-1)   { 
			    	System.out.println(pageSize.intValue()* (curPage.intValue()));
			    	System.out.println(pageSize.intValue());
				    query.setFirstResult(pageSize.intValue()* (curPage.intValue())); 
				    query.setMaxResults(pageSize.intValue());
			    }
			    List resultList = query.list();
			    map.put("taskTotal", totalCount);
			    map.put("taskList", resultList);
	        }
	        catch(Exception e){
	        	System.out.println("-------task list error!---------");
	        	e.printStackTrace();
	        	throw new HibernateException("task list error");
	        }
			    return map;
	        }
	     };
	     return (HashMap) getHibernateTemplate().execute(callback);
		}
		
	}

