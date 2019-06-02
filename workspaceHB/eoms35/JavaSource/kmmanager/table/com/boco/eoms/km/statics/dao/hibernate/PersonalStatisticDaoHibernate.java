package com.boco.eoms.km.statics.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.km.statics.dao.PersonalStatisticDao;

/**
 * <p> Title:知识贡献情况统计 </p>
 * <p> Description:知识贡献情况统计 </p>
 * <p> Mon Mar 30 14:39:15 CST 2009 </p>
 * @author zhangxiaobo
 * @version 0.1
 * 
 */

public class PersonalStatisticDaoHibernate extends BaseDaoHibernate implements PersonalStatisticDao{
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.km.statics.dao.PersonalStatisticDao#getPersonalStatistics(java.lang.Integer, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public Map getPersonalStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer countStr = new StringBuffer("select count(distinct t.operate_user) as count ");
		    	countStr.append("from km_operate_date_log t ");
		    	countStr.append("where t.operate_date BETWEEN ? AND ?");
		    	//System.out.println("sql = " + countStr.toString());
		    	
		    	SQLQuery countQuery = session.createSQLQuery(countStr.toString());
		    	countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
		    	countQuery.setDate(0, startDate);
		    	countQuery.setDate(1, endDate);		    	
		    	Integer total = (Integer)countQuery.uniqueResult(); 

		    	List result = new ArrayList();
		    	if(total.intValue() >0){
			    	StringBuffer queryStr = new StringBuffer("select t.operate_user as operateUser, "); //用户姓名
			    	queryStr.append("t.operate_dept as operateDept, "); //用户部门
			    	queryStr.append("sum(t.add_count) as addCount, " ); //新增知识数
			    	queryStr.append("sum(t.modify_count) as modifyCount, "); //修改知识数
			    	queryStr.append("sum(t.over_count) as overCount, "); //失效知识数
			    	queryStr.append("sum(t.delete_count) as deleteCount, "); //删除知识数
			    	queryStr.append("sum(t.use_count) as useCount, "); //使用知识数
			    	queryStr.append("sum(t.up_count) as upCount "); //上传文件数
			    	queryStr.append("from km_operate_date_log t ");
			    	queryStr.append("where t.operate_date BETWEEN ? AND ? ");
			    	queryStr.append("group by t.operate_user, t.operate_dept");
			    	//System.out.println("sql = " + queryStr.toString());
			    	
					SQLQuery query = session.createSQLQuery(queryStr.toString());
					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					query.addScalar("operateUser", org.hibernate.Hibernate.STRING);
					query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
					query.addScalar("addCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("modifyCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("overCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("deleteCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("useCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("upCount", org.hibernate.Hibernate.INTEGER);

					query.setDate(0, startDate);
					query.setDate(1, endDate);				
					result = query.list();
				}			
				
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.km.statics.dao.PersonalStatisticDao#getPersonalStatistics(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public Map getPersonalStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer countStr = new StringBuffer("select count(distinct t.operate_user) as count ");
		    	countStr.append("from km_operate_date_log t ");
		    	countStr.append("where t.operate_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
		    	//System.out.println("sql = " + countStr.toString());
		    	
		    	SQLQuery countQuery = session.createSQLQuery(countStr.toString());
		    	countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
		    	countQuery.setString(0, startDate);
		    	countQuery.setString(1, endDate);		    	
		    	Integer total = (Integer)countQuery.uniqueResult(); 

		    	List result = new ArrayList();
		    	if(total.intValue() >0){
			    	StringBuffer queryStr = new StringBuffer("select t.operate_user as operateUser, "); //用户姓名
			    	queryStr.append("t.operate_dept as operateDept, "); //用户部门
			    	queryStr.append("sum(t.add_count) as addCount, " ); //新增知识数
			    	queryStr.append("sum(t.modify_count) as modifyCount, "); //修改知识数
			    	queryStr.append("sum(t.over_count) as overCount, "); //失效知识数
			    	queryStr.append("sum(t.delete_count) as deleteCount, "); //删除知识数
			    	queryStr.append("sum(t.use_count) as useCount, "); //使用知识数
			    	queryStr.append("sum(t.up_count) as upCount "); //上传文件数
			    	queryStr.append("from km_operate_date_log t ");
			    	queryStr.append("where t.operate_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
			    	queryStr.append("group by t.operate_user, t.operate_dept");
			    	//System.out.println("sql = " + queryStr.toString());
			    	
					SQLQuery query = session.createSQLQuery(queryStr.toString());
					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					query.addScalar("operateUser", org.hibernate.Hibernate.STRING);
					query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
					query.addScalar("addCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("modifyCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("overCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("deleteCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("useCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("upCount", org.hibernate.Hibernate.INTEGER);
					query.setString(0, startDate);
					query.setString(1, endDate);				
					result = query.list();
				}
				
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.km.statics.dao.PersonalStatisticDao#getDeptStatistics(java.lang.Integer, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public Map getDeptStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer countStr = new StringBuffer("select count(distinct t.operate_dept) as count ");
		    	countStr.append("from km_operate_date_log t ");
		    	countStr.append("where t.operate_date BETWEEN ? AND ?");
		    	//System.out.println("sql = " + countStr.toString());
		    	
		    	SQLQuery countQuery = session.createSQLQuery(countStr.toString());
		    	countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
		    	countQuery.setDate(0, startDate);
		    	countQuery.setDate(1, endDate);		    	
		    	Integer total = (Integer)countQuery.uniqueResult(); 
		    	
		    	List result = new ArrayList();
		    	if(total.intValue() >0){
			    	StringBuffer queryStr = new StringBuffer("select t.operate_dept as operateDept, "); //用户部门
			    	queryStr.append("sum(t.add_count) as addCount, " ); //新增知识数
			    	queryStr.append("sum(t.modify_count) as modifyCount, "); //修改知识数
			    	queryStr.append("sum(t.over_count) as overCount, "); //失效知识数
			    	queryStr.append("sum(t.delete_count) as deleteCount, "); //删除知识数
			    	queryStr.append("sum(t.use_count) as useCount, "); //使用知识数
			    	queryStr.append("sum(t.up_count) as upCount "); //上传文件数
			    	queryStr.append("from km_operate_date_log t ");
			    	queryStr.append("where t.operate_date BETWEEN ? AND ? ");
			    	queryStr.append("group by t.operate_dept");
			    	//System.out.println("sql = " + queryStr.toString());
			    	
					SQLQuery query = session.createSQLQuery(queryStr.toString());
					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
					query.addScalar("addCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("modifyCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("overCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("deleteCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("useCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("upCount", org.hibernate.Hibernate.INTEGER);
					query.setDate(0, startDate);
					query.setDate(1, endDate);				
					result = query.list();					
		    	}
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.km.statics.dao.PersonalStatisticDao#getDeptStatistics(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public Map getDeptStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer countStr = new StringBuffer("select count(distinct t.operate_dept) as count ");
		    	countStr.append("from km_operate_date_log t ");
		    	countStr.append("where t.operate_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
		    	//System.out.println("sql = " + countStr.toString());
		    	
		    	SQLQuery countQuery = session.createSQLQuery(countStr.toString());
		    	countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
		    	countQuery.setString(0, startDate);
		    	countQuery.setString(1, endDate);		    	
		    	Integer total = (Integer)countQuery.uniqueResult(); 
		    	
		    	List result = new ArrayList();
		    	if(total.intValue() >0){
			    	StringBuffer queryStr = new StringBuffer("select t.operate_dept as operateDept, "); //用户部门
			    	queryStr.append("sum(t.add_count) as addCount, " ); //新增知识数
			    	queryStr.append("sum(t.modify_count) as modifyCount, "); //修改知识数
			    	queryStr.append("sum(t.over_count) as overCount, "); //失效知识数
			    	queryStr.append("sum(t.delete_count) as deleteCount, "); //删除知识数
			    	queryStr.append("sum(t.use_count) as useCount, "); //使用知识数
			    	queryStr.append("sum(t.up_count) as upCount "); //上传文件数
			    	queryStr.append("from km_operate_date_log t ");
			    	queryStr.append("where t.operate_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
			    	queryStr.append("group by t.operate_dept");
			    	//System.out.println("sql = " + queryStr.toString());
			    	
					SQLQuery query = session.createSQLQuery(queryStr.toString());
					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
					query.addScalar("addCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("modifyCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("overCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("deleteCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("useCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("upCount", org.hibernate.Hibernate.INTEGER);
					query.setString(0, startDate);
					query.setString(1, endDate);				
					result = query.list();
				}
				
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
}